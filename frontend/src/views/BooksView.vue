<template>

    <main class="page-container">



        <div class="page-header">


            <h1 class="page-title">

                📚 Livros

            </h1>





            <RouterLink

                to="/books/new"

                class="btn btn-primary"

            >

                Novo Livro

            </RouterLink>



        </div>








        <div class="search-box">



            <input


                v-model="search"


                type="text"


                class="form-control"


                placeholder="Buscar por título"


                @keyup.enter="loadBooks"


            />







            <button


                class="btn btn-primary"


                @click="loadBooks"


                :disabled="loading"


            >


                <span v-if="loading">

                    Buscando...

                </span>


                <span v-else>

                    Buscar

                </span>


            </button>



        </div>









        <div

            v-if="loading"

            class="loading-state"

        >

            Carregando livros...

        </div>









        <div

            v-else-if="books.length === 0"

            class="empty-state"

        >

            Nenhum livro encontrado.

        </div>









        <div

            v-else

            class="book-grid"

        >






            <div


                v-for="book in books"


                :key="book.id"


                class="book-card"



            >





                <div class="card">



                    <div class="card-body">





                        <h5 class="card-title">


                            {{ book.title }}


                        </h5>







                        <h6 class="card-subtitle">


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









                        <div class="action-buttons">





                            <RouterLink


                                :to="`/books/${book.id}/edit`"


                                class="btn btn-warning"

                            >

                                Editar


                            </RouterLink>









                            <button


                                class="btn btn-danger"


                                @click="removeBook(book.id)"


                                :disabled="deleting === book.id"

                            >


                                <span v-if="deleting === book.id">

                                    Excluindo...

                                </span>


                                <span v-else>

                                    Excluir

                                </span>


                            </button>





                        </div>





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



import alertService from '@/services/alert.service'






const books = ref<Book[]>([])


const loading = ref(false)


const deleting = ref<number | null>(null)


const search = ref('')









async function loadBooks() {


    try {


        loading.value = true




        const response = await getBooks(

            search.value.trim()

        )



        books.value = response.content





    } catch(error) {



        await alertService.apiError(error)





    } finally {



        loading.value = false





    }



}









async function removeBook(id:number) {



    const result = await alertService.confirm(

        'Deseja excluir este livro?'

    )




    if(!result.isConfirmed){


        return


    }






    try {



        deleting.value = id




        await deleteBook(id)





        await alertService.success(

            'Livro removido com sucesso'

        )





        await loadBooks()





    } catch(error) {




        await alertService.apiError(error)





    } finally {



        deleting.value = null



    }




}









onMounted(() => {


    loadBooks()



})







</script>









<style scoped>

</style>