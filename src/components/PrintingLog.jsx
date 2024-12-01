/* eslint-disable react/prop-types */
import { useMemo, useState } from 'react';
import Pagination from './Pagination/Pagination';

function PrintingLog({ histories }) {  
  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 5;

  const currentTableData = useMemo(() => {
    const firstPageIndex = (currentPage - 1) * pageSize;
    const lastPageIndex = firstPageIndex + pageSize;
    return histories.slice(firstPageIndex, lastPageIndex);
  }, [currentPage, histories]);

  return (
    <div>
      <div className="pt-4">
        <div className="overflow-hidden rounded-lg border border-gray-200">
          <table className="min-w-full table-auto">
            <thead>
              <tr className="text-gray-500">
                <th className="px-4 py-2 border-b font-normal">Mã đơn</th>
                <th className="px-4 py-2 border-b font-normal">Ngày</th>
                <th className="px-4 py-2 border-b font-normal">Số lượng trang in</th>
                <th className="px-4 py-2 border-b font-normal">Tên file in</th>
                <th className="px-4 py-2 border-b font-normal">Trạng thái</th>
              </tr>
            </thead>
            <tbody>
              {currentTableData.map((history, index) => (
                <tr key={index} className="hover:bg-gray-50 font-semibold text-gray-800 text-center">
                  <td className="px-6 py-2 text-sm">{history.id}</td>
                  <td className="px-6 py-2 text-sm">{new Date(history.createdAt).toLocaleDateString('en-GB')}</td>
                  <td className="px-6 py-2 text-sm">{history.documentPages}</td>
                  <td className="px-6 py-2 text-sm">{history.documentName}</td>
                  <td
                    className={`px-6 py-2 text-sm ${history.status === "SUCCESS"
                      ? "text-green-600"
                      : history.status === "PENDING"
                        ? "text-blue-600"
                        : history.status === "FAILED"
                          ? "text-red-600"
                          : "text-gray-600"
                      }`}
                  >
                    {history.status}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          <div className="py-2 flex justify-end">
            <Pagination
              className="pagination-bar"
              currentPage={currentPage}
              totalCount={histories.length}
              pageSize={pageSize}
              onPageChange={page => setCurrentPage(page)}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default PrintingLog;