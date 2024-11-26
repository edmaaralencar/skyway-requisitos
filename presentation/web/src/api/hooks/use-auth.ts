import { useQuery } from '@tanstack/react-query'
import useLocalStorage from 'use-local-storage'
import { getClient } from '../get-client'

export function useAuth() {
  const [clientId, setClientId] = useLocalStorage('clientId', '1')

  async function signIn(id: string) {
    setClientId(id)
  }

  async function signOut() {
    setClientId('')
  }

  const { data } = useQuery({
    queryKey: ['client', clientId],
    queryFn: () => getClient(clientId ?? ''),
    enabled: clientId.length > 0,
  })

  return {
    client: data ?? null,
    signOut,
    signIn,
  }
}
