package databases3.ecoMonitoring.view;

import databases3.ecoMonitoring.controller.dataAccess.DAO;
import databases3.ecoMonitoring.model.Diagnosis;
import databases3.ecoMonitoring.model.Doctor;
import databases3.ecoMonitoring.model.Pollutant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class AddDiagnosisCardDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JList list1;
    private JList list2;
    private DAO dao;
    private List<Doctor> doctors;
    private List<Diagnosis> diagnoses;


    public AddDiagnosisCardDialog() {
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
        int doctorID = doctors.get(0).getID();
        for(Doctor doctor : doctors){
            if(doctor.getName().equals(list2.getSelectedValue().toString())){
                doctorID = doctor.getID();
                break;
            }
        }
        int diagnosisID = diagnoses.get(0).getID();
        for(Diagnosis diagnosis : diagnoses){
            if(diagnosis.getName().equals(list1.getSelectedValue().toString())){
                diagnosisID = diagnosis.getID();
                break;
            }
        }
        new DAO().addDiagnosisCard(Integer.parseInt(textField1.getText()),
                doctorID, diagnosisID,
                new Date(textField2.getText()));
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        AddDiagnosisCardDialog dialog = new AddDiagnosisCardDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


    private void createUIComponents() {
        dao = new DAO();
        doctors = dao.getDoctors();
        java.util.List<String> elements1 = new ArrayList<String>();
        for(Doctor doctor : doctors){
            elements1.add(doctor.getName());
        }
        list2 = new JList(elements1.toArray());
        diagnoses = dao.getDiagnoses();
        java.util.List<String> elements2 = new ArrayList<String>();
        for(Diagnosis diagnosis : diagnoses){
            elements2.add(diagnosis.getName());
        }
        list1 = new JList(elements2.toArray());
    }
}
