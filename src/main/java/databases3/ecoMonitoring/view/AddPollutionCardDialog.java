package databases3.ecoMonitoring.view;

import databases3.ecoMonitoring.controller.dataAccess.DAO;
import databases3.ecoMonitoring.model.Pollutant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddPollutionCardDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JList list1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private DAO dao;
    private List<Pollutant> pollutants;

    public AddPollutionCardDialog() {
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
        int pollutantID = pollutants.get(0).getID();
        for(Pollutant pollutant : pollutants){
            if(pollutant.getName().equals(list1.getSelectedValue().toString())){
                pollutantID = pollutant.getID();
                break;
            }
        }
        new DAO().addPollutionCard(textField1.getText(), new Date(textField2.getText()),
                pollutantID, Double.parseDouble(textField3.getText()),
                textField4.getText(), textField5.getText());
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {

        dao = new DAO();
        pollutants = dao.getPollutants();
        List<String> elements = new ArrayList<String>();
        for(Pollutant pollutant : pollutants){
            elements.add(pollutant.getName());
        }
        list1 = new JList(elements.toArray());
    }
}
