// view/src/pages/Register.jsx
import React, { useState } from 'react';
import api from '../utils/axiosConfig';
import { useNavigate } from 'react-router-dom';

function Register() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleRegister = async () => {
    try {
      const res = await api.post('/auth/register', { name, email, password });
      // se o backend retornar token em res.data.token, armazene
      if (res.data?.token) {
        localStorage.setItem('token', res.data.token);
        navigate('/tasks');
      } else {
        // se backend retornar objeto diferente, adapte conforme necessário
        alert('Registrado com sucesso. Faça login.');
        navigate('/');
      }
    } catch (err) {
      console.error(err);
      alert('Falha no registro');
    }
  };

  return (
    <div>
      <h2>Registrar</h2>
      <input placeholder="Nome" value={name} onChange={e => setName(e.target.value)} />
      <input placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
      <input type="password" placeholder="Senha" value={password} onChange={e => setPassword(e.target.value)} />
      <button onClick={handleRegister}>Registrar</button>
    </div>
  );
}

export default Register;