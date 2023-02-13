package ppc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessedDataModel {

    public ArrayList<EmployeeModel> getListEmployeeDetail() {
        return listEmployeeDetail;
    }

    public ArrayList<FinanceModel> getListFinance() {
        return listFinance;
    }

    public ArrayList<FinanceModel> getListSalary() {
        return listSalary;
    }

    public Map<Integer, HashMap<Integer, List<FinanceModel>>> getListYearMonth() {
        return listYearMonth;
    }

    public void setListEmployeeDetail(ArrayList<EmployeeModel> listEmployeeDetail) {
        this.listEmployeeDetail = listEmployeeDetail;
    }

    public void setListFinance(ArrayList<FinanceModel> listFinance) {
        this.listFinance = listFinance;
    }

    public void setListSalary(ArrayList<FinanceModel> listSalary) {
        this.listSalary = listSalary;
    }

    public void setListYearMonth(Map<Integer, HashMap<Integer, List<FinanceModel>>> listYearMonth) {
        this.listYearMonth = listYearMonth;
    }

    private ArrayList<EmployeeModel> listEmployeeDetail;
    private ArrayList<FinanceModel> listFinance;
    private ArrayList<FinanceModel> listSalary;
    private Map<Integer, HashMap<Integer, List<FinanceModel>>> listYearMonth;

}
