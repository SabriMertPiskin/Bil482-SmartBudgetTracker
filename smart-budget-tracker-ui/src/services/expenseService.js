import axios from 'axios';

// Backend API'sinin temel adresi
const API_URL = 'http://localhost:8080/expenses';

// Tüm harcamaları getirir
export const getAllExpenses = () => {
    return axios.get(API_URL);
};

// ID ile tek bir harcamayı getirir
export const getExpenseById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

// Yeni bir harcama oluşturur
// expenseData: { title, amount, category, date }
export const createExpense = (expenseData) => {
    return axios.post(API_URL, expenseData);
};

// Mevcut bir harcamayı günceller
export const updateExpense = (id, expenseData) => {
    return axios.put(`${API_URL}/${id}`, expenseData);
};

// Bir harcamayı siler
export const deleteExpense = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};

// Son işlemi geri alır
export const undoLastAction = () => {
    return axios.post(`${API_URL}/undo`);
};