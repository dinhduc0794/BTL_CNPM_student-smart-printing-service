import hcmut from '~/assets/hcmut.png'
import { useMemo, useState } from 'react';
import Pagination from '~/components/Pagination/Pagination';
import { useLoaderData } from 'react-router-dom';
import Layout from '~/pages/Layout';

function History() {
  const { user } = useLoaderData();
  const histories = user.historyData;
  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 5;

  const currentTableData = useMemo(() => {
    const firstPageIndex = (currentPage - 1) * pageSize;
    const lastPageIndex = firstPageIndex + pageSize;
    return histories.slice(firstPageIndex, lastPageIndex);
  }, [currentPage, histories]);

  return (
    <div>
      <Layout
        title="Thông tin tài khoản"
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
              <p className="font-bold text-lg">{user.name}</p>
              <p>{user.faculty}</p>
              <p>Lượng giấy còn lại: {user.remainingPages} trang</p>
            </div>
          </div>

          <div className="pt-4">
            <p className="font-bold text-xl pb-4">Lịch sử đơn hàng</p>
            <div className="overflow-hidden rounded-lg border border-gray-200">
              <table className="min-w-full table-auto">
                <thead>
                  <tr className="text-gray-500">
                    <th className="px-4 py-2 border-b font-normal">Mã đơn hàng</th>
                    <th className="px-4 py-2 border-b font-normal">Ngày</th>
                    <th className="px-4 py-2 border-b font-normal">Số lượng trang in</th>
                    <th className="px-4 py-2 border-b font-normal">Tên file in</th>
                    <th className="px-4 py-2 border-b font-normal">Trạng thái</th>
                  </tr>
                </thead>
                <tbody>
                  {currentTableData.map((history, index) => (
                    <tr key={index} className="hover:bg-gray-50 font-semibold text-gray-800 text-center">
                      <td className="px-6 py-2 text-sm">{history.orderID}</td>
                      <td className="px-6 py-2 text-sm">{history.date}</td>
                      <td className="px-6 py-2 text-sm">{history.pageCount}</td>
                      <td className="px-6 py-2 text-sm">{history.fileName}</td>
                      <td
                        className={`px-6 py-2 text-sm ${history.status === "Success"
                          ? "text-green-600"
                          : history.status === "Pending"
                            ? "text-blue-600"
                            : history.status === "Rejected"
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
      </Layout>

    </div>
  );
}

export default History;
