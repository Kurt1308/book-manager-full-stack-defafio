<template>


    <main class="not-found-container">



        <section class="not-found-content">





            <h1 class="not-found-code">

                404

            </h1>







            <h2 class="not-found-title">

                Página não encontrada

            </h2>







            <p class="not-found-description">

                A página que você tentou acessar não existe
                ou foi removida.

            </p>







            <RouterLink

                :to="redirectPath"

                class="btn btn-primary btn-large"

            >

                Voltar

            </RouterLink>







        </section>





    </main>


</template>









<script setup lang="ts">


import { computed } from 'vue'

import { RouterLink } from 'vue-router'

import { useAuthStore } from '@/stores/auth'







const authStore = useAuthStore()








const redirectPath = computed(() => {


    return authStore.isAuthenticated

        ? '/home'

        : '/login'


})






</script>









<style scoped>


.not-found-container {

    min-height: calc(100vh - 70px);

    display: flex;

    justify-content: center;

    align-items: center;

}



.not-found-content {

    text-align: center;

}



.not-found-code {

    font-size: 120px;

    font-weight: bold;

    margin-bottom: 20px;

}



.not-found-title {

    margin-bottom: 15px;

}



.not-found-description {

    color: #6c757d;

    margin-bottom: 30px;

}



</style>