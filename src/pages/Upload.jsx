import cloudIcon from '~/assets/cloud.png';
import uploadIcon from '~/assets/up.png';
import trashIcon from '~/assets/trash.png';
import { useMemo, useState } from 'react';
import Navbar from '~/components/Navbar';
import Button from '~/components/Button';
import Option from '~/pages//Option';
import Pagination from '~/components/Pagination/Pagination';
import globalDataStore from '~/data/global';
import { useParams } from 'react-router-dom';

function Upload() {
  const { printerID } = useParams();
  const [files, setFiles] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [error, setError] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 6;

  const handleFileChange = async (e) => {
    const fileList = e.target.files;

    const fileArray = await Promise.all(
      Array.from(fileList).map((file, index) => {
        return new Promise((resolve, reject) => {
          const extension = file.name.split('.').pop();
          const reader = new FileReader();

          reader.onloadend = () => {
            const content = reader.result;
            const match = content.match(/\/Type[\s]*\/Page[^s]/g);
            const count = match ? match.length : 1;
            console.log(count);
            
            resolve({
              fileId: index + 1,
              documentName: file.name,
              documentType: extension,
              documentSize: (file.size / (1024 * 1024)).toFixed(2),
              documentPages: count,
              printerId: parseInt(printerID)
            });
          };

          reader.onerror = () => reject(new Error("File reading failed"));
          reader.readAsBinaryString(file);
        });
      })
    );

    setFiles(fileArray);
    setError("");
  };

  const handleDelete = (id) => {
    setFiles(files.filter(file => file.fileID !== id));
  };

  const currentTableData = useMemo(() => {
    const firstPageIndex = (currentPage - 1) * pageSize;
    const lastPageIndex = firstPageIndex + pageSize;
    return files.slice(firstPageIndex, lastPageIndex);
  }, [currentPage, files]);


  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const handleNextClick = async () => {
    if (files.length === 0) {
      setError("Vui lòng chọn ít nhất một tệp!");
      return;
    }

    setError("");

    globalDataStore.addActionData('files', files);

    openModal();
  };

  return (
    <div>
      <Navbar />
      <p className="text-center text-3xl font-bold py-6">Upload tài liệu</p>

      {files.length == 0 && (
        <div className="mx-96 max-h-[360px] min-h-[360px] flex flex-col items-center justify-center mt-12 border-4 border-dashed border-gray-600 p-6 rounded-2xl">
          <div className="relative">
            <div className="relative">
              <img src={cloudIcon} alt="Cloud" className="w-auto h-32" />
              <img src={uploadIcon} alt="Upload" className="absolute -bottom-12 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-auto h-16" />
            </div>

          </div>
          <p className="text-center my-8 text-xl">Kéo và thả vào đây</p>

          <label
            htmlFor="fileInput"
            className="cursor-pointer inline-block px-6 py-2 bg-primary-500 text-white font-semibold rounded-md hover:bg-primary-600 transition">
            Tải lên
          </label>
          <input
            type="file" id="fileInput"
            className="hidden"
            multiple
            accept=".pdf, .doc, .docx, .xlsx"
            onChange={handleFileChange}
          />
          <p className="text-center text-gray-600 mt-4 text-lg">Chỉ chấp nhận định dạng file: pdf, doc, docx, xlsx,...</p>
        </div>
      )}

      {files.length > 0 && (
        <div className="mx-96 max-h-[360px] min-h-[360px] mt-12 border-4 border-dashed border-gray-600 p-6 rounded-2xl relative">
          <table className="w-full table-auto">
            <thead>
              <tr className="text-gray-800">
                <th className="px-4 py-2 border-b-2 border-gray-800 font-semibold">STT</th>
                <th className="px-4 py-2 border-b-2 border-gray-800 font-semibold">Tên</th>
                <th className="px-4 py-2 border-b-2 border-gray-800 font-semibold">Định dạng</th>
                <th className="px-4 py-2 border-b-2 border-gray-800 font-semibold">Dung lượng</th>
                <th className="px-4 py-2 border-b-2 border-gray-800 font-semibold"></th>
              </tr>
            </thead>
            <tbody>
              {currentTableData.map((file) => (
                <tr key={file.fileId} className="hover:bg-gray-50 font-semibold text-gray-800 text-center">
                  <td className="px-6 py-2 text-sm">{file.fileId}</td>
                  <td className="px-6 py-2 text-sm">{file.documentName}</td>
                  <td className="px-6 py-2 text-sm">{file.documentType}</td>
                  <td className="px-6 py-2 text-sm">{file.documentSize} MB</td>
                  <td className="px-6 py-2 text-sm">
                    <img
                      src={trashIcon}
                      className="w-auto h-5 cursor-pointer"
                      onClick={() => handleDelete(file.fileID)}
                    />
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          <div className="absolute bottom-4 right-4">
            <Pagination
              className="pagination-bar"
              currentPage={currentPage}
              totalCount={files.length}
              pageSize={pageSize}
              onPageChange={page => setCurrentPage(page)}
            />
          </div>
        </div>
      )}

      {error && (
        <div className="mx-96 my-4 text-red-600 bg-red-100 border border-red-400 p-3 rounded">
          {error}
        </div>
      )}

      {isModalOpen && <Option onClose={closeModal} />}

      <div className="fixed bottom-0 left-0 right-0 mx-auto flex max-w-7xl justify-between px-8 pb-8">
        <Button color="red" label="Hủy bỏ" link="/printer" />
        <button
          type="submit"
          className={`text-white font-semibold py-2 px-4 rounded bg-primary-500 hover:bg-primary-600`}
          onClick={handleNextClick}
        >
          Tiếp theo
        </button>
      </div>
    </div>
  );
}

export default Upload;