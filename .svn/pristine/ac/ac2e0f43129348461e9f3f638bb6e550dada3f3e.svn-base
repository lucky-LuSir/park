<template>
    <div>
        <el-container style="background-color: white;">

            <el-menu
                    :router="true"
                    :default-active="$route.path"
                    class="el-menu-vertical-demo"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    style="min-height: 800px;"
                    v-bind:class="{ sideBarMaxWid: isActive, sideBarMinWid: !(isActive) }"
                    :collapse="isCollapse"
                    background-color="#545c64">

                <el-button type="info" plain style="background-color: rgb(84,92,100);border:0px;"></el-button>

                <el-menu-item v-if="showContractList" index="/contr/contractList" :route="{path: '/contr/contractList'}">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">入驻企业列表</span>
                </el-menu-item>
                <el-menu-item v-if="showFirstVerify" index="/contr/firstVerifyList" :route="{path: '/contr/firstVerifyList'}">
                    <i class="el-icon-news"></i>
                    <span slot="title">一审审核</span>
                </el-menu-item>
                <el-menu-item v-if="showSecondVerify" index="/contr/secondVerifyList" :route="{path: '/contr/secondVerifyList'}">
                    <i class="el-icon-news"></i>
                    <span slot="title">二审审核</span>
                </el-menu-item>
                <!--<el-menu-item v-if="showAddTenantBtn" index="/contr/contractCreate" :route="{path: '/contr/contractCreate'}">-->
                    <!--<i class="el-icon-edit-outline"></i>-->
                    <!--<span slot="title">入驻办理</span>-->
                <!--</el-menu-item>-->
                <el-menu-item v-if="showAddTenantBtn" index="/contr/conCommunicationLog" :route="{path: '/contr/conCommunicationLog'}">
                    <i class="el-icon-date"></i>
                    <span slot="title">入驻企业沟通日志</span>
                </el-menu-item>
            </el-menu>
            <el-container
                    class="pageChild"
                    v-bind:class="{ contentMaxWid: isActive, contentMinWid: !(isActive) }">
                <el-main>
                    <router-view></router-view>
                </el-main>
                <el-footer><p class="bm_txt1">
                    ©2018 &ensp;  环颂(上海)供应链管理有限公司 &ensp;版权所有&emsp;
                    遇见问题联系邮箱：hr@kufangwuyou.com &ensp; 联系电话：400-820-2058
                </p></el-footer>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                isActive: true,
                activeNames: ['1'],
                isCollapse: false,

                // 权限
                showAddTenantBtn: false,// 合同新增
                showContractList: false,// 合同列表
                showFirstVerify: false,// 一审审核
                showSecondVerify: false,// 二审审核
            };
        },
        created: function () {
            let vm = this;
            vm.showContractList = vm.$flagMenuStore.judgeMenu("contractList");
            vm.showAddTenantBtn = vm.$flagMenuStore.judgeMenu("addTenantBtn");
            vm.showFirstVerify = vm.$flagMenuStore.judgeMenu("firstVerifyList");
            vm.showSecondVerify = vm.$flagMenuStore.judgeMenu("secondVerifyList");
        },
        methods: {
            handleOpen(key, keyPath) {
                //查看值key, keyPath
            },
            handleClose(key, keyPath) {
                //查看值key, keyPath
            }
        },
    }
</script>

<style>
    .el-footer {
        background-color: rgb(132, 186, 178);
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: white;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: rgb(242, 242, 242);
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .topbar {
        background-color: #E69954;

    }

    .welcome {
        width: 200px;
        float: right;
    }

    .el-menu-vertical-demo:not(.el-menu--collapse) {
        min-height: 400px;
    }

    body {
        overflow: hidden;
        overflow-x: hidden;
    }
</style>

<style scoped>
    /*子内容页面区域样式*/
    .pageChild {
        height: 100vh;
    }

    .sideBarMaxWid {
        width: 200px;
    }

    .sideBarMinWid {
        width: 55px;
    }

    .contentMaxWid {
        width: 90%;
    }

    .contentMinWid {
        width: 97%;
    }
</style>