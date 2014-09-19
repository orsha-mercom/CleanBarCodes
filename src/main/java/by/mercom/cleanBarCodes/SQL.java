package by.mercom.cleanBarCodes;

import javax.swing.*;
import java.sql.*;
import java.text.MessageFormat;

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
    public static String getGoodsNameOnBarCode(String tfOldBarCodeText) {
        String result = "";
        String SQLQuery = "SELECT tmc.name FROM tmc WHERE tmc.id IN " +
                "(SELECT tmcid FROM tmcbarcodes WHERE barcode = {0})";
        SQLQuery = MessageFormat.format(SQLQuery, tfOldBarCodeText);
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(Propert.getConnectionString(ipAddress, login, pass));
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQLQuery);
            while (rs.next()) {
                result = rs.getString("name");
                break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException ex1) {
            }
        }
        return result;
    }

    private static boolean isExistBarcode(Connection conn, String barcode) {
        Statement st = null;
        Boolean result = false;
        try {
            String SQLQuery = "SELECT 1 FROM tmcbarcodes WHERE barcode = {0}";
            SQLQuery = MessageFormat.format(SQLQuery, barcode);
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQLQuery);
            while (rs.next()) {
                result = true;
                break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                st.close();
            } catch (SQLException ex1) {

            }
            return result;
        }
    }

    public static void setNewBarCode(String tfOldBarCodeText, String tfNewBarCodeText) {
        String SQLQuery_changeBarCodeInWHCard = "UPDATE whcard SET barcode = {0} WHERE BarCode = {1} AND NomenclID IN " +
                "(SELECT tmcid FROM TMCBarCodes WHERE barcode = {1})";
        SQLQuery_changeBarCodeInWHCard = MessageFormat.format(SQLQuery_changeBarCodeInWHCard, tfNewBarCodeText, tfOldBarCodeText);
        String SQLQuery_changeBarCodeInTMCBarcodes;
        if (tfNewBarCodeText.equals("0")) { //Delete barcode int TMCBarcodes
            SQLQuery_changeBarCodeInTMCBarcodes = "DELETE FROM tmcbarcodes WHERE barcode = {1}";
        } else {
            SQLQuery_changeBarCodeInTMCBarcodes = "UPDATE tmcbarcodes SET barcode = {0} WHERE barcode = {1}";
        }
        SQLQuery_changeBarCodeInTMCBarcodes = MessageFormat.format(SQLQuery_changeBarCodeInTMCBarcodes, tfNewBarCodeText, tfOldBarCodeText);
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(Propert.getConnectionString(ipAddress, login, pass));
//            if (tfNewBarCodeText != "0") {
//                if (isExistBarcode(conn, tfNewBarCodeText)) {
//                    Main.sendErrMSG("Штрихкод " + tfNewBarCodeText + " уже существует!");
//                    return;
//                }
//            }
            st = conn.createStatement();
            int updCountWHCard = st.executeUpdate(SQLQuery_changeBarCodeInWHCard);
            int updCountTMCBarcodes = st.executeUpdate(SQLQuery_changeBarCodeInTMCBarcodes);
            String msg = "<html><body><p>WHCard обновлено: " + updCountWHCard + "<br>" +
                    "TMCBarCodes обновлено/удалено: " + updCountTMCBarcodes + "</body></html>";
            Main.sendInfoMSG(msg);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException sql_ex) {

            }
        }
    }


    public static void setConnParam(String ipAddr, String lgn, String pwd) {
        ipAddress = ipAddr;
        login = lgn;
        pass = pwd;
    }
}
