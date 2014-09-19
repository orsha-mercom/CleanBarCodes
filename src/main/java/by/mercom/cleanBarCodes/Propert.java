package by.mercom.cleanBarCodes;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by dm13y on 9/18/14.
 */
public class Propert {
    public final static String FILE_SQL_PROPERTIES = "SQL.properties";
    public static java.util.Properties prop;
    public Propert() {
        prop = new java.util.Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream(FILE_SQL_PROPERTIES));
        } catch (IOException io_ex) {
            io_ex.printStackTrace();
        }
    }

    public static String getConnectionString(String ipAddress, String login, String password) {
        return MessageFormat.format(prop.getProperty("connString"), ipAddress, login, password);
    }

}
