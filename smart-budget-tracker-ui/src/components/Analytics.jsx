import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Pie } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);

const Analytics = ({ expenses }) => {
    // Harcamaları kategorilere göre grupla ve toplamlarını hesapla
    const categoryData = expenses.reduce((acc, expense) => {
        const { category, amount } = expense;
        if (!acc[category]) {
            acc[category] = 0;
        }
        acc[category] += amount;
        return acc;
    }, {});

    const data = {
        labels: Object.keys(categoryData), // Kategoriler: ['Yemek', 'Ulaşım', ...]
        datasets: [
            {
                label: 'Harcama Dağılımı',
                data: Object.values(categoryData), // Toplamlar: [500, 250, ...]
                backgroundColor: [
                    'rgba(255, 99, 132, 0.7)',
                    'rgba(54, 162, 235, 0.7)',
                    'rgba(255, 206, 86, 0.7)',
                    'rgba(75, 192, 192, 0.7)',
                    'rgba(153, 102, 255, 0.7)',
                    'rgba(255, 159, 64, 0.7)',
                ],
                borderColor: 'white',
                borderWidth: 2,
            },
        ],
    };

    return (
        <div>
            {expenses.length > 0 ? (
                <Pie data={data} />
            ) : (
                <p>Grafik için yeterli veri bulunmuyor.</p>
            )}
        </div>
    );
};

export default Analytics;