import { api } from '@/lib/axios'

type Request = {
  clienteId: number
  vooId: number
  assentoId: number
  preco: number
  classe: string
  desconto: null | number
}

export async function bookFlight(data: Request) {
  await api.post('/passagens/reservar', data)
}
