import { IClient } from '@/entities/client'
import { IFlight, ISeat } from '@/entities/flight'
import { api } from '@/lib/axios'

type Response = {
  id: number
  dataCompra: string
  preco: number
  classe: string
  status: string
  voo: IFlight
  cliente: IClient
  assento: ISeat
}[]

export async function getTickets(id: string) {
  const response = await api.get<Response>(`/passagens/${id}`)

  return response.data
}
