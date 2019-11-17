<template>
    <div class="has-position_root" ref="myRoot">
        <!--导航栏-->
        <div class="has-position_header">
            <el-button v-if="showAddPostBtn" class="navigation-add-post" @click="showAddDialog" size="small"
                       type="primary">
                岗位新增
            </el-button>

            <el-input size="small" clearable
                      @keyup.enter.native="searchEnterFun"
                      class="navigation_button_input"
                      v-model="selectPostName"
                      placeholder="请输入岗位名称查询">
                <el-button slot="append" icon="el-icon-search" @click="findReLoadPost"></el-button>
            </el-input>
        </div>

        <!-- 新增岗位对话框 -->
        <el-dialog :visible.sync="dialogFormVisible" width="35%" center title="岗位新增">
            <el-form :model="diaForm" :rules="rules" ref="ruleFormRef" label-width="0px" style="text-align: center;">
                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="岗位名称" prop="postName" label-width="80px">
                            <el-input v-model="diaForm.postName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="岗位描述" label-width="80px">
                            <el-input v-model="diaForm.remark" type="textarea"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>

            <div style="text-align: right;">
                <el-button type="primary" @click="addPost('ruleFormRef')" v-show="submitBtnVisible">提交</el-button>
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="editPost('ruleFormRef')" v-show="!(submitBtnVisible)">保存</el-button>
            </div>
        </el-dialog>

        <div class="post_table">
            <el-table
                    :height="tableHeight"
                    :data="postTbList"
                    :show-header="false"
                    :border="false">

                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        label="岗位名称text"
                        min-width="180">
                    <template slot-scope="scope">
                        <span>岗位名称：</span>
                        <span style="margin-left: 10px">{{ scope.row.postName }}</span>
                    </template>
                </el-table-column>


                <el-table-column
                        label="岗位描述text"
                        min-width="180">
                    <template slot-scope="scope">
                        <span>岗位描述：</span>
                        <span style="margin-left: 10px">{{ scope.row.remark }}</span>
                    </template>
                </el-table-column>

                <el-table-column
                        label="占位列调整">
                </el-table-column>

                <el-table-column label="操作" width="180">
                    <template slot-scope="scope" style="float: right;text-align: right;">
                        <el-button
                                v-show="showDividePermitBtn"
                                style="text-align: right;"
                                size="mini"
                                @click="goPostDivide(scope.$index, scope.row)">分配权限
                        </el-button>
                        <el-button
                                v-show="showEditPostBtn"
                                size="mini"
                                type="danger"
                                @click="handleEditPost(scope.$index, scope.row)">修改
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <div class="block_box4"></div>
    </div>
</template>


<script scoped>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";

    export default {
        components: {
            ElInput,
            ElButton
        },
        created: function () {
            var vm = {};
            vm = this;
            //菜单和按钮权限控制 start
            vm.showAddPostBtn = vm.$flagMenuStore.judgeMenu("addPostBtn");
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
                headers: {'content-type': 'application/json'},
                data: sendObj,
                url: "post/query",
            };
            this.$ajax(
                options
            ).then(function (response) {
                vm.postTbList = response.data.result.data;
            }).catch(function (error) {
                vm.$message.error('页面:获取数据失败!post/query');
            });
        },
//        mounted(){
//            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
//            window.onresize = () => {
//                return (() => {
//                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
//                })();
//            }
//        },
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,
                floatDivWidth: "",
                showAddPostBtn: false,
                showDividePermitBtn: false,
                showEditPostBtn: false,
                rules: {
                    postName: [
                        {required: true, message: '请输入岗位名称', trigger: 'blur'},
                    ],//输入框校验
                    remark: [
                        {required: true, message: '请输入岗位备注', trigger: 'blur'},
                    ] //输入框校验

                },//end obj rules 验证对象
                selectPostName: '',
                submitBtnVisible: true,
                dialogFormVisible: false,
                diaForm: {
                    postCode: '',
                    postName: '',
                    remark: '',
                },
                postTbList: [
                    {
                        postCode: '',
                        postName: '超级管理员',
                        remark: '上海市普陀区金沙江路 1518 弄',
                    },

                ]
            }
        },
        methods: {
            showAddDialog(){
                this.diaForm = {};
                this.submitBtnVisible = true;
                this.dialogFormVisible = true;

            },
            handleEditPost(index, rowsPost)  {
                this.diaForm.postCode = rowsPost.postCode;
                this.diaForm.postName = rowsPost.postName;
                this.diaForm.remark = rowsPost.remark;

                this.dialogFormVisible = true;//打开对话框
                this.submitBtnVisible = false;//隐藏提交按钮,显示保存按钮


            },
            editPost(ruleFormVal){
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
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "post/update",
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
                        vm.$message.error('页面:获取数据失败!post/update');
                    });
                }
            },

            // 分配权限
            goPostDivide(index, rowsCus)  {
                let data = {
                    menuName: "分配权限",
                    item: "dividePostList",
                    params: {
                        name: 'ru_postCodeName',
                        postCode: rowsCus.postCode,
                    }
                };
                this.$emit("backParam", data);
            },

            addPost(ruleFormVal){
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
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "post/create",
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
                        vm.$message.error('页面:获取数据失败!post/create');
                    });
                }
            },
            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                if (keyCode == 13) {
                    vm.findReLoadPost();
                }
            },
            findReLoadPost(){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj.postName = this.selectPostName;
                    var sendObj = {};
                    sendObj.start = 0;
                    sendObj.pageSize = 50;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "post/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.postTbList = response.data.result.data;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!post/query');
                    });
                }
            },
            basicReLoadPost(){
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
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "post/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.postTbList = response.data.result.data;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!post/query');
                    });
                }
            },
            resetForm(formName){
                this.$refs[formName].resetFields();
                this.dialogFormVisible = false;
            },
        }
    }

</script>

<style scoped>
    .root {
        min-width: 1000px;
        height: 100%;
        background: rgb(242, 242, 242);
    }

    /*导航栏DIV*/
    .navigation-div {
        height: 50px;
        margin-top: -50px;

        position: fixed;
    }

    /*岗位新增按钮*/
    .navigation-add-post {
        float: left;
        margin-left: 20px;
    }

    .navigation_button_input {
        float: left;
        margin-left: 20px;
        width: 300px;
    }

    /*岗位表格div*/
    .post_table {
        width: 100%;
        margin-top: 10px;
        background-color: white;
        height: 78vh;
        overflow-y: auto;
        overflow-x: hidden;
        line-height: 30px;
    }
</style>