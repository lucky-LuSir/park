<template>
    <div class="not-position-notPaging_root " ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row>
            <div class="not-position_header">
                    <div class="titles">
                        <span class="navigation_title">当前位置：角色分配</span>
                        <el-button v-show="showSavaDividePostBtn" class="navigation-save" size="small" type="primary" @click="handleClickEdit()">保存</el-button>
                    </div>
            </div>
        </el-row>
        <div class="navigation_button_background"></div>

        <!--第1个方框 一行搜索-->
        <div class="block_box1"></div>
        <!--第1个方框(end)-->
        <el-container>
            <!--子菜单-->
            <el-aside width="201px" class="aside">
                <el-menu
                        style="min-height: 900px;text-align: left;"
                        :collapse="false"
                        :default-active="initPostItem"

                        :default-openeds="activityArr"
                        class="el-menu-vertical"
                        @open="handleOpen"
                        @close="handleClose">
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-bell"></i>
                            <span>角色列表</span>
                        </template>
                        <div v-for="(item, i) in roleTbList" @click="handleClickARole(item)">
                            <el-menu-item :index="item.roleCode">
                                <i class="el-icon-star-off"></i>
                                <span slot="title" :title="item.roleName">{{item.roleName}}</span>
                            </el-menu-item>
                        </div>


                    </el-submenu>
                </el-menu>



            </el-aside>
            <!--主要内容-->
            <el-main>
                <!--岗位权限-->
                <div class="post_resource" >
                    <el-tree :data="treeMenuList"
                             style="line-height: 50px;"
                             show-checkbox=""
                             :default-expand-all="false"
                             :expand-on-click-node="true"
                             node-key="id"
                             ref="tree"
                             highlight-current :props="defaultProps">
                    </el-tree>
                </div>


            </el-main>
        </el-container>

        <!--第2个方框 一行显示搜索条数-->
        <div class="block_box2"></div>
        <!--第2个方框(end)-->

        <!--第3个方框 一行树状表格-->
        <div class="block_box3"></div>
        <!--第3个方框(end)-->

        <!--第4个方框 页码条-->
        <div class="block_box4"></div>
        <!--第4个方框(end)-->


    </div><!--根部分(end)-->
</template>


<script scoped>

    export default {
        components: {},
        created:function () {
            //获取路由传过来的岗位code
            let roleCodeLet = this.$route.params.ru_roleCode;
            //请求岗位列表数据
            var vm = { };
            vm=this;
            //菜单和按钮权限控制 start
            vm.showSavaDividePostBtn =vm.$flagMenuStore.judgeMenu("savaDividePostBtn");
            //菜单和按钮权限控制 end
            var obj = {};
            var sendObj = {};
            sendObj.start = 0;
            sendObj.pageSize = 50;
            sendObj.entity = obj;
            var options = {
                method: 'POST',
                headers: {'content-type': 'application/json'},
                data: sendObj,
                url: "role/query",
            };
            this.$ajax(
                options
            ).then(function (response) {
                vm.roleTbList=response.data.result.data;
                vm.basicInitAllMenu(vm,roleCodeLet);

            }).catch(function (error) {
                vm.$message.error('页面:获取数据失败!role/query');
            });



        },
        data() {
            return {
                floatDivWidth:"",
                showSavaDividePostBtn:false,
                initPostItem:"",
                treeMenuList: [],
                defaultProps: {
                    children: 'children',
                    label: 'menuName'
                },
                activityArr:["1"],
                roleTbList: [

                ],
                dialogFormVisible: false,
                roleObj: {
                    roleCode:'',
                    roleName: '',
                    remark: '',
                },

            }//end return
        },//end vue data
        methods: {
            basicInitAllMenu(vmThis,roleCodeLet){
                //请求树形菜单数据
                var vm=vmThis;

                vm.treeMenuList = JSON.parse(window.sessionStorage.getItem("menuTreeList"));
                //初始化选中岗位todo
                //alert(roleCodeLet);
                if(roleCodeLet==null){
                    vm.initPostItem="角色激活初始值";//测试数据,CEO(平测)的postCode

                }else{
                    vm.initPostItem=roleCodeLet;
                    var aPostObj = {};
                    for(var i=0;i<vm.roleTbList.length;i++){
                        if(roleCodeLet==vm.roleTbList[i].roleCode){
                            aPostObj=vm.roleTbList[i];
                        }
                    }
                    vm.handleClickARole(aPostObj);//用于加载树菜单初值
                }

//                var obj = {};
//                var sendObj = {};
//                sendObj.entity = obj;
//                var options = {
//                    method: 'POST',
//                    headers: {'content-type': 'application/json'},
//                    data: sendObj,
//                    url: "menu/query",
//                };
//                this.$ajax(
//                    options
//                ).then(function (response) {
//                    vm.treeMenuList=response.data.result;
//                    //初始化选中岗位todo
//                    //alert(roleCodeLet);
//                    if(roleCodeLet==null){
//                        vm.initPostItem="角色激活初始值";//测试数据,CEO(平测)的postCode
//
//                    }else{
//                        vm.initPostItem=roleCodeLet;
//                        var aPostObj = {};
//                        for(var i=0;i<vm.roleTbList.length;i++){
//                            if(roleCodeLet==vm.roleTbList[i].roleCode){
//                                aPostObj=vm.roleTbList[i];
//                            }
//                        }
//                        //alert("看看跳转查到的岗位对象");
//                        //alert(aPostObj.postName);
//                        vm.handleClickARole(aPostObj);//用于加载树菜单初值
//                    }
//                    //初始化勾选菜单todo
//                }).catch(function (error) {
//                    vm.$message.error('页面:获取数据失败!menu/query');
//                });
            },
            handleClickEdit(){
                //alert("点击了保存");
                var aValPost = {};
                aValPost = this.roleObj;
                var menuObjList = [];
                    menuObjList = this.$refs.tree.getCheckedNodes();
                var menuCodeList = [];
                for(var i=0;i<menuObjList.length;i++){
                    var aMenuCode = menuObjList[i].menuCode;
                    menuCodeList.push(aMenuCode);
                }

                this.basicEditPost(aValPost,menuCodeList);
            },
            handleClickARole(aVal){
                //alert(aVal.postCode);
                this.roleObj = aVal;
                this.basicQueryOnePost(aVal.roleCode);
            },
            setCheckedNodes() {
                this.$refs.tree.setCheckedNodes([
                ]);
            },
            getCheckedNodes() {
                //树形菜单通过节点获取 this.$refs.tree.getCheckedNodes()
            },

            goBack(){
//                rows.splice(index, 1);
                    var sendRuData = "角色分配页跳角色列表页";
                    this.$router.push({
                        name: 'roleList',   //rutes.js中配置的路由name
                        params: {
                            name: 'ru_roleCodeName',
                            ru_postCode: sendRuData,
                        },
                    });

            },
            basicEditPost(aValPost,menuCodeList){
                var vm = {};
                vm=this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj = aValPost;
                    var sendObj = {};
                    sendObj.entity = obj;
                    sendObj.menuCodes = menuCodeList;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "role/update",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        //alert("更新岗位和菜单成功!");
                        vm.$message({
                            message: response.data.message,
                            type: 'success'
                        });
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/update');
                    });
                }
            },
            basicQueryOnePost(aVal){
                var vm = {};
                vm=this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj.roleCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "role/queryOne",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        //vm.roleTbList=response.data.result.data;
                        vm.$refs.tree.setCheckedNodes(response.data.result.menus);
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/queryOne');
                    });
                }
            },
            basicReLoadPost(){
                var vm = {};
                vm=this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = 0;
                    sendObj.pageSize = 50;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "role/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.roleTbList=response.data.result.data;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/query');
                    });
                }
            },
            handleOpen(key, keyPath) {

            },
            handleClose(key, keyPath) {

            },
        },//end vue methods
    }//end export default

</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .root {
        min-width: 1000px;
        min-height: 900px;
        background-color: rgb(242, 242, 242);
    }

    .titles{
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
        min-height: 50px;
        background-color: white;
        overflow: hidden;
        border-bottom: 1px solid rgb(242,242,242);
    }
    /*导航栏DIV*/
    .navigation_div{
        width: 100%;
        height: 50px;
        margin-top: -46px;
    }
    /*导航栏保存按钮*/
    .navigation-save {
        margin-right: 20px;
    }
    /*导航栏标题*/
    .navigation_title{
        float: left;
        height: 50px;
        line-height: 50px;
        margin-left: 20px;
        color: #409EFF;
        z-index: 200;

    }
    /*导航栏背景控制*/
    .navigation_button_background{
        height: 10px;
    }

    /*新增页样式*/
    .konge {
        min-height: 45px;
        min-width: 1000px;
        background-color: rgb(242, 242, 242);
    }

    .post_resource{
        height: 78vh;
        overflow-x: hidden;
        overflow-y: auto;
        text-align: right;
        background-color: white;
        margin-top: -20px;
        padding-top: 15px;
        padding-left: 20px;
        padding-right: 20px;
        width: 100%;
    }

    .el-menu-vertical {
        height: 82vh;
        overflow-x: hidden;
        overflow-y: auto;
    }


</style>