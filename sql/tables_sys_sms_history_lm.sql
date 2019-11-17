--创建序列
CREATE SEQUENCE seq_yq_sys_sms_history
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE seq_yq_sys_sms_history
  OWNER TO park;

--创建表
CREATE TABLE t_yq_sys_sms_history (
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_sys_sms_history' :: REGCLASS),
  send_code        CHARACTER VARYING, --短信发送code
  sms_code         CHARACTER VARYING, --短信模板code
  template_name    CHARACTER VARYING, --短信模板名称
  template_type    CHARACTER VARYING, --短信模板类型

  content          CHARACTER VARYING, --短信内容
  phone_num        CHARACTER VARYING, --接收短信的手机号码
  receive_date     CHARACTER VARYING, --短信接收日期和时间
  send_date        CHARACTER VARYING, --短信发送日期和时间
  template_code    CHARACTER VARYING, --短信模板ID
  sign_same        CHARACTER VARYING, --短信模板签名

  success          BOOLEAN         DEFAULT FALSE, --是否接受成功
  err_code         CHARACTER VARYING, --状态报告编码
  err_msg          CHARACTER VARYING, --状态报告说明
  sms_size         CHARACTER VARYING, --短信长度
  biz_id           CHARACTER VARYING, --发送序列号


  --基础字段
  cpy_code         CHARACTER VARYING, --公司编号
  cpy_name         CHARACTER VARYING, --公司名称
  owner_code       CHARACTER VARYING, --所属用户编码
  owner_name       CHARACTER VARYING, --所属用户姓名
  owner_dept_code  CHARACTER VARYING, --数据所属部门
  owner_dept_name  CHARACTER VARYING, --数据所属部门名称
  create_code      CHARACTER VARYING, --创建者
  create_name      CHARACTER VARYING, --创建者名称
  create_dept_code CHARACTER VARYING, --创建者部门编号
  create_dept_name CHARACTER VARYING, --创建者部门名称
  last_update_code CHARACTER VARYING, --最后更新者
  last_update_name CHARACTER VARYING, --最后更新者名称
  create_time      TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  is_deleted       BOOLEAN         DEFAULT FALSE, --是否作废或者删除
  remark           CHARACTER VARYING, --备注
  CONSTRAINT pk_yq_sys_sms_history PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX send_code
  ON t_yq_sys_sms_history (send_code);

ALTER TABLE t_yq_sys_sms_history
  OWNER TO park;

--注释
COMMENT ON TABLE t_yq_sys_sms_history IS '短信发送历史模板表';

COMMENT ON COLUMN t_yq_sys_sms_history.send_code IS '短信发送code';
COMMENT ON COLUMN t_yq_sys_sms_history.sms_code IS '短信模板code';
COMMENT ON COLUMN t_yq_sys_sms_history.template_name IS '短信模板名称';
COMMENT ON COLUMN t_yq_sys_sms_history.template_type IS '短信模板类型';

COMMENT ON COLUMN t_yq_sys_sms_history.content IS '短信内容';
COMMENT ON COLUMN t_yq_sys_sms_history.phone_num IS '接收短信的手机号码';
COMMENT ON COLUMN t_yq_sys_sms_history.receive_date IS '短信接收日期和时间';
COMMENT ON COLUMN t_yq_sys_sms_history.send_date IS '短信发送日期和时间';
COMMENT ON COLUMN t_yq_sys_sms_history.template_code IS '短信模板ID';
COMMENT ON COLUMN t_yq_sys_sms_history.sign_same IS '短信模板签名';

COMMENT ON COLUMN t_yq_sys_sms_history.success IS '是否接受成功';
COMMENT ON COLUMN t_yq_sys_sms_history.err_code IS '状态报告编码';
COMMENT ON COLUMN t_yq_sys_sms_history.err_msg IS '状态报告说明';
COMMENT ON COLUMN t_yq_sys_sms_history.sms_size IS '短信长度';
COMMENT ON COLUMN t_yq_sys_sms_history.biz_id IS '发送序列号';

COMMENT ON COLUMN t_yq_sys_sms_history.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_sys_sms_history.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_sys_sms_history.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_yq_sys_sms_history.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_yq_sys_sms_history.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_yq_sys_sms_history.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_yq_sys_sms_history.create_code IS '创建者';
COMMENT ON COLUMN t_yq_sys_sms_history.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_sys_sms_history.create_dept_code IS '创建者部门code';
COMMENT ON COLUMN t_yq_sys_sms_history.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_yq_sys_sms_history.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_sys_sms_history.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_sys_sms_history.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_sys_sms_history.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_sys_sms_history.is_deleted IS '是否删除';
COMMENT ON COLUMN t_yq_sys_sms_history.remark IS '备注';