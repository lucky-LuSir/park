<template>
    <div class="root">
        <el-form :model="loginObj" :rules="rules" ref="loginObj">
            <div class="welcome-title-div">
                <!--<img class="login_logo" src="./img/logo_color.png" alt="">-->
            </div>

            <div class=" login-box-root-div">
                <div class="grid-content">
                    <div class="loginTitle clearfix">
                        <div class="welcome-title">
                            <p>欢迎使用</p>POMS智慧园区管理系统
                        </div>
                    </div>
                    <!-- <el-form-item class="content_item" prop="cpyMark">
                        <el-input placeholder="请输入公司代号" v-model="loginObj.cpyMark"
                        @keyup.enter.native="loginSys('loginObj')" class="input-text" clearable prefix-icon="el-icon-diy-gongsi">
                        </el-input>
                    </el-form-item> -->
                    <el-form-item class="content_item_one content_item" prop="keyword">
                        <el-input placeholder="请输入手机号" v-model="loginObj.keyword" class="input-text" clearable prefix-icon="el-icon-diy-yonghuming">
                        </el-input>
                    </el-form-item>

                    <el-form-item class="content_item" prop="password">
                        <el-input v-model="secure" type="password" class="input-text" @keyup.enter.native="loginSys('loginObj')" placeholder="请输入密码" clearable prefix-icon="el-icon-diy-mima">
                        </el-input>
                    </el-form-item>

                    <el-button class="content_item content_item_btn" type="primary" @click="loginSys('loginObj')">
                        <span style="font-family: Arvo">登&nbsp;&nbsp;&nbsp;&nbsp;录</span>
                    </el-button>
                </div>
            </div>

            <div class="company-info-div">
                <p class="company-info-font">© 2014-2020 yuanquwuyou All right reserved. 沪ICP备110119120</p>
                <p class="company-info-link">
                    <a target="_blank" class="kufang" href="http://www.kufangwuyou.com">库房无忧</a>
                    <a target="_blank" class="huanping" href="http://www.huanjingwuyou.com">环境无忧</a>
                </p>
            </div>
        </el-form>
    </div>
</template>

<script>
    export default {
        created: function() {
            //登陆状态下不能访问login页面
            if (this.$cookieStore.getCookie("gn_request_token")) {
                this.toMainPage();
            }
        },
        data() {
            return {
                secure: "",
                userObj: {},
                loginObj: {
                    cpyMark: "",
                    keyword: "",
                    password: ""
                },
                rules: {
                    // cpyMark: [
                    //     { required: true, message: '请输入公司代号', trigger: 'blur' }
                    // ],
                    keyword: [{
                        required: true,
                        message: "请输入手机号",
                        trigger: "blur"
                    }],
                    password: [{
                        required: true,
                        message: "请输入密码",
                        trigger: "blur"
                    }]
                }
            };
        },
        methods: {
            //忘记密码
            forgetPwd() {
                this.$router.push({
                    name: "forgetPwd"
                });
            },
            //跳转到主页
            toMainPage() {
                this.$router.push({
                    name: "home"
                });
            },
            //登陆
            loginSys(formName) {
                let vm = this;
                vm.loginObj.password = this.$md5(vm.secure);
                
                let obj = {
                    entity: vm.loginObj,
                    keyword: vm.loginObj.keyword
                };

                if (vm.loginObj) {
                    this.$refs[formName].validate(valid => {
                        if (valid) {
                            let options = {
                                method: "POST",
                                data: obj,
                                url: "user/login"
                            };

                            this.$ajax(options)
                                .then(function(response) {
                                    if (response) {
                                        //获取用户信息
                                        vm.userObj.userName = response.data.result.userName;
                                        vm.userObj.userCode = response.data.result.userCode;
                                        vm.userObj.postCode = response.data.result.postCode;
                                        vm.userObj.empCode = response.data.result.empCode;
                                        vm.userObj.deptCode = response.data.result.deptCode;
                                        vm.userObj.cpyCode = response.data.result.cpyCode;
                                        vm.userObj.cpyName = response.data.result.cpyName;
                                        vm.userObj.cpyImg = response.data.result.cpyImg;

                                        //获取后台返回的token
                                        let token = response.data.result.token;

                                        //将token存入Cookie中
                                        vm.$cookieStore.addCookie(
                                            "gn_request_token",
                                            JSON.stringify(token),
                                            0
                                        );
                                        let tok = vm.$cookieStore.getCookie("gn_request_token");

                                        //将userName，userCode，postCode,empCode存入cookie
                                        vm.$cookieStore.addCookie("userName", vm.userObj.userName, 0);
                                        vm.$cookieStore.addCookie("userCode", vm.userObj.userCode, 0);
                                        vm.$cookieStore.addCookie("postCode", vm.userObj.postCode, 0);
                                        vm.$cookieStore.addCookie("empCode", vm.userObj.empCode, 0);
                                        vm.$cookieStore.addCookie("deptCode", vm.userObj.deptCode, 0);
                                        vm.$cookieStore.addCookie("cpyCode", vm.userObj.cpyCode, 0);
                                        vm.$cookieStore.addCookie("cpyName", vm.userObj.cpyName, 0);
                                        vm.$cookieStore.addCookie("cpyImg", vm.userObj.cpyImg, 0);

                                        let menuTreeList = response.data.result.menuTreeList;
                                        let menuListStr = JSON.stringify(menuTreeList); //转化为JSON字符串
                                        sessionStorage.setItem("menuTreeList", menuListStr); //返回{"a":1,"b":2}

                                        // 登陆成功跳转到首页
                                        vm.toMainPage();
                                    }
                                })
                                .catch(function(error) {
                                    vm.$alert("页面：登录方法异常", "系统异常", {
                                        confirmButtonText: "确定",
                                        callback: action => {}
                                    });
                                });
                        }
                    });
                }
            }
        }
    };
</script>

<style scoped>
    @media all and (-webkit-min-device-pixel-ratio: 0) and (min-resolution: 0.001dpcm) {
        .loginTitle {
            position: absolute;
            left: -10%;
            z-index: 9999;
            top: -42%;
            font-size: 40px;
            background-image: -webkit-linear-gradient(left,
                    #0466b4,
                    #e6d205 25%,
                    #147b96 50%,
                    #e6d205 75%,
                    #0466b4);
            -webkit-text-fill-color: transparent;
            -webkit-background-clip: text;
            -webkit-background-size: 200% 100%;
            -webkit-animation: masked-animation 4s infinite linear;
        }
    }

    @-webkit-keyframes masked-animation {
        0% {
            background-position: 0 0;
        }

        100% {
            background-position: -100% 0;
        }
    }

    @media screen and (max-device-width: 1600px) {
        .grid-content {
            width: 310px !important;
            height: 300px !important;
            top: 55% !important;
        }

        .loginTitle {
            position: absolute !important;
            left: -25% !important;
            z-index: 9999 !important;
            top: -45% !important;
            font-size: 33px !important;
        }

        .welcome-title {
            font-size: inherit !important;
        }
    }

    .content_item {
        width: 80%;
        margin: 22px auto 0;
    }

    .content_item_one {
        margin: 11% auto 0;
    }

    .content_item_btn {
        margin: 33px auto 0;
    }

    .root {
        text-align: center;
        background-size: cover;
        position: absolute;
        top: 0px;
        bottom: 0px;
        left: 0px;
        width: 100%;
        height: 100%;
        /* background-image: url("./img/login2.jpg"); */
        /* background-attachment:fixed; */
    }

    /* ************************************************************* */
    /* 欢迎栏 div */
    .welcome-title-div {
        height: 8vh;
        text-align: left;
        display: flex;
        align-items: center;
        /* border: solid 1px red; */
    }

    .login_logo {
        height: 65%;
        margin-left: 2%;
    }

    /* 欢迎标题 */
    .welcome-title {
        width: 460px;
        align-items: center;
        /* font-size: 35px; */
        font-size: 40px;
        font-weight: bold;
        color: white;
        /* display: inline-block; */
        /* height: 100%; */
        /* line-height: 50px; */
        /* line-height: 100%; */
        /* line-height: */
        /* display: flex; */
        float: right;
        /* line-height: 120px; */
    }

    /* ************************************************************* */
    /* 登陆框 */
    .login-box-root-div {
        background-image: url(./img/login2.jpg);
        background-size: 100% 100%;
        height: 83vh;
        background-repeat: no-repeat;
        background-clip: content-box;
        background-origin: content-box;
        position: relative;
    }

    .grid-content {
        position: absolute;
        right: 15%;
        top: 47%;
        transform: translateY(-50%);
        width: 380px;
        height: 370px;
        box-sizing: border-box;
        text-align: center;
        margin: auto;
        background-color: rgba(49, 88, 116, 0.6);
        margin-top: 25px;
        padding-top: 20px;
    }

    /* 输入框 */
    .input-text {
        /* width: 300px; */
        width: 100%;
        margin-top: 5px;
    }

    /* 忘记密码 注册 */
    .res-pwd-div {
        /* border: solid 1px white; */
        margin-top: 5px;
    }

    /* 备案信息 */
    .company-info-div {
        /* border: solid 1px; */
        /* margin-top: 17%; */
        height: 9vh;
        line-height: 8vh;
    }

    /* 备案信息 文字 */
    .company-info-font {
        color: #666;
        line-height: 8vh;
        font-size: 16px;
        float: left;
        margin-left: 12%;
    }

    .company-info-link {
        float: right;
        margin-right: 12%;
        font-size: 16px;
    }

    .company-info-link a {
        padding: 0 15px;
        text-decoration: none;
        color: #666;
        border-right: 1px solid #ccc;
    }

    .company-info-link a:last-child {
        border-right: 0;
    }

    .company-info-link a:hover {
        color: red;
        font-size: 18px;
    }
</style>