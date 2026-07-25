<template>

    <main class="container py-5">


        <div class="d-flex justify-content-between align-items-center mb-4">


            <h1>
                📚 Livros
            </h1>


            <RouterLink
                to="/books/new"
                class="btn btn-primary"
            >

                Novo Livro

            </RouterLink>


        </div>





        <div class="row mb-4">


            <div class="col-md-6">


                <input

                    v-model="search"

                    type="text"

                    class="form-control"

                    placeholder="Buscar por título"

                    @keyup.enter="loadBooks"

                />


            </div>


        </div>





        <div
            v-if="loading"
            class="text-center"
        >

            Carregando livros...

        </div>





        <div
            v-else-if="books.length === 0"
            class="alert alert-info"
        >

            Nenhum livro encontrado.

        </div>





        <div
            v-else
            class="row"
        >


            <div

                v-for="book in books"

                :key="book.id"

                class="col-md-4 mb-4"

            >


                <div class="card shadow-sm h-100">


                    <div class="card-body">


                        <h5 class="card-title">

                            {{ book.title }}

                        </h5>



                        <h6 class="card-subtitle mb-2 text-muted">

                            {{ book.author }}

                        </h6>




                        <p
                            v-if="book.year"
                            class="card-text"
                        >

                            Ano:
                            {{ book.year }}

                        </p>





                        <p class="card-text">

                            {{ book.description }}

                        </p>




                        <RouterLink

                            :to="`/books/${book.id}/edit`"

                            class="btn btn-warning btn-sm me-2"

                        >

                            Editar

                        </RouterLink>





                        <button

                            class="btn btn-danger btn-sm"

                            @click="removeBook(book.id)"

                        >

                            Excluir

                        </button>



                    </div>


                </div>


            </div>


        </div>





    </main>


</template>





<script setup lang="ts">


import { ref, onMounted } from 'vue'

import { RouterLink } from 'vue-router'


import type { Book } from '@/types/book'


import {
    getBooks,
    deleteBook
} from '@/services/book.service'






const books = ref<Book[]>([])


const loading = ref(false)


const search = ref('')








async function loadBooks() {


    try {


        loading.value = true



        const response = await getBooks(

            search.value

        )



        books.value = response.content



    } finally {


        loading.value = false


    }


}








async function removeBook(id:number) {


    const confirmed = confirm(
        'Deseja excluir este livro?'
    )


    if(!confirmed){

        return

    }



    try {


        await deleteBook(id)


        await loadBooks()



    } catch(error) {


        console.error(
            'Erro ao excluir livro:',
            error
        )


        alert(
            'Erro ao excluir o livro.'
        )


    }


}








onMounted(() => {


    loadBooks()


})



</script>





<style scoped>


</style>