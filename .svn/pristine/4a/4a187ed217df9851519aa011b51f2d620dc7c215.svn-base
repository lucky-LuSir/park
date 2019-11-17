<template>
    <div>
        <el-container style="background-color: white;" class="heightAll">

            <el-menu
                    :router="true"
                    :default-active="$route.path"
                    class="el-menu-vertical-demo"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    style="min-height: 800px;max-width: 15%;"
                    v-bind:class="{ sideBarMaxWid: isActive, sideBarMinWid: !(isActive) }"
                    :collapse="isCollapse"
                    background-color="#545c64">

                <el-button type="info" plain style="background-color: rgb(84,92,100);border:0px;"></el-button>
                <el-menu-item index="/bi/gross" :route="{path: '/bi/gross'}" v-if="showGrossList">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">毛利率报表</span>
                </el-menu-item>
                <el-menu-item index="/bi/channelReport" :route="{path: '/bi/channelReport'}" v-if="showChannelReport">
                    <i class="el-icon-star-off"></i>
                    <span slot="title">渠道报表</span>
                </el-menu-item>
                <el-menu-item index="/bi/merchantsReport" :route="{path: '/bi/merchantsReport'}" v-if="showChannelReport">
                    <i class="el-icon-bell"></i>
                    <span slot="title">招商数据报表</span>
                </el-menu-item>
                <el-menu-item index="/bi/accountReceivableReport" :route="{path: '/bi/accountReceivableReport'}" v-if="showChannelReport">
                    <i class="el-icon-news"></i>
                    <span slot="title">应收租金报表</span>
                </el-menu-item>
            </el-menu>

            <el-container
                    v-bind:class="{ contentMaxWid: isActive, contentMinWid: !(isActive) }"
                    class="pageChild">
                <el-main>
                    <router-view></router-view>
                </el-main>
                <!--<el-footer><p class="bm_txt1">-->
                    <!--©2018 &ensp;  环颂(上海)供应链管理有限公司 &ensp; 版权所有-->
                    <!--&emsp;-->
                    <!--遇见问题联系邮箱：hr@kufangwuyou.com &ensp; 联系电话：400-820-2058-->
                <!--</p></el-footer>-->
            </el-container>
        </el-container>
    </div>
</template>


<script>
    export default {
        created: function () {
            var vm = this;

            //菜单和按钮权限控制
            vm.showGrossList = vm.$flagMenuStore.judgeMenu("grossList");
            vm.showChannelReport = vm.$flagMenuStore.judgeMenu("channelReport");
        },
        data() {
            return {
                isActive: true,
                showGrossList: false,// 毛利率报表
                showChannelReport: false,// 渠道报表
                activeNames: ['1'],
                isCollapse: false
            };
        },
        methods: {
            //新增楼栋(测试,参数写死)
            buildingCreate(){
                this.$router.push({
                    name: 'buildingCreate',
                    query: {
                        pkCode: "180615b02228c95c6b45",
                    }
                });
            },


        }
    }
</script>


<style>
    .heightAll {
        background: #6cd1ff;
        height: 100%;
        margin: 0px;
        padding: 0px;
    }

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