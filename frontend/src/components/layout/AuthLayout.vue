<template>

    <div class="auth-layout">


        <NavbarComponent />


        <main class="layout-content">

            <RouterView />

        </main>


    </div>


</template>



<script setup lang="ts">

import { RouterView } from 'vue-router'

import NavbarComponent from './NavbarComponent.vue'

</script>



<style scoped>

.auth-layout {

    min-height: 100vh;

}



.layout-content {

    padding: 20px;

}

</style>