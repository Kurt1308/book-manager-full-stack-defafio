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


                        minlength="6"


                        required


                    />


                </div>









                <button


                    class="btn btn-primary auth-button"


                    type="submit"


                >

                    Registrar


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








<style scoped>


</style>