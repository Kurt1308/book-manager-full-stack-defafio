import api from '@/api/axios'


export interface LoginRequest {

    email: string

    password: string

}



export interface RegisterRequest {

    name: string

    email: string

    password: string

}



export interface AuthenticationResponse {

    token: string

}





export async function login(
    data: LoginRequest
): Promise<AuthenticationResponse> {


    const response = await api.post<AuthenticationResponse>(

        '/auth/login',

        data

    )


    return response.data

}





export async function register(

    data: RegisterRequest

): Promise<AuthenticationResponse> {


    const response = await api.post<AuthenticationResponse>(

        '/auth/register',

        data

    )


    return response.data

}