/**import java.time.LocalDate;
 * external data integration
import java.util.*;

public class ExternalExpenseAdapter implements ExpenseService {

    private ExternalExpenseAPI externalAPI;

    public ExternalExpenseAdapter(ExternalExpenseAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    @Override
    public List<Expense> importExpenses() {
        List<Map<String, String>> rawData = externalAPI.getExpensesFromExternalSystem();
        List<Expense> adaptedExpenses = new ArrayList<>();

        // Her bir veriyi sistemdeki Expense nesnesine dönüştürüyoruz
        for (Map<String, String> externalExpense : rawData) {
            try {
                String category = externalExpense.get("type");
                double amount = Double.parseDouble(externalExpense.get("amount"));
                LocalDate date = LocalDate.parse(externalExpense.get("date"));
                String description = externalExpense.get("note");

                Expense expense = new Expense(category, amount, date, description);
                adaptedExpenses.add(expense);

            } catch (Exception e) {
                System.err.println("Veri dönüştürmede hata: " + e.getMessage());
            }
        }

        return adaptedExpenses;
    }
}
*/
