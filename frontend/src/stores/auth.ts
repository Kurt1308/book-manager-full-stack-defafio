import { defineStore } from 'pinia'



interface AuthState {

    token: string | null

    name: string | null

}






function decodeToken(token: string) {


    try {


        const payload = token.split('.')[1]


        if(!payload){

            return null

        }



        return JSON.parse(

            atob(payload)

        )


    } catch(error) {


        return null


    }

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






                const payload = decodeToken(token)






                if(payload?.name) {



                    this.name = payload.name



                    localStorage.setItem(

                        'name',

                        payload.name

                    )


                }

                else {


                    this.name = null


                    localStorage.removeItem(

                        'name'

                    )


                }


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