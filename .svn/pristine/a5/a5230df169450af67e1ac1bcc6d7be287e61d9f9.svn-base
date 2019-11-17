<template>
    <div class="has-position_root">
        <!-- 悬浮框 -->
        <div class="has-position_header">
            <div class="search_buttons">
                <span>水电表信息 - {{companyName}}</span>
            </div>
        </div>

        <!-- 电表信息框 -->
        <!-- 初始电表信息 -->
        <div class="contract_box">
            <el-tabs class="meter_tabs" v-model="activeName">
                <el-tab-pane class="meter_tabs_pane" label="电表管理" name="1">
                    <div class="meter_info_list_add">
                        <el-button type="primary" icon="el-icon-plus" round class="billingDetail_button" @click="addMeter"
                                   size="small">添加电表
                        </el-button>
                    </div>
                    <div class="meter_info_list" v-for="(item,index) in meterList" :key="index">
                        <el-card shadow="hover">
                            <el-avatar v-if="item.eleIsOpen" shape="circle" :size="100" fit="scale-down" class="el_card_img" style="background-color: #01cd61;">
                                <span style="font-size: 28px;">启用</span>
                            </el-avatar>
                            <el-avatar v-if="!item.eleIsOpen" shape="circle" :size="100" fit="scale-down" class="el_card_img" style="background-color: #ff4949;">
                                <span style="font-size: 28px;">禁用</span>
                            </el-avatar>
                            <div class="meter_info_card">
                                <el-row class="el_col_style">
                                    <el-col :span="6">
                                        电表名称：{{item.eleMeterName}}
                                    </el-col>

                                    <el-col :span="6">
                                        倍率：{{item.multiple}} 倍
                                    </el-col>
                                    <el-col :span="6">
                                        计费方式： {{item.electricityBillingTypeName}}
                                    </el-col>
                                    <el-col :span="6">
                                        <el-button type="primary" icon="el-icon-edit" @click="updateEleMeterButton(item)" size="small">
                                            修改
                                        </el-button>
                                        <el-button type="primary" icon="el-icon-delete" @click="delMeter(item)" size="small">
                                            删除
                                        </el-button>
                                    </el-col>
                                </el-row>
                                <div v-if="item.electricityBillingType == 1">
                                    <el-row class="el_col_style">
                                        <el-col :span="6">
                                            均价单价： {{item.electricityAveragePrice}} 元/kWh
                                        </el-col>
                                    </el-row>

                                    <el-row class="el_col_style">
                                        <el-col :span="18">
                                            初始电表： {{item.initElectricity}} kWh
                                        </el-col>
                                        <el-col :span="6">
                                            是否启用：
                                            <el-switch
                                                    v-model="item.eleIsOpen"
                                                    active-color="#13ce66"
                                                    inactive-color="#ff4949"
                                                    @change="eleIsOpenOff($event,item.id,item.eleMeterCode,item.eleMeterName)">
                                            </el-switch>
                                        </el-col>
                                    </el-row>
                                </div>
                                <div v-else>
                                    <el-row class="el_col_style">
                                        <el-col :span="6">
                                            峰值单价： {{item.electricityPeakPrice}} 元/kWh
                                        </el-col>
                                        <el-col :span="6">
                                            平值单价： {{item.electricityFlatPrice}} 元/kWh
                                        </el-col>
                                        <el-col :span="6">
                                            谷值单价： {{item.electricityTroughPrice}} 元/kWh
                                        </el-col>
                                    </el-row>

                                    <el-row class="el_col_style">
                                        <el-col :span="6">
                                            初始峰值： {{item.electricityPeak}} kWh
                                        </el-col>
                                        <el-col :span="6">
                                            初始平值： {{item.electricityFlat}} kWh
                                        </el-col>
                                        <el-col :span="6">
                                            初始谷值： {{item.electricityTrough}} kWh
                                        </el-col>
                                        <el-col :span="6">
                                            是否启用：
                                            <el-switch
                                                    v-model="item.eleIsOpen"
                                                    active-color="#13ce66"
                                                    inactive-color="#ff4949"
                                                    @change="eleIsOpenOff($event,item.id,item.eleMeterCode,item.eleMeterName)">
                                            </el-switch>
                                        </el-col>
                                    </el-row>

                                </div>
                            </div>

                        </el-card>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="水表管理" name="2">
                    <div class="meter_info_list_add">
                        <el-button type="primary" icon="el-icon-plus" round class="billingDetail_button" @click="addWaterMeter"
                                   size="small">添加水表
                        </el-button>
                    </div>
                    <div class="meter_info_list" v-for="(waterItem,index) in waterMeterList"  :key="index">
                        <el-card shadow="hover">
                            <el-avatar v-if="waterItem.waterIsOpen" shape="circle" :size="100" fit="scale-down" class="el_card_img" style="background-color: #01cd61;">
                                <span style="font-size: 28px;">启用</span>
                            </el-avatar>
                            <el-avatar v-if="!waterItem.waterIsOpen" shape="circle" :size="100" fit="scale-down" class="el_card_img" style="background-color: #ff4949;">
                                <span style="font-size: 28px;">禁用</span>
                            </el-avatar>
                            <div class="meter_info_card">
                                <div class="contract_box_down">
                                    <el-row>
                                        <el-col :span="12">
                                            水表名称:{{waterItem.waterMeterName}}
                                        </el-col>
                                            <div class="contract_box_down_up">
                                                <el-button type="primary" icon="el-icon-edit" class="billingDetail_button"
                                                           @click="updateWaterMeterButton(waterItem)" size="small">修改
                                                </el-button>
                                                <el-button type="primary" icon="el-icon-delete"
                                                           class="billingDetail_button" @click="delWaterMeter(waterItem)" size="small">
                                                    删除
                                                </el-button>
                                            </div>
                                        </el-col>
                                    </el-row>
                                    <el-row>
                                        <el-col :span="10">
                                            计费方式:{{waterItem.waterBillingTypeName}}
                                        </el-col>
                                    </el-row>
                                    <div v-if="waterItem.waterBillingType == 1">
                                        <el-row>
                                            <el-col :span="12">
                                                均摊金额:{{waterItem.waterAmount}}元
                                            </el-col>
                                            <el-col :span="6">
                                                是否启用：
                                                <el-switch
                                                        v-model="waterItem.waterIsOpen"
                                                        active-color="#13ce66"
                                                        inactive-color="#ff4949"
                                                        @change="waterIsOpenOff($event,waterItem.id,waterItem.waterMeterCode,waterItem.waterMeterName)">
                                                </el-switch>
                                            </el-col>
                                        </el-row>
                                    </div>
                                    <div v-else>
                                        <el-row>
                                            <el-col :span="6">
                                                水费单价:{{waterItem.waterUnitPrice}}元/吨
                                            </el-col>
                                            <el-col :span="6">
                                                初始见表:{{waterItem.initWater}}吨
                                            </el-col>
                                            <el-col :span="6">
                                                是否启用：
                                                <el-switch
                                                        v-model="waterItem.waterIsOpen"
                                                        active-color="#13ce66"
                                                        inactive-color="#ff4949"
                                                        @change="waterIsOpenOff($event,waterItem.id,waterItem.waterMeterCode,waterItem.waterMeterName)">
                                                </el-switch>
                                            </el-col>
                                        </el-row>
                                    </div>

                                </div>
                            </div>
                        </el-card>

                    </div>
                </el-tab-pane>
            </el-tabs>
        </div>

        <!-- 电表操作抽屉 -->
        <el-drawer
                :title="eleDrawerTitle"
                size="50%"
                :visible.sync="showEleMeterDrawer"
                :wrapperClosable="false">
            <el-form :model="meterObj" ref="meterObj" :rules="rules" label-suffix=":" label-width="90px">
                <div class="contract_box_down">
                    <el-row :gutter="30">
                        <el-col :span="12">
                            <div class="contract_box_down_up">
                                <el-form-item label="电表名称" prop="eleMeterName">
                                    <el-input v-model="meterObj.eleMeterName"
                                              size="small" placeholder="请输入"
                                              class="contract_box_down_input">
                                    </el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row :gutter="30">
                        <el-col :span="12">
                            <div class="contract_box_down_up">
                                <el-form-item label="倍率" prop="multiple">
                                    <el-input v-model="meterObj.multiple"
                                              size="small" placeholder="请输入"
                                              class="contract_box_down_input">
                                    </el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <div class="contract_box_down_up">
                                <el-form-item label="计费方式" prop="electricityBillingType">
                                    <el-radio-group v-model="meterObj.electricityBillingType">
                                        <el-radio label="1">均价计费</el-radio>
                                        <el-radio label="2">峰谷计费</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <div v-if="meterObj.electricityBillingType == 1">
                        <el-row>
                            <el-col :span="12">
                                <div class="contract_box_down_up">
                                    <el-form-item label="均价单价" prop="electricityAveragePrice">
                                        <el-input v-model="meterObj.electricityAveragePrice"
                                                  size="small" placeholder="请输入"
                                                  class="contract_box_down_input">
                                            <template slot="append">元/kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="12">
                                <div class="contract_box_down_up">
                                    <el-form-item label="初始电表" prop="initElectricity">
                                        <el-input size="small" v-model="meterObj.initElectricity"
                                                  placeholder="请输入" class="contract_box_down_input">
                                            <template slot="append">kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                    </div>
                    <div v-else>
                        <el-row>
                            <el-col :span="12">
                                <div class="contract_box_down_up">
                                    <el-form-item label="峰值单价" prop="electricityPeakPrice">
                                        <el-input size="small" v-model="meterObj.electricityPeakPrice"
                                                  placeholder="请输入" class="contract_box_down_input">
                                            <template slot="append">元/kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="12">
                                <div class="contract_box_down_up">
                                    <el-form-item label="初始峰值" prop="electricityPeak">
                                        <el-input size="small" v-model="meterObj.electricityPeak"
                                                  placeholder="请输入" class="contract_box_down_input">
                                            <template slot="append">kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <div class="contract_box_down_up">
                                    <el-form-item label="平值单价" prop="electricityFlatPrice">
                                        <el-input size="small" v-model="meterObj.electricityFlatPrice"
                                                  placeholder="请输入" class="contract_box_down_input">
                                            <template slot="append">元/kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="12">
                                <div class="contract_box_down_up">
                                    <el-form-item label="初始平值" prop="electricityFlat">
                                        <el-input size="small" v-model="meterObj.electricityFlat"
                                                  placeholder="请输入" class="contract_box_down_input">
                                            <template slot="append">kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <div class="contract_box_down_up">
                                    <el-form-item label="谷值单价" prop="electricityTroughPrice">
                                        <el-input size="small" v-model="meterObj.electricityTroughPrice"
                                                  placeholder="请输入" class="contract_box_down_input">
                                            <template slot="append">元/kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="12">
                                <div class="contract_box_down_up">
                                    <el-form-item label="初始谷值" prop="electricityTrough">
                                        <el-input size="small" v-model="meterObj.electricityTrough"
                                                  placeholder="请输入" class="contract_box_down_input">
                                            <template slot="append">kWh</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                    <div class="contract_box_down_up">
                        <el-button v-if="!meterObj.eleMeterCode" type="primary" icon="el-icon-check" @click="saveMeter('meterObj')"
                                   size="small">提交
                        </el-button>
                        <el-button v-if="meterObj.eleMeterCode" type="primary" icon="el-icon-check" @click="updateMeter('meterObj')"
                                   size="small">修改
                        </el-button>
                        <el-button icon="el-icon-close" @click="showEleMeterDrawer=false"
                                   size="small" >取消
                        </el-button>
                    </div>
                </div>
            </el-form>
        </el-drawer>

        <!-- 水表操作抽屉 -->
        <el-drawer
                :title="waterDrawerTitle"
                size="50%"
                :visible.sync="showWaterMeterDrawer"
                :wrapperClosable="false">
            <el-form :model="waterObj" ref="waterObj" :rules="rules" label-suffix=":" label-width="90px">
                <div class="contract_box_down">
                    <el-row :gutter="30">
                        <el-col :span="12">
                            <div class="contract_box_down_up">
                                <el-form-item label="水表名称" prop="waterMeterName">
                                    <el-input v-model="waterObj.waterMeterName"
                                              size="small" placeholder="请输入"
                                              class="contract_box_down_input">
                                    </el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row :gutter="30">
                        <el-col :span="12">
                            <div class="contract_box_down_up">
                                <el-form-item label="计费方式" prop="waterBillingType">
                                    <el-radio-group v-model="waterObj.waterBillingType">
                                        <el-radio label="1">均摊计费</el-radio>
                                        <el-radio label="2">抄表计费</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <div v-if="waterObj.waterBillingType == 1">
                        <el-row :gutter="30">
                            <el-col :span="12">
                                <div class="contract_box_down_up">
                                    <el-form-item label="均摊金额" prop="waterAmount">
                                        <el-input v-model="waterObj.waterAmount"
                                                  size="small" placeholder="请输入"
                                                  class="contract_box_down_input">
                                            <template slot="append">元</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                    <div v-else>
                        <el-row :gutter="30">
                            <el-col :span="12">
                                <div class="contract_box_down_up">
                                    <el-form-item label="水费单价" prop="waterUnitPrice">
                                        <el-input size="small" v-model="waterObj.waterUnitPrice"
                                                  placeholder="请输入" class="contract_box_down_input">
                                            <template slot="append">元/吨</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="12">
                                <div class="contract_box_down_up">
                                    <el-form-item label="初始见表" prop="initWater">
                                        <el-input size="small" v-model="waterObj.initWater"
                                                  placeholder="请输入" class="contract_box_down_input">
                                            <template slot="append">吨</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                    <div class="contract_box_down_up">
                        <el-button v-if="!waterObj.waterMeterCode" type="primary" icon="el-icon-check"
                                   @click="saveWaterMeter('waterObj')" size="small">提交
                        </el-button>
                        <el-button v-if="waterObj.waterMeterCode" type="primary" icon="el-icon-edit" class="billingDetail_button"
                                   @click="updateWaterMeter('waterObj')" size="small">修改
                        </el-button>
                        <el-button icon="el-icon-close"
                                   class="billingDetail_button" @click="showWaterMeterDrawer = false" size="small">
                            取消
                        </el-button>
                    </div>

                </div>
            </el-form>
        </el-drawer>

    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        //接受父组件传过来的参数
        props: ['paramsObj'],

        components: {ElRow},
        created: function () {
            let vm = this;

            if(vm.paramsObj) {
                vm.companyName = vm.paramsObj.companyName;
            } else {
                vm.$alert('页面:未传入合同编码', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
                return;
            }

            //查询电表信息方法
            this.queryMeterList();

            //页面加载查询水表信息
            this.queryWaterMeterList();

        },
        data() {
            //校验数字0～99999999.99
            var checkNumber = (rule, value, callback) => {
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
            //校验数字只能是正整数或小数
            var checkIntOrFloat = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(value))) {
                        callback(new Error('只能输入正整数或小数'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            return {

                showEleMeterDrawer:false,//控制电表抽屉
                showWaterMeterDrawer:false,//控制水表抽屉

                eleDrawerTitle:"",//电表抽屉的标题
                waterDrawerTitle:"",//水表抽屉标题

                meterObj: {
                    eleMeterCode: "",
                    eleMeterName: "",//电表名称
                    ctCode: this.paramsObj.ctCode,
                    multiple: "",//倍率
                    electricityBillingType: "2",//计费方式
                    electricityPeak: "",//初始峰值
                    electricityFlat: "",//初始平值
                    electricityTrough: "",//初始谷值
                    electricityPeakPrice: "",//电费波峰单价
                    electricityFlatPrice: "",//电费平峰单价
                    electricityTroughPrice: "",//电费波谷单价
                    electricityAveragePrice: "",//均价单价
                    initElectricity: "",//初始均价值
                    eleIsOpen:false, //电表是否启用
                },
                waterObj: {
                    /*水表信息*/
                    waterMeterCode:"",//水表编码
                    waterMeterName:"",//水表名称
                    ctCode: this.paramsObj.ctCode,
                    waterTime: "",//水表上次和本次时间
                    lastBillingTime: "",//上一次抄表时间
                    currentBillingTime: "",//本次抄表时间
                    lastBillingWater: "",//上一次抄表吨位
                    currentBillingWater: "",//本次抄表吨位
                    realWater: 0,
                    waterBillingType: "",//水表计费方式
                    waterAmount: "",//均摊时水费

                    initWater: "",//初始电表值

                    waterUnitPrice: "",//水费单价
                    waterIsOpen:false,//水表是否启用
                },
                rules: {
                    eleMeterName: [
                        {required: true, message: '请输入电表名称', trigger: 'blur'},
                    ],
                    multiple: [
                        {required: true, message: '请输入电表倍率', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    electricityBillingType: [
                        {required: true, message: '请选择计费方式', trigger: 'blur'}
                    ],
                    electricityPeak: [
                        {required: true, message: '请输入初始峰值', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    electricityFlat: [
                        {required: true, message: '请输入初始平值', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    electricityTrough: [
                        {required: true, message: '请输入初始谷值', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    electricityPeakPrice: [
                        {required: true, message: '请输入电费波峰单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    electricityFlatPrice: [
                        {required: true, message: '请输入电费平峰单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    electricityTroughPrice: [
                        {required: true, message: '请输入电费波谷单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    electricityAveragePrice: [
                        {required: true, message: '请输入均价单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    initElectricity: [
                        {required: true, message: '请输入初始均价值', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
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
                    eleTime: [
                        {required: true, message: '抄表日期不正常', trigger: 'blur'},
                    ],
                    currentBillingWater: [
                        {required: true, message: '请输入本次水表值', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    waterMeterName: [
                        {required: true, message: '请输入水表名称', trigger: 'blur'},
                    ],
                    waterBillingType: [
                        {required: true, message: '请选择计费方式', trigger: 'blur'},
                    ],
                    waterAmount: [
                        {required: true, message: '请输入均摊金额', trigger: 'blur'},
                    ],
                    waterUnitPrice: [
                        {required: true, message: '请输入水费单价', trigger: 'blur'},
                    ],
                    initWater: [
                        {required: true, message: '请输入初始电表', trigger: 'blur'},
                    ],
                },
                pickerOptions: {
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
                companyName:"",
                meterList: [],//电表信息集合
                waterMeterList:[],//水表信息集合

                activeName:"1",//表信息页签中的卡片版

            }
        },
        methods: {

            //查询电表信息方法
            queryMeterList() {
                let vm = {};
                vm = this;

                let param = {};
                param.entity = {};

                param.entity.ctCode = vm.paramsObj.ctCode;

                //查询该合同下的电表
                let option = {
                    method: 'POST',
                    data: param,
                    url: 'eleMeter/list'
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (response) {
                        vm.meterList = response.data.result;
                    }
                }).catch(function (error) {
                    vm.$alert('页面:查询合同下电表信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            /*************添加电表页签****************/
            //添加电表
            addMeter(){
                this.meterObj = {
                    eleMeterCode: "",
                    eleMeterName: "",//电表名称
                    ctCode: this.paramsObj.ctCode,
                    multiple: "",//倍率
                    electricityBillingType: "2",//计费方式
                    electricityPeak: "",//初始峰值
                    electricityFlat: "",//初始平值
                    electricityTrough: "",//初始谷值
                    electricityPeakPrice: "",//电费波峰单价
                    electricityFlatPrice: "",//电费平峰单价
                    electricityTroughPrice: "",//电费波谷单价
                    electricityAveragePrice: "",//均价单价
                    initElectricity: "",//均价初始电表
                };

                this.showEleMeterDrawer = true;
                this.eleDrawerTitle = "新增电表";
            },
            //保存电表信息
            saveMeter(subForm) {
                let vm = this;
                let param = {};
                let refs = vm.$refs[subForm];
                refs.validate((valid) => {
                    if (valid) {
                        param.entity = this.meterObj;
                        let option = {
                            method: "POST",
                            data: param,
                            url: "eleMeter/create"
                        };
                        this.$ajax(
                            option
                        ).then(function (response) {
                            if (response) {
                                vm.queryMeterList();
                                vm.$message({
                                    showClose: true,
                                    message: '新增电表信息成功',
                                    type: 'success'
                                });
                                vm.showEleMeterDrawer = false;
                            }
                        }).catch(function () {
                            vm.$alert('页面:新增电表信息失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    } else {
                        vm.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                });
            },

            //点击修改电表按钮
            updateEleMeterButton(item) {
                let vm = this;
                let obj = JSON.parse(JSON.stringify(item));
                vm.meterObj = obj;
                vm.showEleMeterDrawer = true;
                vm.eleDrawerTitle = "修改电表";

            },

            //修改电表信息
            updateMeter(subForm) {
                let vm = this;

                vm.$refs[subForm].validate((valid) => {
                    if(valid) {
                        let param = {};
                        param.entity = vm.meterObj;
                        this.$confirm('此操作将修改"' + param.entity.eleMeterName + '"电表信息, 是否继续?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(() => {
                            //修改电表信息
                            let option = {
                                method: 'POST',
                                data: param,
                                url: 'eleMeter/update'
                            };
                            this.$ajax(
                                option
                            ).then(function (response) {
                                if (response) {
                                    vm.queryMeterList();
                                    vm.$message({
                                        showClose: true,
                                        message: '修改"' + param.entity.eleMeterName + '"电表信息成功',
                                        type: 'success'
                                    });
                                    vm.showEleMeterDrawer = false;
                                }
                            }).catch(function (error) {
                                vm.$alert('页面:修改' + param.entity.eleMeterName + '电表信息失败', '系统异常', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                    }
                                });
                            });
                        }).catch(() => {
                            vm.$message({
                                type: 'info',
                                message: '已取消修改'
                            });
                        });
                    }
                });

            },

            //删除电表
            delMeter(item) {
                let vm = this;
                vm.$confirm('此操作将删除'+item.eleMeterName+'电表, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //执行删除操作
                    let param = {};
                    param.entity = item;
                    let meterName = param.entity.eleMeterName;
                    let option = {
                        method: 'POST',
                        data: param,
                        url: 'eleMeter/delete'
                    };
                    this.$ajax(
                        option
                    ).then(function (response) {
                        if (response) {
                            vm.queryMeterList();
                            vm.$message({
                                showClose: true,
                                message: '删除' + meterName + '电表信息成功',
                                type: 'success'
                            });
                        }
                    }).catch(function (error) {
                        vm.$alert('页面:删除' + meterName + '电表信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }).catch(() => {
                    vm.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

            //启停电表
            eleIsOpenOff(val,id,eleMeterCode,eleMeterName) {
                let vm = this;

                let msg = "";

                if(val) {
                    msg = "启用";
                }else {
                    msg = "禁用";
                }

                let param = {};
                param.entity = {
                    id:id,
                    eleMeterCode:eleMeterCode,
                    eleIsOpen:val
                };

                let option = {
                    method: 'POST',
                    data: param,
                    url: 'eleMeter/changeEleIsOpen'
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if(response) {
                        vm.$message({
                            showClose: true,
                            message: msg+eleMeterName+'电表成功',
                            type: 'success'
                        });
                        vm.queryMeterList();
                    }

                }).catch(function (error) {
                    vm.$alert('页面:操作启用禁用开关失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            /*******************************水表操作*****************************************/

            //添加水表按钮
            addWaterMeter() {
                this.waterObj = {
                    waterMeterCode:"",//水表编号
                    ctCode:this.paramsObj.ctCode,//合同编号
                    waterMeterName:"",//水表名称
                    waterBillingType:"2",//计费方式
                    waterUnitPrice:"",//水费单价（抄表计费）
                    initWater:"",//初始电表（抄表计费）
                    waterAmount:"",//水费金额（均摊计费）
                };
                this.waterDrawerTitle = "新增水表";
                this.showWaterMeterDrawer = true;
            },

            //查看是否存在水表
            selectWaterMeter() {
                let vm = this;
                let param = {};
                param.entity = {};

                param.entity.ctCode = this.paramsObj.ctCode;
                let wr_option = {
                    method: 'POST',
                    data: param,
                    url: 'waterMeter/list'
                };
                this.$ajax(
                    wr_option
                ).then(function (response) {
                    if(response) {
                        vm.waterMeterList = response.data.result;
                    }

                }).catch(function (error) {
                    vm.$alert('页面:查询水表信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            //保存水表
            saveWaterMeter(subForm) {
                let vm = this;
                let param = {};
                let refs = vm.$refs[subForm];
                refs.validate((valid) => {
                    if (valid) {
                        param.entity = vm.waterObj;
                        let option = {
                            method: "POST",
                            data: param,
                            url: "waterMeter/create"
                        };
                        this.$ajax(
                            option
                        ).then(function (response) {
                            if (response) {
                                vm.queryWaterMeterList();
                                vm.$message({
                                    showClose: true,
                                    message: '新增水表信息成功',
                                    type: 'success'
                                });
                                vm.name = "2";
                                vm.showWaterMeterDrawer = false;
                            }
                        }).catch(function () {
                            vm.$alert('页面:新增电表信息失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    } else {
                        vm.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                });
            },

            //点击修改水表
            updateWaterMeterButton(waterItem) {
                let vm = this;
                let obj = JSON.parse(JSON.stringify(waterItem));
                vm.waterObj = obj;
                vm.showWaterMeterDrawer = true;
                vm.waterDrawerTitle = "修改水表";
            },

            //修改水表
            updateWaterMeter(subform) {
                let vm = this;

                vm.$refs[subform].validate((valid) => {
                    if(valid) {
                        let param = {};
                        param.entity = vm.waterObj;

                        vm.$confirm('此操作将修改"' + param.entity.waterMeterName + '"水表信息, 是否继续?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(() => {
                            //修改电表信息
                            let option = {
                                method: 'POST',
                                data: param,
                                url: 'waterMeter/update'
                            };
                            this.$ajax(
                                option
                            ).then(function (response) {
                                if (response) {
                                    vm.queryWaterMeterList();
                                    vm.$message({
                                        showClose: true,
                                        message: '修改"' + param.entity.waterMeterName + '"水表信息成功',
                                        type: 'success'
                                    });
                                    vm.name = "2";
                                    vm.showWaterMeterDrawer = false;
                                }
                            }).catch(function (error) {
                                vm.$alert('页面:修改' + param.entity.waterMeterName + '水表信息失败', '系统异常', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                    }
                                });
                            });
                        }).catch(() => {
                            vm.$message({
                                type: 'info',
                                message: '已取消修改'
                            });
                        });

                    }
                });

            },

            //删除水表
            delWaterMeter(item) {
                let vm = this;
                vm.$confirm('此操作将删除'+item.waterMeterName+'水表, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let param = {};
                    param.entity = item;
                    let meterName = param.entity.waterMeterName;
                    let option = {
                        method: 'POST',
                        data: param,
                        url: 'waterMeter/delete'
                    };
                    this.$ajax(
                        option
                    ).then(function (response) {
                        if (response) {
                            vm.queryWaterMeterList();
                            vm.$message({
                                showClose: true,
                                message: '删除' + meterName + '电表信息成功',
                                type: 'success'
                            });
                            vm.name = "2";
                        }
                    }).catch(function (error) {
                        vm.$alert('页面:删除' + meterName + '电表信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }).catch(() => {
                    vm.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

            //查询水表信息方法
            queryWaterMeterList() {
                let vm = {};
                vm = this;

                let param = {};
                param.entity = {};
                if(vm.paramsObj) {
                    param.entity.ctCode = vm.paramsObj.ctCode;
                }

                //查询该合同下的电表
                let option = {
                    method: 'POST',
                    data: param,
                    url: 'waterMeter/list'
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (response) {
                        vm.waterMeterList = response.data.result;
                    }
                }).catch(function (error) {
                    vm.$alert('页面:查询合同下水表信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            //启停水表
            waterIsOpenOff(val,id,waterMeterCode,waterMeterName) {
                let vm = this;

                let msg = "";

                if(val) {
                    msg = "启用";
                }else {
                    msg = "禁用";
                }

                let param = {};
                param.entity = {
                    id:id,
                    waterMeterCode:waterMeterCode,
                    waterIsOpen:val
                };

                let option = {
                    method: 'POST',
                    data: param,
                    url: 'waterMeter/changeWaterIsOpen'
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if(response) {
                        vm.$message({
                            showClose: true,
                            message: msg+waterMeterName+'水表成功',
                            type: 'success'
                        });
                        vm.queryWaterMeterList();
                    }

                }).catch(function (error) {
                    vm.$alert('页面:操作启用禁用开关失败', '系统异常', {
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

    body {
        margin-left: 200px;
        margin-right: 200px;
        min-height: 700px;
    }

    .root {
        height: 82vh;
        overflow-y: auto;
        overflow-x: hidden;
        min-width: 1000px;
        background-color: rgb(242, 242, 242);
    }

    .search_box {
        width: 100%;
    }

    .search_buttons {
        background-color: white;
        text-align: left;
        height: 50px;
        line-height: 50px;
        position: fixed;
    }

    .search_buttons span {
        margin-left: 20px;
        color: #409EFF;
    }

    .contract_box {
        background-color: white;
        margin-top: 5px;
        height: calc(100vh - 147px);
        overflow-y: auto;
    }

    .meter_info_list {
        border: 1px solid #d9d9d9;
        margin-bottom: 10px;
        padding-top: 10px;
    }

    .meter_info_list_add {
        line-height: 40px;
        text-align: left;
        border-bottom: 1px solid rgb(242, 242, 242);
        padding-left: 20px;
    }

    .meter_info_card {
        float: left;
        width: 80%;
        margin-left: 20px;
        font-size: 14px;
    }

    /*电表卡片*/
    .el_card_img {
        height: 100px;
        width: 100px;
        float: left;
        margin-top: 6px;
    }

    .contract_box_down {
        clear: both;
        text-align: left;
        padding-bottom: 15px;
    }

    .contract_box_down_up {
        margin-left: 20px;
    }

    .contract_box_down_up span {
        color: #606266;

    }

    .contract_box_down_input {
        width: 75%;
        height: 25px;
        margin-left: 10px;
        margin-right: 5px;
    }

    .meter_tabs{
        line-height: 50px;
        padding-left: 20px;
        background-color: #FFFFFF;
    }

    /*行样式*/
    .el_col_style {
        height: 40px;
        line-height: 40px;
    }

</style>