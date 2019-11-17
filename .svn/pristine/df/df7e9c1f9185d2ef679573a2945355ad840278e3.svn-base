<template>
    <!--<div class="root" ref="myRoot">-->
    <!---->
    <!--</div>-->
    <el-dialog title="付款申请单" class="notfooter-dialog_wrapper" width="80%" :visible.sync="showThrowLeasePayApply"
               :close-on-click-modal="false"
               :close-on-press-escape="false"
               :before-close="beforeClose"
               top="5vh"
               :center="true">
        <el-form :model="throwLeasePayApply" ref="throwLeasePayApply" :rules="rules"
                 label-width="100px" label-suffix=":">
            <div style="text-align: left">

                <el-row>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="汇款金额" prop="payMoney">
                            <el-input v-model="throwLeasePayApply.payMoney" readonly><template slot="append">元</template></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="约定汇款时间" prop="appointPayTime" label-width="110px">
                            <el-date-picker
                                    v-model="throwLeasePayApply.appointPayTime"
                                    align="left"
                                    type="date"
                                    placeholder="约定汇款时间">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="是否签订协议" prop="isContract" label-width="110px">
                            <el-radio v-model="throwLeasePayApply.isContract" label="1">是</el-radio>
                            <el-radio v-model="throwLeasePayApply.isContract" label="0">否</el-radio>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="是否有发票" prop="isInvoice" label-width="110px">
                            <el-radio v-model="throwLeasePayApply.isInvoice" label="1" @change="selectInvoice">是</el-radio>
                            <el-radio v-model="throwLeasePayApply.isInvoice" label="0" @change="selectInvoice">否</el-radio>
                        </el-form-item>
                    </el-col>
                    <el-col v-if="throwLeasePayApply.isInvoice=='1'" :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="到票日期" prop="invoiceInTime">
                            <el-date-picker
                                    v-model="throwLeasePayApply.invoiceInTime"
                                    align="left"
                                    type="date"
                                    placeholder="到票日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <!-- 付款方 -->
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="付款方" prop="payCompany">
                            <el-input v-model="throwLeasePayApply.payCompany"></el-input>
                        </el-form-item>
                    </el-col>

                    <!-- 收款方 -->
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item class="inputLabel" label="收款方" prop="payee">
                            <el-input type="textarea" :rows="3" v-model="throwLeasePayApply.payee"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11" style="width: 100%">
                        <el-form-item  label="申请事由" prop="paymentApplyRemark">
<!--                            <el-input  type="textarea" v-model="throwLeasePayApply.paymentApplyRemark"></el-input>-->
                            <wangEditor @editorContent="editorContent" style="margin-bottom: 15px;" :setContent="throwLeasePayApply.paymentApplyRemark"></wangEditor>
                        </el-form-item>
                    </el-col>
                </el-row>

                <div class="block_box_img_special">
                    <div class="video_title" >
                        <span>退租申请单照片(必填)</span>&nbsp;&nbsp;<span v-if="noPicture" style="color: red">请上传图片</span>
                    </div>
                    <div class="video_div">
                        <div v-for="(img,index) in outPictureList">
                            <img class="video" :src=" parkBaseUrl + 'file/read/' + img ">
                            <i class="el-icon-remove-outline" style="float: left"
                               @click="deleteOutPicture(index)"></i>
                        </div>
                        <div class="video_content">
                            <file @selectFile="selectOutPicture"></file>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="clear"></div>
                </div>


                <div v-if="openType=='add'" slot="footer" style="text-align: center">
                    <el-button @click="resetForm('throwLeasePayApply')">重 置</el-button>
                    <el-button type="primary" @click="submitForm('throwLeasePayApply')">提 交</el-button>
                </div>
                <div v-if="openType=='edit'" slot="foot" style="text-align: center">
                    <el-button @click="resetForm('throwLeasePayApply')">重 置</el-button>
                    <el-button @click="editUsbmitForm('throwLeasePayApply')">提 交</el-button>
                </div>
            </div>
        </el-form>
    </el-dialog>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import file from '../../../../common/components/file';
    import ElButton from "../../../../../node_modules/element-ui/packages/button/src/button";
    import wangEditor from '@/common/components/wangEditor.vue';
    export default {
        components: {
            ElButton,
            ElCol,
            ElRow,
            file,
            wangEditor
        },
        props: ['paramsPayObj'],//接受父组件数据
        created(){
            let vm = this;
            if(vm.paramsPayObj) {
                vm.openType = vm.paramsPayObj.type;
                vm.showThrowLeasePayApply = vm.paramsPayObj.showThrowLeasePayApply;
                vm.throwLeasePayApply.throwLeaseApplyCode = vm.paramsPayObj.throwLeaseApplyCode;
                vm.throwLeasePayApply.ctName = vm.paramsPayObj.ctName;
                vm.throwLeasePayApply.payMoney = vm.paramsPayObj.payMoney;
                vm.throwLeasePayApply.payee = vm.paramsPayObj.payee;
                vm.throwLeasePayApply.paymentApplyRemark = vm.paramsPayObj.paymentApplyRemark;
                if(vm.openType == 'edit'){
                    vm.throwLeasePayApply = vm.paramsPayObj.param;
                    vm.outPictureList = vm.throwLeasePayApply.outPicture.split(',');
                }
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

            let checkPicture = (rule, value, callback) => {
                let vm = this;
                setTimeout(() => {
                    if (vm.outPictureList.length>0) {
                        callback();
                    } else {
                        callback(new Error('只能输入正整数或小数'));
                    }
                }, 100);
            };


            return {
                noPicture:false,//是否选择图片
                openType:"",  //申请或审批
                ifInvoice:false,
                outPictureList:[],
                throwLeasePayApply: {
                    throwLeaseApplyCode:'',//退租申请编号
                    ctName:'',
                    ctCode:'',
                    pdId:"",//流程定义Id
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
            //    activeName: "first",
                showThrowLeasePayApply: true,//控制弹窗是否显示
                key:"",//流程key


                rules: {
                    payMoney: [
                        {required: true, message: '请输入付款金额', trigger: 'blur'},
                        {validator:checkIntOrFloat}
                    ],
                    appointPayTime: [
                        {required: true, message: '请输入约定付款时间', trigger: 'blur'},
                    ],
                    isContract: [
                        {required: true, message: '请选择是否有合同或协议', trigger: 'blur'},
                    ],
                    isInvoice: [
                        {required: true, message: '请选择是否有发票', trigger: 'blur'}
                    ],
                    invoiceInTime: [
                        {required: true, message: '请选择到票日期', trigger: 'blur'},
                    ],
                    payCompany: [
                        {required: true, message: '请输入付款方', trigger: 'blur'},
                    ],
                    payee: [
                        {required: true, message: '请输入收款方', trigger: 'blur'},
                    ],
                }
            }
        },
        methods: {
            selectInvoice(index){
                if(index==0){
                    this.ifInvoice = false;
                }else{
                    this.ifInvoice = true;
                }
            },
            selectOutPicture(urls){
                if(urls) {
                    for (var i = 0; i < urls.length; i++) {
                        if(urls[i].fileType != 'image'){
                            this.$message({
                                type: 'wang',
                                message: '仅支持上传图片！'
                            });
                            continue;
                        }
                        this.outPictureList.push(urls[i].fileCode);
                    }
                }
                if (this.outPictureList.length>0) {
                    this.noPicture = false;
                }
            },

            deleteOutPicture(index){
                this.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.outPictureList.splice(index, 1);
                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {
                });
            },

            //点击弹窗关闭时调的函数
            beforeClose() {
                let vm = this;
                vm.showThrowLeasePayApply = false;
                let data = {
                    showThrowLeasePayApply:vm.showThrowLeasePayApply,
                    openType:vm.openType,
                };
                vm.$emit('backParam',data);
            },

            //重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

            //申请提交
            submitForm(formName) {
                let vm = this;

                let obj = {};
                if(vm.outPictureList.length>0){
                    vm.noPicture = false;
                    vm.throwLeasePayApply.outPicture = vm.outPictureList.join(",");
                }else {
                    vm.noPicture = true;
                    return false;
                }
                obj.entity = vm.throwLeasePayApply;
                //进行校验
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let option = {
                            method: 'POST',
                            data: obj,
                            url: "throwLeasePayApplyActiviti/createApply",
                        };
                        this.$ajax(
                            option
                        ).then(function (response) {
                            if(response){
                                vm.beforeClose();
                            }
                        }).catch(function (error) {
                            vm.$alert('页面：提交申请退款单出错咯', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                });
            },

            //调整申请
            editUsbmitForm(formName) {
                let vm = this;

                let obj = {};
                if(vm.outPictureList.length>0){
                    vm.noPicture = false;
                    vm.throwLeasePayApply.outPicture = vm.outPictureList.join(",");
                }else {
                    vm.noPicture = true;
                    return false;
                }
                obj.entity = vm.throwLeasePayApply;
                obj.reapply = true;
                //进行校验
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let option = {
                            method: 'POST',
                            data: obj,
                            url: "throwLeasePayApplyActiviti/applicantResubmit",
                        };
                        this.$ajax(
                            option
                        ).then(function (response) {
                            if(response){
                                vm.beforeClose();
                            }
                        }).catch(function (error) {
                            vm.$alert('页面：提交申请退款单出错咯', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                });
            },
            // 获得子组件的数据
            editorContent(val) {
                this.throwLeasePayApply.paymentApplyRemark = val;
            },
        }
    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .el-col {
        width:50%;
    }
    .el-col {
        /*width: 40.83333%;*/
        /*width:45%;*/
    }
    /*.el-form-item__label {*/
    /*width:100% !important;*/
    /*}*/
    /*.el-form-item .el-form-item__label  {*/
    /*width:115px !important;*/
    /*}*/
    .inputLabel .el-form-item__label {
        width:31% !important;
    }
    .inputLabel .el-form-item__content {
        display: inline-block;
        width: 69% !important;
        margin-left: 0px !important;
    }
    .el-date-editor {
        width:100%;
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
        height: 100%;
        width: 220px;
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
<style>
    .v-modal {
        /*z-index: 2000 !important;*/
    }
</style>




