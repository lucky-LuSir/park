<template>
    <div class="has-position_root" ref="myRoot">
        <!--根部分-->
        <!--导航栏-->
        <div class="has-position_header">
            <el-input size="small" @keyup.enter.native="searchEnterFun" class="navigation_button_input" v-model="selectPostName" style="margin-left: 20px;width: 300px;" placeholder="请输入角色名称查询">
                <el-button slot="append" icon="el-icon-search" @click="findReLoadRole"></el-button>
            </el-input>

            <el-button v-show="showaddRoleBtn" class="navigation-add-button" @click="showAddDialog" size="small" type="primary">
                新增角色
            </el-button>
        </div>

        <!-- 新增角色对话框 -->
        <el-dialog :visible.sync="dialogFormVisible" width="35%" center style="min-width: 1150px;">
            <el-form :model="diaForm" :rules="rules" ref="ruleFormRef" label-width="0px" style="text-align: center;">
                <el-row :gutter="5" style="text-align: center;">
                    <div style="margin-bottom: 50px;line-height: 10px;">
                        <span>角色新增</span>
                    </div>
                </el-row>
                <el-row :gutter="5" style="margin-bottom: 30px;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="角色名称" prop="roleName" label-width="80px">
                                <el-input v-model="diaForm.roleName"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="角色描述" label-width="80px">
                                <el-input v-model="diaForm.remark" type="textarea"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </el-form>
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="addRole('ruleFormRef')" v-show="submitBtnVisible">提交</el-button>
                <el-button type="warning" @click="editRole('ruleFormRef')" v-show="!(submitBtnVisible)">保存</el-button>
            </div>
        </el-dialog>

        <!--第1个方框 一行搜索-->
        <div class="block_box1"></div>
        <!--第1个方框(end)-->

        <!--第2个方框 一行显示搜索条数-->
        <div class="block_box2"></div>
        <!--第2个方框(end)-->

        <!--第3个方框 一行树状表格-->
        <div class="role_table">
            <el-table :data="roleTbList" :height="tableHeight" :show-header="false" :border="false">

                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column label="角色名称text" min-width="180">
                    <template slot-scope="scope">
                        <span>角色名称:</span>
                        <span style="margin-left: 10px">{{ scope.row.roleName }}</span>
                    </template>
                </el-table-column>

                <el-table-column label="角色描述text" min-width="180">
                    <template slot-scope="scope">
                        <span>角色描述:</span>
                        <span style="margin-left: 10px">{{ scope.row.remark }}</span>
                    </template>
                </el-table-column>

                <el-table-column label="占位列调整">
                </el-table-column>

                <el-table-column label="操作" width="180">
                    <template slot-scope="scope" style="float: right;text-align: right;">
                        <el-button v-show="showDividePermitBtn" style="text-align: right;" size="mini" @click="goRoleDivide(scope.$index, scope.row)">分配权限</el-button>
                        <el-button v-show="showEditPostBtn" size="mini" type="danger" @click="handleEditRole(scope.$index, scope.row)">修改</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <!--第3个方框(end)-->

        <!--第4个方框 页码条-->
        <div class="block_box4"></div>
        <!--第4个方框(end)-->

    </div>
    <!--根部分(end)-->
</template>


<script scoped>
import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";

export default {
    components: {
        ElInput,
        ElButton    },
    created: function () {
        var vm = {};
        vm = this;
        //菜单和按钮权限控制 start
        vm.showaddRoleBtn = vm.$flagMenuStore.judgeMenu("addRoleBtn");
        vm.showaddRoleBtn = true;
        vm.showDividePermitBtn = vm.$flagMenuStore.judgeMenu("dividePermitBtn");
        vm.showEditPostBtn = vm.$flagMenuStore.judgeMenu("editPostBtn");
        //菜单和按钮权限控制 end
        var obj = {};
        var sendObj = {};
        sendObj.start = 0;
        sendObj.pageSize = 50;
        sendObj.entity = obj;
        var options = {
            method: 'POST',
            headers: { 'content-type': 'application/json' },
            data: sendObj,
            url: "role/query",
        };
        this.$ajax(
            options
        ).then(function (response) {
            vm.roleTbList = response.data.result.data;
        }).catch(function (error) {
            vm.$message.error('页面:获取数据失败!role/query');
        });
    },
    data() {
        return {
            tableHeight: this.$store.state.tableHeight,
            floatDivWidth: "",
            showaddRoleBtn: false,
            showDividePermitBtn: false,
            showEditPostBtn: false,
            rules: {
                roleName: [
                    { required: true, message: '请输入角色名称', trigger: 'blur' },
                ],//输入框校验
                remark: [
                    { required: true, message: '请输入角色备注', trigger: 'blur' },
                ] //输入框校验

            },//end obj rules 验证对象
            selectPostName: '',
            submitBtnVisible: true,
            dialogFormVisible: false,
            diaForm: {
                roleCode: '',
                roleName: '',
                remark: '',
            },
            roleTbList: [
                {
                    roleCode: '',
                    roleName: '',
                    remark: '',
                },

            ]
        }
    },
    methods: {
        showAddDialog() {
            this.diaForm = {};
            this.submitBtnVisible = true;
            this.dialogFormVisible = true;

        },
        handleEditRole(index, rowsRole) {
            this.diaForm.roleCode = rowsRole.roleCode;
            this.diaForm.roleName = rowsRole.roleName;
            this.diaForm.remark = rowsRole.remark;

            this.dialogFormVisible = true;//打开对话框
            this.submitBtnVisible = false;//隐藏提交按钮,显示保存按钮


        },
        editRole(ruleFormVal) {
            var vm = {};
            vm = this;
            var flag = false;
            //表单验证 start
            this.$refs[ruleFormVal].validate((valid) => {
                if (valid) {
                    //                        alert('submit!');
                    flag = true;
                } else {
                    vm.$message.error('填写不规范,请检查!');
                    return false;
                }
            });
            if (flag) {        //flag留后面表单验证
                var obj = {};
                obj = this.diaForm;
                var sendObj = {};
                sendObj.entity = obj;
                sendObj.menuCodes = [];
                var options = {
                    method: 'POST',
                    headers: { 'content-type': 'application/json' },
                    data: sendObj,
                    url: "role/update",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.$message({
                        message: response.data.message,
                        type: 'success'
                    });
                    vm.dialogFormVisible = false;
                    vm.basicReLoadPost();
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!role/update');
                });
            }
        },
        goRoleDivide(index, rowsCus) {
            //                rows.splice(index, 1);
            var sendRuData = rowsCus.roleCode;
            //                this.$router.push({
            //                    name: 'roleDivide',   //rutes.js中配置的路由name
            //                    params: {
            //                        name: 'ru_roleCodeName',
            //                        ru_roleCode: sendRuData,
            //                    },
            //                });

            let data = {
                menuName: "分配角色",
                item: "divideRoleList",
                params: {
                    name: 'ru_roleCodeName',
                    ru_roleCode: sendRuData,
                }
            };
            this.$emit("backParam", data);

        },//end handleUpdate方法

        addRole(ruleFormVal) {
            var vm = {};
            vm = this;
            var flag = false;
            //表单验证 start
            this.$refs[ruleFormVal].validate((valid) => {
                if (valid) {
                    //                        alert('submit!');
                    flag = true;
                } else {
                    vm.$message.error('填写不规范,请检查!');
                    return false;
                }
            });
            if (flag) {        //flag留后面表单验证
                var obj = {};
                obj = this.diaForm;
                var sendObj = {};
                sendObj.entity = obj;
                //sendObj.menuCodes = [];
                var options = {
                    method: 'POST',
                    headers: { 'content-type': 'application/json' },
                    data: sendObj,
                    url: "role/create",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.$message({
                        message: response.data.message,
                        type: 'success'
                    });
                    vm.dialogFormVisible = false;
                    vm.basicReLoadPost();
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!role/create');
                });
            }
        },
        goBack() {
            this.basicReLoadPost();
        },
        searchEnterFun: function (e) {
            var vm = this;
            var keyCode = window.event ? e.keyCode : e.which;
            if (keyCode == 13) {
                vm.findReLoadRole();

            }

        },
        findReLoadRole() {
            var vm = {};
            vm = this;
            var flag = true;
            if (flag) {        //flag留后面表单验证
                var obj = {};
                obj.roleName = this.selectPostName;
                var sendObj = {};
                sendObj.start = 0;
                sendObj.pageSize = 50;
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    headers: { 'content-type': 'application/json' },
                    data: sendObj,
                    url: "role/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.roleTbList = response.data.result.data;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!role/query');
                });
            }
        },
        basicReLoadPost() {
            var vm = {};
            vm = this;
            var flag = true;
            if (flag) {        //flag留后面表单验证
                var obj = {};
                var sendObj = {};
                sendObj.start = 0;
                sendObj.pageSize = 50;
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    headers: { 'content-type': 'application/json' },
                    data: sendObj,
                    url: "role/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.roleTbList = response.data.result.data;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!role/query');
                });
            }
        },

        handleEdit(index, row) {

        },
        handleDelete(index, row) {

        }
    }
}

</script>

<style scoped>
body {
    margin-left: 200px;
    margin-right: 200px;
}

.root {
    min-width: 1000px;
    min-height: 800px;
    background-color: rgb(242, 242, 242);
}

/*导航栏DIV*/
.navigation-div {
    width: 100%;
    height: 50px;
    background-color: white;
    margin-top: -46px;
    border-bottom: 1px solid rgb(242, 242, 242);
}
/*导航栏返回按钮*/
.navigation_button_back {
    float: left;
    margin-top: 10px;
    margin-left: 20px;
}

/*导航栏新增按钮*/
.navigation-add-button {
    float: right;
    margin-left: 20px;
}

/*导航栏搜索输入框*/
.navigation_button_input {
    float: left;
    margin-left: 20px;
}

/*角色表格div*/
.role_table {
    min-width: 1000px;
    margin-top: 15px;
    background-color: white;
    min-height: 60px;
    line-height: 30px;
}
</style>