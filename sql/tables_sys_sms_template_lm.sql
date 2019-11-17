--创建序列
CREATE SEQUENCE seq_yq_sys_sms_template
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE seq_yq_sys_sms_template
  OWNER TO park;

--创建表
CREATE TABLE t_yq_sys_sms_template (
  id                 BIGINT NOT NULL DEFAULT nextval('seq_yq_sys_sms_template' :: REGCLASS),
  sms_code           CHARACTER VARYING, --短信模板code
  template_id        CHARACTER VARYING, --短信模板ID
  template_sign_same CHARACTER VARYING, --短信模板签名
  template_name      CHARACTER VARYING, --短信模板名称
  template_type      CHARACTER VARYING, --短信模板类型
  template_content   CHARACTER VARYING, --短信模板内容

  --基础字段
  cpy_code           CHARACTER VARYING, --公司编号
  cpy_name           CHARACTER VARYING, --公司名称
  owner_code         CHARACTER VARYING, --所属用户编码
  owner_name         CHARACTER VARYING, --所属用户姓名
  owner_dept_code    CHARACTER VARYING, --数据所属部门
  owner_dept_name    CHARACTER VARYING, --数据所属部门名称
  create_code        CHARACTER VARYING, --创建者
  create_name        CHARACTER VARYING, --创建者名称
  create_dept_code   CHARACTER VARYING, --创建者部门编号
  create_dept_name   CHARACTER VARYING, --创建者部门名称
  last_update_code   CHARACTER VARYING, --最后更新者
  last_update_name   CHARACTER VARYING, --最后更新者名称
  create_time        TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time   TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  is_deleted         BOOLEAN         DEFAULT FALSE, --是否作废或者删除
  remark             CHARACTER VARYING, --备注
  CONSTRAINT pk_yq_sys_sms_template PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX sms_code
  ON t_yq_sys_sms_template (sms_code);

ALTER TABLE t_yq_sys_sms_template
  OWNER TO park;

--注释
COMMENT ON TABLE t_yq_sys_sms_template IS '短信模板表';

COMMENT ON COLUMN t_yq_sys_sms_template.sms_code IS '短信模板code';
COMMENT ON COLUMN t_yq_sys_sms_template.template_sign_same IS '短信模板签名';
COMMENT ON COLUMN t_yq_sys_sms_template.template_id IS '短信模板ID';
COMMENT ON COLUMN t_yq_sys_sms_template.template_name IS '短信模板名称';
COMMENT ON COLUMN t_yq_sys_sms_template.template_type IS '短信模板类型';
COMMENT ON COLUMN t_yq_sys_sms_template.template_content IS '短信模板内容';

COMMENT ON COLUMN t_yq_sys_sms_template.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_sys_sms_template.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_sys_sms_template.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_yq_sys_sms_template.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_yq_sys_sms_template.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_yq_sys_sms_template.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_yq_sys_sms_template.create_code IS '创建者';
COMMENT ON COLUMN t_yq_sys_sms_template.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_sys_sms_template.create_dept_code IS '创建者部门code';
COMMENT ON COLUMN t_yq_sys_sms_template.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_yq_sys_sms_template.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_sys_sms_template.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_sys_sms_template.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_sys_sms_template.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_sys_sms_template.is_deleted IS '是否删除';
COMMENT ON COLUMN t_yq_sys_sms_template.remark IS '备注';