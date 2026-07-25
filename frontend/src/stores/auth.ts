import { defineStore } from 'pinia'


interface AuthState {

    token: string | null

    name: string | null

}



export const useAuthStore = defineStore(

    'auth',

    {


        state: (): AuthState => ({


            token: localStorage.getItem('token'),


            name: localStorage.getItem('name')


        }),





        getters: {



            isAuthenticated(state) {


                return state.token !== null


            },





            getToken(state) {


                return state.token


            },





            getName(state) {


                return state.name


            }


        },







        actions: {



            setToken(

                token: string

            ) {



                this.token = token



                localStorage.setItem(

                    'token',

                    token

                )



                const payload = JSON.parse(

                    atob(

                        token.split('.')[1]

                    )

                )



                this.name = payload.name



                localStorage.setItem(

                    'name',

                    payload.name

                )


            },








            logout() {



                this.token = null


                this.name = null



                localStorage.removeItem(

                    'token'

                )



                localStorage.removeItem(

                    'name'

                )


            }


        }



    }

)