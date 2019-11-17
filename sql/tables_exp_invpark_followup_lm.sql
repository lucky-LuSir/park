---------------------------------------- 拓展跟进【开始】 ----------------------------------------
--创建序列
CREATE SEQUENCE seq_yq_exp_invpark_followup
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE seq_yq_exp_invpark_followup
  OWNER TO park;

--创建表
CREATE TABLE t_yq_exp_invpark_followup (
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_exp_invpark_followup' :: REGCLASS),
  followup_code    CHARACTER VARYING, --跟进编码
  item_code        CHARACTER VARYING, --项目编码
  inv_followup_type        CHARACTER VARYING, --跟进类型
  exp_status       CHARACTER VARYING, --跟进状态
  followup_result  CHARACTER VARYING, --跟进结果

  --基础字段
  cpy_code         CHARACTER VARYING, --公司编号
  cpy_name         CHARACTER VARYING, --公司名称
  owner_code        CHARACTER VARYING, --所属用户编码
  owner_name        CHARACTER VARYING, --所属用户姓名
  owner_dept_code        CHARACTER VARYING, --数据所属部门
  owner_dept_name        CHARACTER VARYING, --数据所属部门名称
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
  CONSTRAINT pk_yq_exp_invpark_followup PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX exp_invpark_followup_code
  ON t_yq_exp_invpark_followup (followup_code);

ALTER TABLE t_yq_exp_invpark_followup
  OWNER TO park;

--注释
COMMENT ON TABLE t_yq_exp_invpark_followup IS '拓展信息表';

COMMENT ON COLUMN t_yq_exp_invpark_followup.followup_code IS '跟进编码';
COMMENT ON COLUMN t_yq_exp_invpark_followup.item_code IS '项目编码';
COMMENT ON COLUMN t_yq_exp_invpark_followup.inv_followup_type IS '跟进类型';
COMMENT ON COLUMN t_yq_exp_invpark_followup.exp_status IS '跟进状态';
COMMENT ON COLUMN t_yq_exp_invpark_followup.followup_result IS '跟进结果';

COMMENT ON COLUMN t_yq_exp_invpark_followup.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_exp_invpark_followup.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_exp_invpark_followup.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_yq_exp_invpark_followup.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_yq_exp_invpark_followup.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_yq_exp_invpark_followup.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_yq_exp_invpark_followup.create_code IS '创建者';
COMMENT ON COLUMN t_yq_exp_invpark_followup.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_exp_invpark_followup.create_dept_code IS '创建者部门code';
COMMENT ON COLUMN t_yq_exp_invpark_followup.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_yq_exp_invpark_followup.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_exp_invpark_followup.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_exp_invpark_followup.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_exp_invpark_followup.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_exp_invpark_followup.is_deleted IS '是否删除';
COMMENT ON COLUMN t_yq_exp_invpark_followup.remark IS '备注';
---------------------------------------- 拓展跟进【结束】 ----------------------------------------