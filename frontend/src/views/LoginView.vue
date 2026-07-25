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


                >

                    Entrar


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








<style scoped>


</style>