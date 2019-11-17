<template>
    <div class="has-position_root" ref="myRoot">
        <!-- 导航栏 -->
        <div class="has-position_header">
            <el-button class="navigation_button" size="small" @click="parkSubmit('pkObj')" type="primary">
                保存
            </el-button>
            <span class="navigation_title">当前位置：修改园区信息</span>
        </div>
        <el-form class="has-position_main" ref="pkObj" :rules="rules" :model="pkObj" label-width="100px">

            <div class="titles_div">
                <span class="title_font">位置信息</span>
            </div>
            <div class="common_content_div">
                <el-row>
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                        <el-form-item label=所属大区 prop="territoryType">
                            <el-select id="territoryTypeName" v-model="pkObj.territoryType" placeholder="请选择">
                                <el-option v-for="(item, indexkey) in territoryTypeList" :key="item.deptCode" :value="item.deptCode" :label="item.deptName">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5" style="padding-top: 10px;">
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所属省份" prop="provinceCode">
                            <el-select id="provinceName" v-model="pkObj.provinceCode" @visible-change="handNeedProvince" @change="changeProvCpy" placeholder="请选择">
                                <el-option v-for="(item, indexkey) in itProvinceArr" :key="item.areaCode" :value="item.areaCode" :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属城市" prop="cityCode">
                            <el-select id="cityName" v-model="pkObj.cityCode" @focus="getCitieList(pkObj.provinceCode)" @change="changeCityCpy" placeholder="请选择">
                                <el-option v-for="(item, indexkey) in itCityArr" :key="item.areaCode" :value="item.areaCode" :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属区域" prop="regionCode">
                            <el-select id="regionName" v-model="pkObj.regionCode" @focus="getAreaList(pkObj.cityCode)" @change="changeRegionCpy" placeholder="请选择">
                                <el-option v-for="(item, indexkey) in itAreaArr" :key="item.areaCode" :value="item.areaCode" :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属街道" prop="streetCode">
                            <el-select id="streetName" v-model="pkObj.streetCode" @focus="getStreetList(pkObj.regionCode)" @change="changeStreet" placeholder="请选择">
                                <el-option v-for="(item, indexkey) in itStreetArr" :key="item.areaCode" :value="item.areaCode" :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                        <el-form-item label="详细地址" prop="address">
                            <el-input v-model="pkObj.address" @blur="changeParkAddress" placeholder="请输入详细地址" clearable suffix-icon="el-icon-location"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="titles_div" style="margin-top: -18px;">
                <span class="title_font">基本信息</span>
            </div>
            <div class="common_content_div">
                <!--第一行-->
                <el-row style="padding-top: 10px;">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="园区名称" prop="pkName">
                            <el-input v-model="pkObj.pkName" @change="changePkName" placeholder="请输入园区名称" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属板块" prop="landBlock">
                            <el-input v-model="pkObj.landBlock" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="地产年限" prop="estateYear">
                            <el-input v-model="pkObj.estateYear">
                                <template slot="append">年</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!--第二行-->
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="承租面积" prop="acreage">
                            <el-input v-model="pkObj.acreage" placeholder="不含公摊">
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="计租面积" prop="investArea">
                            <el-input v-model="pkObj.investArea" placeholder="含公摊">
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="公摊比" prop="areaRate">
                            <el-input v-model="areaRate">
                                <template slot="append">%</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="总用电容量" prop="useElecTotal">
                            <el-input v-model="pkObj.useElecTotal">
                                <template slot="append">kVA</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!--第三行-->
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="可招商空地">
                            <el-input v-model="pkObj.investmentVacantland">
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="停车位数量" prop="parkingSpace">
                            <el-input v-model="pkObj.parkingSpace">
                                <template slot="append">个</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="道路宽度" prop="roadWidth">
                            <el-input v-model="pkObj.roadWidth">
                                <template slot="append">米</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="楼栋总数" prop="buildingTotal">
                            <el-input v-model="pkObj.buildingTotal">
                                <template slot="append">栋</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!--第四行-->
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="最短租期" prop="shortestLease">
                            <el-input v-model="pkObj.shortestLease">
                                <template slot="append">个月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="最长租期" prop="longestLease">
                            <el-input v-model="pkObj.longestLease">
                                <template slot="append">个月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="限制行业" prop="industryRestrictions">
                            <el-input v-model="pkObj.industryRestrictions" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!--第五行-->
                <el-row :gutter="30">
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                        <el-form-item label="配套信息" class="basicmessage_mating">
                            <div>
                                <el-checkbox v-model="pkObj.hasEia" label="可环评" border size="medium"></el-checkbox>
                                <el-checkbox v-model="pkObj.hasCertify" label="有产证" border size="medium"></el-checkbox>
                                <el-checkbox v-model="pkObj.hasSewageCertify" label="有排污证" border size="medium"></el-checkbox>
                                <el-checkbox v-model="pkObj.hasSupermarket" label="有超市" border size="medium"></el-checkbox>
                                <el-checkbox v-model="pkObj.hasCanteen" label="有食堂" border size="medium"></el-checkbox>
                                <el-checkbox v-model="pkObj.hasPubBooth" label="有公摊" border size="medium"></el-checkbox>
                                <el-checkbox v-model="pkObj.hasOfficeBuild" label="有办公楼" border size="medium"></el-checkbox>
                                <el-checkbox v-model="pkObj.hasCanopy" label="有雨棚" border size="medium"></el-checkbox>
                                <el-checkbox v-model="pkObj.hasCarPark" label="有停车场" border size="medium"></el-checkbox>
                                <el-checkbox v-model="pkObj.hasLoadDock" label="有卸货平台" border size="medium"></el-checkbox>
                                <el-checkbox v-model="pkObj.hasChargingPile" label="有充电桩" border size="medium"></el-checkbox>
                            </div>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="titles_div" style="margin-top: -18px;">
                <span class="title_font">其他信息</span>
            </div>
            <div class="common_content_div">
                <el-row style="padding-top: 10px;">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="经营开始日" prop="businessStartDay">
                            <el-date-picker v-model="pkObj.businessStartDay" align="right" type="date" placeholder="选择经营开始日期" :picker-options="pickerOptions1">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="经营到期日" prop="businessEndDay">
                            <el-date-picker v-model="pkObj.businessEndDay" align="right" type="date" placeholder="选择经营到期日期" :picker-options="pickerOptions1">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="拓展价格" prop="averagePrice">
                            <el-input v-model="pkObj.averagePrice">
                                <template slot="append">元/㎡/天</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="每月抄表日" prop="meterReading">
                            <el-input v-model="pkObj.meterReading" placeholder="请输入1-30号的数字" auto-complete="off"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="物业费" prop="propertyCost">
                            <el-input v-model="pkObj.propertyCost">
                                <template slot="append">元/㎡/月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                        <el-form-item label="主导行业" prop="leadTrade">
                            <el-input v-model="pkObj.leadTrade" placeholder="填写本园区的主导行业或优先招商行业"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                        <el-form-item label="交通配套" prop="trafficMatching">
                            <el-input v-model="pkObj.trafficMatching" placeholder="区位优势描述，如：附近的高速、国道、机场车站等"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                        <el-form-item label="优惠政策" prop="favouredPolicy">
                            <el-input v-model="pkObj.favouredPolicy" placeholder="政府、招商单位给出的优惠政策，如：税收、扶持等"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="titles_div" style="margin-top: -18px;">
                <span class="title_font">影像资料</span>
            </div>
            <div class="block_box_img">

                <div class="konge_row"></div>
                <!-- 空格 -->
                <div class="video_title">
                    <p style="margin-top: 60px;color: #409EFF;">视频展示</p>
                </div>
                <div class="video_div">
                    <div v-for="(video, index) in videoList">
                        <video class="video" controls autobuffer>
                            <source :src=" parkBaseUrl + 'file/read/' + video " />
                        </video>
                        <i class="el-icon-remove-outline" style="float: left" @click="deleteVideo(index)"></i>
                    </div>
                    <div class="video_content">
                        <file @selectFile="selectVideo"></file>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>

            <div class="block_box_img_special">
                <div class="konge_row"></div>
                <!-- 空格 -->
                <div class="video_title">
                    <p style="margin-top: 60px;color: #409EFF;">园区鸟瞰</p>
                </div>
                <div class="video_div">
                    <div v-for="(img, index) in aerialViewList">
                        <img class="video" :src=" parkBaseUrl + 'file/read/' + img ">
                        <i class="el-icon-remove-outline" style="float: left" @click="deleteAerialView(index)"></i>
                    </div>
                    <div class="video_content">
                        <file @selectFile="selectAerialView"></file>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>

            <div class="block_box_img_special">
                <div class="konge_row"></div>
                <div class="video_title">
                    <p style="margin-top: 60px;color: #409EFF;">园区环境</p>
                </div>
                <div class="video_div">
                    <div v-for="(img, index) in outPictureList">
                        <img class="video" :src=" parkBaseUrl + 'file/read/' + img ">
                        <i class="el-icon-remove-outline" style="float: left" @click="deleteOutPicture(index)"></i>
                    </div>
                    <div class="video_content">
                        <file @selectFile="selectOutPicture"></file>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>

            <!--末尾提交按钮处-->
            <div style="height: 50px;margin-top: 20px;padding-bottom: 60px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="parkSubmit('pkObj')">保存
                </el-button>
                <el-button style="float: left;" @click="resetForm('pkObj')">重置
                </el-button>
            </div>
        </el-form>
    </div>
</template>


<script>
    import file from '../../../common/components/file';

    export default {
        components: {
            file
        },
        props: ['paramsObj'], // 接收父组件数据
        created: function() {
            let vm = this;

            let deptList = window.localStorage.getItem("_usedQueryDept");
            vm.territoryTypeList = JSON.parse(deptList);

            let params = {
                entity: {
                    pkCode: vm.paramsObj.pkCode,
                }
            };

            if (vm.paramsObj.pkCode) {
                let options = {
                    method: "POST",
                    data: params,
                    url: "park/details",
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {

                        // 初始化省市区街道
                        vm.handNeedProvince();
                        vm.pkObj = response.data.result;

                        var testObj_A = {};
                        testObj_A.areaCode = vm.pkObj.cityCode;
                        testObj_A.name = vm.pkObj.cityName;
                        var arrList = [];
                        arrList.push(testObj_A);
                        vm.itCityArr = arrList;

                        var testObj_A = {};
                        testObj_A.areaCode = vm.pkObj.regionCode;
                        testObj_A.name = vm.pkObj.regionName;
                        var arrList = [];
                        arrList.push(testObj_A);
                        vm.itAreaArr = arrList;

                        testObj_A = {};
                        testObj_A.areaCode = vm.pkObj.streetCode;
                        testObj_A.name = vm.pkObj.streetName;
                        var arrList = [];
                        arrList.push(testObj_A);
                        vm.itStreetArr = arrList;

                        if (vm.pkObj.parkVideo) {
                            vm.videoList = vm.pkObj.parkVideo.split(",");
                        }
                        if (vm.pkObj.aerialView) {
                            vm.aerialViewList = vm.pkObj.aerialView.split(",");
                        }
                        if (vm.pkObj.outPicture) {
                            vm.outPictureList = vm.pkObj.outPicture.split(",");
                        }
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查看园区详细信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            } else {
                this.$message.error("传入参数有误");
            }
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

            // 校验数字0～30
            let checkDay = (rule, value, callback) => {
                setTimeout(() => {
                    if (value < 1 || value > 30) {
                        callback(new Error('请输入1～30数字值'));
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

            return {
                videoList: [],
                aerialViewList: [],
                outPictureList: [],

                itProvinceArr: [],
                itCityArr: [],
                itAreaArr: [],
                itStreetArr: [],
                territoryTypeList: [],

                pkObj: {
                    territoryType: '',
                    pkName: '',
                    acreage: '',
                    investArea: '',
                    areaRate: '',
                    rentPrice: '',
                    priceUnit: '',
                    parkArea: '',
                    rentableArea: '',
                    vacantArea: '',
                    landBlock: '',
                    estateYear: '',
                    rentArea: '',
                    useElecTotal: '',
                    investmentVacantland: '',
                    parkingSpace: '',
                    roadWidth: '',
                    maxPassCar: '',
                    propertyCost: '',
                    propertyCompany: '',
                    longestLease: '',
                    shortestLease: '',
                    manageYear: '',
                    buildingTotal: '',
                    industryRestrictions: '',

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

                    provinceCode: '',
                    cityCode: '',
                    regionCode: '',
                    streetCode: '',
                    provinceName: '',
                    cityName: '',
                    regionName: '',
                    streetName: '',
                    address: '',

                    businessStartDay: '',
                    businessEndDay: '',
                    meterReading: '',
                    averagePrice: '',

                    buildTitle: '',
                    leadTrade: '',
                    trafficMatching: '',
                    favouredPolicy: '',
                    describe: '',

                    parkVideo: '',
                    aerialView: '',
                    outPicture: '',
                },

                rules: {
                    territoryType: [{
                        required: true,
                        message: '请选择需求大区',
                        trigger: 'change'
                    }],
                    pkName: [{
                        required: true,
                        message: '园区名称输入不能为空',
                        trigger: 'blur'
                    }, ],
                    acreage: [{
                            required: true,
                            message: '承租面积输入不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    investArea: [{
                            required: true,
                            message: '计租面积输入不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    areaRate: [{
                            required: true,
                            message: '公摊比输入不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    useElecTotal: [{
                            required: true,
                            message: '总用电量输入不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    shortestLease: [{
                            required: true,
                            message: '最短租期输入不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    parkingSpace: [{
                            required: true,
                            message: '停车位输入不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    roadWidth: [{
                            required: true,
                            message: '道路宽度输入不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    buildingTotal: [{
                            required: true,
                            message: '楼栋总数输入不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    longestLease: [{
                            required: true,
                            message: '最长租期输入不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    estateYear: [{
                        validator: checkNumber
                    }],
                    industryRestrictions: [{
                        required: true,
                        message: '行业限制输入不能为空',
                        trigger: 'blur'
                    }, ],

                    provinceCode: [{
                        required: true,
                        message: '请选择需求省份',
                        trigger: 'change'
                    }],
                    cityCode: [{
                        required: true,
                        message: '请选择需求城市',
                        trigger: 'change'
                    }],
                    regionCode: [{
                        required: true,
                        message: '请选择需求区域',
                        trigger: 'change'
                    }],
                    streetCode: [{
                        required: true,
                        message: '请选择所属街道',
                        trigger: 'change'
                    }],
                    address: [{
                        required: true,
                        message: '详细地址输入不能为空',
                        trigger: 'blur'
                    }, ],

                    businessStartDay: [{
                        required: true,
                        message: '请选择日期',
                        trigger: 'change'
                    }],
                    businessEndDay: [{
                        required: true,
                        message: '请选择日期',
                        trigger: 'change'
                    }],
                    meterReading: [{
                            required: true,
                            message: '抄表日不能为空'
                        },
                        {
                            validator: checkDay
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    averagePrice: [{
                            required: true,
                            message: '拓展平均价格不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    propertyCost: [{
                            required: true,
                            message: '物业费输入不能为空',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                },
                // 时间控件
                pickerOptions1: {
                    shortcuts: [{
                        text: '今天',
                        onClick(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '昨天',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                },
            }
        },
        computed: {
            //公摊比
            areaRate: {
                get: function() {
                    let investArea = this.pkObj.investArea;
                    let acreage = this.pkObj.acreage;
                    if (investArea && acreage) {
                        this.pkObj.areaRate = (((investArea - acreage) / acreage) * 100).toFixed(2);
                        return this.pkObj.areaRate;
                    } else {
                        return 0;
                    }
                },
                set: function() {
                    return 0;
                }
            },
            //            // 园区名拼接
            //            parkName: {
            //                get: function () {
            //                    let provinceName = this.pkObj.provinceName;
            //                    let cityName = this.pkObj.cityName;
            //                    let regionName = this.pkObj.regionName;
            //                    let streetName = this.pkObj.streetName;
            //                    let address = this.pkObj.address;
            //
            //                    let parkNameStr = provinceName + cityName + regionName + streetName + address;
            //
            //                    return parkNameStr;
            //                },
            //                set: function() {
            //                    return null;
            //                }
            //            }
        },
        methods: {
            // 园区名称改变重新赋值
            changeParkAddress() {
                let cityName = this.pkObj.cityName;
                let regionName = this.pkObj.regionName;
                let address = this.pkObj.address;

                let parkNameStr = cityName + regionName + address;

                this.pkObj.pkName = parkNameStr;
            },
            changePkName(value) {
                this.pkObj.pkName = value;
            },
            // 视频和图片选中后的展示事件
            selectVideo(urls) {
                if (urls) {
                    for (let i = 0; i < urls.length; i++) {
                        this.videoList.push(urls[i].fileCode);
                    }
                }
            },
            selectAerialView(urls) {
                if (urls) {
                    for (let i = 0; i < urls.length; i++) {
                        this.aerialViewList.push(urls[i].fileCode);
                    }
                }
            },
            selectOutPicture(urls) {
                if (urls) {
                    for (let i = 0; i < urls.length; i++) {
                        this.outPictureList.push(urls[i].fileCode);
                    }
                }
            },
            // 视频和图片的删除事件
            deleteVideo(index) {
                this.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.videoList.splice(index, 1);
                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {});
            },
            deleteAerialView(index) {
                this.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.aerialViewList.splice(index, 1);
                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {});
            },
            deleteOutPicture(index) {
                this.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.outPictureList.splice(index, 1);
                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {});
            },
            // 园区提交
            parkSubmit(subForm) {
                let provinceName = window.document.getElementById("provinceName");
                let cityName = window.document.getElementById("cityName");
                let regionName = window.document.getElementById("regionName");
                let streetName = window.document.getElementById("streetName");

                // 视频和照片集合转换成字符串
                if (this.videoList.length > 0) {
                    this.pkObj.parkVideo = this.videoList.join(",");
                } else {
                    this.pkObj.parkVideo = '';
                }
                if (this.aerialViewList.length > 0) {
                    this.pkObj.aerialView = this.aerialViewList.join(",");
                } else {
                    this.pkObj.aerialView = '';
                }
                if (this.outPictureList.length > 0) {
                    this.pkObj.outPicture = this.outPictureList.join(",");
                } else {
                    this.pkObj.outPicture = '';
                }

                let vm = this;
                let obj = {};
                obj.entity = vm.pkObj;

                this.$refs[subForm].validate((valid) => {
                    obj.entity.provinceName = provinceName.value;
                    obj.entity.cityName = cityName.value;
                    obj.entity.regionName = regionName.value;
                    obj.entity.streetName = streetName.value;

                    if (valid) {
                        let options = {
                            method: 'POST',
                            data: obj,
                            url: 'park/update',
                        };
                        this.$ajax(
                            options
                        ).then(function(response) {
                            if (response) {
                                vm.$message({
                                    showClose: true,
                                    message: '修改园区信息成功',
                                    type: 'success',
                                });

                                // 把当前修改页签关闭
                                let dataDelObj = {
                                    flag: 'del',
                                    menuName: '园区修改',
                                    item: 'parkEdit'
                                };
                                vm.$emit('backParam', dataDelObj);

                                let dataCloseDetails = {
                                    menuName: '园区详情',
                                    item: 'parkDetails',
                                    params: {
                                        pkCode: obj.entity.pkCode
                                    }
                                };
                                vm.$emit('backParam', dataCloseDetails);

                            }
                        }).catch(function(error) {
                            vm.$alert('页面：修改园区失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {}
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
            // 街道查询
            getStreetList(aVal) {
                if (aVal) {
                    let vm = this;
                    let obj = {};
                    obj.parentCode = aVal;
                    let sendObj = {};
                    sendObj.entity = obj;
                    let options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function(response) {
                        vm.itStreetArr = response.data.result;
                    }).catch(function(error) {
                        vm.$alert('页面：查询街道信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }
            },
            // 区域查询
            getAreaList(bVal) {
                if (bVal) {
                    let vm = this;
                    let obj = {};
                    obj.parentCode = bVal;
                    let sendObj = {};
                    sendObj.entity = obj;
                    let options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function(response) {
                        vm.itAreaArr = response.data.result;
                    }).catch(function(error) {
                        vm.$alert('页面：查询区域信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }
            },
            // 市区查询
            getCitieList(aVal) {
                if (aVal != '') {
                    let vm = this;
                    let obj = {};
                    obj.parentCode = aVal;
                    let sendObj = {};
                    sendObj.entity = obj;
                    let options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function(response) {
                        vm.itCityArr = response.data.result;
                    }).catch(function(error) {
                        vm.$alert('页面：查市区信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    });
                }

            },
            // 省份查询
            handNeedProvince(val) {
                let vm = this;
                let obj = {};
                obj.parentCode = 0;
                let sendObj = {};
                sendObj.entity = obj;
                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    vm.itProvinceArr = response.data.result;
                }).catch(function(error) {
                    vm.$alert('页面：查询省份信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
            // 获取街道名
            changeStreet(aval) {
                let obj = {};
                obj = this.itStreetArr.find((item) => {
                    return item.areaCode === aval; // 筛选出匹配数据
                });
                this.pkObj.streetName = obj.name;

                this.pkObj.address = '';
            },
            // 获取区名
            changeRegionCpy(aval) {
                let obj = {};
                obj = this.itAreaArr.find((item) => {
                    return item.areaCode === aval; // 筛选出匹配数据
                });
                this.pkObj.regionName = obj.name;

                // 清空code
                this.pkObj.streetCode = '';
            },
            // 获取城市名
            changeCityCpy(aval) {
                let obj = {};
                obj = this.itCityArr.find((item) => {
                    return item.areaCode === aval; // 筛选出匹配数据
                });
                this.pkObj.cityName = obj.name;

                // 清空code
                this.pkObj.regionCode = '';
                this.pkObj.streetCode = '';
            },
            // 获取省份名
            changeProvCpy(aval) {
                let obj = {};
                obj = this.itProvinceArr.find((item) => {
                    return item.areaCode === aval; // 筛选出匹配数据
                });
                this.pkObj.provinceName = obj.name;

                // 清空code
                this.pkObj.cityCode = '';
                this.pkObj.regionCode = '';
                this.pkObj.streetCode = '';
            },
            // 重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.pkObj.hasCertify = false;
                this.pkObj.hasEia = false;
                this.pkObj.hasSewageCertify = false;
                this.pkObj.hasSupermarket = false;
                this.pkObj.hasCanteen = false;
                this.pkObj.hasPubBooth = false;
                this.pkObj.hasOfficeBuild = false;
                this.pkObj.hasCanopy = false;
                this.pkObj.hasCarPark = false;
                this.pkObj.hasLoadDock = false;
                this.pkObj.hasChargingPile = false;
            },
        },
    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .root_style {
        height: 88vh;
        overflow-y: auto;
        overflow-x: hidden;
    }

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

    /*每个模块，标题下方内容区div*/
    .common_content_div {
        background-color: white;
        margin-bottom: 30px;
        text-align: left;
    }

    /*空格*/
    .konge_row {
        min-height: 15px;
        min-width: 1000px;
        background-color: white;
    }

    /*------------------------------ 导航栏 ------------------------------*/
    /*导航栏DIV*/
    .navigation_div {
        height: 50px;
        margin-top: -50px;

        position: fixed;
        z-index: 200;
    }

    /*导航栏标题*/
    .navigation_title {
        float: left;
        line-height: 50px;
        margin-left: 20px;
        color: #409eff;
    }

    /*导航栏右边按钮*/
    .navigation_button {
        float: left;
        margin-left: 20px;
    }

    /*------------------------------ 公共样式 ------------------------------*/

    .floor-data-ul li {
        float: left;
        list-style: none;
        height: 20px;
        line-height: 20px;
        font-size: 13px;
    }

    /*视频信息特殊样式*/
    .block_box_img_special {
        min-height: 40px;
        background-color: white;
        padding-bottom: 30px;
        margin-bottom: 30px;
        margin-top: -29px;
    }

    .video_div {
        float: left;
        width: 90%;
    }

    /*基本信息配套信息*/
    .basicmessage_mating {
        text-align: left;
    }

    /*视频信息*/
    .block_box_img {
        background-color: white;
        padding-bottom: 30px;
        margin-bottom: 30px;
    }

    /*清除浮动*/
    .clear {
        clear: both;
    }

    /*视频列标题*/
    .video_title {
        height: 100%;
        float: left;
        width: 160px;
        color: #fe980f;
    }

    /*视频列内容*/
    .video_content {
        float: left;
        width: 240px;
        padding-top: 50px;
        padding-bottom: 50px;
        background-color: #f1f1f1;
        margin-left: 20px;
        margin-top: 20px;
    }

    .video {
        width: 240px;
        float: left;
        margin-left: 20px;
        height: 160px;
        margin-top: 20px;
    }
</style>