<template>
    <div class="has-position_root" ref="myRoot">
        <!-- 导航栏 -->
        <div class="has-position_header">
            <el-button type="primary" size="small" class="menu-info_down_but" @click="submitForm('floorObj')">
                保存
            </el-button>
            <span class="menu-info_span">当前位置：{{floorObj.pkName}} - {{floorObj.bdName}}栋</span>
        </div>
        <el-form class="has-position_main" ref="floorObj" :rules="rules" :model="floorObj" label-width="100px" label-suffix="：">
            <!--第一个方框-->
            <div class="block_box" style="margin-top: 10px;">
                <div class="block_box_info">
                    <span class="menu-info_span">基本信息</span>
                </div>
                <!--空行-->
                <div style="min-height: 10px"></div>
                <el-row :gutter="50">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="楼层号" prop="thisFloor">
                                <el-input placeholder="请输入..." :disabled="true" v-model="floorObj.thisFloor" clearable>
                                    <template slot="append">层</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <div class="grid-content">
                            <el-form-item label="销售定价" prop="floorUnitPrice">
                                <el-tooltip content="记得选择价格单位" placement="right">
                                    <el-input placeholder="请输入..." v-model="floorObj.floorUnitPrice">
                                        <el-select slot="append" prop="priceUnit" v-model="floorObj.priceUnit" placeholder="请选择" style="width: 100px">
                                            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </el-input>
                                </el-tooltip>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <div class="grid-content">
                            <el-form-item label="承租单价" prop="tenantPrice">
                                <el-tooltip content="记得选择价格单位" placement="right">
                                    <el-input placeholder="请输入..." v-model="floorObj.tenantPrice">
                                        <el-select slot="append" prop="tenantUnit" v-model="floorObj.tenantUnit" placeholder="请选择" style="width: 100px">
                                            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </el-input>
                                </el-tooltip>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="50">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="计租面积" prop="investArea">
                                <el-input placeholder="含公摊" v-model="floorObj.investArea">
                                    <template slot="append">㎡</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="层高" prop="floorHeight">
                                <el-input placeholder="请输入..." v-model="floorObj.floorHeight">
                                    <template slot="append">m</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="长*宽" prop="floorDeep">
                                <el-input placeholder="请输入..." v-model="floorObj.floorDeep">
                                    <template slot="append">m</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="柱距" prop="pillarSpacing">
                                <el-input placeholder="请输入..." v-model="floorObj.pillarSpacing">
                                    <template slot="append">m</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="50">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content">
                            <el-form-item label="地坪承重" prop="floorBearWeight">
                                <el-input placeholder="请输入..." v-model="floorObj.floorBearWeight">
                                    <template slot="append">公斤/㎡</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content" style="text-align: left">
                            <el-form-item label="地坪" prop="floorType">
                                <el-radio-group v-model="floorObj.floorType">
                                    <el-radio label="1">环氧地坪</el-radio>
                                    <el-radio label="2">水泥地坪</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <div class="grid-content" style="text-align: left">
                            <el-form-item label="装修类型" prop="decorationType">
                                <el-radio-group v-model="floorObj.decorationType">
                                    <el-radio label="1">毛坯</el-radio>
                                    <el-radio label="2">简装</el-radio>
                                    <el-radio label="3">精装</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="50">
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <div class="grid-content" style="text-align: left">
                            <el-form-item label="用途" prop="checkUseType">
                                <el-checkbox-group v-model="floorObj.checkUseType">
                                    <el-checkbox border v-for="useType in useTypeList" :label="useType" :key="useType">{{useType}}</el-checkbox>
                                </el-checkbox-group>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="50">
                    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
                        <div class="grid-content" style="text-align: left">
                            <el-form-item label="配套信息">
                                <el-checkbox v-model="floorObj.hasElevator" label="通电梯" border></el-checkbox>
                                <el-checkbox v-model="floorObj.hasBathroom" label="有卫生间" border></el-checkbox>
                                <el-checkbox v-model="floorObj.hasOfficeArea" label="有办公区" border></el-checkbox>
                                <el-checkbox v-model="floorObj.hasDriving" label="有行车" border></el-checkbox>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <!-- 第二个方框 -->
            <div class="block_box" style="margin-top: 10px;">
                <div class="block_box_info">
                    <span class="menu-info_span">影像资料</span>
                </div>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div style="height: 160px; line-height: 160px;">
                            <div class="picture_type">
                                <span class="picture_type_span">彩平图&nbsp;&nbsp;&nbsp;</span>
                            </div>
                            <div class="grid-content picture_add">
                                <div class="picture_add_img" v-if="fileType == 'image'" v-for="(item, index) in aerialViewList">
                                    <img style="width: 100%;height: 100%;" :src=" parkBaseUrl + 'file/read/' + item ">
                                    <i @click="deleteImg1(index)" class="el-icon-remove-outline icon"></i>
                                </div>
                                <div class="file_style" v-show="aerialViewList.length < 5">
                                    <file @selectFile="selectFile1"></file>
                                </div>
                            </div>
                        </div>
                    </el-col>
                </el-row>
                <!--<div style="clear: both;"></div>-->
                <div class="line-style" />

                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div style="height: 160px; line-height: 160px;">
                            <div class="picture_type">
                                <span class="picture_type_span">户型图&nbsp;&nbsp;&nbsp;</span>
                            </div>
                            <div class="grid-content picture_add">
                                <div class="picture_add_img" v-if="fileType == 'image'" v-for="(item, index) in outPictureList">
                                    <img style="width: 100%;height: 100%;" :src=" parkBaseUrl + 'file/read/' + item ">
                                    <i @click="deleteImg2(index)" class="el-icon-remove-outline icon"></i>
                                </div>
                                <div class="file_style" v-if="outPictureList.length < 5">
                                    <file @selectFile="selectFile2"></file>
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
                            <div class="grid-content picture_add">
                                <div class="picture_add_img" v-if="fileType == 'image'" v-for="(item, index) in internalDiagramList">
                                    <img style="width: 100%;height: 100%;" :src=" parkBaseUrl + 'file/read/' + item ">
                                    <i @click="deleteImg3(index)" class="el-icon-remove-outline icon"></i>
                                </div>
                                <div class="file_style" v-if="internalDiagramList.length < 5">
                                    <file @selectFile="selectFile3"></file>
                                </div>
                            </div>
                        </div>
                    </el-col>
                </el-row>

                <div class="line-style" />
            </div>


            <!--末尾提交按钮处-->
            <div style="height: 50px;margin-top: 20px;padding-bottom: 60px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="submitForm('floorObj')">保存
                </el-button>
                <el-button style="float: left;" @click="resetForm('floorObj')">重置</el-button>
            </div>

        </el-form>

    </div>

</template>

<script>
    // 引入文件选择器
    import file from '../../../common/components/file.vue'

    export default {
        components: {
            file
        },
        props: ['paramsObj'], // 接收父组件数据
        created: function() {
            let vm = this;

            if (vm.paramsObj) {
                vm.floorObj.pkCode = vm.paramsObj.pkCode;
                vm.floorObj.pkName = vm.paramsObj.pkName;
                vm.floorObj.bdCode = vm.paramsObj.bdCode;
                vm.floorObj.bdName = vm.paramsObj.bdName;
                vm.floorObj.flCode = vm.paramsObj.flCode;
                vm.floorObj.thisFloor = vm.paramsObj.thisFloor;

                if (vm.floorObj.pkCode && vm.floorObj.bdCode) {
                    vm.creOrupdate = "create"; // 楼层新增页
                } else {
                    vm.$message({
                        showClose: true,
                        message: '请先选择楼栋层号',
                        type: 'error'
                    });
                }
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

            return {
                floatDivWidth: '',
                options: [{
                    value: '1',
                    label: '元/㎡/天'
                }, {
                    value: '2',
                    label: '元/㎡/月'
                }, {
                    value: '3',
                    label: '元/㎡/年'
                }],
                floorObj: {
                    pkCode: "",
                    pkName: "",
                    bdCode: "",
                    bdName: "",
                    floorsTotal: "",
                    investArea: "",
                    rentableArea: "",
                    rentedArea: "",
                    floorHeight: "",
                    floorType: "",
                    floorBearWeight: "",
                    thisFloor: "",
                    useType: "",
                    decorationType: "",
                    floorDeep: "",
                    pillarSpacing: "",
                    hasElevator: false,
                    hasDriving: false,
                    hasOfficeArea: false,
                    hasBathroom: false,
                    floorUnitPrice: "",
                    priceUnit: "",
                    tenantPrice: '',
                    tenantUnit: '',

                    // 影像资料
                    aerialView: "",
                    outPicture: "",
                    internalDiagram: "",

                    checkUseType: [], //用途集合
                },
                rules: {
                    investArea: [{
                            required: true,
                            message: '请输入计租面积',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    floorUnitPrice: [{
                            required: true,
                            message: '请输入销售定价',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    priceUnit: [{
                        required: true,
                        message: '请选择定格单位',
                        trigger: 'change'
                    }, ],
                    tenantPrice: [{
                            required: true,
                            message: '请输入承租单价',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    tenantUnit: [{
                        required: true,
                        message: '请选择承租单价单位',
                        trigger: 'change'
                    }, ],

                    floorHeight: [{
                            required: true,
                            message: '请输入层高',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    floorBearWeight: [{
                            required: true,
                            message: '请输入地坪承重',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkIntOrFloat
                        },
                    ],
                    floorType: [{
                        required: true,
                        message: '请选择地坪',
                        trigger: 'change'
                    }],
                    decorationType: [{
                        required: true,
                        message: '请选择装修类型',
                        trigger: 'blur'
                    }],
                    floorDeep: [{
                        required: true,
                        message: '请输入长*宽',
                        trigger: 'blur'
                    }, ],
                    pillarSpacing: [{
                            required: true,
                            message: '请输入柱距',
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
                fileType: 'image',
                aerialViewList: [], //彩平图集合
                outPictureList: [], //户型图集合
                internalDiagramList: [], //细节展示

                useTypeList: ["生产", "仓储", "办公"],
            };
        },
        methods: {
            selectFile1(fileCodes) {
                for (let i = 0; i < fileCodes.length; i++) {
                    if (this.aerialViewList.length < 5) {
                        this.aerialViewList.push(fileCodes[i].fileCode);
                    } else {
                        this.$message.warning("最多上传5张图片");
                        break;
                    }
                }
            },
            selectFile2(fileCodes) {
                for (let i = 0; i < fileCodes.length; i++) {
                    if (this.outPictureList.length < 5) {
                        this.outPictureList.push(fileCodes[i].fileCode);
                    } else {
                        this.$message.warning("最多上传5张图片");
                        break;
                    }
                }
            },
            selectFile3(fileCodes) {
                for (let i = 0; i < fileCodes.length; i++) {
                    if (this.internalDiagramList.length < 5) {
                        this.internalDiagramList.push(fileCodes[i].fileCode);
                    } else {
                        this.$message.warning("最多上传5张图片");
                        break;
                    }
                }
            },
            deleteImg1(index) {
                this.delMessage(this.aerialViewList, index);
            },
            deleteImg2(index) {
                this.delMessage(this.outPictureList, index);
            },
            deleteImg3(index) {
                this.delMessage(this.internalDiagramList, index);
            },
            delMessage(list, index) {
                this.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    list.splice(index, 1);
                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {});
            },
            submitForm(subForm) {
                let vm = {};
                vm = this;

                let obj = {};

                // 处理影像资料照片code
                let aerialView = "",
                    outPicture = "",
                    internalDiagram = "";
                for (let i = 0; i < vm.aerialViewList.length; i++) {
                    aerialView = aerialView + vm.aerialViewList[i] + ",";
                }
                aerialView = aerialView.substring(0, aerialView.length - 1);
                vm.floorObj.aerialView = aerialView;

                for (let i = 0; i < vm.outPictureList.length; i++) {
                    outPicture = outPicture + vm.outPictureList[i] + ",";
                }
                outPicture = outPicture.substring(0, outPicture.length - 1);
                vm.floorObj.outPicture = outPicture;

                for (let i = 0; i < vm.internalDiagramList.length; i++) {
                    internalDiagram = internalDiagram + vm.internalDiagramList[i] + ",";
                }
                internalDiagram = internalDiagram.substring(0, internalDiagram.length - 1);
                vm.floorObj.internalDiagram = internalDiagram;

                vm.floorObj.rentableArea = vm.floorObj.investArea;
                obj.entity = vm.floorObj;


                if (vm.floorObj.checkUseType.length > 0) {
                    for (let i = 0; i < vm.floorObj.checkUseType.length; i++) {
                        if (i == 0) {
                            vm.floorObj.useType = vm.floorObj.checkUseType[i];
                        } else {
                            vm.floorObj.useType = vm.floorObj.useType + "," + vm.floorObj.checkUseType[i];
                        }
                    }
                } else {
                    vm.$message({
                        showClose: true,
                        message: "请选择房屋用途",
                        type: 'error'
                    });
                    return;
                }
                if (!vm.floorObj.tenantUnit) {
                    vm.$message({
                        showClose: true,
                        message: "请选择承租单价单位",
                        type: 'error'
                    });
                    return;
                }
                if (!vm.floorObj.priceUnit) {
                    vm.$message({
                        showClose: true,
                        message: "请选择定价单位",
                        type: 'error'
                    });
                    return;
                }
                this.$refs[subForm].validate((valid) => {
                    if (valid) {
                        let options = {
                            method: 'POST',
                            data: obj,
                            url: 'floor/create',
                        };
                        this.$ajax(
                            options
                        ).then(function(response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '新增楼层信息成功',
                                    type: 'success'
                                });

                                // 楼层新增成功后跳转到楼层详情页
                                let data = {
                                    menuName: '楼层详情',
                                    item: 'floorDetails',
                                    params: {
                                        pkCode: vm.floorObj.pkCode,
                                        pkName: vm.floorObj.pkName,
                                        bdCode: vm.floorObj.bdCode,
                                        bdName: vm.floorObj.bdName,
                                        flCode: response.data.result,
                                        thisFloor: vm.floorObj.thisFloor
                                    }
                                };
                                vm.$emit('backParam', data);

                                // 关闭当前新增页签
                                let dataDelObj = {
                                    flag: 'del',
                                    menuName: '楼栋新增',
                                    item: 'floorCreate'
                                };
                                vm.$emit('backParam', dataDelObj);
                            } else {
                                vm.$message({
                                    showClose: true,
                                    message: response.data.message,
                                    type: 'error'
                                });
                            }
                        }).catch(function(error) {
                            vm.$alert('页面：新增楼层失败', '系统异常', {
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
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                });
            },
            //重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
        },
    }
</script>

<style scoped>
    .konge {
        min-height: 15px;
        min-width: 1160px;
        background-color: rgb(242, 242, 242);
    }

    /*导航栏文字*/
    .menu-info_span {
        color: #409EFF;
        margin-left: 20px;
        line-height: 50px;
        float: left;
    }

    /*导航栏按钮*/
    .menu-info_down_but {
        float: left;
        margin-left: 20px;
    }

    .block_box {
        min-width: 1000px;
        background-color: white;
    }

    .menu-info {
        text-align: left;
        height: 100px;
    }


    .menu-info_down {
        background-color: #FFF;
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }


    .grid-content {
        border-radius: 4px;
        min-height: 36px;
        line-height: 36px;
    }

    .block_box_info {
        background-color: #FFF;
        border-bottom: 1px solid rgb(242, 242, 242);
        height: 50px;
        line-height: 50px;
        text-align: left;
    }

    .picture_type {
        text-align: center;
        float: left;
        margin-left: 30px;
        margin-top: 70px;
        line-height: 22px;
    }

    .picture_type_span {
        color: #409EFF;
    }

    .picture_add {
        margin-left: 50px;
        margin-top: 10px;
        float: left;
        width: 85%;
    }

    .picture_add_img {
        float: left;
        margin-right: 20px;
        height: 150px;
        width: 15%;
    }

    .file_style {
        float: left;
        margin-left: 20px;
        margin-bottom: 10px;
        width: 15%;
        height: 150px;
        line-height: 150px;
        background-color: #F1F1F1;
    }

    /*下划线样式*/
    .line-style {
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    .icon {
        float: right;
        position: relative;
        bottom: 170px;
        left: 18px;
        cursor: pointer;
    }
</style>