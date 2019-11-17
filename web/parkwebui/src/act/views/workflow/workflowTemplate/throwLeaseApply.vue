<template>
    <el-dialog title="申请退租单" class="notfooter-dialog_wrapper" width="80%" :visible.sync="showThrowLeaseApply" :close-on-click-modal="false" :close-on-press-escape="false" :before-close="beforeClose" :center="true">
        <el-form :model="throwLeaseApply" ref="throwLeaseApply" :rules="rules" label-width="100px" label-suffix=":">
            <div style="text-align: left;">
                <el-row>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="退租类型" prop="throwLeaseType">
                            <el-radio-group v-model="throwLeaseApply.throwLeaseType">
                                <el-radio label="1">到期退租</el-radio>
                                <el-radio label="2">违约退租</el-radio>
                                <el-radio label="3">协商退租</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row v-if="throwLeaseApply.throwLeaseType == 3">
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="协商退租" prop="dutyType">
                            <el-radio-group v-model="throwLeaseApply.dutyType">
                                <el-radio label="1">甲方责任</el-radio>
                                <el-radio label="2">乙方责任</el-radio>
                                <el-radio label="3">双方无责</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <!-- 合同信息 -->
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="合同编号" prop="ctName">
                            <el-input v-model="throwLeaseApply.ctName" @blur="checkCtName()"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="租赁区域" prop="leasedArea">
                            <el-input v-model="throwLeaseApply.leasedArea"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="面积" prop="throwLeaseArea">
                            <el-input v-model="throwLeaseApply.throwLeaseArea">
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="月租金" prop="monthlyRent">
                            <el-input v-model="throwLeaseApply.monthlyRent">
                                <template slot="append">元/月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <!-- 甲方 -->
                    <el-col :xs="9" :sm="9" :md="9" :lg="9" :xl="9">
                        <el-form-item label="甲方" prop="partyA">
                            <el-input v-model="throwLeaseApply.partyA"></el-input>
                        </el-form-item>
                    </el-col>
                    <!-- 乙方 -->
                    <el-col :xs="9" :sm="9" :md="9" :lg="9" :xl="9">
                        <el-form-item label="乙方" prop="partyB">
                            <el-input v-model="throwLeaseApply.partyB"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="金额类型" prop="moneyType">
                            <el-radio-group v-model="throwLeaseApply.moneyType">
                                <el-radio label="1">收入</el-radio>
                                <el-radio label="2">支出</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="金额" prop="money">
                            <el-input v-model="throwLeaseApply.money">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 退租日期 -->
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="退租日期" prop="throwLeaseDate">
                            <el-date-picker v-model="throwLeaseApply.throwLeaseDate" align="left" type="date" placeholder="退租日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 事项说明 -->
<!--                <el-row>-->
<!--                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">-->
<!--                        <el-form-item label="事项说明" prop="throwLeaseRemark">-->
<!--                            <el-input type="textarea" rows="7" v-model="throwLeaseApply.throwLeaseRemark"></el-input>-->
<!--                        </el-form-item>-->
<!--                    </el-col>-->
<!--                </el-row>-->
                <el-row>
                <el-form-item label="事项说明" prop="throwLeaseRemark">
                <wangEditor @editorContent="editorContent" style="margin-bottom: 15px;" :setContent="throwLeaseApply.throwLeaseRemark"></wangEditor>
                </el-form-item>
                </el-row>
                <!-- 退款账号信息 -->
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="退款银行" prop="refundBank">
                            <el-input v-model="throwLeaseApply.refundBank"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="账号姓名" prop="refundAccountName">
                            <el-input v-model="throwLeaseApply.refundAccountName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="银行账号" prop="refundAccountNumber">
                            <el-input v-model="throwLeaseApply.refundAccountNumber"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- 甲方信息 -->
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="甲方法定代表" label-width="110px" prop="partyALegalRepresentative">
                            <el-input v-model="throwLeaseApply.partyALegalRepresentative"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                        <el-form-item>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="乙方法定代表" label-width="110px" prop="partyBLegalRepresentative">
                            <el-input v-model="throwLeaseApply.partyBLegalRepresentative"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="甲方电话" prop="partyAPhone">
                            <el-input v-model="throwLeaseApply.partyAPhone"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                        <el-form-item>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="乙方电话" prop="partyBPhone">
                            <el-input v-model="throwLeaseApply.partyBPhone"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="甲方地址" prop="partyAAddress">
                            <el-input v-model="throwLeaseApply.partyAAddress"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="1" :sm="1" :md="1" :lg="1" :xl="1">
                        <el-form-item>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="乙方地址" prop="partyBAddress">
                            <el-input v-model="throwLeaseApply.partyBAddress"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <div v-if="openType == 'add'" slot="footer" style="text-align: center">
                    <el-button @click="resetForm('throwLeaseApply')">重 置</el-button>
                    <el-button type="primary" @click="submitForm('throwLeaseApply')">提 交</el-button>
                </div>
                <div v-if="openType == 'edit'" slot="footer" style="text-align: center">
                    <el-button @click="resetForm('throwLeaseApply')">重 置</el-button>
                    <el-button type="primary" @click="editSubmitForm('throwLeaseApply')">调 整</el-button>
                </div>
            </div>
        </el-form>
    </el-dialog>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import wangEditor from '@/common/components/wangEditor.vue';

    export default {
        components: {
            ElCol,
            ElRow,
            wangEditor
        },
        props: ['paramsApplyObj'], //接受父组件数据
        created() {
            let vm = this;
            if (vm.paramsApplyObj) {
                vm.openType = vm.paramsApplyObj.type;
                vm.showThrowLeaseApply = vm.paramsApplyObj.showThrowLeaseApply;
                if (vm.openType == "edit") {
                    vm.throwLeaseApply = vm.paramsApplyObj.param;
                    vm.checkCtname = vm.throwLeaseApply.ctName;
                } else if (vm.openType == "add" && vm.paramsApplyObj.ctName) {
                    vm.throwLeaseApply.ctName = vm.paramsApplyObj.ctName;
                    vm.checkCtName();
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

            //校验合同是否存在
            let toCheckCtName = (rule, value, callback) => {
                let vm = this;
                setTimeout(() => {
                    if (value != this.checkCtname) {
                        callback(new Error('未查到该合同'));
                        vm.throwLeaseApply.ctCode = '';
                        vm.throwLeaseApply.leasedArea = '';
                        vm.throwLeaseApply.throwLeaseArea = '';
                        vm.throwLeaseApply.monthlyRent = '';
                        vm.throwLeaseApply.partyB = '';
                    } else {
                        callback();
                    }
                }, 1000);
            };

            return {
                checkCtname: '', //检测已存在的合同
                throwLeaseApply: {
                    throwLeaseType: '',
                    dutyType: '',
                    moneyType: '',
                    money: '',
                    key: "", //流程key
                    pdId: "", //流程定义Id
                    ctCode: "",
                    ctName: "", //合同编号
                    approvalStatus: "", //审批状态
                    approvalStatusName: "", //退租状态名称
                    partyA: "", //甲方
                    partyAAddress: "", //甲方地址
                    partyALegalRepresentative: "", //甲方法定代表
                    partyAPhone: "", //甲方电话
                    partyB: "", //乙方
                    partyBAddress: "", //乙方地址
                    partyBLegalRepresentative: "", //乙方法定代表
                    partyBPhone: "", //乙方电话
                    throwLeaseDate: "", //退租日期
                    leasedArea: "", //租赁区域
                    throwLeaseArea: "", //退租面积
                    monthlyRent: "", //月租金
                    refundBank: "", //退款银行
                    refundAccountName: "", //退款账号姓名
                    refundAccountNumber: "", //退款银行账号
                    throwLeaseRemark: "", //退租备注说明
                },
                activeName: "first",
                showThrowLeaseApply: true, //控制弹窗是否显示
                openType: "", //打开方式（'add'：申请  ‘apply’：审批）
                // 退租类型
                throwLeaseType: [{
                    value: '1',
                    label: '到期退租'
                }, {
                    value: '2',
                    label: '违约退租'
                }, {
                    value: '3',
                    label: '协商退租'
                }],
                // 退租责任类型
                dutyType: [{
                    value: '1',
                    label: '甲方责任'
                }, {
                    value: '2',
                    label: '乙方责任'
                }, {
                    value: '3',
                    label: '双方无责'
                }],
                // 金额类型
                moneyType: [{
                    value: '1',
                    label: '收入'
                }, {
                    value: '2',
                    label: '支出'
                }],

                rules: {
                    throwLeaseType: [{
                        required: true,
                        message: '请选择退租类型',
                        trigger: 'change'
                    }, ],
                    dutyType: [{
                        required: true,
                        message: '请选择退租责任类型',
                        trigger: 'change'
                    }, ],
                    moneyType: [{
                        required: true,
                        message: '请选择金额类型',
                        trigger: 'change'
                    }, ],
                    money: [{
                            required: true,
                            message: '请输入金额',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],

                    ctName: [{
                            required: true,
                            message: '请输入合同编号',
                            trigger: 'blur'
                        },
                        {
                            validator: toCheckCtName
                        },
                    ],
                    partyA: [{
                        required: true,
                        message: '请输入甲方信息',
                        trigger: 'blur'
                    }, ],
                    partyB: [{
                        required: true,
                        message: '请输入乙方信息',
                        trigger: 'blur'
                    }, ],
                    throwLeaseDate: [{
                        required: true,
                        message: '请选择退租日期',
                        trigger: 'change'
                    }],
                    leasedArea: [{
                        required: true,
                        message: '请输入租赁区域',
                        trigger: 'blur'
                    }, ],
                    throwLeaseArea: [{
                            required: true,
                            message: '请输入退租面积',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    monthlyRent: [{
                            required: true,
                            message: '请输入月租金',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    throwLeaseRemark: [{
                        required: true,
                        message: '请输入事项说明',
                        trigger: 'blur'
                    }, ],
                    refundBank: [{
                        required: true,
                        message: '请输入退款银行',
                        trigger: 'blur'
                    }, ],
                    refundAccountName: [{
                        required: true,
                        message: '请输入账号姓名',
                        trigger: 'blur'
                    }, ],
                    refundAccountNumber: [{
                        required: true,
                        message: '请输入银行账号',
                        trigger: 'blur'
                    }, ],
                    partyAAddress: [{
                        required: true,
                        message: '请输入甲方地址',
                        trigger: 'blur'
                    }, ],
                    partyALegalRepresentative: [{
                        required: true,
                        message: '请输入甲方法定代表',
                        trigger: 'blur'
                    }, ],
                    partyAPhone: [{
                            required: true,
                            message: '请输入甲方电话',
                            trigger: 'blur'
                        },
                        {
                            validator: checkPhoneOnly
                        },
                    ],
                    partyBAddress: [{
                        required: true,
                        message: '请输入乙方地址',
                        trigger: 'blur'
                    }, ],
                    partyBLegalRepresentative: [{
                        required: true,
                        message: '请输入乙方法定代表',
                        trigger: 'blur'
                    }, ],
                    partyBPhone: [{
                            required: true,
                            message: '请输入乙方电话',
                            trigger: 'blur'
                        },
                        {
                            validator: checkPhoneOnly
                        },
                    ],
                }
            }
        },
        methods: {
            //校验合同是否存在
            checkCtName() {
                let vm = this;
                let obj = {};

                if (vm.throwLeaseApply.ctName == '') {
                    return false;
                }

                obj.entity = {
                    ctName: vm.throwLeaseApply.ctName,
                    ctStatus: "4",
                };

                let option = {
                    method: 'POST',
                    data: obj,
                    url: "contract/oneConDetail",
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (response) {
                        if (response.data.result) {
                            vm.checkCtname = response.data.result.ctName;
                            vm.throwLeaseApply.ctCode = response.data.result.ctCode;
                            vm.throwLeaseApply.leasedArea = response.data.result.pkName;
                            vm.throwLeaseApply.throwLeaseArea = response.data.result.totalRentalArea;
                            vm.throwLeaseApply.monthlyRent = response.data.result.rentTotal;
                            vm.throwLeaseApply.partyB = response.data.result.companyName;
                            vm.throwLeaseApply.partyA = response.data.result.cpyName;
                        }
                    }
                }).catch(function(error) {
                    vm.$alert('页面：检测合同出错咯', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            //点击弹窗关闭时调的函数
            beforeClose() {
                let vm = this;
                vm.showThrowLeaseApply = false;
                let data = {
                    showThrowLeaseApply: vm.showThrowLeaseApply,
                    openType: vm.openType,
                    //                    key:vm.throwLeaseApply.key
                };
                vm.$emit('backParam', data);
            },

            //重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

            //申请提交
            submitForm(formName) {
                let vm = this;

                let obj = {};
                obj.entity = vm.throwLeaseApply;
                //进行校验
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let option = {
                            method: 'POST',
                            data: obj,
                            url: "throwLeaseApplyActiviti/createApply",
                        };
                        this.$ajax(
                            option
                        ).then(function(response) {
                            if (response) {
                                vm.beforeClose();
                            }
                        }).catch(function(error) {
                            vm.$alert('页面：提交申请退租单出错咯', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {}
                            });
                        });
                    }
                });
            },

            //调整申请
            editSubmitForm(formName) {
                let vm = this;

                let obj = {};
                obj.entity = vm.throwLeaseApply;
                obj.reapply = true;
                //进行校验
                this.$refs[formName].validate((valid) => {
                    if (valid) {

                        let option = {
                            method: 'POST',
                            data: obj,
                            url: "throwLeaseApplyActiviti/applicantResubmit",
                        };
                        this.$ajax(
                            option
                        ).then(function(response) {
                            if (response) {
                                vm.beforeClose();
                            }
                        }).catch(function(error) {
                            vm.$alert('页面：修改申请退租单出错咯', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {}
                            });
                        });
                    }
                });
            },

            // 获得子组件的数据
            editorContent(val) {
                this.throwLeaseApply.throwLeaseRemark = val;
            },
        }
    }
</script>

<style scoped>
    /*.nofooter-top10-dialog /deep/ .el-dialog__body {*/
        /*height: 70vh;*/
        /*overflow: scroll;*/
    /*}*/

    body {
        margin-left: 200px;
        margin-right: 200px;
    }
</style>