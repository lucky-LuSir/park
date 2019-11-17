<template>
    <div id="receivablecase" class="receivablecase">
        <el-card shadow="hover">
            <div slot="header" style="height: 30px; line-height: 30px; text-align: left;">
                <el-row :gutter="10">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <span class="park-data-title-font">应收情况总览看板</span>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-button class="edit-button" size="small" type="primary" @click="deleteBoard">删除</el-button>
                    </el-col>
                </el-row>
            </div>

            <el-row class="receivableHead">
                <el-date-picker class="receivablePacker" v-model="selectMonth" type="daterange" align="right" unlink-panels
                                size="small" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"
                                placeholder="选择日期时间">
                </el-date-picker>
                <el-select size="small" @change="changeTerritoryCode(territoryCode)" v-model="territoryCode"
                           placeholder="全部大区">
                    <el-option v-for="item in parkRegionsList" :key="item.deptCode" :label="item.deptName"
                               :value="item.deptCode">
                    </el-option>
                </el-select>
                <el-select size="small" v-model="cityCode" @change="changeCityCode(cityCode)" placeholder="全部城市">
                    <el-option v-for="item in parkCityList" :key="item.cityCode" :label="item.cityName"
                               :value="item.cityCode">
                    </el-option>
                </el-select>
                <el-select size="small" v-model="pkCode" placeholder="全部园区">
                    <el-option v-for="item in parkGardenList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode">
                    </el-option>
                </el-select>
                <el-button size="small" type="primary" @click="changeAll" icon="el-icon-search">查询</el-button>
            </el-row>

            <el-row class="receivableMain" type="flex">
                <el-col :span="12">
                    <div class="mainLeft">
                        <div id="receivableEchartsOne" ref="receivableEchartsOne" class="receivableEchartsOne"></div>
                    </div>
                </el-col>
                <el-col :span="12">
                    <div class="mainRight">
                        <div id="receivableEchartsTwo" ref="receivableEchartsTwo" class="receivableEchartsTwo"></div>
                    </div>
                </el-col>
            </el-row>
        </el-card>
    </div>
</template>

<script scoped>
    import echarts from "echarts";
    export default {
        props: ["id", "param"],
        data() {
            return {
                pkName: "",
                selectMonth: "",
                parkGardenList: [],
                parkCityList: [],
                parkRegionsList: [],
                territoryCode: "",
                pkCode: "",
                cityCode: "",
                rentReceivablePrice: "",
                totalWaitedPrice: "",
                electricityReceivablePrice: "",
                premiumReceivablePrice: "",
                propertyReceivablePrice: "",
                totalPaidPrice: "",
                waterReceivablePrice: ""
            };
        },
        created() {
            // 查询大区
            this.selectParkRegions();
        },
        mounted() {
            let monthSearchFirst = this.monthSearchFirst();
            let monthSearchLast = this.monthSearchLast();
            this.selectMonth = [monthSearchFirst, monthSearchLast];
            let paramObj = {
                entity: {},
                startDate: monthSearchFirst,
                endDate: monthSearchLast
            };
            // 展示echarts数据
            this.$nextTick(() => {
                this.showEcharts(paramObj);
                this.$forceUpdate();
            })
        },
        updated() {
            if (document.querySelector(".receivableMain")) {
                document.querySelector("#receivableEchartsOne").style.width =
                    (window.innerWidth - 320) / 2 + "px";
                document.querySelector("#receivableEchartsTwo").style.width =
                    (window.innerWidth - 320) / 2 + "px";
            }

        },
        methods: {
            // 查询按钮事件
            changeAll() {
                if (this.selectMonth == "" || this.selectMonth == null) {
                    this.$message("请选择日期");
                    return;
                }
                let paramObj = {
                    entity: {
                        territoryType: this.territoryCode,
                        pkCode: this.pkCode,
                        cityCode: this.cityCode
                    },
                    startDate: this.selectMonth[0],
                    endDate: this.selectMonth[1]
                };
                this.showEcharts(paramObj);
            },
            // 展示echarts数据
            showEcharts(paramObj) {
                //  let monthSearchFirst = this.monthSearchFirst()
                //  let monthSearchLast = this.monthSearchLast()
                //  this.selectMonth = [monthSearchFirst, monthSearchLast]
                // this.selectMonth = this.monthSearch()
                // 基于准备好的dom，初始化echarts实例
                // let myChartOne = echarts.init(document.getElementById('receivableEcharts'));
                // 利用ref和$refs 来代替document.getElementById（）获取该图形容器对象
                var receivableEchartsOne = this.$refs.receivableEchartsOne;
                if (receivableEchartsOne) {
                    receivableEchartsOne = this.$echarts.init(receivableEchartsOne);
                }
                // 基于准备好的dom，初始化echarts实例
                // let myChartTwo = echarts.init(document.getElementById('echartsRight'));

                // 利用ref和$refs 来代替document.getElementById（）获取该图形容器对象
                var receivableEchartsTwo = this.$refs.receivableEchartsTwo;
                if (receivableEchartsTwo) {
                    receivableEchartsTwo = this.$echarts.init(receivableEchartsTwo);
                }

                this.$ajax({
                    method: "post",
                    url: "chartReport/receivableList",
                    data: paramObj
                }).then(res => {
                    let result = res.data.result;
                    let totalWaitedPrice = result.totalWaitedPrice.toFixed(2); // 待收款
                    let totalPaidPrice = result.totalPaidPrice.toFixed(2); //  已入账

                    let rentReceivablePrice = result.rentReceivablePrice.toFixed(2); //    房租待收
                    let electricityReceivablePrice = result.electricityReceivablePrice.toFixed(
                        2
                    ); // 本电费应收
                    let premiumReceivablePrice = result.premiumReceivablePrice.toFixed(
                        2
                    ); // 保险费应收
                    let propertyReceivablePrice = result.propertyReceivablePrice.toFixed(
                        2
                    ); //      物业代收
                    let waterReceivablePrice = result.waterReceivablePrice.toFixed(
                        2
                    ); // 水费
                    this.rentReceivablePrice = rentReceivablePrice;
                    this.totalWaitedPrice = totalWaitedPrice;
                    this.electricityReceivablePrice = electricityReceivablePrice;
                    this.premiumReceivablePrice = premiumReceivablePrice;
                    this.propertyReceivablePrice = propertyReceivablePrice;
                    this.totalPaidPrice = totalPaidPrice;
                    this.waterReceivablePrice = waterReceivablePrice;
                    // 设置第一个echarts的option数据
                    let myChartOneOption = {
                        title: {
                            text: "应收概况",
                            x: "3%",
                            y: "3%",
                            textStyle: {
                                color: "#44545f",
                                fontSize: 20,
                                fontWeight: 400
                            }
                        },
                        tooltip: {
                            trigger: "item",
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            formatter: function (name) {
                                if (name === "已入账：") {
                                    return name + " " + totalPaidPrice + "元";
                                }
                                if (name === "待收款：") {
                                    return name + " " + totalWaitedPrice + "元";
                                }
                            },
                            orient: "vertical",
                            x: "48%",
                            y: "center",
                            itemWidth: 33,
                            itemHeight: 20,
                            data: [
                                {
                                    name: `已入账：`,
                                    icon: "pin",
                                    textStyle: {
                                        color: "#666",
                                        fontSize: 16,
                                    }
                                },
                                {
                                    name: `待收款：`,
                                    icon: "pin",
                                    textStyle: {
                                        color: "#666",
                                        fontSize: 16,
                                    }
                                }
                            ]
                        },
                        series: [
                            {
                                name: "应收概况",
                                type: "pie",
                                radius: ["30%", "50%"],
                                center: ["25%", "50%"],
                                data: [
                                    {value: totalPaidPrice, name: "已入账："},
                                    {value: totalWaitedPrice, name: "待收款："}
                                ],
                                label: {
                                    normal: {
                                        position: "inner",
                                        show: false
                                    }
                                },
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: "rgba(0, 0, 0, 0.5)"
                                    }
                                }
                            }
                        ],
                        color: ["#ff822b", "#eff9ff"]
                    };
                    // 绘制图表
                    receivableEchartsOne.setOption(myChartOneOption);

                    // 设置第二个echarts的option数据
                    let myChartTwoOption = {
                        title: {
                            text: "应收构成",
                            x: "3%",
                            y: "3%",
                            textStyle: {
                                color: "#44545f",
                                fontSize: 20,
                                fontWeight: 400
                            }
                        },
                        tooltip: {
                            trigger: "item",
                            formatter: "{a} <br/>{b}: {c} ({d}%)"
                        },
                        legend: {
                            formatter: function (name) {
                                if (name === "房租应收：") {
                                    return name + " " + rentReceivablePrice + "元";
                                }
                                if (name === "物业费应收：") {
                                    return (
                                        name + " " + propertyReceivablePrice + "元"
                                    );
                                }
                                if (name === "电费应收：") {
                                    return (
                                        name +
                                        " " +
                                        electricityReceivablePrice +
                                        "元"
                                    );
                                }
                                if (name === "水费应收：") {
                                    return name + " " + waterReceivablePrice + "元";
                                }
                                if (name === "保险费应收：") {
                                    return (
                                        name + " " + premiumReceivablePrice + "元"
                                    );
                                }
                            },
                            orient: "vertical",
                            x: "48%",
                            y: "center",
                            itemWidth: 33,
                            itemHeight: 20,
                            data: [
                                {
                                    name: `房租应收：`,
                                    icon: "pin",
                                    textStyle: {
                                        color: "#666",
                                        fontSize: 16,
                                    }
                                },
                                {
                                    name: `物业费应收：`,
                                    icon: "pin",
                                    textStyle: {
                                        color: "#666",
                                        fontSize: 16,
                                    }
                                },
                                {
                                    name: `电费应收：`,
                                    icon: "pin",
                                    textStyle: {
                                        color: "#666",
                                        fontSize: 16,
                                    }
                                },
                                {
                                    name: `水费应收：`,
                                    icon: "pin",
                                    textStyle: {
                                        color: "#666",
                                        fontSize: 16,
                                    }
                                },
                                {
                                    name: `保险费应收：`,
                                    icon: "pin",
                                    textStyle: {
                                        color: "#666",
                                        fontSize: 16,
                                    }
                                }
                            ]
                        },
                        series: [
                            {
                                name: "应收构成",
                                type: "pie",
                                radius: ["30%", "50%"],
                                center: ["25%", "50%"],
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
                                data: [
                                    {
                                        value: rentReceivablePrice,
                                        name: "房租应收："
                                    },
                                    {
                                        value: propertyReceivablePrice,
                                        name: "物业费应收："
                                    },
                                    {
                                        value: electricityReceivablePrice,
                                        name: "电费应收："
                                    },
                                    {
                                        value: waterReceivablePrice,
                                        name: "水费应收："
                                    },
                                    {
                                        value: premiumReceivablePrice,
                                        name: "保险费应收："
                                    }
                                ]
                            }
                        ],
                        color: [
                            "#11d0b6",
                            "#01cd61",
                            "#a486f9",
                            "#02b1ee",
                            "#f7d856"
                        ]
                    };
                    // 绘制图表
                    receivableEchartsTwo.setOption(myChartTwoOption);
                    window.onresize = () => {
                        if (document.querySelector(".receivableMain")) {
                            document.querySelector("#receivableEchartsOne").style.width =
                                (window.innerWidth - 320) / 2 + "px";
                            document.querySelector("#receivableEchartsTwo").style.width =
                                (window.innerWidth - 320) / 2 + "px";
                        }
                        receivableEchartsOne.resize();
                        receivableEchartsTwo.resize();
                    };
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
                //    // 选择城市时回显大区
                //    if (cityCode == 420100 || cityCode == 510100) {
                //        this.territoryCode = '3'
                //    } else if (cityCode == 330100 || cityCode == 310100 || cityCode == 330600 || cityCode == 330200) {
                //        this.territoryCode = '1'
                //    } else if (cityCode == 320400 || cityCode == 320500) {
                //        this.territoryCode = '2'
                //    }
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
                                vm.$message({
                                    showClose: true,
                                    message: "删除看板成功",
                                    type: "success"
                                });
                                // this.$router.replace('/back');
                                vm.backParent();
                            })
                            .catch(error => {
                                vm.$alert(
                                    "页面:删除停车费异常看板失败",
                                    "系统异常",
                                    {
                                        confirmButtonText: "确定",
                                        callback: action => {
                                        }
                                    }
                                );
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
            },
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
            }
        }
    };
</script>

<style lang="less" scoped>
    .receivablecase {
        background-color: #fff;
        overflow-x: hidden;
        // --- 头部区域 ---
        .receivableHead {
            width: 100%;
            text-align: left;
            padding-left: 20px;
            overflow: hidden;
            padding-bottom: 12px;
            .headerText {
                font-size: 20px;
                font-weight: 700;
                left: 200px;
                top: 200px;
            }
            & > span {
                margin-right: 10px;
            }
            .receivablePacker {
                width: 22%;
            }
            .el-select {
                width: 16%;
            }
        }

        // --- 内容区域 ---
        .receivableMain {
            text-align: left;
            border: 1px solid #ccc;
            margin-left: 20px;
            margin-right: 20px;
            // height: 480px;
        }

        #receivableEchartsOne {
            width: 100%;
            height: 350px;
        }
        #receivableEchartsTwo {
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

    .receivablecase[data-v-30e13d82] {
        overflow-y: hidden;
    }

    .receivablePacker.el-range-editor.el-input__inner {
        align-items: baseline;
    }

    /*公共元素  每个div结束后分隔的时候用*/

    /*园区去化看板文字*/
    .park-data-title-font {
        color: #409EFF;
        font-weight: bold;
    }

    /*编辑按钮*/
    .edit-button {
        float: right;
        margin-right: 10px;
    }
</style>