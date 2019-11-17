-------------------------------- 部门管理表 --------------------------------
CREATE SEQUENCE seq_yq_hrm_org_dept
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_hrm_org_dept
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_hrm_org_dept;
CREATE TABLE t_yq_hrm_org_dept
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_hrm_org_dept' :: REGCLASS),

  dept_code        CHARACTER VARYING,
  dept_name        CHARACTER VARYING,
  parent_dept_code CHARACTER VARYING,
  parent_dept_name CHARACTER VARYING,
  dept_type        CHARACTER VARYING,
  dept_head_code   CHARACTER VARYING,
  dept_head_name   CHARACTER VARYING,
  province_code    CHARACTER VARYING,
  province_name    CHARACTER VARYING,
  city_code        CHARACTER VARYING,
  city_name        CHARACTER VARYING,
  region_code      CHARACTER VARYING,
  region_name      CHARACTER VARYING,
  street_code      CHARACTER VARYING,
  street_name      CHARACTER VARYING,
  dept_phone       CHARACTER VARYING,
  dept_head_phone  CHARACTER VARYING,
  dept_synopsis    CHARACTER VARYING,
  address          CHARACTER VARYING,
  has_use_query    BOOLEAN,

  -- 基础字段,
  cpy_code         CHARACTER VARYING,
  cpy_name         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_name      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  last_update_name CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  remark           CHARACTER VARYING,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_hrm_org_dept PRIMARY KEY (id)
)
WITH (OIDS = FALSE
);

CREATE UNIQUE INDEX dept_code
  ON t_yq_hrm_org_dept (dept_code);
CREATE INDEX parent_dept_code
  ON t_yq_hrm_org_dept (parent_dept_code);
CREATE INDEX dept_head_code
  ON t_yq_hrm_org_dept (dept_head_code);
CREATE INDEX dept_cpy_code
  ON t_yq_hrm_org_dept (cpy_code);

ALTER TABLE t_yq_hrm_org_dept
  OWNER TO park;
COMMENT ON TABLE t_yq_hrm_org_dept IS '部门管理表';
COMMENT ON COLUMN t_yq_hrm_org_dept.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_hrm_org_dept.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_hrm_org_dept.dept_code IS '部门编号';
COMMENT ON COLUMN t_yq_hrm_org_dept.dept_name IS '部门名称';
COMMENT ON COLUMN t_yq_hrm_org_dept.parent_dept_code IS '上级部门编码';
COMMENT ON COLUMN t_yq_hrm_org_dept.parent_dept_name IS '上级部门名称';
COMMENT ON COLUMN t_yq_hrm_org_dept.dept_type IS '部门类型';
COMMENT ON COLUMN t_yq_hrm_org_dept.dept_head_code IS '部门负责人编码';
COMMENT ON COLUMN t_yq_hrm_org_dept.dept_head_name IS '部门负责人名称';
COMMENT ON COLUMN t_yq_hrm_org_dept.province_code IS '省编码';
COMMENT ON COLUMN t_yq_hrm_org_dept.province_name IS '省名称';
COMMENT ON COLUMN t_yq_hrm_org_dept.city_code IS '市编码';
COMMENT ON COLUMN t_yq_hrm_org_dept.city_name IS '市名称';
COMMENT ON COLUMN t_yq_hrm_org_dept.region_code IS '区编码';
COMMENT ON COLUMN t_yq_hrm_org_dept.region_name IS '区名称';
COMMENT ON COLUMN t_yq_hrm_org_dept.street_code IS '街道编码';
COMMENT ON COLUMN t_yq_hrm_org_dept.street_name IS '街道名称';
COMMENT ON COLUMN t_yq_hrm_org_dept.dept_phone IS '部门联系电话';
COMMENT ON COLUMN t_yq_hrm_org_dept.dept_head_phone IS '部门领导人电话';
COMMENT ON COLUMN t_yq_hrm_org_dept.dept_synopsis IS '部门介绍';
COMMENT ON COLUMN t_yq_hrm_org_dept.address IS '详细地址';
COMMENT ON COLUMN t_yq_hrm_org_dept.has_use_query IS '是否启用查询';
