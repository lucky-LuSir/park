<template>
    <div class="has-position_root" ref="myRoot">
        <!--<div class="navigation_div">
            <el-button class="edit_button" type="primary" size="small" @click="landlordEdit()" v-if="landlordObj.ldCtStatus === '5' ">
                修改
            </el-button>
            <span class="navigation_title" v-if="landlordObj.ldCtStatus != 5">大房东合同：{{ landlordObj.landlordName }} - {{ landlordObj.pkName }}</span>
        </div>-->
        <div class="has-position_header">
            <span class="navigation_title">合同状态：{{ landlordObj.ldCtStatusName }}&nbsp;&nbsp; <a v-if="landlordObj.ldCtStatus === '5'"  @click="toActiviti" class="act_i" >前往工作流</a></span>
        </div>

        <el-form labelSuffix="：" class="form_style">
            <div class="common-title-div">
                <span class="common-title-font">园区信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="园区" class="el-row-style">
                            {{ landlordObj.pkName }}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="承租面积" class="el-row-style">
                            {{ landlordObj.acreage }}&nbsp;㎡
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">业主信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="业主名字" class="el-row-style">
                            {{ landlordObj.landlordName }}&nbsp;{{ landlordObj.landlordSexName }}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="业主电话" class="el-row-style">
                            {{ landlordObj.landlordPhone }}
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">签约人信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="签约人" class="el-row-style">
                            {{ landlordObj.contractor }}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="电话" class="el-row-style">
                            {{ landlordObj.contractorPhone }}
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">合同信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="合同编号" class="el-row-style">
                            {{ landlordObj.ldCtName }}
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="开始时间" class="el-row-style">
                            {{ landlordObj.contractStartDate | timeDate }}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="结束时间" class="el-row-style">
                            {{ landlordObj.contractEndDate | timeDate }}
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="提前交租" class="el-row-style">
                            {{ landlordObj.advanceDay }}&nbsp;天
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="单价" class="el-row-style">
                            {{ landlordObj.rentUnivalence }}&nbsp;{{ landlordObj.rentUnitName }}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="付款周期" class="el-row-style">
                            押&nbsp;{{ landlordObj.depositRule }}&nbsp;个月，付&nbsp;{{ landlordObj.payRule }}&nbsp;个月
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="押金" prop="deposit" class="el-row-style">
                            {{ landlordObj.deposit }}&nbsp;元
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">递增规则</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-if="landlordObj.hasDetailAdd == true">
                        <el-form-item label="递增详情" class="el-row-style">
                            <span v-for="(item, index) in progressiveIncreaseDetailList" style="margin-right: 50px;">
                                第&nbsp;{{ index + 1 }}&nbsp;年递增&nbsp;{{ item }}&nbsp;%
                            </span>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-if="landlordObj.hasDetailAdd == false">
                        <el-form-item label="递增详情"  class="el-row-style">
                            每&nbsp;{{ landlordObj.rentIncreasingCycle }}&nbsp;年递增&nbsp;{{ landlordObj.rentIncreaseProportion }}&nbsp;%
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">合同附件</span>
                <el-button v-if="landlordObj.ldCtStatus == '4'"
                           size="small"
                           type="primary" style="float: left;margin: 12px 0 0 20px;"
                           @click="openUpProtocol">
                    上传补签协议
                </el-button>
            </div>

            <el-dialog
                    title="上传补签协议"
                    :visible.sync="showUpProtocol"
                    width="50%"
                    :close-on-click-modal="false"
                    :before-close="handleClose">

                <!--<el-button size="small">上传补签协议</el-button>-->
                <el-row class="row_style">
                    <el-col  style="line-height: 30px;margin-left: 20px;">
                        <div style="float: left">
                            <file  @selectFile="selectFile"></file>
                        </div>
                    </el-col>
                </el-row>
                <el-row style="padding-bottom: 15px;">
                    <el-col style="" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-for="(item, index) in fileList" :key="item">
                        <div style="margin:8px 0 0 20px;float: left">
                            <span >文件:&nbsp;&nbsp;&nbsp;{{item}}&nbsp;&nbsp;</span><i class="el-icon-delete" title="删除" @click="deleteFile(index)"></i>
                        </div>
                    </el-col>
                </el-row>


                <span slot="footer" class="dialog-footer">
                    <el-button @click="handleClose">取 消</el-button>
                    <el-button type="primary" @click="submitProtocol">确 定</el-button>
                 </span>
            </el-dialog>


            <div class="common-info-div">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-for="(item, index) in contractUrlList" :key="index">
                        <div class="el-row-style">
                            <el-form-item label="文件" class="download_file">
                                <a title="点击下载" @click="downloadFile(item)">{{item}}</a>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div" style="border-bottom: 1px solid white;">
                <span class="common-title-font">大房东租金</span>
            </div>
            <div class="customer-info-div">
                <el-table
                        :data="landlordObj.landlordRentEntityList"
                        border
                        style="width: 100%;line-height: 20px;">
                    <el-table-column
                            type="index">
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="开始日期">
                        <template slot-scope="scope">{{ scope.row.costStartTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="结束日期">
                        <template slot-scope="scope">{{ scope.row.costEndTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="截止日期">
                        <template slot-scope="scope">{{ scope.row.deadline | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="本期租金">
                        <template slot-scope="scope">{{ scope.row.rentPrice | initNum}}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="租金票面税点">
                        <template slot-scope="scope">{{ scope.row.rentInvoiceTaxPoint }}&nbsp;%</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="租金开票金额">
                        <template slot-scope="scope">{{ scope.row.rentOnInvoice | initNum}}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="租金实付税点">
                        <template slot-scope="scope">{{ scope.row.rentRealTaxPoint }}&nbsp;%</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="应付租金税额">
                        <template slot-scope="scope">{{ scope.row.rentTaxPrice | initNum}}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="本期物业费">
                        <template slot-scope="scope">{{ scope.row.propertyPrice | initNum}}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            width="120px"
                            label="物业费票面税点">
                        <template slot-scope="scope">{{ scope.row.propertyInvoiceTaxPoint }}&nbsp;%</template>
                    </el-table-column>
                    <el-table-column
                            width="120px"
                            label="物业费开票金额">
                        <template slot-scope="scope">{{ scope.row.propertyOnInvoice | initNum}}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            width="120px"
                            label="物业费实付税点">
                        <template slot-scope="scope">{{ scope.row.propertyRealTaxPoint }}&nbsp;%</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="应付物业税额">
                        <template slot-scope="scope">{{ scope.row.propertyTaxPrice | initNum}}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="其他应付">
                        <template slot-scope="scope">{{ scope.row.otherPrice | initNum}}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="备注">
                        <template slot-scope="scope">{{ scope.row.remark }}</template>
                    </el-table-column>
                    <el-table-column
                            width="110px"
                            label="总应付金额">
                        <template slot-scope="scope">{{ scope.row.totalPrice | initNum}}&nbsp;元</template>
                    </el-table-column>
                    <!--<el-table-column-->
                            <!--label="优惠金额">-->
                        <!--<template slot-scope="scope">{{ scope.row.discountsPrice | initNum }}&nbsp;元</template>-->
                    <!--</el-table-column>-->

                    <el-table-column
                            label="已付金额">
                        <template slot-scope="scope">{{ scope.row.paidPrice | initNum }}&nbsp;元</template>
                    </el-table-column>
                    <el-table-column
                            label="状态">
                        <template slot-scope="scope" >
                            <el-tag v-if="scope.row.payStatus == '1'" type="danger">{{ scope.row.payStatusName }}</el-tag>
                            <el-tag v-if="scope.row.payStatus == '2'" type="success">{{ scope.row.payStatusName }}</el-tag>
                            <el-tag v-if="scope.row.payStatus == '3'" type="danger">{{ scope.row.payStatusName }}</el-tag>
                            <el-tag v-if="scope.row.payStatus == '4'" type="warning">{{ scope.row.payStatusName }}</el-tag>
                        </template>
                    </el-table-column>

                    <el-table-column
                            fixed
                            width="180px"
                            fixed="right"
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <el-button v-if="showRentPaymentBtn && scope.row.payStatus != '2' && landlordObj.ldCtStatus =='4'" size="mini" @click="toPay(scope.row)" type="text">缴费办理</el-button>
                            <el-button  size="mini" @click="payDetail(scope.row.rentCode)" type="text">缴费详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </el-form>

        <!--缴费弹窗-->
        <landlord-contract-pay v-if="showPay" @backParam="backParamPay($event)" :paramsPay="paramsPay"></landlord-contract-pay>
        <!--缴费详情-->
        <landlord-contract-pay-detail v-if="showPayDetail" @backParam="backParamPayDetail($event)" :paramsPayDetail="paramsPayDetail"></landlord-contract-pay-detail>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import landlordContractPay from "./landllordContractPay.vue";
    import landlordContractPayDetail from "./landlordContractPayDetail.vue";
    import file from "../../../common/components/file.vue";
    export default {
        components: {
            ElRow,
            landlordContractPay,
            landlordContractPayDetail,
            file
            },
        data() {
            return {
                showUpProtocol:false,
                contractUrlList:[],

                fileList:[],
                showRentPaymentBtn:false,//缴费办理按钮
                showPay:false, //缴费办理弹窗
                paramsPay:{}, //付款参数

                showPayDetail: false, //缴费详情
                paramsPayDetail:{}, //缴费详情参数
                // 大房东合同
                landlordObj: {},
                progressiveIncreaseDetailList: [],// 递增详情结合
            }
        },
        props: ['paramsObj'],// 接收父组件数据
        created() {
            this.showRentPaymentBtn = this.$flagMenuStore.judgeMenu("rentPaymentBtn");// 缴费办理按钮
            if (this.paramsObj && this.paramsObj.ldCode) {
                this.landlordObj.ldCode = this.paramsObj.ldCode;
                this.getDetailInfo(this.landlordObj.ldCode)
            }
        },
        methods: {
            // 打开修改页面
            landlordEdit() {
                let data = {
                    menuName: '大房东合同修改',
                    item: 'landlordContractEdit',
                    params: {
                        editType: 'edit',
                        landlordContract: this.landlordObj,
                    }
                };
                this.$emit('backParam', data);
            },
            //缴费办理
            toPay(row){
                let vm = this;
                vm.showPay = true;
                vm.paramsPay.showPay = vm.showPay;
                vm.paramsPay.param = row;
            },

            //付款办理回调
            backParamPay(val){
                this.showPay = val.showPay;
                this.getDetailInfo(this.landlordObj.ldCode)
            },

            //查看缴费详情
            payDetail(code){
                this.showPayDetail = true;
                this.paramsPayDetail.showPayDetail = this.showPayDetail;
                this.paramsPayDetail.code = code;
            },
            //缴费详情回调
            backParamPayDetail(val){
                this.showPayDetail = val.showPayDetail;
            },

            getDetailInfo(ldCode){
                    let params = {
                        entity: {
                            ldCode: ldCode
                        }
                    };

                    let options = {
                        method: 'POST',
                        data: params,
                        url: 'landlord/detail',
                    };

                    this.$ajax(
                        options
                    ).then(response => {
                        if (response) {
                            this.landlordObj = response.data.result;
                            if (this.landlordObj.hasDetailAdd == true) {
                                this.progressiveIncreaseDetailList = this.landlordObj.progressiveIncreaseDetail.split(',')// 详细递增
                            }
                            if(this.landlordObj.contractUrl){
                                this.contractUrlList = this.landlordObj.contractUrl.split('，');
                            }
                        }
                    }).catch(error => {
                        this.$alert('页面：大房东合同详情查询失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                },

            //上传附件
            selectFile(urls){
                if (urls) {
                    for (let i = 0; i < urls.length; i++) {
                        this.fileList.push(urls[i].fileName+'---'+urls[i].fileCode);
                    }
                } else {
                    this.fileList = [];
                }
            },

            // 删除文件
            deleteFile(index){
                this.$confirm('是否删除该文件？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.fileList.splice(index, 1);

                    this.$message({
                        type: 'success',
                        message: '删除成功！'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

            //下载文件
            downloadFile(item) {
                let fileCodeList = item.split('---');

                let fileCode = fileCodeList[1];
                if(!fileCode) {
                    this.$alert('页面：下载文件编码为空！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                    return;
                }

                // 本地测试打开
//                window.location.href="http://localhost:8380/park/file/download/" + fileCode;

                // 发布线上打开
                window.location.href="/park/file/download/" + fileCode;

            },

            handleClose(){
                this.fileList = [];
                this.showUpProtocol = false;
            },

            //提交补签协议
            submitProtocol(){
                let vm = this;
                let fileList = vm.fileList;

                if(fileList.length<1){
                    vm.$message({
                        type: 'warning',
                        message: '请选择文件！'
                    })
                    return;
                }

                for(let i = 0; i < fileList.length; i++){
                    vm.contractUrlList.push(fileList[i]);
                }
                let contractUrl = vm.contractUrlList.join("，");

                let params = {
                    entity:{
                        id: vm.landlordObj.id,
                        ldCode: vm.landlordObj.ldCode,
                        contractUrl: contractUrl
                    }
                }

                let options = {
                    method: 'post',
                    data: params,
                    url: '/landlord/updateContractUrl'
                }
                this.$ajax(
                    options
                ).then(res => {
                    vm.fileList = [];
                    vm.showUpProtocol = false;
                    vm.$message({
                        type: 'success',
                        message: '添加成功！'
                    })
                }).catch(error =>{
                    vm.$message({
                        type: 'error',
                        message: '添加失败！'
                    });
                })
            },
            //打开补签协议弹框
            openUpProtocol(){
                this.showUpProtocol = true;
            },

            toActiviti(){
                let data = {
                    menuName: '大房东合同工作流',
                    item: 'landlordRentApply',
                    params: {
                    }
                };
                this.$emit('backParam', data);
            }
        },


    }
</script>

<style scoped>
    .form_style {
        height: 80vh;
        overflow-y: auto;
        overflow-x: hidden;
    }

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
        line-height: 50px;
    }

    /*修改按钮*/
    .edit_button {
        float: left;
        margin-left: 5px;
        margin-top: 3px;
        margin-right: 20px;
    }

    /*导航栏标题*/
    .navigation_title{
        font-size: 17px;
        color: #409EFF;
        margin-left: 20px;
    }

    /*基本信息 div*/
    .customer-info-div {
        background-color: white;
        text-align: left;
    }

    /*行样式*/
    .el-row-style{
        height: 20px;
        line-height: 20px;
        margin-left: 20px;
    }

    .download_file a:hover{
        cursor: pointer;
        color: #66b1ff;
        text-decoration: underline;
    }
    /*基本信息div*/
    .common-info-div{
        background-color: white;
        width: 100%;
        text-align: left;
    }

    .act_i{
        cursor:pointer;
        font-size: smaller;
        color: red;
        text-decoration:underline;
        font-style:italic
    }
    .act_i:hover{
        color: blue !important;
    }

</style>