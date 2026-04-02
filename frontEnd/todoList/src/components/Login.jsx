function Login(){

    function logar(){
        console.log("Logado")
    }

    return(
        <main className="mb-26.5">
            <div>


                <div className="pt-6 w-105 h-130 bg-blue-950 m-auto rounded-2xl shadow-2xl text-center mt-30">           
                    <h1 className="text-white text-5xl font-bold ">LOGIN</h1>
                    <h2 className="text-white text-1xl font-bold mb-18">TODOLIST</h2>
                    
                    <h2 className="text-white text-2xl font-bold">Email</h2>
                    <input className="border-2 rounded-2xl bg-white hover:bg-white/80 w-70 h-8  mb-5" type="email"/>

                    <h2 className="text-white text-2xl font-bold">Password</h2>
                    <input className="border-2 rounded-2xl bg-white hover:bg-white/80 w-70 h-8 mb-28" type="email" />

                    <br />
                    <button className="font-semibold bg-green-300 hover:bg-green-400 rounded-2xl w-22 h-7.5" type="submit" onClick={logar}>ENTRAR</button>

                </div>
            </div>
        </main>
    )
}

export default Login