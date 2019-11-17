------------------------------------- 任务指标 -------------------------------------
CREATE SEQUENCE seq_yq_task_attract_target
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_task_attract_target
  OWNER TO park;

CREATE TABLE t_yq_task_attract_target
(
  id                              BIGINT NOT NULL DEFAULT nextval('seq_yq_task_attract_target' :: REGCLASS),
  target_code                     CHARACTER VARYING,
  emp_code                        CHARACTER VARYING,
  emp_name                        CHARACTER VARYING,
  pk_code                         CHARACTER VARYING,
  pk_name                         CHARACTER VARYING,
  target_date                     TIMESTAMP WITHOUT TIME ZONE,
  leaflets_target                 NUMERIC(10, 2),
  banner_target                   NUMERIC(10, 2),
  friends_share_target            NUMERIC(10, 2),
  other_channel_target            NUMERIC(10, 2),
  customer_phone_followup_target  NUMERIC(10, 2),
  customer_visity_followup_target NUMERIC(10, 2),
  sign_area_target                NUMERIC(10, 2),
  customer_count_target            NUMERIC(10, 2),

  --基础字段
  cpy_code                        CHARACTER VARYING, --公司编号
  cpy_name                        CHARACTER VARYING, --公司名称
  owner_code                      CHARACTER VARYING, --所属用户编码
  owner_name                      CHARACTER VARYING, --所属用户姓名
  owner_dept_code                 CHARACTER VARYING, --数据所属部门
  owner_dept_name                 CHARACTER VARYING, --数据所属部门名称
  create_code                     CHARACTER VARYING, --创建者
  create_name                     CHARACTER VARYING, --创建者名称
  create_dept_code                CHARACTER VARYING, --创建者部门编号
  create_dept_name                CHARACTER VARYING, --创建者部门名称
  last_update_code                CHARACTER VARYING, --最后更新者
  last_update_name                CHARACTER VARYING, --最后更新者名称
  create_time                     TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time                TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  is_deleted                      BOOLEAN         DEFAULT FALSE, --是否作废或者删除
  remark                          CHARACTER VARYING, --备注
  CONSTRAINT pk_t_yq_task_attract_target PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

CREATE UNIQUE INDEX task_attract_target_code
  ON t_yq_task_attract_target (target_code);
CREATE INDEX task_attract_target_emp_code
  ON t_yq_task_attract_target (emp_code);
CREATE INDEX task_attract_target_cpy_code
  ON t_yq_task_attract_target (cpy_code);

ALTER TABLE t_yq_task_attract_target
  OWNER TO park;

COMMENT ON TABLE t_yq_task_attract_target IS '招商指标表';

COMMENT ON COLUMN t_yq_task_attract_target.target_code IS '任务编号';
COMMENT ON COLUMN t_yq_task_attract_target.emp_code IS '人员编号';
COMMENT ON COLUMN t_yq_task_attract_target.emp_name IS '姓名';
COMMENT ON COLUMN t_yq_task_attract_target.pk_code IS '园区编码';
COMMENT ON COLUMN t_yq_task_attract_target.pk_name IS '园区名称';
COMMENT ON COLUMN t_yq_task_attract_target.target_date IS '指标日期';
COMMENT ON COLUMN t_yq_task_attract_target.leaflets_target IS '宣传单/小广告指标';
COMMENT ON COLUMN t_yq_task_attract_target.banner_target IS '横幅指标';
COMMENT ON COLUMN t_yq_task_attract_target.friends_share_target IS '朋友圈指标';
COMMENT ON COLUMN t_yq_task_attract_target.other_channel_target IS '管委会/中介拜访指标';
COMMENT ON COLUMN t_yq_task_attract_target.customer_phone_followup_target IS '客户电话跟进指标';
COMMENT ON COLUMN t_yq_task_attract_target.customer_visity_followup_target IS '客户拜访指标';
COMMENT ON COLUMN t_yq_task_attract_target.sign_area_target IS '去化面积指标';
COMMENT ON COLUMN t_yq_task_attract_target.customer_count_target IS '员工获客指标';
