import { useAuth } from '@/api/hooks/use-auth'
import { Link, Outlet } from 'react-router-dom'
import { Button } from './ui/button'

export function Wrapper() {
  const { client, signOut } = useAuth()

  return (
    <div className="flex flex-col">
      <header className="flex h-16 items-center justify-between px-6">
        <Link to="/">
          <h1 className="text-3xl font-bold">SkyWay</h1>
        </Link>

        <div className="flex items-center gap-4">
          {client ? (
            <>
              <Link to="/passagens">Passagens</Link>
              <div className="flex flex-col gap-1">
                <span>
                  Saldo:{' '}
                  {new Intl.NumberFormat('pt-br', {
                    style: 'currency',
                    currency: 'brl',
                  }).format(client.saldo)}
                </span>
              </div>
              <Button variant="ghost" onClick={signOut}>
                Sair
              </Button>
            </>
          ) : (
            <Button asChild>
              <Link to="/autenticacao">Entrar</Link>
            </Button>
          )}
        </div>
      </header>
      <div className="p-6">
        <Outlet />
      </div>
    </div>
  )
}
