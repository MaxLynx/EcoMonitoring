package databases3.ecoMonitoring.model.analysis;


import databases3.ecoMonitoring.model.DiagnosisCard;
import databases3.ecoMonitoring.model.PollutionCard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EcoAnalyzer {
    private List<DiagnosisCard> diagnosisCards;
    private List<PollutionCard> pollutionCards;

    public EcoAnalyzer (List<DiagnosisCard> diagnosisCards, List<PollutionCard> pollutionCards){
        this.diagnosisCards = diagnosisCards;
        this.pollutionCards = pollutionCards;
    }

    public Set<RegionInfo> process(){
        Set<RegionInfo> regions = new HashSet<RegionInfo>();
        for(PollutionCard card : pollutionCards){
            regions.add(new RegionInfo(card.getLocation()));
        }
        for(RegionInfo region : regions){
            double maxConcentration = 0;
            String mainPollutant = "None";
            int illCount = 0;
            for(PollutionCard card : pollutionCards){
                if(card.getLocation().equals(region.getName()))
                if(card.getConcentration() > maxConcentration){
                    maxConcentration = card.getConcentration();
                    mainPollutant = card.getPollutant().getName();
                }
            }
            region.setMainPollutant(mainPollutant);
            String pollutionLevel = "-";
            if((maxConcentration > 0) && (maxConcentration < 1)) {
                pollutionLevel = "LOW";
            }
                else
                if(maxConcentration < 10) {
                    pollutionLevel = "SIGNIFICANT";
                }
                     else
                        pollutionLevel = "ENORMOUS";
            region.setPollutionLevel(pollutionLevel);
            for(DiagnosisCard card : diagnosisCards){
                if(card.getMedicCard().getLocation().equals(region.getName()))
                    illCount++;
            }
            region.setIllCount(illCount);

        }
        return regions;
    }
}
