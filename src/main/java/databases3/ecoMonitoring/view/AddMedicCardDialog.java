package databases3.ecoMonitoring.view;

import databases3.ecoMonitoring.controller.dataAccess.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddMedicCardDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField5;
    private JTextField textField4;
    private JList list1;
    private JTextField textField2;
    private JTextField textField6;

    public AddMedicCardDialog() {
        setContentPane(contentPane);
        setUndecorated(true);
        setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        setVisible(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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
        new DAO().addMedicCard(textField1.getText(), textField3.getText(),
                Integer.parseInt(textField5.getText()), textField4.getText(),
                list1.getSelectedValue().toString(), textField2.getText(), textField6.getText());
        dispose();
    }

    private void onCancel() {

        dispose();
    }

    private void createUIComponents() {
        String [] elements = {"0", "A", "B", "AB"};
        list1 = new JList(elements);
    }
}
