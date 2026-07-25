<template>

    <main class="auth-container">

        <section class="auth-card">


            <h1 class="auth-title">

                Criar Conta

            </h1>





            <form

                class="auth-form"

                @submit.prevent="handleRegister"

            >



                <div class="form-group">


                    <label class="form-label">

                        Nome

                    </label>



                    <input

                        class="form-control"

                        v-model="name"

                        placeholder="Digite seu nome"

                        autocomplete="name"

                        required

                    />


                </div>







                <div class="form-group">


                    <label class="form-label">

                        Email

                    </label>



                    <input

                        type="email"

                        class="form-control"

                        v-model="email"

                        placeholder="Digite seu email"

                        autocomplete="email"

                        required

                    />


                </div>







                <div class="form-group">


                    <label class="form-label">

                        Senha

                    </label>



                    <input

                        type="password"

                        class="form-control"

                        v-model="password"

                        placeholder="Mínimo 6 caracteres"

                        minlength="6"

                        autocomplete="new-password"

                        required

                    />


                </div>







                <button

                    class="btn btn-primary auth-button"

                    type="submit"

                    :disabled="loading"

                >


                    <span v-if="loading">

                        Registrando...

                    </span>


                    <span v-else>

                        Registrar

                    </span>


                </button>





            </form>







            <div class="auth-divider"></div>







            <RouterLink

                to="/login"

                class="btn btn-outline-secondary auth-button"

            >

                Voltar para login

            </RouterLink>








        </section>







    </main>


</template>









<script setup lang="ts">


import { ref } from 'vue'

import { useRouter, RouterLink } from 'vue-router'


import { register } from '@/services/auth.service'

import { useAuthStore } from '@/stores/auth'

import alertService from '@/services/alert.service'





const router = useRouter()


const authStore = useAuthStore()





const name = ref('')

const email = ref('')

const password = ref('')

const loading = ref(false)








async function handleRegister() {


    loading.value = true



    try {



        const response = await register({



            name: name.value,

            email: email.value,

            password: password.value



        })






        authStore.setToken(

            response.token

        )







        await alertService.success(

            'Usuário cadastrado com sucesso'

        )







        router.push('/home')







    } catch(error) {



        await alertService.apiError(error)






    } finally {



        loading.value = false



    }



}







</script>









<style scoped>

</style>