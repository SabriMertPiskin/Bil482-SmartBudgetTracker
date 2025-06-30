package com.smart_budget_tracker.bil482.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is a Example Controller
 * @author Çağan Durgun
 */
@Controller
public class ExampleController {
    
    /// Yazılımın giriş fonksiyonu.
    /// http://localhost:8080 linki ile erişilebilir.
    @GetMapping("/")
    public String showHomePage() {
        return "redirect:/main.html"; // ✅ Static dosya için redirect
    }
}
