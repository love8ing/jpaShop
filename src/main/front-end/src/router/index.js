import Vue from "vue";
import VueRouter from "vue-router";
import NotFound from "@/components/NotFound";


import login from "@/components/login/login";
import loginFail from "@/components/login/loginFail";

import createMemberForm from "@/components/members/CreateMemberForm";
import UpdateMemberForm from "@/components/members/UpdateMemberForm";
import memberList from "@/components/members/memberList";

import home_admin from "@/components/home_admin";
import home from "@/components/home";

import createItemForm from "@/components/items/createItemForm";
import itemList from "@/components/items/itemList";
import updateItemForm from "@/components/items/updateItemForm";

import orderForm from "@/components/order/orderForm";
import orderList from "@/components/order/orderList";




Vue.use(VueRouter); // 뷰 어플리케이션에 라우터 플러그인을 추가한다.

// 라우터 객체를 생성
export default new VueRouter({
    mode: "history",
    routes: [
        {
            path: "/",
            name: "login",
            component: login
        },
        {
            path: "/loginFail",
            name: "loginFail",
            component: loginFail
        },
        {
            path: "/createMemberForm",
            name: "createMemberForm",
            component: createMemberForm
        },
        {
            path: "/UpdateMemberForm",
            name: "UpdateMemberForm",
            component: UpdateMemberForm
        },
        {
            path: "/memberList",
            name: "memberList",
            component: memberList
        },
        {
            path: "/home",
            name: "home",
            component: home
        },
        {
            path: "/home_admin",
            name: "home_admin",
            component: home_admin
        },
        {
            path: "/createItemForm",
            name: "createItemForm",
            component: createItemForm
        },
        {
            path: "/itemList",
            name: "itemList",
            component: itemList
        },
        {
            path: "/updateItemForm",
            name: "updateItemForm",
            component: updateItemForm
        },

        {
            path: "/orderForm",
            name: "orderForm",
            component: orderForm
        },
        {
            path: "/orderList",
            name: "orderList",
            component: orderList
        },
        {
            path: "*",
            name: "NotFound",
            component: NotFound
        }
    ]
});
