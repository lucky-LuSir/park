<template>
    <div class="not-position_root" ref="myRoot">
        <el-form>
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
                    :data="eleRecordList"
                    border
                    :highlight-current-row="true"
                    size="small"
                    :height="tableHeight">
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
                        prop="eleMeterName"
                        min-width="120"
                        label="电表名">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
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
                    <template slot-scope="scope">{{scope.row.companyName == null ? "---" : scope.row.companyName}}</template>
                </el-table-column>
                <el-table-column
                        header-align="center"
                        prop="electricityBillingTypeName"
                        width="100"
                        label="计费方式">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.electricityBillingType === '1'">均价计费</el-tag>
                        <el-tag v-if="scope.row.electricityBillingType === '2'" type="warning">峰谷计费</el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="lastEleAverageDegree"
                        label="上次均价电表度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="currentEleAverageDegree"
                        label="本次均价电表度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="lastPeakDegree"
                        label="上次峰值抄表度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="currentPeakDegree"
                        label="本次峰值抄表度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="lastTroughDegree"
                        label="上次谷值抄表度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="currentTroughDegree"
                        label="本次谷值抄表度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="lastFlatDegree"
                        label="上次平值抄表度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="currentFlatDegree"
                        label="本次平值抄表度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="peakDegree"
                        label="高峰实用度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="troughDegree"
                        label="低峰实用度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="flatDegree"
                        label="平峰实用度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        prop="realDegree"
                        label="实用度数">
                </el-table-column>
                <el-table-column
                        header-align="center"
                        fixed="right"
                        label="操作"
                        width="80">
                    <template slot-scope="scope">
                        <el-button v-if="scope.row.hasBill == false"
                                   @click="editEleRecord(scope.row)"
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
        </el-form>

        <el-dialog title="抄表修改" style="text-align: center;" :visible.sync="dialogFormVisible" width="80%">
            <el-form :model="electricityRecordObj" ref="electricityRecordObj" labelSuffix="：" :rules="rules" style="text-align: left;" label-width="130px">
                <el-card style="margin-bottom: 20px;" shadow="hover">
                    <div slot="header">
                        <el-row :gutter="10">
                            <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" style="text-align: left;">
                                <span style="font-weight: bold;">合同编号：</span>
                                <span style="font-weight: bold; color: red;">{{ electricityRecordObj.ctName }}</span>
                            </el-col>
                            <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" style="text-align: left;">
                                <span style="font-weight: bold;">园区：</span>
                                {{ electricityRecordObj.pkName }}
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left;">
                                <span style="font-weight: bold;">公司：</span>{{ electricityRecordObj.companyName }}
                            </el-col>
                            <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" style="text-align: left;">
                                <span style="font-weight: bold;">联系人：</span>
                                {{ electricityRecordObj.relateName }}
                            </el-col>
                        </el-row>
                    </div>

                    <div>
                        <el-row :gutter="20">
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="电表名">
                                    <span style="font-weight: bold;">{{ electricityRecordObj.eleMeterName }}</span>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="计费方式">
                                    <span style="font-weight: bold;">{{ electricityRecordObj.electricityBillingTypeName }}</span>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="上次抄表时间">
                                    <span v-if="!electricityRecordObj.lastMeterRecordDate">无数据</span>
                                    <span v-else>{{ electricityRecordObj.lastMeterRecordDate | timeDate}}</span>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="本次抄表时间" prop="currentMeterRecordDate">
                                    <el-date-picker
                                            v-model="electricityRecordObj.currentMeterRecordDate"
                                            type="date"
                                            placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="所属月份" prop="recordMonth">
                                    <el-date-picker
                                            v-model="electricityRecordObj.recordMonth"
                                            type="month"
                                            placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <!-- 上次均价计费 -->
                        <el-row :gutter="20" v-if="electricityRecordObj.electricityBillingType == '1'">
                            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                <el-form-item label="上次见表">
                                    <span>{{ electricityRecordObj.lastEleAverageDegree }}&nbsp;kWh</span>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <!-- 上次峰谷计费 -->
                        <el-row :gutter="20" v-if="electricityRecordObj.electricityBillingType == '2'">
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="上次峰值">
                                    <span>{{ electricityRecordObj.lastPeakDegree }}&nbsp;kWh</span>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="上次平值">
                                    <span>{{ electricityRecordObj.lastFlatDegree }}&nbsp;kWh</span>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="上次谷值">
                                    <span>{{ electricityRecordObj.lastTroughDegree }}&nbsp;kWh</span>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <!-- 本次均价计费 -->
                        <el-row :gutter="20" v-if="electricityRecordObj.electricityBillingType == '1'">
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="本次见表" prop="currentEleAverageDegree">
                                    <el-input v-model="electricityRecordObj.currentEleAverageDegree"
                                              @change="computedCurrentDegree(index, indexEle)"
                                              clearable>
                                        <template slot="append">kWh</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <!-- 本次峰谷计费 -->
                        <el-row :gutter="20" v-if="electricityRecordObj.electricityBillingType == '2'">
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="本次峰值" prop="currentPeakDegree">
                                    <el-input v-model="electricityRecordObj.currentPeakDegree"
                                              @change="computedCurrentPeakDegree(index, indexEle)"
                                              clearable>
                                        <template slot="append">kWh</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="本次平值" prop="currentFlatDegree">
                                    <el-input v-model="electricityRecordObj.currentFlatDegree"
                                              @change="computedCurrentFlatDegree(index, indexEle)"
                                              clearable>
                                        <template slot="append">kWh</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <el-form-item label="本次谷值" prop="currentTroughDegree">
                                    <el-input v-model="electricityRecordObj.currentTroughDegree"
                                              @change="computedCurrentTroughDegree(index, indexEle)"
                                              clearable>
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
                <el-button type="primary" @click="submitForm('electricityRecordObj')">确 定</el-button>
            </div>
        </el-dialog>


        <!-- 生成应收 -->
        <el-dialog title="生成应收" style="text-align: center;" :visible.sync="createBillWindow" width="50%">
            <el-form :model="eleReceiptObj" ref="eleReceiptObj" labelSuffix="：" :rules="receiptRules" style="text-align: left;" label-width="130px">
                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="收款截止时间" prop="receivingDate">
                            <el-date-picker
                                    v-model="eleReceiptObj.receivingDate"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="createBillWindow = false">取 消</el-button>
                <el-button type="primary" @click="submitBill('eleReceiptObj')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        components: {
            ElCol,
            ElRow,
        },
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

                pkList: [],// 园区集合
                eleRecordList: [],

                // 查询条件
                ctName: '',// 合同编号
                pkCode: '',// 园区编码
                recordMonth: '',// 月份筛选


                selectedList: [],// 已勾选集合

                electricityRecordObj: {},// 修改抄表记录实体

                dialogFormVisible: false,// 修改弹窗
                createBillWindow: false,// 生成账单弹窗

                eleReceiptObj: {},// 电费应收

                rules: {
                    currentEleAverageDegree: [
                        {required: true, message: '请输入本次见表均价值', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    currentPeakDegree: [
                        {required: true, message: '请输入本次见表波峰值', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    currentFlatDegree: [
                        {required: true, message: '请输入本次见表平值', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    currentTroughDegree: [
                        {required: true, message: '请输入本次见表波谷值', trigger: 'blur'},
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
                        {required: true, message: '请选择所属月份', trigger: 'blur'},
                    ],
                },

                receiptRules: {
                    receivingDate: [
                        {required: true, message: '请选择截止日期', trigger: 'blur'},
                    ],
                }
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

            // 查询二审通过后的合同及缴费记录
            queryContract() {
                let vm = this;

                let param = {
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize,

                    entity: {
                        ctName: vm.ctName,
                        pkCode: vm.pkCode,
                        recordMonth: vm.recordMonth,
                        ctStatus: '4'
                    }
                };

                let options = {
                    method: 'POST',
                    data: param,
                    url: 'eleRecord/query',
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.eleRecordList = response.data.result.data;
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
            editEleRecord(row) {
                let vm = this;

                vm.dialogFormVisible = true;

                vm.electricityRecordObj = JSON.parse(JSON.stringify(row));
            },

            // 提交表单
            submitForm(formName) {
                let vm = this;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params = {
                            entity: vm.electricityRecordObj
                        };

                        let options = {
                            method: 'POST',
                            data: params,
                            url: 'eleRecord/update'
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

            // 生成账单提交
            submitBill(formName) {
                let vm = this;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params = {
                            entity: vm.electricityRecordObj
                        };

                        let options = {
                            method: 'POST',
                            data: params,
                            url: 'eleRecord/update'
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
            }
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
        margin-left: 20px;
    }

    /*园区选择下拉选*/
    .select_park {
        width: 150px;
        margin-left: 20px;
    }
</style>