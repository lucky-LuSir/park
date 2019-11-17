<template>
    <div class="has-position_root" ref="myRoot">

        <!--导航栏-->
        <div class="has-position_header" v-bind:style="{width:floatDivWidth+'px'}">

            <div class="titles">
                <el-button class="navigation-button" size="small" type="warning" @click="goBack()" plain>
                    返回
                </el-button>
                <span class="navigation-span">新增跟进</span>

                <el-button class="navigation-right-button" type="warning" size="small"
                           @click="submitForm('customerFollowup')" plain
                           style="margin-top: 10px;float: right;margin-right: 20px;">保存
                </el-button>
            </div>
        </div>
        <el-form class="has-position_main" ref="customerFollowup" :rules="rules" :model="customerFollowup" label-width="100px">


            <div class="kong"></div>

            <div class="titles" style="margin-top: 2px;">
                <p class="titles-p">跟进信息</p>
            </div>
            <div class="block_box1">
                <div class="konge_row"></div>

                <!--第一行-->
                <el-row :gutter="30" class="one">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="跟进类型：" prop="followupType">
                            <el-radio-group v-model="customerFollowup.followupType">
                                <el-radio label="1">电话拜访</el-radio>
                                <el-radio label="2">客户来访</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!--第二行-->
                <el-row :gutter="30" class="one">
                    <el-col :xs="15" :sm="15" :md="15" :lg="15" :xl="15">
                        <el-form-item label="客户状态：" prop="cusStatus">
                            <el-radio-group v-model="customerFollowup.cusStatus">
                                <el-radio label="1">跟进中</el-radio>
                                <el-radio label="2">谈判中</el-radio>
                                <el-radio label="3">已入住</el-radio>
                                <el-radio label="4">不合适</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!--第三行-->
                <!--<div v-if="customerFollowup.followupType == 2">-->
                    <el-row :gutter="30">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label="来访时间：" required>
                                <el-form-item prop="followupTime">
                                    <el-date-picker style="width: 100%;"
                                                    v-model="customerFollowup.followupTime"
                                                    type="datetime"
                                                    placeholder="选择日期时间"
                                                    align="right"
                                                    :picker-options="pickerOptions">
                                    </el-date-picker>
                                </el-form-item>
                            </el-form-item>
                        </el-col>
                    </el-row>
                <!--</div>-->


                <!--园区-->
                <div v-if="customerFollowup.followupType == 2">
                    <el-row :gutter="30">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label="来访园区：" prop="pkCode">
                                <el-select style="width: 100%;"
                                           v-model="customerFollowup.pkCode"
                                           clearable
                                           filterable
                                           placeholder="请选择园区"
                                           @change="nameByCode">
                                    <!--@change="selectParkByCode">-->
                                    <el-option
                                            v-for="item in pkNames"
                                            :key="item.code"
                                            :label="item.name"
                                            :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <!--<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">-->
                        <!--<el-form-item label="意向楼层：">-->
                        <!--<el-input v-model="customerFollowup.cusIntentPark">-->
                        <!--</el-input>-->
                        <!--</el-form-item>-->
                        <!--</el-col>-->
                    </el-row>
                </div>

                <!--意向弹框-->
                <!--<div v-if="isIntentionShow">-->
                <!--<el-row :gutter="30">-->
                <!--<el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">-->
                <!--<div class="intention">{{pkName}}-->
                <!--<div class="intention-div" v-for="item in builds">-->
                <!--<el-button class="button" style="height: 30px" size="small"-->
                <!--@click="showBuildClick(item.bdCode,item.thisBuilding)">-->
                <!--{{ item.thisBuilding }}-->
                <!--</el-button>-->
                <!--</div>-->
                <!--<div v-show="isBuildShow">-->
                <!--<el-table-->
                <!--ref="multipleTable"-->
                <!--:data="parkTableData"-->
                <!--tooltip-effect="dark"-->
                <!--style="width: 100%"-->
                <!--@selection-change="handleSelectionChange">-->
                <!--<el-table-column-->
                <!--type="selection"-->
                <!--width="50">-->
                <!--</el-table-column>-->
                <!--<el-table-column-->
                <!--prop="thisFloor"-->
                <!--label="楼层"-->
                <!--width="50">-->
                <!--</el-table-column>-->
                <!--<el-table-column-->
                <!--prop="rentableArea"-->
                <!--label="可租用面积/m²"-->
                <!--width="160">-->
                <!--</el-table-column>-->
                <!--<el-table-column-->
                <!--prop="floorStatusName"-->
                <!--label="楼层状态"-->
                <!--width="100">-->
                <!--</el-table-column>-->
                <!--</el-table>-->
                <!--</div>-->
                <!--<div class="intention-button">-->
                <!--<el-button class="intention-button-el" type="primary" @click="addBuildClick">确认-->
                <!--</el-button>-->
                <!--</div>-->

                <!--</div>-->
                <!--</el-col>-->
                <!--</el-row>-->
                <!--</div>-->


                <el-row :gutter="30">
                    <div v-if="customerFollowup.cusStatus == 4">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="one">
                            <el-form-item label="跟进结果：" prop="improperReason">
                                <el-radio-group v-model="customerFollowup.improperReason">
                                    <el-radio label="1">园区不符合客户的要求</el-radio>
                                    <el-radio label="2">客户不符合园区的要求</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                    </div>
                </el-row>

                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="描述说明：" prop="followupRes">
                            <el-input type="textarea" v-model="customerFollowup.followupRes"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>
        </el-form>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {ElRow},
        created: function () {
            var vm = this;
            var obj = {};

            vm.customerFollowup.cusCode = vm.$route.query.cusCode;
            vm.markDitch = vm.$route.query.ditch;//客户来源渠道
            vm.customerFollowup.cusChannel = vm.$route.query.ditch;//客户来源渠道
            vm.customerFollowup.cusName = vm.$route.query.mainContactName;//客户主联系人姓名
            vm.customerFollowup.cusPhone = vm.$route.query.mainContactPhone;//客户主联系人电话
            vm.flag = vm.$route.query.flag;//用于判断是否从客户列表页或客户详情页进入跟进页面

            var options = {
                method: 'POST',
                data: obj,
                url: "park/list",
            };
            this.$ajax(
                options
            ).then(function (response) {
                if (!response.data.isException) {
                    let result = response.data.result;

                    let pkNameList = [];
                    let pkObj = [];

                    for (let s = 0; s < result.length; s++) {
                        let code = result[s].pkCode;
                        let name = result[s].pkName;
                        pkNameList.push(
                            {code: code, name: name}
                        );
                        pkObj[code] = name;
                    }

                    vm.pkNames = pkNameList;
                    vm.pkObj = pkObj;
                } else {
                    vm.$message({
                        showClose: true,
                        message: '搜索房源失败',
                        type: 'error'
                    });
                }
            })
        },
        mounted(){
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        data() {
            return {
                flag: "",//用于判断是否从客户列表页或客户详情页进入跟进页面
                markDitch: "",// 客户来源渠道
                floatDivWidth: "",
                pkObj: {},
                pkNames: [],
                pkCode: "",
                pkName: "",
                list: [],
                pickerOptions: {
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
                },
                customerFollowup: {
                    //实体类数据
                    pkCode: '',
                    pkName: '',
                    cusCode: '',//客户编码
                    cusName: '',// 客户联系人姓名
                    cusPhone: '',// 客户联系人电话
                    pkName: '',// 客户意向房源（来访园区）
                    cusChannel: '',// 渠道来源
                    receptionType: "",// 带看类型
                    followupType: '',// 跟进(来访)类型
                    cusStatus: '',//跟进客户状态名称
                    improperReason: '',//不合适原因
                    followupRes: '',//来访结果
                    followupTime: '',//看房时间
                    //客户意向房源列表
                    cusIntentParkList: [
                        {
                            pkCode: '',//园区编码
                            sortAddress: '',//园区地址
                            cusAppraisal: '',//客户评价
                            rentableArea: 0,//可用面积
                        }
                    ],
                    cusIntentPark: ''//意向房源
                },
                rules: {
                    receptionType: [
                        {required: true, message: '请选择带看类型', trigger: 'change'}
                    ],
                    followupType: [
                        {required: true, message: '请选择跟进类型', trigger: 'change'}
                    ],
                    cusStatus: [
                        {required: true, message: '请选择客户状态', trigger: 'change'}
                    ],
                    followupTime: [
                        {type: 'date', required: true, message: '请选择时间', trigger: 'change'}
                    ],
                    pkCode: [
                        {required: true, message: '请选择园区', trigger: 'change'}
                    ],
                    followupRes: [
                        {required: true, message: '请填写来访结果', trigger: 'blur'}
                    ],
                    improperReason: [
                        {required: true, message: '请填写不合适原因', trigger: 'blur'}
                    ]
                },

                /*其他数据*/
                park: {
                    //园区编码
                    pkCode: '',
                    pkName: '',
                    //园区地址
                    sortAddress: '',
                    //客户评价
                    cusAppraisal: '',
                    selectionList: []
                },

                isIntentionShow: false,
                isBuildShow: false,
                parkTableData: [],
                /*栋集合*/
                builds: [],
                /*列表*/
                currentBuild: '',
                currentBuilds: [],
                selectionList: [],
                multipleSelection: []
            };
        },
//        computed:{
//          改计算转移到methods的addBuildClick() 方法中
//          floors:function () {
//              let fls = "";
//              for (let i = 0; i < this.selectionList.length; i++) {
//                  for (let j = 0; j < this.selectionList[i].multipleSelection.length; j++) {
//                      fls += this.selectionList[i].multipleSelection[j].bdName;
//                      fls += "-";
//                      fls += this.selectionList[i].multipleSelection[j].thisFloor;
//                      fls += ";";
//                  }
//              }
//              return fls
//          }
//        },

        methods: {
            /*提交*/
            submitForm(formName) {
                let vm = this;
                let obj = {};

                this.$refs[formName].validate((valid) => {
                    if (valid) {

                        //第一版没有意向房源
//                        var ciList = [];
//                        for (let i = 0; i < this.selectionList.length; i++) {
//                            for (let j = 0; j < this.selectionList[i].multipleSelection.length; j++) {
//                                let item = {};
//                                item.pkCode = this.pkCode;
//                                item.bdCode = this.selectionList[i].multipleSelection[j].bdCode;
//                                item.flCode = this.selectionList[i].multipleSelection[j].flCode;
//                                item.rentableArea = this.selectionList[i].multipleSelection[j].rentableArea;
//                                item.flStatus = this.selectionList[i].multipleSelection[j].floorStatus;
//                                item.cusAppraisal = this.cusAppraisal;
//                                ciList.push(item);
//                            }
//                        }
//
//                        this.customerFollowup.cusIntentParkList = ciList;

                        //客户带看类型处理
                        if (this.markDitch == 3) {   //客户来源渠道等于3为中介
                            this.customerFollowup.receptionType = "2";    //中介带看
                        } else {
                            this.customerFollowup.receptionType = "1";    //主动获客
                        }

                        obj.entity = this.customerFollowup;

                        var options = {
                            method: 'POST',
                            data: obj,
                            url: "followup/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (response.data.isSuccess) {
                                vm.$message({
                                    showClose: true,
                                    message: '新增客户跟进成功',
                                    type: 'success',
                                });
                            } else {
                                vm.$message({
                                    showClose: true,
                                    message: '新增客户跟进失败',
                                    type: 'error'
                                });
                            }
                        });
                        //跳转到客户列表页
                        vm.$router.push({path: 'customerList'})
                    } else {
                        this.$message({
                            showClose: true,
                            message: '信息不完整',
                            type: 'error'
                        });
                        return false;
                    }
                });
            },
            // 重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            nameByCode(pkCode){
                this.customerFollowup.pkName = this.pkObj[pkCode];
            },
            // 显示点击建筑信息,该方法第一版不用
            showBuildClick (bdCode, thisBuilding) {
                this.isBuildShow = true;
                this.currentBuild = thisBuilding;
                var vm = {};
                vm = this;
                var obj = {
                    entity: {bdCode: bdCode}
                };
                var options = {
                    method: 'POST',
                    data: obj,
                    url: "floor/list",

                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response.data.isSuccess) {
                        var result = response.data.result;
                        vm.parkTableData = result;
                    } else {
                        vm.$message({
                            showClose: true,
                            message: '查询楼栋详情失败',
                            type: 'error'
                        });
                    }
                })
            },
            // 添加建筑信息,该方法第一版不用
            addBuildClick () {

                let current = this.currentBuild;
                var hasCurrentBuild = false;
                for (var i = 0; i < this.currentBuilds.length; i++) {
                    if (this.currentBuilds[i].currentBuild == this.currentBuild) {
                        //需清除原数据
                        hasCurrentBuild = true;
                    }
                }
                if (!hasCurrentBuild) {
                    this.selectionList.push(
                        {
                            multipleSelection: this.multipleSelection
                        }
                    );
                    this.currentBuilds.push({
                        currentBuild: this.currentBuild
                    })
                }
                this.isIntentionShow = false;
                this.isBuildShow = false;
                this.parkTableData = [];
                this.builds = [];

                //计算拼接意向房源信息
                let fls = "";
                for (let i = 0; i < this.selectionList.length; i++) {
                    for (let j = 0; j < this.selectionList[i].multipleSelection.length; j++) {
                        fls += this.selectionList[i].multipleSelection[j].bdName;
                        fls += "-";
                        fls += this.selectionList[i].multipleSelection[j].thisFloor;
                        fls += ";";
                    }
                }
                this.customerFollowup.cusIntentPark = fls;


            },
            // 通过code搜索,该方法第一版不用
            selectParkByCode(pkCode){
                if (pkCode == "") {
                    return
                }
                var vm = this;
                var obj = {
                    entity: {pkCode: pkCode}
                };
                var options = {
                    method: 'POST',
                    data: obj,
                    url: "building/queryByPkCode",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response.data.isSuccess) {
                        vm.builds = response.data.result;
                    } else {
                        vm.$message({
                            showClose: true,
                            message: '查询楼栋列表失败',
                            type: 'error'
                        });
                    }
                });
                this.isIntentionShow = true;
                this.isBuildShow = false;
                this.pkName = this.pkObj[pkCode];
                this.pkCode = pkCode;

            },
            //列表
            handleSelectionChange(val) {
                val.currentBuild = this.currentBuild;
                this.multipleSelection = val;
            },
            //返回按钮
            goBack(){
                if (this.flag == 1) {
                    this.$router.push({
                        name: 'customerList',
                    });
                } else if (this.flag == 2) {
                    this.$router.push({
                        name: 'customerDetail',
                        query: {
                            cusCode: this.customerFollowup.cusCode,
                        },
                    });
                }
            },
        }

    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .root {
        min-width: 1000px;
        min-height: 900px;
        background-color: rgb(242, 242, 242);
    }

    .konge_row {
        min-height: 15px;
        min-width: 1000px;
        background-color: white;
    }

    .titles {
        min-height: 50px;
        background-color: white;
        overflow: hidden;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    .titles-p {
        color: #FE980F;
        float: left;
        margin-top: -50px;
        margin-left: 20px;
        height: 50px;
    }

    .block_box1 {
        min-height: 40px;
        background-color: white;
        margin-bottom: 20px;
    }

    .navigation {
        position: fixed;
        z-index: 200;
        width: 100%;
        height: 50px;
        margin-top: -10px;
    }

    .navigation-span {
        float: left;
        height: 60px;
        margin-left: 20px;
        color: #FE980F;
        z-index: 300;
    }

    .navigation-button {
        float: left;
        margin-left: 20px;
    }

    .navigation-right-button {
        float: left;
        margin-left: 10px;
    }

    .kong {
        height: 40px;
    }

    .one {
        text-align: left;
    }

    .space {
        width: 30px;
        float: left;
    }

    .intention {
        width: 400px;
        min-height: 200px;
        max-height: inherit;
        border: 1px solid #CCCCCC;
        margin: auto;
        position: relative;
        line-height: 40px;
    }

    .intention-div {
        float: left;

    }

    .button {
        background: #CCCCCC;
    }

    .intention-button-el {
        float: right;
        position: absolute;
        right: 5px;
        bottom: 5px;
    }

    .intention-button {
        height: 50px;
    }

</style>