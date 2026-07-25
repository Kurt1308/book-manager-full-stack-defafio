<template>


    <main class="container py-5">


        <div class="row justify-content-center">


            <div class="col-md-8">


                <div class="card shadow-sm">


                    <div class="card-body">


                        <h1 class="mb-4">

                            📚 Novo Livro

                        </h1>





                        <form @submit.prevent="saveBook">





                            <div class="mb-3">


                                <label class="form-label">

                                    Título

                                </label>


                                <input

                                    v-model="form.title"

                                    type="text"

                                    class="form-control"

                                    required

                                />


                            </div>







                            <div class="mb-3">


                                <label class="form-label">

                                    Autor

                                </label>


                                <input

                                    v-model="form.author"

                                    type="text"

                                    class="form-control"

                                    required

                                />


                            </div>







                            <div class="mb-3">


                                <label class="form-label">

                                    Ano

                                </label>


                                <input

                                    v-model.number="form.year"

                                    type="number"

                                    class="form-control"

                                />


                            </div>







                            <div class="mb-3">


                                <label class="form-label">

                                    Descrição

                                </label>


                                <textarea

                                    v-model="form.description"

                                    class="form-control"

                                    rows="4"

                                ></textarea>


                            </div>







                            <div class="d-flex gap-2">



                                <button

                                    type="submit"

                                    class="btn btn-primary"

                                >

                                    Salvar

                                </button>





                                <RouterLink

                                    to="/books"

                                    class="btn btn-secondary"

                                >

                                    Cancelar

                                </RouterLink>



                            </div>





                        </form>



                    </div>


                </div>


            </div>


        </div>



    </main>


</template>







<script setup lang="ts">


import { reactive } from 'vue'

import { useRouter } from 'vue-router'


import { createBook } from '@/services/book.service'

import type { BookRequest } from '@/types/book'





const router = useRouter()





const form = reactive<BookRequest>({

    title: '',

    author: '',

    year: undefined,

    description: ''

})







async function saveBook() {


    await createBook(form)


    router.push('/books')


}



</script>







<style scoped>


</style>