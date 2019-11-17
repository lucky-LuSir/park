<template>
    <div class="root" ref="myRoot">
        <el-form :model="roomObj" ref="roomObj" label-width="110px">
            <div class="back-button-root-div" v-bind:style="{width:floatDivWidth+'px'}">
                <el-button class="back-button" size="small" type="warning" plain @click="goBack1()">返回</el-button>

                <span class="back-button-title">{{roomObj.pkName}} - {{roomObj.bdName}} - 房间详情</span>

                <el-button class="save-button" size="small" type="warning" @click="roomEdit()" plain>房间修改</el-button>
            </div>

            <div style="min-height: 68px"></div>

            <div class="common-title-div" style="margin-top: -13px;">
                <span class="common-title-font">房间信息</span>
            </div>

            <div class="room-info-root-div">
                <el-row :gutter="10" class="common-el-row-style1">
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="房间编号：">
                                <span>{{roomObj.roomNumber}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="房间租金：">
                                <span>{{roomObj.rentPrice}}{{roomObj.priceUnitName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="房间面积：">
                                <span>{{roomObj.investArea}}㎡</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="10" class="common-el-row-style">
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="物业费：">
                                <span>{{roomObj.property}}元/月</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="递增周期：">
                                <span>{{roomObj.increasingCycle}}年</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="免租期天数：">
                                <span>{{roomObj.freeRentDays}}天</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="10" class="common-el-row-style">
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="付款规则：">
                                <span>{{roomObj.payRule}}月</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="押金规则：">
                                <span>{{roomObj.depositRule}}月</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <div style="clear: both;"></div>
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
        data(){
            return {
                roomObj: {
                    flCode: "",//楼层编号
                    roomNumber: "",//办公室编号
                    rentPrice: "",//房间租金
                    priceUnitName:"",//租金单价
                    investArea: "",//房间面积
                    property: "",//物业费
                    increasingCycle: "",//递增周期(单位:年)
                    freeRentDays: "",//免租期天数
                    payRule: "",//付款规则(数字:付几个月)
                    depositRule: "",//押金规则(数字:付几个月)
                },
                floatDivWidth:"",
            }
        },
        mounted(){
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        created: function () {
            var vm = this;
            vm.roomObj = vm.$route.query.roomObj;
            vm.roomObj.priceUnitName = vm.$route.query.priceUnitName;
            vm.roomObj.pkCode = vm.$route.query.pkCode;
            vm.roomObj.pkName = vm.$route.query.pkName;
            vm.roomObj.bdCode = vm.$route.query.bdCode;
            vm.roomObj.bdName = vm.$route.query.bdName;
            vm.roomObj.thisFloor = vm.$route.query.thisFloor;
        },
        methods: {
            //修改房间
            roomEdit(){
                this.$router.push({
                    name: 'roomEdit',
                    query: {
                        paramsObj: this.roomObj
                    }
                })
            },
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

    /*房间详情  文字*/
    .back-button-title {
        line-height: 50px;
        margin-left: 20px;
        font-size: 17px;
        color: rgba(255, 153, 0, 1);
        float: left;
        margin-top: -1px;
    }

    /*修改按钮*/
    .save-button {
        float: right;
        margin-top: 10px;
        margin-right: 20PX;
    }

    /******************************************************************/
    /*房间信息  根div*/
    .room-info-root-div {
        background-color: white;
        width: 100%;
    }

    /*房间信息行样式*/
    .common-el-row-style1 {
        padding-top: 20px;
        text-align: left;
    }

    /*房间信息行样式*/
    .common-el-row-style {
        text-align: left;
    }

    /*各项输入框div*/
    .grid-content {
        height: 40px;
        margin-bottom: 20px;
        /*border: solid 1px;*/
    }
</style>