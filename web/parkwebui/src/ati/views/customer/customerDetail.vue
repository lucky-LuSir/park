<template>
    <div class="has-position_root" ref="myRoot">
        <div class="has-position_header">
            <el-button v-if="showEditCustomerBtn" type="primary" @click="customerUpdate(cusObj)" size="small" class="edit-button" icon="el-icon-edit-outline">
                修改
            </el-button>
        </div>
        <el-form class="has-position_main" label-position="left" ref="ruleForm">

            <div class="common-title-div">
                <span class="common-title-font">基本信息</span>
            </div>
            <div class="customer-info-div">
                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="客户类型：">
                            <span>{{cusObj.customerTypeName}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="公司名称：">
                            <span style="float: left">{{cusObj.companyName}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="联系人：">
                            <span>{{cusObj.mainContactName}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="联系电话：">
                            <span style="float: left">{{cusObj.mainContactPhone}}</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row class="common_el_row_style">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所属行业：">
                            <span style="float: left;min-width: 80px">{{cusObj.tradeName}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="公司产品：">
                            <span style="float: left;min-width: 80px">{{cusObj.companyProduct}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="租房用途：">
                            <span style="float: left;min-width: 80px">{{cusObj.useType}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="客户渠道：">
                            <span style="float: left;min-width: 80px">{{cusObj.ditchName}}</span>
                            &nbsp;
                            <span class="upLoad_protocol" @click="showFile">查看附件</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row class="common_el_row_style" v-if="cusObj.channelCode">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="渠道联系人：">
                            <span>{{channelAndCpyObj.userName}}&nbsp;&nbsp;{{channelAndCpyObj.sex == 1 ? "先生" : "女士"}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="渠道电话：">
                            <span>{{channelAndCpyObj.phone}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1" v-if="channelAndCpyObj.companyCode">
                        <el-form-item label="渠道单位：">
                            <span style="float: left;width: 60%;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;" :title="channelAndCpyObj.channelCpyEntity.channelCpyName">{{channelAndCpyObj.channelCpyEntity.channelCpyName}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1" v-if="channelAndCpyObj.companyCode">
                        <el-form-item label="单位地址：">
                            <span style="float: left;min-width: 80px">{{channelAndCpyObj.channelCpyEntity.channelCpyAddress}}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">更多联系人</span>
                <el-button v-if="showAddContactBtn" type="primary" @click="dialogFormVisible = true" class="title-button" size="small" icon="el-icon-plus">
                    添加联系人
                </el-button>
            </div>
            <div class="customer-info-div">
                <el-dialog title="新增联系人信息" :visible.sync="dialogFormVisible" style="line-height: 30px;text-align: center;">
                    <el-form :model="dialogContactObj" label-width="80px" :rules="rules" ref="ruleRef123">
                        <el-row :gutter="5">
                            <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                <div class="grid-content ">
                                    <el-form-item label="姓名" prop="contactName">
                                        <el-input v-model="dialogContactObj.contactName"></el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                            <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                <div class="grid-content ">
                                    <el-form-item label=''>
                                        <el-radio-group v-model="dialogContactObj.contactSex">
                                            <el-radio label="1">先生</el-radio>
                                            <el-radio label="2">女士</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="5">
                            <el-col :xs="15" :sm="15" :md="15" :lg="15" :xl="15">
                                <div class="grid-content ">
                                    <el-form-item label="电话" prop="contactPhone">
                                        <el-input v-model="dialogContactObj.contactPhone"></el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row :gutter="5">
                            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                <div class="grid-content ">
                                    <el-form-item label="备注">
                                        <el-input type="textarea" v-model="dialogContactObj.remark"></el-input>
                                    </el-form-item>
                                </div>
                            </el-col>
                        </el-row>
                    </el-form>
                    <div slot="footer">
                        <el-button @click="dialogFormVisible = false">取消</el-button>
                        <el-button type="primary" @click="handAddContact('ruleRef123')">确认</el-button>
                    </div>
                </el-dialog>

                <div class="more-contracts-table">
                    <el-table :data="cusObj.contactList" border>
                        <el-table-column align="center" min-width="30px" label="姓名" prop="contactName">
                        </el-table-column>
                        <el-table-column align="center" min-width="30px" label="性别" prop="contactSexName">
                        </el-table-column>
                        <el-table-column align="center" min-width="50px" label="客户电话" prop="contactPhone">
                        </el-table-column>
                        <el-table-column align="center" min-width="50px" label="描述" prop="remark">
                        </el-table-column>
                        <el-table-column align="center" label="录入时间" min-width="30px" show-overflow-tooltip>
                            <template slot-scope="scope">{{ scope.row.createTime | timeDate}}</template>
                        </el-table-column>
                        <el-table-column align="center" min-width="40px" label="录入人" prop="createName">
                        </el-table-column>
                    </el-table>
                </div>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">需求信息</span>
            </div>
            <div class="customer-info-div">
                <el-row class="common_el_row_style">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="input-col1">
                        <el-form-item label="所属园区：">
                            <span>{{cusObj.pkName}}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row class="common_el_row_style">
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" class="input-col1">
                        <el-form-item label="需求面积：">
                            <span>{{cusObj.mainArea}}&nbsp;㎡</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" class="input-col1">
                        <el-form-item label="楼层要求：">
                            <span>{{cusObj.floorAskName}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" class="input-col1">
                        <el-form-item label="层高要求：">
                            <span>{{cusObj.floorHeightAsk}}&nbsp; 米</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" class="input-col1">
                        <el-form-item label="电量要求：">
                            <span>{{cusObj.electricAsk}} &nbsp; kva</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="input-col1">
                        <el-form-item label="入驻时间：">
                            <span>{{cusObj.entryTime | timeDate}} &nbsp; 之前</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row class="common_el_row_style">
                    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16" class="input-col1">
                        <el-form-item label="客户要求：">
                            <el-tag v-if="cusObj.hasEia" type="success">有环评</el-tag>
                            <el-tag v-if="cusObj.hasCertify" type="success">有产证</el-tag>
                            <el-tag v-if="cusObj.hasSewageCertify" type="success">有排污证</el-tag>
                            <el-tag v-if="cusObj.hasSupermarket" type="success">有超市</el-tag>
                            <el-tag v-if="cusObj.hasCanteen" type="success">有食堂</el-tag>
                            <!--有公摊-->
                            <el-tag v-if="cusObj.hasOfficeBuild" type="success">有办公楼</el-tag>
                            <el-tag v-if="cusObj.hasCanopy" type="success">有雨棚</el-tag>
                            <!--有停车场-->
                            <el-tag v-if="cusObj.hasLoadDock" type="success">有卸货平台</el-tag>
                            <el-tag v-if="cusObj.hasChargingPile" type="success">有充电桩</el-tag>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row class="common_el_row_style">
                    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16" class="input-col1">
                        <div class="grid-content ">
                            <el-form-item label="客户找房原因：">
                                <span>{{cusObj.reason}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row class="common_el_row_style">
                    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16" class="input-col1">
                        <div class="grid-content ">
                            <el-form-item label="客户关心问题：">
                                <span>{{cusObj.moreDepict}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">找房区域</span>
                <el-button v-if="showAddNeedAreaBtn" class="title-button" type="primary" @click="handOpenAddArea()" size="small" icon="el-icon-plus">
                    添加区域
                </el-button>
            </div>
            <div class="customer-info-div">
                <el-dialog title="新增区域" :visible.sync="dialogAreaVisible" style="line-height: 30px;text-align: center;">
                    <el-form label-width="80px" style="text-align: left;">
                        <el-row :gutter="5">
                            <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                <el-form-item label="省份">
                                    <el-select id="provinceEId" v-model="dialogNeedAreaObj.provinceCode" @visible-change="handNeedProvince" @change="changeProvCpy" placeholder="请选择">
                                        <el-option v-for="(item, index) in itProvinceArr" :key="index" :value="item.areaCode" :label="item.name">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="5">
                            <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                <el-form-item label="城市">
                                    <el-select id="cityEId" v-model="dialogNeedAreaObj.cityCode" @visible-change="getCitieList(dialogNeedAreaObj.provinceCode)" @change="changeCityCpy" placeholder="请选择">
                                        <el-option v-for="(item, index) in itCityArr" :key="index" :value="item.areaCode" :label="item.name">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="5">
                            <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                <el-form-item label="区域">
                                    <el-select id="regionEId" v-model="dialogNeedAreaObj.regionCode" @focus="getAreaList(dialogNeedAreaObj.cityCode)" placeholder="请选择">
                                        <el-option v-for="(item, index) in itAreaArr" :key="index" :value="item.areaCode" :label="item.name">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                    <div slot="footer">
                        <el-button @click="dialogAreaVisible = false">取消</el-button>
                        <el-button type="primary" @click="handAddArea">确认</el-button>
                    </div>
                </el-dialog>

                <el-row class="common_el_row_style" v-for="(areaItem, arrkey) in cusObj.needAreaList" :key=arrkey>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="考虑省份:">
                            <span>{{areaItem.provinceName}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="所在城市:">
                            <span>{{areaItem.cityName}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="所在区域:">
                            <span>{{areaItem.regionName}}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">跟进记录</span>
                <el-button @click="addFollowup(cusObj, 2)" class="title-button" v-if="showAddFlowupBtn" type="primary" size="small" icon="el-icon-plus">
                    添加跟进
                </el-button>
            </div>
            <div class="customer-info-div">
                <div class="more-contracts-table">
                    <el-table style="line-height: 30px;" class="cusDetailFlowTb" :data="followupList" border>
                        <el-table-column align="center" min-width="30px" label="跟进时间" show-overflow-tooltip>
                            <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                        </el-table-column>
                        <el-table-column align="center" min-width="30px" label="客户姓名" prop="cusName">
                        </el-table-column>
                        <el-table-column align="center" min-width="30px" label="客户电话" prop="cusPhone">
                        </el-table-column>
                        <el-table-column align="center" min-width="30px" label="客户状态" prop="cusStatusName">
                        </el-table-column>

                        <el-table-column align="center" min-width="30px" label="跟进类型" prop="followupTypeName">
                        </el-table-column>
                        <el-table-column align="center" min-width="50px" label="跟进结果" prop="followupRes">
                        </el-table-column>
                        <el-table-column align="center" min-width="30px" label="跟进人员" prop="createName">
                        </el-table-column>
                        <el-table-column align="center" min-width="50px" label="来访园区" show-overflow-tooltip prop="pkName">
                        </el-table-column>
                    </el-table>
                </div>
            </div>
            <div style="background-color: rgb(242, 242, 242);height: 80px;"></div>
        </el-form>


        <!-- 添加跟进 -->
        <el-dialog title="添加跟进" style="line-height: 30px" :visible.sync="followupWindow">
            <el-form :model="customerFollowup" :rules="followupRules" ref="customerFollowup" label-width="130px" style="text-align: left;">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="跟进类型：" prop="followupType">
                            <el-radio-group v-model="customerFollowup.followupType">
                                <el-radio label="1">电话拜访</el-radio>
                                <el-radio label="2">客户来访</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="客户状态：" prop="cusStatus">
                            <el-radio-group v-model="customerFollowup.cusStatus">
                                <el-radio label="1">跟进中</el-radio>
                                <el-radio label="2">谈判中</el-radio>
                                <el-radio label="3">已签约</el-radio>
                                <el-radio label="4">不合适</el-radio>
                                <el-tooltip class="item" content="表示已入驻非本公司的园区" placement="top">
                                    <el-radio label="5">在外入驻</el-radio>
                                </el-tooltip>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="15" :sm="15" :md="15" :lg="15" :xl="15">
                        <el-form-item label="来访/跟进时间：" prop="followupTime">
                            <el-date-picker style="width: 100%;" v-model="customerFollowup.followupTime" type="datetime" placeholder="选择日期时间" align="right">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <div v-if="customerFollowup.followupType == 2">
                    <el-row :gutter="20">
                        <el-col :xs="15" :sm="15" :md="15" :lg="15" :xl="15">
                            <el-form-item label="来访园区：" prop="pkCode">
                                <el-select style="width: 100%;" v-model="customerFollowup.pkCode" clearable filterable placeholder="请选择园区" @change="nameByCode">
                                    <el-option v-for="item in parkList" :key="item.code" :label="item.name" :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>
                <el-row :gutter="20">
                    <div v-if="customerFollowup.cusStatus == 4">
                        <el-col :sm="24" :md="24" :lg="24" :xl="24">
                            <el-form-item label="跟进结果：" prop="improperReason">
                                <el-radio-group v-model="customerFollowup.improperReason">
                                    <el-radio label="1">园区不符合客户的要求</el-radio>
                                    <el-radio label="2">客户不符合园区的要求</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                    </div>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="描述说明：" prop="followupRes">
                            <el-input type="textarea" v-model="customerFollowup.followupRes"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div class="block_box_img_up">
                <div class="video_title_up">
                    <span>上传照片:</span>
                </div>
                <div class="video_div_up">
                    <div v-for="(img,index) in outPictureList" :key="index">
                        <img class="video_up" :src=" parkBaseUrl + 'file/read/' + img ">
                        <i class="el-icon-remove-outline" style="float: left" @click="deleteOutPicture(index)"></i>
                    </div>
                    <div class="video_content_up">
                        <file @selectFile="selectOutPicture"></file>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
            <div slot="footer">
                <el-button @click="resetForm('customerFollowup')">重 置</el-button>
                <el-button type="primary" @click="submitForm('customerFollowup')">确 定</el-button>
            </div>
        </el-dialog>

        <!--查看附件-->
        <el-dialog title="查看附件（图片）" :visible.sync="showAttachment" width="52%" :close-on-click-modal="false">
            <div class="block_box_img_special">
                <div v-if="fileList.length != 0" class="video_div">
                    <div v-for="(img,index) in fileList" :key="index">
                        <img class="video" :src=" parkBaseUrl + 'file/read/' + img ">
                    </div>
                </div>
                <div v-else style="float: left">
                    没有附件
                </div>
                <div class="clear"></div>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import file from "../../../common/components/file";
    export default {
        components: {
            ElRow,
            file
        },
        data() {
            // 校验电话号码
            let checkNumber = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (!pattern.test(value)) {
                        callback(new Error("请输入正确的电话号码"));
                    } else {
                        callback();
                    }
                }, 100);
            };

            return {
                outPictureList: [], //附件
                showAttachment: false, //查看附件弹窗
                fileList: [], //附件列表

                // 权限控制
                showEditCustomerBtn: false,
                showAddContactBtn: false,
                showAddNeedAreaBtn: false,
                showEditNeedAreaBtn: false,
                showAddFlowupBtn: false,

                channelAndCpyObj: {}, // 渠道关联渠道单位实体

                // 弹窗控制
                dialogAreaVisible: false, // 添加区域
                dialogFormVisible: false, // 添加更多联系人
                followupWindow: false, // 添加跟进弹窗

                tags: [
                    { name: "接受公摊", type: "" },
                    { name: "需要产证", type: "" },
                    { name: "需要环评", type: "danger" },
                    { name: "需要注册", type: "warning" },
                    { name: "需要办公区域", type: "danger" },
                    { name: "需要装修", type: "" }
                ],

                dialogContactObj: {
                    contactPhone: "",
                    contactName: "",
                    contactSex: "",
                    contactJob: "",
                    remark: "",
                    createTime: ""
                },
                dialogNeedAreaObj: {
                    cusCode: "",
                    provinceCode: "",
                    cityCode: "",
                    regionCode: "",
                    streetCode: "",
                    provinceName: "",
                    cityName: "",
                    regionName: "",
                    streetName: ""
                },
                cusObj: {
                    cusCode: "",
                    customerType: "",
                    customerTypeName: "",
                    companyName: "",
                    trade: "",
                    tradeName: "",
                    companyProduct: "",
                    ditch: "",
                    ditchName: "",
                    mainContactName: "",
                    mainContactSex: "",
                    mainContactPhone: "",
                    mainArea: "",
                    entryTime: "",
                    electricAsk: "",
                    floorAsk: "",
                    floorAskName: "",
                    floorHeightAsk: "",
                    useTypeName: "",

                    hasCertify: true,
                    hasEia: true,
                    hasSewageCertify: true,
                    hasSupermarket: true,
                    hasCanteen: true,
                    hasOfficeBuild: true,
                    hasCanopy: true,
                    hasCarPark: true,
                    hasLoadDock: true,
                    hasChargingPile: true,

                    moreDepict: "",
                    reason: "",
                    cusStatus: "",

                    contactList: [
                    {
                        contactPhone: "",
                        contactName: "",
                        contactSex: "",
                        contactJob: "",
                        remark: "",
                        createTime: "",
                        userName: ""
                    }],
                    needAreaList: [
                    {
                        cusCode: "",
                        provinceCode: "",
                        cityCode: "  ",
                        regionCode: "",
                        streetCode: "",
                        provinceName: "",
                        cityName: "",
                        regionName: "",
                        streetName: ""
                    }]
                },
                // 客户跟进数据集合
                followupList: [],
                itArr: {},
                itProvinceArr: [],
                itCityArr: [],
                itAreaArr: [],
                itStreetArr: [],

                // 跟进信息
                customerFollowup: {
                    cusCode: "",
                    followupType: "", // 跟进类型
                    cusStatus: "", // 跟进客户状态名称
                    followupTime: "", // 跟进时间
                    pkCode: "",
                    pkName: "", // 客户意向房源（来访园区）
                    improperReason: "", // 不合适原因
                    followupRes: "" // 来访结果
                },
                // 添加跟进校验
                followupRules: {
                    receptionType: [
                        { required: true, message: "请选择跟进类型", trigger: "change" }
                    ],
                    followupType: [
                        { required: true, message: "请选择跟进类型", trigger: "change" }
                    ],
                    cusStatus: [
                        { required: true, message: "请选择客户状态", trigger: "change" }
                    ],
                    followupTime: [
                    {
                        type: "date",
                        required: true,
                        message: "请选择时间",
                        trigger: "change"
                    }],
                    pkCode: [{ required: true, message: "请选择园区", trigger: "change" }],
                    followupRes: [
                        { required: true, message: "请填写来访结果", trigger: "blur" }
                    ],
                    improperReason: [
                        { required: true, message: "请填写不合适原因", trigger: "blur" }
                    ]
                },

                rules: {
                    contactName: [
                        { required: true, message: "请输入联系人名称", trigger: "blur" }
                    ],
                    contactPhone: [{ validator: checkNumber, trigger: "blur" }]
                }
            };
        },
        props: ["paramsObj"], // 接收父组件数据
        created: function () {
            let vm = this;

            // 菜单和按钮权限控制
            vm.showEditCustomerBtn = vm.$flagMenuStore.judgeMenu("editCustomerBtn");
            vm.showAddContactBtn = vm.$flagMenuStore.judgeMenu("addContactBtn");
            vm.showAddNeedAreaBtn = vm.$flagMenuStore.judgeMenu("addNeedAreaBtn");
            vm.showEditNeedAreaBtn = vm.$flagMenuStore.judgeMenu("editNeedAreaBtn"); // 编辑(编辑区域按钮)
            vm.showAddFlowupBtn = vm.$flagMenuStore.judgeMenu("addFlowupBtn"); // 添加跟进(按钮)

            if (this.paramsObj && this.paramsObj.cusCode) {
                vm.cusObj.cusCode = vm.paramsObj.cusCode;

                vm.queryCustomerDetail(vm.cusObj.cusCode); // 查询客户详情
                vm.queryCustomerFollowup(vm.cusObj.cusCode); // 查询客户跟进详情
            }
        },
        methods: {
            /*------------------------- created 引用开始 -------------------------*/
            // 查询客户详情
            queryCustomerDetail(cusCode) {
                let vm = this;
                let sendObj = {
                    entity: {
                        cusCode: cusCode
                    }
                };

                let options = {
                    method: "POST",
                    data: sendObj,
                    url: "customer/details"
                };

                this.$ajax(options)
                    .then(function (response) {
                        vm.cusObj = response.data.result;

                        if (vm.cusObj.attachment) {
                            vm.fileList = vm.cusObj.attachment.split("，");
                        }

                        if (vm.cusObj.channelCode) {
                            vm.queryChannelInfo(vm.cusObj.channelCode); // 根据channelCode查询单条渠道并关联渠道单位
                        }
                    })
                    .catch(function (error) {
                        vm.$message.error("页面：获取客户详情失败！");
                    });
            },
            // 查询客户跟进详情
            queryCustomerFollowup(cusCode) {
                let vm = this;

                let params = {
                    entity: {
                        cusCode: cusCode
                    }
                };

                let options = {
                    method: "POST",
                    data: params,
                    url: "followup/queryCusFollowup"
                };

                this.$ajax(options)
                    .then(function (response) {
                        if (!response.data.isException) {
                            vm.followupList = response.data.result.data;
                        }
                    })
                    .catch(function (error) {
                        vm.$alert("页面：查询客户跟进信息失败", "系统异常", {
                            confirmButtonText: "确定",
                            callback: action => {}
                        });
                    });
            },
            /*------------------------- created 引用结束 -------------------------*/

            // 根据channelCode查询渠道并关联单位
            queryChannelInfo(channelCode) {
                let vm = this;

                let param = {
                    entity: {
                        channelCode: channelCode
                    }
                };

                let options = {
                    method: "POST",
                    data: param,
                    url: "customer/channel/queryChannelAndCpy"
                };

                this.$ajax(options)
                    .then(response => {
                        if (!response.data.isException) {
                            vm.channelAndCpyObj = response.data.result;
                        }
                    })
                    .catch(error => {
                        vm.$alert("页面：渠道信息查询失败", "系统异常", {
                            confirmButtonText: "确定",
                            callback: action => {}
                        });
                    });
            },

            // 添加跟进
            addFollowup(rows, flag) {
                let vm = this;

                vm.followupWindow = true;

                vm.customerFollowup.cusCode = rows.cusCode;
                vm.customerFollowup.markDitch = rows.ditch; //客户来源渠道
                vm.customerFollowup.cusChannel = rows.ditch; //客户来源渠道
                vm.customerFollowup.cusName = rows.mainContactName; //客户主联系人姓名
                vm.customerFollowup.cusPhone = rows.mainContactPhone; //客户主联系人电话

                vm.followupWindow = true; // 添加跟进弹窗展示

                let options = {
                    method: "POST",
                    data: {},
                    url: "park/list"
                };
                this.$ajax(options).then(function (response) {
                    if (!response.data.isException) {
                        let result = response.data.result;

                        let pkNameList = [];
                        let pkObj = [];

                        for (let s = 0; s < result.length; s++) {
                            let code = result[s].pkCode;
                            let name = result[s].pkName;
                            pkNameList.push({ code: code, name: name });
                            pkObj[code] = name;
                        }
                        vm.parkList = pkNameList;
                        vm.pkObj = pkObj;
                    } else {
                        vm.$message({
                            showClose: true,
                            message: "园区查询失败！",
                            type: "error"
                        });
                    }
                });
            },
            // 添加跟进 保存按钮
            submitForm(formName) {
                let vm = this;

                this.$refs[formName].validate(valid => {
                    if (valid) {
                        // 客户带看类型处理
                        if (this.customerFollowup.markDitch == 3) {
                            // 客户来源渠道等于3为中介
                            this.customerFollowup.receptionType = "2"; // 中介带看
                        } else {
                            this.customerFollowup.receptionType = "1"; // 主动获客
                        }

                        vm.customerFollowup.attachment = vm.outPictureList.join(",");
                        let obj = {
                            entity: this.customerFollowup
                        };

                        let options = {
                            method: "POST",
                            data: obj,
                            url: "followup/create"
                        };
                        this.$ajax(options).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: "新增客户跟进成功",
                                    type: "success"
                                });

                                // 跟进新增成功后，重置表单并关闭窗口
                                vm.resetForm(formName);
                                vm.followupWindow = false;

                                vm.queryCustomerFollowup(vm.cusObj.cusCode); // 跟进新增成功刷新跟进列表
                            }
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: "信息不完整",
                            type: "error"
                        });
                        return false;
                    }
                });
            },
            // 重置，添加跟进
            resetForm(formName) {
                this.outPictureList = [];
                this.$refs[formName].resetFields();
            },

            handOpenAddArea() {
                this.dialogNeedAreaObj = {
                    cusCode: "",
                    provinceCode: "",
                    cityCode: "",
                    regionCode: "",
                    streetCode: "",
                    provinceName: "",
                    cityName: "",
                    regionName: "",
                    streetName: ""
                };
                this.dialogAreaVisible = true;
            },
            basicReLoadArea() {
                let vm = this;

                let obj = {};
                obj.cusCode = vm.cusObj.cusCode;
                let sendObj = {};
                sendObj.entity = obj;

                let options = {
                    method: "POST",
                    data: sendObj,
                    url: "needArea/query"
                };
                this.$ajax(options)
                    .then(function (response) {
                        vm.$message({
                            message: response.data.message,
                            type: "success"
                        });
                        vm.cusObj.needAreaList = response.data.result.data;
                        let tempArr = response.data.result.data;
                        vm.cusObj.needAreaList = [];
                        for (let i = 0; i < tempArr.length; i++) {
                            vm.cusObj.needAreaList.push(tempArr[i]);
                        }
                    })
                    .catch(function (error) {
                        vm.$message.error("页面：获取数据失败！needArea/query");
                    });
            },
            basicReLoadContact() {
                let vm = this;

                let obj = {};
                obj.cusCode = vm.cusObj.cusCode;
                let sendObj = {};
                sendObj.entity = obj;

                let options = {
                    method: "POST",
                    data: sendObj,
                    url: "relate/query"
                };

                this.$ajax(options)
                    .then(function (response) {
                        vm.$message({
                            message: response.data.message,
                            type: "success"
                        });
                        vm.cusObj.contactList = response.data.result.data;
                    })
                    .catch(function (error) {
                        vm.$message.error("页面：获取数据失败！relate/query");
                    });
            },
            handAddArea() {
                let provinceChinaName = window.document.getElementById("provinceEId")
                    .value;
                let cityChinaName = window.document.getElementById("cityEId").value;
                let regionChinaName = window.document.getElementById("regionEId").value;

                this.dialogNeedAreaObj.provinceName = provinceChinaName;
                this.dialogNeedAreaObj.cityName = cityChinaName;
                this.dialogNeedAreaObj.regionName = regionChinaName;

                let vm = this;

                let obj = {};
                obj = vm.dialogNeedAreaObj;
                obj.cusCode = vm.cusObj.cusCode;
                let sendObj = {};
                sendObj.entity = obj;
                let options = {
                    method: "POST",
                    data: sendObj,
                    url: "needArea/create"
                };
                this.$ajax(options)
                    .then(function (response) {
                        vm.$message({
                            message: response.data.message,
                            type: "success"
                        });
                        vm.basicReLoadArea();
                    })
                    .catch(function (error) {
                        vm.$message.error("页面:获取数据失败!needArea/create");
                    });

                this.dialogAreaVisible = false;
                this.basicReLoadArea();
            },
            handAddContact(ruleFormVal) {
                let vm = this;

                //表单验证
                this.$refs[ruleFormVal].validate(valid => {
                    if (valid) {
                        let obj = {};
                        obj = vm.dialogContactObj;
                        obj.cusCode = vm.cusObj.cusCode;
                        let sendObj = {};
                        sendObj.entity = obj;

                        //存入录入用户
                        sendObj.entity.createCode = vm.$cookieStore.getCookie("userCode");
                        sendObj.entity.createName = vm.$cookieStore.getCookie("userName");
                        let options = {
                            method: "POST",
                            data: sendObj,
                            url: "relate/create"
                        };
                        this.$ajax(options)
                            .then(function (response) {
                                vm.$message({
                                    message: response.data.message,
                                    type: "success"
                                });
                                vm.dialogFormVisible = false;
                                vm.basicReLoadContact();
                            })
                            .catch(function (error) {
                                vm.$message.error("页面:获取数据失败!relate/create");
                            });
                    } else {
                        return false;
                    }
                });
                this.dialogFormVisible = false;
                this.basicReLoadContact();
            },
            // 客户修改
            customerUpdate(rowsCus) {
                let data = {
                    menuName: "客户修改",
                    item: "customerEdit",
                    params: {
                        cusCode: rowsCus.cusCode,
                        channelCode: rowsCus.channelCode
                    }
                };
                this.$emit("backParam", data);
            },
            /*区域查询*/
            getAreaList(bVal) {
                if (bVal) {
                    let vm = this;
                    let obj = {};
                    obj.parentCode = bVal;
                    let sendObj = {};
                    sendObj.entity = obj;
                    let options = {
                        method: "POST",
                        data: sendObj,
                        url: "cpyArea/queryNoPage"
                    };
                    this.$ajax(options)
                        .then(function (response) {
                            vm.itAreaArr = response.data.result;
                        })
                        .catch(function (error) {
                            vm.$message({
                                showClose: true,
                                message: "查询区域信息失败",
                                type: "error"
                            });
                        });
                }
            },
            /*市区查询*/
            getCitieList(aVal) {
                if (aVal != "") {
                    let vm = this;
                    let obj = {};
                    obj.parentCode = aVal;
                    let sendObj = {};
                    sendObj.entity = obj;
                    let options = {
                        method: "POST",
                        data: sendObj,
                        url: "cpyArea/queryNoPage"
                    };
                    this.$ajax(options)
                        .then(function (response) {
                            vm.itCityArr = response.data.result;
                        })
                        .catch(function (error) {
                            vm.$message({
                                showClose: true,
                                message: "查询市区信息失败",
                                type: "error"
                            });
                        });
                }
            },

            handNeedProvince(val) {
                let vm = this;
                let obj = {};
                obj.parentCode = 0;
                let sendObj = {};
                sendObj.entity = obj;
                let options = {
                    method: "POST",
                    data: sendObj,
                    url: "cpyArea/queryNoPage"
                };
                this.$ajax(options)
                    .then(function (response) {
                        vm.itProvinceArr = response.data.result;
                    })
                    .catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: "查询省份信息失败",
                            type: "error"
                        });
                    });
            },
            addNeedArea() {
                this.cusObj.needAreaList.push({
                    provinceCode: "",
                    cityCode: "",
                    regionCode: "",
                    streetCode: ""
                });
            },

            // 省市区级联选择清空
            changeCityCpy(aval) {
                this.dialogNeedAreaObj.regionCode = "";
            },
            changeProvCpy(aval) {
                this.dialogNeedAreaObj.cityCode = "";
                this.dialogNeedAreaObj.regionCode = "";
            },

            //查看附件
            showFile() {
                this.showAttachment = true;
            },

            //选择附件
            selectOutPicture(urls) {
                if (urls) {
                    for (var i = 0; i < urls.length; i++) {
                        if (urls[i].fileType != "image") {
                            this.$message({
                                showClose: true,
                                message: "仅支持图片格式",
                                type: "error"
                            });
                            this.outPictureList = [];
                            return;
                        }
                        this.outPictureList.push(urls[i].fileCode);
                    }
                }
            },

            deleteOutPicture(index) {
                this.$confirm("确定移除这张图片?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    })
                    .then(() => {
                        this.outPictureList.splice(index, 1);
                        this.$message({
                            type: "success",
                            message: "移除成功!"
                        });
                    })
                    .catch(() => {});
            }
        }
    };
</script>

<style scoped>
    .root {
        min-width: 1000px;
        background-color: rgb(242, 242, 242);
    }

    .root_style {
        height: 88vh;
        overflow-y: auto;
        overflow-x: hidden;
    }

    /**************************************************************/

    /*导航栏 div*/
    .navigation-div {
        margin-top: -50px;

        position: fixed;
        z-index: 200;
    }

    /*修改按钮*/
    .edit-button {
        float: left;
        margin-left: 20px;
    }

    /**************************************************************/

    /*公共标题 div*/
    .common-title-div {
        background-color: white;
        height: 50px;
        width: 100%;
        border-bottom: 1px solid rgb(242, 242, 242);
        margin-top: 10px;
    }

    /*公共标题 文字*/
    .common-title-font {
        float: left;
        color: #409eff;
        margin-left: 20px;
        line-height: 50px;
    }

    /*添加更多联系人 按钮*/
    .title-button {
        float: right;
        margin-top: 12px;
        margin-right: 20px;
    }

    /**************************************************************/

    /*基本信息 div*/
    .customer-info-div {
        width: 100%;
        background-color: white;
        text-align: left;
    }

    /*el-row 样式*/
    .common_el_row_style {
        margin-left: 20px;
    }

    .input-col1 {
        height: 35px;
        line-height: 35px;
    }

    /*更多联系人 表格*/
    .more-contracts-table {
        line-height: 20px;
        width: 98%;
        margin-left: 1%;
        padding-top: 10px;
        padding-bottom: 10px;
    }

    /*显示附件*/
    .upLoad_protocol {
        color: #ffc125;
        font-size: 14px;
        float: left;
        cursor: pointer;
    }

    .upLoad_protocol:hover {
        color: blue;
    }

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

    .video {
        width: 240px;
        float: left;
        margin-left: 20px;
        height: 160px;
        margin-top: 20px;
    }

    /*清除浮动*/
    .clear {
        clear: both;
    }

    .block_box_img_up {
        min-height: 40px;
        background-color: white;
        padding-bottom: 30px;
        margin-bottom: 30px;
        margin-top: -29px;
        margin-left: 60px;
    }

    .video_title_up {
        height: 100%;
        width: 220px;
        color: #fe980f;
        text-align: left;
        margin-left: -13px;
    }

    .video_up {
        width: 240px;
        float: left;
        margin-left: 20px;
        height: 160px;
        margin-top: 20px;
    }

    .video_content_up {
        float: left;
        width: 240px;
        padding-top: 50px;
        padding-bottom: 50px;
        background-color: #f1f1f1;
        /*margin-left: 20px;*/
        margin-top: 20px;
        text-align: center;
    }
</style>