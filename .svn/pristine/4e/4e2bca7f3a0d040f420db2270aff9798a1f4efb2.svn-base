
-----------------********************* 客户跟进管理表  begin     **************************----------------------
-----------------******* update :1、新增cus_name,cus_phone,cus_intent_park 2、followup_code改为普通索引 ****---

CREATE SEQUENCE seq_yq_ati_cus_followup
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_cus_followup
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_cus_followup;
CREATE TABLE t_yq_ati_cus_followup
(
  id                      BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_cus_followup'::regclass),

  followup_code          CHARACTER VARYING,
  pk_code                CHARACTER VARYING,
  pk_name                CHARACTER VARYING,
  cus_code               CHARACTER VARYING,
  cus_name               CHARACTER VARYING,
  cus_phone              CHARACTER VARYING,
  cus_intent_park       CHARACTER VARYING,
  reception_type       CHARACTER VARYING,
  followup_type         CHARACTER VARYING,
  cus_channel           CHARACTER VARYING,
  cus_status             CHARACTER VARYING,
  improper_reason       CHARACTER VARYING,
  improper_reason_note  CHARACTER VARYING,
  followup_res           CHARACTER VARYING,
  followup_time          TIMESTAMP,
  emp_code                CHARACTER VARYING,
  emp_name                CHARACTER VARYING,
  emp_phone               CHARACTER VARYING,
  mid_cpy_code           CHARACTER VARYING,
  mid_cpy_name            CHARACTER VARYING,
  mid_code                CHARACTER VARYING,
  mid_name                CHARACTER VARYING,
  mid_phone               CHARACTER VARYING,
  mid_position           CHARACTER VARYING,
  attachment           CHARACTER VARYING,

  -- 基础字段,
  cpy_code               CHARACTER VARYING,
  cpy_name               CHARACTER VARYING,
  create_code           CHARACTER VARYING,
  create_name           CHARACTER VARYING,
  create_dept_code           CHARACTER VARYING,
  create_dept_name           CHARACTER VARYING,
  last_update_code    CHARACTER VARYING,
  last_update_name     CHARACTER VARYING,
  create_time         TIMESTAMP WITHOUT TIME ZONE,
  last_update_time   TIMESTAMP WITHOUT TIME ZONE,
  remark               CHARACTER VARYING,
  is_deleted           BOOLEAN DEFAULT FALSE,
  CONSTRAINT pk_yq_ati_cus_followup PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE INDEX followup_code ON t_yq_ati_cus_followup (followup_code);
CREATE INDEX followup_cus_code ON t_yq_ati_cus_followup(cus_code);
CREATE INDEX followup_pk_code ON t_yq_ati_cus_followup(pk_code);
CREATE INDEX followup_emp_code ON t_yq_ati_cus_followup(emp_code);
CREATE INDEX followup_mid_cpy_code ON t_yq_ati_cus_followup(mid_cpy_code);
CREATE INDEX followup_cpy_code ON t_yq_ati_cus_followup(cpy_code);

ALTER TABLE t_yq_ati_cus_followup
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_cus_followup IS '客户跟进管理表';
COMMENT ON COLUMN t_yq_ati_cus_followup.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_cus_followup.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_cus_followup.followup_code IS '客户跟进编码';
COMMENT ON COLUMN t_yq_ati_cus_followup.pk_code IS '园区编码';
COMMENT ON COLUMN t_yq_ati_cus_followup.cus_intent_park IS '客户意向房源';
COMMENT ON COLUMN t_yq_ati_cus_followup.cus_code IS '客户编码';
COMMENT ON COLUMN t_yq_ati_cus_followup.cus_name IS '客户姓名';
COMMENT ON COLUMN t_yq_ati_cus_followup.cus_phone IS '客户联系方式';
COMMENT ON COLUMN t_yq_ati_cus_followup.reception_type IS '客户带看类型';--2018-7-25添加该字段
COMMENT ON COLUMN t_yq_ati_cus_followup.followup_type IS '跟进(来访)类型';
COMMENT ON COLUMN t_yq_ati_cus_followup.cus_channel IS '渠道来源';
COMMENT ON COLUMN t_yq_ati_cus_followup.cus_status IS '跟进客户状态';
COMMENT ON COLUMN t_yq_ati_cus_followup.improper_reason IS '不合适原因';
COMMENT ON COLUMN t_yq_ati_cus_followup.improper_reason_note IS '备注不合适原因';
COMMENT ON COLUMN t_yq_ati_cus_followup.followup_res IS '来访结果';
COMMENT ON COLUMN t_yq_ati_cus_followup.followup_time IS '看房时间';
COMMENT ON COLUMN t_yq_ati_cus_followup.emp_code IS '接待人编码';
COMMENT ON COLUMN t_yq_ati_cus_followup.emp_name IS '接待人姓名';
COMMENT ON COLUMN t_yq_ati_cus_followup.emp_phone IS '接待人电话';
COMMENT ON COLUMN t_yq_ati_cus_followup.mid_cpy_code IS '中介公司编码';
COMMENT ON COLUMN t_yq_ati_cus_followup.mid_cpy_name IS '中介公司名称';
COMMENT ON COLUMN t_yq_ati_cus_followup.mid_code IS '中介联系人编码';
COMMENT ON COLUMN t_yq_ati_cus_followup.mid_name IS '中介联系人姓名';
COMMENT ON COLUMN t_yq_ati_cus_followup.mid_phone IS '中介联系人电话';
COMMENT ON COLUMN t_yq_ati_cus_followup.mid_position IS '中介联系人职务';
COMMENT ON COLUMN t_yq_ati_cus_followup.attachment IS '附件';

-----------------********************* 客户跟进管理表  end     **************************----------------------


-----------------********************* 客户意向房源表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_cus_intent_park
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_cus_intent_park
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_cus_intent_park;
CREATE TABLE t_yq_ati_cus_intent_park
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_cus_intent_park'::regclass),

  followup_code        CHARACTER VARYING,
  pk_code               CHARACTER VARYING,
  province_code         CHARACTER VARYING,
  province_name         CHARACTER VARYING,
  city_code              CHARACTER VARYING,
  city_name             CHARACTER VARYING,
  region_code           CHARACTER VARYING,
  region_name           CHARACTER VARYING,
  pk_address            CHARACTER VARYING,
  bd_code                CHARACTER VARYING,
  fl_code               CHARACTER VARYING,
  rentable_area       NUMERIC(10, 2),
  fl_status             CHARACTER VARYING,
  cus_appraisal        CHARACTER VARYING,

  -- 基础字段,
  cpy_code              CHARACTER VARYING,
  cpy_name              CHARACTER VARYING,
  create_code           CHARACTER VARYING,
  create_name           CHARACTER VARYING,
  last_update_code    CHARACTER VARYING,
  last_update_name     CHARACTER VARYING,
  create_time         TIMESTAMP WITHOUT TIME ZONE,
  last_update_time   TIMESTAMP WITHOUT TIME ZONE,
  remark               CHARACTER VARYING,
  is_deleted           BOOLEAN DEFAULT FALSE,
  CONSTRAINT pk_yq_ati_cus_intent_park PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE UNIQUE INDEX intent_followup_code ON t_yq_ati_cus_intent_park (followup_code);
CREATE INDEX intent_pk_code ON t_yq_ati_cus_intent_park(pk_code);
CREATE INDEX intent_cpy_code ON t_yq_ati_cus_intent_park(cpy_code);

ALTER TABLE t_yq_ati_cus_intent_park
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_cus_intent_park IS '客户意向房源表';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.followup_code IS '客户跟进编码';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.pk_code IS '园区编码';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.province_code IS '园区所在省编码';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.province_name IS '园区所在省';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.city_code IS '园区所在市编码';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.city_name IS '园区所在市';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.region_code IS '园区所在区编码';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.region_name IS '园区所在区';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.pk_address IS '园区地址';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.bd_code IS '建筑编码';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.fl_code IS '楼层编码';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.rentable_area IS '可租用面积';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.fl_status IS '楼层状态';
COMMENT ON COLUMN t_yq_ati_cus_intent_park.cus_appraisal IS '客户评价';

-----------------********************* 客户意向房源表  end     **************************----------------------
