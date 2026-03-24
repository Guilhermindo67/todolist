import React, { useEffect, useState } from 'react'
import api from '../utils/axiosConfig'
import { useNavigate } from 'react-router-dom'

function Tasks() {
  const [tasks, setTasks] = useState([])
  const [newTask, setNewTask] = useState('')
  const [description, setDescription] = useState('')
  const navigate = useNavigate()

  const loadTasks = async () => {
    try {
      const res = await api.get('/task')
      setTasks(res.data)
    } catch (err) {
      if (err.response && err.response.status === 401) {
        // token inválido/expirado
        localStorage.removeItem('token')
        navigate('/')
      } else {
        console.error(err)
      }
    }
  }

  const createTask = async () => {
    try {
      await api.post('/task', { name: newTask, description })
      setNewTask('')
      setDescription('')
      loadTasks()
    } catch (err) {
      if (err.response && err.response.status === 401) {
        localStorage.removeItem('token')
        navigate('/')
      } else {
        console.error(err)
        alert('Erro ao criar tarefa')
      }
    }
  }

  useEffect(() => {
    const token = localStorage.getItem('token')
    if (!token) {
      navigate('/')
      return
    }
    loadTasks()
  }, [])

  const handleLogout = () => {
    localStorage.removeItem('token')
    navigate('/')
  }

  return (
    <div>
      <h2>Minhas Tarefas</h2>
      <button onClick={handleLogout}>Logout</button>
      <div>
        <input
          placeholder="Nova tarefa"
          value={newTask}
          onChange={e => setNewTask(e.target.value)}
        />
        <input
          placeholder="Descrição (opcional)"
          value={description}
          onChange={e => setDescription(e.target.value)}
        />
        <button onClick={createTask}>Criar</button>
      </div>

      <ul>
        {tasks.map(task => (
          <li key={task.id}>
            <strong>{task.name}</strong>
            {task.description && <div>{task.description}</div>}
          </li>
        ))}
      </ul>
    </div>
  )
}

export default Tasks