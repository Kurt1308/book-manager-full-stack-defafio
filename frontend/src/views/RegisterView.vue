<template>


<div class="container mt-5">


    <div class="row justify-content-center">


        <div class="col-md-6">


            <div class="card shadow">


                <div class="card-body">


                    <h2 class="text-center mb-4">

                        Criar Conta

                    </h2>




                    <form @submit.prevent="handleRegister">





                        <div class="mb-3">


                            <label class="form-label">

                                Nome

                            </label>



                            <input

                                class="form-control"

                                v-model="name"

                                required

                            />


                        </div>





                        <div class="mb-3">


                            <label class="form-label">

                                Email

                            </label>



                            <input

                                type="email"

                                class="form-control"

                                v-model="email"

                                required

                            />


                        </div>





                        <div class="mb-3">


                            <label class="form-label">

                                Senha

                            </label>



                            <input

                                type="password"

                                class="form-control"

                                v-model="password"

                                minlength="6"

                                required

                            />


                        </div>





                        <button

                            class="btn btn-primary w-100"

                            type="submit"

                        >

                            Registrar

                        </button>




                    </form>




                    <hr>




                    <RouterLink

                        to="/login"

                        class="btn btn-outline-secondary w-100"

                    >

                        Voltar para login

                    </RouterLink>



                </div>


            </div>


        </div>


    </div>


</div>



</template>




<script setup lang="ts">


import { ref } from 'vue'

import { useRouter } from 'vue-router'

import { RouterLink } from 'vue-router'


import { register } from '@/services/auth.service'

import { useAuthStore } from '@/stores/auth'





const router = useRouter()


const authStore = useAuthStore()




const name = ref('')


const email = ref('')


const password = ref('')







async function handleRegister(){


    try {


        const response = await register({


            name: name.value,


            email: email.value,


            password: password.value


        })




        authStore.setToken(

            response.token

        )



        router.push('/home')



    }


    catch {


        alert(

            'Erro ao cadastrar usuário'

        )


    }


}



</script>