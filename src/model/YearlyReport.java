package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YearlyReport {
    private List<MonthlyTransaction> monthlyTransactions;

    public YearlyReport () {

    }

    public List<MonthlyTransaction> getMonthlyTransactions() {
        return monthlyTransactions;
    }

    public void setMonthlyTransactions(List<MonthlyTransaction> monthlyTransactions) {
        this.monthlyTransactions = monthlyTransactions;
    }

    public Map<String, Double> getMontlyProfit() {
        Map<String, Double> months = new HashMap<>();
        List<MonthlyTransaction> profitTransactions = new ArrayList<>();
        for (MonthlyTransaction transaction: monthlyTransactions) {
            if (!transaction.isExpense()) {
                profitTransactions.add(transaction);
            }
        }
        for (MonthlyTransaction transaction: profitTransactions) {
            months.put(transaction.getMonth(), transaction.getAmount());
        }
        for (MonthlyTransaction transaction: monthlyTransactions) {
            if (transaction.isExpense()) {
                months.put(transaction.getMonth(), months.get(transaction.getMonth())- transaction.getAmount());
            }
        }
        return months;
    }

    public double getMiddleExpense() {
        double res = 0;
        List<MonthlyTransaction> expenseList = new ArrayList<>();
        for (MonthlyTransaction transaction: monthlyTransactions) {
            if (transaction.isExpense()) {
                expenseList.add(transaction);
                res += transaction.getAmount();
            }
        }
        return res / expenseList.size();
    }

    public double getMiddleProfit() {
        double res = 0;
        List<MonthlyTransaction> profitTransactions = new ArrayList<>();
        for (MonthlyTransaction transaction: monthlyTransactions) {
            if (!transaction.isExpense()) {
                profitTransactions.add(transaction);
                res += transaction.getAmount();
            }
        }
        return res / profitTransactions.size();
    }
}
