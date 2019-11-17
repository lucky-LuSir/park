<template>
    <div id="sellingDetail" class="sellingDetail">
        <el-card shadow="hover">
            <div slot="header" style="height: 30px; line-height: 30px; text-align: left;">
                <el-row :gutter="10">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <span class="park-data-title-font">去划面积统计看板</span>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-button class="edit-button" size="small" type="primary" @click="deleteBoard">删除</el-button>
                    </el-col>
                </el-row>
            </div>

            <el-row class="sellingHead">
                <el-date-picker class="select_style"
                                v-model="selectMonth"
                                type="month"
                                size="small"
                                placeholder="选择日期时间">
                </el-date-picker>

                <el-select class="select_style" size="small" @change="changeTerritoryCode(territoryCode)" v-model="territoryCode" placeholder="全部大区">
                    <el-option
                            v-for="item in parkRegionsList"
                            :key="item.deptCode"
                            :label="item.deptName"
                            :value="item.deptCode">
                    </el-option>
                </el-select>

                <el-select class="select_style" size="small" v-model="cityCode" @change="changeCityCode(cityCode)" placeholder="全部城市">
                    <el-option
                            v-for="item in parkCityList"
                            :key="item.cityCode"
                            :label="item.cityName"
                            :value="item.cityCode">
                    </el-option>
                </el-select>

                <el-select class="select_style" size="small" v-model="pkCode" placeholder="全部园区">
                    <el-option
                            v-for="item in parkGardenList"
                            :key="item.pkCode"
                            :label="item.pkName"
                            :value="item.pkCode">
                    </el-option>
                </el-select>
                <el-button class="changeAllButton"  size="small" type="primary" @click="changeAll" icon="el-icon-search">查询</el-button>
            </el-row>

            <el-row class="sellingMain" type="flex">
                <el-col :span="8">
                    <div class="mainLeft">
                        <span class="mainLeftTitles">概况</span>
                        <div class="">
                            <div id="sellingEcharts" ref="myCharts" class="sellingEcharts" style="width:400px; height:400px;"></div>
                        </div>
                        <div class="textLeft">
                            <p class="dotOne"><i></i> &nbsp;&nbsp;在租面积：{{rentInvestArea}}㎡</p>
                            <p class="dotTwo"><i></i> &nbsp;&nbsp;次月到期：{{totalNextMonthEndArea}}㎡</p>
                            <p class="dotThree"><i></i> &nbsp;&nbsp;空置面积：{{rentableArea}}㎡</p>
                            <p class="dotFour"><i></i> &nbsp;&nbsp;本月退租：{{territoryTypeName}}㎡</p>
                        </div>
                    </div>
                </el-col>
                <el-col :span="16">
                    <div class="mainRight" v-cloak>
                        <el-table
                                stripe
                                height="365"
                                :data="tableData"
                                style="width: 100%">
                            <el-table-column v-if="showPkName"
                                             prop="pkName"
                                             label="园区 (园区)"
                                             width="180">
                            </el-table-column>0
                            <el-table-column v-if="showTerritoryTypeName"
                                             prop="territoryTypeName"
                                             label="大区 (大区)"
                                             width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="investArea"
                                    label="总面积"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="rentInvestArea"
                                    label="在租面积">
                            </el-table-column>
                            <el-table-column
                                    prop="totalNextMonthEndArea"
                                    label="次月到期">
                            </el-table-column>
                            <el-table-column
                                    prop="rentableArea"
                                    label="空置">
                            </el-table-column>
                            <el-table-column
                                    prop="totalThisMonthEndArea"
                                    label="本月退租">
                            </el-table-column>
                        </el-table>
                    </div>
                </el-col>
            </el-row>
        </el-card>
    </div>
</template>

<script scoped>
   	import echarts from 'echarts'
    export default {
        props: ['id','param'],
        data () {
            return {
                pkName: '',
                parkGardenList: [],
                parkCityList: [],
                parkRegionsList: [],
                territoryCode: '',
                pkCode: '',
                cityCode: '',
                selectMonth: '',
                tableData: [{
                    territoryTypeName: '',
                    investArea: '',
                    rentInvestArea: '',
                    totalNextMonthEndArea: '',
                    rentableArea: '',
                    totalThisMonthEndArea: ''
                }],
                investArea: '', // 总面积
                rentInvestArea: '', // 在租面积
                rentableArea: '', // 空置面积
                totalNextMonthEndArea: '', // 次月到期
                totalThisMonthEndArea: '', // 本月退租
                territoryTypeName: '',  // 大区类型
                showTerritoryTypeName: true,
                showPkName: false
            }
        },
        created () {
            this.selectMonth = new Date()
            // 查询大区
            this.selectParkRegions();
        },
        mounted () {
            let selectMonth = new Date();
            let paramObj = {
                entity: {},
                selectMonth: selectMonth
            };
            this.showEcharts(paramObj)
        },
        methods: {
            // 查询按钮事件
            changeAll () {
                if (this.selectMonth == '' || this.selectMonth == null) {
                    this.$message('请选择日期')
                    return
                }
                if (this.cityCode != '' || this.pkCode != '' || this.territoryCode != '') {
                     this.showTerritoryTypeName =  false // 隐藏大区
                     this.showPkName =  true // 显示园区
                } else {
                    this.showTerritoryTypeName = true
                    this.showPkName = false
                }

                let paramObj = {
                    entity: {
                        territoryType: this.territoryCode,
                        pkCode: this.pkCode,
                        cityCode: this.cityCode,
                    },
                    selectMonth: this.selectMonth
                };
                this.showEcharts(paramObj)
            },
            // 展示数据
            showEcharts (paramObj) {
                // 基于准备好的dom，初始化echarts实例
                // let myCharts = echarts.init(document.getElementById('sellingEcharts'));

                // 利用ref和$refs 来代替document.getElementById（）获取该图形容器对象
                var myCharts = this.$refs.myCharts;
                if ( myCharts ) {
                    var myCharts = this.$echarts.init(myCharts)
                }
                this.$ajax({
                    method: 'post',
                    url: "chartReport/sellingReport",
                    data: paramObj
                }).then( res => {
                    let investArea = 0 // 总面积
                    let rentInvestArea = 0 // 在租面积
                    let rentableArea = 0 // 空置面积
                    let totalNextMonthEndArea = 0 // 次月到期
                    let totalThisMonthEndArea = 0 // 本月退租
                    let territoryTypeName = 0 // 大区类型
                    let result = res.data.result
                    for (let i = 0; i < result.length; i++) {
                        rentInvestArea += +(result[i].rentInvestArea)
                        totalNextMonthEndArea += +(result[i].totalNextMonthEndArea)
                        rentableArea += +(result[i].rentableArea)
                        totalThisMonthEndArea += +(result[i].totalThisMonthEndArea)
                    }
                    rentInvestArea = rentInvestArea.toFixed(2)
                    totalNextMonthEndArea = totalNextMonthEndArea.toFixed(2)
                    rentableArea = rentableArea.toFixed(2)
                    totalThisMonthEndArea = totalThisMonthEndArea.toFixed(2)
                    this.rentInvestArea = rentInvestArea
                    this.rentableArea = rentableArea
                    this.totalNextMonthEndArea = totalNextMonthEndArea
                    this.totalThisMonthEndArea = totalThisMonthEndArea
                    this.territoryTypeName = territoryTypeName
                    this.tableData = result // 赋值给整个表格
                    // 设置第一个echarts的option数据
                    let myChartOption = {
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b}: {c} ({d}%)"
                        },
                        series: [
                            {
                                name:'去化概况',
                                type:'pie',
                                radius: ['30%', '50%'],
                                avoidLabelOverlap: false,
                                center: ['32%', '33%'],
                                label: {
                                    normal: {
                                        show: false,
                                        position: 'center'
                                    },
                                    emphasis: {
                                        show: false,
                                        textStyle: {
                                            fontSize: '30',
                                            fontWeight: 'bold'
                                        }
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data:[
                                    {value:'', name:''},
                                    {value:'', name:''},
                                    {value:'', name:''},
                                    {value:'', name:''},
                                ]
                            }
                        ],
                        color: ['#01cd61', '#f7d856', '#f26161', '#02b1ee']
                    };
                        
                    // 修改第一个echarts的option相应数据
                    myChartOption.series[0].data = [
                        {value: rentableArea, name:'空置面积'},
                        {value: totalThisMonthEndArea, name:'本月退租'},
                        {value: rentInvestArea, name:'在租面积'},
                        {value: totalNextMonthEndArea, name:'次月到期'}
                    ];
                    // 绘制图表
                    myCharts.setOption(myChartOption);
                })
            },
            // select框查询园区
            selectAllpark (paramObj) {
                this.$ajax({
                    method: 'post',
                    url: "park/list",
                    data: paramObj
                }).then( res => {
                    if (res.status === 200) {
                        this.parkGardenList = res.data.result
                        this.parkGardenList.unshift(
                            {pkCode: '', pkName: '全部园区'}
                        )
                    }
                })
            },
            // 城市三级联动查园区
            changeCityCode (cityCode) {
                this.pkCode = ''
                let paramObj = {
                    entity: {
                        cityCode: cityCode
                    },
                    selectMonth: new Date()
                };
                this.selectAllpark(paramObj)
//                // 选择城市时回显大区
//                if (cityCode == 420100 || cityCode == 510100) {
//                    this.territoryCode = '3'
//                } else if (cityCode == 330100 || cityCode == 310100 || cityCode == 330600 || cityCode == 330200) {
//                    this.territoryCode = '1'
//                } else if (cityCode == 320400 || cityCode == 320500) {
//                    this.territoryCode = '2'
//                }
            },
            // select框查询城市
            selectParkCity (paramObj) {
                this.$ajax({
                    method: 'post',
                    url: "park/selectParkCity",
                    data: paramObj
                }).then( res => {
                    if (res.status === 200) {
                        this.parkCityList = res.data.result
                        this.parkCityList.unshift(
                            {cityCode: '', cityName: '全部城市'}
                        )
                    }
                })
            },
            // 大区三级联动查城市
            changeTerritoryCode (territoryCode) {
                this.cityCode = ''
                this.pkCode = ''
                let paramObj = {
                    entity: {
                        territoryType: territoryCode
                    },
                    selectMonth: new Date()
                };
                this.selectParkCity(paramObj)
                
            },
            // select查询全部大区
            selectParkRegions () {
                let deptList = window.localStorage.getItem("_usedQueryDept");
                this.parkRegionsList = JSON.parse(deptList);
                this.parkRegionsList.unshift(
                    {deptCode: '', deptName: '全部大区'}
                )
            },
            // 删除看板
            deleteBoard(){
                this.$confirm('此操作将永久删除该看板, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let param = {};
                    let entity = {
                        id:this.id,
                    };
                    param.entity = entity;
                    let options = {
                        method: 'POST',
                        data: param,
                        url:"board/delete",
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        vm.$message({
                            showClose: true,
                            message: '删除看板成功',
                            type: 'success'
                        });
                        // this.$router.replace('/back');
                        vm.backParent();
                    }).catch(error => {
                        vm.$alert('页面:去划面积统计看板失败', '系统异常', {
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
            backParent() {
                this.$emit("backParam","1");
            },
        }
    }    
</script>

<style scoped>
    .cusListTable.el-table th {
        background-color: #f5f7fa;
    }

    .sellingDetail {
        /*margin-right: 15px;*/
        /*min-width: 1000px;*/
        /*background-color: white;*/
        overflow-x: hidden;
        /*height: 620px;*/
    }

    .sellingDetail[data-v-30e13d82] {
        overflow-y: hidden;
    }

    /*-------------------------------公共元素  每个div结束后分隔的时候用----------------------------------------*/

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


    /* -----------------------------------头部区域---------------------------------------- */
    .sellingHead {
        min-width: 1200px;
        /*margin-top: 50px;*/
        text-align: left;
        /*padding-left: 20px;*/
        overflow: hidden;
    }
    .sellingHead {
        width: 100%;
    }
    .sellingHead>span {
        margin-right: 10px;
    }

    .el-select {
        width: 14%;
        margin-right: 1%;
    }
    /* ----------------------------------头部区域-------------------------------------------- */

    /* ----------------------------------内容区域-------------------------------------------- */
    /* 内容区域 */
    .sellingMain {
        text-align: left;
        border: 1px solid #ccc;
        /*margin-left: 20px;*/
        margin-top: 20px;
        padding: 20px;
        padding-left: 0;
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
    /* 左边内容echarts区域 */
    #sellingEcharts {
        display: inline-block;
        width: 515px !important;
        height: 414px !important;
    }

    /* 左边内容文字区域 */
    .textLeft {
        position: absolute;
        bottom: -10px;
        width: 100%;
        left: 11%;
    }
    /* .textLeft .textLeftDots {
        display: inline-block;
        text-align: left;
    } */
    p {
        padding: 0;
        margin: 0;
    }

    /* 左边内容圆点 */
    .textRight .dotOne>i, .dotTwo>i, .dotThree>i, .dotFour>i {
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
        background-color: #01cd61;
    }
    .dotFour>i {
        background-color: #f7d856;
    }

    /* ----------------------------------左边内容区域-------------------------------------------- */


    /* ----------------------------------右边内容区域-------------------------------------------- */
    /* 右边内容区域 */
    .mainRight {
        position: relative;
        width: 100%;
    }

    /* 右边内容表格区域 */

    .el-table >>>  th {
        text-align: center;
    }
    .el-table {
        box-shadow: -1px 1px 8px 1px #CCC;
        border: 1px solid #ccc;
        position: absolute;
        right: 0px;
        top: 72px;
        height: 365px;
        overflow: auto;
    }

    .select_style {
        margin-right: 20px;
    }

    /* ----------------------------------右边内容区域-------------------------------------------- */
</style>
