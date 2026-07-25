<template>

    <NavbarComponent />


    <main>

        <RouterView />

    </main>


</template>



<script setup lang="ts">

import { RouterView } from 'vue-router'

import NavbarComponent from './NavbarComponent.vue'

</script>



<style scoped>

</style>