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



    const params: Record<string, any> = {


        page,


        size


    }






    if(title && title.trim()) {


        params.title = title.trim()


    }







    const response = await api.get<BookPage>(


        '/books',


        {

            params

        }


    )





    return response.data


}









export async function getBookById(


    id: number


): Promise<Book> {



    const response = await api.get<Book>(


        `/books/${id}`


    )





    return response.data


}









export async function createBook(


    book: BookRequest


): Promise<Book> {



    const response = await api.post<Book>(


        '/books',


        book


    )





    return response.data


}









export async function updateBook(


    id: number,


    book: BookRequest


): Promise<Book> {



    const response = await api.put<Book>(


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