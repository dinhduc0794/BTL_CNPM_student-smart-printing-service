import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import landingImage from '~/assets/landing.png';
import Navbar from '~/components/Navbar';
import globalDataStore from '~/data/global';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function Landing() {
  const [isPopupVisible, setIsPopupVisible] = useState(false);
  const navigate = useNavigate();
  const [message, setMessage] = useState("");

  useEffect(() => {
    const storedMessage = sessionStorage.getItem('successMessage');
    if (storedMessage) {
      setMessage(storedMessage);
      toast.success(storedMessage);
      sessionStorage.removeItem('successMessage');
    }
  }, []);

  const handleStartClick = () => {
    setIsPopupVisible(true);
  };

  const handlePopupCloseWhenYes = () => {
    setIsPopupVisible(false);
    navigate("/schedule");
  };

  const handlePopupCloseWhenNo = () => {
    setIsPopupVisible(false);
    globalDataStore.addActionData('schedule', {
      scheduleTime: '',
      note: '',
      isScheduled: false
    });
    navigate("/printer?isSchedule=false");
  };

  return (
    <div>
      <Navbar />
      {message && (
        <ToastContainer />
      )}
      <div className="flex flex-col md:flex-row items-center justify-between px-8 lg:px-32 pt-16 lg:pt-16">
        {/* Nội dung giới thiệu */}
        <div className="text-center md:text-left max-w-xl">
          <h1 className="text-4xl font-bold text-gray-800 mb-6">
            Dịch vụ in ấn thông minh
          </h1>
          <p className="text-gray-600 text-lg leading-relaxed mb-8 text-justify">
            Hệ thống SSPS (Student Smart Printing Service) là giải pháp in ấn thông minh dành cho sinh viên Đại học Bách Khoa TP.HCM,
            giúp đáp ứng nhu cầu in tài liệu nhanh chóng, an toàn và thuận tiện ngay trong khuôn viên trường.
            SSPS cung cấp các tính năng như đăng nhập SSO, chọn máy in, tùy chỉnh thiết lập in (khổ giấy, in màu, số bản sao), và thanh toán qua BKPay.
            Hệ thống này không chỉ đảm bảo bảo mật tài liệu mà còn giúp tối ưu hóa tài nguyên in và chi phí vận hành cho nhà trường.
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
                onClick={handlePopupCloseWhenNo}
                className="flex-1 w-10 px-4 py-2 bg-red-500 text-white font-semibold rounded-md hover:bg-red-600 transition"
              >
                KHÔNG
              </button>
              <button
                onClick={handlePopupCloseWhenYes}
                className="flex-1 w-10 px-4 py-2 bg-primary-500 text-white font-semibold rounded-md hover:bg-primary-600 transition"
              >
                CÓ
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default Landing;
