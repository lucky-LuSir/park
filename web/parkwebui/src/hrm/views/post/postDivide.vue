<template>
    <div class="has-position_root" ref="myRoot">
        <!--导航栏-->
        <div class="has-position_header" style="justify-content: space-between">
            <span class="navigation_title">当前位置：岗位分配</span>
            <el-button v-show="showSavaDividePostBtn" class="navigation-save-button" size="small" type="primary"
                       @click="handleClickEdit()">保存
            </el-button>
        </div>


        <el-container style="height: calc(100vh - 147px); padding-bottom: 10px;">
            <!--子菜单-->
            <el-aside width="230px" style="height: 100%;">
                <el-menu style="text-align: left;" :collapse="false" :default-active="initPostItem" :default-openeds="activityArr" background-color="#666666" text-color="#fff" active-text-color="#ffd04b">

                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-star-on"></i>
                            <span>岗位列表</span>
                        </template>
                        <div v-for="(item, index) in postTbList" @click="handleClickAPost(item)" :key="index">
                            <el-menu-item :index="item.postCode">
                                <i class="el-icon-star-off"></i>
                                <span slot="title" :title="item.postName">{{item.postName}}</span>
                            </el-menu-item>
                        </div>
                    </el-submenu>

                </el-menu>
            </el-aside>

            <el-main class="post_main" style="height: 100%;">
                <!--岗位权限-->
                <div class="post_resource">
                    <el-tree :data="treeMenuList" style="line-height: 50px;" show-checkbox="" :default-expand-all="false" :expand-on-click-node="true" node-key="id" ref="tree" highlight-current :props="defaultProps">
                    </el-tree>
                </div>
            </el-main>
        </el-container>
    </div>
</template>


<script scoped>
    export default {
        components: {},
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,
                showSavaDividePostBtn: false,
                initPostItem: '',
                treeMenuList: [],

                defaultProps: {
                    children: 'children',
                    label: 'menuName'
                },

                activityArr: ["1"],
                postTbList: [],
                dialogFormVisible: false,

                postObj: {
                    postCode: '',
                    postName: '',
                    remark: '',
                },
            }
        },
        props: ['paramsObj'], // 接收父组件数据
        created: function() {
            let vm = this;

            let postCodeLet;
            if (vm.paramsObj) {
                postCodeLet = vm.paramsObj.postCode;
            }

            // 菜单和按钮权限控制
            vm.showSavaDividePostBtn = vm.$flagMenuStore.judgeMenu("savaDividePostBtn");
            let params = {
                entity: {},
                start: 0,
                pageSize: 50
            };

            let options = {
                method: 'POST',
                data: params,
                url: 'post/query',
            };

            this.$ajax(
                options
            ).then(function(response) {
                vm.postTbList = response.data.result.data;
                vm.basicInitAllMenu(vm, postCodeLet);
            }).catch(function(error) {
                vm.$message.error('页面：查询岗位失败！');
            });
        },
        methods: {
            basicInitAllMenu(vmThis, postCodeLet) {
                //请求树形菜单数据
                var vm = vmThis;

                // 公司为三蚁科技的用户登陆，取出所有的菜单
                let cpyCode = vm.$cookieStore.getCookie('cpyCode');
                if (cpyCode == '1906277326158896984f') {
                    let sendObj = {
                        entity: {}
                    };

                    let options = {
                        method: 'POST',
                        data: sendObj,
                        url: 'menu/query',
                    };
                    this.$ajax(
                        options
                    ).then(function(response) {
                        vm.treeMenuList = response.data.result;
                        if (postCodeLet == null) {
                            vm.initPostItem = "岗位激活初始值";
                        } else {
                            vm.initPostItem = postCodeLet;
                            var aPostObj = {};
                            for (var i = 0; i < vm.postTbList.length; i++) {
                                if (postCodeLet == vm.postTbList[i].postCode) {
                                    aPostObj = vm.postTbList[i];
                                }
                            }
                            vm.handleClickAPost(aPostObj); //用于加载树菜单初值
                        }
                    }).catch(function(error) {
                        vm.$message.error('页面:获取数据失败!menu/query');
                    });
                } else {
                    vm.treeMenuList = JSON.parse(window.sessionStorage.getItem("menuTreeList"));
                }

                if (postCodeLet == null) {
                    vm.initPostItem = "岗位激活初始值"; //测试数据,CEO(平测)的postCode
                } else {
                    vm.initPostItem = postCodeLet;
                    var aPostObj = {};
                    for (var i = 0; i < vm.postTbList.length; i++) {
                        if (postCodeLet == vm.postTbList[i].postCode) {
                            aPostObj = vm.postTbList[i];
                        }
                    }
                    vm.handleClickAPost(aPostObj); //用于加载树菜单初值
                }
            },
            handleClickEdit() {
                var aValPost = {};
                aValPost = this.postObj;
                if (!aValPost.postCode) {
                    this.$alert('页面:请先选择相应岗位分配权限', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                    return;
                }
                var menuObjList = [];
                menuObjList = this.$refs.tree.getCheckedNodes();
                var menuCodeList = [];
                for (var i = 0; i < menuObjList.length; i++) {
                    var aMenuCode = menuObjList[i].menuCode;
                    menuCodeList.push(aMenuCode);
                }

                this.basicEditPost(aValPost, menuCodeList);
            },
            handleClickAPost(aVal) {
                this.postObj = aVal;
                this.basicQueryOnePost(aVal.postCode);
            },
            setCheckedNodes() {
                this.$refs.tree.setCheckedNodes([{
                    "id": 12,
                    "isDeleted": false,
                    "menuCode": "3-2",
                    "menuName": "合同新增",
                    "parentCode": "3",
                    "menuTypeName": "找不到指定词典",
                    "level": 2,
                    "children": []
                }, ]);
            },
            getCheckedNodes() {
                //树形菜单通过节点获取
            },
            basicEditPost(aValPost, menuCodeList) {
                var vm = this;

                var obj = {};
                obj = aValPost;
                var sendObj = {};
                sendObj.entity = obj;
                sendObj.menuCodes = menuCodeList;

                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "post/update",
                };

                this.$ajax(
                    options
                ).then(function(response) {
                    vm.$message({
                        message: response.data.message,
                        type: 'success'
                    });
                }).catch(function(error) {
                    vm.$message.error('页面:获取数据失败!post/update');
                });
            },
            basicQueryOnePost(aVal) {
                let vm = this;

                let obj = {};
                obj.postCode = aVal;
                let sendObj = {};
                sendObj.entity = obj;

                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "post/queryOne",
                };

                this.$ajax(
                    options
                ).then(function(response) {
                    vm.$refs.tree.setCheckedNodes(response.data.result.menus);
                }).catch(function(error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },
            basicReLoadPost() {
                let vm = this;

                let obj = {};
                let sendObj = {};
                sendObj.start = 0;
                sendObj.pageSize = 50;
                sendObj.entity = obj;

                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "post/query",
                };

                this.$ajax(
                    options
                ).then(function(response) {
                    vm.postTbList = response.data.result.data;
                }).catch(function(error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },
        },
    }
</script>

<style scoped>
    /*导航栏保存按钮*/
    .navigation-save-button {
        float: right;
        margin-right: 20px;
    }

    /*导航栏标题*/
    .navigation_title {
        float: left;
        height: 50px;
        line-height: 50px;
        margin-left: 20px;
        color: #409eff;
    }

    .post_main {
        overflow-x: hidden;
        background-color: white;
    }

    .post_resource {
        overflow-x: auto;
        text-align: right;
        background-color: white;
        margin-top: -20px;
        padding-top: 15px;
        padding-left: 20px;
        padding-right: 20px;
        width: 100%;
    }

</style>