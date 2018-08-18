package databases3.ecoMonitoring.view;


import databases3.ecoMonitoring.controller.dataAccess.DAO;
import databases3.ecoMonitoring.model.DiagnosisCard;
import databases3.ecoMonitoring.model.PollutionCard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayDiagnosisCardsForm extends JFrame{
    private JTable table1;
    private JButton OKButton;
    private JPanel rootPanel;
    private DAO dao;
    private List<DiagnosisCard> cards;

    public DisplayDiagnosisCardsForm() {
        setContentPane(rootPanel);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void createUIComponents() {
        dao = new DAO();
        cards = dao.getDiagnosisCards();
        String[] columnNames = {"NAME",
                "MEDIC CARD ID",
                "AGE",
                "ADDRESS",
                "DOCTOR",
                "DIAGNOSIS",
                "DATE"};
        Object[][] data = new Object [cards.size()][7];
        int i = 0;
        for(DiagnosisCard card : cards){
            data[i][0] = card.getMedicCard().getName();
            data[i][1] = card.getMedicCard().getID();
            data[i][2] = card.getMedicCard().getAge();
            data[i][3] = card.getMedicCard().getAddress();
            data[i][4] = card.getDoctor().getName();
            data[i][5] = card.getDiagnosis().getName();
            data[i][6] = card.getDate();
            i++;
        }
        table1 = new JTable(data, columnNames);
    }
}
