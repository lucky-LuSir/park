<template>
    <div class="not-position-notPaging_root" ref="myRoot">

        <div class="not-position_header">
            <el-button type="primary" class="add_button" size="small" icon="el-icon-plus" @click="createApply()">退租申请</el-button>
        </div>

        <el-form>
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="退租单待处理" name="first">
                    <el-table :data="waitApprovalList" border size="small" :height="tableHeight" style="width: 100%;line-height: 30px;margin-top: 0px;">
                        <el-table-column align="center" label="序号" type="index">
                        </el-table-column>
                        <el-table-column prop="ctName" label="合同编号" align="center">
                        </el-table-column>
                        <el-table-column label="申请人" prop="createName" align="center">
                        </el-table-column>
                        <!--流程实例id-->
                        <el-table-column label="单号" prop="executionId" align="center">
                        </el-table-column>
                        <el-table-column label="申请时间" align="center">
                            <template slot-scope="scope">{{scope.row.createTime | time}}</template>
                        </el-table-column>
                        <el-table-column label="审批状态" prop="approvalStatusName" align="center">
                        </el-table-column>
                        <el-table-column label="乙方" prop="partyB" align="center">
                        </el-table-column>
                        <el-table-column label="任务ID" prop="taskId" align="center">
                        </el-table-column>
                        <el-table-column show-overflow-tooltip prop="approveName" label="审核节点" align="center">
                        </el-table-column>
                        <el-table-column min-width="90" label="流程定义ID" prop="processDefinitionId" align="center">
                        </el-table-column>
                        <el-table-column min-width="100" label="任务创建时间" align="center">
                            <template slot-scope="scope">{{scope.row.taskCreateTime | time}}</template>
                        </el-table-column>
                        <el-table-column width="150px" align="center" label="操作">
                            <template slot-scope="scope">
                                <div v-if="scope.row.approvalStatus == '4'">
                                    <el-button v-if="scope.row.assignee" size="mini" @click="editProcess(scope.row)" type="text">调整
                                    </el-button>
                                    <el-button size="mini" @click="endFlow(scope.row.id,scope.row.applyCode,scope.row.ctCode,scope.row.processInstanceId)" type="text">取消申请
                                    </el-button>
                                </div>
                                <div v-else>
                                    <el-button v-if="!scope.row.assignee" size="mini" @click="pickUp('1',scope.row.taskId,scope.row.assignee)" type="text">拾取
                                    </el-button>
                                    <el-button v-if="scope.row.assignee" size="mini" @click="dealProcess(scope.row)" type="text">处理
                                    </el-button>
                                    <el-button v-if="scope.row.assignee" size="mini" @click="cancelPickUp('1',scope.row.taskId)" type="text">取消拾取
                                    </el-button>
                                </div>
                                <el-button v-if="scope.row.assignee" size="mini" @click="showVerifyLogInfo(scope.row)" type="text">
                                    审核详情
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>

                <el-tab-pane label="退租单申请" name="second">
                    <el-table :data="myApplicationList" border size="small" :height="tableHeight" style="width: 100%;line-height: 30px;">
                        <el-table-column align="center" label="序号" type="index">
                        </el-table-column>
                        <el-table-column prop="ctName" label="合同编号" align="center">
                        </el-table-column>
                        <el-table-column label="申请人" prop="createName" align="center">
                        </el-table-column>
                        <!--流程实例id-->
                        <el-table-column label="单号" prop="processInstanceId" align="center">
                        </el-table-column>
                        <el-table-column label="申请内容" align="center">
                            <template slot-scope="scope">
                                {{"【" + scope.row.processInstanceId + "】" + scope.row.partyB + "- 申请退租"}}
                            </template>
                        </el-table-column>
                        <el-table-column label="申请时间" align="center">
                            <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                        </el-table-column>
                        <el-table-column label="审批状态" prop="approvalStatusName" align="center">
                        </el-table-column>
                        <el-table-column label="流程定义ID" prop="pdId" align="center">
                        </el-table-column>
                        <el-table-column width="180px" align="center" label="操作">
                            <template slot-scope="scope">
                                <el-button size="mini" @click="applyDetail(scope.row)" type="text">详情</el-button>
                                <el-button size="mini" @click="showVerifyLogInfo(scope.row)" type="text">
                                    审核详情
                                </el-button>
                                <el-button v-if="scope.row.approvalStatus == '3'" size="mini" @click="downLoadApply(scope.row.applyCode)" type="text">下载</el-button>

                                <el-button v-if="scope.row.approvalStatus != '2' && scope.row.approvalStatus !='3'" size="mini" @click="endFlow(scope.row.id,scope.row.applyCode,scope.row.ctCode,scope.row.processInstanceId)" type="text">取消申请
                                </el-button>
                                <!--<el-button v-if="scope.row.approvalStatus == '3'" size="mini"
                                           @click="createPayApply(scope.row.applyCode,scope.row.ctName,scope.row.money)" type="text">申请打款
                                </el-button>-->
                                <el-button v-if="scope.row.approvalStatus == '3' && scope.row.moneyType=='2'" size="mini" @click="createPayApply(scope.row)" type="text">申请打款
                                </el-button>
                                <el-button v-if="showRentPaymentBtn && scope.row.approvalStatus == '3' && scope.row.moneyType=='1'" @click="toRentPay(scope.row)" type="text" size="small">
                                    缴费办理
                                </el-button>
                                <el-button v-if="scope.row.approvalStatus != '3' && scope.row.approvalStatus != '2'" size="mini" @click="currentNode(scope.row.processInstanceId)" type="text">审批节点
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="emp-page-div">
                        <el-pagination background @size-change="throwLeaseApplySizeChange" @current-change="throwLeaseApplyCurrentChange" :current-page.sync="start" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
                        </el-pagination>
                    </div>
                </el-tab-pane>

                <el-tab-pane label="打款待处理" name="third">
                    <el-table :data="waitApprovalPayList" border size="small" :height="tableHeight" style="width: 100%;line-height: 30px;">
                        <el-table-column align="center" label="序号" type="index">
                        </el-table-column>
                        <el-table-column prop="ctName" label="合同编号" align="center">
                        </el-table-column>
                        <el-table-column label="申请人" prop="createName" align="center">
                        </el-table-column>
                        <!--流程实例id-->
                        <el-table-column label="单号" prop="executionId" align="center">
                        </el-table-column>
                        <el-table-column width="200px" label="收款人" align="center">
                            <template slot-scope="scope">
                                <pre>{{scope.row.payee}}</pre>
                            </template>
                        </el-table-column>
                        <el-table-column label="申请时间" align="center">
                            <template slot-scope="scope">{{scope.row.createTime | time}}</template>
                        </el-table-column>
                        <el-table-column label="审批状态" prop="approvalStatusName" align="center">
                        </el-table-column>
                        <el-table-column label="任务ID" prop="taskId" align="center">
                        </el-table-column>
                        <el-table-column show-overflow-tooltip prop="approveName" label="审核节点" align="center">
                        </el-table-column>
                        <el-table-column label="流程定义ID" prop="processDefinitionId" align="center">
                        </el-table-column>
                        <el-table-column label="任务创建时间" align="center">
                            <template slot-scope="scope">{{scope.row.taskCreateTime | time}}</template>
                        </el-table-column>
                        <el-table-column width="150px" align="center" label="操作">
                            <template slot-scope="scope">
                                <div v-if="scope.row.approvalStatus == '4'">
                                    <el-button v-if="scope.row.assignee" size="mini" @click="editPayProcess(scope.row)" type="text">调整
                                    </el-button>
                                    <el-button v-if="scope.row.assignee" size="mini" @click="cancelApproval(scope.row)" type="text">取消申请
                                    </el-button>
                                </div>
                                <div v-else>
                                    <el-button v-if="!scope.row.assignee" size="mini" @click="pickUp('2',scope.row.taskId,scope.row.assignee)" type="text">拾取
                                    </el-button>
                                    <el-button v-if="scope.row.assignee" size="mini" @click="dealPayProcess(scope.row)" type="text">处理
                                    </el-button>
                                    <el-button v-if="scope.row.assignee" size="mini" @click="cancelPickUp('2',scope.row.taskId)" type="text">取消拾取
                                    </el-button>
                                </div>
                                <el-button v-if="scope.row.assignee" size="mini" @click="showVerifyLogInfo(scope.row)" type="text">
                                    审核详情
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>

                <el-tab-pane label="打款申请" name="fourth">
                    <el-table :data="myApplicationPayList" border size="small" :height="tableHeight" style="width: 100%;line-height: 30px;">
                        <el-table-column align="center" label="序号" type="index">
                        </el-table-column>
                        <el-table-column prop="ctName" label="合同编号" align="center">
                        </el-table-column>
                        <el-table-column label="申请人" prop="createName" align="center">
                        </el-table-column>
                        <!--流程实例id-->
                        <el-table-column label="单号" prop="processInstanceId" align="center">
                        </el-table-column>
                        <el-table-column width="220px" label="收款人" align="center">
                            <template slot-scope="scope">
                                <pre>{{scope.row.payee}}</pre>
                            </template>
                        </el-table-column>
                        <el-table-column label="申请内容" align="center">
                            <template slot-scope="scope">
                                <!--                                <pre>{{"【" + scope.row.processInstanceId + "】" + scope.row.payee + "-申请付款"}}</pre>-->
                                【{{scope.row.processInstanceId}}】{{scope.row.payee | splitName}}-申请付款
                            </template>
                        </el-table-column>
                        <el-table-column label="申请时间" align="center">
                            <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                        </el-table-column>
                        <el-table-column label="审批状态" prop="approvalStatusName" align="center">
                        </el-table-column>
                        <el-table-column label="流程定义ID" prop="pdId" align="center">
                        </el-table-column>
                        <el-table-column width="180px" align="center" label="操作">
                            <template slot-scope="scope">
                                <el-button size="mini" @click="showDetail(scope.row)" type="text">详情</el-button>
                                <el-button size="mini" @click="showVerifyLogInfo(scope.row)" type="text">审核详情</el-button>
                                <el-button v-if="scope.row.approvalStatus != '2' && scope.row.approvalStatus !='3'" size="mini" @click="cancelApply(scope.row)" type="text">取消申请
                                </el-button>
                                <el-button v-if="scope.row.approvalStatus != '3' && scope.row.approvalStatus != '2'" size="mini" @click="currentNode(scope.row.processInstanceId)" type="text">审批节点
                                </el-button>
                                <el-button v-if="scope.row.approvalStatus == '3'" size="mini" @click="downLoadPayApply(scope.row.applyCode)" type="text">下载</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="emp-page-div">
                        <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="start" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
                        </el-pagination>
                    </div>
                </el-tab-pane>
            </el-tabs>

            <!-- 退租单申请 -->
            <throw-lease-apply v-if="showThrowLeaseApply" @backParam="backParam($event)" :paramsApplyObj="paramsApplyObj"></throw-lease-apply>

            <!-- 退租单详情 -->
            <throw-lease-apply-detail v-if="showThrowLeaseApplyDetail" @backParam="backParamDetail($event)" :paramsObjDetail="paramsObjDetail"></throw-lease-apply-detail>

            <!-- 退款单申请 -->
            <throw-lease-pay-apply v-if="showThrowLeasePayApply" @backParam="backPayParam($event)" :paramsPayObj="paramsPayObj"></throw-lease-pay-apply>

            <!-- 退款单详情 -->
            <throw-lease-pay-detail v-if="showthrowLeasePayApplyDetail" @backParam="backParamPayDetail($event)" :paramsPayObjDetail="paramsPayObjDetail"></throw-lease-pay-detail>
            <!--审核详情-->
            <verify-log v-if="showVerifyLog" @backParam="backParamLog($event)" :paramsLog="paramsLog"></verify-log>
            <!--审核节点图展示-->
            <el-dialog :visible.sync="activitiPictureWindow" width="70%">
                <img width="90%" height="90%" :src="activitiPicture" />
            </el-dialog>

            <!--缴纳租金-->
            <rent-pay v-if="rentPyWindow" @backParam="backParamRentPay($event)" :paramsRentPay="paramsRentPay"></rent-pay>
        </el-form>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import throwLeaseApply from './workflowTemplate/throwLeaseApply.vue';
    import throwLeaseApplyDetail from './workflowTemplate/throwLeaseApplyDetail.vue';
    import throwLeasePayApply from './workflowTemplate/throwLeasePayApply.vue';
    import throwLeasePayDetail from './workflowTemplate/throwLeasePayDetail.vue';
    import verifyLog from './workflowTemplate/verifyLog.vue';
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import rentPay from "./workflowTemplate/rentPay.vue";

    export default {
        components: {
            ElButton,
            ElRow,
            throwLeaseApply,
            throwLeaseApplyDetail,
            throwLeasePayApply,
            throwLeasePayDetail,
            verifyLog,
            rentPay
        },
        props: ['paramsObj'], // 接收父组件数据
        created() {
            let vm = this;
            vm.selectWaitApprovalList();

            if (vm.paramsObj && vm.paramsObj.activeName) {
                vm.activeName = vm.paramsObj.activeName;

                if ('third' == vm.activeName) {
                    vm.activeName = vm.paramsObj.activeName;
                    vm.selectWaitApprovalPayList();

                    vm.waitApprovalTaskCount();
                }
            } else {
                vm.selectWaitApprovalList();

                vm.waitApprovalTaskCount();
            }
            vm.showRentPaymentBtn = vm.$flagMenuStore.judgeMenu("rentPaymentBtn"); // 缴费办理按钮
        },
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,

                // 分页
                start: 1, // 起始页
                pageSize: 25, // 每页数量
                pageSizes: [25, 50, 100, 150],
                total: 0, // 总条数

                waitApprovalList: [], //待我处理列表
                waitApprovalPayList: [], //打款待我处理列表

                myApplicationList: [], //退租单我的申请列表
                myApplicationPayList: [], //打款我的申请列表

                showThrowLeaseApply: false, //申请退租单弹窗控制
                showThrowLeaseApplyDetail: false, //申请退租单详细弹窗控制

                showThrowLeasePayApply: false, //申请退租单弹窗控制
                showthrowLeasePayApplyDetail: false, //申请退租单详细弹窗控制

                showVerifyLog: false, //审核记录窗口

                paramsApplyObj: {}, //父子组件传值参数对象
                paramsObjDetail: {},

                paramsPayObj: {}, //父子组件传值参数对象
                paramsPayObjDetail: {},

                paramsLog: {},

                activeName: "first", //默认tab标签

                activitiPicture: '', // 图片URL
                activitiPictureWindow: false, // 显示流程图窗口

                showRentPaymentBtn: false, //缴费办理权限控制
                rentPyWindow: false, //缴费窗口
                paramsRentPay: {},
            }
        },
        methods: {

            /*---------------------分页start---------------------*/
            throwLeaseApplySizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.selectProcdefList();
            },
            throwLeaseApplyCurrentChange(val) {
                this.start = val;
                this.selectProcdefList();
            },

            // 申请打款工作流
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.selectProcdefPayList();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.selectProcdefPayList();
            },
            /*---------------------分页end----------------------*/

            handleClick(tab, event) {
                if (tab.name == 'first') {
                    this.selectWaitApprovalList();
                } else if (tab.name == 'second') {
                    this.selectProcdefList();
                } else if (tab.name == 'third') {
                    this.selectWaitApprovalPayList();
                } else if (tab.name == 'fourth') {
                    this.selectProcdefPayList();
                }
            },

            // 查询待我处理任务的数量
            waitApprovalTaskCount() {
                let vm = this;

                let params = {
                    entity: {}
                };

                let option = {
                    method: 'POST',
                    data: params,
                    url: 'activitiManage/waitApprovalCount'
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (response) {
                        let throwLeaseApplyTaskCount = response.data.result.throwLeaseApply;
                        let throwLeasePayApplyTaskCount = response.data.result.throwLeasePayApply;
                        let landlordContractApplyTaskCount = response.data.result.landlordCount;

                        let data = {
                            params: {
                                throwLeaseApplyTaskCount: throwLeaseApplyTaskCount,
                                throwLeasePayApplyTaskCount: throwLeasePayApplyTaskCount,
                                landlordContractApplyTaskCount: landlordContractApplyTaskCount,
                            }
                        };
                        vm.$emit('waitApprovalTask', data);
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询待我处理列表出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // 查询待我处理
            selectWaitApprovalList() {
                let vm = this;

                vm.activeName = "first";

                let params = {
                    entity: {}
                };

                let option = {
                    method: 'POST',
                    data: params,
                    url: 'throwLeaseApplyActiviti/waitApprovalList'
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (response) {
                        vm.waitApprovalList = response.data.result;
                        vm.waitApprovalTaskCount();
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询待我处理列表出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
            // 查询退租我的申请
            selectProcdefList() {
                let vm = this;

                vm.activeName = "second";

                let params = {
                    entity: {},
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize
                };

                let option = {
                    method: 'POST',
                    data: params,
                    url: 'throwLeaseApplyActiviti/myApplyList'
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (response) {
                        vm.myApplicationList = response.data.result.data;
                        vm.total = response.data.result.totalCount
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询待我处理列表出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
            // 查询打款待处理
            selectWaitApprovalPayList() {
                let vm = this;

                vm.activeName = "third";

                let params = {
                    entity: {}
                };

                let option = {
                    method: 'POST',
                    data: params,
                    url: 'throwLeasePayApplyActiviti/waitApprovalList'
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (response) {
                        vm.waitApprovalPayList = response.data.result;
                        vm.waitApprovalTaskCount();
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询待我处理列表出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
            // 查询打款我的申请
            selectProcdefPayList() {
                let vm = this;

                vm.activeName = "fourth";

                let params = {
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize,
                    entity: {}
                };

                let option = {
                    method: 'POST',
                    data: params,
                    url: 'throwLeasePayApplyActiviti/myApplyList'
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (response) {
                        vm.myApplicationPayList = response.data.result.data;
                        vm.total = response.data.result.totalCount
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询我的申请列表出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // 查看当前节点
            currentNode(processInstanceId) {
                let vm = this;

                vm.activitiPictureWindow = true;

                let params = {
                    entity: {
                        processInstanceId: processInstanceId
                    }
                };

                let option = {
                    method: 'POST',
                    data: params,
                    url: 'activitiManage/currentNode'
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (response) {
                        let pic = response.data.result;
                        vm.activitiPicture = "data:image/png;base64," + pic;
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询我的申请列表出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            //创建申请
            createApply(row) {
                let vm = this;
                vm.showThrowLeaseApply = true;
                vm.paramsApplyObj.type = "add";
                vm.paramsApplyObj.showThrowLeaseApply = vm.showThrowLeaseApply; //控制弹窗是否展示
            },

            //创建打款申请
            createPayApply(row) {
                let vm = this;
                let params = {};
                params.entity = {
                    throwLeaseApplyCode: row.applyCode,
                };

                let option = {
                    method: "POST",
                    data: params,
                    url: "throwLeasePayApplyActiviti/checkApply",
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (!response.data.result) {
                        vm.$alert('该租金退款流程正在审核中或者已经通过，请查看退款申请记录', '申请失败', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    } else {
                        vm.showThrowLeasePayApply = true;
                        vm.paramsPayObj.type = "add";
                        vm.paramsPayObj.throwLeaseApplyCode = row.applyCode;
                        vm.paramsPayObj.ctName = row.ctName;
                        vm.paramsPayObj.payMoney = row.money;
                        vm.paramsPayObj.payee = row.refundAccountName + "\n" + row.refundBank + "\n" + row.refundAccountNumber;
                        vm.paramsPayObj.paymentApplyRemark = row.throwLeaseRemark;
                        vm.paramsPayObj.showThrowLeasePayApply = vm.showThrowLeasePayApply; //控制弹窗是否展示
                    }
                }).catch(function(error) {
                    vm.$alert('页面：申请打款出错咯', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                })
            },

            //拾取
            pickUp(type, taskId, assignee) {
                let url = '';
                if (type == '1') {
                    url = 'throwLeaseApplyActiviti/getPublicTask';
                } else if (type == '2') {
                    url = 'throwLeasePayApplyActiviti/getPublicTask';
                }
                this.$confirm('此操作将拾取该流程，只能由您来审批, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let param = {
                        taskId: taskId,
                        assignee: assignee

                    };
                    this.pickUpCommon(param, url, type, "页面：拾取审批流程出错");
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '中断拾取操作'
                    });
                });
            },

            //取消拾取
            cancelPickUp(type, taskId) {
                let url = '';
                if (type == '1') {
                    url = 'throwLeaseApplyActiviti/backPublicTask';
                } else if (type == '2') {
                    url = 'throwLeasePayApplyActiviti/backPublicTask';
                }
                this.$confirm('此操作将取消拾取该流程, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let param = {
                        taskId: taskId
                    };
                    this.pickUpCommon(param, url, type, "页面：取消拾取审批流程出错");
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '中断取消拾取操作'
                    });
                });
            },

            //拾取公共请求
            pickUpCommon(param, url, type, msg) {
                let vm = this;

                let params = {};
                params.entity = param;

                let option = {
                    method: 'POST',
                    data: params,
                    url: url
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (response) {
                        if (type == '1') {
                            vm.selectWaitApprovalList();
                        } else if (type == '2') {
                            vm.selectWaitApprovalPayList();
                        }

                    }
                }).catch(function(error) {
                    vm.$alert(msg, '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            //处理退租单流程
            dealProcess(row) {
                let vm = this;
                vm.showThrowLeaseApplyDetail = true;
                vm.paramsObjDetail.showThrowLeaseApplyDetail = vm.showThrowLeaseApplyDetail;
                vm.paramsObjDetail.param = row;
                vm.paramsObjDetail.type = 'deal';
            },
            //处理打款流程
            dealPayProcess(row) {
                let vm = this;
                vm.showthrowLeasePayApplyDetail = true;
                vm.paramsPayObjDetail.showthrowLeasePayApplyDetail = vm.showthrowLeasePayApplyDetail;
                vm.paramsPayObjDetail.param = row;
                vm.paramsPayObjDetail.detailType = 'deal';
            },

            //调整申请
            editProcess(row) {
                let vm = this;
                vm.showThrowLeaseApply = true;
                vm.paramsApplyObj.type = "edit";
                vm.paramsApplyObj.showThrowLeaseApply = vm.showThrowLeaseApply;
                vm.paramsApplyObj.param = row;
            },

            //打款申请详情
            showDetail(row) {
                let vm = this;
                vm.showthrowLeasePayApplyDetail = true;
                vm.paramsPayObjDetail.showthrowLeasePayApplyDetail = vm.showthrowLeasePayApplyDetail;
                vm.paramsPayObjDetail.param = row;
                vm.paramsPayObjDetail.detailType = 'detail';
            },

            //审核详情
            showVerifyLogInfo(row) {
                let vm = this;
                vm.showVerifyLog = true;
                vm.paramsLog.processInstanceId = row.processInstanceId;
                vm.paramsLog.createName = row.createName;
                vm.paramsLog.showVerifyLog = vm.showVerifyLog;
            },

            //调整申请
            editPayProcess(row) {
                let vm = this;
                vm.showThrowLeasePayApply = true;
                vm.paramsPayObj.type = "edit";
                vm.paramsPayObj.showThrowLeasePayApply = vm.showThrowLeasePayApply;
                vm.paramsPayObj.param = row;
            },

            //我的申请：详情
            applyDetail(row) {
                let vm = this;
                vm.showThrowLeaseApplyDetail = true;
                vm.paramsObjDetail.type = 'detail';
                vm.paramsObjDetail.showThrowLeaseApplyDetail = vm.showThrowLeaseApplyDetail;
                vm.paramsObjDetail.param = row;
            },

            //退租填写弹窗子组件回调
            backParam(val) {
                this.showThrowLeaseApply = val.showThrowLeaseApply;
                if (val.openType == 'edit') {
                    this.selectWaitApprovalList();
                } else {
                    this.selectProcdefList();
                }

            },

            //退款填写弹窗子组件回调
            backPayParam(val) {
                this.showThrowLeasePayApply = val.showThrowLeasePayApply;
                if (val.openType == 'edit') {
                    this.selectWaitApprovalPayList();
                } else {
                    this.selectProcdefList();
                }
            },

            //审核记录窗口回调
            backParamLog(val) {
                this.showVerifyLog = val.showVerifyLog;
            },

            //退租详情弹窗子组件回调
            backParamDetail(val) {
                this.showThrowLeaseApplyDetail = val.showThrowLeaseApplyDetail;
                if (val.showDetail == 'detail') {
                    this.selectProcdefList();
                } else {
                    this.selectWaitApprovalList();
                }

            },

            //退款详情和退款处理弹窗子组件回调
            backParamPayDetail(val) {
                this.showthrowLeasePayApplyDetail = val.showthrowLeasePayApplyDetail;
                if (val.detailType == 'deal') {
                    this.selectWaitApprovalPayList(); //查询待处理
                } else {
                    this.selectProcdefPayList(); //查询我的申请
                }
            },

            // 退租单取消申请
            endFlow(id, applyCode, ctCode, processInstanceId) {
                let vm = this;

                this.$confirm('是否结束该流程申请？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {

                    let params = {
                        entity: {
                            id: id,
                            applyCode: applyCode,
                            ctCode: ctCode,
                            processInstanceId: processInstanceId,
                        },
                        reapply: false
                    };

                    let options = {
                        method: "POST",
                        data: params,
                        url: "throwLeaseApplyActiviti/applicantResubmit"
                    };

                    this.$ajax(
                        options
                    ).then(response => {
                        vm.$message({
                            showClose: true,
                            message: '页面：流程已结束',
                            type: 'success'
                        });
                        vm.selectProcdefList();
                    }).catch(error => {
                        vm.$message({
                            showClose: true,
                            message: '页面：操作失败！',
                            type: 'error'
                        })
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    });
                });
            },

            //打款取消调整
            cancelApproval(row) {
                this.$confirm('是否确定取消该申请?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let obj = {};
                    obj.entity = row;
                    obj.reapply = false;
                    let option = {
                        method: 'POST',
                        data: obj,
                        url: "throwLeasePayApplyActiviti/applicantResubmit",
                    };
                    this.$ajax(
                        option
                    ).then(function(response) {
                        if (response) {
                            vm.selectWaitApprovalPayList();
                        }
                    }).catch(function() {
                        vm.$alert('页面：取消转账申请出错咯', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '中断取消操作'
                    });
                });
            },

            //打款取消申请
            cancelApply(row) {
                this.$confirm('是否确定取消该申请?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let obj = {};
                    obj.entity = row;
                    let option = {
                        method: 'POST',
                        data: obj,
                        url: "throwLeasePayApplyActiviti/cancelApply",
                    };
                    this.$ajax(
                        option
                    ).then(function(response) {
                        if (response) {
                            vm.selectProcdefPayList();
                        }
                    }).catch(function(error) {
                        vm.$alert('页面：取消申请出错咯', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '中断取消操作'
                    });
                });
            },

            //检查是否重复提交打款申请
            checkPayApply(applyCode) {
                let vm = this;
                let params = {};
                params.entity = {
                    throwLeaseApplyCode: applyCode,
                }
                let option = {
                    method: "POST",
                    async: false,
                    data: params,
                    url: "throwLeasePayApplyActiviti/checkApply",
                }
                this.$ajax(
                    option
                ).then(function(response) {
                    if (!response.data.result) {
                        vm.$alert('页面：申请失败', '该租金退款流程正在审核中或者已经通过，请查看退款申请记录', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    }
                }).catch(function(error) {
                    vm.$alert('页面：申请打款出错咯', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                })
            },

            //缴费
            toRentPay(row) {
                let vm = this;
                vm.rentPyWindow = true;
                vm.paramsRentPay.rentPyWindow = vm.rentPyWindow;
                vm.paramsRentPay.receivableRent = row.money;
                vm.paramsRentPay.ctCode = row.ctCode;
                vm.paramsRentPay.applyCode = row.applyCode;
            },
            //缴费回调
            backParamRentPay(val) {
                this.rentPyWindow = val.rentPyWindow;
            },

            //下载退租单
            downLoadApply(applyCode) {
                // 线下测试
                //                window.location.href = "http://localhost:8380/park/throwLeaseApplyActiviti/exportThrowLeaseWordWithImg" +
                //                    "?applyCode="+applyCode;

                // 线上导出
                window.location.href = "/park/throwLeaseApplyActiviti/exportThrowLeaseWordWithImg" +
                    "?applyCode=" + applyCode;
            },

            //下载退款单
            downLoadPayApply(applyCode) {
                // 线下测试
                //                window.location.href = "http://localhost:8380/park/throwLeasePayApplyActiviti/exportThrowLeasePayWord" +
                //                    "?applyCode="+applyCode;

                // 线上导出
                window.location.href = "/park/throwLeasePayApplyActiviti/exportThrowLeasePayWord" +
                    "?applyCode=" + applyCode;
            }
        }
    }
</script>

<style scoped>

    /*退租申请按钮*/
    .add_button {
        float: left;
        margin-left: 20px;
    }

    /*分页*/
    .emp-page-div {
        padding-top: 10px;
        text-align: left;
        background-color: rgb(242, 242, 242);

        height: 40px;
        width: 100%;
        line-height: 40px;

        display: block;
        position: fixed;
        bottom: 0px;
    }

    .el-table th,
    .el-table tr {
        height: 47px;
        background-color: #fff;
    }

    .root /deep/ .el-table__fixed-body-wrapper {
        top: 47px !important;
    }
</style>