package databases3.ecoMonitoring.model;


public class Doctor {
    private int id;
    private String name;

    public Doctor(){}
    public Doctor(String name){
        this.name = name;
    }

    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
