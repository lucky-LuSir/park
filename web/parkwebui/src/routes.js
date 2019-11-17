import Vue from "vue";
import Router from "vue-router";

// import login from "./sys/views/login/login.vue";
const login = () =>
    import(/* webpackChunkName: "loading1" */ "./sys/views/login/login.vue");

// import forgetPwd from "./sys/views/login/forgetPwd.vue";
const forgetPwd = () =>
    import(
        /* webpackChunkName: "loading1" */ "./sys/views/login/forgetPwd.vue"
    );

// import mainPage from "./common/views/mainPage.vue";
const mainPage = () =>
    import(/* webpackChunkName: "loading1" */ "./common/views/mainPage.vue");

// import mainList from "./common/views/mainList.vue";
const mainList = () =>
    import(/* webpackChunkName: "loading1" */ "./common/views/mainList.vue");

// import back from "./common/views/back.vue";
const back = () =>
    import(/* webpackChunkName: "loading1" */ "./common/views/back.vue");

Vue.use(Router);

//配置导航守卫
var pageUrl = {
    requireAuth: true
};

let routes = [
    {
        path: "/",
        component: mainPage,
        meta: pageUrl,
        children: [
            {
                path: "home",
                name: "home",
                component: mainList,
                meta: pageUrl
            },
            { path: "back", name: "back", component: back, meta: pageUrl }
        ]
    },
    // 登陆
    {
        path: "/login",
        name: "login",
        component: login
    }
    // {
    //     path: "/forgetPwd",
    //     name: "forgetPwd",
    //     component: forgetPwd
    // }
];

export default routes;
