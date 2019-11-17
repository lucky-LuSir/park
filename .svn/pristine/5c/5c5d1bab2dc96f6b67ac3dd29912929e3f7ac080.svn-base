<template>
    <div class="has-position_root" ref="myRoot"><!--根部分-->
        <div class="has-position_header">
            <el-button v-if="cont.contract.ctCode == null" size="small" class="navigation_button_submit"
                       type="primary" @click="submit('cont.contract')">提交
            </el-button>
            <el-button v-if="cont.contract.ctCode == null" size="small" class="navigation_button_submit"
                       type="primary" @click="remember">记忆
            </el-button>
            <el-button v-if="cont.contract.ctCode == null" size="small" class="navigation_button_submit"
                       type="primary" @click="restore">恢复
            </el-button>
        </div>

        <el-form class="has-position_main" ref="cont.contract" :rules="rules" :model="cont.contract" label-width="100px" label-suffix=":">

            <div class="common_title_div">
                <span class="common_title_font">基本信息</span>
            </div>
            <div class="common_content_div">
                <el-row :gutter="30" class="row_style">
                    <el-col :xs="9" :sm="9" :md="9" :lg="9" :xl="9">
                        <el-form-item label="客户信息" prop="cusCode">
                            <el-select
                                    style="width: 350px;"
                                    v-model="cont.contract.cusCode"
                                    filterable
                                    remote
                                    :remote-method="remoteMethod"
                                    :loading="loading"
                                    placeholder="输入客户姓名"
                                    @change="selectCusByCode">
                                <el-option
                                        v-for="item in cont.cusNames"
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
                <el-row :gutter="30" style="margin-top: 10px;margin-bottom: 15px;">
                    <el-col :xs="9" :sm="9" :md="9" :lg="9" :xl="9">
                        <el-form-item label="客户类型" prop="customerType">
                            <el-radio-group v-model="cont.contract.customerType">
                                <el-radio label="1">公司</el-radio>
                                <el-radio label="2">个人</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="公司名称" prop="companyName">
                            <el-input v-model="cont.contract.companyName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="所属行业" prop="tradeName" style="width: 100%;">
                            <el-select v-model="cont.contract.tradeName" placeholder="请选择"
                                       style="width:100%;">
                                <el-option
                                        v-for="item in cont.trades"
                                        :key="item.index"
                                        :label="item.value"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :xs="9" :sm="9" :md="9" :lg="9" :xl="9">
                        <el-form-item label="联系人" prop="relateName">
                            <el-input v-model="cont.contract.relateName" style="width: 90px"></el-input>
                            <el-radio-group v-model="cont.contract.relateSex">
                                <el-radio label="1">先生</el-radio>
                                <el-radio label="2">女士</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="联系电话" prop="relatePhone">
                            <el-input v-model="cont.contract.relatePhone"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <div class="grid-content ">
                            <el-form-item label="固定电话">
                                <el-input v-model="cont.contract.landlinePhone" placeholder="请输入号码"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :xs="9" :sm="9" :md="9" :lg="9" :xl="9">
                        <div class="grid-content ">
                            <el-form-item label="租房用途" label-width="100px">
                                <el-checkbox-group v-model="cont.checkUseType">
                                    <el-checkbox v-for="useType in cont.useTypeList" :label="useType" :key="useType">{{useType}}</el-checkbox>
                                </el-checkbox-group>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="公司产品" prop="companyProduct">
                            <el-input v-model="cont.contract.companyProduct"></el-input>
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
                            <el-date-picker
                                    v-model="cont.contract.contractDate"
                                    align="right"
                                    type="date">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="签单人" prop="contractorCode">
                            <el-select v-model="cont.contract.contractorCode" @change="changeEmp"
                                       filterable placeholder="请选择">
                                <el-option v-for="item in cont.empList"
                                           :key="item.code"
                                           :label="item.name"
                                           :value="item.code">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="联系电话" prop="contractorPhone">
                            <el-input v-model="cont.contract.contractorPhone"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="甲方公司" prop="partyName">
                            <el-input v-model="cont.contract.partyName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">合同信息</span>
            </div>
            <div class="common_content_div">
                <el-row :gutter="30" class="row_style">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="合同开始" prop="contractStartTime">
                            <el-date-picker
                                    v-model="cont.contract.contractStartTime"
                                    align="right"
                                    type="date"
                                    @change="checkConTime(0)">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="合同结束" prop="contractEndTime">
                            <el-date-picker
                                    v-model="cont.contract.contractEndTime"
                                    align="right"
                                    type="date"
                                    @change="checkConTime(1)">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="免租日期" prop="freeRentTimes">
                            <el-date-picker
                                    v-model="cont.contract.freeRentTimes"
                                    type="daterange"
                                    align="right"
                                    unlink-panels
                                    :clearable="false"
                                    range-separator="至"
                                    start-placeholder="免租开始日"
                                    end-placeholder="免租结束日"
                                    :picker-options="cont.pickerOptions">
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
                            <el-input v-model="cont.contract.propertyUnitPrice" @blur="autoTotal(1)">
                                <template slot="append">{{cont.contract.propertyUnit}}</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="入住园区 " prop="pkName">
                            <el-select
                                    id="pkName"
                                    v-model="cont.contract.pkName"
                                    clearable
                                    filterable
                                    default-first-option
                                    placeholder="输入园区名称或者地址"
                                    @change="selectParkByCode">
                                <el-option
                                        v-for="item in cont.pkNames"
                                        :key="item.code"
                                        :label="item.name"
                                        :value="item.code">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="园区地址">
                            <el-input v-model="cont.contract.houseAddress" readonly="readonly"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="房源操作">
                            <el-button v-if="cont.contract.ctCode == null" size="small" type="primary"
                                       @click="addFloor">添加楼层
                            </el-button>
                            <el-button v-if="cont.contract.ctCode == null" size="small" type="primary"
                                       @click="clearFloor">清空楼层
                            </el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
                <div class="conHouseBox" v-for="(item, index) in cont.contract.contractHouseList">
                    <el-row :gutter="20">
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <el-form-item label="出租楼层">
                                <el-select
                                        v-model="item.flCode"
                                        filterable
                                        placeholder="请输入楼层"
                                        @change="selectFloor(item.flCode,index)">
                                    <el-option
                                            v-for="item in cont.fls"
                                            :key="item.code"
                                            :label="item.name"
                                            :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <el-form-item label="出租房间号">
                                <!--<el-select v-model="item.roomCode"-->
                                <!--@focus="getRoomList(item.flCode,index)"-->
                                <!--@change="getRoomArea(item.roomCode,index)"-->
                                <!--placeholder="请选择">-->
                                <!--<el-option-->
                                <!--v-for="(itemRoom,j) in cont.itRoomArr[index]"-->
                                <!--:key="j"-->
                                <!--:label="itemRoom.roomNumber"-->
                                <!--:value="itemRoom.roomCode">-->
                                <!--</el-option>-->

                                <!--</el-select>-->
                                <el-input v-model="item.roomNumber" placeholder="房间号">
                                </el-input>

                            </el-form-item>
                        </el-col>
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <el-form-item label="房租单价">
                                <el-tooltip content="记得选择价格单位" placement="right">
                                    <el-input v-model="item.unitPrice"
                                              @blur="checkHouseNum(2,index,item.unitPrice)">
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
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                            <el-form-item label="计租面积">
                                <el-input v-model="item.investArea"
                                          :placeholder="'本层剩余：'+cont.rentableArea"
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
                <el-row class="row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="每月合计">
                            <div style="float: left">
                                <el-input v-model="total">
                                    <template slot="append">元/月</template>
                                </el-input>
                            </div>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="出租总面积">
                            <div style="float: left">
                                <el-input v-model="cont.contract.totalRentalArea" disabled>
                                    <template slot="append">㎡</template>
                                </el-input>
                            </div>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="每月物业费">
                            <div style="float: left">
                                <el-input v-model="cont.contract.propertyTotal">
                                    <template slot="append">元/月</template>
                                </el-input>
                            </div>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="每月租金">
                            <div style="float: left">
                                <el-input v-model="cont.contract.rentTotal">
                                    <template slot="append">元/月</template>
                                </el-input>
                            </div>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">租金规则</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="提前交租" prop="advanceDateCount">
                            <el-input v-model="cont.contract.advanceDateCount">
                                <template slot="append">天</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="详细递增">
                            <el-radio-group v-model="cont.contract.incDetailType">
                                <el-radio label='1'>是</el-radio>
                                <el-radio label='2'>否</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item>
                            <div style="line-height: 30px" v-if="cont.contract.incDetailType == 1">
                                <el-button v-if="cont.contract.ctCode == null" size="small" type="warning" plain
                                           @click="addIncDetail">
                                    添加递增
                                </el-button>
                                <el-button v-if="cont.contract.ctCode == null" size="small" type="warning" plain
                                           @click="clearIncDetail">
                                    清空递增
                                </el-button>
                            </div>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="租金递增" prop="increasingCycle" v-if="cont.contract.incDetailType == 2">
                            <div style="float: left">
                                <el-input v-model="cont.contract.increasingCycle" style="width: 210px">
                                    <template slot="prepend">每</template>
                                    <template slot="append">年递增</template>
                                </el-input>
                            </div>
                            <div style="float: left">
                                <el-input v-model="cont.contract.rentIncreaseProportion"
                                          @blur="checkNum('rentIncreaseProportion')" style="width: 140px">
                                    <template slot="append">%</template>
                                </el-input>
                            </div>
                        </el-form-item>
                    </el-col>
                    <div v-if="cont.contract.incDetailType == 1" v-for="(item,index) in cont.incDetailCase">
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
            <el-dialog :visible.sync="cont.incDetailDialog" width="86%" style="line-height: 30px;">
                <p style="font-size: x-large;margin-top: -25px; font-weight: bold;">租金递增详情预览</p>
                <el-table style="min-height: 300px;"
                          :data="cont.contract.rentIncrementList"
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
            <el-dialog :visible.sync="cont.editIncDelDialog" width="70%">
                <el-form :model="cont.editIncModel" ref="cont.editIncModel" :rules="rules" label-width="130px"
                         style="text-align: left">
                    <el-row :gutter="20">
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="递增周期：">
                                {{cont.editIncModel.incStartTime | timeDate}}～{{cont.editIncModel.incEndTime | timeDate}}
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="出租含公摊面积：">
                                {{cont.editIncModel.rentInvestArea}}㎡
                            </el-form-item>
                        </el-col>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="递增比：">
                                {{cont.editIncModel.rentIncreaseProportion}}%
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="每月租金：" prop="rentTotal">
                                <el-input v-model="cont.editIncModel.rentTotal">
                                    <template slot="append">元</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <el-form-item label="平均单价：" prop="avgUnitPrice">
                                <el-input v-model="cont.editIncModel.avgUnitPrice">
                                    <template slot="append">元/㎡/天</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div slot="footer" style="text-align: center;height: 50px;line-height: 50px;">
                    <el-button @click="cancelEditIncDel">取 消</el-button>
                    <el-button type="primary" @click="submitEditIncDel('cont.editIncModel')">修 改</el-button>
                </div>
            </el-dialog>


            <!-- 房租支付方式 -->
            <div class="common_title_div">
                <span class="common_title_font">房租支付方式</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="付款方式" prop="payRule">
                            <div style="float: left">
                                <el-input v-model="cont.contract.payRule" style="width: 200px">
                                    <template slot="prepend">付</template>
                                    <template slot="append">月 押</template>
                                </el-input>
                            </div>
                            <div style="float: left">
                                <el-input v-model="cont.contract.depositRule" @blur="checkNum('depositRule')"
                                          style="width: 130px">
                                    <template slot="append">月</template>
                                </el-input>
                            </div>
                        </el-form-item>
                        <div style="clear:both"></div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="实付押金" prop="deposit">
                            <el-input v-model="cont.contract.deposit">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">租金周期及费用</span>
                <el-button type="primary" size="small" class="common-title-button" @click="addRentWindow()">
                    添加租金
                </el-button>
            </div>
            <div class="common_content_div">
                <div class="contract_box_data">
                    <el-table
                            class="costTable"
                            :data="cont.contract.rentReceiptList"
                            border
                            :highlight-current-row="true"
                            size="small"
                            min-height="500">
                        <el-table-column
                                align="center"
                                label="序号"
                                type="index"
                                width="50px">
                        </el-table-column>
                        <el-table-column
                                header-align="center"
                                min-width="100"
                                label="开始日期">
                            <template slot-scope="scope">{{ scope.row.rentStartTime | timeDate}}</template>
                        </el-table-column>
                        <el-table-column
                                header-align="center"
                                min-width="100"
                                label="结束日期">
                            <template slot-scope="scope">{{ scope.row.rentEndTime | timeDate}}</template>
                        </el-table-column>
                        <el-table-column
                                header-align="center"
                                min-width="100"
                                label="截止日期">
                            <template slot-scope="scope">{{ scope.row.receivingDate | timeDate}}</template>
                        </el-table-column>
                        <el-table-column
                                header-align="center"
                                min-width="100"
                                prop="receivableRent"
                                label="租金">
                        </el-table-column>
                        <el-table-column
                                header-align="center"
                                min-width="70"
                                label="租金是否含税">
                            <template slot-scope="scope">
                                <span v-if="scope.row.hasTax">是</span>
                                <span v-if="!scope.row.hasTax">否</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                header-align="center"
                                min-width="70"
                                label="税点">
                            <template slot-scope="scope">
                                <span v-if="scope.row.hasTax">{{ cont.taxPoint[parseInt(scope.row.taxPoint) - 3].label }}</span>
                                <span v-if="!scope.row.hasTax">---</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                header-align="center"
                                min-width="100"
                                prop="propertyPrice"
                                label="物业费">
                        </el-table-column>
                        <el-table-column
                                header-align="center"
                                min-width="70"
                                label="物业费是否含税">
                            <template slot-scope="scope">
                                <span v-if="scope.row.proHasTax">是</span>
                                <span v-if="!scope.row.proHasTax">否</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                header-align="center"
                                min-width="70"
                                label="税点">
                            <template slot-scope="scope">
                                <span v-if="scope.row.proHasTax">{{ cont.taxPoint[parseInt(scope.row.proTaxPoint) - 3].label }}</span>
                                <span v-if="!scope.row.proHasTax">---</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                header-align="center"
                                min-width="100"
                                prop="totalPrice"
                                label="合计应收">
                        </el-table-column>
                        <el-table-column
                                header-align="center"
                                fixed="right"
                                label="操作"
                                width="200">
                            <template slot-scope="scope">
                                <el-button @click="editRentWindow(scope.$index)" type="text" size="small">修改</el-button>
                                <el-button @click="delRentWindow(scope.$index)" type="text" size="small">移除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>

            <!-- 添加租金弹窗 -->
            <el-dialog :title="cont.rentTitle" :visible.sync="cont.addRentReceipt" width="60%" center
                       style="line-height: 20px;border: solid 1px;">
                <el-form :model="cont.rentReceiptObj" :rules="rentRules" ref="cont.rentReceiptObj" label-width="100px"
                         style="text-align: left;">
                    <el-row :gutter="10" class="common-el-row-style">
                        <el-col :span="12">
                            <el-form-item label="开始日期" prop="rentStartTime">
                                <el-date-picker
                                        v-model="cont.rentReceiptObj.rentStartTime"
                                        align="right"
                                        type="date">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="结束日期" prop="rentEndTime">
                                <el-date-picker
                                        v-model="cont.rentReceiptObj.rentEndTime"
                                        align="right"
                                        type="date">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="10" class="common-el-row-style">
                        <el-col :span="12">
                            <el-form-item label="截止日期" prop="receivingDate">
                                <el-date-picker
                                        v-model="cont.rentReceiptObj.receivingDate"
                                        align="right"
                                        type="date">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="本期租金" prop="receivableRent">
                                <el-input clearable v-model="cont.rentReceiptObj.receivableRent">
                                    <template slot="append">元</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content" style="width: 300px;height: 50px;">
                                <el-form-item label="是否含税" prop="hasTax">
                                    <el-radio-group v-model="cont.rentReceiptObj.hasTax">
                                        <el-radio :label=true>是</el-radio>
                                        <el-radio :label=false>否</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>

                        <el-col :span="8" v-if="cont.rentReceiptObj.hasTax==true">
                            <el-form-item label="税点" prop="taxPoint">
                                <el-select v-model="cont.rentReceiptObj.taxPoint" placeholder="请选择">
                                    <el-option
                                            v-for="item in cont.taxPoint"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="物业费" prop="propertyPrice">
                                <el-input clearable v-model="cont.rentReceiptObj.propertyPrice">
                                    <template slot="append">元</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content" style="width: 300px;height: 50px;">
                                <el-form-item label="是否含税" prop="proHasTax">
                                    <el-radio-group v-model="cont.rentReceiptObj.proHasTax">
                                        <el-radio :label=true>是</el-radio>
                                        <el-radio :label=false>否</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>

                        <el-col :span="8" v-if="cont.rentReceiptObj.proHasTax==true">
                            <el-form-item label="税点" prop="proTaxPoint">
                                <el-select v-model="cont.rentReceiptObj.proTaxPoint" placeholder="请选择">
                                    <el-option
                                            v-for="item in cont.taxPoint"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <!--<el-row :gutter="20" style="margin-top: 15px;height: 50px;">-->
                        <!--<el-col :span="8">-->
                            <!--<div class="grid-content" style="width: 300px;height: 50px;">-->
                                <!--<el-form-item label="是否含税" prop="hasTax">-->
                                    <!--<el-radio-group v-model="cont.rentReceiptObj.hasTax">-->
                                        <!--<el-radio :label=true>是</el-radio>-->
                                        <!--<el-radio :label=false>否</el-radio>-->
                                    <!--</el-radio-group>-->
                                <!--</el-form-item>-->
                            <!--</div>-->
                        <!--</el-col>-->

                        <!--<el-col :span="8" v-if="cont.rentReceiptObj.hasTax==true">-->
                            <!--<el-form-item label="税点" prop="taxPoint">-->
                                <!--<el-select v-model="cont.rentReceiptObj.taxPoint" placeholder="请选择">-->
                                    <!--<el-option-->
                                            <!--v-for="item in cont.taxPoint"-->
                                            <!--:key="item.value"-->
                                            <!--:label="item.label"-->
                                            <!--:value="item.value">-->
                                    <!--</el-option>-->
                                <!--</el-select>-->
                            <!--</el-form-item>-->
                        <!--</el-col>-->

                        <!--<el-col :span="8" v-if="cont.rentReceiptObj.hasTax==true">-->
                            <!--<el-form-item label="税金" prop="taxPrice">-->
                                <!--<el-input clearable v-model="cont.rentReceiptObj.taxPrice"></el-input>-->
                            <!--</el-form-item>-->
                        <!--</el-col>-->
                    <!--</el-row>-->

                    <el-row :gutter="10">
                        <el-col :span="10">
                            <el-form-item label="合计应收" prop="totalPrice">
                                <el-input clearable v-model="cont.rentReceiptObj.totalPrice">
                                    <template slot="append">元</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="cont.addRentReceipt = false">取 消</el-button>
                    <el-button v-if="cont.addOrEditRent == 1" type="primary"
                               @click="addRentSubmitForm('cont.rentReceiptObj')">确 定
                    </el-button>
                    <el-button v-if="cont.addOrEditRent == 2" type="primary"
                               @click="editRentSubmitForm('cont.rentReceiptObj')">确 定
                    </el-button>
                </div>
            </el-dialog>


            <div class="common_title_div">
                <span class="common_title_font">保险费</span>
                <el-button type="primary" size="small" class="common-title-button" @click="addPremiumMethod()">
                    添加保险
                </el-button>
            </div>
            <div class="common_content_div">
                <el-table class="costTable" :data="cont.contract.premiumReceiptList" border
                          :highlight-current-row="true" size="small" min-height="500">
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            label="开始日期">
                        <template slot-scope="scope">{{ scope.row.premiumStartDate | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            label="结束日期">
                        <template slot-scope="scope">{{ scope.row.premiumEndDate | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            label="截止日期">
                        <template slot-scope="scope">{{ scope.row.deadline | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            prop="premiumAmount"
                            label="投保金额">
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            prop="receivablePremium"
                            label="应收保险费">
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="70"
                            label="是否含税">
                        <template slot-scope="scope">
                            <span v-if="scope.row.hasTax">是</span>
                            <span v-if="!scope.row.hasTax">否</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="70"
                            label="税点">
                        <template slot-scope="scope">
                            <span v-if="scope.row.hasTax">{{ cont.taxPoint[parseInt(scope.row.taxPoint) - 3].label }}</span>
                            <span v-if="!scope.row.hasTax">---</span>
                        </template>
                    </el-table-column>
                    <!--<el-table-column-->
                            <!--header-align="center"-->
                            <!--min-width="100"-->
                            <!--label="税金">-->
                        <!--<template slot-scope="scope">-->
                            <!--<span v-if="scope.row.hasTax">{{ scope.row.taxPrice }}</span>-->
                            <!--<span v-if="!scope.row.hasTax">-&#45;&#45;</span>-->
                        <!--</template>-->
                    <!--</el-table-column>-->
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            prop="totalPrice"
                            label="合计应收">
                    </el-table-column>
                    <el-table-column header-align="center" fixed="right" label="操作" width="200">
                        <template slot-scope="scope">
                            <el-button @click="openUpdatePremium(scope.$index)" type="text" size="small">
                                修改
                            </el-button>
                            <el-button @click="deletePremium(scope.$index)" type="text" size="small">
                                移除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>

            <!-- 【添加保险费】弹窗 -->
            <el-dialog :title="cont.windowTitle" style="line-height: 30px;" :visible.sync="cont.addPremiumWindow">
                <el-form :model="cont.addPremiumObj" :rules="premiumRules" ref="addPremiumObj" label-width="100px"
                         style="text-align: left;">
                    <el-row :gutter="10" class="common-el-row-style">
                        <el-col :span="12">
                            <el-form-item label="开始日期" prop="premiumStartDate">
                                <el-date-picker
                                        v-model="cont.addPremiumObj.premiumStartDate"
                                        align="right"
                                        type="date">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="结束日期" prop="premiumEndDate">
                                <el-date-picker
                                        v-model="cont.addPremiumObj.premiumEndDate"
                                        align="right"
                                        type="date">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="10" class="common-el-row-style">
                        <el-col :span="12">
                            <el-form-item label="截止日期" prop="deadline">
                                <el-date-picker
                                        v-model="cont.addPremiumObj.deadline"
                                        align="right"
                                        type="date">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="10">
                        <el-col :span="12">
                            <el-form-item label="投保金额" prop="premiumAmount">
                                <el-input clearable v-model="cont.addPremiumObj.premiumAmount">
                                    <template slot="append">元</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="应收保险费" prop="receivablePremium">
                                <el-input clearable v-model="cont.addPremiumObj.receivablePremium">
                                    <template slot="append">元</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :span="8">
                            <div class="grid-content" style="width: 360px;height: 65px;">
                                <el-form-item label="是否含税" prop="hasTax">
                                    <el-radio-group v-model="cont.addPremiumObj.hasTax">
                                        <el-radio :label=true>是</el-radio>
                                        <el-radio :label=false>否</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8" v-if="cont.addPremiumObj.hasTax==true">
                            <el-form-item label="税点" prop="taxPoint">
                                <el-select v-model="cont.addPremiumObj.taxPoint" placeholder="请选择">
                                    <el-option
                                            v-for="item in cont.taxPoint"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <!--<el-col :span="8" v-if="cont.addPremiumObj.hasTax==true">-->
                            <!--<el-form-item label="税金" prop="taxPrice">-->
                                <!--<el-input clearable v-model="cont.addPremiumObj.taxPrice"></el-input>-->
                            <!--</el-form-item>-->
                        <!--</el-col>-->
                    </el-row>
                    <el-row :gutter="10">
                        <el-col :span="12">
                            <el-form-item label="合计应收" prop="totalPrice">
                                <el-input clearable v-model="cont.addPremiumObj.totalPrice">
                                    <template slot="append">元</template>
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div v-if="cont.commonAddOrEdit == 1" slot="footer" class="dialog-footer">
                    <el-button @click="cancleAddPremium('addPremiumObj')">取 消</el-button>
                    <el-button type="primary" @click="addPremiumSubmit('addPremiumObj')">确 定</el-button>
                </div>
                <div v-if="cont.commonAddOrEdit == 2" slot="footer" class="dialog-footer">
                    <el-button @click="cont.addPremiumWindow = false">取 消</el-button>
                    <el-button type="primary" @click="editPremiumSubmit('addPremiumObj')">确 定</el-button>
                </div>
            </el-dialog>


            <div class="common_title_div">
                <span class="common_title_font">首付款信息</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="首付总额：">
                            {{fpayAllPrice}}&nbsp;元
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="电费押金" prop="eleDeposit">
                            <el-input v-model="cont.contract.eleDeposit">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="水费押金" prop="waterDeposit">
                            <el-input v-model="cont.contract.waterDeposit">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="工程押金" prop="fpayProjectDeposit">
                            <el-input v-model="cont.contract.fpayProjectDeposit">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="房屋租金" prop="fpayHouseRent">
                            <el-input v-model="cont.contract.fpayHouseRent">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="物业费" prop="fpayPropertyPrice">
                            <el-input v-model="cont.contract.fpayPropertyPrice">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="保险费" prop="fpayInsurancePrice">
                            <el-input v-model="cont.contract.fpayInsurancePrice">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="保证金" prop="fpayBond">
                            <el-input v-model="cont.contract.fpayBond">
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
                            <el-input v-model="cont.contract.outputValue">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="税收金额" prop="taxTake">
                            <el-input v-model="cont.contract.taxTake">
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="税收是否可落户到当地" label-width="200px" prop="hasRevenueBackLocal">
                            <el-radio-group v-model="cont.contract.hasRevenueBackLocal">
                                <el-radio :label=true>是</el-radio>
                                <el-radio :label=false>否</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="设备清单" prop="equipmentList">
                            <el-input v-model="cont.contract.equipmentList"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="设备价值" prop="equipmentValue">
                            <el-input v-model="cont.contract.equipmentValue">
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
                            <el-input v-model="cont.contract.totalElectricity">
                                <template slot="append">kVA</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="基础单价" prop="basicElectricityUnitPrice">
                            <el-input v-model="cont.contract.basicElectricityUnitPrice">
                                <template slot="append">元/kVA/月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="电损" prop="addLoss">
                            <el-input v-model="cont.contract.addLoss">
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
                            <el-radio-group v-model="cont.contract.elHasTaxNum">
                                <el-radio :label=true>是</el-radio>
                                <el-radio :label=false>否</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <div class="meter_info_list_add">
                    <el-button type="primary" icon="el-icon-plus" round class="billingDetail_button" @click="addMeter"
                               size="small">添加电表
                    </el-button>
                </div>
                <div class="meter_info_list" v-for="(item,index) in cont.contract.meterList">
                    <el-form :model="item" ref="item" :rules="rules" label-suffix=":" label-width="100px">
                        <div class="contract_box_down">
                            <el-row>
                                <el-col :span="8">
                                    <div class="contract_box_down_up">
                                        <el-form-item label="电表名称" prop="eleMeterName">
                                            <el-input v-model="item.eleMeterName"
                                                      placeholder="请输入"
                                                      class="contract_box_down_input">
                                            </el-input>
                                        </el-form-item>
                                    </div>
                                </el-col>

                                <el-col :span="8">
                                    <div class="contract_box_down_up">
                                        <el-form-item label="倍率" prop="multiple">
                                            <el-input v-model="item.multiple"
                                                      placeholder="请输入"
                                                      class="contract_box_down_input">
                                            </el-input>
                                        </el-form-item>
                                    </div>
                                </el-col>
                                <el-col :span="4">
                                    <div class="contract_box_down_up">
                                        <el-form-item>
                                        </el-form-item>
                                    </div>
                                </el-col>
                                <el-col :span="4">
                                    <div class="contract_box_down_up">
                                        <el-button type="primary" icon="el-icon-delete" size="small"
                                                   @click="delMeter(index)">
                                            删除电表
                                        </el-button>
                                    </div>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="10">
                                    <div class="contract_box_down_up">
                                        <el-form-item label="计费方式" prop="electricityBillingType">
                                            <el-radio-group v-model="item.electricityBillingType">
                                                <el-radio label="1">均价计费</el-radio>
                                                <el-radio label="2">峰谷计费</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                    </div>
                                </el-col>
                            </el-row>
                            <div v-if="item.electricityBillingType == 1">
                                <el-row>
                                    <el-col :span="8">
                                        <div class="contract_box_down_up">
                                            <el-form-item label="均价单价" prop="electricityAveragePrice">
                                                <el-input v-model="item.electricityAveragePrice"
                                                          placeholder="请输入"
                                                          class="contract_box_down_input">
                                                    <template slot="append">元/kWh</template>
                                                </el-input>
                                            </el-form-item>
                                        </div>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="8">
                                        <div class="contract_box_down_up">
                                            <el-form-item label="初始电表" prop="initElectricity">
                                                <el-input v-model="item.initElectricity"
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
                                    <el-col :span="8">
                                        <div class="contract_box_down_up">
                                            <el-form-item label="峰值单价" prop="electricityPeakPrice">
                                                <el-input v-model="item.electricityPeakPrice"
                                                          placeholder="请输入" class="contract_box_down_input">
                                                    <template slot="append">元/kWh</template>
                                                </el-input>
                                            </el-form-item>
                                        </div>
                                    </el-col>
                                    <el-col :span="8">
                                        <div class="contract_box_down_up">
                                            <el-form-item label="平值单价" prop="electricityFlatPrice">
                                                <el-input v-model="item.electricityFlatPrice"
                                                          placeholder="请输入" class="contract_box_down_input">
                                                    <template slot="append">元/kWh</template>
                                                </el-input>
                                            </el-form-item>
                                        </div>
                                    </el-col>
                                    <el-col :span="8">
                                        <div class="contract_box_down_up">
                                            <el-form-item label="谷值单价" prop="electricityTroughPrice">
                                                <el-input v-model="item.electricityTroughPrice"
                                                          placeholder="请输入" class="contract_box_down_input">
                                                    <template slot="append">元/kWh</template>
                                                </el-input>
                                            </el-form-item>
                                        </div>
                                    </el-col>
                                </el-row>

                                <el-row>
                                    <el-col :span="8">
                                        <div class="contract_box_down_up">
                                            <el-form-item label="初始峰值" prop="electricityPeak">
                                                <el-input v-model="item.electricityPeak"
                                                          placeholder="请输入" class="contract_box_down_input">
                                                    <template slot="append">kWh</template>
                                                </el-input>
                                            </el-form-item>
                                        </div>
                                    </el-col>
                                    <el-col :span="8">
                                        <div class="contract_box_down_up">
                                            <el-form-item label="初始平值" prop="electricityFlat">
                                                <el-input v-model="item.electricityFlat"
                                                          placeholder="请输入" class="contract_box_down_input">
                                                    <template slot="append">kWh</template>
                                                </el-input>
                                            </el-form-item>
                                        </div>
                                    </el-col>
                                    <el-col :span="8">
                                        <div class="contract_box_down_up">
                                            <el-form-item label="初始谷值" prop="electricityTrough">
                                                <el-input v-model="item.electricityTrough"
                                                          placeholder="请输入" class="contract_box_down_input">
                                                    <template slot="append">kWh</template>
                                                </el-input>
                                            </el-form-item>
                                        </div>
                                    </el-col>
                                </el-row>
                            </div>
                        </div>
                    </el-form>
                </div>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">水费</span>
            </div>
            <div class="common_content_div">
                <div class="meter_info_list_add">
                    <el-button type="primary" icon="el-icon-plus" round class="billingDetail_button" @click="addWaterMeter"
                               size="small">添加水表
                    </el-button>
                </div>
                <div class="meter_info_list" v-for="(waterItem,index) in cont.contract.waterMeterList">
                    <el-form :model="waterItem" ref="waterItem" :rules="rules" label-suffix=":" label-width="100px">
                        <div class="contract_box_down">
                            <el-row>
                                <el-col :span="8">
                                    <div class="contract_box_down_up">
                                        <el-form-item label="水表名称" prop="waterMeterName">
                                            <el-input v-model="waterItem.waterMeterName"
                                                       placeholder="请输入"
                                                      class="contract_box_down_input">
                                            </el-input>
                                        </el-form-item>
                                    </div>
                                </el-col>
                                <el-col :span="12">
                                    <div class="contract_box_down_up">
                                        <el-form-item>
                                        </el-form-item>
                                    </div>
                                </el-col>
                                <el-col :span="4">
                                    <div class="contract_box_down_up">
                                        <el-button type="primary"  icon="el-icon-delete"
                                                   @click="delWaterMeter(index)" size="small">
                                            删除水表
                                        </el-button>
                                    </div>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="10">
                                    <div class="contract_box_down_up">
                                        <el-form-item label="计费方式" prop="waterBillingType">
                                            <el-radio-group v-model="waterItem.waterBillingType">
                                                <el-radio label="1">均摊计费</el-radio>
                                                <el-radio label="2">抄表计费</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                    </div>
                                </el-col>
                            </el-row>
                            <div v-if="waterItem.waterBillingType == 1">
                                <el-row>
                                    <el-col :span="7">
                                        <div class="contract_box_down_up">
                                            <el-form-item label="均摊金额" prop="waterAmount">
                                                <el-input v-model="waterItem.waterAmount"
                                                           placeholder="请输入"
                                                          class="contract_box_down_input">
                                                    <template slot="append">元</template>
                                                </el-input>
                                            </el-form-item>
                                        </div>
                                    </el-col>
                                </el-row>
                            </div>
                            <div v-else>
                                <el-row>
                                    <el-col :span="7">
                                        <div class="contract_box_down_up">
                                            <el-form-item label="水费单价" prop="waterUnitPrice">
                                                <el-input  v-model="waterItem.waterUnitPrice"
                                                          placeholder="请输入" class="contract_box_down_input">
                                                    <template slot="append">元/吨</template>
                                                </el-input>
                                            </el-form-item>
                                        </div>
                                    </el-col>
                                    <el-col :span="7">
                                        <div class="contract_box_down_up">
                                            <el-form-item label="初始见表" prop="initWater">
                                                <el-input  v-model="waterItem.initWater"
                                                          placeholder="请输入" class="contract_box_down_input">
                                                    <template slot="append">吨</template>
                                                </el-input>
                                            </el-form-item>
                                        </div>
                                    </el-col>
                                </el-row>
                            </div>
                        </div>
                    </el-form>
                </div>
            </div>


            <div class="common_title_div">
                <span class="common_title_font">停车费</span>
            </div>
            <div class="common_content_div">
                <el-row class="row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="免费车位" prop="freeParkingCount">
                            <el-input v-model="cont.contract.freeParkingCount">
                                <template slot="append">个</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="超出收费" prop="parkingPrice">
                            <el-input v-model="cont.contract.parkingPrice">
                                <template slot="append">元/个</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="是否含税">
                            <el-radio-group v-model="cont.contract.paHasTaxNum">
                                <el-radio :label=true>是</el-radio>
                                <el-radio :label=false>否</el-radio>
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
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" style="line-height: 30px;margin-left: 20px;">
                        <div style="float: left">
                            <file v-if="cont.contract.ctCode == null" @selectFile="selectFile"></file>
                        </div>
                        <div style="float: left;color: red;font-size: 14px;">&nbsp;* 请上传附件</div>
                    </el-col>
                </el-row>
                <el-row style="padding-bottom: 15px;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-for="(item, index) in cont.fileList" :key="item">
                        <el-form-item label="文件">
                            {{item}}&nbsp;
                            <i class="el-icon-delete" title="删除" @click="deleteFile(index)"></i>
                        </el-form-item>
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
                            <el-input type="textarea" v-model="cont.contract.remark"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div style="height: 50px;margin-top: 20px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="submit('cont.contract')">提交
                </el-button>
                <el-button style="float: left;" @click="resetForm('cont.contract')">重置
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
        created: function () {
            let vm = this;

            vm.addMeter();
            vm.addWaterMeter();

            //人员搜索栏
            let empobj = {};
            let empOptions = {
                method: 'POST',
                data: empobj,
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
                    vm.cont.empList = empList;
                    vm.cont.employees = employees;
                }
            }).catch(function (error) {
                vm.$alert('页面：新增租户查询人员信息失败', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            });


            //房源搜索栏
            let pkobj = {};
            let options = {
                method: 'POST',
                data: pkobj,
                url: "park/list",
            };
            this.$ajax(
                options
            ).then(function (response) {
                if (response) {
                    let result = response.data.result;
                    vm.cont.pkAllList = result;
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
                    vm.cont.pkNames = pkNameList;
                    vm.pkObj = pkObj;
                }
            }).catch(function (error) {
                vm.$alert('页面：新增租户客户房源搜索查询失败', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            });

            //查询客户详情
//            let ctCode = vm.$route.query.ctCode;
//            let param = {};
//            param.entity = vm.contract;
//            if (ctCode != null) {
//                param.entity.ctCode = ctCode;
//                if (param.entity.ctCode) {
//                    let options = {
//                        method: "POST",
//                        data: param,
//                        url: "contract/detail",
//                    };
//                    this.$ajax(
//                        options
//                    ).then(function (response) {
//                        if (response) {
//                            vm.contract = response.data.result;
//                            vm.selectParkByCode(vm.cont.contract.pkCode);
//                            if (vm.cont.contract.waHasTax) {
//                                vm.cont.contract.waHasTaxNum = '1'
//                            } else {
//                                vm.cont.contract.waHasTaxNum = '2'
//                            }
//                        }
//                    }).catch(function (error) {
//                        vm.$alert('页面：查看租户详细信息失败', '系统异常', {
//                            confirmButtonText: '确定',
//                            callback: action => {
//                            }
//                        });
//                    });
//                } else {
//                    this.$message.error("传入参数有误");
//                }
//            }

        },
//        mounted(){
//            this.cont.floatDivWidth = this.$refs.myRoot.offsetWidth;
//            window.onresize = () => {
//                return (() => {
//                    this.cont.floatDivWidth = this.$refs.myRoot.offsetWidth;
//                })();
//            }
//        },
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
                loading: false,
                cont: {
                    fileList: [],// 合同上传文件集合

                    useTypeList: ["生产", "仓储", "办公"],
                    checkUseType: [],//用途集合
                    isIncDel: false,//是否点击过递增详情
//                    itRoomArr: [[]],
                    floatDivWidth: "",
                    pkAllList: [],
                    incDetailCase: [
                        {
                            value: "0",
                        }
                    ],

                    incDetailDialog: false,//控制预览窗口
                    editIncDelDialog: false,//控制修改递增详情弹框
                    //递增租金
                    editIncModel: {
                        incIndex: "",//记录下标
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
                    empList: [],//人员下拉选集合
                    employees: [],//人员取值
                    contract: {
                        cusCode: "",//客户编码
                        customerType: "",//客户类型
                        companyName: "",//公司名称
                        trade: "", //所属行业+
                        tradeName: "",//所属行业名称+
                        companyProduct: "",//公司产品+
                        relateName: "",//联系人
                        relateSex: "",//联系人性别
                        relatePhone: "",//联系电话
                        landlinePhone: "",//座机

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
                        contractorDept: "",//签单人部门
                        contractorPhone: "",//联系电话+
                        partyName: "",//甲方公司+
                        houseAddress: "",//房源地址
                        ctName: "",//合同编号+

                        /******暂时不用***********/
                        //交付日期+
//                    deliveryDate: "",
                        //计租日期
//                    startRentDate: "",


                        //租金集合
                        rentReceiptList: [],//租金应收列表
                        premiumReceiptList: [],// 保险费应收列表


                        advanceDateCount: "",//提前交租+
                        contractDate: "",//签约日期+
                        contractStartTime: "",//合同开始
                        contractEndTime: "",//合同结束

                        //租金递增
                        incDetailType: "2",
                        increasingCycle: "",
                        rentIncreaseProportion: "",
                        incDetailCase: "",

                        rentTotal: "",//租金合计+
                        propertyTotal: "",//物业费合计
                        total:"",//租金+物业
                        totalRentalArea: "",//出租总面积
                        rentIncrementList: [],//递增租金预览
                        //是否含税
                        hasTax: "",
                        hasTaxNum: "",
                        propertyUnitPrice: "",//物业费单价
                        propertyUnit: "元/㎡/月",//物业费单价单位
                        //出租楼层集合
                        contractHouseList: [
                            {
                                flCode: "",
                                roomNumber: "",
                                roomCode: "",
                                areaRate: "",
                                investArea: "",
                                unitPrice: "",
                                unit: "",//单位
                                monthlyRent: ""
                            }
                        ],


                        payRule: "",//付款方式
                        depositRule: "",//押
                        deposit: "",//实付押金
                        eleDeposit: "",//电费押金
                        waterDeposit: "",//水费押金

                        //首期付款
                        fpayHouseRent: "",//首期支付房屋租金
                        fpayPropertyPrice: "",//首期支付物业费
                        fpayInsurancePrice: "",//首期支付保险费
                        fpayProjectDeposit: "",//首期支付工程押金
                        fpayBond: "",//首期支付保证金

                        /*电费*/

                        basicElectricityPriceByMonth: "",//月基础电费
                        //电费是否含税+
                        elHasTax: "",
                        elHasTaxNum: "",
                        totalElectricity: "",//总用电量
                        basicElectricityUnitPrice: "",//基础单价
                        addLoss: "",//电损


                        /*水费*/
                        waterBillingType: "2",//水费计费方式+
                        waterAmount: "",//水费金额
                        waterPrice: "",//水费单价
                        initWater: "",//水表初始值+
                        //水表是否含税+
                        waHasTax: "",
                        waHasTaxNum: "",

                        /*停车费*/
                        freeParkingCount: "",//免费车位
                        parkingPrice: "",//超出收费
                        //停车费是否含税
                        paHasTax: "",
                        paHasTaxNum: "",

                        contractUrl: "",//合同url+

                        // 合同添加税收信息
                        outputValue: "",// 产值
                        hasRevenueBackLocal: "",// 税收是否可落户当地
                        taxTake: "",// 税收金额
                        equipmentList: "",// 设备清单
                        equipmentValue: "",// 设备价值
                        remark: "",//备注

                        meterList: [],//电表相关
                        waterMeterList: [],//水表相关
                    },
                    //控制添加租金弹窗
                    addRentReceipt: false,
                    addOrEditRent: "",//租金弹窗新增修改标识   1：新增   2：修改
                    rentReceIndex: "",//当前下标
                    rentTitle: "",//弹窗title

                    windowTitle: '',// 弹窗标题
                    commonAddOrEdit: '',// 判断是新增还是修改

                    // 添加租金
                    rentReceiptObj: {
                        rentStartTime: "",//开始日期
                        rentEndTime: "",//结束日期
                        receivingDate: "",//截止日期
                        receivableRent: "",//本期租金
                        propertyPrice: "",//本期物业费
                        hasTax: "",//是否含税
                        taxPoint: "",//税点
                        taxPrice: "",//税金
                        proHasTax: "",//是否含税
                        proTaxPoint: "",//税点
                        totalPrice: "",//合计应收
                    },

                    // 添加保险费
                    addPremiumObj: {
                        premiumStartDate: '',// 开始日期
                        premiumEndDate: '',// 结束日期
                        deadline: '',// 截止日期
                        premiumAmount: '',// 投保金额
                        receivablePremium: '',// 应收保险费
                        hasTax: false,// 是否含税
                        taxPoint: '',// 税点
                        taxPointName: '',//税点名称
                        taxPrice: '',// 税金
                        totalPrice: '',// 合计应收
                    },
                    addPremiumWindow: false,// 添加保险费弹窗

                    taxPoint: [
//                        {
//                            value: "1",
//                            label: '3%'
//                        },
//                        {
//                            value: "2",
//                            label: '5%'
//                        },
                        {
                            value: "3",
                            label: '6%'
                        },
                        {
                            value: "4",
                            label: '9%'
                        },
//                        {
//                            value: "5",
//                            label: '16%'
//                        },
                    ],
                    cusNameList: [], //存放检索到的客户信息
                    cusNames: [], //下拉框中显示的筛选的客户信息
                    pkNames: [],
                    //楼层集合
                    fls: [],
                    //选择的楼层名
                    flName: "",
                    //选择楼层剩余面积
                    rentableArea:"",
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
                },
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
                        {type: 'date', required: true, message: '请选择合同签约日期', trigger: 'change'}
                    ],
                    contractStartTime: [
                        {type: 'date', required: true, message: '请选择合同开始日期', trigger: 'change'}
                    ],
                    contractEndTime: [
                        {type: 'date', required: true, message: '请选择合同结束日期', trigger: 'change'}
                    ],
//                    startRentDate: [
//                        {type: 'date', required: true, message: '请选择计租开始日期', trigger: 'change'}
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
                    avgUnitPrice: [
                        {required: true, message: '请输入平均单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    rentTotal: [
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
                    waterMeterName: [
                        {required: true, message: '请输入水表名称', trigger: 'blur'},
                    ],
                    waterBillingType: [
                        {required: true, message: '请选择水费计费方式', trigger: 'change'},
                    ],
                    waterAmount: [
                        {required: true, message: '请输入水费金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    waterUnitPrice: [
                        {required: true, message: '请输入水费单价', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    initWater: [
                        {required: true, message: '请输入水费初始见表', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                },
                // 保险费：添加、修改、办理校验
                premiumRules: {
                    premiumStartDate: [
                        {required: true, message: '请选择开始日期', trigger: 'blur'},
                    ],
                    premiumEndDate: [
                        {required: true, message: '请选择结束日期', trigger: 'blur'},
                    ],
                    deadline: [
                        {required: true, message: '请选择截止日期', trigger: 'blur'},
                    ],
                    premiumAmount: [
                        {required: true, message: '请输入投保金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    receivablePremium: [
                        {required: true, message: '请输入应收保险费', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    thePayment: [
                        {required: true, message: '请输入缴费金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    payDate: [
                        {required: true, message: '请选择缴费时间', trigger: 'blur'},
                    ],
                    balance: [
                        {required: true, message: '请输入余额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    totalPrice: [
                        {required: true, message: '合计应收不能为空', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    hasTax: [
                        {required: true, message: '请选择是否含税', trigger: 'blur'},
                    ],
                    taxPoint: [
                        {required: true, message: '请选择税点', trigger: 'blur'},
                    ],
                    taxPrice: [
                        {required: true, message: '请输入税金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                },
                // 添加租金列表校验
                rentRules: {
                    rentStartTime: [
                        {required: true, message: '请选择开始日期', trigger: 'blur'},
                    ],
                    rentEndTime: [
                        {required: true, message: '请选择结束日期', trigger: 'blur'},
                    ],
                    receivingDate: [
                        {required: true, message: '请选择截止日期', trigger: 'blur'},
                    ],
                    receivableRent: [
                        {required: true, message: '请输入本期租金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    propertyPrice: [
                        {required: true, message: '请输入本期物业费', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    hasTax: [
                        {required: true, message: '请选择是否含税', trigger: 'blur'},
                    ],
                    taxPoint: [
                        {required: true, message: '请选择税点', trigger: 'blur'},
                    ],
                    taxPrice: [
                        {required: true, message: '请输入税金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    proHasTax: [
                        {required: true, message: '请选择是否含税', trigger: 'blur'},
                    ],
                    proTaxPoint: [
                        {required: true, message: '请选择税点', trigger: 'blur'},
                    ],
                    totalPrice: [
                        {required: true, message: '合计应收不能为空', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                },
            }
        },
        computed: {
            //租金合计
            total: {
                //计算属性
                get: function () {
                    let total =  Number(this.cont.contract.rentTotal) + Number(this.cont.contract.propertyTotal);
                    return total.toFixed(2);
                },
                set: function () {
                    return 0;
                }
            },
//            total: {
//                //计算属性
//                get: function () {
//                    let list = this.cont.contract.contractHouseList;
//                    let total = 0;
//                    let propertyUnitPrice = this.cont.contract.propertyUnitPrice;
//                    this.cont.contract.rentTotal = 0;
//                    this.cont.contract.propertyTotal = 0;
//                    this.cont.contract.totalRentalArea = 0;
//                    for (let i = 0; i < list.length; i++) {
//                        let investArea = 0;
//                        let unitPrice = 0;
//                        if (list[i].investArea && list[i].unitPrice && list[i].unit) {
//                            investArea = list[i].investArea;
//                            unitPrice = list[i].unitPrice;
//                            this.cont.contract.totalRentalArea += Number(investArea);
//                            if (list[i].unit == "元/㎡/天") {
//                                list[i].monthlyRent = (investArea * unitPrice * (365 / 12)).toFixed(2);
//                            } else if (list[i].unit == "元/㎡/月") {
//                                list[i].monthlyRent = (investArea * unitPrice).toFixed(2);
//                            } else if (list[i].unit == "元/㎡/年") {
//                                list[i].monthlyRent = (investArea * unitPrice / 12).toFixed(2);
//                            }
//                            this.cont.contract.rentTotal = Number(this.cont.contract.rentTotal) + Number(list[i].monthlyRent);
//                            this.cont.contract.propertyTotal += investArea * propertyUnitPrice;
//                            total = (this.cont.contract.rentTotal + this.cont.contract.propertyTotal).toFixed(2);
//                        }
//                    }
//                    this.cont.contract.propertyTotal = this.cont.contract.propertyTotal.toFixed(2);
//                    return total;
//                },
//                set: function () {
//                    return 0;
//                }
//            },
            //月基础电费
            basicElectricityPriceByMonth: {
                get: function () {
                    return this.cont.contract.basicElectricityUnitPrice * this.cont.contract.totalElectricity;
                },
                set: function () {
                    return 0;
                }
            },
            //首期收款总额
            fpayAllPrice: function () {
                let num = 0;
                if (this.cont.contract.eleDeposit) {
                    num += Number(this.cont.contract.eleDeposit);
                }
                if (this.cont.contract.waterDeposit) {
                    num += Number(this.cont.contract.waterDeposit);
                }
                if (this.cont.contract.fpayHouseRent) {
                    num += Number(this.cont.contract.fpayHouseRent);
                }
                if (this.cont.contract.fpayPropertyPrice) {
                    num += Number(this.cont.contract.fpayPropertyPrice);
                }
                if (this.cont.contract.fpayInsurancePrice) {
                    num += Number(this.cont.contract.fpayInsurancePrice);
                }
                if (this.cont.contract.fpayProjectDeposit) {
                    num += Number(this.cont.contract.fpayProjectDeposit);
                }
                if (this.cont.contract.fpayBond) {
                    num += Number(this.cont.contract.fpayBond);
                }
                return num.toFixed(2);
            }
        },
        methods: {
            remoteMethod(query){
                if(query.trim() != ''){
                    this.loading = true;
                    setTimeout(() =>{
                        this.selectCustom(query.trim());
                    },200);
                }else{
                    this.cont.cusNames = [];
                }

            },

            selectCustom(keyword){
                let vm = this;
                //客户搜索栏
                let cusobj = {
                    keyword:keyword
                };
                let options = {
                    method: 'POST',
                    data: cusobj,
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
                        vm.cont.cusNames = cusNameList.filter(item => {
                            return (item.name.toLowerCase()
                                    .indexOf(keyword.toLowerCase()) > -1 || item.phone.indexOf(keyword) > -1);
                        });
                      //  vm.cusNameList = cusNameList;
                        vm.cont.customers = customers;
                        vm.loading = false;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：新增租户客户搜索栏查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            //自动计算租金
            autoTotal(flag) {
                if(flag == 1) {//自动计算月租金
                    let list = this.cont.contract.contractHouseList;
                    let total = 0;
                    let propertyUnitPrice = this.cont.contract.propertyUnitPrice;
                    this.cont.contract.rentTotal = 0;
                    this.cont.contract.propertyTotal = 0;
                    this.cont.contract.totalRentalArea = 0;
                    for (let i = 0; i < list.length; i++) {
                        let investArea = 0;
                        let unitPrice = 0;
                        if (list[i].investArea && list[i].unitPrice && list[i].unit) {
                            investArea = list[i].investArea;
                            unitPrice = list[i].unitPrice;
                            this.cont.contract.totalRentalArea += Number(investArea);
                            if (list[i].unit == "元/㎡/天") {
                                list[i].monthlyRent = (investArea * unitPrice * (365 / 12)).toFixed(2);
                            } else if (list[i].unit == "元/㎡/月") {
                                list[i].monthlyRent = (investArea * unitPrice).toFixed(2);
                            } else if (list[i].unit == "元/㎡/年") {
                                list[i].monthlyRent = (investArea * unitPrice / 12).toFixed(2);
                            }
                            this.cont.contract.rentTotal = Number(this.cont.contract.rentTotal) + Number(list[i].monthlyRent);
                            this.cont.contract.propertyTotal += investArea * propertyUnitPrice;
                            total = (this.cont.contract.rentTotal + this.cont.contract.propertyTotal).toFixed(2);
                        }
                    }
                    this.cont.contract.propertyTotal = this.cont.contract.propertyTotal.toFixed(2);
//                    this.cont.contract.total = Number(this.cont.contract.rentTotal) + Number(this.cont.contract.propertyTotal);
                } else if(flag == 2) {//修改每月租金或者每月物业费
                    let list = this.cont.contract.contractHouseList;
                    this.cont.contract.rentTotal = 0;
                    for (let i = 0; i < list.length; i++) {
                        this.cont.contract.rentTotal = Number(this.cont.contract.rentTotal) + Number(list[i].monthlyRent);
                    }
//                    this.cont.contract.total = Number(this.cont.contract.rentTotal) + Number(this.cont.contract.propertyTotal);
                }
            },

            //租金详细递增预览
            incDetailPreview() {
                let vm = {};
                vm = this;


                if (vm.cont.contract.rentIncrementList.length > 0) {
                    vm.$confirm('已生成预览列表，如有改动请重新生成', '提示', {
                        confirmButtonText: '生成',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        vm.cont.contract.rentIncrementList = [];
                        vm.incDetailPreview();
                    }).catch(() => {
                        //租金递增详情预览打开
                        vm.cont.incDetailDialog = true;
                    });

                } else {
                    let avgUnitPrice = 0;
                    if (!vm.cont.contract.contractStartTime || !vm.cont.contract.contractEndTime) {
                        vm.$alert('提示：请先选择合同周期', '温馨提示', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                        return;
                    }

                    //循环房源集合，判断code不为空
                    for (let i = 0; i < vm.cont.contract.contractHouseList.length; i++) {
                        if (vm.cont.contract.contractHouseList[i].flCode == '' || vm.cont.contract.contractHouseList[i].investArea == ''
                            || vm.cont.contract.contractHouseList[i].unitPrice == '' || vm.cont.contract.contractHouseList[i].unit == '') {
                            //租用楼层为空
                            vm.$alert('提示：请填写好租用楼层的信息', '温馨提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                            return;
                        } else {
                            if (vm.cont.contract.contractHouseList[i].unit == "元/㎡/天") {
                                avgUnitPrice = Number(avgUnitPrice) + Number(vm.cont.contract.contractHouseList[i].unitPrice);
                            } else if (vm.cont.contract.contractHouseList[i].unit == "元/㎡/月") {
                                avgUnitPrice = Number(avgUnitPrice) + Number(vm.cont.contract.contractHouseList[i].unitPrice) * 12 / 365;
                            } else if (vm.cont.contract.contractHouseList[i].unit == "元/㎡/年") {
                                avgUnitPrice = Number(avgUnitPrice) + Number(vm.cont.contract.contractHouseList[i].unitPrice) / 365;
                            }
                        }
                    }
                    //记录平均单价
                    avgUnitPrice = (avgUnitPrice / vm.cont.contract.contractHouseList.length).toFixed(4);

                    //判断详细递增是不是为空
                    if (vm.cont.contract.incDetailType == 1) {
                        //先判断他是不是详细递增类型
                        for (let i = 0; i < vm.cont.incDetailCase.length; i++) {
                            if (vm.cont.incDetailCase[i].value === '') {
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
                        if (!(vm.cont.contract.increasingCycle && vm.cont.contract.rentIncreaseProportion)) {
                            vm.$alert('提示：输入租金递增的数据不完整', '温馨提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                            return;
                        }
                    }


                    vm.cont.contract.rentIncrementList = [];
                    let conStartDate = new Date(vm.cont.contract.contractStartTime);
                    let conEndDate = new Date(vm.cont.contract.contractEndTime);

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
                                rentInvestArea: vm.cont.contract.totalRentalArea,//出租含公摊面积
                                rentIncreaseProportion: ""//递增比
                            };
                            let sDate = new Date(startDate);
                            let eDate = new Date(sDate);
                            eDate.setYear(eDate.getFullYear() + 1);

                            rentInc.incStartTime = sDate;
                            rentInc.incEndTime = eDate;
                            rentInc.incStartTime = rentInc.incStartTime.getTime();
                            if (rentInc.incEndTime >= conEndDate.getTime()) {
                                rentInc.incEndTime = conEndDate.getTime() + (24 * 60 * 60 * 1000) - 1;
                            } else {
                                rentInc.incEndTime = rentInc.incEndTime.getTime() - 1;
                            }

                            startDate = eDate;
                            vm.cont.contract.rentIncrementList.push(rentInc);
                        }
                    }

                    let rentTotal = vm.cont.contract.rentTotal;
                    let icy = 0;
                    if (vm.cont.contract.incDetailType == "2") {
                        icy = Number(vm.cont.contract.increasingCycle);
                    }
                    for (let j = 0; j < vm.cont.contract.rentIncrementList.length; j++) {
                        if (vm.cont.contract.incDetailType == "1") {//详细递增
                            if (vm.cont.incDetailCase[j]) {
                                rentTotal = (rentTotal * (1 + Number(vm.cont.incDetailCase[j].value) / 100)).toFixed(2);
                                vm.cont.contract.rentIncrementList[j].rentIncreaseProportion = Number(vm.cont.incDetailCase[j].value);
                            } else {
                                rentTotal = vm.cont.contract.rentIncrementList[j - 1].rentTotal;
                                vm.cont.contract.rentIncrementList[j].rentIncreaseProportion = 0;
                                vm.cont.incDetailCase.push({"value": "0"});
                            }

                        } else {//每n年递增m%
                            if (j == icy) {
                                rentTotal = (rentTotal * (1 + Number(vm.cont.contract.rentIncreaseProportion) / 100)).toFixed(2);
                                vm.cont.contract.rentIncrementList[j].rentIncreaseProportion = vm.cont.contract.rentIncreaseProportion;
                                //下次递增的下标
                                icy += Number(vm.cont.contract.increasingCycle);
                            } else {
                                if (vm.cont.contract.rentIncrementList[j - 1]) {
                                    rentTotal = vm.cont.contract.rentIncrementList[j - 1].rentTotal;
                                }
                                vm.cont.contract.rentIncrementList[j].rentIncreaseProportion = 0;
                            }
                        }
                        avgUnitPrice = avgUnitPrice * (1 + Number(vm.cont.contract.rentIncrementList[j].rentIncreaseProportion) / 100);
                        vm.cont.contract.rentIncrementList[j].rentTotal = rentTotal;
                        vm.cont.contract.rentIncrementList[j].avgUnitPrice = avgUnitPrice.toFixed(4);
                    }

                    //租金递增详情预览打开
                    vm.cont.incDetailDialog = true;

                }
                //判断是否点击过递增详情
                vm.cont.isIncDel = true;

            },
            //点击修改
            editRentIncDialog(val) {
                let vm = {};
                vm = this;

                vm.cont.editIncModel = vm.cont.contract.rentIncrementList[val];
                vm.cont.editIncModel.incIndex = val;

                vm.cont.editIncModelCopy.rentTotal = vm.cont.editIncModel.rentTotal;
                vm.cont.editIncModelCopy.avgUnitPrice = vm.cont.editIncModel.avgUnitPrice;
                //递增列表弹窗先关闭
                vm.cont.incDetailDialog = false;
                //修改弹窗打开
                vm.cont.editIncDelDialog = true;

            },
            //修改取消
            cancelEditIncDel() {
                this.cont.editIncDelDialog = false;
                this.cont.incDetailDialog = true;
                this.cont.contract.rentIncrementList[this.cont.editIncModel.incIndex].rentTotal = this.cont.editIncModelCopy.rentTotal;
                this.cont.contract.rentIncrementList[this.cont.editIncModel.incIndex].avgUnitPrice = this.cont.editIncModelCopy.avgUnitPrice;
            },
            //修改提交
            submitEditIncDel(subForm) {
                if (this.cont.editIncModel.rentTotal && this.cont.editIncModel.avgUnitPrice) {
                    this.cont.contract.rentIncrementList[this.cont.editIncModel.incIndex] = this.cont.editIncModel;
                    this.cont.editIncDelDialog = false;
                    this.cont.incDetailDialog = true;
                }
            },

            checkFreeRent() {
                if (this.cont.contract.contractStartTime && this.cont.contract.contractEndTime) {
                    if (this.cont.contract.freeRentTimes[0] && this.cont.contract.freeRentTimes[1]) {
                        if (this.cont.contract.freeRentTimes[0].getTime() != this.cont.contract.contractStartTime.getTime()
                            && this.cont.contract.freeRentTimes[1].getTime() != this.cont.contract.contractEndTime.getTime()) {
                            this.$alert('页面：免租期的开始日期与合同开始日期、免租期的结束日期与合同的结束日期，至少要有一个相等', '温馨提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    this.cont.contract.freeRentTimes = [];
                                }
                            });
                        }
                    }
                } else {
                    this.$alert('页面：请先选择合同周期', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.cont.contract.freeRentTimes = [];
                        }
                    });
                }

            },

            //重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.cont.contract.houseAddress = '';
            },
            //校验房屋出租非必填字段0～99999999.99
            checkHouseNum(value, index, val){
                if(this.cont.contract.contractHouseList[index].investArea > this.cont.rentableArea) {
                    this.$alert('提示：要出租面积大于本层剩余面积', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.cont.contract.contractHouseList[index].investArea = "";
                        }
                    });
                    return;
                }
                if (val < 0 || val > 99999999.99) {
                    this.$message.error('输入数字超出0～99999999.99范围');
                    if (value == 2) {
                        this.cont.contract.contractHouseList[index].unitPrice = 0;
                    }
                }

                if (value == 2) {
                    if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(val))) {
                        this.cont.contract.contractHouseList[index].unitPrice = 0;
                        this.$message.error('请输入正整数或小数');
                    }
                }
                this.autoTotal(1);
            },
            //校验非必填字段0～99999999.99
            checkNum(value){
                if (value == 'rentIncreaseProportion') {
                    let rent = this.cont.contract.rentIncreaseProportion;
                    if (rent < 0 || rent > 99999999.99) {
                        this.$message.error('租金递增输入数字超出0～99999999.99范围');
                        this.cont.contract.rentIncreaseProportion = 0;
                    }

                    if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(rent))) {
                        this.$message.error('递增百分比只能为正整数或小数');
                        this.cont.contract.rentIncreaseProportion = 0;
                    }
                } else if (value == 'depositRule') {
                    let dep = this.cont.contract.depositRule;
                    if (dep < 0 || dep > 99999999.99) {
                        this.$message.error('付款方式输入数字超出0～99999999.99范围');
                        this.cont.contract.depositRule = 0;
                    }

                    if (!(/^[0-9]\d*$/.test(dep))) {
                        this.$message.error('押金月数只能为正整数');
                        this.cont.contract.depositRule = 0;
                    }
                } else if (value == 'fpayAllPrice') {
                    let dep = this.cont.contract.fpayAllPrice;
                    if (dep < 0 || dep > 99999999.99) {
                        this.$message.error('首付总额输入数字超出0～99999999.99范围');
                        this.cont.contract.fpayAllPrice = 0;
                    }
                }
            },
            //选择签单人
            changeEmp(code){
                let employee = this.cont.employees[code];
                this.cont.contract.contractor = employee.empName;
                this.cont.contract.contractorDeptCode = employee.deptCode;
                this.cont.contract.contractorDept = employee.deptName;
                this.cont.contract.contractorPhone = employee.phone;
                this.cont.contract.partyName = employee.cpyName;
            },

            //记忆
            remember() {
                window.localStorage.setItem("_contObj", JSON.stringify(this.cont));
                this.$message({
                    showClose: true,
                    message: '记忆本页填写信息成功',
                    type: 'success',
                });
            },
            //恢复
            restore() {

                //判断是否有记忆的信息
                let contCache = window.localStorage.getItem("_contObj");
                let contCacheObj = null;
                if (contCache) {
                    contCacheObj = JSON.parse(contCache);
                    contCacheObj.contract.contractDate = new Date(contCacheObj.contract.contractDate);
                    contCacheObj.contract.contractStartTime = new Date(contCacheObj.contract.contractStartTime);
                    contCacheObj.contract.contractEndTime = new Date(contCacheObj.contract.contractEndTime);
                    contCacheObj.contract.freeRentTimes[0] = new Date(contCacheObj.contract.freeRentStartTime);
                    contCacheObj.contract.freeRentTimes[1] = new Date(contCacheObj.contract.freeRentEndTime);
                    contCacheObj.isIncDel = false;
                    contCacheObj.employees = this.cont.employees;
                    this.cont = contCacheObj;

                }
            },
            //提交
            submit(subForm) {
                let vm = {};
                vm = this;
                let obj = {};

                //判断是否提交
                let flag = 0;

                //提交记忆本页录入信息到浏览器本地
                window.localStorage.setItem("_contObj", JSON.stringify(vm.cont));

                if (!vm.cont.isIncDel) {
                    vm.$alert('提示：请先点击递增详情查看无误再提交', '温馨提示', {
                        confirmButtonText: '递增详情',
                        callback: action => {
                            vm.incDetailPreview();
                        }
                    });
                    return;
                }

                if (vm.cont.contract.incDetailType == "1") {
                    vm.cont.contract.increasingCycle = 1;
                }

                // 多文件上传支持
                if (vm.cont.fileList.length > 0) {
                    vm.cont.contract.contractUrl = vm.cont.fileList.join('，');
                } else {
                    vm.cont.contract.contractUrl = '';
                    vm.$alert('提示：请上传文件附件', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                //租金详情递增处理
                let incList = vm.cont.incDetailCase;
                let string = "";
                for (let i = 0; i < incList.length; i++) {
                    let value = incList[i].value;
                    string += value + ",";
                }
                vm.cont.contract.incDetailCase = string;

//                //计算属性
//                let list = this.cont.contract.contractHouseList;
//                let rentTotal = 0;
//                let propertyTotal = 0;
//                let propertyUnitPrice = this.cont.contract.propertyUnitPrice;
//                for (let i = 0; i < list.length; i++) {
//                    let investArea = list[i].investArea;
//                    let unitPrice = list[i].unitPrice;
//                    rentTotal += investArea * unitPrice * 30;
//                    propertyTotal += investArea * propertyUnitPrice;
//                }
//                vm.cont.contract.rentTotal = rentTotal;
//                vm.cont.contract.propertyTotal = propertyTotal;
                vm.cont.contract.basicElectricityPriceByMonth = vm.basicElectricityPriceByMonth;
                obj.entity = vm.cont.contract;

//                let ctCode = vm.$route.query.ctCode;
//                obj.entity.ctCode = ctCode;

                let pkname = window.document.getElementById("pkName");
                obj.entity.pkName = pkname.value;

                //循环房源集合，判断code不为空
                for (let i = 0; i < vm.cont.contract.contractHouseList.length; i++) {
                    if (vm.cont.contract.contractHouseList[i].flCode == '' || vm.cont.contract.contractHouseList[i].investArea == ''
                        || vm.cont.contract.contractHouseList[i].unitPrice == '' || vm.cont.contract.contractHouseList[i].unit == '') {
                        //如果房源集合的flCode为空，将flag的值修改为1
                        flag = 1;
                    }
                }

                //判断详细递增是不是为空
                if (vm.cont.contract.incDetailType == 1) {
                    //先判断他是不是详细递增类型
                    for (let i = 0; i < vm.cont.incDetailCase.length; i++) {
                        if (vm.cont.incDetailCase[i].value == '') {
                            //如果incDetailCase为空，就代表没有输入，将flag的值修改为2
                            flag = 2;
                        }
                    }
                }

                //将免租日期集合拆开分别记录在免租开始和免租结束字段中
                if (vm.cont.contract.freeRentTimes[0] && vm.cont.contract.freeRentTimes[1]) {
                    vm.cont.contract.freeRentStartTime = vm.cont.contract.freeRentTimes[0];
                    vm.cont.contract.freeRentEndTime = vm.cont.contract.freeRentTimes[1];
                } else {
                    flag = 3;
                }

                //租房用途转为String存入数据库
                if (vm.cont.checkUseType.length > 0) {
                    for (let i = 0; i < vm.cont.checkUseType.length; i++) {
                        if (i == 0) {
                            vm.cont.contract.useType = vm.cont.checkUseType[i];
                        } else {
                            vm.cont.contract.useType = vm.cont.contract.useType + "," + vm.cont.checkUseType[i];
                        }
                    }
                } else {
                    vm.$alert('提示：请选择租房用途', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                //通过flag的值来判断数据的完整性
                if (flag == 1) {
                    //租用楼层为空
                    vm.$alert('提示：输入租用楼层的信息不完整', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                } else if (flag == 2) {
                    //详细递增为空
                    vm.$alert('提示：输入租金递增的数据不完整', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                } else if (flag == 3) {
                    //免租开始日期和结束日期不完整
                    vm.$alert('提示：免租日期选择数据不完整', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                } else {
                    //正常提交
                    this.$refs[subForm].validate((valid) => {
                        if (valid) {
                            let options = {
                                method: 'POST',
                                data: obj,
                                url: "contract/create",
                            };
                            this.$ajax(
                                options
                            ).then(function (response) {
                                if (response) {
                                    vm.$message({
                                        showClose: true,
                                        message: '新增租户成功',
                                        type: 'success',
                                    });
                                    //window.localStorage.removeItem("_contObj");
//                                    vm.$router.push({
//                                        path: '/contr/contractList'
//                                    });

                                    // 合同新增成功后返回合同详情
//                                    vm.$router.push({
//                                        name: 'contractDetails',
//                                        query: {
//                                            ctCode: response.data.result,
//                                        }
//                                    })
                                    //新增完跳转到详情页
                                    let dataObj = {
                                        flag: "add",
                                        menuName:"合同详情",
                                        item: "contractDetails",
                                        params: {
                                            ctCode: response.data.result
                                        }
                                    };
                                    vm.$emit("backParam",dataObj);

                                    //删除入驻办理页签
                                    let dataDelObj = {
                                        flag: "del",
                                        menuName:"入驻办理",
                                        item: "contractCreate"
                                    };
                                    vm.$emit("backParam",dataDelObj);

                                }
                            }).catch(function (error) {
                                vm.$alert('页面：新增租户失败', '系统异常', {
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
                    //提交end
                }
                //通过flag的值来判断数据的完整性 end
            },

            /*通过cusCode搜索*/
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
                        vm.cont.contract.cusCode = customer.cusCode;
                        vm.cont.contract.customerType = customer.customerType;
                        vm.cont.contract.companyName = customer.companyName;
                        vm.cont.contract.tradeName = customer.tradeName;
                        vm.cont.contract.companyProduct = customer.companyProduct;
                        vm.cont.contract.relateName = customer.mainContactName;
                        vm.cont.contract.relateSex = customer.mainContactSex;
                        vm.cont.contract.relatePhone = customer.mainContactPhone;
                        let useList = customer.useType.split(",");
                        vm.cont.checkUseType = useList;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：新增租户查询客户信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });

            },
            /*通过pkCode搜索*/
            selectParkByCode(pkCode){
                //先清空之前选择的楼层信息
                this.clearFloor();

                this.cont.rentableArea = "";

                this.cont.contract.pkCode = pkCode;

                for (let i = 0; i < this.cont.pkAllList.length; i++) {
                    if (this.cont.pkAllList[i].pkCode == pkCode) {
                        this.cont.contract.houseAddress = this.cont.pkAllList[i].provinceName + this.cont.pkAllList[i].cityName + this.cont.pkAllList[i].regionName + this.cont.pkAllList[i].streetName + this.cont.pkAllList[i].address;
                    }
                }

                //园区选择后,查询出出租楼层
                if (pkCode == "") {
                    return
                }
                let vm = this;
                let obj = {
                    entity: {pkCode: pkCode}
                };
                let options = {
                    method: 'POST',
                    data: obj,
                    url: "floor/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        let floors = response.data.result;
                        let fls = [];
                        for (let i = 0; i < floors.length; i++) {
                            let code = floors[i].flCode;
                            let name = floors[i].bdName + "#" + floors[i].thisFloor + "层";
                            let rentableArea = floors[i].rentableArea;
                            fls.push(
                                {
                                    code: code,
                                    name: name,
                                    rentableArea:rentableArea
                                }
                            )
                        }
                        vm.cont.fls = fls;
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
                this.cont.contract.contractHouseList[index].investArea = "";
                for(let i=0;i<this.cont.fls.length;i++) {
                    if(code == this.cont.fls[i].code) {
                        this.cont.rentableArea = this.cont.fls[i].rentableArea;
                        break;
                    }
                }
                this.autoTotal(1);
            },
            //添加楼层按钮
            addFloor(){
                this.cont.contract.contractHouseList.push({
                    flCode: "",
                    roomNumber: "",
                    roomCode: "",
                    investArea: "",
                    unitPrice: "",
                    unit: "",//单位
                });
//                this.cont.itRoomArr.push([]);//存入数组
            },
            //清空楼层
            clearFloor() {
                this.cont.contract.contractHouseList = [{
                    flCode: "",
                    roomNumber: "",
                    roomCode: "",
                    investArea: "",
                    unitPrice: "",
                    unit: "",//单位
                }]
            },
            //添加递增详情按钮
            addIncDetail(){
                this.cont.incDetailCase.push({value: ""});
            },
            clearIncDetail() {
                this.cont.incDetailCase = [{
                    value: "0"
                }]
            },
            goBack(){
                window.history.back();
            },

            selectFile(urls){
                if (urls) {
                    for (let i = 0; i < urls.length; i++) {
                        this.cont.fileList.push(urls[i].fileName+'---'+urls[i].fileCode);
                    }
                } else {
                    this.cont.fileList = [];
                }
            },
            // 删除文件
            deleteFile(index){
                this.$confirm('是否删除该文件？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.cont.fileList.splice(index, 1);

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

            //校验合同时间
            checkConTime(val) {
                let vm = this;
                if (val === 0) {//合同开始日
                    if (vm.cont.contract.contractEndTime && (vm.cont.contract.contractEndTime <= vm.cont.contract.contractStartTime)) {
                        vm.$message({
                            showClose: true,
                            message: "选择合同开始日期要大于合同结束日期",
                            type: "error"
                        });
                        vm.cont.contract.contractStartTime = "";
                    }
//                    if (vm.cont.contract.startRentDate && (vm.cont.contract.startRentDate < vm.cont.contract.contractStartTime)) {
//                        vm.$message({
//                            showClose: true,
//                            message: "选择合同开始日期要大于计租日期",
//                            type: "error"
//                        });
//                        vm.cont.contract.contractStartTime = '';
//                    }
                } else if (val === 1) {//合同结束日
                    if (!vm.cont.contract.contractStartTime) {
                        vm.$message({
                            showClose: true,
                            message: "请先选择合同开始日期",
                            type: "error"
                        });
                        vm.cont.contract.contractEndTime = "";
                    } else {
                        if (vm.cont.contract.contractEndTime <= vm.cont.contract.contractStartTime) {
                            vm.$message({
                                showClose: true,
                                message: "选择合同结束日期不能小于合同开始日期",
                                type: "error"
                            });
                            vm.cont.contract.contractEndTime = "";
                        }
                    }
                }
//               else if (val === 2) {//计租日期
//                    if (vm.cont.contract.contractStartTime && (vm.cont.contract.startRentDate < vm.cont.contract.contractStartTime)) {
//                        vm.$message({
//                            showClose: true,
//                            message: "选择计租日期不能小于合同开始日期",
//                            type: "error"
//                        });
//                        vm.cont.contract.startRentDate = "";
//                    }
//                }

            },

            //添加租金按钮
            addRentWindow() {
                this.cont.rentTitle = "添加租金费用";
                this.cont.addRentReceipt = true;
                this.cont.addOrEditRent = 1;
                this.cont.rentReceiptObj = {
                    rentStartTime: "",//开始日期
                    rentEndTime: "",//结束日期
                    receivingDate: "",//截止日期
                    receivableRent: "",//本期租金
                    propertyPrice: "",//本期物业费
                    hasTax: "",//租金是否含税
                    taxPointName: "",//租金税点
                    taxPrice: "",//税金
                    proHasTax: "",//物业费是否含税
                    proTaxPointName: "",//物业费税点
                    totalPrice: "",//合计应收
                };
            },

            //租金提交
            addRentSubmitForm(subForm) {
                let vm = {};
                vm = this;

                vm.$refs[subForm].validate((valid) => {
                    if (valid) {
                        vm.cont.rentReceiptObj.rentStartTime = vm.cont.rentReceiptObj.rentStartTime.getTime();
                        vm.cont.rentReceiptObj.rentEndTime = vm.cont.rentReceiptObj.rentEndTime.getTime();
                        vm.cont.rentReceiptObj.receivingDate = vm.cont.rentReceiptObj.receivingDate.getTime();
                        if(!vm.cont.rentReceiptObj.hasTax) {//当不含税时将税点清空
                            vm.cont.rentReceiptObj.taxPoint = "";
                        }
                        if(!vm.cont.rentReceiptObj.proHasTax) {//当不含税时将税点清空
                            vm.cont.rentReceiptObj.proTaxPoint = "";
                        }
                        vm.cont.contract.rentReceiptList.push(vm.cont.rentReceiptObj);

                        vm.cont.addRentReceipt = false;
                    }
                });
            },

            //修改租金弹窗
            editRentWindow(index) {
                let vm = {};
                vm = this;

                vm.cont.rentTitle = "修改租金费用";
                vm.cont.addOrEditRent = 2;
                vm.cont.rentReceIndex = index;
                vm.cont.rentReceiptObj = JSON.parse(JSON.stringify(vm.cont.contract.rentReceiptList[index]));

                vm.cont.addRentReceipt = true;
            },

            //租金修改提交
            editRentSubmitForm(subForm) {
                let vm = {};
                vm = this;

                vm.$refs[subForm].validate((valid) => {
                    if (valid) {
                        //传入的日期格式可能为时间搓格式
                        // 所以将日期都转为date类型再.getTime()防止报错
                        const startDate = new Date(vm.cont.rentReceiptObj.rentStartTime);
                        const endDate = new Date(vm.cont.rentReceiptObj.rentEndTime);
                        const receDate = new Date(vm.cont.rentReceiptObj.receivingDate);

                        vm.cont.rentReceiptObj.rentStartTime = startDate.getTime();
                        vm.cont.rentReceiptObj.rentEndTime = endDate.getTime();
                        vm.cont.rentReceiptObj.receivingDate = receDate.getTime();

                        if(!vm.cont.rentReceiptObj.hasTax) {//当不含税时将税点清空
                            vm.cont.rentReceiptObj.taxPoint = "";
                        }
                        if(!vm.cont.rentReceiptObj.proHasTax) {//当不含税时将税点清空
                            vm.cont.rentReceiptObj.proTaxPoint = "";
                        }

                        vm.cont.contract.rentReceiptList[vm.cont.rentReceIndex] = vm.cont.rentReceiptObj;
                        vm.cont.contract.rentReceiptList.push(vm.cont.rentReceiptObj);
                        vm.cont.contract.rentReceiptList.pop();

                        vm.cont.addRentReceipt = false;
                    }
                });
            },

            //删除租金
            delRentWindow(index) {
                this.$confirm('确定移除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.cont.contract.rentReceiptList.splice(index, 1);
                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {
                });
            },

            /*************************电表管理****************************************/

            //添加电表
            addMeter(){
                this.cont.contract.meterList.push({
                    eleMeterCode: "",
                    eleMeterName: "",//电表名称
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
                });
            },

            //删除电表
            delMeter(index) {
                let vm = this;
                vm.$confirm('此操作将删除该电表, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //执行删除操作
                    let meterName = vm.cont.contract.meterList[index].eleMeterName;
                    vm.cont.contract.meterList.splice(index, 1);
                    vm.$message({
                        showClose: true,
                        message: '删除' + meterName + '电表信息成功',
                        type: 'success'
                    });
                }).catch(() => {
                    vm.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

            //添加水表
            addWaterMeter() {
                this.cont.contract.waterMeterList.push({
                    waterMeterCode:"",//水表编号
                    ctCode:"",//合同编号
                    waterMeterName:"",//水表名称
                    waterBillingType:"2",//计费方式
                    waterUnitPrice:"",//水费单价（抄表计费）
                    initWater:"",//初始电表（抄表计费）
                    waterAmount:"",//水费金额（均摊计费）
                });
            },

            //删除水表
            delWaterMeter(index) {
                let vm = this;
                vm.$confirm('此操作将删除该水表, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //执行删除操作
                    let meterName = vm.cont.contract.waterMeterList[index].waterMeterName;
                    vm.cont.contract.waterMeterList.splice(index, 1);
                    vm.$message({
                        showClose: true,
                        message: '删除' + meterName + '电表信息成功',
                        type: 'success'
                    });
                }).catch(() => {
                    vm.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

/*****************************************************************************************
 * 添加保险费
 ****************************************************************************************/
            // 打开添加保险费弹窗
            addPremiumMethod(){
                this.cont.windowTitle = '添加保险费';
                this.cont.addPremiumWindow = true;
                this.cont.commonAddOrEdit = 1;

                this.cont.addPremiumObj = {
                    premiumStartDate: '',// 开始日期
                    premiumEndDate: '',// 结束日期
                    deadline: '',// 截止日期
                    premiumAmount: '',// 投保金额
                    receivablePremium: '',// 应收保险费
                    hasTax: false,// 是否含税
                    taxPoint: '',// 税点
                    taxPointName: '',//税点名称
                    taxPrice: '',// 税金
                    totalPrice: '',// 合计应收
                };
            },
            // 保存
            addPremiumSubmit(subForm){
                let vm = this;

                vm.$refs[subForm].validate((valid) => {
                    if (valid) {
                        vm.cont.addPremiumObj.premiumStartDate = vm.cont.addPremiumObj.premiumStartDate.getTime();
                        vm.cont.addPremiumObj.premiumEndDate = vm.cont.addPremiumObj.premiumEndDate.getTime();
                        vm.cont.addPremiumObj.deadline = vm.cont.addPremiumObj.deadline.getTime();
                        if(!vm.cont.addPremiumObj.hasTax) {
                            vm.cont.addPremiumObj.taxPoint = "";
                        }
                        vm.cont.contract.premiumReceiptList.push(vm.cont.addPremiumObj);

                        vm.cont.addPremiumWindow = false;
                    }
                });
            },
            // 打开修改保险费弹窗
            openUpdatePremium(index){
                let vm = this;

                vm.cont.windowTitle = '修改保险费';
                vm.cont.commonAddOrEdit = 2;

                vm.cont.addPremiumObj = vm.cont.contract.premiumReceiptList[index];
                vm.cont.addPremiumWindow = true;
            },
            // 修改租金提交
            editPremiumSubmit(subForm){
                let vm = this;
                vm.$refs[subForm].validate((valid) => {
                    if (valid) {
                        const startDate = new Date(vm.cont.addPremiumObj.premiumStartDate);
                        const endDate = new Date(vm.cont.addPremiumObj.premiumEndDate);
                        const deadline = new Date(vm.cont.addPremiumObj.deadline);

                        vm.cont.addPremiumObj.premiumStartDate = startDate.getTime();
                        vm.cont.addPremiumObj.premiumEndDate = endDate.getTime();
                        vm.cont.addPremiumObj.deadline = deadline.getTime();
                        if(!vm.cont.addPremiumObj.hasTax) {
                            vm.cont.addPremiumObj.taxPoint = "";
                        }

                        vm.cont.contract.premiumReceiptList.push(vm.cont.addPremiumObj);
                        vm.cont.contract.premiumReceiptList.pop();

                        vm.cont.addPremiumWindow = false;
                    }
                });
            },
            // 删除
            deletePremium(index){
                this.$confirm('确定移除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.cont.contract.premiumReceiptList.splice(index, 1);

                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {});
            },
            // 取消添加保险费
            cancleAddPremium(formName){
                this.$refs[formName].resetFields();
                this.cont.addPremiumWindow = false;
            },
        },
    }
</script>

<style scoped>
    .conHouseBox {
        padding-top: 20px;
        border: 1px solid #dcdfe6;
        margin-bottom: 4px;
    }

    /* 租金费用表格 */
    .common-title-button {
        float: right;
        margin-top: 10px;
        margin-right: 20px;
    }

    .costTable {
        line-height: 40px;
        text-align: center;
        width: 100%;
    }


    /***********电表相关********************/
    .meter_info_list {
        border: 1px solid #d9d9d9;
        margin-bottom: 10px;
    }

    .meter_info_list_add {
        line-height: 40px;
        text-align: left;
        border-top: 1px solid rgb(242, 242, 242);
        padding-left: 20px;
    }

    .contract_box_down {
        clear: both;
        text-align: left;
        padding-bottom: 15px;
        padding-top: 10px;
    }

    .contract_box_down_up {
        height: 60px;
        line-height: 60px;
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

    /*----------------------------------- 导航栏 -----------------------------------*/
    /*导航栏提交按钮*/
    .navigation_button_submit {
        float: left;
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
        padding-top: 15px;
    }

</style>