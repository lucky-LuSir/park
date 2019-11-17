<template>
    <div class="root" ref="myRoot">
        <el-form :model="roomObj" :rules="rules" ref="roomObj" label-width="110px">
            <div class="back-button-root-div" v-bind:style="{width:floatDivWidth+'px'}">
                <el-button class="back-button" size="small" type="warning" plain @click="goBack1()">返回</el-button>

                <span class="back-button-title">{{roomObj.pkName}} - {{roomObj.bdName}} - 房间新增</span>

                <el-button class="save-button" size="small" type="warning" @click="submitForm('roomObj')" plain>保存
                </el-button>
            </div>

            <div style="min-height: 68px"></div>

            <div class="common-title-div" style="margin-top: -13px;">
                <span class="common-title-font">房间信息</span>
            </div>

            <div class="emp-info-root-div">
                <el-row :gutter="10" class="common-el-row-style">
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="房间编号：" prop="roomNumber">
                                <el-input clearable v-model="roomObj.roomNumber"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="房间租金：" prop="rentPrice">
                                <el-input clearable v-model="roomObj.rentPrice">
                                    <template slot="append">{{roomObj.priceUnitName}}</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="房间面积：" prop="investArea">
                                <el-input v-model="roomObj.investArea" placeholder="含公摊">
                                    <template slot="append">㎡</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="物业费：" prop="property">
                                <el-input clearable v-model="roomObj.property">
                                    <template slot="append">元/月</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="递增周期：" prop="increasingCycle">
                                <el-input clearable v-model="roomObj.increasingCycle">
                                    <template slot="append">年</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="免租期天数：" prop="freeRentDays">
                                <el-input clearable v-model="roomObj.freeRentDays"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="付款规则：" prop="payRule">
                                <el-input clearable v-model="roomObj.payRule" placeholder="租金付几个月">
                                    <template slot="append">月</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="押金规则：" prop="depositRule">
                                <el-input clearable v-model="roomObj.depositRule" placeholder="押金付几个月"
                                         >
                                    <template slot="append">月</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <div style="clear: both;"></div>
            </div>

            <!--末尾提交按钮处-->
            <div style="height: 50px;margin-top: 20px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="submitForm('roomObj')">保存
                </el-button>
                <el-button style="float: left;" @click="resetForm('roomObj')">重置</el-button>
            </div>
        </el-form>
    </div>
</template>

<script>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElOption from "../../../../node_modules/element-ui/packages/select/src/option";

    export default{
        components: {
            ElOption,
        },
        mounted(){
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        data(){
            //校验数字0～99999999.99
            var checkNumber = (rule, value, callback) => {
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
            var checkIntOrFloat = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(value))) {
                        callback(new Error('只能输入正整数或小数'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            //校验数字只能为整数
            var checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('楼层数只能为正整数'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            return {
                floatDivWidth: "",
                roomObj: {
                    flCode: "",//楼层编号
                    roomNumber: "",//办公室编号
                    rentPrice: "",//房间租金
                    investArea: "",//房间面积
                    property: "",//物业费
                    increasingCycle: "",//递增周期(单位:年)
                    freeRentDays: "",//免租期天数
                    payRule: "",//付款规则(数字:付几个月)
                    depositRule: "",//押金规则(数字:付几个月)
                },
                rules: {
                    roomNumber: [
                        {required: true, message: '请输入房间编号', trigger: 'blur'},
                    ],
                    rentPrice: [
                        {required: true, message: '请输入房间租金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    investArea: [
                        {required: true, message: '请输入房间面积', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    property: [
                        {required: true, message: '请输入物业费', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    increasingCycle: [
                        {required: true, message: '请输入递增周期', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    freeRentDays: [
                        {required: true, message: '请输入免租期天数', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    payRule: [
                        {required: true, message: '请输入付款规则', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    depositRule: [
                        {required: true, message: '请输入押金规则', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                },
            }
        },
        created: function () {
            var vm = this;
            vm.roomObj = vm.$route.query;
        },
        methods: {
            //返回按钮
            goBack1(){
                this.$router.push({
                    name: "floorDetails",
                    query: {
                        flCode: this.roomObj.flCode,
                        pkCode: this.roomObj.pkCode,
                        pkName: this.roomObj.pkName,
                        bdCode: this.roomObj.bdCode,
                        bdName: this.roomObj.bdName,
                        thisFloor: this.roomObj.thisFloor,
                    }
                })
            },
            //重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            //提交
            submitForm(formName){
                let vm = {};
                vm = this;
                let roomObj = {};
                roomObj.entity = vm.roomObj;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        var options = {
                            method: "POST",
                            data: roomObj,
                            url: "room/create"
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '房间新增成功',
                                    type: 'success'
                                });
                                vm.goBack1();
                            } else {
                                vm.$message({
                                    showClose: true,
                                    message: response.data.message,
                                    type: 'error'
                                })
                            }
                        }).catch(function (error) {
                            vm.$alert('页面：房间新增失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                });
            },
        }
    }
</script>

<style scoped>
    /*根元素  div*/
    .root {
        min-width: 1000px;
        height: 100%;
        background: rgb(242, 242, 242);
        margin-top: -20px;
    }

    /******************************************************************/
    /*  title  公共div */
    .common-title-div {
        background-color: white;
        margin-top: 10px;
        text-align: left;
        height: 50px;
        width: 100%;
        line-height: 50px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*  title  公共文字  */
    .common-title-font {
        font-size: 17px;
        color: rgba(255, 153, 0, 1);
        margin-left: 20px;
        font-weight: bold;
    }

    .common-el-row-style {
        /*margin-top: 15px;*/
        padding-top: 20px;
    }

    /******************************************************************/
    /*返回按钮  根div*/
    .back-button-root-div {
        background-color: white;
        width: 100%;
        height: 50px;
        position: fixed;
        z-index: 200;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*返回按钮*/
    .back-button {
        float: left;
        margin-top: 10px;
        margin-left: 20px;
    }

    /*人员详情  文字*/
    .back-button-title {
        line-height: 50px;
        margin-left: 20px;
        font-size: 17px;
        color: rgba(255, 153, 0, 1);
        float: left;
        margin-top: -1px;
    }

    /*保存按钮*/
    .save-button {
        float: right;
        margin-top: 10px;
        margin-right: 20PX;
    }

    /******************************************************************/
    /*基本信息  根div*/
    .emp-info-root-div {
        background-color: white;
        width: 100%;
    }

    /*各项输入框div*/
    .grid-content {
        height: 40px;
        margin-bottom: 20px;
        /*border: solid 1px;*/
    }


</style>