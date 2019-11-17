<template>
    <div id="app">
        <router-view></router-view>
        <!--<button>这里是app.vue 主根页</button>-->
    </div>
</template>

<script>
    export default {
        name: "app",
        components: {},
        created() {}
    };
</script>

<style>
    body {
        margin: 0;
        padding: 0;
        border: 0;
    }

    h1,h2,h3,h4,h5,h6 {
        margin: 0;
    }

    /*园区详情中：鼠标悬浮上显示合同信息弹窗的全局样式*/
    .el-popper[x-placement^="bottom"] {
        margin-top: 4px;
    }
    /* .search_box {
        width: 100%;
        height: 50px;
        line-height: 50px;
        background-color: #fff;
        display: flex;
        align-items: center;
    } */

    /* 改变input样式
    .el-input {
        height: 28px;
    }

    .el-input__inner {
        height: 28px!important;
    }

    .el-input--small .el-input__icon {
        line-height: 28px;
    } 
    */

    /* .el-button--small, .el-button--small.is-round {
        padding: 9px 15px;
    } */

    /* .el-button--small, .el-button--small.is-round {
        padding: 7px 15px;
    } */

    .el-tabs__nav-scroll {
        background-color: white;
    }

    .el-table th,
    td {
        text-align: center;
    }

    p {
        margin: 0;
        padding: 0;
    }

    .clearfix:after {
        content: "";
        height: 0;
        line-height: 0;
        clear: both;
        overflow: hidden;
        display: block;
        visibility: hidden;
    }

    /* tabs页面默认高度 */
    .el-tab-pane {
        height: inherit !important;
    }

    /* 添加看板 */
    .add-data-box[data-v-30e13d82] {
        margin-top: 0 !important;
        margin-left: 22px !important;
    }

    /* 侧边栏隐藏前全局el-main组件样式 */
    #globalMain {
        margin-left: 225px;
        transition: all 0.3s;
        padding: 10px;
        padding-bottom: 0;
    }

    /* 侧边栏隐藏后全局el-main组件样式 */
    #globalMain.active {
        margin-left: 54px;
    }

    .el-table--enable-row-transition .el-table__body td {
        text-align: center;
    }

    /* 最大容器 */
    .rootcss {
        background-color: rgb(242, 242, 242);
        width: auto;
        height: 100vh;
    }

    .el-menu-item,
    .el-submenu__title {
        height: 40px;
        line-height: 40px;
    }

    .el-submenu .el-menu-item {
        height: 40px;
        line-height: 40px;
    }

    /* ---优化了全局的el-tab-png的样式 ---*/
    .root /deep/ .el-tabs__active-bar {
        margin-left: 20px;
    }

    .root /deep/ .el-tabs__nav {
        padding-left: 20px;
    }



    /* ---优化了全局的el-tab-png的样式 ---*/

    img {
        width: 100%;
    }
</style>