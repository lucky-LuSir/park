<template>
    <div class="rootDiv">
        <el-dialog
                class="nofooter-top10-dialog"
                :visible.sync="showEdit"
                width="80%"
                top="10vh"
                :close-on-click-modal="false"
                :before-close="beforeClose"
                center>
            <el-form
                    :model="editInfo"
                    ref="editInfo"
                    label-width="100px"
                    :rules="rules"
                    label-suffix=":">
                <el-card class="task_select">
                    <div slot="header" style="text-align: center">
                        <span style="font-size: 25px">修改车辆信息</span>
                    </div>
                    <el-row>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label="园区" prop="pkCode">
                                <el-select filterable  size="small" v-model="editInfo.pkCode" placeholder="选择园区" @change="changePk">
                                    <el-option
                                            v-for="item in parkList"
                                            :key="item.code"
                                            :label="item.name"
                                            :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label="客户" prop="cusCode">
                                <el-select size="small" v-model="editInfo.cusCode" filterable  clearable placeholder="选择客户">
                                    <el-option
                                            v-for="item in cusNames"
                                            :key="item.code"
                                            :label="item.name"
                                            :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label="车牌号" prop="licenseNum">
                                <el-input size="small" v-model="editInfo.licenseNum" placeholder="车牌号"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label="车主姓名" prop="carOwnerName">
                                <el-input size="small" v-model="editInfo.carOwnerName" placeholder="车主姓名"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label="车主手机号" prop="carOwnerPhone">
                                <el-input size="small" v-model="editInfo.carOwnerPhone" placeholder="车主手机号"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label="车主身份证" prop="carOwnerIdCard">
                                <el-input size="small" v-model="editInfo.carOwnerIdCard" placeholder="车主身份证"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <div class="block_box_img_special">
                        <div class="video_title" >
                            <span>驾驶证照片(必填)</span>&nbsp;&nbsp;<span v-if="noDrivingLicence" style="color: red">请上传图片</span>
                        </div>
                        <div class="video_div">
                            <div v-for="(img,index) in drivingLicenceList">
                                <img class="video" :src=" parkBaseUrl + 'file/read/' + img ">
                                <i class="el-icon-remove-outline" style="float: left"
                                   @click="deleteDrivingLicence(index)"></i>
                            </div>
                            <div class="video_content">
                                <file @selectFile="selectDrivingLicence"></file>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="clear"></div>
                    </div>

                    <div class="block_box_img_special">
                        <div class="video_title" >
                            <span>行驶证照片(必填)</span>&nbsp;&nbsp;<span v-if="noVehicleLicense" style="color: red">请上传图片</span>
                        </div>
                        <div class="video_div">
                            <div v-for="(img,index) in vehicleLicenseList">
                                <img class="video" :src=" parkBaseUrl + 'file/read/' + img ">
                                <i class="el-icon-remove-outline" style="float: left"
                                   @click="deleteVehicleLicense(index)"></i>
                            </div>
                            <div class="video_content">
                                <file @selectFile="selectVehicleLicense"></file>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div style="float: right; padding-bottom: 10px">
                        <el-button type="info" @click="beforeClose">取消</el-button>
                        <el-button type="primary" @click="beforeSubmit('editInfo')">提交</el-button>
                    </div>
                </el-card>
            </el-form>
        </el-dialog>
    </div>
</template>
<script>
    import file from '../../common/components/file';
    export default {
        components: {
            file
        },
        props: ['paramsEdit'],
        created(){
            let vm = this;
            if(vm.paramsEdit){
                vm.showEdit = vm.paramsEdit.showEdit;
                vm.editInfo.id = vm.paramsEdit.id;
            }
            vm.selectInfo();
        },
        data(){
            // 校验电话号码
            let checkPhone = (rule, value, callback) => {
                if(value){
                    setTimeout(() => {
                        let pattern = this.$MOBILE_PATTERN;
                        if (!pattern.test(value)) {
                            callback(new Error('请输入正确的电话号码'));
                        } else {
                            callback();
                        }
                    }, 100);
                }
            };
            // 校验身份证
            let checkIdCard = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/.test(value))) {
                        callback(new Error('请输入正确的身份证号'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {
                drivingLicenceList: [],
                vehicleLicenseList: [],
                noDrivingLicence: true,
                noVehicleLicense: true,

                showEdit: false,
                parkList: [],
                parkNames: [],
                oldPkCode: '',
                oldLicenseNum: '',
                editInfo: {
                    id: '',
                    carCode: '',
                    pkCode: '',
                    pkName: '',
                    cusCode: '',
                    company: '',
                    licenseNum: '',
                    carOwnerName: '',
                    carOwnerPhone: '',
                    carOwnerIdCard: '',
                    drivingLicence: '',
                    vehicleLicense: '',
                },
                cusNames: [],
                cusObj: [],
                rules: {
                    pkCode: [
                        {required: true, message: '请选择园区', trigger: 'change'},
                    ],
                    cusCode: [
                        {required: true, message: '请选择客户', trigger: 'change'},
                    ],
                    licenseNum: [
                        {required: true, message: '请输入车牌号', trigger: 'blur'},
                    ],
                    carOwnerName: [
                        {required: true, message: '请输入车主姓名', trigger: 'blur'},
                    ],
                    carOwnerPhone: [
                        {required: true, message: '请输入车主手机号', trigger: 'blur'},
                        {validator:checkPhone}
                    ],
                    carOwnerIdCard: [
                        {required: true, message: '请输入车主身份证', trigger: 'blur'},
                        {validator:checkIdCard}
                    ]
                }
            }
        },
        methods: {
            //获取所有园区
            getPark(){
                let vm = this;
                let params = {
                    entity:{}
                }
                let options = {
                    method: "POST",
                    data: params,
                    url: "park/list"
                }
                this.$ajax(
                    options
                ).then(response => {
                    let parkList = response.data.result;
                    for(let i = 0; i < parkList.length; i++){
                        vm.parkList.push(
                            {code: parkList[i].pkCode,name: parkList[i].pkName}
                        );
                        vm.parkNames[parkList[i].pkCode] = parkList[i].pkName;
                    }
                }).catch(error => {
                    this.$alert("页面:获取园区失败", "系统异常", {
                        confirmButtonText: "确定",
                        callback: action => {
                        }
                    });
                })
            },
            changePk(){
                let vm = this;
                vm.editInfo.cusCode = '';
                this.selectCus();
            },
            //获取用户信息
            selectCus(){
                let vm = this;
                let params = {
                    entity: {
                        pkCode: vm.editInfo.pkCode,
                        ctStatus: '4',
                    }
                }
                let options = {
                    method: "POST",
                    data: params,
                    url: "/contract/cusList"
                }
                this.$ajax(
                    options
                ).then(response => {
                    if(response){
                        let cusNames = [];
                        let cusObj = [];
                        let data = response.data.result;
                        for(let i = 0; i < data.length; i++){
                            let code = data[i].cusCode;
                            let name = data[i].companyName;
                            cusNames.push({code: code, name: name});
                            cusObj[code] = name;
                        }
                        vm.cusNames = cusNames;
                        vm.cusObj = cusObj;
                    }
                }).catch(error =>{
                    vm.$alert('页面：客户查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },
            //关闭弹框
            beforeClose(){
                let vm = this;
                vm.showEdit = false;
                let data = {
                    showEdit: vm.showEdit
                }
                vm.$emit('backParam',data);
            },
            //提交
            submitInfo(ruleFormVal){
                this.$refs[ruleFormVal].validate(async (valid) => {
                    if (valid){
                        if(this.noVehicleLicense ){
                            this.$message({
                                type: 'wang',
                                message: '请上传行驶证！'
                            });
                            return  false;
                        }
                        if(this.noDrivingLicence){
                            this.$message({
                                type: 'wang',
                                message: '请上传驾驶证！'
                            });
                            return  false;
                        }
                        this.editInfo.drivingLicence = this.drivingLicenceList.join(',');
                        this.editInfo.vehicleLicense = this.vehicleLicenseList.join(',');
                        this.editInfo.pkName = this.parkNames[this.editInfo.pkCode];
                        this.editInfo.company = this.cusObj[this.editInfo.cusCode];
                        var entity = this.editInfo;
                        const res = await this.$ajax.post('carManager/update', {
                            entity
                        });
                        if(res){
                            this.$message({
                                type: 'success',
                                message: '修改成功'
                            })
                            this.beforeClose();
                        }
                    }
                })
            },

            async beforeSubmit(ruleFormVal){
                if(this.editInfo.pkCode != this.oldPkCode || this.editInfo.licenseNum != this.oldLicenseNum){
                    let entity = {
                        pkCode: this.editInfo.pkCode,
                        licenseNum: this.editInfo.licenseNum
                    }
                    const res = await this.$ajax.post('carManager/list', {
                        entity
                    });
                    if(res.data.result.length > 0){
                        this.$message.error('该车牌号已在该园区记录,请勿重复记录');
                    }else{
                        this.submitInfo(ruleFormVal);
                    }
                } else{
                    this.submitInfo(ruleFormVal);
                }
            },
            //获取信息
            async selectInfo(){
                let entity = {
                    id: this.editInfo.id
                }
                const res = await this.$ajax.post("carManager/selectOne",{
                    entity
                });
                if(res){
                    this.editInfo = res.data.result;
                    this.drivingLicenceList = this.editInfo.drivingLicence.split(",");
                    if(this.drivingLicenceList && this.drivingLicenceList.length > 0){
                        this.noDrivingLicence = false;
                    }
                    this.vehicleLicenseList = this.editInfo.vehicleLicense.split(",");
                    if(this.vehicleLicenseList && this.vehicleLicenseList.length > 0){
                        this.noVehicleLicense = false;
                    }
                    this.oldPkCode = res.data.result.pkCode;
                    this.oldLicenseNum = res.data.result.licenseNum;
                    this.getPark();
                    this.selectCus();
                }
            },

            selectDrivingLicence(urls){
                if(urls) {
                    for (var i = 0; i < urls.length; i++) {
                        if(urls[i].fileType != 'image'){
                            this.$message({
                                type: 'wang',
                                message: '仅支持上传图片！'
                            });
                            continue;
                        }
                        this.drivingLicenceList.push(urls[i].fileCode);
                    }
                }
                if (this.drivingLicenceList.length>0) {
                    this.noDrivingLicence = false;
                }
            },

            deleteDrivingLicence(index){
                this.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.drivingLicenceList.splice(index, 1);
                    if (this.drivingLicenceList.length < 1){
                        this.noDrivingLicence = true;
                    }
                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {
                });
            },

            selectVehicleLicense(urls){
                if(urls) {
                    for (var i = 0; i < urls.length; i++) {
                        if(urls[i].fileType != 'image'){
                            this.$message({
                                type: 'wang',
                                message: '仅支持上传图片！'
                            });
                            continue;
                        }
                        this.vehicleLicenseList.push(urls[i].fileCode);
                    }
                }
                if (this.vehicleLicenseList.length>0) {
                    this.noVehicleLicense = false;
                }
            },

            deleteVehicleLicense(index){
                this.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.vehicleLicenseList.splice(index, 1);
                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                    if (this.vehicleLicenseList.length < 1){
                        this.noVehicleLicense = true;
                    }
                }).catch(() => {
                });
            },
        }
    }
</script>
<style scoped>
    /*视频信息特殊样式*/
    .block_box_img_special {
        min-height: 40px;
        background-color: white;
        padding-bottom: 30px;
        margin-bottom: 30px;
        margin-top: -29px;
        margin-left: 60px;
    }
    /*视频列标题*/
    .video_title {
        height: 100%;
        width: 220px;
        color: #FE980F;
    }
    .video_div {
        float: left;
        width: 90%;
    }
    .video {
        width: 240px;
        float: left;
        margin-left: 20px;
        height: 160px;
        margin-top: 20px;
    }
    /*视频列内容*/
    .video_content {
        float: left;
        width: 240px;
        padding-top: 50px;
        padding-bottom: 50px;
        background-color: #F1F1F1;
        /*margin-left: 20px;*/
        margin-top: 20px;
        text-align: center;
    }
    /*清除浮动*/
    .clear {
        clear: both
    }
</style>