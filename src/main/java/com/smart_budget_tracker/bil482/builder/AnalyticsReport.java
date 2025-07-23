/**package com.smart_budget_tracker.bil482.builder;


 * Neden Builder? Raporun adım adım, parçalı olarak (modüler) üretilmesi için.
 *
 

 * AnalyticsReport ---------------------- [Builder Pattern] "View Analytics
 * Dashboard" use case'i için oluşturulan mock rapor veri sınıfı. Builder ile
 * adım adım oluşturulacak, Facade ile frontend'e tek API üzerinden sunulacak.
 
public class AnalyticsReport {

    private String userStats;
    private String expenseSummary;
    private String topCategories;

    // Getter & Setter metodları
    public void setUserStats(String userStats) {
        this.userStats = userStats;
    }

    public void setExpenseSummary(String expenseSummary) {
        this.expenseSummary = expenseSummary;
    }

    public void setTopCategories(String topCategories) {
        this.topCategories = topCategories;
    }

    @Override
    public String toString() {
        return "AnalyticsReport{"
                + "userStats='" + userStats + '\''
                + ", expenseSummary='" + expenseSummary + '\''
                + ", topCategories='" + topCategories + '\''
                + '}';
    }
}
*/
