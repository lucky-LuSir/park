<template>
<!-- 租金缴费弹窗 -->
<el-dialog title="租金缴费办理" style="line-height: 30px;" :visible.sync="rentPyWindow"
           :before-close="beforeClose"
           :center="true">
    <el-form :model="paymentInput" ref="paymentInput" :rules="rules" label-width="110px" style="text-align: left;">
        <el-row :gutter="20">
            <el-col :span="8">
                <div class="grid-content">
                    <el-form-item label="应收金额：">
                        <span class="bd-info-value">{{paymentInput.receivableRent}}元</span>
                    </el-form-item>
                </div>
            </el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="24">
                <div class="grid-content" style="width: 750px;height: 50px;">
                    <el-form-item label="缴费金额：" prop="realRent">
                        <el-input clearable style="width: 221px;"
                                  v-model="paymentInput.realRent"><template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                </div>
            </el-col>
        </el-row>

        <el-row style="margin-top: 15px;height: 50px;">
            <el-col :span="8">
                <el-form-item label="缴费时间：" prop="payDate">
                    <el-date-picker
                            v-model="paymentInput.payDate"
                            type="datetime"
                            placeholder="选择日期时间"
                            default-time="00:00:00">
                    </el-date-picker>
                </el-form-item>
            </el-col>
        </el-row>

        <el-row>
            <el-col>
                <el-form-item label="备注：" prop="remark">
                    <el-input clearable type="textarea" clearable style="width: 400px;"
                              v-model="paymentInput.remark">
                    </el-input>
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="restForm('paymentInput')">取 消</el-button>
        <el-button type="primary" @click="rentSubmit('paymentInput')">提 交</el-button>
    </div>
</el-dialog>
</template>
<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    export default{
        components: {
            ElCol,
            ElRow},
        props: ['paramsRentPay'],//接受父组件数据
        created(){
            let vm = this;
            vm.rentPyWindow = vm.paramsRentPay.rentPyWindow;
            vm.paymentInput.receivableRent = vm.paramsRentPay.receivableRent;
            vm.paymentInput.code = vm.paramsRentPay.applyCode;
            vm.paymentInput.ctCode = vm.paramsRentPay.ctCode;
        },
        data(){
            let checkNumber = (rule, value, callback) => {
                setTimeout(() => {
                    if (value < 0) {
                        callback(new Error('数字值不能为负数'));
                    } else if (value > 99999999.99) {
                        callback(new Error('请输入0～99999999.99数字值'));
                    } else {
                        callback();
                    }
                }, 100);
            };
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
                rentPyWindow:false,

                payableData:'', //应收
                paymentInput: {
                    id: "",
                    code: "",// 应收编码
                    ctCode: "",// 合同编号
                    paidPrice: "",// 已收金额（每期缴费金额之和）
                    receivableRent:"", //应收
                    realRent: "",// 缴费金额（本次缴费金额）
                    realPrice: "",// 水、电、停车费实收金额
                    payDate: "",// 缴费时间
                    hasTax: false,// 租金是否含税
                    taxPoint: "",// 税率，值为1、2、3、4、5
                    taxPrice: "",// 税金
                    proHasTax: false,//物业费是否含税
                    proTaxPoint: "",//物业费税点
                    payStatus: "",// 支付状态
                    balance: "",// 余额
                    allPay: 0,//本次共缴
                    remark:"", //备注
                    costType:"",//付款类型

                    overdueDays: 0,//逾期天数
                    latePayment: 0,//滞纳金
                },

                // 缴费办理、修改缴费信息校验
                rules: {
                    realRent: [
                        {required: true, message: '请输入缴费金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    payDate: [
                        {required: true, message: '请输入缴费日期', trigger: 'blur'},
                    ]
                },
            }

        },

        methods: {
            rentSubmit(formName){
                let vm = this;
                let dataObj = {};
                vm.paymentInput.costType = '6';
                vm.paymentInput.payStatus = '2';
                dataObj.entity = vm.paymentInput;
                this.$refs[formName].validate((valid) => {
                    if(valid){
                        let options = {
                            method: "POST",
                            data: dataObj,
                            url: "rentReceiptPay/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (response) {
                                vm.$message({
                                    showClose: true,
                                    message: '申请成功！',
                                    type: 'success'
                                });
                                vm.beforeClose();
                            }
                        }).catch(function (error) {
                            vm.$alert(msg + '失败！', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                })
            },

            //点击弹窗关闭时调的函数
            beforeClose() {
                let vm = this;
                vm.rentPyWindow = false;
                let data = {
                    rentPyWindow:vm.rentPyWindow,
                };
                vm.$emit('backParam',data);
            },

            restForm(formName){
                this.$refs[formName].resetFields();
                this.beforeClose();
            },
        }

    }
</script>
