(function(e){function n(n){for(var o,r,u=n[0],i=n[1],l=n[2],s=0,d=[];s<u.length;s++)r=u[s],Object.prototype.hasOwnProperty.call(a,r)&&a[r]&&d.push(a[r][0]),a[r]=0;for(o in i)Object.prototype.hasOwnProperty.call(i,o)&&(e[o]=i[o]);p&&p(n);while(d.length)d.shift()();return c.push.apply(c,l||[]),t()}function t(){for(var e,n=0;n<c.length;n++){for(var t=c[n],o=!0,r=1;r<t.length;r++){var u=t[r];0!==a[u]&&(o=!1)}o&&(c.splice(n--,1),e=i(i.s=t[0]))}return e}var o={},r={app:0},a={app:0},c=[];function u(e){return i.p+"js/"+({about:"about","board-group":"board-group",home:"home",mypage:"mypage",signup:"signup"}[e]||e)+"."+{about:"cbbb2314","board-group":"3f6e75c9","chunk-49fe5823":"fb5c3d08","chunk-87a09f2c":"70ce638d","chunk-a8d0ce5c":"dea7d5d7",home:"f560b5e4",mypage:"087b59c6",signup:"4243e40b","chunk-2d0abcbc":"7e4cf615"}[e]+".js"}function i(n){if(o[n])return o[n].exports;var t=o[n]={i:n,l:!1,exports:{}};return e[n].call(t.exports,t,t.exports,i),t.l=!0,t.exports}i.e=function(e){var n=[],t={"board-group":1,"chunk-49fe5823":1,"chunk-87a09f2c":1,"chunk-a8d0ce5c":1,mypage:1,signup:1};r[e]?n.push(r[e]):0!==r[e]&&t[e]&&n.push(r[e]=new Promise((function(n,t){for(var o="css/"+({about:"about","board-group":"board-group",home:"home",mypage:"mypage",signup:"signup"}[e]||e)+"."+{about:"31d6cfe0","board-group":"bbd732a1","chunk-49fe5823":"68e6a77c","chunk-87a09f2c":"71137b21","chunk-a8d0ce5c":"a3ebaf9b",home:"31d6cfe0",mypage:"a8a186f4",signup:"fba5bb1e","chunk-2d0abcbc":"31d6cfe0"}[e]+".css",a=i.p+o,c=document.getElementsByTagName("link"),u=0;u<c.length;u++){var l=c[u],s=l.getAttribute("data-href")||l.getAttribute("href");if("stylesheet"===l.rel&&(s===o||s===a))return n()}var d=document.getElementsByTagName("style");for(u=0;u<d.length;u++){l=d[u],s=l.getAttribute("data-href");if(s===o||s===a)return n()}var p=document.createElement("link");p.rel="stylesheet",p.type="text/css",p.onload=n,p.onerror=function(n){var o=n&&n.target&&n.target.src||a,c=new Error("Loading CSS chunk "+e+" failed.\n("+o+")");c.code="CSS_CHUNK_LOAD_FAILED",c.request=o,delete r[e],p.parentNode.removeChild(p),t(c)},p.href=a;var f=document.getElementsByTagName("head")[0];f.appendChild(p)})).then((function(){r[e]=0})));var o=a[e];if(0!==o)if(o)n.push(o[2]);else{var c=new Promise((function(n,t){o=a[e]=[n,t]}));n.push(o[2]=c);var l,s=document.createElement("script");s.charset="utf-8",s.timeout=120,i.nc&&s.setAttribute("nonce",i.nc),s.src=u(e);var d=new Error;l=function(n){s.onerror=s.onload=null,clearTimeout(p);var t=a[e];if(0!==t){if(t){var o=n&&("load"===n.type?"missing":n.type),r=n&&n.target&&n.target.src;d.message="Loading chunk "+e+" failed.\n("+o+": "+r+")",d.name="ChunkLoadError",d.type=o,d.request=r,t[1](d)}a[e]=void 0}};var p=setTimeout((function(){l({type:"timeout",target:s})}),12e4);s.onerror=s.onload=l,document.head.appendChild(s)}return Promise.all(n)},i.m=e,i.c=o,i.d=function(e,n,t){i.o(e,n)||Object.defineProperty(e,n,{enumerable:!0,get:t})},i.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},i.t=function(e,n){if(1&n&&(e=i(e)),8&n)return e;if(4&n&&"object"===typeof e&&e&&e.__esModule)return e;var t=Object.create(null);if(i.r(t),Object.defineProperty(t,"default",{enumerable:!0,value:e}),2&n&&"string"!=typeof e)for(var o in e)i.d(t,o,function(n){return e[n]}.bind(null,o));return t},i.n=function(e){var n=e&&e.__esModule?function(){return e["default"]}:function(){return e};return i.d(n,"a",n),n},i.o=function(e,n){return Object.prototype.hasOwnProperty.call(e,n)},i.p="/",i.oe=function(e){throw console.error(e),e};var l=window["webpackJsonp"]=window["webpackJsonp"]||[],s=l.push.bind(l);l.push=n,l=l.slice();for(var d=0;d<l.length;d++)n(l[d]);var p=s;c.push([0,"chunk-vendors"]),t()})({0:function(e,n,t){e.exports=t("56d7")},"56d7":function(e,n,t){"use strict";t.r(n);t("e260"),t("e6cf"),t("cca6"),t("a79d");var o=t("2b0e"),r=function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("v-app",[t("core-app-bar"),t("core-view"),t("core-footer")],1)},a=[],c=(t("d3b7"),t("3ca3"),t("ddb0"),{name:"App",components:{CoreAppBar:function(){return t.e("chunk-87a09f2c").then(t.bind(null,"6dac"))},CoreView:function(){return t.e("chunk-49fe5823").then(t.bind(null,"874a"))},CoreFooter:function(){return t.e("chunk-a8d0ce5c").then(t.bind(null,"972f"))}}}),u=c,i=(t("5c0b"),t("2877")),l=t("6544"),s=t.n(l),d=t("7496"),p=Object(i["a"])(u,r,a,!1,null,null,null),f=p.exports;s()(p,{VApp:d["a"]});var b=t("f309");o["a"].use(b["a"]);var g=new b["a"]({}),m=t("8c4f"),h=function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("v-container",{staticClass:"fill-height",staticStyle:{"max-width":"500px"}},[t("v-layout",{attrs:{row:"",wrap:"","align-center":""}},[t("v-flex",{attrs:{xs12:""}},[t("v-alert",{attrs:{value:e.isLoginError,dense:"",outlined:"",type:"error"}},[e._v(" 아이디와 비밀번호를 확인해주세요 ")]),t("v-card",{staticStyle:{"max-width":"500px"}},[t("v-toolbar",{staticClass:"indigo lighten-5",attrs:{flat:""}},[t("v-toolbar-title",[e._v("로그인")])],1),t("div",{staticClass:"pa-3"},[t("v-text-field",{attrs:{label:"이메일"},model:{value:e.userName,callback:function(n){e.userName=n},expression:"userName"}}),t("v-text-field",{attrs:{label:"비밀번호"},model:{value:e.password,callback:function(n){e.password=n},expression:"password"}}),t("v-btn",{attrs:{color:"primary",depressed:"",large:"",block:""},on:{click:function(n){return e.login({userName:e.userName,password:e.password})}}},[e._v("로그인 ")]),t("v-btn",{on:{click:function(n){return e.$router.push({name:"SignUp"})}}},[e._v("회원가입")])],1)],1)],1)],1)],1)},v=[],y=t("5530"),w=t("2f62"),k=t("bc3a"),x=t.n(k);o["a"].use(w["a"]);var E=new w["a"].Store({state:{isLogin:!1,isLoginError:!1,userInfo:{userName:"Test1",password:"1234",nickName:"TestNick1"},boardHeader:null,boardBody:null,debateHeader:null,debateBody:null},getters:{},mutations:{loginSuccess:function(e){e.isLogin=!0,e.isLoginError=!1},loginError:function(e){e.isLoginError=!0,e.isLogin=!1},logout:function(e){e.isLogin=!1,e.isLoginError=!1},setUserInfo:function(e,n){e.userInfo=n},setBoardHeader:function(e,n){e.boardHeader=n},setDebateHeader:function(e,n){e.debateHeader=n}},actions:{getUserInfo:function(e){var n=e.commit;x.a.get("/api/user").then((function(e){n("setUserInfo",e.data),console.log(e.data)})).catch((function(e){console.log(e)}))},loginAction:function(e,n){var t=e.commit;200===n?(t("loginSuccess"),R.push({name:"Home"})):204===n&&t("loginError")},logoutAction:function(e){var n=e.commit;n("logout")},getBoardHeader:function(e){var n=e.commit;x.a.get("/api/boardHeader").then((function(e){n("setBoardHeader",e.data.content),console.log(e.data)})).catch((function(e){console.log(e)}))},getDebateHeader:function(e){var n=e.commit;x.a.get("/api/debateHeader").then((function(e){n("setdebateHeader",e.data.content),console.log(e)})).catch((function(e){console.log(e)}))}},modules:{}}),L={name:"Login",data:function(){return{userName:null,password:null}},computed:Object(y["a"])({},Object(w["c"])(["isLoginError","isLogin"])),methods:Object(y["a"])(Object(y["a"])({},Object(w["b"])(["loginAction","logoutAction"])),{},{login:function(e){console.log(e),x.a.post("/login",e).then((function(e){console.log(e);var n=e.data.status;E.dispatch("loginAction",n)})).catch((function(e){console.log(e)}))}})},O=L,j=t("0798"),_=t("8336"),A=t("b0af"),S=t("a523"),C=t("0e8f"),H=t("a722"),B=t("8654"),N=t("71d9"),T=t("2a7f"),P=Object(i["a"])(O,h,v,!1,null,null,null),V=P.exports;s()(P,{VAlert:j["a"],VBtn:_["a"],VCard:A["a"],VContainer:S["a"],VFlex:C["a"],VLayout:H["a"],VTextField:B["a"],VToolbar:N["a"],VToolbarTitle:T["b"]}),o["a"].use(m["a"]);var I=function(){return t.e("home").then(t.bind(null,"bb51"))},U=function(){return t.e("about").then(t.bind(null,"f820"))},D=function(){return t.e("mypage").then(t.bind(null,"73e8"))},F=function(){return t.e("signup").then(t.bind(null,"5ace"))},M=function(){return t.e("board-group").then(t.bind(null,"5d6d"))},$=function(){return t.e("board-group").then(t.bind(null,"c290"))},q=function(){return t.e("board-group").then(t.bind(null,"9950"))},J=[{path:"/",name:"Home",component:I},{path:"/about",name:"About",component:U},{path:"/login",name:"Login",component:V},{path:"/mypage/:username",name:"MyPage",component:D},{path:"/signup",name:"SignUp",component:F},{path:"/board",name:"Board",component:M},{path:"/board/:boardid",name:"ReadBoard"},{path:"/board/free-board",name:"FreeBoard",component:$},{path:"/board/debate-board",name:"DebateBoard",component:q}],K=new m["a"]({mode:"history",base:"/",routes:J}),R=K;o["a"].config.productionTip=!1,new o["a"]({vuetify:g,router:R,store:E,render:function(e){return e(f)}}).$mount("#app")},"5c0b":function(e,n,t){"use strict";t("9c0c")},"9c0c":function(e,n,t){}});
//# sourceMappingURL=app.6902f153.js.map