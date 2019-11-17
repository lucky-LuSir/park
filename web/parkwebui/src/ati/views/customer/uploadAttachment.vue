<template>
    <div class="root" ref="myRoot">
        <!--上传附件-->
        <el-dialog title="上传附件（图片）"
                   :visible.sync="showAttachment"
                   width="52%"
                   :close-on-click-modal="false"
                   :beforeClose="cancelUpLoad"
                   append-to-body
        >
            <div class="block_box_img_special">
                <div class="video_div">
                    <div v-for="(img,index) in fileList">
                        <img class="video" :src=" parkBaseUrl + 'file/read/' + img ">
                        <i class="el-icon-remove-outline" style="float: left;margin-top: 13px;"
                           @click="deleteFile(index)"></i>
                    </div>
                    <div class="video_content">
                        <file @selectFile="selectFile"></file>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
            <span slot="footer" class="dialog-footer">
                        <el-button @click='cancelUpLoad'>取 消</el-button>
                        <el-button type="primary" @click="saveFile">确 定</el-button>
                     </span>
        </el-dialog>
    </div>
</template>
<script>
    import file from "../../../common/components/file.vue";

    export default{
        props:['paramAttachment'],
        components:{
            file
        },
        created(){
            let vm = this;
            vm.showAttachment = vm.paramAttachment.showAttachment;
            if(vm.paramAttachment.fileList){
                for(let i = 0; i < vm.paramAttachment.fileList.length; i++){
                    vm.fileList.push(vm.paramAttachment.fileList[i]);
                    vm.cacheFileList.push(vm.paramAttachment.fileList[i]);
                }
            }
            if(vm.paramAttachment.objRlCode){
                vm.objRlCode = vm.paramAttachment.objRlCode;
            }
            vm.index = vm.paramAttachment.index;

        },
        data(){
            return{
                objRlCode:'',
                index:'',
                showAttachment:false,
                fileList:[],
                cacheFileList:[],
            }
        },
        methods:{
            //添加图片
            selectFile(urls){
                if (urls) {
                    for (let i = 0; i < urls.length; i++) {
                        this.fileList.push(urls[i].fileCode);
                    }
                }

            },
            // 删除文件
            deleteFile(index){
                this.$confirm('是否移除该图片？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.fileList.splice(index, 1);

                    this.$message({
                        type: 'success',
                        message: '移除成功！'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消移除'
                    });
                });
            },

            //关闭弹窗时清空fileList
            cancelUpLoad(){
                let vm = this;
                vm.showAttachment = false;
                let data = {
                    showAttachment: vm.showAttachment,
                    fileList: vm.cacheFileList,
                    objRlCode: vm.objRlCode,
                    index: vm.index,
                }
                vm.$emit('backParam',data);
                vm.fileList = [];
                vm.cacheFileList = [];
            },

            //保存附件
            saveFile(){
                let vm = this;
                vm.showAttachment = false;
                let data = {
                    showAttachment: vm.showAttachment,
                    fileList: vm.fileList,
                    objRlCode: vm.objRlCode,
                    index: vm.index,
                }
                vm.$emit('backParam',data);
                vm.fileList = [];
                vm.cacheFileList = [];
            },
        },
    }
</script>
<style scoped>
    .root {
        min-width: 1000px;
        min-height: 900px;
        background-color: rgb(242, 242, 242);
    }
    /*图片信息特殊样式*/
    .block_box_img_special {
        min-height: 40px;
        background-color: white;
        padding-bottom: 30px;
        margin-bottom: 30px;
        margin-top: -29px;
    }

    /*图片列内容*/
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
        clear: both
    }
</style>