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


                @keyup.enter="searchBooks"


            />






            <button


                class="btn btn-primary"


                @click="searchBooks"


            >

                Buscar

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

                            >

                                Excluir


                            </button>





                        </div>





                    </div>



                </div>




            </div>






        </div>









        <div

            v-if="totalPages > 1"

            class="pagination-container"

        >





            <button


                class="btn btn-secondary"


                :disabled="currentPage === 0"


                @click="previousPage"


            >

                ← Anterior

            </button>







            <span class="page-info">


                Página {{ currentPage + 1 }}

                de {{ totalPages }}


            </span>







            <button


                class="btn btn-secondary"


                :disabled="currentPage >= totalPages - 1"


                @click="nextPage"


            >

                Próxima →

            </button>





        </div>









        <div

            v-if="totalElements > 0"

            class="total-info"

        >

            Total de livros:

            {{ totalElements }}


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



const search = ref('')





const currentPage = ref(0)



const totalPages = ref(0)



const totalElements = ref(0)



const pageSize = ref(10)









async function loadBooks() {



    try {



        loading.value = true





        const response = await getBooks(


            search.value.trim(),


            currentPage.value,


            pageSize.value


        )






        books.value = response.content



        totalPages.value = response.totalPages



        totalElements.value = response.totalElements







    } catch(error) {



        await alertService.apiError(error)





    } finally {



        loading.value = false




    }



}









function searchBooks() {



    currentPage.value = 0



    loadBooks()



}









function nextPage() {



    if(currentPage.value < totalPages.value - 1){


        currentPage.value++


        loadBooks()


    }


}









function previousPage() {



    if(currentPage.value > 0){


        currentPage.value--


        loadBooks()


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



        await deleteBook(id)





        await alertService.success(

            'Livro removido com sucesso'

        )





        loadBooks()





    } catch(error) {



        await alertService.apiError(error)





    }



}









onMounted(() => {


    loadBooks()



})





</script>









<style scoped>


.pagination-container {


    display: flex;


    justify-content: center;


    align-items: center;


    gap: 20px;


    margin-top: 30px;


}




.page-info {


    font-weight: bold;


}





.total-info {


    text-align: center;


    margin-top: 15px;


    color: #666;


}



</style>