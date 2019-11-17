<template>
    <div class="not-position_root">
        <div class="not-position_header">
            <el-select v-model="territoryType" placeholder="请选择大区" class="selected" filterable size="small"
                       @change="changeRegion()">
                <el-option v-for="item in parkRegionsList"
                           :key="item.deptCode"
                           :label="item.deptName"
                           :value="item.deptCode">
                </el-option>
            </el-select>
            <el-select v-model="cityCode" placeholder="请选择城市" class="selected" filterable size="small"
                       @focus="selectParkCity()" @change="changeCity()">
                <el-option v-for="item in parkCityList"
                           :key="item.code"
                           :label="item.name"
                           :value="item.code">
                </el-option>
            </el-select>
            <el-select v-model="pkCode" placeholder="请选择园区" class="selected" filterable size="small"
                       @focus="selectPark()">
                <el-option v-for="item in pkNames"
                           :key="item.code"
                           :label="item.name"
                           :value="item.code">
                </el-option>
            </el-select>

            <el-date-picker
                    style="width: 15%; float: left; margin-left: 20px;"
                    size="small"
                    v-model="selectMonth"
                    type="month"
                    placeholder="选择月">
            </el-date-picker>

            <el-button style="float: left; margin-left: 20px; margin-top: 2px;"
                       size="small" type="primary" @click="selectData()" icon="el-icon-search">查询
            </el-button>

            <el-button style="float: left; margin-left: 20px; margin-top: 2px;"
                       size="small" type="success" @click="exportReport()" icon="el-icon-download">导出
            </el-button>
        </div>
        <el-table
                :data="grossList"
                border
                size="small"
                :height="tableHeight"
                :summary-method="getSummaries"
                show-summary>
            <el-table-column
                    align="center"
                    label="序号"
                    type="index"
                    width="50px">
            </el-table-column>
            <el-table-column
                    label="经营开始日"
                    align="center"
                    width="110">
                <template slot-scope="scope">
                    <span v-if="scope.row.businessStartDay == null">---</span>
                    <span v-else>{{ scope.row.businessStartDay | timeDate }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="经营结束日"
                    align="center"
                    width="110">
                <template slot-scope="scope">
                    <span v-if="scope.row.businessEndDay == null">---</span>
                    <span v-else>{{ scope.row.businessEndDay | timeDate }}</span></template>
            </el-table-column>
            <el-table-column
                    show-overflow-tooltip
                    prop="pkName"
                    label="项目名称"
                    align="center"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="acreage"
                    label="项目承租面积（㎡）"
                    align="center"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="investArea"
                    label="项目计租面积（㎡）"
                    align="center"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="rentableArea"
                    label="空置计租面积（㎡）"
                    align="center"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="rentInvestArea"
                    label="去化计租面积（㎡）"
                    align="center"
                    width="130">
            </el-table-column>
            <!--<el-table-column-->
            <!--prop="investArea"-->
            <!--label="公摊后去化面积/㎡"-->
            <!--align="center"-->
            <!--width="130">-->
            <!--<template slot-scope="scope">{{ scope.row.investArea == null ? 0 : scope.row.investArea }}</template>-->
            <!--</el-table-column>-->
            <!--<el-table-column-->
            <!--label="公摊比"-->
            <!--align="center"-->
            <!--width="130">-->
            <!--<template slot-scope="scope">{{ scope.row.areaRate == null ? "0%" : scope.row.areaRate + "%" }}</template>-->
            <!--&lt;!&ndash;<template slot-scope="scope">{{ (scope.row.investArea - scope.row.rentArea)/scope.row.rentArea }}</template>&ndash;&gt;-->
            <!--</el-table-column>-->
            <el-table-column
                    label="去化率"
                    align="center"
                    width="130">
                <template slot-scope="scope">{{ scope.row.rate + "%" }}</template>
            </el-table-column>
            <el-table-column
                    prop="rentIncome"
                    label="租金收入（元）"
                    align="center"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="projectPrice"
                    label="项目承租金额（元）"
                    align="center"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="investPrice"
                    label="出租承租金额（元）"
                    align="center"
                    width="130">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="项目毛利率"
                    align="center"
                    min-width="130">
                <template slot-scope="scope">{{ scope.row.projectGross == null ? "0%" : scope.row.projectGross + "%"
                    }}
                </template>
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="出租毛利率"
                    align="center"
                    min-width="130">
                <template slot-scope="scope">{{ scope.row.investGross == null ? "0%" : scope.row.investGross + "%" }}
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {ElRow},
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,
                flagGrossList: false,// 毛利率报表权限
                grossList: [],// 毛利率
                pkNames: [],// 园区集合
                parkCityList: [],// 园区城市集合
                pkObj: {},
                cityObj: {},
                pkCode: "",// 园区code
                cityCode: "",// 城市code
                territoryType: "",//大区code
                parkRegionsList: [],//大区列表
                selectType: "1",//查询类型
                selectMonth: "",//选择的月
            }
        },
        created(){
            let vm = this;

            vm.flagGrossList = vm.$flagMenuStore.judgeMenu("grossList");// 报表权限

            if (vm.flagGrossList) {
                vm.selectPark();// 查询园区
                vm.selectParkCity();// 查询城市
                vm.selectParkRegions();//查询大区
            }

            vm.selectMonth = new Date();
        },
        methods: {
            getSummaries(param){
                const {columns, data} = param;
                const sums = [];

                columns.forEach((column, index) => {
                    if (index === 0) {
                        sums[index] = '合计';
                        return;
                    }
                    const values = data.map(item => Number(item[column.property]));
                    if (index != 1) {
                        if (!values.every(value => isNaN(value))) {
                            sums[index] = values.reduce((prev, curr) => {
                                const value = Number(curr);
                                if (value) {
                                    return prev + curr;
                                } else {
                                    return prev;
                                }
                            }, 0);
                            let sum = sums[index];
                            sum = sum.toFixed(2) + ' 元';
                            sums[index] = sum;
                        }
                    }
                });
                return sums;
            },
            // 查询所有园区
            selectPark(){
                let vm = this;
                let param = {};
                let entity = {
                    cityCode: vm.cityCode,
                    //  pkCode:vm.pkCode,
                    territoryType: vm.territoryType,
                };
                param.entity = entity;
                let parkOptions = {
                    method: 'POST',
                    data: param,
                    url: "park/list",
                };
                this.$ajax(
                    parkOptions
                ).then(function (response) {
                    if (!response.data.isException) {
                        let result = response.data.result;

                        let pkNameList = [];
                        let pkObj = [];

                        pkNameList.push(
                            {code: '', name: "全部园区"}
                        );
                        pkObj[1] = "全部园区";

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
                            message: '查询房源失败',
                            type: 'error'
                        });
                    }
                }).catch(function (error) {
                    vm.$alert('页面：园区列表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 查询城市
            selectParkCity(){
                let vm = this;

                let paramObj = {
                    entity: {
                        territoryType: vm.territoryType,
                    },
                };

                let options = {
                    method: "POST",
                    data: paramObj,
                    url: "park/selectParkCity",
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (!response.data.isException) {
//                        vm.parkCityList = response.data.result;
//
//                        let aObj = {cityCode: "", cityName: "全部"};
//                        vm.parkCityList.unshift(aObj);

                        let result = response.data.result;
                        let cityNameList = [];
                        let cityObj = [];

                        cityNameList.push(
                            {code: '', name: "全部城市"}
                        );
//                        cityObj[1] = "全部";

                        for (let i = 0; i < result.length; i++) {
                            let code = result[i].cityCode;
                            let name = result[i].cityName;
                            cityNameList.push(
                                {code: code, name: name}
                            );
                            cityObj[code] = name;
                        }
                        vm.parkCityList = cityNameList;
                        vm.cityObj = cityObj;
                    }
                }).catch(error => {
                    vm.$alert('页面：园区所属城市查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },

            // select查询全部大区
            selectParkRegions () {
                let deptList = window.localStorage.getItem("_usedQueryDept");
                this.parkRegionsList = JSON.parse(deptList);
                this.parkRegionsList.unshift(
                    {deptCode: '', deptName: '全部大区'}
                )
            },

            changeCity(){
                let pkNameList = [];
                pkNameList.push(
                    {code: '', name: "全部园区"}
                );
                this.pkCode = '';
                this.pkNames = pkNameList;
            },

            changeRegion(){
                let cityNameList = [];
                cityNameList.push(
                    {code: 1, name: "全部城市"}
                );
                this.parkCityList = cityNameList;
                this.cityCode = '';
                this.changeCity();
            },

            selectData(){
                let vm = this;

                let param = {
                    cityCode: vm.cityCode,
                    territoryType: vm.territoryType,
                    pkCode: vm.pkCode,
                    entity: {},
                    selectMonth: vm.selectMonth,
                };

                let option = {
                    method: "POST",
                    data: param,
                    url: "grossBi/parkMonthGross"
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if (response) {
                        vm.grossList = response.data.result;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查询毛利率信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            //文件导出
            exportReport() {

                let vm = this;

                let url = "";
                let pkCode = vm.pkCode;
                let cityCode = vm.cityCode;
                let territoryType = vm.territoryType;

                if (vm.grossList.length <= 0) {
                    vm.$alert('页面：无导出数据', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }
                //选择的月份
                let selectMonth = vm.selectMonth.getTime();


                // 线下测试
                window.location.href = "http://localhost:8380/park/grossBi/exportGrossBiExcel" +
                    "?pkCode=" + pkCode + "&cityCode=" + cityCode + "&selectMonth=" + selectMonth + "&territoryType=" + territoryType;

                // 线上导出
//                window.location.href="/park/grossBi/exportGrossBiExcel" +
//                    "?pkCode="+pkCode+"&cityCode="+cityCode+"&selectMonth="+selectMonth+"&territoryType="+territoryType;
            },
        }
    }
</script>

<style scoped>
    .root {
        background: rgb(242, 242, 242);
        width: 100%;
    }

    .navigation_div {
        float: left;
        margin-top: -45px;

        position: fixed;
        z-index: 200;
    }

    .selected {
        float: left;
        margin-left: 20px;
    }
</style>



