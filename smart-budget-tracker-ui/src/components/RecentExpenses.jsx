import React from 'react';

const ICONS = {
    'Yemek': { icon: '🍔', color: '#FF6384' },
    'Ulaşım': { icon: '🚌', color: '#36A2EB' },
    'Eğlence': { icon: '🎬', color: '#FFCE56' },
    'Alışveriş': { icon: '🛒', color: '#4BC0C0' },
    'Fatura': { icon: '🧾', color: '#9966FF' }
};

const RecentExpenses = ({ expenses }) => {
    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleTimeString('tr-TR', { hour: '2-digit', minute: '2-digit' });
    };

    return (
        <div className="recent-expenses-list">
            {expenses.slice(0, 4).map(expense => {
                const { icon, color } = ICONS[expense.category] || { icon: '💰', color: '#CCCCCC' };
                return (
                    <div key={expense.id} className="expense-item">
                        <div className="expense-item-icon" style={{ backgroundColor: color }}>{icon}</div>
                        <div className="expense-item-details">
                            <span>{expense.title}</span>
                            <span className="time">{formatDate(expense.date)}</span>
                        </div>
                        <div className="expense-item-amount">
                            ₺{expense.amount.toFixed(2)}
                        </div>
                    </div>
                );
            })}
        </div>
    );
};

export default RecentExpenses;