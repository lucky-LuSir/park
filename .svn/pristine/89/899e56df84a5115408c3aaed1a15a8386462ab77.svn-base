<template>
    <div><!--第0部分Root根标签-->


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
                <el-menu-item index="/hrm/cpyList" :route="{path: '/hrm/cpyList'}" v-if="showCompanyList">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">公司列表</span>
                </el-menu-item>

                <el-menu-item index="/hrm/deptList" :route="{path: '/hrm/deptList'}" v-if="showDeptList">
                    <i class="el-icon-news"></i>
                    <span slot="title">部门列表</span>
                </el-menu-item>

                <el-menu-item index="/hrm/postList" :route="{path: '/hrm/postList'}" v-if="showPostList">
                    <i class="el-icon-search"></i>
                    <span slot="title">岗位列表</span>
                </el-menu-item>

                <el-menu-item index="/hrm/postDivide" :route="{path: '/hrm/postDivide'}" v-if="showDividePostList">
                    <i class="el-icon-service"></i>
                    <span slot="title">岗位分配</span>
                </el-menu-item>

                <el-menu-item index="/hrm/roleList" :route="{path: '/hrm/roleList'}" v-if="showRoleList">
                    <i class="el-icon-document"></i>
                    <span slot="title">角色列表</span>
                </el-menu-item>

                <el-menu-item index="/hrm/roleDivide" :route="{path: '/hrm/roleDivide'}" v-if="showDivideRoleList">
                    <i class="el-icon-view"></i>
                    <span slot="title">角色分配</span>
                </el-menu-item>

                <el-menu-item index="/hrm/menuList" :route="{path: '/hrm/menuList'}" v-if="showMenuList">
                    <i class="el-icon-setting"></i>
                    <span slot="title">菜单列表</span>
                </el-menu-item>

                <el-menu-item index="/hrm/empList" :route="{path: '/hrm/empList'}" v-if="showEmployeeList">
                    <i class="el-icon-star-off"></i>
                    <span slot="title">人员列表</span>
                </el-menu-item>

                <el-menu-item index="/hrm/cpyAreaList" :route="{path: '/hrm/cpyAreaList'}" v-if="showAreaList">
                    <i class="el-icon-location-outline"></i>
                    <span slot="title">地区列表</span>
                </el-menu-item>

                <el-menu-item index="/hrm/userList" :route="{path: '/hrm/userList'}" v-if="showUserList">
                    <i class="el-icon-mobile-phone"></i>
                    <span slot="title">用户列表</span>
                </el-menu-item>
            </el-menu>

            <el-container class="pageChild"
                            v-bind:class="{ contentMaxWid: isActive, contentMinWid: !(isActive) }">
                <el-main>
                    <router-view></router-view>
                </el-main>
                <el-footer><p class="bm_txt1">
                    ©2018 &ensp;  环颂(上海)供应链管理有限公司 &ensp; 版权所有&emsp;
                    遇见问题联系邮箱：hr@kufangwuyou.com &ensp; 联系电话：400-820-2058
                </p></el-footer>
            </el-container>
        </el-container>
    </div><!--第0部分Root根标签end-->
</template>


<script>
    export default {
        created: function () {
            var vm = this;
            vm.showCompanyList = vm.$flagMenuStore.judgeMenu("companyList");

            vm.showDeptList = vm.$flagMenuStore.judgeMenu("deptList");

            vm.showPostList = vm.$flagMenuStore.judgeMenu("postList");
            vm.showDividePostList = vm.$flagMenuStore.judgeMenu("dividePostList");
            vm.showMenuList = vm.$flagMenuStore.judgeMenu("menuList");
            vm.showEmployeeList = vm.$flagMenuStore.judgeMenu("employeeList");

            vm.showCpyList = vm.$flagMenuStore.judgeMenu("cpyList");
            vm.showRoleList = vm.$flagMenuStore.judgeMenu("roleList");
            vm.showDivideRoleList = vm.$flagMenuStore.judgeMenu("divideRoleList");
            vm.showAreaList = vm.$flagMenuStore.judgeMenu("areaList");
            vm.showUserList = vm.$flagMenuStore.judgeMenu("userList");
        },
        data() {
            return {
                isActive: true,
                activeNames: ['1'],
                isCollapse: false,

                showCompanyList: false,

                showDeptList: false,
                showPostList: false,
                showDividePostList: false,
                showMenuList: false,
                showEmployeeList: false,

                showCpyList: false,
                showRoleList: false,
                showDivideRoleList: false,
                showAreaList: false,
                showUserList: false,
            };
        },
        methods: {
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