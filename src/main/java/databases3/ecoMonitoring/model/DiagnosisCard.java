package databases3.ecoMonitoring.model;


import java.util.Date;

public class DiagnosisCard {
    private int id;
    private MedicCard medicCard;
    private Doctor doctor;
    private Diagnosis diagnosis;
    private Date date;

    public DiagnosisCard(){};
    public DiagnosisCard(MedicCard card, Doctor doctor, Diagnosis diagnosis, Date date){
        this.medicCard = card;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.date = date;
    }

    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public MedicCard getMedicCard(){
        return medicCard;
    }
    public void setMedicCard(MedicCard card){
        this.medicCard = card;
    }
    public Doctor getDoctor(){
        return doctor;
    }
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }
    public Diagnosis getDiagnosis(){
        return diagnosis;
    }
    public void setDiagnosis(Diagnosis diagnosis){
        this.diagnosis = diagnosis;
    }
    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date = date;
    }
}
