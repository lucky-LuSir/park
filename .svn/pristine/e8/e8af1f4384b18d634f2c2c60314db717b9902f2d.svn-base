<template>
    <div class="not-position_root" ref="myRoot">
        <el-form label-width="100px">
            <div class="not-position_header">
                <!-- 部门筛选组件 -->
                <dept-selector class="select-style" @sendDeptCode="getDeptCode"></dept-selector>

                <el-select class="select-style" filterable placeholder="请选择园区" v-model="pkCode" size="small" clearable>
                    <el-option v-for="item in parkList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode">
                    </el-option>
                </el-select>

                <el-select class="select-style" filterable placeholder="请选择" v-model="cusStatus" size="small">
                    <el-option v-for="item in cusStatusList" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>

                <el-date-picker size="small" class="select-style-date" v-model="dateData" type="daterange" align="right" unlink-panels range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions2">
                </el-date-picker>

                <el-button class="date-style" size="small" type="primary" @click="followupStatus()" icon="el-icon-search">
                    查询
                </el-button>
                <el-button class="date-style" size="small" type="primary" @click="deleteTerm()" icon="el-icon-delete">
                    清空
                </el-button>
            </div>

            <el-table  size="small" :data="followupList" :height="tableHeight" border >
                <el-table-column align="center" min-width="30" label="序号" type="index">
                </el-table-column>
                <el-table-column align="center" min-width="50" label="客户姓名" prop="cusName">
                </el-table-column>
                <!--<el-table-column-->
                <!--align="center"-->
                <!--min-width="40px"-->
                <!--label="客户电话"-->
                <!--prop="cusPhone">-->
                <!--</el-table-column>-->

                <el-table-column align="center" min-width="50" label="客户来源" prop="cusChannelName">
                </el-table-column>

                <el-table-column align="center" min-width="50" label="客户状态" prop="cusStatusName">
                </el-table-column>

                <el-table-column align="center" min-width="50" label="跟进类型" prop="followupTypeName">
                </el-table-column>
                <el-table-column align="center" min-width="50" label="来访园区" show-overflow-tooltip prop="pkName">
                </el-table-column>

                <el-table-column align="center" min-width="50" label="跟进人员" prop="createName">
                </el-table-column>
                <el-table-column align="center" min-width="50" label="跟进时间" show-overflow-tooltip>
                    <template slot-scope="scope">{{ scope.row.followupTime | time}}</template>
                </el-table-column>
                <el-table-column align="center" min-width="50px" label="录入时间" show-overflow-tooltip>
                    <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                </el-table-column>
                <el-table-column align="center" min-width="60" label="跟进结果" prop="followupRes">
                </el-table-column>
                <el-table-column align="center" min-width="60" label="图片展示">
                    <template slot-scope="scope">
                        <img v-for="(item, index) in scope.row.attachmentList" :key="index" class="img_attachment" :src=" parkBaseUrl + 'file/read/' + item ">
                    </template>
                </el-table-column>
                <el-table-column width="80px" align="center" label="操作">
                    <template slot-scope="scope">
                        <el-button @click="handleEdit(scope.row.cusCode)" size="mini" type="text">客户详情</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <div class="table-page-div">
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
            followupList: [],// 跟进集合
            dateData: [],// 日期数据
            deptList: [],// 部门集合
            parkList: [],//园区集合

            deptCode: '',// 部门code
            cusStatus: '',// 客户跟进状态
            pkCode: '',//园区

            // 分页数据
            pageSize: 16,// 默认每页条数
            totalCount: 10,// 总条数
            currentPage: 1,// 当前页数

            // 跟进状态
            cusStatusList: [
                {
                    value: "",
                    label: "全部"
                },
                {
                    value: "1",
                    label: "跟进中"
                },
                {
                    value: "2",
                    label: "谈判中"
                },
                {
                    value: "3",
                    label: "已签约"
                },
                {
                    value: "4",
                    label: "不合适"
                },
            ],

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
    created: function () {
        let vm = this;
        let params = {};
        params.pageSize = vm.pageSize;
        params.start = 0;
        vm.queryListTemplate(vm, params);// 查询跟进
        vm.selectAllPark()// 查询所有园区
    },
    methods: {
        followupStatus() {
            this.handleCurrentChange(1);
        },
        // 客户详情
        handleEdit(cusCode) {
            let data = {
                menuName: '客户详情',
                item: 'customerDetail',
                params: {
                    cusCode: cusCode
                }
            };
            this.$emit('backParam', data);
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

            let pageData = {
                entity: {
                    cusStatus: vm.cusStatus,
                    createDeptCode: vm.deptCode,
                    pkCode: vm.pkCode,
                },
                pageSize: vm.pageSize,
                start: (val - 1) * (vm.pageSize),
                followupTimeStart: vm.dateData[0],
                followupTimeEnd: vm.dateData[1],
            };

            vm.queryListTemplate(vm, pageData);
        },
        // 查询客户跟进模板
        queryListTemplate(vm, params) {

            let options = {
                method: "POST",
                data: params,
                url: "followup/query"
            };
            this.$ajax(
                options
            ).then(function (response) {
                if (response) {
                    vm.totalCount = response.data.result.totalCount;
                    vm.followupList = response.data.result.data;
                    if (vm.followupList) {
                        for (let i = 0; i < vm.followupList.length; i++) {
                            if (vm.followupList[i].attachment) {
                                let attachmentList = vm.followupList[i].attachment.split(",");
                                vm.followupList[i].attachmentList = attachmentList;
                            }
                        }
                    }
                }
            }).catch(function (error) {
                vm.$message({
                    showClose: true,
                    message: '页面：客户跟进查询失败！',
                    type: 'error'
                })
            })
        },
        // 获取组件传递的deptCode
        getDeptCode(data) {
            let vm = this;
            vm.deptCode = data;
        },
        // 根据部门code查询跟进信息
        queryFollowupListByDeptCode() {
            let vm = this;

            let params = {
                entity: {
                    createDeptCode: vm.deptCode,
                    cusStatus: vm.cusStatus,
                },
                pageSize: vm.pageSize,
                start: 0
            };

            let options = {
                method: "POST",
                data: params,
                url: "followup/query"
            };
            this.$ajax(
                options
            ).then(function (response) {
                if (!response.data.isException) {
                    vm.totalCount = response.data.result.totalCount;
                    vm.followupList = response.data.result.data;
                }
            }).catch(function (error) {
                vm.$message({
                    showClose: true,
                    message: '页面：客户跟进查询失败！',
                    type: 'error'
                })
            })
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
        // 清空条件
        deleteTerm() {
            this.dateData = '';
            this.cusStatus = '';
            this.pkCode = '';

            this.followupStatus();
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
    width: 150px;
}

.select-style-date {
    float: left;
    /*margin: 10px 0 0 20px;*/
    margin-left: 20px;
    height: 30px;
    line-height: 30px;
    width: 220px;
}

/*日期样式*/
.date-style {
    float: left;
    /*margin-top: 10px;*/
    margin-left: 20px;
}

/*表格样式*/
.table-style {
    line-height: 20px;
}

/*分页*/
.table-page-div {
    height: 40px;
    background-color: rgb(242, 242, 242);
    text-align: left;
    bottom: 0px;
    width: 100%;
    padding-top: 10px;

    position: fixed;
    z-index: 200;
}

/*图片展示*/
.img_attachment {
    width: 50px;
    height: 50px;
}
</style>