<template>
    <div class="not-position_root" ref="myRoot">

        <!-- 悬浮框 -->
        <div class="not-position_header">
            <el-input size="small"  clearable  style="width: 285px;margin-left: 20px"
                      @keyup.enter.native="searchEnterFun"
                      v-model="keyword"
                      placeholder="输入单号、合同编号或申请人查询">
                <el-button slot="append" icon="el-icon-search" @click="searchCon"></el-button>
            </el-input>
            <el-select class="select-style" filterable placeholder="请选择部门" v-model="createDeptCode" size="small" @change="searchCon">
                <el-option v-for="item in deptList"
                           :key="item.createDeptCode"
                           :label="item.createDeptName"
                           :value="item.createDeptCode">
                </el-option>
            </el-select>
            <el-select class="select-style" filterable placeholder="请选择状态" v-model="ldCtStatus" size="small" @change="searchCon">
                <el-option v-for="item in statusList"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
            </el-select>
        </div>

        <el-form>
            <!-- 表格化信息 -->
            <el-table
                    class="cost_table"
                    :data="applyList"
                    border
                    :highlight-current-row="true"
                    size="small"
                    :height="tableHeight">

                <el-table-column
                        align="center"
                        label="序号"
                        type="index">
                </el-table-column>
                <el-table-column
                        prop="ldCtName"
                        label="合同编号"
                        width="180"
                        align="center">
                </el-table-column>
                <el-table-column
                        label="申请人"
                        prop="createName"
                        align="center">
                </el-table-column>
                <!--流程实例id-->
                <el-table-column
                        label="单号"
                        prop="processInstanceId"
                        align="center">
                </el-table-column>
                <el-table-column
                        label="申请内容"
                        align="center">
                    <template slot-scope="scope">
                        {{"【" + scope.row.processInstanceId + "】" + scope.row.landlordName + "- 大房东合同"}}
                    </template>
                </el-table-column>
                <el-table-column
                        label="申请时间"
                        align="center">
                    <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                </el-table-column>
                <el-table-column
                        label="审批状态"
                        prop="ldCtStatusName"
                        align="center">
                </el-table-column>
                <el-table-column
                        label="流程定义ID"
                        prop="processDefinitionId"
                        align="center">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button size="mini" @click="showDetail(scope.row.ldCode)" type="text">详情</el-button>
                        <el-button @click="showVerifyLogInfo(scope.row)" type="text" size="small">审核详情</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <div class="page_down">
                <el-pagination style="margin-top:10px;"
                               background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="start"
                               :page-sizes="pageSizes"
                               :page-size="pageSize"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total">
                </el-pagination>
            </div>

            <!--审批详情-->
            <verify-log v-if="showVerifyLog" @backParam="backParamLog($event)" :paramsLog="paramsLog"></verify-log>
            <!-- 退款单详情 -->
            <throw-lease-pay-detail v-if="showthrowLeasePayApplyDetail" @backParam="backParamPayDetail($event)"
                                    :paramsPayObjDetail="paramsPayObjDetail"></throw-lease-pay-detail>
        </el-form>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import verifyLog from './workflowTemplate/landlordVerifyLog.vue';
    import throwLeasePayDetail from './workflowTemplate/throwLeasePayDetail.vue';

    export default {
        components: {
            ElCol,
            ElRow,
            verifyLog,
            throwLeasePayDetail},
        props: ['paramsObj'],// 接收父组件数据
        created: function () {
            let vm = this;
            vm.searchCon();
            vm.queryDept();

        },
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,

                rentPyWindow:false, //缴费窗口
                paramsRentPay:{},
                showVerifyLog: false,//审核记录窗口
                paramsLog: {},
                paramsPayObjDetail: {},
                showthrowLeasePayApplyDetail: false, //申请详情

                floatDivHeight: "",
                keyword:'',
                createDeptCode:'',
                ldCtStatus:'',
                deptList:[],
                applyList:[],

                // 分页
                start: 1,
                pageSize: 50,
                pageSizes: [25, 50, 100, 150],
                total: 0,

                statusList: [
                    {
                        value: '',
                        label: '全部状态'
                    }, {
                        value: '1',
                        label: '待提交'
                    }, {
                        value: '2',
                        label: '一审提交'
                    }, {
                        value: '3',
                        label: '一审通过'
                    }, {
                        value: '4',
                        label: '二审通过'
                    },{
                        value: '5',
                        label: '驳回'
                    },{
                        value: '6',
                        label: '撤回'
                    }, {
                        value: '7',
                        label: '已退租'
                    }, {
                        value: '8',
                        label: '已改签'
                    },
                ],


            }
        },
        methods: {
            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                if (keyCode == 13) {
                    vm.handleCurrentChange(1);
                }
            },

            searchCon(){
                this.handleCurrentChange(1);
            },

            /*---------------------分页start---------------------*/
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.queryLandLordApply(1);
            },
            handleCurrentChange(val) {
                this.start = val;
                this.queryLandLordApply();
            },
            /*---------------------分页end----------------------*/

            //查询申请列表
            queryLandLordApply() {
                let vm = this;

                let param = {
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize,
                    keyword: vm.keyword,
                    entity: {
                        createDeptCode:vm.createDeptCode,
                        ldCtStatus:vm.ldCtStatus,
                    }
                };

                let options = {
                    method: "POST",
                    data: param,
                    url: "landlordRentApply/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.applyList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：退租列表查询信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            // 查询部门
            queryDept(){
                let vm = this;

                let param = {
                    entity: {}
                };

                let option = {
                    method: 'POST',
                    data: param,
                    url: 'landlordRentApply/getDept'
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.deptList = response.data.result;
                        let aObj = {createDeptCode: '', createDeptName: '所有部门'};
                        vm.deptList.unshift(aObj);
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '查询部门信息失败',
                        type: 'error'
                    });
                });
            },

            //审核详情
            showVerifyLogInfo(row){
                let vm = this;
                vm.showVerifyLog = true;
                vm.paramsLog.processInstanceId = row.processInstanceId;
                vm.paramsLog.createName = row.createName;
                vm.paramsLog.showVerifyLog = vm.showVerifyLog;
            },
            //审核记录窗口回调
            backParamLog(val){
                this.showVerifyLog = val.showVerifyLog;
            },

            //打款申请详情
            showDetail(ldCode){
                let data = {
                    menuName: '大房东合同详情',
                    item: 'landlordContractDetail',
                    params: {
                        ldCode: ldCode
                    }
                };
                this.$emit('backParam', data);
            },

            //退款详情和退款处理弹窗子组件回调
            backParamPayDetail(val) {
                this.showthrowLeasePayApplyDetail = val.showthrowLeasePayApplyDetail;
            },
        }
    }

</script>

<style scoped>

    body {
        margin-left: 200px;
        margin-right: 200px;
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

    /*表格*/
    .cost_table{
        text-align: center;
        width: 100%;
    }

    .select-style{
        margin-left: 20px;
        width: 150px;
    }
</style>