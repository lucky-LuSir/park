<template>
    <div class="not-position_root">
        <!-- 导航栏 -->
        <div class="not-position_header">
            <el-date-picker
                    size="small"
                    style="width: 250px; margin-left: 20px;"
                    v-model="dateList"
                    type="daterange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期">
            </el-date-picker>

            <el-button @click="querySmsSendHistoryList()"
                       type="primary"
                       size="small"
                       icon="el-icon-search"
                       style="margin-left: 10px;">
                查询
            </el-button>
        </div>

        <el-table
                height="78vh"
                :data="smsSendHistoryList"
                border size="small"
                :height="tableHeight"
               >
            <el-table-column
                    type="index"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="templateName"
                    label="模板名称"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="success"
                    label="发送状态"
                    width="100">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.success === false" type="danger">失败</el-tag>
                    <el-tag v-if="scope.row.success === true" type="success">成功</el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    prop="templateCode"
                    label="短信模板ID"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="signName"
                    label="短信模板签名"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="templateTypeName"
                    label="模板类型"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="content"
                    label="短信内容">
            </el-table-column>
            <el-table-column
                    prop="phoneNum"
                    label="接收号码"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="sendDate"
                    label="发送日期"
                    width="150">
            </el-table-column>
            </el-table-column>
            <el-table-column
                    prop="receiveDate"
                    label="接收日期"
                    width="150">
            </el-table-column>
        </el-table>

        <!-- 分页栏 -->
        <div class="page-box">
            <el-pagination background
                           @size-change="handleSizeChange"
                           @current-change="handleCurrentChange"
                           :current-page.sync="currentPage"
                           :page-sizes="pageSizes"
                           :page-size="defaultPageSize"
                           layout="total, sizes, prev, pager, next, jumper"
                           :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,
                smsSendHistoryList: [],// 短信发送历史
                dateList: [],// 时间选择
                // 分页
                currentPage: 1,
                defaultPageSize: 15,// 默认每页数量
                pageSizes: [15, 50, 100, 150],
                total: 0,
            }
        },
        created: function () {
            let vm = this;

            vm.querySmsSendHistoryList();
        },
        methods: {
            // 改变每页数量
            handleSizeChange(val) {
                this.defaultPageSize = val;
                this.currentPage = 1;
                this.querySmsSendHistoryList();
            },
            // 改变当前页
            handleCurrentChange(val) {
                this.currentPage = val;
                this.querySmsSendHistoryList();
            },

            // 查询短信发送历史
            querySmsSendHistoryList() {
                let vm = this;

                let contractDateStart = '';
                if(vm.dateList[0]){
                    contractDateStart = vm.dateList[0].getTime();
                }

                let contractDateEnd = '';
                if(vm.dateList[1]){
                    contractDateEnd = vm.dateList[1].getTime();
                }

                let params = {
                    sendDateStart: contractDateStart,
                    sendDateEnd: contractDateEnd,
                    start: (vm.currentPage - 1) * vm.defaultPageSize,
                    pageSize: vm.defaultPageSize,
                    entity: {}
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: 'smsSendHistory/query'
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.total = response.data.result.totalCount;
                        vm.smsSendHistoryList = response.data.result.data;
                    }
                }).catch(error => {
                    vm.$message({
                        showClose: true,
                        message: '页面：短信模板列表查询失败！',
                        type: 'error'
                    })
                })
            },
        }
    }
</script>

<style scoped>
    /*分页栏样式*/
    .page-box {
        height: 40px;
        padding-top: 10px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }
</style>