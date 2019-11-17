<template>
    <div class="not-position_root" ref="myRoot">
        <!-- 悬浮框 -->
        <div class="not-position_header">
            <!-- 部门筛选组件 -->
            <dept-selector @sendDeptCode="getDeptCode" class="navigate-select"></dept-selector>

            <el-select size="small" filterable v-model="selectParams.empCode"
                       placeholder="请选择招商人员" class="navigate-select">
                <el-option v-for="item in empList" :key="item.empCode" :label="item.empName" :value="item.empCode">
                </el-option>
            </el-select>

            <el-date-picker size="small" class="navigate-select-date" v-model="selectParams.dateSelect" type="daterange"
                            align="right" unlink-panels range-separator="至" start-placeholder="开始日期"
                            end-placeholder="结束日期" :picker-options="pickerOptions2">
            </el-date-picker>
            <el-button type="primary" size="small" class="navigate-select-button" @click="queryTaskList(1)"
                       icon="el-icon-search">查询
            </el-button>

            <el-button class="nevigate-add-button" size="small" type="primary" @click="addTask()"
                       v-if="showAddAtiTaskBtn" icon="el-icon-plus">
                添加任务
            </el-button>
        </div>

        <el-table size="small" :height="tableHeight" :data="taskList"  border>
            <el-table-column align="center" label="序号" type="index" width="60px">
            </el-table-column>
            <el-table-column label="招商人员" width="70px" align="center" prop="empName">
            </el-table-column>
            <el-table-column label="所属部门" width="110px" align="center" prop="createDeptName">
            </el-table-column>
            <el-table-column label="负责园区" align="center" prop="pkName">
            </el-table-column>
            <el-table-column label="日期" width="100px" align="center">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.taskTime | timeDate }}</span>
                </template>
            </el-table-column>
            <el-table-column label="横幅" width="60px" align="center" prop="banner">
            </el-table-column>
            <el-table-column label="宣传单" width="60px" align="center" prop="leaflets">
            </el-table-column>
            <el-table-column label="朋友圈" width="60px" align="center" prop="friendsShare">
            </el-table-column>
            <el-table-column label="今日描述" align="center" min-width="300px" prop="description">
            </el-table-column>
        </el-table>

        <div class="page_div" :style="{width:floatDivWidth+'px'}">
            <el-pagination background
                           @size-change="handleSizeChange"
                           @current-change="handleCurrentChange"
                           :current-page.sync="currentPage"
                           :page-sizes="[8, 16, 32]"
                           :page-size="pageSize"
                           layout="total, sizes, prev, pager, next, jumper"
                           :total="totalCount">
            </el-pagination>
        </div>

        <!-- 添加任务 -->
        <el-dialog title="新增每日任务" :visible.sync="todayTaskWindow" width="70%">
            <el-form ref="taskObj" :rules="rules" :model="taskObj" label-width="110px">

                <div class="add_today_task_div">
                    <el-row>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="日期：" style="text-align: left">
                                <span style="text-align: left">{{new Date().format("yyyy-MM-dd")}}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="招商人员姓名：" style="text-align: left">
                                <span>{{taskObj.empName}}{{taskObj.parkAddress}}</span>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
                            <el-form-item label="负责园区" prop="pkCode" style="text-align: left;">
                                <el-select v-model="taskObj.pkCode" @change="getPkName(taskObj.pkCode)"
                                           style="line-height: 30px;" filterable placeholder="请选择负责园区">
                                    <el-option v-for="item in parkList" :key="item.pkCode" :label="item.pkName"
                                               :value="item.pkCode">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label="横幅" prop="banner">
                                <el-input v-model="taskObj.banner">
                                    <template slot="append">条</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label="宣传单" prop="leaflets">
                                <el-input v-model="taskObj.leaflets">
                                    <template slot="append">张</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label="微信朋友圈" prop="friendsShare">
                                <el-input v-model="taskObj.friendsShare">
                                    <template slot="append">条</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                            <el-form-item label="今日任务描述" prop="description">
                                <el-input :rows="4" type="textarea" v-model="taskObj.description"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>
            </el-form>
            <div slot="footer" style="margin-top: -30px;">
                <el-button @click="resetForm('taskObj')">取 消</el-button>
                <el-button type="primary" @click="todayTaskSubmit('taskObj')">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    import deptSelector from '@/common/components/deptSelector.vue'

    export default {
        components: {
            deptSelector,
        },
        data() {
            // 校验数字只能为整数
            let checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('只能为正整数'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {
                tableHeight: this.$store.state.tableHeight,
                showAddAtiTaskBtn: false,// 招商人员日常任务添加按钮，权限控制
                floatDivWidth: '',// 悬浮栏宽度控制

                taskList: [],// 表格数据绑定
                empList: [],// 所有人员
                deptList: [],// 所有部门
                empObj: {},// 人员实体

                // 悬浮栏查询所需参数
                selectParams: {
                    empCode: '',
                    deptCode: '',
                    dateSelect: [],// 时间选择组件
                },

                // 分页数据
                pageSize: 16,// 默认每页条数
                totalCount: 10,// 总条数
                currentPage: 1,// 当前页数

                // 时间选择组件，快捷选择数据
                pickerOptions2: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },

                /*--------------------------------- 添加日常任务 ---------------------------------*/
                parkList: [],// 园区集合
                parkObj: {},// 园区实体
                todayTaskWindow: false,// 添加今日任务弹窗

                postObj: {
                    postCode: ''
                },
                taskObj: {
                    leaflets: '',
                    friendsShare: '',
                    banner: '',
                },
                rules: {
                    pkCode: [
                        {required: true, message: '请选择园区', trigger: 'blur'},
                    ],
                    leaflets: [
                        {required: true, message: '宣传单输入不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    friendsShare: [
                        {required: true, message: '微信朋友圈输入不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    banner: [
                        {required: true, message: '横幅输入不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    description: [
                        {required: true, message: '今日描述不能为空', trigger: 'blur'},
                    ],
                },
            }
        },
        created: function () {
            let vm = this;

            // 添加任务按钮权限控制
            vm.showAddAtiTaskBtn = vm.$flagMenuStore.judgeMenu("addAtiTaskBtn");

            // 查询所有招商人员
            vm.queryEmp();

            // 查询所有任务列表
            let param = {};
            param.entity = {};
            param.pageSize = vm.pageSize;// 每页条数
            param.start = 0;
            vm.queryTaskList(0, param);
        },
        methods: {
            // 分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.currentPage = 1;
                this.handleCurrentChange(1);
            },
            // 分页
            handleCurrentChange(val) {
                let vm = {};
                vm = this;
                let param = {};
                param.entity = {};
                param.entity.empCode = vm.selectParams.empCode;
                if (vm.selectParams.dateSelect && vm.selectParams.dateSelect.length != 0) {
                    param.entity.taskTimeList = vm.selectParams.dateSelect;
                }
                param.entity.createDeptCode = vm.selectParams.deptCode;

                param.pageSize = vm.pageSize;
                param.start = (val - 1) * (param.pageSize);

                vm.queryTaskList(2, param);
            },

            // 查询所有任务列表
            queryTaskList(flag, paramObj) {
                let vm = this;

                let param = {};

                if (flag == 0) {
                    param = paramObj;
                }

                // 根据empCode查询日常任务
                if (flag == 1) {
                    param.entity = {};
                    param.entity.empCode = vm.selectParams.empCode;
                    if (vm.selectParams.dateSelect && vm.selectParams.dateSelect.length != 0) {
                        param.entity.taskTimeList = vm.selectParams.dateSelect;
                    }
                    param.entity.createDeptCode = vm.selectParams.deptCode;
                    param.pageSize = vm.pageSize;// 每页条数
                    param.start = 0;
                }

                // 分页调用
                if (flag == 2) {
                    param = paramObj;
                }
                let option = {
                    method: 'POST',
                    data: param,
                    url: 'task/query'
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if (response) {
                        vm.taskList = response.data.result.data;
                        vm.totalCount = response.data.result.totalCount;
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '查询区域信息失败',
                        type: 'error'
                    });
                });
            },

            // 查询所有招商人员
            queryEmp() {
                let vm = this;

                let param = {
                    entity: {}
                };

                let option = {
                    method: 'POST',
                    data: param,
                    url: 'employee/list'
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.empList = response.data.result;
                        let aObj = {empCode: "", empName: "全部人员"};
                        vm.empList.unshift(aObj);
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '查询人员信息失败',
                        type: 'error'
                    });
                });
            },

            // 获取组件传递的deptCode
            getDeptCode(data) {
                let vm = this;
                vm.selectParams.deptCode = data;
            },

            /*------------------------------ 添加日常任务 ------------------------------*/
            // 添加任务按钮
            addTask() {
                let vm = this;

                vm.queryAllPark();// 查询所有园区

                // 获取当前登陆人员信息
                vm.postObj.postCode = vm.$cookieStore.getCookie("postCode");
                vm.taskObj.empCode = vm.$cookieStore.getCookie("empCode");
                vm.taskObj.empName = vm.$cookieStore.getCookie("userName");

                vm.todayTaskWindow = true;

            },
            // 查询所有园区
            queryAllPark() {
                let vm = this;

                let options = {
                    method: 'POST',
                    data: {},
                    url: 'park/list'
                };
                this.$ajax(
                    options
                ).then(response => {
                    if (!response.data.isException) {
                        vm.parkList = response.data.result;
                        let parkObj = {};
                        for (let i = 0; i < vm.parkList.length; i++) {
                            let pkCode = vm.parkList[i].pkCode;
                            let pkName = vm.parkList[i].pkName;
                            parkObj[pkCode] = pkName;
                        }
                        vm.parkObj = parkObj;
                    }
                }).catch(error => {
                    vm.$alert('页面：园区查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },
            // 获取园区名称
            getPkName(pkCode) {
                this.taskObj.pkName = this.parkObj[pkCode];
            },
            // 表单重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.todayTaskWindow = false;
            },
            // 提交任务新增
            todayTaskSubmit(subForm) {
                let vm = this;

                let param = {};
                param.entity = vm.postObj;

                this.$refs[subForm].validate((valid) => {
                    if (valid) {
                        let params = {};
                        params.entity = vm.taskObj;
                        let option = {
                            method: 'POST',
                            data: params,
                            url: "task/insert",
                        };
                        vm.$ajax(
                            option
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '添加每日任务成功',
                                    type: 'success',
                                });
                                vm.resetForm(subForm);

                                // 查询所有任务列表
                                let param = {};
                                param.entity = {};
                                param.pageSize = vm.pageSize;// 每页条数
                                param.start = 0;
                                vm.queryTaskList(0, param);
                            }
                        }).catch(error => {
                            vm.$message({
                                showClose: true,
                                message: '添加每日任务失败',
                                type: 'error'
                            });
                        })
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                });
            },
        },
    }
</script>

<style scoped>
    .root {
        min-width: 1000px;
        min-height: 790px;
    }

    /*悬浮栏 div*/
    .nevigate-box-div {
        margin: -50px 0 0 0;

        position: fixed;
        z-index: 200;
    }

    /*悬浮栏 select选择器*/
    .navigate-select {
        /*margin: 5px 0 0 20px;*/
        margin-left: 20px;
        width: 150px;
        float: left;
    }

    /*日期选择器*/
    .navigate-select-date {
        /*margin: 10px 0 0 20px;*/
        margin-left: 20px;
        width: 250px;
        float: left;
    }

    /*查询按钮*/
    .navigate-select-button {
        float: left;
        /*margin: 10px 0 0 20px;*/
        margin-left: 20px;
    }

    /*悬浮栏 添加任务按钮*/
    .nevigate-add-button {
        margin-right: 20px;
        float: right;
        /*margin-top: 10px;*/
    }

    /*表格样式*/
    .table-style {
        line-height: 20px;
        border-top: white solid 1px;
    }

    /*分页栏*/
    .page_div {
        height: 40px;
        padding-top: 10px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }

    /*添加日常任务*/
    .add_today_task_div {
        background-color: white;
        margin-top: -20px;
    }
</style>