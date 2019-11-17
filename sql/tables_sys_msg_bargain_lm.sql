--创建序列
CREATE SEQUENCE seq_yq_sys_msg_bargain
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE seq_yq_sys_msg_bargain
  OWNER TO park;

--创建表
CREATE TABLE t_yq_sys_msg_bargain (
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_sys_msg_bargain' :: REGCLASS),
  bm_code          CHARACTER VARYING, --成交消息code
  bargain_date     TIMESTAMP WITHOUT TIME ZONE, --成交日期
  pk_code          CHARACTER VARYING, --园区code
  pk_name          CHARACTER VARYING, --园区name
  emp_code         CHARACTER VARYING, --签单人code
  emp_name         CHARACTER VARYING, --签单人name
  area             NUMERIC(10, 2), --出租面积
  rent             NUMERIC(10, 2), --年租金
  channel          CHARACTER VARYING, --客户来源渠道

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
  CONSTRAINT pk_yq_sys_msg_bargain PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX bm_code
  ON t_yq_sys_msg_bargain (bm_code);

ALTER TABLE t_yq_sys_msg_bargain
  OWNER TO park;

--注释
COMMENT ON TABLE t_yq_sys_msg_bargain IS '园区出租成交通知表';

COMMENT ON COLUMN t_yq_sys_msg_bargain.bm_code IS '成交消息code';
COMMENT ON COLUMN t_yq_sys_msg_bargain.bargain_date IS '成交日期';
COMMENT ON COLUMN t_yq_sys_msg_bargain.pk_code IS '园区code';
COMMENT ON COLUMN t_yq_sys_msg_bargain.pk_name IS '园区name';
COMMENT ON COLUMN t_yq_sys_msg_bargain.emp_code IS '签单人code';
COMMENT ON COLUMN t_yq_sys_msg_bargain.emp_name IS '签单人name';
COMMENT ON COLUMN t_yq_sys_msg_bargain.area IS '出租面积';
COMMENT ON COLUMN t_yq_sys_msg_bargain.rent IS '年租金';
COMMENT ON COLUMN t_yq_sys_msg_bargain.channel IS '客户来源渠道';

COMMENT ON COLUMN t_yq_sys_msg_bargain.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_sys_msg_bargain.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_sys_msg_bargain.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_yq_sys_msg_bargain.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_yq_sys_msg_bargain.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_yq_sys_msg_bargain.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_yq_sys_msg_bargain.create_code IS '创建者';
COMMENT ON COLUMN t_yq_sys_msg_bargain.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_sys_msg_bargain.create_dept_code IS '创建者部门code';
COMMENT ON COLUMN t_yq_sys_msg_bargain.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_yq_sys_msg_bargain.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_sys_msg_bargain.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_sys_msg_bargain.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_sys_msg_bargain.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_sys_msg_bargain.is_deleted IS '是否删除';
COMMENT ON COLUMN t_yq_sys_msg_bargain.remark IS '备注';