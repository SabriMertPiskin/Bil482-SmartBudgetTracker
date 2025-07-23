/**package com.smart_budget_tracker.bil482.facade;


import com.smart_budget_tracker.bil482.builder.AnalyticsDirector;
import com.smart_budget_tracker.bil482.builder.AnalyticsReport;

**
 * DashboardFacade ---------------------- [Facade Pattern] AnalyticsDirector ve
 * AnalyticsReportBuilder'ın karmaşıklığını saklayıp, frontend'e tek bir basit
 * API ile dashboard raporunu döner. "View Analytics Dashboard" use case'i için
 * oluşturulmuştur.
 *
public class DashboardFacade {

    private AnalyticsDirector director;

    public DashboardFacade() {
        this.director = new AnalyticsDirector();
    }

    public AnalyticsReport getDashboardReport() {
        // Mock rapor döndürülüyor.
        return director.buildMockReport();
    }
}
*/
