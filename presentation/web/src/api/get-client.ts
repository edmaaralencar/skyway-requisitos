import { IClient } from '@/entities/client'
import { api } from '@/lib/axios'

type Response = IClient

export async function getClient(id: string) {
  const response = await api.get<Response>(`/clientes/${id}`)

  return response.data
}
