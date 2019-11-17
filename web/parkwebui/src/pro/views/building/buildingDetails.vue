<template>
    <div class="has-position_root" ref="myRoot">
        <div class="has-position_header">
            <el-button v-if="showAddBuildBtn" v-show="!isCreate" type="primary" size="small" icon="el-icon-plus" class="edit-create-button" @click="createBuilding()">
                新增
            </el-button>
            <el-button v-if="showEditBuildBtn" v-show="isCreate" type="primary" size="small" icon="el-icon-edit-outline" class="edit-create-button" @click="editBuilding()">
                修改
            </el-button>
            <div class="building-list-button" v-for="(item, index) in buildingList" :key="item.bdCode">
                <el-button type="primary" size="small" @click="changeBuilding(item.bdCode, index+1)" v-if="item.bdCode && item.thisBuilding == buildingObj.thisBuilding">{{ item.bdName }}栋
                </el-button>

                <el-button type="primary" plain size="small" @click="changeBuilding(item.bdCode, index+1)" v-if="item.bdCode && item.thisBuilding != buildingObj.thisBuilding">{{ item.bdName }}栋
                </el-button>

                <el-button type="info" size="small" @click="changeBuilding(item.bdCode,index+1)" v-if="!item.bdCode" plain>无数据
                </el-button>
            </div>
        </div>

        <el-form class="has-position_main" :model="buildingObj">

            <div class="common-title-div">
                <span class="common-title-font">基本信息</span>
            </div>
            <div class="building-info-root-div">
                <el-row :gutter="10">
                    <el-col :span="24">
                        <div class="grid-content ">
                            <el-form-item label="园区：">
                                <span>{{ buildingObj.pkName }}，{{ buildingObj.bdName }}栋</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="6">
                        <div class="grid-content ">
                            <el-form-item label="计租面积：">
                                <span>{{ buildingObj.investArea }}㎡</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :span="6">
                        <div class="grid-content ">
                            <el-form-item label="总层数：">
                                <span>{{ buildingObj.floorsTotal }}层</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :span="6">
                        <div class="grid-content ">
                            <el-form-item label="建筑结构：">
                                <span>{{buildingObj.buildingStructureName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :span="6">
                        <div class="grid-content ">
                            <el-form-item label="消防等级：">
                                <span>{{buildingObj.firefightingLevelName}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div class="grid-content ">
                            <el-form-item label="货梯数量：">
                                <!--用数据库电梯数量字段-->
                                <span>{{buildingObj.elevatorQuantity}}部</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :span="6">
                        <div class="grid-content ">
                            <el-form-item label="货梯承重：">
                                <span>{{buildingObj.freightElevatorWeight}}吨</span>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :span="6">
                        <div class="grid-content ">
                            <el-form-item label="货梯开门：">
                                <!--<span>{{buildingObj.openDoor == 1 ? "单向" : "双向"}}</span>-->
                                <span>{{buildingObj.openDoor}}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">其他描述</span>
            </div>
            <div class="building-info-root-div">
                <el-row :span="24">
                    <el-col>
                        <div class="grid-content ">
                            <el-form-item label="剩余计租面积：">
                                <span>{{buildingObj.rentableArea}}㎡</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>


            <div class="floor-root-div">
                <div class="floor-info-div" v-for="(item, i) in floorList" :class="{floorStyle: (i + 1) % 3 != 0}" :key="item.flCode">
                    <div class="floor-number-div" style="line-height: 50px;text-align: left">
                        <span class="floor-number">{{ i + 1 }}层</span>
                    </div>

                    <el-popover placement="top" width="500" trigger="hover">
                        <p style="text-align: center; font-size: 18px; font-weight: bold; margin-top: -5px;">{{item.thisFloor}}&nbsp;层入驻企业</p>
                        <el-table :data="item.floorContractHouseEntityList" height="220px" @row-dblclick="dblclickContractDetail">
                            <el-table-column type="index" width="50" label="序号"></el-table-column>
                            <el-table-column property="ctName" label="合同编号"></el-table-column>
                            <el-table-column property="companyName" label="公司名"></el-table-column>
                            <el-table-column property="relateName" width="70" label="客户名"></el-table-column>
                            <el-table-column property="ctInvestArea" width="80" label="出租面积"></el-table-column>
                            <el-table-column min-width="20px" fixed="right" align="center" label="操作" width="50">
                                <template slot-scope="scope">
                                    <el-button @click="dblclickContractDetail(scope.row)" size="mini" type="text">
                                        详情
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>


                        <div class="floor-data" v-show="item.flCode" @click="floorDetails(item.flCode,item.thisFloor)" slot="reference">
                            <ul class="floor-data-ul">
                                <li style="width: 28%;margin-left: 10px;text-align: left;" :title="item.investArea">总面积: {{item.investArea}}㎡</li>
                                <li style="width: 10px;background-color: #D9E5E4;"></li>
                                <li style="width: 28%;color: #9c9c9c;text-align: left;" :title="item.rentableArea">&nbsp;可出租: {{item.rentableArea}}㎡
                                </li>
                                <li style="width: 10px;background-color: #178679;"></li>
                                <li style="width: 28%;color: #178679;text-align: left;" :title="item.rentedArea">
                                    &nbsp;已出租: {{item.rentedArea}}㎡
                                </li>
                            </ul>

                            <div class="floor-data-stat" @click="floorDetails(item.flCode,item.thisFloor)">
                                <div :id="'FL' + item.flCode" v-bind:style="{width: echartsDivWidth + 'px'}" style="height: 35px"></div>
                            </div>
                        </div>
                    </el-popover>
                    <div class="floor-info-div3" v-if="!item.flCode">
                        <el-button v-show="showAddFloorBtn" @click="createFloor(item.thisFloor)" style="float: left;margin-top: 4%;margin-left: 35%;" type="primary">添加本层信息
                        </el-button>
                    </div>
                </div>
                <div style="clear:both"></div>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">影像资料</span>
            </div>

            <div class="building-img-root-div" style="border-bottom: 1px solid rgb(242, 242, 242);">
                <div class="building-img-title-div">
                    <span style="line-height: 130px;">视频</span>
                </div>

                <div class="building-picture-root-div">
                    <div class="building-picture-div" v-if="videoList" v-for="(fileCode,index) in videoList" :key="index">
                        <video width="100%" height="100%" controls autobuffer>
                            <source :src=" parkBaseUrl + 'file/read/' + fileCode " />
                        </video>
                    </div>
                </div>

                <div style="clear:both"></div>
            </div>

            <div class="building-img-root-div">
                <div class="building-img-title-div">
                    <span style="line-height: 130px;">图片</span>
                </div>

                <div class="building-picture-root-div">
                    <div class="building-picture-div" v-if="imgList" v-for="(fileCode,index) in imgList" :key="index">
                        <img style="width: 100%;height: 100%;" :src=" parkBaseUrl + 'file/read/' + fileCode ">
                    </div>
                </div>

                <div style="clear:both"></div>
            </div>

            <!--末尾处-->
            <div style="height: 30px;margin-top: 20px;padding-bottom: 60px;"></div>
        </el-form>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        components: {
            ElCol,
            ElRow
        },
        props: ['paramsObj'], // 接收父组件数据
        created: function() {
            let vm = this;

            // 菜单和按钮权限控制
            vm.showAddBuildBtn = vm.$flagMenuStore.judgeMenu("addBuildBtn");
            vm.showEditBuildBtn = vm.$flagMenuStore.judgeMenu("editBuildBtn");
            vm.showAddFloorBtn = vm.$flagMenuStore.judgeMenu("addFloorBtn");

            if (vm.paramsObj) {
                vm.buildingObj.pkCode = vm.paramsObj.pkCode;
                vm.buildingObj.pkName = vm.paramsObj.pkName;
                vm.buildingObj.bdCode = vm.paramsObj.bdCode;
                vm.buildingObj.thisBuilding = vm.paramsObj.thisBuilding;

                vm.selectAllBuilding(vm.buildingObj.pkCode); // 查询园区所有楼栋

                // 查询楼栋详情
                let param = {
                    entity: vm.buildingObj,
                    bdCode: vm.buildingObj.bdCode
                };
                vm.buildingDetails(vm, param);
                vm.selectCtHouse(vm.buildingObj.bdCode); // 查询合同房源
            }
            vm.isResize = false;
        },
        data() {
            return {
                showAddBuildBtn: false,
                showEditBuildBtn: false,
                showAddFloorBtn: false,

                ctHouseList: [], // 合同房源集合

                buildingObj: {
                    pkCode: '', // 园区编号
                    pkName: '', // 园区名称
                    bdCode: '', // 建筑编号
                    bdName: '', // 建筑名称

                    investArea: '', // 计租面积
                    floorsTotal: '', // 总楼层数
                    buildingStructureName: '', // 建筑结构
                    firefightingLevelName: '', // 消防等级
                    elevatorQuantity: '', // 货梯数量
                    freightElevatorWeight: '', // 货梯承重
                    openDoor: '', // 货梯开门
                    rentableArea: '', // 剩余计租面积
                    thisBuilding: '', // 当前楼栋编号
                    buildingVideo: '', // 楼栋视频
                    outPicture: '', // 楼栋视频

                    buildingStatusName: '', // 状态
                    elevatorSpecification: '', // 电梯规格
                    isDeed: '', // 是否有产证
                    hasRegister: '', // 是否可注册
                    hasEia: '', // 是否可环评
                    isFreightElevator: '', // 有无货梯
                    hasPollutionTreatment: '', // 有无排污证
                },
                floorList: [{
                    flCode: '', // 层编号
                    thisFloor: '', // 所在层
                    investArea: '', // 计租面积
                    rentableArea: '', // 可租用面积
                    rentedArea: '', // 已租用面积
                    floorsTotal: '' // 总楼层数
                }, ],
                buildingList: [],
                videoList: [],
                imgList: [],
                isCreate: true,

                isResize: false, //是否自适应再次加载echarts柱状图
                echartsDivWidth: '', //echarts柱状图宽度
            };
        },
        updated() {
            let vm = this;

            let floorList = vm.floorList;

            /**
             * 计租面积（含公摊）：investArea
             * 可租面积：rentableArea
             */
            let investArea = 0,
                rentableArea = 0,
                flCode = "";

            if (floorList) {
                for (let i = 0; i < floorList.length; i++) {
                    investArea = floorList[i].investArea;
                    rentableArea = floorList[i].rentableArea;
                    flCode = floorList[i].flCode;
                    vm.showChart(flCode, investArea, rentableArea);
                }
            }
        },
        methods: {
            // 查询所有楼栋
            selectAllBuilding(pkCode) {
                let vm = this;

                let params = {
                    entity: {
                        pkCode: pkCode
                    }
                };
                let options = {
                    method: 'POST',
                    data: params,
                    url: 'building/queryByPkCode'
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.buildingList = response.data.result;
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询楼栋详情失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // 楼栋详情
            buildingDetails(vm, param) {
                let options = {
                    method: "POST",
                    data: param,
                    url: "contract/buildingDetail"
                };

                this.$ajax(
                    options
                ).then(function(response) {
                    if (!response.data.isException) {
                        vm.buildingObj = response.data.result;
                        vm.floorList = response.data.result.floorList;

                        // 赋值给videoList
                        if (vm.buildingObj.buildingVideo) {
                            vm.videoList = vm.buildingObj.buildingVideo.split(',');
                        } else {
                            vm.videoList = [];
                        }

                        // 赋值给imgList
                        if (vm.buildingObj.outPicture) {
                            vm.imgList = vm.buildingObj.outPicture.split(',');
                        } else {
                            vm.imgList = [];
                        }

                    } else {
                        vm.$message({
                            showClose: true,
                            message: '该楼栋暂无数据',
                            type: 'warning'
                        })
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询楼栋详情失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // 切换楼栋按钮
            changeBuilding(bdCode, index) {
                this.buildingObj.bdCode = bdCode;
                this.buildingObj.thisBuilding = index;
                let vm = this;
                let param = {};
                param.entity = {
                    pkCode: this.buildingObj.pkCode,
                    bdCode: bdCode,
                    thisBuilding: index
                };
                param.bdCode = bdCode;

                if (bdCode) {
                    vm.isCreate = true;
                    vm.buildingDetails(vm, param);
                } else {
                    vm.isCreate = false;
                    vm.$message({
                        showClose: true,
                        message: '该楼栋暂无数据，请点击左侧新增按钮！',
                        type: 'warning'
                    });
                    vm.buildingObj.investArea = '';
                    vm.buildingObj.floorsTotal = '';
                    vm.buildingObj.buildingStructureName = '';
                    vm.buildingObj.firefightingLevelName = '';
                    vm.buildingObj.elevatorQuantity = '';
                    vm.buildingObj.freightElevatorWeight = '';
                    vm.buildingObj.rentableArea = '';
                    vm.buildingObj.buildingVideo = '';
                    vm.buildingObj.outPicture = '';
                    vm.floorList = [];
                }
            },

            // 楼层详情
            floorDetails(flCode, thisFloor) {
                let data = {
                    menuName: '楼层详情',
                    item: 'floorDetails',
                    params: {
                        pkCode: this.buildingObj.pkCode,
                        pkName: this.buildingObj.pkName,
                        bdCode: this.buildingObj.bdCode,
                        bdName: this.buildingObj.bdName,
                        flCode: flCode,
                        thisFloor: thisFloor
                    }
                };
                this.$emit('backParam', data);
            },

            // 编辑楼栋
            editBuilding() {
                let data = {
                    menuName: '楼栋修改',
                    item: 'buildingEdit',
                    params: {
                        paramsObj: this.buildingObj
                    }
                };
                this.$emit('backParam', data);
            },

            // 新增楼栋
            createBuilding() {
                let data = {
                    menuName: '新增楼栋',
                    item: 'buildingCreate',
                    params: {
                        pkCode: this.buildingObj.pkCode,
                        pkName: this.buildingObj.pkName,
                        thisBuilding: this.buildingObj.thisBuilding
                    }
                };
                this.$emit('backParam', data);
            },

            // 新增楼层
            createFloor(thisFloor) {
                let data = {
                    menuName: '新增楼层',
                    item: 'floorCreate',
                    params: {
                        pkCode: this.buildingObj.pkCode,
                        pkName: this.buildingObj.pkName,
                        bdCode: this.buildingObj.bdCode,
                        bdName: this.buildingObj.bdName,
                        thisFloor: thisFloor
                    }
                };
                this.$emit('backParam', data);
            },

            //设置容器高宽
            resizeWorldMapContainer(echartsWarp) {
                this.echartsDivWidth = (window.innerWidth - 400) * 0.3;
                echartsWarp.style.width = this.echartsDivWidth + 'px';
            },

            // 查询合同房源
            selectCtHouse(bdCode) {
                let params = {
                    entity: {
                        bdCode: bdCode
                    }
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: 'ctHouse/list'
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (!response.data.isException) {
                        this.ctHouseList = response.data.result;

                        //                        this.floorList();

                        //                        for (let i = 0; i < this.floorList.length; i++) {
                        //                            for (let j = 0; j < this.ctHouseList.length; j++) {
                        //                                if (this.floorList[i].flCode == this.ctHouseList[j].flCode) {
                        //                                    this.floorList[j] = {
                        //                                        ctInvestArea: this.ctHouseList.investArea,
                        //                                    };
                        //                                    continue;
                        //                                }
                        //                            }
                        //                        }
                    }
                }).catch(error => {
                    this.$alert('页面：合同房源查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                })
            },

            // 柱状图
            showChart(flCode, investArea, rentableArea) {

                let vm = this;

                /**
                 * 总面积：investArea
                 * 可租用面积：rentableArea
                 */
                let num1 = rentableArea / investArea * 100;
                let numberData = num1.toFixed(2); // 可出租百分比
                let num2 = 100 - numberData; // 已出租百分比

                let echartsWarp = document.getElementById('FL' + flCode);
                if (echartsWarp) {
                    /**
                     * eCharts 组件 id 需唯一，当前多标签页样式，所有组件共存，
                     * 造成园区详情的 eCharts id 与楼栋重复，故拼接 'FL' 字符，
                     * 避免 id 重复。——2018/12/21
                     */

                    vm.resizeWorldMapContainer(echartsWarp);
                    let myChart = vm.$echarts.init(echartsWarp);

                    let option = {
                        grid: {
                            x: 10,
                            y: 20,
                            x2: 10,
                            y2: 20,
                            borderWidth: 1
                        },
                        tooltip: {
                            trigger: 'item',
                        },
                        color: ['#D9E5E4', '#178679'],
                        xAxis: [{
                            type: 'value',
                            show: false
                        }],
                        yAxis: [{
                            type: 'category',
                            show: false,
                            data: ['占比']
                        }],
                        series: [{
                                name: '可出租',
                                type: 'bar',
                                stack: '占比',
                                barWidth: 35,

                                data: [{
                                    value: '',
                                    label: {
                                        normal: {
                                            color: '#333333',
                                            show: true,
                                            formatter: [
                                                '{c}' + '%'
                                            ].join('\n')
                                        }
                                    }
                                }]
                            },
                            {
                                name: '已出租',
                                type: 'bar',
                                stack: '占比',
                                barWidth: 35,

                                data: [{
                                    value: '',
                                    label: {
                                        normal: {
                                            color: '#333333',
                                            show: true,
                                            formatter: [
                                                '{c}' + '%'
                                            ].join('\n')
                                        }
                                    }
                                }]
                            }
                        ]
                    };

                    if (numberData == 100) {
                        option.series[0].data[0].value = numberData;
                        option.series[1].data[0].value = '';
                    } else if (numberData == 0) {
                        option.series[0].data[0].value = '';
                        option.series[1].data[0].value = num2.toFixed(2);
                    } else {
                        option.series[0].data[0].value = numberData;
                        option.series[1].data[0].value = num2.toFixed(2);
                    }
                    myChart.setOption(option);

                    if (vm.isResize) {
                        myChart.resize();
                    }
                    //根据窗口的大小变动图表 --- 重点
                    window.onresize = function() {
                        vm.resizeWorldMapContainer(echartsWarp);
                        myChart.resize();
                        vm.isResize = true;
                    };
                }
            },
            //合同详情
            dblclickContractDetail(row, column) {
                let data = {
                    flag: "add",
                    menuName: "合同详情",
                    item: "contractDetails",
                    params: {
                        ctCode: row.ctCode
                    }
                };
                this.$emit("backParam", data);

            },
        }
    }
</script>

<style scoped>
    /*******************************************************/

    /*楼栋按钮*/
    .building-list-button {
        float: left;
        margin-left: 20px;
    }

    /*修改或新增按钮*/
    .edit-create-button {
        float: left;
        margin-left: 20px;
    }

    /*******************************************************/
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
        font-size: 17px;
        color: #409EFF;
        margin-left: 20px;
    }

    /*******************************************************/
    /*其他描述  根div*/
    .building-info-root-div {
        background-color: white;
        text-align: left;
    }

    .grid-content {
        height: 30px;
        line-height: 30px;
        margin-left: 20px;
    }

    /*******************************************************/
    /*楼层  根div*/
    .floor-root-div {
        max-width: 1644px;
    }

    .floor-info-div {
        background-color: white;
        width: 32%;
        height: 135px;
        float: left;
        margin: 10px 0px 0px 0px;
    }

    /*第几层  div*/
    .floor-number-div {
        background-color: white;
        height: 50px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*第几层  文字*/
    .floor-number {
        margin-left: 20px;
        font-size: 17px;
        color: #409EFF;
    }

    .floor-data {
        height: 30px;
        line-height: 30px;
    }

    /*楼层面积信息*/
    .floor-data-ul {
        /*float: left;*/
        list-style: none;
        height: 10px;
        line-height: 10px;
        font-size: 13px;
        width: 90%;
        /*border: solid 1px;*/
    }

    /*总面积,可出租,已出租*/
    .floor-data-ul li {
        margin-top: 5px;
        float: left;
        list-style: none;
        height: 12px;
        line-height: 12px;
        font-size: 10px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    /*统计图形*/
    .floor-data-stat {
        height: 50px;
        width: 100%;
        margin-top: 10px;

    }

    /*楼层循环 加宽度*/
    .floorStyle {
        margin-right: 2%;
    }

    .floor-info-div3 {
        height: 80px;
        background-color: white;
        cursor: pointer;
    }

    /*******************************************************/
    /*影像资料  根Div*/
    .building-img-root-div {
        max-width: 1644px;
        background-color: white;
    }

    /*视频和图片回显 文字div*/
    .building-img-title-div {
        width: 70px;
        color: #409EFF;
        margin-left: 15px;
        float: left;
        height: 150px;
        margin-top: 10px;
    }

    /*视频和图片回显 div*/
    .building-picture-div {
        height: 150px;
        width: 15%;
        float: left;
        margin-left: 20px;
        margin-top: 10px;
        padding-bottom: 10px;
    }

    /*图片和视频循环 根div*/
    .building-picture-root-div {
        width: 93%;
        height: 100%;
        margin-left: 90px;
    }
</style>