<template>
    <div>
        <el-container style="background-color: white;">
            <el-menu :default-active="$route.path"
                      :router="true"
                      class="el-menu-vertical-demo"
                      text-color="#fff"
                      active-text-color="#ffd04b"
                      style="min-height: 800px;"
                      v-bind:class="{ sideBarMaxWid: isActive, sideBarMinWid: !(isActive) }"
                      :collapse="isCollapse"
                      background-color="#545c64"
                     :default-openeds="openeds">

                <el-button type="info" plain style="background-color: rgb(84,92,100);border:0px;"></el-button>

                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-menu"></i>
                        <span>意向客户管理</span>
                    </template>
                    <el-menu-item-group style="margin-top: -13px;">
                        <el-menu-item index="/ati/customerList" :route="{path: '/ati/customerList'}" v-if="showCustomerList">
                            <i class="el-icon-tickets"></i>
                            <span slot="title">客户列表</span>
                        </el-menu-item>
                        <!--<el-menu-item index="/ati/customerCreate" :route="{path: '/ati/customerCreate'}">-->
                            <!--<i class="el-icon-edit-outline"></i>-->
                            <!--<span slot="title">客户新增</span>-->
                        <!--</el-menu-item>-->
                    </el-menu-item-group>
                </el-submenu>


                <el-submenu index="2">
                    <template slot="title">
                        <i class="el-icon-menu"></i>
                        <span>渠道管理</span>
                    </template>
                    <el-menu-item-group style="margin-top: -13px;">
                        <el-menu-item index="/ati/followupList" :route="{path: '/ati/followupList'}">
                            <i class="el-icon-time"></i>
                            <span slot="title">跟进列表</span>
                        </el-menu-item>
                        <el-menu-item index="/ati/channelList" :route="{path: '/ati/channelList'}">
                            <i class="el-icon-tickets"></i>
                            <span slot="title">渠道列表</span>
                        </el-menu-item>
                        <el-menu-item index="/ati/channelFollowup" :route="{path: '/ati/channelFollowup'}">
                            <i class="el-icon-date"></i>
                            <span slot="title">渠道跟进</span>
                        </el-menu-item>
                        <el-menu-item index="/ati/channelCpy" :route="{path: '/ati/channelCpy'}">
                            <i class="el-icon-document"></i>
                            <span slot="title">单位列表</span>
                        </el-menu-item>
                    </el-menu-item-group>
                </el-submenu>


                <el-submenu index="3">
                    <template slot="title">
                        <i class="el-icon-menu"></i>
                        <span>招商日志</span>
                    </template>
                    <el-menu-item-group style="margin-top: -13px;">
                        <el-menu-item index="/ati/taskList" :route="{path: '/ati/taskList'}" v-if="showAtiTaskList">
                            <i class="el-icon-news"></i>
                            <span slot="title">招商日常</span>
                        </el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
            </el-menu>

            <el-container class="pageChild" v-bind:class="{ contentMaxWid: isActive, contentMinWid: !(isActive) }">
                <el-main>
                    <router-view></router-view>
                </el-main>
                <el-footer><p class="bm_txt1">
                    ©2018 &ensp;  环颂(上海)供应链管理有限公司 &ensp; 版权所有&emsp;
                    遇见问题联系邮箱：hr@kufangwuyou.com &ensp; 联系电话：400-820-2058
                </p></el-footer>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        created: function () {
            var vm = this;

            //菜单和按钮权限控制
            vm.showCustomerList = vm.$flagMenuStore.judgeMenu("customerList");
            vm.showAtiTaskList=vm.$flagMenuStore.judgeMenu("atiTaskList");
        },
        data() {
            return {
                isActive: true,
                showCustomerList: false,
                showAtiTaskList: false,
                activeNames: ['1'],
                isCollapse: false,
                openeds: ['1','2','3']
            };
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