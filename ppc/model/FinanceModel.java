package ppc.model;

public class FinanceModel {
    String SequenceNo, EmpID, Event, Value, EventDate, Notes;

    public long getTimeStamp() {
        return TimeStamp;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    long TimeStamp;
    int day, month, year;

    public FinanceModel(String sequenceNo, String empID, String event, String value, String eventDate, String notes, long timeStamp, int day, int month, int year) {
        SequenceNo = sequenceNo;
        EmpID = empID;
        Event = event;
        Value = value;
        EventDate = eventDate;
        Notes = notes;
        TimeStamp = timeStamp;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public FinanceModel(String sequenceNo, String empID, String event, String value, String eventDate,
                        String notes, long timeStamp) {
        SequenceNo = sequenceNo;
        EmpID = empID;
        Event = event;
        Value = value;
        EventDate = eventDate;
        Notes = notes;
     //   TimeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "\n" + "FinanceModel{" +
                "SequenceNo='" + SequenceNo + '\'' +
                ", EmpID='" + EmpID + '\'' +
                ", Event='" + Event + '\'' +
                ", Value='" + Value + '\'' +
                ", EventDate='" + EventDate + '\'' +
                ", Notes='" + Notes + '\'' +
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

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getYearlyFinancialReport() {
        return "FinanceModel{" +
                "Event='" + Event + '\'' +
                ", EmpID='" + EmpID + '\'' +
                ", EventDate='" + EventDate + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }
}

