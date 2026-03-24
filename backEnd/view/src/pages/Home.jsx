import React from 'react'
import { Link } from 'react-router-dom'

function Home() {
  return (
    <div>
      <h1>Bem-vindo ao sistema</h1>
      <Link to="/tasks">Ver Minhas Tarefas</Link>
    </div>
  )
}
export default Home;