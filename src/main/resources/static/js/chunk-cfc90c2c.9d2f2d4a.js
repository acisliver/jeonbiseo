(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-cfc90c2c"],{"1f4f":function(t,e,a){"use strict";var s=a("5530"),i=(a("a9e3"),a("8b37"),a("80d2")),r=a("7560"),n=a("58df");e["a"]=Object(n["a"])(r["a"]).extend({name:"v-simple-table",props:{dense:Boolean,fixedHeader:Boolean,height:[Number,String]},computed:{classes:function(){return Object(s["a"])({"v-data-table--dense":this.dense,"v-data-table--fixed-height":!!this.height&&!this.fixedHeader,"v-data-table--fixed-header":this.fixedHeader,"v-data-table--has-top":!!this.$slots.top,"v-data-table--has-bottom":!!this.$slots.bottom},this.themeClasses)}},methods:{genWrapper:function(){return this.$slots.wrapper||this.$createElement("div",{staticClass:"v-data-table__wrapper",style:{height:Object(i["d"])(this.height)}},[this.$createElement("table",this.$slots.default)])}},render:function(t){return t("div",{staticClass:"v-data-table",class:this.classes},[this.$slots.top,this.genWrapper(),this.$slots.bottom])}})},"8b37":function(t,e,a){},c290:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-simple-table",{scopedSlots:t._u([{key:"default",fn:function(){return[a("thead",[a("tr",[a("th",{staticClass:"text-left"},[t._v(" 제목 ")]),a("th",{staticClass:"text-right"},[t._v(" 작성자 ")])])]),a("tbody",t._l(t.boardHeader,(function(e){return a("tr",{key:e.title},[a("td",{staticClass:"text-left"},[t._v(t._s(e.title))]),a("td",{staticClass:"text-right"},[t._v(t._s(e.writer))])])})),0)]},proxy:!0}])})},i=[],r=a("5530"),n=a("2f62"),d={name:"ViewBoard",data:function(){return{}},computed:Object(r["a"])({},Object(n["c"])(["boardHeader"])),methods:Object(r["a"])({},Object(n["b"])(["getBoardHeader"])),created:function(){this.getBoardHeader()}},l=d,o=a("2877"),c=a("6544"),h=a.n(c),b=a("1f4f"),f=Object(o["a"])(l,s,i,!1,null,"1c8d5f35",null);e["default"]=f.exports;h()(f,{VSimpleTable:b["a"]})}}]);
//# sourceMappingURL=chunk-cfc90c2c.9d2f2d4a.js.map