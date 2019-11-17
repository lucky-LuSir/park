<template>
    <div class="root">
        <el-form ref="parkingObj" :rules="rules"  :model="parkingObj" label-width="180px">
            <!-- 悬浮框 -->
            <div class="search_box">
                <div class="search_buttons">
                    <!--<el-button style="margin-left: 20px;" size="small" type="warning" plain @click="goBack()">返回</el-button>-->
                    <span>添加停车位</span>
                    <div class="search_box_create">
                        <el-button type="warning" size="small" plain @click="createRentCus('parkingObj')">提交数据</el-button>
                    </div>
                </div>
            </div>

            <!-- 停车位新增信息-->
            <div class="contract_box">
                <div class="contract_box_up">
                    <el-row>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <span class="contract_box_up_title">新增停车位时间:</span>
                            <el-form-item  prop="startTime" >
                                <el-date-picker
                                        style="margin-top: 5px"
                                        v-model="parkingObj.startTime"
                                        align="right"
                                        type="date"
                                        placeholder="选择新增停车位时间"
                                        :picker-options="pickerOptions1">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <span class="contract_box_up_title">免费停车位个数</span>
                            <span class="ele_type"></span><span class="ele_color">{{freeParkingCount}}个</span>
                        </el-col>
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <span class="contract_box_up_title">停车位单价</span>
                            <span class="ele_type"></span><span class="ele_color">{{parkingObj.unitPrice}}元/个/月</span>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                            <el-form-item style="margin-left: 20px" label="当前停车位个数（不含免）">
                                <span class="ele_color" v-if="parkingObj.parkingSpace">{{parkingObj.parkingSpace}}个</span>
                                <span class="ele_color" v-if="!parkingObj.parkingSpace">0个</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="本次新增停车位个数" prop="addparkingSpace">
                                <el-input  style="width: 200px" v-model="parkingObj.addparkingSpace"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>
            </div>


        </el-form>

    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";

    export default {
        //接受父组件传过来的参数
        props: ['paramsObj'],

        components: {ElRow},
        created:function() {
            var vm = {};
            vm = this;

            if(vm.paramsObj) {
                vm.parkingObj.ctCode=vm.paramsObj.ctCode;
                vm.freeParkingCount=vm.paramsObj.freeParkingCount;
                vm.parkingObj.unitPrice=vm.paramsObj.parkingPrice;
                vm.parkingObj.parkingSpace=vm.paramsObj.parkingSpace;
            }

        },
        data() {
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
                freeParkingCount:'',

                parkingObj: {
                    addparkingSpace:'',
                    parkingSpace:''
                },
                //时间控件
                pickerOptions1: {
                    shortcuts: [{
                        text: '今天',
                        onClick(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '昨天',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                },//时间控件end
                rules: {
                    startTime:[
                        { type: 'date', required: true, message: '请选择时间', trigger: 'blur' }
                    ],
                    addparkingSpace:[
                        {required: true, message: '请输入停车位数量', trigger: 'blur' },
                        {validator: checkInteger},
                    ]
                },
            }
        },
        methods: {
            goBack(){
                window.history.back();
            },
            createRentCus(subForm){
                var ss=new Date( this.parkingObj.startTime);
                ss.setMonth(ss.getMonth()+1);
                this.parkingObj.endTime=ss;
                if(this.parkingObj.parkingSpace){
                    this.parkingObj.parkingSpace=parseInt(this.parkingObj.addparkingSpace) +parseInt(this.parkingObj.parkingSpace);
                }else{
                    this.parkingObj.parkingSpace=this.parkingObj.addparkingSpace;
                }
                this.parkingObj.receivablePrice=parseInt(this.parkingObj.parkingSpace)*this.parkingObj.unitPrice;
                this.parkingObj.payStatus=1;

                var vm = {};
                vm = this;
                var param = {};
                param.entity = vm.parkingObj;
                this.$refs[subForm].validate((valid) => {
                    if (valid) {
                        var options = {
                            method: 'POST',
                            data: param,
                            url:"parkReceipt/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (response) {
                                vm.$message({
                                    showClose: true,
                                    message: '新增停车位信息成功',
                                    type: 'success',
                                });
                                vm.$router.push({
                                    name:'contractList',
                                });
                            }
                        }).catch(function (error) {
                            vm.$alert('页面:添加停车费信息失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    vm.$message({
                                        type: 'info',
                                        message: `action: ${ action }`
                                    });
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
            }
        }
    }

</script>

<style scoped>

    body{
        margin-left: 200px;
        margin-right: 200px;
    }

    .root{
        min-width: 1000px;
        background-color: rgb(242, 242, 242);
    }

    .search_box{
        width: 100%;
        margin-top: 4px;
    }

    .search_buttons{
        background-color: white;
        text-align: left;
        width: 100%;
        height: 50px;
        line-height: 50px;
    }

    .search_buttons span{
        margin-left: 20px;
        color: #FF8720;
    }

    .search_box_create{
        float: right;
        margin-right: 20px;
    }

    .contract_box{
        background-color: white;
        margin-top: 5px;
        height: 150px;
        line-height: 150px;
    }

    /*电费抄表信息框*/
    .contract_box_up{
        height: 50px;
        line-height: 50px;
        text-align: left;
        border-bottom: 1px solid rgb(242,242,242);
    }

    .contract_box_up_title{
        margin-left: 20px;
        color: #FF8720;
        font-size: 18px;
        float: left;
    }

    .ele_color{
        color: #999999;
        margin-left: 20px;
    }


    .this_info_span {
        font-size: 18px;
        color: #FF8720;
    }

    .use_num{
        margin-left: 70px;
        font-size: 17px;
        float: left;
    }

    .ele_type{
        margin-left: 50px;
        width: 200px;
        float: left;
    }



</style>

