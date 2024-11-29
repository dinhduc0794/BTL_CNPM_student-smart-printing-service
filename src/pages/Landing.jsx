import { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate để điều hướng
import LayoutLanding from '~/pages/LayoutLanding';
import landingImage from '~/assets/landing.png';

function Landing() {
  const [isPopupVisible, setIsPopupVisible] = useState(false);
  const navigate = useNavigate(); // Khởi tạo hook navigate

  const handleStartClick = () => {
    setIsPopupVisible(true); // Hiển thị popup
  };

  const handlePopupClose = () => {
    setIsPopupVisible(false); // Đóng popup
  };

  const handleYesClick = () => {
    setIsPopupVisible(false); // Đóng popup
    navigate('/schedule'); // Chuyển hướng đến trang schedule khi chọn "CÓ"
  };

  return (
    <LayoutLanding
      title=""
      leftLabel=""
      leftLink=""
      rightLabel=""
      rightLink=""
    >
      <div className="flex flex-col md:flex-row items-center justify-between px-8 lg:px-32 pt-16 lg:pt-32">
        {/* Nội dung giới thiệu */}
        <div className="text-center md:text-left max-w-xl">
          <h1 className="text-4xl font-bold text-gray-800 mb-6">
            Dịch vụ in ấn thông minh
          </h1>
          <p className="text-gray-600 text-lg leading-relaxed mb-8">
            Hệ thống SSPS (Student Smart Printing Service) là giải pháp in ấn thông minh dành cho sinh viên Đại học Bách Khoa TP.HCM, giúp đáp ứng nhu cầu in tài liệu nhanh chóng, an toàn và thuận tiện ngay trong khuôn viên trường. SSPS cung cấp các tính năng như đăng nhập SSO, chọn máy in, tùy chỉnh thiết lập in (khổ giấy, in màu, số bản sao), và thanh toán qua BKPay. Hệ thống này không chỉ đảm bảo bảo mật tài liệu mà còn giúp tối ưu hóa tài nguyên in và chi phí vận hành cho nhà trường.
          </p>
          <button
            onClick={handleStartClick}
            className="px-6 py-3 bg-primary-500 text-white text-lg font-semibold rounded-md hover:bg-primary-600 transition duration-300"
          >
            Bắt đầu in
          </button>
        </div>

        {/* Hình ảnh minh họa */}
        <div className="mt-12 md:mt-0 md:ml-12 w-full md:w-1/2">
          <img
            src={landingImage}
            alt="In ấn thông minh"
            className="w-full h-auto object-cover"
          />
        </div>
      </div>

      {/* Popup */}
      {isPopupVisible && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white rounded-lg p-8 w-96 text-center shadow-lg">
            <p className="text-lg font-semibold mb-6">
              Bạn có muốn đặt lịch in không?
            </p>
            <div className="flex justify-between gap-2">
              <button
                onClick={handlePopupClose}
                className="flex-1 w-10 px-4 py-2 bg-red-500 text-white font-semibold rounded-md hover:bg-red-600 transition"
              >
                KHÔNG
              </button>
              <button
                onClick={handleYesClick} 
                className="flex-1 w-10 px-4 py-2 bg-primary-500 text-white font-semibold rounded-md hover:bg-primary-600 transition"
              >
                CÓ
              </button>
            </div>
          </div>
        </div>
      )}
    </LayoutLanding>
  );
}

export default Landing;
