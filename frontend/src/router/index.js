import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/components/Login";

Vue.use(VueRouter)

const Home = () => import(/* webpackChunkName: "home" */ '../views/Home.vue')
const About = () => import(/* webpackChunkName: "about" */ '../views/About.vue');
// const Login = () => import(/* webpackChunkName: "login" */ '../components/Login.vue');
const MyPage = () => import(/* webpackChunkName: "mypage" */ '../views/MyPage.vue');
const SignUp = () => import(/* webpackChunkName: "signup" */ '../components/SignUp.vue');
const Board = () => import(/* webpackChunkName: "board-group" */ '../views/Board.vue');
const FreeBoard = () => import(/* webpackChunkName: "board-group" */ '../views/FreeBoard');
const DebateBoard = () => import(/* webpackChunkName: "board-group" */ '../views/DebateBoard');

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
  {
    path: '/board',
    name: 'Board',
    component: Board
  },
  {
    path: '/board/:boardid',
    name: 'ReadBoard'
  },
  {
    path: '/board/free-board',
    name: 'FreeBoard',
    component: FreeBoard
  },
  {
    path: '/board/debate-board',
    name: 'DebateBoard',
    component: DebateBoard
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
