import { useState } from 'react';

const ExpenseForm = ({ onAddExpense }) => {
    const [amount, setAmount] = useState('');
    const [description, setDescription] = useState('');
    const [category, setCategory] = useState('Yemek');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!amount || !category) {
            alert('Lütfen Tutar ve Kategori alanlarını doldurun.');
            return;
        }
        onAddExpense({
            title: description || category, // Eğer açıklama yoksa kategori adını başlık yap
            amount: parseFloat(amount),
            category: category,
        });
        setAmount('');
        setDescription('');
    };

    return (
        <form onSubmit={handleSubmit} className="expense-form">
            <div className="form-group">
                <label htmlFor="amount">Tutar</label>
                <input id="amount" type="number" value={amount} onChange={(e) => setAmount(e.target.value)} placeholder="0.00" />
            </div>
            <div className="form-group">
                <label htmlFor="description">Açıklama</label>
                <input id="description" type="text" value={description} onChange={(e) => setDescription(e.target.value)} placeholder="Harcama açıklaması" />
            </div>
             <div className="form-group">
                <label htmlFor="category">Kategori</label>
                <select id="category" value={category} onChange={(e) => setCategory(e.target.value)}>
                    <option value="Yemek">Yemek</option>
                    <option value="Ulaşım">Ulaşım</option>
                    <option value="Eğlence">Eğlence</option>
                    <option value="Alışveriş">Alışveriş</option>
                    <option value="Fatura">Fatura</option>
                </select>
            </div>
            <button type="submit" className="add-expense-btn">+ Harcama Ekle</button>
        </form>
    );
};

export default ExpenseForm;