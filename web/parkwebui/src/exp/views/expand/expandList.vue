<template>
    <div class="not-position_root" ref="myRoot">
        <!-- 悬浮框 -->
        <div class="not-position_header clearfix" style="margin-left: 10px;">
            <el-input v-model="keyword" @keyup.enter.native="searchCon('keyword')" placeholder="支持业主公司、姓名、电话、地址搜索" class="input-with-select" size="small" clearable>
                <el-button slot="append" icon="el-icon-search" @click="searchCon()"></el-button>
            </el-input>

            <el-button @click="createExpand()" v-if="showExpandCreateBtn" class="add_expand" type="primary" size="small">
                拓展新增
            </el-button>
        </div>
        <el-form>
            <!-- 表格化信息 -->
            <div>
                <el-table style="line-height: 30px;"
                          class="costTable"
                          :data="expandList"
                          fixed="right"
                          border
                          :highlight-current-row="true"
                          size="small"
                          :height="tableHeight">
                    <el-table-column type="expand">
                        <template slot-scope="scope">
                            <el-row style="text-align: left">
                                <el-col :span="6" style="height: 30px;">
                                    <el-form-item label="拓展人:">
                                        {{scope.row.expEmpName}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6" style="height: 30px;">
                                    <el-form-item label="拓展人电话:">
                                        {{scope.row.expEmpPhone}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12" style="height: 30px;">
                                    <el-form-item label="拓展人公司:">
                                        {{scope.row.expEmpCpyName}}
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="序号"
                            type="index">
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            label="项目得分"
                            min-width="80px">
                        <template slot-scope="scope">
                            {{scope.row.score + '(' + scope.row.assessResult + ')'}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="parkOwnerName"
                            header-align="center"
                            label="业主姓名"
                            min-width="70px">
                    </el-table-column>
                    <el-table-column
                            prop="parkOwnerPhone"
                            header-align="center"
                            min-width="100px"
                            label="业主电话">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            header-align="center"
                            min-width="120px"
                            label="项目地址">
                        <template slot-scope="scope">
                            {{scope.row.provinceName}}{{scope.row.cityName}}{{scope.row.regionName}}{{scope.row.address}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="expStatusName"
                            header-align="center"
                            label="项目状态"
                            min-width="70px;">
                    </el-table-column>
                    <el-table-column
                            prop="contractYear"
                            header-align="center"
                            label="合同年限/年" min-width="100px">
                    </el-table-column>
                    <el-table-column
                            prop="parkArea"
                            header-align="center"
                            label="占地面积/㎡" min-width="100px">
                    </el-table-column>

                    <el-table-column
                            prop="parkBuildingArea"
                            header-align="center"
                            label="建筑面积/㎡" min-width="100px">
                    </el-table-column>
                    <el-table-column
                            prop="singleFloorQuantity"
                            header-align="center"
                            label="单层厂房数" min-width="90px">
                    </el-table-column>
                    <el-table-column
                            prop="moreFloorQuantity"
                            header-align="center"
                            label="多层厂房数" min-width="90px">
                    </el-table-column>
                    <el-table-column
                            prop="unitPrice"
                            header-align="center"
                            label="价格(元/㎡/月)" min-width="110px">
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            label="附房占比/%" min-width="110px">
                        <template slot-scope="scope">
                            {{scope.row.otherBuildingRate + '%'}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            fixed="right"
                            label="操作"
                            width="180">
                        <template slot-scope="scope">
                            <el-button v-if="showExpandDetailBtn" type="text" size="small"
                                       @click="expandDetail(scope.row.itemCode)">详情
                            </el-button>
                            <el-button v-if="showExpandFollowupDetailBtn" type="text" size="small"
                                       @click="expandFollowupDetail(scope.row.itemCode)">跟进详情
                            </el-button>
                            <el-button v-if="showExpandFollowupCreateBtn" type="text" size="small"
                                       @click="expandFollowupCreate(scope.row.itemCode)">添加跟进
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="page_down">
                <el-pagination style="margin-top:10px;"
                               background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="start"
                               :page-sizes="pageSizes"
                               :page-size="pageSize"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total"
                               v-if="total != 0">
                </el-pagination>
            </div>
        </el-form>

        <el-dialog title="添加拓展跟进" style="line-height: 30px;" :visible.sync="expandFollowupCreateWindow">
            <el-form :model="expandFollowupForm" :rules="expandFollowupCreateRules" ref="expandFollowupForm"
                     label-width="110px">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="跟进类型：" prop="invFollowupType" style="text-align: left;">
                            <el-radio-group v-model="expandFollowupForm.invFollowupType">
                                <el-radio :label="1">电话咨询</el-radio>
                                <el-radio :label="2">实地拜访</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="跟进状态：" prop="expStatus" style="text-align: left;">
                            <el-radio-group v-model="expandFollowupForm.expStatus">
                                <el-radio label="1">跟进中</el-radio>
                                <el-radio label="2">谈判中</el-radio>
                                <el-radio label="3">已签约</el-radio>
                                <el-radio label="4">不合适</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="跟进结果：" prop="followupResult">
                            <el-input type="textarea" :row="6" v-model="expandFollowupForm.followupResult"
                                      clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer">
                <el-button @click="resetForm('expandFollowupForm')">重 置</el-button>
                <el-button @click="submitForm('expandFollowupForm')" type="primary">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="拓展跟进详情" style="line-height: 30px;" :visible.sync="expandFollowupDetailWindow">
            <el-table :data="expandFollowupList" border height="300">
                <el-table-column align="center" min-width="30px" label="跟进类型"
                                 prop="invFollowupTypeName"></el-table-column>
                <el-table-column align="center" min-width="30px" label="项目状态" prop="expStatusName"></el-table-column>
                <el-table-column align="center" min-width="50px" label="跟进时间" show-overflow-tooltip>
                    <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                </el-table-column>
                <el-table-column align="center" min-width="30px" label="跟进结果" prop="followupResult"></el-table-column>
                <el-table-column align="center" min-width="30px" label="跟进人员" prop="createName"></el-table-column>
            </el-table>
        </el-dialog>
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
        data () {
            return {
                tableHeight: this.$store.state.tableHeight,

                // 权限
                showExpandList: false,// 拓展列表
                showExpandDetailBtn: false,// 拓展列表
                showExpandCreateBtn: false,// 新增拓展按钮
                showExpandFollowupCreateBtn: false,// 拓展跟进新增
                showExpandFollowupDetailBtn: false,// 拓展跟进详情

                // 弹窗打开关闭控制
                expandFollowupCreateWindow: false,// 添加拓展跟进弹窗
                expandFollowupDetailWindow: false,// 拓展跟进详情弹窗
                dialogFormVisible: false,// 更多条件弹窗控制

                expandFollowupForm: {},// 拓展跟进表单
                expandFollowupList: [],// 拓展跟进实体

                expandList: [],// 拓展信息集合

                keyword: '',// 关键词搜索

                // 分页
                start: 1,
                pageSize: 16,
                pageSizes: [1, 2],
                total: 0,

                // 添加拓展跟进
                expandFollowupCreateRules: {
                    invFollowupType: [
                        {required: true, message: '请选择跟进类型', trigger: 'change'},
                    ],
                    expStatus: [
                        {required: true, message: '请选择跟进状态', trigger: 'change'},
                    ],
                    followupResult: [
                        {required: true, message: '请填写跟进结果', trigger: 'change'},
                    ],
                },
            }
        },
        created () {
            let vm = this;

            // 权限
            vm.showExpandList = vm.$flagMenuStore.judgeMenu("expandList");
            vm.showExpandDetailBtn = vm.$flagMenuStore.judgeMenu("expandDetailBtn");
            vm.showExpandCreateBtn = vm.$flagMenuStore.judgeMenu("expandCreateBtn");
            vm.showExpandFollowupCreateBtn = vm.$flagMenuStore.judgeMenu("expandFollowupCreateBtn");
            vm.showExpandFollowupDetailBtn = vm.$flagMenuStore.judgeMenu("expandFollowupDetailBtn");

            if (vm.showExpandList) {
                let param = {};
                param.entity = {};
                param.start = (vm.start - 1) * vm.pageSize;
                param.pageSize = vm.pageSize;

                vm.queryInvparkList(param); // 查询拓展信息列表
            } else {
                window.history.back();
            }
        },
        methods: {
            // 拓展新增
            createExpand() {
                // this.$router.push({
                //  path: '/exp/expandCreate'
                // });
                let data = {
                    menuName: "拓展新增",
                    item: "expandCreate",
                    params: {}
                };
                this.$emit("backParam", data);
            },
            // 拓展跟进新增
            expandFollowupCreate(itemCode){
                this.expandFollowupCreateWindow = true;
                this.expandFollowupForm.itemCode = itemCode;
            },
            // 添加跟进 保存按钮
            submitForm(formName) {
                let vm = this;

                this.$refs[formName].validate((valid) => {
                    if (valid) {

                        let obj = {
                            entity: this.expandFollowupForm,
                        };

                        let options = {
                            method: 'POST',
                            data: obj,
                            url: "invparkFollowup/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '拓展跟进新增成功',
                                    type: 'success',
                                });

                                // 跟进新增成功后，重置表单并关闭窗口
                                vm.resetForm(formName);
                                vm.expandFollowupCreateWindow = false;

                                //添加完跟进刷新页面
                                vm.searchCon();
                            }
                        });
                    } else {
                        vm.$message({
                            showClose: true,
                            message: '信息填写不完整',
                            type: 'error'
                        });
                    }
                });
            },
            // 拓展跟进详情
            expandFollowupDetail(itemCode){
                let vm = this;

                vm.expandFollowupDetailWindow = true;

                let params = {
                    entity: {
                        itemCode: itemCode,
                    }
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: "invparkFollowup/list",
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (!response.data.isException) {
                        vm.expandFollowupList = response.data.result;
                    }
                }).catch(error => {
                    vm.$message({
                        showClose: true,
                        message: '信息填写不完整',
                        type: 'error'
                    });
                })
            },
            // 重置，添加跟进
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            // 拓展详情
            expandDetail(itemCode){
                // this.$router.push({
                // name: "expandDetail",
                // query: {
                // itemCode: itemCode,
                // }
                // })
                let data = {
                    menuName: "拓展详情",
                    item: "expandDetail",
                    params: {
                        itemCode: itemCode,
                    }
                };
                this.$emit("backParam", data);
            },
            // 搜索框
            searchCon(){
                this.handleCurrentChange(1);
            },
            // 分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.handleCurrentChange(1);
            },
            handleCurrentChange(val) {
                let vm = this;

                let params = {
                    entity: {},
                    pageSize: vm.pageSize,
                    start: (val - 1) * (vm.pageSize),
                    keyword: vm.keyword
                };

                vm.queryInvparkList(params);
            },
            queryInvparkList(params){
                let vm = this;

                let option = {
                    method: "POST",
                    data: params,
                    url: 'invpark/query'
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.expandList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查询拓展项目列表失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
        }
    }

</script>

<style scoped>
    /*拓展新增按钮*/
    .add_expand {
        margin-left: auto;
        margin-right: 30px;
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

    /*搜索框*/
    .input-with-select {
        width: 300px;
        margin-left: 10px;
    }

    /*表格*/
    .costTable {
        text-align: center;
        width: 100%;
    }
</style>