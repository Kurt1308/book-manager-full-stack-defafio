<template>


    <main class="page-container">


        <div class="form-wrapper">


            <div class="card">


                <div class="card-body">


                    <h1 class="page-title">
                        📚 Novo Livro
                    </h1>





                    <form @submit.prevent="saveBook">





                        <div class="form-group">


                            <label class="form-label">

                                Título

                            </label>


                            <input

                                v-model="form.title"

                                type="text"

                                class="form-control"

                                placeholder="Digite o título do livro"

                                required

                            />


                        </div>







                        <div class="form-group">


                            <label class="form-label">

                                Autor

                            </label>


                            <input

                                v-model="form.author"

                                type="text"

                                class="form-control"

                                placeholder="Digite o autor"

                                required

                            />


                        </div>







                        <div class="form-group">


                            <label class="form-label">

                                Ano

                            </label>


                            <input

                                v-model.number="form.year"

                                type="number"

                                class="form-control"

                                placeholder="Ano de publicação"

                            />


                        </div>







                        <div class="form-group">


                            <label class="form-label">

                                Descrição

                            </label>


                            <textarea

                                v-model="form.description"

                                class="form-control"

                                rows="5"

                                placeholder="Digite uma descrição do livro"

                            ></textarea>


                        </div>








                        <div class="form-actions">



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