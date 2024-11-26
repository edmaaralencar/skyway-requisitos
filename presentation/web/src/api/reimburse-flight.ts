import { api } from '@/lib/axios'

type Request = {
  passagemId: number
  clienteId: number
}
export async function reimburseFlight(data: Request) {
  await api.post('/reembolsos', data)
}
