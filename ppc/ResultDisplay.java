package ppc;


import ppc.model.EmployeeModel;
import ppc.model.FinanceModel;
import ppc.model.ProcessedDataModel;

import java.util.*;

public class ResultDisplay {

    private ProcessedDataModel processedData;

    public void printResults(ProcessedDataModel processedData ) {

        this.processedData = processedData ;

        System.out.println("-----------------------------------\n");
        System.out.println("1) Total number of employees in an organization.");
        System.out.println("Answer: " + processedData.getListEmployeeDetail().size());
        System.out.println("-----------------------------------\n");
        System.out.println("2) Month wise following details\n" +
                "a. Total number of employees joined the organization");
        getMonthlyJoinExistLIst(false);
        System.out.println("-----------------------------------\n");
        System.out.println("b. Total number of employees exit organization with employee details like name, surname.");
        getMonthlyJoinExistLIst(true);
        System.out.println("-----------------------------------\n");
        System.out.println("3) Monthly salary report in following format");
        getTotalEmployeeSalaryMonth();
        System.out.println("-----------------------------------\n");
        System.out.println("4) Employee wise financial report in the following format");
        getEmployeWiseFinanceReport();
        System.out.println("-----------------------------------\n");
        System.out.println("\n5) Monthly amount released in following format");
        getMonthAmountReleased();
        System.out.println("-----------------------------------\n");
        System.out.println("6) Yearly financial report in the following format");
        getYearlyFinancialReport();
        System.out.println("-----------------------------------\n");
    }

    private void getYearlyFinancialReport() {
        ArrayList<FinanceModel> listFinance = processedData.getListFinance();
        if (listFinance != null && listFinance.size() != 0) {

            Collections.sort(listFinance, new Comparator<FinanceModel>() {
                @Override
                public int compare(FinanceModel financeModel, FinanceModel t1) {
                    if (financeModel.getTimeStamp() > t1.getTimeStamp()) {
                        return 1;
                    } else
                        return 0;
                }
            });
            for (FinanceModel financeModel : listFinance) {

                System.out.println(financeModel.getYearlyFinancialReport());

            }
        } else {
            System.out.println("listFinance is empty ");
        }
    }

    private void getEmployeWiseFinanceReport() {
        ArrayList<EmployeeModel> listEmployeeDetail = processedData.getListEmployeeDetail();
        ArrayList<FinanceModel> listSalary = processedData.getListSalary();

        if (listEmployeeDetail != null && listEmployeeDetail.size() != 0)
            for (EmployeeModel employeeModel : listEmployeeDetail) {

                int count = 0;
                for (FinanceModel financeModel : listSalary) {
                    if (financeModel != null && financeModel.getValue() != null && financeModel.getValue().length() != 0
                            && employeeModel.getEmpID().equals(financeModel.getEmpID())) {
                        count = count + Integer.parseInt(financeModel.getValue());
                    }
                }
                System.out.println(employeeModel.getJoinModel() + " Salary: " + count);
            }
        else {
            System.out.println("Employee List is empty ");
        }
    }

    private void getTotalEmployeeSalaryMonth() {
        Map<Integer, HashMap<Integer, List<FinanceModel>>> copylistYearMonth = new HashMap<>();

        Map<Integer, HashMap<Integer, List<FinanceModel>>> listYearMonth = processedData.getListYearMonth();
        if (listYearMonth != null && listYearMonth.size() != 0) {
            for (Map.Entry<Integer, HashMap<Integer, List<FinanceModel>>> yearsModel : listYearMonth.entrySet()) {
                HashMap<Integer, List<FinanceModel>> newlistYearMonth = new HashMap<>();
                if (yearsModel.getValue() != null && yearsModel.getValue() != null && yearsModel.getValue().size() != 0)
                    for (Map.Entry<Integer, List<FinanceModel>> monthsModel : yearsModel.getValue().entrySet()) {
                        List<FinanceModel> financeList = monthsModel.getValue();
                        List<FinanceModel> newlistJoin = new ArrayList<>();
                        if (financeList != null && financeList.size() != 0) {
                            for (int k = 0; k < financeList.size(); k++) {
                                FinanceModel financeModel = financeList.get(k);
                                if (financeModel.getEvent().equals("SALARY")) {
                                    newlistJoin.add(financeModel);
                                }
                            }
                            if (newlistJoin != null && newlistJoin.size() != 0) {
                                newlistYearMonth.put(monthsModel.getKey(), newlistJoin);
                            }
                        }
                    }
                if (newlistYearMonth != null && newlistYearMonth.size() != 0) {
                    copylistYearMonth.put(yearsModel.getKey(), newlistYearMonth);
                }
            }
        }

        if (copylistYearMonth != null && copylistYearMonth.size() != 0) {
            for (Map.Entry<Integer, HashMap<Integer, List<FinanceModel>>> yearsModel : copylistYearMonth.entrySet()) {
                System.out.println("Year " + yearsModel.getKey());
                if (yearsModel.getValue() != null && yearsModel.getValue() != null && yearsModel.getValue().size() != 0) {
                    for (Map.Entry<Integer, List<FinanceModel>> monthsModel : yearsModel.getValue().entrySet()) {

                        int totalSalary = 0;
                        int count = 0;
                        List<FinanceModel> financeList = monthsModel.getValue();
                        if (financeList != null && financeList.size() != 0) {

                            for (int k = 0; k < financeList.size(); k++) {
                                FinanceModel financeModel = financeList.get(k);
                                count++;
                                totalSalary = totalSalary + Integer.parseInt(financeModel.getValue());
                            }
                        }
                        System.out.println("Month: " + monthsModel.getKey() + " Total Salary: " + totalSalary
                                + " Total Employee: " + count);
                    }
                }
            }
        } else {
            System.out.println("Finance List By Year Month List Is Empty !");
        }
    }

    private void getMonthAmountReleased() {

        Map<Integer, HashMap<Integer, List<FinanceModel>>> listYearMonth = processedData.getListYearMonth();
        Map<Integer, HashMap<Integer, List<FinanceModel>>> copylistYearMonth = new HashMap<>();

        if (listYearMonth != null && listYearMonth.size() != 0) {
            for (Map.Entry<Integer, HashMap<Integer, List<FinanceModel>>> yearsModel : listYearMonth.entrySet()) {
                HashMap<Integer, List<FinanceModel>> newlistYearMonth = new HashMap<>();
                if (yearsModel.getValue() != null && yearsModel.getValue() != null && yearsModel.getValue().size() != 0)

                    for (Map.Entry<Integer, List<FinanceModel>> monthsModel : yearsModel.getValue().entrySet()) {
                        List<FinanceModel> financeList = monthsModel.getValue();
                        List<FinanceModel> newlistJoin = new ArrayList<>();
                        if (financeList != null && financeList.size() != 0) {
                            for (int k = 0; k < financeList.size(); k++) {
                                FinanceModel financeModel = financeList.get(k);
                                if (financeModel.getEvent().equals("SALARY")
                                        || financeModel.getEvent().equals("REIMBURSEMENT")
                                        || financeModel.getEvent().equals("BONUS")) {
                                    newlistJoin.add(financeModel);
                                }
                            }
                            if (newlistJoin != null && newlistJoin.size() != 0) {
                                newlistYearMonth.put(monthsModel.getKey(), newlistJoin);
                            }
                        }
                    }

                if (newlistYearMonth != null && newlistYearMonth.size() != 0) {
                    copylistYearMonth.put(yearsModel.getKey(), newlistYearMonth);
                }
            }
        }

        if (copylistYearMonth != null && copylistYearMonth.size() != 0) {
            for (Map.Entry<Integer, HashMap<Integer, List<FinanceModel>>> yearsModel : copylistYearMonth.entrySet()) {
                System.out.println("Year " + yearsModel.getKey());
                if (yearsModel.getValue() != null && yearsModel.getValue() != null && yearsModel.getValue().size() != 0) {
                    for (Map.Entry<Integer, List<FinanceModel>> monthsModel : yearsModel.getValue().entrySet()) {

                        int totalSalary = 0;
                        int count = 0;
                        List<FinanceModel> financeList = monthsModel.getValue();
                        if (financeList != null && financeList.size() != 0) {

                            for (int k = 0; k < financeList.size(); k++) {
                                FinanceModel financeModel = financeList.get(k);
                                count++;
                                totalSalary = totalSalary + Integer.parseInt(financeModel.getValue());
                            }
                        }
                        System.out.println("Month: " + monthsModel.getKey() + " Total Salary: " + totalSalary
                                + " Total Employee: " + count);
                    }
                }
            }
        } else {
            System.out.println("Amount Released By Year Monthly List Is Empty !");
        }
    }

    private void getMonthlyJoinExistLIst(boolean isExistEmployee) {

         Map<Integer, HashMap<Integer, List<FinanceModel>>> listYearMonth = processedData.getListYearMonth();
        Map<Integer, HashMap<Integer, List<FinanceModel>>> copylistYearMonth = new HashMap<>();
        int count = 0;
        if (listYearMonth != null && listYearMonth.size() != 0) {
            for (Map.Entry<Integer, HashMap<Integer, List<FinanceModel>>> yearsModel : listYearMonth.entrySet()) {
                HashMap<Integer, List<FinanceModel>> newlistYearMonth = new HashMap<>();
                if (yearsModel.getValue() != null && yearsModel.getValue() != null && yearsModel.getValue().size() != 0)
                    for (Map.Entry<Integer, List<FinanceModel>> monthsModel : yearsModel.getValue().entrySet()) {
                        List<FinanceModel> financeList = monthsModel.getValue();
                        List<FinanceModel> newlistJoin = new ArrayList<>();
                        if (financeList != null && financeList.size() != 0) {
                            for (int k = 0; k < financeList.size(); k++) {
                                FinanceModel financeModel = financeList.get(k);
                                if (!isExistEmployee && financeModel.getEvent().equals("ONBOARD")) {
                                    newlistJoin.add(financeModel);
                                    count++;
                                } else if (isExistEmployee && financeModel.getEvent().equals("EXIT")) {
                                    newlistJoin.add(financeModel);
                                    count++;
                                }
                            }
                            if (newlistJoin != null && newlistJoin.size() != 0) {
                                newlistYearMonth.put(monthsModel.getKey(), newlistJoin);
                            }
                        }
                    }
                if (newlistYearMonth != null && newlistYearMonth.size() != 0) {
                    copylistYearMonth.put(yearsModel.getKey(), newlistYearMonth);
                }
            }
        }
        System.out.println("Total Count : " + count);
        if (copylistYearMonth != null && copylistYearMonth.size() != 0) {
            for (Map.Entry<Integer, HashMap<Integer, List<FinanceModel>>> yearsModel : copylistYearMonth.entrySet()) {
                System.out.println("Year " + yearsModel.getKey());
                if (yearsModel.getValue() != null && yearsModel.getValue() != null && yearsModel.getValue().size() != 0) {
                    for (Map.Entry<Integer, List<FinanceModel>> monthsModel : yearsModel.getValue().entrySet()) {
                        System.out.println("Month " + monthsModel.getKey());
                        List<FinanceModel> financeList = monthsModel.getValue();
                        if (financeList != null && financeList.size() != 0) {
                            for (int k = 0; k < financeList.size(); k++) {
                                FinanceModel financeModel = financeList.get(k);
                                EmployeeModel employeeModel = getEmployeeData(financeModel.getEmpID());
                                if (employeeModel != null)
                                    System.out.println(employeeModel.getJoinModel());
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Monthly Join List Is Empty !");
        }


    }

    private EmployeeModel getEmployeeData(String empID) {
        ArrayList<EmployeeModel> listEmployeeDetail = processedData.getListEmployeeDetail();
        EmployeeModel employeeModel = null;
        for (EmployeeModel employeeModels : listEmployeeDetail) {
            if (employeeModels.getEmpID().equals(empID)) {
                employeeModel = employeeModels;
            }
        }
        return employeeModel;
    }



}
