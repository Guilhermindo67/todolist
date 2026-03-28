function Login(){

    function logar(){
        console.log("Logado")
    }

    return(
        <main>
            <div>

                <div className="w-110 h-18 bg-blue-900 m-auto mt-16 rounded-t-2xl text-center content-center shadow-2xl">
                    <h1 className="text-white text-4xl font-bold">LOGIN</h1>
                </div>

                <div className="pt-5 pl-4 w-110 h-112 bg-blue-500 m-auto rounded-b-2xl shadow-2xl">           
                    <h2 className="text-white text-2xl font-bold">Email</h2>
                    <input className="border-2 rounded-2xl" type="email" placeholder="Digite seu Email:"/>

                    <h2 className="text-white text-2xl font-bold">Password</h2>
                    <input className="border-2 rounded-2xl" type="email" placeholder="Digite sua senha:"/>

                    <br />
                    <button type="submit" onClick={logar}>ENTRAR</button>

                </div>
            </div>
        </main>
    )
}

export default Login