<template>
    <div>
        <el-container style="background-color: white;">
            <el-menu
                    :default-active="$route.path"
                    :router="true"
                    class="el-menu-vertical-demo"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    style="min-height: 800px;"
                    v-bind:class="{ sideBarMaxWid: isActive, sideBarMinWid: !(isActive) }"
                    :collapse="isCollapse"
                    background-color="#545c64">

                <el-button type="info" plain style="background-color: rgb(84,92,100);border:0px;"></el-button>

                <el-menu-item index="/exp/expandList" :route="{path: '/exp/expandList'}">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">项目列表</span>
                </el-menu-item>
                <!--<el-menu-item index="/exp/expandCreate" :route="{path: '/exp/expandCreate'}">-->
                    <!--<i class="el-icon-edit-outline"></i>-->
                    <!--<span slot="title">项目新增</span>-->
                <!--</el-menu-item>-->
                <el-menu-item index="/exp/expandFollowupList" :route="{path: '/exp/expandFollowupList'}">
                    <i class="el-icon-document"></i>
                    <span slot="title">项目跟进列表</span>
                </el-menu-item>
                <el-menu-item index="/exp/expandTodayTask" :route="{path: '/exp/expandTodayTask'}">
                    <i class="el-icon-document"></i>
                    <span slot="title">拓展日常工作</span>
                </el-menu-item>
            </el-menu>

            <el-container v-bind:class="{ contentMaxWid: isActive, contentMinWid: !(isActive) }" class="pageChild">
                <el-main>
                    <router-view></router-view>
                </el-main>
                <el-footer>
                    <p class="bm_txt1">
                    ©2018 &ensp;  环颂(上海)供应链管理有限公司 &ensp; 版权所有&emsp;
                    遇见问题联系邮箱：hr@kufangwuyou.com &ensp; 联系电话：400-820-2058
                    </p>
                </el-footer>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        created: function () {
            var vm = this;

            // 权限
            vm.showExpandCreateBtn = vm.$flagMenuStore.judgeMenu("expandCreateBtn");

            if (!vm.showExpandCreateBtn) {
                window.history.back();
            }
        },
        data() {
            return {
                isActive: true,
                activeNames: ['1'],
                isCollapse: false,

                // 权限
                showExpandCreateBtn: false,
            };
        },
        methods: {
            handleRadio(val){
                //"单选的值"+val
            },
            handleOpen(key, keyPath) {
                //查看值key, keyPath
            },
            handleClose(key, keyPath) {
                //查看值key, keyPath
            }
        }
    }
</script>

<style>

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