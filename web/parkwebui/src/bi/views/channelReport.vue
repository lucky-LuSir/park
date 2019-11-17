<template>
    <div class="not-position_root" ref="myRoot">
        <el-form>
            <el-tabs v-model="activeName"  class="navigation-div">
                <el-tab-pane label="渠道获客" name="first">
                    <div class="not-position_header">
                        <el-date-picker class="date-change" size="small"
                                        v-model="dateList" @change="selectDitchCount()"
                                        type="daterange"
                                        margin-top="-5px"
                                        align="right"
                                        unlink-panels
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        :picker-options="pickerOptions2">
                        </el-date-picker>
                        <el-tooltip class="item" effect="dark" placement="top-start">
                            <div style="line-height: 20px;" slot="content">获客数量: 通过各渠道获得的顾客数量<br>顾客复看数量: 仅包含客户来访数量,不包含电话拜访数量</div>
                            <i class="el-icon-diy-wenhao" style="font-size: 15px; cursor: pointer;"></i>
                        </el-tooltip>
                    </div>

                    <el-table :data="channelResultList"
                              border size="small"
                              show-summary
                              style="width: 100%;line-height: 30px;">
                        <el-table-column
                                align="center"
                                min-width="30px"
                                label="序号"
                                type="index">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="ditchName"
                                         label="获客渠道">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="ditchCount"
                                         label="获客数量">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="reWatchCount"
                                         label="客户复看数量">
                        </el-table-column>
                    </el-table>
                </el-tab-pane>

                <el-tab-pane label="职员获客" name="second">
                    <div class="not-position_header">
                        <el-date-picker class="date-change" size="small"
                                        v-model="empDateList" @change="selectEmpCount(1)"
                                        type="daterange"
                                        align="right"
                                        unlink-panels
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        :picker-options="pickerOptions2">
                        </el-date-picker>
                    </div>

                    <el-table :data="empResultList"
                              :height="tableHeight"
                              border size="small"
                              style="width: 100%;line-height: 30px;">
                        <el-table-column
                                align="center"
                                min-width="30px"
                                label="序号"
                                type="index">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="empName"
                                         label="职员姓名">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="empCount"
                                         label="获客数量">
                        </el-table-column>
                    </el-table>

                    <!--分页-->
                    <div class="table-page-div" v-bind:style="{width:floatDivWidth+'px'}">
                        <el-pagination background
                                       @size-change="handleSizeChange"
                                       @current-change="handleCurrentChange"
                                       :current-page.sync="currentPage"
                                       :page-sizes="[8, 16, 32]"
                                       :page-size="pageSize"
                                       layout="total, sizes, prev, pager, next, jumper"
                                       :total="totalCount">
                        </el-pagination>
                    </div>

                </el-tab-pane>
            </el-tabs>


        </el-form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableHeight: `calc(100vh - 231px)`,
                pageSize: 16,// 默认每页条数
                totalCount: 10,// 总条数
                currentPage: 1,// 当前页数

                activeName: 'first', //设置teb默认页
                floatDivWidth: '',

                flagChannelReport: false,// 渠道报表权限

                dateList: [],

                empDateList: [],
                channelResultList: [],// 渠道统计实体
                empResultList: [], //职员统计实体

                // 日期选择组件
                pickerOptions2: {
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
        mounted(){
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        created(){
            let vm = this;

            vm.flagChannelReport = vm.$flagMenuStore.judgeMenu("channelReport");

            if (vm.flagChannelReport) {
                let params = {
                    entity: {},
                    startDate: vm.dateList[0],
                    endDate: vm.dateList[1],
                    pageSize: vm.pageSize,
                    start: '0'
                };
                vm.commonMethod(params);
                vm.empCommonMethod(params);
            } else {
                window.history.back();
            }

            let monthFirst = vm.get_month_first(0);
            let monthLast = vm.get_month_last(0);
            let empDateList = [];
            empDateList.push(monthFirst);
            empDateList.push(monthLast);
            vm.empDateList = empDateList;
            vm.dateList = empDateList;


        },
        methods: {
            handleSizeChange(val) {
                this.pageSize = val;
                this.currentPage = 1;
                this.selectEmpCount(1);
            },
            // 时间选择组件
            selectDitchCount(){
                let vm = this;

                let params = {
                    entity: {},
                    startDate: vm.dateList[0],
                    endDate: vm.dateList[1]
                };
                vm.commonMethod(params);
            },

            handleCurrentChange(val) {
                this.selectEmpCount(val);
            },
            // 时间选择组件
            selectEmpCount(val){
                let vm = this;

                let params = {
                    entity: {},
                    startDate: vm.empDateList[0],
                    endDate: vm.empDateList[1],
                    pageSize: vm.pageSize,
                    start: (val - 1) * (vm.pageSize)
                };

                vm.empCommonMethod(params);
            },
            commonMethod(params){
                let vm = this;

                let options = {
                    method: "POST",
                    data: params,
                    url: "customer/ditchReport"
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.channelResultList = response.data.result.countList;
                    }
                }).catch(error => {

                })
            },

            //总获客
            empCommonMethod(params){
                let vm = this;

                let options = {
                    method: "POST",
                    data: params,
                    url: "customer/empCount"
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.empResultList = response.data.result.data;
                        vm.totalCount = response.data.result.totalCount;
                    }
                }).catch(error => {

                })
            },
        }
    }
</script>

<style scoped>
    /***********************************************************/

    /*日期选择*/
    .navigation-date-div{
        /*position: fixed;
        z-index: 200;*/
        height: 47px;
    }

    .el-range-editor.el-input__inner {
        align-items: baseline;
    }

    /*日期选择器*/
    .date-change{
        float: left;
        margin-left: 20px;
    }
    /*分页*/
    .table-page-div {
        height: 40px;
        padding-top: 10px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;

        position: fixed;
        z-index: 200;
    }

    .item{
        float:left;
        margin-left: 9px;
    }
</style>



