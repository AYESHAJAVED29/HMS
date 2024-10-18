package DatabaseSetup;

import java.sql.*;
import AllClasses.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Implementation implements MethodInterface {

    @Override
    public void addPatient(Patient obj) {
        Connection con = null;
        CallableStatement callableStatement = null;
        try {
            con = Database.getConnection();
            String sql = "CALL patientAdd(?, ?, ?, ?, ?);";
            callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, obj.getpId());
            callableStatement.setString(2, obj.getName());
            callableStatement.setInt(3, obj.getAge());
            callableStatement.setString(4, obj.getDisease());
            callableStatement.setString(5, obj.getPhNo());
            int row = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void deletePatient(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL patientDelete(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            int rowsAffected = callableStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void updatePatient(int id, Patient obj) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL patientUpdate(?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.setString(2, obj.getName());
            callableStatement.setInt(3, obj.getAge());
            callableStatement.setString(4, obj.getDisease());
            callableStatement.setString(5, obj.getPhNo());
            int rowsAffected = callableStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public Patient searchPatientById(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Patient patient = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL searchPatientById(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                int pId = resultSet.getInt("pId");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String disease = resultSet.getString("disease");
                String phNo = resultSet.getString("phNo");
                patient = new Patient(pId, name, age, disease, phNo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return patient;
    }

    @Override
    public int patientNextId() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int nextPatientId = 0;
        try {
            connection = Database.getConnection();
            callableStatement = connection.prepareCall("CALL getNextPatientId(?);");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.executeUpdate();
            nextPatientId = callableStatement.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nextPatientId;
    }

    @Override
    public ArrayList<Patient> showPatientData() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Patient> patientList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL showPatientData()}";
            callableStatement = connection.prepareCall(sql);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int patientId = resultSet.getInt("p_id");
                String patientName = resultSet.getString("p_name");
                int patientAge = resultSet.getInt("age");
                String patientDisease = resultSet.getString("disease");
                String patientPhoneNumber = resultSet.getString("phone_no");

                Patient patient = new Patient(patientId, patientName, patientAge, patientDisease, patientPhoneNumber);
                patientList.add(patient);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return patientList;
    }

    @Override
    public void addDoctor(Doctor obj) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL doctorAdd(?, ?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, obj.getdId());
            callableStatement.setString(2, obj.getName());
            callableStatement.setString(3, obj.getQualification());
            callableStatement.setString(4, obj.getDesignation());
            callableStatement.setInt(5, obj.getSalary());
            callableStatement.setString(6, obj.getDepartment());
            callableStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void deleteDoctor(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL doctorDelete(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void updateDoctor(int id, Doctor obj) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL doctorUpdate(?, ?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.setString(2, obj.getName());
            callableStatement.setString(3, obj.getQualification());
            callableStatement.setString(4, obj.getDesignation());
            callableStatement.setInt(5, obj.getSalary());
            callableStatement.setString(6, obj.getDepartment());
            callableStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public ArrayList<Doctor> showDoctorData() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Doctor> doctorList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL showDoctorData()}";
            callableStatement = connection.prepareCall(sql);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int doctorId = resultSet.getInt("d_id");
                String doctorName = resultSet.getString("d_name");
                String qualification = resultSet.getString("qualification");
                String designation = resultSet.getString("designation");
                int salary = resultSet.getInt("salary");
                String department = resultSet.getString("department");
                Doctor doctor = new Doctor(doctorId, doctorName, qualification, designation, salary, department);
                doctorList.add(doctor);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return doctorList;
    }

    @Override
    public Doctor searchDoctorById(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Doctor doctor = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL searchDoctorById(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                int dId = resultSet.getInt("d_id");
                String name = resultSet.getString("d_name");
                String qualification = resultSet.getString("qualification");
                String designation = resultSet.getString("designation");
                int salary = resultSet.getInt("salary");
                String department = resultSet.getString("department");
                doctor = new Doctor(dId, name, qualification, designation, salary, department);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return doctor;
    }

    @Override
    public int doctorNextId() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int nextId = 0;
        try {
            connection = Database.getConnection();
            String sql = "{CALL getNextDoctorId(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.execute();
            nextId = callableStatement.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nextId;
    }

    @Override
    public void addAppointment(Appointment obj) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL addAppointmentByNames(?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, obj.getaId());
            callableStatement.setString(2, obj.getpName());
            callableStatement.setString(3, obj.getdName());
            callableStatement.setTimestamp(4, obj.getDate_time());
            callableStatement.setString(5, obj.getApp_type());
            callableStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void deleteAppointment(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL appointmentDelete(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public Appointment searchAppointmentById(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Appointment appointment = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL searchAppointmentById(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                int aId = resultSet.getInt("app_id");
                String pName = resultSet.getString("p_name");
                String dName = resultSet.getString("d_name");
                Timestamp dateTime = resultSet.getTimestamp("date_time");
                String appType = resultSet.getString("app_type");
                appointment = new Appointment(aId, pName, dName, dateTime, appType);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return appointment;
    }

    @Override
    public int appointmentNextId() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int nextAppointmentId = 0;

        try {
            connection = Database.getConnection();
            String sql = "{CALL getNextAppointmentId(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.execute();
            nextAppointmentId = callableStatement.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nextAppointmentId;
    }

    @Override
    public void addBill(Bill obj) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL addBill(?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, obj.getbId());
            callableStatement.setInt(2, obj.getAmount());
            callableStatement.setString(3, obj.getPayer_name());
            callableStatement.setInt(4, obj.getaId());
            callableStatement.setDate(5, Date.valueOf(obj.getDate()));
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public int billNextId() {
        Connection con = null;
        CallableStatement stat = null;
        int nextId = 0;
        try {
            con = Database.getConnection();
            String sql = "{CALL getNextBillId(?)}";
            stat = con.prepareCall(sql);
            stat.registerOutParameter(1, Types.INTEGER);
            stat.execute();
            nextId = stat.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nextId;
    }

    @Override
    public Bill searchBillById(int billId) {
        Bill bill = null;
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            conn = Database.getConnection();
            String sql = "{CALL searchBillById(?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, billId);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                int bId = rs.getInt("bill_id");
                int amount = rs.getInt("payable_amount");
                String payerName = rs.getString("payer_name");
                int appId = rs.getInt("app_id");
                String date = rs.getString("date");
                bill = new Bill(bId, amount, payerName, appId, date);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return bill;
    }

    @Override
    public ArrayList<Bill> showBillData() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Bill> billList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL showBillData()}";
            callableStatement = connection.prepareCall(sql);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt("bill_id");
                int amount = resultSet.getInt("payable_amount");
                String patientName = resultSet.getString("payer_name");
                int appointmentId = resultSet.getInt("app_id");
                Date billDate = resultSet.getDate("date");
                Bill bill = new Bill(billId, amount, patientName, appointmentId, billDate.toString());
                billList.add(bill);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return billList;
    }

    @Override
    public ArrayList<String[]> patientsByDoctorName(String doctorName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<String[]> patientList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL getPatientsByDoctorName(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, doctorName);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String patientId = String.valueOf(resultSet.getInt("p_id"));
                String patientName = resultSet.getString("p_name");
                patientList.add(new String[]{patientId, patientName});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return patientList;
    }

    @Override
    public String[][] billByDate(String date) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<String[]> billsList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL getBillsByDate(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setDate(1, java.sql.Date.valueOf(date));
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt("bill_id");
                int payableAmount = resultSet.getInt("payable_amount");
                String[] billInfo = new String[2];
                billInfo[0] = String.valueOf(billId);
                billInfo[1] = String.valueOf(payableAmount);
                billsList.add(billInfo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return billsList.toArray(new String[0][0]);
    }

    @Override
    public Object[][] AppointmentDetailBypatientName(String name) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Object[]> appointmentDetailsList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL getAppointmentDetailsByPatientName(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, name);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int appId = resultSet.getInt("app_id");
                String appType = resultSet.getString("app_type");
                String disease = resultSet.getString("disease");
                String doctorName = resultSet.getString("d_name");
                int payableAmount = resultSet.getInt("payable_amount");
                Timestamp dateTime = resultSet.getTimestamp("date_time");
                appointmentDetailsList.add(new Object[]{
                    appId,
                    appType,
                    disease,
                    doctorName,
                    payableAmount,
                    dateTime
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return appointmentDetailsList.toArray(new Object[0][]);
    }

    @Override
    public ArrayList<Appointment> showAppointmentData() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL showAppointmentData()}";
            callableStatement = connection.prepareCall(sql);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int appId = resultSet.getInt("app_id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                Timestamp dateTime = resultSet.getTimestamp("date_time");
                String appType = resultSet.getString("app_type");
                Appointment appointment = new Appointment(appId, patientName, doctorName, dateTime, appType);
                appointment.setaId(appId);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return appointments;
    }

}
