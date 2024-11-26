import { useAuth } from '@/api/hooks/use-auth'
import { Button } from '@/components/ui/button'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { api } from '@/lib/axios'
import { useState } from 'react'
import { Link, useNavigate, useSearchParams } from 'react-router-dom'

export function Auth() {
  const [searchParams] = useSearchParams()
  const [email, setEmail] = useState('')
  const [name, setName] = useState('')
  const [cpf, setCpf] = useState('')
  const [password, setPassword] = useState('')
  const { signIn } = useAuth()
  const navigate = useNavigate()

  const type = searchParams.get('type') ?? 'login'

  async function onSubmit() {
    if (type === 'login') {
      if (password === 'teste123') {
        signIn('1')
        navigate('/')
      }
    } else {
      try {
        const res = await api.post('/clientes', {
          nome: name,
          email,
          cpf,
        })

        signIn(res.data.id.id.toString())
        navigate('/')
      } catch (error) {}
    }
  }

  return (
    <Card className="mx-auto my-32 w-[350px]">
      <CardHeader>
        <CardTitle>
          {type === 'login' ? 'Entre agora' : 'Cadastre-se'}
        </CardTitle>
        <CardDescription>
          Preencha suas credenciais para aproveitar a plataforma.
        </CardDescription>
      </CardHeader>
      <CardContent>
        <form>
          <div className="grid w-full items-center gap-4">
            {type !== 'login' && (
              <>
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="name">Nome</Label>
                  <Input
                    value={name}
                    onChange={(event) => setName(event.target.value)}
                  />
                </div>
                <div className="flex flex-col space-y-1.5">
                  <Label>CPF</Label>
                  <Input
                    value={cpf}
                    onChange={(event) => setCpf(event.target.value)}
                  />
                </div>
              </>
            )}
            <div className="flex flex-col space-y-1.5">
              <Label htmlFor="name">E-mail</Label>
              <Input
                value={email}
                onChange={(event) => setEmail(event.target.value)}
              />
            </div>
            <div className="flex flex-col space-y-1.5">
              <Label>Senha</Label>
              <Input
                value={password}
                type="password"
                onChange={(event) => setPassword(event.target.value)}
              />
            </div>
          </div>
        </form>
      </CardContent>
      <CardFooter className="flex justify-end">
        <Button onClick={onSubmit}>Entrar</Button>
      </CardFooter>

      <div className="my-3">
        {type === 'cadastro' ? (
          <Link
            className="block text-center text-sm text-muted-foreground"
            to="/autenticacao"
          >
            Já possui uma conta? Entre agora
          </Link>
        ) : (
          <Link
            className="block text-center text-sm text-muted-foreground"
            to="/autenticacao?type=cadastro"
          >
            Não possui uma conta? Crie agora
          </Link>
        )}
      </div>
    </Card>
  )
}
