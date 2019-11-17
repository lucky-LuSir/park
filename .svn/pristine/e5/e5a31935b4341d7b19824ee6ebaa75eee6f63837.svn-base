<template>
    <div class="has-position_root" ref="myRoot">

        <div class="has-position_header">
            <el-button class="add_park_btn" v-if="showAddParkBtn" type="primary" size="small" @click="parkCreate()">园区新增</el-button>
            <span>区域查询：</span>
            <div style="float: left; margin-left: 10px;" v-if="cityArrList != null" v-for="(item, index) in cityArrList" :key="index">
                <el-button v-if="index==0" type="text" v-bind:class="{red_color: -1 == pkListObj.isActive}" @click="selectCity(-1)">不限</el-button>
                <el-button type="text" v-bind:class="{red_color:index == pkListObj.isActive}" @click="selectCity(index,item.cityName)">{{item.cityName}}</el-button>
            </div>

        </div>

        <div class="search_box_display" v-show="pkListObj.show">
            <transition name="el-fade-in-linear">
                <div class="transition-box">
                    <ul class="clearfix">
                        <li>
                            <div class="search_li_div" v-bind:class="{red_color:-1 == pkListObj.isActive2}" @click="selectRegion(-1)">不限</div>
                        </li>
                        <li v-if="regionArrList != null" v-for="(item,index) in regionArrList" :key="index">
                            <div class="search_li_div" v-bind:class="{red_color:index == pkListObj.isActive2}" @click="selectRegion(index,item.cityName,item.regionName)">{{item.regionName}}</div>
                        </li>
                    </ul>
                </div>
            </transition>
        </div>

        <div class="has-position-paging_main" v-bind:class="{result_box_top_add : pkListObj.show}">
            <div v-if="parkList != null" v-for="(item, index) in parkList" :key="index">
                <div class="result_box">
                    <el-tooltip class="item" effect="dark" :content="item.pkName" placement="top">
                        <div class="list_title">{{item.pkName}}</div>
                    </el-tooltip>

                    <div class="park_button">
                        <!--<el-button v-show="showCustomerListParkBtn" size="small" class="search_butt" @click="cusList(1,item.pkCode)">入驻客户(10)</el-button>-->
                        <el-tooltip class="item" effect="dark" content="统计到当天凌晨1点前客户数" placement="top">
                            <el-button v-show="showCustomerListParkBtn" size="small" class="search_butt" @click="cusList(2,item.pkCode)">
                                意向客户
                                <span style="">({{item.intentCusCount}})</span>
                            </el-button>
                        </el-tooltip>
                        <el-tooltip class="item" effect="dark" content="统计到当天凌晨1点前入驻企业数" placement="top">
                            <el-button v-show="showDealEntryParkBtn" size="small" @click="contractList(item.pkCode)" class="search_butt">
                                入驻企业
                                <span>({{item.cusCount}})</span>
                            </el-button>
                        </el-tooltip>
                    </div>
                    <div style="clear: both;"></div>
                    <!--echarts-->
                    <div style="height: 140px" @click="showDetails(item)">
                        <div :id="item.pkCode" class="listChart" :style="{width: '180px', height: '120px'}"></div>

                        <div class="park_info_box">
                            <ul class="park_info_1">
                                <li>
                                    <div class="park_info_icon"></div>
                                    <div class="park_info_icon_p">已出租面积: {{item.rentInvestArea}}㎡</div>
                                </li>
                                <li>
                                    <div class="park_info_icon2"></div>
                                    <div class="park_info_icon_p">剩余面积: {{item.rentableArea}}㎡</div>
                                </li>
                                <li>
                                    <div class="park_info_icon3">
                                        <div class="park_info_icon3_1"></div>
                                    </div>
                                    <div class="park_info_icon_p">空置率: {{item.areaProp*100 | numFilter}}%</div>
                                </li>
                            </ul>
                        </div>

                        <div class="park_info_box">
                            <ul class="park_info">
                                <li>
                                    承租面积: {{item.acreage}}㎡
                                </li>
                                <li>
                                    计租面积: {{(item.rentInvestArea+item.rentableArea).toFixed(2)}}㎡
                                </li>
                                <li>
                                    理论公摊比: {{item.areaRate}}%
                                </li>
                            </ul>
                        </div>
                        <div class="park_info_box">
                            <ul class="park_info">
                                <!--<li>-->
                                <!--费用逾期数: {{item.overdueCount}}家-->
                                <!--</li>-->
                                <!--<li>-->
                                <!--15天内应缴租金户: {{item.rentWarningCount}}家-->
                                <!--</li>-->
                                <!--<li>-->
                                <!--2个月内合同到期数: {{item.ctWarningCount}}家-->
                                <!--</li>-->
                                <li>
                                    意向客户: {{item.intentCusCount}}个
                                </li>
                                <li>
                                    入驻企业: {{item.cusCount}}个
                                </li>

                            </ul>
                        </div>
                        <div class="park_info_box">
                            <ul class="park_info">
                                <li>
                                    项目毛利率: {{item.projectGross}}%
                                </li>
                                <li>
                                    出租毛利率: {{item.investGross}}%
                                </li>

                            </ul>
                        </div>
                    </div>
                </div>
                <div style="clear: both;"></div>
            </div>

            <div class="page_down">
                <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="start" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
                </el-pagination>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        created: function() {
            let vm = this;

            // 菜单和按钮权限控制
            vm.showAddParkBtn = vm.$flagMenuStore.judgeMenu("addParkBtn");
            vm.showCustomerListParkBtn = vm.$flagMenuStore.judgeMenu("customerListParkBtn");
            vm.showDealEntryParkBtn = vm.$flagMenuStore.judgeMenu("dealEntryParkBtn");

            vm.queryAllCity(); // 查询所有城市
            vm.queryParkList(); // 查询园区列表
        },
        data() {
            return {
                showAddParkBtn: false,
                showCustomerListParkBtn: false,
                showDealEntryParkBtn: false,

                pkListObj: {
                    //区域选择使用
                    show: false, //显示区域选择框
                    isActive: -1, //判断城市选中
                    isActive2: -1, //判断区选中
                    cityName: "", //城市名
                    regionName: "" //区名
                },

                /*-----------标签start-----------*/
                start: 1,
                pageSize: 8,
                pageSizes: [6, 8, 12],
                total: 0,
                /*-----------标签end-----------*/

                parkList: [],
                cityArrList: [],
                regionArrList: [],
            }
        },
        updated() {
            var chartId = ''; //echarts图表唯一ID
            var rentArea = ''; //出租面积
            var rentableArea = ''; //空置面积

            if (this.parkList) {
                if (this.parkList.length > 0) {
                    for (var i = 0; i < this.parkList.length; i++) {
                        chartId = this.parkList[i].pkCode;
                        rentArea = this.parkList[i].rentInvestArea;
                        rentableArea = this.parkList[i].rentableArea;
                        this.showChartPie(chartId, rentArea, rentableArea);
                    }
                } else {
                    this.showChartPie(chartId, rentArea, rentableArea);
                }
            }
        },
        filters: {
            numFilter(value) {
                // 截取当前数据到小数点后两位
                let realVal = Number(value).toFixed(2);
                // num.toFixed(2)获取的是字符串
                return Number(realVal);
            }
        },
        methods: {
            /*---------------------分页start---------------------*/
            handleSizeChange(val) {
                this.pageSize = val;
                this.queryParkList();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.queryParkList();
            },
            /*---------------------分页end----------------------*/

            // 查询所有城市
            queryAllCity() {
                let vm = this;

                // 先查出园区所在城市
                let param = {};
                param.entity = {};
                let option = {
                    method: 'POST',
                    data: param,
                    url: 'parkBi/queryCity'
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.cityArrList = response.data.result;
                    }
                }).catch(function(error) {
                    vm.$alert('页面：园区关联城市查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // 查询园区列表
            queryParkList() {
                let vm = this;

                let parkParam = {};
                parkParam.start = (vm.start - 1) * vm.pageSize;
                parkParam.pageSize = vm.pageSize;
                parkParam.entity = {};
                if (vm.pkListObj.cityName) {
                    parkParam.entity.cityName = vm.pkListObj.cityName;
                }
                if (vm.pkListObj.regionName) {
                    parkParam.entity.regionName = vm.pkListObj.regionName;
                }

                let option = {
                    method: 'POST',
                    data: parkParam,
                    url: 'parkBi/query'
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.parkList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }
                }).catch(function(error) {
                    vm.$alert('页面：园区列表加载失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // 园区详情
            showDetails(val) {
                let data = {
                    menuName: '园区详情',
                    item: 'parkDetails',
                    params: val
                };
                this.$emit('backParam', data);
            },

            // 意向客户
            cusList(flag, pkCode) {
                let cusStatus = "";
                if (flag == 1) {
                    cusStatus = "3";
                }
                if (flag == 2) {
                    cusStatus = "2";
                }

                let data = {
                    menuName: '客户列表',
                    item: 'customerList',
                    params: {
                        pkCode: pkCode,
                        cusStatus: cusStatus
                    }
                };
                this.$emit('backParam', data);
            },

            // 新增园区
            parkCreate() {
                let data = {
                    menuName: '园区新增',
                    item: 'parkCreate',
                };
                this.$emit('backParam', data);
            },

            //显示echarts
            showChartPie(id, rentArea, rentableArea) {
                if (id != '') {
                    let myChart = this.$echarts.init(document.getElementById(id));

                    var option = {
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)",
                            position: 'right'
                        },
                        series: [{
                            name: '园区面积信息',
                            type: 'pie',
                            center: ['50%', '50%'],
                            data: [],
                            label: {
                                normal: {
                                    textStyle: {
                                        color: '#FFF',
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            itemStyle: {
                                normal: {
                                    borderWidth: 2,
                                    borderColor: '#ffffff',
                                },
                                emphasis: {
                                    borderWidth: 0,
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            },
                            color: [
                                '#178679',
                                '#D9E5E4'
                            ]
                        }]
                    };

                    //设置option相应数据
                    option.series[0].data = [{
                            value: rentArea,
                            name: '已租面积'
                        },
                        {
                            value: rentableArea,
                            name: '空置面积'
                        }
                    ];
                    myChart.setOption(option);
                }
            },

            //选择城市
            selectCity(index, cityName) {
                var vm = this;

                //点击区域快速查询都从第一页开始
                vm.start = 1;
                if (index != -1) {
                    //根据城市查询响应的区域
                    var param = {};
                    param.entity = {
                        "cityName": cityName
                    };
                    var option = {
                        method: 'POST',
                        data: param,
                        url: 'parkBi/queryRegion'
                    };

                    this.$ajax(
                        option
                    ).then(function(response) {
                        if (!response.data.isException) {
                            vm.regionArrList = response.data.result;
                        }
                    }).catch(function(error) {
                        vm.$alert('页面：查询城市区信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                    vm.pkListObj.show = true;
                } else {
                    cityName = '';
                    vm.pkListObj.show = false;
                }
                this.pkListObj.isActive2 = -1;
                vm.pkListObj.isActive = index;
                vm.pkListObj.cityName = cityName;
                vm.pkListObj.regionName = "";
                vm.queryParkList();

            },

            //按区查询园区
            selectRegion(index, cityName, regionName) {
                //点击区域快速查询都从第一页开始
                this.start = 1;
                if (index == -1) {
                    this.pkListObj.cityName = this.regionArrList[0].cityName;
                    this.pkListObj.regionName = "";
                    this.queryParkList();
                } else {
                    this.pkListObj.cityName = cityName;
                    this.pkListObj.regionName = regionName;
                    this.queryParkList();
                }
                this.pkListObj.isActive2 = index;
            },

            //办理入驻
            contractList(pkCode) {
                let data = {
                    menuName: '入驻企业列表',
                    item: 'contractList',
                    params: {
                        pkCode: pkCode
                    }
                };
                this.$emit('backParam', data);
            },
        },
    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .has-position-paging_main {
        height: calc(100vh - 197px);
    }

    .has-position-paging_main.result_box_top_add {
        height: calc(100vh - 247px);
    }

    /************************************************/

    /*新增园区按钮*/
    .add_park_btn {
        float: left;
        margin-left: 20px;
    }


    /*区域查询*/
    .has-position_header span {
        float: left;
        margin-left: 20px;
        color: black;
    }

    /*区域查询二级隐藏*/
    .search_box_display {
        /*margin-top: 45px;*/
        display: flex;
        /*width: 100%;*/
        background-color: white;
        border: 1px solid #409eff;
        /* position: fixed; */
        z-index: 200;
    }

    .search_li_div {
        font-size: 14px;
        color: #409eff;
    }

    .transition-box {
        height: 50px;
        min-width: 1000px;
        border-radius: 4px;
        text-align: center;
        color: #fff;
        padding: 0px 20px;
        box-sizing: border-box;
    }

    .transition-box ul {
        margin: 0;
        height: 50px;
        display: flex;
        align-items: center;
    }

    .transition-box li {
        cursor: pointer;
        float: left;
        list-style: none;
        min-height: 30px;
        line-height: 30px;
        margin-right: 20px;
    }

    .search_box_create {
        float: left;
        margin-left: 20px;
    }

    .red_color {
        color: red;
    }

    /************************************************/

    .search_butt {
        border: 1px solid #409eff;
        color: #409eff;
        margin-left: 10px;
    }

    .search_butt:hover {
        background-color: #409eff;
        color: white;
    }

    .search_butt:focus {
        background-color: #409eff;
        color: white;
    }


    .has-position_header a {
        color: #000;
    }

    .result_box {
        background-color: white;
        margin-top: 10px;
        min-height: 175px;
        text-align: left;
        padding-left: 10px;
        cursor: pointer;
    }

    .list_title {
        width: 260px;
        color: #409eff;
        height: 30px;
        float: left;
        line-height: 30px;
        padding-top: 10px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .listChart {
        margin-left: 25px;
        float: left;
        max-width: 180px;
        max-height: 150px;
    }

    .park_info {
        /*margin-left: 20px;*/
        list-style: none;
        width: 220px;
    }

    .park_info_1 {
        list-style: none;
        width: 220px;
    }

    .park_info_1 li {
        height: 35px;
        line-height: 35px;
    }

    .park_info_box {
        float: left;
        width: 200px;
    }

    .park_info li {
        height: 28px;
        line-height: 28px;
    }

    .park_info_icon {
        float: left;
        width: 14px;
        height: 14px;
        background-color: #178679;
        margin-top: 8px;
        margin-right: 2px;
    }

    .park_info_icon2 {
        float: left;
        width: 14px;
        height: 14px;
        margin-top: 8px;
        margin-right: 2px;
        background-color: #d9e5e4;
    }

    .park_info_icon3 {
        float: left;
        width: 14px;
        height: 14px;
        background-color: #178679;
        margin-top: 8px;
        margin-right: 2px;
    }

    .park_info_icon3_1 {
        height: 50%;
        background-color: #d9e5e4;
    }

    .park_info_icon_p {
        float: left;
        margin-top: -3px;
    }

    .park_button {
        max-height: 50px;
        line-height: 50px;
        float: left;
        /*margin-left: -40px;*/
    }

    /*分页*/
    .page_down {
        text-align: left;
        background-color: rgb(242, 242, 242);
        height: 40px;
        padding-top: 10px;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }
</style>