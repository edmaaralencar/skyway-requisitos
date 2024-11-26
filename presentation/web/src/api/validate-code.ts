import { api } from '@/lib/axios'

type Response = {
  id: {
    id: number
  }
  codigo: string
  valorDesconto: number
  validade: string
  utilizado: boolean
}

export async function validateCode(code: string) {
  const response = await api.post<Response>('/cupons/validar', {
    codigo: code,
  })

  return response.data
}
