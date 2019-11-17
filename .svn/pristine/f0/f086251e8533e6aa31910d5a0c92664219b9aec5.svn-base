<template>
    <div class="customerAnalysis clearfix">
        <div class="customerAnalysisMain">
            <el-row class="firstBanner clearfix">
                <el-col :span="12" class="getCustomerDitch">
                    <el-row class="getCustomerDitchTitle">
                        <el-col :span="12">
                            <p class="title">
                                获客渠道统计分析
                                <el-tooltip class="item" effect="dark" placement="top-start">
                                    <div style="line-height: 18px;" slot="content">横轴显示获客渠道。<br />纵轴显示获客渠道客户量。</div>
                                    <i class="el-icon-diy-wenhao" style="font-size: 15px;  cursor: pointer;"></i>
                                </el-tooltip>
                            </p>
                        </el-col>
                        <el-col :span="12">
                            <div class="desc">
                                <div class="echartsToggle">
                                    <el-tooltip class="item" effect="dark" placement="top-start">
                                        <div slot="content">切换为柱状图</div>
                                        <i @click="customerDitchToggleEcharts(1)" class="el-icon-diy-zhuzhuangtu" style="color: #4494f0;  cursor: pointer;"></i>
                                    </el-tooltip>
                                    <el-tooltip class="item" effect="dark" placement="top-start">
                                        <div slot="content">切换为饼图</div>
                                        <i @click="customerDitchToggleEcharts(2)" class="el-icon-diy-fsux_tubiao_bingtu" style="color: #4494f0;  cursor: pointer;"></i>
                                    </el-tooltip>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                    <div id="getCustomerDitchEcharts"></div>
                </el-col>
                <el-col :span="12" class="customerDemand">
                    <el-row class="customerDemandTitle">
                        <el-col :span="12">
                            <p class="title">
                                客户需求面积统计
                                <el-tooltip class="item" effect="dark" placement="top-start">
                                    <div style="line-height: 18px;" slot="content">横轴显示客户需求面积范围。<br />纵轴显示客户需求面积客户量。</div>
                                    <i class="el-icon-diy-wenhao" style="font-size: 15px;  cursor: pointer;"></i>
                                </el-tooltip>
                            </p>
                        </el-col>
                        <el-col :span="12">
                            <div class="desc">
                                <div class="echartsToggle">
                                    <el-tooltip class="item" effect="dark" placement="top-start">
                                        <div slot="content">切换为柱状图</div>
                                        <i @click="customerDemandToggleEcharts(1)" class="el-icon-diy-zhuzhuangtu" style="color: #4494f0;  cursor: pointer;"></i>
                                    </el-tooltip>
                                    <el-tooltip class="item" effect="dark" placement="top-start">
                                        <div slot="content">切换为饼图</div>
                                        <i @click="customerDemandToggleEcharts(2)" class="el-icon-diy-fsux_tubiao_bingtu" style="color: #4494f0;  cursor: pointer;"></i>
                                    </el-tooltip>
                                </div>
                                <!-- <span style="display: inline-block;width: 6px;height: 6px;background: rgb(68, 148, 240);border-radius: 6px;margin-bottom: 2px;"></span>
                                <span style="margin-left: 5px; color: rgb(114, 128, 161); font-size: 12px;">Y客户需求面积客户量(人)</span>
                                <span style="display: inline-block; width: 6px; height: 6px; background: rgb(92, 173, 160); border-radius: 6px; margin: 0px 0px 2px 25px;"></span>
                                <span style="margin-left: 5px; color: rgb(114, 128, 161); font-size: 12px;">X客户需求面积范围</span> -->
                            </div>
                        </el-col>
                    </el-row>
                    <div id="customerDemandEcharts"></div>
                </el-col>
            </el-row>
            <el-row class="secondBanner">
                <el-col :span="24" class="analysisByTrade">
                    <el-row class="analysisByTradeHead">
                        <el-col :span="12">
                            <p class="title">
                                行业获取客户量分析
                                <el-tooltip class="item" effect="dark" placement="top-start">
                                    <div style="line-height: 18px;" slot="content">横轴显示行业渠道。<br />纵轴每个行业渠道获客。</div>
                                    <i class="el-icon-diy-wenhao" style="font-size: 15px; cursor: pointer;"></i>
                                </el-tooltip>
                            </p>
                        </el-col>
                        <el-col :span="12">
                            <div class="desc">
                                <span style="display: inline-block;width: 6px;height: 6px;background: rgb(68, 148, 240);border-radius: 6px;margin-bottom: 2px;  cursor: pointer;"></span>
                                <span style="margin-left: 5px; color: rgb(114, 128, 161); font-size: 12px;">每个行业渠道获客量(个)</span>
                            </div>
                            <el-select size=mini @change="getIndustry()" v-model="tradeUserStatusValue" placeholder="跟进状态">
                                <el-option v-for="(item, index) in tradeUserStatusOptions" :key="index" :label="item.cusStatusName" :value="item.cusStatus">
                                </el-option>
                            </el-select>
                        </el-col>

                    </el-row>
                    <div class="analysisByTradeBox">
                        <div class="analysisByTradeEcharts" ref="analysisByTradeEcharts" id="analysisByTradeEcharts"></div>
                    </div>
                </el-col>
            </el-row>
            <el-row class="threeBanner">
                <el-col :span="24">
                    <el-row class="parkAreaDemandHead">
                        <el-col :span="12">
                            <p class="title">
                                园区客户数量分析
                                <el-tooltip class="item" effect="dark" placement="top-start">
                                    <div style="line-height: 18px;" slot="content">横轴显示园区分布。<br />纵轴显示每个园区客户数量。</div>
                                    <i class="el-icon-diy-wenhao" style="font-size: 15px;  cursor: pointer;"></i>
                                </el-tooltip>
                            </p>
                        </el-col>
                        <el-col :span="12">
                            <div class="desc">
                                <span style="display: inline-block;width: 6px;height: 6px;background: rgb(68, 148, 240);border-radius: 6px;margin-bottom: 2px;  cursor: pointer;"></span>
                                <span style="margin-left: 5px; color: rgb(114, 128, 161); font-size: 12px;">每个园区客户数量(个)</span>
                            </div>
                            <el-select size="small" @change="getParkMsg()" v-model="parkUserStatusValue" placeholder="跟进状态">
                                <el-option v-for="(item, index) in tradeUserStatusOptions" :key="index" :label="item.cusStatusName" :value="item.cusStatus">
                                </el-option>
                            </el-select>
                        </el-col>
                    </el-row>
                    <div class="parkAreaDemandBox">
                        <div class="parkAreaDemandEcharts" ref="parkAreaDemandEcharts" id="parkAreaDemandEcharts"></div>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                tradeUserStatusOptions: [],
                parkUserStatusOptions: [],
                tradeUserStatusValue: "",
                parkUserStatusValue: "",
                getCustomerDitchCode: 2, // 1是柱状图, 2是饼图
                getCustomerDemandCode: 2 // 1是柱状图, 2是饼图
            };
        },
        mounted() {
            this.$nextTick(() => {
                this.$forceUpdate();
                this.getCustomer(); // 获客渠道统计axios获取数据
                this.customerDemand(); // 客户需求面积axios获取数据
                this.getUserStatus(); // 获取用户状态axios获取数据
                this.getParkMsg(); // 园区分析客户数量axios获取数据
                this.getIndustry(); // 行业获取客户数量axios获取数据
            });

        },
        updated() {
            var customerAnalysisMain = document.querySelector(".customerAnalysisMain");
            if (customerAnalysisMain) {
                document.querySelector("#getCustomerDitchEcharts").style.width =
                    (window.innerWidth - 320) / 2 + "px";
                document.querySelector("#customerDemandEcharts").style.width =
                    (window.innerWidth - 320) / 2 + "px";
                document.querySelector("#parkAreaDemandEcharts").style.width =
                    window.innerWidth - 320 + "px";
                document.querySelector("#analysisByTradeEcharts").style.width =
                    window.innerWidth - 320 + "px";
            }

        },
        methods: {
            // 获客渠道统计切换echarts图
            customerDitchToggleEcharts(getCustomerDitchCode) {
                this.getCustomerDitchCode = getCustomerDitchCode;
                this.getCustomer();
            },
            // 客户需求面积统计-切换echarts图
            customerDemandToggleEcharts(getCustomerDemandCode) {
                this.getCustomerDemandCode = getCustomerDemandCode;
                this.customerDemand();
            },
            // 获客渠道统计-分析图
            showDitchEcharts(result = [], getCustomerDitchCode = 1) {
                let getCustomerDitchEcharts = document.querySelector(
                    "#getCustomerDitchEcharts"
                );
                getCustomerDitchEcharts = this.$echarts.init(
                    getCustomerDitchEcharts
                );
                let getCustomerDitchOptions;
                if (getCustomerDitchCode === 1) {
                    getCustomerDitchOptions = {
                        width: "97%",
                        color: "#02d5ff",
                        xAxis: [{
                            type: "category",
                            data: [
                                "58同城",
                                "户外横幅",
                                "小广告/传单",
                                "微信朋友圈",
                                "园区建筑广告",
                                "拨打电话",
                                "中介",
                                "兼职/转介绍",
                                "其他",
                                "库房无忧"
                            ],
                            axisLabel: {
                                interval: 0,
                                rotate: 40, // 文字倾斜
                                color: function(value) {
                                    return (value = "#02c2ff");
                                },
                                textStyle: {
                                    align: "right",
                                    fontSize: 14
                                }
                            },
                            axisLine: {
                                symbol: ["none", "arrow"],
                                symbolSize: [5, 7],
                                lineStyle: {
                                    color: "#979797"
                                }
                            }
                        }],
                        grid: {
                            left: 0,
                            right: 0,
                            top: 30,
                            bottom: 20,
                            containLabel: true,
                            tooltip: {
                                //---鼠标焦点放在图形上，产生的提示框
                                show: true,
                                trigger: "item", //---触发类型
                                textStyle: {
                                    color: "yellow"
                                }
                            }
                        },
                        tooltip: {
                            formatter: "{a} <br/> " +
                                "<i style='width: 10px; height:10px; background-color: yellow; border-radius: 50%; display: inline-block;'></i>" +
                                "&nbsp;{b}&nbsp;:&nbsp;&nbsp;{c}个&nbsp;&nbsp;&nbsp;",
                            textStyle: {
                                align: "left"
                            }
                        },
                        yAxis: [{
                            type: "value",
                            axisLabel: {
                                show: true
                            },
                            axisTick: {
                                show: false
                            },
                            splitLine: {
                                show: true
                            },
                            axisLine: {
                                symbol: ["none", "arrow"],
                                symbolSize: [5, 7],
                                lineStyle: {
                                    color: "#979797"
                                }
                            }
                        }],
                        series: [{
                            name: "获客渠道统计",
                            data: [
                                result.app58,
                                result.streamer,
                                result.advertising,
                                result.friendsShare,
                                result.buildingAdvertising,
                                result.callPhone,
                                result.middleman,
                                result.pluralistic,
                                result.other,
                                result.kfwy
                            ],
                            type: "bar",
                            barCategoryGap: "60%", //调整柱子宽度
                            itemStyle: {
                                normal: {
                                    barBorderRadius: [10, 10, 0, 0],
                                    color: new this.$echarts.graphic.LinearGradient(
                                        0,
                                        0,
                                        0,
                                        1,
                                        [{
                                                offset: 0,
                                                color: "#55c8eb"
                                            },
                                            {
                                                offset: 0.5,
                                                color: "#4387fd"
                                            },
                                            {
                                                offset: 1,
                                                color: "#5866e0"
                                            }
                                        ]
                                    ),
                                    opacity: 0.85
                                },
                                emphasis: {
                                    color: "#ff8d1f"
                                }
                            }
                        }]
                    };
                } else {
                    getCustomerDitchOptions = {
                        tooltip: {
                            formatter: "{a} <br/> " +
                                "<i style='width: 10px; height:10px; background-color: yellow; border-radius: 50%; display: inline-block;'></i>" +
                                "&nbsp;{b}{c}个&nbsp;&nbsp;&nbsp;",
                            textStyle: {
                                align: "left",
                                color: "yellow"
                            }
                        },
                        legend: {
                            formatter: function(name) {
                                if (name === "58同城：") {
                                    return name + " " + result.app58 + "个";
                                }
                                if (name === "户外横幅：") {
                                    return name + " " + result.streamer + "个";
                                }
                                if (name === "小广告/传单：") {
                                    return name + " " + result.advertising + "个";
                                }
                                if (name === "微信朋友圈：") {
                                    return name + " " + result.friendsShare + "个";
                                }
                                if (name === "园区建筑广告：") {
                                    return (
                                        name +
                                        " " +
                                        result.buildingAdvertising +
                                        "个"
                                    );
                                }
                                if (name === "拨打电话：") {
                                    return name + " " + result.callPhone + "个";
                                }
                                if (name === "中介：") {
                                    return name + " " + result.middleman + "个";
                                }
                                if (name === "兼职/转介绍：") {
                                    return name + " " + result.pluralistic + "个";
                                }
                                if (name === "其他：") {
                                    return name + " " + result.other + "个";
                                }
                                if (name === "库房无忧：") {
                                    return name + " " + result.kfwy + "个";
                                }
                            },
                            orient: "vertical",
                            x: "58%",
                            y: "18%",
                            itemWidth: 17,
                            itemHeight: 10,
                            icon: "pin",
                            textStyle: {
                                color: "#666",
                                fontSize: 13
                            },
                            data: [{
                                    name: "58同城："
                                },
                                {
                                    name: "户外横幅："
                                },
                                {
                                    name: "小广告/传单："
                                },
                                {
                                    name: "微信朋友圈："
                                },
                                {
                                    name: "园区建筑广告："
                                },
                                {
                                    name: "拨打电话："
                                },
                                {
                                    name: "中介："
                                },
                                {
                                    name: "兼职/转介绍："
                                },
                                {
                                    name: "其他："
                                },
                                {
                                    name: "库房无忧："
                                }
                            ]
                        },
                        series: [{
                            name: "获客渠道统计",
                            type: "pie",
                            radius: ["30%", "45%"],
                            center: ["30%", "45%"],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: false,
                                    position: "center"
                                },
                                emphasis: {
                                    show: false,
                                    textStyle: {
                                        fontSize: "30",
                                        fontWeight: "bold"
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data: [{
                                    value: result.app58,
                                    name: "58同城："
                                },
                                {
                                    value: result.streamer,
                                    name: "户外横幅："
                                },
                                {
                                    value: result.advertising,
                                    name: "小广告/传单："
                                },
                                {
                                    value: result.friendsShare,
                                    name: "微信朋友圈："
                                },
                                {
                                    value: result.buildingAdvertising,
                                    name: "园区建筑广告："
                                },
                                {
                                    value: result.callPhone,
                                    name: "拨打电话："
                                },
                                {
                                    value: result.middleman,
                                    name: "中介："
                                },
                                {
                                    value: result.pluralistic,
                                    name: "兼职/转介绍："
                                },
                                {
                                    value: result.other,
                                    name: "其他："
                                },
                                {
                                    value: result.kfwy,
                                    name: "库房无忧："
                                }
                            ]
                        }],
                        color: [
                            "#646464",
                            "#ff4545",
                            "#ffd916",
                            "#ff8f2f",
                            "#5ddcff",
                            "#b6f91e",
                            "#4387fd",
                            "#10fbbe",
                            "#5866e0",
                            "#dae3e9"
                        ]
                    };
                }
                getCustomerDitchEcharts.setOption(getCustomerDitchOptions, true);

                window.addEventListener("resize", function() {
                    var customerAnalysisMain = document.querySelector(".customerAnalysisMain");
                    if (customerAnalysisMain) {
                        document.querySelector("#getCustomerDitchEcharts").style.width =
                            (window.innerWidth - 320) / 2 + "px";
                        getCustomerDitchEcharts.resize();
                    }
                })
            },
            // 客户需求面积-统计图
            showDemandEcharts(result = [], getCustomerDemandCode = 1) {
                let customerDemandEcharts = document.querySelector(
                    "#customerDemandEcharts"
                );
                customerDemandEcharts = this.$echarts.init(customerDemandEcharts);
                let customerDemandOptions;
                if (getCustomerDemandCode === 1) {
                    customerDemandOptions = {
                        width: "97%",
                        color: ["#02c2ff"],
                        xAxis: [{
                            type: "category",
                            data: [
                                "0~200",
                                "200~400",
                                "400~600",
                                "600~800",
                                "800~1000",
                                "1000~1200",
                                "1200~1500",
                                "1500~1700",
                                "1700~2000",
                                "2000以上"
                            ],
                            axisLabel: {
                                interval: 0,
                                rotate: 40, // 文字倾斜
                                color: function(value) {
                                    return (value = "#02c2ff");
                                },
                                textStyle: {
                                    align: "right",
                                    fontSize: 14
                                }
                            },
                            axisLine: {
                                symbol: ["none", "arrow"],
                                symbolSize: [5, 7],
                                lineStyle: {
                                    color: "#979797"
                                }
                            }
                        }],
                        tooltip: {
                            formatter: "{a} <br/> " +
                                "<i style='width: 10px; height:10px; background-color: yellow; border-radius: 50%; display: inline-block;'></i>" +
                                "&nbsp;{b}&nbsp;:&nbsp;&nbsp;{c}个&nbsp;&nbsp;&nbsp;",
                            textStyle: {
                                align: "left"
                            }
                        },
                        grid: {
                            left: 0,
                            right: 0,
                            top: 30,
                            bottom: 20,
                            containLabel: true,
                            tooltip: {
                                //---鼠标焦点放在图形上，产生的提示框
                                show: true,
                                trigger: "item", //---触发类型
                                textStyle: {
                                    color: "yellow"
                                }
                            }
                        },
                        yAxis: [{
                            type: "value",
                            axisLabel: {
                                show: true
                            },
                            axisTick: {
                                show: false
                            },
                            splitLine: {
                                show: true
                            },
                            axisLine: {
                                symbol: ["none", "arrow"],
                                symbolSize: [5, 7],
                                lineStyle: {
                                    color: "#979797"
                                }
                            }
                        }],
                        series: [{
                            name: "客户需求面积统计",
                            data: [
                                result.result1,
                                result.result2,
                                result.result3,
                                result.result4,
                                result.result5,
                                result.result6,
                                result.result7,
                                result.result8,
                                result.result9,
                                result.result10
                            ],
                            type: "bar",
                            barCategoryGap: "70%", //调整柱子宽度
                            itemStyle: {
                                normal: {
                                    barBorderRadius: [10, 10, 0, 0],
                                    color: new this.$echarts.graphic.LinearGradient(
                                        0,
                                        0,
                                        0,
                                        1,
                                        [{
                                                offset: 0,
                                                color: "#55c8eb"
                                            },
                                            {
                                                offset: 0.5,
                                                color: "#4387fd"
                                            },
                                            {
                                                offset: 1,
                                                color: "#5866e0"
                                            }
                                        ]
                                    ),
                                    opacity: 0.85
                                },
                                emphasis: {
                                    color: "#ff8d1f"
                                }
                            }
                        }]
                    };
                } else {
                    customerDemandOptions = {
                        tooltip: {
                            formatter: "{a} <br/> " +
                                "<i style='width: 10px; height:10px; background-color: yellow; border-radius: 50%; display: inline-block;'></i>" +
                                "&nbsp;{b}{c}个&nbsp;&nbsp;&nbsp;",
                            textStyle: {
                                align: "left",
                                color: "yellow"
                            }
                        },
                        legend: {
                            formatter: function(name) {
                                if (name === "0~200：") {
                                    return name + " " + result.result1 + "个";
                                }
                                if (name === "200~400：") {
                                    return name + " " + result.result2 + "个";
                                }
                                if (name === "400~600：") {
                                    return name + " " + result.result3 + "个";
                                }
                                if (name === "600~800：") {
                                    return name + " " + result.result4 + "个";
                                }
                                if (name === "800~1000：") {
                                    return name + " " + result.result5 + "个";
                                }
                                if (name === "1000~1200：") {
                                    return name + " " + result.result6 + "个";
                                }
                                if (name === "1200~1500：") {
                                    return name + " " + result.result7 + "个";
                                }
                                if (name === "1500~1700：") {
                                    return name + " " + result.result8 + "个";
                                }
                                if (name === "1700~2000：") {
                                    return name + " " + result.result9 + "个";
                                }
                                if (name === "2000以上：") {
                                    return name + " " + result.result10 + "个";
                                }
                            },
                            orient: "vertical",
                            x: "58%",
                            y: "18%",
                            itemWidth: 17,
                            itemHeight: 10,
                            icon: "pin",
                            textStyle: {
                                color: "#666",
                                fontSize: 13
                            },
                            data: [{
                                    name: "0~200："
                                },
                                {
                                    name: "200~400："
                                },
                                {
                                    name: "400~600："
                                },
                                {
                                    name: "600~800："
                                },
                                {
                                    name: "800~1000："
                                },
                                {
                                    name: "1000~1200："
                                },
                                {
                                    name: "1200~1500："
                                },
                                {
                                    name: "1500~1700："
                                },
                                {
                                    name: "1700~2000："
                                },
                                {
                                    name: "2000以上："
                                }
                            ]
                        },
                        series: [{
                            name: "客户需求面积统计",
                            type: "pie",
                            radius: ["30%", "45%"],
                            center: ["30%", "45%"],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: false,
                                    position: "center"
                                },
                                emphasis: {
                                    show: false,
                                    textStyle: {
                                        fontSize: "30",
                                        fontWeight: "bold"
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data: [{
                                    value: result.result1,
                                    name: "0~200："
                                },
                                {
                                    value: result.result2,
                                    name: "200~400："
                                },
                                {
                                    value: result.result3,
                                    name: "400~600："
                                },
                                {
                                    value: result.result4,
                                    name: "600~800："
                                },
                                {
                                    value: result.result5,
                                    name: "800~1000："
                                },
                                {
                                    value: result.result6,
                                    name: "1000~1200："
                                },
                                {
                                    value: result.result7,
                                    name: "1200~1500："
                                },
                                {
                                    value: result.result8,
                                    name: "1500~1700："
                                },
                                {
                                    value: result.result9,
                                    name: "1700~2000："
                                },
                                {
                                    value: result.result10,
                                    name: "2000以上："
                                }
                            ]
                        }],
                        color: [
                            "#646464",
                            "#ff4545",
                            "#ffd916",
                            "#ff8f2f",
                            "#5ddcff",
                            "#b6f91e",
                            "#4387fd",
                            "#10fbbe",
                            "#5866e0",
                            "#dae3e9"
                        ]
                    };
                }
                customerDemandEcharts.setOption(customerDemandOptions, true);
                window.addEventListener("resize", function() {
                    var customerAnalysisMain = document.querySelector(".customerAnalysisMain");
                    if (customerAnalysisMain) {
                        document.querySelector("#customerDemandEcharts").style.width =
                            (window.innerWidth - 320) / 2 + "px";
                        customerDemandEcharts.resize();
                    }

                })
            },
            // 行业获取客户量-柱状图
            showAnalysisByTradeEcharts(xAxisMsg = [], seriesMsg = []) {
                let analysisByTradeEchartsOptions = {
                    width: "100%",
                    color: ["#02c2ff"],
                    xAxis: [{
                        type: "category",
                        data: xAxisMsg,
                        axisLabel: {
                            interval: 0,
                            // rotate: 40, // 文字倾斜
                            formatter: function(value) {
                                var maxLength;
                                if (window.innerWidth > 1480) {
                                    maxLength = 4; //每项显示文字个数
                                }
                                if (
                                    window.innerWidth < 1480 &&
                                    window.innerWidth > 1200
                                ) {
                                    maxLength = 3; //每项显示文字个数
                                }
                                if (window.innerWidth < 1200) {
                                    maxLength = 2; //每项显示文字个数
                                }
                                var ret = ""; //拼接加\n返回的类目项
                                var valLength = value.length; //X轴类目项的文字个数

                                var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数

                                if (rowN > 1) {
                                    //如果类目项的文字大于3,
                                    for (var i = 0; i < rowN; i++) {
                                        var temp = ""; //每次截取的字符串
                                        var start = i * maxLength; //开始截取的位置
                                        var end = start + maxLength; //结束截取的位置
                                        //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                        temp =
                                            value.substring(start, end) + "\n";
                                        ret += temp; //凭借最终的字符串
                                    }
                                    return ret;
                                } else {
                                    return value;
                                }
                            },
                            textStyle: {
                                align: "center"
                            },
                            color: function(value) {
                                return (value = "#02c2ff");
                            }
                        },
                        axisLine: {
                            symbol: ["none", "arrow"],
                            symbolSize: [5, 7],
                            lineStyle: {
                                color: "#979797"
                            }
                        }
                    }],
                    tooltip: {
                        formatter: "{a} <br/> " +
                            "<i style='width: 10px; height:10px; background-color: yellow; border-radius: 50%; display: inline-block;'></i>" +
                            "&nbsp;{b}&nbsp;:&nbsp;&nbsp;{c}个&nbsp;&nbsp;&nbsp;",
                        textStyle: {
                            align: "left"
                        }
                    },
                    grid: {
                        left: 0,
                        right: 0,
                        top: 30,
                        bottom: 20,
                        containLabel: true,
                        tooltip: {
                            //---鼠标焦点放在图形上，产生的提示框
                            show: true,
                            trigger: "item", //---触发类型
                            textStyle: {
                                color: "yellow"
                            }
                        }
                    },
                    yAxis: [{
                        type: "value",
                        axisLabel: {
                            show: true
                        },
                        axisTick: {
                            show: false
                        },
                        splitLine: {
                            show: true
                        },
                        axisLine: {
                            symbol: ["none", "arrow"],
                            symbolSize: [5, 7],
                            lineStyle: {
                                color: "#979797"
                            }
                        }
                    }],
                    series: [{
                        name: "行业获取客户量",
                        data: seriesMsg,
                        type: "bar",
                        barCategoryGap: "60%", //调整柱子宽度
                        itemStyle: {
                            normal: {
                                barBorderRadius: [10, 10, 0, 0],
                                color: new this.$echarts.graphic.LinearGradient(
                                    0,
                                    0,
                                    0,
                                    1,
                                    [{
                                            offset: 0,
                                            color: "#55c8eb"
                                        },
                                        {
                                            offset: 0.5,
                                            color: "#4387fd"
                                        },
                                        {
                                            offset: 1,
                                            color: "#5866e0"
                                        }
                                    ]
                                ),
                                opacity: 0.85
                            },
                            emphasis: {
                                color: "#ff8d1f"
                            }
                        }
                    }]
                };
                let analysisByTradeEcharts = this.$refs.analysisByTradeEcharts;
                analysisByTradeEcharts = this.$echarts.init(analysisByTradeEcharts);
                analysisByTradeEcharts.setOption(analysisByTradeEchartsOptions);
                window.addEventListener("resize", function() {
                    var customerAnalysisMain = document.querySelector(".customerAnalysisMain");
                    if (customerAnalysisMain) {
                        document.querySelector("#analysisByTradeEcharts").style.width =
                            window.innerWidth - 320 + "px";
                        analysisByTradeEcharts.resize();
                    }

                })
            },
            // 展示园区客户数量-柱状图
            showAreaDemandEcharts(xAxisMsg = [], seriesMsg = []) {
                let parkAreaDemandOptions = {
                    width: "100%",
                    color: ["#02c2ff"],
                    xAxis: [{
                        type: "category",
                        data: xAxisMsg,
                        axisLabel: {
                            interval: 0,
                            // rotate: 40, // 文字倾斜
                            formatter: function(value) {
                                if (window.innerWidth > 1480) {
                                    var maxLength = 4; //每项显示文字个数
                                }
                                if (
                                    window.innerWidth < 1480 &&
                                    window.innerWidth > 1200
                                ) {
                                    var maxLength = 3; //每项显示文字个数
                                }
                                if (window.innerWidth < 1200) {
                                    var maxLength = 2; //每项显示文字个数
                                }
                                var ret = ""; //拼接加\n返回的类目项
                                var valLength = value.length; //X轴类目项的文字个数
                                var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                                if (rowN > 1) {
                                    //如果类目项的文字大于3,
                                    for (var i = 0; i < rowN; i++) {
                                        var temp = ""; //每次截取的字符串
                                        var start = i * maxLength; //开始截取的位置
                                        var end = start + maxLength; //结束截取的位置
                                        //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                        temp =
                                            value.substring(start, end) + "\n";
                                        ret += temp; //凭借最终的字符串
                                    }
                                    return ret;
                                } else {
                                    return value;
                                }
                            },
                            textStyle: {
                                align: "center"
                            },
                            color: function(value) {
                                return (value = "#02c2ff");
                            }
                        },
                        axisLine: {
                            symbol: ["none", "arrow"],
                            symbolSize: [5, 7],
                            lineStyle: {
                                color: "#979797"
                            }
                        }
                    }],
                    tooltip: {
                        formatter: "{a} <br/> " +
                            "<i style='width: 10px; height:10px; background-color: yellow; border-radius: 50%; display: inline-block;'></i>" +
                            "&nbsp;{b}&nbsp;:&nbsp;&nbsp;{c}个&nbsp;&nbsp;&nbsp;",
                        textStyle: {
                            align: "left"
                        }
                    },
                    grid: {
                        left: 0,
                        right: 0,
                        top: 30,
                        bottom: 20,
                        containLabel: true,
                        tooltip: {
                            //---鼠标焦点放在图形上，产生的提示框
                            show: true,
                            trigger: "item", //---触发类型
                            textStyle: {
                                color: "yellow"
                            }
                        }
                    },
                    yAxis: [{
                        type: "value",
                        axisLabel: {
                            show: true
                        },
                        axisTick: {
                            show: false
                        },
                        splitLine: {
                            show: true
                        },
                        axisLine: {
                            symbol: ["none", "arrow"],
                            symbolSize: [5, 7],
                            lineStyle: {
                                color: "#979797"
                            }
                        }
                    }],
                    series: [{
                        name: "园区客户数",
                        data: seriesMsg,
                        type: "bar",
                        barCategoryGap: "60%", //调整柱子宽度
                        itemStyle: {
                            normal: {
                                barBorderRadius: [10, 10, 0, 0],
                                color: new this.$echarts.graphic.LinearGradient(
                                    0,
                                    0,
                                    0,
                                    1,
                                    [{
                                            offset: 0,
                                            color: "#55c8eb"
                                        },
                                        {
                                            offset: 0.5,
                                            color: "#4387fd"
                                        },
                                        {
                                            offset: 1,
                                            color: "#5866e0"
                                        }
                                    ]
                                ),
                                opacity: 0.85
                            },
                            emphasis: {
                                color: "#ff8d1f"
                            }
                        }
                    }]
                };
                let parkAreaDemandEcharts = this.$refs.parkAreaDemandEcharts;
                parkAreaDemandEcharts = this.$echarts.init(parkAreaDemandEcharts);
                parkAreaDemandEcharts.setOption(parkAreaDemandOptions);
                window.addEventListener("resize", function() {
                    var customerAnalysisMain = document.querySelector(".customerAnalysisMain");
                    if (customerAnalysisMain) {
                        document.querySelector("#parkAreaDemandEcharts").style.width =
                            window.innerWidth - 320 + "px";
                        parkAreaDemandEcharts.resize();
                    }

                });
            },
            // 园区分析客户数量axios获取数据
            async getParkMsg() {
                let entity = {
                    cusStatus: this.parkUserStatusValue || "3"
                };
                const res = await this.$ajax.post(`/customer/analysisByPk`, {
                    entity
                });
                let result = res.data.result;
                let xAxisMsg = [];
                let seriesMsg = [];
                result.filter((item, index, arr) => {
                    xAxisMsg.push(item.pkName);
                    seriesMsg.push(item.counter);
                });
                this.showAreaDemandEcharts(xAxisMsg, seriesMsg);
            },
            // 行业获取客户数量axios获取数据
            async getIndustry() {
                let entity = {
                    cusStatus: this.tradeUserStatusValue || 3
                };
                const res = await this.$ajax.post(`/customer/analysisByTrade`, {
                    entity
                });
                let result = res.data.result;
                let xAxisMsg = [];
                let seriesMsg = [];
                result.filter((item, index, arr) => {
                    xAxisMsg.push(item.tradeName);
                    seriesMsg.push(item.counter);
                });
                this.showAnalysisByTradeEcharts(xAxisMsg, seriesMsg);
            },
            // 客户需求面积axios获取数据
            async customerDemand() {
                const res = await this.$ajax.post(
                    `/needAreaAnalysis/selectNeedAreaAnalysis`, {
                        entity: {}
                    }
                );
                let result = res.data.result;
                this.showDemandEcharts(result, this.getCustomerDemandCode);
            },
            // 获客渠道统计axios获取数据
            async getCustomer() {
                let res = await this.$ajax.post(
                    `/channelAnalysis/selectChannelAnalysis`, {
                        entity: {}
                    }
                );
                let result = res.data.result;
                this.showDitchEcharts(result, this.getCustomerDitchCode);
            },
            // 获取用户状态axios获取数据
            async getUserStatus() {
                let res = await this.$ajax.post(`/customer/status`, {
                    entity: {}
                });
                let result = res.data.result;
                this.parkUserStatusOptions = result;
                this.tradeUserStatusOptions = result;
                // this.showAnalysisByTradeEcharts(result)
            }
        }
    };
</script>
<style lang='less' scoped>
    .customerAnalysis {
        height: calc(100vh - 165px);
        border: 0 !important;
        background-color: #f2f2f2;
        // margin-top: 10px;
        margin: 0px !important;
        overflow: auto;

        .customerAnalysisMain {
            .firstBanner {
                display: flex;
                justify-content: flex-start;

                .getCustomerDitch {
                    float: left;
                    margin-right: 10px;
                    background-color: #fff;
                    padding-top: 15px;
                    padding-left: 1.5%;

                    .getCustomerDitchTitle {
                        text-align: left;
                        color: #333b4d;
                        font-size: 16px;
                        margin: 10px;
                    }
                }

                .customerDemand {
                    padding-top: 15px;
                    padding-left: 1.5%;
                    float: right;
                    background-color: #fff;

                    .customerDemandTitle {
                        text-align: left;
                        color: #333b4d;
                        font-size: 16px;
                        margin: 10px;
                    }
                }

                .title {
                    float: left;
                    margin-left: 2%;
                }

                .desc {
                    float: right;
                    margin-right: 7%;
                }
            }

            .secondBanner {
                .analysisByTrade {
                    margin-top: 10px;
                    padding-top: 20px;
                    // padding-left: 1.5%;
                    float: left;
                    background-color: #fff;

                    .analysisByTradeHead {
                        width: 100%;
                        text-align: left;
                        padding-left: 20px;
                        overflow: hidden;
                        padding-bottom: 12px;

                        .title {
                            float: left;
                            margin-left: 2%;
                        }

                        .desc {
                            float: right;
                            margin-right: 5%;
                            height: 34.55px;
                        }

                        .el-select {
                            float: right;
                            width: 100px;
                            margin-right: 30px;
                        }

                        & /deep/ .el-input {
                            min-width: 50px;
                        }
                    }

                    .analysisByTradeBox {
                        padding-left: 1.5%;
                    }

                    .analysisByTradeTitle {
                        text-align: left;
                        color: #333b4d;
                        font-size: 16px;
                    }
                }
            }

            .threeBanner {
                margin-top: 10px;
                padding-top: 20px;
                background-color: #fff;

                .parkAreaDemandHead {
                    width: 100%;
                    text-align: left;
                    padding-left: 20px;
                    overflow: hidden;
                    padding-bottom: 12px;

                    .title {
                        float: left;
                        margin-left: 2%;
                    }

                    .desc {
                        float: right;
                        margin-right: 5%;
                    }

                    .el-select {
                        float: right;
                        width: 100px;
                        margin-right: 30px;
                    }
                }

                .parkAreaDemandBox {
                    padding-left: 1.5%;
                }
            }
        }
    }

    #getCustomerDitchEcharts {
        width: 97%;
        height: 400px;
    }

    #customerDemandEcharts {
        width: 97%;
        height: 400px;
    }

    #analysisByTradeEcharts {
        width: 100%;
        height: 450px;
    }

    #parkAreaDemandEcharts {
        width: 100%;
        height: 450px;
    }
</style>