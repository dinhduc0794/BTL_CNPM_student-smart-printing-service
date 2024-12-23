import axiosInstance from './axiosInstance';

export async function historyLoader() {
  const userId = sessionStorage.getItem('user_id');
  const userInfoRes = await axiosInstance.get(`/students/${userId}`);

  const user = userInfoRes.data;
  
  return { user };
}

export async function printerLoader() {
  const res = await axiosInstance.get(`/printers`);
  
  const printers = res.data;  

  return { printers };
}
