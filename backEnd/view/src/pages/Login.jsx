import React, { useState } from 'react'
import api from '../utils/axiosConfig'
import { useNavigate, Link } from 'react-router-dom'

function Login() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const navigate = useNavigate()

  const handleLogin = async () => {
    try {
      const res = await api.post('/auth/login', {
        email,
        password,
      })
      // supondo que backend retorne { token: '...' }
      if (res.data?.token) {
        localStorage.setItem('token', res.data.token)
        navigate('/tasks')
      } else {
        alert('Login falhou: token não recebido')
      }
    } catch (err) {
      console.error(err)
      alert('Login falhou!')
    }
  }

  return (
    <div>
      <h2>Login</h2>
      <input placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
      <input type="password" placeholder="Senha" value={password} onChange={e => setPassword(e.target.value)} />
      <button onClick={handleLogin}>Entrar</button>
      <div>
        <p>Não tem conta? <Link to="/register">Registre-se</Link></p>
      </div>
    </div>
  )
}

export default Login