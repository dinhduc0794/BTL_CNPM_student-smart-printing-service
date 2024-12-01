import React from 'react';
import hcmut from '~/assets/hcmut.png';
import Layout from '~/pages/Layout';

function Guide() {
  return (
    <div>
      <Layout
        title="Hướng dẫn sử dụng"
        leftLabel="Trang chủ"
        leftLink="/home"
        rightLabel="Bắt đầu in"
        rightLink="/schedule"
      >
        <div className="px-96 text-gray-800">
          <div className="flex items-center space-x-4">
            <div
              className="relative w-24 h-24 border-8 border-primary-300 rounded-full overflow-hidden flex items-center justify-center shadow-md"
            >
              <img src={hcmut} alt="HCMUT Logo" className="object-cover w-24 h-24 absolute right-2" />
            </div>

            <div className="text-sky-900 font-semibold text-md">
              <p className="font-bold text-lg">HCMUT SPSS</p>
              <p>Hệ thống in ấn tài liệu trực tuyến</p>
            </div>
          </div>

          {/* Thêm phần hướng dẫn sử dụng hệ thống in */}
          <div className="pt-6">
            <p className="font-bold text-xl pb-4">Các bước sử dụng hệ thống in:</p>
            <ul className="list-disc pl-6 space-y-2">
              <li className="text-gray-800">
                <strong>Bước 1:</strong> Đăng nhập vào hệ thống bằng tài khoản của bạn.
              </li>
              <li className="text-gray-800">
                <strong>Bước 2:</strong> Đặt lịch in và chọn máy muốn in.
              </li>
              <li className="text-gray-800">
                <strong>Bước 3:</strong> Chọn và tải lên các tài liệu cần in.
              </li>
              <li className="text-gray-800">
                <strong>Bước 4:</strong> Điều chỉnh các cài đặt in, bao gồm số trang, số lượng bản in, và các tùy chọn khác.
              </li>
              <li className="text-gray-800">
                <strong>Bước 5:</strong> Nhấn nút "Bắt đầu in" để gửi yêu cầu in.
              </li>
              <li className="text-gray-800">
                <strong>Bước 6:</strong> Kiểm tra trạng thái in trong mục "Lịch sử đơn hàng" để theo dõi tiến trình.
              </li>
            </ul>
          </div>
          
        </div>
      </Layout>
    </div>
  );
}

export default Guide;
