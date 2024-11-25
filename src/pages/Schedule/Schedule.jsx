import Layout from '~/pages/Layout';
import { useState } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './Schedule.scss';
import { Form } from 'react-router-dom';

function Schedule() {
  const [value, setValue] = useState(new Date());

  function onChange(nextValue) {
    setValue(nextValue);
  };

  return (
    <div>
      <Layout
        title="Đặt lịch in"
        leftLabel="Hủy bỏ"
        leftLink="/home"
        rightLabel="Tiếp theo"
        rightLink="/printer"
        isSubmit={true}
        submitButtonId="submitSchedule"
      >
        <Form method="post" action="/schedule">
          <div className="flex gap-20 px-96 pt-16">
            <div>
              <div className="pb-4 font-medium">Chọn ngày nhận</div>
              <Calendar onChange={onChange} value={value} />
              <input
                type="hidden"
                name="date"
                value={value.toISOString().split('T')[0]}
              />
            </div>

            <div className="w-full flex flex-col justify-between">
              <div>
                <div className="pb-4 font-medium">Chọn giờ nhận</div>
                <div className="border rounded-md border-zinc-400 px-4">
                  <input
                    type="time"
                    name="time"
                    className="block w-full h-12 focus:outline-none"
                  />
                </div>
              </div>

              <div>
                <div className="pb-4 font-medium">Ghi chú</div>
                <div>
                  <textarea
                    name="note"
                    placeholder="Nhập ghi chú..."
                    className="w-full h-48 border rounded-md border-zinc-400 p-4"
                  ></textarea>
                </div>
              </div>
            </div>
          </div>

          <button type="submit" id="submitSchedule" style={{ display: 'none' }}>
            Submit
          </button>
        </Form>

      </Layout>
    </div>
  );
}

export default Schedule;