<template>
    <div class="totalBoard">
        <el-card shadow="hover">
            <div slot="header" style="height: 30px; line-height: 30px; text-align: left;">
                <el-row :gutter="10">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <span class="park-data-title-font">数据统计看板</span>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-button class="edit-button" size="small" type="primary" @click="deleteBoard">删除</el-button>
                    </el-col>
                </el-row>
            </div>

            <el-row class="totalBoardHead">
                <!--<span class="headerText">数据统计：</span>-->
                <el-date-picker class="sellingPacker" v-model="selectMonth" type="month" size="small" placeholder="选择日期时间">
                </el-date-picker>
                <el-select size="small" class="territoryCode" @change="changeTerritoryCode(territoryCode)" v-model="territoryCode" placeholder="全部大区">
                    <el-option v-for="item in parkRegionsList" :key="item.deptCode" :label="item.deptName" :value="item.deptCode">
                    </el-option>
                </el-select>
                <el-select size="small" v-model="cityCode" @change="changeCityCode(cityCode)" placeholder="全部城市">
                    <el-option v-for="item in parkCityList" :key="item.cityCode" :label="item.cityName" :value="item.cityCode">
                    </el-option>
                </el-select>
                <el-select size="small" v-model="pkCode" placeholder="全部园区">
                    <el-option v-for="item in parkGardenList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode">
                    </el-option>
                </el-select>
                <el-button class="changeAllButton" size="small" type="primary" @click="changeAll" icon="el-icon-search">查询</el-button>
            </el-row>

            <el-row class="totalBoardMain" type="flex">
                <el-col :span="8">
                    <div ref="oneChartsBox" class="oneChartsBox">
                        <div id="oneCharts" ref="oneCharts" class="oneCharts"></div>
                    </div>
                </el-col>
                <el-col :span="8">
                    <div ref="twoChartsBox" class="twoChartsBox">
                        <div id="twoCharts" ref="twoCharts" class="twoCharts"></div>
                    </div>
                </el-col>
                <el-col :span="8">
                    <div ref="threeChartsBox" class="threeChartsBox">
                        <div id="threeCharts" ref="threeCharts" class="threeCharts"></div>
                    </div>
                </el-col>
            </el-row>
        </el-card>

        <!-- <div @click="to_kanban($event, 1)" class="oneCharts">去化跳转</div> -->
        <!-- <div @click="to_kanban($event, 2)" class="twoCharts">毛利率跳转</div> -->
        <!-- <div @click="to_kanban($event, 3)" class="threeCharts">应收跳转</div> -->
        <!-- <div class="fourCharts"></div> -->
    </div>
</template>
<script>
    import echarts from "echarts";
    export default {
        props: ["id", "param"],
        data() {
            return {
                selectMonth: "",
                parkRegionsList: [],
                parkCityList: [],
                parkGardenList: [],
                territoryCode: "",
                cityCode: "",
                pkCode: ""
            };
        },
        created() {
            this.selectMonth = new Date();
            this.selectParkRegions();
        },
        mounted() {
            let paramObj = {
                entity: {},
                selectMonth: new Date()
            };
            this.$nextTick(() => {
                this.$ajax({
                    data: paramObj,
                    url: "/chartReport/overView",
                    method: "post"
                }).then(res => {
                    this.showEcharts(res);
                });
                this.$forceUpdate();
            });
        },
        updated() {
            var oneCharts = document.querySelector("#oneCharts");
            var twoCharts = document.querySelector("#twoCharts");
            var threeCharts = document.querySelector("#threeCharts");
            oneCharts.style.width = (window.innerWidth - 250) / 3 + "px";
            twoCharts.style.width = (window.innerWidth - 250) / 3 + "px";
            threeCharts.style.width = (window.innerWidth - 250) / 3 + "px";
        },
        methods: {
            // 展示echarts
            showEcharts(res) {
                let result = res.data.result;
                let totalPaidPrice = result.receivableMap.totalPaidPrice; // 应收-已入账
                let totalWaitedPrice = result.receivableMap.totalWaitedPrice; // 应收-待收款

                let totalPropertyIncome = result.profitMap.totalPropertyIncome; // 毛利率-物业收入
                let totalRentCost = result.profitMap.totalRentCost; // 毛利率-支出
                let totalRentIncome = result.profitMap.totalRentIncome; // 毛利率-房租收入

                let totalRentInvestArea = result.sellingMap.totalRentInvestArea; // 去化-空置
                let totalRentableArea = result.sellingMap.totalRentableArea; // 去化-已租

                //  ----------------------------------------------------
                var oneCharts = this.$refs.oneCharts;
                if (oneCharts) {
                    oneCharts = this.$echarts.init(oneCharts);
                }
                var oneChartsOption = {
                    title: {
                        text: "应收概况分析",
                        x: "3%",
                        y: "3%",
                        textStyle: {
                            color: "#333b4d",
                            fontWeight: 400
                        }
                    },
                    tooltip: {
                        trigger: "item",
                        formatter: "{a} <br/>{b}{c} ({d}%)"
                    },
                    legend: {
                        formatter: function(name) {
                            if (name === "已入账：") {
                                return name + " " + totalPaidPrice + "元";
                            }
                            if (name === "待收款：") {
                                return name + " " + totalWaitedPrice + "元";
                            }
                        },
                        orient: "vertical",
                        x: "center",
                        y: "74%",
                        itemWidth: 33,
                        itemHeight: 20,
                        data: [{
                                name: `已入账：`
                            },
                            {
                                name: `待收款：`
                            }
                        ],
                        icon: "pin",
                        textStyle: {
                            color: "#666",
                            fontSize: 16
                        }
                    },
                    series: [{
                        name: "应收概况",
                        type: "pie",
                        radius: ["30%", "45%"],
                        center: ["50%", "40%"],
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
                                value: totalPaidPrice,
                                name: `已入账：`
                            },
                            {
                                value: totalWaitedPrice,
                                name: `待收款：`
                            }
                        ]
                    }],
                    color: ["#ff822b", "#dae3e9"]
                };
                oneCharts.setOption(oneChartsOption);
                // --------------------------------------------------------
                var twoCharts = this.$refs.twoCharts;
                if (twoCharts) {
                    var twoCharts = this.$echarts.init(twoCharts);
                }
                var twoChartsOption = {
                    title: {
                        text: "毛利率分析",
                        x: "3%",
                        y: "3%",
                        textStyle: {
                            color: "#333b4d",
                            fontWeight: 400
                        }
                    },
                    tooltip: {
                        trigger: "item",
                        formatter: "{a} <br/>{b}{c} ({d}%)"
                    },
                    legend: {
                        formatter: function(name) {
                            if (name === "物业费收入：") {
                                return name + " " + totalPropertyIncome + "元";
                            }
                            if (name === "租金成本：") {
                                return name + " " + totalRentCost + "元";
                            }
                            if (name === "租金收入：") {
                                return name + " " + totalRentIncome + "元";
                            }
                        },
                        orient: "vertical",
                        x: "center",
                        y: "70%",
                        itemWidth: 33,
                        itemHeight: 20,
                        data: [{
                                name: `物业费收入：`,
                                icon: "pin",
                                textStyle: {
                                    color: "#666",
                                    fontSize: 16
                                }
                            },
                            {
                                name: `租金成本：`,
                                icon: "pin",
                                textStyle: {
                                    color: "#666",
                                    fontSize: 16
                                }
                            },
                            {
                                name: `租金收入：`,
                                icon: "pin",
                                textStyle: {
                                    color: "#666",
                                    fontSize: 16
                                }
                            }
                        ]
                    },
                    series: [{
                        name: "毛利率",
                        type: "pie",
                        radius: ["30%", "45%"],
                        center: ["50%", "40%"],
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
                                value: totalPropertyIncome,
                                name: `物业费收入：`
                            },
                            {
                                value: totalRentCost,
                                name: `租金成本：`
                            },
                            {
                                value: totalRentIncome,
                                name: `租金收入：`
                            }
                        ]
                    }],
                    color: ["#f26161", "#a6e31c", "#02c2ff"]
                };
                twoCharts.setOption(twoChartsOption);
                // --------------------------------------------------------
                var threeCharts = this.$refs.threeCharts;
                if (threeCharts) {
                    var threeCharts = this.$echarts.init(threeCharts);
                }
                var threeChartsOption = {
                    title: {
                        text: "去化概况分析",
                        x: "3%",
                        y: "3%",
                        textStyle: {
                            color: "#333b4d",
                            fontWeight: 400
                        }
                    },
                    tooltip: {
                        trigger: "item",
                        formatter: "{a} <br/>{b}{c} ({d}%)"
                    },
                    legend: {
                        formatter: function(name) {
                            if (name === "空置面积：") {
                                return name + " " + totalRentInvestArea + "元";
                            }
                            if (name === "在租面积：") {
                                return name + " " + totalRentableArea + "元";
                            }
                        },
                        orient: "vertical",
                        x: "center",
                        y: "74%",
                        itemWidth: 33,
                        itemHeight: 20,
                        data: [{
                                name: `空置面积：`,
                                icon: "pin",
                                textStyle: {
                                    color: "#666",
                                    fontSize: 16
                                }
                            },
                            {
                                //   name: `在租面积：${totalRentableArea}元`,
                                name: `在租面积：`,
                                value: "aaaa",
                                icon: "pin",
                                textStyle: {
                                    color: "#666",
                                    fontSize: 16
                                }
                            }
                        ]
                    },
                    series: [{
                        name: "去化概况",
                        type: "pie",
                        radius: ["30%", "45%"],
                        center: ["50%", "40%"],
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
                                value: totalRentInvestArea,
                                name: `空置面积：`
                            },
                            {
                                value: totalRentableArea,
                                name: `在租面积：`
                            }
                        ]
                    }],
                    color: ["#5ddcff", "#4387fd"]
                };
                threeCharts.setOption(threeChartsOption);
                // --------------------------------------------------------
                window.addEventListener('resize', () => {
                    var totalBoardMain = document.querySelector(".totalBoardMain");
                    if (totalBoardMain) {
                        document.querySelector("#oneCharts").style.width = (window.innerWidth - 320) / 3 + "px";
                        document.querySelector("#twoCharts").style.width = (window.innerWidth - 320) / 3 + "px";
                        document.querySelector("#threeCharts").style.width = (window.innerWidth - 320) / 3 + "px";
                        oneCharts.resize();
                        twoCharts.resize();
                        threeCharts.resize();
                    }
                });
            },
            // 查询按钮事件
            changeAll() {
                if (this.selectMonth == "" || this.selectMonth == null) {
                    this.$message("请选择日期");
                    return;
                }
                if (
                    this.cityCode != "" ||
                    this.pkCode != "" ||
                    this.territoryCode != ""
                ) {
                    this.showTerritoryTypeName = false; // 隐藏大区
                    this.showPkName = true; // 显示园区
                } else {
                    this.showPkName = false;
                    this.showTerritoryTypeName = true;
                }
                let paramObj = {
                    entity: {
                        territoryType: this.territoryCode,
                        pkCode: this.pkCode,
                        cityCode: this.cityCode
                    },
                    selectMonth: this.selectMonth
                };

                this.$ajax({
                    data: paramObj,
                    url: "/chartReport/overView",
                    method: "post"
                }).then(res => {
                    this.showEcharts(res);
                });
            },
            // select框查询园区
            selectAllpark(paramObj) {
                this.$ajax({
                    method: "post",
                    url: "park/list",
                    data: paramObj
                }).then(res => {
                    if (res.status === 200) {
                        this.parkGardenList = res.data.result;

                        this.parkGardenList.unshift({
                            pkCode: "",
                            pkName: "全部园区"
                        });
                    }
                });
            },
            // 城市三级联动查园区
            changeCityCode(cityCode) {
                this.pkCode = "";
                let paramObj = {
                    entity: {
                        cityCode: cityCode
                    },
                    selectMonth: new Date()
                };
                this.selectAllpark(paramObj);
                //  // 选择城市时回显大区
                //  if (cityCode == 420100 || cityCode == 510100) {
                //      this.territoryCode = '3'
                //  } else if (cityCode == 330100 || cityCode == 310100 || cityCode == 330600 || cityCode == 330200) {
                //      this.territoryCode = '1'
                //  } else if (cityCode == 320400 || cityCode == 320500) {
                //      this.territoryCode = '2'
                //  }
            },
            // select框查询城市
            selectParkCity(paramObj) {
                this.$ajax({
                    method: "post",
                    url: "park/selectParkCity",
                    data: paramObj
                }).then(res => {
                    if (res.status === 200) {
                        this.parkCityList = res.data.result;
                        this.parkCityList.unshift({
                            cityCode: "",
                            cityName: "全部城市"
                        });
                    }
                });
            },
            // 大区三级联动查城市
            changeTerritoryCode(territoryCode) {
                this.cityCode = "";
                this.pkCode = "";
                let paramObj = {
                    entity: {
                        territoryType: territoryCode
                    },
                    selectMonth: new Date()
                };
                this.selectParkCity(paramObj);
            },
            // select查询全部大区
            selectParkRegions() {
                let deptList = window.localStorage.getItem("_usedQueryDept");
                this.parkRegionsList = JSON.parse(deptList);
                this.parkRegionsList.unshift({
                    deptCode: "",
                    deptName: "全部大区"
                });
            },
            // 跳转看板
            to_kanban(e, eve) {
                var sellingDetail = document.querySelector("#sellingDetail");
                var grossProfit = document.querySelector("#grossProfit");
                var receivablecase = document.querySelector("#receivablecase");
                // var el_tabs_style = document.querySelector(".el_tabs_style")
                // el_tabs_style.classList.remove("active");
                // document.querySelector(".el_tabs_style").style.paddingTop = "0px";
                // document.querySelector(".el-tabs__content").scrollTop = 200;

                window.location.hash = "";

                if (eve === 1) {
                    // sellingDetail.scrollTop = sellingDetail.offsetTop
                    sellingDetail.scrollIntoView();
                    sellingDetail.scrollTop = "200px";
                    // this.$router.push('/testOnly')

                    // window.location.hash = "#sellingDetail";
                } else if (eve === 2) {
                    grossProfit.scrollIntoView();
                    grossProfit.scrollTop = "200px";

                    // grossProfit.scrollTop = grossProfit.offsetTop
                    // window.location.hash = "#grossProfit";
                } else if (eve === 3) {
                    receivablecase.scrollIntoView();
                    receivablecase.scrollTop = "200px";

                    // receivablecase.scrollTop = receivablecase.offsetTop

                    // window.location.hash = "#receivablecase";
                }
                // document.querySelector(".el_tabs_style").style.paddingTop = "115px";

                // el_tabs_style.classList.add("active")
            },
            // 当月第一天
            monthSearchFirst() {
                var date = new Date();
                date.setDate(1);
                var month = parseInt(date.getMonth() + 1);
                var day = date.getDate();
                if (month < 10) {
                    month = "0" + month;
                }
                if (day < 10) {
                    day = "0" + day;
                }
                return date.getFullYear() + "-" + month + "-" + day;
            },
            // 当月最后一天
            monthSearchLast() {
                var date = new Date();
                var currentMonth = date.getMonth();
                var nextMonth = ++currentMonth;
                var nextMonthFirstDay = new Date(date.getFullYear(), nextMonth, 1);
                var oneDay = 1000 * 60 * 60 * 24;
                var lastTime = new Date(nextMonthFirstDay - oneDay);
                var month = parseInt(lastTime.getMonth() + 1);
                var day = lastTime.getDate();
                if (month < 10) {
                    month = "0" + month;
                }
                if (day < 10) {
                    day = "0" + day;
                }
                return date.getFullYear() + "-" + month + "-" + day;
            },
            // 删除看板
            deleteBoard() {
                this.$confirm("此操作将永久删除该看板, 是否继续?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    })
                    .then(() => {
                        let vm = this;
                        let param = {};
                        let entity = {
                            id: this.id
                        };
                        param.entity = entity;
                        let options = {
                            method: "POST",
                            data: param,
                            url: "board/delete"
                        };
                        this.$ajax(options)
                            .then(response => {
                                if (response) {
                                    vm.$message({
                                        showClose: true,
                                        message: "删除看板成功",
                                        type: "success"
                                    });
                                    // this.$router.replace('/back');
                                    vm.backParent();
                                }
                            })
                            .catch(error => {
                                vm.$alert("页面:数据统计看板失败", "系统异常", {
                                    confirmButtonText: "确定",
                                    callback: action => {}
                                });
                            });
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消删除"
                        });
                    });
            },
            backParent() {
                this.$emit("backParam", "1");
            }
        }
    };
</script>

<style lang="less" scoped>
    .totalBoard {
        // min-width: 1200px;
        background-color: #fff;
        overflow-x: hidden;

        /*padding-bottom: 20px;*/
        .totalBoardHead {
            width: 100%;
            text-align: left;
            margin-top: -10px;
            overflow: hidden;
            padding-bottom: 12px;

            .headerText {
                font-size: 20px;
                font-weight: 700;
                left: 200px;
                top: 200px;
            }

            &>span {
                margin-right: 10px;
            }

            & /deep/ .el-input {
                min-width: 110px;
            }

            .el-date-editor {
                width: 11%;
            }

            .el-select {
                width: 14%;
            }
        }

        .totalBoardMain {
            text-align: left;
            border: 1px solid #ccc;
            /*margin-left: 20px;*/
            /*margin-right: 20px;*/
        }



        .oneCharts {
            min-width: 285px;
            width: 100%;
            height: 350px;
        }

        .twoCharts {
            min-width: 285px;
            width: 100%;
            height: 350px;
        }

        .threeCharts {
            min-width: 285px;
            width: 100%;
            height: 350px;
        }

        .el-date-editor {
            margin-right: 1%;
        }

        .el-select {
            margin-right: 1%;
        }
    }

    .rece-cost-exception-div {
        height: 50px;
    }

    /*-------------------------------公共元素  每个div结束后分隔的时候用----------------------------------------*/

    /*园区去化看板文字*/
    .park-data-title-font {
        font-weight: bold;
        color: #409EFF
    }

    /*编辑按钮*/
    .edit-button {
        float: right;
        margin-right: 10px;
    }

    /* -----------------------------------头部区域---------------------------------------- */
</style>