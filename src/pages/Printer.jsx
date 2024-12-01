import Navbar from '~/components/Navbar';
import printerLogo from '~/assets/printer.png';
import inforIcon from '~/assets/info.png';
import { Link, useLoaderData } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function Printer() {
  const [openPopups, setOpenPopups] = useState({});
  const { printers } = useLoaderData();
  const [message, setMessage] = useState("");

  const params = new URLSearchParams(location.search);
  const isSchedule = params.get('isSchedule') === 'true';

  useEffect(() => {
    const storedMessage = sessionStorage.getItem('failedMessage');
    if (storedMessage) {
      setMessage(storedMessage);
      toast.error(storedMessage);
      sessionStorage.removeItem('failedMessage');
    }
  }, []);

  const togglePopup = (id) => {
    setOpenPopups((prev) => ({
      ...prev,
      [id]: !prev[id],
    }));
  };

  return (
    <div>
      <Navbar />
      <p className="text-center text-3xl font-bold py-6">Chọn máy in</p>

      {message && (
        <ToastContainer />
      )}
      
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 p-4">
        {printers.filter((printer) => printer.isScheduled === isSchedule).map((printer) => (
          <div
            key={printer.id}
            className="p-2 flex flex-col items-center text-center"
          >
            <p className="font-semibold text-lg mb-2">
              {printer.room} - {printer.building}
            </p>
            <div className="relative">
              <img
                src={printerLogo}
                alt="Printer"
                className="w-28 h-28 object-contain mb-4"
              />

              <button
                onClick={() => togglePopup(printer.id)}
                className="absolute -top-4 right-0 w-8 h-8 flex items-center justify-center"
              >
                <img src={inforIcon} alt="Info" className="w-4 h-4" />
              </button>

              {openPopups[printer.id] && (
                <div className="absolute top-8 right-0 bg-white border border-gray-300 shadow-lg p-4 rounded-lg w-64 z-50">
                  <h3 className="text-lg font-bold mb-2">Thông tin máy in</h3>
                  <p>Phòng: {printer.room}</p>
                  <p>Tòa nhà: {printer.building}</p>
                  <p>Model: {printer.model}</p>
                  <p>Brand: {printer.brand}</p>
                  <button
                    onClick={() => togglePopup(printer.id)}
                    className="mt-2 px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
                  >
                    Đóng
                  </button>
                </div>
              )}
            </div>

            <Link
              to={`/${printer.id}/upload`}
              className="px-4 py-2 bg-primary-500 text-white rounded hover:bg-primary-600 transition"
            >
              Chọn
            </Link>
          </div>
        ))}
      </div>

      <Link
        to="/"
        className="fixed bottom-4 right-4 px-8 py-2 bg-red-500 text-white rounded hover:bg-red-600 transition shadow-lg mr-36"
      >
        Hủy bỏ
      </Link>

    </div>
  );
}

export default Printer;