<template>
    <div class="not-position-notPaging_root " ref="myRoot">
        <el-form>
            <!-- 悬浮框 -->
            <div class="not-position_header">
                <div class="search_buttons">
                    <el-select @change="searchCon" v-model="expireMonth" placeholder="过期日期" class="selectMonth" filterable size="small">
                        <el-option v-for="item in expireMonthList" :key="item.code" :label="item.name" :value="item.code">
                        </el-option>
                    </el-select>
                    <el-radio-group v-model="ifMe" @change="searchCon" style="margin-left: 20px ;border:1px solid rgb(205, 210, 218) ;padding: 6px; padding-top: 7px;border-radius: 5px">
                        <el-radio label="1">我的</el-radio>
                        <el-radio label="2">全部</el-radio>
                    </el-radio-group>
                </div>
            </div>
            <!-- 表格化信息 -->
            <div class="contract_box_top">
                <el-table class="costTable" :height="tableHeight" :data="ctList" border :highlight-current-row="true" size="small">

                    <el-table-column align="center" label="序号" type="index" width="50px">
                    </el-table-column>
                    <el-table-column prop="ctName" header-align="center" min-width="120" label="合同编号">
                    </el-table-column>
                    <el-table-column prop="ctStatusName" header-align="center" min-width="80" label="合同状态">
                    </el-table-column>
                    <el-table-column show-overflow-tooltip header-align="center" min-width="100" label="联系人">
                        <template slot-scope="scope">{{scope.row.relateName == null ? "---" : scope.row.relateName}}</template>
                    </el-table-column>
                    <el-table-column show-overflow-tooltip header-align="center" min-width="150" label="公司名称">
                        <template slot-scope="scope">{{scope.row.companyName == null ? "---" : scope.row.companyName}}</template>
                    </el-table-column>
                    <el-table-column header-align="center" min-width="100" label="签约日期">
                        <template slot-scope="scope">{{ scope.row.contractDate | timeDate}}</template>
                    </el-table-column>
                    <el-table-column prop="contractor" header-align="center" min-width="80" label="签约代表">
                    </el-table-column>
                    <el-table-column prop="contractorPhone" header-align="center" min-width="100" label="联系电话">
                    </el-table-column>
                    <el-table-column header-align="center" min-width="100" label="合同开始">
                        <template slot-scope="scope">{{ scope.row.contractStartTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column header-align="center" min-width="100" label="合同结束">
                        <template slot-scope="scope">{{ scope.row.contractEndTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column show-overflow-tooltip prop="pkName" header-align="center" min-width="100" label="项目名称">
                    </el-table-column>
                    <el-table-column prop="createName" header-align="center" min-width="80" label="录入人">
                    </el-table-column>
                    <el-table-column header-align="center" min-width="140" label="录入时间">
                        <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                    </el-table-column>
                    <el-table-column header-align="center" fixed="right" label="操作" width="200">
                        <template slot-scope="scope">
                            <el-button v-if="showDetailTenantBtn" @click="contratDetail(scope.row.ctCode)" type="text" size="small">详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="page_down">
                <el-pagination style="margin-top:10px;" background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="start" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
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
        props: ['paramsObj'], // 接收父组件数据
        created: function() {
            let vm = this;
            //菜单和按钮权限控制
            vm.showDetailTenantBtn = vm.$flagMenuStore.judgeMenu("detailTenantBtn"); // 合同详情按钮
            vm.expireMonth = 3;
            vm.searchCon();

        },
        data() {
            return {
                tableHeight: `calc(100vh - 155px)`,
                ifMe: "1",
                floatDivHeight: "",
                // 权限
                showDetailTenantBtn: false, // 合同详情按钮
                expireMonth: "", //过期月份

                ctList: [],

                expireMonthList: [{
                        code: 3,
                        name: "近三个月"
                    },
                    {
                        code: 2,
                        name: "近两个月"
                    },
                    {
                        code: 1,
                        name: "近一个月"
                    },
                ],

                // 分页
                start: 1,
                pageSize: 50,
                pageSizes: [25, 50, 100, 150],
                total: 0,


            }
        },
        methods: {
            searchCon() {
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
                let vm = this;
                let contractDateStart = vm.get_month_first(0);
                let contractDateEnd = vm.get_month_last(3);

                let param = {
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize,
                    contractDateStart: contractDateStart,
                    contractDateEnd: contractDateEnd,
                    ifMe: vm.ifMe,
                    entity: {}
                };

                let options = {
                    method: "POST",
                    data: param,
                    url: "contract/expireInfo",
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (response) {
                        vm.ctList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }
                }).catch(function(error) {
                    vm.$alert('页面：合同列表查询信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // 合同详情
            contratDetail(ctCode) {

                let data = {
                    flag: "add",
                    menuName: "合同详情",
                    item: "contractDetails",
                    params: {
                        ctCode: ctCode
                    }
                };
                this.$emit("backParam", data);

            },
        }
    }
</script>

<style scoped>
    .search_buttons {
        background-color: white;
        width: 100%;
        display: flex;
        align-items: center;
    }

    .search_buttons span {
        float: left;
        margin-left: 20px;
        color: #409eff;
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

    /*月份选择下拉选*/
    .selectMonth {
        width: 150px;
        float: left;
        margin-left: 20px;
    }

    /*表格*/
    .costTable {
        line-height: 40px;
        text-align: center;
        width: 100%;
    }
</style>