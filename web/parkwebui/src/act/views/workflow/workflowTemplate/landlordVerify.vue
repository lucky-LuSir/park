<template>
    <div class="root" ref="myRoot">
        <el-dialog class="hasfooter-dialog_wrapper" title="大房东合同申请" width="60%" :visible.sync="showVerify" :before-close="beforeClose" :center="true">
            <el-form :model="landlordContract" label-width="100px" ref="landlordContract" labelSuffix="：" class="form_style">
                <div class="common-title-div">
                    <span class="common-title-font">园区信息</span>
                </div>
                <div class="customer-info-div">
                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                            <el-form-item label="园区" prop="pkCode">
                                {{landlordContract.pkName}}
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                            <el-form-item label="承租面积" prop="acreage">
                                {{landlordContract.acreage}}
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>

                <div class="common-title-div">
                    <span class="common-title-font">业主信息</span>
                </div>
                <div class="customer-info-div">
                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                            <el-form-item label="业主名字" prop="landlordName">
                                {{landlordContract.landlordName}}
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                            <el-form-item label="业主性别" prop="landlordSex">
                                {{landlordContract.landlordSexName}}
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                            <el-form-item label="业主电话" prop="landlordPhone">
                                {{landlordContract.landlordPhone}}
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>

                <div class="common-title-div">
                    <span class="common-title-font">签约人信息</span>
                </div>
                <div class="customer-info-div">
                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                            <el-form-item label="签约人" prop="contractorCode">
                                {{landlordContract.contractor}}
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                            <el-form-item label="电话" prop="contractorPhone">
                                {{landlordContract.contractorPhone}}
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>

                <div class="common-title-div">
                    <span class="common-title-font">合同信息</span>
                </div>
                <div class="customer-info-div">
                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                            <el-form-item label="开始时间" prop="contractStartDate">
                                {{landlordContract.contractStartDate}}
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                            <el-form-item label="结束时间" prop="contractEndDate">
                                {{landlordContract.contractEndDate}}
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label="提前交租" prop="advanceDay">
                                {{landlordContract.advanceDay}}
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label="单价" prop="rentUnivalence">
                                {{landlordContract.rentUnivalence}}<template slot="append">{{landlordContract.rentUnitName}}</template>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label="付款周期" prop="depositRule">
                                押&nbsp; {{landlordContract.depositRule}} 月&nbsp;&nbsp;付 &nbsp;{{landlordContract.payRule}} 月
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label="押金" prop="deposit">
                                {{landlordContract.deposit}}<template slot="append">元</template>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>

                <div class="common-title-div">
                    <span class="common-title-font">递增规则</span>
                </div>
                <div class="customer-info-div">
                    <el-row>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" v-if="landlordContract.hasDetailAdd == true" v-for="(item, index) in progressiveIncreaseDetailList" :key="index">
                            <el-form-item>
                                第&nbsp; {{ index + 1 }} 年&nbsp;递增&nbsp;{{item}}%
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" v-if="landlordContract.hasDetailAdd == false">
                            <el-form-item>
                                每&nbsp;{{landlordContract.rentIncreasingCycle}}年&nbsp;&nbsp;递增&nbsp;{{landlordContract.rentIncreaseProportion}}%
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>

                <div class="common-title-div">
                    <span class="common-title-font">大房东租金</span>
                </div>
                <div class="customer-info-div">
                    <el-table :data="landlordContract.landlordRentEntityList" border style="width: 100%;margin-top: 10px;line-height: 20px;">
                        <el-table-column label="开始日期">
                            <template slot-scope="scope">{{ scope.row.costStartTime | timeDate}}</template>
                        </el-table-column>
                        <el-table-column label="结束日期">
                            <template slot-scope="scope">{{ scope.row.costEndTime | timeDate}}</template>
                        </el-table-column>
                        <el-table-column label="截止日期">
                            <template slot-scope="scope">{{ scope.row.deadline | timeDate}}</template>
                        </el-table-column>
                        <el-table-column label="支付状态">
                            <template slot-scope="scope">{{ scope.row.payStatusName }}</template>
                        </el-table-column>
                        <el-table-column label="本期租金">
                            <template slot-scope="scope">{{ scope.row.rentPrice | checkNullNumber}}</template>
                        </el-table-column>
                        <el-table-column label="租金票面金额">
                            <template slot-scope="scope">{{ scope.row.rentOnInvoice | checkNullNumber}}</template>
                        </el-table-column>
                        <el-table-column label="租金应付税额">
                            <template slot-scope="scope">{{ scope.row.rentTaxPrice | checkNullNumber}}</template>
                        </el-table-column>
                        <el-table-column label="租金实际税点">
                            <template slot-scope="scope">{{ scope.row.rentRealTaxPoint | checkNullPoint}}</template>
                        </el-table-column>
                        <el-table-column label="租金票面税点">
                            <template slot-scope="scope">{{ scope.row.rentInvoiceTaxPoint | checkNullPoint}}</template>
                        </el-table-column>
                        <el-table-column label="本期物业费">
                            <template slot-scope="scope">{{ scope.row.propertyPrice | checkNullNumber}}</template>
                        </el-table-column>
                        <el-table-column label="物业票面金额">
                            <template slot-scope="scope">{{ scope.row.propertyOnInvoice | checkNullNumber}}</template>
                        </el-table-column>
                        <el-table-column label="物业应付税额">
                            <template slot-scope="scope">{{ scope.row.propertyTaxPrice | checkNullNumber}}</template>
                        </el-table-column>
                        <el-table-column label="物业票面税点">
                            <template slot-scope="scope">{{ scope.row.propertyInvoiceTaxPoint | checkNullPoint }}</template>
                        </el-table-column>
                        <el-table-column label="物业实际税点">
                            <template slot-scope="scope">{{ scope.row.propertyRealTaxPoint | checkNullPoint }}</template>
                        </el-table-column>
                        <el-table-column label="其他应付">
                            <template slot-scope="scope">{{ scope.row.otherPrice | checkNullNumber }}</template>
                        </el-table-column>
                        <el-table-column label="优惠金额">
                            <template slot-scope="scope">{{ scope.row.discountsPrice | checkNullNumber }}</template>
                        </el-table-column>
                        <el-table-column label="总应付金额">
                            <template slot-scope="scope">{{ scope.row.totalPrice | checkNullNumber}}</template>
                        </el-table-column>
                        <el-table-column label="已付总金额">
                            <template slot-scope="scope">{{ scope.row.paidPrice | checkNullNumber}}</template>
                        </el-table-column>
                    </el-table>
                </div>

                <!-- 末尾提交按钮处 -->

            </el-form>
            <div slot="footer" style="height: 50px;margin-top: 20px;">
                <el-button style="float: left;margin-left: 45%;" @click="submitEditForm('false')">
                    驳回
                </el-button>
                <el-button style="float: left;" type="primary" @click="submitEditForm('true')">同意</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElFormItem from "../../../../../node_modules/element-ui/packages/form/src/form-item";
    export default {
        components: {
            ElFormItem,
            ElRow
        },
        props: ['paramsVerify'], // 接收父组件数据
        created() {
            let vm = this;
            vm.showVerify = vm.paramsVerify.showVerify;
            vm.landlordContract = vm.paramsVerify.param;
            if (vm.landlordContract.progressiveIncreaseDetail) {
                let progressiveIncreaseDetail = vm.landlordContract.progressiveIncreaseDetail;
                vm.progressiveIncreaseDetailList = progressiveIncreaseDetail.split(",");
                vm.landlordRentEntityList = vm.landlordContract.landlordRentEntityList;
            }

        },
        data() {
            return {
                showVerify: false,
                progressiveIncreaseDetailList: [], //递增详细
                landlordContract: {}, //合同信息
                verify: true, //同意或驳回
                promptInputValue: '', //同意或驳回的备注
                landlordRentEntityList: {},
            }
        },

        mounted() {
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        methods: {
            //点击
            submitEditForm(val) {
                let vm = this;
                let obj = {};
                let title = '';

                //代表通过
                if (val == 'true') {
                    //审批置为同意
                    vm.verify = true;
                    vm.promptInputValue = "同意";
                    title = '同意';
                } else if (val == 'false') { //代表驳回
                    //审批置为同意
                    vm.verify = false;
                    vm.promptInputValue = "";
                    title = '驳回';
                }
                vm.$prompt('审批意见', title, {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputValue: vm.promptInputValue,
                    inputPattern: /[\S]/,
                    inputErrorMessage: '请输入审批意见'
                }).then(({
                    value
                }) => {
                    //审批意见
                    //   obj.approveReason = value;
                    obj.approveReason = value;
                    obj.entity = vm.landlordContract;
                    obj.verify = vm.verify;
                    let option = {
                        method: 'POST',
                        data: obj,
                        url: "landlordRentApply/verify",
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
                }).catch(() => {});
            },

            beforeClose() {
                let vm = this;
                vm.showVerify = false;
                let data = {
                    showVerify: vm.showVerify,
                };
                vm.$emit('backParam', data);
            },
        },

        filters: {
            checkNullNumber: function(value) {
                if (!value) {
                    return '0' + " 元";
                } else {
                    return value + " 元";
                }
            },

            checkNullPoint: function(value) {
                if (!value) {
                    return "";
                } else {
                    return value + " %";
                }
            }
        },
    }
</script>

<style scoped>
    .root {
        min-width: 1000px;
        min-height: 900px;
        background-color: rgb(242, 242, 242);
    }

    .form_style {
        overflow-y: auto;
        overflow-x: hidden;
    }

    /*导航栏*/
    .navigation_div {
        float: left;
        margin-left: 20px;
        margin-top: -45px;
        text-align: left;
        position: fixed;
    }

    /*公共标题 div*/
    .common-title-div {
        background-color: white;
        height: 50px;
        width: 100%;
        margin-top: 10px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*公共标题 文字*/
    .common-title-font {
        float: left;
        font-size: 17px;
        color: #409EFF;
        margin-left: 20px;
        line-height: 50px;
    }

    /*基本信息 div*/
    .customer-info-div {
        background-color: white;
        text-align: left;
    }

    /*输入框 第一行*/
    .input-col1 {
        margin-top: 10px;
        height: 60px;
    }
</style>