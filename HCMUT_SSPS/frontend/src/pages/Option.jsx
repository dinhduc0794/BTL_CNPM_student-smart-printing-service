/* eslint-disable react/prop-types */
import { useState } from 'react';
import { Form } from 'react-router-dom';

function Option({ onClose }) {
  const [paperSize, setPaperSize] = useState("A4");
  const [quantity, setQuantity] = useState(1);
  const [isDoubleSided, setIsDoubleSided] = useState(false);
  const [color, setColor] = useState(false);

  const handleSubmit = async () => {
    onClose();
  };

  return (
    <div>
      <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center z-50">
        <div className="bg-white px-12 py-6 rounded-2xl w-2/5">
          <h3 className="text-2xl font-semibold mb-4 text-center">CÀI ĐẶT IN</h3>
          <Form method="post" encType="multipart/form-data" onSubmit={handleSubmit}>
            <div className="mb-4 flex justify-between items-center">
              <label className="block text-sm font-medium">Khổ giấy</label>
              <select
                name="pageSize"
                value={paperSize}
                onChange={(e) => setPaperSize(e.target.value)}
                className="mt-1 block w-3/5 border-gray-300 rounded-2xl bg-gray-100 p-2"
              >
                <option value="A4">A4</option>
                <option value="A3">A3</option>
                <option value="A2">A2</option>
                <option value="A1">A1</option>
              </select>
            </div>

            <div className="mb-4 flex justify-between items-center">
              <label className="block text-sm font-medium">Số lượng bản in</label>
              <input
                name="numberOfCopies"
                type="number"
                value={quantity}
                onChange={(e) => setQuantity(e.target.value)}
                min={1}
                className="mt-1 block w-3/5 border-gray-300 rounded-2xl bg-gray-100 p-2"
              />
            </div>

            <div className="mb-4 flex justify-between items-center">
              <label className="block text-sm font-medium">Một mặt hay hai mặt</label>
              <select
                name="is2Side"
                value={isDoubleSided}
                onChange={(e) => setIsDoubleSided(e.target.value)}
                className="mt-1 block w-3/5 border-gray-300 rounded-2xl bg-gray-100 p-2"
              >
                <option value={false}>Một mặt</option>
                <option value={true}>Hai mặt</option>
              </select>
            </div>

            <div className="mb-4 flex justify-between items-center">
              <label className="block text-sm font-medium">In màu</label>
              <select
                name="isColor"
                value={color}
                onChange={(e) => setColor(e.target.value)}
                className="mt-1 block w-3/5 border-gray-300 rounded-2xl bg-gray-100 p-2"
              >
                <option value={false}>Đen trắng</option>
                <option value={true}>Màu</option>
              </select>
            </div>

            <div className="flex justify-between mt-4">
              <button
                type="button"
                onClick={onClose}
                className="px-4 py-2 bg-red-500 text-sm rounded-md text-white min-w-24"
              >
                Hủy bỏ
              </button>
              <button
                type="submit"
                className="px-4 py-2 bg-primary-500 text-white text-sm rounded-md min-w-24"
              >
                In
              </button>
            </div>
          </Form>
        </div>
      </div>
    </div>
  );
}

export default Option;
