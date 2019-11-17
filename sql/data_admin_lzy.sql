--用户
INSERT INTO t_yq_sys_user_user (user_code,user_name,work_number,password,user_phone,post_code,emp_code,emp_name,dept_code,dept_name,email,sex,remark,cpy_code,cpy_name,create_code,last_update_code,create_time,last_update_time,is_deleted)
VALUES
('u001', 'kfwy',  '000001','21232f297a57a5a743894a0e4a801fc3','17621511366','p001','e001',  '默认人员-kfwy', 'd001', '默认部门-kfwy','admin@kufangwuyou.com', '2','初始管理员', 'cpy001','三蚁科技', 'e001','e001', now(), now(), 'f');

--岗位
INSERT INTO t_yq_hrm_org_post (post_code,post_name,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted)
VALUES
('p001','超级管理员','超级管理员','cpy001','e001','e001', now(), now(), 'f');


--菜单
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807207d83f616db2e46', '所有菜单(根菜单)', '无', '0', '1', '暂无', '暂无', '0', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720c5904d8b3ebd45', '园区管理', 'parkManager', '1807207d83f616db2e46', '1', '暂无', '暂无', '1', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('18072036d9bf9b63c64f', '招商管理', 'investmentManager', '1807207d83f616db2e46', '1', '暂无', '暂无', '1', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('18072024060ef954a74b', '租户管理', 'tenantManager', '1807207d83f616db2e46', '1', '暂无', '暂无', '1', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720166462f65f604f', '系统管理', 'systemManager', '1807207d83f616db2e46', '1', '暂无', '暂无', '1', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720ad86ee975cf746', '园区列表', 'parkList', '180720c5904d8b3ebd45', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720038f6a69fe0d4d', '园区页', 'parkPage', '180720ad86ee975cf746', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807204a73ca8dfec344', '园区详情页', 'parkDetailPage', '180720ad86ee975cf746', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720008fe25bce7747', '楼栋详情页', 'buildPage', '180720ad86ee975cf746', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720e6101ea3871d4c', '层详情页', 'floorPage', '180720ad86ee975cf746', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720eb7c56944f5e44', '客户列表', 'customerList', '18072036d9bf9b63c64f', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720df8764f666ea45', '客户页', 'customerPage', '180720eb7c56944f5e44', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720209646255e0f47', '客户详情页', 'customerDetailPage', '180720eb7c56944f5e44', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807205553a4e9eae847', '合同列表', 'contractList', '18072024060ef954a74b', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(),now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720767693f4047e42', '合同页', 'contrPage', '1807205553a4e9eae847', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807207da40cc72f9f4e', '费用页', 'feePage', '1807205553a4e9eae847', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720ac37151101a641', '详情页', 'contrDetailPage', '1807205553a4e9eae847', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001',now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720fd00cdfe98af48', '部门列表', 'deptList', '180720166462f65f604f', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720623d665de3da49', '岗位列表', 'postList', '180720166462f65f604f', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('18072035fb08cb521640', '岗位分配列表', 'dividePostList', '180720166462f65f604f', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('18072052a860a4bce240', '菜单列表', 'menuList', '180720166462f65f604f', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807201a5430b30cc14a', '人员列表', 'employeeList', '180720166462f65f604f', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807208314512278844b', '新增园区', 'addParkBtn', '180720038f6a69fe0d4d', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720fed503ba6f5d44', '客户列表', 'customerListParkBtn', '180720038f6a69fe0d4d', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720900e22fcf1d645', '办理入住', 'dealEntryParkBtn', '180720038f6a69fe0d4d', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720e82f0feb85c345', '修改园区', 'editParkBtn', '1807204a73ca8dfec344', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720395cffc5bf4b40', '添加本栋', 'addBuildBtn', '1807204a73ca8dfec344', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720365c9764dec64d', '招商加对接人', 'addInvestDockPersonBtn', '1807204a73ca8dfec344', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('18072089e0c03a67f442', '物业加对接人', 'addProDockPersonBtn', '1807204a73ca8dfec344', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('18072063eb35b92c5b40', '添加来访', 'addVisitBtn', '1807204a73ca8dfec344', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720cc0ae7209bdc4a', '修改楼栋', 'editBuildBtn', '180720008fe25bce7747', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(),now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720a4cd28e4a4d54a', '添加本层', 'addFloorBtn', '180720008fe25bce7747', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('18072077c9188036c549', '修改楼层', 'editFloorBtn', '180720e6101ea3871d4c', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720decab8029c864a', '新增客户', 'addCustomerBtn', '180720df8764f666ea45', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001',now(),now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720510ff967e91e4a', '详情', 'detailCustomerBtn', '180720df8764f666ea45', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720ab1f4aee226547', '跟进', 'flowupCustomerBtn', '180720df8764f666ea45', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('18072074c7e71c031146', '修改', 'editCustomerBtn', '180720209646255e0f47', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720aa0b7933ae534a', '添加新联系人', 'addContactBtn', '180720209646255e0f47', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720a86de55dd5734f', '添加需求区域', 'addNeedAreaBtn', '180720209646255e0f47', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720fbbd47f3d4124d', '编辑', 'editNeedAreaBtn', '180720209646255e0f47', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807209e1d07abd5b542', '添加跟进', 'addFlowupBtn', '180720209646255e0f47', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807208a455444902543', '新增租户', 'addTenantBtn', '180720767693f4047e42', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807206bef1602e9d449', '手动抄表', 'manualMeterBtn', '180720767693f4047e42', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807208485e594e57b4e', '费用详情', 'detailFeeBtn', '180720767693f4047e42', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720770aa6e7428b4d', '租户详情', 'detailTenantBtn', '180720767693f4047e42', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720b9e54137bcd545', '租赁缴费', 'rentPaymentBtn', '1807207da40cc72f9f4e', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807206539f68555944d', '电费缴费', 'electricityFeePaymentBtn', '1807207da40cc72f9f4e', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720654e7e136fac4c', '水表缴费', 'waterFeePaymentBtn', '1807207da40cc72f9f4e', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807207a97bbc018b942', '停车缴费', 'parkingPaymentBtn', '1807207da40cc72f9f4e', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807208bc98e77f8854a', '增加出租位置', 'addRentLocationBtn', '180720ac37151101a641', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720416112fb49d543', '提交合同', 'submitContractBtn', '180720ac37151101a641', '1', '暂无', '暂无', '4', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807202c61b0db38004a', '新增下级部门', 'addSubDeptBtn', '180720fd00cdfe98af48', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807208dd9b864cf1d43', '修改部门信息', 'editDeptBtn', '180720fd00cdfe98af48', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807200b52e796b12040', '新增岗位', 'addPostBtn', '180720623d665de3da49', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720e5523adc9e5a41', '分配权限', 'dividePermitBtn', '180720623d665de3da49', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001',now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807202b7e1c48a7144a', '修改', 'editPostBtn', '180720623d665de3da49', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807201441c9b76cb84a', '保存', 'savaDividePostBtn', '18072035fb08cb521640', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807202b9aa4dd78a34c', '新增', 'addMenuBtn', '18072052a860a4bce240', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720403652e6ae964e', '修改', 'editMenuBtn', '18072052a860a4bce240', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1807206c04b1d533e142', '新增', 'addEmployeeBtn', '1807201a5430b30cc14a', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180720b44db4ea33994d', '修改', 'editEmployeeBtn', '1807201a5430b30cc14a', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180830acabbb04008e4b', '日常工作', 'taskManager', '1807207d83f616db2e46', '1', '暂无', '暂无', '1', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('18083084f25ffc59744b', '招商日常', 'atiTaskList', '180830acabbb04008e4b', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180830874341f6bb9a44', '添加任务', 'addAtiTaskBtn', '18083084f25ffc59744b', '1', '暂无', '暂无', '3', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180831b7bec35d3dcf4a', '公司列表', 'cpyList', '180720166462f65f604f', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180831673ff47666844a', '角色列表', 'roleList', '180720166462f65f604f', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('1808318397ab4a96f045', '角色分配列表', 'divideRoleList', '180720166462f65f604f', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180831a221d17b588647', '地区列表', 'areaList', '180720166462f65f604f', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');
INSERT INTO t_yq_sys_auth_menu (menu_code,menu_name,item,parent_code,menu_type,pack,icon_cls,level,sort,remark,cpy_code,create_code,last_update_code,create_time,last_update_time,is_deleted) VALUES ('180831743a5e588e1445', '用户列表', 'userList', '180720166462f65f604f', '1', '暂无', '暂无', '2', '111', '备注无', 'cpy001', 'u001', 'u001', now(), now(), 'f');



--岗位菜单
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807207d83f616db2e46');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720166462f65f604f');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '18072052a860a4bce240');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720403652e6ae964e');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807202b9aa4dd78a34c');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807201a5430b30cc14a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807206c04b1d533e142');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720b44db4ea33994d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1808318397ab4a96f045');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720623d665de3da49');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807200b52e796b12040');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720e5523adc9e5a41');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807202b7e1c48a7144a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180831a221d17b588647');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180831673ff47666844a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180831b7bec35d3dcf4a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '18072035fb08cb521640');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807201441c9b76cb84a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180831743a5e588e1445');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720fd00cdfe98af48');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807202c61b0db38004a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807208dd9b864cf1d43');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180830acabbb04008e4b');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '18083084f25ffc59744b');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180830874341f6bb9a44');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720c5904d8b3ebd45');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720ad86ee975cf746');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720008fe25bce7747');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720a4cd28e4a4d54a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720cc0ae7209bdc4a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720038f6a69fe0d4d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720fed503ba6f5d44');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720900e22fcf1d645');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807208314512278844b');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720e6101ea3871d4c');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '18072077c9188036c549');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807204a73ca8dfec344');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '18072063eb35b92c5b40');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720365c9764dec64d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720e82f0feb85c345');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720395cffc5bf4b40');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '18072089e0c03a67f442');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '18072024060ef954a74b');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807205553a4e9eae847');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720ac37151101a641');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807208bc98e77f8854a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720416112fb49d543');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720767693f4047e42');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720770aa6e7428b4d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807208a455444902543');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807206bef1602e9d449');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807208485e594e57b4e');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807207da40cc72f9f4e');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720b9e54137bcd545');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807206539f68555944d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807207a97bbc018b942');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720654e7e136fac4c');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '18072036d9bf9b63c64f');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720eb7c56944f5e44');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720df8764f666ea45');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720510ff967e91e4a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720ab1f4aee226547');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720decab8029c864a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720209646255e0f47');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720aa0b7933ae534a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720fbbd47f3d4124d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '18072074c7e71c031146');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '1807209e1d07abd5b542');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('p001', '180720a86de55dd5734f');



--子公司岗位菜单
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807207d83f616db2e46');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720166462f65f604f');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '18072052a860a4bce240');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720403652e6ae964e');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807202b9aa4dd78a34c');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807201a5430b30cc14a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807206c04b1d533e142');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720b44db4ea33994d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1808318397ab4a96f045');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720623d665de3da49');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807200b52e796b12040');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720e5523adc9e5a41');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807202b7e1c48a7144a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180831a221d17b588647');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180831673ff47666844a');

INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '18072035fb08cb521640');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807201441c9b76cb84a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180831743a5e588e1445');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720fd00cdfe98af48');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807202c61b0db38004a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807208dd9b864cf1d43');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180830acabbb04008e4b');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '18083084f25ffc59744b');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180830874341f6bb9a44');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720c5904d8b3ebd45');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720ad86ee975cf746');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720008fe25bce7747');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720a4cd28e4a4d54a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720cc0ae7209bdc4a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720038f6a69fe0d4d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720fed503ba6f5d44');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720900e22fcf1d645');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807208314512278844b');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720e6101ea3871d4c');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '18072077c9188036c549');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807204a73ca8dfec344');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '18072063eb35b92c5b40');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720365c9764dec64d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720e82f0feb85c345');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720395cffc5bf4b40');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '18072089e0c03a67f442');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '18072024060ef954a74b');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807205553a4e9eae847');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720ac37151101a641');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807208bc98e77f8854a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720416112fb49d543');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720767693f4047e42');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720770aa6e7428b4d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807208a455444902543');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807206bef1602e9d449');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807208485e594e57b4e');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807207da40cc72f9f4e');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720b9e54137bcd545');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807206539f68555944d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807207a97bbc018b942');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720654e7e136fac4c');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '18072036d9bf9b63c64f');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720eb7c56944f5e44');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720df8764f666ea45');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720510ff967e91e4a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720ab1f4aee226547');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720decab8029c864a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720209646255e0f47');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720aa0b7933ae534a');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720fbbd47f3d4124d');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '18072074c7e71c031146');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '1807209e1d07abd5b542');
INSERT INTO t_yq_hrm_org_post_menu VALUES ('subp001', '180720a86de55dd5734f');

--子公司岗位菜单end