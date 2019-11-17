<template>
    <div class="root" ref="myRoot">
        <el-form :model="landlordObj" label-width="100px" :rules="rules" ref="landlordObj" labelSuffix="：" class="form_style">
            <div class="navigation_div">
                <el-button type="primary" size="small" @click="submitForm('landlordObj')">
                    保存
                </el-button>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">园区信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="园区" prop="pkCode">
                            <el-select v-model="landlordObj.pkCode"
                                       @change="getParkObj"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, index) in parkList"
                                        :key="item.pkCode"
                                        :value="item.pkCode"
                                        :label="item.pkName">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="承租面积" prop="acreage">
                            <el-input v-model="landlordObj.acreage" placeholder="请输入面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">业主信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="业主名字" prop="landlordName">
                            <el-input v-model="landlordObj.landlordName" placeholder="请输入业主名字" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="业主性别" prop="landlordSex">
                            <el-radio-group v-model="landlordObj.landlordSex">
                                <el-radio label="1">男</el-radio>
                                <el-radio label="2">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="业主电话" prop="landlordPhone">
                            <el-input v-model="landlordObj.landlordPhone" placeholder="请输入手机号" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">签约人信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="签约人" prop="contractorCode">
                            <el-select v-model="landlordObj.contractorCode"
                                       @change="getEmpObj"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, index) in empList"
                                        :key="item.empCode"
                                        :value="item.empCode"
                                        :label="item.empName">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="电话" prop="contractorPhone">
                            <el-input v-model="landlordObj.contractorPhone" placeholder="请输入签约人手机号" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">合同信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="开始时间" prop="contractStartDate">
                            <el-date-picker
                                    v-model="landlordObj.contractStartDate"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="结束时间" prop="contractEndDate">
                            <el-date-picker
                                    v-model="landlordObj.contractEndDate"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="提前交租" prop="advanceDay">
                            <el-input v-model="landlordObj.advanceDay" placeholder="请输入提前交租天数" clearable>
                                <template slot="append">天</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="单价" prop="rentUnivalence">
                            <el-input v-model="landlordObj.rentUnivalence" placeholder="请输入单价" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="单位" prop="rentUnit">
                            <el-radio-group v-model="landlordObj.rentUnit">
                                <el-radio label="1">元/天</el-radio>
                                <el-radio label="2">元/月</el-radio>
                                <el-radio label="3">元/年</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="付款周期" prop="depositRule">
                            <el-input v-model="landlordObj.depositRule" placeholder="押几" clearable>
                                <template slot="prepend">押</template>
                                <template slot="append">月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item prop="payRule">
                            <el-input v-model="landlordObj.payRule" placeholder="付几" clearable>
                                <template slot="prepend">付</template>
                                <template slot="append">月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="押金" prop="deposit">
                            <el-input v-model="landlordObj.deposit" placeholder="请输入押金" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">递增规则</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="input-col1">
                        <el-form-item label="是否详细递增" label-width="125px" prop="hasDetailAdd">
                            <el-radio-group v-model="landlordObj.hasDetailAdd">
                                <el-radio :label=true>是</el-radio>
                                <el-radio :label=false>否</el-radio>
                            </el-radio-group>

                            <el-button-group v-if="landlordObj.hasDetailAdd == true" style="margin-left: 20px;">
                                <el-button @click="addProgressiveIncreaseDetailList()" type="primary" size="small" icon="el-icon-plus">新增</el-button>
                                <el-button @click="delProgressiveIncreaseDetailList()" type="primary" size="small" icon="el-icon-delete">清空</el-button>
                            </el-button-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" v-for="(item, index) in progressiveIncreaseDetailList" :key="index" v-if="landlordObj.hasDetailAdd == true">
                        <el-form-item>
                            <el-input v-model="item.value" clearable>
                                <template slot="prepend">第 {{ index + 1 }} 年递增</template>
                                <template slot="append">%</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" v-if="landlordObj.hasDetailAdd == false">
                        <el-form-item prop="rentIncreasingCycle">
                            <el-input v-model="landlordObj.rentIncreasingCycle" clearable>
                                <template slot="prepend">每</template>
                                <template slot="append">年</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" v-if="landlordObj.hasDetailAdd == false">
                        <el-form-item prop="rentIncreaseProportion">
                            <el-input v-model="landlordObj.rentIncreaseProportion" clearable>
                                <template slot="prepend">递增</template>
                                <template slot="append">%</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common_title_div">
                <span class="common_title_font">合同附件</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3" style="line-height: 30px;margin-left: 20px;">
                        <file @selectFile="selectFile"></file>
                    </el-col>
                </el-row>
                <el-row class="row_style">
                    <!--<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" v-for="(item, index) in fileList" :key="item">
                        <el-form-item label="文件">
                            {{item}}&nbsp;
                            <i class="el-icon-delete" title="删除" @click="deleteFile(index)"></i>
                        </el-form-item>
                    </el-col>-->

                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-for="(item, index) in fileList" :key="index">
                        <div class="el-row-style">
                            <el-form-item label="文件" class="download_file">
                                <a title="点击下载" @click="downloadFile(item)">{{item}}&nbsp;</a>
                                <i class="el-icon-delete" title="删除" @click="deleteFile(index)"></i>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div" style="border-bottom: 1px solid white;">
                <span class="common-title-font">大房东租金</span>
                <el-button @click="addRent()" type="primary" size="small" style="float: right; margin-right: 20px;margin-top: 10px;" icon="el-icon-plus">新增租金</el-button>
            </div>
            <div class="customer-info-div">
                <el-table
                        :data="landlordObj.landlordRentEntityList"
                        border
                        style="width: 100%;line-height: 20px;">
                    <el-table-column
                            label="开始日期">
                        <template slot-scope="scope">{{ scope.row.costStartTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            label="结束日期">
                        <template slot-scope="scope">{{ scope.row.costEndTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            label="截止日期">
                        <template slot-scope="scope">{{ scope.row.deadline | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            label="本期租金">
                        <template slot-scope="scope">{{ scope.row.rentPrice }}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            label="租金票面税点">
                        <template slot-scope="scope">{{ scope.row.rentInvoiceTaxPoint }}&nbsp;%</template>
                    </el-table-column>
                    <el-table-column
                            label="租金开票金额">
                        <template slot-scope="scope">{{ scope.row.rentOnInvoice }}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            label="租金实付税点">
                        <template slot-scope="scope">{{ scope.row.rentRealTaxPoint }}&nbsp;%</template>
                    </el-table-column>
                    <el-table-column
                            label="应付租金税额">
                        <template slot-scope="scope">{{ scope.row.rentTaxPrice }}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            label="本期物业费">
                        <template slot-scope="scope">{{ scope.row.propertyPrice }}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            label="物业费票面税点">
                        <template slot-scope="scope">{{ scope.row.propertyInvoiceTaxPoint }}&nbsp;%</template>
                    </el-table-column>
                    <el-table-column
                            label="物业费开票金额">
                        <template slot-scope="scope">{{ scope.row.propertyOnInvoice }}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            label="物业费实付税点">
                        <template slot-scope="scope">{{ scope.row.propertyRealTaxPoint }}&nbsp;%</template>
                    </el-table-column>
                    <el-table-column
                            label="应付物业税额">
                        <template slot-scope="scope">{{ scope.row.propertyTaxPrice }}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            label="其他应付">
                        <template slot-scope="scope">{{ scope.row.otherPrice }}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            label="备注">
                        <template slot-scope="scope">{{ scope.row.remark }}</template>
                    </el-table-column>
                    <el-table-column
                            label="优惠金额">
                        <template slot-scope="scope">{{ scope.row.discountsPrice }}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            label="总应付金额">
                        <template slot-scope="scope">{{ scope.row.totalPrice }}&nbsp;元</template>
                    </el-table-column>

                    <el-table-column
                            fixed="right"
                            label="操作">
                        <template slot-scope="scope">
                            <el-button @click="openUpdateRent(scope.$index)" type="text" size="small">
                                修改
                            </el-button>
                            <el-button @click="deleteLandlordRent(scope.$index)" type="text" size="small">
                                移除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>

            <!-- 末尾提交按钮处 -->
            <div style="height: 50px;margin-top: 20px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="submitForm('landlordObj')">保存</el-button>
                <el-button style="float: left;" @click="resetForm('landlordObj')">重置</el-button>
            </div>
        </el-form>

        <el-dialog :title="windowTitle"
                   :visible.sync="addRentWindow"
                   :close-on-press-escape="false"
                   :close-on-click-modal="false"
                   width="80%">
            <el-form :model="landlordRentObj" :rules="rentRules" ref="landlordRentObj" labelSuffix="：" label-width="135px" style="text-align: left;">
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="开始日期" prop="costStartTime">
                            <el-date-picker
                                    v-model="landlordRentObj.costStartTime"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="结束日期" prop="costEndTime">
                            <el-date-picker
                                    v-model="landlordRentObj.costEndTime"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="截至日期" prop="deadline">
                            <el-date-picker
                                    v-model="landlordRentObj.deadline"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>

                <hr/>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="本期租金" prop="rentPrice">
                            <el-input v-model="landlordRentObj.rentPrice" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="租金票面税点" prop="rentInvoiceTaxPoint">
                            <el-input v-model="landlordRentObj.rentInvoiceTaxPoint" clearable>
                                <template slot="append">%</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="租金开票金额" prop="rentOnInvoice">
                            <el-input v-model="landlordRentObj.rentOnInvoice" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="租金实付税点" prop="rentRealTaxPoint">
                            <el-input v-model="landlordRentObj.rentRealTaxPoint" clearable>
                                <template slot="append">%</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="应付租金税额">
                            <el-input v-model="rentTaxPrice" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <hr/>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="本期物业费" prop="propertyPrice">
                            <el-input v-model="landlordRentObj.propertyPrice" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="物业费票面税点" prop="propertyInvoiceTaxPoint">
                            <el-input v-model="landlordRentObj.propertyInvoiceTaxPoint" clearable>
                                <template slot="append">%</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="物业费开票金额" prop="propertyOnInvoice">
                            <el-input v-model="landlordRentObj.propertyOnInvoice" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="物业费实付税点" prop="propertyRealTaxPoint">
                            <el-input v-model="landlordRentObj.propertyRealTaxPoint" clearable>
                                <template slot="append">%</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="应付物业税额">
                            <el-input v-model="propertyTaxPrice" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <hr/>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="其他应付" prop="otherPrice">
                            <el-input v-model="landlordRentObj.otherPrice" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16" class="input-col1">
                        <el-form-item label="备注" prop="remark">
                            <el-input v-model="landlordRentObj.remark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="优惠金额" prop="discountsPrice">
                            <el-input v-model="landlordRentObj.discountsPrice" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="总应付金额">
                            <el-input v-model="totalPrice" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <!-- 添加租金 -->
            <div slot="footer" class="dialog-footer" v-if="flag == 1">
                <el-button @click="addRentWindow = false">取 消</el-button>
                <el-button type="primary" @click="submitRentForm('landlordRentObj')">确 定</el-button>
            </div>
            <!-- 修改租金 -->
            <div slot="footer" class="dialog-footer" v-if="flag == 2">
                <el-button @click="addRentWindow = false">取 消</el-button>
                <el-button type="primary" @click="editRentForm('landlordRentObj')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import file from "../../../common/components/file.vue";
    export default {
        components: {ElRow,
        file},
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

            // 校验数字只能为整数
            let checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('楼层数只能为正整数'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            // 校验电话号码
            let checkPhone = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (!pattern.test(value)) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            return {
                fileList:[],
                floatDivWidth: '',

                // 大房东合同数据表单
                landlordObj: {
                    landlordRentEntityList: [],// 大房东租金数据表单集合
                },

                // 大房东租金数据表单
                landlordRentObj: {},

                flag: '',// 判断租金是修改还是新增

                empList: [],// 人员集合
                parkList: [],// 园区集合
                addRentWindow: false,// 添加租金窗口
                windowTitle: '',// 弹框标题

                // 递增详情列表
                progressiveIncreaseDetailList: [],

                editType:'', //编辑类型

                // 税点
                taxPoint: [
                    {
                        value: "1",
                        label: "3%"
                    }, {
                        value: "2",
                        label: "5%"
                    }, {
                        value: "3",
                        label: "6%"
                    }, {
                        value: "4",
                        label: "9%"
                    }, {
                        value: "5",
                        label: "16%"
                    },
                ],

                rules: {
                    pkCode: [
                        {required: true, message: '请选择园区', trigger: 'change'}
                    ],
                    acreage: [
                        {required: true, message: '请输入承租面积', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    landlordName: [
                        {required: true, message: '请选择业主姓名', trigger: 'blur'}
                    ],
                    landlordSex: [
                        {required: true, message: '选择业主性别', trigger: 'change'},
                    ],
                    landlordPhone: [
                        {required: true, message: '请输入业主电话', trigger: 'blur'},
                        {validator: checkPhone}
                    ],
                    contractorCode: [
                        {required: true, message: '请选择签约人', trigger: 'change'},
                    ],
                    contractorPhone: [
                        {required: true, message: '请输入签约人电话', trigger: 'change'},
                    ],
                    contractStartDate: [
                        {required: true, message: '请选择合同开始日期', trigger: 'change'}
                    ],
                    contractEndDate: [
                        {required: true, message: '请选择合同结束日期', trigger: 'change'}
                    ],
                    advanceDay: [
                        {required: true, message: '请输入提前交租天数', trigger: 'blur'}
                    ],
                    rentUnivalence: [
                        {required: true, message: '请输入单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    rentUnit: [
                        {required: true, message: '请选择单位', trigger: 'change'}
                    ],
                    depositRule: [
                        {required: true, message: '请输入付款周期', trigger: 'blur'},
                        {validator: checkInteger}
                    ],
                    payRule: [
                        {required: true, message: '请输入付款周期', trigger: 'blur'},
                        {validator: checkInteger}
                    ],
                    deposit: [
                        {required: true, message: '请输入押金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    hasDetailAdd: [
                        {required: true, message: '请选择是否详细递增', trigger: 'blur'}
                    ],
                    rentIncreasingCycle: [
                        {required: true, message: '请输入递增周期', trigger: 'blur'},
                        {validator: checkInteger}
                    ],
                    rentIncreaseProportion: [
                        {required: true, message: '请输入自增指数', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                },

                // 租金验证
                rentRules: {
                    costStartTime: [
                        {required: true, message: '请选择开始日期', trigger: 'change'},
                    ],
                    costEndTime: [
                        {required: true, message: '请选择结束日期', trigger: 'change'},
                    ],
                    deadline: [
                        {required: true, message: '请选择截止日期', trigger: 'change'},
                    ],
                    payStatus: [
                        {required: true, message: '请选择状态', trigger: 'change'},
                    ],

                    rentPrice: [
                        {required: true, message: '请输入本期租金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    rentOnInvoice: [
                        {required: true, message: '请输入租金开票金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    rentRealTaxPoint: [
                        {required: true, message: '请选择租金实付税点', trigger: 'blur'},
                    ],
                    rentInvoiceTaxPoint: [
                        {required: true, message: '请选择租金票面税点', trigger: 'blur'},
                    ],
                    rentTaxPrice: [
                        {required: true, message: '请输入应付租金税额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],

                    propertyPrice: [
                        {required: true, message: '请输入本期物业费', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    propertyOnInvoice: [
                        {required: true, message: '请输入物业费开票金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    propertyRealTaxPoint: [
                        {required: true, message: '请选择物业费实付税点', trigger: 'blur'},
                    ],
                    propertyInvoiceTaxPoint: [
                        {required: true, message: '请选择物业费票面税点', trigger: 'blur'},
                    ],
                    propertyTaxPrice: [
                        {required: true, message: '请输入应付物业税额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],

                    otherPrice: [
                        {required: true, message: '请输入其他应付', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    totalPrice: [
                        {required: true, message: '请输入总应付金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    discountsPrice: [
                        {required: true, message: '请输入优惠金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    remark: [
                        {required: true, message: '请输入其他应付金额备注', trigger: 'blur'},
                    ],
                },
            }
        },
        mounted() {
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            };
        },
        props: ['paramsObj'],// 接收父组件数据
        created() {
            let vm = this;
            vm.selectEmpList();
            vm.selectParkList();

            if (vm.paramsObj && vm.paramsObj.landlordContract) {
                vm.landlordObj = vm.paramsObj.landlordContract;
                if(vm.landlordObj.progressiveIncreaseDetail){
                    let progressiveIncreaseDetailList = vm.landlordObj.progressiveIncreaseDetail.split(",");

                    for(let i = 0 ;i< progressiveIncreaseDetailList.length; i++){
                        vm.progressiveIncreaseDetailList.push({value: progressiveIncreaseDetailList[i]});
                    }
                }

                vm.editType = vm.paramsObj.editType;
            }

            if(vm.landlordObj.contractUrl){
                vm.fileList = vm.landlordObj.contractUrl.split("，");
            }
            //  vm.getContractInfo();
        },
        computed: {
            // 应付租金税额
            rentTaxPrice: {
                get: function () {
                    let rentOnInvoice = this.landlordRentObj.rentOnInvoice;
                    let rentRealTaxPoint = this.landlordRentObj.rentRealTaxPoint;

                    if (rentOnInvoice && rentRealTaxPoint) {
                        let rentTaxPrice = Number(rentOnInvoice) * (Number(rentRealTaxPoint / 100));
                        this.landlordRentObj.rentTaxPrice = rentTaxPrice.toFixed(2);
                        return rentTaxPrice.toFixed(2);
                    } else {
                        this.landlordRentObj.rentTaxPrice = 0;
                        return 0;
                    }
                },
                set: function () {
                    return 0;
                }
            },

            // 应付物业费税额
            propertyTaxPrice: function () {
                let propertyOnInvoice = this.landlordRentObj.propertyOnInvoice;
                let propertyRealTaxPoint = this.landlordRentObj.propertyRealTaxPoint;

                if (propertyOnInvoice && propertyRealTaxPoint) {
                    let propertyTaxPrice = Number(this.landlordRentObj.propertyOnInvoice) * (Number(this.landlordRentObj.propertyRealTaxPoint) / 100);
                    this.landlordRentObj.propertyTaxPrice = propertyTaxPrice.toFixed(2);
                    return propertyTaxPrice.toFixed(2);
                } else {
                    this.landlordRentObj.propertyTaxPrice = 0;
                    return 0;
                }
            },

            // 应付总金额
            totalPrice: function () {
                let rentPrice = this.landlordRentObj.rentPrice;// 本期租金
                let rentTaxPrice = this.landlordRentObj.rentTaxPrice;// 应付租金税额

                let propertyPrice = this.landlordRentObj.propertyPrice;// 本期物业费
                let propertyTaxPrice = this.landlordRentObj.propertyTaxPrice;// 应付物业税额

                let otherPrice = this.landlordRentObj.otherPrice;// 其他应付
                let discountsPrice = this.landlordRentObj.discountsPrice;// 优惠金额

                if (rentPrice && rentTaxPrice && propertyPrice && propertyTaxPrice && otherPrice && discountsPrice) {
                    let totalPrice = Number(rentPrice) + Number(rentTaxPrice) + Number(propertyPrice) + Number(propertyTaxPrice) + Number(otherPrice) - Number(discountsPrice);
                    this.landlordRentObj.totalPrice = totalPrice.toFixed(2);
                    return totalPrice.toFixed(2);
                } else {
                    return 0;
                }
            }
        },
        methods: {
            // 添加详细递增
            addProgressiveIncreaseDetailList() {
                this.progressiveIncreaseDetailList.push({});
            },
            // 清空递增
            delProgressiveIncreaseDetailList() {
                this.progressiveIncreaseDetailList = [{
                    value: 0
                }];
            },

            // 查询签约人员
            selectEmpList() {
                let options = {
                    method: 'POST',
                    data: {},
                    url: "employee/list",
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        this.empList = response.data.result;
                    }
                }).catch(error => {
                    this.$alert('页面：人员查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },

            // 得到签约人Code和Name
            getEmpObj(val) {
                let obj = {};
                obj = this.empList.find((item) => {
                    return item.empCode === val;// 筛选出匹配数据
                });
                this.landlordObj.contractor = obj.empName;
                this.landlordObj.contractorPhone = obj.empPhone;
            },

            // 查询所有园区
            selectParkList() {
                let options = {
                    method: 'POST',
                    data: {},
                    url: "park/list",
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        this.parkList = response.data.result;
                    }
                }).catch(error => {
                    this.$alert('页面：人员查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },

            // 得到园区pkCode和pkName
            getParkObj(val) {
                let obj = {};
                obj = this.parkList.find((item) => {
                    return item.pkCode === val;// 筛选出匹配数据
                });
                this.landlordObj.pkName = obj.pkName;
            },

            // 打开新增租金窗口
            addRent() {
                this.windowTitle = '添加租金';
                this.flag = 1;
                this.addRentWindow = true;
                this.landlordRentObj = {};
            },

            // 新增租金提交
            submitRentForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.landlordRentObj.costStartTime = this.landlordRentObj.costStartTime.getTime();
                        this.landlordRentObj.costEndTime = this.landlordRentObj.costEndTime.getTime();
                        this.landlordRentObj.deadline = this.landlordRentObj.deadline.getTime();
                        this.landlordObj.landlordRentEntityList.push(this.landlordRentObj);
                        this.addRentWindow = false;
                    }
                });
            },

            // 打开修改租金窗口
            openUpdateRent(index) {
                this.windowTitle = '修改租金';
                this.flag = 2;
                this.addRentWindow = true;
                this.landlordRentObj = this.landlordObj.landlordRentEntityList[index];
            },

            // 修改租金提交
            editRentForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const startDate = new Date(this.landlordRentObj.costStartTime);
                        const endDate = new Date(this.landlordRentObj.costEndTime);
                        const deadline = new Date(this.landlordRentObj.deadline);

                        this.landlordRentObj.costStartTime = startDate.getTime();
                        this.landlordRentObj.costEndTime = endDate.getTime();
                        this.landlordRentObj.deadline = deadline.getTime();

                        this.landlordObj.landlordRentEntityList.push(this.landlordRentObj);
                        this.landlordObj.landlordRentEntityList.pop();
                        this.addRentWindow = false;
                    }
                });
            },

            // 移除租金
            deleteLandlordRent(index) {
                this.$confirm('确定移除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.landlordObj.landlordRentEntityList.splice(index, 1);
                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {});
            },


            //提交
            submitForm(formName){
                let vm = this;
                if(vm.editType == "reSubmit"){
                    vm.editForm(formName, "landlordRentApply/create");
                }else if(vm.editType == "edit"){
                    vm.editForm(formName, "landlordRentApply/applicantResubmit");
                }
            },

            // 调整申请
            editForm(formName,url) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let vm = this;
                        let fileList = vm.fileList;

                        if(fileList.length<1){
                            vm.$message({
                                type: 'warning',
                                message: '请选择文件！'
                            })
                            return;
                        }

                        vm.landlordObj.contractUrl = fileList.join("，");


                        if (vm.landlordObj.hasDetailAdd == true) {
                            let arr = [];
                            for (let i = 0; i < this.progressiveIncreaseDetailList.length; i++) {
                                arr.push(vm.progressiveIncreaseDetailList[i].value);
                            }
                            vm.landlordObj.progressiveIncreaseDetail = arr.join(',');
                        }

                        let obj = {
                            entity: vm.landlordObj
                        };


                        let options = {
                            method: 'POST',
                            data: obj,
                            url: url,
                        };

                        this.$ajax(
                            options
                        ).then(response => {
                            if (response) {
                                this.$message({
                                    showClose: true,
                                    message: '合同修改成功',
                                    type: 'success'
                                });

                                // 关闭当前页签
                                let dataDelObj = {
                                    flag: 'del',
                                    menuName: '修改大房东合同',
                                    item: 'landlordContractEdit',
                                };
                                this.$emit('backParam', dataDelObj);

                                // 打开新页签
                                let data = {
                                    menuName: '大房东合同工作流',
                                    item: 'landlordRentApply',
                                    params: {
                                    }
                                };
                                this.$emit('backParam', data);
                            }
                        }).catch(error => {
                            this.$alert('页面：合同修改失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        })
                    }
                });
            },

            //获取合同数据
            getContractInfo(){
                let vm = this;
                let obj = {
                    entity: {
                        ldCode: vm.landlordObj.ldCode
                    }
                };
                let options = {
                    method: 'POST',
                    data: obj,
                    url: 'landlordRentApply/ContractInfo'
                };
                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                       vm.landlordObj = response.data.result;
                       let progressiveIncreaseDetailList = vm.landlordObj.progressiveIncreaseDetail.split(",");
                       for(let i = 0 ;i< progressiveIncreaseDetailList.length; i++){
                           vm.progressiveIncreaseDetailList.push({value: progressiveIncreaseDetailList[i]});
                       }
                    }
                }).catch(error => {
                    this.$alert('页面：合同新增失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },


            // 重置表单
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

            selectFile(urls){
                if (urls) {
                    for (let i = 0; i < urls.length; i++) {
                        this.fileList.push(urls[i].fileName+'---'+urls[i].fileCode);
                    }
                } else {
                    this.fileList = [];
                }
            },

            // 删除文件
            deleteFile(index){
                this.$confirm('是否删除该文件？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.fileList.splice(index, 1);

                    this.$message({
                        type: 'success',
                        message: '删除成功！'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

            //下载文件
            downloadFile(item) {
                let fileCodeList = item.split('---');

                let fileCode = fileCodeList[1];
                if(!fileCode) {
                    this.$alert('页面：下载文件编码为空！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                // 本地测试打开
//                window.location.href="http://localhost:8380/park/file/download/" + fileCode;

                // 发布线上打开
                window.location.href="/park/file/download/" + fileCode;

            },
        },
    }
</script>

<style scoped>
    .root {
        min-width: 1000px;
        min-height: 900px;
        background-color: rgb(242, 242, 242);
    }

    .form_style {
        height: 80vh;
        overflow-y: auto;
        overflow-x: hidden;
    }

    /*导航栏*/
    .navigation_div {
        float: left;
        margin-left: 20px;
        margin-top: -45px;
        text-align: left;
        position: fixed;
    }

    /*公共标题 div*/
    .common-title-div{
        background-color: white;
        height: 50px;
        width: 100%;
        margin-top: 10px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*公共标题 文字*/
    .common-title-font {
        float: left;
        font-size: 17px;
        color: #409EFF;
        margin-left: 20px;
        line-height: 50px;
    }

    /*基本信息 div*/
    .customer-info-div {
        background-color: white;
        text-align: left;
    }

    /*输入框 第一行*/
    .input-col1{
        margin-top: 10px;
        height: 60px;
    }


    .common_title_div {
        background-color: white;
        height: 50px;
        width: 100%;
        border-bottom: 1px solid rgb(242, 242, 242);
        margin-top: 10px;
    }

    .common_content_div {
        background-color: white;
        text-align: left;
    }

    .row_style {
        padding-top: 15px;
    }
    .el-row-style{
        height: 20px;
        line-height: 20px;
        margin-left: 20px;
    }
    .download_file a:hover{
        cursor: pointer;
        color: #66b1ff;
        text-decoration: underline;
    }

</style>