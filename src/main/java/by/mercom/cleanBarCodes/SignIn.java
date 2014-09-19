package by.mercom.cleanBarCodes;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;

public class SignIn extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField passwordField1;
    private JTextField saTextField;
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SignIn dialog = new SignIn();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
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
