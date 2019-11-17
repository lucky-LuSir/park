<template>
    <!-- 租金缴费弹窗 -->
    <el-dialog title="租金缴费办理" style="line-height: 30px;" width="70%" :visible.sync="showPay"
               :before-close="beforeClose"
               :center="true">
        <el-form :model="payInfo" ref="payInfo" :rules="rules" label-width="110px" style="text-align: left;">
            <el-row >
                <el-col :span="8">
                    <div class="grid-content">
                        <el-form-item label="应收金额：" label-width="130px">
                            {{rentInfo.totalPrice | initNum}}元
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="8">
                    <div class="grid-content">
                        <el-form-item label="已收金额：">
                            {{rentInfo.paidPrice | initNum}}元
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="8">
                    <div class="grid-content">
                        <el-form-item label="已优惠金额：">
                            {{rentInfo.discountsPrice | initNum}}元
                        </el-form-item>
                    </div>
                </el-col>

            </el-row>

            <el-row>
                <el-col :span="24">
                    <div class="grid-content">
                        <el-form-item label-width="130px" label="待收金额：">
                            {{waitPaid}}元
                        </el-form-item>
                    </div>
                </el-col>
            </el-row>
            <el-row >
                <el-col :span="24">
                    <div class="grid-content" style="width: 750px;height: 50px;">
                        <el-form-item label="缴费金额：" label-width="130px" prop="realRent">
                            <el-input clearable style="width: 221px;"
                                      v-model="payInfo.realRent"><template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </div>
                </el-col>
            </el-row>

            <el-row >
                <el-col :span="24">
                    <div class="grid-content"  style="width: 750px;height: 50px;">
                        <el-form-item label="此次优惠金额：" label-width="130px" prop="thisDiscountsPrice">
                            <el-input clearable style="width: 221px;"
                                      v-model="payInfo.thisDiscountsPrice"><template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </div>
                </el-col>
            </el-row>

            <el-row style="margin-top: 15px;height: 50px;">
                <el-col :span="8">
                    <el-form-item label="缴费时间：" label-width="130px" prop="payDate">
                        <el-date-picker
                                v-model="payInfo.payDate"
                                type="datetime"
                                placeholder="选择日期时间"
                                default-time="00:00:00">
                        </el-date-picker>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col>
                    <el-form-item label="备注：" label-width="130px" prop="remark">
                        <el-input clearable type="textarea" clearable style="width: 400px;"
                                  v-model="payInfo.remark">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>

        </el-form>

        <div slot="footer" class="dialog-footer">
            <el-button @click="restForm('payInfo')">取 消</el-button>
            <el-button type="primary" @click="rentSubmit('payInfo')">提 交</el-button>
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
        props: ['paramsPay'],//接受父组件数据
        created(){
            let vm = this;
            vm.showPay = vm.paramsPay.showPay;

            vm.rentInfo = vm.paramsPay.param ;

            vm.getWaitPaid(vm.rentInfo.totalPrice,vm.rentInfo.paidPrice,vm.rentInfo.discountsPrice);


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
                showPay:false,

                waitPaid:"",//待收金额

                payInfo:{
                    realRent:"",//实收
                    payDate: "",// 缴费时间
                    remark:"",//备注
                    costType:"",//缴费类型
                    thisDiscountsPrice:"",//此次优惠金额
                    receivableRent:"",
                }, //此次付款信息

                rentInfo: {
//                    id: "",
//                    code: "",// 应收编码
//                    ctCode: "",// 合同编号
//                    paidPrice: "",// 已收金额
//                    receivableRent:"", //应收
//                    payStatus: "",// 支付状态
//                    remark:"", //备注
//                    costType:"",//付款类型
                    payEntityList:[], //付款信息
                },
                payEntityList:[], //付款信息
                // 缴费办理、修改缴费信息校验
                rules: {
                    realRent: [
                        {required: true, message: '请输入缴费金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    payDate: [
                        {required: true, message: '请输入缴费日期', trigger: 'blur'},
                    ],
                    thisDiscountsPrice:[
                        {required: true, message: '请输入优惠金额', trigger: 'blur'},
                    ]
                },
            }

        },

        methods: {
            rentSubmit(formName){
                this.$refs[formName].validate((valid) => {
                    if(valid){
                        let vm = this;
                        let dataObj = {};
                        let discountsPrice = Number(vm.rentInfo.discountsPrice)  + Number(vm.payInfo.thisDiscountsPrice);
                        vm.rentInfo.discountsPrice = discountsPrice;
                        let paidPrice = Number(vm.rentInfo.paidPrice) + Number(vm.payInfo.realRent);

                        vm.rentInfo.paidPrice = paidPrice;

                        if(Number(vm.rentInfo.discountsPrice) + Number(vm.rentInfo.paidPrice) < Number(vm.rentInfo.totalPrice)){
                            vm.rentInfo.payStatus = '4';  //未结清
                        }else{
                            vm.rentInfo.payStatus = '2'; //已付款
                        }
                        vm.payInfo.costType = '7'; //缴费类型为7 大房东合同缴费
                        vm.payInfo.receivableRent = vm.waitPaid;

                       // let one = vm.payInfo;

                        vm.payEntityList.push(vm.payInfo);
                        vm.rentInfo.payEntityList = vm.payEntityList;

                        dataObj.entity = vm.rentInfo;


                        let options = {
                            method: "POST",
                            data: dataObj,
                            url: "landlordRent/pay",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (response) {
                                vm.$message({
                                    showClose: true,
                                    message: '付费成功！',
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

            //获取待付
            getWaitPaid(totalPrice,paidPrice, discountsPrice){
              if(totalPrice){
                  totalPrice = totalPrice;
              }  else{
                  totalPrice = 0;
              }
              if(paidPrice){
                  paidPrice = paidPrice;
              }else {
                  paidPrice = 0;
              }
              if(discountsPrice){
                  discountsPrice = discountsPrice;
              }else {
                  discountsPrice = 0;
              }

                let waitPaid =  totalPrice - paidPrice -discountsPrice;
                this.waitPaid = waitPaid.toFixed(2);
            },

            //点击弹窗关闭时调的函数
            beforeClose() {
                let vm = this;
                vm.showPay = false;
                let data = {
                    showPay:vm.showPay,
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
