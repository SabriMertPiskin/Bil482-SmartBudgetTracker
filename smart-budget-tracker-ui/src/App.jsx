import { useState, useEffect } from 'react';
import ExpenseForm from './components/ExpenseForm';
import BudgetStatus from './components/BudgetStatus';
import Analytics from './components/Analytics';
import RecentExpenses from './components/RecentExpenses';
import BudgetModal from './components/BudgetModal';
import { getAllExpenses, createExpense } from './services/expenseService';
import './App.css';

function App() {
    const [expenses, setExpenses] = useState([]);
    const [isBudgetModalOpen, setBudgetModalOpen] = useState(false);

    const fetchExpenses = async () => {
        try {
            const response = await getAllExpenses();
            setExpenses(response.data);
            console.log("Backend'den veriler başarıyla çekildi.");
        } catch (error) {
            console.error('Backend bağlantı hatası! Mock veriler kullanılıyor:', error);
            const mockExpenses = [
                { id: 1, title: 'Öğle Yemeği', amount: 445.00, category: 'Yemek', date: '2025-07-15T14:30:00Z' },
                { id: 2, title: 'Otobüs Bileti', amount: 815.00, category: 'Ulaşım', date: '2025-07-15T09:15:00Z' },
                { id: 3, title: 'Market Alışverişi', amount: 8156.50, category: 'Alışveriş', date: '2025-07-14T18:00:00Z' },
                { id: 4, title: 'Sinema Bileti', amount: 835.00, category: 'Eğlence', date: '2025-07-14T20:00:00Z' }
            ];
            setExpenses(mockExpenses);
        }
    };

    useEffect(() => {
        fetchExpenses();
    }, []);

    const handleAddExpense = async (expenseData) => {
        try {
            await createExpense(expenseData);
            fetchExpenses();
        } catch (error) {
            console.error('Harcama eklenirken hata:', error);
            const newExpense = { id: Date.now(), ...expenseData, date: new Date().toISOString() };
            setExpenses(prevExpenses => [newExpense, ...prevExpenses]);
        }
    };
    
    const handleSaveBudget = (budgetData) => {
        console.log('Kaydedilen Bütçe:', budgetData);
        alert(`${budgetData.category} kategorisi için bütçe ₺${budgetData.limit} olarak ayarlandı.`);
    };

    return (
        <div className="app-container">
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
                    <BudgetStatus onSetBudget={() => setBudgetModalOpen(true)} />
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