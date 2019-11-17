<template>
    <el-dialog title="申请退租单" width="80%" :visible.sync="showThrowLeaseApplyDetail"
               :close-on-click-modal="false"
               :close-on-press-escape="false"
               :before-close="beforeClose"
               :center="true">
        <el-form :model="throwLeaseApply" ref="throwLeaseApply"
                 label-width="100px" label-suffix=":">
            <div style="text-align: left">
                <el-row>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="退租类型">
                            {{ throwLeaseApply.throwLeaseTypeName }}
                        </el-form-item>
                    </el-col>

                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10" v-if="throwLeaseApply.throwLeaseType == 3">
                        <el-form-item label="协商退租" prop="dutyType">
                            {{ throwLeaseApply.dutyTypeName }}
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <!-- 甲方 -->
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="甲方">
                            {{throwLeaseApply.partyA}}
                        </el-form-item>
                    </el-col>

                    <!-- 乙方 -->
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="乙方">
                            {{throwLeaseApply.partyB}}
                        </el-form-item>
                    </el-col>
                </el-row>

                <!-- 退租日期 -->
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="退租日期">
                            {{throwLeaseApply.throwLeaseDate | timeDate}}
                        </el-form-item>
                    </el-col>
                </el-row>

                <!-- 合同信息 -->
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="合同编号">
                            {{throwLeaseApply.ctName}}
                        </el-form-item>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="租赁区域">
                            {{throwLeaseApply.leasedArea}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="面积">
                            {{throwLeaseApply.throwLeaseArea}}㎡
                        </el-form-item>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="月租金">
                            {{throwLeaseApply.monthlyRent}}元
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="金额类型" prop="moneyType">
                            {{ throwLeaseApply.moneyTypeName }}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="金额" prop="money">
                            {{ throwLeaseApply.money }}&nbsp;元
                        </el-form-item>
                    </el-col>
                </el-row>

                <!-- 事项说明 -->
                <el-row>
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="事项说明">
                            <span v-html="throwLeaseApply.throwLeaseRemark"></span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <!-- 退款账号信息 -->
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="退款银行">
                            {{throwLeaseApply.refundBank}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="账号姓名">
                            {{throwLeaseApply.refundAccountName}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="银行账号">
                            {{throwLeaseApply.refundAccountNumber}}
                        </el-form-item>
                    </el-col>
                </el-row>

                <!-- 甲方信息 -->
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="甲方法定代表" label-width="110px">
                            {{throwLeaseApply.partyALegalRepresentative}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                        <el-form-item>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="乙方法定代表" label-width="110px">
                            {{throwLeaseApply.partyBLegalRepresentative}}
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="甲方电话">
                            {{throwLeaseApply.partyAPhone}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                        <el-form-item>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="乙方电话">
                            {{throwLeaseApply.partyBPhone}}
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="甲方地址">
                            {{throwLeaseApply.partyAAddress}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="1" :sm="1" :md="1" :lg="1" :xl="1">
                        <el-form-item>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="乙方地址">
                            {{throwLeaseApply.partyBAddress}}
                        </el-form-item>
                    </el-col>
                </el-row>
                <div slot="footer" style="text-align: center" v-if="showDetail != 'detail'">
                    <el-button @click="submitEditForm('false')">驳 回</el-button>
                    <el-button type="primary" @click="submitEditForm('true')">同 意</el-button>
                </div>
            </div>
        </el-form>
    </el-dialog>


</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {ElRow},
        props: ['paramsObjDetail'],//接受父组件数据
        created(){
            let vm = this;
            if(vm.paramsObjDetail) {
                if(vm.paramsObjDetail.type) {
                    vm.showDetail = vm.paramsObjDetail.type;
                }
                vm.showThrowLeaseApplyDetail = vm.paramsObjDetail.showThrowLeaseApplyDetail;
                vm.throwLeaseApply = vm.paramsObjDetail.param;
            }
        },
        data() {
            return {
                throwLeaseApply: {
                    id:"",
                    taskId:"",//任务ID
                    processInstanceId:"",//流程实例ID
                    processDefinitionKey:"",//流程key
                    pdId:"",//流程定义Id
                    ctCode:"",
                    ctName:"",//合同编号
                    approvalStatus:"",//审批状态
                    approvalStatusName:"",//退租状态名称
                    partyA:"",//甲方
                    partyAAddress:"",//甲方地址
                    partyALegalRepresentative:"",//甲方法定代表
                    partyAPhone:"",//甲方电话
                    partyB:"",//乙方
                    partyBAddress:"",//乙方地址
                    partyBLegalRepresentative:"",//乙方法定代表
                    partyBPhone:"",//乙方电话
                    throwLeaseDate:"",//退租日期
                    leasedArea:"",//租赁区域
                    throwLeaseArea:"",//退租面积
                    monthlyRent:"",//月租金
                    refundBank:"",//退款银行
                    refundAccountName:"",//退款账号姓名
                    refundAccountNumber:"",//退款银行账号
                    throwLeaseRemark:"",//退租备注说明
                },
                showThrowLeaseApplyDetail: false,//控制弹窗是否显示
                promptInputValue:"",//默认审批意见
                approveReason:"",//审批意见
                showDetail:"",//判断是否是详情展示
            }
        },
        methods: {
            //点击弹窗关闭时调的函数
            beforeClose() {
                let vm = this;
                vm.showThrowLeaseApplyDetail = false;
                let data = {
                    showThrowLeaseApplyDetail:vm.showThrowLeaseApplyDetail,
                    showDetail:vm.showDetail,
                };
                vm.$emit('backParam',data);
            },

            //重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

            //点击
            submitEditForm(val) {
                let vm = this;
                let obj = {};
                obj.entity = vm.throwLeaseApply;

                let title = '';
                //代表通过
                if(val == 'true') {
                    vm.promptInputValue = "同意";
                    //审批置为同意
                    obj.verify = true;
                    title = '同意';
                } else if(val == 'false') {//代表驳回
                    vm.promptInputValue = "";
                    //审批置为驳回
                    obj.verify = false;
                    title = '驳回';
                }
                vm.$prompt('审批意见', title, {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputValue: vm.promptInputValue,
                    inputPattern: /[\S]/,
                    inputErrorMessage: '请输入审批意见'
                }).then(({ value }) => {
                    //审批意见
                    obj.approveReason = value;

                    let option = {
                        method: 'POST',
                        data: obj,
                        url: "throwLeaseApplyActiviti/verify",
                    };
                    this.$ajax(
                        option
                    ).then(function (response) {
                        if(response){
                            vm.beforeClose();
                        }
                    }).catch(function (error) {
                        vm.$alert('页面：提交申请退租单出错咯', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }).catch(() => {
                });
            }
        }
    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

</style>



