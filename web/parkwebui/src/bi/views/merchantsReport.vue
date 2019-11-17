<template>
    <div class="not-position_root">
        <div class="not-position_header">
            <!-- 部门筛选组件 -->
            <dept-selector @sendDeptCode="getDeptCode" class="navigation_select"></dept-selector>

            <el-select v-model="pkCode" filterable placeholder="园区筛选" size="small" class="navigation_select" clearable>
                <el-option v-for="(item, index) in parkList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode">
                </el-option>
            </el-select>

            <el-date-picker class="navigation_date_select" v-model="dateChangeList" type="daterange" align="right" size="small" unlink-panels :clearable="false" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions">
            </el-date-picker>
            <el-button @click="selectReport()" size="small" class="select_button" icon="el-icon-search" type="primary">查询</el-button>
            <el-button @click="exportExcel()" size="small" class="select_button" icon="el-icon-download" type="success">导出</el-button>
        </div>

        <el-table :data="merchantsList" border size="small" :height="tableHeight" style="width: 100%; line-height: 30px; border-top: white;">
            <el-table-column align="center" label="序号" type="index">
            </el-table-column>
            <el-table-column align="center" label="姓名" prop="empName">
            </el-table-column>

            <el-table-column label="招商日常" align="center">
                <el-table-column align="center" label="横幅" prop="banner">
                </el-table-column>
                <el-table-column align="center" label="小广告" prop="leaflets">
                </el-table-column>
                <el-table-column align="center" label="朋友圈" prop="friendsShare">
                </el-table-column>
            </el-table-column>

            <el-table-column label="客户跟进" align="center">
                <el-table-column align="center" label="电话跟进" prop="customerPhoneFollowup">
                </el-table-column>
                <el-table-column align="center" label="客户带看" prop="customerVisityFollowup">
                </el-table-column>
            </el-table-column>

            <el-table-column label="获客渠道" align="center">
                <el-table-column align="center" label="主动获客" prop="initiative">
                </el-table-column>
                <el-table-column align="center" label="中介获客" prop="agent">
                </el-table-column>
                <el-table-column align="center" label="库房无忧获客" prop="kuFangWuYou">
                </el-table-column>
                <el-table-column align="center" label="获客数" prop="cusCount">
                </el-table-column>
            </el-table-column>

            <el-table-column align="center" label="所有谈判中" prop="cusNegotiateCount">
            </el-table-column>
            <el-table-column align="center" label="入驻客户" prop="cusEntryCount">
            </el-table-column>
            <el-table-column align="center" label="去化面积" prop="signAreaMonth">
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
                tableHeight: this.$store.state.tableHeight,
                dateChangeList: [], // 日期选择组件绑定数据
                parkList: [], // 园区集合
                merchantsList: [], // 报表数据集合
                // 导航栏查询参数
                deptCode: '', // 部门编码
                pkCode: '', // 园区编码
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
        created() {
            let vm = this;

            vm.selectAllPark(); // 查询所有园区
        },
        methods: {
            // 查询按钮
            selectReport() {
                let vm = this;

                if (vm.dateChangeList.length === 0) {
                    vm.$alert('页面：请选择查询日期！', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                    return;
                }

                let params = {
                    entity: {
                        deptCode: vm.deptCode,
                        pkCode: vm.pkCode,
                        startDate: vm.dateChangeList[0],
                        endDate: vm.dateChangeList[1],
                    },
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: "merchantsBi/selectMerchants",
                };
                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.merchantsList = response.data.result;
                    }
                }).catch(error => {
                    vm.$alert('页面：招商人员统计数据查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
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

            // 报表导出
            exportExcel() {
                let vm = this;

                if (vm.merchantsList.length <= 0) {
                    vm.$alert('页面：无导出数据', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                    return;
                }

                // 查询参数
                let startDate = vm.dateChangeList[0].getTime();
                let endDate = vm.dateChangeList[1].getTime();
                let deptCode = vm.deptCode;
                let pkCode = vm.pkCode;

                // 线下测试
                window.open("http://localhost:8380/park/merchantsBi/exportMerchantsBiExcel" +
                    "?startDate=" + startDate + "&endDate=" + endDate + "&deptCode=" + deptCode + "&pkCode=" + pkCode);

                // 线上导出
                //                window.location.href="/park/merchantsBi/exportMerchantsBiExcel" +
                //                    "?startDate=" + startDate + "&endDate=" + endDate + "&deptCode=" + deptCode + "&pkCode=" + pkCode;
            },
        }
    }
</script>

<style scoped>
    /*导航栏*/
    .navigation {
        margin-top: -52px;
        margin-bottom: 10px;
        position: fixed;
        z-index: 200;
    }

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

    /*查询按钮*/
    .select_button {
        float: left;
        margin-left: 20px;
    }

    /*分页*/
    .table-page-div {
        height: 50px;
        padding-top: 10px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;

        position: fixed;
        z-index: 200;
    }
</style>