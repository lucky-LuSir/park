<template>
    <div class="has-position_root" ref="myRoot">
        <!-- 导航栏 -->
        <div class="has-position_header">
            <el-button type="primary" size="small" @click="submitForm('expObj')" class="save-button">
                提交
            </el-button>
        </div>

        <el-form class="root_form" :rules="rules" :model="expObj" ref="expObj" label-width="110px">

            <div class="common-title-div" style="margin-top: 10px;">
                <span class="common-title-font">业主信息</span>
            </div>
            <div class="customer-info-div">
                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="公司名：" prop="parkOwnerCpyName">
                            <el-input v-model="expObj.parkOwnerCpyName" placeholder="请输入业主公司名" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="姓名：" prop="parkOwnerName">
                            <el-input v-model="expObj.parkOwnerName" placeholder="请输入业主公司名" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="性别：" prop="parkOwnerSex">
                            <el-radio-group v-model="expObj.parkOwnerSex">
                                <el-radio label="1">先生</el-radio>
                                <el-radio label="2">女士</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="电话：" prop="parkOwnerPhone">
                            <el-input suffix-icon="el-icon-phone" v-model="expObj.parkOwnerPhone" placeholder="请输入业主公司名"
                                      clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="备用联系人：">
                            <el-input v-model="expObj.parkOwnerReserveContactName" placeholder="请输入业主备用联系人"
                                      clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="性别：">
                            <el-radio-group v-model="expObj.parkOwnerReserveContactSex">
                                <el-radio label="1">先生</el-radio>
                                <el-radio label="2">女士</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="备用电话：">
                            <el-input suffix-icon="el-icon-phone" v-model="expObj.parkOwnerReserveContactPhone"
                                      placeholder="请输入业主备用联系人电话" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">拓展人信息</span>
            </div>
            <div class="customer-info-div">
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="拓展人：" prop="expEmpCode">
                            <el-select v-model="expObj.expEmpCode" @change="changeEmp"
                                       filterable placeholder="请选择">
                                <el-option v-for="item in empList"
                                           :key="item.code"
                                           :label="item.name"
                                           :value="item.code">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="拓展人手机：" prop="expEmpPhone">
                            <el-input v-model="expObj.expEmpPhone" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10" class="input-col1">
                        <el-form-item label="拓展人公司：" prop="expEmpCpyName">
                            <el-input v-model="expObj.expEmpCpyName" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">位置信息</span>
            </div>
            <div class="customer-info-div">
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所在省份：" prop="provinceCode">
                            <el-select id="provinceName"
                                       v-model="expObj.provinceCode"
                                       @visible-change="handNeedProvince"
                                       @change="changeProvCpy"
                                       placeholder="请选择">
                                <el-option v-for="(item, indexkey) in provinceList"
                                           :key="item.areaCode"
                                           :value="item.areaCode"
                                           :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所在城市：" prop="cityCode">
                            <el-select id="cityName"
                                       v-model="expObj.cityCode"
                                       @visible-change="getCityList(expObj.provinceCode)"
                                       @change="changeCityCpy"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in cityList"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所在区域：" prop="regionCode">
                            <el-select id="regionName"
                                       v-model="expObj.regionCode"
                                       @visible-change="getAreaList(expObj.cityCode)"
                                       @change="changeRegionCpy"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in areaList"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所在街道：" prop="streetCode">
                            <el-select id="streetName"
                                       v-model="expObj.streetCode"
                                       @visible-change="getStreetList(expObj.regionCode)"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in streetList"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14" class="input-col1">
                        <el-form-item label="详细地址：" prop="address">
                            <el-input suffix-icon="el-icon-location" v-model="expObj.address" placeholder="请输入详细地址"
                                      clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="距离主城区：" label-width="125px" prop="distanceDowntown">
                            <el-input v-model="expObj.distanceDowntown" placeholder="请输入主城区距离" clearable>
                                <template slot="append">km</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="周边生活区：" prop="distanceNearbyLifeRegion">
                            <el-input v-model="expObj.distanceNearbyLifeRegion" placeholder="请输入周边生活区距离" clearable>
                                <template slot="append">km</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23" class="input-col1">
                        <el-form-item label="是否在工业集中区：" label-width="165px" prop="manufacturingDistrict">
                            <el-radio-group v-model="expObj.manufacturingDistrict">
                                <el-radio label="1">处于工业集中区</el-radio>
                                <el-radio label="2">非工业集中区，但距离在5公里及以内</el-radio>
                                <el-radio label="3">距离工业集中区超过5.1公里</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="离高速入口：" label-width="120px" prop="distanceFreewayEntrance">
                            <el-input v-model="expObj.distanceFreewayEntrance" placeholder="请输入高速公路入口距离" clearable>
                                <template slot="append">km</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="1.5公里内是否有公共交通：" label-width="200px" prop="hasPublicTraffic">
                            <el-radio-group v-model="expObj.hasPublicTraffic">
                                <el-radio :label=true>有</el-radio>
                                <el-radio :label=false>无</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="距离主干道：" label-width="120px" prop="distanceMainStreet">
                            <el-input v-model="expObj.distanceMainStreet" placeholder="请输入主干道距离" clearable>
                                <template slot="append">km</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="车道数：" prop="laneQuantity">
                            <el-input v-model="expObj.laneQuantity" placeholder="请输入双向总车道数" clearable>
                                <template slot="append">个</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="周边价格：" prop="nearbyPrice">
                            <el-input v-model="expObj.nearbyPrice" placeholder="请输入周边价格" clearable>
                                <template slot="append">元/㎡/天</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">厂房装修</span>
            </div>
            <div class="customer-info-div">
                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="厂房形象：">
                            <el-checkbox border v-model="expObj.hasNewPlant">新建厂房</el-checkbox>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="expObj.plantRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="装修情况：" prop="plantDecoration">
                            <el-radio-group v-model="expObj.plantDecoration">
                                <el-radio label="1">厕所楼道地坪无需修整</el-radio>
                                <el-radio label="2">厕所楼道地坪需稍作修整</el-radio>
                                <el-radio label="3">毛坯需要重新修整</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="expObj.decorationRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="送电情况：" prop="plantElectricity">
                            <el-radio-group v-model="expObj.plantElectricity">
                                <el-radio label="1">电已通</el-radio>
                                <el-radio label="2">电未通</el-radio>
                                <el-radio label="3">电基本完成</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="expObj.electricityRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">厂房面积结构</span>
            </div>
            <div class="customer-info-div">
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="总占地面积：" prop="parkArea">
                            <el-input v-model="expObj.parkArea" placeholder="请输入占地总面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="单层厂房：" prop="singleFloorQuantity">
                            <el-input v-model="expObj.singleFloorQuantity" placeholder="请输入单层厂房栋数" clearable>
                                <template slot="append">栋</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="建筑面积：" prop="singleFloorArea">
                            <el-input v-model="expObj.singleFloorArea" placeholder="请输入单层厂房建筑面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="层高：" prop="singleFloorHeight">
                            <el-input v-model="expObj.singleFloorHeight" placeholder="请输入单层厂房楼层高度" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="expObj.singleFloorRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="多层厂房：" prop="moreFloorQuantity">
                            <el-input v-model="expObj.moreFloorQuantity" placeholder="请输入多层厂房栋数" clearable>
                                <template slot="append">栋</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="建筑面积：" prop="moreFloorArea">
                            <el-input v-model="expObj.moreFloorArea" placeholder="请输入多层厂房建筑面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="层高：" prop="moreFloorHeight">
                            <el-input v-model="expObj.moreFloorHeight" placeholder="请输入多层厂房楼层高度" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="expObj.moreFloorRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="电梯数：" prop="moreFloorElevatorQuantity">
                            <el-input v-model="expObj.moreFloorElevatorQuantity" placeholder="请输入电梯数"
                                      clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="17" :sm="17" :md="17" :lg="17" :xl="17" class="input-col1">
                        <el-form-item label="电梯位置：" prop="elevatorLocationList">
                            <el-checkbox-group v-model="expObj.elevatorLocationList">
                                <el-checkbox border label="中间位置双开">中间位置双开</el-checkbox>
                                <el-checkbox border label="两头位置双开">两头位置双开</el-checkbox>
                                <el-checkbox border label="其他情况非双开">其他情况非双开</el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="办公楼：" prop="officeBuildingQuantity">
                            <el-input v-model="expObj.officeBuildingQuantity" placeholder="请输入办公楼栋数" clearable>
                                <template slot="append">栋</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="建筑面积：" prop="officeBuildingArea">
                            <el-input v-model="expObj.officeBuildingArea" placeholder="请输入办公楼面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="层高：" prop="officeBuildingHeight">
                            <el-input v-model="expObj.officeBuildingHeight" placeholder="请输入办公楼楼层高度" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="expObj.officeBuildingRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="其他附房：" prop="otherBuildingQuantity">
                            <el-input v-model="expObj.otherBuildingQuantity" placeholder="请输入办公楼栋数" clearable>
                                <template slot="append">栋</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="建筑面积：" prop="otherBuildingArea">
                            <el-input v-model="expObj.otherBuildingArea" placeholder="请输入办公楼面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="expObj.otherBuildingRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="空地面积：" prop="vacancySoilArea">
                            <el-input v-model="expObj.vacancySoilArea" placeholder="请输入办公楼面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="expObj.vacancySoilRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">配电及消防</span>
            </div>
            <div class="customer-info-div">
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="总用电容量：" prop="totalElectricity">
                            <el-input v-model="expObj.totalElectricity" placeholder="请输入总用电容量">
                                <template slot="append">kva</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="17" :sm="17" :md="17" :lg="17" :xl="17" class="input-col1">
                        <el-form-item label="消防等级 :" prop="firefightingLevel">
                            <el-radio-group v-model="expObj.firefightingLevel">
                                <el-radio label="1">甲类</el-radio>
                                <el-radio label="2">乙类</el-radio>
                                <el-radio label="3">丙一类</el-radio>
                                <el-radio label="4">丙二类</el-radio>
                                <el-radio label="5">丁类</el-radio>
                                <el-radio label="6">戌类</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">商务条款及政策情况</span>
            </div>
            <div class="customer-info-div">
                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="地产年限：" prop="estateYear">
                            <el-input v-model="expObj.estateYear" placeholder="请输入地产年限">
                                <template slot="append">年</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="合同年限：" prop="contractYear">
                            <el-input v-model="expObj.contractYear" placeholder="请输入合同年限">
                                <template slot="append">年</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="所属板块：">
                            <el-input v-model="expObj.belongArea" placeholder="请输入所属板块">
                                <template slot="append">年</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14" class="input-col1">
                        <el-form-item label="拥有证件：">
                            <el-checkbox border v-model="expObj.hasHouseCertificate" label="房产证"></el-checkbox>
                            <el-checkbox border v-model="expObj.hasEiaCertificate" label="环评证"></el-checkbox>
                            <el-checkbox border v-model="expObj.hasSewageCertificate" label="排污证"></el-checkbox>
                            <el-checkbox border v-model="expObj.hasFirefightingCertificate" label="消防证"></el-checkbox>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="expObj.certificateRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="价格：" prop="unitPrice">
                            <el-input v-model="expObj.unitPrice" clearable>
                                <template slot="append">元/㎡/天</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="物业费：" prop="strataFee">
                            <el-input v-model="expObj.strataFee" clearable>
                                <template slot="append">元/㎡/天</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="免租期：" prop="freeRentDate">
                            <el-input v-model="expObj.freeRentDate" clearable>
                                <template slot="append">月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10" class="input-col1">
                        <el-form-item label="付款方式：" prop="payRule">
                            <el-radio-group v-model="expObj.payRule">
                                <el-radio label="1">押二付三</el-radio>
                                <el-radio label="2">押一付六</el-radio>
                                <el-radio label="3">年付</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="递增方式：">
                            <el-radio-group v-model="expObj.increaseRule">
                                <el-radio label="1">三年或以上递增</el-radio>
                                <el-radio label="2">两年递增</el-radio>
                                <el-radio label="3">年递增</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="18" :sm="18" :md="18" :lg="18" :xl="18" class="input-col1">
                        <el-form-item label="税收要求：" prop="taxRequest">
                            <el-radio-group v-model="expObj.taxRequest">
                                <el-radio label="1">无税收要求，行业无限制</el-radio>
                                <el-radio label="2">有税收要求但行业无明显限制</el-radio>
                                <el-radio label="3">有税收要求行业限制高</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="税金：" prop="taxes">
                            <el-input v-model="expObj.taxes" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="限制行业：" prop="restrictIndustry">
                            <el-input v-model="expObj.restrictIndustry" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">影像资料</span>
            </div>
            <div class="building-img-root-div">
                <div class="building-img-title-div">
                    <span class="building-img-title">视频</span>
                </div>

                <div class="building-picture-root-div">
                    <div class="building-picture-div" v-if="fileType1 == 'video'" v-for="(fileCode,index) in videoList">
                        <i @click="removeVideo(index)" class="el-icon-remove-outline"></i>
                        <video width="100%" height="100%" controls autobuffer>
                            <source :src=" parkBaseUrl + 'file/read/' + fileCode "/>
                        </video>
                        <div style="clear:both"></div>
                    </div>

                    <div class="building-picture-button-div">
                        <file @selectFile="selectVideo" class="file-button" v-model="expObj.showVideo"></file>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div style="clear:both"></div>
            </div>

            <div class="building-img-root-div">
                <div class="building-img-title-div">
                    <span class="building-img-title">图片</span>
                </div>

                <div class="building-picture-root-div">
                    <div class="building-picture-div" v-if="fileType2 == 'image'" v-for="(fileCode,index) in imgList">
                        <i @click="removeImg(index)" class="el-icon-remove-outline"></i>
                        <img width="100%" height="100%" style="" :src=" parkBaseUrl + 'file/read/' + fileCode ">
                        <div style="clear:both"></div>
                    </div>

                    <div class="building-picture-button-div">
                        <file @selectFile="selectImg" class="file-button" v-model="expObj.showPicture"></file>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div style="clear:both"></div>
            </div>

            <div style="height: 50px;margin-top: 20px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="submitForm('expObj')">提交
                </el-button>
                <el-button style="float: left;" @click="resetForm('expObj')">重置</el-button>
            </div>

            <div style="background-color: rgb(242,242,242);height: 20px;"></div>
        </el-form>
    </div>
</template>

<script>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElRow from "element-ui/packages/row/src/row";
    import ElFormItem from "../../../../node_modules/element-ui/packages/form/src/form-item";
    import file from '../../../common/components/file.vue'

    export default {
        components: {
            ElFormItem,
            ElRow,
            ElButton,
            file,
        },
        data() {
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
            // 校验数字只能为正整数
            let checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('只能输入正整数'));
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
                floatDivWidth: '',

                showExpandEditBtn: false,// 权限

                // 拓展实体
                expObj: {
                    elevatorLocationList: [],// 电梯位置
                },

                videoList: [],// 图片集合
                imgList: [],// 视频集合

                // 省市区集合
                provinceList: [],
                cityList: [],
                areaList: [],
                streetList: [],

                // 上传文件类型
                fileType1: 'video',
                fileType2: 'image',

                empList: [],//人员下拉选集合
                employees: [],//人员取值

                rules: {
                    parkOwnerCpyName: [
                        {required: true, message: '公司名不能为空', trigger: 'blur'},
                    ],
                    parkOwnerName: [
                        {required: true, message: '业主姓名不能为空', trigger: 'blur'},
                    ],
                    parkOwnerSex: [
                        {required: true, message: '请选择业主性别', trigger: 'change'},
                    ],
                    parkOwnerPhone: [
                        {required: true, message: '业主电话不能为空', trigger: 'blur'},
                        {validator: checkPhone},
                    ],
                    provinceCode: [
                        {required: true, message: '请选择省份', trigger: 'change'}
                    ],
                    cityCode: [
                        {required: true, message: '请选择城市', trigger: 'change'},
                    ],
                    regionCode: [
                        {required: true, message: '请选择区域', trigger: 'change'},
                    ],
                    streetCode: [
                        {required: true, message: '请选择街道', trigger: 'change'},
                    ],
                    address: [
                        {required: true, message: '详细地址不能为空', trigger: 'blur'},
                    ],
                    distanceDowntown: [
                        {required: true, message: '主城区距离不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    distanceNearbyLifeRegion: [
                        {required: true, message: '周边生活区距离不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    manufacturingDistrict: [
                        {required: true, message: '请选择是否在工业集中区', trigger: 'change'},
                    ],
                    distanceFreewayEntrance: [
                        {required: true, message: '高速入口距离不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    hasPublicTraffic: [
                        {required: true, message: '请选择1.5公里内是否有公共交通', trigger: 'change'},
                    ],
                    distanceMainStreet: [
                        {required: true, message: '主干道距离不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    laneQuantity: [
                        {required: true, message: '车道数不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    nearbyPrice: [
                        {required: true, message: '周边价格不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    plantDecoration: [
                        {required: true, message: '请选择装修情况', trigger: 'change'},
                    ],
                    plantElectricity: [
                        {required: true, message: '请选择送电情况', trigger: 'change'},
                    ],
                    parkArea: [
                        {required: true, message: '总占地面积不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    singleFloorQuantity: [
                        {required: true, message: '楼栋数量不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    singleFloorArea: [
                        {required: true, message: '建筑面积不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    singleFloorHeight: [
                        {required: true, message: '层高不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    moreFloorQuantity: [
                        {required: true, message: '楼栋数量不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    moreFloorArea: [
                        {required: true, message: '建筑面积不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    moreFloorHeight: [
                        {required: true, message: '层高不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    moreFloorElevatorQuantity: [
                        {required: true, message: '电梯数不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    elevatorLocationList: [
                        {required: true, message: '请选择电梯位置', trigger: 'change'},
                    ],
                    officeBuildingQuantity: [
                        {required: true, message: '办公楼栋数不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    officeBuildingArea: [
                        {required: true, message: '建筑面积不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    officeBuildingHeight: [
                        {required: true, message: '层高不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    otherBuildingQuantity: [
                        {required: true, message: '附房栋数不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    otherBuildingArea: [
                        {required: true, message: '建筑面积不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    vacancySoilArea: [
                        {required: true, message: '空地面积不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    totalElectricity: [
                        {required: true, message: '总用电容量不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    firefightingLevel: [
                        {required: true, message: '请选择消防等级', trigger: 'change'},
                    ],
                    estateYear: [
                        {required: true, message: '地产年限不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    contractYear: [
                        {required: true, message: '合同年限不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    unitPrice: [
                        {required: true, message: '价格不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    strataFee: [
                        {required: true, message: '物业费不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    freeRentDate: [
                        {required: true, message: '免租期不能为空', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    payRule: [
                        {required: true, message: '请选择付款方式', trigger: 'change'},
                    ],
                    increaseRule: [
                        {required: true, message: '请选择递增方式', trigger: 'change'},
                    ],
                    taxRequest: [
                        {required: true, message: '请选择税收要求', trigger: 'change'},
                    ],
                    taxes: [
                        {required: true, message: '税金不能为空', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    restrictIndustry: [
                        {required: true, message: '限制行业不能为空', trigger: 'blur'},
                    ],
                },
            }
        },
        //接受父组件传过来的参数
        props: ['paramsObj'],

        created: function () {

            let vm = this;
            vm.showExpandEditBtn = vm.$flagMenuStore.judgeMenu("expandEditBtn");

            if (vm.showExpandEditBtn) {
                if (vm.paramsObj && vm.paramsObj.itemCode) {
                    vm.expObj.itemCode = vm.paramsObj.itemCode;
                }

                let queryBdName = {
                    entity: {
                        itemCode: vm.expObj.itemCode,
                    }
                };

                let options = {
                    method: 'POST',
                    data: queryBdName,
                    url: 'invpark/detail'
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (!response.data.isException) {
                        vm.expObj = response.data.result;

                        vm.expObj.elevatorLocationList = vm.expObj.elevatorLocation.split('，');

                        // 初始化省市区街道
                        vm.handNeedProvince();
                        vm.getCityList(vm.expObj.provinceCode);
                        vm.getAreaList(vm.expObj.cityCode);
                        vm.getStreetList(vm.expObj.regionCode);

                        // 赋值给videoList
                        if (vm.expObj.showVideo) {
                            vm.videoList = vm.expObj.showVideo.split(',');
                        } else {
                            vm.videoList = [];
                        }

                        // 赋值给imgList
                        if (vm.expObj.showPicture) {
                            vm.imgList = vm.expObj.showPicture.split(',');
                        } else {
                            vm.imgList = [];
                        }
                    }
                }).catch(error => {
                    vm.$alert('页面：查询拓展信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });

                vm.queryEmp();// 查询人员
            } else {
                window.history.back();
            }
        },
        methods: {
            // 返回按钮
            goBack(){
//                this.$router.push({
//                    name: "expandDetail",
//                    query: {
//                        itemCode: this.expObj.itemCode,
//                    }
//                })
            },
            // 重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            // 查询人员
            queryEmp(){
                let vm = this;

                let options = {
                    method: 'POST',
                    data: {},
                    url: "employee/list",
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
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
                    vm.$alert('页面：新增拓展查询人员信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 选择拓展人
            changeEmp(code) {
                let employee = this.employees[code];
                this.expObj.expEmpName = employee.empName;
                this.expObj.expEmpPhone = employee.phone;
                this.expObj.expEmpCpyName = employee.cpyName;
            },
            // 视频上传
            selectVideo(urls){
                if (urls) {
                    for (var i = 0; i < urls.length; i++) {
                        this.videoList.push(urls[i].fileCode);
                    }
                } else {
                    this.videoList = [];
                }
            },
            // 图片上传
            selectImg(urls){
                if (urls) {
                    for (var i = 0; i < urls.length; i++) {
                        this.imgList.push(urls[i].fileCode);
                    }
                } else {
                    this.imgList = [];
                }
            },
            // 移除视频
            removeVideo(index){
                this.$confirm('是否删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.videoList.splice(index, 1);
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            // 移除图片
            removeImg(index){
                this.deleteMessage(this.imgList, index);
            },
            // 删除提示
            deleteMessage(list, index){
                this.$confirm('是否删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    list.splice(index, 1);
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            // 提交表单
            submitForm(formName){
                let vm = this;

                let provinceName = window.document.getElementById("provinceName");
                let cityName = window.document.getElementById("cityName");
                let regionName = window.document.getElementById("regionName");
                let streetName = window.document.getElementById("streetName");


                if (this.videoList.length > 0) {
                    this.expObj.showVideo = this.videoList.join(',');
                } else {
                    this.expObj.showVideo = '';
                }

                if (this.imgList.length > 0) {
                    this.expObj.showPicture = this.imgList.join(',');
                } else {
                    this.expObj.showPicture = '';
                }


                this.$refs[formName].validate((valid) => {
                    if (valid) {

                        vm.expObj.elevatorLocation = vm.expObj.elevatorLocationList.join('，');

                        let params = {};
                        params.entity = vm.expObj;
                        params.entity.provinceName = provinceName.value;
                        params.entity.cityName = cityName.value;
                        params.entity.regionName = regionName.value;
                        params.entity.streetName = streetName.value;

                        let options = {
                            method: "POST",
                            data: params,
                            url: "invpark/update"
                        };

                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '拓展信息修改成功',
                                    type: 'success'
                                });

                                // 关闭详情页
                                let dataCloseDetails = {
                                    flag: 'del',
                                    menuName: '拓展详情',
                                    item: 'expandDetail'
                                };
                                vm.$emit('backParam', dataCloseDetails);

                                // 把当前修改页签关闭
                                let dataDelObj = {
                                    flag: 'del',
                                    menuName: '拓展修改',
                                    item: 'expandEdit'
                                };
                                vm.$emit('backParam', dataDelObj);
                            }
                        }).catch(function (error) {
                            vm.$message({
                                showClose: true,
                                message: '页面：拓展信息修改失败',
                                type: 'error'
                            });
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                });
            },

            /**************************************************************************
             * 省、市、区、街道查询
             *************************************************************************/

            // 省份查询
            handNeedProvince(){
                let vm = this;

                let params = {
                    entity: {
                        parentCode: 0,
                    }
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: "cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.provinceList = response.data.result;
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '查询省份信息失败',
                        type: 'error'
                    });
                });
            },
            // 市区查询
            getCityList(aVal) {
                if (aVal) {
                    let vm = this;

                    let params = {
                        entity: {
                            parentCode: aVal,
                        }
                    };

                    let options = {
                        method: 'POST',
                        data: params,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.cityList = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '查询市区信息失败',
                            type: 'error'
                        });
                    });
                }
            },
            // 区域查询
            getAreaList(bVal) {
                if (bVal) {
                    let vm = this;

                    let params = {
                        entity: {
                            parentCode: bVal,
                        }
                    };

                    let options = {
                        method: 'POST',
                        data: params,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.areaList = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '查询区域信息失败',
                            type: 'error'
                        });
                    });
                }
            },
            // 街道查询
            getStreetList(aVal){
                if (aVal) {
                    let vm = this;

                    let params = {
                        entity: {
                            parentCode: aVal,
                        }
                    };

                    let options = {
                        method: 'POST',
                        data: params,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.streetList = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '查询街道信息失败',
                            type: 'error'
                        });
                    });
                }
            },
            changeRegionCpy(aval){
                // 清空code
                this.expObj.streetCode = "";
                this.expObj.streetName = "";
            },
            changeCityCpy(aval){
                // 清空code
                this.expObj.regionCode = "";
                this.expObj.streetCode = "";

                this.expObj.regionName = "";
                this.expObj.streetName = "";
            },
            changeProvCpy(aval){
                // 清空code
                this.expObj.cityCode = "";
                this.expObj.regionCode = "";
                this.expObj.streetCode = "";

                this.expObj.cityName = "";
                this.expObj.regionName = "";
                this.expObj.streetName = "";
            },
        },
        mounted(){
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
    }
</script>


<style scoped>
    .root_form {
        height: 84vh;
        overflow-y: auto;
        overflow-x: hidden;
    }

    /*保存按钮*/
    .save-button {
        float: left;
        margin-left: 20px;
    }

    /*******************************************************************/

    /*公共标题 div*/
    .common-title-div {
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
        /*font-weight: bold;*/
        line-height: 50px;
    }

    /*******************************************************************/

    /*基本信息 div*/
    .customer-info-div {
        min-width: 1000px;
        /*height: 120px;*/
        background-color: white;
        text-align: left;
        padding-bottom: 7px;
    }

    /*输入框 第一行*/
    .input-col1 {
        margin-top: 10px;
        height: 55px;
        /*border: solid 1px;*/
    }

    /*******************************************************************/

    /*影像资料  根Div*/
    .building-img-root-div {
        max-width: 1644px;
        background-color: white;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    .building-img-title-div {
        width: 70px;
        color: #409EFF;
        margin-left: 15px;
        float: left;
        height: 100%;
        line-height: 190px;
    }

    /*上传文件按钮*/
    .file-button {
        width: 100px;
        height: 30px;
        float: left;
        margin-top: 10px;
        margin-left: 65px;
    }

    /*图片和视频循环 根div*/
    .building-picture-root-div {
        width: 93%;
        height: 100%;
        margin-left: 90px;
    }

    /*图片和视频 循环div*/
    .building-picture-div {
        height: 160px;
        width: 240px;
        float: left;
        margin-left: 15px;
        margin-top: 10px;
        padding-bottom: 20px;
        /*border: solid 1px;*/
    }

    /*删除按钮*/
    .el-icon-remove-outline {
        float: right;
        margin-top: -5px;
        position: relative;
    }

    /*上传按钮 div*/
    .building-picture-button-div {
        height: 140px;
        line-height: 140px;
        width: 240px;
        float: left;
        margin-left: 15px;
        margin-top: 20px;
        padding-bottom: 20px;
        background-color: #F1F1F1;
    }
</style>