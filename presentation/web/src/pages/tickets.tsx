import { getFlights } from '@/api/get-flights'
import { getTickets } from '@/api/get-tickets'
import { useAuth } from '@/api/hooks/use-auth'
import { reimburseFlight } from '@/api/reimburse-flight'
import { Button } from '@/components/ui/button'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog'
import { Label } from '@/components/ui/label'
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
import { queryClient } from '@/lib/react-query'
import { useMutation, useQuery } from '@tanstack/react-query'
import { isAxiosError } from 'axios'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { toast } from 'sonner'

export function Tickets() {
  const { client } = useAuth()
  const [flightId, setFlightId] = useState('')
  const { data: tickets } = useQuery({
    queryKey: ['tickets', client?.id],
    queryFn: () => getTickets(client?.id.toString() ?? ''),
  })
  const navigate = useNavigate()
  const { data: flights } = useQuery({
    queryFn: getFlights,
    queryKey: ['flights'],
  })

  const reimburseFlightMutation = useMutation({
    mutationFn: reimburseFlight,
    onSuccess: async () => {
      await queryClient.invalidateQueries({
        queryKey: ['tickets', client?.id],
      })
      await queryClient.invalidateQueries({
        queryKey: ['flights'],
      })
      await queryClient.invalidateQueries({
        queryKey: ['client'],
      })

      toast.success('Voo reembolsado com sucesso.', {
        description: 'Olhe seu saldo.',
      })
    },
    onError: async (error) => {
      if (isAxiosError(error)) {
        if (
          error.response?.data?.message.includes(
            'Não há reembolso disponível para este cancelamento.',
          )
        ) {
          await queryClient.invalidateQueries({
            queryKey: ['tickets', client?.id],
          })
          await queryClient.invalidateQueries({
            queryKey: ['flights'],
          })
          await queryClient.invalidateQueries({
            queryKey: ['client'],
          })
          toast.success('Reembolso realizado com sucesso', {
            description: 'Esse reembolso não possui retorno de saldo.',
          })
        } else {
          toast.error('Reembolso indisponível.')
        }
      }
    },
  })

  function handleChangeFlight(passagemId: number) {
    if (!flightId) {
      return toast.error('Selecione um voo')
    }

    navigate(`/reservar/${flightId}?passagemId=${passagemId}`)
  }

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
                onClick={async () =>
                  await reimburseFlightMutation.mutateAsync({
                    passagemId: ticket.id,
                    clienteId: client?.id ?? 0,
                  })
                }
                disabled={ticket.status !== 'ATIVA'}
                variant="destructive"
              >
                Reembolsar
              </Button>
              <Dialog>
                <DialogTrigger asChild>
                  <Button disabled={ticket.status !== 'ATIVA'}>
                    Trocar voo
                  </Button>
                </DialogTrigger>
                <DialogContent>
                  <DialogHeader>
                    <DialogTitle>Trocar voo</DialogTitle>
                  </DialogHeader>
                  <div>
                    <div className="flex flex-col gap-2">
                      <Label>Voos</Label>
                      <div className="flex gap-2">
                        <Select value={flightId} onValueChange={setFlightId}>
                          <SelectTrigger className="h-10">
                            <SelectValue placeholder="Selecione um voo" />
                          </SelectTrigger>
                          <SelectContent>
                            <SelectGroup>
                              {flights?.map((flight) => (
                                <SelectItem
                                  key={'VOO' + flight.id}
                                  value={flight.id.toString()}
                                >
                                  {flight.origem} - {flight.destino}
                                </SelectItem>
                              ))}
                            </SelectGroup>
                          </SelectContent>
                        </Select>
                      </div>
                    </div>
                  </div>
                  <DialogFooter>
                    <Button onClick={() => handleChangeFlight(ticket.id)}>
                      Ver disponibilidade
                    </Button>
                  </DialogFooter>
                </DialogContent>
              </Dialog>
            </CardFooter>
          </Card>
        ))}
      </div>
    </div>
  )
}
