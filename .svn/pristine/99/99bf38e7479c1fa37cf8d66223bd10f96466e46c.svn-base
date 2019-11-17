<template>
    <div class="has-position_root" ref="myRoot">
        <!-- 导航栏 -->
        <div class="has-position_header">
            <el-button type="primary" size="small" @click="submitForm('exp.expObj')" class="save-button">
                提交
            </el-button>
            <el-button size="small" class="save-button" type="primary" @click="remember">
                记忆
            </el-button>
            <el-button size="small" class="save-button" type="primary" @click="restore">
                恢复
            </el-button>
        </div>
        <el-form class="has-position_main" :rules="rules" :model="exp.expObj" ref="exp.expObj" label-width="110px">
            <div class="common-title-div" style="margin-top: 10px;">
                <span class="common-title-font">业主信息</span>
            </div>
            <div class="customer-info-div">
                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="公司名：" prop="parkOwnerCpyName">
                            <el-input v-model="exp.expObj.parkOwnerCpyName" placeholder="请输入业主公司名" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="姓名：" prop="parkOwnerName">
                            <el-input v-model="exp.expObj.parkOwnerName" placeholder="请输入业主名" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="性别：" prop="parkOwnerSex">
                            <el-radio-group v-model="exp.expObj.parkOwnerSex">
                                <el-radio label="1">先生</el-radio>
                                <el-radio label="2">女士</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="电话：" prop="parkOwnerPhone">
                            <el-input suffix-icon="el-icon-phone" v-model="exp.expObj.parkOwnerPhone" placeholder="请输入业主公司名" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="备用联系人：">
                            <el-input v-model="exp.expObj.parkOwnerReserveContactName" placeholder="请输入业主备用联系人" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="性别：">
                            <el-radio-group v-model="exp.expObj.parkOwnerReserveContactSex">
                                <el-radio label="1">先生</el-radio>
                                <el-radio label="2">女士</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="备用电话：">
                            <el-input suffix-icon="el-icon-phone" v-model="exp.expObj.parkOwnerReserveContactPhone" placeholder="请输入业主备用联系人电话" clearable></el-input>
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
                            <el-select v-model="exp.expObj.expEmpCode" @change="changeEmp" filterable placeholder="请选择">
                                <el-option v-for="item in exp.empList" :key="item.code" :label="item.name" :value="item.code">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="拓展人手机：" prop="expEmpPhone">
                            <el-input v-model="exp.expObj.expEmpPhone" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10" class="input-col1">
                        <el-form-item label="拓展人公司：" prop="expEmpCpyName">
                            <el-input v-model="exp.expObj.expEmpCpyName" clearable></el-input>
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
                            <el-select id="provinceName" v-model="exp.expObj.provinceCode" @visible-change="handNeedProvince" @change="changeProvCpy" placeholder="请选择">
                                <el-option v-for="(item, indexkey) in exp.provinceList" :key="item.areaCode" :value="item.areaCode" :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所在城市：" prop="cityCode">
                            <el-select id="cityName" v-model="exp.expObj.cityCode" @visible-change="getCityList(exp.expObj.provinceCode)" @change="changeCityCpy" placeholder="请选择">
                                <el-option v-for="(item, indexkey) in exp.cityList" :key="item.areaCode" :value="item.areaCode" :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所在区域：" prop="regionCode">
                            <el-select id="regionName" v-model="exp.expObj.regionCode" @visible-change="getAreaList(exp.expObj.cityCode)" @change="changeRegionCpy" placeholder="请选择">
                                <el-option v-for="(item, indexkey) in exp.areaList" :key="item.areaCode" :value="item.areaCode" :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所在街道：" prop="streetCode">
                            <el-select id="streetName" v-model="exp.expObj.streetCode" @visible-change="getStreetList(exp.expObj.regionCode)" placeholder="请选择">
                                <el-option v-for="(item, indexkey) in exp.streetList" :key="item.areaCode" :value="item.areaCode" :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14" class="input-col1">
                        <el-form-item label="详细地址：" prop="address">
                            <el-input suffix-icon="el-icon-location" v-model="exp.expObj.address" placeholder="请输入详细地址" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="距离主城区：" prop="distanceDowntown">
                            <el-input v-model="exp.expObj.distanceDowntown" placeholder="请输入主城区距离" clearable>
                                <template slot="append">km</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="周边生活区：" prop="distanceNearbyLifeRegion">
                            <el-input v-model="exp.expObj.distanceNearbyLifeRegion" placeholder="请输入周边生活区距离" clearable>
                                <template slot="append">km</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23" class="input-col1">
                        <el-form-item label="是否在工业集中区：" label-width="150px" prop="manufacturingDistrict">
                            <el-radio-group v-model="exp.expObj.manufacturingDistrict">
                                <el-radio label="1">处于工业集中区</el-radio>
                                <el-radio label="2">非工业集中区，但距离在5公里及以内</el-radio>
                                <el-radio label="3">距离工业集中区超过5.1公里</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="离高速入口：" prop="distanceFreewayEntrance">
                            <el-input v-model="exp.expObj.distanceFreewayEntrance" placeholder="请输入高速公路入口距离" clearable>
                                <template slot="append">km</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="1.5公里内是否有公共交通：" label-width="200px" prop="hasPublicTraffic">
                            <el-radio-group v-model="exp.expObj.hasPublicTraffic">
                                <el-radio :label=true>有</el-radio>
                                <el-radio :label=false>无</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="距离主干道：" prop="distanceMainStreet">
                            <el-input v-model="exp.expObj.distanceMainStreet" placeholder="请输入主干道距离" clearable>
                                <template slot="append">km</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="车道数：" prop="laneQuantity">
                            <el-input v-model="exp.expObj.laneQuantity" placeholder="请输入双向总车道数" clearable>
                                <template slot="append">个</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="周边价格：" prop="nearbyPrice">
                            <el-input v-model="exp.expObj.nearbyPrice" placeholder="请输入周边价格" clearable>
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
                            <el-checkbox border v-model="exp.expObj.hasNewPlant">新建厂房</el-checkbox>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="exp.expObj.plantRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="装修情况：" prop="plantDecoration">
                            <el-radio-group v-model="exp.expObj.plantDecoration">
                                <el-radio label="1">厕所楼道地坪无需修整</el-radio>
                                <el-radio label="2">厕所楼道地坪需稍作修整</el-radio>
                                <el-radio label="3">毛坯需要重新修整</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="exp.expObj.decorationRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="送电情况：" prop="plantElectricity">
                            <el-radio-group v-model="exp.expObj.plantElectricity">
                                <el-radio label="1">电已通</el-radio>
                                <el-radio label="2">电基本完成</el-radio>
                                <el-radio label="3">电未通</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="exp.expObj.electricityRemark" clearable></el-input>
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
                            <el-input v-model="exp.expObj.parkArea" placeholder="请输入占地总面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="单层厂房：" prop="singleFloorQuantity">
                            <el-input v-model="exp.expObj.singleFloorQuantity" placeholder="请输入单层厂房栋数" clearable>
                                <template slot="append">栋</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="建筑面积：" prop="singleFloorArea">
                            <el-input v-model="exp.expObj.singleFloorArea" placeholder="请输入单层厂房建筑面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="层高：" prop="singleFloorHeight">
                            <el-input v-model="exp.expObj.singleFloorHeight" placeholder="请输入单层厂房楼层高度" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="exp.expObj.singleFloorRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="多层厂房：" prop="moreFloorQuantity">
                            <el-input v-model="exp.expObj.moreFloorQuantity" placeholder="请输入多层厂房栋数" clearable>
                                <template slot="append">栋</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="建筑面积：" prop="moreFloorArea">
                            <el-input v-model="exp.expObj.moreFloorArea" placeholder="请输入多层厂房建筑面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="层高：" prop="moreFloorHeight">
                            <el-input v-model="exp.expObj.moreFloorHeight" placeholder="请输入多层厂房楼层高度" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="exp.expObj.moreFloorRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="电梯数：" prop="moreFloorElevatorQuantity">
                            <el-input v-model="exp.expObj.moreFloorElevatorQuantity" placeholder="请输入电梯数" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="17" :sm="17" :md="17" :lg="17" :xl="17" class="input-col1">
                        <el-form-item label="电梯位置：" prop="elevatorLocationList">
                            <el-checkbox-group v-model="exp.expObj.elevatorLocationList">
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
                            <el-input v-model="exp.expObj.officeBuildingQuantity" placeholder="请输入办公楼栋数" clearable>
                                <template slot="append">栋</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="建筑面积：" prop="officeBuildingArea">
                            <el-input v-model="exp.expObj.officeBuildingArea" placeholder="请输入办公楼面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="层高：" prop="officeBuildingHeight">
                            <el-input v-model="exp.expObj.officeBuildingHeight" placeholder="请输入办公楼楼层高度" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="exp.expObj.officeBuildingRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="其他附房：" prop="otherBuildingQuantity">
                            <el-input v-model="exp.expObj.otherBuildingQuantity" placeholder="请输入办公楼栋数" clearable>
                                <template slot="append">栋</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="建筑面积：" prop="otherBuildingArea">
                            <el-input v-model="exp.expObj.otherBuildingArea" placeholder="请输入办公楼面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="exp.expObj.otherBuildingRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="空地面积：" prop="vacancySoilArea">
                            <el-input v-model="exp.expObj.vacancySoilArea" placeholder="请输入办公楼面积" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="exp.expObj.vacancySoilRemark" clearable></el-input>
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
                            <el-input v-model="exp.expObj.totalElectricity" placeholder="请输入总用电容量">
                                <template slot="append">kva</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="17" :sm="17" :md="17" :lg="17" :xl="17" class="input-col1">
                        <el-form-item label="消防等级 :" prop="firefightingLevel">
                            <el-radio-group v-model="exp.expObj.firefightingLevel">
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
                            <el-input v-model="exp.expObj.estateYear" placeholder="请输入地产年限">
                                <template slot="append">年</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="合同年限：" prop="contractYear">
                            <el-input v-model="exp.expObj.contractYear" placeholder="请输入合同年限">
                                <template slot="append">年</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="所属板块：">
                            <el-input v-model="exp.expObj.belongArea" placeholder="请输入所属板块">
                                <template slot="append">年</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14" class="input-col1">
                        <el-form-item label="拥有证件：">
                            <el-checkbox border v-model="exp.expObj.hasHouseCertificate" label="房产证"></el-checkbox>
                            <el-checkbox border v-model="exp.expObj.hasEiaCertificate" label="环评证"></el-checkbox>
                            <el-checkbox border v-model="exp.expObj.hasSewageCertificate" label="排污证"></el-checkbox>
                            <el-checkbox border v-model="exp.expObj.hasFirefightingCertificate" label="消防证"></el-checkbox>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <el-input v-model="exp.expObj.certificateRemark" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="价格：" prop="unitPrice">
                            <el-input v-model="exp.expObj.unitPrice" clearable>
                                <template slot="append">元/㎡/天</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="物业费：" prop="strataFee">
                            <el-input v-model="exp.expObj.strataFee" clearable>
                                <template slot="append">元/㎡/天</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="免租期：" prop="freeRentDate">
                            <el-input v-model="exp.expObj.freeRentDate" clearable>
                                <template slot="append">月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10" class="input-col1">
                        <el-form-item label="付款方式：">
                            <el-radio-group v-model="exp.expObj.payRule">
                                <el-radio label="1">押二付三</el-radio>
                                <el-radio label="2">押一付六</el-radio>
                                <el-radio label="3">年付</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="递增方式：">
                            <el-radio-group v-model="exp.expObj.increaseRule">
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
                            <el-radio-group v-model="exp.expObj.taxRequest">
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
                            <el-input v-model="exp.expObj.taxes" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="限制行业：" prop="restrictIndustry">
                            <el-input v-model="exp.expObj.restrictIndustry" clearable></el-input>
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
                    <div class="building-picture-div" v-if="exp.fileType1 == 'video'" v-for="(fileCode,index) in exp.videoList">
                        <i @click="removeVideo(index)" class="el-icon-remove-outline"></i>
                        <!--<div style="height: 100%">-->
                        <video width="100%" height="100%" controls autobuffer>
                            <source :src=" parkBaseUrl + 'file/read/' + fileCode " />
                        </video>
                        <!--</div>-->
                        <div style="clear:both"></div>
                    </div>

                    <div class="building-picture-button-div">
                        <file @selectFile="selectVideo" class="file-button" v-model="exp.expObj.showVideo"></file>
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
                    <div class="building-picture-div" v-if="exp.fileType2 == 'image'" v-for="(fileCode,index) in exp.imgList">
                        <i @click="removeImg(index)" class="el-icon-remove-outline"></i>
                        <img width="100%" height="100%" style="" :src=" parkBaseUrl + 'file/read/' + fileCode ">
                        <div style="clear:both"></div>
                    </div>

                    <div class="building-picture-button-div">
                        <file @selectFile="selectImg" class="file-button" v-model="exp.expObj.showPicture"></file>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div style="clear:both"></div>
            </div>

            <div style="height: 50px;margin-top: 20px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="submitForm('exp.expObj')">提交</el-button>
                <el-button style="float: left;" @click="resetForm('exp.expObj')">重置</el-button>
            </div>

            <div style="background-color: rgb(242,242,242);height: 20px;"></div>
        </el-form>

        <el-dialog width="60%" :visible.sync="exp.dialogScore" :before-close="handleClose">
            <el-form :model="exp.followupObj" :rules="followRules" ref="exp.followupObj" label-width="110px">
                <div class="res_dialog_left">
                    <div class="circle">
                        <div class="circle_score">{{exp.expObj.score == null ? 0 : exp.expObj.score}}</div>
                    </div>
                    <div class="res_dialog_left_res">评级：优质</div>
                </div>
                <div class="res_dialog_right">
                    <div class="res_dialog_right_top">分值说明：</div>
                    <span style="font-size: 16px;">
                        ＜60分：不合格，60~70分：合格，71~80分：良好，＞80分：优质
                    </span>
                    <br>
                    <br>
                    <span style="font-size: 16px;">
                        评分不合格项目直接不予考虑<br>
                        评分合格项目需与园区总经理进行汇报探讨，是否继续进行拓展<br>
                        评分良好及优质项目，拓展部可直接与业主进行条款约谈
                    </span>
                </div>

                <div class="res_dialog_status">
                    <el-row>
                        <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                            <el-form-item label="跟进状态：" prop="invFollowupType" style="text-align: left;">
                                <el-radio-group v-model="exp.followupObj.invFollowupType">
                                    <el-radio :label="1">电话咨询</el-radio>
                                    <el-radio :label="2">实地拜访</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                            <el-form-item label="客户状态：" prop="expStatus">
                                <el-radio-group v-model="exp.followupObj.expStatus">
                                    <el-radio label="1">跟进中</el-radio>
                                    <el-radio label="2">谈判中</el-radio>
                                    <el-radio label="3">已签约</el-radio>
                                    <el-radio label="4">不合适</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                            <el-form-item label="描述说明：" prop="followupResult">
                                <el-input type="textarea" v-model="exp.followupObj.followupResult"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>
            </el-form>
            <div slot="footer" class="dialog_footer">
                <el-button @click="dialogScoreForm('exp.followupObj')">重 置</el-button>
                <el-button type="primary" @click="dialogScoreSubmit('exp.followupObj')">确 定</el-button>
            </div>
        </el-dialog>
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
                exp: {
                    floatDivWidth: '',

                    // 权限
                    showExpandCreateBtn: false, // 新增拓展

                    // 拓展实体
                    expObj: {
                        itemCode: "", //项目编码
                        elevatorLocationList: [], // 电梯位置
                        expEmpCode: "", //拓展人编码
                        expEmpName: "", //拓展人姓名
                        expEmpPhone: "", //拓展人电话
                        expEmpCpyName: "", //拓展人公司
                        hasNewPlan: false, //是否是新厂房
                        score: "", //项目评分
                        assessResult: "", //项目评级
                    },

                    //跟进实体
                    followupObj: {
                        itemCode: "", //项目编码
                        invFollowupType: "", //跟进类型
                        followupResult: "", //描述
                        expStatus: "", //项目状态
                    },

                    videoList: [], // 图片集合
                    imgList: [], // 视频集合

                    // 省市区集合
                    provinceList: [],
                    cityList: [],
                    areaList: [],
                    streetList: [],

                    // 上传文件类型
                    fileType1: 'video',
                    fileType2: 'image',

                    empList: [], //人员下拉选集合
                    employees: [], //人员取值

                    dialogScore: false, //添加项目信息后的得分弹窗
                },

                rules: {
                    parkOwnerCpyName: [{
                        required: true,
                        message: '公司名不能为空',
                        trigger: 'blur'
                    }, ],
                    parkOwnerName: [{
                        required: true,
                        message: '业主姓名不能为空',
                        trigger: 'blur'
                    }, ],
                    parkOwnerSex: [{
                        required: true,
                        message: '请选择业主性别',
                        trigger: 'change'
                    }, ],
                    parkOwnerPhone: [{
                            required: true,
                            message: '业主电话不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkPhone
                        },
                    ],
                    provinceCode: [{
                        required: true,
                        message: '请选择省份',
                        trigger: 'change'
                    }],
                    expEmpCode: [{
                        required: true,
                        message: '请选择拓展人',
                        trigger: 'change'
                    }],
                    expEmpPhone: [{
                        required: true,
                        message: '请选择拓展人电话',
                        trigger: 'change'
                    }],
                    expEmpCpyName: [{
                        required: true,
                        message: '请选择拓展人公司',
                        trigger: 'change'
                    }],
                    cityCode: [{
                        required: true,
                        message: '请选择城市',
                        trigger: 'change'
                    }, ],
                    regionCode: [{
                        required: true,
                        message: '请选择区域',
                        trigger: 'change'
                    }, ],
                    streetCode: [{
                        required: true,
                        message: '请选择街道',
                        trigger: 'change'
                    }, ],
                    address: [{
                        required: true,
                        message: '详细地址不能为空',
                        trigger: 'blur'
                    }, ],
                    distanceDowntown: [{
                            required: true,
                            message: '主城区距离不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    distanceNearbyLifeRegion: [{
                            required: true,
                            message: '周边生活区距离不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    manufacturingDistrict: [{
                        required: true,
                        message: '请选择是否在工业集中区',
                        trigger: 'change'
                    }, ],
                    distanceFreewayEntrance: [{
                            required: true,
                            message: '高速入口距离不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    hasPublicTraffic: [{
                        required: true,
                        message: '请选择1.5公里内是否有公共交通',
                        trigger: 'change'
                    }, ],
                    distanceMainStreet: [{
                            required: true,
                            message: '主干道距离不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    laneQuantity: [{
                            required: true,
                            message: '车道数不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    nearbyPrice: [{
                            required: true,
                            message: '周边价格不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    plantDecoration: [{
                        required: true,
                        message: '请选择装修情况',
                        trigger: 'change'
                    }, ],
                    plantElectricity: [{
                        required: true,
                        message: '请选择送电情况',
                        trigger: 'change'
                    }, ],
                    parkArea: [{
                            required: true,
                            message: '总占地面积不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    singleFloorQuantity: [{
                            required: true,
                            message: '楼栋数量不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    singleFloorArea: [{
                            required: true,
                            message: '建筑面积不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    singleFloorHeight: [{
                            required: true,
                            message: '层高不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    moreFloorQuantity: [{
                            required: true,
                            message: '楼栋数量不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    moreFloorArea: [{
                            required: true,
                            message: '建筑面积不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    moreFloorHeight: [{
                            required: true,
                            message: '层高不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    moreFloorElevatorQuantity: [{
                            required: true,
                            message: '电梯数不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    elevatorLocationList: [{
                        required: true,
                        message: '请选择电梯位置',
                        trigger: 'change'
                    }, ],
                    officeBuildingQuantity: [{
                            required: true,
                            message: '办公楼栋数不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    officeBuildingArea: [{
                            required: true,
                            message: '建筑面积不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    officeBuildingHeight: [{
                            required: true,
                            message: '层高不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    otherBuildingQuantity: [{
                            required: true,
                            message: '附房栋数不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    otherBuildingArea: [{
                            required: true,
                            message: '建筑面积不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    vacancySoilArea: [{
                            required: true,
                            message: '空地面积不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    totalElectricity: [{
                            required: true,
                            message: '总用电容量不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    firefightingLevel: [{
                        required: true,
                        message: '请选择消防等级',
                        trigger: 'change'
                    }, ],
                    estateYear: [{
                            required: true,
                            message: '地产年限不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    contractYear: [{
                            required: true,
                            message: '合同年限不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    unitPrice: [{
                            required: true,
                            message: '价格不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    strataFee: [{
                            required: true,
                            message: '物业费不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    freeRentDate: [{
                            required: true,
                            message: '免租期不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    payRule: [{
                        required: true,
                        message: '请选择付款方式',
                        trigger: 'change'
                    }, ],
                    increaseRule: [{
                        required: true,
                        message: '请选择递增方式',
                        trigger: 'change'
                    }, ],
                    taxRequest: [{
                        required: true,
                        message: '请选择税收要求',
                        trigger: 'change'
                    }, ],
                    taxes: [{
                            required: true,
                            message: '税金不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    restrictIndustry: [{
                        required: true,
                        message: '限制行业不能为空',
                        trigger: 'blur'
                    }, ],
                },
                followRules: {
                    invFollowupType: [{
                        required: true,
                        message: '请选择跟进类型',
                        trigger: 'blur'
                    }, ],
                    expStatus: [{
                        required: true,
                        message: '请选择状态',
                        trigger: 'blur'
                    }, ],
                    followupResult: [{
                        required: true,
                        message: '请填写描述',
                        trigger: 'blur'
                    }, ],
                },
            }
        },
        created: function() {
            let vm = this;

            vm.showExpandCreateBtn = vm.$flagMenuStore.judgeMenu("expandCreateBtn");

            if (vm.showExpandCreateBtn) {
                // 人员搜索栏
                let empobj = {};
                let empOptions = {
                    method: 'POST',
                    data: empobj,
                    url: "employee/list",
                };
                this.$ajax(
                    empOptions
                ).then(function(response) {
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
                            empList.push({
                                code: code,
                                name: name
                            });
                            employees[code] = {
                                empName: name,
                                deptCode: deptCode,
                                deptName: deptName,
                                phone: phone,
                                cpyName: cpyName
                            };
                        }
                        vm.exp.empList = empList;
                        vm.exp.employees = employees;
                    }
                }).catch(function(error) {
                    vm.$alert('页面：新增拓展查询人员信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            } else {
                window.history.back();
            }
        },
        methods: {
            handleClose() {
                this.$alert('请完善项目跟进信息,然后提交', '温馨提示', {
                    confirmButtonText: '确定',
                    callback: action => {}
                });
            },
            // 记忆
            remember() {
                window.localStorage.setItem("_expObj", JSON.stringify(this.exp));
                this.$message({
                    showClose: true,
                    message: '记忆本页填写信息成功',
                    type: 'success',
                });
            },
            //恢复
            restore() {
                // 判断是否有记忆的信息
                let expCache = window.localStorage.getItem("_expObj");
                let expCacheObj = null;
                if (expCache) {
                    expCacheObj = JSON.parse(expCache);
                    this.exp = expCacheObj;
                }
            },
            // 选择拓展人
            changeEmp(code) {
                let employee = this.exp.employees[code];
                this.exp.expObj.expEmpName = employee.empName;
                this.exp.expObj.expEmpPhone = employee.phone;
                this.exp.expObj.expEmpCpyName = employee.cpyName;
            },

            // 重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            // 视频上传
            selectVideo(urls) {
                if (urls) {
                    for (var i = 0; i < urls.length; i++) {
                        this.exp.videoList.push(urls[i].fileCode);
                    }
                } else {
                    this.exp.videoList = [];
                }
            },
            // 图片上传
            selectImg(urls) {
                if (urls) {
                    for (var i = 0; i < urls.length; i++) {
                        this.exp.imgList.push(urls[i].fileCode);
                    }
                } else {
                    this.exp.imgList = [];
                }
            },
            // 移除视频
            removeVideo(index) {
                this.$confirm('是否删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.exp.videoList.splice(index, 1);
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
            removeImg(index) {
                this.deleteMessage(this.exp.imgList, index);
            },
            // 删除提示
            deleteMessage(list, index) {
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
            submitForm(formName) {
                let vm = this;

                let provinceName = window.document.getElementById("provinceName");
                let cityName = window.document.getElementById("cityName");
                let regionName = window.document.getElementById("regionName");
                let streetName = window.document.getElementById("streetName");

                // 将填写的数据记忆到本地
                window.localStorage.setItem("_expObj", JSON.stringify(this.exp));

                if (this.exp.videoList.length > 0) {
                    this.exp.expObj.showVideo = this.exp.videoList.join(',');
                } else {
                    this.exp.expObj.showVideo = '';
                }

                if (this.exp.imgList.length > 0) {
                    this.exp.expObj.showPicture = this.exp.imgList.join(',');
                } else {
                    this.exp.expObj.showPicture = '';
                }

                this.$refs[formName].validate((valid) => {
                    if (valid) {

                        vm.exp.expObj.elevatorLocation = vm.exp.expObj.elevatorLocationList.join('，');

                        let params = {};
                        params.entity = vm.exp.expObj;
                        params.entity.provinceName = provinceName.value;
                        params.entity.cityName = cityName.value;
                        params.entity.regionName = regionName.value;
                        params.entity.streetName = streetName.value;

                        let options = {
                            method: "POST",
                            data: params,
                            url: "invpark/create"
                        };

                        this.$ajax(
                            options
                        ).then(function(response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '拓展信息新增成功',
                                    type: 'success'
                                });
                                window.localStorage.removeItem("_expObj");

                                // 新增成功后计算改拓展项目的综合评分
                                let result = response.data.result;
                                vm.exp.expObj.itemCode = result.itemCode;
                                vm.exp.expObj.score = result.score;
                                vm.exp.expObj.assessResult = result.assessResult;
                                vm.exp.dialogScore = true;
                            }
                        }).catch(function(error) {
                            vm.$message({
                                showClose: true,
                                message: '页面：拓展信息新增失败',
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

            // 项目跟进提交
            dialogScoreSubmit(formName) {
                let vm = this;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.exp.followupObj.itemCode = this.exp.expObj.itemCode;
                        let obj = {
                            entity: this.exp.followupObj,
                        };

                        let options = {
                            method: 'POST',
                            data: obj,
                            url: "invparkFollowup/create",
                        };
                        this.$ajax(
                            options
                        ).then(function(response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '拓展跟进新增成功',
                                    type: 'success',
                                });

                                // 跟进新增成功后，重置表单并关闭窗口
                                vm.resetForm(formName);
                                vm.exp.dialogScore = false;

                                // 拓展新增成功后跳转到拓展详情页
                                let data = {
                                    menuName: "拓展详情",
                                    item: "expandDetail",
                                    params: {
                                        itemCode: vm.exp.expObj.itemCode,
                                    }
                                };
                                vm.$emit("backParam", data);

                                // 关闭当前新增页签
                                let dataDelObj = {
                                    flag: 'del',
                                    menuName: '楼栋新增',
                                    item: 'expandCreate'
                                };
                                vm.$emit('backParam', dataDelObj);

                            }
                        });
                    } else {
                        vm.$message({
                            showClose: true,
                            message: '信息填写不完整',
                            type: 'error'
                        });
                    }
                });
            },

            // 重置，添加跟进
            dialogScoreForm(formName) {
                this.$refs[formName].resetFields();
            },

            /**************************************************************************
             * 省、市、区、街道查询
             *************************************************************************/

            // 省份查询
            handNeedProvince(val) {
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
                ).then(function(response) {
                    vm.exp.provinceList = response.data.result;
                }).catch(function(error) {
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
                    ).then(function(response) {
                        vm.exp.cityList = response.data.result;
                    }).catch(function(error) {
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
                    ).then(function(response) {
                        vm.exp.areaList = response.data.result;
                    }).catch(function(error) {
                        vm.$message({
                            showClose: true,
                            message: '查询区域信息失败',
                            type: 'error'
                        });
                    });
                }
            },
            // 街道查询
            getStreetList(aVal) {
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
                    ).then(function(response) {
                        vm.exp.streetList = response.data.result;
                    }).catch(function(error) {
                        vm.$message({
                            showClose: true,
                            message: '查询街道信息失败',
                            type: 'error'
                        });
                    });
                }
            },
            changeRegionCpy(aval) {
                // 清空code
                this.exp.expObj.streetCode = "";
            },
            changeCityCpy(aval) {
                // 清空code
                this.exp.expObj.regionCode = "";
                this.exp.expObj.streetCode = "";
            },
            changeProvCpy(aval) {
                // 清空code
                this.exp.expObj.cityCode = "";
                this.exp.expObj.regionCode = "";
                this.exp.expObj.streetCode = "";
            },
        },
        mounted() {
            this.exp.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.exp.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
    }
</script>

<style scoped>

    /*保存按钮*/
    .save-button {
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

    /*得分弹窗*/
    .res_dialog_left {
        float: left;
        width: 39%;
        height: 230px;

    }

    .res_dialog_right {
        float: right;
        width: 59%;
        height: 230px;
        text-align: left;
    }

    .res_dialog_right_top {
        margin-top: 20px;
        font-size: 18px;
    }

    .circle {
        width: 170px;
        height: 170px;
        background-color: #FE980F;
        border-radius: 50%;
        -moz-border-radius: 50%;
        -webkit-border-radius: 50%;
        margin: auto;
    }

    .circle_score {
        color: #FFFFFF;
        font-weight: bold;
        font-size: 60px;
        height: 180px;
        line-height: 180px;
    }

    .res_dialog_left_res {
        margin-top: 10px;
        font-size: 20px;
        font-weight: bold;
        color: #FE980F;
    }

    .res_dialog_status {
        text-align: left;
        margin-left: 100px;
    }

    .dialog_footer {
        text-align: center;
        height: 60px;
        line-height: 60px;
    }
</style>