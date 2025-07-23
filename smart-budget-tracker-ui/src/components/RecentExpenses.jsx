import React, { useState } from 'react';

const ICONS = {
    'Yemek': { icon: '🍔', color: '#FF6384' },
    'Ulaşım': { icon: '🚌', color: '#36A2EB' },
    'Eğlence': { icon: '🎬', color: '#FFCE56' },
    'Alışveriş': { icon: '🛒', color: '#4BC0C0' },
    'Fatura': { icon: '🧾', color: '#9966FF' }
};

const RecentExpenses = ({ expenses, onDelete, onEdit }) => {
    const [editingId, setEditingId] = useState(null);
    const [editData, setEditData] = useState({ title: '', amount: '', category: '' });

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleTimeString('tr-TR', { hour: '2-digit', minute: '2-digit' });
    };

    const startEdit = (expense) => {
        setEditingId(expense.id);
        setEditData({
            title: expense.title,
            amount: expense.amount,
            category: expense.category
        });
    };

    const handleEditChange = (e) => {
        const { name, value } = e.target;
        setEditData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleEditSave = (id) => {
        onEdit(id, {
            ...editData,
            amount: Number(editData.amount)
        });
        setEditingId(null);
    };

    return (
        <div className="recent-expenses-list">
            {expenses.slice(0, 4).map(expense => {
                const { icon, color } = ICONS[expense.category] || { icon: '💰', color: '#CCCCCC' };
                return (
                    <div key={expense.id} className="expense-item">
                        <div className="expense-item-icon" style={{ backgroundColor: color }}>{icon}</div>
                        <div className="expense-item-details">
                            {editingId === expense.id ? (
                                <>
                                    <input name="title" value={editData.title} onChange={handleEditChange} />
                                    <input name="amount" value={editData.amount} onChange={handleEditChange} type="number" />
                                    <input name="category" value={editData.category} onChange={handleEditChange} />
                                </>
                            ) : (
                                <>
                                    <span>{expense.title}</span>
                                    <span className="time">{formatDate(expense.date)}</span>
                                </>
                            )}
                        </div>
                        <div className="expense-item-amount">
                            {editingId === expense.id
                                ? null
                                : <>₺{expense.amount.toFixed(2)}</>
                            }
                        </div>
                        <div>
                            {editingId === expense.id ? (
                                <button className="edit-btn" onClick={() => handleEditSave(expense.id)}>Kaydet</button>
                            ) : (
                                <button className="edit-btn" onClick={() => startEdit(expense)}>Düzenle</button>
                            )}
                            <button className="delete-btn" onClick={() => onDelete(expense.id)}>Sil</button>
                        </div>
                    </div>
                );
            })}
        </div>
    );
};

export default RecentExpenses;
