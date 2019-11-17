-----------------------客户主表 begin-----------------------
--创建序列
CREATE SEQUENCE seq_yq_ati_cus_customer
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_cus_customer
  OWNER TO park;

--创建表
CREATE TABLE t_yq_ati_cus_customer (
  id                  BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_cus_customer' :: REGCLASS),
  cus_code            CHARACTER VARYING, --客户编码
  customer_type       CHARACTER VARYING, --客户类型
  company_name        CHARACTER VARYING, --公司名称
  trade               CHARACTER VARYING, --行业分类
  company_product     CHARACTER VARYING, --公司产品
  cus_status          CHARACTER VARYING, --客户状态
  ditch               CHARACTER VARYING, --客户渠道
  channel_code        CHARACTER VARYING, --客户渠道
  main_area           NUMERIC(10, 2), --主面积
  entry_time          TIMESTAMP WITHOUT TIME ZONE, --入驻时间
  electric_ask        CHARACTER VARYING, --电量要求
  floor_ask           CHARACTER VARYING, --楼层要求
  floor_height_ask    CHARACTER VARYING, --层高要求
  reason              CHARACTER VARYING, --找房原因
  use_type            CHARACTER VARYING, --用途

  has_eia             BOOLEAN         DEFAULT FALSE, --是否需要环评
  has_certify         BOOLEAN         DEFAULT FALSE, --是否需要产证
  has_office_build    BOOLEAN         DEFAULT FALSE, --是否需要办公区
  has_canteen         BOOLEAN         DEFAULT FALSE, --是否需要食堂
  has_supermarket     BOOLEAN         DEFAULT FALSE, --是否有超市
  has_canopy          BOOLEAN         DEFAULT FALSE, --是否有雨棚
  has_load_dock       BOOLEAN         DEFAULT FALSE, --有无卸货平台
  has_charging_pile   BOOLEAN         DEFAULT FALSE, --有无充电桩
  has_sewage_certify  BOOLEAN         DEFAULT FALSE, --有无排污证

  more_depict         CHARACTER VARYING, --更多需求描述
  main_contact_name   CHARACTER VARYING, --主联系人姓名
  main_contact_sex    CHARACTER VARYING, --主联系人性别
  main_contact_phone  CHARACTER VARYING, --主联系人电话
  landline_phone      CHARACTER VARYING, --客户座机

  emp_code            CHARACTER VARYING, --当前业务人员编号
  emp_name            CHARACTER VARYING, --当前业务人员姓名
  dept_code           CHARACTER VARYING, --当前业务人员部门编号
  pk_code             CHARACTER VARYING, --当前园区编号
  pk_name             CHARACTER VARYING, --当前园区名称

  agency_cpy_name     CHARACTER VARYING, --中介单位名称
  agency_person_name  CHARACTER VARYING, --中介联系人名称
  agency_person_sex   CHARACTER VARYING, --中介联系人性别
  agency_person_phone CHARACTER VARYING, --中介联系人电话
  attachment          CHARACTER VARYING, --附件

  cpy_code            CHARACTER VARYING, --公司编号
  cpy_name            CHARACTER VARYING, --公司名称
  owner_code          CHARACTER VARYING, --该条信息所属者
  owner_name          CHARACTER VARYING,
  owner_dept_code     CHARACTER VARYING, --该条信息所属部门
  owner_dept_name     CHARACTER VARYING,
  create_code         CHARACTER VARYING, --创建者
  create_dept_code    CHARACTER VARYING, --创建部门code
  create_dept_name    CHARACTER VARYING, --创建部门名称
  create_name         CHARACTER VARYING, --创建者名称
  last_update_code    CHARACTER VARYING, --最后更新者
  last_update_name    CHARACTER VARYING, --最后更新者名称
  create_time         TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time    TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  remark              CHARACTER VARYING, --备注
  is_deleted          BOOLEAN         DEFAULT FALSE, --是否删除
  CONSTRAINT pk_yq_ati_cus_customer PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX cus_code
  ON t_yq_ati_cus_customer (cus_code);
--创建索引
CREATE INDEX cus_cpy_code
  ON t_yq_ati_cus_customer (cpy_code);

ALTER TABLE t_yq_ati_cus_customer
  OWNER TO park;

COMMENT ON TABLE t_yq_ati_cus_customer IS '客户管理表';
COMMENT ON COLUMN t_yq_ati_cus_customer.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_cus_customer.cpy_name IS '公司名称';

COMMENT ON COLUMN t_yq_ati_cus_customer.cus_code IS '客户编码';
COMMENT ON COLUMN t_yq_ati_cus_customer.customer_type IS '客户类型';
COMMENT ON COLUMN t_yq_ati_cus_customer.company_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_cus_customer.trade IS '行业分类';
COMMENT ON COLUMN t_yq_ati_cus_customer.company_product IS '公司产品';
COMMENT ON COLUMN t_yq_ati_cus_customer.cus_status IS '客户状态';
COMMENT ON COLUMN t_yq_ati_cus_customer.ditch IS '客户渠道';
COMMENT ON COLUMN t_yq_ati_cus_customer.channel_code IS '渠道编码';
COMMENT ON COLUMN t_yq_ati_cus_customer.main_area IS '主面积';
COMMENT ON COLUMN t_yq_ati_cus_customer.entry_time IS '入驻时间';
COMMENT ON COLUMN t_yq_ati_cus_customer.electric_ask IS '电量要求';
COMMENT ON COLUMN t_yq_ati_cus_customer.floor_ask IS '楼层需求';
COMMENT ON COLUMN t_yq_ati_cus_customer.floor_height_ask IS '层高要求';
COMMENT ON COLUMN t_yq_ati_cus_customer.reason IS '找房原因';
COMMENT ON COLUMN t_yq_ati_cus_customer.use_type IS '租房用途';

COMMENT ON COLUMN t_yq_ati_cus_customer.has_eia IS '是否需要环评';
COMMENT ON COLUMN t_yq_ati_cus_customer.has_certify IS '是否需要产证';
COMMENT ON COLUMN t_yq_ati_cus_customer.has_office_build IS '是否需要办公区';
COMMENT ON COLUMN t_yq_ati_cus_customer.has_canteen IS '是否需要食堂';
COMMENT ON COLUMN t_yq_ati_cus_customer.has_supermarket IS '是否有超市';
COMMENT ON COLUMN t_yq_ati_cus_customer.has_canopy IS '是否有雨棚';
COMMENT ON COLUMN t_yq_ati_cus_customer.has_load_dock IS '有无卸货平台';
COMMENT ON COLUMN t_yq_ati_cus_customer.has_charging_pile IS '有无充电桩';
COMMENT ON COLUMN t_yq_ati_cus_customer.has_sewage_certify IS '有无排污证';
COMMENT ON COLUMN t_yq_ati_cus_customer.more_depict IS '更多需求描述';

COMMENT ON COLUMN t_yq_ati_cus_customer.main_contact_name IS '主联系人姓名';
COMMENT ON COLUMN t_yq_ati_cus_customer.main_contact_sex IS '主联系人性别';
COMMENT ON COLUMN t_yq_ati_cus_customer.main_contact_phone IS '主联系人电话';
COMMENT ON COLUMN t_yq_ati_cus_customer.landline_phone IS '客户座机';
COMMENT ON COLUMN t_yq_ati_cus_customer.attachment IS '附件';

COMMENT ON COLUMN t_yq_ati_cus_customer.emp_code IS '人员编号';
COMMENT ON COLUMN t_yq_ati_cus_customer.emp_name IS '人员姓名';
COMMENT ON COLUMN t_yq_ati_cus_customer.dept_code IS '部门编号';
COMMENT ON COLUMN t_yq_ati_cus_customer.pk_code IS '园区编号';
COMMENT ON COLUMN t_yq_ati_cus_customer.pk_name IS '园区名称';

COMMENT ON COLUMN t_yq_ati_cus_customer.agency_cpy_name IS '中介单位名称';
COMMENT ON COLUMN t_yq_ati_cus_customer.agency_person_name IS '中介联系人名称';
COMMENT ON COLUMN t_yq_ati_cus_customer.agency_person_sex IS '中介联系人性别';
COMMENT ON COLUMN t_yq_ati_cus_customer.agency_person_phone IS '中介联系人电话';

COMMENT ON COLUMN t_yq_ati_cus_customer.owner_code IS '该条信息所属者code';
COMMENT ON COLUMN t_yq_ati_cus_customer.owner_came IS '该条信息所属者';
COMMENT ON COLUMN t_yq_ati_cus_customer.owner_dept_code IS '该条信息所属部门code';
COMMENT ON COLUMN t_yq_ati_cus_customer.owner_dept_name IS '该条信息所属部门';
COMMENT ON COLUMN t_yq_ati_cus_customer.create_code IS '创建者';
COMMENT ON COLUMN t_yq_ati_cus_customer.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_ati_cus_customer.create_dept_code IS '创建部门code';
COMMENT ON COLUMN t_yq_ati_cus_customer.create_dept_name IS '创建部门名称';
COMMENT ON COLUMN t_yq_ati_cus_customer.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_ati_cus_customer.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_ati_cus_customer.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_ati_cus_customer.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_ati_cus_customer.remark IS '备注';
COMMENT ON COLUMN t_yq_ati_cus_customer.is_deleted IS '是否删除';
-----------------------客户主表 end-----------------------


-----------------------客户联系人表 begin-----------------------
--创建序列
CREATE SEQUENCE seq_yq_ati_cus_customer_relate
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_cus_customer_relate
  OWNER TO park;

--创建表
CREATE TABLE t_yq_ati_cus_customer_relate (
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_cus_customer_relate' :: REGCLASS),

  cus_code         CHARACTER VARYING, --客户编码
  relate_code      CHARACTER VARYING, --联系人编号(主键唯一标识)
  contact_name     CHARACTER VARYING, --联系人姓名
  contact_sex      CHARACTER VARYING, --联系人性别
  contact_phone    CHARACTER VARYING, --联系人电话
  contact_job      CHARACTER VARYING, --联系人职务
  has_main_contact BOOLEAN         DEFAULT FALSE, --是否是主联系人

  cpy_code         CHARACTER VARYING, --公司编号
  cpy_name         CHARACTER VARYING, --公司名称
  create_code      CHARACTER VARYING, --创建者
  create_name      CHARACTER VARYING, --创建者名称
  last_update_code CHARACTER VARYING, --最后更新者
  last_update_name CHARACTER VARYING, --最后更新者名称
  create_time      TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  remark           CHARACTER VARYING, --备注
  is_deleted       BOOLEAN         DEFAULT FALSE, --是否删除
  CONSTRAINT pk_yq_ati_cus_customer_relate PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX relate_code
  ON t_yq_ati_cus_customer_relate (relate_code);
--创建索引
CREATE INDEX contact_cus_code
  ON t_yq_ati_cus_customer_relate (cus_code);
CREATE INDEX contact_cpy_code
  ON t_yq_ati_cus_customer_relate (cpy_code);


ALTER TABLE t_yq_ati_cus_customer_relate
  OWNER TO park;

COMMENT ON TABLE t_yq_ati_cus_customer_relate IS '客户联系人表';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.cpy_code IS '公司编码';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.cpy_name IS '公司名称';

COMMENT ON COLUMN t_yq_ati_cus_customer_relate.cus_code IS '客户编码';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.relate_code IS '联系人编号';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.contact_name IS '联系人姓名';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.contact_sex IS '联系人性别';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.contact_phone IS '联系人电话';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.contact_job IS '联系人职务';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.has_main_contact IS '是否是主联系人';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.create_code IS '创建者';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.remark IS '备注';
COMMENT ON COLUMN t_yq_ati_cus_customer_relate.is_deleted IS '是否删除';
-----------------------客户联系人表 end-----------------------


-----------------------客户需求区域表 begin-----------------------
--创建序列
CREATE SEQUENCE seq_yq_ati_cus_need_area
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_cus_need_area
  OWNER TO park;

--创建表
CREATE TABLE t_yq_ati_cus_need_area (
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_cus_need_area' :: REGCLASS),
  cus_area_code    CHARACTER VARYING, --客户需求编号
  cus_code         CHARACTER VARYING, --客户编号

  province_code    CHARACTER VARYING, --省编号
  city_code        CHARACTER VARYING, --城市编号
  region_code      CHARACTER VARYING, --区编号
  street_code      CHARACTER VARYING, --街道编号

  province_name    CHARACTER VARYING, --客户需求省份
  city_name        CHARACTER VARYING, --客户需求城市
  region_name      CHARACTER VARYING, --客户需求区
  street_name      CHARACTER VARYING, --客户需求街道

  cpy_code         CHARACTER VARYING, --公司编号
  cpy_name         CHARACTER VARYING, --公司名称
  create_code      CHARACTER VARYING, --创建者
  create_name      CHARACTER VARYING, --创建者名称
  last_update_code CHARACTER VARYING, --最后更新者
  last_update_name CHARACTER VARYING, --最后更新者名称
  create_time      TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  remark           CHARACTER VARYING, --备注
  is_deleted       BOOLEAN         DEFAULT FALSE, --是否删除
  CONSTRAINT pk_yq_ati_cus_need_area PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX cus_area_code
  ON t_yq_ati_cus_need_area (cus_area_code);
--创建索引
CREATE INDEX need_area_cus_code
  ON t_yq_ati_cus_need_area (cus_code);
CREATE INDEX need_area_cpy_code
  ON t_yq_ati_cus_need_area (cpy_code);

ALTER TABLE t_yq_ati_cus_need_area
  OWNER TO park;

COMMENT ON TABLE t_yq_ati_cus_need_area IS '客户需求区域表';
COMMENT ON COLUMN t_yq_ati_cus_need_area.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_cus_need_area.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_cus_need_area.cus_code IS '客户编码';
COMMENT ON COLUMN t_yq_ati_cus_need_area.cus_area_code IS '客户需求编号';

COMMENT ON COLUMN t_yq_ati_cus_need_area.province_code IS '省编号';
COMMENT ON COLUMN t_yq_ati_cus_need_area.city_code IS '城市编号';
COMMENT ON COLUMN t_yq_ati_cus_need_area.region_code IS '区编号';
COMMENT ON COLUMN t_yq_ati_cus_need_area.street_code IS '街道编号';

COMMENT ON COLUMN t_yq_ati_cus_need_area.province_name IS '需求省份';
COMMENT ON COLUMN t_yq_ati_cus_need_area.city_name IS '需求城市';
COMMENT ON COLUMN t_yq_ati_cus_need_area.region_name IS '需求区';
COMMENT ON COLUMN t_yq_ati_cus_need_area.street_name IS '需求街道';
COMMENT ON COLUMN t_yq_ati_cus_need_area.create_code IS '创建者';
COMMENT ON COLUMN t_yq_ati_cus_need_area.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_ati_cus_need_area.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_ati_cus_need_area.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_ati_cus_need_area.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_ati_cus_need_area.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_ati_cus_need_area.remark IS '备注';
COMMENT ON COLUMN t_yq_ati_cus_need_area.is_deleted IS '是否删除';
-----------------------客户需求区域表 end-----------------------