package com.smart_budget_tracker.bil482;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart_budget_tracker.bil482.command.ExpenseCommandInvoker;
import com.smart_budget_tracker.bil482.controllers.ExpenseController;
import com.smart_budget_tracker.bil482.entity.Expense;
import com.smart_budget_tracker.bil482.factory.ExpenseFactory;
import com.smart_budget_tracker.bil482.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseService;

    @MockBean
    private ExpenseCommandInvoker commandInvoker;

    @MockBean
    private ExpenseFactory expenseFactory;

    @Autowired
    private ObjectMapper objectMapper;

    private Expense expense1;
    private Expense expense2;

    @BeforeEach
    void setUp() {
        expense1 = new Expense(1L, "Öğle Yemeği", new BigDecimal("150.00"), LocalDateTime.now(), "Yemek");
        expense2 = new Expense(2L, "Otobüs Bileti", new BigDecimal("50.00"), LocalDateTime.now(), "Ulaşım");
    }

    @Test
    void getAllExpenses_shouldReturnListOfExpenses() throws Exception {
        List<Expense> allExpenses = Arrays.asList(expense1, expense2);
        when(expenseService.getAllExpenses()).thenReturn(allExpenses);

        mockMvc.perform(get("/expenses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Öğle Yemeği")))
                .andExpect(jsonPath("$[1].title", is("Otobüs Bileti")));
    }

    @Test
    void getExpenseById_shouldReturnExpense() throws Exception {
        when(expenseService.getExpenseById(1L)).thenReturn(Optional.of(expense1));

        mockMvc.perform(get("/expenses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Öğle Yemeği")));
    }

    @Test
    void getExpenseById_shouldReturnNotFound() throws Exception {
        when(expenseService.getExpenseById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/expenses/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addExpense_shouldReturnCreatedExpense() throws Exception {
        // Factory'nin bir builder döndürdüğünü varsayıyoruz.
        // Bu kısmı kendi factory implementasyonunuza göre düzenleyebilirsiniz.
        when(expenseFactory.createExpenseBuilder(any())).thenReturn(
                new com.smart_budget_tracker.bil482.factory.VariableExpenseBuilder()
                    .setTitle(expense1.getTitle())
                    .setAmount(expense1.getAmount())
                    .setCategory(expense1.getCategory())
                    .setDate(expense1.getDate())
        );

        mockMvc.perform(post("/expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expense1)))
                .andExpect(status().isOk());
    }


    @Test
    void deleteExpense_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/expenses/1"))
                .andExpect(status().isNoContent());
    }
}