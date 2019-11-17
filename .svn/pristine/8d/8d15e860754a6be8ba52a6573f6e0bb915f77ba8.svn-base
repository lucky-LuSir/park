<template>
    <div >
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

                <el-button type="info"  plain style="background-color: rgb(84,92,100);border:0px;" ></el-button>

                <el-menu-item index="/valueAddedServices/convenientStore" :route="{path: '/valueAddedServices/convenientStore'}" v-show="showParkList">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">便利店</span>
                </el-menu-item>
                <el-menu-item index="/valueAddedServices/chargingPile" :route="{path: '/valueAddedServices/chargingPile'}">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">充电桩</span>
                </el-menu-item>
                <el-menu-item index="/valueAddedServices/advertisingSpace" :route="{path: '/valueAddedServices/advertisingSpace'}">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">广告位</span>
                </el-menu-item>
                <el-menu-item index="/valueAddedServices/decoration" :route="{path: '/valueAddedServices/decoration'}" v-show="showParkList">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">装修</span>
                </el-menu-item>
                <el-menu-item index="/valueAddedServices/eia" :route="{path: '/valueAddedServices/eia'}">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">环评</span>
                </el-menu-item>
                <el-menu-item index="/valueAddedServices/rentStaging" :route="{path: '/valueAddedServices/rentStaging'}">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">租金分期</span>
                </el-menu-item>
                <el-menu-item index="/valueAddedServices/corporateLoan" :route="{path: '/valueAddedServices/corporateLoan'}">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">企业贷款</span>
                </el-menu-item>
            </el-menu>

            <el-container
                    v-bind:class="{ contentMaxWid: isActive, contentMinWid: !(isActive) }"
                    class="pageChild">
                <el-main >
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        created:function() {
            var vm = {};
            vm = this;
            //菜单和按钮权限控制
            vm.showParkList=vm.$flagMenuStore.judgeMenu("parkList");
        },
        data() {
            return {
                isActive: true,
                showParkList:false,
                activeNames: ['1'],
                isCollapse: false
            };
        },
        methods: {
            handleOpen(key, keyPath) {
                //查看值key, keyPath
            },
            handleClose(key, keyPath) {
                //查看值key, keyPath
            },
            parkDetails(){
                this.$router.push({
                    name:'parkDetails',
                    query: {
                        pkCode: "180628e3375deffd8841"
                    }
                });
            },
            //新增楼栋(测试,参数写死)
            buildingCreate(){
                this.$router.push({
                    name:'buildingCreate',
                    query: {
                        pkCode: "180615b02228c95c6b45",
                    }
                });
            },
            //查看楼栋详情
            buildingDetails(){
                this.$router.push({
                    name:'buildingDetails',
                    query: {
                        pkCode: "180615b02228c95c6b45",
                        bdCode: "180615aa2f4518085545",
                    }
                });
            },
            //新增楼层(测试,参数写死)
            floorCreate() {
                this.$router.push({
                    name: 'floorCreate',
                    params: {
                        paramsObj: {
                            pkCode: "180615b02228c95c6b45",
                            pkName: "幸福大街",
                            bdCode: "180615aa2f4518085545",
                            bdName: "幸福大街园区C栋",
                            thisFloor:"1"
                        }
                    }
                });
            },
            //楼层详情(测试,参数写死)
            floorDetails() {
                this.$router.push({
                    name:'floorDetails',
                    params: {
                        paramsObj: {
                            pkCode: "180615b02228c95c6b45",
                            pkName: "幸福大街",
                            bdCode: "180615aa2f4518085545",
                            bdName: "幸福大街园区C栋",
                            flCode: "1806296605cc965ab84c",
                            thisFloor:"1"
                        }
                    }
                });
            }
        }
    }
</script>

<style >
    .heightAll {
        background: #6cd1ff;
        height: 100%;
        margin: 0px;
        padding: 0px;
    }

    .el-footer {
        background-color: rgb(132,186,178);
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
        background-color: rgb(242,242,242);
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

    .topbar{
        background-color: #E69954;

    }

    .el-menu-vertical-demo:not(.el-menu--collapse) {
        min-height: 400px;
    }

    body{
        overflow:hidden;
        overflow-x:hidden;
    }
</style>

<style scoped>
    /*子内容页面区域样式*/
    .pageChild{
        height: 100vh;
    }

    .sideBarMaxWid{
        width: 200px;
    }

    .sideBarMinWid{
        width: 55px;
    }

    .contentMaxWid{
        width: 90%;
    }

    .contentMinWid{
        width: 97%;
    }
</style>