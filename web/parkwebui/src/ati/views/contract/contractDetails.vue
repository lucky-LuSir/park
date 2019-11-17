<template>
    <div class="has-position_root" ref="myRoot">
        <!-- 导航栏 -->
        <div class="has-position_header">
            <!--<el-button class="change-contract" v-if="showAddParkingSpaceBtn && contract.ctStatus == 4" size="small" type="primary" @click="parkingAdd(contract.ctCode)" >新增停车位</el-button>-->
            <el-button class="change-contract" v-if="showManualMeterBtn && contract.ctStatus == 4" size="small" type="primary" @click="manualMeter(contract.ctCode)" >水电抄表</el-button>
            <el-button class="change-contract" v-if="showDetailFeeBtn" size="small" type="primary" @click="costDetail(contract.ctCode, contract.ctStatus)" >应收详情</el-button>
            <el-button class="change-contract" v-if="showEditContractBtn && (contract.ctStatus == 1 || contract.ctStatus == 5 || contract.ctStatus == 6)" size="small" type="primary" @click="editCon(contract.id, contract.ctCode, contract.ctStatus)" >合同变更</el-button>

            <!-- 提交一审，招商人员可见 -->
            <el-button class="change-contract" v-if="(contract.ctStatus == 1 || contract.ctStatus == 5 || contract.ctStatus == 6)" size="small" type="primary" @click="firstVerify(contract.id, contract.ctCode)" >提交一审</el-button>

            <!-- 一审通过，审核人可见 -->
            <el-button class="change-contract" v-if="showFirstVerify && (contract.ctStatus == 2)" size="small" type="primary" @click="firstVerifyPass(contract.id, contract.ctCode)" >一审通过</el-button>
            <!-- 一审操作人可见 -->
            <el-button class="change-contract" v-if="showSecondVerify && (contract.ctStatus == 3)" size="small" type="primary" @click="secondVerifyPass(contract.id, contract.ctCode)" >二审通过</el-button>

            <!-- 驳回 -->
            <el-button class="change-contract" v-if="(showFirstVerify || showSecondVerify) && (contract.ctStatus == 2 || contract.ctStatus == 3)" size="small" type="primary" @click="openRejectWindow(contract.id, contract.ctCode)">驳回</el-button>

            <el-button class="change-contract" v-if="(contract.ctStatus == 2)" size="small" type="primary" @click="cancelVerify(contract.id, contract.ctCode)" >撤回审核</el-button>

            <!--<div class="basic_info_butt">-->
            <!--<el-tooltip effect="dark" content="只修改基础信息，不修改房源、租金应收和递增" placement="top">-->
            <el-button class="change-contract" v-if="showBasicInfoUpdate && (contract.ctStatus == 4)" size="small" type="primary" @click="basicInfoUpdate(contract.id, contract.ctCode, contract.ctStatus)" >基础变更</el-button>
            <!--</el-tooltip>-->
            <!--</div>-->

            <span class="navigation-title">合同状态：【{{contract.ctStatusName}}】</span>
        </div>
        <el-form class="has-position_main" ref="contract" :model="contract" label-suffix="：" :height="tableHeight">

            <div class="titles-div">
                <span class="common-title-font">基本信息</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="客户类型">
                                <span v-if="contract.customerType==1">公司</span>
                                <span v-if="contract.customerType==2">个人</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="公司名称">
                                <span>{{contract.companyName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="固定电话">
                                <span v-if="!contract.landlinePhone">无数据</span>
                                <span v-if="contract.landlinePhone">{{contract.landlinePhone}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="联系人">
                                <span>{{contract.relateName}}&nbsp;</span>
                                <span v-if="contract.relateSex==1">先生</span>
                                <span v-if="contract.relateSex==2">女士</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="联系电话">
                                <span>{{contract.relatePhone}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="所属行业">
                                <span>{{contract.tradeName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="公司产品">
                                <span>{{contract.companyProduct}}</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="用途">
                                <span>{{contract.useType}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </div>



            <div class="titles-div">
                <span class="common-title-font">甲方信息</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="甲方公司">
                                <span>{{contract.partyName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="签约日期">
                                <span>{{contract.contractDate | timeDate}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="签约代表">
                                <span>{{contract.contractor}}</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="联系电话">
                                <span>{{contract.contractorPhone}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </div>



            <div class="titles-div">
                <span class="common-title-font">合同信息</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="合同编号">
                                <span>{{contract.ctName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="合同开始">
                                <span>{{contract.contractStartTime | timeDate}}</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="合同结束">
                                <span>{{contract.contractEndTime | timeDate}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="免租期开始">
                                <span>{{contract.freeRentStartTime | timeDate}}</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="免租期结束">
                                <span>{{contract.freeRentEndTime | timeDate}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>



            <div class="titles-div">
                <span class="common-title-font">租金规则</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                        <div class="el-row-style">
                            <el-form-item label="提前交租">
                                <span>{{contract.advanceDateCount}}天</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="15" :sm="15" :md="15" :lg="15" :xl="15" v-if="contract.incDetailType == 1">
                        <div class="el-row-style">
                            <el-form-item>
                                <div style="float: left;margin-right: 30px" v-for="(item,index) in incDetailCaseList">
                                    <span v-if="item != ''">第&nbsp;{{index + 1}}&nbsp;年递增&nbsp;{{item}}&nbsp;%</span>
                                </div>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col style="padding-bottom: 10px;" :xs="8" :sm="8" :md="8" :lg="8" :xl="8" v-if="contract.incDetailType == 2">
                        <div class="el-row-style">
                            <el-form-item>
                                <span>每&nbsp;{{contract.increasingCycle}}&nbsp;年递增&nbsp;{{contract.rentIncreaseProportion}}&nbsp;%</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>



            <div class="titles-div">
                <span class="common-title-font">房屋出租情况</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="每月合计">
                                <div style="float: left">
                                    <span>{{total}}&nbsp;元/月</span>
                                </div>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="每月总租金">
                                <div style="float: left">
                                    <span>{{contract.rentTotal}}&nbsp;元/月</span>
                                </div>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="出租总面积">
                                <div style="float: left">
                                    <span>{{contract.totalRentalArea}}&nbsp;㎡</span>
                                </div>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="每月物业费">
                                <span>{{proTotal}}元/月</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="物业费单价">
                                <span>{{contract.propertyUnitPrice}}&nbsp;{{contract.propertyUnit}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="入住园区">
                                <span>{{contract.pkName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
                        <div class="el-row-style">
                            <el-form-item label="园区地址">
                                <span>{{contract.houseAddress}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-table
                        class="costTable"
                        :data="contract.contractHouseList"
                        border
                        :highlight-current-row="true"
                        size="small"
                        max-height="400">
                    <el-table-column
                            header-align="center"
                            type="index"
                            width="60">
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            label="出租楼层">
                        <template slot-scope="scope">{{scope.row.bdName}}&nbsp;栋&nbsp;-&nbsp;{{scope.row.flCount}}&nbsp;层</template>
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            label="出租房间号">
                        <template slot-scope="scope">{{scope.row.roomNumber != null ? scope.row.roomNumber : '---'}}</template>
                    </el-table-column>
                    <el-table-column
                            prop="investArea"
                            header-align="center"
                            min-width="100"
                            label="出租计租面积(㎡)">
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            label="公摊比(%)">
                        <template slot-scope="scope">{{scope.row.areaRate != null ? scope.row.areaRate : '---'}}</template>
                    </el-table-column>
                    <el-table-column
                            prop="unitPrice"
                            header-align="center"
                            min-width="100"
                            label="房租单价">
                    </el-table-column>
                    <el-table-column
                            prop="unit"
                            header-align="center"
                            min-width="70"
                            label="单位">
                    </el-table-column>
                    <el-table-column
                            prop="monthlyRent"
                            header-align="center"
                            min-width="100"
                            label="月租金">
                    </el-table-column>
                </el-table>
            </div>

            <div class="titles-div">
                <span class="common-title-font">房租支付方式</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="付款方式">
                                <div style="float: left">
                                    <span>付&nbsp;{{contract.payRule}}&nbsp;月，押&nbsp;{{contract.depositRule}}&nbsp;月</span>
                                </div>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="实付押金">
                                <span>{{contract.deposit}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>



            <div class="titles-div">
                <span class="common-title-font">首付款信息</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="首付总额">
                                <span>{{fpayAllPrice}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="电费押金">
                                <span>{{contract.eleDeposit}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="水费押金">
                                <span>{{contract.waterDeposit}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="工程押金">
                                <span v-if="contract.fpayProjectDeposit">{{contract.fpayProjectDeposit}}&nbsp;元</span>
                                <span v-if="!contract.fpayProjectDeposit">0&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="房屋租金">
                                <span>{{contract.fpayHouseRent}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="物业费">
                                <span>{{contract.fpayPropertyPrice}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="保险费">
                                <span>{{contract.fpayInsurancePrice}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="el-row-style">
                            <el-form-item label="保证金">
                                <span>{{contract.fpayBond}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </div>

            <div class="titles-div">
                <span class="common-title-font">税收信息</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="30">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="产值">
                                <span>{{contract.outputValue}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="税收是否可落户到当地">
                                <span>{{contract.hasRevenueBackLocal == true ? "是" : "否"}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="30">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="税收金额">
                                <span>{{contract.taxTake}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="设备清单">
                                <span>{{contract.equipmentList}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="设备价值">
                                <span>{{contract.equipmentValue}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>


            <div class="titles-div">
                <span class="common-title-font">电费</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="月基础电费">
                                <span>{{basicElectricityPriceByMonth}}&nbsp;元/月</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="是否含税">
                            <span v-if="contract.elHasTaxNum == 1">是</span>
                            <span v-if="contract.elHasTaxNum == 2">否</span>
                        </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="总用电量">
                                <span>{{contract.totalElectricity}}&nbsp;kVA</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="基础单价">
                                <span>{{contract.basicElectricityUnitPrice}}&nbsp;元/kVA/月</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="电损">
                                <span>{{contract.addLoss}}&nbsp;%</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>



            <!--<div class="titles-div">
                <span class="common-title-font">水费</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="计费方式">
                                <span v-if="contract.waterBillingType==1">均摊计费</span>
                                <span v-if="contract.waterBillingType==2">抄表计费</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style" v-if="contract.waterBillingType == '1'">
                            <el-form-item label="水费金额">
                                <span>{{contract.waterAmount}}&nbsp;元</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style"  v-if="contract.waterBillingType == '2'">
                            <el-form-item label="水费单价">
                                <span>{{contract.waterPrice}}&nbsp;元/吨</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="是否含税">
                                <span v-if="contract.waHasTaxNum==1">是</span>
                                <span v-if="contract.waHasTaxNum==2">否</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>-->



            <div class="titles-div">
                <span class="common-title-font">停车费</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="免费车位">
                                <span>{{contract.freeParkingCount}}&nbsp;个</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="超出收费">
                                <span>{{contract.parkingPrice}}&nbsp;元/个</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="el-row-style">
                            <el-form-item label="是否含税">
                                <span v-if="contract.paHasTaxNum==1">是</span>
                                <span v-if="contract.paHasTaxNum==2">否</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>


            <div class="titles-div">
                <span class="common-title-font">合同附件</span>
                <el-button v-if="contract.ctStatus == '4'"
                           size="small"
                           type="primary" style="float: left;margin: 12px 0 0 20px;"
                           @click="openUpProtocol">
                            上传补签协议
                </el-button>
            </div>

            <el-dialog
                    title="上传补签协议"
                    :visible.sync="showUpProtocol"
                    width="50%"
                    :close-on-click-modal="false"
                    :before-close="handleClose">

                <!--<el-button size="small">上传补签协议</el-button>-->
                    <el-row class="row_style">
                        <el-col  style="line-height: 30px;margin-left: 20px;">
                            <div style="float: left">
                                <file  @selectFile="selectFile"></file>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row style="padding-bottom: 15px;">
                        <el-col style="" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-for="(item, index) in fileList" :key="item">
                            <div style="margin:8px 0 0 20px;float: left">
                                <span >文件:&nbsp;&nbsp;&nbsp;{{item}}&nbsp;&nbsp;</span><i class="el-icon-delete" title="删除" @click="deleteFile(index)"></i>
                            </div>
                        </el-col>
                    </el-row>


                <span slot="footer" class="dialog-footer">
                    <el-button @click="handleClose">取 消</el-button>
                    <el-button type="primary" @click="submitProtocol">确 定</el-button>
                 </span>
            </el-dialog>


            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-for="(item, index) in contractUrlList" :key="index">
                        <div class="el-row-style">
                            <el-form-item label="文件" class="download_file">
                                <a title="点击下载" @click="downloadFile(item)">{{item}}</a>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <div class="titles-div">
                <span class="common-title-font">备注</span>
            </div>
            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="el-row-style">
                            <el-form-item label="备注说明">
                                <span>{{contract.remark}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </el-form>

        <!-- 合同驳回理由 -->
        <el-dialog title="合同审核结果" :visible.sync="rejectWindow" style="line-height: 40px;">
            <el-form ref="verifyObj" :model="verifyObj" :rules="rules" label-width="100px" style="text-align: left;">
                <el-form-item label="审核结果：" prop="ctStatus">
                    <el-radio v-model="verifyObj.ctStatus" label="5">驳回</el-radio>
                </el-form-item>
                <el-form-item label="驳回原因：" prop="operateRemark">
                    <el-input v-model="verifyObj.operateRemark" type="textarea" :rows="4" placeholder="请输入合同驳回原因"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="resetForm('verifyObj')">取 消</el-button>
                <el-button type="primary" @click="rejectSubmit('verifyObj')">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>


<script>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import file from "../../../common/components/file.vue";
    import ElRow from "element-ui/packages/row/src/row";
    import ElDialog from "../../../../node_modules/element-ui/packages/dialog/src/component";
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        components: {
            ElCol,
            ElDialog,
            ElRow,
            ElButton,
            file
        },
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,

                // 权限控制
                showEditContractBtn: false,// 合同变更或修改按钮
                showDetailFeeBtn: false,// 应收详情按钮
                showManualMeterBtn: false,// 水电抄表按钮
                showAddParkingSpaceBtn: false,// 合同变更或修改按钮
                showFirstVerify: false,// 一审审核
                showSecondVerify: false,// 二审审核

                showBasicInfoUpdate: false,//二审通过的合同变更（只能修改基础信息）

                rejectWindow: false,// 合同驳回窗口

                pkAllList: [],
                contractUrlList: [],// 合同附件编号

                incDetailCase: [
                    {
                        value: '',
                    }
                ],
                incDetailCaseList: [],

                fileList:[],//补签协议
                showUpProtocol:false,//补签协议弹框

                contract: {
                    cusCode: "",//客户编码
                    customerType: "",//客户类型
                    companyName: "",//公司名称
                    trade: "",//所属行业+
                    tradeName: "",//所属行业名称+
                    companyProduct: "",//公司产品+
                    relateName: "",//联系人
                    relateSex: "",//联系人性别
                    relatePhone: "",//联系电话
                    landlinePhone: "",//座机


                    pkCode: "",//入驻园区+
                    pkName: "",

                    contractor: "",//签约代表
                    contractorPhone: "",//联系电话+
                    negotiator: "",//谈判代表+
                    negotiatorPhone: "",//联系电话+
                    partyName: "",//甲方公司+
                    houseAddress: "",//房源地址


                    ctName: "",//合同编号+

                    deliveryDate: "",//交付日期+

                    advanceDateCount: "",//提前交租+

                    contractDate: "",//签约日期+

                    contractStartTime: "",//合同开始

                    contractEndTime: "",//合同结束

                    startRentDate: "",//计租日期
                    //租金递增
                    incDetailType: "2",
                    increasingCycle: "",
                    rentIncreaseProportion: "",
                    incDetailCase: "",

                    rentTotal: "",//租金合计+
                    hasTax: "",//是否含税
                    hasTaxNum: "",

                    propertyUnitPrice: "",//物业费单价

                    //物业费单价单位
                    propertyUnit: "元/㎡/月",
                    totalRentalArea: "",

                    //出租楼层集合
                    contractHouseList: [
                        {
                            flCode: "",
                            roomNumber: "",
                            roomCode:"",
                            rentArea: "",
                            unitPrice: ""
                        }
                    ],

                    //付款方式
                    payRule: "",//付
                    depositRule: "",//押
                    deposit: "",//实付押金
                    eleDeposit: "",//电费押金
                    waterDeposit: "",//水费押金

                    //首期付款
                    fpayHouseRent: "",// 首期支付房屋租金
                    fpayPropertyPrice: "",// 首期支付物业费
                    fpayInsurancePrice: "",// 首期支付保险费
                    fpayProjectDeposit: "",// 首期支付工程押金
                    fpayBond: "",// 首期支付保证金

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
                },
                cusNames: [],
                pkNames: [],

                fls: [],// 楼层集合
                flName: "",// 选择的楼层名

                customers: [],
                cusObj: {
                    customerType: "",
                    companyName: "",
                    trade: "",
                    companyProduct: "",
                    mainContactName: "",
                    mainContactPhone: "",
                },

                companyNameFlag: false,
                proTotal: 0,//物业费

                // 原因表单提交
                verifyObj: {
                    ctStatus: '5',// 合同状态
                    operateRemark: '',// 合同驳回原因
                },

                // 校验
                rules: {
                    ctStatus: [
                        {required: true, message: '请选择审核结果', trigger: 'change'},
                    ],
                    operateRemark: [
                        {required: true, message: '请填写驳回原因', trigger: 'blur'}
                    ],
                },
            }
        },
        computed: {
            //租金合计
            total: {
                get: function () {
                    let list = this.contract.contractHouseList;
                    let total = 0;
                    let propertyUnitPrice = this.contract.propertyUnitPrice;
                    for (let i = 0; i < list.length; i++) {
                        let investArea = list[i].investArea;
                        total += (list[i].monthlyRent + investArea * propertyUnitPrice);
                    }
                    this.proTotal = (propertyUnitPrice * this.contract.totalRentalArea).toFixed(2);
                    total = total.toFixed(2);
                    return total;
                },
                set: function () {
                    return 0;
                }
            },
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
                    num += this.contract.eleDeposit;
                }
                if (this.contract.waterDeposit) {
                    num += this.contract.waterDeposit;
                }
                if (this.contract.fpayHouseRent) {
                    num += this.contract.fpayHouseRent;
                }
                if (this.contract.fpayPropertyPrice) {
                    num += this.contract.fpayPropertyPrice;
                }
                if (this.contract.fpayInsurancePrice) {
                    num += this.contract.fpayInsurancePrice;
                }
                if (this.contract.fpayProjectDeposit) {
                    num += this.contract.fpayProjectDeposit;
                }
                if (this.contract.fpayBond) {
                    num += this.contract.fpayBond;
                }
                return num.toFixed(2);
            }
        },
        //接受父组件传过来的参数
        props: ['paramsObj'],

        created: function () {
            let vm = this;

            // 菜单和按钮权限控制
            vm.showEditContractBtn = vm.$flagMenuStore.judgeMenu("editContractBtn");// 合同变更或修改按钮
            vm.showDetailFeeBtn = vm.$flagMenuStore.judgeMenu("detailFeeBtn");// 应收详情按钮
            vm.showManualMeterBtn = vm.$flagMenuStore.judgeMenu("manualMeterBtn");// 水电抄表按钮
            vm.showAddParkingSpaceBtn = vm.$flagMenuStore.judgeMenu("addParkingSpaceBtn");// 新增停车位按钮
            vm.showFirstVerify = vm.$flagMenuStore.judgeMenu("firstVerifyList");
            vm.showSecondVerify = vm.$flagMenuStore.judgeMenu("secondVerifyList");

            vm.showBasicInfoUpdate = vm.$flagMenuStore.judgeMenu("showBasicInfoUpdate");

            // 查询客户详情
            if(vm.paramsObj) {
                vm.contract.ctCode = vm.paramsObj.ctCode;
            }

            if (vm.contract.ctCode) {
                vm.contractDetails(vm.contract.ctCode);// 查询合同详情
            }
        },
        methods: {
            // 合同详情
            contractDetails(ctCode){
                let vm = this;
                let ctData = {
                    entity:{
                        ctCode: ctCode
                    }
                };
                let options = {
                    method: "POST",
                    data: ctData,
                    url: "contract/detail",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if(response){
                        vm.contract = response.data.result;
                        vm.incDetailCaseList = vm.contract.incDetailCase.split(",");
                        if (vm.contract.hasTax) {
                            vm.contract.hasTaxNum = '1'
                        } else {
                            vm.contract.hasTaxNum = '2'
                        }
                        if (vm.contract.elHasTax) {
                            vm.contract.elHasTaxNum = '1'
                        } else {
                            vm.contract.elHasTaxNum = '2'
                        }
                        if (vm.contract.waHasTax) {
                            vm.contract.waHasTaxNum = '1'
                        } else {
                            vm.contract.waHasTaxNum = '2'
                        }
                        if (vm.contract.paHasTax) {
                            vm.contract.paHasTaxNum = '1'
                        } else {
                            vm.contract.paHasTaxNum = '2'
                        }

                        vm.contractUrlList = vm.contract.contractUrl.split('，');
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查看租户详细信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 跳转到合同变更页面
            editCon(id, ctCode, ctStatus){
                if (this.contract.ctStatus == '4') {
                    this.$alert('页面：该合同已经审核通过，无法修改！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                } else {
                    let data = {
                        menuName:"合同变更",
                        item: "contractEdit",
                        params: {
                            id: id,
                            ctCode: ctCode,
                            ctStatus: ctStatus
                        }
                    };
                    this.$emit("backParam",data);
                }
            },

            /*--------------------------------- 合同审核开始 ---------------------------------*/
            // 提交一审
            firstVerify(id, ctCode){
                let vm = this;

                let params = {
                    entity: {
                        id: id,
                        ctCode: ctCode,
                        ctStatus: '2',
                    }
                };
                let confirmMsg = '是否提交合同审核？';
                let thenMsg = '提交一审成功！';
                let catchMsg = '页面：提交一审失败！';
                let cancelMsg = '审核提交已取消！';
                vm.contractVerifyCommonMethod(params, confirmMsg, thenMsg, catchMsg, cancelMsg);
            },
            // 一审通过
            firstVerifyPass(id, ctCode){
                let vm = this;

                let params = {
                    entity: {
                        id: id,
                        ctCode: ctCode,
                        ctStatus: '3',
                    }
                };
                let confirmMsg = '是否确认合同一审通过？';
                let thenMsg = '合同一审已通过！';
                let catchMsg = '页面：一审通过操作失败！';
                let cancelMsg = '一审通过已取消！';
                vm.contractVerifyCommonMethod(params, confirmMsg, thenMsg, catchMsg, cancelMsg);
            },
            // 二审通过
            secondVerifyPass(id, ctCode){
                let vm = this;

                let params = {
                    entity: {
                        id: id,
                        ctCode: ctCode,
                        ctStatus: '4',
                    }
                };
                let confirmMsg = '是否确认合同二审通过？';
                let thenMsg = '合同二审已通过！';
                let catchMsg = '页面：二审通过操作失败！';
                let cancelMsg = '二审通过已取消！';
                vm.contractVerifyCommonMethod(params, confirmMsg, thenMsg, catchMsg, cancelMsg);
            },
            // 驳回填写重置
            resetForm(subForm){
                this.rejectWindow = false;
                this.$refs[subForm].resetFields();
            },
            // 打开合同驳回窗口，填写驳回理由
            openRejectWindow(){
                let vm = this;
                vm.rejectWindow = true;
            },
            // 驳回
            rejectSubmit(subForm){
                let vm = this;

                this.$refs[subForm].validate((valid) => {
                    if (valid) {
                        let params = {
                            entity: {
                                id: vm.contract.id,
                                ctCode: vm.contract.ctCode,
                                ctStatus: '5',
                                operateRemark: vm.verifyObj.operateRemark,
                            },
                        };
                        let confirmMsg = '是否驳回此合同？';
                        let thenMsg = '合同驳回成功！';
                        let catchMsg = '页面：合同驳回失败！';
                        let cancelMsg = '合同驳回已取消！';
                        vm.contractVerifyCommonMethod(params, confirmMsg, thenMsg, catchMsg, cancelMsg);

                        vm.resetForm(subForm);// 表单重置
                    } else {
                        vm.$message({
                            showClose: true,
                            message: '信息填写不完整',
                            type: 'warning'
                        });
                    }
                });
            },

            //合同基础信息变更
            basicInfoUpdate(id, ctCode, ctStatus) {
                let data = {
                    menuName:"合同基础变更",
                    item: "contractBasicEdit",
                    params: {
                        id: id,
                        ctCode: ctCode,
                        ctStatus: ctStatus
                    }
                };
                this.$emit("backParam",data);
            },

            // 取消审核
            cancelVerify(id, ctCode){
                let vm = this;

                let params = {
                    entity: {
                        id: id,
                        ctCode: ctCode,
                        ctStatus: '6',
                    }
                };
                let confirmMsg = '是否撤回合同审核？';
                let thenMsg = '合同审核已撤回';
                let catchMsg = '页面：合同审核撤回失败！';
                let cancelMsg = '撤回审核已取消！';

                vm.contractVerifyCommonMethod(params, confirmMsg, thenMsg, catchMsg, cancelMsg);
            },
            // 合同审批公共方法
            contractVerifyCommonMethod(params, confirmMsg, thenMsg, catchMsg, cancelMsg){
                let vm = this;

                vm.$confirm(confirmMsg, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let options = {
                        method: "POST",
                        data: params,
                        url: "contract/contractVerify",
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        if (!response.data.isException) {
                            vm.$message({
                                showClose: true,
                                message: thenMsg,
                                type: 'success',
                            });
                            vm.contractDetails(vm.contract.ctCode);
                        }
                    }).catch(error => {
                        vm.$alert(catchMsg, '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    })
                }).catch(() => {
                    vm.$message({
                        type: 'info',
                        message: cancelMsg
                    });
                });
            },
            /*--------------------------------- 合同审核结束 ---------------------------------*/

            // 校验房屋出租非必填字段0～99999999.99
            checkHouseNum(value, index, val){
                if (val < 0 || val > 99999999.99) {
                    this.$message.error('输入数字超出0～99999999.99范围');
                    if (value == 1) {
                        this.contract.contractHouseList[index].rentArea = '';
                    } else if (value == 2) {
                        this.contract.contractHouseList[index].unitPrice = '';
                    }
                }
            },

            //校验非必填字段0～99999999.99
            checkNum(value){
                if (value == 'eleDeposit') {
                    var ele = this.contract.eleDeposit;
                    if (ele < 0 || ele > 99999999.99) {
                        this.$message.error('电费押金输入数字超出0～99999999.99范围');
                        this.contract.eleDeposit = '';
                    }
                } else if (value == 'waterDeposit') {
                    var water = this.contract.waterDeposit;
                    if (water < 0 || water > 99999999.99) {
                        this.$message.error('水费押金输入数字超出0～99999999.99范围');
                        this.contract.waterDeposit = '';
                    }
                } else if (value == 'rentIncreaseProportion') {
                    var rent = this.contract.rentIncreaseProportion;
                    if (rent < 0 || rent > 99999999.99) {
                        this.$message.error('租金递增输入数字超出0～99999999.99范围');
                        this.contract.rentIncreaseProportion = 0;
                    }
                } else if (value == 'depositRule') {
                    var dep = this.contract.depositRule;
                    if (dep < 0 || dep > 99999999.99) {
                        this.$message.error('付款方式输入数字超出0～99999999.99范围');
                        this.contract.depositRule = 0;
                    }
                } else if (value == 'fpayHouseRent') {
                    var dep = this.contract.fpayHouseRent;
                    if (dep < 0 || dep > 99999999.99) {
                        this.$message.error('房屋租金输入数字超出0～99999999.99范围');
                        this.contract.fpayHouseRent = 0;
                    }
                } else if (value == 'fpayPropertyPrice') {
                    var dep = this.contract.fpayPropertyPrice;
                    if (dep < 0 || dep > 99999999.99) {
                        this.$message.error('物业费输入数字超出0～99999999.99范围');
                        this.contract.fpayPropertyPrice = 0;
                    }
                } else if (value == 'fpayInsurancePrice') {
                    var dep = this.contract.fpayInsurancePrice;
                    if (dep < 0 || dep > 99999999.99) {
                        this.$message.error('保险费输入数字超出0～99999999.99范围');
                        this.contract.fpayInsurancePrice = 0;
                    }
                } else if (value == 'fpayProjectDeposit') {
                    var dep = this.contract.fpayProjectDeposit;
                    if (dep < 0 || dep > 99999999.99) {
                        this.$message.error('工程押金输入数字超出0～99999999.99范围');
                        this.contract.fpayProjectDeposit = 0;
                    }
                } else if (value == 'fpayBond') {
                    var dep = this.contract.fpayBond;
                    if (dep < 0 || dep > 99999999.99) {
                        this.$message.error('保证金输入数字超出0～99999999.99范围');
                        this.contract.fpayBond = 0;
                    }
                } else if (value == 'fpayAllPrice') {
                    var dep = this.contract.fpayAllPrice;
                    if (dep < 0 || dep > 99999999.99) {
                        this.$message.error('首付总额输入数字超出0～99999999.99范围');
                        this.contract.fpayAllPrice = 0;
                    }
                }
            },

            //应收详情
            costDetail(ctCode, ctStatus) {
                let data = {
                    menuName:"应收详情",
                    item: "costDetail",
                    params: {
                        ctCode: ctCode,
                        ctStatus: ctStatus,
                    }
                };
                this.$emit("backParam",data);
            },

            //手动抄表
            manualMeter(ctCode) {
                let data = {
                    menuName:"水电抄表",
                    item: "manualMeter",
                    params: {
                        ctCode: ctCode
                    }
                };
                this.$emit("backParam",data);
            },

            //新增停车位
            parkingAdd(ctCode, freeParkingCount, parkingPrice, parkingSpace){
                let data = {
                    menuName:"新增停车位",
                    item: "parkingAdd",
                    params: {
                        ctCode: ctCode,
                        freeParkingCount: freeParkingCount,
                        parkingPrice: parkingPrice,
                        parkingSpace: parkingSpace,
                    }
                };
                this.$emit("backParam",data);
            },

            //上传附件
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

            //打开补签协议弹框
            openUpProtocol(){
                this.showUpProtocol = true;
            },

            handleClose(){
                this.fileList = [];
                this.showUpProtocol = false;
            },

            //提交补签协议
            submitProtocol(){
                let vm = this;
                let fileList = vm.fileList;

                if(fileList.length<1){
                    vm.$message({
                        type: 'warning',
                        message: '请选择文件！'
                    })
                    return;
                }

                for(let i = 0; i < fileList.length; i++){
                    vm.contractUrlList.push(fileList[i]);
                }
                let contractUrl = vm.contractUrlList.join("，");

                let params = {
                    entity:{
                        id: vm.contract.id,
                        ctCode: vm.contract.ctCode,
                        contractUrl: contractUrl
                    }
                }

                let options = {
                    method: 'post',
                    data: params,
                    url: '/contract/updateQuery'
                }
                this.$ajax(
                    options
                ).then(res => {
                    vm.fileList = [];
                    vm.showUpProtocol = false;
                    vm.$message({
                        type: 'success',
                        message: '添加成功！'
                    })
                }).catch(error =>{
                    vm.$message({
                        type: 'error',
                        message: '添加失败！'
                    });
                })
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
    .root_form {
        overflow-y: auto;
        overflow-x: hidden;
    }

    /*导航栏标题*/
    .navigation-title {
        line-height: 50px;
        margin-left: 20px;
        font-size: 17px;
        color: #409EFF;
        float: left;
    }

    /*变更合同或续签按钮*/
    .change-contract {
        float: left;
        margin: 0 0 0 20px;
    }

/*****************************************************************************************************/
    /*标题div*/
    .titles-div{
        height: 50px;
        background-color: white;
        border-bottom: 1px solid rgb(242, 242, 242);
        margin-top: 6px;
    }

    /* 标题*/
    .common-title-font {
        line-height: 50px;
        margin: 0 0 0 20px;
        font-size: 17px;
        color: #409EFF;
        float: left;
    }
/*****************************************************************************************************/
    /*基本信息div*/
    .common-info-div{
        background-color: white;
        width: 100%;
        text-align: left;
    }

    /*行样式*/
    .el-row-style{
        /*border: solid 1px;*/
        height: 30px;
        line-height: 30px;
        margin-left: 20px;
    }

    /*应收表格*/
    .costTable{
        line-height: 40px;
        text-align: center;
        margin-left: 20px;
        width: 96%;
        padding-bottom: 10px;
    }

    .download_file a:hover{
        cursor: pointer;
        color: #66b1ff;
        text-decoration: underline;
    }

</style>