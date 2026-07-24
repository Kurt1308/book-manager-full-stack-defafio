<template>

  <div class="container mt-5">

    <div class="row justify-content-center">

      <div class="col-md-6">

        <div class="card shadow">

          <div class="card-body">

            <h2 class="text-center mb-4">
              Criar Conta
            </h2>


            <form>

              <div class="mb-3">

                <label class="form-label">
                  Nome
                </label>

                <input
                  type="text"
                  class="form-control"
                  placeholder="Digite seu nome"
                />

              </div>


              <div class="mb-3">

                <label class="form-label">
                  Email
                </label>

                <input
                  type="email"
                  class="form-control"
                  placeholder="Digite seu email"
                />

              </div>


              <div class="mb-3">

                <label class="form-label">
                  Senha
                </label>

                <input
                  type="password"
                  class="form-control"
                  placeholder="Digite sua senha"
                />

              </div>


              <button
                type="submit"
                class="btn btn-primary w-100"
              >
                Registrar
              </button>


            </form>


            <hr>


            <RouterLink
              to="/login"
              class="btn btn-outline-secondary w-100"
            >
              Já tenho uma conta
            </RouterLink>


          </div>

        </div>

      </div>

    </div>

  </div>

</template>


<script setup lang="ts">

import { RouterLink } from 'vue-router'

</script>


<style scoped>

</style>