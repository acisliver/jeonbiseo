(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0abcbc"],{1799:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-simple-table",{scopedSlots:t._u([{key:"default",fn:function(){return[a("thead",[a("tr",[a("th",{staticClass:"text-left"},[t._v(" "+t._s(t.title)+" ")]),a("th",{staticClass:"text-right"},[t._v(" 작성자 ")])])]),a("tbody",t._l(t.paginatedData,(function(e){return a("tr",{key:e.id},[a("td",{staticClass:"text-left"},[t._v(t._s(e.title))]),a("td",{staticClass:"text-right"},[t._v(t._s(e.user.nickName))])])})),0),a("div",{staticClass:"btn-cover"},[a("v-btn",{staticClass:"page-btn",attrs:{disabled:0===t.pageNum},on:{click:t.prevPage}},[t._v(" 이전 ")]),a("span",{staticClass:"page-count"},[t._v(t._s(t.pageNum+1)+" / "+t._s(t.pageCount)+" 페이지")]),a("v-btn",{staticClass:"page-btn",attrs:{disabled:t.pageNum>=t.pageCount-1},on:{click:t.nextPage}},[t._v(" 다음 ")])],1)]},proxy:!0}])})},s=[],i=(a("a9e3"),a("fb6a"),{name:"Pagination",data:function(){return{pageNum:0}},props:{title:{type:String,required:!0},header:{type:Array,required:!0},pageSize:{type:Number,required:!1,default:7}},methods:{nextPage:function(){this.pageNum+=1},prevPage:function(){this.pageNum-=1}},computed:{pageCount:function(){var t=this.header.length,e=this.pageSize,a=Math.floor(t/e);return t%e>0&&(a+=1),a},paginatedData:function(){var t=this.pageNum*this.pageSize,e=t+this.pageSize;return this.header.slice(t,e)}}}),r=i,u=a("2877"),p=a("6544"),c=a.n(p),l=a("8336"),o=a("1f4f"),d=Object(u["a"])(r,n,s,!1,null,"7c4db7d6",null);e["default"]=d.exports;c()(d,{VBtn:l["a"],VSimpleTable:o["a"]})}}]);
//# sourceMappingURL=chunk-2d0abcbc.7e4cf615.js.map