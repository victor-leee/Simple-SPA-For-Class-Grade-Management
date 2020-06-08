import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const Welcome = () => import('../components/Welcome')
const Main = () => import('../components/Main')
const ClassCourse = () => import('../components/ClassCourse')
const Stat = () => import('../components/Stat')

export default new Router({
  routes: [
    {
      path: '/',
      redirect:'/welcome'
    },
    {
      path: '/welcome',
      component: Welcome
    },
    {
      path:'/main',
      children:[
        {
          path:'class',
          component: ClassCourse
        },
        {
          path: 'stat',
          component: Stat
        }
      ],
      component: Main
    }
  ],
  mode:'history'
})
