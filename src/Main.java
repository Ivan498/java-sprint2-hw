import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Main {

    public static void main(String[] args) {
        MonthAndYearReportsCheck monthAndYearReportsCheck = new MonthAndYearReportsCheck();
        ReportEngine reportEngine = new ReportEngine();
        FileReader reader = new FileReader();
        Scanner scanner = new Scanner(System.in);
        List<String> stringTransactions = new ArrayList<>();
        MonthlyReport monthlyReport = new MonthlyReport();
        InfoYearAndMounth infoYearAndMounth = new InfoYearAndMounth();
        Transaction transaction = new Transaction();
        while(true){
            printMenu();
            int userInput  = scanner.nextInt();
            if(userInput == 1){
                reportEngine.addMonthlyReportsList(reader);
            } else if (userInput == 2) {
                reportEngine.addYearlyReportsList(reader);
            } else if (userInput == 3) {

                if(!monthAndYearReportsCheck.isFillYearlyReport(reportEngine.yearlyReport) || !monthAndYearReportsCheck.isFillMonthlyReports(reportEngine.monthlyReportsList)){
                    System.out.println("Не сходятся отчёты");
                }
                else {
                    System.out.println("Этот-и месяц-а не сходиться " + monthAndYearReportsCheck.reconciliationOfIncome(reportEngine.yearlyReport, reportEngine.monthlyReportsList));
                }
            } else if (userInput == 4) {
                

            } else if (userInput == 5) {
                return;
            }
            else{
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

