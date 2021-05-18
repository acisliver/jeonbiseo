import Vue from 'vue'
import VueRouter from 'vue-router'

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(() => {
    return window.location.reload()
  })
};

Vue.use(VueRouter)

const Home = () => import(/* webpackChunkName: "home" */ '../views/Home.vue')
// const About = () => import(/* webpackChunkName: "about" */ '../views/About.vue');
const Login = () => import(/* webpackChunkName: "login" */ '../components/Login.vue');
const MyPage = () => import(/* webpackChunkName: "mypage" */ '../views/MyPage.vue');
const SignUp = () => import(/* webpackChunkName: "signup" */ '../components/SignUp.vue');
const Board = () => import(/* webpackChunkName: "board-group" */ '../views/Board.vue');
const FreeBoard = () => import(/* webpackChunkName: "board-group" */ '../views/FreeBoard');
const DebateBoard = () => import(/* webpackChunkName: "board-group" */ '../views/DebateBoard');
const WriteFreeBoard = () => import(/* webpackChunkName: "board-group" */ '../components/WriteFreeBoard');
const WriteDebateBoard = () => import(/* webpackChunkName: "board-group" */ '../components/WriteDebateBoard');
const ReadFreeBoard = () => import(/* webpackChunkName: "board-group" */ '../components/ReadFreeBoard');
const ReadDebateBoard = () => import(/* webpackChunkName: "board-group" */ '../components/ReadDebateBoard');
const Compare = () => import(/* webpackChunkName: "compare" */ '../views/Compare');
const Recommend = () => import(/* webpackChunkName: "recommend" */ '../views/Recommend');
const OS = () => import(/* webpackChunkName: "recommend" */ '../views/recommends/OS');
const Size = () => import(/* webpackChunkName: "recommend" */ '../views/recommends/Size');
const Network = () => import(/* webpackChunkName: "recommend" */ '../views/recommends/Network');
const Portability = () => import(/* webpackChunkName: "recommend" */ '../views/recommends/Portability');
const Pen = () => import(/* webpackChunkName: "recommend" */ '../views/recommends/Pen');




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
  // {
  //   path: '/about',
  //   name: 'About',
  //   component: About
  // },
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
  //  게시판 라우팅
  {
    path: '/board',
    name: 'Board',
    component: Board
  },
  {
    path: '/board/free-board/:boardId',
    name: 'ReadFreeBoard',
    component: ReadFreeBoard
  },
  {
    path: '/board/debate-board/:boardId',
    name: 'ReadDebateBoard',
    component: ReadDebateBoard
  },
  {
    path: '/board/debate-board/:boardId',
    name: 'ReadDebateBoard'
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
  },
  {
    path: '/board/free-board/write',
    name: 'WriteFreeBoard',
    component: WriteFreeBoard
  },
  {
    path: '/board/debate-board/write',
    name: 'WriteDebateBoard',
    component: WriteDebateBoard
  },
  //  웹사이트 서비스
  {
    path: '/compare',
    name: 'Compare',
    component: Compare
  },
  {
    path: '/recommend',
    name: 'Recommend',
    component: Recommend
  },
  {
    path: '/recommend/os',
    name: 'OS',
    component: OS
  },
  {
    path: '/recommend/size',
    name: 'Size',
    component: Size
  },{
    path: '/recommend/network',
    name: 'Network',
    component: Network
  },
  {
    path: '/recommend/pen',
    name: 'Pen',
    component: Pen
  },
  {
    path: '/recommend/protability',
    name: 'Portability',
    component: Portability
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
