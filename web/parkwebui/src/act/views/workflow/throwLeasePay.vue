<!--<template>-->
    <!--<div class="root" ref="myRoot">-->
        <!--<div class="navigation_div">-->
            <!--<el-button type="primary" size="small" icon="el-icon-plus" @click="createApply()">新增申请</el-button>-->
        <!--</div>-->
        <!--<el-tabs v-model="activeName" @tab-click="handleClick">-->
            <!--<el-tab-pane label="待我处理" name="first" v-if="manager">-->
                <!--<el-table-->
                        <!--:data="waitApprovalList"-->
                        <!--border-->
                        <!--size="small"-->
                        <!--height="74vh"-->
                        <!--style="width: 100%;line-height: 30px;">-->
                    <!--<el-table-column-->
                            <!--align="center"-->
                            <!--label="序号"-->
                            <!--type="index">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="申请人"-->
                            <!--prop="createName"-->
                            <!--align="center">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="收款人"-->
                            <!--prop="payee"-->
                            <!--align="center">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="申请时间"-->
                            <!--align="center">-->
                        <!--<template slot-scope="scope">{{scope.row.createTime | time}}</template>-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="审批状态"-->
                            <!--prop="approvalStatusName"-->
                            <!--align="center">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="任务ID"-->
                            <!--prop="taskId"-->
                            <!--align="center">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--show-overflow-tooltip-->
                            <!--prop="approveName"-->
                            <!--label="审核节点"-->
                            <!--align="center">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="流程定义ID"-->
                            <!--prop="processDefinitionId"-->
                            <!--align="center">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="流程实例ID"-->
                            <!--prop="executionId"-->
                            <!--align="center">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="任务创建时间"-->
                            <!--align="center">-->
                        <!--<template slot-scope="scope">{{scope.row.taskCreateTime | time}}</template>-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--fixed-->
                            <!--width="150px"-->
                            <!--fixed="right"-->
                            <!--align="center"-->
                            <!--label="操作">-->
                        <!--<template slot-scope="scope">-->
                            <!--<div v-if="scope.row.approvalStatus == '4'">-->
                                <!--<el-button v-if="scope.row.assignee" size="mini" @click="editProcess(scope.row)" type="text">调整</el-button>-->
                                <!--<el-button v-if="scope.row.assignee" size="mini" @click="cancelApproval(scope.row)" type="text">取消申请</el-button>-->
                            <!--</div>-->
                            <!--<div v-else>-->
                                <!--<el-button v-if="!scope.row.assignee" size="mini" @click="pickUp(scope.row.taskId,scope.row.assignee)" type="text">拾取</el-button>-->
                                <!--<el-button v-if="scope.row.assignee" size="mini" @click="dealProcess(scope.row)" type="text">处理</el-button>-->
                                <!--<el-button v-if="scope.row.assignee" size="mini" @click="cancelPickUp(scope.row.taskId)" type="text">取消拾取</el-button>-->
                            <!--</div>-->
                        <!--</template>-->
                    <!--</el-table-column>-->
                <!--</el-table>-->
            <!--</el-tab-pane>-->
            <!--<el-tab-pane label="我的申请" name="second" style="">-->
                <!--<el-table-->
                        <!--:data="myApplicationList"-->
                        <!--border-->
                        <!--size="small"-->
                        <!--height="70vh"-->
                        <!--style="width: 100%;line-height: 30px;">-->
                    <!--<el-table-column-->
                            <!--align="center"-->
                            <!--label="序号"-->
                            <!--type="index">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="申请人"-->
                            <!--prop="createName"-->
                            <!--align="center">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="收款人"-->
                            <!--prop="payee"-->
                            <!--align="center">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="申请内容"-->
                            <!--align="center">-->
                        <!--<template slot-scope="scope">{{scope.row.payee+"【申请付款】"}}</template>-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="申请时间"-->
                            <!--align="center">-->
                        <!--<template  slot-scope="scope">{{ scope.row.createTime | time}}</template>-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="审批状态"-->
                            <!--prop="approvalStatusName"-->
                            <!--align="center">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                            <!--label="流程定义ID"-->
                            <!--prop="pdId"-->
                            <!--align="center">-->
                    <!--</el-table-column>-->
                    <!--&lt;!&ndash;<el-table-column&ndash;&gt;-->
                            <!--&lt;!&ndash;prop="ctName"&ndash;&gt;-->
                            <!--&lt;!&ndash;label="合同编号"&ndash;&gt;-->
                            <!--&lt;!&ndash;align="center">&ndash;&gt;-->
                    <!--&lt;!&ndash;</el-table-column>&ndash;&gt;-->
                    <!--<el-table-column-->
                            <!--fixed-->
                            <!--width="150px"-->
                            <!--fixed="right"-->
                            <!--align="center"-->
                            <!--label="操作">-->
                        <!--<template slot-scope="scope">-->
                            <!--<el-button size="mini" @click="showDetail(scope.row)" type="text">详情</el-button>-->
                            <!--<el-button size="mini" @click="showVerifyLogInfo(scope.row)" type="text">审核详情</el-button>-->
                            <!--<el-button v-if="scope.row.approvalStatus != '2' && scope.row.approvalStatus !='3'" size="mini" @click="cancelApply(scope.row)" type="text">取消申请</el-button>-->
                            <!--&lt;!&ndash;<el-button size="mini" @click="delApply(scope.row)" type="text">处理</el-button>&ndash;&gt;-->
                        <!--</template>-->
                    <!--</el-table-column>-->
                <!--</el-table>-->
                <!--<div class="page_down">-->
                    <!--<el-pagination style="margin-top:10px;"-->
                                   <!--background-->
                                   <!--@size-change="handleSizeChange"-->
                                   <!--@current-change="handleCurrentChange"-->
                                   <!--:current-page.sync="start"-->
                                   <!--:page-sizes="pageSizes"-->
                                   <!--:page-size="pageSize"-->
                                   <!--layout="total, sizes, prev, pager, next, jumper"-->
                                   <!--:total="total">-->
                    <!--</el-pagination>-->
                <!--</div>-->
            <!--</el-tab-pane>-->
        <!--</el-tabs>-->

        <!--&lt;!&ndash;<throw-lease-apply v-if="showThrowLeaseApply" @backParam="backParam($event)" :paramsObj="paramsObj" ></throw-lease-apply>&ndash;&gt;-->

        <!--<throw-lease-pay-detail v-if="showthrowLeasePayApplyDetail" @backParam="backParamDetail($event)" :paramsObjDetail="paramsObjDetail" ></throw-lease-pay-detail>-->
        <!--<throw-lease-pay v-if="showThrowLeasePayApply" @backParam="backParam($event)" :paramsObj="paramsObj" ></throw-lease-pay>-->
        <!--<verify-log v-if="showVerifyLog" @backParam="backParamLog($event)" :paramsLog="paramsLog"></verify-log>-->
    <!--</div>-->
<!--</template>-->

<!--<script>-->
    <!--import ElRow from "element-ui/packages/row/src/row";-->
<!--//    import throwLeaseApply from './workflowTemplate/throwLeaseApply.vue';-->
<!--//    import throwLeaseApplyDetail from './workflowTemplate/throwLeaseApplyDetail.vue';-->


    <!--export default {-->
        <!--components: {-->
            <!--ElRow,-->
<!--//            throwLeaseApply,-->
<!--//            throwLeaseApplyDetail,-->

        <!--},-->
        <!--created(){-->
            <!--let vm = this;-->

            <!--vm.selectWaitApprovalPayList();-->
        <!--},-->
        <!--data() {-->
            <!--return {-->





            <!--}-->
        <!--},-->
        <!--methods: {-->













        <!--}-->
    <!--}-->
<!--</script>-->

<!--<style scoped>-->
    <!--body {-->
        <!--margin-left: 200px;-->
        <!--margin-right: 200px;-->
    <!--}-->

    <!--/*&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45; 导航栏 &#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;*/-->
    <!--/*导航栏DIV*/-->
    <!--.navigation_div {-->
        <!--height: 50px;-->
        <!--line-height: 50px;-->
        <!--margin-top: -46px;-->
        <!--text-align: left;-->

    <!--}-->

    <!--/*分页*/-->
    <!--.page_down {-->
        <!--text-align: left;-->
        <!--background-color: rgb(242, 242, 242);-->
        <!--height: 50px;-->
        <!--line-height: 50px;-->
        <!--clear: both;-->
        <!--display: block;-->
        <!--margin: 0px auto;-->
        <!--position: fixed;-->
        <!--bottom: 0px;-->
        <!--width: 100%;-->
        <!--z-index: 10;-->
    <!--}-->

<!--</style>-->



