<template>
    <div class="has-position_root" ref="myRoot">
        <!-- 导航栏 -->
        <div class="has-position_header">
            <el-button v-if="showExpandCreateBtn" type="primary" size="small" @click="expandEdit(expObj.itemCode)" class="save-button">
                修改
            </el-button>
            <span class="nevigate-title">项目详情 - {{expObj.score }}{{ '(' + expObj.assessResult + ')'}}</span>
        </div>

        <el-form class="root_form" :model="expObj" ref="expObj">

            <div class="common-title-div" style="margin-top: 10px;">
                <span class="common-title-font">业主信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10" class="input-col1">
                        <el-form-item label="公司名：">
                            <span>{{ expObj.parkOwnerCpyName }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="姓名：">
                            <span>{{ expObj.parkOwnerName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="性别：">
                            <span>{{ expObj.parkOwnerSexName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="电话：">
                            <span>{{ expObj.parkOwnerPhone }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="备用联系人：">
                            <span>{{ expObj.parkOwnerReserveContactName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="性别：">
                            <span>{{ expObj.parkOwnerReserveContactSex == null ? "" :  expObj.parkOwnerReserveContactSexName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="备用电话：">
                            <span>{{ expObj.parkOwnerReserveContactPhone }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">拓展人信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="拓展人：">
                            <span>{{ expObj.expEmpName }}</span>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="拓展人手机：">
                            <span>{{ expObj.expEmpPhone }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10" class="input-col1">
                        <el-form-item label="拓展人公司：">
                            <span>{{ expObj.expEmpCpyName }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">位置信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所在省份：">
                            <span>{{ expObj.provinceName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所在城市：">
                            <span>{{ expObj.cityName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所在区域：">
                            <span>{{ expObj.regionName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所在街道：">
                            <span>{{ expObj.streetName }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14" class="input-col1">
                        <el-form-item label="详细地址：">
                            <span>{{ expObj.address }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="距离主城区：">
                            <span>{{ expObj.distanceDowntown }}&nbsp;km</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="周边生活区：">
                            <span>{{ expObj.distanceNearbyLifeRegion }}&nbsp;km</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23" class="input-col1">
                        <el-form-item label="是否在工业集中区：">
                            <span>{{ expObj.manufacturingDistrictName }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="离高速入口：">
                            <span>{{ expObj.distanceFreewayEntrance }}&nbsp;km</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="1.5公里内是否有公共交通：">
                            <span>{{ expObj.hasPublicTraffic == true ? "有" : "没有" }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="距离主干道：">
                            <span>{{ expObj.distanceMainStreet }}&nbsp;km</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="车道数：">
                            <span>{{ expObj.laneQuantity }}&nbsp;个</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="周边价格：">
                            <span>{{ expObj.nearbyPrice }}&nbsp;元/㎡/天</span>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">厂房装修</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="厂房形象：">
                            <span>{{ expObj.hasNewPlant == true ? "新建厂房" : "非新厂房" }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <span>{{ expObj.plantRemark }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="装修情况：">
                            <span>{{ expObj.plantDecorationName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <span>{{ expObj.decorationRemark }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="送电情况：">
                            <span>{{ expObj.plantElectricityName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <span>{{ expObj.electricityRemark }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">厂房面积结构</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="总占地面积：">
                            <span>{{ expObj.parkArea }}&nbsp;㎡</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="单层厂房：">
                            <span>{{ expObj.singleFloorQuantity }}&nbsp;栋</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="建筑面积：">
                            <span>{{ expObj.singleFloorArea }}&nbsp;㎡</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="层高：">
                            <span>{{ expObj.singleFloorHeight }}&nbsp;㎡</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="备注：">
                            <span>{{ expObj.singleFloorRemark }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="多层厂房：">
                            <span>{{ expObj.moreFloorQuantity }}&nbsp;栋</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="建筑面积：">
                            <span>{{ expObj.moreFloorArea }}&nbsp;㎡</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="层高：">
                            <span>{{ expObj.moreFloorHeight }}&nbsp;㎡</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="备注：">
                            <span>{{ expObj.moreFloorRemark }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="电梯数：">
                            <span>{{ expObj.moreFloorElevatorQuantity }}&nbsp;个</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="17" :sm="17" :md="17" :lg="17" :xl="17" class="input-col1">
                        <el-form-item label="电梯位置：">
                            <span>{{ expObj.elevatorLocation }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="办公楼：">
                            <span>{{ expObj.officeBuildingQuantity }}&nbsp;栋</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="建筑面积：">
                            <span>{{ expObj.officeBuildingArea }}&nbsp;㎡</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="层高：">
                            <span>{{ expObj.officeBuildingHeight }}&nbsp;㎡</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="备注：">
                            <span>{{ expObj.officeBuildingRemark }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="其他附房：">
                            <span>{{ expObj.otherBuildingQuantity }}&nbsp;栋</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="建筑面积：">
                            <span>{{ expObj.otherBuildingArea }}&nbsp;㎡</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="备注：">
                            <span>{{ expObj.otherBuildingRemark }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="空地面积：">
                            <span>{{ expObj.vacancySoilArea }}&nbsp;㎡</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="备注：">
                            <span>{{ expObj.vacancySoilRemark }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>



            <div class="common-title-div">
                <span class="common-title-font">配电及消防</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="总用电容量：">
                            <span>{{ expObj.totalElectricity }}&nbsp;kva</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="17" :sm="17" :md="17" :lg="17" :xl="17" class="input-col1">
                        <el-form-item label="消防等级 :">
                            <span>{{ expObj.firefightingLevelName }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>



            <div class="common-title-div">
                <span class="common-title-font">商务条款及政策情况</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="地产年限：">
                            <span>{{ expObj.estateYear }}&nbsp;年</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="合同年限：">
                            <span>{{ expObj.contractYear }}&nbsp;年</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="所属板块：">
                            <span>{{ expObj.belongArea }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="50">
                    <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14" class="input-col1">
                        <el-form-item label="拥有证件：">
                            <el-tag type="success" v-if="expObj.hasHouseCertificate">房产证</el-tag>
                            <el-tag type="success" v-if="expObj.hasEiaCertificate">环评证</el-tag>
                            <el-tag type="success" v-if="expObj.hasSewageCertificate">排污证</el-tag>
                            <el-tag type="success" v-if="expObj.hasFirefightingCertificate">消防证</el-tag>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="备注：">
                            <span>{{ expObj.certificateRemark }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="价格：">
                            <span>{{ expObj.unitPrice }}&nbsp;元/㎡/天</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="物业费：">
                            <span>{{ expObj.strataFee }}&nbsp;元/㎡/天</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="免租期：">
                            <span>{{ expObj.freeRentDate }}&nbsp;月</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="付款方式：">
                            <span>{{ expObj.payRuleName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="递增方式：">
                            <span>{{ expObj.increaseRuleName }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="50">
                    <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14" class="input-col1">
                        <el-form-item label="税收要求：">
                            <span>{{ expObj.taxRequestName }}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="税金：">
                            <span>{{ expObj.taxes }}&nbsp;元</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7" class="input-col1">
                        <el-form-item label="限制行业：">
                            <span>{{ expObj.restrictIndustry }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">影像资料</span>
            </div>
            <div class="img-root-div" style="border-bottom: 1px solid rgb(242, 242, 242);">
                <div class="img-title-div">
                    <span class="building-img-title">视频</span>
                </div>
                <div class="picture-root-div">
                    <div class="picture-div" v-if="videoList" v-for="(fileCode,index) in videoList">
                        <video width="100%" height="100%" controls autobuffer>
                            <source :src=" parkBaseUrl + 'file/read/' + fileCode "/>
                        </video>
                    </div>
                </div>
                <div style="clear:both"></div>
            </div>

            <div class="img-root-div">
                <div class="img-title-div">
                    <span class="building-img-title">图片</span>
                </div>
                <div class="picture-root-div">
                    <div class="picture-div" v-if="imgList" v-for="(fileCode,index) in imgList">
                        <img style="width: 100%;height: 100%;" :src=" parkBaseUrl + 'file/read/' + fileCode ">
                    </div>
                </div>
                <div style="clear:both"></div>
            </div>

            <div style="background-color: rgb(242,242,242);height: 50px;"></div>
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

                // 权限
                showExpandDetailPage: false,// 拓展详情页
                showExpandEditBtn: false,// 拓展修改

                expObj: {},// 拓展实体

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
            }
        },

        // 接收父组件数据
        props: ["paramsObj"],

        created: function () {
            let vm = this;
            if (vm.paramsObj && vm.paramsObj.itemCode) {
                vm.expObj.itemCode = vm.paramsObj.itemCode;
            }

            vm.showExpandDetailPage = vm.$flagMenuStore.judgeMenu("expandDetailPage");
            vm.showExpandCreateBtn = vm.$flagMenuStore.judgeMenu("expandEditBtn");

            if (vm.showExpandDetailPage) {
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
                    if (response) {
                        vm.expObj = response.data.result;

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
                        callback: action => {}
                    });
                });
            } else {
                window.history.back();
            }
        },
        methods: {
            // 拓展信息修改
            expandEdit(itemCode){
                let data = {
                    menuName: "拓展修改",
                    item: "expandEdit",
                    params: {
                        itemCode: itemCode,
                    }
                };
                this.$emit("backParam",data);
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

    /*返回按钮*/
    .back-button{
        float: left;
        margin-left: 20px;
        margin-top: 10px;
    }

    /*导航栏标题*/
    .nevigate-title{
        color: #409EFF;
        font-size: 17px;
        float: left;
        margin-left: 20px;
        line-height: 50px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*保存按钮*/
    .save-button{
        float: left;
        margin-left: 20px;
    }
    /*******************************************************************/

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
    .input-col1{
        height: 30px;
        line-height: 30px;
        margin-left: 20px;
        /*border: solid 1px;*/
    }

    /*输入框 第二行*/
    .input-col2{
        margin-top: 5px;
        height: 60px;
        /*border: solid 1px;*/
    }
    /*******************************************************************/

    /*影像资料  根Div*/
    .img-root-div {
        max-width: 1644px;
        background-color: white;
    }

    /*视频和图片回显 文字div*/
    .img-title-div {
        width: 70px;
        color: #409EFF;
        margin-left: 15px;
        float: left;
        height: 150px;
        line-height: 150px;
        margin-top: 10px;
    }

    /*视频和图片回显 div*/
    .picture-div {
        height: 150px;
        width: 15%;
        float: left;
        margin-left: 20px;
        margin-top: 10px;
        padding-bottom: 10px;
    }

    /*图片和视频循环 根div*/
    .picture-root-div {
        width: 93%;
        height: 100%;
        margin-left: 90px;
    }
</style>