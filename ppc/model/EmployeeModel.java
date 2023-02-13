package ppc.model;

public class EmployeeModel {
    String SequenceNo, EmpID, EmpFName, EmpLName, Designation, Event;
    long TimeStamp;
    int days, month, year;

    public String getJoinModel() {
        return "{" +
                "EmpID='" + EmpID + '\'' +
                ", EmpFName='" + EmpFName + '\'' +
                ", EmpLName='" + EmpLName + '\'' +
                ", Designation='" + Designation + '\'' +
                '}';
    }

    public EmployeeModel(String sequenceNo, String empID, String empFName, String empLName, String designation, String event, long timeStamp, int days, int month, int year) {
        SequenceNo = sequenceNo;
        EmpID = empID;
        EmpFName = empFName;
        EmpLName = empLName;
        Designation = designation;
        Event = event;
        TimeStamp = timeStamp;
        this.days = days;
        this.month = month;
        this.year = year;
    }

    public EmployeeModel(String sequenceNo, String empID, String empFName, String empLName, String designation, String event, long timeStamp) {
        SequenceNo = sequenceNo;
        EmpID = empID;
        EmpFName = empFName;
        EmpLName = empLName;
        Designation = designation;
        Event = event;
        TimeStamp = timeStamp;
    }

    public EmployeeModel(String sequenceNo, String empID, String empFName, String empLName, String designation, String event) {
        SequenceNo = sequenceNo;
        EmpID = empID;
        EmpFName = empFName;
        EmpLName = empLName;
        Designation = designation;
        Event = event;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "SequenceNo='" + SequenceNo + '\'' +
                ", EmpID='" + EmpID + '\'' +
                ", EmpFName='" + EmpFName + '\'' +
                ", EmpLName='" + EmpLName + '\'' +
                ", Designation='" + Designation + '\'' +
                ", Event='" + Event + '\'' +
                '}';
    }

    public String getSequenceNo() {
        return SequenceNo;
    }

    public void setSequenceNo(String sequenceNo) {
        SequenceNo = sequenceNo;
    }

    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String empID) {
        EmpID = empID;
    }

    public String getEmpFName() {
        return EmpFName;
    }

    public void setEmpFName(String empFName) {
        EmpFName = empFName;
    }

    public String getEmpLName() {
        return EmpLName;
    }

    public void setEmpLName(String empLName) {
        EmpLName = empLName;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}