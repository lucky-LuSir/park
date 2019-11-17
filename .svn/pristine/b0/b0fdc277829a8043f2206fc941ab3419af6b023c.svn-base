<template>
    <div class="root">
        <el-dialog
                class="dialog"
                :visible.sync="showDetail"
                width="70%"
                :close-on-click-modal="false"
                :before-close="closeShow"
                center>
            <el-form
                    :model="inspectInfo"
                    ref="inspectInfo"
                    label-width="100px"
                    label-suffix=":">

                <el-card class="task_select">
                    <div slot="header" style="text-align: center">
                        <span style="font-size: 25px">评分详情</span>
                    </div>
                    <el-row>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label="园区" prop="pkCode">
                                {{inspectInfo.pkName}}
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label="责任人" prop="beInspectedEmpCode">
                                {{inspectInfo.beInspectedEmpName}}
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label="检查人" prop="inspectEmpCode">
                                {{inspectInfo.inspectEmpName}}
                            </el-form-item>
                        </el-col>

                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="8">
                            <el-form-item label="总分" >
                                {{ inspectInfo.totalGrade}}
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="8">
                            <el-form-item label="得分">
                                <span style="color: #df5000;font-size: 20px">{{ inspectInfo.totalScore}}</span>
                            </el-form-item>
                        </el-col>

                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label="检查日期" prop="inspectTime">
                                {{ inspectInfo.inspectTime| timeDate}}
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <div class="fill_in">
                        <div v-for="(item,index) in scoreTree" :key="item.objRlCode" >
                            <el-collapse v-model="openNode">
                                <el-collapse-item :name="item.objRlCode" :disabled="openDisable">
                                    <template slot="title">
                                        <span style="font-size: 16px">{{item.objRlName}}&nbsp;&nbsp;</span>
                                        <el-button type="primary" size="small" @click="watchAttachment(item.fileCode)">
                                            查看图片
                                            <i class="el-icon-upload el-icon--right"></i></el-button>
                                    </template>
                                    <el-row class="fill_row" v-if="item.children" v-for="(child,cindex) in item.children" :key="cindex">
                                        <el-col :xs="3" :sm="3" :md="3" :lg="6" :xl="4">
                                            <el-popover
                                                    class="childDescribe"
                                                    placement="top-start"
                                                    width="200"
                                                    trigger="hover"
                                                    :content="child.describe">
                                                <span slot="reference">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{child.objRlName}}({{child.grade}}分)</span>
                                            </el-popover>
                                        </el-col>
                                        <el-col :xs="12" :sm="12" :md="12" :lg="6" :xl="6">
                                            <!--<el-form-item :label="child.objRlName" :title="child.describe" prop="score">
                                                <el-rate
                                                        style="margin-top: 10px"
                                                        :max="child.grade"
                                                        v-model="child.score"
                                                        disabled
                                                        show-score
                                                        text-color="#ff9900"
                                                        score-template="{value}分">
                                                </el-rate>
                                            </el-form-item>-->
                                            <el-form-item>
                                                得分：<span style="color: #fe980f">{{child.score}}</span>&nbsp;分
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="10" :sm="10" :md="10" :lg="12" :xl="9">
                                            <el-form-item label="备注">
                                                {{child.remark}}
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-collapse-item>
                            </el-collapse>
                        </div>
                    </div>
                    <div style="float: right;margin-bottom: 15px;margin-top: 15px">
                        <el-button  style="margin-left: 20px"  type="primary" size="small" @click="closeShow">确定</el-button>
                    </div>
                </el-card>
            </el-form>
        </el-dialog>
        <!--查看附件-->
        <el-dialog
                append-to-body
                :before-close="closeShowAttachment"
                title="查看图片"
                :visible.sync="showAttachment"
                width="52%"
                :close-on-click-modal="false">
            <div class="block_box_img_special">
                <div v-if="fileList.length != 0" class="video_div">
                    <div v-for="(img,index) in fileList" :key="index">
                        <el-image  class="video" :src=" parkBaseUrl + 'file/read/' + img "></el-image >
                    </div>
                </div>
                <div v-else style="text-align: center">
                    暂无图片
                </div>
                <div class="clear"></div>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        components:{

        },
        props:["paramsDetail"],
        created: function(){
            let vm = this;
            if(vm.paramsDetail){
                vm.showDetail = vm.paramsDetail.showDetail;
                vm.inspectCode = vm.paramsDetail.inspectCode;
            }

            this.getInspectInfo();
        },
        data(){
            return {
                openNode:[],//默认展开的折叠面板
                showAttachment: false,
                showDetail:false,
                inspectCode:'',
                inspectInfo: {},
                scoreTree:[],
                openDisable: false,
                fileList:[],
            }
        },

        methods:{
            //获取详情
            getInspectInfo(){
                let vm = this;
                let params = {
                    entity:{
                        inspectCode: vm.inspectCode
                    }
                }
                let options = {
                    method: "POST",
                    data: params,
                    url: "inspectInfo/infoWithScoreTree"
                }
                this.$ajax(
                    options
                ).then(response => {
                    if(response){
                        vm.inspectInfo = response.data.result;
                        if(vm.inspectInfo.scoreEntityTree.children){
                            vm.scoreTree = vm.inspectInfo.scoreEntityTree.children;
                            for(let i = 0; i < vm.scoreTree.length; i++){
                                vm.openNode.push(vm.scoreTree[i].objRlCode);
                            }
                        }

                    }

                })
            },

            //关闭弹窗回调
            closeShow(){
                let vm = this;
                vm.showCreate = false;
                let data = {
                    showCreate :vm.showCreate,
                };
                vm.$emit('backParam',data);
            },

            //查看图片
            watchAttachment(fileCode){
                let vm = this;
                vm.openDisable = true;
                vm.showAttachment = true;

                if(fileCode){
                    vm.fileList = fileCode.split("，");
                }else{
                    vm.fileList = [];
                }

            },

            closeShowAttachment(){
                this.showAttachment = false;
                this.openDisable = false;
            },

            closeShow(){
                let vm = this;
                vm.showDetail = false;
                let data = {
                    showDetail :vm.showDetail,
                };
                vm.$emit('backParam',data);
            }

        }
    }
</script>
<style scoped>
    .task_select{
        width: 100%;
    }
    .fill_in{
        max-height: 350px;
        overflow:auto;
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

    .fill_row {
    display: flex;
    align-items: center;
    }
   .el-form-item {
        margin-bottom: 0px;
    }
    .dialog .el-dialog__header{
        padding: 0;
    }
    .root /deep/ .el-dialog__header {
        padding: 0;
    }
    .root /deep/ .el-dialog__headerbtn {
        top: 10px;
        right: 5px;
    }
    .childDescribe:hover{
        cursor: pointer;
    }
</style>