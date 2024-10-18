package DatabaseSetup;

import AllClasses.*;
import java.util.ArrayList;

public interface MethodInterface {

    void addPatient(Patient obj);

    void deletePatient(int id);

    void updatePatient(int id, Patient obj);

    Patient searchPatientById(int id);

    ArrayList<Patient> showPatientData();

    int patientNextId();

    void addDoctor(Doctor obj);

    void deleteDoctor(int id);

    void updateDoctor(int id, Doctor obj);

    Doctor searchDoctorById(int id);

    ArrayList<Doctor> showDoctorData();

    int doctorNextId();

    void addAppointment(Appointment obj);

    void deleteAppointment(int id);

    ArrayList<Appointment> showAppointmentData();

    Appointment searchAppointmentById(int id);

    int appointmentNextId();

    int billNextId();

    void addBill(Bill obj);

    ArrayList<Bill> showBillData();

    Bill searchBillById(int billId);

    ArrayList<String[]> patientsByDoctorName(String name);

    String[][] billByDate(String date);

    Object[][] AppointmentDetailBypatientName(String name);

}
