<template>
    <div class="has-position_root" ref="myRoot">
        <el-form class="has-position_main" :model="buildingObj" :rules="rules" ref="buildingObj" label-width="120px">

            <div class="has-position_header">
                <el-button type="primary" size="small" class="menu-info_down_but" @click="submitForm('buildingObj')">
                    保存
                </el-button>
                <span class="menu-info_span">当前位置：新增第 {{buildingObj.thisBuilding}} 栋信息</span>
            </div>


            <div class="common-title-div" style="margin-top: 10px;">
                <span class="common-title-font">基本信息</span>
            </div>

            <div class="building-info-root-div">
                <el-row :gutter="20">
                    <el-col :span="6">
                        <div class="grid-content">
                            <el-form-item label="楼栋名称 :" prop="bdName">
                                <el-input class="input-text" v-model="buildingObj.bdName" clearable>
                                    <template slot="append">栋</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :span="6">
                        <div class="grid-content">
                            <el-form-item label="计租面积 :" prop="investArea">
                                <el-input class="input-text" v-model="buildingObj.investArea" placeholder="含公摊">
                                    <template slot="append">㎡</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div class="grid-content">
                            <el-form-item label="共有层数 :" prop="floorsTotal">
                                <el-input v-model="buildingObj.floorsTotal">
                                    <template slot="append">层</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="10">
                        <div class="grid-content">
                            <el-form-item label="建筑结构 :" prop="buildingStructure">
                                <el-radio-group v-model="buildingObj.buildingStructure">
                                    <el-radio label="1">钢结构</el-radio>
                                    <el-radio label="2">砖混</el-radio>
                                    <el-radio label="3">框架</el-radio>
                                    <el-radio label="4">钢混</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="15">
                        <div class="grid-content">
                            <el-form-item label="消防等级 :" prop="firefightingLevel">
                                <el-radio-group v-model="buildingObj.firefightingLevel">
                                    <el-radio label="1">甲类</el-radio>
                                    <el-radio label="2">乙类</el-radio>
                                    <el-radio label="3">丙一类</el-radio>
                                    <el-radio label="4">丙二类</el-radio>
                                    <el-radio label="5">丁类</el-radio>
                                    <el-radio label="6">戌类</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row class="building-input" :gutter="20">
                    <el-col :span="6">
                        <div class="grid-content">
                            <el-form-item label="货梯数量 :" prop="elevatorQuantity"><!--用数据库电梯数量字段-->
                                <el-input class="input-text"
                                          v-model="buildingObj.elevatorQuantity"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :span="6">
                        <div class="grid-content">
                            <el-form-item label="货梯承重 :" prop="freightElevatorWeight">
                                <el-input class="input-text"
                                          v-model="buildingObj.freightElevatorWeight">
                                    <template slot="append">吨/㎡</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :span="6">
                        <div class="grid-content" style="margin-bottom: 15px;">
                            <el-form-item label="货梯开门 :" prop="openDoor">
                                <el-input class="input-text" clearable v-model="buildingObj.openDoor"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">影像资料</span>
            </div>

            <!--视频-->
            <div class="building-img-root-div">
                <div class="building-img-title-div">
                    <span class="building-img-title">视频</span>
                </div>

                <div class="building-picture-root-div">
                    <div class="building-picture-div" v-if="fileType1 == 'video'" v-for="(fileCode,index) in videoList">
                        <i @click="removeVideo(index)" class="el-icon-remove-outline"></i>
                        <!--<div style="height: 100%">-->
                        <video width="100%" height="100%" controls autobuffer>
                            <source :src=" parkBaseUrl + 'file/read/' + fileCode "/>
                        </video>
                        <!--</div>-->
                        <div style="clear:both"></div>
                    </div>

                    <div class="building-picture-button-div">
                        <file @selectFile="selectVideo" class="file-button" v-model="buildingObj.buildingVideo"></file>
                    </div>

                    <div style="clear:both"></div>
                </div>

                <div style="clear:both"></div>
            </div>

            <!--图片-->
            <div class="building-img-root-div">
                <div class="building-img-title-div">
                    <span class="building-img-title">图片</span>
                </div>

                <div class="building-picture-root-div">
                    <div class="building-picture-div" v-if="fileType2 == 'image'" v-for="(fileCode,index) in imgList">
                        <i @click="removeImg(index)" class="el-icon-remove-outline"></i>
                        <img width="100%" height="100%" style='' :src=" parkBaseUrl + 'file/read/' + fileCode ">
                        <div style="clear:both"></div>
                    </div>

                    <div class="building-picture-button-div">
                        <file @selectFile="selectImg" class="file-button" v-model="buildingObj.outPicture"></file>
                    </div>
                    <div style="clear:both"></div>
                </div>

                <div style="clear:both"></div>
            </div>

            <!--末尾提交按钮处-->
            <div style="height: 50px;margin-top: 20px;padding-bottom: 60px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="submitForm('buildingObj')">保存
                </el-button>
                <el-button style="float: left;" @click="resetForm('buildingObj')">重置</el-button>
            </div>


        </el-form>
    </div>
</template>


<script>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import file from '../../../common/components/file.vue'

    export default{
        components: {
            ElButton,
            ElCol,
            file,
            ElRow
        },
        props: ['paramsObj'],// 接收父组件数据
        created: function () {
            let vm = this;

            let pkCode, pkName, thisBuilding, buildingVideo, outPicture;

            if (vm.paramsObj) {
                vm.buildingObj.pkCode = vm.paramsObj.pkCode;
                vm.buildingObj.pkName = vm.paramsObj.pkName;
                vm.buildingObj.thisBuilding = vm.paramsObj.thisBuilding;

                buildingVideo = vm.$route.query.buildingVideo;
                outPicture = vm.$route.query.outPicture;

                if (buildingVideo) {
                    vm.videoList = buildingVideo.split(',');
                }
                if (outPicture) {
                    vm.imgList = outPicture.split(',');
                }

//                vm.buildingObj = vm.paramsObj;
            }
        },
        data(){
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
                // 实体数据
                buildingObj: {
                    pkCode: '',
                    pkName: '',
                    bdCode: '',
                    bdName: '',
                    investArea: '',// 计租面积
                    floorsTotal: '',// 总楼层数
                    buildingStructure: '',// 建筑结构
                    firefightingLevel: '',// 消防等级
                    elevatorQuantity: '',// 货梯数量
                    freightElevatorWeight: '',// 货梯承重
                    openDoor: '',// 货梯开门
                    thisBuilding: '',// 当前楼栋编号
                    buildingVideo: '',// 楼栋视频
                    outPicture: '',// 楼栋视频

                    buildingStatus: '',// 状态
                    rentableArea: '',// 剩余计租面积
                    elevatorSpecification: '',// 电梯规格
                    hasRegister: true,// 是否可注册
                    hasPollutionTreatment: true,// 有无排污证
                    isFreightElevator: true,// 有无货梯
                    isDeed: true,// 是否有产证
                    hasEia: true,// 是否可环评
                    buildingStyle: ''// 建筑特色
                },
                videoList: [],
                imgList: [],
                fileType1: 'video',
                fileType2: "image",
                rules: {
                    bdName: [
                        {required: true, message: '请输入建筑编号', trigger: 'blur'}
                    ],
                    investArea: [
                        {required: true, message: '请输入计租面积', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    buildingStatus: [
                        {required: true, message: '请选择楼层状态', trigger: 'change'}
                    ],
                    floorsTotal: [
                        {required: true, message: '请输入总楼层', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    buildingStructure: [
                        {required: true, message: '请选择建筑结构', trigger: 'change'}
                    ],
                    elevatorQuantity: [
                        {required: true, message: '请输入电梯数量', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    freightElevatorWeight: [
                        {required: true, message: '请输入货梯承重', trigger: 'blur'},
                        {validator: checkNumber},
                        {validator: checkIntOrFloat},
                    ],
                    openDoor: [
                        {required: true, message: '请输入货梯开门类型', trigger: 'blur'}
                    ],
                    elevatorSpecification: [
                        {required: true, message: '请输入电梯规格', trigger: 'blur'}
                    ],
                    firefightingLevel: [
                        {required: true, message: '请选择消防等级', trigger: 'change'}
                    ],
                    hasRegister: [
                        {required: true, message: '请选择是否可注册', trigger: 'change'}
                    ],
                    hasPollutionTreatment: [
                        {required: true, message: '请选择有无排污证', trigger: 'change'}
                    ],
                    isFreightElevator: [
                        {required: true, message: '请选择有无货梯', trigger: 'change'}
                    ],
                    isDeed: [
                        {required: true, message: '请选择是否有产证', trigger: 'change'}
                    ],
                    hasEia: [
                        {required: true, message: '请选择是否可环评', trigger: 'change'}
                    ],
                    buildingStyle: [
                        {required: true, message: '请输入建筑特色', trigger: 'blur'}
                    ]
                },
                imageUrl: '',
                dialogImageUrl: '',
                dialogVisible: false,
                floatDivWidth: '',
            }
        },
        mounted(){
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        methods: {
            // 提交
            submitForm(formName){
                let vm = this;

                let obj = {};

                vm.buildingObj.rentableArea = vm.buildingObj.investArea;

                if (this.videoList.length > 0) {
                    this.buildingObj.buildingVideo = this.videoList.join(',');
                } else {
                    this.buildingObj.buildingVideo = '';
                }

                if (this.imgList.length > 0) {
                    this.buildingObj.outPicture = this.imgList.join(',');
                } else {
                    this.buildingObj.outPicture = '';
                }

                obj.entity = vm.buildingObj;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let options = {
                            method: "POST",
                            data: obj,
                            url: "building/create"
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '楼栋新增成功',
                                    type: 'success'
                                });

                                // 楼栋新增成功后跳转到楼栋详情页
                                vm.buildingObj.bdCode = response.data.result;
                                let data = {
                                    menuName: '楼栋详情',
                                    item: 'buildingDetails',
                                    params: {
                                        pkCode: vm.buildingObj.pkCode,
                                        pkName: vm.buildingObj.pkName,
                                        bdCode: vm.buildingObj.bdCode,
                                        thisBuilding: vm.buildingObj.thisBuilding,
                                    }
                                };
                                vm.$emit('backParam', data);

                                // 关闭当前新增页签
                                let dataDelObj = {
                                    flag: 'del',
                                    menuName: '楼栋新增',
                                    item: 'buildingCreate'
                                };
                                vm.$emit('backParam', dataDelObj);
                            }
                        }).catch(function (error) {
                            vm.$alert('页面：楼栋新增失败', '系统异常', {
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
                });
            },
            // 重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            // 视频上传
            selectVideo(urls){
                if (urls) {
                    for (let i = 0; i < urls.length; i++) {
                        this.videoList.push(urls[i].fileCode);
                    }
                } else {
                    this.videoList = [];
                }
            },
            // 图片上传
            selectImg(urls){
                if (urls) {
                    for (let i = 0; i < urls.length; i++) {
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
            }
        }
    }
</script>


<style scoped>
    /*保存按钮*/
    .menu-info_down_but {
        float: left;
        margin-left: 20px;
    }

    /*悬浮栏文字*/
    .menu-info_span {
        color: #409EFF;
        margin-left: 20px;
        float: left;
        line-height: 50px;
    }

    /****************************************/
    /*  title  公共div */
    .common-title-div {
        background-color: white;
        margin-top: 10px;
        text-align: left;
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*  title  公共文字  */
    .common-title-font {
        color: #409EFF;
        margin-left: 20px;
    }

    /****************************************/
    /*楼栋详情  根div*/
    .building-info-root-div {
        background-color: white;
        text-align: left;
    }

    /*每一列的块区域*/
    .grid-content {
        margin-left: -15px;
        margin-top: 25px;
        /*border: solid 1px;*/
        height: 40px;
    }

    .building-input {
        padding-bottom: 15px;
    }

    /*******************************************************/
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
        /*border: solid 1px;*/
        background-color: #F1F1F1;
    }
</style>