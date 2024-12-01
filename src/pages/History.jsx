import hcmut from '~/assets/hcmut.png'
import { useState } from 'react';
import { useLoaderData } from 'react-router-dom';
import Layout from '~/pages/Layout';
import PrintingLog from '~/components/PrintingLog';
import PaperLog from '~/components/PaperLog';

function History() {
  const { user } = useLoaderData();

  const [isPrinting, setIsPrinting] = useState(true);

  const handleClickPrintingHistory = () => {
    setIsPrinting(true);
  }

  const handleClickBuyPaperHistory = () => {
    setIsPrinting(false);
  }

  return (
    <div>
      <Layout
        title="Thông tin tài khoản"
        leftLabel="Trang chủ"
        leftLink="/"
        rightLabel="Bắt đầu in"
        rightLink="/printer"
      >
        <div className="px-96 text-gray-800">
          <div className="flex items-center space-x-4">
            <div
              className="relative w-24 h-24 border-8 border-primary-300 rounded-full overflow-hidden flex items-center justify-center shadow-md"
            >
              <img src={hcmut} alt="HCMUT Logo" className="object-cover w-24 h-24 absolute right-2" />
            </div>

            <div className="text-sky-900 font-semibold text-md">
              <p className="font-bold text-lg">{user.name}</p>
              <p>{user.faculty}</p>
              <p>Lượng giấy còn lại: {user.paperQuantity} trang</p>
            </div>
          </div>

          <div className="flex space-x-4 mt-6">
            <button
              onClick={handleClickPrintingHistory}
              className={`px-4 py-2 min-w-40 rounded ${isPrinting ? 'bg-primary-500 text-white' : 'bg-gray-300 text-gray-700'
                }`}
            >
              Lịch sử in
            </button>
            <button
              onClick={handleClickBuyPaperHistory}
              className={`px-4 py-2 min-w-40 rounded ${!isPrinting ? 'bg-primary-500 text-white' : 'bg-gray-300 text-gray-700'
                }`}
            >
              Lịch sử mua giấy
            </button>
          </div>

          {isPrinting && (
            <PrintingLog histories={user.printingLogs} />
          )}

          {!isPrinting && (
            <PaperLog histories={user.paperLogs} />
          )}
        </div>
      </Layout>

    </div>
  );
}

export default History;
