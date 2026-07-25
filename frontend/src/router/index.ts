import { createRouter, createWebHistory } from 'vue-router'


import { useAuthStore } from '@/stores/auth'


import LoginView from '@/views/LoginView.vue'

import RegisterView from '@/views/RegisterView.vue'

import HomeView from '@/views/HomeView.vue'

import NotFoundView from '@/views/NotFoundView.vue'

import AuthLayout from '@/components/layout/AuthLayout.vue'

import BooksView from '@/views/BooksView.vue'

import BookCreateView from '@/views/BookCreateView.vue'

import BookEditView from '@/views/BookEditView.vue'





const routes = [



    {
        path: '/',

        redirect: '/login'

    },





    {
        path: '/login',

        name: 'login',

        component: LoginView

    },





    {
        path: '/register',

        name: 'register',

        component: RegisterView

    },





    {

        path: '/',

        component: AuthLayout,


        meta: {

            requiresAuth: true

        },


        children: [



            {

                path: 'home',

                name: 'home',

                component: HomeView

            },




            {

                path: 'books',

                name: 'books',

                component: BooksView

            },




            {

                path: 'books/new',

                name: 'book-create',

                component: BookCreateView

            },




            {

                path: 'books/:id/edit',

                name: 'book-edit',

                component: BookEditView

            }


        ]

    },





    {

        path: '/:pathMatch(.*)*',

        name: 'not-found',

        component: NotFoundView

    }


]







const router = createRouter({


    history: createWebHistory(),


    routes


})








router.beforeEach((to, from, next) => {


    const authStore = useAuthStore()



    const requiresAuth = to.matched.some(

        route => route.meta.requiresAuth

    )





    if (

        requiresAuth &&

        !authStore.isAuthenticated

    ) {


        next('/login')


        return

    }






    next()


})






export default router