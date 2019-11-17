<template>
    <el-dialog
            title="修改园区进出信息"
            :visible.sync="showEdit"
            :close-on-click-modal="false"
            width="80%"
            center
            top="5vh"
            :before-close="handleClose">
        <el-form :model="dataObj" ref="dataObj" :rules="rules" label-width="110px" label-suffix=":">
            <div style="text-align: left;background-color: rgb(242,242,242);">
                <el-card>
                    <el-row>
                        <el-col :xs="15" :sm="14" :md="13" :lg="12" :xl="12">
                            <el-form-item label="园区" prop="pkCode">
                                <el-select :disabled="disabled" size="small" v-model="dataObj.pkCode" filterable  placeholder="选择园区" @change="changePk">
                                    <el-option
                                            v-for="item in pkNames"
                                            :key="item.code"
                                            :label="item.name"
                                            :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="8" :sm="8" :md="9" :lg="8" :xl="8">
                            <el-form-item label="被访客户/公司" prop="cusCode">
                                <el-autocomplete
                                        :disabled="disabled"
                                        clearable
                                        size="small"
                                        class="inline-input"
                                        v-model="dataObj.company"
                                        :fetch-suggestions="querySearchCus"
                                        placeholder="被访客户/公司"
                                        @select="handleSelectCus"
                                ></el-autocomplete>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="9" :lg="8" :xl="8">
                            <el-form-item label="被访人" prop="beVisitorName">
                                <el-input :disabled="disabled" size="small" v-model="dataObj.beVisitorName" placeholder="被访人"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="9" :lg="8" :xl="8">
                            <el-form-item label="被访人手机号" prop="beVisitorPhone">
                                <el-input :disabled="disabled" size="small" v-model="dataObj.beVisitorPhone" placeholder="被访人手机号"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-card>
                <el-card>
                    <el-row>
                        <el-col :xs="8" :sm="8" :md="9" :lg="8" :xl="8">
                            <el-form-item label="进出类型" prop="inOutType">
                                <el-radio :disabled="disabled" v-model="dataObj.inOutType" label="1">车辆进出</el-radio>
                                <el-radio :disabled="disabled" v-model="dataObj.inOutType" label="2" @change="selectPerson">人员进出</el-radio>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row v-if="dataObj.inOutType === '1'">
                        <el-col :xs="8" :sm="8" :md="9" :lg="8" :xl="8">
                            <el-form-item label="车牌号" prop="licenseNum">
                                <!--                                <el-input size="small" style="max-width: 217px;" v-model="dataObj.licenseNum"></el-input>-->
                                <el-autocomplete
                                        :disabled="disabled"
                                        clearable
                                        size="small"
                                        class="inline-input"
                                        v-model="dataObj.licenseNum"
                                        :fetch-suggestions="querySearchLN"
                                        @select="handleSelectLN"
                                ></el-autocomplete>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="8" :sm="8" :md="9" :lg="8" :xl="8">
                            <el-form-item label="来访人" prop="visitor">
                                <el-input :disabled="disabled" size="small" v-model="dataObj.visitor"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="9" :lg="8" :xl="8">
                            <el-form-item label="来访人手机号" prop="visitorPhone">
                                <el-input :disabled="disabled" size="small" v-model="dataObj.visitorPhone"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="9" :lg="8" :xl="8">
                            <el-form-item label="身份证" prop="visitorIdCard">
                                <el-input :disabled="disabled" size="small" v-model="dataObj.visitorIdCard"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :xs="8" :sm="8" :md="6" :lg="8" :xl="8">
                            <el-form-item label="来访人数" prop="visitorNum">
                                <el-input :disabled="disabled" size="small" v-model="dataObj.visitorNum"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="9" :sm="9" :md="10" :lg="9" :xl="9">
                            <el-form-item label="来访目的" prop="visitPurpose">
                                <el-input :disabled="disabled" size="small" type="textarea" v-model="dataObj.visitPurpose"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-card>
                <el-card>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
                            <el-form-item label="来访时间" prop="inTime">
                                <el-date-picker
                                        :disabled="disabled"
                                        size="small"
                                        style="max-width: 217px"
                                        v-model="dataObj.inTime"
                                        type="datetime"
                                        placeholder="选择日期时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
                            <el-form-item label="离开时间" prop="outTime">
                                <el-date-picker
                                        size="small"
                                        v-model="dataObj.outTime"
                                        type="datetime"
                                        placeholder="选择日期时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="9" :sm="9" :md="10" :lg="9" :xl="9">
                            <el-form-item label="备注" prop="remark">
                                <el-input :disabled="disabled" type="textarea" v-model="dataObj.remark"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-card>
            </div>
        </el-form>
        <span slot="footer">
            <el-button @click="handleClose">取 消</el-button>
            <el-button type="primary" @click="submitCreate('dataObj')">确 定</el-button>
        </span>
    </el-dialog>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElForm from "../../../node_modules/element-ui/packages/form/src/form";
    import ElFormItem from "../../../node_modules/element-ui/packages/form/src/form-item";
    export default{
        components: {
            ElFormItem,
            ElForm,
            ElCol,
            ElRow},
        props:['paramsEdit'],
        created(){
            let vm = this;
            if(vm.paramsEdit){
                vm.showEdit = vm.paramsEdit.showEdit;
                vm.dataObj = vm.paramsEdit.dataObj;
                vm.selectAllPark();
                vm.selectCus();

                if(vm.paramsEdit.type == 'out'){
                    vm.disabled = true;
                }
            }
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

            // 校验数字只能为整数
            let checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('只能为正整数'));
                    } else {
                        callback();
                    }
                }, 100);
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
                carList: [],
                dataObj: {
                    pkCode:'',
                    pkName:'',
                    licenseNum:'',
                    inTime:'',
                    outTime:'',
                    visitor:'',
                    visitorPhone:'',
                    cusCode:'',
                    company:'',
                    otherCompany:'',
                    remark: '',
                    visitorNum: '',
                    visitPurpose: '',
                    beVisitorName: '',
                    beVisitorPhone: '',
                    visitorIdCard: '',
                    inOutType: '1',
                },

                dateTime: [],
                disabled: false,

                showEdit: false,
                pkNames: [],
                pkObj: [],

                cusNames: [],
                cusObj: [],


                rules:{
                    visitor: [
                        {required: true, message: '请输入访问人姓名', trigger: 'blur'},
                    ],
                    pkCode: [
                        {required: true, message: '请选择园区', trigger: 'blur'},
                    ],
                    inTime: [
                        {required: true, message: '请选择进入时间', trigger: 'blur'},
                    ],
                    visitorPhone: [
                        {required: true, message: '请输入来访者手机号', trigger: 'blur'},
                        {validator: checkPhone}
                    ],
                    visitorNum: [
                        {required: true, message: '请输入来访人数', trigger: 'blur'},
                        {validator: checkInteger}
                    ],
                    beVisitorName: [
                        {required: true, message: '请输入被访问人姓名', trigger: 'blur'},
                    ],
                    beVisitorPhone: [
                        {required: true, message: '请输入被访者手机号', trigger: 'blur'},
                        {validator: checkPhone}
                    ],
                    visitorIdCard: [
                        {required: true, message: '请输入来访人身份证号', trigger: 'blur'},
                        {validator: checkIdCard},
                    ],
                    inOutType: [
                        {required: true, message: '请选择进出类型', trigger: 'blur'},
                    ],
                    licenseNum: [
                        {required: true, message: '请输入车牌号', trigger: 'blur'},
                    ]
                }
            }
        },

        methods:{
            handleClose(){
                let vm = this;
                vm.showEdit = false;
                let data = {
                    showEdit:false
                };
                vm.$emit('backParam',data);
            },

            // 查询所有园区
            selectAllPark(){
                let vm = this;

                let parkOptions = {
                    method: 'POST',
                    data: {},
                    url: "park/list",
                };

                this.$ajax(
                    parkOptions
                ).then(function (response) {
                    if (response) {
                        let result = response.data.result;
                        let pkNameList = [];
                        let pkObj = [];
                        for (let s = 0; s < result.length; s++) {
                            let code = result[s].pkCode;
                            let name = result[s].pkName;
                            pkNameList.push(
                                {code: code, name: name}
                            );
                            pkObj[code] = name;
                        }
                        vm.pkNames = pkNameList;
                        vm.pkObj = pkObj;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：园区列表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            changePk(){
                let vm = this;
                this.selectCus();
            },
            //获取用户信息
            selectCus(){
                let vm = this;
                let params = {
                    entity: {
                        pkCode: vm.dataObj.pkCode,
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
                            cusNames.push({code: code, value: name});
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

            //提交
            submitCreate(ruleFormVal){
                let vm = this;
                vm.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {
                        if (vm.dataObj.cusCode == '' && vm.dataObj.beVisitorName == '') {
                            vm.$message.error('请输入被访客户');
                            return false;
                        }

                        let pkCode = vm.dataObj.pkCode;
                        let pkName = vm.pkObj[pkCode];
                        vm.dataObj.pkName = pkName;

                        let params = {
                            entity: vm.dataObj
                        }
                        let options = {
                            method: "POST",
                            data: params,
                            url: "/car/updateAll"
                        }
                        this.$ajax(
                            options
                        ).then(response => {
                            if (response) {
                                this.$message({
                                    message: '修改成功!',
                                    type: 'success'
                                });
                                vm.handleClose();
                            }
                        })
                    } else {
                        vm.$message.error('填写不规范,请检查!');
                        return false;
                    }
                });

            },


            //获取联系人姓名和手机号
            getRelateInfo(){
                let vm = this;
                let params = {
                    entity: {
                        cusCode: vm.dataObj.cusCode,
                        ctStatus: '4',
                    }
                }
                let options = {
                    method: "POST",
                    data: params,
                    url: "contract/list"
                }
                this.$ajax(
                    options
                ) .then(response => {
                    if(response){
                        let result = response.data.result;
                        if(result && result.length > 0){
                            vm.dataObj.beVisitorName = result[0].relateName;
                            vm.dataObj.beVisitorPhone = result[0].relatePhone;
                        }
                    }
                });
            },
            //获取客户信息车辆
            async selectCarList(){
                let entity = {
                    cusCode: this.dataObj.cusCode,
                    pkCode: this.dataObj.pkCode,
                }
                const res = await this.$ajax.post("carManager/list", {
                    entity
                });
                if (res){
                    this.carList = res.data.result;
                }
            },

            async querySearchLN(queryString, cb) {
                let entity = {
                    cusCode: this.dataObj.cusCode,
                    pkCode: this.dataObj.pkCode,
                }
                const res = await this.$ajax.post("carManager/list", {
                    entity
                });
                if (res){
                    this.carList = res.data.result;
                }

                let carInfo = [];
                this.carList.forEach(res => {
                    carInfo.push({'value': res.licenseNum});
                })
                var results = queryString ? carInfo.filter(this.createFilter(queryString)) : carInfo;
                // 调用 callback 返回建议列表的数据
                cb(results);
            },
            createFilter(queryString) {
                return (res) => {
                    return (res.value.indexOf(queryString) === 0);
                };
            },
            handleSelectLN(item){
                let personInfo = this.carList.filter(res => res.licenseNum == item.value);
                this.dataObj.visitor = personInfo[0].carOwnerName;
                this.dataObj.visitorPhone = personInfo[0].carOwnerPhone;
                this.dataObj.visitorIdCard = personInfo[0].carOwnerIdCard;
            },

            //选择人员进入时清空车牌号
            selectPerson(){
                this.dataObj.licenseNum = '';
            },

            querySearchCus(queryString, cb){
                let cusNames = this.cusNames;
                let results = queryString ? cusNames.filter(res => this.createFilter(queryString)) : cusNames;
                cb(results);
            },

            handleSelectCus(item){
                this.dataObj.cusCode = item.code;
                this.getRelateInfo();
            }
        },
    }
</script>

<style scoped>
    .null_cus{
        color: #ee9900;
    }
    .null_cus:hover{
        cursor: pointer;
        color: #66b1ff;
    }
    .content_div{
        background-color: white;
        margin-top: 10px;
    }
    /deep/ .el-autocomplete{
        display: block;
    }
    /deep/ .el-input{
        max-width: 217px;
    }
    /deeep/ .el-dialog__footer{
        margin-top: -21px;
    }
</style>