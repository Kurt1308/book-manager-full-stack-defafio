<template>
  <NavbarComponent />

  <main class="container py-5">

    <div class="text-center">

      <h1 class="display-4 fw-bold mb-3">
        BookManager
      </h1>

      <p class="lead text-secondary mb-4">
        Gerencie sua biblioteca de forma simples, rápida e segura.
      </p>

      <RouterLink
        to="/login"
        class="btn btn-primary btn-lg"
      >
        Começar
      </RouterLink>

    </div>

  </main>
</template>

<script setup lang="ts">
import { RouterLink } from 'vue-router'
import NavbarComponent from '@/components/layout/NavbarComponent.vue'
</script>

<style scoped>
</style>