<template>

    <main class="auth-container">

        <section class="auth-card">

            <h1 class="auth-title">
                📚 BookManager
            </h1>


            <form
                class="auth-form"
                @submit.prevent="handleLogin"
            >

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
                        placeholder="Digite sua senha"
                        autocomplete="current-password"
                        required
                    />

                </div>


                <div
                    v-if="error"
                    class="error-message"
                >
                    {{ error }}
                </div>


                <button
                    type="submit"
                    class="btn btn-primary auth-button"
                    :disabled="loading"
                >

                    <span v-if="loading">
                        Entrando...
                    </span>

                    <span v-else>
                        Entrar
                    </span>

                </button>


            </form>


            <div class="auth-divider"></div>


            <RouterLink
                to="/register"
                class="btn btn-outline-secondary auth-button"
            >

                Criar uma conta

            </RouterLink>


        </section>

    </main>

</template>




<script setup lang="ts">

import { ref } from 'vue'
import { useRouter, RouterLink } from 'vue-router'

import { login } from '@/services/auth.service'
import { useAuthStore } from '@/stores/auth'

import alertService from '@/services/alert.service'



const router = useRouter()

const authStore = useAuthStore()



const email = ref('')

const password = ref('')

const error = ref('')

const loading = ref(false)




async function handleLogin() {


    error.value = ''

    loading.value = true


    try {


        const response = await login({

            email: email.value,

            password: password.value

        })



        authStore.setToken(

            response.token

        )



        await alertService.success(

            'Login realizado com sucesso'

        )



        router.push('/home')



    } catch (error) {


        await alertService.apiError(error)



    } finally {


        loading.value = false


    }


}



</script>




<style scoped>

</style>