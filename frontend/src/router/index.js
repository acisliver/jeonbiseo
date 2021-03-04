import Vue from 'vue'
import VueRouter from 'vue-router'
// import store from '@/store'

Vue.use(VueRouter)

const Home = () => import(/* webpackChunkName: "home" */ '../views/Home.vue')
const About = () => import(/* webpackChunkName: "about" */ '../views/About.vue');
const Login = () => import(/* webpackChunkName: "login" */ '../components/Login.vue');
const MyPage = () => import(/* webpackChunkName: "mypage" */ '../views/MyPage.vue');
const SignUp = () => import(/* webpackChunkName: "signup" */ '../components/SignUp.vue');

// const rejectAuthUser = (to, from, next) =>{
//   if(store.state.isLogin){
//     alert('이미 로그인하였습니다')
//     next('/')
//   }
//   else{
//     next()
//   }
// }
//
// const onlyAuthUser = (to, from, next) =>{
//   if(!store.state.isLogin){
//     alert('로그인이 필요한 기능힙니다')
//     next('/')
//   }
//   else{
//     next()
//   }
// }

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/login',
    name: 'Login',
    // beforeEnter: rejectAuthUser,
    component: Login
  },
  {
    path: '/mypage/:username',
    name: 'MyPage',
    // beforeEnter: onlyAuthUser,
    component: MyPage
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
