package databases3.ecoMonitoring.view;

import databases3.ecoMonitoring.controller.dataAccess.DAO;
import databases3.ecoMonitoring.model.PollutionCard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartForm extends JFrame{
    private JButton CREATENEWMEDICCARDButton; //TODO
    private JPanel rootPanel;
    private JButton CREATENEWPOLLUTIONCARDButton; //TODO
    private JButton CREATENEWDIAGNOSISCARDButton; //TODO
    private JButton ADDDOCTORButton;
    private JButton ADDPOLLUTANTButton;
    private JButton ADDDIAGNOSISButton;
    private JButton DISPLAYDIAGNOSISCARDSButton; //TODO
    private JButton DISPLAYPOLLUTIONCARDSButton; //TODO
    private JButton EXITButton;
    private JButton DISPLAYREGIONSButton;
    private DAO dao;

    public StartForm(){
        setContentPane(rootPanel);
        dao = new DAO();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        CREATENEWMEDICCARDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMedicCardDialog();
            }
        });
        CREATENEWPOLLUTIONCARDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPollutionCardDialog();
            }
        });
        CREATENEWDIAGNOSISCARDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDiagnosisCardDialog();
            }
        });
        ADDDOCTORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDoctorDialog();
            }
        });
        ADDPOLLUTANTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPollutantDialog();
            }
        });
        ADDDIAGNOSISButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDiagnosisDialog();
            }
        });
        DISPLAYDIAGNOSISCARDSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayDiagnosisCardsForm();
            }
        });
        DISPLAYPOLLUTIONCARDSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             new DisplayPollutionCardsForm();
            }
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        DISPLAYREGIONSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AnalysisForm();
            }
        });
    }
}
