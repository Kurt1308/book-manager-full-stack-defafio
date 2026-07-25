export interface Book {


    id: number


    title: string


    author: string


    year?: number | null


    description?: string | null


}





export interface BookRequest {


    title: string


    author: string


    year?: number | null


    description?: string | null


}