package databases3.ecoMonitoring.model.analysis;


public class RegionInfo {
    private String name;
    private String pollutionLevel;
    private String mainPollutant;
    private int illCount;

    public RegionInfo(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPollutionLevel(){
        return pollutionLevel;
    }
    public void setPollutionLevel(String pollutionLevel){
        this.pollutionLevel = pollutionLevel;
    }
    public String getMainPollutant(){
        return mainPollutant;
    }
    public void setMainPollutant(String mainPollutant){
        this.mainPollutant = mainPollutant;
    }
    public int getIllCount(){
        return illCount;
    }
    public void setIllCount(int illCount){
        this.illCount = illCount;
    }
}
