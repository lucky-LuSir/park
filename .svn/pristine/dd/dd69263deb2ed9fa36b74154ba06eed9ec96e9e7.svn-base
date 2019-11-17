import Vue from "vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import App from "./App.vue";
import VueRouter from "vue-router";
import routes from "./routes";
import {addCookie, getCookie, delCookie} from "./common/common/js/cookie.js";
import {judgeMenu} from "./common/common/js/flagMenu.js";
import store from "./store.js"; // vuex
import { mapState } from 'vuex'
import BASE_URL from "../public/park_BASE_URL"; //配置全局URL
import echarts from "echarts";
import md5 from "js-md5";
import {Loading} from "element-ui";
import axios from "axios";
import "./common/assets/icon/iconfont.css";
import {usedQueryDept} from "./common/common/js/deptQuery";
import '@/common/common/css/normalize.css'
import '@/common/common/css/common.css'
import '@/common/common/css/editGlobal.css'

Vue.prototype.$md5 = md5;
Vue.prototype.$ajax = axios;
axios.defaults.baseURL = BASE_URL.BASE_URL; //设置全局URL
axios.defaults.headers.post["Content-Type"] = "application/json";

/*------------------------------------ Loading加载 ------------------------------------*/
let loading;
function startLoading() {
    loading = Loading.service({
        lock: true,
        text: "拼命加载中...",
        target: document.querySelector(".mainBox")
    });
}

function endLoading() {
    loading.close(); // 关闭 loading
}

let needLoadingRequestCount = 0;
export function showFullScreenLoading() {
    if (needLoadingRequestCount === 0) {
        startLoading();
    }
    needLoadingRequestCount++;
}

export function tryHideFullScreenLoading() {
    if (needLoadingRequestCount <= 0) return;
    needLoadingRequestCount--;
    if (needLoadingRequestCount === 0) {
        endLoading();
    }
}

/*------------------------------------ Loading加载 ------------------------------------*/

/*---------------------------------联调环境,将token放入header中start---------------------------------*/
axios.interceptors.request.use(
    config => {
        let token = Vue.prototype.$cookieStore.getCookie("gn_request_token");
        if (token == null) {
            token = "";
        }
        config.headers.gn_request_token = token;
        showFullScreenLoading();
        return config;
    },
    error => {
        console.log("error", error);
    }
);
/*---------------------------------联调环境,将token放入header中end---------------------------------*/

/*---------------------------------http response 拦截器start---------------------------------*/
axios.interceptors.response.use(
    response => {
        if (!response.data.isSuccess) {
            park.$alert(response.data.message, "系统异常", {
                confirmButtonText: "确定",
                callback: action => {
                }
            });
            tryHideFullScreenLoading();
            return null;
        } else {
            tryHideFullScreenLoading();
            return response;
        }
    },
    error => {
        park.$alert("服务器发生未知异常，请联系管理员", "系统异常", {
            confirmButtonText: "确定",
            callback: action => {
            }
        });
    }
);
/*---------------------------------http response 拦截器end---------------------------------*/

Vue.use(ElementUI);
Vue.use(VueRouter);

/*---------------------------------封装cookie方法---------------------------------*/
//添加使用cookie
Vue.prototype.$cookieStore = {
    addCookie,
    getCookie,
    delCookie
};
/*---------------------------------封装cookie方法---------------------------------*/

/*---------------------------------判断权限全局方法---------------------------------*/
Vue.prototype.$flagMenuStore = {judgeMenu};
/*---------------------------------判断权限全局方法---------------------------------*/

/*---------------------------------添加全局变量,包括baseurl---------------------------------*/
//添加全局变量,包括baseurl
Vue.prototype.parkGlobal = BASE_URL;
Vue.prototype.parkBaseUrl = BASE_URL.BASE_URL;
//添加全局变量,包括baseurl  end
/*---------------------------------添加全局变量,包括baseurl---------------------------------*/

Vue.prototype.$echarts = echarts;

/*-------------------------------配置全局日期start-----------------------------------------*/
Date.prototype.format = function (format) {
    let date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(
            RegExp.$1,
            (this.getFullYear() + "").substr(4 - RegExp.$1.length)
        );
    }
    for (let k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(
                RegExp.$1,
                RegExp.$1.length == 1
                    ? date[k]
                    : ("00" + date[k]).substr(("" + date[k]).length)
            );
        }
    }
    return format;
};
Vue.filter("time", function (value) {
    if (value) {
        let newDate = new Date(parseInt(value));
        return newDate.format("yyyy-MM-dd hh:mm:ss");
    }
});
Vue.filter("timeDate", function (value) {
    if (value) {
        let newDate = new Date(parseInt(value));
        return newDate.format("yyyy-MM-dd");
    }
});
Vue.filter("monthDate", function (value) {
    if (value) {
        let newDate = new Date(parseInt(value));
        return newDate.format("yyyy-MM");
    }
});

Vue.filter("splitName", function (value) {
    if (value) {
        return value.split("\n")[0];
    }
});

// 时间格式化
Vue.prototype.format_date = function (DateFor) {
    //全局函数1
    let year = DateFor.getFullYear();
    let month = DateFor.getMonth() + 1;
    let day = DateFor.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    let tmp = year + "-" + month + "-" + day;
    return tmp;
};

// 获取月初，获取 当前月+gap 的月初(若gap为-1，则获取上月初，若gap为1，则获取下月初，
Vue.prototype.get_month_first = function (gap) {
    let date1 = new Date();
    // 获取间隔月
    let gapMonth = date1.getMonth() + gap;
    // 获取间隔月月初
    let gapMonthFirstDay = new Date(date1.getFullYear(), gapMonth, 1);
    let firstDay = this.format_date(gapMonthFirstDay);
    return firstDay;
};

// 获取月末
Vue.prototype.get_month_last = function (gap) {
    // gap = gap + 1
    let date1 = new Date();
    // 获取下个月月初
    let gapMonth = date1.getMonth();
    gapMonth = gapMonth + 1 + gap;

    let NextMonthFirstDay = new Date(date1.getFullYear(), gapMonth, 1);
    NextMonthFirstDay = NextMonthFirstDay.getTime() - 1000 * 60 * 60 * 24;
    let temp = new Date();
    temp.setTime(NextMonthFirstDay);
    let lastDay = this.format_date(temp);
    return lastDay;
};

/*----------------------------设置数值类型默认为0----------------------------------------*/
Vue.filter("initNum", function (value) {
    if (value) {
        return value;
    } else {
        return 0;
    }
});

/*-------------------------------配置全局日期end-----------------------------------------*/

/*----------------------------设置数值类型默认为0 end----------------------------------------*/

/*-------------------------------配置数字转换-----------------------------------------*/
Vue.filter("num", function (value) {
    let N = ["零", "一", "二", "三", "四", "五", "六", "七", "八", "九"];
    let str = value.toString();
    let len = value.toString().length;
    let C_Num = [];
    for (let i = 0; i < len; i++) {
        C_Num.push(N[str.charAt(i)]);
    }
    return C_Num.join("");
});

/*手机号码校验*/
let judgePhone = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
Vue.prototype.$MOBILE_PATTERN = judgePhone;
//座机验证
let landlinePhone = /^0\d{2,3}-\d{7,8}$/;
Vue.prototype.$LANDLINE_PHONE = landlinePhone;
/*-------------------------------配置数字转换end-----------------------------------------*/

const router = new VueRouter({
    mode: "history",
    routes
});

/*------------------------------- 获取可检索的部门------------------------------------*/

Vue.prototype.$usedQueryDept = {usedQueryDept};

/*------------------------------- 获取可检索的部门结束---------------------------------*/

/*-------------------------------定义导航守卫-----------------------------------------*/
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
        //判断该路由是否需要登录权限
        let cookie = Vue.prototype.$cookieStore.getCookie("gn_request_token");
        // console.log("查看cookie::"+cookie);
        if (cookie) {
            //cookie不为null,则放行
            next();
        } else {
            next({
                path: "/login",
                query: {redirect: to.fullPath} //将跳转的路由path作为参数，登录成功后跳转到该路由
            });
        }
    } else {
        next();
    }
});
/*-------------------------------定义导航守卫-----------------------------------------*/


let park = new Vue({
    el: "#app",
    store, //vuex第一步引入import,就能用了
    router,
    computed: mapState({
        tableHeight: `calc(100vh - 197px)`,
        notPagingTableHeight: `calc(100vh - 157px)`
    }),
    render: h => h(App)
});

Vue.prototype.table_body = function (table__body, num) {
    if (num == 0) {
        var height = parseInt(table__body.style.height);
        table__body.style.height = height - 64 + "px";
    }
};
