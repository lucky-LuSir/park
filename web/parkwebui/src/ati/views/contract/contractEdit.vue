<template>
    <div class="has-position_root" ref="myRoot">

        <div class="has-position_header">
            <el-button @click="submit('contract')" class="navigation_button_submit" size="small" type="primary">提交</el-button>
            <span v-if="contract.ctStatus == 1 || contract.ctStatus == 2 || contract.ctStatus == 3" class="navigation_title">当前位置：合同变更</span>
            <span v-if="contract.ctStatus == 5" class="navigation_title">当前位置：处理驳回</span>
        </div>

        <el-form class="has-position_main" ref="contract" :rules="rules" :model="contract" label-width="100px" label-suffix="">

            <div class="common_title_div">
                <span class="common_title_font">基本信息</span>
            </div>
            <div class="common_content_div">
                <el-row :gutter="30" class="row_style">
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="客户信息" prop="cusCode">
                            <el-select
                                    style="width: 350px;"
                                    v-model="contract.cusCode"
                                    filterable
                                    remote
                                    :remote-method="remoteMethod"
                                    placeholder="输入客户姓名"
                                    @change="selectCusByCode">
                                <el-option
                                        v-for="item in cusNames"
                                        :key="item.code"
                                        :label="item.name"
                                        :value="item.code">
                                    <span style="float: left">{{ item.name }}</span>
                                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.phone }}</span>
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="客户类型" prop="customerType">
                            <el-radio-group v-model="contract.customerType">
                                <el-radio label="1" @change="handCusTypeCompany()">公司</el-radio>
                                <el-radio label="2" @change="handCusType()">个人</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="公司名称" prop="companyName">
                            <el-input v-model="contract.companyName" :disabled="companyNameFlag"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="所属行业" prop="tradeName" style="width: 100%;">
                            <el-select v-model="contract.tradeName" placeholder="请选择"
                                       style="width:100%;">
                                <el-option
                                        v-for="item in trades"
                                        :key="item.index"
                                        :label="item.value"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="联系人" prop="relateName">
                            <el-input v-model="contract.relateName" style="width: 90px"></el-input>
                            <el-radio-group v-model="contract.relateSex">
                                <el-radio label="1">先生</el-radio>
                                <el-radio label="2">女士</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="联系电话" prop="relatePhone">
                            <el-input v-model="contract.relatePhone"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <div class="grid-content ">
                            <el-form-item label="固定电话">
                                <el-input v-model="contract.landlinePhone" placeholder="请输入号码"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content ">
                            <el-form-item label="租房用途" label-width="100px">
                                <el-checkbox-group v-model="checkUseType">
                                    <el-checkbox v-for="useType in useTypeList" :label="useType" :key="useType">{{useType}}</el-checkbox>
                                </el-checkbox-group>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="公司产品" prop="companyProduct">
                            <el-input v-model="contract.companyProduct"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">甲方信息</span>
            </div>
            <div class="common_content_div">
                <el-row :gutter="30" class="row_style">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="签约日期" prop="contractDate">
                            <el-date-picker v-model="contract.contractDate" align="right" type="date"></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="签单人" prop="contractorCode">
                            <el-select v-model="contract.contractorCode" @change="changeEmp" filterable placeholder="请选择">
                                <el-option v-for="item in empList"
                                           :key="item.code"
                                           :label="item.name"
                                           :value="item.code">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="联系电话" prop="contractorPhone">
                            <el-input v-model="contract.contractorPhone"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="甲方公司" prop="partyName">
                            <el-input v-model="contract.partyName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">合同信息</span>
            </div>
            <div class="common_content_div">
                <el-row :gutter="30" class="row_style">
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="合同开始" prop="contractStartTime">
                            <el-date-picker
                                    v-model="contract.contractStartTime"
                                    align="right"
                                    type="date"
                                    @change="checkConTime(0)">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="合同结束" prop="contractEndTime">
                            <el-date-picker
                                    v-model="contract.contractEndTime"
                                    align="right"
                                    type="date"
                                    @change="checkConTime(1)">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="免租日期">
                            <el-date-picker
                                    v-model="freeRentDateList"
                                    @change="freeRentDateMethod()"
                                    type="daterange"
                                    align="right"
                                    unlink-panels
                                    :clearable="false"
                                    range-separator="至"
                                    start-placeholder="免租开始日"
                                    end-placeholder="免租结束日"
                                    :picker-options="pickerOptions">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">房屋出租情况</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="物业费单价" prop="propertyUnitPrice">
                            <el-input v-model="contract.propertyUnitPrice" @blur="autoTotal(1)">
                                <template slot="append">{{contract.propertyUnit}}</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="入住园区" prop="pkName">
                            <el-select id="pkName"
                                       v-model="contract.pkName"
                                       clearable
                                       filterable
                                       default-first-option
                                       placeholder="输入园区名称或者地址"
                                       @change="selectParkByCode">
                                <el-option v-for="item in pkNames"
                                           :key="item.index"
                                           :label="item.name"
                                           :value="item.code">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="园区地址">
                            <el-input v-model="contract.houseAddress" readonly="readonly"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="房源操作">
                            <el-button @click="addFloor" size="small" type="warning" plain>添加楼层</el-button>
                            <el-button @click="clearFloor" size="small" type="warning" plain>清空楼层</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
                <div class="conHouseBox" v-for="(item, index) in contract.contractHouseList">
                    <el-row :gutter="20">
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <el-form-item label="出租楼层">
                                <el-select v-model="item.flCode"
                                           filterable
                                           placeholder="请输入楼层"
                                           @blur="selectFloor(item.flCode, index)">
                                    <el-option v-for="item in fls"
                                               :key="item.index"
                                               :label="item.name"
                                               :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <el-form-item label="出租房间号">
                                <el-input v-model="item.roomNumber" placeholder="房间号"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <el-form-item label="房租单价">
                                <el-tooltip content="记得选择价格单位" placement="right">
                                    <el-input v-model="item.unitPrice" @blur="checkHouseNum(2,index,item.unitPrice)">
                                        <el-select v-model="item.unit" slot="append" placeholder="请选择"
                                                   style="width: 100px;" @change="autoTotal(1)">
                                            <el-option value="元/㎡/天">元/㎡/天</el-option>
                                            <el-option value="元/㎡/月">元/㎡/月</el-option>
                                            <el-option value="元/㎡/年">元/㎡/年</el-option>
                                        </el-select>
                                    </el-input>
                                </el-tooltip>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="1" :sm="1" :md="1" :lg="1" :xl="1">
                            <el-form-item>
                                <i @click="deleteThisHouse(index, item.rentHosCode)" title="删除" class="el-icon-delete" style="margin-left: -100px;"></i>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <el-form-item label="计租面积">
                                <el-input v-model="item.investArea"
                                          placeholder="一般大于承租面积"
                                          @blur="checkHouseNum(1,index,item.investArea)">
                                    <template slot="append">㎡</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <el-form-item label="公摊比">
                                <el-input v-model="item.areaRate">
                                    <template slot="append">%</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <el-form-item label="月租金">
                                <el-input v-model="item.monthlyRent"
                                          placeholder="自动计算"
                                          @blur="autoTotal(2)">
                                    <template slot="append">元/月</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">租金规则</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="提前交租" prop="advanceDateCount">
                            <el-input v-model="contract.advanceDateCount">
                                <template slot="append">天</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="详细递增">
                            <el-radio-group v-model="contract.incDetailType">
                                <el-radio label='1'>是</el-radio>
                                <el-radio label='2'>否</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item>
                            <div style="line-height: 30px" v-if="contract.incDetailType == 1">
                                <el-button size="small" type="warning" plain @click="addIncDetail">
                                    添加递增
                                </el-button>
                                <el-button size="small" type="warning" plain @click="clearIncDetail">
                                    清空递增
                                </el-button>
                            </div>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="租金递增" prop="increasingCycle" v-if="contract.incDetailType == 2">
                            <div style="float: left">
                                <el-input v-model="contract.increasingCycle" style="width: 210px">
                                    <template slot="prepend">每</template>
                                    <template slot="append">年递增</template>
                                </el-input>
                            </div>
                            <div style="float: left">
                                <el-input v-model="contract.rentIncreaseProportion"
                                          @blur="checkNum('rentIncreaseProportion')" style="width: 140px">
                                    <template slot="append">%</template>
                                </el-input>
                            </div>
                        </el-form-item>
                    </el-col>
                    <div v-if="contract.incDetailType == 1" v-for="(item,index) in incDetailCase">
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <el-form-item label="租金递增">
                                <div v-if="index == 0">
                                    <el-input v-model="item.value" style="width: 220px" readonly>
                                        <template slot="prepend">第{{index + 1}}年递增</template>
                                        <template slot="append">%</template>
                                    </el-input>
                                </div>
                                <div v-else>
                                    <el-input v-model="item.value" style="width: 220px">
                                        <template slot="prepend">第{{index + 1}}年递增</template>
                                        <template slot="append">%</template>
                                    </el-input>
                                </div>
                            </el-form-item>
                        </el-col>
                    </div>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="预览">
                            <el-button type="primary" size="small" @click="incDetailPreview">递增详情</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <!-- 预览弹窗 -->
            <el-dialog :visible.sync="incDetailDialog" width="86%" style="line-height: 30px;">
                <p style="font-size: x-large;margin-top: -25px; font-weight: bold;">租金递增详情预览</p>
                <el-table style="min-height: 300px;"
                          :data="contract.rentIncrementList"
                          border
                          height="200">
                    <el-table-column
                            align="center"
                            label="序号"
                            type="index"
                            width="50px">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            min-width="100px"
                            label="递增开始时间">
                        <template slot-scope="scope">{{ scope.row.incStartTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            min-width="100px"
                            label="递增结束时间">
                        <template slot-scope="scope">{{ scope.row.incEndTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            min-width="100px"
                            label="每月租金(元)"
                            prop="rentTotal">
                    </el-table-column>

                    <el-table-column
                            align="center"
                            min-width="120px"
                            label="均价(元/平米/天)"
                            prop="avgUnitPrice">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            min-width="100px"
                            label="出租含公摊面积(㎡)"
                            prop="rentInvestArea">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            min-width="80px"
                            label="递增比(%)"
                            prop="rentIncreaseProportion">
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            min-width="50px">
                        <template slot-scope="scope">
                            <el-button type="text" size="small" @click="editRentIncDialog(scope.$index)">修改</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-dialog>

            <!-- 修改递增详情 -->
            <el-dialog :visible.sync="editIncDelDialog" width="70%">
                <el-form :model="editIncModel" ref="editIncModel" :rules="rules" label-width="130px"
                         style="text-align: left">
                    <el-row :gutter="20">
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="递增周期：">
                                {{editIncModel.incStartTime | timeDate}}～{{editIncModel.incEndTime | timeDate}}
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="出租含公摊面积：">
                                {{editIncModel.rentInvestArea}}㎡
                            </el-form-item>
                        </el-col>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="递增比：">
                                {{editIncModel.rentIncreaseProportion}}%
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="每月租金：" prop="rentTotal">
                                <el-input v-model="editIncModel.rentTotal">
                                    <template slot="append">元</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="平均单价：" prop="avgUnitPrice">
                                <el-input v-model="editIncModel.avgUnitPrice">
                                    <template slot="append">元/㎡/月</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div slot="footer" style="text-align: center;height: 50px;line-height: 50px;">
                    <el-button @click="cancelEditIncDel">取 消</el-button>
                    <el-button type="primary" @click="submitEditIncDel('editIncModel')">修 改</el-button>
                </div>
            </el-dialog>

            <div class="common_title_div">
                <span class="common_title_font">房租支付方式</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="付款方式" prop="payRule">
                            <div style="float: left">
                                <el-input v-model="contract.payRule" style="width: 200px">
                                    <template slot="prepend">付</template>
                                    <template slot="append">月 押</template>
                                </el-input>
                            </div>
                            <div style="float: left">
                                <el-input v-model="contract.depositRule" @blur="checkNum('depositRule')"
                                          style="width: 130px">
                                    <template slot="append">月</template>
                                </el-input>
                            </div>
                        </el-form-item>
                        <div style="clear:both"></div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="实付押金" prop="deposit">
                            <el-input v-model="contract.deposit">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">首付款信息</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="首付总额：">
                            {{fpayAllPrice}}元
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="电费押金" prop="eleDeposit">
                            <el-input v-model="contract.eleDeposit">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="水费押金" prop="waterDeposit">
                            <el-input v-model="contract.waterDeposit">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="工程押金" prop="fpayProjectDeposit">
                            <el-input v-model="contract.fpayProjectDeposit">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="房屋租金" prop="fpayHouseRent">
                            <el-input v-model="contract.fpayHouseRent">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="物业费" prop="fpayPropertyPrice">
                            <el-input v-model="contract.fpayPropertyPrice">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="保险费" prop="fpayInsurancePrice">
                            <el-input v-model="contract.fpayInsurancePrice">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="保证金" prop="fpayBond">
                            <el-input v-model="contract.fpayBond">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">税收信息</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="产值" prop="outputValue">
                            <el-input v-model="contract.outputValue">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="税收金额" prop="taxTake">
                            <el-input v-model="contract.taxTake">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="税收是否可落户到当地" label-width="200px" prop="hasRevenueBackLocal">
                            <el-radio-group v-model="contract.hasRevenueBackLocal">
                                <el-radio :label=true>是</el-radio>
                                <el-radio :label=false>否</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="设备清单" prop="equipmentList">
                            <el-input v-model="contract.equipmentList"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="设备价值" prop="equipmentValue">
                            <el-input v-model="contract.equipmentValue">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">电费</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="总用电量" prop="totalElectricity">
                            <el-input v-model="contract.totalElectricity">
                                <template slot="append">kVA</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="基础单价" prop="basicElectricityUnitPrice">
                            <el-input v-model="contract.basicElectricityUnitPrice">
                                <template slot="append">元/kVA/月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="电损" prop="addLoss">
                            <el-input v-model="contract.addLoss">
                                <template slot="append">%</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="月基础电费">
                            <el-input v-model="basicElectricityPriceByMonth">
                                <template slot="append">元/月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="是否含税">
                            <el-radio-group v-model="contract.elHasTaxNum">
                                <el-radio label='1'>是</el-radio>
                                <el-radio label='2'>否</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">停车费</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="免费车位" prop="freeParkingCount">
                            <el-input v-model="contract.freeParkingCount">
                                <template slot="append">个</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="超出收费" prop="parkingPrice">
                            <el-input v-model="contract.parkingPrice">
                                <template slot="append">元/个</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="是否含税">
                            <el-radio-group v-model="contract.paHasTaxNum">
                                <el-radio label='1'>是</el-radio>
                                <el-radio label='2'>否</el-radio>
                            </el-radio-group>
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


            <div class="common_title_div">
                <span class="common_title_font">备注</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="备注说明">
                            <el-input type="textarea" v-model="contract.remark"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <!-- 末尾提交按钮 -->
            <div style="height: 50px;margin-top: 20px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="submit('contract')">提交
                </el-button>
                <el-button style="float: left;" @click="resetForm('contract')">重置
                </el-button>
            </div>
        </el-form>
    </div>
</template>


<script>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import file from "../../../common/components/file.vue";
    import ElRow from "element-ui/packages/row/src/row";


    export default {
        components: {
            ElRow,
            ElButton,
            file
        },
        mounted(){
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        data() {
            let meterReadingChange = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = /^((0|[12]?[0-9]?)|30)$/;
                    if (!pattern.test(value)) {
                        callback(new Error('请输入1～30数字值'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            //校验数字0～99999999.99
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
            //校验电话号码
            let checkPhone = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (!(pattern.test(value))) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            //校验座机
            let checkLandlinePhone = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$LANDLINE_PHONE;
                    if (!pattern.test(value)) {
                        callback(new Error('请输入正确的固定电话'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            //校验数字只能是正整数或小数
            let checkIntOrFloat = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(value))) {
                        callback(new Error('只能输入正整数或小数'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            //校验数字只能为整数
            let checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('楼层数只能为正整数'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            return {
                fileList: [],// 合同上传文件集合

                freeRentDateList: [],// 免租日期集合
                proTotal: 0,//物业费
                useTypeList:["生产","仓储","办公"],
                checkUseType: [],//用途集合
                isIncDel: false,//是否点击过递增详情
                itRoomArr: [[]],
                floatDivWidth: "",
                pkAllList: [],
                incDetailCase: [
                    {
                        value: "0",
                    }
                ],
                //控制预览窗口
                incDetailDialog: false,
                //控制修改递增详情弹框
                editIncDelDialog: false,
                //递增租金
                editIncModel: {
                    incIndex:"",//记录下标
                    incStartTime: "",//递增开始时间
                    incEndTime: "",//递增结束时间
                    rentTotal: "",//每月租金
                    avgUnitPrice: "",//平均单价(元/平米/月)
                    rentInvestArea: "",//出租含公摊面积
                    rentIncreaseProportion: ""//递增比
                },
                //保留递增租金对象
                editIncModelCopy: {
                    rentTotal: "",//每月租金
                    avgUnitPrice: "",//平均单价(元/平米/月)
                },

                empList: [],//人员下拉选集合
                employees: [],//人员取值
                contract: {
                    //客户编码
                    cusCode: "",
                    //客户类型
                    customerType: "",
                    //公司名称
                    companyName: "",
                    //所属行业+
                    trade: "",
                    //所属行业名称+
                    tradeName: "",
                    //公司产品+
                    companyProduct: "",
                    //联系人
                    relateName: "",
                    //联系人性别
                    relateSex: "",
                    //联系电话
                    relatePhone: "",
                    //座机
                    landlinePhone: "",


                    freeRentTimes: [],//免租日期集合
                    freeRentStartTime: "",//免租开始日期
                    freeRentEndTime: "",//免租期结束日期

                    useType: "",//租房用途
                    //入驻园区+
                    pkCode: "",
                    pkName: "",
                    contractorCode: "",//签单人编码
                    contractor: "",//签单人
                    contractorDeptCode: "",//签单人部门编码
                    contractorDept: "", //签单人部门
                    contractorPhone: "",//联系电话+
                    partyName: "",//甲方公司+
                    houseAddress: "",//房源地址

                    ctName: "",//合同编号+

                    /*-------------- 不用字段 --------------*/
//                    deliveryDate: "",// 交付日期+
//                    startRentDate: "",// 计租日期

                    advanceDateCount: "",//提前交租+
                    contractDate: "",//签约日期+
                    contractStartTime: "",//合同开始
                    contractEndTime: "",//合同结束

                    //租金递增
                    incDetailType: "2",
                    increasingCycle: "",
                    rentIncreaseProportion: "",
                    incDetailCase: "",
                    //租金合计+
                    rentTotal: "",
                    //物业费合计
                    propertyTotal:"",
                    //出租总价：租金+物业费
                    total:"",
                    //出租总面积
                    totalRentalArea:"",
                    //递增租金预览
                    rentIncrementList : [],
                    //是否含税
                    hasTax: "",
                    hasTaxNum: "",
                    //物业费单价
                    propertyUnitPrice: "",
                    //物业费单价单位
                    propertyUnit: "元/㎡/月",
                    //出租楼层集合
                    contractHouseList: [
                        {
                            flCode: "",
                            roomNumber: "",
                            roomCode: "",
                            areaRate: "",
                            investArea: "",
                            unitPrice: "",
                            monthlyRent:""
                        }
                    ],

                    //付款方式
                    payRule: "",
                    //押
                    depositRule: "",
                    //实付押金
                    deposit: "",
                    //电费押金
                    eleDeposit: "",
                    //水费押金
                    waterDeposit: "",

                    //首期付款
                    /*首期支付房屋租金*/
                    fpayHouseRent: "",
                    /*首期支付物业费*/
                    fpayPropertyPrice: "",
                    /*首期支付保险费*/
                    fpayInsurancePrice: "",
                    /*首期支付工程押金*/
                    fpayProjectDeposit: "",
                    /*首期支付保证金*/
                    fpayBond: "",

                    /*电费*/
                    //月基础电费
                    basicElectricityPriceByMonth: "",
                    //电费是否含税+
                    elHasTax: "",
                    elHasTaxNum: "",
                    //总用电量
                    totalElectricity: "",
                    //基础单价
                    basicElectricityUnitPrice: "",
                    //电损
                    addLoss: "",


                    /*水费*/
                    waterBillingType: "2",//水费计费方式+
                    waterAmount: "",//水费金额
                    //水费单价
                    waterPrice: "",
                    //水表初始值+
                    initWater: "",
                    //水表是否含税+
                    waHasTax: "",
                    waHasTaxNum: "",

                    /*停车费*/
                    //免费车位
                    freeParkingCount: "",
                    //超出收费
                    parkingPrice: "",
                    //停车费是否含税
                    paHasTax: "",
                    paHasTaxNum: "",

                    //合同url+
                    contractUrl: "",
                },
                cusNames: [],
                pkNames: [],
                //楼层集合
                fls: [],
                //选择的楼层名
                flName: "",
                customers: [],
                cusObj: {
                    customerType: "",
                    companyName: "",
                    trade: "",
                    companyProduct: "",
                    mainContactName: "",
                    mainContactPhone: "",
                },
                trades: [
                    {value: "食品加工业"},
                    {value: "饮料制造业"},
                    {value: "酒加工"},
                    {value: "烟草制品业"},
                    {value: "纺织业"},
                    {value: "皮、绒制品业"},
                    {value: "木材加工"},
                    {value: "家具制造"},
                    {value: "纸制品业"},
                    {value: "印刷业"},
                    {value: "文体教育"},
                    {value: "用品制造"},
                    {value: "石油加工"},
                    {value: "化学燃料"},
                    {value: "医药制造"},
                    {value: "橡胶制品"},
                    {value: "塑料制品"},
                    {value: "金属制品"},
                    {value: "制造业"},
                    {value: "交通运输"},
                    {value: "电子设备"}
                ],
                companyNameFlag: false,
                rules: {
                    outputValue: [
                        {required: true, message: '请填写产值', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    hasRevenueBackLocal: [
                        {required: true, message: '请选择税收是否可落户到当地', trigger: 'change'}
                    ],
                    taxTake: [
                        {required: true, message: '请填写税收金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    equipmentList: [
                        {required: true, message: '请填写设备清单', trigger: 'blur'}
                    ],
                    equipmentValue: [
                        {required: true, message: '请填写设备价值', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],

                    cusCode: [
                        {required: true, message: '请选择客户', trigger: 'change'}
                    ],
                    customerType: [
                        {required: true, message: '请选择客户类型', trigger: 'change'}
                    ],
                    companyName: [
                        {required: true, message: '请输入公司名称', trigger: 'blur'},
                    ],
                    companyProduct: [
                        {required: true, message: '请输入公司产品', trigger: 'blur'},
                    ],
                    relateName: [
                        {required: true, message: '请输入联系人名字', trigger: 'blur'},
                    ],
                    relatePhone: [
                        {required: true, message: '请输入联系电话', trigger: 'blur'},
                        {validator: checkPhone}
                    ],
                    landlinePhone: [
                        {required: true, message: '请输入固定电话', trigger: 'blur'},
                        {validator: checkLandlinePhone}
                    ],
                    tradeName: [
                        {required: true, message: '请输入所属行业', trigger: 'blur'},
                    ],
                    pkName: [
                        {required: true, message: '请选择园区', trigger: 'change'},
                    ],
                    contractorCode: [
                        {required: true, message: '请输入签单人', trigger: 'blur'},
                    ],
                    contractorPhone: [
                        {required: true, message: '请输入联系电话', trigger: 'blur'},
                        {validator: checkPhone}
                    ],
                    partyName: [
                        {required: true, message: '请输入甲方公司', trigger: 'blur'},
                    ],
                    ctName: [
                        {required: true, message: '请输入合同编号', trigger: 'blur'},
                    ],
//                    deliveryDate: [
//                        {type: 'date', required: true, message: '请选择合同交付日期', trigger: 'change'}
//                    ],
                    advanceDateCount: [
                        {required: true, message: '请输入提前交租', trigger: 'blur'},
                        {validator: meterReadingChange}
                    ],
                    contractDate: [
                        {required: true, message: '请选择合同签约日期', trigger: 'change'}
                    ],
                    contractStartTime: [
                        { required: true, message: '请选择合同开始日期', trigger: 'change'}
                    ],
                    contractEndTime: [
                        { required: true, message: '请选择合同结束日期', trigger: 'change'}
                    ],
//                    startRentDate: [
//                        {required: true, message: '请选择计租开始日期', trigger: 'change'}
//                    ],
                    freeRentTimes: [
                        {required: true, message: '请选择免租日期', trigger: 'change'}
                    ],
                    increasingCycle: [
                        {required: true, message: '请输入租金周期', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    propertyUnitPrice: [
                        {required: true, message: '请输入物业费单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    avgUnitPrice :[
                        {required: true, message: '请输入平均单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    rentTotal :[
                        {required: true, message: '请输入平均单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    payRule: [
                        {required: true, message: '请输入支付方式', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    deposit: [
                        {required: true, message: '请输入实付押金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],

                    /*******************************************/
                    eleDeposit: [
                        {required: true, message: '请输入电费押金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    waterDeposit: [
                        {required: true, message: '请输入水费押金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    fpayProjectDeposit: [
                        {required: true, message: '请输入工程押金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    fpayHouseRent: [
                        {required: true, message: '请输入房屋租金押金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    fpayPropertyPrice: [
                        {required: true, message: '请输入物业费押金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    fpayInsurancePrice: [
                        {required: true, message: '请输入保险费押金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    fpayBond: [
                        {required: true, message: '请输入保证金押金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    /*******************************************/

                    totalElectricity: [
                        {required: true, message: '请输入总用电量', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    basicElectricityUnitPrice: [
                        {required: true, message: '请输入电费基础单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    addLoss: [
                        {required: true, message: '请输入电损', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    waterBillingType: [
                        {required: true, message: '请选择水费计费方式', trigger: 'change'},
                    ],
                    waterAmount: [
                        {required: true, message: '请输入水费金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    waterPrice: [
                        {required: true, message: '请输入水费单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    initWater: [
                        {required: true, message: '请输入水费初始见表', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    freeParkingCount: [
                        {required: true, message: '请输入免费车位', trigger: 'blur'},
                        {validator: checkInteger}
                    ],
                    parkingPrice: [
                        {required: true, message: '请输入停车位超出收费', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    contractUrl: [
                        {required: true, message: '请上传文件', trigger: 'blur'},
                    ],
                },
                //免租日期选择框
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
            }
        },
        computed: {
            //租金合计
            total: {
                //计算属性
                get: function () {
                    let total =  Number(this.contract.rentTotal) + Number(this.contract.propertyTotal);
                    return total.toFixed(2);
                },
                set: function () {
                    return 0;
                }
            },
//            total: {
//                //计算属性
//                get: function () {
//                    let vm = this;
//                    let list = vm.contract.contractHouseList;
//                    let total = 0;
//                    let propertyUnitPrice = vm.contract.propertyUnitPrice;
//                    vm.contract.rentTotal = 0;
//                    vm.contract.propertyTotal = 0;
//                    vm.contract.totalRentalArea = 0;
//                    for (let i = 0; i < list.length; i++) {
//                        let investArea = 0;
//                        let unitPrice = 0;
//                        if (list[i].investArea && list[i].unitPrice && list[i].unit) {
//                            investArea = Number(list[i].investArea);
//                            unitPrice = list[i].unitPrice;
//                            vm.contract.totalRentalArea += investArea;
//                            if (list[i].unit == "元/㎡/天") {
//                                list[i].monthlyRent = (investArea * unitPrice * (365 / 12)).toFixed(2);
//                            } else if (list[i].unit == "元/㎡/月") {
//                                list[i].monthlyRent = (investArea * unitPrice).toFixed(2);
//                            } else if (list[i].unit == "元/㎡/年") {
//                                list[i].monthlyRent = (investArea * unitPrice / 12).toFixed(2);
//                            }
//                            vm.contract.rentTotal = Number(vm.contract.rentTotal) + Number(list[i].monthlyRent);
//                            vm.contract.propertyTotal += investArea * propertyUnitPrice;
//                            total = (vm.contract.rentTotal + vm.contract.propertyTotal).toFixed(2);
//                        }
//                    }
//                    vm.contract.propertyTotal = vm.contract.propertyTotal.toFixed(2);
//                    return total;
//                },
//                set: function () {
//                    return 0;
//                },
//            },
            //月基础电费
            basicElectricityPriceByMonth: {
                get: function () {
                    return this.contract.basicElectricityUnitPrice * this.contract.totalElectricity;
                },
                set: function () {
                    return 0;
                }
            },
            //首期收款总额
            fpayAllPrice: function () {
                let num = 0;
                if (this.contract.eleDeposit) {
                    num += Number(this.contract.eleDeposit);
                }
                if (this.contract.waterDeposit) {
                    num += Number(this.contract.waterDeposit);
                }
                if (this.contract.fpayHouseRent) {
                    num += Number(this.contract.fpayHouseRent);
                }
                if (this.contract.fpayPropertyPrice) {
                    num += Number(this.contract.fpayPropertyPrice);
                }
                if (this.contract.fpayInsurancePrice) {
                    num += Number(this.contract.fpayInsurancePrice);
                }
                if (this.contract.fpayProjectDeposit) {
                    num += Number(this.contract.fpayProjectDeposit);
                }
                if (this.contract.fpayBond) {
                    num += Number(this.contract.fpayBond);
                }
                return num.toFixed(2);
            }
        },
        //接受父组件传过来的参数
        props: ['paramsObj'],
        created: function () {
            let vm = this;

            if(vm.paramsObj) {
                vm.contract.ctCode = vm.paramsObj.ctCode;
                vm.contract.id = vm.paramsObj.id;
                vm.contract.ctStatus = vm.paramsObj.ctStatus;
            }



            if (vm.contract.ctStatus == 2 || vm.contract.ctStatus == 3 || vm.contract.ctStatus == 4) {
                window.history.back();
            } else {
                vm.contractDetail(vm.contract.ctCode);// 查询合同详情
                vm.queryEmp();// 查询所有人员
                vm.selectAllPark();// 查询所有园区
            }
        },
        methods: {
            //自动计算租金
            autoTotal(flag) {
                if(flag == 1) {//自动计算月租金
                    let list = this.contract.contractHouseList;
                    let total = 0;
                    let propertyUnitPrice = this.contract.propertyUnitPrice;
                    this.contract.rentTotal = 0;
                    this.contract.propertyTotal = 0;
                    this.contract.totalRentalArea = 0;
                    for (let i = 0; i < list.length; i++) {
                        let investArea = 0;
                        let unitPrice = 0;
                        if (list[i].investArea && list[i].unitPrice && list[i].unit) {
                            investArea = list[i].investArea;
                            unitPrice = list[i].unitPrice;
                            this.contract.totalRentalArea += Number(investArea);
                            if (list[i].unit == "元/㎡/天") {
                                list[i].monthlyRent = (investArea * unitPrice * (365 / 12)).toFixed(2);
                            } else if (list[i].unit == "元/㎡/月") {
                                list[i].monthlyRent = (investArea * unitPrice).toFixed(2);
                            } else if (list[i].unit == "元/㎡/年") {
                                list[i].monthlyRent = (investArea * unitPrice / 12).toFixed(2);
                            }
                            this.contract.rentTotal = Number(this.contract.rentTotal) + Number(list[i].monthlyRent);
                            this.contract.propertyTotal += investArea * propertyUnitPrice;
                            total = (this.contract.rentTotal + this.contract.propertyTotal).toFixed(2);
                        }
                    }
                    this.contract.propertyTotal = this.contract.propertyTotal.toFixed(2);
                    this.contract.total = Number(this.contract.rentTotal) + Number(this.contract.propertyTotal);
                } else if(flag == 2) {//修改每月租金或者每月物业费
                    let list = this.contract.contractHouseList;
                    this.contract.rentTotal = 0;
                    for (let i = 0; i < list.length; i++) {
                        this.contract.rentTotal = Number(this.contract.rentTotal) + Number(list[i].monthlyRent);
                    }
                    this.contract.total = Number(this.contract.rentTotal) + Number(this.contract.propertyTotal);
                }
            },

            // 返回租户详情页
            goContractDetailPage(){

                //首先把本修改页签关闭
                let dataDelObj = {
                    flag: "del",
                    menuName:"合同变更",
                    item: "contractEdit"
                };
                this.$emit("backParam",dataDelObj);

                //最后把新详情页签打开
                let dataObj = {
                    flag: "add",
                    menuName:"合同详情",
                    item: "contractDetails",
                    params: {
                        ctCode: this.contract.ctCode,
                    }
                };
                this.$emit("backParam",dataObj);

            },
            // 合同详情
            contractDetail(ctCode){
                let vm = this;

                let params = {
                    entity:{
                        ctCode: ctCode
                    }
                };

                let options = {
                    method: "POST",
                    data: params,
                    url: "contract/detail",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if(response){
                        vm.contract = response.data.result;

                        // 合同状态为2、3、4的情况下编辑页面无法访问，直接回到上一页
                        if (vm.contract.ctStatus == 2 || vm.contract.ctStatus == 3 || vm.contract.ctStatus == 4) {
                            window.history.back();
                        }

                        vm.queryHouseByCtCode(vm.contract.ctCode);// 根据ctCode查询房源信息
                        vm.selectParkByCode(vm.contract.pkCode);// 根据当前pkCode查询楼层
                        vm.queryCustomer(vm.contract.cusCode,2);// 查询客户信息

                        vm.fileList = vm.contract.contractUrl.split('，');

                        let fls = [];
                        for (let i = 0; i < vm.contract.contractHouseList.length; i++) {
                            let code = vm.contract.contractHouseList[i].bdName;
                            let name = vm.contract.contractHouseList[i].bdName + "栋" + vm.contract.contractHouseList[i].flCount + "层";
                            fls.push(
                                {code: code, name: name}
                            )
                        }
                        vm.fls = fls;


                        let freeRentStartTime = vm.contract.freeRentStartTime;
                        let freeRentEndTime = vm.contract.freeRentEndTime;
                        vm.freeRentDateList = [freeRentStartTime,freeRentEndTime];


                    }
                }).catch(function (error) {
                    vm.$alert('页面：查看租户详细信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 删除文件
            deleteFile(index){
                this.$confirm('是否删除该文件？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.fileList.splice(index, 1);
                    this.contract.contractUrl = this.fileList.join('，');

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
            // 根据合同ctCode查询房源
            queryHouseByCtCode(ctCode){
                let vm = this;
                let params = {
                    entity: {
                        ctCode: ctCode,
                    }
                };
                let options = {
                    method: 'POST',
                    data: params,
                    url: "ctHouse/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.contractHouseArr = response.data.result;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：修改租户查询房源信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 查询人员
            queryEmp(){
                let vm = this;
                let empOptions = {
                    method: 'POST',
                    data: {},
                    url: "employee/list",
                };
                this.$ajax(
                    empOptions
                ).then(function (response) {
                    if (response) {
                        let result = response.data.result;
                        let empList = [];
                        let employees = [];
                        for (let s = 0; s < result.length; s++) {
                            let code = result[s].empCode;
                            let name = result[s].empName;
                            let phone = result[s].empPhone;
                            let cpyName = result[s].cpyName;
                            let deptCode = result[s].deptCode;
                            let deptName = result[s].deptName;
                            empList.push(
                                {code: code, name: name}
                            );
                            employees[code] = {
                                empName: name,
                                deptCode: deptCode,
                                deptName: deptName,
                                phone: phone,
                                cpyName: cpyName
                            };
                        }
                        vm.empList = empList;
                        vm.employees = employees;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：新增租户查询人员信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            remoteMethod(query){
                if(query.trim() != ''){
                    this.loading = true;
                    setTimeout(() =>{
                        this.queryCustomer(query.trim(),1);
                    },200);
                }else{
                    this.cusNames = [];
                }

            },

            // 查询客户
            // type == 1：客户下拉框远程搜索
            // type == 2：初次加载出客户数据（一个客户数据）
            queryCustomer(keyword, type){
                let data = {};
                if(type == 1) {
                    data = {
                        keyword:keyword
                    }
                }
                else if(type == 2) {
                    data = {
                        entity: {
                            cusCode:keyword
                        }
                    }
                }
                let vm = this;
                let options = {
                    method: 'POST',
                    data: data,
                    url: "customer/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        let result = response.data.result;
                        let cusNameList = [];
                        let customers = [];
                        for (let s = 0; s < result.length; s++) {
                            let code = result[s].cusCode;
                            let name = "";
                            let phone = result[s].mainContactPhone;
                            if (result[s].customerType == "1") {
                                name = result[s].mainContactName + "(" + result[s].companyName + ")";
                                cusNameList.push(
                                    {code: code, name: name, phone: phone}
                                );
                            } else {
                                name = result[s].mainContactName;
                                cusNameList.push(
                                    {code: code, name: name, phone: phone}
                                );
                            }
                            customers[code] = name;
                        }
                        vm.cusNames = cusNameList;
                        vm.customers = customers;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：新增租户客户搜索栏查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 查询所有园区
            selectAllPark(){
                let vm = this;

                let options = {
                    method: 'POST',
                    data: {},
                    url: "park/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        let result = response.data.result;
                        vm.pkAllList = result;
                        let pkNameList = [];
                        let pkObj = [];
                        for (let s = 0; s < result.length; s++) {
                            let code = result[s].pkCode;
                            let name = result[s].pkName;
                            pkNameList.push(
                                {code: code, name: name}
                            );
                            pkObj[code] = name;
                        }
                        vm.pkNames = pkNameList;
                        vm.pkObj = pkObj;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：新增租户客户房源搜索查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            /***************************************************************************************************/
            // 免租期：日期处理
            freeRentDateMethod(){
                this.contract.freeRentStartTime = this.freeRentDateList[0];
                this.contract.freeRentEndTime = this.freeRentDateList[1];
            },
            //租金详细递增预览
            incDetailPreview() {
                let vm = this;

                if (vm.contract.rentIncrementList && vm.contract.rentIncrementList.length > 0) {
                    vm.$confirm('已生成预览列表，如有改动请重新生成', '提示', {
                        confirmButtonText: '生成',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        vm.contract.rentIncrementList = [];
                        vm.incDetailPreview();
                    }).catch(() => {
                        //租金递增详情预览打开
                        vm.incDetailDialog = true;
                    });

                } else {
                    let avgUnitPrice = 0;
                    if (!vm.contract.contractStartTime || !vm.contract.contractEndTime) {
                        vm.$alert('提示：请先选择合同周期', '温馨提示', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                        return;
                    }

                    //循环房源集合，判断code不为空
                    for (let i = 0; i < vm.contract.contractHouseList.length; i++) {
                        if (vm.contract.contractHouseList[i].flCode == '' || vm.contract.contractHouseList[i].investArea == '' || vm.contract.contractHouseList[i].unitPrice == '') {
                            //租用楼层为空
                            vm.$alert('提示：请填写好租用楼层的信息', '温馨提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                            return;
                        } else {
                            avgUnitPrice = Number(avgUnitPrice) + Number(vm.contract.contractHouseList[i].unitPrice);
                        }
                    }
                    //记录平均单价
                    avgUnitPrice = (avgUnitPrice/vm.contract.contractHouseList.length).toFixed(4);

                    //判断详细递增是不是为空
                    if (vm.contract.incDetailType == 1) {
                        //先判断他是不是详细递增类型
                        for (let i = 0; i < vm.incDetailCase.length; i++) {
                            if (vm.incDetailCase[i].value === '') {
                                //详细递增为空
                                vm.$alert('提示：输入租金递增的数据不完整', '温馨提示', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                    }
                                });
                                return;
                            }
                        }
                    } else {
                        if (!(vm.contract.increasingCycle && vm.contract.rentIncreaseProportion)) {
                            vm.$alert('提示：输入租金递增的数据不完整', '温馨提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                            return;
                        }
                    }


                    vm.contract.rentIncrementList = [];
                    let conStartDate = new Date(vm.contract.contractStartTime);
                    let conEndDate = new Date(vm.contract.contractEndTime);

                    let startDate = new Date(conStartDate);
                    //获取年差
                    let sYear = conStartDate.getFullYear();
                    let eYear = conEndDate.getFullYear();
                    //开始年的年数设置成结束年年数看是否大于结束年
                    let seDate = new Date(conStartDate);
                    seDate.setYear(eYear);
                    let listSize = eYear - sYear;
                    if (seDate.getTime() <= conEndDate.getTime()) {
                        listSize = listSize + 1;
                    }
                    for (let i = 0; i < listSize; i++) {
                        if (startDate.getTime() < conEndDate.getTime()) {
                            let rentInc = {
                                incStartTime: "",//递增开始时间
                                incEndTime: "",//递增结束时间
                                rentTotal: "",//每月租金
                                avgUnitPrice: "",//平均单价(元/平米/天)
                                rentInvestArea: vm.contract.totalRentalArea,//出租含公摊面积
                                rentIncreaseProportion: ""//递增比
                            };
                            let sDate = new Date(startDate);
                            let eDate = new Date(sDate);
                            eDate.setYear(eDate.getFullYear() + 1);

                            rentInc.incStartTime = sDate;
                            rentInc.incEndTime = eDate;
                            rentInc.incStartTime = rentInc.incStartTime.getTime();
                            if (rentInc.incEndTime >= conEndDate.getTime()) {
                                rentInc.incEndTime = conEndDate.getTime() + (24*60*60*1000) - 1;
                            } else {
                                rentInc.incEndTime = rentInc.incEndTime.getTime() - 1;
                            }

                            startDate = eDate;
                            vm.contract.rentIncrementList.push(rentInc);
                        }
                    }

                    let rentTotal = vm.contract.rentTotal;
                    let icy = 0;
                    if (vm.contract.incDetailType == "2") {
                        icy = Number(vm.contract.increasingCycle);
                    }
                    for (let j = 0; j < vm.contract.rentIncrementList.length; j++) {
                        if (vm.contract.incDetailType == "1") {//详细递增
                            if (vm.incDetailCase[j]) {
                                rentTotal = (rentTotal * (1 + Number(vm.incDetailCase[j].value) / 100)).toFixed(2);
                                vm.contract.rentIncrementList[j].rentIncreaseProportion = Number(vm.incDetailCase[j].value);
                            } else {
                                rentTotal = vm.contract.rentIncrementList[j - 1].rentTotal;
                                vm.contract.rentIncrementList[j].rentIncreaseProportion = 0;
                                vm.incDetailCase.push({"value":"0"});
                            }

                        } else {//每n年递增m%
                            if (j == icy) {
                                rentTotal = (rentTotal * (1 + Number(vm.contract.rentIncreaseProportion) / 100)).toFixed(2);
                                vm.contract.rentIncrementList[j].rentIncreaseProportion = vm.contract.rentIncreaseProportion;
                                //下次递增的下标
                                icy += Number(vm.contract.increasingCycle);
                            } else {
                                if (vm.contract.rentIncrementList[j - 1]) {
                                    rentTotal = vm.contract.rentIncrementList[j - 1].rentTotal;
                                }
                                vm.contract.rentIncrementList[j].rentIncreaseProportion = 0;
                            }
                        }
                        avgUnitPrice = avgUnitPrice * (1 + Number(vm.contract.rentIncrementList[j].rentIncreaseProportion)/100);
                        vm.contract.rentIncrementList[j].rentTotal = rentTotal;
                        vm.contract.rentIncrementList[j].avgUnitPrice = avgUnitPrice.toFixed(4);
                    }

                    //租金递增详情预览打开
                    vm.incDetailDialog = true;

                }
                //判断是否点击过递增详情
                vm.isIncDel = true;

            },
            //点击修改
            editRentIncDialog(val) {
                let vm = {};
                vm = this;

                vm.editIncModel = vm.contract.rentIncrementList[val];
                vm.editIncModel.incIndex = val;

                vm.editIncModelCopy.rentTotal = vm.editIncModel.rentTotal;
                vm.editIncModelCopy.avgUnitPrice = vm.editIncModel.avgUnitPrice;
                //递增列表弹窗先关闭
                vm.incDetailDialog = false;
                //修改弹窗打开
                vm.editIncDelDialog = true;

            },

            //修改取消
            cancelEditIncDel() {
                this.editIncDelDialog = false;
                this.incDetailDialog = true;
                this.contract.rentIncrementList[this.editIncModel.incIndex].rentTotal = this.editIncModelCopy.rentTotal;
                this.contract.rentIncrementList[this.editIncModel.incIndex].avgUnitPrice = this.editIncModelCopy.avgUnitPrice;
            },
            //修改提交
            submitEditIncDel(subForm) {
                if (this.editIncModel.rentTotal && this.editIncModel.avgUnitPrice) {
                    this.contract.rentIncrementList[this.editIncModel.incIndex] = this.editIncModel;
                    this.editIncDelDialog = false;
                    this.incDetailDialog = true;
                }
            },

            //重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.contract.houseAddress = '';
                this.fileList = [];
            },
            //校验房屋出租非必填字段0～99999999.99
            checkHouseNum(value, index, val){
                if (val < 0 || val > 99999999.99) {
                    this.$message.error('输入数字超出0～99999999.99范围');
                    if (value == 2) {
                        this.contract.contractHouseList[index].unitPrice = 0;
                    }
                }

                if (value == 2) {
                    if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(val))) {
                        this.contract.contractHouseList[index].unitPrice = 0;
                        this.$message.error('请输入正整数或小数');
                    }
                }
                this.autoTotal(1);
            },
            //校验非必填字段0～99999999.99
            checkNum(value){
                if (value == 'rentIncreaseProportion') {
                    let rent = this.contract.rentIncreaseProportion;
                    if (rent < 0 || rent > 99999999.99) {
                        this.$message.error('租金递增输入数字超出0～99999999.99范围');
                        this.contract.rentIncreaseProportion = 0;
                    }

                    if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(rent))) {
                        this.$message.error('递增百分比只能为正整数或小数');
                        this.contract.rentIncreaseProportion = 0;
                    }
                } else if (value == 'depositRule') {
                    let dep = this.contract.depositRule;
                    if (dep < 0 || dep > 99999999.99) {
                        this.$message.error('付款方式输入数字超出0～99999999.99范围');
                        this.contract.depositRule = 0;
                    }

                    if (!(/^[0-9]\d*$/.test(dep))) {
                        this.$message.error('押金月数只能为正整数');
                        this.contract.depositRule = 0;
                    }
                } else if (value == 'fpayAllPrice') {
                    let dep = this.contract.fpayAllPrice;
                    if (dep < 0 || dep > 99999999.99) {
                        this.$message.error('首付总额输入数字超出0～99999999.99范围');
                        this.contract.fpayAllPrice = 0;
                    }
                }
            },
            //选择签单人
            changeEmp(code){
                let employee = this.employees[code];
                this.contract.contractor = employee.empName;
                this.contract.contractorDeptCode = employee.deptCode;
                this.contract.contractorDept = employee.deptName;
                this.contract.contractorPhone = employee.phone;
                this.contract.partyName = employee.cpyName;
            },

            //记忆
            remember() {
                window.localStorage.setItem("_contObj", JSON.stringify(this.cont));
                this.$message.success("记忆本页填写信息成功");
            },
            //提交
            submit(subForm) {
                let vm = this;

                // 合同状态为待审核无发修改
                if (vm.contract.ctStatus == '2') {
                    vm.$alert('页面：该合同已经审核通过，无法修改！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                if (!vm.isIncDel) {
                    vm.$alert('提示：请先点击递增详情查看无误再提交', '温馨提示', {
                        confirmButtonText: '递增详情',
                        callback: action => {
                            vm.incDetailPreview();
                        }
                    });
                    return;
                }

                let pkname = window.document.getElementById("pkName");
                vm.contract.pkName = pkname.value;

                //租金详情递增处理
                let incList = vm.incDetailCase;
                let string = "";
                for (let i = 0; i < incList.length; i++) {
                    let value = incList[i].value;
                    string += value + ",";
                }
                vm.contract.incDetailCase = string;

                //循环房源集合，判断code不为空
                if(vm.contract.contractHouseList.length <= 0) {
                    vm.$alert('页面：请填写房屋出租情况！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }
                for (let i = 0; i < vm.contract.contractHouseList.length; i++) {
                    if (vm.contract.contractHouseList[i].flCode == '' || vm.contract.contractHouseList[i].investArea == ''
                        || vm.contract.contractHouseList[i].unitPrice == '' || vm.contract.contractHouseList[i].unit == '') {
                        vm.$alert('页面：请完善房屋出租情况！', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                        return;
                    }
                }

                //判断附件
                let fileList = vm.fileList;
                if(fileList.length<1){
                    vm.$message({
                        type: 'warning',
                        message: '请选择文件！'
                    })
                    return;
                }
                vm.contract.contractUrl = fileList.join("，");

                let obj = {
                    entity: vm.contract,
                };
                this.$refs[subForm].validate((valid) => {
                    if (valid) {
                        let options = {
                            method: 'POST',
                            data: obj,
                            url: "contract/update",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (response) {
                                vm.$message({
                                    showClose: true,
                                    message: '修改合同成功',
                                    type: 'success',
                                });
                                vm.goContractDetailPage();
                            }
                        }).catch(function (error) {
                            vm.$alert('页面：修改合同失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {}
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

            // 通过cusCode搜索
            selectCusByCode(cusCode){
                if (cusCode == "") {
                    return
                }
                let vm = this;
                let obj = {
                    entity: {cusCode: cusCode}
                };
                let options = {
                    method: 'POST',
                    data: obj,
                    url: "customer/detailsOnlyTable",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        let customer = response.data.result;
                        vm.contract.cusCode = customer.cusCode;
                        vm.contract.customerType = customer.customerType;
                        vm.contract.companyName = customer.companyName;
                        vm.contract.tradeName = customer.tradeName;
                        vm.contract.companyProduct = customer.companyProduct;
                        vm.contract.relateName = customer.mainContactName;
                        vm.contract.relateSex = customer.mainContactSex;
                        vm.contract.relatePhone = customer.mainContactPhone;
                        let useList = customer.useType.split(",");
                        vm.checkUseType = useList;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：新增租户查询客户信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });

            },
            // 通过pkCode搜索
            selectParkByCode(pkCode){
                this.contract.pkCode = pkCode;

                for (let i = 0; i < this.pkAllList.length; i++) {
                    if (this.pkAllList[i].pkCode == pkCode) {
                        this.contract.houseAddress = this.pkAllList[i].provinceName + this.pkAllList[i].cityName + this.pkAllList[i].regionName + this.pkAllList[i].streetName + this.pkAllList[i].address;
                    }
                }

                // 园区选择后,查询出出租楼层
                if (pkCode == "") {
                    return
                }

                let vm = this;

                let obj = {
                    entity: {
                        pkCode: pkCode
                    }
                };
                let options = {
                    method: 'POST',
                    data: obj,
                    url: "floor/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        let floors = response.data.result;
                        let fls = [];
                        for (let i = 0; i < floors.length; i++) {
                            let code = floors[i].flCode;
                            let name = floors[i].bdName + "#" + floors[i].thisFloor + "层";
                            fls.push(
                                {code: code, name: name}
                            )
                        }
                        vm.fls = fls;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：新增租户查询楼层信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            //选择楼层时,将flCode的楼层信息放入楼层集合中
            selectFloor(code, index){
                this.contract.contractHouseList[index].investArea = "";
                for(let i=0;i<this.fls.length;i++) {
                    if(code == this.fls[i].code) {
                        this.rentableArea = this.fls[i].rentableArea;
                        break;
                    }
                }
                this.autoTotal(1);
            },
            //添加楼层按钮
            addFloor(){
                this.contract.contractHouseList.push({
                    flCode: "",
                    roomNumber: "",
                    roomCode: "",
                    areaRate: "",
                    investArea: "",
                    unitPrice: ""
                });
                this.itRoomArr.push([]);//存入数组
            },
            //清空楼层
            clearFloor() {
                this.contract.contractHouseList = [{
                    flCode: "",
                    roomNumber: "",
                    roomCode: "",
                    areaRate: "",
                    investArea: "",
                    unitPrice: ""
                }]
            },
            //添加递增详情按钮
            addIncDetail(){
                this.incDetailCase.push({value: ""});
            },
            clearIncDetail() {
                this.incDetailCase = [{
                    value: "0"
                }]
            },
            goBack(){
                window.history.back();
            },
            handCusTypeCompany(){
                this.companyNameFlag = false;
            },
            handCusType() {
                this.companyNameFlag = true;
            },
            selectFile(urls){
                if (urls) {
                    for (let i = 0; i < urls.length; i++) {
                   //     this.fileList.push(urls[i].fileCode+'《'+urls[i].fileName+'》');
                        this.fileList.push(urls[i].fileName+'---'+urls[i].fileCode);

                        this.contract.contractUrl = this.fileList.join('，');
                    }
                } else {
                    this.fileList = [];
                }
            },

            //校验合同时间
            checkConTime(val) {
                let vm = this;
                if (val === 0) {//合同开始日
                    if (vm.contract.contractEndTime && (vm.contract.contractEndTime <= vm.contract.contractStartTime)) {
                        vm.$message({
                            showClose: true,
                            message: "选择合同开始日期要大于合同结束日期",
                            type: "error"
                        });
                        vm.contract.contractStartTime = "";
                    }
//                    if (vm.contract.startRentDate && (vm.contract.startRentDate < vm.contract.contractStartTime)) {
//                        vm.$message({
//                            showClose: true,
//                            message: "选择合同开始日期要大于计租日期",
//                            type: "error"
//                        });
//                        vm.contract.contractStartTime = '';
//                    }
                } else if (val === 1) {//合同结束日
                    if (!vm.contract.contractStartTime) {
                        vm.$message({
                            showClose: true,
                            message: "请先选择合同开始日期",
                            type: "error"
                        });
                        vm.contract.contractEndTime = "";
                    } else {
                        if (vm.contract.contractEndTime <= vm.contract.contractStartTime) {
                            vm.$message({
                                showClose: true,
                                message: "选择合同结束日期不能小于合同开始日期",
                                type: "error"
                            });
                            vm.contract.contractEndTime = "";
                        }
                    }
                }
//               else if (val === 2) {//计租日期
//                    if (vm.contract.contractStartTime && (vm.contract.startRentDate < vm.contract.contractStartTime)) {
//                        vm.$message({
//                            showClose: true,
//                            message: "选择计租日期不能小于合同开始日期",
//                            type: "error"
//                        });
//                        vm.contract.startRentDate = "";
//                    }
//                }

            },
            // 删除房源
            deleteThisHouse(index, rentHosCode){
                this.$confirm('是否删除该房源信息？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.contract.contractHouseList.splice(index, 1);
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
    /*----------------------------------- 导航栏 -----------------------------------*/
    /*导航栏标题*/
    .navigation_title {
        float: left;
        line-height: 50px;
        color: #409EFF;
    }

    /*导航栏提交按钮*/
    .navigation_button_submit {
        float: left;
        margin-right: 20px;
        margin-left: 20px;
    }


    /*----------------------------------- 公共标题 -----------------------------------*/
    /*公共的，标题div*/
    .common_title_div {
        background-color: white;
        height: 50px;
        width: 100%;
        border-bottom: 1px solid rgb(242, 242, 242);
        margin-top: 6px;
    }

    /*公共的，标题文字*/
    .common_title_font {
        float: left;
        color: #409EFF;
        margin-left: 20px;
        line-height: 50px;
    }

    /*公共的，内容区div*/
    .common_content_div {
        background-color: white;
        text-align: left;
    }

    /*行样式*/
    .row_style {
        padding-top: 10px;
    }

    .conHouseBox {
        padding-top: 20px;
        border:1px solid #dcdfe6;
        margin-bottom: 4px;
    }

    .download_file a:hover{
        cursor: pointer;
        color: #66b1ff;
        text-decoration: underline;
    }

    .el-row-style{
        height: 20px;
        line-height: 20px;
        margin-left: 20px;
    }
</style>