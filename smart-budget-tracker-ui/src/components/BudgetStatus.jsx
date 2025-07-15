import React from 'react';

const budgetItems = [
    { category: 'Yemek', icon: '🍔', spent: '₺1,200', total: '₺2,000', color: '#FF6384' },
    { category: 'Ulaşım', icon: '🚌', spent: '₺850', total: '₺1,500', color: '#36A2EB' },
    { category: 'Eğlence', icon: '🎬', spent: '₺1,050', total: '₺1,800', color: '#FFCE56' }
];

const BudgetStatus = () => {
    return (
        <div className="budget-status-list">
            {budgetItems.map(item => (
                <div key={item.category} className="budget-item">
                    <div className="budget-item-icon" style={{ backgroundColor: item.color }}>{item.icon}</div>
                    <div className="budget-item-details">
                        <span>{item.category}</span>
                    </div>
                    <div className="budget-item-amount">
                        <span>{item.spent}</span>
                        <span className="total">/ {item.total}</span>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default BudgetStatus;