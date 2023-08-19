import java.awt.desktop.SystemEventListener;
import java.util.List;

public class MonthAndYearReportsCheck {
    public static boolean isFillYearlyReport(YearlyReport yearlyReport){
        return yearlyReport.yearlyReport.size() > 0;
    }
    public static boolean isFillMonthlyReports(List<MonthlyReport> monthlyReports) {
        boolean isFill = monthlyReports.size() == 3;
        for (MonthlyReport monthlyReport : monthlyReports) {
            isFill = isFill && monthlyReport.monthlyReport.size() > 0;
        }
        return isFill;
    }
    public static double sumMounthlyIncome(MonthlyReport monthlyReport){
        double sumIncomeMounthly= 0;
        for (Transaction transaction : monthlyReport.monthlyReport) {
            if(transaction.isExpense == false){
                sumIncomeMounthly += transaction.quantity * transaction.unitPrice;
            }

        }
        return sumIncomeMounthly;
    }
    public static double sumMounthlyExpenses(MonthlyReport monthlyReport){
        double sumExpensesMounthly= 0;
        for (Transaction transaction : monthlyReport.monthlyReport) {
            if(transaction.isExpense == true){
                sumExpensesMounthly += transaction.quantity * transaction.unitPrice;
            }

        }
        return sumExpensesMounthly;
    }
    public static double yearlyIncome(YearlyReport yearlyReport,String month){
        double sumIncomeYearly = 0.0;
        for (MonthlyTransaction monthlyTransaction : yearlyReport.yearlyReport) {
            if(monthlyTransaction.isExpense == false && monthlyTransaction.month.equals(month)){
                sumIncomeYearly = monthlyTransaction.amount;
            }

        }
        return sumIncomeYearly;
    }
    public static double yearlyExpenses(YearlyReport yearlyReport, String month){
        double sumExpensesYearly = 0;
        for (MonthlyTransaction monthlyTransaction : yearlyReport.yearlyReport) {
            if(monthlyTransaction.isExpense == true && monthlyTransaction.month == month){
                sumExpensesYearly = monthlyTransaction.amount;
            }
        }
        return sumExpensesYearly;
    }

    public static String reconciliationOfIncome(YearlyReport yearlyReport, List<MonthlyReport> monthlyReportList) {
        String checkDoesNotMatch = "";
        for (MonthlyReport monthlyReport : monthlyReportList) {
            if (sumMounthlyIncome(monthlyReport) == yearlyIncome(yearlyReport,monthlyReport.mounth)){
                System.out.println("Успешное завершение операции для месяца " + monthlyReport.mounth );
            } else {
                checkDoesNotMatch = monthlyReport.mounth;
            }
        }
        return checkDoesNotMatch;
    }
}
