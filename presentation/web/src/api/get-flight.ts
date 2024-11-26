import { api } from '@/lib/axios'

type Response = {
  id: number
  numero: string
  origem: string
  destino: string
  escalas: {
    id: number
    aeroporto: string
    horarioPartida: string
    horarioChegada: string
  }[]

  horarioPartida: string
  horarioChegada: string
  status: string
  preco: number
}

export async function getFlight(id: string) {
  const response = await api.get<Response>(`/voos/${id}`)

  return response.data
}
