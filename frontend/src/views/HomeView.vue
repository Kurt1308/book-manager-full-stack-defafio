<template>


    <main class="container py-5">


        <div class="text-center">


            <h1 class="display-4 fw-bold mb-3">

                📚 BookManager

            </h1>



            <p class="lead text-secondary mb-4">

                Gerencie sua biblioteca de forma simples,
                rápida e segura.

            </p>



            <RouterLink

                to="/books"

                class="btn btn-primary btn-lg"

            >

                Acessar livros

            </RouterLink>



        </div>



    </main>



</template>





<script setup lang="ts">

import { RouterLink } from 'vue-router'

</script>





<style scoped>


</style>