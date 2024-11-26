export interface IFlight {
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

export interface ISeat {
  id: number
  numero: string
  estaDisponivel: boolean
  voo: IFlight
}
