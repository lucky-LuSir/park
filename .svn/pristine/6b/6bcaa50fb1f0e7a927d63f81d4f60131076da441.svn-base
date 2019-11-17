<template>
    <div class="has-position_root" ref="myRoot">
        <div class="has-position_header">
            <el-button @click="floorCreate()" v-if="showAddFloorBtn" v-show="isCreate" type="primary" class="navigation_box_btn" size="small" icon="el-icon-plus">
                新增
            </el-button>
            <el-button @click="editDetails()" v-if="showEditFloorBtn" v-show="!isCreate" type="primary" class="navigation_box_btn" size="small" icon="el-icon-edit-outline">
                修改
            </el-button>

            <div class="change_floor_btn" v-for="(item, index) in thisFloorList" :key="index">
                <el-button v-if="item.flCode&&item.thisFloor == floorObj.thisFloor" size="small" type="primary" @click="chooseFloor(index)">{{item.thisFloor}}层
                </el-button>
                <el-button v-if="item.flCode&&item.thisFloor != floorObj.thisFloor" size="small" type="primary" plain @click="chooseFloor(index)">{{item.thisFloor}}层
                </el-button>
                <el-button v-if="!item.flCode" type="info" plain @click="chooseFloor(index)" size="small">
                    {{item.thisFloor}}层
                </el-button>
            </div>
        </div>
        <el-form class="has-position_main" ref="floorObj" :model="floorObj">
            <div class="common-title-div">
                <span class="common-title-font">毛利率</span>
            </div>
            <div class="common_content_div">
                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="项目毛利率：">
                                <span class="span_info">{{floorObj.projectGross}}%</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="出租毛利率：">
                                <span class="span_info">{{floorObj.investGross}}%</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">基本信息</span>
            </div>
            <div class="common_content_div">
                <el-row class="common_el_row_style">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content">
                            <el-form-item label="园区：">
                                <span class="span_info">{{floorObj.pkName}}，{{floorObj.bdName}}栋，{{floorObj.thisFloor}}层</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content">
                            <el-form-item label="销售定价：">
                                <span class="span_info">{{floorObj.floorUnitPrice}}&nbsp;&nbsp;{{floorObj.priceUnitName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content">
                            <el-form-item label="承租单价：">
                                <span class="span_info">{{floorObj.tenantPrice}}&nbsp;&nbsp;{{floorObj.tenantUnitName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="计租面积：">
                                <span class="span_info">{{floorObj.investArea}}㎡</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="层高：">
                                <span class="span_info">{{floorObj.floorHeight}}m</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="长*宽：">
                                <span class="span_info">{{floorObj.floorDeep}}m</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="柱距：">
                                <span class="span_info">{{floorObj.pillarSpacing}}m</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="地坪承重：">
                                <span class="span_info">{{floorObj.floorBearWeight}}公斤/㎡</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="地坪：">
                                <span class="span_info">{{floorObj.floorTypeName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="装修类型：">
                                <span class="span_info">{{floorObj.decorationTypeName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="用途：">
                                <span class="span_info">{{floorObj.useType}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row class="common_el_row_style">
                    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
                        <div class="grid-content">
                            <el-form-item label="配套信息：">
                                <el-tag v-if="floorObj.hasElevator" type="success" class="tag_info">通电梯</el-tag>
                                <el-tag v-if="floorObj.hasBathroom" type="success" class="tag_info">有卫生间</el-tag>
                                <el-tag v-if="floorObj.hasOfficeArea" type="success" class="tag_info">有办公区</el-tag>
                                <el-tag v-if="floorObj.hasDriving" type="success" class="tag_info">有行车</el-tag>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">其他描述</span>
            </div>
            <div class="common_content_div">
                <el-row class="common_el_row_style">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content">
                            <el-form-item label="剩余计租面积：">
                                <span class="span_info">{{floorObj.rentableArea}}㎡</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>



            <div class="common-title-div">
                <span class="common-title-font">房间信息</span>
                <el-button type="primary" size="small" class="common-title-button" @click="addRoomWindow(floorObj.flCode)">
                    添加房间
                </el-button>
            </div>
            <div class="common_content_div">
                <div style="width: 100%;padding-top: 10px;padding-bottom: 10px;">
                    <el-table :data="roomList" border align="center" size="small" style="line-height: 30px;width: 98%;margin-left: 1%;">
                        <el-table-column align="center" prop="roomNumber" label="房间编号">
                        </el-table-column>
                        <el-table-column align="center" label="房间面积">
                            <template slot-scope="scope">{{ scope.row.investArea }}&nbsp;㎡</template>
                        </el-table-column>
                        <!--<el-table-column-->
                        <!--align="center"-->
                        <!--prop="roomStatusName"-->
                        <!--label="房间状态">-->
                        <!--</el-table-column>-->
                        <el-table-column align="center" label="房间租金">
                            <template slot-scope="scope">{{ scope.row.rentPrice }}&nbsp;元/㎡/天</template>
                        </el-table-column>
                        <el-table-column align="center" label="物业费">
                            <template slot-scope="scope">{{ scope.row.property }}&nbsp;元/月</template>
                        </el-table-column>
                        <el-table-column align="center" label="递增周期">
                            <template slot-scope="scope">{{ scope.row.increasingCycle }}&nbsp;年</template>
                        </el-table-column>
                        <el-table-column align="center" label="免租期天数">
                            <template slot-scope="scope">{{ scope.row.freeRentDays }}&nbsp;天</template>
                        </el-table-column>
                        <el-table-column align="center" label="付租金">
                            <template slot-scope="scope">{{ scope.row.payRule }}&nbsp;月</template>
                        </el-table-column>
                        <el-table-column align="center" label="付押金">
                            <template slot-scope="scope">{{ scope.row.depositRule }}&nbsp;月</template>
                        </el-table-column>
                        <el-table-column align="center" label="付押金">
                            <template slot-scope="scope">{{ scope.row.depositRule }}&nbsp;月</template>
                        </el-table-column>
                        <el-table-column align="center" label="操作" width="100">
                            <template slot-scope="scope">
                                <el-button @click="roomDelete(scope.row.roomCode, scope.row.roomStatus, scope.row.flCode)" type="text" size="small">删除</el-button>
                                <el-button type="text" size="small" @click="roomEdit(
                                    scope.row.id, scope.row.roomCode, scope.row.flCode, scope.row.roomNumber,
                                    scope.row.rentPrice, scope.row.investArea, scope.row.property, scope.row.increasingCycle,
                                    scope.row.freeRentDays, scope.row.payRule, scope.row.depositRule, scope.row.roomStatus)">编辑</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">影像资料</span>
            </div>
            <div class="common_content_div">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div style="height: 160px; line-height: 160px;">
                            <div class="picture_type">
                                <span class="picture_type_span">彩平图&nbsp;&nbsp;&nbsp;</span>
                            </div>
                            <div class="picture_add">
                                <div class="picture_add_img" v-if="fileType == 'image'" v-for="(item, index) in aerialViewList">
                                    <img v-if="item != ''" style="width: 100%;height: 100%;" :src=" parkBaseUrl + 'file/read/' + item ">
                                </div>
                            </div>
                        </div>
                    </el-col>
                </el-row>
                <div class="line-style" />

                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div style="height: 160px; line-height: 160px;">
                            <div class="picture_type">
                                <span class="picture_type_span">户型图&nbsp;&nbsp;&nbsp;</span>
                            </div>
                            <div class="picture_add">
                                <div class="picture_add_img" v-if="fileType == 'image'" v-for="(item, index) in outPictureList">
                                    <img v-if="item != ''" style="width: 100%;height: 100%;" :src=" parkBaseUrl + 'file/read/' + item ">
                                </div>
                            </div>
                        </div>
                    </el-col>
                </el-row>

                <div class="line-style" />

                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div style="height: 160px; line-height: 160px;">
                            <div class="picture_type">
                                <span class="picture_type_span">细节展示</span>
                            </div>
                            <div class="picture_add">
                                <div class="picture_add_img" v-if="fileType == 'image'" v-for="(item, index) in internalDiagramList">
                                    <img v-if="item != ''" style="width: 100%;height: 100%;" :src=" parkBaseUrl + 'file/read/' + item ">
                                </div>
                            </div>
                        </div>
                    </el-col>
                </el-row>
                <div class="line-style" />
            </div>
            <div style="height: 10px;margin-top: 20px;padding-bottom: 60px;"></div>
        </el-form>

        <!-- 房间修改 -->
        <el-dialog title="修改房间" :visible.sync="holdRoomEdit" style="line-height: 20px;border: solid 1px;">
            <el-form :model="roomObj" :rules="rules" ref="roomObj" label-width="110px">
                <el-row :gutter="10" class="common-el-row-style">
                    <el-col :span="12">
                        <el-form-item label="房间编号：" prop="roomNumber">
                            <el-input clearable v-model="roomObj.roomNumber"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="房间面积：" prop="investArea">
                            <el-input v-model="roomObj.investArea" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="房间租金：" prop="rentPrice">
                            <el-input clearable v-model="roomObj.rentPrice">
                                <template slot="append">{{roomObj.priceUnitName}}</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="物业费：" prop="property">
                            <el-input clearable v-model="roomObj.property">
                                <template slot="append">元/月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="递增周期：" prop="increasingCycle">
                            <el-input clearable v-model="roomObj.increasingCycle">
                                <template slot="append">年</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="免租期天数：" prop="freeRentDays">
                            <el-input clearable v-model="roomObj.freeRentDays">
                                <template slot="append">天</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="付款规则：" prop="payRule">
                            <el-input clearable v-model="roomObj.payRule" placeholder="租金付几个月">
                                <template slot="append">月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="押金规则：" prop="depositRule">
                            <el-input clearable v-model="roomObj.depositRule" placeholder="押金付几个月">
                                <template slot="append">月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="holdRoomEdit = false">取 消</el-button>
                <el-button type="primary" @click="roomEditSubmitForm('roomObj')">确 定</el-button>
            </div>
        </el-dialog>

        <!-- 房间新增 -->
        <el-dialog title="修改房间" :visible.sync="holdRoomAdd" style="line-height: 20px;border: solid 1px;">
            <el-form :model="roomAddObj" :rules="rules" ref="roomAddObj" label-width="110px">
                <el-row :gutter="10" class="common-el-row-style">
                    <el-col :span="12">
                        <el-form-item label="房间编号：" prop="roomNumber">
                            <el-input clearable v-model="roomAddObj.roomNumber"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="房间面积：" prop="investArea">
                            <el-input v-model="roomAddObj.investArea" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="房间租金：" prop="rentPrice">
                            <el-input clearable v-model="roomAddObj.rentPrice">
                                <template slot="append">{{roomAddObj.priceUnitName}}</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="物业费：" prop="property">
                            <el-input clearable v-model="roomAddObj.property">
                                <template slot="append">元/月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="递增周期：" prop="increasingCycle">
                            <el-input clearable v-model="roomAddObj.increasingCycle">
                                <template slot="append">年</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="免租期天数：" prop="freeRentDays">
                            <el-input clearable v-model="roomAddObj.freeRentDays">
                                <template slot="append">天</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="付款规则：" prop="payRule">
                            <el-input clearable v-model="roomAddObj.payRule" placeholder="租金付几个月">
                                <template slot="append">月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="押金规则：" prop="depositRule">
                            <el-input clearable v-model="roomAddObj.depositRule" placeholder="押金付几个月">
                                <template slot="append">月</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="resetForm('roomAddObj')">取 消</el-button>
                <el-button type="primary" @click="roomAddSubmitForm('roomAddObj')">确 定</el-button>
            </div>
        </el-dialog>
    </div>

</template>

<script>
    //引入文件选择器
    import file from '../../../common/components/file.vue'
    import ElRow from "element-ui/packages/row/src/row";

    export default {
        components: {
            ElRow,
            file
        },
        props: ['paramsObj'], // 接收父组件数据
        created: function() {
            let vm = this;
            //菜单和按钮权限控制
            vm.showAddFloorBtn = vm.$flagMenuStore.judgeMenu("addFloorBtn");
            vm.showEditFloorBtn = vm.$flagMenuStore.judgeMenu("editFloorBtn");

            if (vm.paramsObj) {
                vm.floorObj.pkCode = vm.paramsObj.pkCode;
                vm.floorObj.pkName = vm.paramsObj.pkName;
                vm.floorObj.bdCode = vm.paramsObj.bdCode;
                vm.floorObj.bdName = vm.paramsObj.bdName;
                vm.floorObj.flCode = vm.paramsObj.flCode;
                vm.floorObj.thisFloor = vm.paramsObj.thisFloor;

                // 查询本栋有多少层
                vm.selectFloorCount(vm.floorObj.bdCode);

                // 查询楼层详情
                let param = {
                    entity: vm.floorObj,
                };
                vm.queryDetails(vm, param);

                // 查询房间
                this.queryRoom(vm.floorObj.flCode);
            }
        },
        mounted() {
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
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
                floatDivWidth: "",
                holdRoomEdit: false, // 控制修改房间弹窗
                holdRoomAdd: false, // 控制新增房间弹窗

                showAddFloorBtn: false,
                showEditFloorBtn: false,
                floorObj: {
                    pkCode: "",
                    pkName: "",
                    bdCode: "",
                    bdName: "",
                    flCode: "",
                    floorsTotal: "",
                    investArea: "",
                    rentableArea: "",
                    rentedArea: "",
                    floorHeight: "",
                    floorType: "",
                    floorBearWeight: "",
                    thisFloor: "",
                    floorUnitPrice: "",
                    priceUnitName: "",
                    useType: "",
                    decorationType: "",
                    floorDeep: "",
                    pillarSpacing: "",
                    hasElevator: "",
                    hasDriving: "",
                    hasOfficeArea: "",
                    hasBathroom: "",

                    // 影像资料
                    aerialView: "",
                    outPicture: "",
                    internalDiagram: ""
                },
                // 图片相关
                fileType: 'image',
                aerialViewList: [], //彩平图集合
                outPictureList: [], //户型图集合
                internalDiagramList: [], //细节展示

                thisFloorList: [],
                roomList: [],
                roomObj: {}, // 房间实体
                roomAddObj: {}, // 房间新增实体
                isCreate: false, //判断新增(编辑)按钮

                rules: {
                    roomNumber: [{
                        required: true,
                        message: '请输入房间编号',
                        trigger: 'blur'
                    }, ],
                    rentPrice: [{
                            required: true,
                            message: '请输入房间租金',
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
                            message: '请输入房间面积',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    property: [{
                            required: true,
                            message: '请输入物业费',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    increasingCycle: [{
                            required: true,
                            message: '请输入递增周期',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    freeRentDays: [{
                            required: true,
                            message: '请输入免租期天数',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    payRule: [{
                            required: true,
                            message: '请输入付款规则',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    depositRule: [{
                            required: true,
                            message: '请输入押金规则',
                            trigger: 'blur'
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                },
            };
        },
        methods: {
            // 查询本栋有多少层
            selectFloorCount(bdCode) {
                let vm = this;

                let params = {
                    entity: {
                        bdCode: bdCode
                    }
                };

                let option = {
                    method: 'POST',
                    data: params,
                    url: 'building/details'
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.thisFloorList = response.data.result.floorList;
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询总层数失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                            vm.$message({
                                type: 'info',
                                message: `action: ${ action }`
                            });
                        }
                    });
                });
            },
            // 删除房间
            roomDelete(roomCode, roomStatus, flCode) {
                this.$confirm('是否删除该房间？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let params = {
                        entity: {
                            roomCode: roomCode,
                            roomStatus: roomStatus
                        }
                    };
                    let options = {
                        method: "POST",
                        data: params,
                        url: "room/realDelete"
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        if (!response.data.isException) {
                            this.$message({
                                showClose: true,
                                message: '房间删除成功',
                                type: 'success'
                            });
                            this.queryRoom(flCode); // 删除成功后查询该楼层下的所有房间
                        } else {
                            this.$message({
                                showClose: true,
                                message: response.data.message,
                                type: 'error'
                            })
                        }
                    })
                }).catch(() => {});
            },
            // 房间修改
            roomEdit(id, roomCode, flCode, roomNumber, rentPrice, investArea, property, increasingCycle, freeRentDays, payRule, depositRule, roomStatus) {
                this.holdRoomEdit = true;

                this.roomObj.id = id;
                this.roomObj.roomCode = roomCode;
                this.roomObj.flCode = flCode;
                this.roomObj.roomNumber = roomNumber;
                this.roomObj.rentPrice = rentPrice;
                this.roomObj.investArea = investArea;
                this.roomObj.property = property;
                this.roomObj.increasingCycle = increasingCycle;
                this.roomObj.freeRentDays = freeRentDays;
                this.roomObj.payRule = payRule;
                this.roomObj.depositRule = depositRule;
                this.roomObj.roomStatus = roomStatus;
                this.roomObj.priceUnitName = this.floorObj.priceUnitName;
            },
            // 房间修改提交
            roomEditSubmitForm(formName) {
                let vm = this;

                let roomObj = {};
                roomObj.entity = vm.roomObj;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let options = {
                            method: "POST",
                            data: roomObj,
                            url: "room/update"
                        };
                        this.$ajax(
                            options
                        ).then(function(response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '房间修改成功',
                                    type: 'success'
                                });
                                vm.holdRoomEdit = false;
                                vm.queryRoom(vm.floorObj.flCode);
                            } else {
                                vm.$message({
                                    showClose: true,
                                    message: response.data.message,
                                    type: 'error'
                                })
                            }
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
            // 重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.holdRoomAdd = false;
            },
            // 添加房间窗口
            addRoomWindow(flCode) {
                this.holdRoomAdd = true;
                this.roomAddObj.flCode = flCode;
                this.roomAddObj.priceUnitName = this.floorObj.priceUnitName;
            },
            // 房间新增表单提交
            roomAddSubmitForm(formName) {
                let vm = this;

                let roomObj = {};
                roomObj.entity = vm.roomAddObj;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let options = {
                            method: "POST",
                            data: roomObj,
                            url: "room/create"
                        };
                        this.$ajax(
                            options
                        ).then(function(response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '房间新增成功',
                                    type: 'success'
                                });
                                vm.$refs[formName].resetFields();
                                vm.holdRoomAdd = false;
                                vm.queryRoom(vm.floorObj.flCode);
                            } else {
                                vm.$message({
                                    showClose: true,
                                    message: response.data.message,
                                    type: 'error'
                                })
                            }
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
            // 新增信息
            floorCreate() {
                let data = {
                    menuName: '楼层添加',
                    item: 'floorCreate',
                    params: {
                        pkCode: this.floorObj.pkCode,
                        pkName: this.floorObj.pkName,
                        bdCode: this.floorObj.bdCode,
                        bdName: this.floorObj.bdName,
                        thisFloor: this.floorObj.thisFloor
                    }
                };
                this.$emit('backParam', data);
            },
            // 修改信息
            editDetails() {
                let data = {
                    menuName: '楼层修改',
                    item: 'floorEdit',
                    params: {
                        paramsObj: this.floorObj,
                    }
                };
                this.$emit('backParam', data);
            },
            // 查询房间
            queryRoom(flCode) {
                let vm = this;
                if (flCode) {
                    let roomObj = {
                        entity: {
                            flCode: flCode
                        }
                    };
                    let option = {
                        method: 'POST',
                        data: roomObj,
                        url: 'room/list'
                    };
                    this.$ajax(
                        option
                    ).then(function(response) {
                        if (!response.data.isException) {
                            vm.roomList = response.data.result;
                        }
                    }).catch(function(error) {
                        vm.$alert('页面：查询房间列表失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                                vm.$message({
                                    type: 'info',
                                    message: `action: ${ action }`
                                });
                            }
                        });
                    });
                } else {
                    vm.roomList = [];
                }
            },
            // 根据楼层显示相应信息
            chooseFloor(index) {
                this.floorObj = this.thisFloorList[index];

                // 图片字符串分割成数组
                this.changePic(this);

                if (!this.floorObj.flCode) {
                    this.$message({
                        showClose: true,
                        message: '未添加本层信息,点击左侧新增本层信息',
                        type: 'warning'
                    });
                    this.isCreate = true;
                } else {
                    this.isCreate = false;
                }
                this.queryRoom(this.floorObj.flCode);
            },
            // 查询详情的方法(公用)
            queryDetails(vm, param) {
                let options = {
                    method: "POST",
                    data: param,
                    url: "floor/queryDetails",
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        if (!(response.data.result.flCode)) {
                            vm.$message({
                                showClose: true,
                                message: '未添加本层信息',
                                type: 'error'
                            });
                            vm.floorObj = response.data.result;
                        } else {
                            vm.floorObj = response.data.result;
                            //图片字符串分割成数组
                            vm.changePic(vm);
                        }
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询楼层详情失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                            vm.$message({
                                type: 'info',
                                message: `action: ${ action }`
                            });
                        }
                    });
                });
            },
            changePic(vm) {
                if (vm.floorObj.aerialView) {
                    vm.aerialViewList = vm.floorObj.aerialView.split(",");
                } else {
                    vm.aerialViewList = [];
                }
                if (vm.floorObj.outPicture) {
                    vm.outPictureList = vm.floorObj.outPicture.split(",");
                } else {
                    vm.outPictureList = [];
                }
                if (vm.floorObj.internalDiagram) {
                    vm.internalDiagramList = vm.floorObj.internalDiagram.split(",");
                } else {
                    vm.internalDiagramList = [];
                }
            },
        }
    }
</script>

<style scoped>
    .root_style {
        height: 88vh;
        overflow-y: auto;
        overflow-x: hidden;
    }

    /*----------------------------------- 导航栏 -----------------------------------*/

    /*切换楼层div*/
    .navigation_box {
        height: 50px;
        margin-top: -50px;

        position: fixed;
        z-index: 200;
    }

    /*导航栏新增或修改*/
    .navigation_box_btn {
        float: left;
        margin-left: 20px;
    }

    /*切换楼层按钮*/
    .change_floor_btn {
        float: left;
        margin-left: 20px;
    }

    /*----------------------------------- 公共样式 -----------------------------------*/

    /*公共标题 div*/
    .common-title-div {
        height: 50px;
        width: 100%;
        background-color: white;
        margin-top: 10px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*标题 文字*/
    .common-title-font {
        float: left;
        line-height: 50px;
        color: #409EFF;
        margin-left: 20px;
    }

    /*标题栏 按钮*/
    .common-title-button {
        float: right;
        margin-top: 10px;
        margin-right: 20px;
    }

    /*内容区*/
    .common_content_div {
        background-color: white;
        text-align: left;
    }

    .grid-content {
        height: 40px;
        line-height: 30px;
    }

    .common_el_row_style {
        margin-left: 20px;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------*/

    .span_info {
        color: #8c939d;
    }

    .tag_info {
        margin-right: 10px;
    }

    .picture_type {
        text-align: center;
        float: left;
        margin-left: 1px;
        margin-top: 70px;
        line-height: 22px;
        width: 8%;
    }

    .picture_type_span {
        color: #409EFF;
    }

    .picture_add {
        margin-left: 50px;
        margin-top: 10px;
        margin-bottom: 10px;
        float: left;
        border-radius: 4px;
        min-height: 36px;
        line-height: 36px;
        width: 85%;
    }

    .picture_add_img {
        float: left;
        margin-right: 10px;
        height: 150px;
        width: 15%;
    }

    /*下划线样式*/
    .line-style {
        border-bottom: 1px solid rgb(242, 242, 242);
    }
</style>