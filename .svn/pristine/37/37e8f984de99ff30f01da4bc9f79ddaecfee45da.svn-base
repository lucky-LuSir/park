<template>
    <div class="not-position-notPaging_root" ref="myRoot">

        <div class="not-position_header">
            <!-- 部门筛选组件 -->
            <dept-selector @sendDeptCode="getDeptCode" class="navigation_select"></dept-selector>

            <el-select v-model="pkCode" filterable placeholder="园区筛选" size="small" class="navigation_select" clearable>
                <el-option v-for="(item, index) in parkList"
                           :key="item.pkCode"
                           :label="item.pkName"
                           :value="item.pkCode">
                </el-option>
            </el-select>
            <el-date-picker class="navigation_date_select"
                            v-model="dateChangeList"
                            type="daterange"
                            align="right"
                            size="small"
                            unlink-panels
                            :clearable="false"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            :picker-options="pickerOptions">
            </el-date-picker>
            <el-button @click="selectReport()" size="small" class="select_button" icon="el-icon-search" type="primary">
                查询
            </el-button>
            <el-button @click="exportReport()" size="small" class="select_button" icon="el-icon-download"
                       type="success">导出
            </el-button>
        </div>

        <el-table :data="firstReceivableList" border size="small" :height="tableHeight"
                  :summary-method="getSummaries" show-summary highlight-current-row>
            <el-table-column align="center" fixed
                             label="序号"
                             type="index">
            </el-table-column>
            <el-table-column align="center" fixed
                             label="合同编号"
                             width="120"
                             prop="ctName">
            </el-table-column>
            <el-table-column align="center" fixed
                             label="合同状态"
                             prop="ctStatusName">
            </el-table-column>
            <el-table-column align="center" fixed
                             label="支付状态">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.payStatus == 1" type="info">{{ scope.row.payStatusName }}</el-tag>
                    <el-tag v-if="scope.row.payStatus == 2" type="success">{{ scope.row.payStatusName }}</el-tag>
                    <el-tag v-if="scope.row.payStatus == 3" type="danger">{{ scope.row.payStatusName }}</el-tag>
                    <el-tag v-if="scope.row.payStatus == 4" type="warning">{{ scope.row.payStatusName }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column align="center" fixed min-width="110"
                             label="项目名称"
                             prop="pkName">
            </el-table-column>
            <el-table-column align="center" fixed min-width="110"
                             label="公司名称"
                             prop="companyName">
            </el-table-column>
            <el-table-column align="center" fixed
                             label="联系人"
                             prop="relateName">
            </el-table-column>
            <el-table-column align="center" width="100"
                             label="合同开始日期"
                             prop="leaflets">
                <template slot-scope="scope">{{ scope.row.contractStartTime | timeDate }}</template>
            </el-table-column>
            <el-table-column align="center" width="100"
                             label="合同结束日期"
                             prop="leaflets">
                <template slot-scope="scope">{{ scope.row.contractEndTime | timeDate }}</template>
            </el-table-column>
            <el-table-column align="center" width="100"
                             label="费用开始日期"
                             prop="leaflets">
                <template slot-scope="scope">{{ scope.row.rentStartTime | timeDate }}</template>
            </el-table-column>
            <el-table-column align="center" width="100"
                             label="费用结束日期">
                <template slot-scope="scope">{{ scope.row.rentEndTime | timeDate }}</template>
            </el-table-column>
            <el-table-column align="center" width="100"
                             label="缴费截至日期"
                             prop="customerFollowup">
                <template slot-scope="scope">{{ scope.row.receivingDate | timeDate }}</template>
            </el-table-column>
            <el-table-column align="center" min-width="110"
                             label="租金"
                             prop="receivableRent">
            </el-table-column>
            <el-table-column align="center"
                             label="租金是否含税"
                             prop="hasTax">
                <template slot-scope="scope">{{ scope.row.hasTax == true ? "是" : "否" }}</template>
            </el-table-column>
            <el-table-column align="center"
                             label="租金税点">
                <template slot-scope="scope">
                    <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                    <span v-if="!scope.row.hasTax">---</span>
                </template>
            </el-table-column>
            <el-table-column align="center" min-width="110"
                             label="物业费"
                             prop="propertyPrice">
            </el-table-column>
            <el-table-column align="center"
                             label="物业费是否含税"
                             width="80"
                             prop="proHasTax">
                <template slot-scope="scope">{{ scope.row.proHasTax == true ? "是" : "否" }}</template>
            </el-table-column>
            <el-table-column align="center"
                             label="物业税点">
                <template slot-scope="scope">
                    <span v-if="scope.row.proHasTax">{{ scope.row.proTaxPointName }}</span>
                    <span v-if="!scope.row.proHasTax">---</span>
                </template>
            </el-table-column>
            <el-table-column align="center" min-width="110"
                             label="电费押金"
                             prop="eleDeposit">
            </el-table-column>
            <el-table-column align="center"
                             label="水费押金"
                             prop="waterDeposit">
            </el-table-column>
            <el-table-column align="center" min-width="110"
                             label="保险费"
                             prop="fpayInsurancePrice">
            </el-table-column>
            <el-table-column align="center" min-width="110"
                             label="工程押金"
                             prop="fpayProjectDeposit">
            </el-table-column>
            <el-table-column align="center" min-width="110"
                             label="保证金"
                             prop="fpayBond">
            </el-table-column>
            <el-table-column align="center" min-width="110"
                             label="本期应收"
                             prop="totalPrice">
            </el-table-column>
            <el-table-column header-align="center" fixed="right" label="操作" width="80">
                <template slot-scope="scope">
                    <el-button v-if="scope.row.payStatus != '2'"
                               @click="costDeatil(scope.row.ctCode, scope.row.ctStatus)" type="text">缴费
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import deptSelector from '@/common/components/deptSelector.vue'

    export default {
        components: {
            deptSelector,
        },
        data() {
            return {
                tableHeight: `calc(100vh - 155px)`,
                firstReceivableList: [],// 租金集合
                selectMonth: '',// 选择的月
                dateChangeList: [],// 选择的开始日期和结束日期
                deptList: [],// 部门集合
                parkList: [],// 园区集合

                // 导航栏查询参数
                deptCode: '',// 部门编码
                pkCode: '',// 园区编码

                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
            }
        },
        created(){
            let vm = this;

            vm.selectAllPark();// 查询所有园区
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
                    if (index != 1 && index != 13 && index != 14 && index != 16 && index != 17) {
                        if (!values.every(value => isNaN(value))) {
                            sums[index] = values.reduce((prev, curr) => {
                                const value = Number(curr);
                                if (!isNaN(value)) {
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
            // 查询按钮
            selectReport(){
                let vm = this;

                if (!vm.dateChangeList) {
                    vm.$alert('页面：请选择查询日期！', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                let params = {
                    entity: {
                        contractorDeptCode: vm.deptCode,
                        pkCode: vm.pkCode,
                        startDate: vm.dateChangeList[0],
                        endDate: vm.dateChangeList[1],
                    }
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: "firstReceivable/selectFirstReceivableInfo",
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.firstReceivableList = response.data.result;
                    }
                }).catch(error => {
                    vm.$alert('页面：应收租金报表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },

            // 获取组件传递的deptCode
            getDeptCode(data) {
                let vm = this;
                vm.deptCode = data;
            },

            // 查询所有园区
            selectAllPark(){
                let vm = this;

                let options = {
                    method: 'POST',
                    data: {},
                    url: 'park/list'
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.parkList = response.data.result;
//                        let aObj = {pkCode: '', pkName: '所有园区'};
//                        vm.parkList.unshift(aObj);
                    }
                }).catch(error => {
                    vm.$message({
                        showClose: true,
                        message: '查询园区失败',
                        type: 'error'
                    });
                })
            },

            // 费用详情
            costDeatil(ctCode, ctStatus) {
                let data = {
                    flag: "add",
                    menuName: "应收详情",
                    item: "costDetail",
                    params: {
                        ctCode: ctCode,
                        ctStatus: ctStatus
                    }
                };
                this.$emit("backParam", data);
            },

            // 导出Excel
            exportReport(){
                let vm = this;

                if (vm.firstReceivableList.length <= 0) {
                    vm.$alert('页面：无导出数据', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                // 查询参数
                let startDate = vm.dateChangeList[0].getTime();
                let endDate = vm.dateChangeList[1].getTime();
                let contractorDeptCode = vm.deptCode;
                let pkCode = vm.pkCode;


                // 线下测试
//               window.location.href="http://localhost:8380/park/firstReceivable/exportFirstBiExcel" +
//                   "?startDate=" + startDate + "&endDate=" + endDate + "&contractorDeptCode=" + contractorDeptCode + "&pkCode=" + pkCode;

                // 线上导出
                window.location.href = "/park/firstReceivable/exportFirstBiExcel" +
                    "?startDate=" + startDate + "&endDate=" + endDate + "&contractorDeptCode=" + contractorDeptCode + "&pkCode=" + pkCode;
            },
        }
    }
</script>

<style scoped>
    /*导航栏筛选条件*/
    .navigation_select {
        float: left;
        width: 150px;
        line-height: 30px;
        margin-left: 20px;
    }

    /*导航栏日期选择*/
    .navigation_date_select {
        float: left;
        width: 250px;
        line-height: 30px;
        margin-left: 20px;
    }

    .select_button {
        float: left;
        margin-left: 20px;
    }

</style>



