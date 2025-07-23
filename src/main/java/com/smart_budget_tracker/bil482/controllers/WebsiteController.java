package com.smart_budget_tracker.bil482.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Çağan Durgun
 */
@Controller
public class WebsiteController {

    /// Yazılımın giriş fonksiyonu.
    @GetMapping("/")
    public String showHomePage() {
        return "redirect:/index.html"; // ✅ Static dosya için redirect
    }
}
