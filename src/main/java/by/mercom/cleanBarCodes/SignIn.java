package by.mercom.cleanBarCodes;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class SignIn extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField tfPass;
    private JTextField tfLogin;
    private JFormattedTextField tfIPAddress;

    public SignIn() {

        tfIPAddress.setText("192168018100");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (!SQL.testConnection(tfIPAddress.getValue().toString(), tfLogin.getText(), String.valueOf(tfPass.getPassword()))) {
            Main.sendErrMSG("Невозможно подключиться к БД!");
            return;
        }
        SQL.setConnParam(tfIPAddress.getValue().toString(), tfLogin.getText(), String.valueOf(tfPass.getPassword()));
        dispose();
        new GUI();
    }

    private void onCancel() {
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        SignIn dialog = new SignIn();
        dialog.pack();
        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation((int)(dm.getWidth() - dialog.getWidth()) / 2, (int)(dm.getHeight() - dialog.getHeight()) / 2);
        dialog.setVisible(true);
    }

    private void createUIComponents() {
        try {
            MaskFormatter maskFormatter = new MaskFormatter("###.###.###.###");
            maskFormatter.setPlaceholderCharacter(' ');
            tfIPAddress = new JFormattedTextField(maskFormatter);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }
}
