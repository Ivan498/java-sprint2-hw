import model.MonthlyReport;
import model.YearlyReport;
import service.MonthAndYearReportsCheck;
import service.ReportEngine;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportEngine reportEngine = new ReportEngine();
        YearlyReport yearlyReport = null;
        List<MonthlyReport> monthlyReports = null;

        while(true) {
            printMenu();
            int userInput = scanner.nextInt();
            if(userInput == 1){
                monthlyReports = reportEngine.addMonthlyReportsList();
            } else if (userInput == 2) {
                yearlyReport = reportEngine.addYearlyReportsList();
            } else if (userInput == 3) {
                if (monthlyReports == null) {
                    System.out.println("Месячные отчеты не загржуены!");
                } else if (yearlyReport == null){
                    System.out.println("Годовой отчет не загружен!");
                } else {
                    if ((!MonthAndYearReportsCheck.isFillYearlyReport(yearlyReport)
                            || !MonthAndYearReportsCheck.isFillMonthlyReports(monthlyReports))
                            && MonthAndYearReportsCheck.reconciliationOfIncome(yearlyReport, monthlyReports)
                    ) {
                        System.out.println("Отчеты сходятся");
                    } else {
                        System.out.println("Отчеты не сходятся!");
                    }
                }
            } else if (userInput == 4) {
                if (monthlyReports != null) {
                    for (MonthlyReport report: monthlyReports) {
                        String maxProfit = report.profitableProductsOfTheMonth();
                        String maxExpense = report.expensesProductsOfTheMonth();
                        System.out.println("Месяц: " + report.getMonthName());
                        System.out.println("Максимальный доход. " + maxProfit);
                        System.out.println("Максимальный расход. " + maxExpense);
                    }
                } else {
                    System.out.println("Месячные отчеты не загржуены!");
                }
            } else if (userInput == 5) {
                if (yearlyReport != null) {
                    System.out.println("Отчет по 2021 году");
                    Map<String, Double> profitForMoths = yearlyReport.getMontlyProfit();
                    for (Map.Entry<String, Double> entry: profitForMoths.entrySet()) {
                        System.out.println("Месяц: " + entry.getKey() + " Прибыль: " + entry.getValue());
                    }
                    Double middleExpense = yearlyReport.getMiddleExpense();
                    System.out.println("Средний расход за год: " + middleExpense.intValue());
                    Double middleProfit = yearlyReport.getMiddleProfit();
                    System.out.println("Cредняя прибыль за год: " + middleProfit.intValue());
                } else {
                    System.out.println("Годовой отчет не загружен!");
                }
            } else {
                System.out.println("Такой команды нет.");
                break;
            }
        }
    }

    public static void printMenu(){
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
    }
}

