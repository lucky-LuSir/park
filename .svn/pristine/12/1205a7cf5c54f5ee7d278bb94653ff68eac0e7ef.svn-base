<!-- 查看流程审核记录 -->
<template>
    <el-dialog title="审核记录" :visible.sync="showVerifyLog" style="line-height: 40px;" width="70%"
               :before-close="beforeClose"
               :center="true">
        <el-steps :active="lastStatus" finish-status="success" style="width: 70%;margin-left: 15%;">
            <!--<template v-for="(item, index) in activitiNameList" :key="index">-->
                <!--<el-step title="{{index}}"></el-step>-->
            <!--</template>-->
            <el-step title="提交申请"></el-step>
            <el-step title="职能招商审批"></el-step>
            <el-step title="财务审批"></el-step>
        </el-steps>
        <el-table :data="verifyList" border height="300" style="margin-top: 10px">
            <el-table-column align="center" label="审核时间" >
                <template slot-scope="scope">{{ scope.row.createTime| time}}</template>
            </el-table-column>

            <el-table-column align="center" label="操作人" prop="createName"></el-table-column>

            <el-table-column align="center" label="审核节点">
                <template slot-scope="scope">
                    <p v-if="scope.row.actName == '取消申请' " style="color: #FF3030">{{ scope.row.actName}}</p>
                    <p v-else>
                        {{ scope.row.actName}}
                    </p>
                </template>

            </el-table-column>

            <el-table-column align="center" label="状态">
                <template slot-scope="scope">
                    <p v-if="scope.row.approvalStatus == '1'" style="color: #FFC125">{{ scope.row.approvalStatusName | checkDictionaries}}</p>
                    <p v-if="scope.row.approvalStatus == '2' ||scope.row.approvalStatus == '4' " style="color: #FF3030">{{ scope.row.approvalStatusName | checkDictionaries}}</p>
                    <p v-if="scope.row.approvalStatus == '3' || scope.row.approvalStatus == '5'" style="color: #43CD80">{{ scope.row.approvalStatusName | checkDictionaries}}</p>
                </template>
            </el-table-column>

            <el-table-column align="center" label="备注" prop="approveComment"></el-table-column>
        </el-table>
    </el-dialog>
</template>
<script>
    import ElRow from "element-ui/packages/row/src/row";
    export default{
        components: {ElRow},
        props:['paramsLog'],//接收父组件参数
        created(){
            let vm = this;
            if(vm.paramsLog){
                vm.showVerifyLog = true;
                vm.processInstanceId = vm.paramsLog.processInstanceId;
                vm.createName = vm.paramsLog.createName;
                vm.showVerifyLog = vm.paramsLog.showVerifyLog;
                vm.getComment();
                vm.getStatus();
            }


        },
        data(){
            return{
                lastStatus:0,
                showVerifyLog:false,
                processInstanceId:"",
                createName:'',
                verifyList:[],
                activitiNameList:[],//审核节点
            }
        },
        methods:{
            getComment(){
                let vm = this;
                let param = {};
                param.entity = {
                    processInstanceId :vm.processInstanceId,
                    createName: vm.createName
                };
                let option = {
                    method: 'POST',
                    data: param,
                    url: 'activitiManage/approvalDetail'
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if(response){
                        vm.verifyList = response.data.result;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查询查询审核记录出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            getStatus(){
                let vm = this;
                let param = {};
                param.entity = {
                    processInstanceId :vm.processInstanceId,
                };
                let option = {
                    method: 'POST',
                    data: param,
                    url: 'activitiManage/approvalProgress'
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if(response){
                        let status = response.data.result.status;
                        let tdkey = response.data.result.tdkey;
                        if(status =="5"){
                            if(tdkey == 'attractInvestment'){
                                vm.lastStatus = 2
                            }else if(tdkey == 'accountancy'){
                                vm.lastStatus = 3
                            }
                        }else if(status == '2' ){
                            vm.lastStatus = 0
                        }else if(status == '4' || status == '0'){
                            vm.lastStatus = 1
                        }
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查询查询审核状态出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            beforeClose(){
                let vm = this;
                vm.showVerifyLog = false;
                let data = {
                    showVerifyLog :vm.showVerifyLog,
                };
                vm.$emit('backParam',data);
            },
        },

        filters:{
            checkDictionaries:function (value) {
                if(value=="找不到指定词典"){
                    return '';
                }else {
                    return value;
                }
            },
        },
    }
</script>