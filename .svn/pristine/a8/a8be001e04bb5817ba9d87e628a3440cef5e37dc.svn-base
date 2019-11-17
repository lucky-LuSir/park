<!--<template>-->
    <!--<div class="root" ref="myRoot">-->
        <!--<div class="navigation_div">-->
            <!--<span style="font-size: 17px;color: #409EFF;margin-left: 20px;">待我处理列表</span>-->
        <!--</div>-->

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
                    <!--<el-button v-if="!scope.row.assignee" size="mini" @click="pickUp(scope.row.taskId,scope.row.assignee)" type="text">拾取</el-button>-->
                    <!--<el-button v-if="scope.row.assignee" size="mini" @click="dealProcess(scope.row)" type="text">处理</el-button>-->
                    <!--<el-button v-if="scope.row.assignee" size="mini" @click="cancelPickUp(scope.row.taskId)" type="text">取消拾取</el-button>-->
                <!--</template>-->
            <!--</el-table-column>-->
        <!--</el-table>-->

        <!--<throw-lease-apply-detail v-if="showThrowLeaseApply" @backParam="backParam($event)" :paramsObj="paramsObj" ></throw-lease-apply-detail>-->

    <!--</div>-->
<!--</template>-->

<!--<script>-->
    <!--import ElRow from "element-ui/packages/row/src/row";-->
    <!--import throwLeaseApplyDetail from './workflowTemplate/throwLeaseApplyDetail.vue';-->

    <!--export default {-->
        <!--components: {-->
            <!--ElRow,-->
            <!--throwLeaseApplyDetail-->
        <!--},-->
        <!--created(){-->
            <!--let vm = this;-->

            <!--vm.selectWaitApprovalList();-->
        <!--},-->
        <!--data() {-->
            <!--return {-->
                <!--waitApprovalList: [],//待我处理列表-->

                <!--showThrowLeaseApply: false,//申请退租单弹窗控制-->
                <!--paramsObj: {},//父子组件传值参数对象-->
            <!--}-->
        <!--},-->
        <!--methods: {-->
            <!--// 查询待我处理-->
            <!--selectWaitApprovalList(){-->
                <!--let vm = this;-->

                <!--let params = {-->
                    <!--entity: {}-->
                <!--};-->

                <!--let option = {-->
                    <!--method: 'POST',-->
                    <!--data: params,-->
                    <!--url: 'throwLeaseApplyActiviti/waitApprovalList'-->
                <!--};-->

                <!--this.$ajax(-->
                    <!--option-->
                <!--).then(function (response) {-->
                    <!--if(response) {-->
                        <!--vm.waitApprovalList = response.data.result;-->
                    <!--}-->
                <!--}).catch(function (error) {-->
                    <!--vm.$alert('页面：查询待我处理列表出错', '系统异常', {-->
                        <!--confirmButtonText: '确定',-->
                        <!--callback: action => {}-->
                    <!--});-->
                <!--});-->
            <!--},-->

            <!--//拾取-->
            <!--pickUp(taskId,assignee) {-->
                <!--this.$confirm('此操作将拾取该流程，只能由您来审批, 是否继续?', '提示', {-->
                    <!--confirmButtonText: '确定',-->
                    <!--cancelButtonText: '取消',-->
                    <!--type: 'warning'-->
                <!--}).then(() => {-->
                    <!--let param = {-->
                        <!--taskId: taskId,-->
                        <!--assignee: assignee-->

                    <!--};-->
                    <!--this.pickUpCommon(param,"activitiManage/getPublicTask","页面：拾取审批流程出错");-->
                <!--}).catch(() => {-->
                    <!--this.$message({-->
                        <!--type: 'info',-->
                        <!--message: '中断拾取操作'-->
                    <!--});-->
                <!--});-->
            <!--},-->

            <!--//取消拾取-->
            <!--cancelPickUp(taskId) {-->
                <!--this.$confirm('此操作将取消拾取该流程, 是否继续?', '提示', {-->
                    <!--confirmButtonText: '确定',-->
                    <!--cancelButtonText: '取消',-->
                    <!--type: 'warning'-->
                <!--}).then(() => {-->
                    <!--let param = {-->
                        <!--taskId: taskId-->
                    <!--};-->
                    <!--this.pickUpCommon(param,"activitiManage/backPublicTask","页面：取消拾取审批流程出错");-->
                <!--}).catch(() => {-->
                    <!--this.$message({-->
                        <!--type: 'info',-->
                        <!--message: '中断取消拾取操作'-->
                    <!--});-->
                <!--});-->
            <!--},-->

            <!--//拾取公共请求-->
            <!--pickUpCommon(param,url,msg) {-->
                <!--let vm = this;-->

                <!--let params = {};-->
                <!--params.entity = param;-->

                <!--let option = {-->
                    <!--method: 'POST',-->
                    <!--data: params,-->
                    <!--url: url-->
                <!--};-->

                <!--this.$ajax(-->
                    <!--option-->
                <!--).then(function (response) {-->
                    <!--if(response) {-->
                        <!--vm.selectWaitApprovalList();-->
                    <!--}-->
                <!--}).catch(function (error) {-->
                    <!--vm.$alert(msg, '系统异常', {-->
                        <!--confirmButtonText: '确定',-->
                        <!--callback: action => {}-->
                    <!--});-->
                <!--});-->
            <!--},-->

            <!--//处理流程-->
            <!--dealProcess(row){-->
                <!--let vm = this;-->
                <!--if(row.processDefinitionKey == 'kfwy_throw_lease_apply') {//打开申请退租单填写窗口-->
                    <!--vm.showThrowLeaseApply = true;-->
                    <!--vm.paramsObj.showThrowLeaseApply = vm.showThrowLeaseApply;-->
                    <!--vm.paramsObj.param = row;-->
                <!--}-->
            <!--},-->
            <!--//子组件回调-->
            <!--backParam(val) {-->
                <!--if(val.key == "kfwy_throw_lease_apply") {-->
                    <!--this.showThrowLeaseApply = val.showThrowLeaseApply;-->
                <!--}-->
                <!--this.selectWaitApprovalList();-->
            <!--}-->
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
        <!--height: 60px;-->
        <!--line-height: 60px;-->
        <!--margin-top: -50px;-->
        <!--text-align: left;-->
    <!--}-->

    <!--/*导航栏右边按钮*/-->
    <!--.navigation_button {-->
        <!--float: left;-->
        <!--margin-top: 10px;-->
        <!--margin-left: 20px;-->
    <!--}-->

<!--</style>-->



