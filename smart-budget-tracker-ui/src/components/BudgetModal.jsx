
import { useState } from 'react';

const BudgetModal = ({ isOpen, onClose, onSaveBudget }) => {
    const [category, setCategory] = useState('Yemek');
    const [limit, setLimit] = useState('');

    if (!isOpen) {
        return null;
    }

    const handleSave = () => {
        if (!limit || isNaN(parseFloat(limit)) || parseFloat(limit) <= 0) {
            alert('Lütfen geçerli bir bütçe limiti girin.');
            return;
        }
        onSaveBudget({ category, limit: parseFloat(limit) });
        onClose(); 
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Bütçe Limiti Belirle</h2>
                <div className="form-group">
                    <label htmlFor="budget-category">Kategori</label>
                    <select id="budget-category" value={category} onChange={(e) => setCategory(e.target.value)}>
                        <option value="Yemek">Yemek</option>
                        <option value="Ulaşım">Ulaşım</option>
                        <option value="Eğlence">Eğlence</option>
                        <option value="Alışveriş">Alışveriş</option>
                        <option value="Fatura">Fatura</option>
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="budget-limit">Limit (₺)</label>
                    <input
                        id="budget-limit"
                        type="number"
                        value={limit}
                        onChange={(e) => setLimit(e.target.value)}
                        placeholder="Örn: 2000"
                    />
                </div>
                <div className="modal-actions">
                    <button onClick={onClose} className="btn-secondary">İptal</button>
                    <button onClick={handleSave} className="btn-primary">Kaydet</button>
                </div>
            </div>
        </div>
    );
};

export default BudgetModal;