package model;

public class MonthlyTransaction {
    private String month;
    private double amount;
    private boolean isExpense;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }
}
