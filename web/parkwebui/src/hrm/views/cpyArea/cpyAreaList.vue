

<template>
    <div class="not-position_root"
         v-loading="waitFlag"
         element-loading-text="拼命加载中"
         element-loading-spinner="el-icon-loading"
         element-loading-background="rgba(0, 0, 0, 0.6)"><!--根部分-->

        <el-form label-width="100px">
        <!--导航栏-->

        <div class="not-position_header">
            <span>启动地区</span>
        </div>
        <!--第1个方框 一行搜索-->
        <!--位置信息-->
        <div class="titles">
            <span class="message_title">查看启用地区:</span>
        </div>
        <div class="block_box1">
            <!--空格--><div class="konge_row"></div><!--空格(end)-->

            <el-row>
                <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                    <el-form-item label="所属省份" prop="provinceCode">
                        <el-select id="provinceNameCpy"  v-model="cpyAreaObj.provinceCode" @visible-change="handNeedProvinceCpy" @change="changeProvCpy" placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itProvinceArrCpy"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name"
                            >
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                    <el-form-item label="所属城市" prop="cityCode">
                        <el-select id="cityNameCpy"  v-model="cpyAreaObj.cityCode" @focus="getCitieListCpy(cpyAreaObj.provinceCode)" @change="changeCityCpy"   placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itCityArrCpy"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                    <el-form-item label="所属区域" prop="regionCode">
                        <el-select id="regionNameCpy" v-model="cpyAreaObj.regionCode" @focus="getAreaListCpy(cpyAreaObj.cityCode)"  @change="changeRegionCpy"   placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itAreaArrCpy"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                    <el-form-item label="所属街道" prop="streetCode">
                        <el-select  id="streetNameCpy" v-model="cpyAreaObj.streetCode" @focus="getStreetListCpy(cpyAreaObj.regionCode)"   placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itStreetArrCpy"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>

        </div>
        <!--第1个方框(end)-->

        <!--第2个方框 一行显示搜索条数-->
            <!--启用功能操作-->
            <div class="titles">
                <p class="message_title">启用功能操作:</p>
            </div>
            <div class="block_box1">
                <el-row>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所属省份" prop="provinceCode">
                            <el-select id="provinceNameBase" ref="provinceNameBase"  v-model="baseAreaObj.provinceCode" @visible-change="handNeedProvinceBase" @change="changeProvBase" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itProvinceArrBase"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name"
                                >
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属城市" prop="cityCode">
                            <el-select id="cityNameBase"  v-model="baseAreaObj.cityCode" @focus="getCitieListBase(baseAreaObj.provinceCode)" @change="changeCityBase"   placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itCityArrBase"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属区域" prop="regionCode">
                            <el-select id="regionNameBase" v-model="baseAreaObj.regionCode" @focus="getAreaListBase(baseAreaObj.cityCode)"  @change="changeRegionBase"   placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itAreaArrBase"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>

                </el-row>

            </div>
        <!--第2个方框(end)-->

        <!--第3个方框 一行树状表格-->
            <div class="msg_show_name">
                <span style="margin-right: 15px;margin-left: 28px;">您将启用:</span>
                <el-tag v-show="(baseAreaObj.provinceName)" style="width: 70px">{{baseAreaObj.provinceName}}</el-tag>
                <el-tag v-show="(baseAreaObj.cityName)" style="width: 70px">{{baseAreaObj.cityName}}</el-tag>
                <el-tag v-show="(baseAreaObj.regionName)" style="width: 70px">{{baseAreaObj.regionName}}</el-tag>
                <b style="margin-left: 30px">(及其下辖地区)</b>
                <el-button
                        @click="handConfirmEnable"

                        style="margin-left: 30px;"
                        size="mini"
                        type="primary"
                        >确认启用</el-button>
            </div>

            <div class="msg_show_code" style="clear:both">
                <span style="margin-right: 15px;margin-left: 28px;">对应编码:</span>
                <el-tag v-show="(baseAreaObj.provinceCode)" style="width: 70px">{{baseAreaObj.provinceCode}}</el-tag>
                <el-tag v-show="(baseAreaObj.cityCode)" style="width: 70px">{{baseAreaObj.cityCode}}</el-tag>
                <el-tag v-show="(baseAreaObj.regionCode)" style="width: 70px">{{baseAreaObj.regionCode}}</el-tag>
            </div>
        <!--第3个方框(end)-->

        <!--第4个方框 页码条-->
        <div class="block_box4"></div>
        <!--第4个方框(end)-->

        </el-form>
    </div><!--根部分(end)-->
</template>

<script scoped>
    export default {

        created:function () {

        },
        data() {
            return {
                waitFlag:false,
                //公司在用基本地区
                cpyAreaObj:{

                    provinceCode:"",
                    cityCode:"",
                    regionCode:"",
                    streetCode:"",

                    provinceName:"",
                    cityName:"",
                    regionName:"",
                    streetName:"",

                },
                itProvinceArrCpy:[],
                itCityArrCpy:[],
                itAreaArrCpy:[],
                itStreetArrCpy:[],

                //全国基本地区
                baseAreaObj:{

                    provinceCode:"",
                    cityCode:"",
                    regionCode:"",
                    streetCode:"",

                    provinceName:"",
                    cityName:"",
                    regionName:"",
                    streetName:"",

                },
                itProvinceArrBase:[],
                itCityArrBase:[],
                itAreaArrBase:[],
                itStreetArrBase:[],


            }
        },
        methods: {
            handConfirmEnable(){
                //判断选择框绑定的值,从而给请求体对象中的enableFlag 赋值
                var vm = {};
                vm=this;
                var enableFlag=99;//初始化为99
                var provinceObj={};
                provinceObj.areaCode = this.baseAreaObj.provinceCode;
                provinceObj.name = this.baseAreaObj.provinceName;
                var cityObj={};
                cityObj.areaCode = this.baseAreaObj.cityCode;
                cityObj.name = this.baseAreaObj.cityName;
                var regionObj={};
                regionObj.areaCode = this.baseAreaObj.regionCode;
                regionObj.name = this.baseAreaObj.regionName;

                if(regionObj.areaCode !="" && regionObj.areaCode != null){
                    enableFlag=3;
                }else if(regionObj.areaCode ==""){
                    if(cityObj.areaCode !="" && cityObj.areaCode != null){
                        enableFlag=2;
                    }else if(cityObj.areaCode ==""){
                        if(provinceObj.areaCode !="" && provinceObj.areaCode != null){
                            enableFlag=1;
                        }else if(provinceObj.areaCode ==""){
                            this.$message.error('请选择省市区');
                            this.$notify({
                                title: '提示',
                                message: h('i', { style: 'color: teal'}, '请选择省市区')
                            });
                        }else{
                            this.$message({
                                showClose: true,
                                message: '启用失败,省编码code='+regionObj.areaCode,
                                type: 'error'
                            });
                        }

                    }else{
                        this.$message({
                            showClose: true,
                            message: '启用失败,城市编码code='+regionObj.areaCode,
                            type: 'error'
                        });
                    }

                }else{
                    var enableFlag=99;
                    this.$message({
                        showClose: true,
                        message: '启用失败,县区编码code='+regionObj.areaCode,
                        type: 'error'
                    });
                }
                //enableFlag判断完毕,发送请求

                vm.waitFlag=true;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.entity = obj;
                    sendObj.enableFlag = enableFlag;
                    sendObj.provinceObj = provinceObj;
                    sendObj.cityObj = cityObj;
                    sendObj.regionObj = regionObj;

                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "baseArea/enableArea",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.waitFlag =false;
                        vm.$message.success('启用地区成功!');
                    }).catch(function (error) {
                        vm.$alert('页面：启用地区失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }


            },
            changeRegionCpy(aval){
                //清空code
                this.cpyAreaObj.streetCode="";
            },
            changeCityCpy(aval){
                //清空code
                this.cpyAreaObj.regionCode="";
                this.cpyAreaObj.streetCode="";

            },
            changeProvCpy(aval){
                //清空code
                this.cpyAreaObj.cityCode="";
                this.cpyAreaObj.regionCode="";
                this.cpyAreaObj.streetCode="";

            },
            /********/
            changeStreetBase(aval){
                //赋值街道name
                let areaObj = {};
                areaObj = this.itStreetArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.streetName= areaObj.name;
            },

            changeRegionBase(aval){
                this.baseAreaObj.streetCode="";
                //赋值县区name
                let areaObj = {};
                areaObj = this.itAreaArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.regionName= areaObj.name;
                //清空name
                this.baseAreaObj.streetName="";
            },
            changeCityBase(aval){
                this.baseAreaObj.regionCode="";
                this.baseAreaObj.streetCode="";

                //赋值市name
                let areaObj = {};
                areaObj = this.itCityArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.cityName= areaObj.name;
                //清空name
                this.baseAreaObj.regionName="";
                this.baseAreaObj.streetName="";
            },
            changeProvBase(aval){
                //清空code
                this.baseAreaObj.cityCode="";
                this.baseAreaObj.regionCode="";
                this.baseAreaObj.streetCode="";

                //赋值省name
                let areaObj = {};
                areaObj = this.itProvinceArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.provinceName= areaObj.name;
                //清空name
                this.baseAreaObj.cityName="";
                this.baseAreaObj.regionName="";
                this.baseAreaObj.streetName="";


            },

            /*省份查询*/
            handNeedProvinceCpy(val){
                var vm=this;
                var obj = { };
                obj.parentCode = 0;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url:"cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.itProvinceArrCpy = response.data.result;
                }).catch(function (error) {
                    vm.$alert('页面：查询省份信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            /*市区查询*/
            getCitieListCpy(aVal) {
                if(aVal != ""){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itCityArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$alert('页面：查询市区信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }

            },

            /*区域查询*/
            getAreaListCpy(bVal) {
                if(bVal){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = bVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itAreaArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$alert('页面：查询区域信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }
            },

            /*街道查询*/
            getStreetListCpy(aVal){
                if(aVal){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itStreetArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$alert('页面：查询街道信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }


            },


/*****全国地区方法******/

            /*省份查询*/
            handNeedProvinceBase(val){
                var vm=this;
                var obj = { };
                obj.parentCode = 0;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url:"baseArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.itProvinceArrBase = response.data.result;
                }).catch(function (error) {
                    vm.$alert('页面：查询省份信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            /*市区查询*/
            getCitieListBase(aVal) {
                if(aVal != ""){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"baseArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itCityArrBase = response.data.result;
                    }).catch(function (error) {
                        vm.$alert('页面：查询市区信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }

            },

            /*区域查询*/
            getAreaListBase(bVal) {
                if(bVal){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = bVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"baseArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itAreaArrBase = response.data.result;
                    }).catch(function (error) {
                        vm.$alert('页面：查询区域信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }
            },

            /*街道查询*/
            getStreetListBase(aVal){
                if(aVal){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"baseArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itStreetArrBase = response.data.result;
                    }).catch(function (error) {
                        vm.$alert('页面：查询街道信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }


            },


        }//end methods
    }//end export

</script>

<style scoped>
    .not-position_root {
        padding-left: 20px;
    }

    .konge_row {
        min-height: 15px;
        min-width: 1000px;
        background-color: white;
    }

    .area_title{
        margin-top: -40px;
        float: left;
        font-size: 17px;
    }
    .area_title span{
        color: #409EFF;
    }

    .block_box1 {
        min-height:40px;
        background-color: white;
        margin-bottom: 30px;
    }

    .titles{
        min-height: 50px;
        background-color: white;
        overflow: hidden;
        border-bottom: 1px solid rgb(242,242,242);
    }

    .msg_show_name{
        float: left;
        min-height: 50px;
        background-color: white;
        overflow: hidden;
        color: #409EFF;
    }

    .msg_show_code{
        float: left;
        min-height: 50px;
        background-color: white;
        margin-left: 0px;
        overflow: hidden;
        color: #409EFF;
    }

    /*信息标题*/
    .message_title{
        color: #409EFF;
        float: left;
        margin-left: 0px;
        height: 50px;
        line-height: 50px;
    }

</style>