import { createRouter, createWebHistory } from 'vue-router'

import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import HomeView from '@/views/HomeView.vue'
import NotFoundView from '@/views/NotFoundView.vue'


const router = createRouter({

  history: createWebHistory(),

  routes: [

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
      path: '/home',
      name: 'home',
      component: HomeView
    },

    {
      path: '/:pathMatch(.*)*',
      name: 'not-found',
      component: NotFoundView
    }

  ]

})


export default router