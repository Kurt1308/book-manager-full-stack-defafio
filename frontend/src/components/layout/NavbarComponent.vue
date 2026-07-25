<template>

  <nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">

    <div class="container">


      <RouterLink
        class="navbar-brand fw-bold"
        to="/home"
      >

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

            <RouterLink
              class="nav-link"
              to="/home"
            >

              Home

            </RouterLink>

          </li>





          <li class="nav-item">

            <RouterLink
              class="nav-link"
              to="/books"
            >

              Livros

            </RouterLink>

          </li>





          <li class="nav-item">


              <button

                class="nav-link btn btn-link px-0"

                @click="logout"

              >

                Sair

              </button>


          </li>



        </ul>


      </div>


    </div>


  </nav>


</template>





<script setup lang="ts">


import { RouterLink, useRouter } from 'vue-router'

import { useAuthStore } from '@/stores/auth'





const router = useRouter()


const authStore = useAuthStore()






function logout() {


  authStore.logout()


  router.push('/login')


}



</script>





<style scoped>


.btn-link {

  text-decoration: none;

}



</style>