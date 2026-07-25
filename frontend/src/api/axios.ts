import axios from 'axios'


const api = axios.create({

    baseURL: import.meta.env.VITE_API_URL,

    headers: {

        'Content-Type': 'application/json'

    }

})




// Intercepta todas as requisições antes de enviar para API
api.interceptors.request.use(

    config => {


        const token = localStorage.getItem('token')


        if(token){


            config.headers.Authorization =
                `Bearer ${token}`


        }


        return config


    },


    error => {


        return Promise.reject(error)


    }

)






// Intercepta respostas da API
api.interceptors.response.use(


    response => {


        return response


    },



    error => {



        if(error.response){



            const status = error.response.status




            // Token inválido ou expirado
            if(status === 401){



                localStorage.removeItem('token')



                window.location.href = '/login'


            }


        }



        return Promise.reject(error)


    }


)





export default api