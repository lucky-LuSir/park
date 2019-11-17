<template>
    <div class="not-position_root" ref="myRoot">
        <el-form label-width="100px">

            <div class="not-position_header">
                <!-- 部门筛选组件 -->
                <dept-selector @sendDeptCode="getDeptCode" class="select-style"></dept-selector>
                <el-select size="small" v-model="companyCode" class="select-style" @change="channelCpyChange()" filterable placeholder="请选择">
                    <el-option v-for="(item) in selectChannelCpy" :key="item.companyCode" :label="item.channelCpyName" :value="item.companyCode">
                    </el-option>
                </el-select>
            </div>

            <el-table  size="small" :data="channelCpyList" :height="tableHeight" border>
                <el-table-column align="center" min-width="30px" label="序号" type="index">
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="单位名称" prop="channelCpyName">
                </el-table-column>
                <el-table-column align="center" min-width="40px" label="单位描述" prop="channelCpyRemark">
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="单位地址" prop="channelCpyAddress">
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="创建人" prop="createName">
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="录入时间">
                    <template slot-scope="scope">{{ scope.row.createTime | timeDate }}</template>
                </el-table-column>
            </el-table>

            <div class="table-page-div" v-bind:style="{width:floatDivWidth+'px'}">
                <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="currentPage" :page-sizes="[8, 16, 32]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="totalCount">
                </el-pagination>
            </div>
        </el-form>
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
                channelCpyList: [], // 表格绑定数据
                selectChannelCpy: [], // 下拉选条件
                floatDivWidth: "",
                companyCode: "", // 下拉选菜单，选中的code
                createDeptCode: '', //部门

                currentPage: 1, // 当前页数
                totalCount: 10, // 总条数
                pageSize: 16, // 默认每页条数

            }
        },
        created: function() {
            let vm = {};
            vm = this;

            let params = {
                entity: {

                }
            };
            params.pageSize = vm.pageSize;
            params.start = 0;

            vm.queryListTemplate(vm, params);
            vm.selectMethod();
        },
        methods: {
            channelCpyChange() {
                this.handleCurrentChange(1);
            },
            // 分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.currentPage = 1;
                this.handleCurrentChange(1);
            },
            // 查询条件删选
            handleCurrentChange(val) {
                var vm = {};
                vm = this;

                var pageData = {
                    entity: {
                        companyCode: vm.companyCode,
                        createDeptCode: vm.createDeptCode,
                    },
                    pageSize: vm.pageSize,
                    start: (val - 1) * (vm.pageSize),
                };

                vm.queryListTemplate(vm, pageData);
            },
            // 查询客户跟进模板
            queryListTemplate(vm, params) {
                let options = {
                    method: "POST",
                    data: params,
                    url: "customer/channel/cpy/query"
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.totalCount = response.data.result.totalCount;
                        vm.channelCpyList = response.data.result.data;
                    }
                }).catch(function(error) {
                    vm.$message({
                        showClose: true,
                        message: '页面：渠道列表查询失败！',
                        type: 'error'
                    })
                })
            },
            // 下拉选菜单
            selectMethod() {
                let vm = {};
                vm = this;

                let pageData = {
                    entity: {},
                };

                let options = {
                    method: "POST",
                    data: pageData,
                    url: "customer/channel/cpy/list"
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.selectChannelCpy = response.data.result;
                        let aObj = {
                            companyCode: "",
                            channelCpyName: "全部"
                        };
                        vm.selectChannelCpy.unshift(aObj);
                    }
                }).catch(function(error) {
                    vm.$message({
                        showClose: true,
                        message: '页面：渠道列表查询失败！',
                        type: 'error'
                    })
                })
            },

            // 获取组件传递的deptCode
            getDeptCode(data) {
                let vm = this;
                vm.createDeptCode = data;
                vm.handleCurrentChange(1);
            },
        },
    }
</script>

<style scoped>

    /*下拉选样式*/
    .select-style {
        float: left;
        /*margin: 13px 0 0 20px;*/
        margin-left: 20px;
        height: 30px;
        line-height: 30px;
        width: 160px;
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
</style>