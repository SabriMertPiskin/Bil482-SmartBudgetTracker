/**
 * DashboardController
 * ----------------------
 * [Presentation Layer]
 * Frontend'in dashboard analytics raporunu alması için endpoint sağlar.
 * Facade pattern üzerinden AnalyticsReport mock verisi döner.
 *
package com.smart_budget_tracker.bil482.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smart_budget_tracker.bil482.facade.DashboardFacade;
import com.smart_budget_tracker.bil482.builder.AnalyticsReport;

@RestController
public class DashboardController {

    private DashboardFacade facade = new DashboardFacade();

    @GetMapping("/api/dashboard/analytics")
    public AnalyticsReport getAnalyticsDashboard() {
        return facade.getDashboardReport();
    }
}
*/
