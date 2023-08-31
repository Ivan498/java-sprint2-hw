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
                if (monthlyReports == null ) {
                    System.out.println("Месячные отчеты не загржуены!");
                } else if (yearlyReport == null){
                    System.out.println("Годовой отчет не загружен!");
                } else {
                    if (!MonthAndYearReportsCheck.isFillYearlyReport(yearlyReport)
                            || !MonthAndYearReportsCheck.isFillMonthlyReports(monthlyReports)
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
                        String maxExpense = report.expensableProductsOfTheMonth();
                        System.out.println("Название месяца: " + report.getMonth());
                        System.out.println("Продукт с наибольшим профитом: " + maxProfit);
                        System.out.println("Продукт с наибольшей растратой: " + maxExpense);
                    }
                } else {
                    System.out.println("Месячные отчеты не загржуены!");
                }
            } else if (userInput == 5) {
                if (yearlyReport != null) {
                    System.out.println("Рассматриваемый год: 2021");
                    Map<String, Double> profitForMoths = yearlyReport.getMontlyProfit();
                    for (Map.Entry<String, Double> entry: profitForMoths.entrySet()) {
                        System.out.println("Прибыль в месяце: " + entry.getKey() + " " + entry.getValue());
                    }
                    double middleExpense = yearlyReport.getMiddleExpense();
                    System.out.println("Средний расход за год: " + middleExpense);
                    double middleProfit = yearlyReport.getMiddleProfit();
                    System.out.println("Cредняя прибыль за год: " + middleProfit);
                } else {
                    System.out.println("Годовой отчет не загружен!");
                }
            } else {
                System.out.println("Такой команды нет.");
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

