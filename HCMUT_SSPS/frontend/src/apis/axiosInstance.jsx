import axios from 'axios';
import { API_ROOT } from '~/data/constant';

const axiosInstance = axios.create({
  baseURL: API_ROOT,
  withCredentials: true
});

axiosInstance.interceptors.request.use(
  (config) => {
    const token = sessionStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axiosInstance;
