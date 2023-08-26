package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class ReportEngine {

    private FileReader reader = new FileReader();

    public List<MonthlyReport> addMonthlyReportsList() {
        List<MonthlyReport> list = new ArrayList<>();
        list.add(convertToMonthlyReport(reader.readFileContents("m.202101.csv"), "01"));
        list.add(convertToMonthlyReport(reader.readFileContents("m.202102.csv"), "02"));
        list.add(convertToMonthlyReport(reader.readFileContents("m.202103.csv"), "03"));
        return list;
    }

    public YearlyReport addYearlyReportsList() {
        return convertToYearlyReport((reader.readFileContents("y.2021.csv")));
    }

    public MonthlyReport convertToMonthlyReport(List<String> stringTransactions, String month){
        MonthlyReport monthlyReport = new MonthlyReport();
        monthlyReport.setMonth(month);
        List<Transaction> transactionList = new ArrayList<>();
        for (int i = 1; i < stringTransactions.size(); i++) {
            String[] arrayTransaction = stringTransactions.get(i).split(",");
            Transaction transaction = new Transaction();
            transaction.setItemName(arrayTransaction[0]);
            transaction.setExpense(Boolean.parseBoolean(arrayTransaction[1]));
            transaction.setQuantity(Integer.parseInt(arrayTransaction[2]));
            transaction.setUnitPrice(Double.parseDouble(arrayTransaction[3]));
            transactionList.add(transaction);
        }
        monthlyReport.setTransactions(transactionList);
        return monthlyReport;
    }

    public YearlyReport convertToYearlyReport(List<String> stringTransactions) {
        YearlyReport yearlyReport = new YearlyReport();
        List<MonthlyTransaction> transactionList = new ArrayList<>();
        for (int i = 1; i < stringTransactions.size(); i++) {
            String[] arrayArticle = stringTransactions.get(i).split(",");
            MonthlyTransaction transaction = new MonthlyTransaction();
            transaction.setMonth(arrayArticle[0]);
            transaction.setAmount(Double.parseDouble(arrayArticle[1]));
            transaction.setExpense(Boolean.parseBoolean(arrayArticle[2]));
            transactionList.add(transaction);
        }
        yearlyReport.setMonthlyTransactions(transactionList);
        return yearlyReport;
    }
}
