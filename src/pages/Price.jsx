import { useState, useMemo, useEffect } from 'react';
import Layout from '~/pages/Layout';
import { Form, useLoaderData, useNavigation } from 'react-router-dom';
import QRCode from 'react-qr-code';
import Pagination from '~/components/Pagination/Pagination';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function Price() {
  const { user } = useLoaderData();
  const navigation = useNavigation();

  const prices = [
    { id: 1, name: 'SV1', price: 10000, pages: 15 },
    { id: 2, name: 'SV2', price: 20000, pages: 30 },
    { id: 3, name: 'SV3', price: 50000, pages: 150 },
    { id: 4, name: 'SV4', price: 75000, pages: 200 },
    { id: 5, name: 'SV5', price: 100000, pages: 300 },
    { id: 6, name: 'SV6', price: 150000, pages: 450 },
    { id: 7, name: 'SV7', price: 200000, pages: 600 },
  ];

  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 3;
  const [selectedPrice, setSelectedPrice] = useState(null);
  const [paymentOption, setPaymentOption] = useState(null);
  const [message, setMessage] = useState("");

  const isSubmitting = navigation.state === 'submitting';

  const currentTableData = useMemo(() => {
    const firstPageIndex = (currentPage - 1) * pageSize;
    const lastPageIndex = firstPageIndex + pageSize;
    return prices.slice(firstPageIndex, lastPageIndex);
  }, [currentPage, prices]);

  const handleBuyClick = (price) => {
    setSelectedPrice(price);
  };

  const handleOptionSelect = (option) => {
    setPaymentOption(option);
  };

  const closeQrModal = () => {
    setSelectedPrice(null);
    setPaymentOption(null);
  };

  useEffect(() => {
    const storedMessage = sessionStorage.getItem('failedMessage');
    if (storedMessage) {
      setMessage(storedMessage);
      toast.error(storedMessage);
      sessionStorage.removeItem('failedMessage');
    }
  }, []);

  const handleCashPayment = () => {
    if (!isSubmitting) {
      closeQrModal();
      setPaymentOption(null);
    }
  };

  return (
    <div className="h-full flex flex-col">
      <Layout
        title="Bảng giá dịch vụ"
        leftLabel="Trang chủ"
        leftLink="/"
        rightLabel="Bắt đầu in"
        rightLink="/schedule"
      >
        {message && (
          <ToastContainer />
        )}

        <div className="flex-1 overflow-auto px-40 text-gray-800">
          {/* Hiển thị thông tin người dùng */}
          <div className="flex items-center space-x-4">
            <div className="text-sky-900 font-semibold text-md">
              <p className="font-bold text-lg">{user.name}</p>
              <p>{user.faculty}</p>
              <p>Lượng giấy còn lại: {user.paperQuantity} trang</p>
            </div>
          </div>

          {/* Danh sách gói dịch vụ */}
          <div className="pt-4">
            <p className="font-bold text-xl pb-4">Danh sách gói dịch vụ</p>
            <div className="overflow-hidden rounded-lg border border-gray-200">
              <table className="min-w-full table-auto">
                <thead>
                  <tr className="text-gray-500">
                    <th className="px-4 py-2 border-b font-normal">Tên gói</th>
                    <th className="px-4 py-2 border-b font-normal">Số trang</th>
                    <th className="px-4 py-2 border-b font-normal">Giá</th>
                    <th className="px-4 py-2 border-b font-normal">Hành động</th>
                  </tr>
                </thead>
                <tbody>
                  {currentTableData.map((price) => (
                    <tr key={price.id} className="hover:bg-gray-50 font-semibold text-gray-800 text-center">
                      <td className="px-6 py-2 text-sm">{price.name}</td>
                      <td className="px-6 py-2 text-sm">{price.pages} trang</td>
                      <td className="px-6 py-2 text-sm">{price.price.toLocaleString('vi-VN')}₫</td>
                      <td className="px-6 py-2 text-sm">
                        <button
                          onClick={() => handleBuyClick(price)}
                          className="px-4 py-2 text-white bg-blue-500 rounded-md hover:bg-blue-600 transition"
                        >
                          Mua
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>

            {/* Phân trang */}
            <div className="py-4 flex justify-center">
              <Pagination
                className="pagination-bar"
                currentPage={currentPage}
                totalCount={prices.length}
                pageSize={pageSize}
                onPageChange={(page) => setCurrentPage(page)}
              />
            </div>
          </div>
        </div>


        {/* Popup hiển thị lựa chọn payment */}
        {selectedPrice && !paymentOption && (
          <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-white rounded-lg p-8 w-96 text-center shadow-lg">
              <p className="text-lg font-semibold mb-6">Chọn phương thức thanh toán</p>
              <div className="flex flex-col gap-5">
                <button
                  onClick={() => handleOptionSelect('BKPAY')}
                  className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition"
                >
                  BKPay
                </button>
                <button
                  onClick={() => handleOptionSelect('CASH')}
                  className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600 transition"
                >
                  Tiền mặt
                </button>
              </div>
            </div>
          </div>
        )}

        {/* Mã QR nếu chọn BKPay */}
        {paymentOption === 'BKPAY' && selectedPrice && (
          <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-white rounded-lg p-8 w-96 text-center shadow-lg">
              <p className="text-lg font-semibold mb-6">
                Mã QR Thanh toán cho gói {selectedPrice.name}
              </p>
              <div className="flex justify-center">
                <QRCode
                  value={JSON.stringify({
                    name: user.name,
                    faculty: user.faculty,
                    pages: selectedPrice.pages,
                    price: selectedPrice.price,
                  })}
                  size={200}
                  fgColor="#0F172A"
                  bgColor="#FFFFFF"
                />
              </div>
              <p className="text-gray-500 mt-4">
                Người dùng: <span className="font-semibold">{user.name}</span>
              </p>
              <p className="text-gray-500 mt-1">
                Khoa: <span className="font-semibold">{user.faculty}</span>
              </p>
              <p className="text-gray-500 mt-1">
                Số trang: <span className="font-semibold">{selectedPrice.pages}</span>
              </p>
              <p className="text-gray-500 mt-1">
                Số tiền: <span className="font-semibold">{selectedPrice.price}₫</span>
              </p>
              <button
                onClick={closeQrModal}
                className="mt-6 px-4 py-2 bg-red-500 text-white font-semibold rounded-md hover:bg-red-600 transition"
              >
                Đóng
              </button>
            </div>
          </div>
        )}

        {paymentOption === 'CASH' && selectedPrice && (
          <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-white rounded-lg p-8 w-96 text-center shadow-lg">
              <p className="text-lg font-semibold mb-6">
                Bấm &quot;Xác nhận&quot; thanh toán gói {selectedPrice.name}.
              </p>
              <p className="text-gray-500 mt-4">
                Nhân viên quản lý sẽ kiểm tra và xác nhận yêu cầu mua giấy.
              </p>
              <Form method="post" action="/price">
                <input type="hidden" name="quantity-bought" value={selectedPrice?.pages} />
                <input type="hidden" name="payment-method" value={paymentOption} />

                <div className="flex flex-col gap-5 mt-6">
                  <button
                    type="submit"
                    disabled={isSubmitting}
                    className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600 transition"
                  >
                    {isSubmitting ? 'Đang xử lý...' : 'Xác nhận'}
                  </button>

                  <button
                    onClick={handleCashPayment}
                    disabled={isSubmitting}
                    className="px-4 py-2 bg-red-500 text-white rounded-md hover:bg-red-600 transition"
                  >
                    Đóng
                  </button>
                </div>
              </Form>
            </div>
          </div>
        )}
      </Layout>
    </div>
  );
}

export default Price;
