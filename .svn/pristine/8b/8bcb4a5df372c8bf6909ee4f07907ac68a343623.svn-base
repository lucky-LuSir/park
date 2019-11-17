<template>
    <div class="has-position_root" ref="myRoot">
        <!-- 导航栏 -->
        <div class="has-position_header">
            <el-button type="primary" size="small" @click="customerEditSubmit('ruleFormRef')" class="save-button">
                提交
            </el-button>
        </div>
        <el-form class="has-position_main" :rules="rules" :model="cusObj" ref="ruleFormRef" label-width="110px">

            <div class="common-title-div" style="margin-top: 10px;">
                <span class="common-title-font">基本信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="客户类型：" prop="customerType">
                            <el-radio-group v-model="cusObj.customerType">
                                <el-radio label="1">公司</el-radio>
                                <el-radio label="2">个人</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="公司名称：" prop="companyName">
                            <el-input v-model="cusObj.companyName" :disabled="cusObj.customerType == 1 ? false : true" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="所属行业：" prop="trade">
                            <el-select v-model="cusObj.trade" placeholder="请选择" style="width:100%;">
                                <el-option v-for="item in industryList" :key="item.value" :label="item.label" :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col1">
                        <el-form-item label="公司产品：" prop="companyProduct">
                            <el-input v-model="cusObj.companyProduct" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>


                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col2">
                        <el-form-item label="联系人：" prop="mainContactName">
                            <el-input v-model="cusObj.mainContactName" @blur="setCompanyName()" placeholder="请输入">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col2">
                        <el-form-item label="性别：" prop="mainContactSex">
                            <el-radio-group v-model="cusObj.mainContactSex">
                                <el-radio label="1">先生</el-radio>
                                <el-radio label="2">女士</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col2">
                        <el-form-item label="联系电话：" prop="mainContactPhone">
                            <el-input v-model="cusObj.mainContactPhone" @blur="checkCusPhone()" placeholder="请输入号码">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" class="input-col2">
                        <el-form-item label="固定电话：">
                            <el-input v-model="cusObj.landlinePhone" placeholder="请输入号码"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">客户来源</span>&nbsp;&nbsp;<span class="upLoad_protocol" @click="upLoadAttachment">上传附件</span>
            </div>
            <div class="customer-info-div">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="input-col1">
                        <el-form-item label="客户来源：" prop="ditch">
                            <el-radio-group v-model="cusObj.ditch">
                                <el-radio label="1">58端口</el-radio>
                                <el-radio label="2">户外横幅</el-radio>
                                <el-radio label="3">小广告/传单</el-radio>
                                <el-radio label="4">微信朋友圈</el-radio>
                                <el-radio label="5">园区建筑广告</el-radio>
                                <el-radio label="6">电话拨打</el-radio>
                                <el-radio label="7">中介</el-radio>
                                <el-radio label="8">兼职/转介绍</el-radio>
                                <el-radio label="10">库房无忧</el-radio>
                                <el-radio label="9">其他</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20" v-if="cusObj.ditch == 7 || cusObj.ditch == 8 || cusObj.ditch == 10">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="input-col2">
                        <el-form-item label="渠道信息：">
                            <el-button type="primary" @click="addChannelGetCpyList()" size="small">
                                新增渠道
                            </el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20" v-if="cusObj.ditch == 7 || cusObj.ditch == 8 || cusObj.ditch == 10">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col2">
                        <el-form-item label="联系人姓名：" prop="channelCode">
                            <el-select v-model="cusObj.channelCode" filterable placeholder="请输入姓名：" @change="selectChannelByCode">
                                <el-option v-for="item in channelList" :key="item.code" :label="item.name" :value="item.code">
                                    <span style=''>{{ item.name }}</span>
                                        <span style="float: right;">{{ item.phone }}</span>
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="性别：">
                            <el-radio-group v-model="cusChannel.channelSex" :disabled="true">
                                <el-radio label="1">先生</el-radio>
                                <el-radio label="2">女士</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="联系人电话：">
                            <el-input v-model="cusChannel.channelPhone" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="联系人职务：">
                            <el-input v-model="cusChannel.channelPost" :disabled="true">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">需求信息</span>
            </div>
            <div class="customer-info-div">
                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col1">
                        <el-form-item label="绑定园区：" label-width="100px" prop="pkCode">
                            <el-select v-model="cusObj.pkCode" placeholder="请选择" filterable @focus="getParkList()" @change="nameByCode">
                                <el-option v-for="item in parkList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col2">
                        <el-form-item label="需求面积：" prop="mainArea">
                            <el-input v-model="cusObj.mainArea" placeholder="请输入数字">
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col2">
                        <el-form-item label="层高要求：" prop="floorHeightAsk">
                            <el-input v-model="cusObj.floorHeightAsk" placeholder="请输入数字">
                                <template slot="append">米</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col2">
                        <el-form-item label="电量要求：" prop="electricAsk">
                            <el-input v-model="cusObj.electricAsk" placeholder="请输入数字">
                                <template slot="append">KVA</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" class="input-col2">
                        <el-form-item label="入驻时间：" prop="entryTime">
                            <el-date-picker style="width: 70%;" v-model="cusObj.entryTime" type="date" placeholder="选择日期" :picker-options="pickerOptions1">
                            </el-date-picker>
                            <span>之前</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="input-col2">
                        <el-form-item label="需求楼层：" prop="floorAsk">
                            <el-radio-group v-model="cusObj.floorAsk">
                                <el-radio label="1">单层</el-radio>
                                <el-radio label="2">一楼</el-radio>
                                <el-radio label="3">楼上</el-radio>
                                <el-radio label="4">一楼或楼上皆可</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11" class="input-col2">
                        <el-form-item label="租房用途：" prop="checkUseType">
                            <el-checkbox-group v-model="checkUseType">
                                <el-checkbox border size="small" v-for="useType in useTypeList" :label="useType" :key="useType">{{useType}}
                                </el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="input-col2">
                        <el-form-item label="客户要求：">
                            <el-checkbox border size="small" label="可环评" v-model="cusObj.hasEia"></el-checkbox>
                            <el-checkbox border size="small" label="有产证" v-model="cusObj.hasCertify"></el-checkbox>
                            <el-checkbox border size="small" label="有排污证" v-model="cusObj.hasSewageCertify"></el-checkbox>
                            <el-checkbox border size="small" label="有超市" v-model="cusObj.hasSupermarket"></el-checkbox>
                            <el-checkbox border size="small" label="有食堂" v-model="cusObj.hasCanteen"></el-checkbox>
                            <el-checkbox border size="small" label="有办公楼" v-model="cusObj.hasOfficeBuild"></el-checkbox>
                            <el-checkbox border size="small" label="有雨棚" v-model="cusObj.hasCanopy"></el-checkbox>
                            <el-checkbox border size="small" label="有卸货平台" v-model="cusObj.hasLoadDock"></el-checkbox>
                            <el-checkbox border size="small" label="有充电桩" v-model="cusObj.hasChargingPile"></el-checkbox>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">客户找房原因</span>
            </div>
            <div class="customer-info-div">
                <el-form-item>
                    <el-checkbox-group v-model="checkList" style="margin-left: -80px;">
                        <el-row :gutter="20">
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="首次找房"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="无法加装航车"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="消防等级不达标"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="生产转仓储"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="仓储转生产"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址行业限制"></el-checkbox>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址拆迁"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址无法办环评"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址不可注册"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址不可续租"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址电量不充足"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址无产证"></el-checkbox>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址交通不便"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="周边道路太拥挤"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址面积太大"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址价格上涨"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址合同到期"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="上下游客户转移"></el-checkbox>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="想换条件更好的"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="车辆进出不便"></el-checkbox>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <el-checkbox label="原址面积太小"></el-checkbox>
                            </el-col>
                        </el-row>
                    </el-checkbox-group>
                </el-form-item>
                <el-row :gutter="20" style="margin-top: 10px;margin-bottom: 15px;">
                    <el-col :xs="19" :sm="19" :md="19" :lg="19" :xl="19">
                        <el-form-item label="客户关心问题：" prop="moreDepict" label-width="135px">
                            <el-input type="textarea" v-model="cusObj.moreDepict"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>

            <div style="height: 50px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="customerEditSubmit('ruleFormRef')">提交
                </el-button>
                <el-button style="float: left;" @click="resetForm('ruleFormRef')">重置</el-button>
            </div>

            <div style="background-color: rgb(242,242,242);height: 50px;"></div>
        </el-form>


        <el-dialog style="line-height: 30px" title="新增渠道" :visible.sync="isShowInsertChannel">
            <el-form :model="channelForm" :rules="channelRules" ref="channelForm" label-width="100px" style="text-align: left;">
                <el-row :gutter="5">
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="渠道类型" prop="channelStatus">
                            <el-select v-model="channelForm.channelStatus" placeholder="请选择">
                                <el-option v-for="item in channelStatuses" :key="item.value" :label="item.label" :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="联系人姓名" prop="userName">
                            <el-input v-model="channelForm.userName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="性别" prop="sex">
                            <el-radio-group v-model="channelForm.sex">
                                <el-radio label="1">先生</el-radio>
                                <el-radio label="2">女士</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="联系人电话" prop="phone">
                            <el-input v-model="channelForm.phone" @blur="checkChannelPhone()"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="联系人职务" prop="post">
                            <el-input v-model="channelForm.post"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="单位名称" prop="companyCode">
                            <el-select v-model="channelForm.companyCode" filterable placeholder="请选择单位">
                                <el-option v-for="item in channelCpyList" :key="item.companyCode" :label="item.channelCpyName" :value="item.companyCode">
                                </el-option>
                            </el-select>
                            <el-button type="primary" @click="isShowInsertChannelCpy = true" style="margin-left: 20px;">新增单位</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer">
                <el-button type="primary" @click="submitForm('channelForm')">提交</el-button>
                <el-button @click="resetFormChannel('channelForm')">重置</el-button>
            </div>
        </el-dialog>


        <el-dialog style="text-align: center;line-height: 30px;" title="新增单位" :visible.sync="isShowInsertChannelCpy" append-to-body>

            <el-form :model="channelCpyForm" :rules="channelCpyRules" ref="channelCpyForm" label-width="100px">
                <el-form-item label="单位名称" prop="channelCpyName">
                    <el-input v-model="channelCpyForm.channelCpyName" @blur="checkChannelCpyName()"></el-input>
                </el-form-item>
                <el-form-item label="单位描述" prop="channelCpyRemark">
                    <el-input v-model="channelCpyForm.channelCpyRemark"></el-input>
                </el-form-item>
                <el-form-item label="单位地址" prop="channelCpyAddress">
                    <el-input v-model="channelCpyForm.channelCpyAddress"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer">
                <el-button type="primary" @click="submitChannelCpyForm('channelCpyForm')">提交</el-button>
                <el-button @click="resetFormCpy('channelCpyForm')">重置</el-button>
            </div>
        </el-dialog>
        <upload-attachment v-if="showAttachment" @backParam="backParamAttachment($event)" :paramAttachment="paramAttachment">

        </upload-attachment>
    </div>
</template>

<script>
import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
import ElRow from "element-ui/packages/row/src/row";
import ElFormItem from "../../../../node_modules/element-ui/packages/form/src/form-item";
import uploadAttachment from "./uploadAttachment.vue";

export default {
  components: {
    ElFormItem,
    ElRow,
    ElButton,
    uploadAttachment
  },
  data() {
    //校验座机
    let checkLandlinePhone = (rule, value, callback) => {
      setTimeout(() => {
        let pattern = this.$LANDLINE_PHONE;
        if (!pattern.test(value)) {
          callback(new Error("请输入正确的固定电话"));
        } else {
          callback();
        }
      }, 100);
    };
    //校验数字只能是正整数或小数
    let checkIntOrFloat = (rule, value, callback) => {
      setTimeout(() => {
        if (!/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(value)) {
          callback(new Error("只能输入正整数或小数"));
        } else {
          callback();
        }
      }, 100);
    };
    // 检查【渠道电话】、【客户电话】是否唯一
    let checkPhoneOnly = (rule, value, callback) => {
      setTimeout(() => {
        let pattern = this.$MOBILE_PATTERN;
        if (value == this.checkPhoneFlag) {
          callback(new Error("电话号话已存在"));
        } else if (!pattern.test(value)) {
          callback(new Error("请输入正确的电话号码"));
        } else {
          callback();
        }
      }, 100);
    };
    // 检查【渠道公司名称】是否存在
    let checkCpyName = (rule, value, callback) => {
      setTimeout(() => {
        if (value == this.channelCpyNameFlag) {
          callback(new Error("渠道单位名称已存在"));
        } else {
          callback();
        }
      }, 100);
    };

    return {
      showAttachment: false, //上传附件弹窗
      paramAttachment: {},

      floatDivWidth: "",
      parkList: [], // 所有园区
      useTypeList: ["生产", "仓储", "办公"],

      checkUseType: [], // 租房用途集合
      checkList: [], // 找房原因集合

      channelList: [], // 渠道联系人集合
      channelCpyNameList: [], // 渠道单位名称集合

      channelCpyList: [], // 渠道单位集合

      checkPhoneFlag: "", // 记录查询到的手机号
      channelCpyNameFlag: "", // 记录查询到的渠道单位名称

      isShowInsertChannel: false, // 控制【新增渠道】弹窗
      isShowInsertChannelCpy: false, // 控制【新增渠道单位】弹窗

      channelForm: {}, // 渠道表单
      channelCpyForm: {}, // 渠道单位表单

      cusChannel: {}, // 客户渠道
      pkObj: [], //绑定园区
      cusObj: {
        attachment: "", //附件

        cusCode: "", // 客户编号
        customerType: "1", // 客户类型
        companyName: "", // 公司名
        trade: "", // 行业分类
        companyProduct: "", // 公司产品

        mainContactName: "", // 主联系人姓名
        mainContactSex: "", // 主联系人性别
        mainContactPhone: "", // 主联系人电话
        landlinePhone: "", // 客户座机

        ditch: "", // 客户来源
        channelCode: "", // 渠道code

        pkCode: "", // 绑定园区
        pkName: "", //园区名
        mainArea: "", // 需求面积
        floorHeightAsk: "", // 层高要求
        electricAsk: "", // 电量要求
        entryTime: "", // 入驻时间
        floorAsk: "", // 需求楼层

        useType: "", // 租房用途

        hasEia: false, // 可环评
        hasCertify: false, // 有产证
        hasSewageCertify: false, // 有排污证
        hasSupermarket: false, // 有超市
        hasCanteen: false, // 有食堂
        hasOfficeBuild: false, // 有办公楼
        hasCanopy: false, // 有雨棚
        hasLoadDock: false, // 有卸货平台
        hasChargingPile: false, // 有充电桩
        reason: "", // 找房原因
        moreDepict: "", // 客户关心问题

        cusStatus: "1", // 客户状态，默认1为洽谈中
        cusStatusName: ""
      },
      // 渠道类型
      channelStatuses: [
        {
          value: "1",
          label: "个人中介"
        },
        {
          value: "2",
          label: "公司中介"
        },
        {
          value: "3",
          label: "管委会"
        },
        {
          value: "4",
          label: "房东"
        },
        {
          value: "5",
          label: "其他"
        }
      ],
      // 行业集合
      industryList: [
        {
          value: "1",
          label: "食品加工业"
        },
        {
          value: "2",
          label: "饮料制造业"
        },
        {
          value: "3",
          label: "酒加工"
        },
        {
          value: "4",
          label: "烟草制品业"
        },
        {
          value: "5",
          label: "纺织业"
        },
        {
          value: "6",
          label: "皮、绒制品业"
        },
        {
          value: "7",
          label: "木材加工"
        },
        {
          value: "8",
          label: "家具制造"
        },
        {
          value: "9",
          label: "纸质品业"
        },
        {
          value: "10",
          label: "印刷业"
        },
        {
          value: "11",
          label: "文教体育"
        },
        {
          value: "12",
          label: "用品制造"
        },
        {
          value: "13",
          label: "石油加工"
        },
        {
          value: "14",
          label: "化学燃料"
        },
        {
          value: "15",
          label: "医药制造"
        },
        {
          value: "16",
          label: "橡胶制品"
        },
        {
          value: "17",
          label: "塑料制品"
        },
        {
          value: "18",
          label: "金属制品"
        },
        {
          value: "19",
          label: "制造业"
        },
        {
          value: "20",
          label: "交通运输"
        },
        {
          value: "21",
          label: "电子设备"
        }
      ],
      rules: {
        trade: [{ required: true, message: "请选择行业", trigger: "change" }], //下拉框校验
        useType: [{ required: true, message: "请选择用途", trigger: "change" }], //下拉框校验
        pkCode: [{ required: true, message: "请选择园区", trigger: "change" }], //下拉框校验
        customerType: [
          { required: true, message: "请选择客户类型", trigger: "change" }
        ], //单选组校验
        companyName: [
          { required: true, message: "请输入公司名称", trigger: "blur" }
        ], //输入框校验
        companyProduct: [
          { required: true, message: "请输入公司产品", trigger: "blur" }
        ], //输入框校验
        mainContactName: [
          { required: true, message: "请输入联系人名字", trigger: "blur" }
        ],
        mainContactPhone: [
          { required: true, message: "请输入联系电话", trigger: "blur" },
          { validator: checkPhoneOnly }
        ],
        landlinePhone: [
          { required: true, message: "请输入固定电话", trigger: "blur" },
          { validator: checkLandlinePhone }
        ],
        ditch: [
          { required: true, message: "请选择客户来源", trigger: "change" }
        ],
        channelCode: [
          { required: true, message: "请选择渠道信息", trigger: "blur" }
        ],
        channelStatus: [
          { required: true, message: "请选择渠道类型", trigger: "change" }
        ],
        channelPhone: [
          { required: true, message: "请输入联系人电话", trigger: "blur" }
        ],
        channelSex: [
          { required: true, message: "请输入性别", trigger: "change" }
        ],
        channelPost: [
          { required: true, message: "请输入联系人职务", trigger: "blur" }
        ],
        channelCpyName: [
          { required: true, message: "请输入单位名称", trigger: "blur" }
        ],
        channelCpyRemark: [
          { required: true, message: "请输入单位描述", trigger: "blur" }
        ],
        channelCpyAddress: [
          { required: true, message: "请输入单位地址", trigger: "blur" }
        ],
        mainArea: [
          { required: true, message: "请输入需求面积", trigger: "blur" },
          { validator: checkIntOrFloat }
        ],
        floorHeightAsk: [
          { required: true, message: "请输入层高要求", trigger: "blur" },
          { validator: checkIntOrFloat }
        ],
        electricAsk: [
          { required: true, message: "请输入电量要求", trigger: "blur" },
          { validator: checkIntOrFloat }
        ],
        entryTime: [
          { required: true, message: "请选择入驻时间", trigger: "change" }
        ],
        floorAsk: [
          { required: true, message: "请选择需求楼层", trigger: "change" }
        ],
        checkList: [
          {
            type: "array",
            required: true,
            message: "请选择找房原因",
            trigger: "change"
          }
        ],
        moreDepict: [
          { required: true, message: "请输入客户关心问题", trigger: "blur" }
        ],
        mainContactSex: [
          { required: true, message: "请选择性别", trigger: "change" }
        ]
      },
      channelRules: {
        channelStatus: [
          { required: true, message: "请选择渠道类型", trigger: "change" }
        ],
        userName: [
          { required: true, message: "请输入联系人姓名", trigger: "blur" }
        ],
        sex: [{ required: true, message: "请选择性别", trigger: "change" }],
        phone: [
          { required: true, message: "请输入联系人电话", trigger: "blur" },
          { validator: checkPhoneOnly }
        ],
        post: [{ required: true, message: "请输入联系人职务", trigger: "blur" }]
      },
      channelCpyRules: {
        channelCpyName: [
          { required: true, message: "请输入单位名称", trigger: "blur" },
          { validator: checkCpyName }
        ],
        channelCpyRemark: [
          { required: true, message: "请输入单位描述", trigger: "blur" }
        ],
        channelCpyAddress: [
          { required: true, message: "请输入单位地址", trigger: "blur" }
        ]
      },
      pickerOptions1: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            }
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
      }
    };
  },
  props: ["paramsObj"], // 接收父组件数据
  created: function() {
    let vm = this;

    vm.cusObj.cusCode = this.paramsObj.cusCode;
    vm.cusObj.channelCode = this.paramsObj.channelCode;

    vm.selectCustomerDetail(vm.cusObj.cusCode); // 查询客户详情

    vm.getParkList(); // 查询园区
    vm.initChannelList(); // 初始化渠道下拉选，联系人集合

    // 根据channelCode查询当前客户渠道
    if (vm.cusObj.channelCode) {
      vm.selectChannelByCode(vm.cusObj.channelCode);
    }
  },
  methods: {
    // 公司类型为个人时，联系人名字为公司名
    setCompanyName() {
      if (this.cusObj.customerType == 2) {
        this.cusObj.companyName = this.cusObj.mainContactName;
      }
    },
    // 查询客户详情
    selectCustomerDetail(cusCode) {
      let vm = this;

      let params = {
        entity: {
          cusCode: cusCode
        }
      };

      let options = {
        method: "POST",
        data: params,
        url: "customer/details"
      };

      this.$ajax(options)
        .then(function(response) {
          vm.cusObj = {};
          vm.cusObj = response.data.result;

          vm.checkList = vm.cusObj.reason.split(","); // 找房原因，split，字符串拆分为数组
          vm.checkUseType = vm.cusObj.useType.split(","); // 拆分租房用途
        })
        .catch(function(error) {
          vm.$message.error("页面：获取客户详情失败");
        });
    },
    // 查询所有园区，绑定园区下拉选
    getParkList() {
      let vm = this;

      let options = {
        method: "POST",
        data: {},
        url: "park/list"
      };
      this.$ajax(options)
        .then(function(response) {
          let pkObj = [];
          let result = response.data.result;
          for (let s = 0; s < result.length; s++) {
            let code = result[s].pkCode;
            let name = result[s].pkName;
            pkObj[code] = name;
          }
          vm.pkObj = pkObj;
          vm.parkList = response.data.result;
        })
        .catch(function(error) {
          vm.$message.error("页面:下拉框，绑定园区，获取数据失败！");
        });
    },

    nameByCode(pkCode) {
      this.cusObj.pkName = this.pkObj[pkCode];
    },
    // 校验客户电话是否唯一
    checkCusPhone() {
      let vm = this;

      let param = {
        entity: {
          mainContactPhone: vm.cusObj.mainContactPhone
        }
      };

      let options = {
        method: "POST",
        data: param,
        url: "customer/checkCusPhone"
      };
      this.$ajax(options)
        .then(function(response) {
          if (!response.data.isException) {
            if (response.data.result) {
              if (response.data.result.cusCode != vm.cusObj.cusCode) {
                vm.checkPhoneFlag = response.data.result.mainContactPhone;
              }
            }
          }
        })
        .catch(function(error) {
          vm.$alert("页面：客户电话查询失败", "系统异常", {
            confirmButtonText: "确定",
            callback: action => {}
          });
        });
    },

    // 重置渠道
    resetFormChannel(formName) {
      this.$refs[formName].resetFields();
    },
    // 重置渠道单位
    resetFormCpy(formName) {
      this.$refs[formName].resetFields();
    },
    // 客户修改提交
    customerEditSubmit(ruleFormVal) {
      let vm = this;

      if (vm.checkUseType.length > 0) {
        for (let i = 0; i < vm.checkUseType.length; i++) {
          if (i == 0) {
            vm.cusObj.useType = vm.checkUseType[i];
          } else {
            vm.cusObj.useType = vm.cusObj.useType + "," + vm.checkUseType[i];
          }
        }
      } else {
        vm.$message({
          showClose: true,
          message: "请选择房屋用途",
          type: "error"
        });
        return;
      }

      let flag = false;
      // 表单验证
      this.$refs[ruleFormVal].validate(valid => {
        if (valid) {
          flag = true;
          vm.basicEdit();
        } else {
          return false;
        }
      });
    },
    basicEdit() {
      let vm = this;

      let obj = {};
      obj = vm.cusObj;

      // 将找房原因数组checkList转成逗号字符串reson字段
      let temp = "起始值";
      for (let i = 0; i < vm.checkList.length; i++) {
        if (i == 0) {
          temp = vm.checkList[i];
        } else {
          temp = temp + "," + vm.checkList[i];
        }
      }

      obj.reason = temp;
      let sendObj = {
        entity: obj
      };

      let options = {
        method: "POST",
        data: sendObj,
        url: "customer/update"
      };
      this.$ajax(options)
        .then(function(response) {
          if (response) {
            vm.$message({
              message: "保存数据成功!",
              type: "success"
            });

            // 关闭当前修改页签
            let dataDelObj = {
              flag: "del",
              menuName: "客户修改",
              item: "customerEdit"
            };
            vm.$emit("backParam", dataDelObj);

            //新增完跳转到详情页
            let dataObj = {
              menuName: "客户详情",
              item: "customerDetail",
              params: {
                cusCode: vm.cusObj.cusCode
              }
            };
            vm.$emit("backParam", dataObj);
          }
        })
        .catch(function(error) {
          vm.$message.error("页面：客户修改失败！");
        });
    },

    /*--------------------------------- 新增渠道、新增渠道单位 ---------------------------------*/
    /**
     * 1、下拉选：通过channelCode查询渠道表信息，下拉选值改变，获得对应联系人的信息
     * 2、created 初始方法，加载默认展示渠道表人员信息
     */
    selectChannelByCode(channelCode) {
      let vm = this;

      vm.cusObj.channelCode = null;
      vm.cusObj.channelPhone = null;
      vm.cusObj.channelUserName = null;
      vm.cusObj.channelSex = null;
      vm.cusObj.channelPost = null;

      let obj = {
        entity: { channelCode: channelCode }
      };

      let options = {
        method: "POST",
        data: obj,
        url: "customer/channel/details"
      };
      this.$ajax(options)
        .then(function(response) {
          if (!response.data.isException) {
            vm.cusObj.channelCode = response.data.result.channelCode;

            vm.cusChannel.channelCode = response.data.result.channelCode;
            vm.cusChannel.channelPhone = response.data.result.phone;
            vm.cusChannel.channelUserName = response.data.result.userName;
            vm.cusChannel.channelSex = response.data.result.sex;
            vm.cusChannel.channelPost = response.data.result.post;
          }
        })
        .catch(function(error) {
          vm.$alert("页面：新增客户查询渠道信息失败", "系统异常", {
            confirmButtonText: "确定",
            callback: action => {}
          });
        });
    },

    /*--------------------------------- 新增渠道、新增渠道单位 ---------------------------------*/
    // 初始化渠道下拉选
    initChannelList() {
      let vm = this;

      let options = {
        method: "POST",
        data: {},
        url: "customer/channel/list"
      };
      this.$ajax(options)
        .then(function(response) {
          if (response) {
            let channels = response.data.result;
            let channelPhoneList = [];

            for (let i = 0; i < channels.length; i++) {
              let code = channels[i].channelCode;
              let userName = channels[i].userName;
              let phone = channels[i].phone;

              channelPhoneList.push({
                code: code,
                name: userName,
                phone: phone
              });
            }
            vm.channelList = channelPhoneList;
          }
        })
        .catch(function(error) {
          vm.$alert("页面：新增客户查询渠道信息集合失败", "系统异常", {
            confirmButtonText: "确定",
            callback: action => {}
          });
        });
    },

    // 查询【渠道单位】列表
    addChannelGetCpyList() {
      let vm = this;

      vm.isShowInsertChannel = true;

      let options = {
        method: "POST",
        data: {},
        url: "customer/channel/cpy/list"
      };
      this.$ajax(options)
        .then(function(response) {
          if (response) {
            vm.channelCpyList = response.data.result;
          }
        })
        .catch(function(error) {
          vm.$alert("页面：新增客户查询渠道单位信息集合失败", "系统异常", {
            confirmButtonText: "确定",
            callback: action => {}
          });
        });
    },

    // 【新增渠道】提交表单
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let vm = this;

          let channelForm = vm.channelForm;

          let obj = {
            entity: vm.channelForm
          };

          let options = {
            method: "POST",
            data: obj,
            url: "customer/channel/create"
          };

          this.$ajax(options)
            .then(function(response) {
              if (response) {
                vm.isShowInsertChannel = false; // 关闭弹窗

                vm.cusObj.channelCode = response.data.result;
                vm.cusObj.channelPhone = channelForm.phone;
                vm.cusObj.channelSex = channelForm.sex;
                vm.cusObj.channelPost = channelForm.post;

                vm.initChannelList(); // 刷新渠道
                vm.selectChannelByCode(response.data.result);
                vm.resetFormChannel(formName);
              }
            })
            .catch(function(error) {
              vm.$alert("页面：新增渠道失败", "系统异常", {
                confirmButtonText: "确定",
                callback: action => {}
              });
            });
        }
      });
    },

    // 【新增渠道单位】提交表单
    submitChannelCpyForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let vm = this;

          let channel = vm.channelCpyForm;
          let obj = {
            entity: channel
          };
          let options = {
            method: "POST",
            data: obj,
            url: "customer/channel/cpy/create"
          };
          this.$ajax(options)
            .then(function(response) {
              if (response) {
                vm.isShowInsertChannelCpy = false; // 关闭弹窗
                vm.channelForm.companyCode = response.data.result;

                vm.addChannelGetCpyList(); // 刷新渠道单位
                vm.resetFormCpy(formName);
              }
            })
            .catch(function(error) {
              vm.$alert("页面：新增渠道单位失败", "系统异常", {
                confirmButtonText: "确定",
                callback: action => {}
              });
            });
        }
      });
    },

    // 校验渠道手机号是否唯一
    checkChannelPhone() {
      let vm = this;

      if (vm.channelForm.phone) {
        let param = {
          entity: {
            phone: vm.channelForm.phone
          }
        };

        let options = {
          method: "POST",
          data: param,
          url: "customer/channel/getList"
        };
        this.$ajax(options)
          .then(function(response) {
            if (!response.data.isException) {
              let channelList = response.data.result;
              if (channelList.length > 0) {
                vm.checkPhoneFlag = channelList[0].phone;
              }
            }
          })
          .catch(function(error) {
            vm.$alert("页面：渠道信息手机号已存在", "系统异常", {
              confirmButtonText: "确定",
              callback: action => {}
            });
          });
      }
    },

    // 校验渠道单位名称是否唯一
    checkChannelCpyName() {
      let vm = this;

      let param = {
        entity: {
          channelCpyName: vm.channelCpyForm.channelCpyName
        }
      };

      let options = {
        method: "POST",
        data: param,
        url: "customer/channel/cpy/getList"
      };

      this.$ajax(options)
        .then(function(response) {
          if (!response.data.isException) {
            let channelCpyList = response.data.result;
            if (channelCpyList.length > 0) {
              vm.channelCpyNameFlag = channelCpyList[0].channelCpyName;
            }
          }
        })
        .catch(function(error) {
          vm.$alert("页面：渠道单位信息名称已存在", "系统异常", {
            confirmButtonText: "确定",
            callback: action => {}
          });
        });
    },

    //上传附件
    upLoadAttachment() {
      let vm = this;
      vm.showAttachment = true;
      let fileList = [];
      if (vm.cusObj.attachment) {
        fileList = vm.cusObj.attachment.split("，");
      }
      vm.paramAttachment.fileList = fileList;
      vm.paramAttachment.showAttachment = vm.showAttachment;
    },

    //接收上传附件返回
    backParamAttachment(val) {
      let vm = this;
      vm.showAttachment = val.showAttachment;
      let fileList = [];
      fileList = val.fileList;
      if (fileList.length > 0) {
        vm.cusObj.attachment = fileList.join("，");
      } else {
        vm.cusObj.attachment = "";
      }
    }
  }
};
</script>


<style scoped>
.root {
  min-width: 1000px;
  min-height: 900px;
  background-color: rgb(242, 242, 242);
}

.root_style {
  height: 88vh;
  overflow-y: auto;
  overflow-x: hidden;
}

/*导航栏 div*/
.nevigate-div {
  margin-top: -50px;

  position: fixed;
  z-index: 200;
}

/*保存按钮*/
.save-button {
  float: left;
  margin-left: 20px;
}

/*--------------------------------------------------------------------*/

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
  color: #409eff;
  margin-left: 20px;
  line-height: 50px;
}

/*--------------------------------------------------------------------*/

/*基本信息 div*/
.customer-info-div {
  min-width: 1000px;
  /*height: 120px;*/
  background-color: white;
  text-align: left;
}

/*输入框 第一行*/
.input-col1 {
  margin-top: 10px;
  height: 55px;
  /*border: solid 1px;*/
}

/*输入框 第二行*/
.input-col2 {
  margin-top: 5px;
  height: 60px;
  /*border: solid 1px;*/
}

.upLoad_protocol {
  color: #ffc125;
  font-size: 14px;
  float: left;
  margin: 9px 0 0 20px;
  cursor: pointer;
}

.upLoad_protocol:hover {
  color: blue;
}
</style>