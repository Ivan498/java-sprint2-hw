import java.util.ArrayList;
import java.util.List;

public class ReportEngine {
    FileReader reader = new FileReader();
    YearlyReport yearlyReport = new YearlyReport();
    MonthlyReport monthlyReport = new MonthlyReport();
    ArrayList<MonthlyReport> monthlyReportsList = new ArrayList<>();
    public void addMonthlyReportsList(FileReader reader) {
        monthlyReportsList.add(convertToMonthlyReport(reader.readFileContents("m.202101.csv"), "01"));
        monthlyReportsList.add(convertToMonthlyReport(reader.readFileContents("m.202102.csv"), "02"));
        monthlyReportsList.add(convertToMonthlyReport(reader.readFileContents("m.202103.csv"), "03"));

    }
    public void addYearlyReportsList(FileReader reader) {
        yearlyReport = convertToYearlyReport((reader.readFileContents("y.2021.csv")));
    }
    public MonthlyReport convertToMonthlyReport(List<String> stringTransactions, String mounth){
        MonthlyReport monthlyReport = new MonthlyReport();
        monthlyReport.mounth = mounth;
        for (int i = 1; i < stringTransactions.size(); i++) {
            String[] arrayTransaction = stringTransactions.get(i).split(",");
            Transaction transaction = new Transaction();
            transaction.itemName = arrayTransaction[0];
            transaction.isExpense = Boolean.parseBoolean(arrayTransaction[1]);
            transaction.quantity = Integer.parseInt(arrayTransaction[2]);
            transaction.unitPrice = Double.parseDouble(arrayTransaction[3]);
            monthlyReport.monthlyReport.add(transaction);
        }
        return monthlyReport;
    }
    public YearlyReport convertToYearlyReport(List<String> stringTransactions){
        YearlyReport yearlyReport = new YearlyReport();
        for (int i = 1; i < stringTransactions.size(); i++) {
            String[] arrayArticle = stringTransactions.get(i).split(",");
            MonthlyTransaction transaction = new MonthlyTransaction();
            transaction.month = arrayArticle[0];
            transaction.amount = Double.parseDouble(arrayArticle[1]);
            transaction.isExpense = Boolean.parseBoolean(arrayArticle[2]);
            yearlyReport.yearlyReport.add(transaction);
        }
        return yearlyReport;
    }
}
