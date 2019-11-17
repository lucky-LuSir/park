<template>
    <div>
        <el-button size="small" type="success" @click="openWindow()" v-if="ctList">
            批量抄水表
        </el-button>
        <el-button size="mini" type="text" @click="openWindow()" v-if="ctObj" style="color: rgb(103, 194, 58);">
            抄水表
        </el-button>

        <!-- 抄电表 -->
        <el-dialog title="抄水表"
                   class="hasfooter-dialog_wrapper"
                   top="10vh"
                   style="text-align: center;"
                   :visible.sync="recordMeterWindow"
                   append-to-body
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

                <div v-for="(itemEle, indexEle) in item.waterMeterList" :key="indexEle">
                    <el-form :model="itemEle.waterRecordEntity" ref="itemEle.waterRecordEntity"
                             labelSuffix="：" :rules="rules"
                             style="text-align: left;" label-width="130px">
                        <el-card shadow="never" style="margin-bottom: 10px;">
                            <div slot="header">
                                <el-row :gutter="10">
                                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                        <span style="font-weight: bold;">水表名：</span>
                                        <span style="font-weight: bold;">{{ itemEle.waterMeterName }}</span>
                                    </el-col>
                                </el-row>
                            </div>

                            <el-row :gutter="20">
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="计费方式">
                                        <span>{{ itemEle.waterBillingType == '1' ? '均价计费' : '抄表计费' }}</span>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row :gutter="20">
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="上次抄表时间">
                                        <span v-if="!itemEle.waterRecordEntity.lastMeterRecordDate">无数据</span>
                                        <span v-else>{{ itemEle.waterRecordEntity.lastMeterRecordDate | timeDate}}</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="本次抄表时间" prop="currentMeterRecordDate">
                                        <el-date-picker
                                                v-model="itemEle.waterRecordEntity.currentMeterRecordDate"
                                                type="date"
                                                placeholder="选择日期">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="所属月份" prop="recordMonth">
                                        <el-date-picker
                                                v-model="itemEle.waterRecordEntity.recordMonth"
                                                type="month"
                                                placeholder="选择月">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                            </el-row>

                            <!-- 上次抄表计费 -->
                            <el-row :gutter="20" v-if="itemEle.waterBillingType == '2'">
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="水费单价">
                                        <span>{{ itemEle.waterRecordEntity.waterUnitPrice }}&nbsp;元/吨</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="上次见表">
                                        <span>{{ itemEle.waterRecordEntity.lastBillingWater }}&nbsp;吨</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="本次见表" prop="currentBillingWater">
                                        <el-input v-model="itemEle.waterRecordEntity.currentBillingWater"
                                                  @change="computedCurrentBillingWater(index, indexEle)"
                                                  clearable>
                                            <template slot="append">kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>

                            <!-- 本次均价计费 -->
                            <el-row :gutter="20" v-if="itemEle.waterBillingType == '1'">
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="均摊计费" prop="waterAmount">
                                        <el-input v-model="itemEle.waterRecordEntity.waterAmount" clearable>
                                            <template slot="append">元</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-card>
                    </el-form>
                </div>
            </el-card>

            <div slot="footer" class="dialog-footer">
                <el-button @click="resetForm('itemEle.waterRecordEntity')">取 消</el-button>
                <el-button type="primary" @click="submitForm('itemEle.waterRecordEntity')">确 定</el-button>
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
                recordMeterWindow: false,// 抄表弹窗

                contractArray: [],// 合同集合，父组件批量抄表或单个合同抄表，将数据push到改集合中

                // 合同集合
                contractList: [
                    {
                        waterMeterList: [
                            {
                                waterRecordEntity: {

                                },
                            }
                        ]
                    }
                ],

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
                    url: 'waterMeter/queryWaterMeterListByCtCode',
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.contractList = response.data.result;

                        /**
                         * 某个合同下的水表集合
                         * 1. 将本次抄表的数据赋值给上次抄表数据
                         * 2. 将本次抄表值置为空
                         * 3. 当前抄表页面，不存在实际值，需对数据进行处理
                         */
                        if (this.contractList.length != 0) {
                            for (let i = 0; i < this.contractList.length; i++) {
                                let waterMeterList = this.contractList[i].waterMeterList;
                                if (waterMeterList.length != 0) {
                                    for (let j = 0; j < waterMeterList.length; j++) {
                                        // 水表关联的最新抄表实体数据不为空
                                        if (waterMeterList[j].waterRecordEntity.waterRecordCode != null) {
                                            // 抄表实体数据中水表费用类型为1的情况下，1为均价计费
                                            if (waterMeterList[j].waterRecordEntity.waterBillingType == 1) {
                                                waterMeterList[j].waterRecordEntity.waterAmount = '';// 水费金额（均摊计费）
                                            }

                                            // 抄表实体数据中水表费用类型为2的情况下，2为抄表计费
                                            if (waterMeterList[j].waterRecordEntity.waterBillingType == 2) {
                                                // 上次抄表吨位
                                                waterMeterList[j].waterRecordEntity.lastBillingWater = waterMeterList[j].waterRecordEntity.currentBillingWater;
                                                // 本次抄表吨位
                                                waterMeterList[j].waterRecordEntity.currentBillingWater = '';
                                            }

                                            // 上次抄表时间处理
                                            waterMeterList[j].waterRecordEntity.lastMeterRecordDate = waterMeterList[j].waterRecordEntity.currentMeterRecordDate;
                                            waterMeterList[j].waterRecordEntity.currentMeterRecordDate = '';

                                            // 字段处理分别为：实用吨数
                                            waterMeterList[j].waterRecordEntity.realWater = '';
                                        } else {
                                            waterMeterList[j].waterRecordEntity.waterMeterCode = waterMeterList[j].waterMeterCode;
                                            waterMeterList[j].waterRecordEntity.waterMeterName = waterMeterList[j].waterMeterName;
                                            waterMeterList[j].waterRecordEntity.ctCode = waterMeterList[j].ctCode;
                                            waterMeterList[j].waterRecordEntity.waterBillingType = waterMeterList[j].waterBillingType;

                                            // 水表抄表计费
                                            waterMeterList[j].waterRecordEntity.waterUnitPrice = waterMeterList[j].waterUnitPrice;// 水费单价（抄表计费）
                                            waterMeterList[j].waterRecordEntity.lastBillingWater = waterMeterList[j].initWater;// 初始电表（抄表计费）
                                            waterMeterList[j].waterRecordEntity.waterAmount = '';// 水费金额（均摊计费）
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
            computedCurrentBillingWater(index, indexEle) {
                let waterRecordEntity = this.contractList[index].waterMeterList[indexEle].waterRecordEntity;
                let waterMeter = this.contractList[index].waterMeterList[indexEle];

                if (waterRecordEntity.currentBillingWater < waterRecordEntity.lastBillingWater) {
                    this.$message({
                        showClose: true,
                        message: '本次见表度数小于上次见表吨数，请重新填写！',
                        type: 'error'
                    });
                    waterRecordEntity.currentBillingWater = '';
                } else {
                    let result = Number(waterRecordEntity.currentBillingWater) - waterMeter.initWater;
                    this.contractList[index].waterMeterList[indexEle].waterRecordEntity.realWater = result;
                }
            },

            // 抄表提交
            submitForm(formName) {
                let flag = true;

                let sum = 0;// 水表数

                for (let i = 0; i < this.contractList.length; i++) {
                    if (this.contractList[i].waterMeterList) {
                        sum = sum + this.contractList[i].waterMeterList.length;
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
                        url: 'waterRecord/batchCreate'
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
                this.contractArray = [];

                let sum = 0;// 电表数
                for (let i = 0; i < this.contractList.length; i++) {
                    if (this.contractList[i].waterMeterList) {
                        sum = sum + this.contractList[i].waterMeterList.length;
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

</style>