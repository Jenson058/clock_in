import{d as D,r as c,c as L,b as t,w as n,l as o,F as U,B as F,E as C,g as y,o as J,_ as u,h as d,t as m,n as r,q as M,v as N,C as P}from"./index-Dy3wVuRg.js";import{J as q,P as z}from"./JensonTable-CJvPI8iK.js";import{a as V,R as g,b as h}from"./RoleApi-D6Oyjcry.js";import{_ as H}from"./JensonEditModel.vue_vue_type_script_setup_true_lang-DU8Ymk2r.js";import{u as j}from"./RoleStore-DameUVYJ.js";const Y=D({__name:"index",setup(G){const b=c([]),w=c([]),f=c(new z),s=c(!1),i=c(new h),S=F(),x=j();async function p(){let a=new V(f.value.page,f.value.pageSize);g.pageAll(a).then(e=>{var v,_;w.value=((v=e==null?void 0:e.data)==null?void 0:v.content)??[],f.value.total=((_=e==null?void 0:e.data)==null?void 0:_.totalElements)??0})}function R(){console.log("add"),i.value=new h,s.value=!0}function A(a){i.value={...a},s.value=!0}async function B(){let a={...i.value};g.edit(a).then(async()=>{C.success("保存成功"),s.value=!1,await p()})}async function T(a){x.setRoleVo(a),localStorage.setItem("roleId",a.id),await S.push("auth")}async function $(){let a=new V;a.idList=b.value.map(e=>e.id),g.delAll(a).then(async()=>{await p(),C.success("删除成功")})}async function E(a){g.del({id:a.id}).then(async()=>{await p(),C.success("删除成功")})}return(a,e)=>{const v=y("el-input"),_=y("el-form-item"),I=y("el-form");return J(),L(U,null,[t(q,{"column-list":o(P).getColumnList(o(h)),"table-data":w.value,onInitTable:p,"page-config":f.value,onHandleSelectionChange:e[0]||(e[0]=l=>b.value=l),column:"",index:"",view:"",refresh:"",selection:"",setting:""},{"header-add":n(()=>[t(u,{type:"primary",onClick:R},{default:n(()=>[d(m(o(r)("btn.add")),1)]),_:1}),M(t(u,{type:"danger",onClick:$},{default:n(()=>[d(m(o(r)("btn.deleteAll")),1)]),_:1},512),[[N,b.value.length>0]])]),controls:n(({row:l})=>[t(u,{type:"success",onClick:k=>A(l)},{default:n(()=>[d(m(o(r)("btn.edit")),1)]),_:2},1032,["onClick"]),t(u,{type:"warning",onClick:k=>T(l)},{default:n(()=>[d(m(o(r)("btn.role")),1)]),_:2},1032,["onClick"]),t(u,{type:"danger",onClick:k=>E(l)},{default:n(()=>[d(m(o(r)("btn.delete")),1)]),_:2},1032,["onClick"])]),_:1},8,["column-list","table-data","page-config"]),t(H,{modelValue:s.value,"onUpdate:modelValue":e[2]||(e[2]=l=>s.value=l),title:"用户",style:{width:"400px"},"submit-on-click":B},{default:n(()=>[t(I,null,{default:n(()=>[t(_,{label:"名称"},{default:n(()=>[t(v,{modelValue:i.value.name,"onUpdate:modelValue":e[1]||(e[1]=l=>i.value.name=l)},null,8,["modelValue"])]),_:1})]),_:1})]),_:1},8,["modelValue"])],64)}}});export{Y as default};
