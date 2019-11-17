<template>
    <div class="has-position_root" ref="myRoot">
        <!-- 悬浮框 -->
        <div class="search_box">
            <div class="has-position_header">
                <div class="contract_box_up_right">
                    <div class="contract_code">
                        <!-- 自动生成租金按钮隐藏  (不删除) -->
                        <!--<el-button v-if="ctObj.rentReceiptList != null && ctObj.rentReceiptList.length <= 0"-->
                        <!--type="warning" size="small"  plain @click="createRent()">生成租金</el-button>-->
                        <el-button type="primary" size="small" @click="manualMeter(ctObj.ctCode)">水电抄表</el-button>
                        <el-button type="primary" size="small" @click="showCusDetail(ctObj.ctCode)">租户详情</el-button>
                    </div>
                </div>
                <span style="color: #409EFF;margin-left: 20px;">应收详情 - {{ctObj.companyName}}</span>
            </div>
        </div>

        <el-form label-width="110px">
            <!-- 一个租户的信息-->
            <div class="contract_box_top">
                <!--  应收明细 -->
                <div class="billingDetail">
                    <div class="billingDetail_top_right" title="点击查看" @click="showIncInfo()">
                        租金递增明细
                    </div>
                    <div class="billingDetail_top_right" title="点击查看" @click="showCostInfo(ctObj)">
                        计费规则
                    </div>
                    <div class="billingDetail_top_right" title="点击查看" @click="showHouseInfo()">
                        房租构成
                    </div>
                    <div class="billingDetail_top">
                        <el-button type="primary" size="small" class="billingDetail_button" @click="showThisBilling(1)"
                                   v-if="isShowIndex == 1">本期应收
                        </el-button>

                        <el-button type="primary" size="small" class="billingDetail_button" plain
                                   @click="showThisBilling(1)" v-if="isShowIndex != 1">本期应收
                        </el-button>

                        <el-button type="primary" size="small" class="billingDetail_button"
                                   @click="showHouseProperty(2)" v-if="isShowIndex == 2">房租物业费
                        </el-button>

                        <el-button type="primary" size="small" class="billingDetail_button" plain
                                   @click="showHouseProperty(2)" v-if="isShowIndex != 2">房租物业费
                        </el-button>

                        <el-button type="primary" size="small" class="billingDetail_button" @click="showPremium(6)"
                                   v-if="isShowIndex == 6">保险费
                        </el-button>

                        <el-button type="primary" size="small" class="billingDetail_button" plain
                                   @click="showPremium(6)" v-if="isShowIndex != 6">保险费
                        </el-button>

                        <el-button type="primary" size="small" class="billingDetail_button" @click="showEle(3)"
                                   v-if="isShowIndex == 3">电费
                        </el-button>

                        <el-button type="primary" size="small" class="billingDetail_button" plain @click="showEle(3)"
                                   v-if="isShowIndex != 3">电费
                        </el-button>

                        <el-button type="primary" size="small" class="billingDetail_button" @click="showWater(4)"
                                   v-if="isShowIndex == 4">水费
                        </el-button>

                        <el-button type="primary" size="small" class="billingDetail_button" plain @click="showWater(4)"
                                   v-if="isShowIndex != 4">水费
                        </el-button>

                        <el-button type="primary" size="small" class="billingDetail_button" @click="showParking(5)"
                                   v-if="isShowIndex == 5">停车费
                        </el-button>

                        <el-button type="primary" size="small" class="billingDetail_button" plain
                                   @click="showParking(5)" v-if="isShowIndex != 5">停车费
                        </el-button>
                    </div>
                </div>

                <!-- 显示详情信息 -->
                <div>
                    <!-- 本期应收框 -->
                    <div class="detail_box" v-show="isShowIndex == 1">

                        <!-- 本期所属租赁信息 -->
                        <div class="contract_box_data">
                            <div class="this_billing_div">
                                <div v-if="!ctObj.rentThisReceipt || !ctObj.rentThisReceipt[0]">
                                    <div class="this_billing_title">
                                        <div style="float: left">
                                            <span class="contract_title">本期暂无所属租赁信息</span>
                                        </div>
                                    </div>
                                </div>

                                <div v-else>
                                    <div class="this_billing_title">
                                        <div style="float: left">
                                            <span class="contract_title">本期物业费</span>
                                            <span class="contract_title">(免租期：{{ctObj.freeRentStartTime | timeDate}} ～ {{ctObj.freeRentEndTime | timeDate}})</span>
                                        </div>
                                    </div>

                                    <el-table :data="ctObj.rentThisReceipt" class="costTable" border :highlight-current-row="true" size="small" min-height="500">
                                        <el-table-column header-align="center" min-width="100" label="开始日期">
                                            <template slot-scope="scope">{{ scope.row.rentStartTime  | timeDate}}</template>
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" label="结束日期">
                                            <template slot-scope="scope">{{ scope.row.rentEndTime  | timeDate}}</template>
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" label="截止日期">
                                            <template slot-scope="scope">{{ scope.row.receivingDate  | timeDate}}</template>
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" label="状态">
                                            <template slot-scope="scope">
                                                <el-button v-if="scope.row.payStatus == 1" type="info" size="mini" round>待支付</el-button>
                                                <el-button v-if="scope.row.payStatus == 2" type="success"size="mini" round>已支付</el-button>
                                                <el-button v-if="scope.row.payStatus == 3" type="danger" size="mini" round>逾&nbsp;&nbsp;&nbsp;&nbsp;期</el-button>
                                                <el-button v-if="scope.row.payStatus == 4" type="warning" size="mini" round>未结清</el-button>
                                            </template>
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                prop="receivableRent"
                                                label="本期租金">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                prop="propertyPrice"
                                                label="本期物业费">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                prop="totalPrice"
                                                label="合计应收">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                prop="paidPrice"
                                                label="已收金额">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                prop="discountsPrice"
                                                label="优惠金额">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="待收金额">
                                            <template slot-scope="scope">
                                                <span v-if="(scope.row.totalPrice - scope.row.paidPrice - scope.row.discountsPrice) >= 0">
                                                    {{(scope.row.totalPrice - scope.row.paidPrice - scope.row.discountsPrice).toFixed(2)}}
                                                </span>
                                                <span v-else>0.00</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" label="余额">
                                            <template slot-scope="scope">
                                                <span v-if="scope.row.paidPrice - scope.row.totalPrice > 0">
                                                    {{(scope.row.paidPrice - scope.row.totalPrice).toFixed(2)}}
                                                </span>
                                                <span v-else>0.00</span>
                                            </template>
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
                                                <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                                                <span v-if="!scope.row.hasTax">---</span>
                                            </template>
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
                                                <span v-if="scope.row.proHasTax">{{ scope.row.proTaxPointName }}</span>
                                                <span v-if="!scope.row.proHasTax">---</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" fixed="right" label="操作" width="200">
                                            <template slot-scope="scope">
                                                <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6" @click="openEditPay(scope.row.rentCode)" type="text" size="small">修改</el-button>
                                                <el-button v-if="ctStatus == 4 || ctStatus==5 || ctStatus==6" @click="openPayHistoryWindow(scope.row.rentCode, 1, scope.row.totalPrice,scope.row.paidPrice,scope.row.discountsPrice)" type="text" size="small">缴费列表</el-button>
                                                <el-button v-if="showRentPaymentBtn && ctStatus== 4" @click="openRentWindows(scope.row.rentCode)" type="text" size="small">缴费办理</el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </div>
                            </div>
                        </div>

                        <div style="clear: both;"></div>

                        <!-- 本期保险费 -->
                        <div class="contract_box_data">
                            <div class="this_billing_div">
                                <div v-if="!ctObj.premiumThisReceipt || !ctObj.premiumThisReceipt[0]">
                                    <div class="this_billing_title">
                                        <div style="float: left">
                                            <span class="contract_title">本期暂无保险费信息</span>
                                        </div>
                                    </div>
                                </div>
                                <div v-else>
                                    <div class="this_billing_title">
                                        <div style="float: left">
                                            <span class="contract_title">本期保险费</span>
                                        </div>
                                    </div>
                                    <el-table class="costTable" :data="ctObj.premiumThisReceipt" border
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
                                        <el-table-column header-align="center" min-width="100" label="状态">
                                            <template slot-scope="scope">
                                                <el-button v-if="scope.row.payStatus == 1" type="info" size="mini" round>待支付</el-button>
                                                <el-button v-if="scope.row.payStatus == 2" type="success"size="mini" round>已支付</el-button>
                                                <el-button v-if="scope.row.payStatus == 3" type="danger" size="mini" round>逾&nbsp;&nbsp;&nbsp;&nbsp;期</el-button>
                                                <el-button v-if="scope.row.payStatus == 4" type="warning" size="mini" round>未结清</el-button>
                                            </template>
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
                                                <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                                                <span v-if="!scope.row.hasTax">---</span>
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
                                                min-width="100"
                                                prop="paidPrice"
                                                label="已收金额">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="待收金额">
                                            <template slot-scope="scope">
                                                <span v-if="(scope.row.totalPrice - scope.row.paidPrice) >= 0">
                                                    {{(scope.row.totalPrice - scope.row.paidPrice).toFixed(2)}}
                                                </span>
                                                <span v-else>0.00</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" label="余额">
                                            <template slot-scope="scope">
                                                <span v-if="scope.row.paidPrice - scope.row.totalPrice > 0">
                                                    {{(scope.row.paidPrice - scope.row.totalPrice).toFixed(2)}}
                                                </span>
                                                <span v-else>0.00</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" fixed="right" label="操作" width="200">
                                            <template slot-scope="scope">
                                                <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6" @click="openUpdatePremium(scope.row.premiumCode)" type="text" size="small">修改</el-button>
                                                <el-button v-if="ctStatus == 4 || ctStatus==5 || ctStatus==6" @click="openPayHistoryWindow(scope.row.premiumCode, 5, scope.row.totalPrice, scope.row.paidPrice)" type="text" size="small">缴费列表</el-button>
                                                <el-button v-if="showPremiumPaymentBtn && ctStatus== 4" @click="openPremiumPayWindow(scope.row.premiumCode)" type="text" size="small">缴费办理</el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </div>
                            </div>
                        </div>

                        <div style="clear: both;"></div>

                        <!-- 本期电费收费信息 -->
                        <div class="contract_box_data">
                            <div class="this_billing_div">
                                <div v-if="!ctObj.eleThisReceipt || !ctObj.eleThisReceipt[0]">
                                    <div class="this_billing_title">
                                        <div style="float: left">
                                            <span class="contract_title">本期暂无电费收费信息</span>
                                        </div>
                                    </div>
                                </div>
                                <div v-else>
                                    <div class="this_billing_title">
                                        <div style="float: left">
                                            <span class="contract_title">本期电费</span>
                                        </div>
                                    </div>

                                    <el-table
                                            class="costTable"
                                            :data="ctObj.eleThisReceipt"
                                            border
                                            :highlight-current-row="true"
                                            size="small"
                                            min-height="500">
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="开始日期">
                                            <template slot-scope="scope">{{ scope.row.lastBillingTime  | monthDate}}</template>
                                        </el-table-column>
                                        <!--<el-table-column-->
                                                <!--header-align="center"-->
                                                <!--min-width="100"-->
                                                <!--label="结束日期">-->
                                            <!--<template slot-scope="scope">{{ scope.row.currentBillingTime  | timeDate}}</template>-->
                                        <!--</el-table-column>-->
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="截止日期">
                                            <template slot-scope="scope">{{ scope.row.receivingDate  | timeDate}}</template>
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" label="状态">
                                            <template slot-scope="scope">
                                                <el-button v-if="scope.row.payStatus == 1" type="info" size="mini" round>待支付</el-button>
                                                <el-button v-if="scope.row.payStatus == 2" type="success"size="mini" round>已支付</el-button>
                                                <el-button v-if="scope.row.payStatus == 3" type="danger" size="mini" round>逾&nbsp;&nbsp;&nbsp;&nbsp;期</el-button>
                                                <el-button v-if="scope.row.payStatus == 4" type="warning" size="mini" round>未结清</el-button>
                                            </template>
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                prop="basicElectricityPriceByMonth"
                                                label="月基础电费">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                prop="electricityPrice"
                                                label="用电应收">
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
                                        <el-table-column header-align="center" min-width="70" label="税点">
                                            <template slot-scope="scope">
                                                <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                                                <span v-if="!scope.row.hasTax">---</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" prop="receivablePrice" label="合计应收">
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" prop="paidPrice" label="已收金额">
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" label="待收金额">
                                            <template slot-scope="scope">
                                                <span v-if="(scope.row.receivablePrice - scope.row.paidPrice) >= 0">
                                                    {{(scope.row.receivablePrice - scope.row.paidPrice).toFixed(2)}}
                                                </span>
                                                <span v-else>0.00</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" label="余额">
                                            <template slot-scope="scope">
                                                <span v-if="scope.row.paidPrice - scope.row.receivablePrice > 0">
                                                    {{(scope.row.paidPrice - scope.row.receivablePrice).toFixed(2)}}
                                                </span>
                                                <span v-else>0.00</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" fixed="right" label="操作" width="200">
                                            <template slot-scope="scope">
                                                <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6" @click="openEleWaterEdit(scope.row.eleCode)" type="text" size="small">修改</el-button>
                                                <el-button v-if="ctStatus == 4 || ctStatus==5 || ctStatus==6" @click="openPayHistoryWindow(scope.row.eleCode, 2, scope.row.receivablePrice, scope.row.paidPrice)" type="text" size="small">缴费列表</el-button>
                                                <el-button v-if="showElectricityFeePaymentBtn && ctStatus== 4" @click="openBox(scope.row.eleCode)" type="text" size="small">缴费办理</el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </div>
                            </div>
                        </div>

                        <div style="clear: both;"></div>

                        <!-- 本期水费收费信息 -->
                        <div class="contract_box_data">
                            <div class="this_billing_div">
                                <div v-if="!ctObj.waterThisReceipt || !ctObj.waterThisReceipt[0]">
                                    <div class="this_billing_title">
                                        <div style="float: left">
                                            <span class="contract_title">本期暂无水费收费信息</span>
                                        </div>
                                    </div>
                                </div>
                                <div v-else>
                                    <div class="this_billing_title">
                                        <div style="float: left">
                                            <span class="contract_title">本期水费</span>
                                        </div>
                                    </div>

                                    <el-table
                                            class="costTable"
                                            :data="ctObj.waterThisReceipt"
                                            border
                                            :highlight-current-row="true"
                                            size="small"
                                            min-height="500">
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="所属月份">
                                            <template slot-scope="scope">{{ scope.row.lastBillingTime  | monthDate}}</template>
                                        </el-table-column>
                                        <!--<el-table-column-->
                                                <!--header-align="center"-->
                                                <!--min-width="100"-->
                                                <!--label="结束日期">-->
                                            <!--<template slot-scope="scope">{{ scope.row.currentBillingTime  | timeDate}}</template>-->
                                        <!--</el-table-column>-->
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="截止日期">
                                            <template slot-scope="scope">{{ scope.row.receivingDate  | timeDate}}</template>
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="状态">
                                            <template slot-scope="scope">
                                                <el-button v-if="scope.row.payStatus == 1" type="info" size="mini" round>待支付</el-button>
                                                <el-button v-if="scope.row.payStatus == 2" type="success"size="mini" round>已支付</el-button>
                                                <el-button v-if="scope.row.payStatus == 3" type="danger" size="mini" round>逾&nbsp;&nbsp;&nbsp;&nbsp;期</el-button>
                                                <el-button v-if="scope.row.payStatus == 4" type="warning" size="mini" round>未结清</el-button>
                                            </template>
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
                                                <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                                                <span v-if="!scope.row.hasTax">---</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                prop="receivablePrice"
                                                label="合计应收">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                prop="paidPrice"
                                                label="已收金额">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="待收金额">
                                            <template slot-scope="scope">
                                                <span v-if="(scope.row.receivablePrice - scope.row.paidPrice) >= 0">
                                                    {{(scope.row.receivablePrice - scope.row.paidPrice).toFixed(2)}}
                                                </span>
                                                <span v-else>0.00</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" label="余额">
                                            <template slot-scope="scope">
                                                <span v-if="scope.row.paidPrice - scope.row.receivablePrice > 0">
                                                    {{(scope.row.paidPrice - scope.row.receivablePrice).toFixed(2)}}
                                                </span>
                                                <span v-else>0.00</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" fixed="right" label="操作" min-width="200">
                                            <template slot-scope="scope">
                                                <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6" @click="openEleWaterEdit(scope.row.waterCode)" type="text" size="small">修改</el-button>
                                                <el-button v-if="ctStatus == 4 || ctStatus==5 || ctStatus==6" @click="openPayHistoryWindow(scope.row.waterCode, 3, scope.row.receivablePrice, scope.row.paidPrice)" type="text" size="small">缴费列表</el-button>
                                                <el-button v-if="showWaterFeePaymentBtn && ctStatus== 4" @click="openBox(scope.row.waterCode)" type="text" size="small">缴费办理</el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </div>
                            </div>
                        </div>

                        <div style="clear: both;"></div>

                        <!-- 本期停车收费信息 -->
                        <div class="contract_box_data">
                            <div class="this_billing_div">
                                <div v-if="!ctObj.parkingThisReceipt || !ctObj.parkingThisReceipt[0]">
                                    <div class="this_billing_title">
                                        <div style="float: left">
                                            <span class="contract_title">本期暂无停车收费信息</span>
                                        </div>
                                    </div>
                                </div>
                                <div v-else>
                                    <div class="this_billing_title">
                                        <div style="float: left">
                                            <span class="contract_title">本期停车费</span>
                                        </div>
                                    </div>

                                    <el-table
                                            class="costTable"
                                            :data="ctObj.parkingThisReceipt"
                                            border
                                            :highlight-current-row="true"
                                            size="small"
                                            min-height="500"
                                            :show-overflow-tooltip="false">
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="开始日期">
                                            <template slot-scope="scope">{{ scope.row.startTime  | timeDate}}</template>
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="结束日期">
                                            <template slot-scope="scope">{{ scope.row.endTime  | timeDate}}</template>
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="截止日期">
                                            <template slot-scope="scope">{{ scope.row.receivingDate  | timeDate}}</template>
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="200"
                                                prop="parkingSpace"
                                                label="车位数量(不含免)">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="200"
                                                prop="unitPrice"
                                                label="计费单价(元/个/月)">
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
                                                <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                                                <span v-if="!scope.row.hasTax">---</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                prop="receivablePrice"
                                                label="合计应收">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                prop="paidPrice"
                                                label="已收金额">
                                        </el-table-column>
                                        <el-table-column
                                                header-align="center"
                                                min-width="100"
                                                label="待收金额">
                                            <template slot-scope="scope">
                                                <span v-if="(scope.row.totalPrice - scope.row.paidPrice) >= 0">
                                                    {{(scope.row.totalPrice - scope.row.paidPrice).toFixed(2)}}
                                                </span>
                                                <span v-else>0.00</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" label="余额">
                                            <template slot-scope="scope">
                                                <span v-if="scope.row.paidPrice - scope.row.totalPrice > 0">
                                                    {{(scope.row.paidPrice - scope.row.totalPrice).toFixed(2)}}
                                                </span>
                                                <span v-else>0.00</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" min-width="100" label="状态">
                                            <template slot-scope="scope">
                                                <el-button v-if="scope.row.payStatus == 1" type="info" size="mini" round>待支付</el-button>
                                                <el-button v-if="scope.row.payStatus == 2" type="success"size="mini" round>已支付</el-button>
                                                <el-button v-if="scope.row.payStatus == 3" type="danger" size="mini" round>逾&nbsp;&nbsp;&nbsp;&nbsp;期</el-button>
                                                <el-button v-if="scope.row.payStatus == 4" type="warning" size="mini" round>未结清</el-button>
                                            </template>
                                        </el-table-column>
                                        <el-table-column header-align="center" fixed="right" label="操作" width="200">
                                            <template slot-scope="scope">
                                                <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6" @click="openEleWaterEdit(scope.row.parkingCode)" type="text" size="small">修改</el-button>
                                                <el-button v-if="ctStatus == 4 || ctStatus==5 || ctStatus==6" @click="openPayHistoryWindow(scope.row.parkingCode, 4, scope.row.totalPrice, scope.row.paidPrice)" type="text" size="small">缴费列表</el-button>
                                                <el-button v-if="showParkingPaymentBtn && ctStatus== 4" @click="openBox(scope.row.parkingCode)" type="text" size="small">缴费办理</el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </div>
                            </div>
                        </div>


                    </div><!-- 本期应收框 结束-->
                    <!---------------------------------------------------------------------------------------------------------------------->

                    <!-- 循环房租物业费框 -->
                    <div class="detail_box" v-show="isShowIndex == 2">
                        <!--租金新样子-->
                        <div class="contract_box_data">
                            <div class="contract_box_addRent">
                                <el-button type="primary" size="small" @click="addRentWindow()">
                                    添加租金
                                </el-button>
                            </div>
                            <el-table
                                    class="costTable"
                                    :data="ctObj.rentReceiptList"
                                    border
                                    :highlight-current-row="true"
                                    size="small"
                                    height="500">
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="开始日期">
                                    <template slot-scope="scope">{{ scope.row.rentStartTime  | timeDate}}</template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="结束日期">
                                    <template slot-scope="scope">{{ scope.row.rentEndTime  | timeDate}}</template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="截止日期">
                                    <template slot-scope="scope">{{ scope.row.receivingDate  | timeDate}}</template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="状态">
                                    <template slot-scope="scope">
                                        <el-button v-if="scope.row.payStatus == 1" type="info" size="mini" round>待支付</el-button>
                                        <el-button v-if="scope.row.payStatus == 2" type="success"size="mini" round>已支付</el-button>
                                        <el-button v-if="scope.row.payStatus == 3" type="danger" size="mini" round>逾&nbsp;&nbsp;&nbsp;&nbsp;期</el-button>
                                        <el-button v-if="scope.row.payStatus == 4" type="warning" size="mini" round>未结清</el-button>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="receivableRent"
                                        label="本期租金">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="propertyPrice"
                                        label="本期物业费">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="totalPrice"
                                        label="合计应收">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="paidPrice"
                                        label="已收金额">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="discountsPrice"
                                        label="优惠金额">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="待收金额">
                                    <template slot-scope="scope">
                                        <span v-if="(scope.row.totalPrice - scope.row.paidPrice - scope.row.discountsPrice) >= 0">
                                            {{(scope.row.totalPrice - scope.row.paidPrice - scope.row.discountsPrice).toFixed(2)}}
                                        </span>
                                        <span v-else>0.00</span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="余额">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.paidPrice - scope.row.totalPrice > 0">
                                            {{(scope.row.paidPrice - scope.row.totalPrice).toFixed(2)}}
                                        </span>
                                        <span v-else>
                                            0.00
                                        </span>
                                    </template>
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
                                        <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                                        <span v-if="!scope.row.hasTax">---</span>
                                    </template>
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
                                        <span v-if="scope.row.proHasTax">{{ scope.row.proTaxPointName }}</span>
                                        <span v-if="!scope.row.proHasTax">---</span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        fixed="right"
                                        label="操作"
                                        width="220">
                                    <template slot-scope="scope">
                                        <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6 || bigEditReceipt" @click="openEditPay(scope.row.rentCode)" type="text" size="small">修改</el-button>
                                        <el-button v-if="ctStatus == 4 || ctStatus==5 || ctStatus==6" @click="openPayHistoryWindow(scope.row.rentCode, 1, scope.row.totalPrice, scope.row.paidPrice)" type="text" size="small">缴费列表</el-button>
                                        <el-button v-if="showRentPaymentBtn && ctStatus== 4" @click="openRentWindows(scope.row.rentCode)" type="text" size="small">缴费办理</el-button>
                                        <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6" @click="delRentReceipt(scope.row.id, scope.row.rentCode, scope.row.paidPrice)" type="text" size="small">删除</el-button>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>

                        <!-- 添加租金弹窗 -->
                        <el-dialog title="添加租金应收" :visible.sync="addRentReceipt" style="line-height: 20px;border: solid 1px;width: 105%">
                            <el-form :model="rentReceiptObj" :rules="rules" ref="rentReceiptObj" label-width="100px" style="text-align: left;">
                                <el-row :gutter="10" class="common-el-row-style">
                                    <el-col :span="12">
                                        <el-form-item label="开始日期" prop="rentStartTime">
                                            <el-date-picker
                                                    v-model="rentReceiptObj.rentStartTime"
                                                    align="right"
                                                    type="date">
                                            </el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label="结束日期" prop="rentEndTime">
                                            <el-date-picker
                                                    v-model="rentReceiptObj.rentEndTime"
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
                                                    v-model="rentReceiptObj.receivingDate"
                                                    align="right"
                                                    type="date">
                                            </el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                </el-row>

                                <el-row>
                                    <el-col :span="8">
                                        <el-form-item label="本期租金" prop="receivableRent">
                                            <el-input clearable v-model="rentReceiptObj.receivableRent">
                                                <template slot="append">元</template>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="8">
                                        <div class="grid-content" style="width: 300px;height: 50px;">
                                            <el-form-item label="是否含税" prop="hasTax">
                                                <el-radio-group v-model="rentReceiptObj.hasTax">
                                                    <el-radio :label=true>是</el-radio>
                                                    <el-radio :label=false>否</el-radio>
                                                </el-radio-group>
                                            </el-form-item>
                                        </div>
                                    </el-col>

                                    <el-col :span="8" v-if="rentReceiptObj.hasTax==true">
                                        <el-form-item label="税点" prop="taxPoint">
                                            <el-select v-model="rentReceiptObj.taxPoint" placeholder="请选择">
                                                <el-option
                                                        v-for="item in taxPoint"
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
                                            <el-input clearable v-model="rentReceiptObj.propertyPrice">
                                                <template slot="append">元</template>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="8">
                                        <div class="grid-content" style="width: 300px;height: 50px;">
                                            <el-form-item label="是否含税" prop="proHasTax">
                                                <el-radio-group v-model="rentReceiptObj.proHasTax">
                                                    <el-radio :label=true>是</el-radio>
                                                    <el-radio :label=false>否</el-radio>
                                                </el-radio-group>
                                            </el-form-item>
                                        </div>
                                    </el-col>

                                    <el-col :span="8" v-if="rentReceiptObj.proHasTax==true">
                                        <el-form-item label="税点" prop="proTaxPoint">
                                            <el-select v-model="rentReceiptObj.proTaxPoint" placeholder="请选择">
                                                <el-option
                                                        v-for="item in taxPoint"
                                                        :key="item.value"
                                                        :label="item.label"
                                                        :value="item.value">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>

                                <el-row :gutter="10">
                                    <el-col :span="12">
                                        <el-form-item label="合计应收" prop="totalPrice">
                                            <el-input clearable v-model="rentReceiptObj.totalPrice">
                                                <template slot="append">元</template>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>

                            </el-form>
                            <div slot="footer" class="dialog-footer">
                                <el-button @click="addRentReceipt = false">取 消</el-button>
                                <el-button type="primary" @click="rentSubmitForm('rentReceiptObj')">确 定</el-button>
                            </div>
                        </el-dialog>
                    </div>

                    <!-- 电费框 -->
                    <div class="detail_box" v-show="isShowIndex == 3">

                        <!-- 内容框1 -->
                        <div v-if="!ctObj.electricityReceiptList || ctObj.electricityReceiptList.length <= 0">
                            <div class="contract_box_data">
                                <div class="this_billing_title">
                                    <div style="float: left">
                                        <span class="contract_title">暂无电费抄表信息</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!--电费新样子-->
                        <div v-else class="contract_box_data">
                            <el-table
                                    class="costTable"
                                    :data="ctObj.electricityReceiptList"
                                    border
                                    :highlight-current-row="true"
                                    size="small"
                                    height="500">
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="开始日期">
                                    <template slot-scope="scope">{{ scope.row.lastBillingTime  | monthDate}}</template>
                                </el-table-column>
                                <!--<el-table-column-->
                                        <!--header-align="center"-->
                                        <!--min-width="100"-->
                                        <!--label="结束日期">-->
                                    <!--<template slot-scope="scope">{{ scope.row.currentBillingTime  | timeDate}}</template>-->
                                <!--</el-table-column>-->
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="截止日期">
                                    <template slot-scope="scope">{{ scope.row.receivingDate  | timeDate}}</template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="状态">
                                    <template slot-scope="scope">
                                        <el-button v-if="scope.row.payStatus == 1" type="info" size="mini" round>待支付</el-button>
                                        <el-button v-if="scope.row.payStatus == 2" type="success"size="mini" round>已支付</el-button>
                                        <el-button v-if="scope.row.payStatus == 3" type="danger" size="mini" round>逾&nbsp;&nbsp;&nbsp;&nbsp;期</el-button>
                                        <el-button v-if="scope.row.payStatus == 4" type="warning" size="mini" round>未结清</el-button>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="basicElectricityPriceByMonth"
                                        label="月基础电费">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="electricityPrice"
                                        label="用电应收">
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
                                        <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                                        <span v-if="!scope.row.hasTax">---</span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="receivablePrice"
                                        label="合计应收">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="paidPrice"
                                        label="已收金额">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="待收金额">
                                    <template slot-scope="scope">
                                        <span v-if="(scope.row.receivablePrice - scope.row.paidPrice) >= 0">
                                            {{(scope.row.receivablePrice - scope.row.paidPrice).toFixed(2)}}
                                        </span>
                                        <span v-else>
                                            0.00
                                        </span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="余额">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.paidPrice - scope.row.receivablePrice > 0">
                                            {{(scope.row.paidPrice - scope.row.receivablePrice).toFixed(2)}}
                                        </span>
                                        <span v-else>
                                            0.00
                                        </span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        fixed="right"
                                        label="操作"
                                        width="200">
                                    <template slot-scope="scope">
                                        <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6" @click="openEleWaterEdit(scope.row.eleCode)" type="text" size="small">修改</el-button>
                                        <el-button v-if="ctStatus == 4 || ctStatus==5 || ctStatus==6" @click="openPayHistoryWindow(scope.row.eleCode, 2, scope.row.receivablePrice, scope.row.paidPrice)" type="text" size="small">缴费列表</el-button>
                                        <el-button v-if="showElectricityFeePaymentBtn && ctStatus== 4" @click="openBox(scope.row.eleCode)" type="text" size="small">缴费办理</el-button>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                    </div>

                    <!-- 水费框 -->
                    <div class="detail_box" v-show="isShowIndex == 4">
                        <!-- 内容框1 -->
                        <div v-if="!ctObj.waterReceiptList || ctObj.waterReceiptList.length <= 0">
                            <div class="contract_box_data">
                                <div class="this_billing_title">
                                    <div style="float: left">
                                        <span class="contract_title">暂无水费抄表信息</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!--水费新样子-->
                        <div v-else class="contract_box_data">
                            <el-table
                                    class="costTable"
                                    :data="ctObj.waterReceiptList"
                                    border
                                    :highlight-current-row="true"
                                    size="small"
                                    height="500">
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="所属月份">
                                    <template slot-scope="scope">{{ scope.row.lastBillingTime  | monthDate}}</template>
                                </el-table-column>
                                <!--<el-table-column-->
                                        <!--header-align="center"-->
                                        <!--min-width="100"-->
                                        <!--label="结束日期">-->
                                    <!--<template slot-scope="scope">{{ scope.row.currentBillingTime  | timeDate}}</template>-->
                                <!--</el-table-column>-->
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="截止日期">
                                    <template slot-scope="scope">{{ scope.row.receivingDate  | timeDate}}</template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="状态">
                                    <template slot-scope="scope">
                                        <el-button v-if="scope.row.payStatus == 1" type="info" size="mini" round>待支付</el-button>
                                        <el-button v-if="scope.row.payStatus == 2" type="success"size="mini" round>已支付</el-button>
                                        <el-button v-if="scope.row.payStatus == 3" type="danger" size="mini" round>逾&nbsp;&nbsp;&nbsp;&nbsp;期</el-button>
                                        <el-button v-if="scope.row.payStatus == 4" type="warning" size="mini" round>未结清</el-button>
                                    </template>
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
                                        <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                                        <span v-if="!scope.row.hasTax">---</span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="receivablePrice"
                                        label="合计应收">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="paidPrice"
                                        label="已收金额">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="待收金额">
                                    <template slot-scope="scope">
                                        <span v-if="(scope.row.receivablePrice - scope.row.paidPrice) >= 0">
                                            {{(scope.row.receivablePrice - scope.row.paidPrice).toFixed(2)}}
                                        </span>
                                        <span v-else>
                                            0.00
                                        </span>

                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="余额">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.paidPrice - scope.row.receivablePrice > 0">
                                            {{(scope.row.paidPrice - scope.row.receivablePrice).toFixed(2)}}
                                        </span>
                                        <span v-else>
                                            0.00
                                        </span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        fixed="right"
                                        label="操作"
                                        min-width="200">
                                    <template slot-scope="scope">
                                        <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6" @click="openEleWaterEdit(scope.row.waterCode)" type="text" size="small">修改</el-button>
                                        <el-button v-if="ctStatus == 4 || ctStatus==5 || ctStatus==6" @click="openPayHistoryWindow(scope.row.waterCode, 3, scope.row.receivablePrice, scope.row.paidPrice)" type="text" size="small">缴费列表</el-button>
                                        <el-button v-if="showWaterFeePaymentBtn && ctStatus== 4" @click="openBox(scope.row.waterCode)" type="text" size="small">缴费办理</el-button>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                    </div>

                    <!-- 停车费框 -->
                    <div class="detail_box" v-show="isShowIndex == 5">
                        <!-- 内容框1 -->
                        <div v-if="!ctObj.parkingReceiptList || ctObj.parkingReceiptList.length <= 0">
                            <div class="contract_box_data">
                                <div class=" this_billing_title ">
                                    <div style="float: left">
                                        <span class="contract_title">暂无停车收费信息</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!--停车费新样子-->
                        <div v-else class="contract_box_data">
                            <el-table
                                    class="costTable"
                                    :data="ctObj.parkingReceiptList"
                                    border
                                    :highlight-current-row="true"
                                    size="small"
                                    height="500"
                                    :show-overflow-tooltip="false">
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="开始日期">
                                    <template slot-scope="scope">{{ scope.row.startTime  | timeDate}}</template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="结束日期">
                                    <template slot-scope="scope">{{ scope.row.endTime  | timeDate}}</template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="截止日期">
                                    <template slot-scope="scope">{{ scope.row.receivingDate  | timeDate}}</template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="200"
                                        prop="parkingSpace"
                                        label="车位数量(不含免)">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="200"
                                        prop="unitPrice"
                                        label="计费单价(元/个/月)">
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
                                        <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                                        <span v-if="!scope.row.hasTax">---</span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="receivablePrice"
                                        label="合计应收">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        prop="paidPrice"
                                        label="已收金额">
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="待收金额">
                                    <template slot-scope="scope">
                                        <span v-if="(scope.row.totalPrice - scope.row.paidPrice) >= 0">
                                            {{(scope.row.totalPrice - scope.row.paidPrice).toFixed(2)}}
                                        </span>
                                        <span v-else>0.00</span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="余额">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.paidPrice - scope.row.totalPrice > 0">
                                            {{(scope.row.paidPrice - scope.row.totalPrice).toFixed(2)}}
                                        </span>
                                        <span v-else>0.00</span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        min-width="100"
                                        label="状态">
                                    <template slot-scope="scope">
                                        <el-button v-if="scope.row.payStatus == 1" type="info" size="mini" round>待支付</el-button>
                                        <el-button v-if="scope.row.payStatus == 2" type="success"size="mini" round>已支付</el-button>
                                        <el-button v-if="scope.row.payStatus == 3" type="danger" size="mini" round>逾&nbsp;&nbsp;&nbsp;&nbsp;期</el-button>
                                        <el-button v-if="scope.row.payStatus == 4" type="warning" size="mini" round>未结清</el-button>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        header-align="center"
                                        fixed="right"
                                        label="操作"
                                        width="200">
                                    <template slot-scope="scope">
                                        <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6" @click="openEleWaterEdit(scope.row.parkingCode)" type="text" size="small">修改</el-button>
                                        <el-button v-if="ctStatus == 4 || ctStatus==5 || ctStatus==6" @click="openPayHistoryWindow(scope.row.parkingCode, 4, scope.row.totalPrice, scope.row.paidPrice)" type="text" size="small">缴费列表</el-button>
                                        <el-button v-if="showParkingPaymentBtn && ctStatus== 4" @click="openBox(scope.row.parkingCode)" type="text" size="small">缴费办理</el-button>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                    </div>


                    <!-- 保险费框 -->
                    <div class="detail_box" v-show="isShowIndex == 6">
                        <div class="common_detail_result_div">
                            <el-button class="add_cost_button" type="primary" size="small"
                                       @click="addPremiumWindow = true">添加保险费
                            </el-button>
                        </div>
                        <el-table class="costTable" :data="ctObj.premiumReceiptList" border
                                  :highlight-current-row="true" size="small" height="500">
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
                            <el-table-column header-align="center" min-width="100" label="状态">
                                <template slot-scope="scope">
                                    <el-button v-if="scope.row.payStatus == 1" type="info" size="mini" round>待支付</el-button>
                                    <el-button v-if="scope.row.payStatus == 2" type="success"size="mini" round>已支付</el-button>
                                    <el-button v-if="scope.row.payStatus == 3" type="danger" size="mini" round>逾&nbsp;&nbsp;&nbsp;&nbsp;期</el-button>
                                    <el-button v-if="scope.row.payStatus == 4" type="warning" size="mini" round>未结清</el-button>
                                </template>
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
                                    <span v-if="scope.row.hasTax">{{ scope.row.taxPointName }}</span>
                                    <span v-if="!scope.row.hasTax">---</span>
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
                                    min-width="100"
                                    prop="paidPrice"
                                    label="已收金额">
                            </el-table-column>
                            <el-table-column
                                    header-align="center"
                                    min-width="100"
                                    label="待收金额">
                                <template slot-scope="scope">
                                    <span v-if="(scope.row.totalPrice - scope.row.paidPrice) >= 0">
                                        {{(scope.row.totalPrice - scope.row.paidPrice).toFixed(2)}}
                                    </span>
                                    <span v-else>0.00</span>
                                </template>
                            </el-table-column>
                            <el-table-column header-align="center" min-width="100" label="余额">
                                <template slot-scope="scope">
                                    <span v-if="scope.row.paidPrice - scope.row.totalPrice > 0">
                                        {{(scope.row.paidPrice - scope.row.totalPrice).toFixed(2)}}
                                    </span>
                                    <span v-else>0.00</span>
                                </template>
                            </el-table-column>
                            <el-table-column header-align="center" fixed="right" label="操作" width="220">
                                <template slot-scope="scope">
                                    <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6" @click="openUpdatePremium(scope.row.premiumCode)" type="text" size="small">修改</el-button>
                                    <el-button v-if="ctStatus == 4 || ctStatus==5 || ctStatus==6" @click="openPayHistoryWindow(scope.row.premiumCode, 5, scope.row.totalPrice, scope.row.paidPrice)" type="text" size="small">缴费列表</el-button>
                                    <el-button v-if="showPremiumPaymentBtn && ctStatus== 4" @click="openPremiumPayWindow(scope.row.premiumCode)" type="text" size="small">缴费办理</el-button>
                                    <el-button v-if="ctStatus == 1 || ctStatus==5 || ctStatus==6" @click="deletePremium(scope.row.id, scope.row.premiumCode, scope.row.paidPrice)" type="text" size="small">删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                </div>

                <!-- 【添加保险费】弹窗 -->
                <el-dialog title="添加保险费" style="line-height: 30px;" :visible.sync="addPremiumWindow">
                    <el-form :model="addPremiumObj" :rules="premiumRules" ref="addPremiumObj" label-width="100px" style="text-align: left;">
                        <el-row :gutter="10" class="common-el-row-style">
                            <el-col :span="12">
                                <el-form-item label="开始日期" prop="premiumStartDate">
                                    <el-date-picker
                                            v-model="addPremiumObj.premiumStartDate"
                                            align="right"
                                            type="date">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="结束日期" prop="premiumEndDate">
                                    <el-date-picker
                                            v-model="addPremiumObj.premiumEndDate"
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
                                            v-model="addPremiumObj.deadline"
                                            align="right"
                                            type="date">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="投保金额" prop="premiumAmount">
                                    <el-input clearable v-model="addPremiumObj.premiumAmount">
                                        <template slot="append">元</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="应收保险费" prop="receivablePremium">
                                    <el-input clearable v-model="addPremiumObj.receivablePremium">
                                        <template slot="append">元</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :span="8">
                                <div class="grid-content" style="width: 360px;height: 65px;">
                                    <el-form-item label="是否含税" prop="hasTax">
                                        <el-radio-group v-model="addPremiumObj.hasTax">
                                            <el-radio :label=true>是</el-radio>
                                            <el-radio :label=false>否</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>

                            <el-col :span="8" v-if="addPremiumObj.hasTax==true">
                                <el-form-item label="税点" prop="taxPoint">
                                    <el-select v-model="addPremiumObj.taxPoint" placeholder="请选择">
                                        <el-option
                                                v-for="item in taxPoint"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>

                        </el-row>

                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="合计应收" prop="totalPrice">
                                    <el-input clearable v-model="addPremiumObj.totalPrice">
                                        <template slot="append">元</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="cancleAddPremium('addPremiumObj')">取 消</el-button>
                        <el-button type="primary" @click="premiumSubmit('addPremiumObj')">确 定</el-button>
                    </div>
                </el-dialog>

                <!--******************************************************************************************************************
                * 1.租金修改弹窗
                * 2.水、电、停车费修改弹窗
                * 3.保险费缴费弹窗
                *******************************************************************************************************************-->

                <!-- 租金修改弹窗 -->
                <el-dialog class="pay-box" title="修改租金缴费信息" :visible.sync="dialogFormEditPay">
                    <el-form :model="editPayment" ref="editPayment" :rules="rules" label-width="100px">
                        <el-row :gutter="10" class="common-el-row-style">
                            <el-col :span="12">
                                <el-form-item label="开始日期" prop="rentStartTime">
                                    <el-date-picker
                                            v-model="editPayment.rentStartTime"
                                            align="right"
                                            type="date">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="结束日期" prop="rentEndTime">
                                    <el-date-picker
                                            v-model="editPayment.rentEndTime"
                                            align="right"
                                            type="date">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row :gutter="10" >
                            <el-col :span="12">
                                <el-form-item label="截止日期" prop="receivingDate">
                                    <el-date-picker
                                            v-model="editPayment.receivingDate"
                                            align="right"
                                            type="date">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <div v-if="showDeposit">
                            <el-row>
                                <el-col :span="5">
                                    <div class="grid-content">
                                        <el-form-item label="电费押金：">
                                            {{ctObj.eleDeposit}}
                                        </el-form-item>
                                    </div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-content">
                                        <el-form-item label="水费押金：">
                                            {{ctObj.waterDeposit}}
                                        </el-form-item>
                                    </div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-content">
                                        <el-form-item label="工程押金：">
                                            {{ctObj.fpayProjectDeposit}}
                                        </el-form-item>
                                    </div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-content">
                                        <el-form-item label="保证金：">
                                            {{ctObj.fpayBond}}
                                        </el-form-item>
                                    </div>
                                </el-col>
                                <el-col :span="4">
                                    <div class="grid-content">
                                        <el-form-item label="保险费：">
                                            {{ctObj.fpayInsurancePrice}}
                                        </el-form-item>
                                    </div>
                                </el-col>
                            </el-row>
                        </div>

                        <el-row>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="租金：" prop="receivableRent">
                                        <el-input clearable v-model="editPayment.receivableRent"
                                                  @blur="rentTotalSum()"></el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content" style="width: 300px;height: 50px;">
                                    <el-form-item label="是否含税：" prop="hasTax">
                                        <el-radio-group v-model="editPayment.hasTax">
                                            <el-radio :label=true>是</el-radio>
                                            <el-radio :label=false>否</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content" v-if="editPayment.hasTax == true">
                                    <el-form-item label="税点：" prop="taxPoint">
                                        <el-select v-model="editPayment.taxPoint" placeholder="请选择">
                                            <el-option
                                                    v-for="item in taxPoint"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </el-form-item>
                                </div>
                            </el-col>

                        </el-row>

                        <el-row>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="物业费：" prop="propertyPrice">
                                        <el-input clearable v-model="editPayment.propertyPrice"
                                                  @blur="rentTotalSum()"></el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content" style="width: 300px;height: 50px;">
                                    <el-form-item label="是否含税：" prop="proHasTax">
                                        <el-radio-group v-model="editPayment.proHasTax">
                                            <el-radio :label=true>是</el-radio>
                                            <el-radio :label=false>否</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content" v-if="editPayment.proHasTax == true">
                                    <el-form-item label="税点：" prop="proTaxPoint">
                                        <el-select v-model="editPayment.proTaxPoint" placeholder="请选择">
                                            <el-option
                                                    v-for="item in taxPoint"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="应收租金：" prop="totalPrice">
                                        <el-input clearable v-model="editPayment.totalPrice"
                                                  @blur="receivableRentEdit(1)"></el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="已收租金：">
                                        {{editPayment.paidPrice}}元
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="上次缴费：">
                                        {{editPayment.realRent}}元
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content" style="height: 50px;">
                                    <el-form-item label="余额：" prop="overPrice">
                                        {{editPayment.overPrice}}元
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="15">
                                <div class="grid-content" style="width: 450px;height: 50px;">
                                    <el-form-item label="缴费状态：" prop="payStatus">

                                        <el-radio-group v-model="editPayment.payStatus">
                                            <el-radio label="1">待支付</el-radio>
                                            <el-radio label="2">已支付</el-radio>
                                            <el-radio label="3">逾期</el-radio>
                                            <el-radio label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <!--<el-radio-group v-model="editPayment.payStatus" v-if="editPayment.payStatus==2">-->
                                            <!--<el-radio disabled label="1">待支付</el-radio>-->
                                            <!--<el-radio label="2">已支付</el-radio>-->
                                            <!--<el-radio disabled label="3">逾期</el-radio>-->
                                            <!--<el-radio disabled label="4">未结清</el-radio>-->
                                        <!--</el-radio-group>-->

                                        <!--<el-radio-group v-model="editPayment.payStatus" v-if="editPayment.payStatus==3">-->
                                            <!--<el-radio disabled label="1">待支付</el-radio>-->
                                            <!--<el-radio disabled label="2">已支付</el-radio>-->
                                            <!--<el-radio label="3">逾期</el-radio>-->
                                            <!--<el-radio disabled label="4">未结清</el-radio>-->
                                        <!--</el-radio-group>-->

                                        <!--<el-radio-group v-model="editPayment.payStatus" v-if="editPayment.payStatus==4">-->
                                            <!--<el-radio disabled label="1">待支付</el-radio>-->
                                            <!--<el-radio disabled label="2">已支付</el-radio>-->
                                            <!--<el-radio disabled label="3">逾期</el-radio>-->
                                            <!--<el-radio label="4">未结清</el-radio>-->
                                        <!--</el-radio-group>-->
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormEditPay = false">取 消</el-button>
                        <el-button type="primary" @click="submitEditForm('editPayment')">修 改</el-button>
                    </div>
                </el-dialog>

                <!-- 水、电、停车费修改弹窗 -->
                <el-dialog class="pay-box" title="修改缴费信息" :visible.sync="dialogEelWaterFormEditPay">
                    <el-form :model="editEleWaterPayment" ref="editEleWaterPayment" :rules="rules" label-width="100px">
                        <el-row :gutter="20">
                            <el-col>
                                <div class="grid-content">
                                    <el-form-item label="本期时间：">
                                        <el-date-picker
                                                v-model="eleWaterTimeList"
                                                type="daterange"
                                                align="right"
                                                size="small"
                                                unlink-panels
                                                :clearable="false"
                                                range-separator="至"
                                                start-placeholder="开始日期"
                                                end-placeholder="结束日期"
                                                disabled>
                                        </el-date-picker>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="应收金额：" prop="receivablePrice">
                                        <el-input clearable v-model="editEleWaterPayment.receivablePrice"
                                                  @blur="receivableRentEdit(2)"></el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="已收金额：">
                                        {{editEleWaterPayment.paidPrice}}元
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="上次缴费：">
                                        {{editEleWaterPayment.realPrice}}元
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :span="8">
                                <div class="grid-content" style="height: 50px;">
                                    <el-form-item label="余额：">
                                        {{editEleWaterPayment.overPrice}}元
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="15">
                                <div class="grid-content" style="width: 450px;height: 50px;">
                                    <el-form-item label="缴费状态：" prop="payStatus">

                                        <el-radio-group v-model="editEleWaterPayment.payStatus" v-if="editEleWaterPayment.payStatus==1">
                                            <el-radio label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="editEleWaterPayment.payStatus" v-if="editEleWaterPayment.payStatus==2">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="editEleWaterPayment.payStatus" v-if="editEleWaterPayment.payStatus==3">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="editEleWaterPayment.payStatus" v-if="editEleWaterPayment.payStatus==4">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio label="4">未结清</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogEelWaterFormEditPay = false">取 消</el-button>
                        <el-button type="primary" @click="submitEleWaterForm('editEleWaterPayment')">确 定</el-button>
                    </div>
                </el-dialog>

                <!-- 保险费修改弹窗 -->
                <el-dialog style="line-height: 30px;" title="修改保险缴费信息" :visible.sync="holdPremiumEditPaymentWindow">
                    <el-form :model="editPremiumObj" ref="editPremiumObj" :rules="premiumRules" label-width="110px" style="text-align: left;">
                        <el-row :gutter="20">
                            <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                <el-form-item label="开始日期" prop="premiumStartDate">
                                    <el-date-picker v-model="editPremiumObj.premiumStartDate" align="right" type="date"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                <el-form-item label="结束日期" prop="premiumEndDate">
                                    <el-date-picker v-model="editPremiumObj.premiumEndDate" align="right" type="date"></el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                <el-form-item label="截止日期" prop="deadline">
                                    <el-date-picker v-model="editPremiumObj.deadline" align="right" type="date"></el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                <div class="grid-content">
                                    <el-form-item label="投保金额：" prop="premiumAmount">
                                        <el-input clearable v-model="editPremiumObj.premiumAmount">
                                            <template slot="append">元</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                <div class="grid-content">
                                    <el-form-item label="应收保险费：" prop="receivablePremium">
                                        <el-input clearable v-model="editPremiumObj.receivablePremium"
                                                  @blur="receivableRentEdit(3)">
                                            <template slot="append">元</template>
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <div class="grid-content">
                                    <el-form-item label="已收保险费：">
                                        {{editPremiumObj.paidPrice}}元
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <div class="grid-content">
                                    <el-form-item label="上次缴费：">
                                        {{editPremiumObj.thePayment}}元
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                <div class="grid-content" style="height: 50px;">
                                    <el-form-item label="余额：" prop="overPrice">
                                        {{editPremiumObj.overPrice}}元
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :span="15">
                                <div class="grid-content" style="width: 450px;height: 50px;">
                                    <el-form-item label="缴费状态：" prop="payStatus">
                                        <el-radio-group v-model="editPremiumObj.payStatus" v-if="editPremiumObj.payStatus==1">
                                            <el-radio label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="editPremiumObj.payStatus" v-if="editPremiumObj.payStatus==2">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="editPremiumObj.payStatus" v-if="editPremiumObj.payStatus==3">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="editPremiumObj.payStatus" v-if="editPremiumObj.payStatus==4">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio label="4">未结清</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="holdPremiumEditPaymentWindow = false">取 消</el-button>
                        <el-button type="primary" @click="updatePremium('editPremiumObj')">修 改</el-button>
                    </div>
                </el-dialog>

                <!--******************************************************************************************************************
                * 1.租金缴费弹窗
                * 2.水、电、停车费缴费弹窗
                * 3.保险费缴费弹窗
                * 4.缴费列表弹窗
                *******************************************************************************************************************-->

                <!-- 租金缴费弹窗 -->
                <el-dialog title="租金缴费办理" style="line-height: 30px;" :visible.sync="holdRentWindow" width="70%" top="6vh">
                    <el-form :model="paymentInput" ref="paymentInput" :rules="rules" label-width="110px" style="text-align: left;">
                        <el-row :gutter="20">
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="应收金额：">
                                        <span class="bd-info-value">{{paymentObj.payableData}}元</span>
                                        <span style="color: red;" v-if="paymentInput.payStatus==3">(已包含滞纳金)</span>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="已收金额：">
                                        <span class="bd-info-value">{{paymentObj.paidPriceData}}元</span>
                                        <!--租金已收-->
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="待收金额：">
                                        <span class="bd-info-value">{{paymentObj.pendingPayment}}元</span>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20" v-if="paymentInput.payStatus==3">
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="逾期天数：">
                                        <span style="color: red;">{{paymentInput.overdueDays}}天</span>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="滞纳金：">
                                        <span style="color: red;">{{paymentInput.latePayment}}元</span>
                                        <!--租金已收-->
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :span="10">
                                <div class="grid-content" style="width: 750px;height: 50px;">
                                    <el-form-item label="缴费金额：" prop="realRent">
                                        <el-input clearable style="width: 200px;"
                                                  v-model="paymentInput.realRent"
                                                  @blur="autoBalance()">
                                        </el-input>
                                        <el-button v-if="paymentInput.payStatus == 2" size="small" icon="el-icon-circle-check" style="color: green;font-size: 18px;"></el-button>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="10">
                                <div class="grid-content" style="width: 750px;height: 50px;">
                                    <el-form-item label="优惠金额：" prop="discountsPrice">
                                        <el-input clearable style="width: 200px;"
                                                  v-model="paymentInput.discountsPrice"
                                                  @blur="autoBalance()">
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row>
                            <div v-if="hasBalance">
                                <el-col :span="10">
                                    <div class="grid-content" style="width: 450px;height: 50px;">
                                        <el-form-item label="余额：" prop="balance">
                                            <el-input clearable style="width: 200px;" v-model="paymentInput.balance"></el-input>
                                        </el-form-item>
                                    </div>
                                </el-col>
                            </div>
                        </el-row>

                        <el-row style="margin-top: 15px;height: 50px;">
                            <el-col :span="8">
                                <el-form-item label="缴费时间：" prop="payDate">
                                    <el-date-picker
                                            v-model="paymentInput.payDate"
                                            type="datetime"
                                            placeholder="选择日期时间"
                                            default-time="00:00:00">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <!--<el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="8">
                                <div class="grid-content" style="width: 400px;height: 50px;">
                                    <el-form-item label="租金是否含税：" prop="hasTax" label-width="140px">
                                        <el-radio-group v-model="paymentInput.hasTax">
                                            <el-radio :label=true>是</el-radio>
                                            <el-radio :label=false>否</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>

                            <el-col :span="8" v-if="paymentInput.hasTax==true">
                                <el-form-item label="税点：" prop="taxPoint">
                                    <el-select v-model="paymentInput.taxPoint" placeholder="请选择">
                                        <el-option
                                                v-for="item in taxPoint"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>

                        </el-row>

                        <el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="8">
                                <div class="grid-content" style="width: 360px;height: 50px;">
                                    <el-form-item label="物业费是否含税：" prop="proHasTax" label-width="140px">
                                        <el-radio-group v-model="paymentInput.proHasTax">
                                            <el-radio :label=true>是</el-radio>
                                            <el-radio :label=false>否</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>

                            <el-col :span="8" v-if="paymentInput.proHasTax==true">
                                <el-form-item label="税点：" prop="proTaxPoint">
                                    <el-select v-model="paymentInput.proTaxPoint" placeholder="请选择">
                                        <el-option
                                                v-for="item in taxPoint"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>

                        </el-row>-->

                        <el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="24">
                                <div class="grid-content" style="width: 450px;height: 50px;">
                                    <el-form-item label="缴费状态：" prop="payStatus">

                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==1">
                                            <el-radio label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==2">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==3">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==4">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio label="4">未结清</el-radio>
                                        </el-radio-group>

                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="24">
                                <div class="grid-content" style="margin-top: 15px; width: 750px;height: 50px;">
                                    <el-form-item label="备注：" prop="remark">
                                        <el-input clearable style="width: 400px;"
                                                  v-model="paymentInput.remark" type="textarea">
                                        </el-input>
                                    </el-form-item>
                                </div>

                            </el-col>
                        </el-row>
                        <el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="24">
                                <el-form-item label="">
                                    <span style="color: green;font-size: 18px;font-weight: bold;float: right;margin-right: 20px;">本次共收:{{paymentInput.allPay}}元</span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="restForm('paymentInput')">取 消</el-button>
                        <el-button type="primary" @click="rentSubmit('paymentInput')">提 交</el-button>
                    </div>
                </el-dialog>


                <!-- 【水、电、停车费】缴费弹窗 -->
                <el-dialog class="pay-box" title="缴费办理" :visible.sync="dialogFormVisible" width="70%" top="6vh">
                    <el-form :model="paymentInput" ref="paymentInput" :rules="rules" label-width="110px">
                        <el-row :gutter="20">
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="应收金额：">
                                        <span v-if="markListCode" class="bd-info-value">{{paymentObj.payableData}}元</span>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="已收金额：">
                                        <span v-if="markListCode" class="bd-info-value">{{paymentObj.paidPriceData}}元</span>
                                        <!--租金已收-->
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="待收金额：">
                                        <span class="bd-info-value">{{paymentObj.pendingPayment}}元</span>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :span="24">
                                <div class="grid-content" style="width: 750px;height: 50px;"
                                     v-if="paymentInput.payStatus != 2">
                                    <el-form-item label="缴费金额：" prop="realPrice">
                                        <el-input clearable style="width: 200px;"
                                                  v-model="paymentInput.realPrice"
                                                  @blur="autoBalanceEWP"></el-input>
                                        <span style="color: red;">&nbsp;&nbsp;（该值不含税）</span>
                                    </el-form-item>
                                </div>

                                <div class="grid-content" style="width: 750px;height: 50px;"
                                     v-if="paymentInput.payStatus == 2">
                                    <el-form-item label="缴费金额：" prop="realPrice">
                                        <el-input disabled style="width: 200px;"
                                                  v-model="paymentInput.realPrice"
                                                  @blur="autoBalanceEWP"></el-input>
                                        <span style="color: red;">&nbsp;&nbsp;费用已支付</span>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row>
                            <div v-if="hasBalance">
                                <el-col :span="10">
                                    <div class="grid-content" style="width: 450px;height: 50px;">
                                        <el-form-item label="余额：" prop="balance">
                                            <el-input clearable style="width: 200px;"
                                                      v-model="paymentInput.balance"></el-input>
                                        </el-form-item>
                                    </div>
                                </el-col>
                            </div>
                        </el-row>

                        <el-row style="margin-top: 15px;height: 50px;">
                            <el-col :span="8">
                                <el-form-item label="缴费时间：" prop="payDate">
                                    <el-date-picker
                                            v-model="paymentInput.payDate"
                                            type="datetime"
                                            placeholder="选择日期时间"
                                            default-time="00:00:00">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <!--<el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="8">
                                <div class="grid-content" style="width: 360px;height: 50px;">
                                    <el-form-item label="是否含税：" prop="hasTax">
                                        <el-radio-group v-model="paymentInput.hasTax">
                                            <el-radio :label=true>是</el-radio>
                                            <el-radio :label=false>否</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>

                            <el-col :span="8" v-if="paymentInput.hasTax==true">
                                <el-form-item label="税点：" prop="taxPoint">
                                    <el-select v-model="paymentInput.taxPoint" placeholder="请选择">
                                        <el-option
                                                v-for="item in taxPoint"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>

                        </el-row>-->

                        <el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="14">
                                <div class="grid-content" style="width: 450px;height: 50px;">
                                    <el-form-item label="缴费状态：" prop="payStatus">
                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==1">
                                            <el-radio label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==2">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==3">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==4">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio label="4">未结清</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="24">
                                <div class="grid-content" style="margin-top: 15px; width: 750px;height: 50px;">
                                    <el-form-item label="备注：" prop="remark">
                                        <el-input clearable style="width: 400px;"
                                                  v-model="paymentInput.remark" type="textarea">
                                        </el-input>
                                    </el-form-item>
                                </div>

                            </el-col>
                        </el-row>
                        <el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="24">
                                <el-form-item label="">
                                    <span style="color: green;font-size: 18px;font-weight: bold;float: right;margin-right: 20px;">本次共收:{{paymentInput.allPay}}元</span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="restForm('paymentInput')">取 消</el-button>
                        <el-button type="primary" @click="submitForm('paymentInput')">提 交</el-button>
                    </div>
                </el-dialog>


                <!-- 保险费缴费弹窗 -->
                <el-dialog title="保险费缴费办理" style="line-height: 30px;" :visible.sync="holdPremiumPaymentWindow" width="70%" top="6vh">
                    <el-form :model="paymentInput" ref="paymentInput" :rules="premiumRules" label-width="110px" style="text-align: left;">
                        <el-row :gutter="20">
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="应收金额：">
                                        <span v-if="markListCode" class="bd-info-value">{{paymentObj.payableData}}&nbsp;元</span>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="已收金额：">
                                        <span v-if="markListCode" class="bd-info-value">{{paymentObj.paidPriceData}}&nbsp;元</span>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="待收金额：">
                                        <span class="bd-info-value">{{paymentObj.pendingPayment}}&nbsp;元</span>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :span="24">
                                <div class="grid-content" style="width: 750px;height: 50px;"
                                     v-if="paymentInput.payStatus != 2">
                                    <el-form-item label="缴费金额：" prop="thePayment">
                                        <el-input clearable style="width: 200px;"
                                                  v-model="paymentInput.thePayment"
                                                  @blur="autoBalancePremium"></el-input>
                                    </el-form-item>
                                </div>

                                <div class="grid-content" style="width: 750px;height: 50px;"
                                     v-if="paymentInput.payStatus == 2">
                                    <el-form-item label="缴费金额：" prop="thePayment">
                                        <el-input v-model="paymentInput.thePayment"
                                                  @blur="autoBalancePremium"
                                                  disabled style="width: 200px;">
                                        </el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row>
                            <div v-if="hasBalance">
                                <el-col :span="10">
                                    <div class="grid-content" style="width: 450px;height: 50px;">
                                        <el-form-item label="余额：" prop="balance">
                                            <el-input clearable style="width: 200px;"
                                                      v-model="paymentInput.balance"></el-input>
                                        </el-form-item>
                                    </div>
                                </el-col>
                            </div>
                        </el-row>

                        <el-row style="margin-top: 15px;height: 50px;">
                            <el-col :span="8">
                                <el-form-item label="缴费时间：" prop="payDate">
                                    <el-date-picker
                                            v-model="paymentInput.payDate"
                                            type="datetime"
                                            placeholder="选择日期时间"
                                            default-time="00:00:00">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <!--<el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="8">
                                <div class="grid-content" style="width: 360px;height: 50px;">
                                    <el-form-item label="是否含税：" prop="hasTax">
                                        <el-radio-group v-model="paymentInput.hasTax">
                                            <el-radio :label=true>是</el-radio>
                                            <el-radio :label=false>否</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>

                            <el-col :span="8" v-if="paymentInput.hasTax==true">
                                <el-form-item label="税点：" prop="taxPoint">
                                    <el-select v-model="paymentInput.taxPoint" placeholder="请选择">
                                        <el-option
                                                v-for="item in taxPoint"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>

                        </el-row>-->

                        <el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="14">
                                <div class="grid-content" style="width: 450px;height: 50px;">
                                    <el-form-item label="缴费状态：" prop="payStatus">
                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==1">
                                            <el-radio label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==2">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==3">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio label="3">逾期</el-radio>
                                            <el-radio disabled label="4">未结清</el-radio>
                                        </el-radio-group>

                                        <el-radio-group v-model="paymentInput.payStatus" v-if="paymentInput.payStatus==4">
                                            <el-radio disabled label="1">待支付</el-radio>
                                            <el-radio disabled label="2">已支付</el-radio>
                                            <el-radio disabled label="3">逾期</el-radio>
                                            <el-radio label="4">未结清</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="24">
                                <div class="grid-content" style="margin-top: 15px; width: 750px;height: 50px;">
                                    <el-form-item label="备注：" prop="remark">
                                        <el-input clearable style="width: 400px;"
                                                  v-model="paymentInput.remark" type="textarea">
                                        </el-input>
                                    </el-form-item>
                                </div>

                            </el-col>
                        </el-row>
                        <el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="24">
                                <el-form-item label="">
                                    <span style="color: green;font-size: 18px;font-weight: bold;float: right;margin-right: 20px;">本次共收:{{paymentInput.allPay}}元</span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="cancleEditPremium('paymentInput')">取 消</el-button>
                        <el-button type="primary" @click="submitPremiumForm('paymentInput')">提 交</el-button>
                    </div>
                </el-dialog>


                <!--缴费记录弹窗-->
                <el-dialog :visible.sync="holdPayHistory" width="70%">
                    <div v-if="markCostType == 1" style="text-align: left;">
                        <p style="font-size: 20px;margin-top: -30px;">租金缴费记录</p>
                        <span style="margin-left: 20px;">租金合计应收：{{payHistoryTotalPrice}}&nbsp;元</span>
                        <span style="margin-left: 20px;color: red">租金待收金额：{{payHistoryPendingPrice}}&nbsp;元</span>
                    </div>
                    <div v-if="markCostType == 2" style="text-align: left;">
                        <p style="font-size: 20px;margin-top: -30px;">电费缴费记录</p>
                        <span style="margin-left: 20px;">电费合计应收：{{payHistoryTotalPrice}}&nbsp;元</span>
                        <span style="margin-left: 20px;color: red">电费待收金额：{{payHistoryPendingPrice}}&nbsp;元</span>
                    </div>
                    <div v-if="markCostType == 3" style="text-align: left;">
                        <p style="font-size: 20px;margin-top: -30px;">水费缴费记录</p>
                        <span style="margin-left: 20px;">水费合计应收：{{payHistoryTotalPrice}}&nbsp;元</span>
                        <span style="margin-left: 20px;color: red">水费待收金额：{{payHistoryPendingPrice}}&nbsp;元</span>
                    </div>
                    <div v-if="markCostType == 4" style="text-align: left;">
                        <p style="font-size: 20px;margin-top: -30px;">停车费缴费记录</p>
                        <span style="margin-left: 20px;">停车费合计应收：{{payHistoryTotalPrice}}&nbsp;元</span>
                        <span style="margin-left: 20px;color: red">停车费待收金额：{{payHistoryPendingPrice}}&nbsp;元</span>
                    </div>
                    <div v-if="markCostType == 5" style="text-align: left;">
                        <p style="font-size: 20px;margin-top: -30px;">保险费缴费记录</p>
                        <span style="margin-left: 20px;">保险费合计应收：{{payHistoryTotalPrice}}&nbsp;元</span>
                        <span style="margin-left: 20px;color: red">保险费待收金额：{{payHistoryPendingPrice}}&nbsp;元</span>
                    </div>

                    <el-table :data="payHistoryList"
                              border
                              :summary-method="getSummaries"
                              show-summary
                              style="width: 100%; margin-top: 20px">
                        <el-table-column
                                align="center"
                                label="序号"
                                type="index"
                                width="50px">
                        </el-table-column>
                        <el-table-column align="center" min-width="50px" label="支付时间" show-overflow-tooltip>
                            <template slot-scope="scope">{{ scope.row.payDate | time}}</template>
                        </el-table-column>

                        <el-table-column align="center" min-width="25" label="是否含税">
                            <template slot-scope="scope">
                                <span v-if="scope.row.hasTax">是</span>
                                <span v-if="!scope.row.hasTax">否</span>
                            </template>
                        </el-table-column>

                        <el-table-column align="center" min-width="25" label="税率 %">
                            <template slot-scope="scope">{{(scope.row.hasTax && scope.row.taxPoint != null) ? scope.row.taxPointName : "---"}}&nbsp;</template>
                        </el-table-column>

                        <el-table-column align="center" min-width="25" label="物业是否含税" v-if="markCostType == 1">
                            <template slot-scope="scope">
                                <span v-if="scope.row.proHasTax">是</span>
                                <span v-if="!scope.row.proHasTax">否</span>
                            </template>
                        </el-table-column>

                        <el-table-column align="center" min-width="25" label="税率 %" prop="taxPoint" v-if="markCostType == 1">
                            <template slot-scope="scope">{{(scope.row.proHasTax && scope.row.proTaxPoint != null) ? scope.row.proTaxPointName : "---"}}&nbsp;</template>
                        </el-table-column>

                        <el-table-column align="center" min-width="50px" label="本次付款" prop="realRent">
                        </el-table-column>

                        <el-table-column align="center" min-width="50px" label="最后操作人" prop="lastUpdateName">
                        </el-table-column>

                        <el-table-column align="center" min-width="50px" label="最后操作时间">
                            <template slot-scope="scope">{{ scope.row.lastUpdateTime | time}}</template>
                        </el-table-column>

                        <el-table-column align="center" min-width="50px" label="备注" prop="remark">
                        </el-table-column>

                        <!--<el-table-column align="center" min-width="25" label="共收金额" prop="paidPrice">-->
                        <!--</el-table-column>-->

                        <!--<el-table-column align="center" v-if="markCostType == 1" min-width="25px" label="待收金额" prop="pendingPayment">-->
                        <!--<template slot-scope="scope">{{scope.row.paidPrice > payHistoryTotalPrice ? 0 : (payHistoryTotalPrice - scope.row.paidPrice).toFixed(2)}}</template>-->
                        <!--</el-table-column>-->

                        <!--<el-table-column align="center" v-if="markCostType == 2" min-width="25px" label="待收金额" prop="pendingPayment">-->
                        <!--<template slot-scope="scope">{{scope.row.paidPrice > payHistoryTotalPrice ? 0 : (payHistoryTotalPrice - scope.row.paidPrice).toFixed(2)}}</template>-->
                        <!--</el-table-column>-->

                        <!--<el-table-column align="center" v-if="markCostType == 3" min-width="25px" label="待收金额" prop="pendingPayment">-->
                        <!--<template slot-scope="scope">{{scope.row.paidPrice > payHistoryTotalPrice ? 0 : (payHistoryTotalPrice - scope.row.paidPrice).toFixed(2)}}</template>-->
                        <!--</el-table-column>-->

                        <!--<el-table-column align="center" v-if="markCostType == 4" min-width="25px" label="待收金额" prop="pendingPayment">-->
                        <!--<template slot-scope="scope">{{scope.row.paidPrice > payHistoryTotalPrice ? 0 : (payHistoryTotalPrice - scope.row.paidPrice).toFixed(2)}}</template>-->
                        <!--</el-table-column>-->

                        <!--<el-table-column align="center" min-width="25px" label="余额" prop="balance">-->
                        <!--<template slot-scope="scope">{{scope.row.paidPrice > payHistoryTotalPrice ? (scope.row.paidPrice - payHistoryTotalPrice).toFixed(2) : 0}}</template>-->
                        <!--</el-table-column>-->
                        <el-table-column
                                header-align="center"
                                label="操作"
                                width="90">
                            <template slot-scope="scope">
                                <el-button v-if="showRentPaymentBtn" @click="editPayHisInfo(scope.row)" type="text" size="small">修改</el-button>
                                <el-button v-if="showRentPaymentBtn" @click="delPayHisInfo(scope.row.id)" type="text" size="small">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-dialog>

                <!-- 修改租金缴费记录 -->
                <el-dialog title="修改缴费记录" :visible.sync="showPayHistoryWindow" width="70%">
                    <el-form :model="payHistoryObj" ref="payHistoryObj" :rules="rules" label-width="110px" style="text-align: left;">
                        <el-row :gutter="20">
                            <el-col :span="8">
                                <div class="grid-content">
                                    <el-form-item label="应收金额：">
                                        <span class="bd-info-value">{{payHistoryObj.receivableRent}}&nbsp;元</span>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20">
                            <el-col :span="24">
                                <div class="grid-content" style="width: 750px;height: 50px;">
                                    <el-form-item label="缴费金额：" prop="realRent">
                                        <el-input clearable style="width: 200px;"
                                                  v-model="payHistoryObj.realRent"></el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row style="margin-top: 15px;height: 50px;">
                            <el-col :span="8">
                                <el-form-item label="缴费时间：" prop="payDate">
                                    <el-date-picker
                                            v-model="payHistoryObj.payDate"
                                            type="datetime"
                                            placeholder="选择日期时间"
                                            default-time="00:00:00">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20" style="margin-top: 15px;height: 50px;">
                            <el-col :span="8">
                                <div v-if="payHistoryObj.costType == '1'" class="grid-content" style="width: 360px;height: 50px;">
                                    <el-form-item label="租金是否含税：" prop="hasTax" label-width="140px">
                                        <el-radio-group v-model="payHistoryObj.hasTax">
                                            <el-radio :label=true>是</el-radio>
                                            <el-radio :label=false>否</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                                <div v-else class="grid-content" style="width: 360px;height: 50px;">
                                    <el-form-item label="是否含税：" prop="hasTax">
                                        <el-radio-group v-model="payHistoryObj.hasTax">
                                            <el-radio :label=true>是</el-radio>
                                            <el-radio :label=false>否</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>

                            </el-col>

                            <el-col :span="8" v-if="payHistoryObj.hasTax==true">
                                <el-form-item label="税点：" prop="taxPoint">
                                    <el-select v-model="payHistoryObj.taxPoint" placeholder="请选择">
                                        <el-option
                                                v-for="item in taxPoint"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row :gutter="20" style="margin-top: 15px;height: 50px;" v-if="payHistoryObj.costType == '1'">
                            <el-col :span="8">
                                <div class="grid-content" style="width: 360px;height: 50px;">
                                    <el-form-item label="物业是否含税：" prop="proHasTax" label-width="140px">
                                        <el-radio-group v-model="payHistoryObj.proHasTax">
                                            <el-radio :label=true>是</el-radio>
                                            <el-radio :label=false>否</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>

                            <el-col :span="8" v-if="payHistoryObj.proHasTax==true">
                                <el-form-item label="税点：" prop="proTaxPoint">
                                    <el-select v-model="payHistoryObj.proTaxPoint" placeholder="请选择">
                                        <el-option
                                                v-for="item in taxPoint"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row>
                            <el-col :span="24">
                                <div class="grid-content" style="margin-top: 15px; width: 750px;height: 50px;">
                                    <el-form-item label="备注：" prop="remark">
                                        <el-input clearable style="width: 400px;"
                                                  v-model="payHistoryObj.remark" type="textarea">
                                        </el-input>
                                    </el-form-item>
                                </div>

                            </el-col>
                        </el-row>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="rentHisRestForm(payHistoryObj)">取 消</el-button>
                        <el-button type="primary" @click="rentHisEditSubmit(payHistoryObj)">修 改</el-button>
                    </div>
                </el-dialog>

            </div>
        </el-form>

    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        //接受父组件传过来的参数
        props: ['paramsObj'],

        components: {
            ElCol,
            ElRow
        },
        created: function () {
            this.isShowIndex = 1;

            let vm = this;

            // 菜单和按钮权限控制
            vm.showRentPaymentBtn = vm.$flagMenuStore.judgeMenu("rentPaymentBtn");
            vm.showElectricityFeePaymentBtn = vm.$flagMenuStore.judgeMenu("electricityFeePaymentBtn");
            vm.showWaterFeePaymentBtn = vm.$flagMenuStore.judgeMenu("waterFeePaymentBtn");
            vm.showParkingPaymentBtn = vm.$flagMenuStore.judgeMenu("parkingPaymentBtn");
            vm.showPremiumPaymentBtn = vm.$flagMenuStore.judgeMenu("premiumPaymentBtn");
            vm.bigEditReceipt = vm.$flagMenuStore.judgeMenu("bigEditReceipt");

            if(vm.paramsObj && vm.paramsObj.ctStatus) {
                vm.ctStatus = vm.paramsObj.ctStatus;
            }

            if(!vm.paramsObj.ctCode) {
                vm.$alert('页面：传入合同编号错误', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
                return;
            }

            // 页面加载初始化方法
            vm.initLoad();
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
            // 校验数字只能是正整数或小数
            let checkFushuIntOrFloat = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^(\-?)[0-9]+([.]{1}[0-9]+){0,1}$/.test(value))) {
                        callback(new Error('只能输入正整数或小数'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            // 校验数字只能为整数
            let checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[1-9]\d*$/.test(value))) {
                        callback(new Error('楼层数只能为正整数'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            return {
                ctStatus: '',// 合同状态

                // 权限控制
                showRentPaymentBtn: false,// 租赁缴费权限控制
                showElectricityFeePaymentBtn: false,// 电费缴费权限控制
                showWaterFeePaymentBtn: false,// 水费缴费权限控制
                showParkingPaymentBtn: false,// 停车缴费权限控制
                showPremiumPaymentBtn: false,// 保险费缴费权限控制
                bigEditReceipt: false,// 审核通过合同租金修改权限，只是在修正数据阶段放给周江怡、后倩云和何智栋，使用完关闭该角色

                // 弹窗打开控制
                holdRentWindow: false,// 租金缴费窗口
                dialogFormEditPay: false,// 租金缴费修改弹窗
                showDeposit: false,//是否显示首笔款信息
                fPayAllPrice: 0,//首笔款总计

                dialogFormVisible: false,// 水、电、停车费缴费弹窗显示
                dialogEelWaterFormEditPay: false,// 水、电、停车缴费修改弹窗

                holdPremiumPaymentWindow: false,// 保险费缴费弹窗
                holdPremiumEditPaymentWindow: false,// 保险费缴费修改弹窗

                addRentReceipt: false,// 控制添加租金弹窗
                addPremiumWindow: false,// 控制添加保险费弹窗
                rentPayStatus: "",//租金记录原来状态
                eleWaterParkPayStatus:"",//水、电、停车记录原来状态
                premiumPayStatus: "",//保费记录原来状态

                ctObj: {
                    ctCode: "",
                    pkCode:"",
                    rentThisReceipt: [],//本期租赁
                    eleThisReceipt: [],//本期电费
                    waterThisReceipt: [],//本期水费
                    parkingThisReceipt: [],//本期停车费
                    premiumThisReceipt: [],// 本期保险费
                },

                // 租金修改弹窗表单数据
                editPayment: {
                    id: "",
//                    rentTimeList: [],// 费用修改:租金日期段
                    rentStartTime: "",// 租金开始日
                    rentEndTime: "",// 租金结束日
                    receivingDate: "",// 截止日期
                    totalPrice: "",// 合计应收金额
                    receivableRent: "",// 应收租金
                    realRent: "",// 已收租金
                    paidPrice: "",// 已收款
                    overPrice: "",// 余额
                    hasTax: false,// 租金是否含税
                    taxPoint: "",// 租金税点
                    taxPrice: "",// 税金
                    proHasTax: false,// 物业费是否含税
                    tproTaxPoint: "",// 物业费税点
                    payStatus: "",// 缴费状态
                    propertyPrice: '',// 物业费
                },
                // 水、电、停车费修改弹窗表单数据
                editEleWaterPayment: {
                    id: "",
                    lastBillingTime: "",//开始日
                    currentBillingTime: "",//结束日
                    receivablePrice: "",//合计应收金额
                    basicElectricityPriceByMonth: "",//每月基础电费
                    electricityPrice: "",//用电费用
                    realPrice: "",//本次缴费
                    paidPrice: "",//已收款
                    overPrice: "",//余额
                    payStatus: ""//缴费状态
                },
                // 保险费修改弹窗表单数据
                editPremiumObj: {
                    premiumStartDate: '',// 开始日期
                    premiumEndDate: '',// 结束日期
                    deadline: '',// 截至日期
                },

                hasBalance: "",//缴费页面是否有余额
                isShowIndex: false,//控制显示
                changeData: "",//租金、电费、水费、停车费缴纳切换
                eleWaterTimeList: [],//应收修改:水电停车费日期段
                rentTimeListInit: [],//应收修改:租金日期段(记录从库中查询出来的日期段)
                eleWaterTimeListInit: [],//应收修改:水电停车费日期段(记录从库中查询出来的日期段)

                rentIncList: [],//租金应收递增详情集合

                // 应缴应收显示
                paymentObj: {
                    payableData: "",// 应收金额
                    paidPriceData: "",// 已收金额
                    pendingPayment: "",// 待收金额
                },
                // 缴费数据提交
                paymentInput: {
                    id: "",
                    rentCode: "",// 应收编码
                    ctCode: "",// 合同编号
                    paidPrice: "",// 已收金额（每期缴费金额之和）
                    realRent: "",// 缴费金额（本次缴费金额）
                    realPrice: "",// 水、电、停车费实收金额
                    payDate: "",// 缴费时间
                    hasTax: false,// 租金是否含税
                    taxPoint: "",// 税率，值为1、2、3、4、5
                    taxPrice: "",// 税金
                    proHasTax: false,//物业费是否含税
                    proTaxPoint: "",//物业费税点
                    payStatus: "",// 支付状态
                    balance: "",// 余额
                    allPay: 0,//本次共缴
                    remark: "", //备注

                    overdueDays: 0,//逾期天数
                    latePayment: 0,//滞纳金

                    discountsPrice: 0,//优惠金额
                },
                markListCode: "",//记录List中的Code
                markCostType: "",//记录修改的种类:1.租金 2.电费 3.水费 4.停车费 5.保险费
                flag: false,// 公共标记字段

                // 缴费记录参数
                holdPayHistory: false,// 控制缴费历史记录窗口
                payHistoryList: [
                    {
                        id:"",
                        payCode:'',//收款记录Code
                        code: '',// 应收租金、电费、水费、停车费Code
                        costType: '',// 应收类型
                        payDate: '',// 支付时间
                        receivableRent: '',// 应收金额
                        paidPrice: '',// 总支付金额
                        realRent: '',// 本次缴费
                        hasTax: false,//是否含税
                        taxPoint: '',// 税点
                        proHasTax: false,// 物业费是否含税
                        proTaxPoint: "",// 物业费税点
                        pendingPayment: '',// 待收金额
                    },
                ],

                //用于控制修改缴费记录弹窗
                showPayHistoryWindow: false,
                //缴费记录数据对象
                payHistoryObj: {
                    id:"",
                    payCode:'',//收款记录Code
                    code: '',// 应收租金、电费、水费、停车费Code
                    costType: '',// 应收类型
                    payDate: '',// 支付时间
                    receivableRent: '',// 应收金额
                    paidPrice: '',// 总支付金额
                    realRent: '',// 本次缴费
                    hasTax: false,//是否含税
                    proHasTax: false,// 物业费是否含税
                    proTaxPoint: "",// 物业费税点
                    taxPoint: '',// 税点
                    pendingPayment: '',// 待收金额
                    remark:'',//备注
                },

                markTaxPoint: '',// 记录税点，处理税点为空的情况
                payHistoryTotalPrice: '',// 收款列表，合计应收字段
                payHistoryPaidPrice:'',//收款列表，已收金额
                payHistoryPendingPrice: '',// 收款列表，待收金额字段

                // 新增租金列表信息
                rentReceiptObj: {
                    ctCode: "",
                    pkCode: "",
                    rentStartTime: "",// 开始日期
                    rentEndTime: "",// 结束日期
                    receivingDate: "",// 截止日期
                    receivableRent: "",// 本期租金
                    propertyPrice: "",// 本期物业费
                    hasTax: false,// 租金是否含税
                    taxPoint: "",// 租金税点
                    taxPrice: "",// 租金税金
                    proHasTax: false,// 物业费是否含税
                    proTaxPoint: "",// 物业费税点
                    totalPrice: "",// 合计应收
                },

                // 新增保险费
                addPremiumObj: {
                    premiumStartDate: '',// 开始日期
                    premiumEndDate: '',// 结束日期
                    deadline: '',// 截止日期
                    premiumAmount: '',// 投保金额
                    receivablePremium: '',// 应收保险费
                    hasTax: false,// 是否含税
                    taxPoint: '',// 税点
                    taxPrice: '',// 税金
                    totalPrice: '',// 合计应收
                },

                // 税点
                taxPoint: [
//                    {
//                        value: "1",
//                        label: '3%'
//                    },
//                    {
//                        value: "2",
//                        label: '5%'
//                    },
                    {
                        value: "3",
                        label: '6%'
                    },
                    {
                        value: "4",
                        label: '9%'
                    },
//                    {
//                        value: "5",
//                        label: '16%'
//                    },
                ],

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
                // 缴费办理、修改缴费信息校验
                rules: {
                    paidPrice: [
                        {required: true, message: '请输入已缴金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    hasTax: [
                        {required: true, message: '请选择是否含税', trigger: 'change'}
                    ],
                    taxPoint: [
                        {required: true, message: '请选择税率', trigger: 'change'}
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
                    payStatus: [
                        {required: true, message: '请选择缴费状态', trigger: 'change'}
                    ],
                    payDate: [
                        {required: true, message: '请选择缴费日期', trigger: 'blur'},
                    ],
                    totalPrice: [
                        {required: true, message: '请输入应收总额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    balance: [
                        {required: true, message: '请输入余额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],


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
                        {required: true, message: '请输入应收租金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    propertyPrice: [
                        {required: true, message: '请输入本期物业费', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    realRent: [
                        {required: true, message: '请输入缴费金额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    discountsPrice: [
                        {required: true, message: '请输入优惠金额', trigger: 'blur'},
//                        {validator: checkNumber},
                        {validator: checkFushuIntOrFloat},
                    ],
                    realPrice: [
                        {required: true, message: '请输入已收租金', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    receivablePrice: [
                        {required: true, message: '请输入应收总额', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                },
            }
        },
        methods: {
            //加载页面执行方法
            initLoad() {
                let vm = this;

                let param = {};
                param.entity = vm.ctObj;

                let ctCode;
                if (vm.paramsObj && vm.paramsObj.ctCode) {
                    ctCode = vm.paramsObj.ctCode;
                    vm.paymentInput.ctCode = ctCode;
                    param.entity.ctCode = ctCode;
                }

                if (param.entity.ctCode) {
                    let options = {
                        method: "POST",
                        data: param,
                        url: "contract/detail",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        if (response) {
                            vm.ctObj = response.data.result;

                            vm.ctObj.eleThisReceipt = [];
                            vm.ctObj.eleThisReceipt.push(vm.ctObj.electricityReceiptList[vm.ctObj.electricityReceiptList.length-1]);

                            vm.ctObj.waterThisReceipt = [];
                            vm.ctObj.waterThisReceipt.push(vm.ctObj.waterReceiptList[vm.ctObj.waterReceiptList.length-1]);

                            vm.ctObj.parkingThisReceipt = [];
                            vm.ctObj.parkingThisReceipt.push(vm.ctObj.parkingReceiptList[vm.ctObj.parkingReceiptList.length-1]);

                            vm.ctObj.premiumThisReceipt = [];
                            vm.ctObj.premiumThisReceipt.push(vm.ctObj.premiumReceiptList[vm.ctObj.premiumReceiptList.length-1]);

                            let rentList = [];
                            rentList = vm.ctObj.rentReceiptList;
                            if (rentList.length > 0) {
                                let newDate = new Date();
                                for (let i = 0; i < rentList.length; i++) {
                                    if (newDate > rentList[i].rentStartTime && newDate < rentList[i].rentEndTime) {
                                        vm.ctObj.rentThisReceipt = [];
                                        vm.ctObj.rentThisReceipt.push(rentList[i]);
                                        break;
                                    }
                                }
                            }

                            // 电费：应收金额、已收金额null值处理
                            if (vm.ctObj.electricityReceiptList) {
                                for (let i = 0; i < vm.ctObj.electricityReceiptList.length; i++) {
                                    // 应收电费
                                    if (!vm.ctObj.electricityReceiptList[i].receivablePrice) {
                                        vm.ctObj.electricityReceiptList[i].receivablePrice = 0;
                                    }

                                    // 本次实收应收
                                    if (!vm.ctObj.electricityReceiptList[i].realPrice) {
                                        vm.ctObj.electricityReceiptList[i].realPrice = 0;
                                    }

                                    // 已收应收
                                    if (!vm.ctObj.electricityReceiptList[i].paidPrice) {
                                        vm.ctObj.electricityReceiptList[i].paidPrice = 0;
                                    }
                                }
                            }

                            // 水费：应收金额、已收金额null值处理
                            if (vm.ctObj.waterReceiptList) {
                                for (let i = 0; i < vm.ctObj.waterReceiptList.length; i++) {
                                    if (!vm.ctObj.waterReceiptList[i].receivablePrice) {
                                        vm.ctObj.waterReceiptList[i].receivablePrice = 0;
                                    }
                                    if (!vm.ctObj.waterReceiptList[i].realPrice) {
                                        vm.ctObj.waterReceiptList[i].realPrice = 0;
                                    }

                                    // 已收应收
                                    if (!vm.ctObj.waterReceiptList[i].paidPrice) {
                                        vm.ctObj.waterReceiptList[i].paidPrice = 0;
                                    }
                                }
                            }

                            // 停车费：应收金额、已收金额null值处理
                            if (vm.ctObj.parkingReceiptList) {
                                for (let i = 0; i < vm.ctObj.parkingReceiptList.length; i++) {
                                    if (!vm.ctObj.parkingReceiptList[i].receivablePrice) {
                                        vm.ctObj.parkingReceiptList[i].receivablePrice = 0;
                                    }
                                    if (!vm.ctObj.parkingReceiptList[i].realPrice) {
                                        vm.ctObj.parkingReceiptList[i].realPrice = 0;
                                    }

                                    // 已收应收
                                    if (!vm.ctObj.parkingReceiptList[i].paidPrice) {
                                        vm.ctObj.parkingReceiptList[i].paidPrice = 0;
                                    }
                                }
                            }
                        }
                    }).catch(function (error) {
                        vm.$alert('页面：应收详情查询合同详细信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                } else {
                    this.$message.error("传入参数有误");
                }
            },

            //查看租金递增明细
            showIncInfo() {
                let vm = {};
                vm = this;
                let param = {};
                param.entity = {};
                let ctCode;
                if (vm.paramsObj.ctCode) {
                    ctCode = vm.paramsObj.ctCode;
                    param.entity.ctCode = ctCode;
                }
                //应收详情租金递增详情
                let inc_option = {
                    method: "POST",
                    data: param,
                    url: "rentIncrement/list",
                };
                this.$ajax(
                    inc_option
                ).then(function (response) {
                    if (response) {
                        vm.rentIncList = response.data.result;
                        let message = "";
                        if (vm.rentIncList) {
                            for (let i = 0; i < vm.rentIncList.length; i++) {
                                let sDate = new Date(vm.rentIncList[i].incStartTime);
                                let start = sDate.format("yyyy-MM-dd");
                                let eDate = new Date(vm.rentIncList[i].incEndTime);
                                let end = eDate.format("yyyy-MM-dd");
                                message = message + "<h4 style='color:blue;'>" + (i + 1) + '、' + start + "～" + end + "<br>租金:" + vm.rentIncList[i].rentTotal + "元/月&nbsp;&nbsp;递增比:" + vm.rentIncList[i].rentIncreaseProportion + "%<br></h4>";
                            }
                        }
                        vm.$notify({
                            title: '租金递增明细',
                            dangerouslyUseHTMLString: true,
                            message: message,
                            position: 'top-left',
                            duration: 0,
                            customClass: "notify"
                        });
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查询租金递增规则失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },


            //显示租赁房屋情况的弹窗
            showHouseInfo() {
                let message = "";
                let val = this.ctObj.contractHouseList;
                if (val) {
                    for (let i = 0; i < val.length; i++) {
                        if (val[i].roomNumber) {
                            message = message + "<h4 style='color:blue;'>" + val[i].bdName + "栋" + val[i].flCount + "层" + val[i].roomNumber + "&nbsp;&nbsp;承包面积:" + val[i].investArea + "㎡"+"<br></h4>";
                        } else {
                            message = message + "<h4 style='color:blue;'>" + val[i].bdName + "栋" + val[i].flCount + "层" + "&nbsp;&nbsp;承包面积:" + val[i].investArea + "㎡"+"<br></h4>";
                        }
                    }
                }
                this.$notify({
                    title: '房屋构成',
                    dangerouslyUseHTMLString: true,
                    message: message,
                    position: 'top-left',
                    duration: 0
                });
            },

            //合同计费规则弹窗
            showCostInfo(ctObj) {
                let message = "<h4><ul style='list-style-type: none;color: blue;'><li>付款方式:付"+ctObj.payRule+"押"+ctObj.depositRule+"</li><li>物业应收:"+ctObj.propertyTotal+"元</li><li>基础电费:"+ctObj.basicElectricityPriceByMonth+"元</li>" +
                    "<li>承包面积:"+ctObj.totalRentalArea+"㎡</li><li>车位单价:"+ctObj.parkingPrice+"元/个/月（免"+ctObj.freeParkingCount+"个）</li><li>电损:"+ctObj.addLoss+"%</li></ul></h4>";

                this.$notify({
                    title: '计费规则',
                    dangerouslyUseHTMLString: true,
                    message: message,
                    position: 'top-left',
                    duration: 0
                });

            },

            //一键生成租金(暂时没用功能，但是不删)
            createRent() {
                let vm = {};
                vm = this;

                let param = {};
                param.entity = {};
                if (vm.paramsObj.ctCode) {
                    param.entity.ctCode = vm.paramsObj.ctCode;
                }
                vm.$confirm('确定一键生成租金应收?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //一键生成租金详情
//                                vm.$alert('一键生成即将上线，敬请期待', '温馨提示', {
//                                        confirmButtonText: '确定',
//                                        callback: action => {
//                                        }
//                                });
                    let rentInc_option = {
                        method: "POST",
                        data: param,
                        url: "contract/generateRentDetail",
                    };
                    vm.$ajax(
                        rentInc_option
                    ).then(function (response) {
                        if (response) {
                            window.location.reload();
                        }
                    }).catch(function (error) {
                        vm.$alert('页面：一键生成租金详情失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }).catch(() => {
                    vm.$message({
                        type: 'info',
                        message: '已取消自动生成'
                    });
                });
            },

            // 修改应收租金
            receivableRentEdit(val) {
                let vm = this;
                if (val === 1) {
                    // 修改租金总额
                    let overPrice = vm.editPayment.paidPrice - vm.editPayment.totalPrice;
                    if (overPrice >= 0) {
                        vm.editPayment.overPrice = overPrice;
                        vm.editPayment.payStatus = "";
                        vm.editPayment.payStatus = "2";
                    } else {
                        vm.editPayment.overPrice = 0;
                        vm.editPayment.payStatus = vm.rentPayStatus;
                    }
                } else if (val === 2) {
                    // 修改水电费停车费总额
                    let overPrice = vm.editEleWaterPayment.paidPrice - vm.editEleWaterPayment.receivablePrice;
                    if (overPrice >= 0) {
                        vm.editEleWaterPayment.overPrice = overPrice;
                        vm.editEleWaterPayment.payStatus = "";
                        vm.editEleWaterPayment.payStatus = "2";
                    } else {
                        vm.editEleWaterPayment.overPrice = 0;
                        vm.editEleWaterPayment.payStatus = vm.eleWaterParkPayStatus;
                    }
                } else if (val === 3) {
                    // 修改保险费总额
                    let overPrice = vm.editPremiumObj.paidPrice - vm.editPremiumObj.receivablePremium;
                    if (overPrice >= 0) {
                        vm.editPremiumObj.overPrice = overPrice;
                        vm.editPremiumObj.payStatus = "";
                        vm.editPremiumObj.payStatus = "2";
                    } else {
                        vm.editPremiumObj.overPrice = 0;
                        vm.editPremiumObj.payStatus = vm.premiumPayStatus;
                    }
                }
            },

            // 计算应收租金：物业 + 租金
            rentTotalSum(){
                let vm = this;

                if (vm.showDeposit) {
                    vm.editPayment.totalPrice = vm.ctObj.eleDeposit + vm.ctObj.waterDeposit +vm.ctObj.fpayProjectDeposit +
                        vm.ctObj.fpayBond +vm.ctObj.fpayInsurancePrice + Number(vm.editPayment.propertyPrice) + Number(vm.editPayment.receivableRent);
                } else {
                    vm.editPayment.totalPrice = Number(vm.editPayment.propertyPrice) + Number(vm.editPayment.receivableRent);
                }
            },

            //打开修改金额弹窗
            openEditPay(payCode){
                let vm = this;
                vm.dialogFormEditPay = true;
                vm.showDeposit = false;
                //循环租金，含下期
                for (let i = 0; i < vm.ctObj.rentReceiptList.length; i++) {
                    if (payCode == vm.ctObj.rentReceiptList[i].rentCode) {
                        if(i == 0) {
                            vm.showDeposit = true;
                            vm.fPayAllPrice = vm.ctObj.eleDeposit + vm.ctObj.waterDeposit +vm.ctObj.fpayProjectDeposit +
                                vm.ctObj.fpayBond +vm.ctObj.fpayInsurancePrice;
                        }
                        vm.editPayment = JSON.parse(JSON.stringify(vm.ctObj.rentReceiptList[i]));

                        //记录该条租金状态，方便后面状态还原
                        vm.rentPayStatus = vm.editPayment.payStatus;

                        let overPrice = vm.editPayment.paidPrice - vm.editPayment.totalPrice;

                        // 租金、物业
                        vm.editPayment.receivableRent = vm.ctObj.rentReceiptList[i].receivableRent;
                        vm.editPayment.propertyPrice = vm.ctObj.rentReceiptList[i].propertyPrice;

                        //时间组件赋值开始时间和结束时间
//                        vm.editPayment.rentTimeList = [vm.editPayment.rentStartTime, vm.editPayment.rentEndTime];
                        vm.rentTimeListInit = [vm.editPayment.rentStartTime, vm.editPayment.rentEndTime];
                        if (!vm.editPayment.realRent) {
                            vm.editPayment.realRent = 0;
                        }
                        if (overPrice <= 0) {
                            vm.editPayment.overPrice = 0;
                        } else {
                            vm.editPayment.overPrice = overPrice;
                        }
                        break;
                    }
                }
            },

            //打开电、水、停车费修改金额弹窗
            openEleWaterEdit(payCode) {
                let vm = this;
                vm.dialogEelWaterFormEditPay = true;
                /*-----------------------------------------------------------------------------------------------------------------------------------------*/
                //循环电费，含下期
                for (let i = 0; i < vm.ctObj.electricityReceiptList.length; i++) {
                    if (payCode == vm.ctObj.electricityReceiptList[i].eleCode) {
                        vm.editEleWaterPayment = JSON.parse(JSON.stringify(vm.ctObj.electricityReceiptList[i]));
                        if (!vm.editEleWaterPayment.paidPrice) {
                            vm.editEleWaterPayment.paidPrice = 0;
                        }
                        let overPrice = vm.editEleWaterPayment.paidPrice - (vm.editEleWaterPayment.basicElectricityPriceByMonth + vm.editEleWaterPayment.electricityPrice);

                        //记录该条电费状态，方便后面状态还原
                        vm.eleWaterParkPayStatus = vm.editEleWaterPayment.payStatus;

                        //时间组件赋值开始时间和结束时间
                        vm.eleWaterTimeList = [vm.editEleWaterPayment.lastBillingTime, vm.editEleWaterPayment.currentBillingTime];
                        vm.eleWaterTimeListInit = [vm.editEleWaterPayment.lastBillingTime, vm.editEleWaterPayment.currentBillingTime];
                        if (!vm.editEleWaterPayment.realPrice) {
                            vm.editEleWaterPayment.realPrice = 0;
                        }
                        if (!vm.editEleWaterPayment.paidPrice) {
                            vm.editEleWaterPayment.paidPrice = 0;
                        }
                        if (!overPrice || overPrice <= 0) {
                            vm.editEleWaterPayment.overPrice = 0
                        } else {
                            vm.editEleWaterPayment.overPrice = overPrice;
                        }
                        break;
                    }
                }
                /*-----------------------------------------------------------------------------------------------------------------------------------------*/
                //循环水费，含下期
                for (let i = 0; i < vm.ctObj.waterReceiptList.length; i++) {
                    if (payCode == vm.ctObj.waterReceiptList[i].waterCode) {
                        vm.editEleWaterPayment = JSON.parse(JSON.stringify(vm.ctObj.waterReceiptList[i]));
                        if (!vm.editEleWaterPayment.paidPrice) {
                            vm.editEleWaterPayment.paidPrice = 0;
                        }
                        let overPrice = vm.editEleWaterPayment.paidPrice - (vm.editEleWaterPayment.basicElectricityPriceByMonth + vm.editEleWaterPayment.electricityPrice);

                        //记录该条水费状态，方便后面状态还原
                        vm.eleWaterParkPayStatus = vm.editEleWaterPayment.payStatus;

                        //时间组件赋值开始时间和结束时间
                        vm.eleWaterTimeList = [vm.editEleWaterPayment.lastBillingTime, vm.editEleWaterPayment.currentBillingTime];
                        vm.eleWaterTimeListInit = [vm.editEleWaterPayment.lastBillingTime, vm.editEleWaterPayment.currentBillingTime];
                        if (!vm.editEleWaterPayment.realPrice) {
                            vm.editEleWaterPayment.realPrice = 0;
                        }
                        if (!vm.editEleWaterPayment.paidPrice) {
                            vm.editEleWaterPayment.paidPrice = 0;
                        }
                        if (!overPrice || overPrice <= 0) {
                            vm.editEleWaterPayment.overPrice = 0
                        } else {
                            vm.editEleWaterPayment.overPrice = overPrice;
                        }
                        break;
                    }
                }
                /*-----------------------------------------------------------------------------------------------------------------------------------------*/
                //循环停车费，含下期
                for (let i = 0; i < vm.ctObj.parkingReceiptList.length; i++) {
                    if (payCode == vm.ctObj.parkingReceiptList[i].parkingCode) {
                        vm.editEleWaterPayment = JSON.parse(JSON.stringify(vm.ctObj.parkingReceiptList[i]));
                        if (!vm.editEleWaterPayment.paidPrice) {
                            vm.editEleWaterPayment.paidPrice = 0;
                        }
                        let overPrice = vm.editEleWaterPayment.paidPrice - (vm.editEleWaterPayment.basicElectricityPriceByMonth + vm.editEleWaterPayment.electricityPrice);

                        //记录该条水费状态，方便后面状态还原
                        vm.eleWaterParkPayStatus = vm.editEleWaterPayment.payStatus;

                        //时间组件赋值开始时间和结束时间
                        vm.eleWaterTimeList = [vm.editEleWaterPayment.startTime, vm.editEleWaterPayment.endTime];
                        vm.eleWaterTimeListInit = [vm.editEleWaterPayment.startTime, vm.editEleWaterPayment.currentBillingTime];
                        if (!vm.editEleWaterPayment.realPrice) {
                            vm.editEleWaterPayment.realPrice = 0;
                        }
                        if (!vm.editEleWaterPayment.paidPrice) {
                            vm.editEleWaterPayment.paidPrice = 0;
                        }
                        if (!overPrice || overPrice <= 0) {
                            vm.editEleWaterPayment.overPrice = 0
                        } else {
                            vm.editEleWaterPayment.overPrice = overPrice;
                        }
                        break;
                    }
                }

            },

            //修改租金提交
            submitEditForm(submitForm){
                let vm = this;
                let paramObj = {};

                if(!vm.editPayment.hasTax) {
                    vm.editPayment.taxPoint = "";
                }
                if(!vm.editPayment.proHasTax) {
                    vm.editPayment.proTaxPoint = "";
                }

                paramObj.entity = vm.editPayment;
                vm.dialogFormEditPay = false;

                vm.methodTemplate(submitForm, vm, paramObj, "rentReceipt/update", "修改租金缴费信息");
            },

            //修改电,水,停车费金额提交
            submitEleWaterForm(submitForm) {
                let vm = this;
                let paramObj = {};

                let url = "";

                if (vm.markCostType == "2") {
                    url = "eleReceipt/update";
                }
                if (vm.markCostType == "3") {
                    url = "waterReceipt/update";
                }
                if (vm.markCostType == "4") {
                    url = "parkReceipt/update";
                }

                vm.editEleWaterPayment.lastBillingTime = vm.eleWaterTimeList[0];
                vm.editEleWaterPayment.currentBillingTime = vm.eleWaterTimeList[1];

                paramObj.entity = vm.editEleWaterPayment;
                vm.dialogEelWaterFormEditPay =false;
                vm.methodTemplate(submitForm, vm, paramObj, url, "修改应收缴费信息");
            },

            /*---------------------------------------------- 租金缴费 ----------------------------------------------*/
            // 打开租金缴费窗口
            openRentWindows(markCode){
                let vm = this;

                vm.paymentInput.paidPrice = 0;
                vm.paymentInput.balance = 0;
                vm.hasBalance = false;// 页面是否有余额

                //循环租金，含下期
                for (let i = 0; i < vm.ctObj.rentReceiptList.length; i++) {
                    if (markCode == vm.ctObj.rentReceiptList[i].rentCode) {
                        vm.holdRentWindow = true;
                        vm.paymentInput.payStatus = vm.ctObj.rentReceiptList[i].payStatus;// 支付状态

                        //记录该条租金状态，方便后面状态还原
                        vm.rentPayStatus = vm.paymentInput.payStatus;

                        vm.paymentInput.latePayment = 0;

                        //如果是逾期状态则将逾期天数和滞纳金显示出来
                        if(vm.paymentInput.payStatus == '3') {
                            vm.paymentInput.overdueDays = vm.ctObj.rentReceiptList[i].overdueDays;
                            vm.paymentInput.latePayment = vm.ctObj.rentReceiptList[i].latePayment;
                        }

                        // 应收金额 = 房屋租金 + 物业费
                        vm.paymentObj.payableData = Number(vm.ctObj.rentReceiptList[i].totalPrice) + Number(vm.paymentInput.latePayment);
                        vm.paymentObj.paidPriceData = vm.ctObj.rentReceiptList[i].paidPrice;// 已收金额（已收金额+优惠金额）

                        // 待收金额 = 应收金额 - 已收金额
                        vm.paymentObj.pendingPayment = (vm.paymentObj.payableData - vm.ctObj.rentReceiptList[i].paidPrice-vm.ctObj.rentReceiptList[i].discountsPrice).toFixed(2);
                        if (Number(vm.paymentObj.pendingPayment) < 0) {
                            vm.paymentObj.pendingPayment = 0;
                        }

                        vm.markListCode = vm.ctObj.rentReceiptList[i].rentCode;

//                        vm.paymentInput.hasTax = vm.ctObj.rentReceiptList[i].hasTax;// 租金是否含税
//                        vm.paymentInput.taxPoint = vm.ctObj.rentReceiptList[i].taxPoint;// 租金税点
////                        vm.paymentInput.taxPrice = "";//税金置空
//                        vm.paymentInput.proHasTax = vm.ctObj.rentReceiptList[i].proHasTax;// 物业是否含税
//                        vm.paymentInput.proTaxPoint = vm.ctObj.rentReceiptList[i].proTaxPoint;// 物业税点

                        vm.paymentInput.allPay = 0;

                        //当前缴费金额置为0
                        vm.paymentInput.realRent = "";
                        //缴费之间置为空
                        vm.paymentInput.payDate ="";
                        //优惠金额
                        vm.paymentInput.discountsPrice = 0;

                    }
                }
            },

            // 租金缴费提交
            rentSubmit(formName){
                let vm = this;
                let dataObj = {};

                // 本期租金，缴费编号存在则执行
                for (let i = 0; i < vm.ctObj.rentReceiptList.length; i++) {
                    if (vm.markListCode == vm.ctObj.rentReceiptList[i].rentCode) {

                        vm.paymentInput.id = vm.ctObj.rentReceiptList[i].id;// 租金id
                        vm.paymentInput.rentCode = vm.ctObj.rentReceiptList[i].rentCode;//租金编号

                        if (vm.paymentInput.payStatus != "2" && vm.paymentInput.payStatus != "3" && vm.paymentInput.realRent > 0) {
                            vm.paymentInput.payStatus = "4";
                        }
                        // 已收金额 = 已收金额 + 缴费金额（本次缴费）（一期分多次缴费的情况，已收加上本次缴费金额）
                        vm.paymentInput.paidPrice = Number(vm.paymentInput.realRent) + Number(vm.ctObj.rentReceiptList[i].paidPrice);
                        dataObj.entity = vm.paymentInput;

                        let resUrl = "rentReceipt/pay";
                        vm.holdRentWindow = false;
                        vm.methodTemplate(formName, vm, dataObj, resUrl, "租金缴费");
                    }
                }
            },

            /*---------------------------------------------- 租金缴费 ----------------------------------------------*/

            /********************************************水电停车缴费分割线****************************************************/
            // 打开“水、电、停车费”缴费弹窗
            openBox(payCode){
                let vm = this;
                vm.paymentInput.paidPrice = 0;
                vm.paymentInput.balance = 0;
                vm.hasBalance = false;

                //循环电费，含下期
                for (let i = 0; i < vm.ctObj.electricityReceiptList.length; i++) {
                    if (payCode == vm.ctObj.electricityReceiptList[i].eleCode) {

                        vm.paymentObj.payableData = vm.ctObj.electricityReceiptList[i].receivablePrice;//应收金额
                        vm.paymentObj.paidPriceData = vm.ctObj.electricityReceiptList[i].paidPrice;//已收金额

                        // 待收金额 = 应收金额 - 已收金额
                        vm.paymentObj.pendingPayment = (vm.paymentObj.payableData - vm.paymentObj.paidPriceData).toFixed(2);

                        vm.markListCode = vm.ctObj.electricityReceiptList[i].eleCode;
                        vm.paymentInput.payStatus = vm.ctObj.electricityReceiptList[i].payStatus;// 支付状态
//                        vm.paymentInput.hasTax = vm.ctObj.electricityReceiptList[i].hasTax;// 是否含税
//                        vm.paymentInput.taxPoint = vm.ctObj.electricityReceiptList[i].taxPoint;// 税点
                        vm.paymentInput.taxPrice = "";//税金置空
                        vm.paymentInput.allPay = 0;

                        if (vm.paymentObj.pendingPayment < 0) {
                            vm.paymentObj.pendingPayment = 0;
                        }
                    }
                }
                //循环水费，含下期
                for (let i = 0; i < vm.ctObj.waterReceiptList.length; i++) {
                    if (payCode == vm.ctObj.waterReceiptList[i].waterCode) {

                        vm.paymentObj.payableData = vm.ctObj.waterReceiptList[i].receivablePrice;//应收金额
                        vm.paymentObj.paidPriceData = vm.ctObj.waterReceiptList[i].paidPrice;//已收金额

                        // 待收金额 = 应收金额 - 已收金额
                        vm.paymentObj.pendingPayment = (vm.paymentObj.payableData - vm.paymentObj.paidPriceData).toFixed(2);

                        vm.markListCode = vm.ctObj.waterReceiptList[i].waterCode;
                        vm.paymentInput.payStatus = vm.ctObj.waterReceiptList[i].payStatus;
//                        vm.paymentInput.hasTax = vm.ctObj.waterReceiptList[i].hasTax;// 是否含税
//                        vm.paymentInput.taxPoint = vm.ctObj.waterReceiptList[i].taxPoint;// 税点
                        vm.paymentInput.taxPrice = "";//税金置空
                        vm.paymentInput.allPay = 0;

                        if (vm.paymentObj.pendingPayment < 0) {
                            vm.paymentObj.pendingPayment = 0;
                        }
                    }
                }
                //循环停车费，含下期
                for (let i = 0; i < vm.ctObj.parkingReceiptList.length; i++) {
                    if (payCode == vm.ctObj.parkingReceiptList[i].parkingCode) {

                        vm.paymentObj.payableData = vm.ctObj.parkingReceiptList[i].receivablePrice;//应收金额
                        vm.paymentObj.paidPriceData = vm.ctObj.parkingReceiptList[i].realPrice;//已收金额

                        // 待收金额 = 应收金额 - 已收金额
                        vm.paymentObj.pendingPayment = (vm.paymentObj.payableData - vm.paymentObj.paidPriceData).toFixed(2);

                        vm.markListCode = vm.ctObj.parkingReceiptList[i].parkingCode;
                        vm.paymentInput.payStatus = vm.ctObj.parkingReceiptList[i].payStatus;
//                        vm.paymentInput.hasTax = vm.ctObj.parkingReceiptList[i].hasTax;// 是否含税
//                        vm.paymentInput.taxPoint = vm.ctObj.parkingReceiptList[i].taxPoint;// 税点
                        vm.paymentInput.taxPrice = "";//税金置空
                        vm.paymentInput.allPay = 0;

                        if (vm.paymentObj.pendingPayment < 0) {
                            vm.paymentObj.pendingPayment = 0;
                        }
                    }
                }

                vm.dialogFormVisible = true;

                //记录该条水、电、停车费状态，方便后面状态还原
                vm.eleWaterParkPayStatus = vm.paymentInput.payStatus;
                vm.paymentInput.realPrice = "";
                vm.paymentInput.payDate = "";

            },

            // 关闭“水、电、停车费”缴费窗口
            restForm(formName){
                this.$refs[formName].resetFields();

                this.dialogFormVisible = false
                this.holdRentWindow = false
            },

            //办理“水、电、停车费”缴费，提交
            submitForm(formName){
                let vm = this;
                let dataObj = {};

                //本期电费，缴费编号存在执行
                for (let i = 0; i < vm.ctObj.electricityReceiptList.length; i++) {
                    if (vm.markListCode == vm.ctObj.electricityReceiptList[i].eleCode) {

                        vm.paymentInput.id = vm.ctObj.electricityReceiptList[i].id;//电费id
                        vm.paymentInput.eleCode = vm.ctObj.electricityReceiptList[i].eleCode;//电费编号

                        if (vm.paymentInput.payStatus != "2" && vm.paymentInput.payStatus != "3" && vm.paymentInput.realPrice > 0) {
                            vm.paymentInput.payStatus = "4";
                        }
                        // 已收金额 = 已收金额 + 缴费金额（本次缴费）（一期分多次缴费的情况，已收加上本次缴费金额）
                        vm.paymentInput.paidPrice = Number(vm.ctObj.electricityReceiptList[i].paidPrice) + Number(vm.paymentInput.realPrice);
                        dataObj.entity = vm.paymentInput;

                        let resUrl = "eleReceipt/pay";
                        vm.methodTemplate(formName, vm, dataObj, resUrl, "电费缴费");
                    }
                }

                //本期水费，缴费编号存在执行
                for (let i = 0; i < vm.ctObj.waterReceiptList.length; i++) {
                    if (vm.markListCode == vm.ctObj.waterReceiptList[i].waterCode) {

                        vm.paymentInput.id = vm.ctObj.waterReceiptList[i].id;//水费id
                        vm.paymentInput.waterCode = vm.ctObj.waterReceiptList[i].waterCode;//水费编号

                        if (vm.paymentInput.payStatus != "2" && vm.paymentInput.payStatus != "3" && vm.paymentInput.realPrice > 0) {
                            vm.paymentInput.payStatus = "4";
                        }
                        // 已收金额 = 已收金额 + 缴费金额（本次缴费）（一期分多次缴费的情况，已收加上本次缴费金额）
                        vm.paymentInput.paidPrice = Number(vm.ctObj.waterReceiptList[i].paidPrice) + Number(vm.paymentInput.realPrice);
                        dataObj.entity = vm.paymentInput;

                        let resUrl = "waterReceipt/pay";
                        vm.methodTemplate(formName, vm, dataObj, resUrl, "水费缴费");
                    }
                }

                //本期停车费，缴费编号存在执行
                for (let i = 0; i < vm.ctObj.parkingReceiptList.length; i++) {
                    if (vm.markListCode == vm.ctObj.parkingReceiptList[i].parkingCode) {

                        vm.paymentInput.id = vm.ctObj.parkingReceiptList[i].id;//停车费id
                        vm.paymentInput.parkingCode = vm.ctObj.parkingReceiptList[i].parkingCode;//停车费编号

                        if (vm.paymentInput.payStatus != "2" && vm.paymentInput.payStatus != "3" && vm.paymentInput.realPrice > 0) {
                            vm.paymentInput.payStatus = "4";
                        }
                        // 已收金额 = 已收金额 + 缴费金额（本次缴费）（一期分多次缴费的情况，已收加上本次缴费金额）
                        vm.paymentInput.paidPrice = Number(vm.ctObj.parkingReceiptList[i].paidPrice) + Number(vm.paymentInput.realPrice);
                        dataObj.entity = vm.paymentInput;

                        let resUrl = "parkReceipt/pay";
                        vm.methodTemplate(formName, vm, dataObj, resUrl, "停车费缴费");
                    }
                }
                vm.dialogFormVisible = false;
            },
            /********************************************水电停车缴费分割线****************************************************/

            /********************************************缴费历史记录分割线****************************************************/
            // 打开缴费历史记录弹窗
            openPayHistoryWindow(code, costType, totalPrice, paidPrice,discountsPrice){
                let vm = this;
                let paramObj = {
                    entity:{
                        code: code,
                        costType: costType,
                    }
                };

                if(!discountsPrice) {
                    discountsPrice = 0;
                }

                this.holdPayHistory = true;
                this.markCostType = costType;
                this.payHistoryTotalPrice = totalPrice;
                this.payHistoryPaidPrice = paidPrice;
                let payHistoryPendingPrice = (totalPrice - paidPrice - discountsPrice).toFixed(2);
                if(payHistoryPendingPrice >= 0) {
                    this.payHistoryPendingPrice = (totalPrice - paidPrice - discountsPrice).toFixed(2);
                } else {
                    this.payHistoryPendingPrice = 0;
                }

                vm.queryPayHistoryTemplate(vm, paramObj);
            },

            // 查询缴费历史记录方法模板
            queryPayHistoryTemplate(vm, paramObj){
                let options = {
                    method: "POST",
                    data: paramObj,
                    url: "pay/list"
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.payHistoryList = response.data.result;
                    }
                }).catch(function (error) {
                    vm.$alert( '查询失败！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            //租金缴费修改
            editPayHisInfo(val) {
                this.payHistoryObj = val;

                this.showPayHistoryWindow = true;
                //关闭缴费列表
                this.holdPayHistory = false;

            },

            //租金缴费删除
            delPayHisInfo(id) {
                let vm = this;
                vm.$confirm('确定删除本条缴费记录?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let param = {};
                    param.entity = {
                        id: id,
                    };

                    let options = {
                        method: 'POST',
                        data: param,
                        url: "pay/delete",
                    };

                    this.$ajax(
                        options
                    ).then(function (response){
                        if(response) {
                            vm.holdPayHistory = false;
                            vm.$message({
                                showClose: true,
                                type: 'success',
                                message: '删除缴费信息成功'
                            });
                            vm.initLoad();
                        }
                    }).catch(function (error) {
                        vm.$alert('删除缴费记录失败', '温馨提示', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    })
                }).catch(() =>{
                });
            },

            //修改缴费金额提交
            rentHisEditSubmit(payHistoryObj) {

                let vm = this;

                let obj = {};
                obj.entity = payHistoryObj;
                let options = {
                    method: "POST",
                    data: obj,
                    url: "pay/update"
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if(response) {
                        vm.showPayHistoryWindow = false;
                        vm.$message({
                            showClose: true,
                            type: 'success',
                            message: '修改缴费信息成功'
                        });
                        vm.initLoad();
                    }
                }).catch(function (error) {
                    vm.$alert( '修改租金缴费记录失败！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });

            },

            //修改缴费金额取消
            rentHisRestForm(payHistoryObj) {

                //关闭修改租金缴费页面
                this.showPayHistoryWindow = false;

                //打开租金列表
                this.openPayHistoryWindow(payHistoryObj.code,payHistoryObj.costType,this.payHistoryTotalPrice,this.payHistoryPaidPrice);

            },

            // 自定义合计
            getSummaries(param) {
                const { columns, data } = param;
                const sums = [];

                columns.forEach((column, index) => {
                    if (index === 0) {
                        sums[index] = '合计';
                        return;
                    }
                    const values = data.map(item => Number(item[column.property]));
                    if (index != 1 && index != 5 && index != 7) {
                        if (!values.every(value => isNaN(value))) {
                            sums[index] = values.reduce((prev, curr) => {
                                const value = Number(curr);
                                if (!isNaN(value)) {
                                    return prev + curr;
                                } else {
                                    return prev;
                                }
                            }, 0);
                            sums[index] += ' 元';
                        }
                    }
                });
                return sums;
            },
            /********************************************缴费历史记录分割线****************************************************/

            //修改缴费金额触发
            autoBalance(){
                //本次缴费+本次优惠金额 > 应收
                if ((Number(this.paymentInput.realRent)+Number(this.paymentInput.discountsPrice)) >= Number(this.paymentObj.pendingPayment)) {
                    this.hasBalance = true;
                    //余额 = 本次缴费 - （待收金额 - 本次优惠金额）
                    this.paymentInput.balance = (Number(this.paymentInput.realRent) - (Number(this.paymentObj.pendingPayment)-Number(this.paymentInput.discountsPrice))).toFixed(2);
                    this.paymentInput.payStatus = "2";
                } else {
                    this.hasBalance = false;
                    this.paymentInput.balance = 0;
                    this.paymentInput.payStatus = this.rentPayStatus;
                }

                this.paymentInput.allPay = Number(this.paymentInput.realRent);

                if (!this.paymentInput.allPay) {
                    this.paymentInput.allPay = 0;
                }
            },
            //计算税金   不计算税金  先注释
//            mulTaxPrice(num) {
//                let taxLabel = this.taxPoint[this.paymentInput.taxPoint - 1].label;
//                let taxPoint = Number(taxLabel.substr(0, taxLabel.length - 1) / 100);
//
//                if (num == 1) {
//                    if (this.paymentInput.realRent) {
//                        this.paymentInput.taxPrice = (this.paymentInput.realRent * taxPoint).toFixed(2);
//                    }
//                    this.paymentInput.allPay = Number(this.paymentInput.realRent) + Number(this.paymentInput.taxPrice);
//                }
//
//                if (num == 2) {
//                    if (this.paymentInput.realPrice) {
//                        this.paymentInput.taxPrice = (this.paymentInput.realPrice * taxPoint).toFixed(2);
//                    }
//                    this.paymentInput.allPay = Number(this.paymentInput.realPrice) + Number(this.paymentInput.taxPrice);
//                }
//
//                if (num == 3) {
//                    if (this.paymentInput.thePayment) {
//                        this.paymentInput.taxPrice = (this.paymentInput.thePayment * taxPoint).toFixed(2);
//                    }
//                    this.paymentInput.allPay = Number(this.paymentInput.thePayment) + Number(this.paymentInput.taxPrice);
//                }
//            },

            //修改了税金
            editTaxPrice(num) {
                if (num == 1) {
                    this.paymentInput.allPay =  Number(this.paymentInput.realRent) + Number(this.paymentInput.taxPrice);
                }
                if (num == 2) {
                    this.paymentInput.allPay =  Number(this.paymentInput.realPrice) + Number(this.paymentInput.taxPrice);
                }
            },

            //修改“水、电、停车费”缴费金额触发
            autoBalanceEWP(){
                if (Number(this.paymentInput.realPrice) >= Number(this.paymentObj.pendingPayment)) {
                    this.hasBalance = true;
                    this.paymentInput.balance = Number(this.paymentInput.realPrice) - Number(this.paymentObj.pendingPayment);
                    this.paymentInput.payStatus = "2";
                } else {
                    this.hasBalance = false;
                    this.paymentInput.balance = 0;
                    this.paymentInput.payStatus = this.eleWaterParkPayStatus;
                }

                //计算出税钱
                if (this.paymentInput.realPrice && this.paymentInput.taxPoint) {
                    //1、先获取税点
                    let taxLabel = this.taxPoint[this.paymentInput.taxPoint-3].label;
                    let taxPoint = Number(taxLabel.substr(0,taxLabel.length-1)/100);

                    this.paymentInput.taxPrice = (this.paymentInput.realPrice*taxPoint).toFixed(2);
                }
                this.paymentInput.allPay = Number(this.paymentInput.realPrice) + Number(this.paymentInput.taxPrice);

                if (!this.paymentInput.allPay) {
                    this.paymentInput.allPay = 0;
                }
            },

            // 保险费
            autoBalancePremium(){
                if (Number(this.paymentInput.thePayment) >= Number(this.paymentObj.pendingPayment)) {
                    this.hasBalance = true;
                    this.paymentInput.balance = Number(this.paymentInput.thePayment) - Number(this.paymentObj.pendingPayment);
                    this.paymentInput.payStatus = "2";
                } else {
                    this.hasBalance = false;
                    this.paymentInput.balance = 0;
                    this.paymentInput.payStatus = this.premiumPayStatus;
                }

                //计算出税钱
//                if (this.paymentInput.thePayment && this.paymentInput.taxPoint) {
//                    //1、先获取税点
//                    let taxLabel = this.taxPoint[this.paymentInput.taxPoint-3].label;
//                    let taxPoint = Number(taxLabel.substr(0,taxLabel.length-1)/100);
//
//                    this.paymentInput.taxPrice = (this.paymentInput.thePayment * taxPoint).toFixed(2);
//                }
                this.paymentInput.allPay = Number(this.paymentInput.thePayment);

                if (!this.paymentInput.allPay) {
                    this.paymentInput.allPay = 0;
                }
            },

            //方法模板
            methodTemplate(formName, vm, dataObj, url, msg){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let options = {
                            method: "POST",
                            data: dataObj,
                            url: url
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: msg + '成功！',
                                    type: 'success'
                                });
//                                window.location.reload();
                                vm.initLoad();
                            }
                        }).catch(function (error) {
                            vm.$alert(msg + '失败！', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                })
            },

            //本期应收
            showThisBilling(index) {
                this.isShowIndex = index;
            },
            //房租物业费
            showHouseProperty(index) {
                this.isShowIndex = index;
            },
            //电费
            showEle(index) {
                this.isShowIndex = index;
            },
            //水费
            showWater(index) {
                this.isShowIndex = index;
            },
            //停车费
            showParking(index) {
                this.isShowIndex = index;
            },
            // 保险费
            showPremium(index){
                this.isShowIndex = index;
            },
            //修改时间
            chooseOther(){
                let vm = this;
                vm.$confirm('即将影响到后期应收数据,是否继续', '提示', {
                    confirmButtonText: '是',
                    cancelButtonText: '否',
                    type: 'warning'
                }).then(() => {

                }).catch(() => {
                    vm.$message({
                        type: 'info',
                        message: '取消修改时间'
                    });
                    vm.editPayment.rentStartTime = vm.rentTimeListInit[0];
                    vm.editPayment.rentEndTime = vm.rentTimeListInit[1];

                });

                let payStatus = vm.editPayment.payStatus;
                vm.editPayment.payStatus = "0";
                vm.editPayment.payStatus = payStatus;
            },

            /********************************************************************************************
             * 添加租金操作
             *******************************************************************************************/

            // 添加租金按钮
            addRentWindow() {
                this.addRentReceipt = true;
                this.rentReceiptObj = {
                    rentStartTime:"",//开始日期
                    rentEndTime:"",//结束日期
                    receivingDate:"",//截止日期
                    receivableRent:"",//本期租金
                    propertyPrice:"",//本期物业费
                    hasTax:"",//租金是否含税
                    taxPointName:"",//租金税点
                    taxPrice:"",//租金税金
                    proHasTax:"",//物业费是否含税
                    proTaxPointName:"",//物业费税点
                    totalPrice:"",//合计应收
                };
            },

            // 提交租金应收新增
            rentSubmitForm(formName) {
                let vm  = this;

                let param = {};
                param.entity = {};

                vm.rentReceiptObj.ctCode = vm.ctObj.ctCode;
                vm.rentReceiptObj.pkCode = vm.ctObj.pkCode;

                if(!vm.rentReceiptObj.hasTax) {
                    vm.rentReceiptObj.taxPoint = "";
                }

                if(!vm.rentReceiptObj.proHasTax) {
                    vm.rentReceiptObj.proTaxPoint = "";
                }

                param.entity = vm.rentReceiptObj;

                vm.$refs[formName].validate((valid) => {
                    if (valid) {
                        let rent_option = {
                            method: "POST",
                            data: param,
                            url: "rentReceipt/create"
                        };
                        this.$ajax(
                            rent_option
                        ).then(function (response) {
                            if(response) {
                                vm.selectRentReceipt();
                                vm.addRentReceipt = false;
                            }

                        }).catch(function (error) {
                            vm.$alert('租金应收新增失败', '温馨提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                });
            },

            // 删除租金
            delRentReceipt(id, rentCode, paidPrice) {
                let vm = this;
                if(paidPrice > 0) {
                    vm.$alert('本期存在缴费，不能删除本条数据', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                vm.$confirm('确定删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let param = {};
                    param.entity = {
                        id: id,
                        rentCode: rentCode
                    };

                    let options = {
                        method: 'POST',
                        data: param,
                        url: "rentReceipt/delete",
                    };

                    this.$ajax(
                        options
                    ).then(function (response){
                        if(response) {
                            vm.selectRentReceipt();
                        }
                    }).catch(function (error) {
                        vm.$alert('删除租金信息失败', '温馨提示', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    })
                }).catch(() =>{
                });
            },
            // 单独查询租金列表的请求
            selectRentReceipt() {
                let vm = this;
                let param = {};
                param.entity = {};
                param.entity.ctCode = vm.paramsObj.ctCode;
                let options = {
                    method: "POST",
                    data: param,
                    url: "rentReceipt/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.ctObj.rentReceiptList = response.data.result;

                        let rentList = [];
                        rentList = vm.ctObj.rentReceiptList;
                        if (rentList.length > 0) {
                            let newDate = new Date();
                            vm.ctObj.rentThisReceipt = [];
                            for (let i = 0; i < rentList.length; i++) {
                                if (newDate > rentList[i].rentStartTime && newDate < rentList[i].rentEndTime) {
                                    vm.ctObj.rentThisReceipt.push(rentList[i]);
                                    break;
                                }
                            }
                        }
                    }
                }).catch(function (error) {
                    vm.$alert('通用查询租金信息失败', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            /********************************************************************************************
             * 路由跳转
             *******************************************************************************************/

            // 水电抄表
            manualMeter(ctCode){
                let data = {
                    menuName:"水电表管理",
                    item: "manualMeter",
                    params: {
                        ctCode: ctCode
                    }
                };
                this.$emit("backParam",data);
            },
            // 查看租户详情
            showCusDetail(ctCode) {
                let data = {
                    menuName:"合同详情",
                    item: "contractDetails",
                    params: {
                        ctCode: ctCode
                    }
                };
                this.$emit("backParam",data);
            },

            /********************************************************************************************
             * 保险费操作：
             * 1.添加保险费
             * 2.删除保险费
             * 3.保险费缴费
             * 4.修改缴费
             *******************************************************************************************/

            // 取消添加保险费
            cancleAddPremium(formName){
                this.$refs[formName].resetFields();
                this.addPremiumWindow = false;
            },
            // 添加保险费提交
            premiumSubmit(formName){
                let vm = this;

                vm.addPremiumObj.ctCode = vm.ctObj.ctCode;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params = {
                            entity: vm.addPremiumObj
                        };

                        let options = {
                            method: "POST",
                            data: params,
                            url: "premium/create",
                        };

                        this.$ajax(
                            options
                        ).then(response => {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '保险费新增成功',
                                    type: 'success'
                                });
                                this.$refs[formName].resetFields();
                                this.addPremiumWindow = false;
                                vm.queryPremiumList();
                            }
                        }).catch(error => {
                            vm.$alert('添加保险费失败', '温馨提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        })
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                });
            },
            // 查询保险费列表
            queryPremiumList(){
                let vm = this;

                let param = {
                    entity: {
                        ctCode: vm.ctObj.ctCode,
                    }
                };

                let options = {
                    method: "POST",
                    data: param,
                    url: "premium/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.ctObj.premiumReceiptList = response.data.result;

                        let premiumList = vm.ctObj.premiumReceiptList;

                        if (premiumList.length > 0) {
                            let newDate = new Date();

                            for (let i = 0; i < premiumList.length; i++) {
                                if (newDate > premiumList[i].premiumStartDate && newDate < premiumList[i].premiumEndDate) {
                                    vm.ctObj.premiumThisReceipt.push(premiumList[i]);
                                    break;
                                }
                            }
                        }
                    }
                }).catch(function (error) {
                    vm.$alert('通用查询租金信息失败', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 删除保险费
            deletePremium(id, premiumCode, paidPrice) {
                let vm = this;

                if(paidPrice > 0) {
                    vm.$alert('本期存在缴费，不能删除本条数据', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                vm.$confirm('确定删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let params = {
                        entity: {
                            id: id,
                            premiumCode: premiumCode,
                        }
                    };

                    let options = {
                        method: 'POST',
                        data: params,
                        url: "premium/delete",
                    };

                    this.$ajax(
                        options
                    ).then(response => {
                        if(!response.data.isException) {
                            vm.queryPremiumList();
                        }
                    }).catch(error => {
                        vm.$alert('删除保险费失败', '温馨提示', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    })
                }).catch(() =>{
                });
            },
            // 打开保险缴费窗口
            openPremiumPayWindow(premiumCode){
                let vm = this;


                for (let i = 0; i < vm.ctObj.premiumReceiptList.length; i++) {
                    if (premiumCode == vm.ctObj.premiumReceiptList[i].premiumCode) {
                        vm.holdPremiumPaymentWindow = true;
                        vm.paymentInput = {
                            id: "",
                            payStatus: "",// 支付状态
                            thePayment: "",
                            balance: "",// 余额
                            allPay: 0,//本次共缴
                            payDate: "",// 缴费时间
                            remark: "", //备注
                        },

                        // 应收金额
                        vm.paymentObj.payableData = (vm.ctObj.premiumReceiptList[i].receivablePremium).toFixed(2);

                        // 已收金额
                        vm.paymentObj.paidPriceData = (vm.ctObj.premiumReceiptList[i].paidPrice).toFixed(2);// 已收金额

                        // 待收金额 = 应收金额 - 已收金额
                        vm.paymentObj.pendingPayment = (vm.paymentObj.payableData - vm.paymentObj.paidPriceData).toFixed(2);

                        if (Number(vm.paymentObj.pendingPayment) < 0) {
                            vm.paymentObj.pendingPayment = 0;
                        }

                        vm.paymentInput.payStatus = vm.ctObj.premiumReceiptList[i].payStatus;// 支付状态

                        //记录该条投保费状态，方便后面状态还原
                        vm.premiumPayStatus = vm.paymentInput.payStatus;

                        vm.markListCode = vm.ctObj.premiumReceiptList[i].premiumCode;

//                        vm.paymentInput.hasTax = vm.ctObj.premiumReceiptList[i].hasTax;// 是否含税
//                        vm.paymentInput.taxPoint = vm.ctObj.premiumReceiptList[i].taxPoint;// 税点

//                        vm.paymentInput.allPay = 0;
//
//                        //当前缴费金额置为空
//                        vm.paymentInput.thePayment = "";
//                        //缴费之间置为空
//                        vm.paymentInput.payDate ="";
                        break;
                    }
                }
            },
            // 保险费缴费提交
            submitPremiumForm(formName){
                let vm = this;
                let params = {};

                for (let i = 0; i < vm.ctObj.premiumReceiptList.length; i++) {
                    if (vm.markListCode == vm.ctObj.premiumReceiptList[i].premiumCode) {

                        vm.paymentInput.id = vm.ctObj.premiumReceiptList[i].id;
                        vm.paymentInput.premiumCode = vm.ctObj.premiumReceiptList[i].premiumCode;

                        if (vm.paymentInput.payStatus != "2" && vm.paymentInput.payStatus != "3" && vm.paymentInput.thePayment > 0) {
                            vm.paymentInput.payStatus = "4";
                        }

                        // 已收金额 = 已收金额 + 缴费金额（本次缴费）（一期分多次缴费的情况，已收加上本次缴费金额）
                        vm.paymentInput.paidPrice = Number(vm.ctObj.premiumReceiptList[i].paidPrice) + Number(vm.paymentInput.thePayment);

                        params.entity = vm.paymentInput;

                        let resUrl = 'premium/pay';
                        let msg = '保险缴费';
                        vm.holdPremiumPaymentWindow = false;
                        vm.methodTemplate(formName, vm, params, resUrl, msg);
                        break;
                    }
                }
            },
            // 打开修改保险费弹窗
            openUpdatePremium(premiumCode){
                let vm = this;

                vm.holdPremiumEditPaymentWindow = true;

                //循环租金，含下期
                for (let i = 0; i < vm.ctObj.premiumReceiptList.length; i++) {
                    if (premiumCode == vm.ctObj.premiumReceiptList[i].premiumCode) {
                        vm.editPremiumObj = vm.ctObj.premiumReceiptList[i];

                        //记录该条投保费状态，方便后面状态还原
                        vm.premiumPayStatus = vm.editPremiumObj.payStatus;

                        // 余额 = 已付金额 - 应付金额
                        let overPrice = vm.editPayment.paidPrice - vm.editPayment.totalPrice;

                        if (!vm.editPayment.thePrice) {
                            vm.editPayment.thePrice = 0;
                        }
                        if (overPrice <= 0) {
                            vm.editPremiumObj.overPrice = 0;
                        } else {
                            vm.editPremiumObj.overPrice = overPrice;
                        }
                        break;
                    }
                }
            },
            // 修改保险费
            updatePremium(submitForm){
                let vm = this;

                let params = {
                    entity: vm.editPremiumObj,
                };
                vm.holdPremiumEditPaymentWindow = false;
                vm.methodTemplate(submitForm, vm, params, "premium/update", "修改保险缴费信息");
            },
            // 取消添加保险费
            cancleEditPremium(formName){
                this.$refs[formName].resetFields();
                this.holdPremiumPaymentWindow = false;
            },
        }
    }

</script>

<style scoped>

    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .root {
        min-height:900px;
        min-width: 1000px;
        background-color: rgb(242, 242, 242);
    }

    /*缴费弹窗*/
    .pay-box {
        margin-top: 0px;
        text-align: left;
        line-height: 20px;
        width: 105%;
    }

    .search_box {
        width: 100%;
    }

    .search_buttons {
        background-color: white;
        text-align: left;
        height: 50px;
        line-height: 50px;
        margin-top: -56px;
        position: fixed;
        z-index: 200;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*租客信息框*/
    .contract_box_top {
        margin-top: 6px;
    }

    .detail_box {
        height: 70vh;
        overflow-y: auto;
        overflow-x: hidden;
        float: left;
        width: 100%;
    }

    .contract_box_data {
        background-color: white;
        margin-top: 10px;
        width: 100%;
        font-size: 14px;
    }

    .contract_title {
        font-size: 16px;
        color: #409EFF;
    }

    .contract_box_up_left_text span {
        margin-left: 20px;
    }

    .contract_box_up_right {
        float: left;
        margin-left: 20px;
    }

    .contract_code {
        height: 50px;
        line-height: 50px;
    }

    .contract_box_down_title span {
        font-size: 16px;
        font-weight: bolder;
    }
    .houseInfo_ul li {
        height: 30px;
        line-height: 30px;
        float: left;
        list-style: none;
        margin-right: 40px;
        margin-left: -40px;
        width: 33%;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .houseInfo_ul span {
        color: #999999;
    }

    .billingRule_ul li {
        height: 30px;
        line-height: 30px;
        float: left;
        list-style: none;
        margin-right: 40px;
        margin-left: -40px;
        width: 50%;
    }

    .billingRule_ul span {
        color: #999999;
    }

    /*应收详情*/
    .billingDetail {
        background-color: white;
        height: 50px;
    }

    .billingDetail_top {
        height: 50px;
        line-height: 50px;
        text-align: left;
        padding-left: 20px;
        font-size: 18px;
        color: #409EFF;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    .billingDetail_top_right {
        height: 50px;
        line-height: 50px;
        float: right;
        margin-right: 30px;
        color: red;
        font-size: 14px;
        cursor: pointer;
    }

    .notify {
        width: 200px;
    }

    .billingDetail_button {
        width: 100px;
    }

    .this_billing_title {
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid rgb(242, 242, 242);
        padding-left: 20px;
        font-size: 16px;
    }

    .this_billing_div {
        text-align: left;
    }

    .this_billing_text1_top_d2 span {
        margin-right: 20px;
        color: #999999;
    }

    /*文字超长省略号代替*/
    .ellipsis {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    /*应收表格*/
    .costTable{
        line-height: 40px;
        text-align: center;
        width: 100%;
    }

    .contract_box_addRent{
        height: 50px;
        line-height:50px;
        float: left;
        margin-left: 20px;
    }

    /**********************************************/
    .common_detail_result_div{
        height: 50px;
        width: 100%;
        background-color: white;
        margin-top: 10px;
    }

    /*添加费用按钮*/
    .add_cost_button{
        float: left;
        margin-left: 20px;
        margin-top: 10px;
    }

</style>