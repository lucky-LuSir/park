<template>
    <div>
        <el-card shadow="hover">
            <div slot="header" style="height: 30px; line-height: 30px; text-align: left;">
                <el-row :gutter="10">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <span class="card_title_font">招商人员去化看板：{{ deptName }}</span>
                        <el-date-picker style="margin-left: 20px;width: 230px;"
                                        v-model="dateChangeList"
                                        type="daterange"
                                        align="right"
                                        size="small"
                                        unlink-panels
                                        :clearable="false"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期">
                        </el-date-picker>
                        <el-button style="margin-left: 20px" size="small" type="primary" @click="selectBoard">查询</el-button>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-button class="edit-button-delete" size="small" type="primary" @click="deleteBoard">删除</el-button>
                        <el-button class="edit-button-delete" size="small" type="primary" @click="editBoard">配置</el-button>
                    </el-col>
                </el-row>
            </div>

            <el-table :data="empBiTableList"
                      max-height="400"
                      style="line-height: 20px;"
                      border>
                <el-table-column align="center"
                                 label="排名"
                                 prop="rankNum">
                </el-table-column>

                <el-table-column align="center"
                                 label="姓名"
                                 prop="empName">
                </el-table-column>

                <el-table-column align="center"
                                 label="电话"
                                 prop="empPhone">
                </el-table-column>

                <el-table-column align="center"
                                 label="负责园区"
                                 prop="pkName">
                </el-table-column>

                <el-table-column align="center"
                                 label="获客数"
                                 prop="cusCount">
                </el-table-column>

                <el-table-column align="center"
                                 label="所有谈判中"
                                 prop="cusNegotiateCount">
                </el-table-column>

                <el-table-column align="center"
                                 label="入驻客户"
                                 prop="cusEntryCount">
                </el-table-column>

                <el-table-column align="center"
                                 label="去化面积"
                                 prop="signAreaMonth">
                </el-table-column>
            </el-table>
        </el-card>

        <!-- 部门参数弹框 -->
        <el-dialog :title="dialogTitle" :visible.sync="isShowDeptParam">
            <el-form ref="deptParam" :rules="rules" :model="deptParam" label-width="80px" style="text-align: left;">
                <el-form-item label="选择部门" prop="deptCode">
                    <el-select
                            v-model="deptParam.deptCode"
                            clearable
                            filterable
                            default-first-option
                            placeholder="输入部门名称">
                        <el-option
                                v-for="item in deptNames"
                                :key="item.code"
                                :label="item.name"
                                :value="item.code">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="onDeptParamSubmit('deptParam')">立即创建</el-button>
                    <el-button @click="isShowDeptParam= false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script scoped>
    export default {
        props: ['id', 'param'],
        components: {},
        created: function () {
            let sendObj = {};
            sendObj.start = 0;
            sendObj.pageSize = 50;
            sendObj.entity = {};
            sendObj.deptCode = this.param;

            this.queryList(sendObj, 1);
        },
        data() {
            return {
                isShowDeptParam: false,
                dialogTitle: '',
                deptNames: [],// 配置按钮集合
                deptParam: {
                    deptCode: ''
                },
                dateChangeList: [],// 日期选择组件绑定数据
                empBiTableList: [],// 表格数据绑定
                deptName: '',

                rules: {
                    deptCode: [
                        {required: true, message: '部门名称不能为空', trigger: 'blur'}
                    ],
                },
            }
        },
        methods: {
            queryList(sendObj, flag){
                let vm = this;

                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "empBi/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.empBiTableList = response.data.result.empBiList;

                    if(flag == 1){
                        let firstDate = response.data.result.firstDate;
                        let endDate = response.data.result.endDate;
                        vm.dateChangeList.push(firstDate, endDate);
                    }
                }).catch(function (error) {
                    vm.$alert('页面：招商人员统计数据失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });

                let param = {};
                param.entity = {
                    deptCode: this.param,
                };
                let deptOptions = {
                    method: 'POST',
                    data: param,
                    url: "dept/findByCode",
                };
                this.$ajax(
                    deptOptions
                ).then(function (response) {
                    vm.deptName = response.data.result.deptName;
                }).catch(function (error) {
                    vm.$alert('页面:招商人员数据看板获取部门名称数据失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            // 日期组件，查询按钮
            selectBoard(){
                let sendObj = {};
                sendObj.start = 0;
                sendObj.pageSize = 50;
                sendObj.entity = {
                    dateStartAndEndList : this.dateChangeList,
                };
                sendObj.deptCode = this.param;
                this.queryList(sendObj);
            },

            // 通过id删除board
            deleteBoard(){
                this.$confirm('此操作将永久删除该看板, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let param = {};
                    let entity = {
                        id: this.id,
                    };
                    param.entity = entity;

                    let options = {
                        method: 'POST',
                        data: param,
                        url: "board/delete",
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        vm.$message({
                            showClose: true,
                            message: '删除看板成功',
                            type: 'success'
                        });
                        vm.backParent();
                    }).catch(error => {
                        vm.$alert('页面:删除招商人员数据看板失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

            // 配置按钮
            editBoard() {
                this.dialogTitle = "招商人员数据看板参数配置";
                let vm = this;
                vm.isShowDeptParam = true;
                //部门搜索栏
                let deptObj = {};
                let deptOptions = {
                    method: 'POST',
                    data: deptObj,
                    url: "dept/list",
                };
                this.$ajax(
                    deptOptions
                ).then(function (response) {
                    let result = response.data.result;
                    let deptNameList = [];
                    let deptObj = [];
                    for (let s = 0; s < result.length; s++) {
                        let code = result[s].deptCode;
                        let name = result[s].deptName;
                        deptNameList.push(
                            {code: code, name: name}
                        );
                        deptObj[code] = name;
                    }
                    vm.deptNames = deptNameList;
                    vm.deptObj = deptObj;
                }).catch(function (error) {
                    vm.$alert('页面:招商人员数据看板获取部门数据失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
                vm.deptParam.deptCode = vm.param;
            },

            onDeptParamSubmit(subForm) {
                this.$refs[subForm].validate((valid) => {
                    if (valid) {
                        this.isShowDeptParam = false;

                        let vm = this;
                        //房源搜索栏
                        let param = {};
                        let entity = {
                            id: vm.id,
                            param: vm.deptParam.deptCode
                        };
                        param.entity = entity;

                        let options = {
                            method: 'POST',
                            data: param,
                            url: "board/update",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$message({
                                showClose: true,
                                message: '配置看板成功',
                                type: 'success'
                            });
                            vm.backParent();
                        }).catch(function (error) {
                            vm.$alert('页面:招商人员数据看板配置失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                });
            },
            backParent() {
                this.$emit("backParam","1");
            },
        }
    }
</script>

<style scoped>
    .root {
        margin-right: 15px;
        width: 100%;
        height: 100%;
        background-color: white;

        overflow-x: hidden;
        overflow-y: hidden;
    }

    /*删除按钮*/
    .edit-button-delete {
        float: right;
        margin-right: 10px;
    }

    /*卡片标题*/
    .card_title_font {
        font-weight: bold;
        color: #409EFF
    }
</style>