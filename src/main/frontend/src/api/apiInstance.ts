import axios, {type AxiosInstance, type InternalAxiosRequestConfig} from "axios";

const api: AxiosInstance = axios.create({
  baseURL: "/api",
  headers: {
    "Content-Type": "application/json",
  },
});

// TODO: will be used when auth comes
api.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  const token = localStorage.getItem("bearerToken");
  if (token && config.headers) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// TODO: will be used when auth comes
export const setupTokenExpirationInterceptor = (logoutUser: () => void): void => {
  api.interceptors.response.use(
    (response) => response,
    (error) => {
      if (error.response?.status === 401 || error.response?.status === 403) {
        logoutUser();
      }
      return Promise.reject(error);
    }
  );
};

export default api;
