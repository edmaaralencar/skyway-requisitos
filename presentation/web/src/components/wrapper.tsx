import { Link, Outlet } from 'react-router-dom'

export function Wrapper() {
  return (
    <div className="flex flex-col">
      <header className="flex h-16 items-center justify-between px-6">
        <Link to="/">
          <h1 className="text-3xl font-bold">SkyWay</h1>
        </Link>
      </header>
      <div className="p-6">
        <Outlet />
      </div>
    </div>
  )
}
