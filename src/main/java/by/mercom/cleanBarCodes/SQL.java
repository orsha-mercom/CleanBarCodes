package by.mercom.cleanBarCodes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by dm13y on 9/18/14.
 */
public class SQL {
    private static String ipAddress;
    private static String login;
    private static String pass;

    public static boolean testConnection(String ipAddress, String login, String pass) {
        String connString = Propert.getConnectionString(ipAddress, login, pass);
        try {
            Connection conn = DriverManager.getConnection(connString);
            conn.close();
            return true;
        } catch (SQLException sql_ex) {
            sql_ex.printStackTrace();
        }
        return false;
    }
    public static String getGoodsOnBarCode(String tfOldBarCodeText) {
        return null;
    }

    public static void setNewBarCode(String tfOldBarCodeText, String tfNewBarCodeText) {

    }
}
