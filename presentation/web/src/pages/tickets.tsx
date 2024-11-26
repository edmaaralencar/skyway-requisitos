import { getTickets } from '@/api/get-tickets'
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

export function Tickets() {
  const { data: tickets } = useQuery({
    queryKey: ['tickets'],
    queryFn: () => getTickets('1'),
  })

  console.log(tickets)

  return (
    <div className="flex flex-col gap-6">
      <h1 className="text-xl font-bold">Minhas Passagens</h1>

      <div className="grid grid-cols-3 gap-6">
        {tickets?.map((ticket) => (
          <Card key={ticket.id}>
            <CardHeader>
              <CardTitle>
                Voo {ticket.voo.origem} para {ticket.voo.destino}
              </CardTitle>
              <div>
                <CardDescription>Passagem: {ticket.id}</CardDescription>
                <CardDescription>Voo: {ticket.voo.numero}</CardDescription>
              </div>
            </CardHeader>
            <CardContent>
              <div className="flex flex-col gap-2 text-sm">
                Preço:{' '}
                {new Intl.NumberFormat('pt-BR', {
                  style: 'currency',
                  currency: 'brl',
                }).format(ticket.preco)}
              </div>
              <div className="flex flex-col gap-2 text-sm">
                Data de compra:{' '}
                {new Intl.DateTimeFormat('pt-BR').format(
                  new Date(ticket.dataCompra),
                )}
              </div>
              <div className="flex flex-col gap-2 text-sm">
                Classe: {ticket.classe}
              </div>
              <div className="flex flex-col gap-2 text-sm">
                Status: {ticket.status}
              </div>
            </CardContent>
            <CardFooter className="justify-between">
              <Button
                disabled={ticket.status !== 'ATIVA'}
                variant="destructive"
              >
                Reembolsar
              </Button>
              <Button>Trocar voo</Button>
            </CardFooter>
          </Card>
        ))}
      </div>
    </div>
  )
}
