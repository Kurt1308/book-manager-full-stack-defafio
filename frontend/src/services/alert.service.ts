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



        let message =
            'Ocorreu um erro inesperado'



        const status =
            error?.response?.status




        const responseMessage =
            error?.response?.data?.message






        /*
         * Prioriza mensagem enviada pela API
         */
        if(responseMessage) {


            message = responseMessage


        }







        else if(status === 401) {


            message =
                'Usuário não autorizado.'


        }







        else if(status === 403) {


            message =
                'Você não possui permissão para esta ação.'


        }







        else if(status === 404) {


            message =
                'Recurso não encontrado.'


        }







        else if(status === 400) {


            message =
                'Dados inválidos enviados.'


        }







        else if(status >= 500) {


            message =
                'Erro interno do servidor.'


        }







        return this.error(message)


    }



}



export default alertService