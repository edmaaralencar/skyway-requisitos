import { getFlights } from '@/api/get-flights'
import { Button } from '@/components/ui/button'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { useQuery } from '@tanstack/react-query'
import { Link } from 'react-router-dom'

export function Home() {
  const { data: flights } = useQuery({
    queryFn: getFlights,
    queryKey: ['flights'],
  })

  return (
    <div className="flex flex-col gap-6">
      <h1 className="text-xl font-bold">Voos disponíveis</h1>

      <div className="grid grid-cols-3 gap-6">
        {flights?.map((flight) => (
          <Card className="flex flex-col" key={flight.id}>
            <CardHeader>
              <CardTitle>
                {flight.origem} para {flight.destino}
              </CardTitle>
              <CardDescription>Voo {flight.numero}</CardDescription>
            </CardHeader>
            <CardContent className="flex-1">
              <div className="flex flex-1 flex-col gap-1 text-sm">
                <span>
                  Data:{' '}
                  {new Intl.DateTimeFormat('pt-BR').format(
                    new Date(flight.horarioPartida),
                  )}
                </span>
                <span>
                  Horário de partida:{' '}
                  {new Intl.DateTimeFormat('pt-BR', {
                    timeStyle: 'short',
                  }).format(new Date(flight.horarioPartida))}
                </span>
                <span>
                  Horário de Chegada:{' '}
                  {new Intl.DateTimeFormat('pt-BR', {
                    timeStyle: 'short',
                  }).format(new Date(flight.horarioChegada))}
                </span>

                {flight.escalas.length > 0 && (
                  <div className="flex flex-col gap-1">
                    <span>Escalas</span>
                    <ul className="list-disc">
                      {flight.escalas.map((item) => (
                        <li className="ml-5" key={item.id + 'escala'}>
                          {item.aeroporto}
                        </li>
                      ))}
                    </ul>
                  </div>
                )}
              </div>
            </CardContent>

            <CardFooter className="justify-end">
              <Button asChild>
                <Link to={`/reservar/${flight.id}`}>Reservar</Link>
              </Button>
            </CardFooter>
          </Card>
        ))}
      </div>
    </div>
  )
}
