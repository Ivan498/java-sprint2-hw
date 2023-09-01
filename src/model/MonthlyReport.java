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
    return maxByFilter(false);
  }

  public String expensesProductsOfTheMonth() {
    return maxByFilter(true);
  }

  public String getMonthName() {
    if (month.equals("01")) {
      return "Январь";
    }
    if (month.equals("02")) {
      return "Февраль";
    } else {
      return "Март";
    }
  }

  private String maxByFilter(boolean isExpense) {
    double max = 0;
    String maxItemName = "";
    Map<String, Double> products = new HashMap<>();
    List<Transaction> transactionList = new ArrayList<>();

    for (Transaction transaction: transactions) {
      if (transaction.isExpense() == isExpense) {
        transactionList.add(transaction);
      }
    }

    for (Transaction transaction: transactionList) {
      products.put(transaction.getItemName(), transaction.getQuantity() * transaction.getUnitPrice());
    }
    for (Map.Entry<String, Double> keyValue: products.entrySet()) {
      if (keyValue.getValue() > max) {
        max = keyValue.getValue();
        maxItemName = keyValue.getKey();
      }
    }

    String result = "Товар: " + maxItemName + ". Сумма: " + products.get(maxItemName).intValue();
    return result;
  }
}
