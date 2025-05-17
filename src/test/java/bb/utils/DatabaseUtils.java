package bb.utils;

import bb.common.BasePage;
import net.serenitybdd.core.Serenity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    public static Connection createConnect(String url, String user, String pass) throws SQLException {
        Connection connect = DriverManager.getConnection(url, user, pass);
        connect.setAutoCommit(false);
        return connect;
    }

    public static String getAuthCode(String cif) {
        try {
            String sqlQuery = "select * from CUSTOMERS_PERSIST_SERVICE_E.customer where CIF_NUMBER = '" + cif + "'";
            Connection connect = createConnect("jdbc:oracle:thin:@10.1.14.214:1525:xe", "hydro_reader", "hydro_reader");
            Statement st = connect.createStatement();
            ResultSet resultSet = st.executeQuery(sqlQuery);
            resultSet.next();
            return resultSet.getString("HIGHRISK_AUTH_METHOD");
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching auth code for CIF: " + cif, e);
        }
    }

    public static void setAuthMethod(String cif, String authMethod) {
        try {
            String convert;
            if (authMethod.toLowerCase().contains("sms")) {
                convert = "sms_otp";
            } else if (authMethod.toLowerCase().contains("hard_token")) {
                convert = "hard_token_vas";
            } else {
                convert = "etoken";
            }
            String sqlQuery = "UPDATE CUSTOMERS_PERSIST_SERVICE_E.customer SET highrisk_auth_method = '" + convert + "' WHERE cif_number = '" + cif + "'";
            Connection connection = createConnect("jdbc:oracle:thin:@10.1.14.214:1525:xe", "autotest", "autotest123");
            Statement st = connection.createStatement();
            st.executeUpdate(sqlQuery);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error while change to authMethod: " + cif, e);
        }
    }

    public static List<String> getAccountsBlockInContacts(String cif, String creditor) {
        try {
            List<String> listAccount = new ArrayList<>();
            String query = "Select DISTINCT ACCOUNT_NUMBER from FUND_TRANSFER_PANDP_SERVICE_E.FUND_TRANSFER_CONTACTS where CIF = '" + cif + "' AND CREDITOR_NAME = '" + creditor + "'";
            Connection connect = createConnect("jdbc:oracle:thin:@10.1.14.214:1525:xe", "hydro_reader", "hydro_reader");
            Statement st = connect.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                listAccount.add(resultSet.getString("ACCOUNT_NUMBER"));
            }
            return listAccount;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void setRecordStatus(String alias, String status) {
        try {
            Connection connect = createConnect("jdbc:oracle:thin:@10.1.14.214:1525:xe", "autotest", "autotest123");
            Statement st = connect.createStatement();
            String query = "UPDATE customers_persist_service_e.alias_management SET record_status = '" + status + "' WHERE alias_name = '" + alias + "'";
            st.executeUpdate(query);
            connect.commit();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void setKeyStatus(String cif, String key) {
        try {
            Connection connect = createConnect("jdbc:oracle:thin:@10.1.14.214:1525:xe", "autotest", "autotest123");
            Statement st = connect.createStatement();
            String sqlQuery = "UPDATE CUSTOMERS_PERSIST_SERVICE_E.PERSONAL_DISPLAY_CONFIG SET key = '" + key + "' WHERE CIF = '" + cif + "' and TYPE = 'HOME' and VALUE = 'FUND_TRANSFER_MODE'";
            st.executeUpdate(sqlQuery);
            connect.commit();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static String getKeyStatus(String cif) {
        try {
            Connection connect = createConnect("jdbc:oracle:thin:@10.1.14.214:1525:xe", "autotest", "autotest123");
            Statement st = connect.createStatement();
            String sqlQuery = "select * from CUSTOMERS_PERSIST_SERVICE_E.PERSONAL_DISPLAY_CONFIG where CIF = '" + cif + "' and TYPE = 'HOME' and VALUE = 'FUND_TRANSFER_MODE'";
            Serenity.recordReportData().withTitle("sqlQuery").andContents(sqlQuery);
            ResultSet resultSet = st.executeQuery(sqlQuery);
            resultSet.next();
            return resultSet.getString("KEY");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void deleteAccountBlockInContacts(String cif) {
        try {
            Connection connect = createConnect("jdbc:oracle:thin:@10.1.14.214:1525:xe", "autotest", "autotest123");
            Statement st = connect.createStatement();
            String query = "DELETE FROM FUND_TRANSFER_PANDP_SERVICE_E.FUND_TRANSFER_CONTACTS WHERE CIF = '" + cif + "'";
            st.executeUpdate(query);
            connect.commit();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void deleteGroupTingTing(String cif) {
        try {
            Connection connect = createConnect("jdbc:oracle:thin:@10.1.14.214:1525:xe", "autotest", "autotest123");
            Statement st = connect.createStatement();
            String query = "DELETE FROM CUSTOMERS_PERSIST_SERVICE_E.ALIAS_GROUP_NOTIFY WHERE CIF = '" + cif + "'";
            st.executeUpdate(query);
            connect.commit();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void deletePayRecurringBills(String cusCode) {
        try {
            Connection connect = createConnect("jdbc:oracle:thin:@10.1.14.214:1525:xe", "autotest", "autotest123");
            Statement st = connect.createStatement();
            String query = "Delete from billpay_persistence_service_E.auto_billing where cus_code = '" + cusCode + "'";
            st.executeUpdate(query);
            connect.commit();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static String getRefCodeOfStatementTransaction(String accountNumber) {
        String sqlQuery = "SELECT esb_ref_code FROM fee_persistence_service_e.fee_transaction " +
                "WHERE service_code = 'TPBANK_ORDER_STATEMENT' " +
                "AND debtor_account_number = '" + accountNumber + "' " +
                "ORDER BY created_date DESC FETCH FIRST 1 ROWS ONLY";
        LoggerUtil.logInfo("sqlQuery: " + sqlQuery);
        BasePage basePage = new BasePage();
        basePage.waitAMilliSeconds(2000);
        try {
            Connection connect = createConnect("jdbc:oracle:thin:@10.1.14.214:1525:xe", "autotest", "autotest123");
            Statement st = connect.createStatement();
            ResultSet resultSet = st.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return resultSet.getString("ESB_REF_CODE");
            } else {
                throw new RuntimeException("Account number not found: " + accountNumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching transaction code for accountNumber: " + accountNumber, e);
        }
    }

    public static void updateTypeNotify(String phoneNumber, String typeNotify) {
        try {
            Connection connect = createConnect("jdbc:oracle:thin:@10.1.14.214:1525:xe", "autotest", "autotest123");
            Statement st = connect.createStatement();
            String sqlQuery = "UPDATE CUSTOMERS_PERSIST_SERVICE_E.ALIAS_GROUP_NOTIFY_MEMBER " +
                    "SET TYPE_NOTIFY = '" + typeNotify + "' " +
                    "WHERE PHONE_NUMBER = '" + phoneNumber + "' " +
                    "AND UPDATE_DATE = (" +
                    "SELECT MAX(UPDATE_DATE) " +
                    "FROM CUSTOMERS_PERSIST_SERVICE_E.ALIAS_GROUP_NOTIFY_MEMBER " +
                    "WHERE PHONE_NUMBER = '" + phoneNumber + "')";
            System.out.println("sqlQuery: " + sqlQuery);
            st.executeUpdate(sqlQuery);
            connect.commit();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
