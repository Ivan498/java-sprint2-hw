package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyReport {

  private boolean isReaded;
  private String month;
  private List<Transaction> transactions;

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> data) {
    this.transactions = data;
  }

  public String profitableProductsOfTheMonth() {
    double max = 0;
    String maxItemName = "";
    Map<String, Double> products = new HashMap<>();
    List<Transaction> profitTransactions = new ArrayList<>();

    for (Transaction transaction: transactions) {
      if (!transaction.isExpense()) {
        profitTransactions.add(transaction);
      }
    }

    for (Transaction transaction: profitTransactions) {
      products.put(
              transaction.getItemName(), transaction.getQuantity() * transaction.getUnitPrice()
      );
    }
    for (Map.Entry<String, Double> keyValue: products.entrySet()) {
      if (keyValue.getValue() > max) {
        maxItemName = keyValue.getKey();
      }
    }

    String result = month + " " + maxItemName + " " + products.get(maxItemName);
    return result;
  }

  public String expensableProductsOfTheMonth() {
    double max = 0;
    String maxItemName = "";
    Map<String, Double> products = new HashMap<>();
    List<Transaction> expenseTransactions = new ArrayList<>();

    for (Transaction transaction: transactions) {
      if (transaction.isExpense()) {
        expenseTransactions.add(transaction);
      }
    }

    for (Transaction transaction: expenseTransactions) {
      products.put(
              transaction.getItemName(), transaction.getQuantity() * transaction.getUnitPrice()
      );
    }
    for (Map.Entry<String, Double> keyValue: products.entrySet()) {
      if (keyValue.getValue() > max) {
        maxItemName = keyValue.getKey();
      }
    }

    String result = month + " " + maxItemName + " " + products.get(maxItemName);
    return result;
  }
}
