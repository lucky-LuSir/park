<template>
    <div class="has-position_root" ref="myRoot">
        <!--导航栏-->
        <div class="has-position_header">
            <el-input size="small" @keyup.enter.native="searchEnterFun" class="navigation_button_input" v-model="selectUserName" clearable placeholder="请输入用户名称查询">
                <el-button slot="append" icon="el-icon-search" @click="findReLoadUser"></el-button>
            </el-input>

            <el-select v-model="deptCode" filterable placeholder="部门筛选" size="small" class="navigation_select" @change="findReLoadUser">
                <el-option v-for="(item, index) in deptList" :key="item.deptCode" :label="item.deptName" :value="item.deptCode">
                </el-option>
            </el-select>


            <el-button v-show="false" class="navigation_button_back" @click="showAddDialog" size="small" type="primary">
                新增用户
            </el-button>
        </div>

        <div class="navigation_div_2">
            <el-checkbox v-model="checked" class="mark_board" @change="checkBox(checked,userTbList)" />

            <el-button size="small" type="primary" class="mark_board" @click="markBoard">分配看板</el-button>
        </div>
        <!-- 新增用户,修改用户对话框 -->
        <el-dialog :visible.sync="dialogFormVisible" width="35%" center style="min-width: 1150px;">
            <el-form :model="diaForm" :rules="rules" ref="ruleFormRef" label-width="0px">
                <el-row :gutter="5" style="text-align: center;">
                    <div style="margin-bottom: 50px;">
                        <span>用户新增</span>
                    </div>
                </el-row>
                <el-row :gutter="5" style="margin-bottom: 30px;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="用户名称" prop="postName" label-width="80px">
                                <el-input v-model="diaForm.postName"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="用户描述" label-width="80px">
                                <el-input v-model="diaForm.remark" type="textarea"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </el-form>
            <div slot="footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="addPost('ruleFormRef')" v-show="submitBtnVisible">提交</el-button>
                <el-button type="primary" @click="editPost('ruleFormRef')" v-show="!(submitBtnVisible)">保存</el-button>
            </div>
        </el-dialog>

        <!--第1个方框 一行树状表格-->
        <div class="has-position_main">
            <el-table :data="userTbList" ref="multipleTable" style="width: 100%;min-width: 1000px;text-align: left;" :show-header="false" @selection-change="handleSelectionChange" :border="false"
            :height="tableHeight">
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column label="用户名称text" width="280">
                    <template slot-scope="scope">
                        <span>用户名称:</span>
                        <span style="margin-left: 10px">{{ scope.row.userName }}</span>
                    </template>
                </el-table-column>


                <el-table-column label="用户描述text" min-width="180">
                    <template slot-scope="scope">
                        <span>用户描述:</span>
                        <span style="margin-left: 10px">{{ scope.row.remark }}</span>
                    </template>
                </el-table-column>

                <el-table-column label="占位列调整">
                </el-table-column>

                <el-table-column label="操作">
                    <template slot-scope="scope" style="float: right;text-align: right;">
                        <el-button v-show="showDividePermitBtn" style="text-align: right;" size="mini" @click="handleDivideRole(scope.$index, scope.row)">分配角色</el-button>
                        <el-button v-show="false" size="mini" type="danger" @click="handleEditPost(scope.$index, scope.row)">修改</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <!--第3个方框(end)-->

        <!-- 用户,分配角色对话框 -->
        <el-dialog :visible.sync="divideRoleVisible" width="35%" center style="min-width: 1150px;">
            <el-form :model="diaUserRoleForm" ref="divideRoleRef" label-width="0px" style="text-align: center;">
                <el-row :gutter="5" style="text-align: center;">
                    <div style="margin-bottom: 50px;line-height: 10px;">
                        <span>用户分配角色</span>
                    </div>
                </el-row>
                <el-row :gutter="5" style="margin-bottom: 30px;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="用户名称" prop="userName" label-width="80px">
                                <el-input v-model="diaUserRoleForm.userName" :disabled="true"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="用户描述" label-width="80px">
                                <el-input v-model="diaUserRoleForm.remark" :disabled="true" type="textarea"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="选择角色" label-width="80px">
                                <el-select v-model="roleSelectList" multiple placeholder="请选择" style="width: 100%;">
                                    <el-option v-for="item in roleTbList" :key="item.roleCode" :label="item.roleName" :value="item.roleCode">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </el-form>
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="divideRoleVisible = false">取消</el-button>
                <el-button type="primary" @click="editUserRole()">保存</el-button>
            </div>
        </el-dialog>

        <!--第4个方框 页码条-->
        <div class="block_box4"></div>
        <!--第4个方框(end)-->

        <el-dialog :visible.sync="showMarkBoard" :close-on-click-modal="false" :before-close="beforeCloseBoard" width="600px" center>
            <el-container style="height: 460px; border: 1px solid #eee">
                <el-aside width="150px">
                    <p class="user_p">已选择用户</p>
                    <div class="user_list">
                        <ul v-for="(item, index) in multipleSelection" :key="index" style="list-style: none">
                            <li style="line-height: 0px;margin-top: 36px;margin-left: 15px;">{{item.userName}}</li>
                        </ul>
                    </div>
                </el-aside>
                <el-main width="50%" style="border-left: 1px solid #000">
                    <p class="board_p">请选择看板</p>
                    <el-tree :data="boardList" ref="boardTree" show-checkbox node-key="code" default-expand-all :props="defaultProps" style="margin-top: 48px;background: rgb(242, 242, 242);margin-left: 30px;">
                    </el-tree>
                </el-main>
            </el-container>
            <div style="float: right; margin-top: 20px;">
                <el-button type="primary" @click="commitBoard">
                    提交
                </el-button>
            </div>
            <div style="clear: both"></div>

        </el-dialog>

        <div class="page_down">
            <el-pagination style="margin-top:10px;" background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="start" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>
    </div>
    <!--根部分(end)-->
</template>

<script scoped>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
    import ElDialog from "../../../../node_modules/element-ui/packages/dialog/src/component";

    export default {
        components: {
            ElDialog,
            ElInput,
            ElButton
        },
        created: function() {
            var vm = {};
            vm = this;
            //菜单和按钮权限控制 start
            vm.showAddPostBtn = vm.$flagMenuStore.judgeMenu("addPostBtn");
            vm.showDividePermitBtn = vm.$flagMenuStore.judgeMenu("dividePermitBtn");
            vm.showEditPostBtn = vm.$flagMenuStore.judgeMenu("editPostBtn");
            //菜单和按钮权限控制 end
            this.findReLoadUser();

            this.selectAllDept();
        },
        data() {
            return {
                tableHeight: `calc(100vh - 247px)`,
                showMarkBoard: false, //分配看板弹窗
                checked: false,
                deptCode: '',
                deptList: [], // 部门集合
                multipleSelection: [], //选择的集合
                boardList: [{
                    code: 1,
                    label: "所有看板",
                    children: [{
                            code: 1,
                            label: '测试1'
                        },
                        {
                            code: 2,
                            label: '测试2'
                        }
                    ]
                }, ], //看板列表
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },


                floatDivWidth: "",
                roleInList: [],
                showAddPostBtn: false,
                showDividePermitBtn: false,
                showEditPostBtn: false,
                divideRoleVisible: false,
                roleTbList: [{
                    "roleCode": '',
                    "roleName": '',
                }, {
                    "roleCode": '',
                    "roleName": '',
                }, {
                    "roleCode": '',
                    "roleName": '',
                }],
                roleSelectList: [],
                rules: {
                    postName: [{
                        required: true,
                        message: '请输入用户名称',
                        trigger: 'blur'
                    }, ], //输入框校验
                    remark: [{
                        required: true,
                        message: '请输入用户备注',
                        trigger: 'blur'
                    }, ] //输入框校验

                }, //end obj rules 验证对象
                selectUserName: '',
                submitBtnVisible: true,
                dialogFormVisible: false,
                diaForm: {
                    userCode: '',
                    userName: '',
                    remark: '',
                },
                diaUserRoleForm: {
                    userCode: '',
                    userName: '',
                    remark: '',
                },
                userTbList: [{
                        userCode: '',
                        userName: '超级管理员',
                        remark: '上海市普陀区金沙江路 1518 弄',
                    },

                ],

                // 分页
                start: 1,
                pageSize: 50,
                pageSizes: [25, 50, 100, 150],
                total: 0,
            }
        },
        methods: {
            selectAllDept() {
                let vm = this;

                let options = {
                    method: 'POST',
                    data: {},
                    url: "dept/list",
                };
                this.$ajax(
                    options
                ).then(response => {
                    if (!response.data.isException) {
                        vm.deptList = response.data.result;
                        let aObj = {
                            deptCode: "",
                            deptName: "所有部门"
                        };
                        vm.deptList.unshift(aObj);
                    }
                }).catch(error => {
                    vm.$message({
                        showClose: true,
                        message: '查询部门失败',
                        type: 'error'
                    });
                })
            },


            basicQueryOne(codeVal) {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) { //flag留后面表单验证
                    var obj = {};
                    obj.userCode = codeVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {
                            'content-type': 'application/json'
                        },
                        data: sendObj,
                        url: "user/queryOne",
                    };
                    this.$ajax(
                        options
                    ).then(function(response) {
                        vm.roleInList = response.data.result.roles;
                        var tempRoles = [];
                        vm.roleSelectList = [];
                        for (var i = 0; i < vm.roleInList.length; i++) {
                            tempRoles.push(vm.roleInList[i].roleCode);
                        }
                        vm.roleSelectList = tempRoles;
                    }).catch(function(error) {
                        vm.$alert('页面：获取角色数据失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }
            },
            editUserRole() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) { //flag留后面表单验证
                    var obj = {};
                    obj = this.diaUserRoleForm;
                    var sendObj = {};
                    sendObj.entity = obj;
                    sendObj.roleCodes = this.roleSelectList;
                    var options = {
                        method: 'POST',
                        headers: {
                            'content-type': 'application/json'
                        },
                        data: sendObj,
                        url: "user/updateUserRoleList",
                    };
                    this.$ajax(
                        options
                    ).then(function(response) {
                        vm.$message({
                            message: response.data.message,
                            type: 'success'
                        });
                        vm.divideRoleVisible = false;
                    }).catch(function(error) {
                        vm.$alert('页面：更新用户角色数据失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }


            },
            basicReLoadRole() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) { //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = 0;
                    sendObj.pageSize = 50;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {
                            'content-type': 'application/json'
                        },
                        data: sendObj,
                        url: "role/query",
                    };
                    this.$ajax(
                        options
                    ).then(function(response) {
                        vm.roleTbList = response.data.result.data;
                    }).catch(function(error) {
                        vm.$alert('页面：获取数据失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }
            },
            handleDivideRole(index, rowsUser) {
                this.diaUserRoleForm.userCode = rowsUser.userCode;
                this.diaUserRoleForm.userName = rowsUser.userName;
                this.diaUserRoleForm.remark = rowsUser.remark;

                this.divideRoleVisible = true; //打开对话框
                this.submitBtnVisible = false; //隐藏提交按钮,显示保存按钮

                this.basicReLoadRole(); //加载角色下拉框

                this.roleSelectList = [];
                this.basicQueryOne(rowsUser.userCode);

            },
            showAddDialog() {
                this.diaForm = {};
                this.submitBtnVisible = true;
                this.dialogFormVisible = true;

            },
            handleEditPost(index, rowsPost) {
                this.diaForm.postCode = rowsPost.postCode;
                this.diaForm.postName = rowsPost.postName;
                this.diaForm.remark = rowsPost.remark;

                this.dialogFormVisible = true; //打开对话框
                this.submitBtnVisible = false; //隐藏提交按钮,显示保存按钮


            },
            editPost(ruleFormVal) {
                var vm = {};
                vm = this;
                var flag = false;
                //表单验证 start
                this.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {
                        flag = true;
                    } else {
                        vm.$message.error('填写不规范,请检查!');
                        return false;
                    }
                });
                if (flag) { //flag留后面表单验证
                    var obj = {};
                    obj = this.diaForm;
                    var sendObj = {};
                    sendObj.entity = obj;
                    sendObj.menuCodes = [];
                    var options = {
                        method: 'POST',
                        headers: {
                            'content-type': 'application/json'
                        },
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
                        vm.dialogFormVisible = false;
                        vm.basicReLoadUser();
                    }).catch(function(error) {
                        vm.$alert('页面：更新数据失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }
            },
            goPostDivide(index, rowsCus) {
                //                rows.splice(index, 1);
                var sendRuData = rowsCus.postCode;
                this.$router.push({
                    name: 'postDivide', //rutes.js中配置的路由name
                    params: {
                        name: 'ru_postCodeName',
                        ru_postCode: sendRuData,
                    },
                });

            }, //end handleUpdate方法

            addPost(ruleFormVal) {
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
                if (flag) { //flag留后面表单验证
                    var obj = {};
                    obj = this.diaForm;
                    var sendObj = {};
                    sendObj.entity = obj;
                    sendObj.menuCodes = [];
                    var options = {
                        method: 'POST',
                        headers: {
                            'content-type': 'application/json'
                        },
                        data: sendObj,
                        url: "post/create",
                    };
                    this.$ajax(
                        options
                    ).then(function(response) {
                        vm.$message({
                            message: response.data.message,
                            type: 'success'
                        });
                        vm.dialogFormVisible = false;
                        vm.basicReLoadUser();
                    }).catch(function(error) {
                        vm.$alert('页面：新增岗位失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }
            },
            searchEnterFun: function(e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                //'回车搜索', keyCode, e
                if (keyCode == 13) {
                    vm.findReLoadUser();

                }

            },
            findReLoadUser() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) { //flag留后面表单验证
                    var obj = {};
                    obj.userName = vm.selectUserName;
                    obj.deptCode = vm.deptCode;
                    var sendObj = {};
                    sendObj.start = (vm.start - 1) * vm.pageSize;
                    sendObj.pageSize = vm.pageSize;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {
                            'content-type': 'application/json'
                        },
                        data: sendObj,
                        url: "user/query",
                    };
                    this.$ajax(
                        options
                    ).then(function(response) {
                        vm.userTbList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }).catch(function(error) {
                        vm.$alert('页面：获取用户数据失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }
            },
            basicReLoadUser() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) { //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = 0;
                    sendObj.pageSize = 50;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {
                            'content-type': 'application/json'
                        },
                        data: sendObj,
                        url: "user/query",
                    };
                    this.$ajax(
                        options
                    ).then(function(response) {
                        vm.userTbList = response.data.result.data;
                    }).catch(function(error) {
                        vm.$alert('页面：获取用户数据失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }
            },

            checkBox(checked, rows) {
                if (checked) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row);
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },

            handleSelectionChange(val) {
                this.multipleSelection = [];
                if (val) {
                    val.forEach(index => {
                        this.multipleSelection.push({
                            userCode: index.userCode,
                            userName: index.userName
                        });
                    })
                }
            },

            //分配看板
            markBoard() {
                this.boardInfo();
            },

            // 获取看板信息
            boardInfo() {
                let vm = this;
                let params = {
                    entity: {}
                }
                let options = {
                    data: params,
                    method: "POST",
                    url: "program/list"
                }
                this.$ajax(
                    options
                ).then(res => {
                    let result = res.data.result;
                    vm.boardList[0].children = [];
                    for (let i = 0; i < result.length; i++) {
                        vm.boardList[0].children.push({
                            code: result[i].pgCode,
                            label: result[i].pgName
                        });
                    }
                    this.showMarkBoard = true;
                }).catch(error => {
                    vm.$alert('页面:获取看板信息异常', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    })
                })
            },

            //添加看板
            commitBoard() {
                let vm = this;
                let checkedBoardArr = this.$refs.boardTree.getCheckedKeys();
                let board = checkedBoardArr.filter(item => {
                    return item != 1;
                });
                if (board.length < 1 || vm.multipleSelection.length < 1) {
                    this.$message({
                        message: '请选择用户或看板',
                        type: 'warning'
                    });
                    return;
                }
                let checkedBoard = board.join(",");

                let checkedUserList = [];
                vm.multipleSelection.forEach(item => {
                    checkedUserList.push(item.userCode);
                })
                let checkUser = checkedUserList.join(",");
                let params = {
                    entity: {
                        pgCode: checkedBoard,
                        createCode: checkUser
                    }
                }
                let options = {
                    method: "POST",
                    data: params,
                    url: "board/batchCreate"
                }
                this.$ajax(
                    options
                ).then(response => {
                    vm.beforeCloseBoard();
                    this.$message({
                        message: '添加成功',
                        type: 'success'
                    });
                }).catch(error => {
                    vm.$alert('页面:添加看板异常', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    })
                })
            },

            beforeCloseBoard() {
                this.resetBoardChecked();
                this.showMarkBoard = false;
            },

            //清空看板选择框
            resetBoardChecked() {
                this.$refs.boardTree.setCheckedKeys([]);
            },

            /*---------------------分页start---------------------*/
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.findReLoadUser(1);
            },
            handleCurrentChange(val) {
                this.start = val;
                this.findReLoadUser();
            },
            /*---------------------分页end----------------------*/
        }
    }
</script>

<style scoped>
    /*导航栏返回按钮*/
    .navigation_button_back {
        float: left;
        margin-top: 10px;
        margin-left: 20px;
    }

    .has-position_main {
        width: 100%;
    }

    .navigation_button_input {
        float: left;
        margin-left: 20px;
        width: 300px;
    }

    /*导航栏标题*/
    .navigation_title {
        float: left;
        height: 60px;
        margin-top: -55px;
        margin-left: 10px;
        color: #FE980F;
        z-index: 300;
    }

    /*新增页样式*/
    .konge {
        min-height: 45px;
        min-width: 1000px;
        background-color: rgb(242, 242, 242);
    }

    .navigation_select {
        margin-left: 20px;
    }

    .navigation_div_2 {
        height: 50px;
        margin-top: 9px;
        background-color: white;
        z-index: 200;
        float: left;
        width: 100%;
        border-bottom: 1px solid #f2f2f2;
    }

    .mark_board {
        margin-top: 10px;
        float: left;
        margin-left: 20px;
    }

    .personDiv {
        float: left;
        width: 30%;
    }

    .boardDiv {
        float: right;
    }

    .centDiv {
        float: left;

        width: 50px;

        border-right: 1px solid black;

        padding-bottom: 1600px;
        /*关键*/

        margin-bottom: -1600px;
        /*关键*/
    }

    .user_p {
        text-align: left;
        left: 45px;
        position: absolute;
        line-height: 40px;
        top: 56px;
        height: 70px;
        width: 100px;
        background-color: #fff;
    }

    .user_list {
        clear: both;
        text-align: left;
        margin-top: 78px
    }

    .board_p {


        position: absolute;
        line-height: 38px;
        top: 56px;
        height: 70px;
        width: 350px;
        background-color: rgb(242, 242, 242);
        z-index: 1000;
        text-align: left;
    }

    /*分页*/
    .page_down {
        text-align: left;
        background-color: rgb(242, 242, 242);
        height: 50px;
        line-height: 50px;
        clear: both;
        display: block;
        margin: 0px auto;
        position: fixed;
        bottom: 0px;
        width: 100%;
        z-index: 10;
    }
</style>