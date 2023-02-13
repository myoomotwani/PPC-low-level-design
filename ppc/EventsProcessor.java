package ppc;

import ppc.model.EmployeeModel;
import ppc.model.FinanceModel;
import ppc.model.ProcessedDataModel;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EventsProcessor {

    private File file = null;
    private Scanner sc = null;




    public EventsProcessor(String path) {
        try {
            file = new File(path);
            if (file != null && file.exists())
                sc = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProcessedDataModel performProcessingTask() {
        try {
            if (sc != null && sc.hasNext()) {
                //  onboard 9 col   SequenceNo, EmpID, EmpFName, EmpLName, Designation,Event, Value, EventDate, Notes
                // salary 6 col   SequenceNo, EmpID, Event, Value, EventDate, Notes
                // BONUS 6 col   SequenceNo, EmpID, Event, Value, EventDate, Notes
                // Exit 6 col   SequenceNo, EmpID, Event, Value, EventDate, Notes
                // REIMBURSEMENT 6 col   SequenceNo, EmpID, Event, Value, EventDate, Notes
                ArrayList<EmployeeModel>  listEmployeeDetail = new ArrayList<>();
                ArrayList<FinanceModel> listFinance = new ArrayList<>();
                ArrayList<FinanceModel> listSalary = new ArrayList<>();
                Map<Integer, HashMap<Integer, List<FinanceModel>>> listYearMonth = new HashMap<>();
                int currentYear = -1;
                int currentMonth = -1;
                HashMap<Integer, List<FinanceModel>> tempMothFinance = new HashMap<>();
                List<FinanceModel> tempFinance = new ArrayList<>();
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    List<String> columns = Arrays.asList(line.split(","));
                    ArrayList<String> listID = new ArrayList<String>();
                    String sequenceNo = null, empID = null, empFName = null, empLName = null, designation = null, event = null, value = null, eventDate = null, notes = null;
                    int days = -1, month = -1, year = -1;
                    if (columns != null && columns.size() != 0) {
                        if (columns.size() == 9) {
                            sequenceNo = columns.get(0);
                            empID = columns.get(1).trim();
                            empFName = columns.get(2).trim();
                            empLName = columns.get(3).trim();
                            designation = columns.get(4).trim();
                            event = columns.get(5).trim();
                            value = columns.get(6).trim();
                            eventDate = columns.get(7).trim();
                            notes = columns.get(8).trim();
                        } else if (columns.size() == 6) {
                            sequenceNo = columns.get(0).trim();
                            empID = columns.get(1).trim();
                            event = columns.get(2).trim();
                            value = columns.get(3).trim();
                            eventDate = columns.get(4).trim();
                            notes = columns.get(5).trim();
                        }
                        String parseDate = value;
                        if (event != null && event.length() != 0 && value != null && value.length() != 0
                                && !event.equals("ONBOARD"))
                            parseDate = eventDate;
                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            Date parsedDate = null;
                            try {
                                parsedDate = dateFormat.parse(parseDate.trim());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(parsedDate);
                            days = calendar.get(Calendar.DAY_OF_MONTH);
                            month = calendar.get(Calendar.MONTH) + 1;
                            year = calendar.get(Calendar.YEAR);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (event != null && event.length() != 0) {
                            FinanceModel financeModel = new FinanceModel(sequenceNo, empID, event,
                                    value, eventDate, notes
                                    , getTimeStamp(eventDate), days, month, year);
                            listFinance.add(financeModel);
                            if (event.equals("ONBOARD")) {
                                if (listID != null && !listID.contains(empID)) {
                                    listID.add(empID);
                                    listEmployeeDetail.add(new EmployeeModel(sequenceNo, empID, empFName, empLName,
                                            designation, event, getTimeStamp(eventDate), days, month, year));
                                }
                            }
                            if (event.equals("SALARY")) {
                                listSalary.add(financeModel);
                            }

                            {
                                if (currentYear != 0 && currentYear == year) {
                                    if (currentMonth != 0 && currentMonth == month) {
                                        tempFinance.add(financeModel);
                                    } else {
                                        if (tempFinance != null && tempFinance.size() != 0) {
                                            tempMothFinance.put(currentMonth, (List<FinanceModel>) ((ArrayList<FinanceModel>) tempFinance).clone());
                                        }
                                        tempFinance.clear();
                                        tempFinance = new ArrayList<>();
                                        currentMonth = month;
                                        tempFinance.add(financeModel);
                                    }

                                } else {
                                    if (tempFinance != null && tempFinance.size() != 0) {
                                        if (month != -1) {
                                            tempMothFinance.put(currentMonth, (List<FinanceModel>) ((ArrayList<FinanceModel>) tempFinance).clone());
                                            if (year != -1) {
                                                listYearMonth.put(currentYear, (HashMap<Integer, List<FinanceModel>>) tempMothFinance.clone());
                                            }
                                        }
                                    }
                                    tempMothFinance.clear();
                                    tempFinance.clear();
                                    tempFinance = new ArrayList<>();
                                    tempMothFinance = new HashMap<>();
                                    currentYear = year;
                                    currentMonth = month;
                                    tempFinance.add(financeModel);
                                }
                            }
                        }
                    }
                }

                if (tempFinance != null && tempFinance.size() != 0) {
                    if (currentMonth != -1) {
                        tempMothFinance.put(currentMonth, tempFinance);
                        if (currentYear != -1) {
                            listYearMonth.put(currentYear, tempMothFinance);
                        }
                    }
                }

                ProcessedDataModel processedDataModel = new ProcessedDataModel();
                processedDataModel.setListEmployeeDetail(listEmployeeDetail);
                processedDataModel.setListFinance(listFinance);
                processedDataModel.setListSalary(listSalary);
                processedDataModel.setListYearMonth(listYearMonth);

                return processedDataModel ;
            } else {

                System.out.println("File is not parsable !");
                return null ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null ;
        }




    }


    long getTimeStamp(String yourString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parsedDate = dateFormat.parse(yourString);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp.getTime();
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

}
