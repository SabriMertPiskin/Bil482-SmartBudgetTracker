package com.smart_budget_tracker.bil482.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * WebsiteController
 * 
 * @author Çağan Durgun
 */
@Controller
public class WebsiteController {

    /// Yazılımın giriş fonksiyonu.
    /// http://localhost:8080 linki ile erişilebilir.
    @GetMapping("/")
    public String showHomePage() {
        return "redirect:/main.html"; // ✅ Static dosya için redirect
    }
}
