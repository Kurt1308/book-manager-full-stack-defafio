<template>

<div class="container mt-5">


    <div class="row justify-content-center">


        <div class="col-md-5">


            <div class="card shadow">


                <div class="card-body">


                    <h2 class="text-center mb-4">

                        📚 BookManager

                    </h2>



                    <form @submit.prevent="handleLogin">


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

                                required

                            />


                        </div>





                        <div
                            v-if="error"
                            class="alert alert-danger"
                        >

                            {{ error }}

                        </div>





                        <button

                            type="submit"

                            class="btn btn-primary w-100"

                        >

                            Entrar

                        </button>



                    </form>




                    <hr>




                    <RouterLink

                        to="/register"

                        class="btn btn-outline-secondary w-100"

                    >

                        Criar uma conta

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


import { login } from '@/services/auth.service'

import { useAuthStore } from '@/stores/auth'





const router = useRouter()


const authStore = useAuthStore()



const email = ref('')


const password = ref('')


const error = ref('')






async function handleLogin(){


    try {


        const response = await login({

            email: email.value,

            password: password.value

        })



        authStore.setToken(

            response.token

        )



        router.push('/home')



    }

    catch {


        error.value =

        'Email ou senha inválidos'


    }


}



</script>