package by.mercom.cleanBarCodes;

import javax.swing.*;

/**
 * Created by dm13y on 9/18/14.
 */
public class Main {
    public static void main(String[] args) {
        //set default lookAndFeel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows XP".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
        //load properties
        new Propert();
        SignIn.main(null);
    }

    public static void sendErrMSG(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Ошибка!", JOptionPane.ERROR_MESSAGE);
    }

    public static void sendInfoMSG(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Информация!", JOptionPane.INFORMATION_MESSAGE);
    }

}
