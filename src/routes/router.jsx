import ErrorPage from '~/pages/Error';
import History from '~/pages/History';
import Upload from '~/pages/Upload';
import Schedule from '~/pages/Schedule/Schedule';
import Price from '~/pages/Price';
import Landing from '~/pages/Landing';
import Guide from '~/pages/Guide'
import Contact from '~/pages/Contact';
import { historyLoader } from '~/apis/getAPIs';
import { createBrowserRouter } from 'react-router-dom';
import { scheduleAction, setOptionAction } from '~/apis/postAPIs';

export const router = createBrowserRouter([
  {
    path: '/history',
    element: <History />,
    errorElement: <ErrorPage />,
    loader: historyLoader,
  },
  {
    path: '/schedule',
    element: <Schedule />,
    errorElement: <ErrorPage />,
    action: scheduleAction,
  },
  {
    path: '/:printerID/upload',
    element: <Upload />,
    errorElement: <ErrorPage />,
    action: setOptionAction,
  },
  {
    path: '/price',
    element: <Price />,
    errorElement: <ErrorPage />,
    loader: historyLoader,
  },
  {
    path: '/home',
    element: <Landing />,
    errorElement: <ErrorPage />
  },
  {
    path: '/guide',
    element: <Guide />,
    errorElement: <ErrorPage />
  },
  {
    path: '/contact',
    element: <Contact />,
    errorElement: <ErrorPage/>
  }
]);
