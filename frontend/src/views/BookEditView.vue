<template>


    <main class="container py-5">


        <div class="row justify-content-center">


            <div class="col-md-8">


                <div class="card shadow-sm">


                    <div class="card-body">


                        <h1 class="mb-4">

                            ✏️ Editar Livro

                        </h1>





                        <form @submit.prevent="updateBook">





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

                                    Atualizar

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


import { reactive, onMounted } from 'vue'

import { useRoute, useRouter } from 'vue-router'


import {
    getBookById,
    updateBook as updateBookService
} from '@/services/book.service'


import type { BookRequest } from '@/types/book'






const route = useRoute()

const router = useRouter()





const bookId = Number(route.params.id)







const form = reactive<BookRequest>({

    title: '',

    author: '',

    year: undefined,

    description: ''

})








async function loadBook() {


    const book = await getBookById(bookId)



    form.title = book.title

    form.author = book.author

    form.year = book.year

    form.description = book.description



}








async function updateBook() {


    await updateBookService(

        bookId,

        form

    )



    router.push('/books')


}








onMounted(() => {


    loadBook()


})



</script>







<style scoped>


</style>