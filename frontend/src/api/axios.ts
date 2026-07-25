import axios from 'axios'

import alertService from '@/services/alert.service'



const api = axios.create({

    baseURL: import.meta.env.VITE_API_URL,

    headers: {

        'Content-Type': 'application/json'

    }

})







// Interceptor antes de enviar requisições
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









// Interceptor para tratar respostas da API
api.interceptors.response.use(



    response => {


        return response


    },




    async error => {



        const status = error.response?.status

        const url = error.config?.url





        /**
         * Login e registro possuem tratamento próprio.
         * 401 nestas rotas significa credenciais inválidas,
         * não sessão expirada.
         */
        const isAuthRequest =

            url?.includes('/auth/login') ||

            url?.includes('/auth/register')








        /**
         * Sessão expirada ou token inválido
         */
        if(status === 401 && !isAuthRequest){



            localStorage.removeItem('token')



            await alertService.warning(

                'Sua sessão expirou. Faça login novamente.'

            )



            if(window.location.pathname !== '/login'){


                window.location.replace('/login')


            }


        }








        /**
         * Usuário autenticado porém sem permissão
         */
        else if(status === 403){



            await alertService.error(

                'Você não possui permissão para realizar esta ação.'

            )


        }







        return Promise.reject(error)


    }


)








export default api