import Swal from 'sweetalert2'



const alertService = {


    success(message: string) {

        return Swal.fire({

            icon: 'success',

            title: 'Sucesso',

            text: message,

            confirmButtonText: 'OK'

        })

    },





    error(message: string) {

        return Swal.fire({

            icon: 'error',

            title: 'Erro',

            text: message,

            confirmButtonText: 'OK'

        })

    },





    warning(message: string) {

        return Swal.fire({

            icon: 'warning',

            title: 'Atenção',

            text: message,

            confirmButtonText: 'OK'

        })

    },






    confirm(message: string) {


        return Swal.fire({

            icon: 'warning',

            title: 'Confirmação',

            text: message,

            showCancelButton: true,

            confirmButtonText: 'Confirmar',

            cancelButtonText: 'Cancelar'

        })


    },






    apiError(error: any) {


        let message = 'Ocorreu um erro inesperado'



        if(error?.response?.data?.message) {


            message = error.response.data.message


        }


        else if(error?.response?.status === 401) {


            message = 'Usuário não autorizado'


        }


        else if(error?.response?.status === 404) {


            message = 'Recurso não encontrado'


        }



        return this.error(message)


    }



}



export default alertService