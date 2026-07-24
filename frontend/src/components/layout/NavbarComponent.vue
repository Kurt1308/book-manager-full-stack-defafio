<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
    <div class="container">
      <RouterLink class="navbar-brand fw-bold" to="/">
        📚 BookManager
      </RouterLink>

      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbar"
        aria-controls="navbar"
        aria-expanded="false"
        aria-label="Alternar navegação"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div
        id="navbar"
        class="collapse navbar-collapse"
      >
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <RouterLink class="nav-link" to="/">
              Home
            </RouterLink>
          </li>

          <li class="nav-item">
            <RouterLink class="nav-link" to="/login">
              Login
            </RouterLink>
          </li>

          <li class="nav-item">
            <RouterLink class="nav-link" to="/register">
              Registrar
            </RouterLink>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>

<style scoped>
</style>