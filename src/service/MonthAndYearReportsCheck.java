package service;

import model.MonthlyReport;
import model.MonthlyTransaction;
import model.Transaction;
import model.YearlyReport;

import java.util.List;

public class MonthAndYearReportsCheck {

    public static boolean isFillYearlyReport(YearlyReport yearlyReport){
        return !yearlyReport.getMonthlyTransactions().isEmpty();
    }

    public static boolean isFillMonthlyReports(List<MonthlyReport> monthlyReports) {
        boolean isFill = monthlyReports.size() == 3;
        for (MonthlyReport monthlyReport : monthlyReports) {
            isFill = isFill && !monthlyReport.getTransactions().isEmpty();
        }
        return isFill;
    }

    public static double sumMounthlyIncome(MonthlyReport monthlyReport){
        double sumIncomeMounthly = 0;
        for (Transaction transaction : monthlyReport.getTransactions()) {
            if(!transaction.isExpense()){
                sumIncomeMounthly += transaction.getQuantity() * transaction.getUnitPrice();
            }
        }
        return sumIncomeMounthly;
    }

    public static double sumMounthlyExpenses(MonthlyReport monthlyReport){
        double sumExpensesMounthly= 0;
        for (Transaction transaction : monthlyReport.getTransactions()) {
            if(transaction.isExpense()){
                sumExpensesMounthly += transaction.getQuantity() * transaction.getUnitPrice();
            }
        }
        return sumExpensesMounthly;
    }

    public static double yearlyIncome(YearlyReport yearlyReport, String month){
        double sumIncomeYearly = 0.0;
        for (MonthlyTransaction monthlyTransaction : yearlyReport.getMonthlyTransactions()) {
            if(!monthlyTransaction.isExpense() && monthlyTransaction.getMonth().equals(month)){
                sumIncomeYearly = monthlyTransaction.getAmount();
            }
        }
        return sumIncomeYearly;
    }

    public static double yearlyExpenses(YearlyReport yearlyReport, String month){
        double sumExpensesYearly = 0;
        for (MonthlyTransaction monthlyTransaction : yearlyReport.getMonthlyTransactions()) {
            if(monthlyTransaction.isExpense() && monthlyTransaction.getMonth().equals(month)){
                sumExpensesYearly = monthlyTransaction.getAmount();
            }
        }
        return sumExpensesYearly;
    }

    public static boolean isConverge(YearlyReport yearlyReport, List<MonthlyReport> monthlyReportList) {
        if (yearlyReport != null && monthlyReportList != null) {
            boolean flag = true;
            for (MonthlyReport monthlyReport : monthlyReportList) {
                flag = flag && sumMounthlyIncome(monthlyReport) == yearlyIncome(yearlyReport, monthlyReport.getMonth())
                        && sumMounthlyExpenses(monthlyReport) == yearlyExpenses(yearlyReport, monthlyReport.getMonth());
            }
            return flag;
        }
        return false;
    }
}

