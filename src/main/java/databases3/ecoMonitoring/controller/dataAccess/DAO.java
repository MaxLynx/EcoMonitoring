package databases3.ecoMonitoring.controller.dataAccess;

import databases3.ecoMonitoring.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAO {
    private static SessionFactory factory;

    public DAO(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<MedicCard> getMedicCards( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List cards = null;

        try {
            tx = session.beginTransaction();
            cards = session.createQuery("FROM MedicCard").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cards;
    }
    public List<PollutionCard> getPollutionCards( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List cards = null;

        try {
            cards = new ArrayList<PollutionCard>();
            tx = session.beginTransaction();
            List<Object[]> results = session.createQuery(
                    "FROM PollutionCard AS card INNER JOIN card.pollutant").list();
            for(Object[] result : results)
            {
                        Pollutant pollutant = (Pollutant)result[1];
                        PollutionCard card = (PollutionCard) result[0];
                        card.setPollutant(pollutant);
                        cards.add(card);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cards;
    }
    public List<DiagnosisCard> getDiagnosisCards( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List cards = null;

        try {
            cards = new ArrayList<DiagnosisCard>();
            tx = session.beginTransaction();
            List<Object[]> results = session.createQuery("FROM DiagnosisCard " +
                    "AS card INNER JOIN card.medicCard INNER JOIN card.doctor " +
                    "INNER JOIN card.diagnosis").list();
            for(Object[] result : results)
            {
                MedicCard medicCard = (MedicCard)result[1];
                Doctor doctor = (Doctor)result[2];
                Diagnosis diagnosis = (Diagnosis)result[3];
                DiagnosisCard card = (DiagnosisCard) result[0];
                card.setMedicCard(medicCard);
                card.setDoctor(doctor);
                card.setDiagnosis(diagnosis);
                cards.add(card);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cards;
    }
    public List<Doctor> getDoctors( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List doctors = null;

        try {
            tx = session.beginTransaction();
            doctors = session.createQuery("FROM Doctor").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return doctors;
    }
    public List<Pollutant> getPollutants( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List pollutants = null;

        try {
            tx = session.beginTransaction();
            pollutants = session.createQuery("FROM Pollutant").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pollutants;
    }
    public List<Diagnosis> getDiagnoses( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List diagnoses = null;

        try {
            tx = session.beginTransaction();
            diagnoses = session.createQuery("FROM Diagnosis").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return diagnoses;
    }
    public Integer addDoctor(String name){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer doctorID = null;

        try {
            tx = session.beginTransaction();
            Doctor doctor = new Doctor(name);
            doctorID = (Integer) session.save(doctor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return doctorID;
    }

    public Integer addPollutant(String name){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer pollutantID = null;

        try {
            tx = session.beginTransaction();
            Pollutant pollutant = new Pollutant(name);
            pollutantID = (Integer) session.save(pollutant);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pollutantID;
    }

    public Integer addDiagnosis(String name){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer diagnosisID = null;

        try {
            tx = session.beginTransaction();
            Diagnosis diagnosis = new Diagnosis(name);
            diagnosisID = (Integer) session.save(diagnosis);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return diagnosisID;
    }
    public Integer addMedicCard(String name, String jobAddress, int age, String jobPosition,
                                String bloodType, String address, String location){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer cardID = null;

        try {
            tx = session.beginTransaction();
            MedicCard card = new MedicCard(name, jobAddress, age, jobPosition, bloodType, address, location);
            cardID = (Integer) session.save(card);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cardID;
    }
    public Integer addPollutionCard(String location, Date date, int pollutantID, double concentration,
                                    String chemicalGroup, String address){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer cardID = null;

        try {
            tx = session.beginTransaction();
            Pollutant pollutant = (Pollutant)session.get(Pollutant.class, pollutantID);
            PollutionCard card = new PollutionCard(location, date, pollutant, concentration,
                    chemicalGroup, address);
            cardID = (Integer) session.save(card);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cardID;
    }
    public Integer addDiagnosisCard(int medicCardID, int doctorID, int diagnosisID,
                                    Date date){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer cardID = null;
        try {
            tx = session.beginTransaction();
            MedicCard medicCard = (MedicCard)session.get(MedicCard.class, medicCardID);
            System.out.println(medicCard.getID());
            System.out.println(medicCard.getName());
            Doctor doctor = (Doctor)session.get(Doctor.class, doctorID);
            Diagnosis diagnosis = (Diagnosis)session.get(Diagnosis.class, diagnosisID);
            DiagnosisCard card = new DiagnosisCard(medicCard, doctor, diagnosis, date);
            cardID = (Integer) session.save(card);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cardID;
    }
    public void deletePollutant(Integer pollutantID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Pollutant pollutant = (Pollutant)session.get(Pollutant.class, pollutantID);
            session.delete(pollutant);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void deleteDoctor(Integer doctorID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Doctor doctor = (Doctor)session.get(Doctor.class, doctorID);
            session.delete(doctor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void deleteDiagnosis(Integer diagnosisID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Diagnosis diagnosis = (Diagnosis)session.get(Diagnosis.class, diagnosisID);
            session.delete(diagnosis);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void deleteMedicCard(Integer cardID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            MedicCard card = (MedicCard)session.get(MedicCard.class, cardID);
            session.delete(card);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void deletePollutionCard(Integer cardID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            PollutionCard card = (PollutionCard)session.get(PollutionCard.class, cardID);
            session.delete(card);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void deleteDiagnosisCard(Integer cardID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            DiagnosisCard card = (DiagnosisCard)session.get(DiagnosisCard.class, cardID);
            session.delete(card);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
