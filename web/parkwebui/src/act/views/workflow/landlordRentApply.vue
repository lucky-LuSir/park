<template>
    <div class="has-position_root" ref="myRoot">

        <div class="has-position_header">
            <el-button type="primary" class="add_button" size="small" icon="el-icon-plus" @click="createLandlord()">新建申请</el-button>
        </div>

        <el-form>
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="待处理" name="first">
                    <el-table
                            :data="waitApprovalList"
                            border
                            size="small"
                            :height="tableHeight"
                            style="width: 100%;line-height: 30px;margin-top: 0px;">
                        <el-table-column
                                align="center"
                                label="序号"
                                type="index">
                        </el-table-column>
                        <!--流程实例id-->
                        <el-table-column
                                prop="ldCtName"
                                label="合同编号"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                label="申请人"
                                prop="createName"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                label="房东姓名"
                                prop="landlordName"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                label="单号"
                                prop="executionId"
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
                            <template slot-scope="scope">{{scope.row.createTime | time}}</template>
                        </el-table-column>
                        <el-table-column
                                label="审批状态"
                                prop="ldCtStatusName"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                show-overflow-tooltip
                                prop="approveName"
                                label="审核节点"
                                align="center">
                        </el-table-column>
                        <!--流程定义id-->
                        <el-table-column
                                label="流程定义id"
                                prop="processDefinitionId"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                label="任务创建时间"
                                align="center">
                            <template slot-scope="scope">{{scope.row.taskCreateTime | time}}</template>
                        </el-table-column>
                        <el-table-column
                                width="150px"
                                align="center"
                                label="操作">
                            <template slot-scope="scope">
                                <div v-if="scope.row.ldCtStatus == '5'">
                                    <el-button v-if="scope.row.assignee" size="mini" @click="editProcess(scope.row,'edit')"
                                               type="text">调整
                                    </el-button>
                                    <el-button size="mini"
                                               @click="cancelApply(scope.row)"
                                               type="text">取消申请
                                    </el-button>
                                </div>
                                <div v-else>
                                    <el-button v-if="!scope.row.assignee" size="mini"
                                               @click="pickUp(scope.row.taskId,scope.row.assignee)" type="text">拾取
                                    </el-button>
                                    <el-button v-if="scope.row.assignee" size="mini" @click="dealProcess(scope.row)"
                                               type="text">处理
                                    </el-button>
                                    <el-button v-if="scope.row.assignee" size="mini"
                                               @click="cancelPickUp(scope.row.taskId)" type="text">取消拾取
                                    </el-button>
                                </div>
                                <el-button v-if="scope.row.assignee" size="mini" @click="showVerifyLogInfo(scope.row)" type="text">
                                    审核详情
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>

                <el-tab-pane label="我的申请" name="second">
                    <el-table
                            :data="myApplicationList"
                            border
                            size="small"
                            :height="tableHeight"
                            style="width: 100%;line-height: 30px;">
                        <el-table-column
                                align="center"
                                label="序号"
                                type="index">
                        </el-table-column>
                        <el-table-column
                                prop="ldCtName"
                                label="合同编号"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                label="申请人"
                                prop="createName"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                label="房东姓名"
                                prop="landlordName"
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
                                width="180px"
                                align="center"
                                label="操作">
                            <template slot-scope="scope">
                                <el-button size="mini" @click="applyDetail(scope.row.ldCode)" type="text">详情</el-button>
                                <el-button size="mini" @click="showVerifyLogInfo(scope.row)" type="text">审核详情</el-button>
                                <el-button v-if="scope.row.ldCtStatus =='6'" size="mini" @click="editProcess(scope.row,'reSubmit')" type="text">重新申请</el-button>

                                <template v-if="scope.row.ldCtStatus == '1' || scope.row.ldCtStatus =='2' || scope.row.ldCtStatus =='3' || scope.row.ldCtStatus =='5'">
                                    <el-button size="mini"
                                               @click="cancelApply(scope.row)"
                                               type="text">取消申请
                                    </el-button>
                                    <el-button size="mini"
                                               @click="currentNode(scope.row.processInstanceId)"
                                               type="text">审批节点
                                    </el-button>
                                </template>
                                <!--<el-button v-if="scope.row.ldCtStatus == '1' || scope.row.ldCtStatus =='2' || scope.row.ldCtStatus =='3' || scope.row.ldCtStatus =='5'"-->
                                <!--size="mini"-->
                                <!--@click="cancelApply(scope.row)"-->
                                <!--type="text">取消申请-->
                                <!--</el-button>-->
                                <!--<el-button v-if="scope.row.ldCtStatus == '1' || scope.row.ldCtStatus =='2' || scope.row.ldCtStatus =='3' || scope.row.ldCtStatus =='5'"-->
                                <!--size="mini"-->
                                <!--@click="currentNode(scope.row.processInstanceId)" type="text">审批节点-->
                                <!--</el-button>-->
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="emp-page-div">
                        <el-pagination background
                                       @size-change="sizeChange"
                                       @current-change="currentChange"
                                       :current-page.sync="start"
                                       :page-sizes="pageSizes"
                                       :page-size="pageSize"
                                       layout="total, sizes, prev, pager, next, jumper"
                                       :total="total">
                        </el-pagination>
                    </div>
                </el-tab-pane>
            </el-tabs>

            <!--审核详情-->
            <verify-log v-if="showVerifyLog" @backParam="backParamLog($event)" :paramsLog="paramsLog"></verify-log>
            <!--审批-->
            <landlord-verify v-if="showVerify" @backParam="backParamVerify($event)" :paramsVerify="paramsVerify"></landlord-verify>

            <!--审核节点图展示-->
            <el-dialog :visible.sync="activitiPictureWindow" width="70%">
                <img width="90%" height="90%" :src="activitiPicture"/>
            </el-dialog>
        </el-form>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import verifyLog from './workflowTemplate/landlordVerifyLog.vue';
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import landlordVerify from './workflowTemplate/landlordVerify.vue';

    export default {
        components: {
            ElButton,
            ElRow,
            verifyLog,
            landlordVerify
        },
        created(){
            this.selectWaitApprovalList();
            this.waitApprovalTaskCount();
        },
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,
                // 分页
                start: 1,// 起始页
                pageSize: 25,// 每页数量
                pageSizes: [25, 50, 100, 150],
                total: 0,// 总条数

                waitApprovalList: [],//待我处理列表
                myApplicationList: [],//退租单我的申请列表

                activeName: "first",//默认tab标签
                activitiPictureWindow: false,// 显示流程图窗口
                activitiPicture:'',
                landlordApplyTaskCount: 0,// 退租单申请，待我办理的任务数

                paramsLog: {},//审核详情
                paramsVerify:{}, //审批

                showVerifyLog:false, //审核详情
                showVerify:false, //审核
            }
        },
        methods: {

            /*---------------------分页start---------------------*/
            sizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.selectProcdefList();
            },
            currentChange(val) {
                this.start = val;
                this.selectProcdefList();
            },
            /*---------------------分页end----------------------*/

            handleClick(tab, event) {
                if (tab.name == 'first') {
                    this.selectWaitApprovalList();
                } else if (tab.name == 'second') {
                    this.selectProcdefList();
                }
            },

            // 查询待我处理
            selectWaitApprovalList(){
                let vm = this;

                vm.activeName = "first";

                let params = {
                    entity: {}
                };

                let option = {
                    method: 'POST',
                    data: params,
                    url: 'landlordRentApply/waitApprovalList'
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if (response) {
                        vm.waitApprovalList = response.data.result;

                        vm.waitApprovalTaskCount();
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查询待我处理列表出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            // 查询大房东合同审批，待我处理任务的数量
            waitApprovalTaskCount(){
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
                ).then(function (response) {
                    if (response) {
                        let throwLeaseApplyTaskCount = response.data.result.throwLeaseApply;
                        let throwLeasePayApplyTaskCount = response.data.result.throwLeasePayApply;
                        let landlordContractApplyTaskCount = response.data.result.landlordCount;

                        let data = {
                            params: {
                                throwLeaseApplyTaskCount: throwLeaseApplyTaskCount,
                                throwLeasePayApplyTaskCount: throwLeasePayApplyTaskCount,
                                landlordContractApplyTaskCount: landlordContractApplyTaskCount
                            }
                        };
                        vm.$emit('waitApprovalTask', data);
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查询待我处理列表出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 查询我的申请
            selectProcdefList(){
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
                    url: 'landlordRentApply/myApplyList'
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if (response) {
                        vm.myApplicationList = response.data.result.data;
                        vm.total = response.data.result.totalCount
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查询待我处理列表出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            // 查看当前节点
            currentNode(processInstanceId){
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
                ).then(function (response) {
                    if (response) {
                        let pic = response.data.result;
                        vm.activitiPicture = "data:image/png;base64," + pic;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查询我的申请列表出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            //创建申请
            createApply(row){

            },


            //拾取
            pickUp(taskId, assignee) {
                let url = 'activitiManage/getPublicTask';
                this.$confirm('此操作将拾取该流程，只能由您来审批, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let param = {
                        taskId: taskId,
                        assignee: assignee

                    };
                    this.pickUpCommon(param, url, "页面：拾取审批流程出错");
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '中断拾取操作'
                    });
                });
            },

            //取消拾取
            cancelPickUp( taskId) {
                let url = 'activitiManage/backPublicTask';

                this.$confirm('此操作将取消拾取该流程, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let param = {
                        taskId: taskId
                    };
                    this.pickUpCommon(param, url, "页面：取消拾取审批流程出错");
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '中断取消拾取操作'
                    });
                });
            },

            //拾取公共请求
            pickUpCommon(param, url, msg) {
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
                ).then(function (response) {
                    if (response) {
                        vm.selectWaitApprovalList();
                    }
                }).catch(function (error) {
                    vm.$alert(msg, '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            //处理流程
            dealProcess(row){
                let vm = this;
                vm.showVerify = true;
                vm.paramsVerify.param = row;
                vm.paramsVerify.showVerify = vm.showVerify;
            },
            //处理流程回调
            backParamVerify(val){
                this.showVerify = val.showVerify;
                this.selectWaitApprovalList();
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



            //合同详情
            applyDetail(ldCode) {
                let data = {
                    menuName: '大房东合同详情',
                    item: 'landlordContractDetail',
                    params: {
                        ldCode: ldCode
                    }
                };
                this.$emit('backParam', data);
            },

            //打款取消申请
            cancelApply(row){
                this.$confirm('是否确定取消该申请?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let entity = {};
                    entity = row;
                    let obj = {
                        entity:entity
                    };
                    let option = {
                        method: 'POST',
                        data: obj,
                        url: "landlordRentApply/cancelApply",
                    };
                    this.$ajax(
                        option
                    ).then(function (response) {
                        if (response) {
                            vm.selectProcdefList();
                        }
                    }).catch(function (error) {
                        vm.$alert('页面：取消申请出错咯', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '中断取消操作'
                    });
                });
            },

            // 新增大房东合同
            createLandlord() {
                let data = {
                    menuName: '大房东合同新增',
                    item: 'landlordContractCreate',
                    params: {

                    }
                };
                this.$emit('backParam', data);
            },

            //调整
            editProcess(row,editType) {
                let data = {
                    menuName: '修改大房东合同',
                    item: 'landlordContractEdit',
                    params: {
                        landlordContract: row,
                        editType: editType,
                    }
                };
                this.$emit('backParam', data);
            },


            //下载退租单
            downLoadApply(applyCode) {
                // 线下测试
//                window.location.href = "http://localhost:8380/park/throwLeaseApplyActiviti/exportThrowLeaseWord" +
//                    "?applyCode="+applyCode;

                // 线上导出
                window.location.href = "/park/throwLeaseApplyActiviti/exportThrowLeaseWord" +
                    "?applyCode="+applyCode;
            },

            //下载退款单
            downLoadPayApply(applyCode) {
                // 线下测试
//                window.location.href = "http://localhost:8380/park/throwLeasePayApplyActiviti/exportThrowLeasePayWord" +
//                    "?applyCode="+applyCode;

                // 线上导出
                window.location.href = "/park/throwLeasePayApplyActiviti/exportThrowLeasePayWord" +
                    "?applyCode="+applyCode;
            }
        }
    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    /*------------------------------ 导航栏 ------------------------------*/
    /*退租申请按钮*/
    .add_button {
        float: left;
        margin-left: 20px;
        margin-top: 8px;
    }

    /*分页*/
    .emp-page-div {
        padding-top: 10px;
        text-align: left;
        background-color: rgb(242,242,242);

        height: 40px;
        width: 100%;
        line-height: 40px;
        display: block;
        position: fixed;
        bottom: 0px;
    }
</style>



