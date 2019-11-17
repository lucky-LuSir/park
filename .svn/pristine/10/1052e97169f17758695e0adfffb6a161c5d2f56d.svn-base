<template>
    <div class="has-position_root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <div class="has-position_header">
            <div class="titles">
                <span class="navigation_title">部门管理</span>
            </div>
        </div>

        <el-container class="root_container">
            <!--子菜单-->
            <el-aside width="201px" class="aside">
                <el-tree
                        :data="deptTree"
                        style="line-height: 50px;"
                        node-key="id"
                        @node-click="asideClick"
                        :default-expanded-keys="[1]"
                        ref="tree"
                        :expand-on-click-node="false"
                        highlight-current
                        :props="defaultProps">
                </el-tree>
            </el-aside>

            <!--主要内容-->
            <el-main>
                <!--部门信息-->
                <div class="message_div" ref="deptInfo">
                    <el-row>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <span class="message_span">部门名称：</span>
                            <span class="dept_people_p_content">{{dept.deptName}}</span>
                        </el-col>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <span class="message_span">联系电话：</span>
                            <span class="dept_people_p_content">{{dept.deptPhone}}</span>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <span class="message_span">部门领导：</span>
                            <span class="dept_people_p_content">{{dept.deptHeadName}}</span>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <span class="message_span">领导人电话：</span>
                            <span class="dept_people_p_content">{{dept.deptHeadPhone}}</span>
                        </el-col>
                    </el-row>
                    <el-row :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <span class="message_span">通讯地址：</span>
                            <span class="dept_people_p_content">{{dept.provinceName + dept.cityName + dept.regionName + dept.streetName}}</span>
                            <span class="dept_people_p_content" v-if="dept.address != ''">{{dept.address}}</span>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                            <span class="message_span">部门介绍：</span>
                            <span class="dept_people_p_content">{{dept.deptSynopsis}}</span>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24"
                                style="margin-left: 20px;text-align: left;line-height: 45px;" clearable>
                            <!--<div style="margin-left: 20px;text-align: left;line-height: 45px;">-->
                            <el-switch
                                    v-model="form.hasUseQuery"
                                    inactive-text="是否启用查询"
                                    @change="changeUseQuery">
                            </el-switch>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                            <div v-if="dept.parentDeptCode != null">
                                <el-button class="message_button"
                                           size="medium"
                                           type="primary"
                                           @click="addDept(dept.parentDeptName,dept.parentDeptCode,dept.deptType);dialogFormVisible = true"
                                           v-if="showAddEquativeDeptBtn">新增同级部门
                                </el-button>
                            </div>

                            <el-button v-show="showAddSubDeptBtn"
                                       class="message_button"
                                       size="medium"
                                       @click="addDept(dept.deptName,dept.deptCode,(parseInt(dept.deptType)+1));dialogFormVisible2 = true"
                                       type="primary">新增下级部门
                            </el-button>

                            <el-button v-show="showEditDeptBtn"
                                       class="message_button"
                                       size="medium"
                                       @click="updateDept(dept.deptCode);dialogFormVisible1 = true" type="primary">
                                修改部门信息
                            </el-button>
                        </el-col>
                    </el-row>
                </div>

                <!-- 部门成员 -->
                <div class="dept_people_title_height">
                    <span class="dept_people_title">部门成员</span>
                </div>
                <div v-if="!empList" class="dept_people_null">
                    暂无部门人员信息
                </div>
                <div class="dept_people" v-for="emp in empList">
                    <div class="dept_people_picture" v-if="emp.empImg">
                        <img class="dept_people_img" :src=" parkBaseUrl + 'file/read/' + emp.empImg ">
                    </div>
                    <div class="dept_people_picture" v-if="! emp.empImg">
                        <img class="dept_people_img" src="./img/TIM20180716114924.jpg">
                    </div>
                    <div class="dept_people_content_div">
                        <el-row>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <p class="dept_people_name">{{emp.empName}}</p>
                            </el-col>
                            <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                                <el-button type="text" class="dept_people_a" @click="empDetails(emp.empCode)">查看详情
                                </el-button>
                            </el-col>
                        </el-row>
                    </div>

                    <div class="dept_people_content_div">
                        <el-row>
                            <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                                <span class="dept_people_p_title">部门名称：</span>
                                <span class="dept_people_p_content">{{emp.deptName}}</span>
                            </el-col>
                            <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                                <span class="dept_people_p_title">岗位职称：</span>
                                <span class="dept_people_p_content">{{emp.postName}}</span>
                            </el-col>
                        </el-row>
                    </div>
                    <div class="dept_people_content_div">
                        <el-row>
                            <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                                <span class="dept_people_p_title">联系方式：</span>
                                <span class="dept_people_p_content">{{emp.empPhone}}</span>
                            </el-col>
                            <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                                <span class="dept_people_p_title">负责区域：</span>
                                <span class="dept_people_p_content">{{emp.manageArea}}</span>
                            </el-col>
                        </el-row>
                    </div>
                </div>

                <div style="height: 50px;width: 10px;"></div>

                <div class="page-data-div" v-if="empList != null">
                    <el-pagination style="padding-top: 10px;"
                                   @size-change="handleSizeChange"
                                   @current-change="handleCurrentChange"
                                   :current-page="currentPage"
                                   :page-sizes="[5, 10, 20, 30]"
                                   :page-size="pageSize"
                                   layout="total, sizes, prev, pager, next, jumper"
                                   :total="totalCount">
                    </el-pagination>
                </div>
            </el-main>
        </el-container>

        <!-- 新增同级部门 -->
        <el-dialog class="dialog" title="新增同级部门" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="form" :model="form" label-width="100px">
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="上级部门名称">
                            <el-input v-model="form.parentDeptName"
                                      readonly="readonly"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="部门名称" prop="deptName">
                            <el-input v-model="form.deptName" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="联系电话">
                            <el-input v-model="form.deptPhone" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="部门领导">
                            <el-select v-model="form.deptHeadCode" @change="changeEmp"
                                       filterable placeholder="请选择">
                                <el-option v-for="item in empAllList"
                                           :key="item.empCode"
                                           :label="item.empName"
                                           :value="item.empCode">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="领导联系电话">
                            <el-input v-model="form.deptHeadPhone"
                                      readonly="readonly"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="部门介绍">
                            <el-input v-model="form.deptSynopsis" type="textarea"
                                      clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所在省">
                            <el-select id="provinceName" v-model="form.provinceCode"
                                       @visible-change="handNeedProvince" @change="getCitieList"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itProvinceArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所在市">
                            <el-select id="cityName" v-model="form.cityCode"
                                       @focus="getCitieList(form.provinceCode)"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itCityArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所在区">
                            <el-select id="regionName" v-model="form.regionCode"
                                       @focus="getAreaList(form.cityCode)" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itAreaArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所在街道">
                            <el-select id="streetName" v-model="form.streetCode"
                                       @focus="getStreetList(form.regionCode)"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itStreetArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                        <el-form-item label="详细地址" prop="address">
                            <el-input v-model="form.address" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="deptSubmit('form',1)">提 交</el-button>
            </div>
        </el-dialog>

        <!-- 新增下级部门 -->
        <el-dialog class="dialog" title="新增下级部门" :visible.sync="dialogFormVisible2">
            <el-form :rules="rules" ref="form" :model="form" label-width="100px"
                     style="margin-top: -40px">
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="上级部门名称">
                            <el-input v-model="form.parentDeptName" readonly="readonly"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="部门名称" prop="deptName">
                            <el-input v-model="form.deptName" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="联系电话">
                            <el-input v-model="form.deptPhone" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="部门领导">
                            <el-select v-model="form.deptHeadCode" @change="changeEmp" filterable
                                       placeholder="请选择" style="width: 100%;">
                                <el-option v-for="item in empAllList"
                                           :key="item.empCode"
                                           :label="item.empName"
                                           :value="item.empCode">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="领导联系电话">
                            <el-input v-model="form.deptHeadPhone" readonly="readonly"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="部门介绍">
                            <el-input v-model="form.deptSynopsis" type="textarea"
                                      clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="所在省">
                            <el-select id="provinceName" v-model="form.provinceCode"
                                       @visible-change="handNeedProvince" @change="getCitieList"
                                       placeholder="请选择" style="width: 100%;">
                                <el-option
                                        v-for="(item, indexkey) in itProvinceArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name"
                                >
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="所在市">
                            <el-select id="cityName" v-model="form.cityCode"
                                       @focus="getCitieList(form.provinceCode)" placeholder="请选择"
                                       style="width: 100%;">
                                <el-option
                                        v-for="(item, indexkey) in itCityArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="所在区">
                            <el-select id="regionName" v-model="form.regionCode"
                                       @focus="getAreaList(form.cityCode)" placeholder="请选择"
                                       style="width: 100%;">
                                <el-option
                                        v-for="(item, indexkey) in itAreaArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="所在街道">
                            <el-select id="streetName" v-model="form.streetCode"
                                       @focus="getStreetList(form.regionCode)" placeholder="请选择"
                                       style="width: 100%;">
                                <el-option
                                        v-for="(item, indexkey) in itStreetArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="详细地址" prop="address">
                            <el-input v-model="form.address" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer" style="margin-top: -40px;">
                <el-button @click="dialogFormVisible2 = false">取 消</el-button>
                <el-button type="primary" @click="deptSubmit('form',2)">提 交</el-button>
            </div>
        </el-dialog>

        <!-- 修改部门信息 -->
        <el-dialog class="dialog" title="修改部门信息" :visible.sync="dialogFormVisible1">
            <el-form :rules="rules" ref="form" :model="form" label-width="100px">
                <el-row style="margin-top: -40px">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="上级部门名称">
                            <el-input v-model="form.parentDeptName" readonly="readonly"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="部门名称" prop="deptName">
                            <el-input v-model="form.deptName" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="联系电话">
                            <el-input v-model="form.deptPhone" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="部门领导">
                            <el-select id="deptHeadCode" v-model="form.deptHeadCode"
                                       @change="changeEmp" filterable placeholder="请选择"
                                       style="width: 100%;">
                                <el-option v-for="item in empAllList"
                                           :key="item.empCode"
                                           :label="item.empName"
                                           :value="item.empCode">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="领导联系电话">
                            <el-input v-model="form.deptHeadPhone" readonly="readonly"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="部门介绍">
                            <el-input v-model="form.deptSynopsis" type="textarea"
                                      clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="所在省">
                            <el-select id="provinceName" v-model="form.provinceCode"
                                       @visible-change="handNeedProvince" @change="getCitieList"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itProvinceArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="所在市">
                            <el-select id="cityName" @change="changeEmp" v-model="form.cityCode"
                                       @focus="getCitieList(form.provinceCode)" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itCityArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="所在区">
                            <el-select id="regionName" @change="changeEmp" v-model="form.regionCode"
                                       @focus="getAreaList(form.cityCode)" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itAreaArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="所在街道">
                            <el-select id="streetName" @change="changeEmp" v-model="form.streetCode"
                                       @focus="getStreetList(form.regionCode)" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itStreetArr"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="详细地址" prop="address">
                            <el-input v-model="form.address" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer" style="margin-top: -40px;">
                <el-button @click="dialogFormVisible1 = false">取 消</el-button>
                <el-button type="primary" @click="updateSubmit('form',dept.id)">提 交</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import file from '../../../common/components/file';
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        components: {
            ElCol,
            ElRow
        },
        data() {
            //校验电话号码
            var checkNumber = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (!pattern.test(value)) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {
                defaultProps: [{
                    children: 'children',
                    label: 'menuName',
                    value: ''
                }],

                showAddSubDeptBtn: false,
                showEditDeptBtn: false,
                showAddEquativeDeptBtn: false,// 新增同级部门

                pageSize: 5,//每页条数
                totalCount: 10,//总条数
                currentPage: 1,//当前页数

                dialogFormVisible: false,
                dialogFormVisible1: false,
                dialogFormVisible2: false,

                form: {
                    deptName: "",
                    parentDeptCode: "",
                    parentDeptName: "",
                    deptHeadCode: "",
                    deptHeadName: "",
                    deptHeadPhone: "",
                    deptPhone: "",
                    deptSynopsis: "",

                    provinceCode: "",
                    cityCode: "",
                    regionCode: "",
                    streetCode: "",
                    provinceName: "",
                    cityName: "",
                    regionName: "",
                    streetName: "",
                    address: "",

                    delivery: false,
                    type: [],
                    resource: '',
                    desc: '',
                    hasUseQuery: '',
                },

                deptList: {
                    deptObj: {}
                },

                dept: {},
                empList: [],
                empAllList: [],

                itProvinceArr: [],
                itCityArr: [],
                itAreaArr: [],
                itStreetArr: [],

                deptTree: [],

                rules: {
                    deptName: [
                        {required: true, message: '部门名称输入不能为空', trigger: 'blur'},
                    ],
                    deptPhone: [
                        {required: true, message: '部门电话输入不能为空', trigger: 'blur'},
                        {validator: checkNumber}
                    ],
                },
            }
        },
        created: function () {
            this.initCreated();
        },
        methods: {
            // 初始化方法
            initCreated() {
                var vm = this;

                // 菜单和按钮权限控制
                vm.showAddSubDeptBtn = vm.$flagMenuStore.judgeMenu("addSubDeptBtn");
                vm.showEditDeptBtn = vm.$flagMenuStore.judgeMenu("editDeptBtn");
                vm.showAddEquativeDeptBtn = vm.$flagMenuStore.judgeMenu("addEquativeDeptBtn");

                var param = {};
                param.entity = {};
                param.pageSize = vm.pageSize;
                param.start = 0;

                var option = {
                    method: 'POST',
                    data: param,
                    url: 'dept/queryDeptList'
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if (response) {
                        // 未添加部门，给出提示并打开添加部门弹窗。
                        let deptListResult = response.data.result.deptList;
                        if (deptListResult == null || deptListResult.length == 0) {
                            vm.$alert('未添加部门，请先添加部门信息！', '温馨提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    vm.dialogFormVisible2 = true;
                                }
                            });
                            return;
                        }

                        vm.deptTree = response.data.result.deptTree;
                        vm.deptList = response.data.result.deptList;
                        vm.empAllList = response.data.result.empAllList;
                        vm.empList = response.data.result.empList.data;
                        vm.totalCount = response.data.result.empList.totalCount;

                        for (var i = 0; i < vm.deptList.length; i++) {
                            if (vm.deptList[i].parentDeptCode == null) {
                                vm.dept = vm.deptList[i];
                            }

                            //显示初始化部门对应人员
                            if (vm.deptList[i].deptType == '1') {
                                var deptCode = vm.deptList[i].deptCode;

                                vm.getEmpByDept(deptCode);
                                vm.checkHasQuery(deptCode);
                            }
                        }

                        if (!vm.dept.provinceName) {
                            vm.dept.provinceName = '';
                            vm.dept.cityName = '';
                            vm.dept.regionName = '';
                            vm.dept.streetName = '';
                        }
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面：查询部门信息失败！',
                        type: 'error'
                    });
                });
            },

            getEmpByDept(aVal){
                var vm = {};
                vm = this;
                var pageData = {};
                pageData.pageSize = vm.pageSize;
                pageData.start = 0;
                pageData.entity = {};
                pageData.entity.deptCode = aVal;
                var options = {
                    method: "POST",
                    data: pageData,
                    url: "dept/selectEmpByDeptCode"
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.empList = response.data.result.data;
                        vm.totalCount = response.data.result.totalCount;
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:人员列表加载失败!',
                        type: 'error'
                    })
                })
            },

            // 子菜单打开事件
            handleOpen(key, keyPath) {

            },

            // 子菜单关闭事件
            handleClose(key, keyPath) {

            },

            // 分页每页条数改变事件
            handleSizeChange(val) {
                this.pageSize = val;
                this.currentPage = 1;
                this.handleCurrentChange(1);
            },

            // 分页当前页数据加载
            handleCurrentChange(val) {
                var vm = {};
                vm = this;
                if (vm.dept.parentDeptCode == 0) {
                    var pageData = {};
                    pageData.pageSize = vm.pageSize;
                    pageData.start = (val - 1) * (pageData.pageSize);
                    pageData.entity = {};
                    var options = {
                        method: "POST",
                        data: pageData,
                        url: "dept/queryDeptList"
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        if (!response.data.isException) {
                            vm.empList = response.data.result.empList.data;
                        }
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:人员列表加载失败!',
                            type: 'error'
                        })
                    })
                } else {
                    var pageData = {};
                    pageData.pageSize = vm.pageSize;
                    pageData.start = (val - 1) * (pageData.pageSize);
                    pageData.entity = {};
                    pageData.entity.deptCode = vm.dept.deptCode;
                    var options = {
                        method: "POST",
                        data: pageData,
                        url: "dept/selectEmpByDeptCode"
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        if (!response.data.isException) {
                            vm.empList = response.data.result.data;
                        }
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:人员列表加载失败!',
                            type: 'error'
                        })
                    })
                }
            },

            // 加载街道信息
            getStreetList(aVal){
                if (aVal) {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itStreetArr = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询街道信息失败!',
                            type: 'error'
                        });
                    });
                }
            },

            // 加载区域信息
            getAreaList(bVal) {
                if (bVal) {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = bVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itAreaArr = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询区域信息失败!',
                            type: 'error'
                        });
                    });
                }
            },

            // 加载城市信息
            getCitieList(aVal) {
                if (aVal) {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itCityArr = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询城市信息失败!',
                            type: 'error'
                        });
                    });
                }

            },

            // 加载城市信息
            handNeedProvince(val){
                var vm = this;
                var obj = {};
                obj.parentCode = 0;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.itProvinceArr = response.data.result;
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:查询省份信息失败!',
                        type: 'error'
                    });
                });
            },

            // 子菜单选项点击事件
            asideClick(val){
                var vm = {};
                vm = this;

                let deptCode = val.value;
                vm.checkHasQuery(deptCode);

//                if (jiebang == 1) {
//                    //嵌套事件冒泡解绑
//                    event.stopPropagation();
//                }

                for (var i = 0; i < vm.deptList.length; i++) {
                    if (vm.deptList[i].deptCode == deptCode) {
                        vm.dept = vm.deptList[i];
                        var param = {};
                        param.entity = {};
                        param.entity.deptCode = deptCode;
                        param.pageSize = vm.pageSize;
                        param.start = 0;
                        var option = {
                            method: 'POST',
                            data: param,
                            url: 'dept/selectEmpByDeptCode'
                        };
                        this.$ajax(
                            option
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.empList = response.data.result.data;
                                vm.totalCount = response.data.result.totalCount;
                            }
                            for (var i = 0; i < vm.deptList.length; i++) {
                                if (vm.deptList[i].deptCode == deptCode) {
                                    vm.dept = vm.deptList[i];
                                }
                            }
                            if (!vm.dept.provinceName) {
                                vm.dept.provinceName = '';
                            }
                            if (!vm.dept.cityName) {
                                vm.dept.cityName = '';
                            }
                            if (!vm.dept.regionName) {
                                vm.dept.regionName = '';
                            }
                            if (!vm.dept.streetName) {
                                vm.dept.streetName = '';
                            }
                        }).catch(function (error) {
                            vm.$message({
                                showClose: true,
                                message: '页面:查询区域信息失败!',
                                type: 'error'
                            });
                        });

                    }
                }
            },

            // 添加部门按钮数据绑定
            addDept(val, vall, valll, vallll){
                this.form = {};
                this.form.parentDeptName = val;
                this.form.parentDeptCode = vall;
                if (!valll) {
                    this.form.deptType = 1;
                } else {
                    this.form.deptType = valll;
                }

                this.form.deptCode = vallll;
            },

            // 修改部门加载数据
            updateDept(deptCode){
//                this.form = {};
                this.form.parentDeptName = this.dept.parentDeptName;
                this.form.deptName = this.dept.deptName;
                this.form.deptPhone = this.dept.deptPhone;
                this.form.deptHeadPhone = this.dept.deptHeadPhone;
                this.form.deptSynopsis = this.dept.deptSynopsis;
                this.form.address = this.dept.address;
                this.form.provinceCode = this.dept.provinceCode;
                this.form.cityCode = this.dept.cityCode;
                this.form.regionCode = this.dept.regionCode;
                this.form.streetCode = this.dept.streetCode;

                this.form.deptHeadCode = this.dept.deptHeadCode;


                //初始化省市区街道
                this.handNeedProvince();

                var testObj_A = {};
                testObj_A.areaCode = this.dept.cityCode;
                testObj_A.name = this.dept.cityName;
                var arrList = [];
                arrList.push(testObj_A)
                this.itCityArr = arrList;

                var testObj_A = {};
                testObj_A.areaCode = this.dept.regionCode;
                testObj_A.name = this.dept.regionName;
                var arrList = [];
                arrList.push(testObj_A)
                this.itAreaArr = arrList;

                testObj_A = {};
                testObj_A.areaCode = this.dept.streetCode;
                testObj_A.name = this.dept.streetName;
                var arrList = [];
                arrList.push(testObj_A)
                this.itStreetArr = arrList;
            },

            // 部门领导下拉框改变事件
            changeEmp(val){
                var testList = [];
                for (var i = 0; i < this.empAllList.length; i++) {
                    if (this.empAllList[i].empCode == val) {
                        this.form.deptHeadCode = this.empAllList[i].empCode;
                        this.form.deptHeadName = this.empAllList[i].empName;
                        this.form.deptHeadPhone = this.empAllList[i].empPhone;
                    }
                    testList.push(this.empAllList[i]);
                }
                this.empAllList = testList;

            },

            // 新增部门提交事件
            deptSubmit(subForm, flag){
                var vm = {};
                vm = this;
                var obj = {};
                obj.entity = vm.form;

                var provinceName = window.document.getElementById("provinceName");
                var cityName = window.document.getElementById("cityName");
                var regionName = window.document.getElementById("regionName");
                var streetName = window.document.getElementById("streetName");

                this.$refs[subForm].validate((valid) => {
                    obj.entity.provinceName = provinceName.value;
                    obj.entity.cityName = cityName.value;
                    obj.entity.regionName = regionName.value;
                    obj.entity.streetName = streetName.value;

                    if (valid) {
                        var options = {
                            method: 'POST',
                            data: obj,
                            url: "dept/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (response) {
                                vm.$message({
                                    showClose: true,
                                    message: '新增部门信息成功',
                                    type: 'success',
                                });
                                if (flag == 1) {
                                    vm.dialogFormVisible = false;
                                } else if (flag == 2) {
                                    vm.dialogFormVisible2 = false;
                                }

                                vm.initCreated();

                            }
                        }).catch(function (error) {
                            vm.$message({
                                showClose: true,
                                message: '页面:新增部门信息失败!',
                                type: 'error'
                            });
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning',
                        });
                    }
                });
            },

            // 修改部门提交事件
            updateSubmit(subForm, id){
                var vm = {};
                vm = this;
                var obj = {};
                obj.entity = vm.form;
                obj.entity.id = id;

                var provinceName = window.document.getElementById("provinceName");
                var cityName = window.document.getElementById("cityName");
                var regionName = window.document.getElementById("regionName");
                var streetName = window.document.getElementById("streetName");

                this.$refs[subForm].validate((valid) => {
                    obj.entity.provinceName = provinceName.value;
                    obj.entity.cityName = cityName.value;
                    obj.entity.regionName = regionName.value;
                    obj.entity.streetName = streetName.value;

                    obj.entity.deptCode = vm.dept.deptCode;

                    if (valid) {
                        var options = {
                            method: 'POST',
                            data: obj,
                            url: "dept/update",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (response) {
                                vm.$message({
                                    showClose: true,
                                    message: '修改部门信息成功',
                                    type: 'success',
                                });
                                vm.dialogFormVisible1 = false;
                                //   vm.initCreated();
                                vm.initDept(vm.dept.deptCode);
                            }
                        }).catch(function (error) {
                            vm.$message({
                                showClose: true,
                                message: '页面:修改部门信息失败!',
                                type: 'error'
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

            //查看详情
            empDetails(empCode){
                let data = {
                    menuName: "人员详情",
                    item: "empDetails",
                    params: {
                        empCode: empCode
                    }
                };
                this.$emit("backParam", data);
            },

            //检查该部门是否可检索
            checkHasQuery(deptCode){
                let deptJson = window.localStorage.getItem("_usedQueryDept");
                let deptList = JSON.parse(deptJson);
                for (let i = 0; i < deptList.length; i++) {
                    if (deptList[i].deptCode == deptCode) {
                        this.form.hasUseQuery = true;
                        return;
                    }
                }
                this.form.hasUseQuery = false;
            },

            //改变该部门的可检索状态
            changeUseQuery(val){
                let vm = this;
                let param = {
                    entity: {
                        hasUseQuery: val,
                        deptCode: vm.dept.deptCode,
                        deptName: vm.dept.deptName,
                        id: vm.dept.id,
                        cityCode: vm.dept.cityCode
                    }
                };

                let options = {
                    method: "post",
                    data: param,
                    url: "dept/update"
                };

                this.$ajax(
                    options
                ).then(function (res) {

                    if (val) {
                        vm.$message({
                            message: '开通此部门检索状态',
                            type: 'success'
                        });
                    } else {
                        vm.$message("关闭此部门检索状态");
                    }
                    vm.$usedQueryDept.usedQueryDept();
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '修改失败,请联系管理人员!',
                        type: 'error'
                    });
                });
            },

            // 更新某部门页面
            initDept(deptCode){
                let vm = this;

                let param = {
                    entity: {
                        deptCode: deptCode
                    }
                };

                let options = {
                    method: 'POST',
                    data: param,
                    url: 'dept/findByCode'
                };

                this.$ajax(
                    options
                ).then(res => {
                    for (let i = 0; i < vm.deptList.length; i++) {
                        if (vm.deptList[i].deptCode == deptCode) {
                            vm.deptList[i] = res.data.result;
                            vm.dept = res.data.result;
                            break;
                        }
                    }
                    // 加载人员信息
                    vm.getEmpByDept(deptCode);
                }).catch(error => {
                    vm.$message({
                        showClose: true,
                        message: '页面:查询部门信息失败,请刷新本页面!',
                        type: 'error'
                    })
                })
            },
        },
    }
</script>


<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .root {
        min-width: 1000px;
        min-height: 900px;
        background-color: rgb(242, 242, 242);
    }

    .root_container {
        margin-top: 10px;
        height: 84vh;
    }

    .titles {
        height: 50px;
        background-color: white;
        overflow: hidden;
        border-bottom: 1px solid rgb(242, 242, 242);
    }


    /*导航栏标题*/
    .navigation_title {
        float: left;
        height: 50px;
        margin-left: 20px;
        margin-top: 5px;
        color: #409EFF;
        z-index: 200;
    }

    /*部门信息div*/
    .message_div {
        width: 100%;
        margin-top: -20px;
        padding-right: 20px;
        overflow: hidden;
        background-color: white;
    }

    /*部门信息span*/
    .message_span {
        color: #409EFF;
        float: left;
        line-height: 30px;
        margin-left: 20px;
        margin-top: 5px;
    }

    /*部门信息按钮*/
    .message_button {
        float: left;
        margin: 20px
    }

    /*部门成员div*/
    .dept_people {
        width: 100%;
        margin-top: 4px;
        padding-right: 20px;
        overflow: hidden;
        background-color: white;
        min-height: 50px;
        line-height: 50px;
    }

    .dept_people_title_height {
        width: 100%;
        margin-top: 4px;
        padding-right: 20px;
        overflow: hidden;
        background-color: white;
        height: 50px;
    }

    .dept_people_null {
        width: 100%;
        height: 450px;
        background-color: white;
        margin-top: 10px;
        padding-right: 20px;
    }

    /*部门成员标题*/
    .dept_people_title {
        float: left;
        line-height: 50px;
        margin-left: 10px;
        color: #409EFF;
    }

    /*头像框*/
    .dept_people_picture {
        float: left;
        width: 160px;
        height: 160px;
    }

    /*头像框图片*/
    .dept_people_img {
        border-radius: 50%;
        margin: 15px;
        width: 120px;
        height: 120px;
    }

    /*人员信息文字行div*/
    .dept_people_content_div {
        float: left;
        width: 70%;
        height: 53px
    }

    /*人员信息名字*/
    .dept_people_name {
        float: left;
        line-height: 20px;
        font-size: 20px;
        font-weight: bold
    }

    /*人员信息查看详情*/
    .dept_people_a {
        float: left;
        margin: 10px 30px;
        color: #409EFF;
        font-size: 16px;
        font-weight: bold;
    }

    /*人员信息标题*/
    .dept_people_p_title {
        float: left;
        line-height: 30px;
        margin-top: 5px;
    }

    /*人员信息内容*/
    .dept_people_p_content {
        float: left;
        line-height: 30px;
        margin-top: 5px;
        color: #8c939d;
    }

    .page-data-div {
        text-align: left;
        background-color: rgb(242, 242, 242);
        height: 50px;
        line-height: 50px;
        clear: both;
        display: block;
        margin: 0px auto;
        position: fixed;
        bottom: 0px;
        width: 100%;
    }

    .aside {
        text-align: left;
        min-height: 900px;
        background-color: white;
    }

    .dialog {
        line-height: 60px;
    }

</style>