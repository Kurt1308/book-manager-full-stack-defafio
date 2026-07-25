import { defineStore } from 'pinia'


export const useAuthStore = defineStore(
    'auth',
    {


        state: () => ({


            token: localStorage.getItem('token') as string | null


        }),





        getters: {


            isAuthenticated(state) {


                return state.token !== null


            },




            getToken(state) {


                return state.token


            }


        },







        actions: {



            setToken(token:string) {



                this.token = token



                localStorage.setItem(

                    'token',

                    token

                )


            },







            logout() {



                this.token = null



                localStorage.removeItem(

                    'token'

                )


            }


        }



    }

)