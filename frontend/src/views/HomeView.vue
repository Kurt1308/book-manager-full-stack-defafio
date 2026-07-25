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









            <div class="home-actions">





                <RouterLink

                    to="/books"

                    class="btn btn-primary btn-large"

                >

                    Acessar livros

                </RouterLink>





            </div>







        </section>





    </main>


</template>









<script setup lang="ts">


import { RouterLink } from 'vue-router'





</script>









<style scoped>


.home-actions {

    margin-top: 30px;

}


</style>