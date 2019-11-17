<template>
    <div class="has-position_root" ref="myRoot">
        <!-- 导航栏 -->
        <div class="has-position_header">
            <el-button @click="parkUpdate()" v-if="showEditParkBtn" icon="el-icon-edit-outline" class="navigation_button" size="small" type="primary">
                修改
            </el-button>
            <el-button class="navigation_button" @click="contractCreate()" size="small" type="primary">
                入驻办理
            </el-button>
        </div>

        <el-form class="has-position_main" ref="pkObj" :model="pkObj" label-suffix="：">

            <div class="titles_div">
                <span class="title_font">运营信息</span>
            </div>
            <div class="common_content_div">
                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="已出租面积">
                                <span>{{parkListObj.rentInvestArea}}㎡</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="承租面积">
                                <span>{{parkListObj.acreage}}㎡</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="意向客户">
                                <span>{{parkListObj.intentCusCount}}个</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="项目毛利率">
                                <span>{{parkListObj.projectGross}}%</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="剩余面积">
                                <span>{{parkListObj.rentableArea}}㎡</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="计租面积">
                                <span>{{(parkListObj.rentInvestArea+parkListObj.rentableArea).toFixed(2)}}㎡</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="入驻企业">
                                <span>{{parkListObj.cusCount}}个</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="出租毛利率">
                                <span>{{parkListObj.investGross}}%</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="空置率">
                                <span>{{parkListObj.areaProp*100 | numFilter}}%</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="理论公摊比">
                                <span>{{parkListObj.areaRate}}%</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <!-- 没有添加楼栋信息时显示，根据buildingTotal循环出总共楼栋数 -->
            <div v-if="pkObj.buildingList.length == 0" v-for="(item, index) in pkObj.buildingTotal" :key="index" class="common_content_div" style="margin-top: 10px;">
                <!-- 楼栋标题 -->
                <div style="height: 50px; border-bottom: 1px solid rgb(242, 242, 242);">
                    <span class="title_font">楼栋：{{ index + 1}}</span>
                </div>
                <el-button class="add_this_building" @click="buildingCreate(pkObj.pkCode, (index + 1))" v-if="showAddBuildBtn" size="small" type="primary">
                    添加本栋信息
                </el-button>
                <div class="clear"></div>
            </div>

            <div v-if="pkObj.buildingList.length != 0" v-for="(item, index) in pkObj.buildingList" :key="index" class="common_content_div" style="margin-top: 10px;">
                <!-- 楼栋标题 -->
                <div style="height: 50px; border-bottom: 1px solid rgb(242, 242, 242);">
                    <span v-if="item.bdCode" class="title_font">楼栋：{{item.bdName}}，共&nbsp;{{ item.floorsTotal}}&nbsp;层</span>
                    <span v-if="!item.bdCode" class="title_font">楼栋信息为空</span>
                </div>
                <div v-if="item.bdCode != null" @click="buildingDetails(item.pkCode, item.bdCode, index + 1)">
                    <!-- 楼层信息 -->
                    <div v-if="item.floorList.length != 0" v-for="(itemF, index) in item.floorList" :key="itemF.flCode" class="building_list_div" :class="{ floorStyle: (index+1) % 3 != 0 }">
                        <el-popover placement="bottom" width="500" trigger="hover">
                            <p style="text-align: center; font-size: 18px; font-weight: bold;">{{itemF.thisFloor}}&nbsp;层入驻企业</p>
                            <el-table :data="itemF.floorContractHouseEntityList" height="220px" :summary-method="conSummaries" show-summary @row-dblclick="dblclickContractDetail">
                                <el-table-column type="index" width="50" label="序号"></el-table-column>
                                <el-table-column property="ctName" label="合同编号"></el-table-column>
                                <el-table-column property="companyName" label="公司名"></el-table-column>
                                <el-table-column property="relateName" width="70" label="客户名"></el-table-column>
                                <el-table-column property="ctInvestArea" width="80" label="出租面积"></el-table-column>
                                <el-table-column min-width="20px" fixed="right" align="center" label="操作" width="50">
                                    <template slot-scope="scope">
                                        <el-button @click="dblclickContractDetail(scope.row)" size="mini" type="text">
                                            详情
                                        </el-button>
                                    </template>
                                </el-table-column>
                            </el-table>

                            <div v-if="itemF.flCode != null" slot="reference" style="margin-top: 10px;">
                                <ul class="floor_data_ul">
                                    <li style="width: 28%;margin-left: 10px;text-align: left;" :title="itemF.investArea">总面积:{{ itemF.investArea }}㎡</li>
                                    <li style="width: 10px;background-color: #D9E5E4;"></li>
                                    <li style="width: 28%;color: #9c9c9c;text-align: left;" :title="itemF.rentableArea">可出租:{{ itemF.rentableArea }}㎡</li>
                                    <li style="width: 10px;background-color: #178679;"></li>
                                    <li style="width: 28%;color: #178679;text-align: left;" :title="Number(itemF.rentedArea).toFixed(2)">
                                        已出租:{{ Number(itemF.rentedArea).toFixed(2) }}㎡
                                    </li>
                                </ul>

                                <div style="clear:both"></div>

                                <div class="floor_num_style">
                                    {{itemF.thisFloor | num}}层
                                </div>

                                <div :id="itemF.flCode" v-bind:style="{width: echartsDivWidth + 'px'}" class="echarts_style"></div>


                                <div style="clear:both"></div>
                            </div>
                        </el-popover>
                        <!--<el-button v-if="itemF.flCode == null"  type="primary" class="add_this_building">添加层信息</el-button>-->
                    </div>

                    <!-- 楼栋没有录入楼层信息的情况下 -->
                    <div v-if="item.floorList.length == 0" class="building_list_div">
                        <el-button type="primary" class="add_this_building">添加层信息</el-button>
                    </div>
                    <div style="clear:both"></div>
                </div>

                <!-- 添加楼栋 -->
                <div class="buildinglist_title" v-if="item.bdCode == null">
                    <el-button class="add_this_building" type="primary" @click="buildingCreate(item.pkCode, (index + 1))">
                        添加本栋信息
                    </el-button>
                </div>
            </div>

            <!--&lt;!&ndash; 楼栋没有录入楼层的情况下 &ndash;&gt;-->
            <!--<div v-if="pkObj.buildingList.length == 0" v-for="(item, index) in pkObj.buildingTotal" :key="index" class="common_content_div" style="margin-top: 10px;">-->
            <!--&lt;!&ndash; 楼栋标题 &ndash;&gt;-->
            <!--<div style="height: 50px; border-bottom: 1px solid rgb(242, 242, 242);">-->
            <!--<span class="title_font">楼栋：{{ index + 1}}</span>-->
            <!--</div>-->
            <!--<el-button class="add_this_building" @click="buildingCreate(pkObj.pkCode, (index + 1))" v-if="showAddBuildBtn" size="small" type="primary">-->
            <!--添加本栋信息-->
            <!--</el-button>-->
            <!--<div class="clear"></div>-->
            <!--</div>-->

            <!--基本信息-->
            <div class="titles_div">
                <span class="title_font">基本信息</span>
            </div>
            <div class="common_content_div">
                <el-row class="common_el_row_style">
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <div class="common_el_col_div_style">
                            <el-form-item label="园区名称">
                                <span>{{pkObj.pkName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="所属板块">
                                <span>{{pkObj.landBlock}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="楼栋总数">
                                <span>{{pkObj.buildingTotal}}栋</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="地产年限">
                                <span>{{pkObj.estateYear}}年</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="总用电容量">
                                <span>{{pkObj.useElecTotal}}kVA</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <!--<el-row class="common_el_row_style">-->
                <!--<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">-->
                <!--<div class="common_el_col_div_style">-->
                <!--<el-form-item label="承租面积">-->
                <!--<span>{{pkObj.acreage}}㎡</span>-->
                <!--</el-form-item>-->
                <!--</div>-->
                <!--</el-col>-->
                <!--<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">-->
                <!--<div class="common_el_col_div_style">-->
                <!--<el-form-item label="计租面积">-->
                <!--<span>{{pkObj.investArea}}㎡</span>-->
                <!--</el-form-item>-->
                <!--</div>-->
                <!--</el-col>-->
                <!--<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">-->
                <!--<div class="common_el_col_div_style">-->
                <!--<el-form-item label="剩余计租面积">-->
                <!--<span>{{pkObj.rentableArea}}㎡</span>-->
                <!--</el-form-item>-->
                <!--</div>-->
                <!--</el-col>-->
                <!--<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">-->
                <!--<div class="common_el_col_div_style">-->
                <!--<el-form-item label="出租计租面积">-->
                <!--<span>{{pkObj.rentInvestArea}}㎡</span>-->
                <!--</el-form-item>-->
                <!--</div>-->
                <!--</el-col>-->
                <!--</el-row>-->

                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="可招商空地">
                                <span>{{pkObj.investmentVacantland}}㎡</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="公摊比">
                                <span>{{pkObj.areaRate}}%</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="停车位数量">
                                <span>{{pkObj.parkingSpace}}个</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="道路宽度">
                                <span>{{pkObj.roadWidth}}m</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="最短租期">
                                <span>{{pkObj.shortestLease}}个月</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="最长租期">
                                <span>{{pkObj.longestLease}}个月</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <div class="common_el_col_div_style">
                            <el-form-item label="限制行业">
                                <span>{{pkObj.industryRestrictions}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row class="common_el_row_style">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="common_el_col_div_style">
                            <el-form-item label="配套信息">
                                <el-tag v-if="pkObj.hasEia" type="success">可环评</el-tag>
                                <el-tag v-if="pkObj.hasCertify" type="success">有产证</el-tag>
                                <el-tag v-if="pkObj.hasSewageCertify" type="success">有排污证</el-tag>
                                <el-tag v-if="pkObj.hasSupermarket" type="success">有超市</el-tag>
                                <el-tag v-if="pkObj.hasCanteen" type="success">有食堂</el-tag>
                                <el-tag v-if="pkObj.hasPubBooth" type="success">有公摊</el-tag>
                                <el-tag v-if="pkObj.hasOfficeBuild" type="success">有办公楼</el-tag>
                                <el-tag v-if="pkObj.hasCanopy" type="success">有雨棚</el-tag>
                                <el-tag v-if="pkObj.hasCarPark" type="success">有停车场</el-tag>
                                <el-tag v-if="pkObj.hasLoadDock" type="success">有卸货平台</el-tag>
                                <el-tag v-if="pkObj.hasChargingPile" type="success">有充电桩</el-tag>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <div class="titles_div">
                <span class="title_font">位置信息</span>
            </div>
            <div class="common_content_div">
                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="所属大区">
                                <span>{{pkObj.territoryTypeName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row class="common_el_row_style">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="common_el_col_div_style">
                            <el-form-item label="园区地址">
                                <span>{{pkObj.provinceName+pkObj.cityName+pkObj.regionName+pkObj.streetName+pkObj.address}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <!--<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">-->
                    <!--<div class="common_el_col_div_style">-->
                    <!--<el-form-item label="所属城市">-->
                    <!--<span>{{pkObj.cityName}}</span>-->
                    <!--</el-form-item>-->
                    <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">-->
                    <!--<div class="common_el_col_div_style">-->
                    <!--<el-form-item label="所属区域">-->
                    <!--<span>{{pkObj.regionName}}</span>-->
                    <!--</el-form-item>-->
                    <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">-->
                    <!--<div class="common_el_col_div_style">-->
                    <!--<el-form-item label="所属街道">-->
                    <!--<span>{{pkObj.streetName}}</span>-->
                    <!--</el-form-item>-->
                    <!--</div>-->
                    <!--</el-col>-->
                </el-row>
                <!--<el-row class="common_el_row_style">-->
                <!--<el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">-->
                <!--<div class="common_el_col_div_style">-->
                <!--<el-form-item label="详细地址">-->
                <!--<span>{{pkObj.address}}</span>-->
                <!--</el-form-item>-->
                <!--</div>-->
                <!--</el-col>-->
                <!--</el-row>-->
            </div>

            <div class="titles_div">
                <span class="title_font">其他信息</span>
            </div>
            <div class="common_content_div">
                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="经营开始日" size="small">
                                <span>{{pkObj.businessStartDay | timeDate}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="经营到期日" size="small">
                                <span>{{pkObj.businessEndDay | timeDate }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="拓展价格" size="small">
                                <span>{{pkObj.averagePrice}}元/㎡/天</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="每月抄表日" size="small">
                                <span>{{pkObj.meterReading}}号</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="物业费" size="small">
                                <span>{{pkObj.propertyCost}}元/㎡/月</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="common_el_col_div_style">
                            <el-form-item label="剩余电量" size="small">
                                <span>{{pkObj.useElecSurplus}}kVA</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row class="common_el_row_style">
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                        <div class="common_el_col_div_style">
                            <el-form-item label="主导行业">
                                <span>{{pkObj.leadTrade}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row class="common_el_row_style">
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                        <div class="common_el_col_div_style">
                            <el-form-item label="交通配套">
                                <span>{{pkObj.trafficMatching}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row class="common_el_row_style">
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                        <div class="common_el_col_div_style">
                            <el-form-item label="优惠政策">
                                <span>{{pkObj.favouredPolicy}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <div class="titles_div">
                <span class="title_font">招商对接人员</span>
                <el-button @click="dialogFormVisible = true" v-if="showAddInvestDockPersonBtn" class="title_button" size="small" type="primary">
                    添加对接人员
                </el-button>
                <el-dialog :visible.sync="dialogFormVisible" class="buttjoint_add_dialog">
                    <el-form :model="form">
                        <el-form-item label="选择人员" :label-width="120">
                            <el-select style="width: 70%" v-model="form.empCode" filterable placeholder="请选择">
                                <el-option v-for="item in empAtiList" :key="item.empCode" :label="item.empName" :value="item.empCode">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="责任描述" :label-width="120">
                            <el-input style="width: 70%" v-model="form.dutyDescription" type="textarea"></el-input>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="parkButtJintAdd()">确 定</el-button>
                    </div>
                </el-dialog>
            </div>
            <div class="common_content_div">
                <div class="table_div_style">
                    <el-table border class="el_table_style" :data="atiButtJointList">
                        <el-table-column align="center" prop="empName" label="姓名">
                        </el-table-column>
                        <el-table-column align="center" prop="empPhone" label="电话">
                        </el-table-column>
                        <el-table-column align="center" prop="postName" label="岗位">
                        </el-table-column>
                        <el-table-column align="center" prop="dutyDescription" label="责任描述">
                        </el-table-column>
                        <el-table-column align="center" label="操作">
                            <template slot-scope="scope">
                                <el-button @click.native.prevent="deleteRow(atiButtJointList[scope.$index],1)" type="text" size="small">
                                    移除
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>

            <!-- 物业对接人员 -->
            <div class="titles_div">
                <span class="title_font">物业对接人员</span>
                <el-button v-if="showAddProDockPersonBtn" class="title_button" size="small" type="primary" @click="dialogFormVisible1 = true">
                    添加对接人员
                </el-button>
                <el-dialog :visible.sync="dialogFormVisible1" class="buttjoint_add_dialog">
                    <el-form :model="form">
                        <el-form-item label="选择人员" :label-width="120">
                            <el-select style="width: 70%" v-model="form.empCode" filterable placeholder="请选择">
                                <el-option v-for="item in empProList" :key="item.empCode" :label="item.empName" :value="item.empCode">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="责任描述" :label-width="120">
                            <el-input style="width: 70%" v-model="form.dutyDescription" type="textarea"></el-input>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="proButtJintAdd()">确 定</el-button>
                    </div>
                </el-dialog>
            </div>
            <div class="common_content_div">
                <div class="table_div_style">
                    <el-table border class="el_table_style" :data="proButtJointList">
                        <el-table-column align="center" prop="empName" label="姓名">
                        </el-table-column>
                        <el-table-column align="center" prop="empPhone" label="电话">
                        </el-table-column>
                        <el-table-column align="center" prop="postName" label="岗位">
                        </el-table-column>
                        <el-table-column align="center" prop="dutyDescription" label="责任描述">
                        </el-table-column>
                        <el-table-column align="center" label="操作">
                            <template slot-scope="scopee">
                                <el-button @click.native.prevent="deleteRow(proButtJointList[scopee.$index],2)" type="text" size="small">
                                    移除
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>

            <!-- 来访人员记录 -->
            <div class="titles_div">
                <span class="title_font">来访人员记录</span>
                <el-input placeholder="支持客户姓名、电话查询" @keyup.enter.native="keywordSelect()" v-model="keyword" size="small" class="title_input" clearable>
                    <el-button @click="keywordSelect()" slot="append" icon="el-icon-search"></el-button>
                </el-input>
            </div>
            <div class="common_content_div">
                <div class="table_div_style">
                    <el-table border :data="followupList" class="el_table_style">
                        <el-table-column align="center" prop="cusName" label="客户名称">
                        </el-table-column>
                        <el-table-column align="center" prop="cusPhone" label="客户电话">
                        </el-table-column>
                        <el-table-column align="center" prop="createTime" label="来访时间">
                            <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                        </el-table-column>
                        <el-table-column align="center" prop="cusStatusName" label="客户状态">
                        </el-table-column>
                        <el-table-column align="center" prop="followupRes" label="来访结果">
                        </el-table-column>
                        <el-table-column align="center" prop="createName" label="接待人员">
                        </el-table-column>
                    </el-table>
                </div>
            </div>

            <!-- 租客信息 -->
            <div class="titles_div">
                <span class="title_font">租客信息</span>
                <el-input placeholder="支持联系人姓名、电话、公司名查询" @keyup.enter.native="keywordSelect1()" v-model="keyword1" size="small" style="margin-left: 20px;" class="title_input" clearable>
                    <el-button @click="keywordSelect1()" slot="append" icon="el-icon-search"></el-button>
                </el-input>
                <el-button class="title_button" size="small" @click="contractCreate()" type="primary">入驻办理</el-button>
            </div>
            <div class="common_content_div">
                <div class="table_div_style">
                    <el-table border :summary-method="getSummaries" show-summary :data="contractList" class="el_table_style">
                        <el-table-column align="center" width="50" type="index">
                        </el-table-column>
                        <el-table-column align="center" prop="ctName" label="合同编号">
                        </el-table-column>
                        <el-table-column align="center" prop="companyName" label="公司名称">
                        </el-table-column>
                        <el-table-column align="center" prop="relateName" label="联系人">
                        </el-table-column>
                        <el-table-column align="center" prop="relatePhone" label="联系电话">
                        </el-table-column>
                        <el-table-column align="center" prop="tradeName" label="行业">
                        </el-table-column>
                        <el-table-column align="center" prop="totalRentalArea" label="所租面积">
                        </el-table-column>
                        <el-table-column align="center" prop="deliveryDateConvert" label="入住时间">
                        </el-table-column>
                        <el-table-column align="center" prop="contractEndTimeConvert" label="到期时间">
                        </el-table-column>
                        <el-table-column align="center" label="操作">
                            <template slot-scope="scopee">
                                <el-button @click.native.prevent="contractDetails(contractList[scopee.$index].ctCode)" type="text" size="small">
                                    租客详情
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>

            <div class="titles_div">
                <span class="title_font">影像资料</span>
            </div>
            <div class="block_box_img">
                <div class="video_title">
                    <span>视频展示</span>
                </div>

                <div v-for="(video,index) in videoList" :key="index">
                    <video class="video" controls autobuffer>
                        <source :src=" parkBaseUrl + 'file/read/' + video " />
                    </video>
                </div>
                <div class="clear"></div>
            </div>

            <div class="block_box_img">
                <div class="video_title">
                    <span>园区鸟瞰</span>
                </div>

                <div v-for="(img,index) in aerialViewList" :key="index">
                    <img class="video" :src=" parkBaseUrl + 'file/read/' + img ">
                </div>
                <div class="clear"></div>
            </div>

            <div class="block_box_img">
                <div class="video_title">
                    <span>园区环境</span>
                </div>

                <div v-for="(img,index) in outPictureList" :key="index">
                    <img class="video" :src=" parkBaseUrl + 'file/read/' + img ">
                </div>
                <div class="clear"></div>
            </div>

            <div style="height: 100px;"></div>
        </el-form>
    </div>
</template>


<script>
    import file from '../../../common/components/file';
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        components: {
            ElCol,
            ElRow,
            file
        },
        data() {
            return {
                showEditParkBtn: false,
                showAddBuildBtn: false,
                showAddInvestDockPersonBtn: false,
                showAddProDockPersonBtn: false,
                showAddVisitBtn: false,

                parkListObj: {},
                empAtiList: {},
                empProList: {},
                videoList: {},
                aerialViewList: {},
                outPictureList: {},
                pkObj: {
                    territoryType: '',
                    territoryTypeName: '',
                    pkName: "",
                    acreage: "",
                    investArea: "",
                    areaRate: "",
                    rentPrice: "",
                    priceUnit: "",
                    parkArea: "",
                    rentableArea: "",
                    vacantArea: "",
                    landBlock: "",
                    estateYear: "",
                    rentArea: "",
                    useElecTotal: "",
                    investmentVacantland: "",
                    parkingSpace: "",
                    roadWidth: "",
                    maxPassCar: "",
                    propertyCost: "",
                    propertyCompany: "",
                    longestLease: "",
                    shortestLease: "",
                    manageYear: "",
                    buildingTotal: "",
                    industryRestrictions: "",

                    hasCertify: false,
                    hasEia: false,
                    hasSewageCertify: false,
                    hasSupermarket: false,
                    hasCanteen: false,
                    hasPubBooth: false,
                    hasOfficeBuild: false,
                    hasCanopy: false,
                    hasCarPark: false,
                    hasLoadDock: false,
                    hasChargingPile: false,

                    provinceCode: "",
                    cityCode: "",
                    regionCode: "",
                    streetCode: "",
                    provinceName: "",
                    cityName: "",
                    regionName: "",
                    streetName: "",

                    address: "",

                    businessStartDay: "",
                    businessEndDay: "",
                    meterReading: "",
                    averagePrice: "",
                    useElecSurplus: "",

                    buildTitle: "",
                    leadTrade: "",
                    trafficMatching: "",
                    favouredPolicy: "",
                    describe: "",

                    buildingList: [{
                        bdName: "",
                        bdCode: "",
                        investArea: "",
                        rentableArea: "",
                        floorList: [{
                            flCode: "", //层编号
                            thisFloor: "", //所在层
                            investArea: "", //招商总面积
                            rentableArea: "", //可租用面积
                            rentedArea: "" //已租用面积
                        }],
                    }, ],
                },
                itProvinceArr: [],
                itCityArr: [],
                itAreaArr: [],
                itStreetArr: [],


                dialogFormVisible: false,
                dialogFormVisible1: false,
                form: {
                    empCode: '',
                    dutyDescription: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
                tableData: [],
                atiButtJoint: {},
                atiButtJointList: [],
                proButtJointList: [],
                followupList: [{
                    followupTimeConvert: '',
                }],
                contractList: [{
                    deliveryDateConvert: '',
                    contractEndTimeConvert: '',
                }],
                keyword: "",
                keyword1: "",

                isResize: false, //是否自适应再次加载echarts柱状图
                echartsDivWidth: "", //echarts柱状图宽度
            }
        },
        // 修改事件
        updated() {
            let vm = this;
            let arrCen = vm.pkObj.buildingList;
            for (let i = 0; i < arrCen.length; i++) {
                if (arrCen[i].bdCode) {
                    let flCode = "";
                    let rentableArea1 = '';
                    let investArea = '';
                    if (arrCen[i].floorList) {
                        for (let j = 0; j < arrCen[i].floorList.length; j++) {
                            if (arrCen[i].floorList[j].flCode) {
                                flCode = arrCen[i].floorList[j].flCode;
                                rentableArea1 = arrCen[i].floorList[j].rentableArea;
                                investArea = arrCen[i].floorList[j].investArea;
                                vm.handleMoreEchart(flCode, investArea, rentableArea1);
                            }
                        }
                    }
                }
            }
        },
        props: ['paramsObj'], // 接收父组件数据
        created: function() {
            let vm = this;

            // 菜单和按钮权限控制
            vm.showEditParkBtn = vm.$flagMenuStore.judgeMenu("editParkBtn");
            vm.showAddBuildBtn = vm.$flagMenuStore.judgeMenu("addBuildBtn");
            vm.showAddInvestDockPersonBtn = vm.$flagMenuStore.judgeMenu("addInvestDockPersonBtn");
            vm.showAddProDockPersonBtn = vm.$flagMenuStore.judgeMenu("addProDockPersonBtn");
            vm.showAddVisitBtn = vm.$flagMenuStore.judgeMenu("addVisitBtn"); // 添加来访

            if (vm.paramsObj && vm.paramsObj.pkCode) {
                vm.parkListObj = vm.paramsObj;
                let pkCode = vm.paramsObj.pkCode;

                let params = {
                    entity: {
                        pkCode: pkCode
                    }
                };
                vm.selectParkDetail(params); // 查询园区详情
                vm.selectVisitor(params); // 查询园区来访人员
                vm.selectLessee(); // 查询租户列表
                vm.selectEmp(params); // 查询招商人员列表、物业人员
            }
            vm.isResize = false;
        },
        filters: {
            numFilter(value) {
                // 截取当前数据到小数点后两位
                let realVal = Number(value).toFixed(2);
                // num.toFixed(2)获取的是字符串
                return Number(realVal);
            }
        },
        methods: {
            getSummaries(param) {
                const {
                    columns,
                    data
                } = param;
                const sums = [];

                columns.forEach((column, index) => {
                    if (index === 0) {
                        sums[index] = '合计';
                        return;
                    }

                    const values = data.map(item => Number(item[column.property]));
                    if (index != 1 && index != 4) {
                        if (!values.every(value => isNaN(value))) {
                            sums[index] = values.reduce((prev, curr) => {
                                const value = Number(curr);
                                if (!isNaN(value)) {
                                    return prev + curr;
                                } else {
                                    return prev;
                                }
                            }, 0);
                            //                            sums[index] += ' ㎡';
                        }
                    }

                });

                return sums;
            },

            //楼层出租情况面积合计
            conSummaries(param) {
                const {
                    columns,
                    data
                } = param;
                const sums = [];

                columns.forEach((column, index) => {
                    if (index === 0) {
                        sums[index] = '合计';
                        return;
                    }

                    const values = data.map(item => Number(item[column.property]));
                    if (index != 1) {
                        if (!values.every(value => isNaN(value))) {
                            sums[index] = values.reduce((prev, curr) => {
                                const value = Number(curr);
                                if (!isNaN(value)) {
                                    return prev + curr;
                                } else {
                                    return prev;
                                }
                            }, 0);
                        }
                    }

                });

                return sums;
            },

            // 查询园区详情
            selectParkDetail(params) {
                let vm = this;

                let options = {
                    method: "POST",
                    data: params,
                    url: "contract/parkDetail",
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.pkObj = response.data.result;
                        if (vm.pkObj.atiButtJointList) {
                            vm.atiButtJointList = vm.pkObj.atiButtJointList;
                            vm.proButtJointList = vm.pkObj.proButtJointList;
                        }

                        //从localStorage获取大区
                        let deptJson = window.localStorage.getItem("_usedQueryDept");
                        let deptList = JSON.parse(deptJson);
                        for (let i = 0; i < deptList.length; i++) {
                            if (deptList[i].deptCode == vm.pkObj.territoryType) {
                                vm.pkObj.territoryTypeName = deptList[i].deptName;
                                break;
                            }
                        }
                    }

                    if (vm.pkObj.parkVideo) {
                        vm.videoList = vm.pkObj.parkVideo.split(",");
                    }
                    if (vm.pkObj.aerialView) {
                        vm.aerialViewList = vm.pkObj.aerialView.split(",");
                    }
                    if (vm.pkObj.outPicture) {
                        vm.outPictureList = vm.pkObj.outPicture.split(",");
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询园区详细信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
            // 查询园区来访人员
            selectVisitor(params) {
                let vm = this;

                let options = {
                    method: "POST",
                    data: params,
                    url: "followup/query",
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.followupList = response.data.result.data;
                    }
                    if (vm.followupList) {
                        for (let i = 0; i < vm.followupList.length; i++) {
                            let newDate = new Date(parseInt(vm.followupList[i].followupTime));
                            vm.followupList[i].followupTimeConvert = newDate.format('yyyy-MM-dd hh:mm:ss');
                        }
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询来访人员信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
            // 查询租户列表
            selectLessee() {
                let vm = this;

                let params = {
                    entity: {
                        pkCode: vm.paramsObj.pkCode
                    },
                    ctStatusFlag: true
                };

                let options = {
                    method: "POST",
                    data: params,
                    url: "contract/query",
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.contractList = response.data.result.data;
                    }
                    if (vm.contractList) {
                        for (let i = 0; i < vm.contractList.length; i++) {
                            let newDate = new Date(parseInt(vm.contractList[i].contractStartTime));
                            vm.contractList[i].deliveryDateConvert = newDate.format('yyyy-MM-dd hh:mm:ss');

                            let newDatee = new Date(parseInt(vm.contractList[i].contractEndTime));
                            vm.contractList[i].contractEndTimeConvert = newDatee.format('yyyy-MM-dd hh:mm:ss');
                        }
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询租客列表失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
            // 查询招商人员列表
            selectEmp() {
                let vm = this;

                let params = {
                    entity: {}
                };

                let options = {
                    method: "POST",
                    data: params,
                    url: "employee/list",
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.empAtiList = response.data.result;
                        vm.empProList = response.data.result;
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询招商人员列表失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // 园区修改
            parkUpdate() {
                let data = {
                    menuName: '园区修改',
                    item: 'parkEdit',
                    params: {
                        pkCode: this.pkObj.pkCode,
                    }
                };
                this.$emit('backParam', data);
            },
            handleMoreEchart(flCode, investArea, rentableArea) {
                this.showChart(flCode, investArea, rentableArea);
            },
            // 楼栋详情
            buildingDetails(pkCode, bdCode, index) {
                let data = {
                    menuName: '楼栋详情',
                    item: 'buildingDetails',
                    params: {
                        pkCode: pkCode,
                        pkName: this.pkObj.pkName,
                        bdCode: bdCode,
                        thisBuilding: index
                    }
                };
                this.$emit('backParam', data);
            },
            //合同详情
            dblclickContractDetail(row, column) {
                let data = {
                    flag: "add",
                    menuName: "合同详情",
                    item: "contractDetails",
                    params: {
                        ctCode: row.ctCode
                    }
                };
                this.$emit("backParam", data);

            },
            // 新增楼栋
            buildingCreate(pkCode, index) {
                let data = {
                    menuName: '添加楼栋',
                    item: 'buildingCreate',
                    params: {
                        pkCode: pkCode,
                        pkName: this.pkObj.pkName,
                        thisBuilding: index
                    }
                };
                this.$emit('backParam', data);
            },

            //设置容器高宽
            resizeWorldMapContainer(echartsWarp) {
                this.echartsDivWidth = (window.innerWidth - 450) * 0.3;
                echartsWarp.style.width = this.echartsDivWidth + 'px';
            },

            // 柱状图
            showChart(flCode, investArea, rentableArea) {

                let vm = this;
                let num1 = rentableArea / investArea * 100;
                let numberData = num1.toFixed(2);
                let num2 = 100 - numberData;

                let myChart = null;
                let echartsWarp = document.getElementById(flCode);
                if (echartsWarp) {
                    vm.resizeWorldMapContainer(echartsWarp);
                    myChart = vm.$echarts.init(echartsWarp);

                    let option = {
                        grid: {
                            x: 12,
                            y: 20,
                            x2: 0,
                            y2: 20,
                            borderWidth: 1
                        },
                        tooltip: {
                            trigger: 'item',
                        },
                        color: ['#D9E5E4', '#178679'],
                        xAxis: [{
                            type: 'value',
                            show: false
                        }],
                        yAxis: [{
                            type: 'category',
                            show: false,
                            data: ['面积比例']
                        }],
                        series: [{
                                name: '可出租',
                                type: 'bar',
                                stack: '总面积',
                                barWidth: 35,

                                data: [{
                                    value: '',
                                    label: {
                                        normal: {
                                            color: '#333333',
                                            show: true,
                                            formatter: [
                                                '{c}' + '%'
                                            ].join('\n'),
                                        }
                                    }
                                }]
                            },
                            {
                                name: '已出租',
                                type: 'bar',
                                stack: '总面积',
                                barWidth: 35,

                                data: [{
                                    value: '',
                                    label: {
                                        normal: {
                                            color: '#333333',
                                            show: true,
                                            formatter: [
                                                '{c}' + '%'
                                            ].join('\n'),
                                        }
                                    }
                                }]
                            }
                        ]
                    };
                    if (numberData == 100) {
                        option.series[0].data[0].value = numberData;
                        option.series[1].data[0].value = '';
                    } else if (numberData == 0) {
                        option.series[0].data[0].value = '';
                        option.series[1].data[0].value = num2.toFixed(2);
                    } else {
                        option.series[0].data[0].value = numberData;
                        option.series[1].data[0].value = num2.toFixed(2);
                    }
                    myChart.setOption(option);


                    if (vm.isResize) {
                        myChart.resize();
                    }
                    //根据窗口的大小变动图表 --- 重点
                    window.onresize = function() {
                        vm.resizeWorldMapContainer(echartsWarp);
                        myChart.resize();
                        vm.isResize = true;
                    };
                }
            },
            contractCreate() {
                let data = {
                    menuName: '入驻办理',
                    item: 'contractCreate',
                    params: {
                        pkCode: this.pkObj.pkCode,
                    }
                };
                this.$emit('backParam', data);
            },
            parkButtJintAdd() {
                if (this.form.empCode) {
                    let vm = this;
                    for (let i = 0; i < this.empAtiList.length; i++) {
                        if (this.empAtiList[i].empCode == this.form.empCode) {
                            let param = {};
                            param.entity = vm.atiButtJoint;
                            param.entity.pkCode = vm.pkObj.pkCode;
                            param.entity.pkAddress = vm.pkObj.provinceName + vm.pkObj.cityName + vm.pkObj.regionName + vm.pkObj.streetName + vm.pkObj.address;
                            param.entity.buttJointType = 1;
                            param.entity.postName = vm.empAtiList[i].postName;
                            param.entity.dutyDescription = vm.form.dutyDescription;
                            param.entity.empCode = vm.empAtiList[i].empCode;
                            let options = {
                                method: "POST",
                                data: param,
                                url: "parkButtJoint/create",
                            };
                            this.$ajax(
                                options
                            ).then(function(response) {
                                if (!response.data.isException) {
                                    vm.$message({
                                        showClose: true,
                                        message: '新增对接人员信息成功',
                                        type: 'success',
                                    });
                                    //查询此园区下招商对接人员集合
                                    let paramm = {};
                                    paramm.entity = {};
                                    paramm.entity.pkCode = vm.pkObj.pkCode;
                                    paramm.entity.buttJointType = 1;

                                    let option = {
                                        method: "POST",
                                        data: paramm,
                                        url: "parkButtJoint/selectByPkCode",
                                    };
                                    vm.$ajax(
                                        option
                                    ).then(function(response) {
                                        if (!response.data.isException) {
                                            vm.atiButtJointList = response.data.result;
                                        }
                                    }).catch(function(error) {
                                        vm.$message({
                                            showClose: true,
                                            message: '',
                                            type: 'error'
                                        });
                                        vm.$alert('页面：查询招商对接人员列表失败', '系统异常', {
                                            confirmButtonText: '确定',
                                            callback: action => {}
                                        });
                                    });
                                }
                            }).catch(function(error) {
                                vm.$alert('页面：添加对接人员失败', '系统异常', {
                                    confirmButtonText: '确定',
                                    callback: action => {}
                                });
                            });
                        }
                    }

                    this.dialogFormVisible = false;
                } else {
                    this.$message({
                        showClose: true,
                        message: '请选择对接人员再提交',
                        type: 'warning'
                    });
                }
            },
            proButtJintAdd() {
                if (this.form.empCode) {
                    let vm = this;
                    for (let i = 0; i < this.empProList.length; i++) {
                        if (this.empProList[i].empCode == this.form.empCode) {
                            let param = {};
                            param.entity = vm.atiButtJoint;
                            param.entity.pkCode = vm.pkObj.pkCode;
                            param.entity.pkAddress = vm.pkObj.provinceName + vm.pkObj.cityName + vm.pkObj.regionName + vm.pkObj.streetName + vm.pkObj.address;
                            param.entity.buttJointType = 2;
                            param.entity.postName = vm.empProList[i].postName;
                            param.entity.dutyDescription = vm.form.dutyDescription;
                            param.entity.empCode = vm.empProList[i].empCode;
                            let options = {
                                method: "POST",
                                data: param,
                                url: "parkButtJoint/create",
                            };
                            this.$ajax(
                                options
                            ).then(function(response) {
                                if (!response.data.isException) {
                                    vm.$message({
                                        showClose: true,
                                        message: '新增对接人员信息成功',
                                        type: 'success',
                                    });
                                    // 查询此园区下招商对接人员集合
                                    let paramm = {};
                                    paramm.entity = {};
                                    paramm.entity.pkCode = vm.pkObj.pkCode;
                                    paramm.entity.buttJointType = 2;
                                    let option = {
                                        method: "POST",
                                        data: paramm,
                                        url: "parkButtJoint/selectByPkCode",
                                    };
                                    vm.$ajax(
                                        option
                                    ).then(function(response) {
                                        if (!response.data.isException) {
                                            vm.proButtJointList = response.data.result;
                                        }
                                    }).catch(function(error) {
                                        vm.$alert('页面：查询招商对接人员列表失败', '系统异常', {
                                            confirmButtonText: '确定',
                                            callback: action => {}
                                        });
                                    });
                                }
                            }).catch(function(error) {
                                vm.$alert('页面：添加对接人员失败', '系统异常', {
                                    confirmButtonText: '确定',
                                    callback: action => {}
                                });
                            });
                        }
                    }

                    this.dialogFormVisible1 = false;
                } else {
                    this.$message({
                        showClose: true,
                        message: '请选择对接人员再提交',
                        type: 'warning'
                    });
                }
            },
            deleteRow(index, num) {
                this.$confirm('确定移除这条数据?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;

                    let param = {};
                    param.entity = vm.atiButtJoint;
                    param.entity.id = index.id;
                    let options = {
                        method: "POST",
                        data: param,
                        url: "parkButtJoint/delete",
                    };

                    this.$ajax(
                        options
                    ).then(function(response) {
                        if (!response.data.isException) {
                            vm.$message({
                                showClose: true,
                                type: 'success',
                                message: '移除成功!'
                            });

                            //查询此园区下招商对接人员集合
                            let paramm = {};
                            paramm.entity = {};
                            paramm.entity.pkCode = vm.pkObj.pkCode;
                            paramm.entity.buttJointType = num;

                            let option = {
                                method: "POST",
                                data: paramm,
                                url: "parkButtJoint/selectByPkCode",
                            };
                            vm.$ajax(
                                option
                            ).then(function(response) {
                                if (!response.data.isException) {
                                    if (num == 1) {
                                        vm.atiButtJointList = response.data.result;
                                    } else {
                                        vm.proButtJointList = response.data.result;
                                    }
                                }
                            }).catch(function(error) {
                                vm.$alert('页面：查询招商对接人员列表失败', '系统异常', {
                                    confirmButtonText: '确定',
                                    callback: action => {}
                                });
                            });
                        }
                    }).catch(function(error) {
                        vm.$alert('页面：删除对接人员信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }).catch(() => {});
            },

            //来访人员记录关键字查询
            keywordSelect() {
                let vm = this;
                let param = {};
                let obj = vm.pkObj;
                obj = {};
                obj.pkCode = vm.pkObj.pkCode;
                param.entity = obj;
                param.keyword = vm.keyword;
                //查询来访人员列表
                let options = {
                    method: "POST",
                    data: param,
                    url: "followup/query",
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.followupList = response.data.result.data;
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询来访人员信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
            //租客关键字查询
            keywordSelect1() {
                let vm = this;
                let param = {};
                let obj = vm.pkObj;
                obj = {};
                obj.pkCode = vm.pkObj.pkCode;
                param.entity = obj;
                param.keyword = vm.keyword1;
                // 查询来访人员列表
                let options = {
                    method: "POST",
                    data: param,
                    url: "contract/query",
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.contractList = response.data.result.data;
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查租客信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
                //查询租客列表 end
            },
            contractDetails(ctCode) {
                let data = {
                    flag: "add",
                    menuName: "合同详情",
                    item: "contractDetails",
                    params: {
                        ctCode: ctCode
                    }
                };
                this.$emit("backParam", data);
            }
        },


    }
</script>

<style scoped>

    /*------------------------------ 公共样式 ------------------------------*/
    /*标题div*/
    .titles_div {
        background-color: white;
        height: 50px;
        width: 100%;
        border-bottom: 1px solid rgb(242, 242, 242);
        margin-top: 10px;
    }

    /*标题文字*/
    .title_font {
        float: left;
        color: #409eff;
        margin-left: 20px;
        line-height: 50px;
    }

    /*标题中的操作按钮*/
    .title_button {
        float: right;
        margin-right: 20px;
        margin-top: 10px;
    }

    /*标题中的输入框*/
    .title_input {
        float: left;
        margin-top: 10px;
        margin-left: 20px;
        width: 300px;
    }

    /*每个模块，标题下方内容区div*/
    .common_content_div {
        background-color: white;
        text-align: left;
    }

    .common_el_row_style {
        margin-left: 20px;
    }

    /* el-col 样式 */
    .common_el_col_div_style {
        height: 30px;
        line-height: 30px;
    }

    /*------------------------------ 导航栏 ------------------------------*/
    /*导航栏DIV*/
    .navigation_div {
        height: 50px;
        margin-top: -50px;

        position: fixed;
        z-index: 200;
    }

    /*导航栏右边按钮*/
    .navigation_button {
        float: left;
        margin-left: 20px;
    }

    /*表格div样式*/
    .table_div_style {
        width: 100%;
        padding-top: 10px;
        padding-bottom: 10px;
    }

    /*表格样式*/
    .el_table_style {
        line-height: 30px;
        width: 98%;
        margin-left: 1%;
    }

    /*------------------------------ 公共样式 ------------------------------*/

    .floor-data-ul li {
        float: left;
        list-style: none;
        height: 20px;
        line-height: 20px;
        font-size: 13px;
    }

    /*楼层循环 加宽度*/
    .floorStyle {
        margin-right: 2%;
    }

    /*新增建筑div*/
    .add_this_building {
        margin: 10px 0px 10px 20px;
    }

    /*清除浮动*/
    .clear {
        clear: both;
    }

    /*视频信息*/
    .block_box_img {
        background-color: white;
        border-bottom: solid 1px rgb(242, 242, 242);
        padding-bottom: 10px;
    }

    /*视频标题*/
    .video_title {
        float: left;
        width: 6.5%;
        color: #409eff;
        height: 200px;
        line-height: 200px;
        margin-left: 15px;
    }

    .video {
        width: 240px;
        float: left;
        margin-left: 20px;
        height: 160px;
        margin-top: 20px;
    }

    /*添加对接人员弹窗*/
    .buttjoint_add_dialog {
        width: 60%;
        margin: auto;
    }

    /*------------------------- 楼栋和楼层循环信息 -------------------------*/
    /*建筑集合div*/
    .building_list_div {
        float: left;
        width: 32%;
        background-color: white;
        margin-top: 5px;
    }

    /* 楼栋循环，显示楼栋的总面积、已租面积、可租面积 */
    .floor_data_ul {
        margin-top: -5px;
        list-style: none;
        height: 10px;
        line-height: 10px;
        font-size: 13px;
        width: 90%;
    }

    /*总面积,可出租,已出租*/
    .floor_data_ul li {
        float: left;
        list-style: none;
        height: 12px;
        line-height: 12px;
        font-size: 10px;
        margin-top: 10px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    /* 楼层编号 */
    .floor_num_style {
        width: 10%;
        font-size: 13px;
        height: 35px;
        float: left;

        margin-top: 5px;
        margin-left: 10px;
    }

    /* eCharts 样式*/
    .echarts_style {
        height: 35px;
        float: left;

        margin-top: 5px;
        padding-bottom: 10px;
    }
</style>