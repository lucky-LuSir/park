<template>
    <div class="homePage">
        <!-- 头部 -->
        <div class="homePage-header">
            <el-select class="select-region" size="small" v-model="regionValue" placeholder="全部大区" @change="changeRegion(regionValue)">
                <el-option v-for="item in regionsList" :key="item.deptCode" :label="item.deptName" :value="item.deptCode">
                </el-option>
            </el-select>

            <el-select class="select-city" size="small" v-model="cityValue" @change="changeCity(cityValue)" placeholder="全部城市">
                <el-option v-for="item in parkCityList" :key="item.cityCode" :label="item.cityName" :value="item.cityCode">
                </el-option>
            </el-select>

            <el-select class="select-park" size="small" v-model="pkValue" placeholder="全部园区">
                <el-option v-for="item in parkGardenList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode">
                </el-option>
            </el-select>

            <el-button class="queryAllButton" size="small" type="primary" @click="queryAllButton()" icon="el-icon-search">查询</el-button>
        </div>
        <!-- 毛利率 -->
        <div class="gross-main">
            <h2 class="gross-title">毛利率</h2>
            <div class="gross-view clearfix">
                <div class="gross-item">
                    <div class="gross-pic">
                        <img src="../assets/img/rentGross.jpg" alt="">
                    </div>
                    <div class="gross-info">
                        <div class="info-l">
                            出租毛利
                        </div>
                        <div class="info-r">
                            {{allObj.rentGross}}<span>%</span>
                        </div>
                    </div>
                </div>
                <div class="gross-item">
                    <div class="gross-pic">
                        <img src="../assets/img/projectGross.jpg" alt="">
                    </div>
                    <div class="gross-info">
                        <div class="info-l">
                            项目毛利
                        </div>
                        <div class="info-r">
                            {{allObj.projectGross}}<span>%</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 去化率与到期预警 -->
        <section class="panel-soonExpire">
            <!-- 去化率 -->
            <div class="panel-main">
                <h2 class="panel-title">去化率</h2>
                <div class="panel-view">
                    <div class="panel-item">
                        <div class="panel-pic">
                            <img src="../assets/img/investArea.jpg" alt="">
                        </div>
                        <div class="panel-info">
                            <div class="info-l">
                                计租面积
                            </div>
                            <div class="info-r">
                                {{allObj.investArea}} ㎡
                            </div>
                        </div>
                    </div>
                    <div class="panel-item">
                        <div class="panel-pic">
                            <img src="../assets/img/rentableArea.jpg" alt="">
                        </div>
                        <div class="panel-info">
                            <div class="info-l">
                                空置面积
                            </div>
                            <div class="info-r">
                                {{allObj.rentableArea}} ㎡
                            </div>
                        </div>
                    </div>
                    <div class="panel-item">
                        <div class="panel-pic">
                            <img src="../assets/img/rentedArea.jpg" alt="">
                        </div>
                        <div class="panel-info">
                            <div class="info-l">
                                已租面积
                            </div>
                            <div class="info-r">
                                {{allObj.rentedArea}} ㎡
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 6个月到期预警 -->
            <div class="soonExpire">
                <h2 class="soonExpire-title">数据趋势图</h2>
                <div class="soonExpire-view">
                    <div class="expireCustomer">
                        <div class="expireArea-title">
                            <span>近6个月营收</span>
                            <p>
                                <i></i>
                                <span>{{revenueTotal}}<em style="font-size: 12px;font-style: normal;">元</em></span>
                            </p>
                        </div>
                        <div id="expireCustomerEcharts" ref="expireCustomerEcharts"></div>
                    </div>
                    <div class="expireArea">
                        <div class="expireArea-title">
                            <span>6个月面积到期预警</span>
                            <p>
                                <i></i>
                                <span>{{expireAreaTotal}}<em style="font-size: 12px;font-style: normal;">㎡</em></span>
                            </p>
                        </div>
                        <div id="expireAreaEcharts" ref="expireAreaEcharts"></div>
                    </div>
                </div>
            </div>
        </section>
        <!-- 收费 -->
        <div class="charge-main-title clearfix">
            <div class="charge-main-item">
                <h2>本月应收租金&nbsp;</h2>
                <span style="font-size: 16px; color: red;">( 所有逾期租金：{{rentOverdueUnreceived}} ¥)</span>
            </div>
            <div class="charge-main-item">
                <h2>本月应收水费&nbsp;</h2>
                <span style="font-size: 16px; color: red">( 所有逾期水费：{{waterOverdueUnReceived}} ¥)</span>
            </div>
            <div class="charge-main-item">
                <h2>本月应收电费&nbsp;</h2>
                <span style="font-size: 16px; color: red;">( 所有逾期电费：{{electricityOverdueUnReceived}} ¥)</span>
            </div>
        </div>
        <!-- 收费echarts -->
        <div class="charge-main clearfix">
            <div class="rentCharge-main">
                <div class="charge-view">
                    <div id="rentChargeEcharts" ref="rentChargeEcharts"></div>
                </div>
                <div class="charge-con">
                    <div class="charge-item">
                        <div class="charge-item-info itemTotal_one">
                            <p>
                                <i class="itemTotal_l"></i>
                                <i class="itemTotal_r"></i>
                            </p>
                            &nbsp;{{rentReceivedTotal}}&nbsp;<span>¥</span>
                        </div>
                        <div class="charge-item-desc">
                            <p>应收金额</p>
                        </div>
                    </div>
                    <div class="charge-item">
                        <div class="charge-item-info">
                            <i style="background-color: #5083ff"></i>&nbsp;&nbsp;{{rentReceived}}&nbsp;<span>¥</span>
                        </div>
                        <div class="charge-item-desc">
                            <p>实收金额</p>
                        </div>
                    </div>
                    <div class="charge-item">
                        <div class="charge-item-info">
                            <i style="background-color: #dadada;"></i>&nbsp;&nbsp;{{rentUnReceived}}&nbsp;<span>¥</span>
                        </div>
                        <div class="charge-item-desc">
                            <p>未收金额</p>
                        </div>
                    </div>

                </div>
            </div>
            <div class="waterCharge-main">
                <div class="charge-view">
                    <div id="waterChargeEcharts" ref="waterChargeEcharts"></div>
                </div>
                <div class="charge-con">
                    <div class="charge-item">
                        <div class="charge-item-info itemTotal_two">
                            <p>
                                <i class="itemTotal_l"></i>
                                <i class="itemTotal_r"></i>
                            </p>
                            &nbsp;{{waterReceivableTotal}}&nbsp;<span>¥</span>
                        </div>
                        <div class="charge-item-desc">
                            <p>应收金额</p>
                        </div>
                    </div>
                    <div class="charge-item">
                        <div class="charge-item-info">
                            <i style="background-color: #ff7e84;"></i>&nbsp;&nbsp;{{waterReceived}}&nbsp;<span>¥</span>
                        </div>
                        <div class="charge-item-desc">
                            <p>实收金额</p>
                        </div>
                    </div>
                    <div class="charge-item">
                        <div class="charge-item-info">
                            <i style="background-color: #dadada;"></i>&nbsp;&nbsp;{{waterUnReceived}}&nbsp;<span>¥</span>
                        </div>
                        <div class="charge-item-desc">
                            <p>未收金额</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="electricCharge-main">
                <div class="charge-view">
                    <div id="electricChargeEcharts" ref="electricChargeEcharts"></div>
                </div>
                <div class="charge-con">
                    <div class="charge-item">
                        <div class="charge-item-info itemTotal_three">
                            <p>
                                <i class="itemTotal_l"></i>
                                <i class="itemTotal_r"></i>
                            </p>
                            &nbsp;{{electricityReceivedTotal}}&nbsp;<span>¥</span>
                        </div>
                        <div class="charge-item-desc">
                            <p>应收金额</p>
                        </div>
                    </div>
                    <div class="charge-item">
                        <div class="charge-item-info">
                            <i style="background-color: #feb65f;"></i>&nbsp;&nbsp;{{electricityReceived}}&nbsp;<span>¥</span>
                        </div>
                        <div class="charge-item-desc">
                            <p>实收金额</p>
                        </div>
                    </div>
                    <div class="charge-item">
                        <div class="charge-item-info">
                            <i style="background-color: #dadada;"></i>&nbsp;&nbsp;{{electricityUnReceived}}&nbsp;<span>¥</span>
                        </div>
                        <div class="charge-item-desc">
                            <p>未收金额</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                regionsList: [],
                parkCityList: [],
                parkGardenList: [],
                regionValue: "",
                cityValue: "",
                pkValue: "",
                allObj: {},
                waterReceived: "",
                waterUnReceived: "",
                waterOverdueUnReceived: "",
                electricityReceived: "",
                electricityUnReceived: "",
                electricityOverdueUnReceived: "",
                rentReceived: "",
                rentUnReceived: "",
                rentOverdueUnreceived: "",
                revenueTotal: 0, // 6个月内总营收
                expireAreaTotal: 0, // 6个月内总到期面积
                waterReceivableTotal: 0, // 水费应收金额
                electricityReceivedTotal: 0, // 电费应收金额
                rentReceivedTotal: 0 // 租金应收金额
            };
        },
        async created() {
            // 查询大区
            this.queryRegion();
            let paramObj = {
                entity: {
                    territoryType: this.regionValue,
                    pkCode: this.pkValue,
                    cityCode: this.cityValue
                },
                monthDate: new Date(),
                maturityNum: "5"
            };
            const res = await this.$ajax.post(`companySituation/select`, paramObj);
            this.allObj = res.data.result;
            this.showEcharts(res);
        },
        mounted() {
            let expireAreaEcharts = this.$echarts.init(this.$refs.expireAreaEcharts);
            let expireCustomerEcharts = this.$echarts.init(
                this.$refs.expireCustomerEcharts
            );
            let waterChargeEcharts = this.$echarts.init(this.$refs.waterChargeEcharts);
            let electricChargeEcharts = this.$echarts.init(
                this.$refs.electricChargeEcharts
            );
            let rentChargeEcharts = this.$echarts.init(this.$refs.rentChargeEcharts);
            window.addEventListener("resize", function() {
                expireAreaEcharts.resize();
                expireCustomerEcharts.resize();
                waterChargeEcharts.resize();
                electricChargeEcharts.resize();
                rentChargeEcharts.resize();
            });
        },
        methods: {
            // 查询大区
            queryRegion() {
                let deptList = window.localStorage.getItem("_usedQueryDept");
                this.regionsList = JSON.parse(deptList);
                this.regionsList.unshift({
                    deptCode: "",
                    deptName: "全部大区"
                });
            },
            // select切换大区
            changeRegion(regionValue) {
                this.cityValue = "";
                this.pkValue = "";
                let paramObj = {
                    entity: {
                        territoryType: regionValue
                    },
                    selectMonth: new Date()
                };
                this.queryCity(paramObj);
            },
            // 查询城市
            async queryCity(paramObj) {
                const res = await this.$ajax.post(`park/selectParkCity`, paramObj);
                if (res.status === 200) {
                    this.parkCityList = res.data.result;
                    this.parkCityList.unshift({
                        cityCode: "",
                        cityName: "全部城市"
                    });
                }
            },
            // 切换城市
            changeCity(cityValue) {
                this.pkValue = "";
                let paramObj = {
                    entity: {
                        cityCode: cityValue
                    },
                    selectMonth: new Date()
                };
                this.queryAllpark(paramObj);
            },
            // 查询园区
            async queryAllpark(paramObj) {
                const res = await this.$ajax.post(`park/list`, paramObj);
                if (res.status === 200) {
                    this.parkGardenList = res.data.result;
                    this.parkGardenList.unshift({
                        pkCode: "",
                        pkName: "全部园区"
                    });
                }
            },
            // 查询所有
            async queryAllButton() {
                // if (this.selectMonth == "" || this.selectMonth == null) {
                //     this.$message("请选择日期");
                //     return;
                // }
                let paramObj = {
                    entity: {
                        territoryType: this.regionValue,
                        pkCode: this.pkValue,
                        cityCode: this.cityValue
                    },
                    // monthDate: new Date(),
                    maturityNum: "5"
                };

                const res = await this.$ajax.post(`companySituation/select`, paramObj);
                this.allObj = res.data.result;
                this.showEcharts(res);
            },
            // 展示echarts
            showEcharts(res) {
                this.showExpireAreaEcharts(res);
                this.showExpireCustomerEcharts(res);
                this.showWaterChargeEcharts(res);
                this.showElectricChargeEcharts(res);
                this.showRentChargeEcharts(res);
            },
            // 展示到期面积
            showExpireAreaEcharts(res) {
                let result = res.data.result.maturityEntityList;
                let xAxisMsg = [];
                let seriesMsg = [];
                let expireAreaTotal = 0;
                result.filter((item, index, arr) => {
                    // xAxisMsg.push(item.maturityYear + "-" + item.maturityMonth);
                    xAxisMsg.push(item.maturityMonth);
                    seriesMsg.push(item.maturityArea);
                    expireAreaTotal += item.maturityArea;
                });
                this.expireAreaTotal = expireAreaTotal;
                var expireAreaEchartsOption = {
                    color: "#ed765f",
                    grid: {
                        top: "13%", // 等价于 y: '16%'
                        left: "4%",
                        right: "6%",
                        bottom: "4%",
                        containLabel: true
                    },
                    xAxis: [{
                        type: "category",
                        data: xAxisMsg,
                        axisLabel: {
                            interval: 0,
                            // rotate: 40, // 文字倾斜
                            color: function(value) {
                                return (value = "#ed765f");
                            },
                            textStyle: {
                                align: "right",
                                fontSize: 14
                            }
                        },
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        splitLine: {
                            show: false
                        }
                    }],
                    yAxis: {
                        type: "value",
                        axisLabel: {
                            show: true
                        },
                        axisTick: {
                            show: false
                        },
                        splitLine: {
                            // Y轴设置为虚线
                            show: true,
                            lineStyle: {
                                type: "dashed"
                            }
                        },
                        axisLine: {
                            show: false
                        },
                        scale: true
                    },
                    tooltip: {
                        trigger: "axis",
                        axisPointer: {
                            lineStyle: {
                                color: "#1875f0",
                                type: "dashed"
                            }
                        },
                        formatter(params) {
                            params = params[0];
                            var month = params.name.slice(5, 7);
                            var newMonth = params.name.slice(5, 7);
                            if (month.charAt(0) == "0") {
                                month = month.replace(/\b(0+)/gi, "");
                                var newMonth = month;
                            } else {
                                var newMonth = month;
                            }
                            return (
                                newMonth +
                                "月" +
                                "到期面积" +
                                " <br/> " +
                                "<i style='width: 10px; height:10px; background-color: yellow;  display: inline-block;'></i>" +
                                "&nbsp;" +
                                params.value +
                                "㎡" +
                                "&nbsp;&nbsp;&nbsp;"
                            );
                        },
                        textStyle: {
                            color: "yellow",
                            decoration: "none",
                            fontFamily: "Verdana, sans-serif"
                        }
                    },
                    series: [{
                        data: seriesMsg,
                        type: "line",
                        smooth: true,
                        itemStyle: {
                            emphasis: {
                                color: "#ed765f"
                            },
                            normal: {
                                label: {
                                    show: true
                                }
                            }
                        }
                    }]
                };
                var expireAreaEcharts = this.$refs.expireAreaEcharts;
                if (expireAreaEcharts) {
                    expireAreaEcharts = this.$echarts.init(expireAreaEcharts);
                }
                expireAreaEcharts.setOption(expireAreaEchartsOption);
            },
            // 展示6个月营收
            showExpireCustomerEcharts(res) {
                let result = res.data.result.everyMonthRevenue;
                let xAxisMsg = [];
                let seriesMsg = [];
                let revenueTotal = 0;
                result.filter((item, index, arr) => {
                    // xAxisMsg.push(item.maturityYear + "-" + item.maturityMonth);
                    xAxisMsg.push(item.maturityMonth);
                    seriesMsg.push(item.revenue);
                    revenueTotal += item.revenue;
                });
                this.revenueTotal = revenueTotal;
                var expireCustomerEchartsOption = {
                    color: "#4f85fd",
                    grid: {
                        top: "17%", // 等价于 y: '16%'
                        left: "4%",
                        right: "6%",
                        bottom: "4%",
                        containLabel: true
                    },
                    tooltip: {
                        trigger: "axis",
                        axisPointer: {
                            lineStyle: {
                                color: "#1875f0",
                                type: "dashed"
                            }
                        },
                        formatter(params) {
                            params = params[0];
                            var month = params.name.slice(5, 7);
                            var newMonth = params.name.slice(5, 7);
                            if (month.charAt(0) == "0") {
                                month = month.replace(/\b(0+)/gi, "");
                                var newMonth = month;
                            } else {
                                var newMonth = month;
                            }
                            return (
                                newMonth +
                                "月" +
                                "营收" +
                                " <br/> " +
                                "<i style='width: 10px; height:10px; background-color: yellow;  display: inline-block;'></i>" +
                                "&nbsp;" +
                                params.value +
                                "元" +
                                "&nbsp;&nbsp;&nbsp;"
                            );
                        },
                        textStyle: {
                            color: "yellow",
                            decoration: "none",
                            fontFamily: "Verdana, sans-serif"
                        }
                    },
                    xAxis: [{
                        type: "category",
                        data: xAxisMsg,
                        axisLabel: {
                            interval: 0,
                            // rotate: 40, // 文字倾斜
                            color: function(value) {
                                return (value = "#4f85fd");
                            },
                            textStyle: {
                                align: "right",
                                fontSize: 14
                            }
                        },
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        splitLine: {
                            show: false
                        }
                    }],
                    yAxis: {
                        type: "value",
                        axisLabel: {
                            show: true
                        },
                        axisTick: {
                            show: false
                        },
                        splitLine: {
                            // Y轴设置为虚线
                            show: true,
                            lineStyle: {
                                type: "dashed"
                            }
                        },
                        axisLine: {
                            show: false
                        },
                        scale: true
                    },
                    series: [{
                        smooth: true,
                        data: seriesMsg,
                        type: "line",
                        itemStyle: {
                            emphasis: {
                                color: "#4f85fd"
                            },
                            normal: {
                                label: {
                                    show: true
                                }
                            }
                        }
                    }]
                };
                var expireCustomerEcharts = this.$refs.expireCustomerEcharts;
                if (expireCustomerEcharts) {
                    expireCustomerEcharts = this.$echarts.init(expireCustomerEcharts);
                }
                expireCustomerEcharts.setOption(expireCustomerEchartsOption);
            },
            // 水收费
            showWaterChargeEcharts(res) {
                let result = res.data.result;
                let waterReceived = result.waterReceived; // 实收金额
                let waterUnReceived = result.waterUnReceived; // 未收金额
                let waterOverdueUnReceived = result.waterOverdueUnReceived; // 逾期未收金额
                this.waterReceived = waterReceived;
                this.waterUnReceived = waterUnReceived;
                this.waterOverdueUnReceived = waterOverdueUnReceived;
                this.waterReceivableTotal = (this.waterReceived + this.waterUnReceived).toFixed(2);
                let waterReceivedProportion;
                if (this.waterReceivableTotal != 0) {
                    waterReceivedProportion =
                        waterReceived / this.waterReceivableTotal * 100;
                    waterReceivedProportion = Math.round(waterReceivedProportion) + "%";
                } else {
                    waterReceivedProportion = "0%";
                }
                var waterChargeEchartsOption = {
                    tooltip: {
                        formatter: "{a} <br/> " +
                            "<i style='width: 10px; height:10px; background-color: yellow; border-radius: 50%; display: inline-block;'></i>" +
                            "&nbsp;{b}{c} 元&nbsp;&nbsp;&nbsp;",
                        textStyle: {
                            align: "left",
                            color: "yellow"
                        },
                        position: "top"
                    },
                    series: [{
                        name: "水收费",
                        type: "pie",
                        radius: ["55%", "75%"],
                        avoidLabelOverlap: false,
                        center: ["50%", "50%"],
                        hoverAnimation: false,
                        itemStyle: {
                            normal: {
                                borderWidth: 2
                            },
                            emphasis: {
                                borderWidth: 0
                            }
                        },
                        label: {
                            normal: {
                                show: true,
                                position: "center",
                                formatter: function(argument) {
                                    var html;
                                    html = "实收金额\r\n\r\n" + waterReceivedProportion;
                                    return html;
                                },
                                textStyle: {
                                    fontSize: 15,
                                    color: "#ff7e84"
                                }
                            },
                            emphasis: {
                                show: false,
                                textStyle: {
                                    fontSize: "30",
                                    fontWeight: "bold"
                                }
                            }
                        },
                        data: [{
                                value: waterReceived,
                                name: "实收金额："
                            },
                            {
                                value: waterUnReceived,
                                name: "未收金额："
                            }
                        ]
                    }],
                    color: ["#ff7e84", "#dadada"]
                };
                var waterChargeEcharts = this.$refs.waterChargeEcharts;
                if (waterChargeEcharts) {
                    waterChargeEcharts = this.$echarts.init(waterChargeEcharts);
                }
                waterChargeEcharts.setOption(waterChargeEchartsOption);
            },
            // 电收费
            showElectricChargeEcharts(res) {
                let result = res.data.result;
                let electricityReceived = result.electricityReceived; // 实收金额
                let electricityUnReceived = result.electricityUnReceived; // 未收金额
                let electricityOverdueUnReceived = result.electricityOverdueUnReceived; // 逾期未收金额
                this.electricityReceived = electricityReceived;
                this.electricityUnReceived = electricityUnReceived;
                this.electricityOverdueUnReceived = electricityOverdueUnReceived;
                this.electricityReceivedTotal =
                    (this.electricityReceived + this.electricityUnReceived).toFixed(2);

                let electricityReceivedProportion;
                if (this.electricityReceivedTotal != 0) {
                    electricityReceivedProportion =
                        electricityReceived / this.electricityReceivedTotal * 100;
                    electricityReceivedProportion =
                        Math.round(electricityReceivedProportion) + "%";
                } else {
                    electricityReceivedProportion = "0%";
                }
                var electricChargeEchartsOption = {
                    tooltip: {
                        formatter: "{a} <br/> " +
                            "<i style='width: 10px; height:10px; background-color: yellow; border-radius: 50%; display: inline-block;'></i>" +
                            "&nbsp;{b}{c} 元&nbsp;&nbsp;&nbsp;",
                        textStyle: {
                            align: "left",
                            color: "yellow"
                        },
                        position: "top"
                    },
                    series: [{
                        name: "电收费",
                        type: "pie",
                        radius: ["55%", "75%"],
                        avoidLabelOverlap: false,
                        center: ["50%", "50%"],
                        hoverAnimation: false,
                        itemStyle: {
                            normal: {
                                borderWidth: 2
                            },
                            emphasis: {
                                borderWidth: 0
                            }
                        },
                        label: {
                            normal: {
                                show: true,
                                position: "center",
                                formatter: function(argument) {
                                    var html;
                                    html = "实收金额\r\n\r\n" + electricityReceivedProportion;
                                    return html;
                                },
                                textStyle: {
                                    fontSize: 15,
                                    color: "#feb65f"
                                }
                            },
                            emphasis: {
                                show: false,
                                textStyle: {
                                    fontSize: "30",
                                    fontWeight: "bold"
                                }
                            }
                        },
                        data: [{
                                value: electricityReceived,
                                name: "实收金额："
                            },
                            {
                                value: electricityUnReceived,
                                name: "未收金额："
                            }
                        ]
                    }],
                    color: ["#feb65f", "#dadada"]
                };
                var electricChargeEcharts = this.$refs.electricChargeEcharts;
                if (electricChargeEcharts) {
                    electricChargeEcharts = this.$echarts.init(electricChargeEcharts);
                }
                electricChargeEcharts.setOption(electricChargeEchartsOption);
            },
            // 租金收费
            showRentChargeEcharts(res) {
                let result = res.data.result;
                let rentReceived = result.rentReceived; // 实收金额
                let rentUnReceived = result.rentUnReceived; // 未收金额
                let rentOverdueUnreceived = result.rentOverdueUnreceived; // 逾期未收金额
                this.rentReceived = rentReceived;
                this.rentUnReceived = rentUnReceived;
                this.rentOverdueUnreceived = rentOverdueUnreceived;
                this.rentReceivedTotal = (this.rentReceived + this.rentUnReceived).toFixed(2); // 总金额toFixed(2)
                let rentReceivedProportion;
                if (this.rentReceivedTotal != 0) {
                    rentReceivedProportion = rentReceived / this.rentReceivedTotal * 100;
                    rentReceivedProportion = Math.round(rentReceivedProportion) + "%";
                } else {
                    rentReceivedProportion = "0%";
                }
                var rentChargeEchartsOption = {
                    tooltip: {
                        formatter: "{a} <br/> " +
                            "<i style='width: 10px; height:10px; background-color: yellow; border-radius: 50%; display: inline-block;'></i>" +
                            "&nbsp;{b}{c} 元&nbsp;&nbsp;&nbsp;",
                        textStyle: {
                            align: "left",
                            color: "yellow"
                        },
                        position: "top"
                    },
                    series: [{
                        name: "租金收费",
                        type: "pie",
                        radius: ["55%", "75%"],
                        avoidLabelOverlap: false,
                        center: ["50%", "50%"],
                        hoverAnimation: false,
                        itemStyle: {
                            normal: {
                                borderWidth: 2
                            },
                            emphasis: {
                                borderWidth: 0
                            }
                        },
                        label: {
                            normal: {
                                show: true,
                                position: "center",
                                formatter: function(argument) {
                                    var html;
                                    html = "实收金额\r\n\r\n" + rentReceivedProportion;
                                    return html;
                                },
                                textStyle: {
                                    fontSize: 15,
                                    color: "#5083ff"
                                },
                            },
                            emphasis: {
                                show: false,
                                textStyle: {
                                    fontSize: "30",
                                    fontWeight: "bold"
                                }
                            }
                        },
                        data: [{
                                value: rentReceived,
                                name: "实收金额："
                            },
                            {
                                value: rentUnReceived,
                                name: "未收金额："
                            }
                        ]
                    }],
                    color: ["#5083ff", "#dadada"]
                };
                var rentChargeEcharts = this.$refs.rentChargeEcharts;
                if (rentChargeEcharts) {
                    rentChargeEcharts = this.$echarts.init(rentChargeEcharts);
                }
                rentChargeEcharts.setOption(rentChargeEchartsOption);
            }
        }
    };
</script>

<style lang='less' scoped>
    .homePage {
        height: calc(100vh - 97px);
        overflow: scroll;
        padding: 10px;
        margin: 0 !important;
        border: 0 !important;
    }

    // 头部选择框
    .homePage-header {
        z-index: 999;
        margin-top: 10px;

        .select-time {
            width: 144px;
            margin-right: 15px;
        }

        .select-region {
            width: 144px;
            margin-right: 15px;
        }

        .select-city {
            width: 144px;
            margin-right: 15px;
        }

        .select-park {
            width: 144px;
            margin-right: 15px;
        }
    }

    // 毛利率
    .gross-main {
        margin-top: 20px;

        .gross-title {
            margin-bottom: 5px;
        }

        .gross-view {
            height: 100px;
            display: flex;
        }

        .gross-item {
            width: 50%;
            float: left;
            display: flex;
            align-items: center;
            border: 1px solid #d6d6d6;
            margin-right: 10px;
            position: relative;
            // padding-left: 12.5%;
            box-sizing: border-box;
            float: left;

            &:last-child {
                margin-right: 0;
            }
        }

        .gross-item:nth-child(1) .gross-pic {
            background: #fff2f2;
        }

        .gross-item:nth-child(2) .gross-pic {
            background: #f2f6ff;
        }

        .gross-pic {
            width: 25%;
            height: 100%;
            position: absolute;
            left: 0;
            display: flex;
            justify-content: center;
            align-items: center;

            img {
                width: initial;
                height: initial;
            }
        }

        .gross-info {
            width: 100%;
            height: 100%;
            margin-left: 25%;
            display: flex;
            justify-content: space-around;

            .info-l {
                font-size: 20px;
                color: #333;
                display: flex;
                align-items: center;
            }

            .info-r {
                font-size: 42px;
                font-weight: 700;
                color: #ef2022;
                line-height: 100px;

                >span {
                    font-size: 24px;
                    vertical-align: unset;
                }
            }
        }
    }

    // 去化率与到期预警
    .panel-soonExpire {
        display: flex;
    }

    // 去化
    .panel-main {
        vertical-align: top;
        display: inline-block;
        margin-top: 20px;
        width: 33.33%;
        margin-right: 10px;

        .panel-title {
            margin-bottom: 5px;
        }

        .panel-view {
            overflow: hidden;
        }

        .panel-item {
            width: 100%;
            height: 110px;
            margin-bottom: 15px;
            display: block;
            display: flex;
            align-items: center;
            background-color: #ffffff;
            border: 1px solid #d6d6d6;
            margin-right: 10px;
            position: relative;
            box-sizing: border-box;

            &:last-child {
                margin-bottom: 0;
            }
        }

        .panel-pic {
            width: 25%;
            height: 100%;
            position: absolute;
            left: 0;
            display: flex;
            justify-content: center;
            align-items: center;

            img {
                width: initial;
                height: initial;
            }
        }

        .panel-info {
            width: 100%;
            height: 100%;
            margin-left: 25%;
            display: flex;
            justify-content: space-around;

            .info-l {
                font-size: 16px;
                color: #333;
                display: flex;
                align-items: center;
            }

            .info-r {
                font-size: 26px;
                font-weight: 700;
                color: #ef2022;
                display: flex;
                align-items: center;

                >span {
                    font-size: 16px;
                    vertical-align: unset;
                }
            }
        }
    }

    /* 到期预警*/
    .soonExpire {
        vertical-align: top;
        display: inline-block;
        width: 66.6%;
        margin-top: 20px;

        .soonExpire-title {
            margin-bottom: 5px;
        }

        .soonExpire-view {
            overflow: hidden;
        }

        .expireArea {
            width: 100%;
            border: 1px solid #b7b7b7;
            box-sizing: border-box;

            .expireArea-title>p i {
                background-color: #ed765f;
            }
        }

        .expireCustomer {
            width: 100%;
            border: 1px solid #b7b7b7;
            box-sizing: border-box;
            margin-bottom: 15px;

            .expireArea-title {
                height: 30px;
                display: flex;
                align-items: center;
            }

            .expireArea-title>p i {
                background-color: #4f85fd;
            }
        }

        #expireAreaEcharts {
            width: 100%;
            height: 145px;
        }

        #expireCustomerEcharts {
            width: 100%;
            height: 129px;
        }

        .expireArea-title {
            >span {
                /*color: #858585;*/
                margin-left: 5px;
                vertical-align: middle;
            }

            >p {
                margin-left: 20px;
                display: inline-block;

                i {
                    width: 7px;
                    height: 8px;
                    display: inline-block;
                    border-radius: 50%;
                }

                span {
                    margin-left: 6px;
                    vertical-align: middle;
                }
            }
        }
    }

    // 收费标题
    .charge-main-title {
        .charge-main-item {
            float: left;
            width: 33.33%;
            margin-top: 20px;
        }
    }

    // 收费echarts
    .charge-main {
        display: flex;
        margin-bottom: 20px;

        .waterCharge-main {
            float: left;
            width: 33.33%;
            border: 1px solid #d9d9d9;
            margin-right: 10px;
        }

        .electricCharge-main {
            float: left;
            width: 33.33%;
            border: 1px solid #d9d9d9;
        }

        .rentCharge-main {
            float: left;
            width: 33.33%;
            border: 1px solid #d9d9d9;
            margin-right: 10px;
        }

        .charge-view {
            height: 180px;
            width: 50%;
            float: left;
        }

        .charge-con {
            float: left;
            width: 50%;
            height: 180px;
            padding: 12px 0;
            box-sizing: border-box;
        }

        .charge-item {
            line-height: 22px;
            margin-bottom: 5px;
            height: 33.33%;
        }

        .charge-item-info {
            font-size: 20px;
            font-weight: 700;

            >span {
                font-size: 14px;
            }

            >i {
                display: inline-block;
                width: 10px;
                height: 10px;
                vertical-align: unset;
                border-radius: 50%;
            }
        }

        .charge-item-desc {
            >p {
                font-size: 12px;
                color: #a3a3a3;
            }
        }

        #waterChargeEcharts {
            width: 100%;
            height: 100%;
        }

        #electricChargeEcharts {
            width: 100%;
            height: 100%;
        }

        #rentChargeEcharts {
            width: 100%;
            height: 100%;
        }

        .itemTotal_one {
            >p {
                display: inline-block;
                transform: rotate(270deg);

                >i {
                    width: 16px;
                    height: 8px;
                    display: block;
                }

                .itemTotal_l {
                    background: #5083ff;
                    border-radius: 8px 8px 0 0;
                }

                .itemTotal_r {
                    background: #dadada;
                    border-radius: 0 0 8px 8px;
                }
            }

            font-size: 20px;
        }

        .itemTotal_two {
            >p {
                display: inline-block;
                transform: rotate(270deg);

                >i {
                    width: 16px;
                    height: 8px;
                    display: block;
                }

                .itemTotal_l {
                    background: #ff7e84;
                    border-radius: 8px 8px 0 0;
                }

                .itemTotal_r {
                    background: #dadada;
                    border-radius: 0 0 8px 8px;
                }
            }

            font-size: 20px;
        }

        .itemTotal_three {
            >p {
                display: inline-block;
                transform: rotate(270deg);

                >i {
                    width: 16px;
                    height: 8px;
                    display: block;
                }

                .itemTotal_l {
                    background: #feb65f;
                    border-radius: 8px 8px 0 0;
                }

                .itemTotal_r {
                    background: #dadada;
                    border-radius: 0 0 8px 8px;
                }
            }

            font-size: 20px;
        }
    }
</style>