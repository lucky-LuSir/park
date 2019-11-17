<template>
    <!--<div class="root" ref="myRoot">-->
    <!---->
    <!--</div>-->
    <el-dialog title="付款申请单" width="60%" :visible.sync="showthrowLeasePayApplyDetail"
               :close-on-click-modal="false"
               :close-on-press-escape="false"
               :before-close="beforeClose"
               :center="true">
        <el-form :model="throwLeasePayApply" ref="throwLeasePayApply"
                 label-width="100px" label-suffix=":">
            <div style="text-align: left">

                <el-row>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="汇款金额" prop="payMoney">
                            {{throwLeasePayApply.payMoney}} 元
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="约定汇款时间" prop="appointPayTime">
                            {{throwLeasePayApply.appointPayTime | timeDate}}
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="是否签订协议" prop="isContract">
                            <template v-if="throwLeasePayApply.isContract=='1'">是</template>
                            <template v-if="throwLeasePayApply.isContract=='0'">否</template>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row >
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="是否有发票" prop="isInvoice">
                            <template v-if="throwLeasePayApply.isInvoice == '1'">是</template>
                            <template v-if="throwLeasePayApply.isInvoice == '0'">否</template>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11" v-if="throwLeasePayApply.isInvoice == '1'">
                        <el-form-item class="inputLabel" label="到票日期" prop="invoiceInTime">
                            {{throwLeasePayApply.invoiceInTime | timeDate}}
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <!-- 付款方 -->
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="付款方" prop="payCompany">
                            {{throwLeasePayApply.payCompany}}
                        </el-form-item>
                    </el-col>

                    <!-- 收款方 -->
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="收款方" prop="payee">
                            <pre style="margin-top: 0px">{{throwLeasePayApply.payee}}</pre>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11" style="width: 100%">
                        <el-form-item class="inputLabel" label="申请事由" prop="paymentApplyRemark" >
                            <span v-html="throwLeasePayApply.paymentApplyRemark"></span>
                        </el-form-item>

                    </el-col>
                </el-row>
                <div class="block_box_img_special">
                    <div class="video_title" >
                        <p>退租申请单照片</p>
                    </div>
                    <div class="video_div">
                        <div v-for="(img,index) in outPictureList">
                            <img class="video" :src=" parkBaseUrl + 'file/read/' + img " @dblclick="toWatchPicture(parkBaseUrl + 'file/read/' + img)">
                        </div>
                    </div>
                </div>

                <div style="clear: both"></div>
                <div v-if="detailType=='deal'" slot="footer" style="text-align: center;margin-top: 15px">
                    <el-button @click="submitEditForm('false')">驳 回</el-button>
                    <el-button type="primary" @click="submitEditForm('true')">同 意</el-button>
                </div>
            </div>
        </el-form>

        <el-dialog :visible.sync="showWatchPicture" width="70%">
        <img width="90%" height="90%" :src="watchPicture"/>
        </el-dialog>

    </el-dialog>



</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElDialog from "../../../../../node_modules/element-ui/packages/dialog/src/component";
    export default {
        components: {
            ElDialog,
            ElRow},
        props: ['paramsPayObjDetail'],//接受父组件数据
        created(){
            let vm = this;
            if(vm.paramsPayObjDetail) {
                vm.showthrowLeasePayApplyDetail = vm.paramsPayObjDetail.showthrowLeasePayApplyDetail;
                vm.throwLeasePayApply = vm.paramsPayObjDetail.param;
                vm.outPictureList = vm.throwLeasePayApply.outPicture.split(',');
                vm.detailType = vm.paramsPayObjDetail.detailType;
            }
        },
        data() {
            //校验数字只能是正整数或小数
            let checkIntOrFloat = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(value))) {
                        callback(new Error('只能输入正整数或小数'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            // 检查【渠道电话】、【客户电话】是否唯一
            let checkPhoneOnly = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (!pattern.test(value)) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            return {
                showWatchPicture:false,
                watchPicture:"",
                detailType:"",
                outPictureList:[],
                throwLeasePayApply: {
                    throwLeaseApplyCode:'',//退租申请编号
                    id:"",
                    taskId:"", //任务id
                    executionId:"",//流程实例ID
                    processInstanceId:"",//流程实例ID
                    processDefinitionId:"", //流程定义ID
                    processDefinitionKey:"", //流程key
                    applyCode:"",
                    applicantCode:"",
                    applicantName:"",
                    approvalStatus:"", //审批状态
                    approveComment:"", //审批原因
                //    pdId:"",//流程定义Id

                    payMoney:"", //付款金额
                    appointPayTime:"", //约定付款时间
                    isContract:"", //是否有合同或协议
                    isInvoice:"", //是否有发票
                    invoiceInTime:"",//到票时间
                    payCompany:"",//付款方
                    payee:"",//收款方
                    paymentApplyRemark:"",//申请原因
                    outPicture:"",//图片
                },
                showthrowLeasePayApplyDetail: false,//控制弹窗是否显示
                promptInputValue:"",//默认审批意见
                approveReason:"",//审批意见
            }
        },
        methods: {
            //点击弹窗关闭时调的函数
            beforeClose() {
                let vm = this;
                vm.showthrowLeasePayApplyDetail = false;
                let data = {
                    detailType:vm.detailType,
                    showthrowLeasePayApplyDetail:vm.showthrowLeasePayApplyDetail,
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
                let title = '';

                //代表通过
                if(val == 'true') {
                    //审批置为同意
                    vm.throwLeasePayApply.approvalStatus = "5";
                    vm.promptInputValue = "同意";
                    title = '同意';
                } else if(val == 'false') {//代表驳回
                    //审批置为同意
                    vm.throwLeasePayApply.approvalStatus = "4";
                    vm.promptInputValue = "";
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
                 //   obj.approveReason = value;
                    vm.throwLeasePayApply.approveComment = value;
                    obj.entity = vm.throwLeasePayApply;
                    obj.throwLeaseApplyCode = vm.throwLeasePayApply.throwLeaseApplyCode;
                    let option = {
                        method: 'POST',
                        data: obj,
                        url: "throwLeasePayApplyActiviti/verify",
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
            },

            toWatchPicture(url){
                let vm = this;
                vm.watchPicture = url;
                vm.showWatchPicture = true;
            }
        }
    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }
    floor-data-ul li {
        float: left;
        list-style: none;
        height: 20px;
        line-height: 20px;
        font-size: 13px;
    }

    /*视频信息特殊样式*/
    .block_box_img_special {
        min-height: 40px;
        background-color: white;
        padding-bottom: 30px;
        margin-bottom: 30px;
        margin-top: -29px;
        margin-left: 60px;
    }

    .video_div {
        float: left;
        width: 90%;
    }

    /*基本信息配套信息*/
    .basicmessage_mating {
        text-align: left;
    }

    /*视频信息*/
    .block_box_img {
        background-color: white;
        padding-bottom: 30px;
        margin-bottom: 30px;
    }

    /*清除浮动*/
    .clear {
        clear: both
    }

    /*视频列标题*/
    .video_title {
        height: 50px;
        line-height: 50px;
        width: 160px;
        color: #FE980F;
    }

    /*视频列内容*/
    .video_content {
        float: left;
        width: 240px;
        padding-top: 50px;
        padding-bottom: 50px;
        background-color: #F1F1F1;
        /*margin-left: 20px;*/
        margin-top: 20px;
        text-align: center;
    }

    .video {
        width: 240px;
        float: left;
        margin-left: 20px;
        height: 160px;
        margin-top: 20px;
    }

    /*空格*/
    /*.konge_row{*/
    /*min-height: 15px;*/
    /*min-width: 1000px;*/
    /*background-color: white;*/
    /*}*/
    /*.v-modal{*/
    /*background: #ffffff;*/
    /*}*/
    .v-modal {
        background-color: inherit;
    }
</style>



