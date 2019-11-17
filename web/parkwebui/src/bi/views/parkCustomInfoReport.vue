<template>
    <div class="not-position-notPaging_root" ref="myRoot">
        <el-form>
            <!-- 悬浮框 -->
            <div class="not-position_header">
                <el-select v-model="pkCode" placeholder="请选择园区" class="select_park" filterable size="small">
                    <el-option v-for="item in pkNames"
                               :key="item.code"
                               :label="item.name"
                               :value="item.code">
                    </el-option>
                </el-select>

                <el-select v-model="trade" placeholder="行业查询" class="select_park" filterable size="small">
                    <el-option v-for="item in tradeList"
                               :key="item.value"
                               :label="item.label"
                               :value="item.label">
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

                <el-button @click="searchCon()" size="small" class="select_button" icon="el-icon-search" type="primary">
                    查询
                </el-button>
                <el-button @click="exportReport()" size="small" class="select_button" icon="el-icon-download"
                           type="success">导出
                </el-button>
            </div>

            <!-- 表格化信息 -->
            <el-table
                    class="costTable"
                    :data="ctList"
                    border
                    :highlight-current-row="true"
                    size="small"
                    :height="tableHeight">

                <el-table-column
                        align="center"
                        label="序号"
                        type="index"
                        width="50px">
                </el-table-column>

                <el-table-column
                        show-overflow-tooltip
                        prop="pkName"
                        header-align="center"
                        min-width="100"
                        label="园区项目">
                </el-table-column>

                <el-table-column
                        show-overflow-tooltip
                        header-align="center"
                        min-width="100"
                        label="联系人">
                    <template slot-scope="scope">{{scope.row.relateName == null ? "---" : scope.row.relateName}}
                    </template>
                </el-table-column>
                <el-table-column
                        show-overflow-tooltip
                        prop="companyName"
                        header-align="center"
                        min-width="100"
                        label="公司名称">
                </el-table-column>
                <el-table-column
                        show-overflow-tooltip
                        prop="tradeName"
                        header-align="center"
                        min-width="100"
                        label="所属行业">
                </el-table-column>
                <el-table-column
                        show-overflow-tooltip
                        prop="totalRentalArea"
                        header-align="center"
                        min-width="100"
                        label="签约面积">
                </el-table-column>
                <el-table-column
                        show-overflow-tooltip
                        prop="rentTotal"
                        header-align="center"
                        min-width="100"
                        label="租金/月">
                </el-table-column>
                <el-table-column
                        show-overflow-tooltip
                        prop="propertyTotal"
                        header-align="center"
                        min-width="100"
                        label="物业费/月">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        label="合同开始">
                    <template slot-scope="scope">{{ scope.row.contractStartTime | timeDate}}</template>
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        label="合同结束">
                    <template slot-scope="scope">{{ scope.row.contractEndTime | timeDate}}</template>
                </el-table-column>
            </el-table>

            <div class="page_down">
                <el-pagination style="margin-top:10px;"
                               background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="start"
                               :page-sizes="pageSizes"
                               :page-size="pageSize"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total">
                </el-pagination>
            </div>
        </el-form>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        components: {
            ElCol,
            ElRow
        },
        props: ['paramsObj'],// 接收父组件数据
        created: function () {
            let vm = this;


            //判断进入是否有默认园区查询
            if (vm.paramsObj && vm.paramsObj.pkCode) {
                vm.pkCode = vm.paramsObj.pkCode;
            }

            // 查询合同列表
            vm.queryContract();

            //查询所有园区
            vm.selectAllPark();


        },
        data() {
            return {
                tableHeight: `calc(100vh - 155px)`,
                dateChangeList: [],//日期
                trade: "",//行业名称
                floatDivHeight: "",

                pkNames: [],// 园区集合
                pkCode: 1,
                empList: [],// 人员下拉选集合，
                ctList: [],
                conOperateHisList: [],// 合同审核记录

                tradeList: [
                    {
                        "value": 0,
                        "label": "全部"
                    },
                    {
                        "value": "1",
                        "label": "食品加工业"
                    }, {
                        "value": "2",
                        "label": "饮料制造业"
                    },
                    {
                        "value": "3",
                        "label": "酒加工"
                    },
                    {
                        "value": "4",
                        "label": "烟草制品业"
                    }, {
                        "value": "5",
                        "label": "纺织业"
                    },
                    {
                        "value": "6",
                        "label": "皮、绒制品业"
                    },
                    {
                        "value": "7",
                        "label": "木材加工"
                    },
                    {
                        "value": "8",
                        "label": "家具制造"
                    },
                    {
                        "value": "9",
                        "label": "纸制品业"
                    }, {
                        "value": "10",
                        "label": "印刷业"
                    },
                    {
                        "value": "11",
                        "label": "文教体育"
                    },
                    {
                        "value": "12",
                        "label": "用品制造"
                    },
                    {
                        "value": "13",
                        "label": "石油加工"
                    },
                    {
                        "value": "14",
                        "label": "化学燃料"
                    }, {
                        "value": "15",
                        "label": "医药制造"
                    },
                    {
                        "value": "16",
                        "label": "橡胶制品"
                    },
                    {
                        "value": "17",
                        "label": "塑料制品"
                    },
                    {
                        "value": "18",
                        "label": "金属制品"
                    },
                    {
                        "value": "19",
                        "label": "制造业"
                    }, {
                        "value": "20",
                        "label": "交通运输"
                    },
                    {
                        "value": "21",
                        "label": "电子设备"
                    }
                ],


                // 分页
                start: 1,
                pageSize: 50,
                pageSizes: [25, 50, 100, 150],
                total: 0,

                // 校验
                rules: {
                    deleteType: [
                        {required: true, message: '请选择合同删除类型', trigger: 'change'},
                    ],
                    deleteCause: [
                        {required: true, message: '请填写合同删除原因', trigger: 'blur'}
                    ],
                },

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
        mounted(){
            let vm = this;
            vm.floatDivHeight = vm.$refs.myRoot.offsetHeight;
            vm.floatDivHeight -= 50;
            window.onresize = () => {
                return (() => {
                    vm.floatDivHeight = vm.$refs.myRoot.offsetHeight;
                    vm.floatDivHeight -= 50;
                })();
            }
        },
        methods: {
            // 查询所有园区
            selectAllPark(){
                let vm = this;
                let obj = {};

                let parkOptions = {
                    method: 'POST',
                    data: obj,
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
                            {code: 1, name: "全部园区"}
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
                            message: '搜索房源失败',
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


            //搜索框
            searchCon(){
                this.handleCurrentChange(1);
            },

            /*---------------------分页start---------------------*/
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.queryContract(1);
            },
            handleCurrentChange(val) {
                this.start = val;
                this.queryContract();
            },
            /*---------------------分页end----------------------*/

            //查询合同列表
            queryContract() {
                let vm = {};
                vm = this;

                let pkCode = "";
                if (vm.pkCode != 1) {
                    pkCode = vm.pkCode;
                }

                let tradeName = "";
                if (vm.trade != '全部') {
                    tradeName = vm.trade;
                }
                let contractDateStart = '';
                if (vm.dateChangeList[0]) {
                    contractDateStart = vm.dateChangeList[0].getTime();
                }
                let contractDateEnd = '';
                if (vm.dateChangeList[1]) {
                    contractDateEnd = vm.dateChangeList[1].getTime();
                }

                let param = {
                    start: (vm.start - 1) * vm.pageSize,
                    contractDateStart: contractDateStart,
                    contractDateEnd: contractDateEnd,
                    pageSize: vm.pageSize,
                    entity: {
                        tradeName: tradeName,
                        pkCode: pkCode,
                        ctStatus: '4',
                    }
                };

                let options = {
                    method: "POST",
                    data: param,
                    url: "contract/queryDetail",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.ctList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：合同列表查询信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            // 文件导出
            exportReport() {
                let vm = {};
                vm = this;

                let pkCode = "";
                if (vm.pkCode != 1) {
                    pkCode = vm.pkCode;
                }

                let tradeName = "";
                if (vm.trade != '全部') {
                    tradeName = vm.trade;
                }
                let startDate = '';
                if (vm.dateChangeList[0]) {
                    startDate = vm.dateChangeList[0].getTime();
                }
                let endDate = '';
                if (vm.dateChangeList[1]) {
                    endDate = vm.dateChangeList[1].getTime();
                }


                // 本地测试打开
//                window.location.href="http://localhost:8380/park/contract/exportparkCustomInfoExcel" +
//                    "?startDate=" + startDate + "&endDate=" + endDate + "&tradeName=" + tradeName + "&pkCode=" + pkCode;

                // 发布线上打开
                window.location.href = "/park/contract/exportparkCustomInfoExcel" +
                    "?startDate=" + startDate + "&endDate=" + endDate + "&tradeName=" + tradeName + "&pkCode=" + pkCode;
            },
        }
    }

</script>

<style scoped>
    .search_buttons span {
        float: left;
        margin-left: 20px;
        color: #409EFF;
    }

    .contract_box_up_left_text span {
        margin-left: 20px;
    }

    .contract_code span {
        font-size: 16px;
        color: #999999;
    }

    /*分页*/
    .page_down {
        text-align: left;
        background-color: rgb(242, 242, 242);
        height: 50px;
        line-height: 50px;
        clear: both;
        display: block;
        margin: 0px auto;
        position: fixed;
        bottom: 0px;
        width: 100%;
        z-index: 10;
    }

    /*园区选择下拉选*/
    .select_park {
        width: 150px;
        line-height: 30px;
        float: left;
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