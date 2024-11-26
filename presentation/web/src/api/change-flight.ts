import { api } from '@/lib/axios'

type Request = {
  passagemId: number
  clienteId: number
  vooId: number
  assentoId: number
  classe: string
}

export async function changeFlight(data: Request) {
  await api.post('/passagens/trocar', data)
}
