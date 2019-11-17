<template>
    <div class="not-position_root">
        <!-- 悬浮框 -->
        <div class="not-position_header">
            <el-input v-model="ctName"
                      placeholder="输入合同编号"
                      class="input-with-select" size="small" clearable>
            </el-input>
            <el-select v-model="pkCode" placeholder="请选择园区" class="select_park"
                       filterable size="small">
                <el-option v-for="item in pkList"
                           :key="item.pkCode"
                           :label="item.pkName"
                           :value="item.pkCode">
                </el-option>
            </el-select>
            <el-date-picker
                    v-model="recordMonth"
                    type="month"
                    size="small" class="select_park"
                    placeholder="选择月">
            </el-date-picker>
            <el-button type="primary" size="small"
                       icon="el-icon-search"
                       @click="queryContract()"
                       style="margin-left: 20px;">
                查询
            </el-button>
        </div>

        <!-- 表格信息 -->
        <el-table
                :data="waterRecordList"
                border
                :highlight-current-row="true"
                size="small"
                @selection-change="handleSelectionChange"
                :height="tableHeight">
            <el-table-column
                    type="selection"
                    :selectable="selectable"
                    width="55">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    align="center"
                    label="序号"
                    type="index"
                    width="50px">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="ctName"
                    header-align="center"
                    width="130"
                    label="合同编号">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    header-align="center"
                    min-width="120"
                    prop="waterMeterName"
                    label="水表名">
            </el-table-column>
            <el-table-column
                    header-align="center"
                    min-width="100"
                    prop="lastMeterRecordDate"
                    label="上次抄表日期">
                <template slot-scope="scope">
                    {{ scope.row.lastMeterRecordDate | timeDate }}
                </template>
            </el-table-column>
            <el-table-column
                    header-align="center"
                    min-width="100"
                    label="当前抄表日期">
                <template slot-scope="scope">
                    {{ scope.row.currentMeterRecordDate | timeDate }}
                </template>
            </el-table-column>
            <el-table-column
                    header-align="center"
                    min-width="100"
                    label="所属月份">
                <template slot-scope="scope">
                    {{ scope.row.recordMonth | monthDate }}
                </template>
            </el-table-column>
            <el-table-column
                    fixed="left"
                    header-align="center"
                    min-width="120"
                    prop="hasBill"
                    label="已生成账单">
                <template slot-scope="scope">
                    <span v-if="scope.row.hasBill === true" style="color: rgb(103, 194, 58);">是</span>
                    <span v-else style="color: rgb(245, 108, 108);">否</span>
                </template>
            </el-table-column>
            <el-table-column
                    show-overflow-tooltip
                    prop="pkName"
                    header-align="center"
                    width="200"
                    label="园区">
            </el-table-column>
            <el-table-column
                    show-overflow-tooltip
                    header-align="center"
                    min-width="100"
                    label="联系人">
                <template slot-scope="scope">{{scope.row.relateName == null ? "---" : scope.row.relateName}}</template>
            </el-table-column>
            <el-table-column
                    show-overflow-tooltip
                    header-align="center"
                    min-width="150"
                    label="公司名称">
                <template slot-scope="scope">{{scope.row.companyName == null ? "---" : scope.row.companyName}}
                </template>
            </el-table-column>
            <el-table-column
                    header-align="center"
                    width="100"
                    label="计费方式">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.waterBillingType === '1'">均价计费</el-tag>
                    <el-tag v-if="scope.row.waterBillingType === '2'" type="warning">抄表计费</el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    header-align="center"
                    min-width="100"
                    prop="waterAmount"
                    label="水费金额">
            </el-table-column>
            <el-table-column
                    header-align="center"
                    min-width="100"
                    prop="lastBillingWater"
                    label="上次抄表吨位">
            </el-table-column>
            <el-table-column
                    header-align="center"
                    min-width="100"
                    prop="currentBillingWater"
                    label="本次抄表吨位">
            </el-table-column>
            <el-table-column
                    header-align="center"
                    min-width="100"
                    prop="waterUnitPrice"
                    label="水费单价">
            </el-table-column>
            <el-table-column
                    header-align="center"
                    min-width="100"
                    prop="realWater"
                    label="实用量">
            </el-table-column>
            <el-table-column
                    header-align="center"
                    fixed="right"
                    label="操作"
                    width="80">
                <template slot-scope="scope">
                    <el-button v-if="scope.row.hasBill == false"
                               @click="editWaterRecord(scope.row)"
                               type="text" size="small">
                        修改
                    </el-button>
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

        <el-dialog title="抄表修改" style="text-align: center;" :visible.sync="dialogFormVisible" width="80%">
            <el-form :model="waterRecordObj" ref="waterRecordObj" labelSuffix="：" :rules="rules" style="text-align: left;" label-width="130px">
                <el-card style="margin-bottom: 20px;" shadow="hover">
                    <div slot="header">
                        <el-row :gutter="10">
                            <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" style="text-align: left;">
                                <span style="font-weight: bold;">合同编号：</span>
                                <span style="font-weight: bold; color: red;">{{ waterRecordObj.ctName }}</span>
                            </el-col>
                            <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" style="text-align: left;">
                                <span style="font-weight: bold;">园区：</span>
                                {{ waterRecordObj.pkName }}
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left;">
                                <span style="font-weight: bold;">公司：</span>{{ waterRecordObj.companyName }}
                            </el-col>
                            <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" style="text-align: left;">
                                <span style="font-weight: bold;">联系人：</span>
                                {{ waterRecordObj.relateName }}
                            </el-col>
                        </el-row>
                    </div>

                    <div>
                        <el-row :gutter="20">
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item label="水表名">
                                    <span style="font-weight: bold;">{{ waterRecordObj.waterMeterName }}</span>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item label="计费方式">
                                    <span style="font-weight: bold;">{{ waterRecordObj.waterBillingTypeName }}</span>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="上次抄表时间">
                                    <span v-if="!waterRecordObj.lastMeterRecordDate">无数据</span>
                                    <span v-else>{{ waterRecordObj.lastMeterRecordDate | timeDate }}</span>
                                </el-form-item>
                            </el-col>

                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="本次抄表时间" prop="currentMeterRecordDate">
                                    <el-date-picker
                                            v-model="waterRecordObj.currentMeterRecordDate"
                                            type="date"
                                            placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="所属月份" prop="recordMonth">
                                    <el-date-picker
                                            v-model="waterRecordObj.recordMonth"
                                            type="month"
                                            placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <!-- 上次抄表计费 -->
                        <el-row :gutter="20" v-if="waterRecordObj.waterBillingType == '2'">
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item label="上次见表">
                                    <span>{{ waterRecordObj.lastBillingWater }}&nbsp;吨</span>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item label="水费单价">
                                    <span>{{ waterRecordObj.waterUnitPrice }}&nbsp;元/吨</span>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <!-- 本次均价计费 -->
                        <el-row :gutter="20" v-if="waterRecordObj.waterBillingType == '1'">
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="均摊计费" prop="waterAmount">
                                    <el-input v-model="waterRecordObj.waterAmount" clearable>
                                        <template slot="append">元</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <!-- 本次抄表计费 -->
                        <el-row :gutter="20" v-if="waterRecordObj.waterBillingType == '2'">
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="本次见表" prop="currentBillingWater">
                                    <el-input v-model="waterRecordObj.currentBillingWater" clearable>
                                        <template slot="append">kWh</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitForm('waterRecordObj')">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        data() {
            // 校验数字0～99999999.99
            let checkNumber = (rule, value, callback) => {
                setTimeout(() => {
                    if (value < 0) {
                        callback(new Error('数字值不能为负数'));
                    } else if (value > 99999999.99) {
                        callback(new Error('请输入0～99999999.99数字值'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            // 校验数字只能是正整数或小数
            let checkIntOrFloat = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(value))) {
                        callback(new Error('只能输入正整数或小数'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {
                tableHeight: this.$store.state.tableHeight,
                // 分页
                start: 1,
                pageSize: 50,
                pageSizes: [25, 50, 100, 150],
                total: 0,

                dialogFormVisible: false,// 弹窗

                pkList: [],// 园区集合
                pkCode: '',
                ctName: '',// 合同编号
                recordMonth: '',// 月份筛选

                waterRecordList: [],// 水表抄表记录集合
                waterRecordObj: {},// 水表抄表记录实体

                rules: {
                    waterAmount: [
                        {required: true, message: '请输入本次均摊计费', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    currentBillingWater: [
                        {required: true, message: '请输入本次见表吨数', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    lastMeterRecordDate: [
                        {required: true, message: '请选择抄表日期', trigger: 'blur'},
                    ],
                    currentMeterRecordDate: [
                        {required: true, message: '请选择抄表日期', trigger: 'blur'},
                    ],
                    recordMonth: [
                        {required: true, message: '请选择所属日期', trigger: 'blur'},
                    ],
                },
            }
        },
        created: function () {
            let vm = this;

            vm.selectAllPark();// 查询所有园区
        },
        methods: {
            // 查询所有园区
            selectAllPark(){
                let vm = this;

                let parkOptions = {
                    method: 'POST',
                    data: {},
                    url: 'park/list',
                };

                this.$ajax(
                    parkOptions
                ).then(function (response) {
                    if (response) {
                        vm.pkList = response.data.result;
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

            /*--------------------- 分页 ---------------------*/
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.queryContract(1);
            },
            handleCurrentChange(val) {
                this.start = val;
                this.queryContract();
            },

            // 查询二审通过的合同列表
            queryContract() {
                let vm = this;

                let param = {
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize,

                    entity: {
                        ctName: vm.ctName,
                        pkCode: vm.pkCode,
                        ctStatus: '4',
                        recordMonth: vm.recordMonth
                    }
                };

                let options = {
                    method: 'POST',
                    data: param,
                    url: 'waterRecord/query',
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.waterRecordList = response.data.result.data;
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

            // 修改抄表记录
            editWaterRecord(row) {
                let vm = this;

                vm.dialogFormVisible = true;

                vm.waterRecordObj = JSON.parse(JSON.stringify(row));
            },

            // 提交表单
            submitForm(formName) {
                let vm = this;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params = {
                            entity: vm.waterRecordObj
                        };

                        let options = {
                            method: 'POST',
                            data: params,
                            url: 'waterRecord/update'
                        };

                        this.$ajax(
                            options
                        ).then(response => {
                            if (response) {
                                vm.$message({
                                    showClose: true,
                                    message: '修改成功！',
                                    type: 'success'
                                });
                                vm.dialogFormVisible = false;
                                vm.queryContract();
                            }
                        }).catch(error => {
                            vm.$message({
                                showClose: true,
                                message: '修改失败！',
                                type: 'error'
                            });
                        })
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整！',
                            type: 'warning'
                        });
                    }
                })
            },

            // 批量抄电表，勾选
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },

            // 勾选禁用
            selectable(row, index) {
                if (row.waterMeterName == null) {
                    return false;
                } else {
                    return true;
                }
            },
        }
    }
</script>

<style scoped>
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
        width: 200px;
        float: left;
        margin-left: 20px;
    }

    /*园区选择下拉选*/
    .select_park {
        width: 150px;
        line-height: 30px;
        float: left;
        margin-left: 20px;
    }
</style>