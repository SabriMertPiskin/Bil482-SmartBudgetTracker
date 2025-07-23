/**package com.smart_budget_tracker.bil482.builder;


 * AnalyticsReportBuilder ---------------------- [Builder Pattern]
 * AnalyticsReport objesini modüler ve adım adım inşa etmek için kullanılır.
 *
public class AnalyticsReportBuilder {

    private AnalyticsReport report;

    public AnalyticsReportBuilder() {
        this.report = new AnalyticsReport();
    }

    public AnalyticsReportBuilder addUserStats(String userStats) {
        report.setUserStats(userStats);
        return this;
    }

    public AnalyticsReportBuilder addExpenseSummary(String summary) {
        report.setExpenseSummary(summary);
        return this;
    }

    public AnalyticsReportBuilder addTopCategories(String categories) {
        report.setTopCategories(categories);
        return this;
    }

    public AnalyticsReport build() {
        return this.report;
    }
}
*/
