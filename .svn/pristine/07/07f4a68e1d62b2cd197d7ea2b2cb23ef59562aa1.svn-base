<template>
    <div class="not-position_root">
        <div class="not-position_header">
            <el-button @click="createLandlord()" type="primary" size="small" style="margin-left: 20px;">
                合同新增
            </el-button>

            <el-input size="small"  clearable  style="width: 285px; margin-left: 20px"
                      @keyup.enter.native="searchEnterFun"
                      v-model="keyword"
                      placeholder="输入单号、合同编号或申请人查询">
                <el-button slot="append" icon="el-icon-search" @click="searchCon"></el-button>
            </el-input>
            <el-select class="select-style" filterable placeholder="请选择部门" v-model="createDeptCode" size="small" @change="searchCon">
                <el-option v-for="item in deptList"
                           :key="item.createDeptCode"
                           :label="item.createDeptName"
                           :value="item.createDeptCode">
                </el-option>
            </el-select>
            <el-select class="select-style" filterable placeholder="请选择状态" v-model="ldCtStatus" size="small" @change="searchCon">
                <el-option v-for="item in statusList"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
            </el-select>
        </div>

        <el-table
                class="cost_table"
                :data="landlordList"
                border
                size="small"
                :height="tableHeight">
            <el-table-column
                    align="center"
                    label="序号"
                    type="index">
            </el-table-column>
            <el-table-column
                    width="130"
                    prop="ldCtName"
                    label="合同编号">
            </el-table-column>
            <el-table-column
                    prop="ldCtStatusName"
                    label="合同状态">
            </el-table-column>
            <el-table-column
                    prop="pkName"
                    width="150"
                    label="园区名">
            </el-table-column>
            <el-table-column
                    prop="acreage"
                    label="承租面积">
            </el-table-column>
            <el-table-column
                    prop="landlordName"
                    label="业主姓名">
            </el-table-column>
            <el-table-column
                    prop="landlordSexName"
                    label="性别">
            </el-table-column>
            <el-table-column
                    width="100"
                    prop="landlordPhone"
                    label="业主电话">
            </el-table-column>
            <el-table-column
                    prop="contractor"
                    label="签约人">
            </el-table-column>
            <el-table-column
                    width="100"
                    label="合同开始时间">
                <template slot-scope="scope">{{ scope.row.contractStartDate | timeDate}}</template>
            </el-table-column>
            <el-table-column
                    width="100"
                    label="合同结束时间">
                <template slot-scope="scope">{{ scope.row.contractEndDate | timeDate}}</template>
            </el-table-column>
            <el-table-column
                    prop="createName"
                    label="创建人">
            </el-table-column>
            <el-table-column
                    label="创建时间"
                    width="90">
                <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="landlordDetail(scope.row.ldCode)" size="mini" type="text">详情</el-button>
                </template>
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
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    export default {
        components: {
            ElCol,
            ElRow},
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,

                landlordList: [],// 大房东合同集合

                keyword:'',
                createDeptCode:'',
                ldCtStatus:'',
                deptList:[],
                applyList:[],

                // 分页
                start: 1,
                pageSize: 50,
                pageSizes: [25, 50, 100, 150],
                total: 0,

                statusList: [
                    {
                        value: '',
                        label: '全部状态'
                    }, {
                        value: '1',
                        label: '待提交'
                    }, {
                        value: '2',
                        label: '一审提交'
                    }, {
                        value: '3',
                        label: '一审通过'
                    }, {
                        value: '4',
                        label: '二审通过'
                    },{
                        value: '5',
                        label: '驳回'
                    },{
                        value: '6',
                        label: '撤回'
                    }, {
                        value: '7',
                        label: '已退租'
                    }, {
                        value: '8',
                        label: '已改签'
                    },
                ],

            }
        },
        created() {
            let vm = this;
            vm.searchCon();
            vm.queryDept();
        },
        methods: {
            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                if (keyCode == 13) {
                    vm.handleCurrentChange(1);
                }
            },

            searchCon(){
                this.handleCurrentChange(1);
            },

            /*---------------------分页start---------------------*/
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.querylandlordList(1);
            },
            handleCurrentChange(val) {
                this.start = val;
                this.querylandlordList();
            },
            /*---------------------分页end----------------------*/

            // 新增大房东合同
            createLandlord() {
                let data = {
                    menuName: '大房东合同新增',
                    item: 'landlordContractCreate',
                    params: {

                    }
                };
                this.$emit('backParam', data);
            },

            //获取大房东合同列表
            querylandlordList(){
                let vm = this;

                let param = {
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize,
                    keyword: vm.keyword,
                    entity: {
                        createDeptCode:vm.createDeptCode,
                        ldCtStatus:vm.ldCtStatus,
                    }
                };

                let options = {
                    method: "POST",
                    data: param,
                    url: "landlordRentApply/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.landlordList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：退租列表查询信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },


            // 查看大房东合同详情
            landlordDetail(ldCode) {
                let data = {
                    menuName: '大房东合同详情',
                    item: 'landlordContractDetail',
                    params: {
                        ldCode: ldCode
                    }
                };
                this.$emit('backParam', data);
            },

            // 查询部门
            queryDept(){
                let vm = this;

                let param = {
                    entity: {}
                };

                let option = {
                    method: 'POST',
                    data: param,
                    url: 'landlordRentApply/getDept'
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.deptList = response.data.result;
                        let aObj = {createDeptCode: '', createDeptName: '所有部门'};
                        vm.deptList.unshift(aObj);
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '查询部门信息失败',
                        type: 'error'
                    });
                });
            },
        },
    }
</script>

<style scoped>

    /*表格*/
    .cost_table{
        line-height: 30px;
        text-align: center;
        width: 100%;
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

    .select-style{
        margin-left: 20px;
        width: 150px;
    }
</style>