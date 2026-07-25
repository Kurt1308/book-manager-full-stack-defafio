<template>


    <main class="home-container">



        <section class="home-content">





            <h1 class="home-title">

                📚 BookManager

            </h1>






            <p class="home-description">

                Gerencie sua biblioteca de forma simples,
                rápida e segura.

            </p>








            <RouterLink

                to="/books"

                class="btn btn-primary btn-large"

            >

                Acessar livros

            </RouterLink>







        </section>





    </main>


</template>







<script setup lang="ts">


import { RouterLink } from 'vue-router'


</script>







<style scoped>


</style>