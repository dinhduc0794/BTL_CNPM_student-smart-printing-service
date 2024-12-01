import { redirect } from 'react-router-dom';
import globalDataStore from '~/data/global';
import axiosInstance from './axiosInstance';

export async function loginAction({ request }) {
  const formData = await request.formData();

  const data = {
    username: formData.get('username'),
    password: formData.get('password')
  }
  
  const res = await axiosInstance.post('/auth/login', data);
  console.log(res);
  
  
  if (res.status === 200) {
    sessionStorage.setItem('token', res.data.data);
    sessionStorage.setItem('user_id', res.data.entityId);

    return redirect("/");
  }
  
  return 0;
}

export async function registerAction({ request }) {
  const formData = await request.formData();

  const data = {
    username: formData.get('username'),
    password: formData.get('password'),
    retypePassword: formData.get('confirm-password'),
    name: formData.get('name'),
    email: formData.get('email'),
    faculty: formData.get('faculty'),
    phone: formData.get('phone')
  }
  console.log(data);
  
  const res = await axiosInstance.post('/auth/register', data);
  console.log(res);
  
  
  if (res.status === 200) {
    sessionStorage.setItem('token', res.data.data);
    sessionStorage.setItem('user_id', res.data.entityId);

    return redirect("/");
  }
  
  return 0;
}

export async function priceAction({ request }) {
  const formData = await request.formData();
  const userId = sessionStorage.getItem('user_id');
  const data = {
    studentId: userId,
    paymentMethod: formData.get('payment-method'),
    quantity: parseInt(formData.get('quantity-bought'))
  };

  console.log(data);
  
  const res = await axiosInstance.post(`students/paperlogs/buying-info`, data);
  console.log(res);
  
  return 0;
}

export async function scheduleAction({ request }) {
  const formData = await request.formData();
  const data = {
    scheduleTime: formData.get('date') + 'T' + formData.get('time') + ':00',
    note: formData.get('note'),
    isScheduled: true
  };

  globalDataStore.addActionData('schedule', data);

  return redirect("/printer?isSchedule=true");
}

export async function setOptionAction({ request }) {
  const formData = await request.formData();
  const userId = sessionStorage.getItem('user_id');

  const data = {
    pageSize: formData.get('pageSize'),
    numberOfCopies: parseInt(formData.get('numberOfCopies')),
    is2Side: formData.get('is2Side') === true,
    isColor: formData.get('isColor') === true
  };

  globalDataStore.addActionData('options', data);

  const options = globalDataStore.getActionData('options')[0];
  const schedule = globalDataStore.getActionData('schedule')[0];
  const files = globalDataStore.getActionData('files');

  const dataSend = files.flat().map(file => ({
    ...file,
    ...options,
    ...schedule,
    studentId: userId,
  }));

  const res = await axiosInstance.post(`/printinglogs/printing-info`, dataSend);

  console.log(res);
  
  
  if (res.data.message === 'Insufficient paper quantity for the printer.') {
    sessionStorage.setItem('failedMessage', 'Máy in tạm thời hết giấy, vui lòng chọn máy in khác.');

    if (dataSend.isScheduled) {
      return redirect("/printer?isSchedule=true");
    } else {
      return redirect("/printer");
    }

  } else if (res.data.message === 'Insufficient paper quantity for the student.') {
    
    sessionStorage.setItem('failedMessage', 'Số giấy của bạn không đủ, vui lòng mua thêm để thực hiện thao tác.');

    return redirect("/price");

  }

  sessionStorage.setItem('successMessage', 'Tài liệu của bạn được gửi in thành công!');

  return redirect("/");
}
