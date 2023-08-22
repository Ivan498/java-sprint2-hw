import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyReport {
    List<Transaction> monthlyReport = new ArrayList<>();
    static String mounth;
    public Map<String, Double> profitableProductOfTheMonth(List<MonthlyReport> monthlyReportList, Transaction transaction, String month, String itemName) {
        Map<String, Double> product = new HashMap<>();
        // Инициализируем переменную для хранения максимальной прибыли
        double maxProfit = 0;
        // Инициализируем переменную для хранения названия самого прибыльного товара
        String mostProfitableProduct = "";

        for (MonthlyReport monthlyReport : monthlyReportList) {
            if (!transaction.isExpense && month.equals("01")) {
                for (String keyProducts : itemName) {
                    // Получаем сумму по текущему товару в текущем месяце
                    double currentProfit;

                    // Если текущая прибыль больше максимальной прибыли, обновляем переменные
                    if (currentProfit > maxProfit) {
                        maxProfit = currentProfit;
                        mostProfitableProduct = keyProducts;
                    }
                }
            }
        }

        // Добавляем название самого прибыльного товара и его сумму в результат
        product.put(mostProfitableProduct, maxProfit);

        return product;
    }
}

