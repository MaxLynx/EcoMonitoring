package databases3.ecoMonitoring.view;

import databases3.ecoMonitoring.controller.dataAccess.DAO;
import databases3.ecoMonitoring.model.DiagnosisCard;
import databases3.ecoMonitoring.model.PollutionCard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayPollutionCardsForm extends JFrame {
    private JTable table1;
    private JButton OKButton;
    private JPanel rootPanel;
    private DAO dao;
    private List<PollutionCard> cards;

    public DisplayPollutionCardsForm(){

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
        cards = dao.getPollutionCards();
        String[] columnNames = {"LOCATION",
                "DATE",
                "POLLUTANT",
                "CONCENTRATION",
                "CHEMICAL GROUP",
                "ADDRESS"};
        Object[][] data = new Object [cards.size()][6];
        int i = 0;
        for(PollutionCard card : cards){
            data[i][0] = card.getLocation();
            data[i][1] = card.getDate();
            data[i][2] = card.getPollutant().getName();
            data[i][3] = card.getConcentration();
            data[i][4] = card.getChemicalGroup();
            data[i][5] = card.getAddress();
            i++;
        }
        table1 = new JTable(data, columnNames);
    }
}
