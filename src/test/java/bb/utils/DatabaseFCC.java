package bb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bb.utils.Utilities;

public class DatabaseFCC {

    public static Connection createConnect() throws SQLException {
        String url = "", user = "", pass = "";
        String ENV = Utilities.getConfigValue("serenity.api.environment");
        LoggerUtil.logInfo("ENV: " + ENV);
        switch (ENV) {
            case "UAT":
                url = Utilities.getConfigValue("environments.default.fccUrl");
                user = Utilities.getConfigValue("environments.default.fccUser");
                pass = Utilities.getConfigValue("environments.default.fccPass");
                break;
            case "SIT":
                url = Utilities.getConfigValue("environments.android_SIT.fccUrl");
                user = Utilities.getConfigValue("environments.android_SIT.fccUser");
                pass = Utilities.getConfigValue("environments.android_SIT.fccPass");
                break;
        }
        return DriverManager.getConnection(url, user, pass);
    }

    public static Connection createConnect(String url, String user, String pass) throws SQLException {
        Connection connect = DriverManager.getConnection(url, user, pass);
        connect.setAutoCommit(false);
        return connect;
    }

    public static ResultSet getAccountInfo(String accountNumber) {
        String sql = "SELECT bankprd.fn_get_avail_bal(a.branch_code, a.cust_ac_no) as acy_curr_balance, TOD_LIMIT FROM bankprd.sttms_cust_account a WHERE a.CUST_AC_NO = '" + accountNumber + "'";
        try {
            Thread.sleep(1500);
            Connection connect = createConnect();
            Statement st = connect.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            return resultSet;
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException("Error while fetching account info for accountNumber: " + accountNumber, e);
        }
    }

    public static String getCurrentBalance(String accountNumber) {
        try {
            ResultSet rs = getAccountInfo(accountNumber);
            if (rs.next()) {
                String availableBalance = rs.getString("ACY_CURR_BALANCE");
                String overdraftLimit = rs.getString("TOD_LIMIT");
                if (overdraftLimit == null) {
                    return availableBalance;
                } else {
                    return String.valueOf(Long.parseLong(availableBalance) + Long.parseLong(overdraftLimit));
                }
            } else {
                throw new RuntimeException("Account number not found: " + accountNumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching Current Balance for accountNumber: " + accountNumber, e);
        }
    }

    public static HashMap<String, String> getVATAndSRVOfStatementTransaction(String accountNumber, String refCode) {
        HashMap<String, String> vatAndSrv = new HashMap<>();
        String sql = "Select * from bankprd.acvws_all_ac_entries t where t.AC_NO='" + accountNumber + "' and trn_ref_no = '" + refCode + "' order by t.LCY_AMOUNT desc";
        LoggerUtil.logInfo("sqlFCC: " + sql);
        try {
            Thread.sleep(1500);
            Connection connect = createConnect("jdbc:oracle:thin:@10.6.73.28:1521/obpp", "ebankx_fcc_dbprod2", "Tienphong#123");
            Statement st = connect.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                String lcyAmount = resultSet.getString("LCY_AMOUNT");
                String trnCode = resultSet.getString("TRN_CODE");
                vatAndSrv.put(trnCode, lcyAmount);
            }
            resultSet.close();
            st.close();
            connect.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException("Error while fetching brand code for accountNumber" + e);
        }
        return vatAndSrv;
    }

    public static List<String> getBrandCode(String refCode) {
        List<String> listBrandCode = new ArrayList<>();
        String sql = "Select * from bankprd.acvws_all_ac_entries t where trn_ref_no = '" + refCode + "' order by t.AC_ENTRY_SR_NO desc";
        try {
            Connection connect = createConnect("jdbc:oracle:thin:@10.6.73.28:1521/obpp", "ebankx_fcc_dbprod2", "Tienphong#123");
            Statement st = connect.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                String brandCode = resultSet.getString("AC_BRANCH");
                listBrandCode.add(brandCode);
            }
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching brand code for accountNumber" + e);
        }
        return listBrandCode;
    }
}
