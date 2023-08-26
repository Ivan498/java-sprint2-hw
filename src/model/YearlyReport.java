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
        for (MonthlyTransaction monthlyTransaction: profitTransactions) {
            if (!months.containsKey(monthlyTransaction.getMonth())) {
                months.put(monthlyTransaction.getMonth(), monthlyTransaction.getAmount());
            } else {
                months.put(monthlyTransaction.getMonth(), months.get(monthlyTransaction.getMonth()) + monthlyTransaction.getAmount());
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
