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


    }



}



export default alertService