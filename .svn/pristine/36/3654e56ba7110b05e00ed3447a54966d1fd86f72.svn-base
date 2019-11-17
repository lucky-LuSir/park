<template>
    <div>
        <el-card shadow="hover">
            <div slot="header" style="height: 30px; line-height: 30px; text-align: left;">
                <span v-if="parkObj.pkCode" class="park-data-title-font">园区去化看板：{{ parkObj.pkName }}</span>
                <span v-if="!parkObj.pkCode" class="park-data-title-font">全部园区去化看板</span>
                <el-button style="float: right;" size="small" type="primary" @click="deleteBoard">删除</el-button>
                <el-button style="float: right; margin-right: 10px;" size="small" type="primary" @click="editBoard">配置</el-button>
            </div>

            <el-row :gutter="10" style="margin-top: -10px;">
                <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" style="text-align: left;">
                    <span class="park-info-title">总承租面积：</span>{{ parkObj.acreage }}㎡
                </el-col>
                <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" style="text-align: left;">
                    <span class="park-info-title">总计租面积：</span>{{ parkObj.investArea }}㎡
                </el-col>
                <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" style="text-align: left;">
                    <span class="park-info-title">已出租面积：</span>{{ parkObj.rentInvestArea }}㎡
                </el-col>
                <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" style="text-align: left;">
                    <span class="park-info-title">空置率：</span>{{ parkObj.vacancyPercent }}%
                </el-col>
                <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" style="text-align: left;">
                    <span class="park-info-title">入驻的客户：</span>{{ contractCount }}位
                </el-col>
            </el-row>

            <!-- 柱状图 -->
            <div :id="'RE' + parkObj.pkCode" class="echart_columnar"></div>

            <el-card class="chart-root-div" v-for="(item,index) in buildingList" :key="index" shadow="hover">
                <el-row :gutter="10">
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" style="text-align: left;">
                        <div :id="item.bdCode" class="listChart"></div>
                    </el-col>
                    <el-col :xs="19" :sm="19" :md="19" :lg="19" :xl="19" style="text-align: left;">
                        <p class="building_name_style">{{ item.bdName }}栋</p>
                        <ul class="building-ul">
                            <li class="ul-li-box"></li>
                            <li>&nbsp;已出租面积：{{ (item.investArea - item.rentableArea).toFixed(2) }}㎡</li>
                            <li class="ul-li-box1"></li>
                            <li class="ul-li-font1">&nbsp;剩余面积：{{ item.rentableArea }}㎡</li>
                            <li class="ul-li-font1">空置率：{{ (item.rentableArea / item.investArea * 100).toFixed(2) }}%</li>
                        </ul>
                    </el-col>
                </el-row>
            </el-card>
        </el-card>

        <!-- 园区参数弹框 -->
        <el-dialog :title="dialogTitle" :visible.sync="isShowParkParam" style="text-align: left">
            <el-form ref="parkParam" :rules="rules" :model="parkParam" label-width="80px">
                <el-form-item label="入住园区 " prop="pkCode">
                    <el-select
                            v-model="parkParam.pkCode"
                            filterable
                            default-first-option
                            placeholder="输入园区名称或者地址">
                        <el-option
                                v-for="item in pkNames"
                                :key="item.code"
                                :label="item.name"
                                :value="item.code">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="onParkParamSubmit('parkParam')">立即配置</el-button>
                    <el-button @click="isShowParkParam= false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script scoped>
    export default {
        props: ['id', 'param'],

        created: function () {
            //解析参数param,调相应方法
            this.queryList(this.param);
            this.parkObj.pkCode = this.param;

            this.echartsDivWidth = (window.innerWidth*0.7);
        },

        updated(){
            if (this.parkObj.pkCode) {
                this.showChart(this.parkObj.pkCode);
            }

            let canArea, investArea, chartId;
            if (this.buildingList.length > 0) {
                for (let i = 0; i < this.buildingList.length; i++) {
                    canArea = this.buildingList[i].rentableArea;
                    investArea = this.buildingList[i].investArea;
                    chartId = this.buildingList[i].bdCode;

                    this.showChartPie(canArea, investArea, chartId);
                }
            }
        },

        data() {
            return {
                isShowParkParam: false,
                pkNames: [],
                dialogTitle: '',
                echartsDivWidth: '',
                parkParam: {
                    pkCode: ''
                },
                
                parkObj: {
                    pkCode: '',
                    pkName: '',
                    parkAddress: '',//园区地址
                    acreage: '',//承租面积
                    rentableArea: '',//剩余计租面积
                    rentInvestArea:'',//已租计租面积
                    rentArea: '',//已租面积
                    vacancyPercent: '',//空置率
                    rentInvestArea: ''//已租含公摊面积
                },
                
                contractCount: '',//园区合同总条数
                buildingList: [],
                
                rules: {
//                    pkCode: [
//                        {required: true, message: '园区名称不能为空', trigger: 'blur'},
//                    ],
                },
            }
        },

        methods: {
            // 设置容器高宽
            resizeWorldMapContainer(echartsWarp) {
                this.echartsDivWidth =  window.innerWidth - 325 + "px";
                echartsWarp.style.width = this.echartsDivWidth +'px';
            },

            // eCharts 柱状图 组件
            showChart(pkCode){
                //investArea总面积   rentableArea可租用面积
                let vm = this;
                let num1 = vm.parkObj.rentableArea / vm.parkObj.investArea * 100;
                let numberData = num1.toFixed(2);
                let num2 = 100 - numberData;// 计算已租面积 百分比
                let echartsWarp= document.getElementById('RE' + pkCode);

                vm.resizeWorldMapContainer(echartsWarp);

                let myChart = this.$echarts.init(echartsWarp);

                let option = {
                    grid: {
                        x: 10,
                        y: 20,
                        x2: 10,
                        y2: 20,
                        borderWidth: 1
                    },
                    tooltip: {
                        trigger: 'item',
                    },
                    color: ['#D9E5E4', '#178679'],
                    xAxis: [{
                        type: 'value',
                        show: false
                    }],
                    yAxis: [{
                        type: 'category',
                        show: false,
                        data: ['面积比例']
                    }],
                    series: [
                        {
                            name: '可出租',
                            type: 'bar',
                            stack: '总面积',
                            barWidth: 35,

                            data: [{
                                value: '65',
                                label: {
                                    normal: {
                                        color: '#000',
                                        show: true,
                                        formatter: [
                                            '{c}' + '%'
                                        ].join('\n')
                                    }
                                }
                            }]
                        },
                        {
                            name: '已出租',
                            type: 'bar',
                            stack: '总面积',
                            barWidth: 35,

                            data: [{
                                value: '32',
                                label: {
                                    normal: {
                                        color: '#D9E5E4',
                                        show: true,
                                        formatter: [
                                            '{c}' + '%'
                                        ].join('\n')
                                    }
                                }
                            }]
                        },
                    ]
                };

                if (numberData == 100) {
                    option.series[0].data[0].value = numberData;
                    option.series[1].data[0].value = '';
                } else if (numberData == 0) {
                    option.series[0].data[0].value = '';
                    option.series[1].data[0].value = num2.toFixed(2);
                } else {
                    option.series[0].data[0].value = numberData;
                    option.series[1].data[0].value = num2.toFixed(2);
                }
                myChart.setOption(option);

                //根据窗口的大小变动图表 --- 重点
                window.onresize = function(){
                    vm.resizeWorldMapContainer(echartsWarp);
                    myChart.resize();
                }
            },

            // eCharts 饼状图 组件
            showChartPie(canArea, investArea, chartId) {
                /**
                 * 可租面积：canArea
                 * 总面积：investArea
                 */
                let num1 = canArea / investArea * 100;
                let numberData = num1.toFixed(2);
                let num2 = 100 - numberData;

                let myChart = this.$echarts.init(document.getElementById(chartId));

                let option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)",
//                        position: function (p) {
//                            // 位置回调
//                            return [p[0] + 1, p[1] - 10];
//                        },
                        position: 'right'
                    },
                    series: [
                        {
                            name: '楼栋面积信息',
                            type: 'pie',
                            radius : '100%',
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
                                '#D9E5E4',
                                '#178679',
                            ]
                        }
                    ]
                };
                // 设置option相应数据
                option.series[0].data = [
                    {value: (canArea).toFixed(2), name: '空置面积'},
                    {value: (investArea - canArea).toFixed(2), name: '已租面积'}
                ];
                myChart.setOption(option);
            },

            // 接收子组件传递的 pkCode
            queryList(pkCode){
                //园区去化看板
                this.showPark(pkCode);
                this.showConCount(pkCode);
            },

            // 园区看板数据
            showPark(pkCode){
                let vm = this;
                let obj = {
                    entity: {
                        pkCode: pkCode
                    }
                };

                if (pkCode) {
                    // 根据 pkCode 查询园区
                    let options = {
                        method: 'POST',
                        data: obj,
                        url: 'park/selectByPkCode'
                    };

                    this.$ajax(
                        options
                    ).then(response => {
                        vm.buildingList = response.data.result.buildingEntities;// 获取楼栋集合
                        vm.parkObj.pkName = response.data.result.pkName;// 获取园区名称
                        vm.parkObj.parkAddress = response.data.result.parkAddress;// 获取园区地址
                        vm.parkObj.acreage = response.data.result.acreage;// 园区承租总面积
                        vm.parkObj.investArea = response.data.result.investArea;// 园区计租总面积
                        vm.parkObj.rentableArea = response.data.result.rentableArea;// 园区可租用面积
                        vm.parkObj.rentInvestArea = response.data.result.rentInvestArea;// 已租计租面积

                        let vacancyPercent = vm.parkObj.rentableArea / vm.parkObj.investArea * 100;// 计算园区空置率
                        vm.parkObj.vacancyPercent = vacancyPercent.toFixed(2);// 园区空置率保存两位小数
                    }).catch(error => {
                        vm.$alert('页面:园区去化看板查询园区信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    })
                } else {
                    // pkCode 为空的情况下查询所有园区的面积
                    let options = {
                        method: 'POST',
                        data: obj,
                        url: 'park/getAllParkSum'
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        vm.parkObj.acreage = response.data.result.acreage;// 园区招商总面积
                        vm.parkObj.rentableArea = response.data.result.rentableArea;// 园区可租用面积
                        vm.parkObj.rentInvestArea = response.data.result.rentInvestArea;// 已租计租面积
                        let vacancyPercent = vm.parkObj.rentableArea / vm.parkObj.investArea * 100;// 计算园区空置率
                        vm.parkObj.vacancyPercent = vacancyPercent.toFixed(2);// 园区空置率保存两位小数
                    }).catch(error => {
                        vm.$alert('页面:园区去化看板查询园区信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    })
                }
            },

            // 根据pkCode查询园区的入驻客户数
            showConCount(pkCode){

                if (pkCode == '') {
                    pkCode = null;
                }
                let vm = this;
                let obj = {
                    entity: {
                        pkCode: pkCode
                    }
                };

                //根据 pkCode 查询园区
                let options = {
                    method: 'POST',
                    data: obj,
                    url: 'contract/queryConByPkCode'
                };
                this.$ajax(
                    options
                ).then(response => {
                    vm.contractCount = response.data.result;
                }).catch(error => {
                    vm.$alert('页面:园区去化看板查看入驻人数失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },

            // 通过id删除board
            deleteBoard(){
                this.$confirm('此操作将永久删除该看板, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let param = {};
                    let entity = {
                        id: this.id,
                    };
                    param.entity = entity;

                    let options = {
                        method: 'POST',
                        data: param,
                        url: "board/delete",
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        vm.$message({
                            showClose: true,
                            message: '删除看板成功',
                            type: 'success'
                        });
//                        this.$router.replace('/back')
                        vm.backParent();
                    }).catch(error => {
                        vm.$alert('页面:删除园区去化看板失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

            //配置按钮
            editBoard() {
                this.dialogTitle = "园区去化看板参数配置";
                let vm = this;
                vm.isShowParkParam = true;
                //房源搜索栏
                let pkobj = {};
                let options = {
                    method: 'POST',
                    data: pkobj,
                    url: "park/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
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
//                    let aObj = {code: '', name: "全部园区"};
                    vm.pkNames = pkNameList;
//                    vm.pkNames.unshift(aObj);

                    vm.pkObj = pkObj;
                }).catch(function (error) {
                    vm.$alert('页面:园区去化看板房源搜索失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
                vm.parkParam.pkCode = vm.parkObj.pkCode;
            },

            onParkParamSubmit(subForm) {
                this.$refs[subForm].validate((valid) => {
                    if (valid) {
                        this.isShowParkParam = false;

                        let vm = this;
                        //房源搜索栏
                        let param = {};
                        let entity = {
                            id: this.id,
                            param: this.parkParam.pkCode
                        };
                        param.entity = entity;

                        let options = {
                            method: 'POST',
                            data: param,
                            url: "board/update",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$message({
                                showClose: true,
                                message: '配置看板成功',
                                type: 'success'
                            });
//                            vm.$router.replace('/back');
                            vm.backParent();
                        }).catch(function (error) {
                            vm.$alert('页面:配置园区去化看板失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                });
            },

            backParent() {
                this.$emit("backParam","1");
            },
        }
    }
</script>

<style scoped>
    .root {
        /*margin-right: 15px;*/
        /*width: 100%;*/
        /*height: 100%;*/
        /*height: 360px;*/
        /*background-color: white;*/
        border: solid 1px;
        overflow-x: hidden;
        overflow-y: hidden;
    }

    /*园区去化看板文字*/
    .park-data-title-font {
        font-weight: bold;
        color: #409EFF
    }

    .ul-park-data li {
        float: left;
        margin-left: 3%;
        color: #919191;
    }

    /*总面积 已出租 空置面积 空置率 洽谈合同的客户  文字样式*/
    .park-info-title {
        color: #6b6b6b;
        font-weight: bold;
    }

    /*eCharts根 div*/
    .chart-root-div {
        width: 300px;
        height: 150px;
        margin-right: 20px;
        margin-bottom: 20px;
        float: left;
    }

    /* eCharts */
    .listChart {
        width: 100px;
        height: 110px;
        margin-left: -10px;
        margin-top: 10px;
    }

    /*楼栋名称样式*/
    .building_name_style {
        height: 25px;
        line-height: 25px;
        text-align: center;
        margin-top: -20px;
    }

    /*饼图右边文字说明*/
    .building-ul {
        list-style: none;
        text-align: left;
        font-size: 13px;
        height: 20px;
        line-height: 20px;
        margin-left: 10px;
    }

    /*小方框*/
    .ul-li-box {
        float: left;
        width: 17px;
        height: 17px;
        background-color: #178679;
    }

    .ul-li-box1 {
        float: left;
        width: 17px;
        height: 17px;
        background-color: #D9E5E4;
        margin-top: 10px;
    }

    /*空置面积  空置率  li*/
    .ul-li-font1 {
        margin-top: 10px;
    }

    /*柱状图*/
    .echart_columnar {
        height: 35px;
        margin-left: -10px;
        margin-bottom: 20px;
    }
</style>