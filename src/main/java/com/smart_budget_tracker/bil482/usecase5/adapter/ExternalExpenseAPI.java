/**import java.util.*;
external data integration
public class ExternalExpenseAPI {

    // Gerçekte bir REST API'den geliyor gibi düşün (şu an sabit veri)
    public List<Map<String, String>> getExpensesFromExternalSystem() {
        List<Map<String, String>> externalExpenses = new ArrayList<>();

        Map<String, String> expense1 = new HashMap<>();
        expense1.put("type", "Food");
        expense1.put("amount", "150.75");
        expense1.put("date", "2025-07-20");
        expense1.put("note", "Lunch at cafe");

        Map<String, String> expense2 = new HashMap<>();
        expense2.put("type", "Transport");
        expense2.put("amount", "60");
        expense2.put("date", "2025-07-21");
        expense2.put("note", "Bus card top-up");

        externalExpenses.add(expense1);
        externalExpenses.add(expense2);

        return externalExpenses;
    }
}
*/
