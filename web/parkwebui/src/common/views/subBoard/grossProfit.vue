<template>
    <div id="grossProfit" class="grossProfit">
        <el-card shadow="hover">
            <div slot="header" style="height: 30px; line-height: 30px; text-align: left;">
                <el-row :gutter="10">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <span class="park-data-title-font">毛利率统计看板</span>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-button class="edit-button" size="small" type="primary" @click="deleteBoard">删除</el-button>
                    </el-col>
                </el-row>
            </div>

            <el-row class="grossHead">
                <el-date-picker class="selectMonth grossPicker" v-model="selectMonth" size="small" type="month" placeholder="选择日期时间">
                </el-date-picker>
                <el-select size="small" class="territoryCode" @change="changeTerritoryCode(territoryCode)" v-model="territoryCode" placeholder="全部大区">
                    <el-option v-for="item in parkRegionsList" :key="item.deptCode" :label="item.deptName" :value="item.deptCode">
                    </el-option>
                </el-select>
                <el-select size="small" class="cityCode" v-model="cityCode" @change="changeCityCode(cityCode)" placeholder="全部城市">
                    <el-option v-for="item in parkCityList" :key="item.cityCode" :label="item.cityName" :value="item.cityCode">
                    </el-option>
                </el-select>
                <el-select size="small" class="pkCode" v-model="pkCode" placeholder="全部园区">
                    <el-option v-for="item in parkGardenList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode">
                    </el-option>
                </el-select>
                <el-button class="changeAllButton" size="small" type="primary" @click="changeAll" icon="el-icon-search">查询
                </el-button>
            </el-row>

            <el-row class="grossMain" type="flex">
                <el-col style="position: relative;" :span="8">
                    <div class="mainLeft_head">
                        <el-radio @click.native.prevent="mainLeft_toggleEcharts1" v-model="showMainLeftEcharts" label="1">
                            项目面积成本
                        </el-radio>
                        <el-radio @click.native.prevent="mainLeft_toggleEcharts2" v-model="showMainLeftEcharts" label="2">
                            已租面积成本
                        </el-radio>
                    </div>
                    <div class="mainLeft">
                        <div v-show="showMainLeftEcharts == '1'" class="mainLeft_Echarts_project" ref="mainLeft_Echarts_project" id="mainLeft_Echarts_project"></div>
                        <div v-show="showMainLeftEcharts == '2'" class="mainLeft_Echarts_invest" ref="mainLeft_Echarts_invest" id="mainLeft_Echarts_invest"></div>
                    </div>
                </el-col>
                <el-col style="position: relative;" :span="16">
                    <div class="mainRight_head">
                        <el-tooltip class="item" effect="dark" placement="top-start">
                            <div slot="content">切换为柱状图</div>
                            <i @click="showTable(1)" class="iconfont el-icon-diy-biaoge"></i>&nbsp;
                        </el-tooltip>
                        <el-tooltip class="item" effect="dark" placement="top-start">
                            <div slot="content">切换为表格</div>
                            <i @click="showTable(2)" class="iconfont el-icon-diy-zhuzhuangtu"></i>
                        </el-tooltip>
                    </div>
                    <div class="mainRight">
                        <div v-show="isShow_table" class="mainRight_table">
                            <!-- <span class="mainRightTitles">查看所有明细</span> -->
                            <el-table :data="tableData" height="350" style="width: 100%">
                                <el-table-column v-if="showPkName" prop="pkName" label="园区 (园区)">
                                </el-table-column>
                                <el-table-column v-if="showTerritoryTypeName" prop="territoryTypeName" label="大区 (大区)">
                                </el-table-column>
                                <el-table-column prop="projectPrice" label="项目面积成本(元/月)">
                                    <template slot-scope="scope">
                                        <span>{{scope.row.projectPrice | rounding}}</span>
                                    </template>
                                </el-table-column>
                                <el-table-column prop="investPrice" label="已租面积成本(元/月)">
                                    <template slot-scope="scope">
                                        <span>{{scope.row.investPrice | rounding}}</span>
                                    </template>
                                </el-table-column>
                                <el-table-column prop="rentIncome" label="已租应收金额(元/月)">
                                    <template slot-scope="scope">
                                        <span>{{scope.row.rentIncome | rounding}}</span>
                                    </template>
                                </el-table-column>
                                <el-table-column prop="projectGross" label="项目毛利率 ">
                                    <template slot-scope="scope">
                                        <span>{{ scope.row.projectGross + '%' }}</span>
                                    </template>
                                </el-table-column>
                                <el-table-column prop="investGross" label="出租毛利率 ">
                                    <template slot-scope="scope">
                                        <span>{{ scope.row.investGross + '%' }}</span>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                        <div v-show="isShow_tableEcharts" class="mainRight_Echarts" ref="mainRight_Echarts" id="mainRight_Echarts"></div>
                    </div>
                </el-col>
            </el-row>
        </el-card>

        <!--<div class="rece-cost-exception-div">-->
        <!--<div class="park-data-title-font">毛利率统计看板： {{pkName}} </div>-->
        <!--<el-button class="edit-button" size="small" type="primary" @click="deleteBoard">删除</el-button>-->
        <!--</div>-->
    </div>
</template>

<script scoped>
    import echarts from "echarts";
    export default {
        props: ["id", "param"],
        data() {
            return {
                pkName: "",
                parkGardenList: [],
                parkCityList: [],
                parkRegionsList: [],
                territoryCode: "",
                pkCode: "",
                cityCode: "",
                selectMonth: "",
                rentIncomeTotal: "",
                investPriceTotal: "",
                tableData: [{
                    pkName: "", // 园区
                    territoryTypeName: "", // 大区
                    projectPrice: "", // 项目面积成本
                    investPrice: "", // 已租面积成本
                    rentIncome: "", // 已租应收金额
                    projectGross: "", // 项目毛利率
                    investGross: "", // 出租毛利率
                }],
                showTerritoryTypeName: true,
                showPkName: false,
                isShow_table: false,
                isShow_tableEcharts: true,
                showMainLeftEcharts: "1", // 1是展示已租面积成本饼图, 2是展示项目面积成本饼图
                paramObj: {}, // ajax参数
            };
        },
        filters: {
            rounding(value) {
                return (+value).toFixed(2)
            }
        },
        created() {
            this.selectMonth = new Date();
            // 查询大区
            this.selectParkRegions();
        },
        mounted() {
            this.paramObj = {
                entity: {},
                selectMonth: new Date()
            };
            if (document.querySelector(".grossMain")) {
                document.querySelector("#mainRight_Echarts").style.width = (window.innerWidth - 320) * (2 / 3) + "px";
                document.querySelector("#mainLeft_Echarts_project").style.width = (window.innerWidth - 320) * (1 / 3) + "px";
                document.querySelector("#mainLeft_Echarts_invest").style.width = (window.innerWidth - 320) * (1 / 3) + "px";
            }
            this.$nextTick(() => {
                this.showEcharts(this.paramObj);
            });
        },
        methods: {
            // 毛利率表格柱状图切换
            showTable(flag) {
                if (flag === 1) {
                    this.isShow_table = true;
                    this.isShow_tableEcharts = false;
                } else if (flag === 2) {
                    this.isShow_table = false;
                    this.isShow_tableEcharts = true;
                }
            },
            // 左边饼图切换
            mainLeft_toggleEcharts1() {
                this.showMainLeftEcharts = "1";
                // if (this.showMainLeftEcharts == '1') {
                //     this.showMainLeftEcharts = '2';
                // } else {
                //     this.showMainLeftEcharts = '1';
                // }
            },
            // 左边饼图切换
            mainLeft_toggleEcharts2() {
                this.showMainLeftEcharts = "2";
                // if (this.showMainLeftEcharts == '2') {
                //     this.showMainLeftEcharts = '1';
                // } else {
                //     this.showMainLeftEcharts = '2';
                // }
            },
            // 展示左边echarts
            showMainLeft_Echarts_One(result) {
                let rentIncomeTotal = 0; // 已租应收金额
                let investPriceTotal = 0; // 已租面积成本
                let projectPriceTotal = 0; // 项目面积成本
                result.filter((item) => {
                    rentIncomeTotal += item.rentIncome;
                    investPriceTotal += item.investPrice;
                    projectPriceTotal += item.projectPrice;
                });
                investPriceTotal = investPriceTotal.toFixed(2);
                rentIncomeTotal = rentIncomeTotal.toFixed(2);
                projectPriceTotal = projectPriceTotal.toFixed(2);
                let option1 = {
                    tooltip: {
                        formatter: "{a} <br/> " +
                            "<i style='width: 10px; height:10px; background-color: yellow; border-radius: 50%; display: inline-block;'></i>" +
                            "&nbsp;{b}{c} 元&nbsp;&nbsp;&nbsp;",
                        textStyle: {
                            align: "left",
                            color: "yellow"
                        }
                    },
                    legend: {
                        formatter: function(name) {
                            if (name === "已租应收金额：") {
                                return name + " " + rentIncomeTotal + " 元";
                            }
                            if (name === "已租面积成本：") {
                                return name + " " + investPriceTotal + " 元";
                            }
                        },
                        orient: "vertical",
                        x: "center",
                        y: "82%",
                        itemWidth: 33,
                        itemHeight: 20,
                        // itemGap: 13, //间距
                        padding: 5,
                        icon: "pin",
                        textStyle: {
                            color: "#666",
                            fontSize: 16,
                        },
                        data: [{
                                name: "已租应收金额："
                            },
                            {
                                name: "已租面积成本："
                            },
                        ]
                    },
                    series: [{
                        name: "毛利率",
                        type: "pie",
                        radius: ["30%", "50%"],
                        center: ["50%", "44%"],
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
                                value: rentIncomeTotal,
                                name: "已租应收金额："
                            },
                            {
                                value: investPriceTotal,
                                name: "已租面积成本："
                            },
                        ]
                    }],
                    color: [
                        "#01cd61",
                        "#f7d856",
                    ]
                }
                let option2 = {
                    tooltip: {
                        formatter: "{a} <br/> " +
                            "<i style='width: 10px; height:10px; background-color: yellow; border-radius: 50%; display: inline-block;'></i>" +
                            "&nbsp;{b}{c} 元&nbsp;&nbsp;&nbsp;",
                        textStyle: {
                            align: "left",
                            color: "yellow"
                        }
                    },
                    legend: {
                        formatter: function(name) {
                            if (name === "已租应收金额：") {
                                return name + " " + rentIncomeTotal + " 元";
                            }
                            if (name === "项目面积成本：") {
                                return name + " " + projectPriceTotal + " 元";
                            }
                        },
                        orient: "vertical",
                        x: "center",
                        y: "82%",
                        itemWidth: 33,
                        itemHeight: 20,
                        icon: "pin",
                        textStyle: {
                            color: "#666",
                            fontSize: 16
                        },
                        data: [{
                                name: "已租应收金额："
                            },
                            {
                                name: "项目面积成本："
                            },
                        ]
                    },
                    series: [{
                        name: "毛利率",
                        type: "pie",
                        radius: ["30%", "50%"],
                        center: ["50%", "44%"],
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
                                value: rentIncomeTotal,
                                name: "已租应收金额："
                            },
                            {
                                value: projectPriceTotal,
                                name: "项目面积成本："
                            },
                        ]
                    }],
                    color: [
                        "#01cd61",
                        "#4994ff",
                    ]
                }
                let mainLeft_Echarts_project = document.querySelector("#mainLeft_Echarts_project");
                let mainLeft_Echarts_invest = document.querySelector("#mainLeft_Echarts_invest");

                mainLeft_Echarts_project = this.$echarts.init(mainLeft_Echarts_project);
                mainLeft_Echarts_invest = this.$echarts.init(mainLeft_Echarts_invest);

                mainLeft_Echarts_project.setOption(option2);
                mainLeft_Echarts_invest.setOption(option1);

                window.addEventListener("resize", function() {
                    var grossMain = document.querySelector(".grossMain");
                    if (grossMain) {
                        document.querySelector("#mainLeft_Echarts_project").style.width = (window.innerWidth - 320) * (1 / 3) + "px";
                        mainLeft_Echarts_project.resize();
                        document.querySelector("#mainLeft_Echarts_invest").style.width = (window.innerWidth - 320) * (1 / 3) + "px";
                        mainLeft_Echarts_invest.resize();
                    }

                })
            },
            // 展示右边毛利率柱状图
            showMainRight_Echarts(result) {
                let xAxisMsg = [];
                let projectGrossMsg = [];
                let investGrossMsg = [];
                if (this.showTerritoryTypeName) {
                    result.filter((item, index) => xAxisMsg.push(item.territoryTypeName))
                } else {
                    result.filter((item, index) => xAxisMsg.push(item.pkName))
                }
                result.filter((item, index) => projectGrossMsg.push(item.projectGross));
                result.filter((item, index) => investGrossMsg.push(item.investGross));
                let mainRight_Echarts_Option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: [
                            '项目毛利率',
                            '出租毛利率'
                        ]
                    },
                    grid: {
                        // left: '3%',
                        // right: '3%',
                        // top: '3%',
                        // bottom: '3%',
                        containLabel: true, // label显示全
                    },
                    xAxis: [{
                        type: 'value',
                    }],
                    yAxis: [{
                        axisTick: {
                            show: false
                        },
                        type: 'category',
                        data: xAxisMsg,
                    }],
                    series: [{
                            name: '项目毛利率',
                            type: 'bar',
                            // stack: '1',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'inside',
                                }
                            },
                            color: "#5ddcff",
                            // barCategoryGap: -1, // 所有的柱状图挨着
                            data: projectGrossMsg,
                        },
                        {
                            name: '出租毛利率',
                            type: 'bar',
                            // stack: '1', // 同一个比较的柱状图叠堆着
                            barGap: 0, // 同一个比较的柱状图紧挨着
                            label: {
                                normal: {
                                    show: true
                                }
                            },
                            color: "#ff8f2f",
                            data: investGrossMsg
                            // data: [-120, -132, -101]
                        },
                    ]
                }
                let mainRight_Echarts = this.$refs.mainRight_Echarts;
                mainRight_Echarts = this.$echarts.init(mainRight_Echarts);
                mainRight_Echarts.setOption(mainRight_Echarts_Option, true);
                window.addEventListener("resize", function() {
                    if (document.querySelector(".grossMain")) {
                        document.querySelector("#mainRight_Echarts").style.width = (window.innerWidth - 320) * (2 / 3) + "px";
                        mainRight_Echarts.resize();
                    }
                })
            },
            // 展示数据
            showEcharts(paramObj) {
                this.$ajax({
                    method: "post",
                    url: "chartReport/grossReport",
                    data: paramObj
                }).then(res => {
                    this.projectGross = this.tableData = res.data.result; // 给整个表格传值
                    this.showMainRight_Echarts(res.data.result);
                    this.showMainLeft_Echarts_One(res.data.result);
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
                this.paramObj = {
                    entity: {
                        territoryType: this.territoryCode,
                        pkCode: this.pkCode,
                        cityCode: this.cityCode
                    },
                    selectMonth: this.selectMonth
                };
                this.showEcharts(this.paramObj);
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
                                    "系统异常", {
                                        confirmButtonText: "确定",
                                        callback: action => {}
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
            }
        }
    };
</script>

<style lang="less" scoped>
    .cusListTable.el-table th {
        background-color: #f5f7fa;
    }

    .grossProfit {
        margin-right: 15px;
        width: 100%;
        background-color: white;
        overflow-x: hidden;
        /*height: 620px;*/
    }

    .grossProfit[data-v-30e13d82] {
        overflow-y: hidden;
    }

    .mainRight_Echarts {
        width: 100%;
        height: 438px;
        margin-top: 35px;
    }

    .iconfont {
        cursor: pointer;
        color: #4494f0;
    }

    #mainLeft_Echarts_project {
        width: 100%;
        height: 438px;
    }

    #mainLeft_Echarts_invest {
        width: 100%;
        height: 438px;
    }

    .mainRight_head {
        position: absolute;
        left: 8%;
        top: 0;
        z-index: 666;
    }

    .mainLeft_head {
        position: absolute;
        left: 0%;
        top: 0;
        z-index: 666;
    }

    .mainRight {
        height: 438px;
    }

    /*-------------------------------公共元素  每个div结束后分隔的时候用----------------------------------------*/
    .comm-box-div {
        height: 10px;
        background-color: #f2f2f2;
    }

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

    .ul-park-data li {
        float: left;
        margin-left: 5%;
        color: #919191;
    }

    /*--------------------------------应收费用异常看板 start------------------------------*/

    .rece-cost-exception-table {
        margin-left: 5px;
        width: 99%;
    }

    .page_down {
        float: right;
        margin-right: 40px;
    }

    /*--------------------------------应收费用异常看板 end----------------------------------*/

    /* -----------------------------------头部区域---------------------------------------- */
    .grossHead {
        min-width: 1200px;
        text-align: left;
        /*padding-left: 20px;*/
        overflow: hidden;
    }

    .headerText {
        font-size: 20px;
        font-weight: 700;
        left: 200px;
        top: 200px;
        margin-right: 20px;
        float: left;
    }

    .grossHead {
        width: 100%;
    }

    .grossHead>.headerText {
        margin-right: 10px;
    }

    .grossHead .grossPicker {
        /*width: 11%;*/
        margin-right: 1%;
    }

    .el-select {
        width: 16%;
        margin-right: 1%;
    }

    /* ----------------------------------头部区域-------------------------------------------- */

    /* ----------------------------------内容区域-------------------------------------------- */
    /* 内容区域 */
    .grossMain {
        text-align: left;
        /*width: 98%;*/
        border: 1px solid #ccc;
        /*margin-left: 1%;*/
        margin-top: 20px;
        padding: 20px;
        /*padding-left: 0;*/
        height: 480px;
    }

    /* ----------------------------------内容区域-------------------------------------------- */

    /* ----------------------------------左边内容区域-------------------------------------------- */
    /* 左边内容模块 */
    .mainLeft {
        position: relative;
        width: 100%;
        height: 100%;
    }

    /* 左边内容标题区域 */
    .mainLeftTitles {
        font-weight: 700;
        font-size: 20px;
        margin-left: 20px;
    }

    /* .grossEchartsCenter {
                text-align: center;
            } */
    /* 左边内容echarts区域 */
    #grossEcharts {
        display: inline-block;
        width: 560px !important;
        height: 400px !important;
    }

    /* 左边内容文字区域 */
    .textLeft {
        position: absolute;
        bottom: -6px;
        width: 100%;
        left: 11%;
    }

    p {
        padding: 0;
        margin: 0;
    }

    /* 左边内容圆点 */
    .textRight .dotOne>i,
    .dotTwo>i,
    .dotThree>i {
        display: inline-block;
        border-radius: 50%;
        width: 20px;
        height: 20px;
        border: 1px solid #ccc;
        vertical-align: middle;
    }

    .dotOne>i {
        display: inline-block;
        border-radius: 50%;
        width: 20px;
        height: 20px;
        border: 1px solid #ccc;
        vertical-align: middle;
        background-color: #f26161;
    }

    .dotTwo>i {
        background-color: #02b1ee;
    }

    .dotThree>i {
        background-color: #f7d856;
    }

    /* ----------------------------------左边内容区域-------------------------------------------- */

    /* ----------------------------------右边内容区域-------------------------------------------- */
    /* 右边内容区域 */
    .mainRight {
        position: relative;
        width: 100%;
        /* left: 430PX; */
        /* top: 18px; */
    }

    /* 右边内容标题区域 */
    .mainRightTitles {
        position: absolute;
        right: 0;
        top: 0;
        color: #4998ff;
        font-size: 20px;
        font-weight: 600;
    }

    /* 右边内容表格区域 */
    .el-table {
        box-shadow: -1px 1px 8px 1px #ccc;
        border: 1px solid #ccc;
        position: absolute;
        right: 0px;
        top: 72px;
        overflow: auto;
    }

    /* ----------------------------------右边内容区域-------------------------------------------- */
</style>