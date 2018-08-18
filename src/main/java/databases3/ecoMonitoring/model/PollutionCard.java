package databases3.ecoMonitoring.model;


import java.util.Date;

public class PollutionCard {
    private int id;
    private String location;
    private Date date;
    private Pollutant pollutant;
    private double concentration;
    private String chemicalGroup;
    private String address;

    public PollutionCard(){}
    public PollutionCard(String location, Date date, Pollutant pollutant, double concentration,
                         String chemicalGroup, String address){
        this.location = location;
        this.date = date;
        this.pollutant = pollutant;
        this.concentration = concentration;
        this.chemicalGroup = chemicalGroup;
        this.address = address;
    }

    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public Pollutant getPollutant(){
        return pollutant;
    }
    public void setPollutant(Pollutant pollutant){
        this.pollutant = pollutant;
    }
    public double getConcentration(){
        return concentration;
    }
    public void setConcentration(double concentration){
        this.concentration = concentration;
    }
    public String getChemicalGroup(){
        return chemicalGroup;
    }
    public void setChemicalGroup(String chemicalGroup){
        this.chemicalGroup = chemicalGroup;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
}
