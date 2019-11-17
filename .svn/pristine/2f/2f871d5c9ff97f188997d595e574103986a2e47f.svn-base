<template>
    <div class="not-position_root" ref="myRoot">

        <div class="not-position_header">
            <!-- 部门筛选组件 -->
            <dept-selector @sendDeptCode="getDeptCode" class="navigation_select"></dept-selector>
            <el-select v-model="pkCode" filterable placeholder="园区筛选" size="small" class="navigation_select" clearable>
                <el-option v-for="(item) in parkList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode">
                </el-option>
            </el-select>

            <el-date-picker class="navigation_date_select" v-model="dateChangeList" type="daterange" align="right"
                            size="small" unlink-panels :clearable="false" range-separator="至" start-placeholder="开始日期"
                            end-placeholder="结束日期" :picker-options="pickerOptions">
            </el-date-picker>

            <el-button @click="selectReport()" size="small" class="select_button" icon="el-icon-search" type="primary">
                查询
            </el-button>

            <el-button @click="exportReport()" size="small" class="select_button" icon="el-icon-download"
                       type="success">导出
            </el-button>
        </div>

        <el-table class="waterReceivableReport_table"
                  :data="waterReceivableList"
                  border size="small"
                  :height="tableHeight"
                  :summary-method="getSummaries"
                  show-summary>
            <el-table-column align="center" label="序号" type="index">
            </el-table-column>
            <el-table-column align="center" label="合同编号" width="120" prop="ctName">
            </el-table-column>
            <el-table-column align="center" label="合同状态" prop="ctStatusName">
            </el-table-column>
            <el-table-column align="center" label="支付状态">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.payStatus == 1" type="info">{{ scope.row.payStatusName }}</el-tag>
                    <el-tag v-if="scope.row.payStatus == 2" type="success">{{ scope.row.payStatusName }}</el-tag>
                    <el-tag v-if="scope.row.payStatus == 3" type="danger">{{ scope.row.payStatusName }}</el-tag>
                    <el-tag v-if="scope.row.payStatus == 4" type="warning">{{ scope.row.payStatusName }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column align="center" label="项目名称" prop="pkName">
            </el-table-column>
            <el-table-column align="center" label="公司名称" prop="companyName">
            </el-table-column>
            <el-table-column align="center" label="联系人" prop="relateName">
            </el-table-column>
            <el-table-column align="center" label="合同开始日期" prop="leaflets">
                <template slot-scope="scope">{{ scope.row.contractStartTime | timeDate }}</template>
            </el-table-column>
            <el-table-column align="center" label="合同结束日期" prop="leaflets">
                <template slot-scope="scope">{{ scope.row.contractEndTime | timeDate }}</template>
            </el-table-column>
            <el-table-column align="center" label="上次抄表时间" prop="leaflets">
                <template slot-scope="scope">{{ scope.row.lastBillingTime | timeDate }}</template>
            </el-table-column>
            <el-table-column align="center" label="本次抄表时间">
                <template slot-scope="scope">{{ scope.row.currentBillingTime | timeDate }}</template>
            </el-table-column>
            <el-table-column align="center" label="缴费截至日期" prop="customerFollowup">
                <template slot-scope="scope">{{ scope.row.receivingDate | timeDate }}</template>
            </el-table-column>
            <el-table-column align="center" label="水费" prop="receivablePrice">
            </el-table-column>
            <el-table-column align="center" label="水费是否含税" prop="hasTax">
                <template slot-scope="scope">{{ scope.row.hasTax == true ? "是" : "否" }}</template>
            </el-table-column>
            <el-table-column align="center" label="税点">
                <template slot-scope="scope">
                    <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                    <span v-if="!scope.row.hasTax">---</span>
                </template>
            </el-table-column>
            <el-table-column align="center" label="本期应收" prop="totalPrice">
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
                tableHeight: `calc(100vh - 147px)`,
                waterReceivableList: [],// 租金集合
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
                num: 0,
            }
        },
        props:['paramsObj'],
        created() {
            let vm = this;

            vm.selectAllPark();// 查询所有园区
            //有传入的默认时间
            if(vm.paramsObj && vm.paramsObj.receivingDate) {
                vm.dateChangeList[0] = vm.paramsObj.receivingDate;
                vm.dateChangeList[1] = vm.paramsObj.receivingDate;

                vm.selectReport();
            }

        },
        methods: {
            getSummaries(param) {
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
            selectReport() {

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
                    contractorDeptCode: vm.deptCode,
                    pkCode: vm.pkCode,
                    entity: {
                        startDate: vm.dateChangeList[0],
                        endDate: vm.dateChangeList[1],
                    }
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: "waterReceivable/select",
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.waterReceivableList = response.data.result;
                    }
                }).catch(error => {
                    vm.$alert('页面：应收租金报表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });

                var table__body = document.querySelector(".waterReceivableReport_table .el-table__body-wrapper.is-scrolling-left");
                this.table_body(table__body, this.num);
                this.num++;
            },

            // 获取组件传递的deptCode
            getDeptCode(data) {
                let vm = this;
                vm.deptCode = data;
            },

            // 查询所有园区
            selectAllPark() {
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

                        if(vm.paramsObj && vm.paramsObj.pkCode) {
                            vm.pkCode = vm.paramsObj.pkCode;
                        }
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


            //导出
            exportReport() {
                let vm = this;

                if (!(vm.dateChangeList[0] && vm.dateChangeList[1])) {
                    vm.$alert('页面：请选择日期！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                let startDate = vm.dateChangeList[0].getTime();
                let endDate = vm.dateChangeList[1].getTime();
                let contractorDeptCode = vm.deptCode;
                let pkCode = vm.pkCode;

                // 本地测试打开
                //                window.location.href="http://localhost:8380/park/waterReceivable/exportWaterReceivableBiExcel" +
                //                    "?startDate=" + startDate + "&endDate=" + endDate + "&contractorDeptCode=" + contractorDeptCode + "&pkCode=" + pkCode;

                // 发布线上打开
                window.location.href = "/park/waterReceivable/exportWaterReceivableBiExcel" +
                    "?startDate=" + startDate + "&endDate=" + endDate + "&contractorDeptCode=" + contractorDeptCode + "&pkCode=" + pkCode;
            }
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



