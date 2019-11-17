<!--<template>-->
    <!--<div class="root" ref="myRoot">-->
        <!--<div class="navigation_div">-->
            <!--<span style="font-size: 17px;color: #409EFF;margin-left: 20px;">我的申请列表</span>-->
        <!--</div>-->

        <!--<el-table-->
                <!--:data="myApplicationList"-->
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
                    <!--label="申请内容"-->
                    <!--align="center">-->
                <!--<template slot-scope="scope">{{scope.row.partyB+"【申请退租】"}}</template>-->
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
            <!--<el-table-column-->
                    <!--prop="ctName"-->
                    <!--label="合同编号"-->
                    <!--align="center">-->
            <!--</el-table-column>-->
            <!--<el-table-column-->
                    <!--fixed-->
                    <!--width="150px"-->
                    <!--fixed="right"-->
                    <!--align="center"-->
                    <!--label="操作">-->
                <!--<template slot-scope="scope">-->
                    <!--&lt;!&ndash;<el-button size="mini" @click="delApply(scope.row)" type="text">处理</el-button>&ndash;&gt;-->
                <!--</template>-->
            <!--</el-table-column>-->
        <!--</el-table>-->

        <!--<throw-lease-apply v-if="showThrowLeaseApply" @backParam="backParam($event)" :paramsObj="paramsObj" ></throw-lease-apply>-->

    <!--</div>-->
<!--</template>-->

<!--<script>-->
    <!--import ElRow from "element-ui/packages/row/src/row";-->

    <!--export default {-->
        <!--components: {-->
            <!--ElRow,-->
        <!--},-->
        <!--created(){-->
            <!--let vm = this;-->

            <!--vm.selectProcdefList();-->
        <!--},-->
        <!--data() {-->
            <!--return {-->
                <!--myApplicationList: [],//我的申请列表-->
                <!--showThrowLeaseApply: false,//申请退租单弹窗控制-->
                <!--paramsObj: {},//父子组件传值参数对象-->
            <!--}-->
        <!--},-->
        <!--methods: {-->
            <!--// 查询已部署的流程-->
            <!--selectProcdefList(){-->
                <!--let vm = this;-->

                <!--let params = {-->
                    <!--entity: {}-->
                <!--};-->

                <!--let option = {-->
                    <!--method: 'POST',-->
                    <!--data: params,-->
                    <!--url: 'throwLeaseApplyActiviti/myApplyList'-->
                <!--};-->

                <!--this.$ajax(-->
                    <!--option-->
                <!--).then(function (response) {-->
                    <!--if(response) {-->
                        <!--vm.myApplicationList = response.data.result;-->
                    <!--}-->
                <!--}).catch(function (error) {-->
                    <!--vm.$alert('页面：查询待我处理列表出错', '系统异常', {-->
                        <!--confirmButtonText: '确定',-->
                        <!--callback: action => {}-->
                    <!--});-->
                <!--});-->
            <!--},-->

            <!--//创建申请-->
            <!--delApply(row){-->
                <!--let vm = this;-->
                <!--if(row.key == 'kfwy_throw_lease_apply') {//打开申请退租单填写窗口-->
                    <!--vm.showThrowLeaseApply = true;-->
                    <!--vm.paramsObj.showThrowLeaseApply = vm.showThrowLeaseApply;-->
                    <!--vm.paramsObj.key = row.key;-->
                    <!--vm.paramsObj.pdId = row.id;-->
                <!--}-->
            <!--},-->
            <!--//子组件回调-->
            <!--backParam(val) {-->
                <!--if(val.key == "kfwy_throw_lease_apply") {-->
                    <!--this.showThrowLeaseApply = val.showThrowLeaseApply;-->
                <!--}-->
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



