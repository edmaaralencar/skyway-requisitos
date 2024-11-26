import { createBrowserRouter } from 'react-router-dom'
import { Wrapper } from './components/wrapper'
import { Home } from './pages/home'
import { BookFlight } from './pages/book-flight'
import { Success } from './pages/sucess'
import { Tickets } from './pages/tickets'

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Wrapper />,
    children: [
      {
        path: '',
        element: <Home />,
      },
      {
        path: 'reservar/:id',
        element: <BookFlight />,
      },
      {
        path: 'sucesso',
        element: <Success />,
      },
      {
        path: 'passagens',
        element: <Tickets />,
      },
    ],
  },
])
