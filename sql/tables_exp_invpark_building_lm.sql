---------------------------------------- 扩展实体【开始】 ----------------------------------------
--创建序列
CREATE SEQUENCE seq_yq_exp_invpark_building
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE seq_yq_exp_invpark_building
  OWNER TO park;

--创建表
CREATE TABLE t_yq_exp_invpark_building (
  id                               BIGINT NOT NULL DEFAULT nextval('seq_yq_exp_invpark_building' :: REGCLASS),
  item_code                        CHARACTER VARYING, --项目编码

  --业主信息
  park_owner_cpy_name              CHARACTER VARYING, --业主公司名
  park_owner_name                  CHARACTER VARYING, --业主姓名
  park_owner_sex                   CHARACTER VARYING, --业主性别
  park_owner_phone                 CHARACTER VARYING, --业主电话
  park_owner_reserve_contact_name  CHARACTER VARYING, --业主备用联系人姓名
  park_owner_reserve_contact_sex   CHARACTER VARYING, --业主备用联系人性别
  park_owner_reserve_contact_phone CHARACTER VARYING, --业主备用联系人电话

  --位置信息
  province_code                    CHARACTER VARYING, --省编码
  city_code                        CHARACTER VARYING, --市编码
  region_code                      CHARACTER VARYING, --区编码
  street_code                      CHARACTER VARYING, --街道编码
  province_name                    CHARACTER VARYING, --省名称
  city_name                        CHARACTER VARYING, --市名称
  region_name                      CHARACTER VARYING, --区名称
  street_name                      CHARACTER VARYING, --街道名称
  address                          CHARACTER VARYING, --详细地址

  distance_downtown                NUMERIC(10, 2), --距离主城区
  distance_nearby_life_region      NUMERIC(10, 2), --距离周围生活区域
  manufacturing_district           CHARACTER VARYING, --是否在工业集中区
  distance_freeway_entrance        NUMERIC(10, 2), --高速公路入口距离
  has_public_traffic               BOOLEAN         DEFAULT FALSE, --1.5公里内是否有公共交通
  distance_main_street             NUMERIC(10, 2), --距离主干道
  lane_quantity                    INTEGER, --车道数量
  nearby_price                     NUMERIC(10, 2), --周边价格

  --厂房形象及装修
  has_new_plant                    BOOLEAN         DEFAULT FALSE, --是否是新厂房
  plant_remark                     CHARACTER VARYING, --厂房备注
  plant_decoration                 CHARACTER VARYING, --厂房装修情况
  decoration_remark                CHARACTER VARYING, --装修备注
  plant_electricity                CHARACTER VARYING, --送电情况
  electricity_remark               CHARACTER VARYING, --送电情况备注

  --厂房面积结构
  park_area                        NUMERIC(10, 2), --园区占地面积
  park_building_area               NUMERIC(10, 2), --园区建筑总面积
  single_floor_quantity            INTEGER, --单层厂房栋数
  single_floor_area                NUMERIC(10, 2), --单层厂房面积
  single_floor_height              NUMERIC(10, 2), --单层厂房层高
  single_floor_remark              CHARACTER VARYING, --单层厂房备注
  more_floor_quantity              INTEGER, --多层厂房栋数
  more_floor_area                  NUMERIC(10, 2), --多层厂房面积
  more_floor_height                NUMERIC(10, 2), --多层厂房层高
  more_floor_remark                NUMERIC(10, 2), --多层厂房备注
  more_floor_elevator_quantity     INTEGER, --多层厂房电梯数
  elevator_location                CHARACTER VARYING, --电梯位置
  office_building_quantity         INTEGER, --办公楼数量
  office_building_area             NUMERIC(10, 2), --办公楼建筑面积
  office_building_height           NUMERIC(10, 2), --办公楼层高
  office_building_remark           CHARACTER VARYING, --办公楼备注
  other_building_quantity          INTEGER, --其他附房数量
  other_building_area              NUMERIC(10, 2), --其他附房建筑面积
  other_building_rate              NUMERIC(10, 2), --附房占比
  other_building_remark            CHARACTER VARYING, --其他附房备注
  vacancy_soil_area                NUMERIC(10, 2), --空地面积
  vacancy_soil_remark              CHARACTER VARYING, --空地备注

  --配电及消防
  total_electricity                NUMERIC(10, 2), --总用电容量
  firefighting_level               CHARACTER VARYING, --消防等级

  --商务条款及政策情况
  estate_year                      NUMERIC(10, 2), --地产年限
  contract_year                    NUMERIC(10, 2), --合同年限
  belong_area                      CHARACTER VARYING, --所属板块
  has_house_certificate            BOOLEAN         DEFAULT FALSE, --房产证
  has_eia_certificate              BOOLEAN         DEFAULT FALSE, --环评证书
  has_sewage_certificate           BOOLEAN         DEFAULT FALSE, --排污证
  has_firefighting_certificate     BOOLEAN         DEFAULT FALSE, --消防证
  certificate_remark               CHARACTER VARYING, --拥有证书备注
  unit_price                       NUMERIC(10, 2), --价格
  strata_fee                       NUMERIC(10, 2), --物业费
  free_rent_date                   NUMERIC(10, 2), --免租期
  pay_rule                         CHARACTER VARYING, --付款规则
  increase_rule                    CHARACTER VARYING, --递增规则
  tax_request                      CHARACTER VARYING, --税收要求
  taxes                            NUMERIC(10, 2), --税金
  restrict_industry                CHARACTER VARYING, --限制行业
  show_video                       CHARACTER VARYING, --视频展示
  show_picture                     CHARACTER VARYING, --图片展示
  exp_emp_code                     CHARACTER VARYING, --拓展人编码
  exp_emp_name                     CHARACTER VARYING, --拓展人姓名
  exp_emp_phone                    CHARACTER VARYING, --拓展人电话
  exp_emp_cpy_name                 CHARACTER VARYING, --拓展人公司
  score                            NUMERIC(5, 2), --评分
  assess_result                    CHARACTER VARYING, --评定结果
  exp_status                       CHARACTER VARYING, --项目状态

  --基础字段
  cpy_code                         CHARACTER VARYING, --公司编号
  cpy_name                         CHARACTER VARYING, --公司名称
  owner_code                       CHARACTER VARYING, --所属用户编码
  owner_name                       CHARACTER VARYING, --所属用户姓名
  owner_dept_code                  CHARACTER VARYING, --数据所属部门
  owner_dept_name                  CHARACTER VARYING, --数据所属部门名称
  create_code                      CHARACTER VARYING, --创建者
  create_name                      CHARACTER VARYING, --创建者名称
  create_dept_code                 CHARACTER VARYING, --创建者部门编号
  create_dept_name                 CHARACTER VARYING, --创建者部门名称
  last_update_code                 CHARACTER VARYING, --最后更新者
  last_update_name                 CHARACTER VARYING, --最后更新者名称
  create_time                      TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time                 TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  is_deleted                       BOOLEAN         DEFAULT FALSE, --是否作废或者删除
  remark                           CHARACTER VARYING, --备注
  CONSTRAINT pk_yq_exp_invpark_building PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX item_code
  ON t_yq_exp_invpark_building (item_code);

ALTER TABLE t_yq_exp_invpark_building
  OWNER TO park;

--注释
COMMENT ON TABLE t_yq_exp_invpark_building IS '拓展信息表';

COMMENT ON COLUMN t_yq_exp_invpark_building.item_code IS '项目编码';
COMMENT ON COLUMN t_yq_exp_invpark_building.park_owner_cpy_name IS '业主公司名';
COMMENT ON COLUMN t_yq_exp_invpark_building.park_owner_name IS '业主姓名';
COMMENT ON COLUMN t_yq_exp_invpark_building.park_owner_sex IS '业主性别';
COMMENT ON COLUMN t_yq_exp_invpark_building.park_owner_phone IS '业主电话';
COMMENT ON COLUMN t_yq_exp_invpark_building.park_owner_reserve_contact_name IS '业主备用联系人姓名';
COMMENT ON COLUMN t_yq_exp_invpark_building.park_owner_reserve_contact_sex IS '业主备用联系人性别';
COMMENT ON COLUMN t_yq_exp_invpark_building.park_owner_reserve_contact_phone IS '业主备用联系人电话';

COMMENT ON COLUMN t_yq_exp_invpark_building.province_code IS '省编码';
COMMENT ON COLUMN t_yq_exp_invpark_building.city_code IS '市编码';
COMMENT ON COLUMN t_yq_exp_invpark_building.region_code IS '区编码';
COMMENT ON COLUMN t_yq_exp_invpark_building.street_code IS '街道编码';
COMMENT ON COLUMN t_yq_exp_invpark_building.province_name IS '省名称';
COMMENT ON COLUMN t_yq_exp_invpark_building.city_name IS '市名称';
COMMENT ON COLUMN t_yq_exp_invpark_building.region_name IS '区名称';
COMMENT ON COLUMN t_yq_exp_invpark_building.street_name IS '街道名称';
COMMENT ON COLUMN t_yq_exp_invpark_building.address IS '详细地址';

COMMENT ON COLUMN t_yq_exp_invpark_building.distance_downtown IS '距离主城区';
COMMENT ON COLUMN t_yq_exp_invpark_building.distance_nearby_life_region IS '距离周围生活区域';
COMMENT ON COLUMN t_yq_exp_invpark_building.manufacturing_district IS '是否在工业集中区';
COMMENT ON COLUMN t_yq_exp_invpark_building.distance_freeway_entrance IS '高速公路入口距离';
COMMENT ON COLUMN t_yq_exp_invpark_building.has_public_traffic IS '1.5公里内是否有公共交通';
COMMENT ON COLUMN t_yq_exp_invpark_building.distance_main_street IS '距离主干道';
COMMENT ON COLUMN t_yq_exp_invpark_building.lane_quantity IS '车道数量';
COMMENT ON COLUMN t_yq_exp_invpark_building.nearby_price IS '周边价格';

COMMENT ON COLUMN t_yq_exp_invpark_building.has_new_plant IS '是否是新厂房';
COMMENT ON COLUMN t_yq_exp_invpark_building.plant_remark IS '厂房备注';
COMMENT ON COLUMN t_yq_exp_invpark_building.plant_decoration IS '厂房装修情况';
COMMENT ON COLUMN t_yq_exp_invpark_building.decoration_remark IS '装修备注';
COMMENT ON COLUMN t_yq_exp_invpark_building.plant_electricity IS '送电情况';
COMMENT ON COLUMN t_yq_exp_invpark_building.electricity_remark IS '送点情况备注';

COMMENT ON COLUMN t_yq_exp_invpark_building.park_area IS '园区占地面积';
COMMENT ON COLUMN t_yq_exp_invpark_building.park_building_area IS '园区总建筑面积';
COMMENT ON COLUMN t_yq_exp_invpark_building.single_floor_quantity IS '单层厂房栋数';
COMMENT ON COLUMN t_yq_exp_invpark_building.single_floor_area IS '单层厂房面积';
COMMENT ON COLUMN t_yq_exp_invpark_building.single_floor_height IS '单层厂房层高';
COMMENT ON COLUMN t_yq_exp_invpark_building.single_floor_remark IS '单层厂房备注';
COMMENT ON COLUMN t_yq_exp_invpark_building.more_floor_quantity IS '多层厂房栋数';
COMMENT ON COLUMN t_yq_exp_invpark_building.more_floor_area IS '多层厂房面积';
COMMENT ON COLUMN t_yq_exp_invpark_building.more_floor_height IS '多层厂房层高';
COMMENT ON COLUMN t_yq_exp_invpark_building.more_floor_remark IS '多层厂房备注';
COMMENT ON COLUMN t_yq_exp_invpark_building.more_floor_elevator_quantity IS '多层厂房电梯数';
COMMENT ON COLUMN t_yq_exp_invpark_building.elevator_location IS '电梯位置';
COMMENT ON COLUMN t_yq_exp_invpark_building.office_building_quantity IS '办公楼数量';
COMMENT ON COLUMN t_yq_exp_invpark_building.office_building_area IS '办公楼建筑面积';
COMMENT ON COLUMN t_yq_exp_invpark_building.office_building_height IS '办公楼层高';
COMMENT ON COLUMN t_yq_exp_invpark_building.office_building_remark IS '办公楼备注';
COMMENT ON COLUMN t_yq_exp_invpark_building.other_building_quantity IS '其他附房数量';
COMMENT ON COLUMN t_yq_exp_invpark_building.other_building_area IS '其他附房建筑面积';
COMMENT ON COLUMN t_yq_exp_invpark_building.other_building_rate IS '附房占比';
COMMENT ON COLUMN t_yq_exp_invpark_building.other_building_remark IS '其他附房备注';
COMMENT ON COLUMN t_yq_exp_invpark_building.vacancy_soil_area IS '空地面积';
COMMENT ON COLUMN t_yq_exp_invpark_building.vacancy_soil_remark IS '空地备注';

COMMENT ON COLUMN t_yq_exp_invpark_building.total_electricity IS '总用电容量';
COMMENT ON COLUMN t_yq_exp_invpark_building.firefighting_level IS '消防等级';

COMMENT ON COLUMN t_yq_exp_invpark_building.estate_year IS '地产年限';
COMMENT ON COLUMN t_yq_exp_invpark_building.contract_year IS '合同年限';
COMMENT ON COLUMN t_yq_exp_invpark_building.belong_area IS '所属板块';
COMMENT ON COLUMN t_yq_exp_invpark_building.has_house_certificate IS '房产证';
COMMENT ON COLUMN t_yq_exp_invpark_building.has_eia_certificate IS '环评证书';
COMMENT ON COLUMN t_yq_exp_invpark_building.has_sewage_certificate IS '排污证';
COMMENT ON COLUMN t_yq_exp_invpark_building.has_firefighting_certificate IS '消防证';
COMMENT ON COLUMN t_yq_exp_invpark_building.certificate_remark IS '拥有证书备注';
COMMENT ON COLUMN t_yq_exp_invpark_building.unit_price IS '价格';
COMMENT ON COLUMN t_yq_exp_invpark_building.strata_fee IS '物业费';
COMMENT ON COLUMN t_yq_exp_invpark_building.free_rent_date IS '免租期';
COMMENT ON COLUMN t_yq_exp_invpark_building.pay_rule IS '付款规则';
COMMENT ON COLUMN t_yq_exp_invpark_building.increase_rule IS '递增规则';
COMMENT ON COLUMN t_yq_exp_invpark_building.tax_request IS '税收要求';
COMMENT ON COLUMN t_yq_exp_invpark_building.taxes IS '税金';
COMMENT ON COLUMN t_yq_exp_invpark_building.restrict_industry IS '限制行业';
COMMENT ON COLUMN t_yq_exp_invpark_building.show_video IS '视频展示';
COMMENT ON COLUMN t_yq_exp_invpark_building.show_picture IS '图片展示';
COMMENT ON COLUMN t_yq_exp_invpark_building.exp_emp_code IS '拓展人编码';
COMMENT ON COLUMN t_yq_exp_invpark_building.exp_emp_name IS '拓展人姓名';
COMMENT ON COLUMN t_yq_exp_invpark_building.exp_emp_phone IS '拓展人电话';
COMMENT ON COLUMN t_yq_exp_invpark_building.exp_emp_cpy_name IS '拓展人公司';
COMMENT ON COLUMN t_yq_exp_invpark_building.score IS '评分';
COMMENT ON COLUMN t_yq_exp_invpark_building.assess_result IS '评定结果';
COMMENT ON COLUMN t_yq_exp_invpark_building.exp_status IS '项目状态';

COMMENT ON COLUMN t_yq_exp_invpark_building.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_exp_invpark_building.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_exp_invpark_building.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_yq_exp_invpark_building.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_yq_exp_invpark_building.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_yq_exp_invpark_building.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_yq_exp_invpark_building.create_code IS '创建者';
COMMENT ON COLUMN t_yq_exp_invpark_building.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_exp_invpark_building.create_dept_code IS '创建者部门code';
COMMENT ON COLUMN t_yq_exp_invpark_building.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_yq_exp_invpark_building.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_exp_invpark_building.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_exp_invpark_building.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_exp_invpark_building.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_exp_invpark_building.is_deleted IS '是否删除';
COMMENT ON COLUMN t_yq_exp_invpark_building.remark IS '备注';
---------------------------------------- 扩展实体【结束】 ----------------------------------------