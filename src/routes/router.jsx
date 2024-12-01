import ErrorPage from '~/pages/Error';
import History from '~/pages/History';
import Upload from '~/pages/Upload';
import Schedule from '~/pages/Schedule/Schedule';
import Price from '~/pages/Price';
import Landing from '~/pages/Landing';
import { historyLoader, printerLoader } from '~/apis/getAPIs';
import { createBrowserRouter } from 'react-router-dom';
import { priceAction, loginAction, scheduleAction, setOptionAction, registerAction } from '~/apis/postAPIs';
import Contact from '~/pages/Contact';
import Printer from '~/pages/Printer';
import Login from '~/pages/Login';
import Register from '~/pages/Register';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Landing />,
    errorElement: <ErrorPage />
  },
  {
    path: '/login',
    element: <Login />,
    errorElement: <ErrorPage />,
    action: loginAction
  },
  {
    path: '/register',
    element: <Register />,
    errorElement: <ErrorPage />,
    action: registerAction
  },
  {
    path: '/printer',
    element: <Printer />,
    errorElement: <ErrorPage />,
    loader: printerLoader
  },
  {
    path: '/history',
    element: <History />,
    errorElement: <ErrorPage />,
    loader: historyLoader
  },
  {
    path: '/schedule',
    element: <Schedule />,
    errorElement: <ErrorPage />,
    action: scheduleAction
  },
  {
    path: '/:printerID/upload',
    element: <Upload />,
    errorElement: <ErrorPage />,
    action: setOptionAction
  },
  {
    path: '/price',
    element: <Price />,
    errorElement: <ErrorPage />,
    loader: historyLoader,
    action: priceAction
  },
  {
    path: '/contact',
    element: <Contact />,
    errorElement: <ErrorPage />
  },
]);
