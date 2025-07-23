import { useState, useEffect } from 'react';
import ExpenseForm from './components/ExpenseForm';
import BudgetStatus from './components/BudgetStatus';
import Analytics from './components/Analytics';
import RecentExpenses from './components/RecentExpenses';
import BudgetModal from './components/BudgetModal';
// MEVCUT SERVİSLER
import { getAllExpenses, createExpense } from './services/expenseService';
// YENİ EKLENECEK SERVİSLER (Bu dosyaları oluşturmanız gerekecek)
import { getAllBudgets, createBudget } from './usecase_2/services/budgetService'; 
import { getAllNotifications } from './usecase_2/services/notificationService';
import Notifications from './usecase_2/components/Notifications'; // YENİ EKLENECEK COMPONENT

import './App.css';

function App() {
    const [expenses, setExpenses] = useState([]);
    const [budgets, setBudgets] = useState([]); // YENİ STATE
    const [notifications, setNotifications] = useState([]); // YENİ STATE
    const [isBudgetModalOpen, setBudgetModalOpen] = useState(false);

    // Harcamaları getiren fonksiyon (Mevcut)
    const fetchExpenses = async () => {
        try {
            const response = await getAllExpenses();
            setExpenses(response.data);
            console.log("Backend'den harcama verileri başarıyla çekildi.");
        } catch (error) {
            console.error('Backend bağlantı hatası! Harcamalar için mock veriler kullanılıyor:', error);
            const mockExpenses = [
                { id: 1, title: 'Öğle Yemeği', amount: 445.00, category: 'Yemek', date: '2025-07-15T14:30:00Z' },
                { id: 2, title: 'Otobüs Bileti', amount: 815.00, category: 'Ulaşım', date: '2025-07-15T09:15:00Z' },
                { id: 3, title: 'Market Alışverişi', amount: 8156.50, category: 'Alışveriş', date: '2025-07-14T18:00:00Z' },
                { id: 4, title: 'Sinema Bileti', amount: 835.00, category: 'Eğlence', date: '2025-07-14T20:00:00Z' }
            ];
            setExpenses(mockExpenses);
        }
    };
    
    // YENİ: Bütçeleri getiren fonksiyon
    const fetchBudgets = async () => {
        try {
            const response = await getAllBudgets();
            setBudgets(response.data);
            console.log("Backend'den bütçe verileri başarıyla çekildi.");
        } catch (error) {
            console.error("Bütçeler çekilirken hata oluştu:", error);
        }
    };
    
    // YENİ: Bildirimleri getiren fonksiyon
    const fetchNotifications = async () => {
        try {
            const response = await getAllNotifications();
            setNotifications(response.data);
        } catch (error) {
            console.error("Bildirimler çekilirken hata oluştu:", error);
        }
    };

    useEffect(() => {
        fetchExpenses();
        fetchBudgets();
        // Bildirimleri periyodik olarak çekmek isteyebilirsiniz
        const interval = setInterval(fetchNotifications, 5000); // Örn: 5 saniyede bir
        return () => clearInterval(interval);
    }, []);

    const handleAddExpense = async (expenseData) => {
        try {
            await createExpense(expenseData);
            fetchExpenses(); // Harcamaları yeniden çek
            fetchBudgets(); // Bütçe durumunu da güncellemek için
        } catch (error) {
            console.error('Harcama eklenirken hata:', error);
            const newExpense = { id: Date.now(), ...expenseData, date: new Date().toISOString() };
            setExpenses(prevExpenses => [newExpense, ...prevExpenses]);
        }
    };
    
    // GÜNCELLENDİ: Bütçe kaydetme fonksiyonu artık API'ye istek atıyor
    const handleSaveBudget = async (budgetData) => {
        try {
            // Strategy backend'de seçileceği için şimdilik 'MONTHLY' gönderilebilir
            await createBudget({ ...budgetData, strategyType: 'MONTHLY' });
            fetchBudgets(); // Bütçeleri yeniden çek
            alert(`${budgetData.category} kategorisi için bütçe ₺${budgetData.limit} olarak ayarlandı.`);
        } catch (error) {
            console.error("Bütçe kaydedilirken hata:", error);
            alert("Bütçe kaydedilirken bir hata oluştu.");
        }
    };

    return (
        <div className="app-container">
            {/* YENİ: Bildirimler Component'i */}
            <Notifications notifications={notifications} />

            <header className="app-header">
                <h1>Smart Budget Tracker</h1>
                <div className="user-profile">
                    <span>Çağan Durgun</span>
                    <div className="user-avatar">Ç</div>
                </div>
            </header>
            <main className="dashboard-grid">
                <div className="card">
                    <h2><i className="icon">⚡</i> Hızlı Harcama Ekle</h2>
                    <ExpenseForm onAddExpense={handleAddExpense} />
                </div>
                <div className="card">
                    <h2><i className="icon">🎯</i> Bütçe Durumu</h2>
                    {/* GÜNCELLENDİ: Artık mock data yerine state'den gelen veriyi kullanıyor */}
                    <BudgetStatus 
                        budgets={budgets} 
                        expenses={expenses} 
                        onSetBudget={() => setBudgetModalOpen(true)} 
                    />
                </div>
                <div className="card large">
                    <h2><i className="icon">📊</i> Harcama Analizi</h2>
                     <Analytics expenses={expenses} />
                </div>
                <div className="card large">
                    <h2><i className="icon">📜</i> Son Harcamalar</h2>
                    <RecentExpenses expenses={expenses} />
                </div>
            </main>

            <BudgetModal
                isOpen={isBudgetModalOpen}
                onClose={() => setBudgetModalOpen(false)}
                onSaveBudget={handleSaveBudget}
            />
        </div>
    );
}

export default App;