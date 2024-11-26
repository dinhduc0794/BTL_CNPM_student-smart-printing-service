import Layout from '~/pages/Layout';
import { Link } from 'react-router-dom';

function Price() {
  const prices = [
    { id: 1, name: 'SV1', price: '10.000₫', pages: '15 trang' },
    { id: 2, name: 'SV2', price: '20.000₫', pages: '30 trang' },
    { id: 3, name: 'SV3', price: '50.000₫', pages: '150 trang' },
  ];

  return (
    <Layout
      title="Bảng giá dịch vụ"
      leftLabel="Trang chủ"
      leftLink="/home"
      rightLabel="Bắt đầu in"
      rightLink="/schedule"
    >
      <div className="flex flex-wrap justify-center gap-8 px-8 pt-8">
        {prices.map((item) => (
          <div
            key={item.id}
            className="flex flex-col items-center justify-between p-6 border rounded-lg shadow-md w-64 bg-white"
          >
            <p className="text-lg font-semibold text-gray-700">{item.name}</p>
            <p className="text-3xl font-bold text-black">{item.price}</p>
            <button className="mt-4 px-4 py-2 text-primary-500 border border-primary-500 rounded-md hover:bg-primary-100">
              Mua
            </button>
            <p className="mt-4 text-sm text-gray-500">{item.pages}</p>
          </div>
        ))}
      </div>
    </Layout>
  );
}

export default Price;
