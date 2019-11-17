<template>
    <div class="has-position_root" ref="myRoot">
        <div class="has-position_header">
            <el-input v-model="keyword" @keyup.enter.native="searchEmp('keyword')" placeholder="支持人员姓名、部门、岗位搜索" class="input-with-select" size="small" clearable>
                <el-button slot="append" icon="el-icon-search" @click="searchEmp()"></el-button>
            </el-input>
            <el-button v-if="showAddEmployeeBtn" class="add-button" type="primary" @click="addEmp()" size="small">
                人员新增
            </el-button>
        </div>

        <el-form class="has-position_main">
            <el-card class="box-card" shadow="hover" v-for="(item,index) in empList" :key="index">
                <div style="float: left; margin-top: -10px;">
                    <el-avatar v-if="item.empImg" shape="circle" :size="80" fit="cover"
                               :src="parkBaseUrl + 'file/read/' + item.empImg">
                    </el-avatar>
                    <el-avatar v-if="!item.empImg" shape="circle" :size="80">
                        <span style="font-size: 25px;">user</span>
                    </el-avatar>
                </div>

                <div style="float: left; margin-left: 20px; width: 80%; margin-top: -5px;">
                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="emp-info-col">
                            <el-form-item>
                                <span class="name-style">{{ item.empName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="emp-info-col" v-if="item.cpyCode == '180904868bbcb902de4b'">
                            <div class="grid-content">
                                <el-form-item label="工号：">
                                    <span class="name-style1">{{ item.workNumber }}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="emp-info-col">
                            <el-button class="view-detail" type="text" @click="empDetails(item.empCode)">
                                查看详情
                            </el-button>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="emp-info-col">
                            <el-form-item label="部门名称：">
                                <span class="name-style1">{{ item.deptName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="emp-info-col">
                            <el-form-item label="岗位职称：">
                                <span class="name-style1">{{ item.postName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="emp-info-col">
                            <el-form-item label="部门名称：">
                                <span class="name-style1">{{ item.deptName }}</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>
            </el-card>

            <div style="height: 50px; width: 10px;"></div>

            <div class="emp-page-div">
                <el-pagination style="padding-top: 10px;"
                        background
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page.sync="currentPage"
                        :page-sizes="[6, 8, 16, 32]"
                        :page-size="pageSize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="totalCount">
                </el-pagination>
            </div>
        </el-form>
    </div>
</template>

<script>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElOption from "../../../../node_modules/element-ui/packages/select/src/option";

    export default{
        components: {ElOption},
        data(){
            return {
                showAddEmployeeBtn: false,
                empList: [
                    {
                        empCode: "",
                        empName: "",
                        empPhone: "",
                        deptCode: "",
                        deptName: "",
                        postCode: "",
                        postName: ""
                    },
                ],
                keyword: "",// 搜索框关键字
                pageSize: 8,// 默认每页条数
                totalCount: 10,// 总条数
                currentPage: 1// 当前页数
            }
        },
        created: function () {
            let vm = this;

            // 菜单和按钮权限控制
            vm.showAddEmployeeBtn = vm.$flagMenuStore.judgeMenu("addEmployeeBtn");

            let pageData = {};
            pageData.pageSize = vm.pageSize;// 每页条数
            pageData.start = 0;

            vm.queryListTemplate(vm,pageData);
        },
        methods: {
            // 搜索框
            searchEmp(){
                this.handleCurrentChange(1);
            },

            // 分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.currentPage = 1;
                this.handleCurrentChange(1);
            },
            handleCurrentChange(val) {
                let vm = this;

                let pageData = {};
                pageData.pageSize = vm.pageSize;
                pageData.start = (val - 1) * (pageData.pageSize);
                pageData.keyword = this.keyword;

                vm.queryListTemplate(vm,pageData);
            },

            // 查询人员列表模板
            queryListTemplate(vm,pageData){
                let options = {
                    method: "POST",
                    data: pageData,
                    url: "employee/query"
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.totalCount = response.data.result.totalCount;
                        vm.empList = response.data.result.data;
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:人员列表加载失败!employee/query',
                        type: 'error'
                    })
                })
            },

            // 添加人员
            addEmp(){
                let data = {
                    menuName: "人员新增",
                    item: "empCreate",
                    params: {}
                };
                this.$emit("backParam", data);
            },

            // 查看详情
            empDetails(empCode){
                let data = {
                    menuName: "人员详情",
                    item: "empDetails",
                    params: {
                        empCode: empCode
                    }
                };
                this.$emit("backParam", data);
            }
        },
    }
</script>

<style scoped>
    .root_form {
        height: 80vh;
        overflow-y: auto;
        overflow-x: hidden;
    }

    /*搜索框*/
    .input-with-select {
        width: 300px;
        float: left;
        margin-left: 20px;
    }

    /*新增按钮*/
    .add-button {
        float: left;
        margin-left: 20PX;
    }

    /******************************************************************/
    /*卡片*/
    .box-card {
        margin-top: 10px;
    }

    /*姓名  样式*/
    .name-style {
        font-weight: bold;
        font-size: 18px;
    }

    /*查看详情按钮*/
    .view-detail {
        font-weight: bold;
        font-size: 18px;
        color: #409EFF;
        float: left;
    }

    /*行样式*/
    .emp-info-col {
        height: 30px;
        line-height: 30px;
    }

    /*分页*/
    .emp-page-div {
        text-align: left;
        background-color: rgb(242,242,242);
        height: 50px;
        line-height: 50px;
        display: block;
        position: fixed;
        bottom: 0px;
        width: 100%;
    }
</style>