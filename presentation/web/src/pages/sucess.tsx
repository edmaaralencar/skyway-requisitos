import { Button } from '@/components/ui/button'
import { Link } from 'react-router-dom'

export function Success() {
  return (
    <div className="grid h-screen w-full place-items-center">
      <div className="-mt-24 flex flex-col gap-8 text-center">
        <h1 className="text-3xl font-bold">Passagem comprada com sucesso!</h1>

        <Button asChild>
          <Link to="/passagens">Ver passagens</Link>
        </Button>
      </div>
    </div>
  )
}
