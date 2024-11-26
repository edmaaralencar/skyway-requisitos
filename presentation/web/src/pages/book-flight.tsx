import { getFlight } from '@/api/get-flight'
import { getSeats } from '@/api/get-seats'
import { Button } from '@/components/ui/button'
import { Plus } from 'lucide-react'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { cn } from '@/lib/utils'
import { useMutation, useQuery } from '@tanstack/react-query'
import { useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { Label } from '@/components/ui/label'
import { toast } from 'sonner'
import { validateCode } from '@/api/validate-code'
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
import { bookFlight } from '@/api/book-flight'
import { queryClient } from '@/lib/react-query'

export function BookFlight() {
  const params = useParams<{ id: string }>()
  const [discount, setDiscount] = useState<number | null>(null)
  const [selectedSeatId, setSelectedSeatId] = useState<number | null>(null)
  const [code, setCode] = useState('')
  const [classType, setClassType] = useState('')

  const navigate = useNavigate()

  const { data: flight } = useQuery({
    queryKey: ['flights', params.id],
    queryFn: () => getFlight(params.id ?? ''),
  })
  const { data: seats } = useQuery({
    queryKey: ['seats', params.id],
    queryFn: () => getSeats(params.id ?? ''),
  })

  const bookFlightMutation = useMutation({
    mutationFn: bookFlight,
    onSuccess: async () => {
      await queryClient.invalidateQueries({
        queryKey: ['seats', params.id],
      })
      await queryClient.invalidateQueries({
        queryKey: ['tickets'],
      })

      navigate('/sucesso')
    },
  })

  const selectedSeat = seats?.find((item) => item.id === selectedSeatId) ?? null

  async function onValidateCode() {
    try {
      const coupon = await validateCode(code)

      setDiscount(coupon.valorDesconto)
      toast.success('Cupom aplicado com sucesso.')
    } catch (error) {
      toast.error('Código inválido ou expirado.')
    }
  }

  async function onSubmit() {
    if (!selectedSeatId) {
      return toast.error('Ocorreu um erro.')
    }

    try {
      await bookFlightMutation.mutateAsync({
        assentoId: Number(selectedSeatId),
        classe: classType,
        clienteId: 1,
        desconto: discount ?? null,
        preco: 1,
        vooId: Number(params.id),
      })
    } catch (error) {
      toast.error('Ocorreu um erro.')
    }
  }

  return (
    <div className="flex flex-col gap-6">
      <h1 className="text-xl font-bold">
        {flight?.origem} para {flight?.destino}
      </h1>

      <div className="grid grid-cols-2 items-start gap-8">
        <div className="flex gap-4">
          {seats?.map((seat) => (
            <button
              key={seat.id}
              disabled={!seat.estaDisponivel}
              className={cn(
                'rounded-md bg-border p-4 disabled:cursor-not-allowed disabled:text-white disabled:bg-red-600',
                seat.id === selectedSeatId && 'bg-blue-300',
              )}
              onClick={() => setSelectedSeatId(seat.id)}
            >
              {seat.numero}
            </button>
          ))}
        </div>

        <Card>
          <CardHeader>
            <CardTitle>
              {flight?.origem} para {flight?.destino}
            </CardTitle>
            <CardDescription>Voo {flight?.numero}</CardDescription>
          </CardHeader>

          <CardContent>
            <div className="flex flex-col gap-5">
              <div className="flex flex-col gap-2">
                <span className="text-sm font-semibold">
                  Seu saldo:{' '}
                  {new Intl.NumberFormat('pt-BR', {
                    style: 'currency',
                    currency: 'brl',
                  }).format(100)}
                </span>
                <span className="text-sm font-semibold">
                  Assento: {selectedSeat ? selectedSeat.numero : 'Nenhum'}
                </span>
              </div>

              <div className="flex flex-col gap-2">
                <Label>Cupom</Label>
                <div className="flex gap-2">
                  <Input
                    placeholder="Código..."
                    value={code}
                    onChange={(event) => setCode(event.target.value)}
                    className="h-10"
                  />
                  <Button
                    type="button"
                    onClick={onValidateCode}
                    variant="outline"
                  >
                    <Plus />
                  </Button>
                </div>
              </div>
              <div className="flex flex-col gap-2">
                <Label>Classe</Label>
                <div className="flex gap-2">
                  <Select value={classType} onValueChange={setClassType}>
                    <SelectTrigger className="h-10">
                      <SelectValue placeholder="Selecione a classe" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectGroup>
                        <SelectItem value="ECONOMICA">Econômica</SelectItem>
                        <SelectItem value="EXECUTIVA">Executiva</SelectItem>
                      </SelectGroup>
                    </SelectContent>
                  </Select>
                </div>
              </div>
            </div>
          </CardContent>

          <CardFooter className="justify-between">
            {flight?.preco && (
              <strong className="text-2xl">
                {discount
                  ? new Intl.NumberFormat('pt-BR', {
                      style: 'currency',
                      currency: 'brl',
                    }).format(flight.preco - discount)
                  : new Intl.NumberFormat('pt-BR', {
                      style: 'currency',
                      currency: 'brl',
                    }).format(flight.preco)}
              </strong>
            )}

            <Button
              isLoading={bookFlightMutation.isPending}
              onClick={onSubmit}
              disabled={!selectedSeatId}
            >
              Comprar
            </Button>
          </CardFooter>
        </Card>
      </div>
    </div>
  )
}
