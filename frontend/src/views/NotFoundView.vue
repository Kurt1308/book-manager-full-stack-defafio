<template>

  <div class="container mt-5 text-center">

    <h1 class="display-1">
      404
    </h1>

    <h2>
      Página não encontrada
    </h2>

    <p class="text-secondary">
      A página que você tentou acessar não existe.
    </p>


    <RouterLink
      to="/login"
      class="btn btn-primary"
    >
      Voltar para Login
    </RouterLink>


  </div>

</template>


<script setup lang="ts">

import { RouterLink } from 'vue-router'

</script>


<style scoped>

</style>