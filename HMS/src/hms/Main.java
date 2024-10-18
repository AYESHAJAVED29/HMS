package hms;

import AllClasses.Appointment;
import AllClasses.Bill;
import AllClasses.Doctor;
import AllClasses.Patient;
import DatabaseSetup.Implementation;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Implementation services = new Implementation();
        int choice;

        do {
            choice = mainMenu(scanner);
            switch (choice) {
                case 1:
                    patientsMenu(scanner, services);
                    break;
                case 2:
                    doctorMenu(scanner, services);
                    break;
                case 3:
                    appointmentMenu(scanner, services);
                    break;
                case 4:
                    billMenu(scanner, services);
                    break;
                case 5:
                    reportsMenu(scanner, services);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (choice != 6);

        scanner.close();
    }

    private static int mainMenu(Scanner scanner) {
        String str = "1.Patients\n2.Doctors\n3.Appointments\n4.Bills\n5.Reports\n6.Exit";
        System.out.println(str);
        System.out.print("Select an option: ");
        return scanner.nextInt();
    }

    private static void patientsMenu(Scanner scanner, Implementation patientService) {
        int choice;
        do {
            String str = "1.Add patient\n2.Delete patient\n3.Update patient\n4.Patient list\n5.Search patient\n6.Main menu";
            System.out.println(str);
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("*********Adding patient**********");
                    addPatient(scanner, patientService);
                    break;
                case 2:
                    System.out.println("*********Deleting patient**********");
                    deletePatient(scanner, patientService);
                    break;
                case 3:
                    System.out.println("*********Updating patient**********");
                    updatePatient(scanner, patientService);
                    break;
                case 4:
                    System.out.println("*********Displaying patient list**********");
                    showAllPatients(patientService);
                    break;
                case 5:
                    System.out.println("*********Searching patient**********");
                    searchPatient(scanner, patientService);
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (choice != 6);
    }

    private static void doctorMenu(Scanner scanner, Implementation doctorService) {
        int choice;
        do {
            String str = "1.Add doctor\n2.Delete doctor\n3.Update doctor\n4.Doctor list\n5.Search doctor\n6.Main menu";
            System.out.println(str);
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("*********Adding doctor**********");
                    addDoctor(scanner, doctorService);
                    break;
                case 2:
                    System.out.println("*********Deleting doctor**********");
                    deleteDoctor(scanner, doctorService);
                    break;
                case 3:
                    System.out.println("*********Updating doctor**********");
                    updateDoctor(scanner, doctorService);
                    break;
                case 4:
                    System.out.println("*********Displaying doctor list**********");
                    showAllDoctors(doctorService);
                    break;
                case 5:
                    System.out.println("*********Searching doctor**********");
                    searchDoctor(scanner, doctorService);
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (choice != 6);
    }

    private static void appointmentMenu(Scanner scanner, Implementation appointmentServicervice) {
        int choice;
        do {
            System.out.println("1.Add appointment\n2.Delete appointment\n3.Search appointment\n4.Show all appointments\n5.Main menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("*********Adding appointment**********");
                    addAppointment(scanner, appointmentServicervice);
                    break;
                case 2:
                    System.out.println("*********Deleting appointment**********");
                    deleteAppointment(scanner, appointmentServicervice);
                    break;
                case 3:
                    System.out.println("*********Searching appointment**********");
                    searchAppointment(scanner, appointmentServicervice);
                    break;
                case 4:
                    System.out.println("*********Displaying appointment list**********");
                    showAllAppointments(appointmentServicervice);
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void billMenu(Scanner scanner, Implementation billService) {
        int choice;
        do {
            String str = "1. Add bill\n2. Bill list\n3. Search bill\n4. Main menu";
            System.out.println(str);
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("*********Adding bill**********");
                    addBill(scanner, billService);
                    break;

                case 2:
                    System.out.println("*********Displaying bill list**********");
                    showAllBills(billService);
                    break;

                case 3:
                    System.out.println("*********Searching bill**********");
                    System.out.print("Enter bill ID to search: ");
                    int billId = scanner.nextInt();
                    searchBillById(billService, billId);
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (choice != 4);
    }

    private static void reportsMenu(Scanner scanner, Implementation reports) {
        int choice;
        do {
            String str = "1.Search patients by doctor\n2.Search bills by date\n3.Search appointment detail by patient\n4.Exit";
            System.out.println(str);
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Searching patients by doctor...");
                    getPatientsByDoctorName(scanner, reports);
                    break;
                case 2:
                    System.out.println("Searching bills by date...");
                    getBillsByDate(scanner, reports);
                    break;
                case 3:
                    System.out.println("Searching appointment details by patient...");
                    getAppointmentDetailsByPatientName(scanner, reports);
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (choice != 4);
    }

    private static void addPatient(Scanner scanner, Implementation patientService) {
        int pId = patientService.patientNextId();
        System.out.println("Next Patient ID: " + pId);
        scanner.nextLine();
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter patient disease: ");
        String disease = scanner.nextLine();
        System.out.print("Enter patient phone number: ");
        String phNo = scanner.nextLine();
        Patient patient = new Patient(pId, name, age, disease, phNo);
        patientService.addPatient(patient);
        System.out.println("Patient added successfully");
    }

    private static void deletePatient(Scanner scanner, Implementation patientService) {
        System.out.print("Enter the patient ID to delete: ");
        int id = scanner.nextInt();
        patientService.deletePatient(id);
        System.out.println("Patient with ID " + id + " has been deleted.");
    }

    private static void updatePatient(Scanner scanner, Implementation patientService) {
        System.out.print("Enter the patient ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new patient disease: ");
        String disease = scanner.nextLine();
        System.out.print("Enter new patient phone number: ");
        String phNo = scanner.nextLine();
        Patient updatedPatient = new Patient(id, name, age, disease, phNo);
        patientService.updatePatient(id, updatedPatient);
        System.out.println("Patient with ID " + id + " has been updated.");
    }

    private static void searchPatient(Scanner scanner, Implementation patientService) {
        System.out.print("Enter the patient ID to search: ");
        int id = scanner.nextInt();
        Patient patient = patientService.searchPatientById(id);
        if (patient != null) {
            System.out.println("Patient found: ");
            System.out.println("ID: " + patient.getpId());
            System.out.println("Name: " + patient.getName());
            System.out.println("Age: " + patient.getAge());
            System.out.println("Disease: " + patient.getDisease());
            System.out.println("Phone Number: " + patient.getPhNo());
        } else {
            System.out.println("No patient found with ID: " + id);
        }
    }

    private static void showAllPatients(Implementation patientService) {
        ArrayList<Patient> patients = patientService.showPatientData();

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            System.out.println("Patient List:");
            for (Patient patient : patients) {
                System.out.println("ID: " + patient.getpId());
                System.out.println("Name: " + patient.getName());
                System.out.println("Age: " + patient.getAge());
                System.out.println("Disease: " + patient.getDisease());
                System.out.println("Phone Number: " + patient.getPhNo());
                System.out.println("---------------");
            }
        }
    }

    private static void addDoctor(Scanner scanner, Implementation doctorService) {
        int id = doctorService.doctorNextId();
        System.out.println("Next available Doctor ID: " + id);
        scanner.nextLine();
        System.out.print("Enter doctor's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter qualification: ");
        String qualification = scanner.nextLine();
        System.out.print("Enter designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter salary: ");
        int salary = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter department: ");
        String department = scanner.next();
        Doctor doctor = new Doctor(id, name, qualification, designation, salary, department);
        doctorService.addDoctor(doctor);
        System.out.println("Doctor added successfully!");
    }

    private static void deleteDoctor(Scanner scanner, Implementation doctorService) {
        System.out.print("Enter the ID of the doctor to delete: ");
        int id = scanner.nextInt();
        doctorService.deleteDoctor(id);
        System.out.println("Doctor deleted successfully!");
    }

    private static void updateDoctor(Scanner scanner, Implementation doctorService) {
        System.out.print("Enter the ID of the doctor to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new name: ");
        String name = scanner.next();
        System.out.print("Enter new qualification: ");
        String qualification = scanner.next();
        System.out.print("Enter new designation: ");
        String designation = scanner.next();
        System.out.print("Enter new salary: ");
        int salary = scanner.nextInt();
        System.out.print("Enter new department: ");
        String department = scanner.next();
        Doctor doctor = new Doctor(id, name, qualification, designation, salary, department);
        doctorService.updateDoctor(id, doctor);
        System.out.println("Doctor updated successfully!");
    }

    private static void showAllDoctors(Implementation doctorService) {
        ArrayList<Doctor> doctors = doctorService.showDoctorData();
        for (Doctor doctor : doctors) {
            System.out.println("ID: " + doctor.getdId()
                    + ", Name: " + doctor.getName()
                    + ", Qualification: " + doctor.getQualification()
                    + ", Designation: " + doctor.getDesignation()
                    + ", Salary: " + doctor.getSalary()
                    + ", Department: " + doctor.getDepartment());
        }
    }

    private static void searchDoctor(Scanner scanner, Implementation doctorService) {
        System.out.print("Enter the doctor ID to search: ");
        int id = scanner.nextInt();
        Doctor doctor = doctorService.searchDoctorById(id);
        if (doctor != null) {
            System.out.println("Doctor found: "
                    + "\nID: " + doctor.getdId()
                    + "\nName: " + doctor.getName()
                    + "\nQualification: " + doctor.getQualification()
                    + "\nDesignation: " + doctor.getDesignation()
                    + "\nSalary: " + doctor.getSalary()
                    + "\nDepartment: " + doctor.getDepartment());
        } else {
            System.out.println("Doctor with ID " + id + " not found.");
        }
    }

    private static void addAppointment(Scanner scanner, Implementation appointmentService) {
        Appointment newAppointment = new Appointment();
        int id = appointmentService.appointmentNextId();
        newAppointment.setaId(id);
        System.out.println("Appointment id: " + id);
        scanner.nextLine();
        System.out.println("Enter patient name:");
        newAppointment.setpName(scanner.nextLine());
        System.out.println("Enter doctor name:");
        newAppointment.setdName(scanner.nextLine());
        System.out.println("Enter appointment type:");
        newAppointment.setApp_type(scanner.nextLine());
        System.out.println("Enter appointment date and time (yyyy-mm-dd hh:mm:ss):");
        String dateTimeInput = scanner.nextLine();
        try {
            newAppointment.setDate_time(Timestamp.valueOf(dateTimeInput));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date-time format. Please use yyyy-mm-dd hh:mm:ss.");
            return;
        }
        appointmentService.addAppointment(newAppointment);
        System.out.println("Appointment added successfully.");
    }

    private static void deleteAppointment(Scanner scanner, Implementation appointmentService) {
        System.out.println("Enter appointment ID to delete:");
        int id = scanner.nextInt();
        appointmentService.deleteAppointment(id);
        System.out.println("Appointment deleted successfully.");
    }

    private static void searchAppointment(Scanner scanner, Implementation appointmentService) {
        System.out.println("Enter appointment ID to search:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Appointment appointment = appointmentService.searchAppointmentById(id);
        if (appointment != null) {
            System.out.println("Appointment found:");
            System.out.println(appointment);
        } else {
            System.out.println("No appointment found with the given ID.");
        }
    }

    private static void showAllAppointments(Implementation appointmentService) {
        ArrayList<Appointment> appointmentList = appointmentService.showAppointmentData();
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment appointment : appointmentList) {
                System.out.println(appointment);
            }
        }
    }

    private static void addBill(Scanner scanner, Implementation billService) {
        int id = billService.billNextId();
        System.out.println("Next available Bill ID: " + id);
        scanner.nextLine();
        System.out.print("Enter payer's name: ");
        String payerName = scanner.nextLine();
        System.out.print("Enter payable amount: ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter appointment ID: ");
        int appId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter bill date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        Bill bill = new Bill(id, amount, payerName, appId, date);
        billService.addBill(bill);
        System.out.println("Bill added successfully!");
    }

    private static void searchBillById(Implementation billService, int billId) {
        Bill bill = billService.searchBillById(billId);
        if (bill != null) {
            System.out.println("Bill found: " + bill);
        } else {
            System.out.println("No bill found with ID: " + billId);
        }
    }

    private static void showAllBills(Implementation billService) {
        ArrayList<Bill> bills = billService.showBillData();
        System.out.println("All bills in the system:");
        for (Bill bill : bills) {
            System.out.println(bill);
        }
    }

    private static void getPatientsByDoctorName(Scanner scanner, Implementation service) {
        System.out.print("Enter doctor's name: ");
        String doctorName = scanner.nextLine();
        ArrayList<String[]> patients = service.patientsByDoctorName(doctorName);
        if (patients.isEmpty()) {
            System.out.println("No patients found for Doctor: " + doctorName);
        } else {
            System.out.println("Patients under Doctor " + doctorName + ":");
            for (String[] patient : patients) {
                System.out.println("Patient ID: " + patient[0] + ", Patient Name: " + patient[1]);
            }
        }
    }

    private static void getBillsByDate(Scanner scanner, Implementation service) {
        System.out.print("Enter bill date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        String[][] bills = service.billByDate(date);
        if (bills.length == 0) {
            System.out.println("No bills found for date: " + date);
        } else {
            System.out.println("Bills on " + date + ":");
            for (String[] bill : bills) {
                System.out.println("Bill ID: " + bill[0] + ", Payable Amount: " + bill[1]);
            }
        }
    }

    private static void getAppointmentDetailsByPatientName(Scanner scanner, Implementation service) {
        System.out.print("Enter patient's name: ");
        String name = scanner.nextLine();
        Object[][] appointments = service.AppointmentDetailBypatientName(name);
        if (appointments.length == 0) {
            System.out.println("No appointments found for patient: " + name);
        } else {
            System.out.println("Appointments for " + name + ":");
            for (Object[] appointment : appointments) {
                System.out.println("Appointment ID: " + appointment[0]
                        + ", Type: " + appointment[1]
                        + ", Disease: " + appointment[2]
                        + ", Doctor: " + appointment[3]
                        + ", Payable Amount: " + appointment[4]
                        + ", Date/Time: " + appointment[5]);
            }
        }
    }

}
