package by.mercom.cleanBarCodes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by dm13y on 9/18/14.
 */
public class GUI {
    private JTextField tfOldBarCode;
    private JTextField tfNameGood;
    private JTextField tfNewBarCode;
    private JButton btnFind;
    private JButton btnChange;
    private JPanel mainPanel;
    private JFrame frame;


    public GUI() {
        tfOldBarCode.addKeyListener(new checkNumTyped());
        tfNewBarCode.addKeyListener(new checkNumTyped());
        frame = new JFrame("Изменение ШК в PSTrade");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)(dm.getWidth() - frame.getWidth()) / 2, (int)(dm.getHeight() - frame.getHeight()) / 2);
        frame.setVisible(true);
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfOldBarCode.getText().length() == 0) {
                    return;
                }
                tfNameGood.setText(SQL.getGoodsNameOnBarCode(tfOldBarCode.getText()));
            }
        });
        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfNewBarCode.getText().length() == 0) {
                    tfNewBarCode.setText("0");
                }
                SQL.setNewBarCode(tfOldBarCode.getText(), tfNewBarCode.getText());
            }
        });
    }
}

class checkNumTyped extends KeyAdapter {
    @Override
    public void keyTyped(KeyEvent e) {
        if (!Character.isDigit(e.getKeyChar())) {
            e.consume();
        }
    }
}