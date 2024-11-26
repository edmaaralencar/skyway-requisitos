import { api } from '@/lib/axios'

type Response = {
  id: number
  numero: string
  estaDisponivel: number
}[]

export async function getSeats(id: string) {
  const response = await api.get<Response>(`/assentos/${id}`)

  return response.data
}
