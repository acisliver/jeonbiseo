(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3b2cb03e"],{1799:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-simple-table",{scopedSlots:t._u([{key:"default",fn:function(){return[a("thead",[a("tr",[a("th",{staticClass:"text-left"},[t._v(" "+t._s(t.title)+" ")]),a("th",{staticClass:"text-right"},[t._v(" 작성자 ")])])]),a("tbody",t._l(t.paginatedData,(function(e){return a("tr",{key:e.id},[a("td",{staticClass:"text-left"},[t._v(t._s(e.title))]),a("td",{staticClass:"text-right"},[t._v(t._s(e.writer))])])})),0),a("v-spacer"),a("div",{staticClass:"btn-cover"},[a("v-btn",{staticClass:"page-btn",attrs:{disabled:0===t.pageNum},on:{click:t.prevPage}},[t._v(" 이전 ")]),a("span",{staticClass:"page-count"},[t._v(t._s(t.pageNum+1)+" / "+t._s(t.pageCount)+" 페이지")]),a("v-btn",{staticClass:"page-btn",attrs:{disabled:t.pageNum>=t.pageCount-1},on:{click:t.nextPage}},[t._v(" 다음 ")])],1)]},proxy:!0}])})},i=[],n=(a("a9e3"),a("fb6a"),{name:"Pagination",data:function(){return{pageNum:0}},props:{title:{type:String,required:!0},header:{type:Array,required:!0},pageSize:{type:Number,required:!1,default:7}},methods:{nextPage:function(){this.pageNum+=1},prevPage:function(){this.pageNum-=1}},computed:{pageCount:function(){var t=this.header.length,e=this.pageSize,a=Math.floor(t/e);return t%e>0&&(a+=1),a},paginatedData:function(){var t=this.pageNum*this.pageSize,e=t+this.pageSize;return this.header.slice(t,e)}}}),r=n,l=a("2877"),c=a("6544"),o=a.n(c),d=a("8336"),p=a("1f4f"),u=a("2fa4"),h=Object(l["a"])(r,s,i,!1,null,"00e7391c",null);e["default"]=h.exports;o()(h,{VBtn:d["a"],VSimpleTable:p["a"],VSpacer:u["a"]})},"1f4f":function(t,e,a){"use strict";var s=a("5530"),i=(a("a9e3"),a("8b37"),a("80d2")),n=a("7560"),r=a("58df");e["a"]=Object(r["a"])(n["a"]).extend({name:"v-simple-table",props:{dense:Boolean,fixedHeader:Boolean,height:[Number,String]},computed:{classes:function(){return Object(s["a"])({"v-data-table--dense":this.dense,"v-data-table--fixed-height":!!this.height&&!this.fixedHeader,"v-data-table--fixed-header":this.fixedHeader,"v-data-table--has-top":!!this.$slots.top,"v-data-table--has-bottom":!!this.$slots.bottom},this.themeClasses)}},methods:{genWrapper:function(){return this.$slots.wrapper||this.$createElement("div",{staticClass:"v-data-table__wrapper",style:{height:Object(i["d"])(this.height)}},[this.$createElement("table",this.$slots.default)])}},render:function(t){return t("div",{staticClass:"v-data-table",class:this.classes},[this.$slots.top,this.genWrapper(),this.$slots.bottom])}})},"2fa4":function(t,e,a){"use strict";a("20f6");var s=a("80d2");e["a"]=Object(s["e"])("spacer","div","v-spacer")},"8b37":function(t,e,a){}}]);
//# sourceMappingURL=chunk-3b2cb03e.7314d954.js.map