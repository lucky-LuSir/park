<template>
    <div class="not-position-notPaging_root" ref="myRoot">

        <div class="not-position_header">
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

            <el-button @click="selectReport()" size="small" class="select_button" icon="el-icon-search" type="primary">查询</el-button>
            <el-button @click="exportReport()" size="small" class="select_button" icon="el-icon-download" type="success">导出</el-button>
        </div>

        <template>
            <el-table :data="parkFollowCountList" border size="small" :height="tableHeight"
                      show-summary
                      :summary-method="getSummaries">
                <el-table-column align="center"
                                 label="序号"
                                 type="index">
                </el-table-column>
                <el-table-column align="center"
                                 label="园区名称"
                                 width="200"
                                 prop="pkName">
                </el-table-column>
                <el-table-column align="center"
                                 label="一次带看客户数"
                                 prop="firstW">
                </el-table-column>
                <el-table-column align="center"
                                 label="两次带看客户数"
                                 prop="secondW">
                </el-table-column>
                <el-table-column align="center"
                                 label="三次带看客户数"
                                 prop="thirdW">
                </el-table-column>
                <el-table-column align="center"
                                 label="四次带看客户数"
                                 prop="fourthW">
                </el-table-column>
                <el-table-column align="center"
                                 label="五次及以上带看客户数"
                                     prop="moreW">
                </el-table-column>

            </el-table>
        </template>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableHeight: `calc(100vh - 155px)`,
                parkFollowCountList: [],// 租金集合
                selectMonth: '',// 选择的月
                dateChangeList: [],// 选择的开始日期和结束日期

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
        },
        methods: {

            // 查询按钮
            selectReport(){
                let vm = this;

                if (!(vm.dateChangeList[0] &&vm.dateChangeList[1])) {
                    vm.$alert('页面：请选择日期！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                let params = {
                    startDate: vm.dateChangeList[0],
                    endDate: vm.dateChangeList[1],
                    entity: {
                    }
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: "parkFollowCount/listCountInfo",
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.parkFollowCountList = response.data.result;
                    }
                }).catch(error => {
                    vm.$alert('页面：报表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },

            // 文件导出
            exportReport() {
                let vm = this;

                if (!(vm.dateChangeList[0] &&vm.dateChangeList[1])) {
                    vm.$alert('页面：请选择日期！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                let startDate = vm.dateChangeList[0].getTime();
                let endDate = vm.dateChangeList[1].getTime();

                // 本地测试打开
//                window.location.href="http://localhost:8380/park/parkFollowCount/exportParkFollowCountExcel" +
//                    "?startDate=" + startDate + "&endDate=" + endDate ;

                // 发布线上打开
                       window.location.href="/park/parkFollowCount/exportParkFollowCountExcel" +
                          "?startDate=" + startDate + "&endDate=" + endDate ;
            },

            getSummaries(param) {
                const { columns, data } = param;
                const sums = [];
                columns.forEach((column, index) => {
                    if (index === 0) {
                        sums[index] = '合计';
                        return;
                    }
                    const values = data.map(item => Number(item[column.property]));
                    if (!values.every(value => isNaN(value))) {
                        sums[index] = values.reduce((prev, curr) => {
                            const value = Number(curr);
                            if (!isNaN(value)) {
                                return prev + curr;
                            } else {
                                return prev;
                            }
                        }, 0);
                        sums[index] += ' 次';
                    }
                });

                return sums;
            }

        }
    }
</script>

<style scoped>
    /*导航栏筛选条件*/
    .navigation_select{
        float: left;
        width: 150px;
        line-height: 30px;
        margin-left: 20px;
    }

    /*导航栏日期选择*/
    .navigation_date_select{
        float: left;
        width: 250px;
        line-height: 30px;
        margin-left: 20px;
    }
    .select_button{
        float: left;
        margin-left: 20px;
    }

</style>



