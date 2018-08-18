package databases3.ecoMonitoring.view;

import databases3.ecoMonitoring.controller.dataAccess.DAO;
import databases3.ecoMonitoring.model.DiagnosisCard;
import databases3.ecoMonitoring.model.PollutionCard;
import databases3.ecoMonitoring.model.analysis.EcoAnalyzer;
import databases3.ecoMonitoring.model.analysis.RegionInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;


public class AnalysisForm extends JFrame {
    private JTable table1;
    private JButton OK;
    private JPanel rootPanel;
    private JButton PRINTREPORTButton;
    private String reportText;

    public AnalysisForm() {
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        PRINTREPORTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("D:\\REPORT.txt", "UTF-8");
                    writer.print(reportText);
                }
                catch(Exception exception){

                }
                finally{
                    writer.close();
                }
            }
        });
    }

    private void createUIComponents() {
        DAO dao = new DAO();
        List<DiagnosisCard> diagnosisCards = dao.getDiagnosisCards();
        List<PollutionCard> pollutionCards = dao.getPollutionCards();
        Set<RegionInfo> regions = new EcoAnalyzer(diagnosisCards, pollutionCards).process();
        String[] columnNames = {"REGION",
                "ILL PEOPLE COUNT",
                "POLLUTION LEVEL",
                "MAIN POLLUTANT"};
        Object[][] data = new Object [regions.size()][4];
        int i = 0;
        reportText = "";
        for(RegionInfo region : regions){
            reportText += region.getName() + ": ill population - " + region.getIllCount()
            + ", pollution level - " + region.getPollutionLevel() + "(" +
            region.getMainPollutant() + ")\r\n";
            data[i][0] = region.getName();
            data[i][1] = region.getIllCount();
            data[i][2] = region.getPollutionLevel();
            data[i][3] = region.getMainPollutant();
            i++;
        }
        table1 = new JTable(data, columnNames);
    }
}
