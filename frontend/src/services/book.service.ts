import api from '@/api/axios'

import type { Book, BookRequest } from '@/types/book'



interface BookPage {

    content: Book[]

    totalElements: number

    totalPages: number

    size: number

    number: number

}





export async function getBooks(

    title?: string,

    page: number = 0,

    size: number = 10

): Promise<BookPage> {


    const response = await api.get('/books', {

        params: {

            title,

            page,

            size

        }

    })


    return response.data

}








export async function getBookById(

    id: number

): Promise<Book> {


    const response = await api.get(

        `/books/${id}`

    )


    return response.data

}








export async function createBook(

    book: BookRequest

): Promise<Book> {


    const response = await api.post(

        '/books',

        book

    )


    return response.data

}








export async function updateBook(

    id: number,

    book: BookRequest

): Promise<Book> {


    const response = await api.put(

        `/books/${id}`,

        book

    )


    return response.data

}








export async function deleteBook(

    id: number

): Promise<void> {


    await api.delete(

        `/books/${id}`

    )

}