import { useState, useEffect } from 'react';
// Bileşenleri import ediyoruz
import ExpenseForm from './components/ExpenseForm';
import BudgetStatus from './components/BudgetStatus'; // Yeni bileşen
import Analytics from './components/Analytics';
import RecentExpenses from './components/RecentExpenses'; // Yeni bileşen
// Servisleri import ediyoruz (API bağlantısı için)
import { getAllExpenses, createExpense, deleteExpense } from './services/expenseService';
// Stil dosyamız
import './App.css';

function App() {
    const [expenses, setExpenses] = useState([]);

    const fetchExpenses = async () => {
        try {
            const response = await getAllExpenses();
            setExpenses(response.data);
        } catch (error) {
            console.error('Harcamaları getirirken hata oluştu:', error);
        }
    };

    useEffect(() => {
        // Mock data yerine API'den veri çekmek için bu satırı aktif et
        // fetchExpenses();

        // Geliştirme için sahte veri (Backend çalıştırmadan denemek için)
        const mockExpenses = [
            { id: 1, title: 'Öğle Yemeği', amount: 445.00, category: 'Yemek', date: '2025-07-15T14:30:00Z' },
            { id: 2, title: 'Otobüs Bilet', amount: 815.00, category: 'Ulaşım', date: '2025-07-15T09:15:00Z' },
            { id: 3, title: 'Market Alışverişi', amount: 8156.50, category: 'Alışveriş', date: '2025-07-14T18:00:00Z' },
            { id: 4, title: 'Sinema Bileti', amount: 835.00, category: 'Eğlence', date: '2025-07-14T20:00:00Z' }
        ];
        setExpenses(mockExpenses);

    }, []);

    const handleAddExpense = async (expenseData) => {
        // Sahte veri listesine ekleme
        const newExpense = { id: Date.now(), ...expenseData, date: new Date().toISOString() };
        setExpenses(prevExpenses => [newExpense, ...prevExpenses]);

        // API ile çalışırken kullanılacak kod
        /*
        try {
            await createExpense(expenseData);
            fetchExpenses();
        } catch (error) {
            console.error('Harcama eklenirken hata:', error);
        }
        */
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
                    <BudgetStatus />
                </div>
                <div className="card large">
                    <h2><i className="icon">📊</i> Harcama Analizi</h2>
                    <p className="placeholder-text">Grafik burada görüntülenecek</p>
                    {/* <Analytics expenses={expenses} /> */}
                </div>
                <div className="card large">
                    <h2><i className="icon">📜</i> Son Harcamalar</h2>
                    <RecentExpenses expenses={expenses} />
                </div>
            </main>
        </div>
    );
}

export default App;