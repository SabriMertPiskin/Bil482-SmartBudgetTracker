import React from 'react';

const ExpenseList = ({ expenses, onDelete }) => {
    return (
        <table>
            <thead>
                <tr>
                    <th>Başlık</th>
                    <th>Tutar</th>
                    <th>Kategori</th>
                    <th>Tarih</th>
                    <th>İşlemler</th>
                </tr>
            </thead>
            <tbody>
                {/* Son eklenen harcamanın en üstte görünmesi için listeyi ters çeviriyoruz */}
                {expenses.length > 0 ? (
                    [...expenses].reverse().map((expense) => (
                        <tr key={expense.id}>
                            <td>{expense.title}</td>
                            <td>{expense.amount.toFixed(2)} ₺</td>
                            <td>{expense.category}</td>
                            <td>{new Date(expense.date).toLocaleDateString()}</td>
                            <td className="actions">
                                <button className="delete-btn" onClick={() => onDelete(expense.id)}>Sil</button>
                            </td>
                        </tr>
                    ))
                ) : (
                    <tr>
                        <td colSpan="5" style={{ textAlign: 'center' }}>Gösterilecek harcama bulunamadı.</td>
                    </tr>
                )}
            </tbody>
        </table>
    );
};

export default ExpenseList;