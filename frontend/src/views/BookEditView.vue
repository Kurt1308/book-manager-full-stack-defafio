<template>


    <main class="page-container">



        <div class="form-card">



            <h1 class="page-title">

                ✏️ Editar Livro

            </h1>






            <div

                v-if="loading"

                class="loading-state"

            >

                Carregando livro...


            </div>







            <form

                v-else

                @submit.prevent="updateBook"

            >





                <div class="form-group">


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









                <div class="form-group">


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









                <div class="form-group">


                    <label class="form-label">

                        Ano

                    </label>



                    <input

                        v-model.number="form.year"

                        type="number"

                        class="form-control"

                    />


                </div>









                <div class="form-group">


                    <label class="form-label">

                        Descrição

                    </label>



                    <textarea

                        v-model="form.description"

                        class="form-control"

                        rows="4"

                    ></textarea>


                </div>









                <div class="form-actions">





                    <RouterLink

                        to="/books"

                        class="btn btn-secondary"

                    >

                        Cancelar

                    </RouterLink>









                    <button

                        type="submit"

                        class="btn btn-primary"

                        :disabled="saving"

                    >

                        {{ saving ? 'Atualizando...' : 'Atualizar' }}


                    </button>





                </div>





            </form>




        </div>





    </main>


</template>







<script setup lang="ts">


import { reactive, onMounted, ref } from 'vue'

import { useRoute, useRouter } from 'vue-router'


import {

    getBookById,

    updateBook as updateBookService

} from '@/services/book.service'


import type { BookRequest } from '@/types/book'


import alertService from '@/services/alert.service'








const route = useRoute()


const router = useRouter()






const bookId = Number(route.params.id)





const loading = ref(false)


const saving = ref(false)








const form = reactive<BookRequest>({


    title: '',


    author: '',


    year: undefined,


    description: ''


})









async function loadBook() {



    try {



        loading.value = true



        const book = await getBookById(bookId)



        form.title = book.title


        form.author = book.author


        form.year = book.year


        form.description = book.description



    } catch(error) {



        await alertService.apiError(error)



        router.push('/books')



    } finally {



        loading.value = false



    }



}









async function updateBook() {



    try {



        saving.value = true



        await updateBookService(

            bookId,

            form

        )



        await alertService.success(

            'Livro atualizado com sucesso'

        )



        router.push('/books')



    } catch(error) {



        await alertService.apiError(error)



    } finally {



        saving.value = false



    }



}









onMounted(() => {


    loadBook()



})





</script>







<style scoped>


</style>