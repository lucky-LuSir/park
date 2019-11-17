<template>
    <div class="not-position_root" ref="myRoot">
        <el-form label-width="100px">
            <div class="not-position_header">
                <!-- 部门筛选组件 -->
                <dept-selector @sendDeptCode="getDeptCode" class="select-style"></dept-selector>

                <el-select size="small" v-model="channelQueryParam.followupType" class="select-style" filterable placeholder="请选择跟进类型" clearable>
                    <el-option v-for="(item) in channelFollowupTypeList" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>

                <el-select size="small" v-model="channelQueryParam.createCode" class="select-style" filterable placeholder="请选择跟进人员" clearable>
                    <el-option v-for="(item) in empList" :key="item.empCode" :label="item.empName" :value="item.empCode">
                    </el-option>
                </el-select>

                <el-button @click="selectButton()" size="small" icon="el-icon-search" style=" margin-left: 20px;" type="primary">
                    查询
                </el-button>
            </div>


            <el-table  size="small" :data="channelFollowupObj" :height="tableHeight" border>
                <el-table-column align="center" min-width="30px" label="序号" type="index">
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="渠道类型" prop="channelEntity.channelStatusName">
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="渠道单位" prop="channelCpyEntity.channelCpyName">
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="渠道联系人" prop="channelEntity.userName">
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="性别" prop="channelEntity.sex">
                    <template slot-scope="scope">{{scope.row.channelEntity.sex == 1 ? "男" : "女"}}</template>
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="联系人电话" prop="channelEntity.phone">
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="职务" prop="channelEntity.post">
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="跟进类型" prop="followupTypeName">
                </el-table-column>
                <el-table-column align="center" min-width="40px" label="跟进人" prop="createName">
                </el-table-column>
                <el-table-column align="center" min-width="40px" label="跟进部门" prop="createDeptName">
                </el-table-column>
                <el-table-column align="center" min-width="50px" label="跟进时间" prop="createTime">
                    <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                </el-table-column>
                <el-table-column align="center" min-width="50px" label="跟进结果" show-overflow-tooltip prop="followupResult">
                </el-table-column>
            </el-table>

            <div class="table-page-div" :style="{width:floatDivWidth+'px'}">
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
                floatDivWidth: '',

                pageSize: 16, // 默认每页条数
                totalCount: 10, // 总条数
                currentPage: 1, // 当前页数

                channelObj: {
                    channelStatus: '', // 渠道类型
                    keyword: '', // 关键词
                },

                // 查询参数
                channelQueryParam: {
                    followupType: '',
                    createDeptCode: '',
                    createCode: '',
                },

                channelFollowupObj: [], // 渠道跟进列表，关联渠道、渠道单位

                deptList: [], // 部门集合
                empList: [], // 人员集合

                // 跟进类型
                channelFollowupTypeList: [{
                        value: '',
                        label: '所有跟进类型'
                    },
                    {
                        value: '1',
                        label: '电话跟进'
                    },
                    {
                        value: '2',
                        label: '上门拜访'
                    },
                ],
            }
        },
        created: function() {
            let vm = this;

            let params = {};
            params.pageSize = vm.pageSize;
            params.start = 0;
            params.entity = {};
            vm.queryListTemplate(vm, params);

            vm.queryAllemp(); // 查询所有人员
        },
        methods: {
            // 查询按钮
            selectButton() {
                this.handleCurrentChange(1);
            },

            // 分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.currentPage = 1;
                this.handleCurrentChange(1);
            },
            // 查询条件筛选
            handleCurrentChange(val) {
                let vm = this;

                let params = {};
                params.entity = {};

                if (vm.channelQueryParam.followupType) {
                    params.entity.followupType = vm.channelQueryParam.followupType;
                }
                if (vm.channelQueryParam.createDeptCode) {
                    params.entity.createDeptCode = vm.channelQueryParam.createDeptCode;
                }
                if (vm.channelQueryParam.createCode) {
                    params.entity.createCode = vm.channelQueryParam.createCode;
                }

                params.pageSize = vm.pageSize;
                params.start = (val - 1) * (vm.pageSize);

                vm.queryListTemplate(vm, params);
            },

            // 查询渠道跟进
            queryListTemplate(vm, params) {
                let options = {
                    method: "POST",
                    data: params,
                    url: "channelFollowup/query"
                };

                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.totalCount = response.data.result.totalCount;
                        vm.channelFollowupObj = response.data.result.data;
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
                vm.channelQueryParam.createDeptCode = data;
            },

            // 查询所有人员
            queryAllemp() {
                let vm = this;

                let options = {
                    method: "POST",
                    data: {},
                    url: "employee/list"
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (!response.data.isException) {
                        vm.empList = response.data.result;
                        let obj = {
                            empCode: "",
                            empName: "所有人员"
                        };
                        vm.empList.unshift(obj);
                    }
                }).catch(error => {
                    vm.$alert('页面：人员查询集合失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                })
            },
        },
    }
</script>

<style scoped>
    /*下拉选样式*/
    .select-style {
        float: left;
        /*margin: 10px 0 0 20px;*/
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