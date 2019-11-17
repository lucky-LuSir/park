<template scoped>
    <div class="has-position_root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
            <div class="has-position_header">
                <el-button v-if="showAddMenuBtn" class="navigation-add-edit-button" size="small" type="primary"
                           @click="showAddDialog">新增
                </el-button>
                <el-button v-if="showEditMenuBtn" class="navigation-add-edit-button" size="small" type="primary"
                           @click="handleEditMenu()">修改
                </el-button>
            </div>

        <!-- 新增菜单对话框 -->
        <el-dialog :visible.sync="dialogFormVisible" width="55%" style="min-width: 1150px;">
            <el-form :model="dialogMenuObj" :rules="rules" ref="ruleFormRef" label-width="100px"
                     style="text-align: center;">
                <el-row :gutter="5" style="text-align: center;">
                    <div style="margin-bottom: 20px;margin-top: -30px;font-size: 17px;">
                        <span v-show="submitBtnVisible">菜单新增</span>
                        <span v-show="!(submitBtnVisible)">菜单修改</span>
                    </div>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="菜单名称:" prop="menuName">
                                <el-input v-model="dialogMenuObj.menuName"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="菜单Item:" prop="item">
                                <el-input v-model="dialogMenuObj.item"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="排序:" prop="sort">
                                <el-input v-model="dialogMenuObj.sort"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>


                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="菜单级别:" prop="level">
                                <el-input v-model="dialogMenuObj.level"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="菜单类型:" prop="menuType">
                                <el-select v-model="dialogMenuObj.menuType" placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in menuTypeArr"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>


                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="父菜单编码:" prop="parentCode">
                                <el-input v-model="dialogMenuObj.parentCode"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="父菜单名称:">
                                <el-input v-model="dialogObjParentName" disabled></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="菜单描述:" prop="remark">
                                <el-input v-model="dialogMenuObj.remark" placeholder="(选填)"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </el-form>
            <div slot="footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addMenu('ruleFormRef')" v-show="submitBtnVisible">提 交</el-button>
                <el-button type="primary" @click="editMenu('ruleFormRef')" v-show="!(submitBtnVisible)">提 交</el-button>
            </div>
        </el-dialog>

        <el-container style="margin-top: 10px;">
            <!--子菜单-->
            <el-aside width="201px" class="aside">
                <!--菜单列表-->
                <div class="post_resource">
                    <el-tree :data="treeMenuList"
                             style="line-height: 50px;"
                             node-key="id"
                             @node-click="handleNodeClick"
                             :default-expanded-keys="[1]"
                             ref="tree"
                             :expand-on-click-node="false"
                             highlight-current
                             :props="defaultProps">
                    </el-tree>
                </div>
            </el-aside>

            <!--主要内容-->
            <el-main style="background: white; margin-left: 10px;">
                <el-form label-position="right" size="mini" ref="ruleForm" label-width="90px">
                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单名称:">
                                    <span style="float: left">{{mainMenuObj.menuName}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单编码:">
                                    <span>{{mainMenuObj.menuCode}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单Item:">
                                    <span>{{mainMenuObj.item}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="排序:">
                                    <span>{{mainMenuObj.sort}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;" v-show="false">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单item:">
                                    <span>{{mainMenuObj.item}}</span>
                                </el-form-item>
                            </div>
                        </el-col>

                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单所属包:">
                                    <span style="float: left">{{mainMenuObj.pack}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" v-show="false">
                            <div class="grid-content ">
                                <el-form-item label="菜单图标:">
                                    <span>{{mainMenuObj.iconCls}}</span>
                                </el-form-item>
                            </div>
                        </el-col>

                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单级别:">
                                    <span style="float: left">{{mainMenuObj.level}}</span>
                                </el-form-item>
                            </div>
                        </el-col>

                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单类型:">
                                    <span style="float: left">{{mainMenuObj.menuTypeName}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" v-show="false">
                            <div class="grid-content ">
                                <el-form-item label="排序:">
                                    <span>{{mainMenuObj.sort}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="父菜单编码:">
                                    <span>{{mainMenuObj.parentCode}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单描述:">
                                    <span>{{mainMenuObj.remark}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                </el-form>
            </el-main>
        </el-container>
    </div>
</template>


<script>
    export default {
        components: {},
        created: function () {
            // 初始化所有菜单
            let vm = this;

            // 菜单和按钮权限控制
            vm.showAddMenuBtn = vm.$flagMenuStore.judgeMenu("addMenuBtn");
            vm.showEditMenuBtn = vm.$flagMenuStore.judgeMenu("editMenuBtn");

            vm.basicInitAllMenu(vm);
            this.reInitDialogMenuObj();
        },
        data() {
            // 校验数字只能为整数
            let checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('楼层数只能为正整数'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {
                floatDivWidth: "",
                showAddMenuBtn: false,
                showEditMenuBtn: false,
                dialogObjParentName: '',
                menuTypeArr: [{
                    value: '1',
                    label: '菜单'
                }, {
                    value: '2',
                    label: '功能'
                }],
                rules: {
                    menuName: [
                        {required: true, message: '请输入菜单名称', trigger: 'blur'},
                    ],//输入框校验
                    parentCode: [
                        {required: true, message: '请输入父菜单编码', trigger: 'blur'},
                    ], //输入框校验
                    level: [
                        {required: true, message: '请输入菜单等级', trigger: 'blur'},
                        {validator: checkInteger},
                    ],//输入框校验
                    menuType: [
                        {required: true, message: '请选择菜单类型', trigger: 'change'}
                    ],//下拉框校验
                },
                initPostItem: "",
                submitBtnVisible: true,
                dialogFormVisible: false,
                dialogMenuObj: {
                    menuCode: '',//菜单编码
                    menuName: '',//菜单名称
                    item: '',//菜单元素

                    parentCode: '',//父级菜单编码
                    menuType: '',//菜单类型
                    menuTypeName: '',//菜单类型名称
                    pack: '',//菜单元素包

                    iconCls: '',//菜单图标
                    level: 0,//菜单级别(Integer)
                    sort: '',//排序
                    remark: '',//菜单描述
                    children: []//菜单List<MenuEntity>
                },
                mainMenuObj: {
                    menuCode: '',//菜单编码
                    menuName: '',//菜单名称
                    item: '',//菜单元素

                    parentCode: '',//父级菜单编码
                    menuType: '',//菜单类型
                    menuTypeName: '',//菜单类型名称
                    pack: '',//菜单元素包

                    iconCls: '',//菜单图标
                    level: 0,//菜单级别(Integer)
                    sort: '',//排序
                    remark: '',//菜单描述
                    children: []//菜单List<MenuEntity>
                },
                treeMenuList: [
                    {
                        id: 0,
                        isDeleted: false,
                        menuCode: "rootNum",
                        menuName: "所有菜单(根菜单)",
                        parentCode: "0",
                        menuTypeName: "找不到指定词典",
                        level: 0,
                        children: [
                            {
                                id: 1,
                                isDeleted: false,
                                menuCode: "1",
                                menuName: "园区管理",
                                parentCode: "rootNum",
                                menuTypeName: "找不到指定词典",
                                level: 1,
                                children: [
                                    {
                                        id: 5,
                                        isDeleted: false,
                                        menuCode: "1-1",
                                        menuName: "园区列表",
                                        parentCode: "1",
                                        menuTypeName: "找不到指定词典",
                                        level: 2,
                                        children: []
                                    },
                                    {
                                        id: 6,
                                        isDeleted: false,
                                        menuCode: "1-2",
                                        menuName: "园区新增",
                                        parentCode: "1",
                                        menuTypeName: "找不到指定词典",
                                        level: 2,
                                        children: []
                                    },
                                    {
                                        id: 7,
                                        isDeleted: false,
                                        menuCode: "1-3",
                                        menuName: "园区详情",
                                        parentCode: "1",
                                        menuTypeName: "找不到指定词典",
                                        level: 2,
                                        children: []
                                    }
                                ]
                            }
                        ]
                    }
                ],
                defaultProps: {
                    children: 'children',
                    label: 'menuName'
                },
                activityArr: ["1"],
                postTbList: [],
                postObj: {
                    postCode: '',
                    postName: '',
                    remark: '',
                },
            }
        },
        methods: {
            basicGetOneMenu(aValMenuCode){
                //请求树形菜单数据
                let vm = this;

                let obj = {};
                obj.menuCode = aValMenuCode;
                let sendObj = {};
                sendObj.entity = obj;

                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "menu/queryByMenuCode",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if(response.data.result.data) {
                        vm.dialogObjParentName = response.data.result.data[0].menuName;
                    } else {
                        vm.dialogObjParentName = '无';
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查询菜单树信息失败','系统提示',{
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 修改菜单
            editMenu(ruleFormVal){
                let vm = this;

                //表单验证
                this.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {
                        let obj = {};
                        obj = this.dialogMenuObj;
                        obj.id = '';
                        let sendObj = {};
                        sendObj.entity = obj;

                        let options = {
                            method: 'POST',
                            data: sendObj,
                            url: "menu/update",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$message({
                                showClose: true,
                                message: response.data.message,
                                type: 'success'
                            });
                            vm.basicInitAllMenu(vm);
                            vm.dialogFormVisible = false;
                            vm.reInitDialogMenuObj();
                        }).catch(function (error) {
                            vm.$alert('页面：更新菜单失败','系统提示',{
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    } else {
                        vm.$alert('页面：更新时填写不规范,请检查!','温馨提示',{
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                        return false;
                    }
                });
            },
            handleEditMenu()  {
                this.dialogObjParentName = '';
                this.dialogMenuObj = this.mainMenuObj;
                this.basicGetOneMenu(this.dialogMenuObj.parentCode);
                this.dialogFormVisible = true;//打开对话框
                this.submitBtnVisible = false;//隐藏提交按钮,显示保存按钮
            },
            reInitDialogMenuObj(){
                this.dialogMenuObj = {
                    menuCode: '',//菜单编码
                    menuName: '',//菜单名称
                    item: '',//菜单元素

                    parentCode: '',//父级菜单编码
                    menuType: '1',//菜单类型
                    menuTypeName: '',//菜单类型名称
                    pack: '',//菜单元素包

                    iconCls: '',//菜单图标
                    level: 0,//菜单级别(Integer)
                    sort: '',//排序
                    remark: '',//菜单描述
                    children: []//菜单List<MenuEntity>
                }
            },
            handleNodeClick(data) {
                this.reInitDialogMenuObj();
                this.mainMenuObj = data;

            },
            // 新增菜单
            addMenu(ruleFormVal){
                let vm = this;

                this.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {
                        let obj = {};
                        obj = this.dialogMenuObj;
                        let sendObj = {};
                        sendObj.entity = obj;

                        let options = {
                            method: 'POST',
                            data: sendObj,
                            url: "menu/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$message({
                                message: response.data.message,
                                type: 'success'
                            });
                            vm.basicInitAllMenu(vm);
                            vm.dialogFormVisible = false;
                            vm.reInitDialogMenuObj();

                        }).catch(function (error) {
                            vm.$alert('页面：获取菜单数据失败','系统提示',{
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    } else {
                        vm.$alert('页面：新增时填写不规范,请检查!','温馨提示',{
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                        return false;
                    }
                });
            },
            showAddDialog(){
                this.reInitDialogMenuObj();
                this.dialogMenuObj.level = this.mainMenuObj.level + 1;
                this.dialogMenuObj.parentCode = this.mainMenuObj.menuCode;
                this.dialogObjParentName = this.mainMenuObj.menuName;
                this.submitBtnVisible = true;
                this.dialogFormVisible = true;
            },
            basicInitAllMenu(vmThis){
                // 请求树形菜单数据

                let vm = vmThis;
                let obj = {};
                let sendObj = {};
                sendObj.entity = obj;

                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "menu/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.treeMenuList = response.data.result;
                }).catch(function (error) {
                    vm.$alert('页面：获取菜单数据失败','系统提示',{
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
        },
    }

</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .root {
        min-width: 1100px;
        min-height: 900px;
        background-color: rgb(242, 242, 242);
    }

    /*导航栏DIV*/
    .navigation_div {
        height: 50px;
        margin-top: -43px;

        position: fixed;
        z-index: 200;
    }

    .navigation-add-edit-button{
        float: left;
        margin-left: 20px;
    }

    .post_resource {
        text-align: right;
        background-color: white;
        height: 78vh;
        overflow-x: hidden;
        overflow-y: auto;
        width: 100%;
    }

    /*每一列的块区域*/
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
        /*background-color: #69ff38;*/
    }


</style>