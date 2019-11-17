<template>
    <div>
        <el-button size="small" type="success" @click="openWindow()" v-if="ctList">
            批量抄电表
        </el-button>
        <el-button size="mini" type="text" @click="openWindow()" v-if="ctObj" style="color: rgb(103, 194, 58);">
            抄电表
        </el-button>

        <!-- 抄电表 -->
        <el-dialog  title="抄电表"
                   class="hasfooter-dialog_wrapper"
                   style="text-align: center;"
                   :visible.sync="recordMeterWindow"
                   append-to-body
                   top="10vh"
                   width="80%"
                   :close-on-press-escape="false"
                   :close-on-click-modal="false">

            <el-card v-for="(item, index) in contractList" :key="index" style="margin-bottom: 20px;" shadow="hover">
                <div slot="header">
                    <el-row :gutter="10">
                        <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" style="text-align: left;">
                            <span style="font-weight: bold;">合同编号：</span>
                            <span style="font-weight: bold; color: red;">{{ item.ctName }}</span>
                        </el-col>
                        <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" style="text-align: left;">
                            <span style="font-weight: bold;">园区：</span>
                            {{ item.pkName }}
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left;">
                            <span style="font-weight: bold;">公司：</span>{{ item.companyName }}
                        </el-col>
                        <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" style="text-align: left;">
                            <span style="font-weight: bold;">联系人：</span>
                            {{ item.relateName }}
                        </el-col>
                    </el-row>
                </div>

                <div v-for="(itemEle, indexEle) in item.meterList" :key="indexEle">
                    <el-form :model="itemEle.electricityRecordEntity" ref="itemEle.electricityRecordEntity"
                             labelSuffix="：" :rules="rules"
                             style="text-align: left;" label-width="130px">
                        <el-card shadow="never" style="margin-bottom: 10px;">
                            <div slot="header">
                                <el-row :gutter="10">
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                        <span style="font-weight: bold;">电表名：</span>
                                        <span style="font-weight: bold;">{{ itemEle.eleMeterName }}</span>
                                    </el-col>
                                </el-row>
                            </div>

                            <el-row :gutter="20">
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="上次抄表时间">
                                        <span v-if="!itemEle.electricityRecordEntity.lastMeterRecordDate">无数据</span>
                                        <span v-else>{{ itemEle.electricityRecordEntity.lastMeterRecordDate | timeDate}}</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="本次抄表时间" prop="currentMeterRecordDate">
                                        <el-date-picker
                                                v-model="itemEle.electricityRecordEntity.currentMeterRecordDate"
                                                type="date"
                                                placeholder="选择日期">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="所属月份" prop="recordMonth">
                                        <el-date-picker
                                                v-model="itemEle.electricityRecordEntity.recordMonth"
                                                type="month"
                                                placeholder="选择月">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                            </el-row>

                            <!-- 上次均价计费 -->
                            <el-row :gutter="20" v-if="itemEle.electricityBillingType == '1'">
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="上次见表">
                                        <span>{{ itemEle.electricityRecordEntity.lastEleAverageDegree }}&nbsp;kWh</span>
                                    </el-form-item>
                                </el-col>

                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="本次见表" prop="currentEleAverageDegree">
                                        <el-input v-model="itemEle.electricityRecordEntity.currentEleAverageDegree"
                                                  @change="computedCurrentDegree(index, indexEle)"
                                                  clearable>
                                            <template slot="append">kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>

                            <!-- 上次峰谷计费 -->
                            <el-row :gutter="20" v-if="itemEle.electricityBillingType == '2'">
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="上次峰值">
                                        <span>{{ itemEle.electricityRecordEntity.lastPeakDegree }}&nbsp;kWh</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="上次平值">
                                        <span>{{ itemEle.electricityRecordEntity.lastFlatDegree }}&nbsp;kWh</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="上次谷值">
                                        <span>{{ itemEle.electricityRecordEntity.lastTroughDegree }}&nbsp;kWh</span>
                                    </el-form-item>
                                </el-col>
                            </el-row>

                            <!-- 本次峰谷计费 -->
                            <el-row :gutter="20" v-if="itemEle.electricityBillingType == '2'">
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="本次峰值" prop="currentPeakDegree">
                                        <el-input v-model="itemEle.electricityRecordEntity.currentPeakDegree"
                                                  @change="computedCurrentPeakDegree(index, indexEle)"
                                                  clearable>
                                            <template slot="append">kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="本次平值" prop="currentFlatDegree">
                                        <el-input v-model="itemEle.electricityRecordEntity.currentFlatDegree"
                                                  @change="computedCurrentFlatDegree(index, indexEle)"
                                                  clearable>
                                            <template slot="append">kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="本次谷值" prop="currentTroughDegree">
                                        <el-input v-model="itemEle.electricityRecordEntity.currentTroughDegree"
                                                  @change="computedCurrentTroughDegree(index, indexEle)"
                                                  clearable>
                                            <template slot="append">kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-card>
                    </el-form>
                </div>
            </el-card>


            <div slot="footer" class="dialog-footer">
                <el-button @click="resetForm('itemEle.electricityRecordEntity')">取 消</el-button>
                <el-button type="primary" @click="submitForm('itemEle.electricityRecordEntity')">确 定</el-button>
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
            ElRow
        },
        props: ['ctList', 'ctObj'],// 接收父组件参数
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
                recordMeterWindow: false,// 抄电表弹窗

                contractArray: [],// 合同集合，父组件批量抄表或单个合同抄表，将数据push到改集合中

                // 合同集合
                contractList: [
                    {
                        meterList: [
                            {
                                electricityRecordEntity: {

                                },
                            }
                        ]
                    }
                ],

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
                        {required: true, message: '请选择抄表月份', trigger: 'change'},
                    ],
                },
            }
        },
        created: function () {

        },
        methods: {
            // 打开抄表窗口
            openWindow() {
                let vm = this;

                vm.contractArray = [];

                // 批量抄表
                if (vm.ctList) {
                    if (vm.ctList.length != 0) {
                        vm.contractArray = vm.ctList;
                    }

                    if (vm.ctList.length === 0) {
                        vm.$alert('请勾选需要批量抄表的合同！', '温馨提示', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                        return;
                    }
                }

                // 单个抄表
                if (vm.ctObj) {
                    vm.contractArray.push(vm.ctObj);
                }

                vm.recordMeterWindow = true;

                let params = {
                    entity: {},
                    contractList: vm.contractArray
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: 'eleMeter/ctCodeEleList',
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.contractList = response.data.result;

                        /**
                         * 某个合同下的电表集合
                         * 1. 将本次抄表的数据赋值给上次抄表数据
                         * 2. 将本次抄表值置为空
                         * 3. 当前抄表页面，不存在实际值，需对数据进行处理
                         */
                        if (this.contractList.length != 0) {
                            for (let i = 0; i < this.contractList.length; i++) {
                                let eleMeter = this.contractList[i].meterList;
                                if (eleMeter.length != 0) {
                                    for (let j = 0; j < eleMeter.length; j++) {
                                        // 电表关联的最新抄表实体数据不为空
                                        if (eleMeter[j].electricityRecordEntity.eleRecordCode != null) {
                                            // 抄表实体数据中电表费用类型为1的情况下，1为均价计费
                                            if (eleMeter[j].electricityRecordEntity.electricityBillingType == 1) {
                                                // 上次均价抄表
                                                eleMeter[j].electricityRecordEntity.lastEleAverageDegree = eleMeter[j].electricityRecordEntity.currentEleAverageDegree;

                                                // 本次抄均价表数据
                                                eleMeter[j].electricityRecordEntity.currentEleAverageDegree = '';
                                            }

                                            // 抄表实体数据中电表费用类型为2的情况下，2为峰平谷计费
                                            if (eleMeter[j].electricityRecordEntity.electricityBillingType == 2) {
                                                // 上次峰、平、谷值
                                                eleMeter[j].electricityRecordEntity.lastPeakDegree = eleMeter[j].electricityRecordEntity.currentPeakDegree;
                                                eleMeter[j].electricityRecordEntity.lastFlatDegree = eleMeter[j].electricityRecordEntity.currentFlatDegree;
                                                eleMeter[j].electricityRecordEntity.lastTroughDegree = eleMeter[j].electricityRecordEntity.currentTroughDegree;
                                                // 本次峰、平、谷值
                                                eleMeter[j].electricityRecordEntity.currentPeakDegree = '';
                                                eleMeter[j].electricityRecordEntity.currentFlatDegree = '';
                                                eleMeter[j].electricityRecordEntity.currentTroughDegree = '';
                                            }

                                            // 上次抄表时间处理
                                            eleMeter[j].electricityRecordEntity.lastMeterRecordDate = eleMeter[j].electricityRecordEntity.currentMeterRecordDate;
                                            eleMeter[j].electricityRecordEntity.currentMeterRecordDate = '';

                                            /**
                                             * 字段处理分别为：高峰使用度数、低峰实用度数、平峰实用度数、实用度数（均价使用度数）
                                             */
                                            eleMeter[j].electricityRecordEntity.peakDegree = '';
                                            eleMeter[j].electricityRecordEntity.troughDegree = '';
                                            eleMeter[j].electricityRecordEntity.flatDegree = '';
                                            eleMeter[j].electricityRecordEntity.realDegree = '';
                                        } else {
                                            eleMeter[j].electricityRecordEntity.eleMeterCode = eleMeter[j].eleMeterCode;
                                            eleMeter[j].electricityRecordEntity.ctCode = eleMeter[j].ctCode;
                                            eleMeter[j].electricityRecordEntity.eleMeterName = eleMeter[j].eleMeterName;
                                            eleMeter[j].electricityRecordEntity.electricityBillingType = eleMeter[j].electricityBillingType;
                                            eleMeter[j].electricityRecordEntity.lastEleAverageDegree = eleMeter[j].initElectricity;// 均价上次见表
                                            eleMeter[j].electricityRecordEntity.lastPeakDegree = eleMeter[j].electricityPeak;// 上次峰值
                                            eleMeter[j].electricityRecordEntity.lastFlatDegree = eleMeter[j].electricityFlat;// 上次平值
                                            eleMeter[j].electricityRecordEntity.lastTroughDegree = eleMeter[j].electricityTrough;// 上次谷值
                                        }
                                    }
                                }
                            }
                        }
                    }
                }).catch(error => {
                    this.$alert('页面：查询电表失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },

            // 均价计费，计算本次见表度数
            computedCurrentDegree(index, indexEle) {
                let electricityRecordEntity = this.contractList[index].meterList[indexEle].electricityRecordEntity;
                let meterEntity = this.contractList[index].meterList[indexEle];

                if (electricityRecordEntity.currentEleAverageDegree < electricityRecordEntity.lastEleAverageDegree) {
                    this.commonHint();
                    electricityRecordEntity.currentEleAverageDegree = '';
                } else {
                    let result = Number(electricityRecordEntity.currentEleAverageDegree) - meterEntity.initElectricity;
                    if (meterEntity.multiple != 0) {
                        this.contractList[index].meterList[indexEle].electricityRecordEntity.realDegree = result * meterEntity.multiple;
                    } else {
                        this.contractList[index].meterList[indexEle].electricityRecordEntity.realDegree = result;
                    }
                }
            },
            // 峰平谷计费，计算本次峰值度数
            computedCurrentPeakDegree(index, indexEle) {
                let electricityRecordEntity = this.contractList[index].meterList[indexEle].electricityRecordEntity;
                let meterEntity = this.contractList[index].meterList[indexEle];

                if (electricityRecordEntity.currentPeakDegree < electricityRecordEntity.lastPeakDegree) {
                    this.commonHint();
                    electricityRecordEntity.currentPeakDegree = '';
                } else {
                    let result = Number(electricityRecordEntity.currentPeakDegree) - electricityRecordEntity.lastPeakDegree;
                    if (meterEntity.multiple != 0) {
                        this.contractList[index].meterList[indexEle].electricityRecordEntity.peakDegree = result * meterEntity.multiple;
                    } else {
                        this.contractList[index].meterList[indexEle].electricityRecordEntity.peakDegree = result;
                    }
                }
            },
            // 峰平谷计费，计算本次平值度数
            computedCurrentFlatDegree(index, indexEle) {
                let electricityRecordEntity = this.contractList[index].meterList[indexEle].electricityRecordEntity;
                let meterEntity = this.contractList[index].meterList[indexEle];

                if (electricityRecordEntity.currentFlatDegree < electricityRecordEntity.lastFlatDegree) {
                    this.commonHint();
                    electricityRecordEntity.currentFlatDegree = '';
                } else {
                    let result = Number(electricityRecordEntity.currentFlatDegree) - electricityRecordEntity.lastFlatDegree;
                    if (meterEntity.multiple != 0) {
                        this.contractList[index].meterList[indexEle].electricityRecordEntity.flatDegree = result * meterEntity.multiple;
                    } else {
                        this.contractList[index].meterList[indexEle].electricityRecordEntity.flatDegree = result;
                    }
                }
            },
            // 峰平谷计费，计算本次谷值度数
            computedCurrentTroughDegree(index, indexEle) {
                let electricityRecordEntity = this.contractList[index].meterList[indexEle].electricityRecordEntity;
                let meterEntity = this.contractList[index].meterList[indexEle];

                if (electricityRecordEntity.currentTroughDegree < electricityRecordEntity.lastTroughDegree) {
                    this.commonHint();
                    electricityRecordEntity.currentTroughDegree = '';
                } else {
                    let result = Number(electricityRecordEntity.currentTroughDegree) - electricityRecordEntity.lastTroughDegree;
                    if (meterEntity.multiple != 0) {
                        this.contractList[index].meterList[indexEle].electricityRecordEntity.troughDegree = result * meterEntity.multiple;
                    } else {
                        this.contractList[index].meterList[indexEle].electricityRecordEntity.troughDegree = result;
                    }
                }
            },
            commonHint() {
                this.$message({
                    showClose: true,
                    message: '本次见表度数小于上次见表度数，请重新填写！',
                    type: 'error'
                });
            },

            // 抄表提交
            submitForm(formName) {
                let flag = true;

                let sum = 0;// 电表数

                for (let i = 0; i < this.contractList.length; i++) {
                    if (this.contractList[i].meterList) {
                        sum = sum + this.contractList[i].meterList.length;
                    }
                }

                for (let j = 0; j < sum; j++) {
                    let refs = this.$refs[formName][j];
                    refs.validate((valid) => {
                        if (!valid) {
                            flag = false;
                            this.$message({
                                showClose: true,
                                message: '填写数据不完整',
                                type: 'warning'
                            });
                        }
                    });
                }

                if (flag == true) {
                    let params = {
                        entity: {},
                        contractList: this.contractList
                    };

                    let options = {
                        method: 'POST',
                        data: params,
                        url: 'eleRecord/batchCreate'
                    };

                    this.$ajax(
                        options
                    ).then(response => {
                        if (response) {
                            this.$message({
                                showClose: true,
                                message: '保存抄表数据成功',
                                type: 'success'
                            });
                            this.resetForm(formName);
                        }
                    }).catch(error => {
                        this.$alert('页面：保存抄表数据失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    })
                }
            },

            // 表单重置
            resetForm(formName) {
                let sum = 0;// 电表数
                for (let i = 0; i < this.contractList.length; i++) {
                    if (this.contractList[i].meterList) {
                        sum = sum + this.contractList[i].meterList.length;
                    }
                }

                for (let j = 0; j < sum; j++) {
                    this.$refs[formName][j].resetFields();
                }
                this.recordMeterWindow = false;
            },
        }
    }
</script>

<style scoped>
    .hasfooter-top10-dialog /deep/ .el-dialog__body {
        height: 63vh;
    }
</style>