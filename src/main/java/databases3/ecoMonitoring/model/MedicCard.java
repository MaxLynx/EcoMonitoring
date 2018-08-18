package databases3.ecoMonitoring.model;


public class MedicCard {
    private int id;
    private String name;
    private String jobAddress;
    private int age;
    private String jobPosition;
    private String bloodType;
    private String address;
    private String location;


    public MedicCard(){}
    public MedicCard(String name, String jobAddress, int age, String jobPosition,
                     String bloodType, String address, String location){
        this.name = name;
        this.jobAddress = jobAddress;
        this.age = age;
        this.jobPosition = jobPosition;
        this.bloodType = bloodType;
        this.address = address;
        this.location = location;
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
    public String getJobAddress(){
        return jobAddress;
    }
    public void setJobAddress(String jobAddress){
        this.jobAddress = jobAddress;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String getJobPosition(){
        return jobPosition;
    }
    public void setJobPosition(String jobPosition){
        this.jobPosition = jobPosition;
    }
    public String getBloodType(){
        return bloodType;
    }
    public void setBloodType(String bloodType){
        this.bloodType = bloodType;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }
}
