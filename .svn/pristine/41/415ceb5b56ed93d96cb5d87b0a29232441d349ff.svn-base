---------------------------------------- 审批表【开始】 ----------------------------------------
--创建序列
CREATE SEQUENCE seq_yq_act_activiti_approval
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE seq_yq_act_activiti_approval
  OWNER TO park;

--创建表
CREATE TABLE t_yq_act_activiti_approval (
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_act_activiti_approval' :: REGCLASS),
  approval_code          CHARACTER VARYING, --审批编码
  apply_code             CHARACTER VARYING, --申请ID
  applicant_code         CHARACTER VARYING, --申请人编码
  approval_status        CHARACTER VARYING, --审批状态
  approve_comment        CHARACTER VARYING, --审批意见
  task_id                CHARACTER VARYING, --任务ID
  execution_id           CHARACTER VARYING, --流程实例ID
  process_instance_id    CHARACTER VARYING, --流程实例ID
  process_definition_id  CHARACTER VARYING, --流程定义ID
  process_definition_key CHARACTER VARYING, --流程定义Key

  --基础字段
  cpy_code               CHARACTER VARYING, --公司编号
  cpy_name               CHARACTER VARYING, --公司名称
  owner_code             CHARACTER VARYING, --所属用户编码
  owner_name             CHARACTER VARYING, --所属用户姓名
  owner_dept_code        CHARACTER VARYING, --数据所属部门
  owner_dept_name        CHARACTER VARYING, --数据所属部门名称
  create_code            CHARACTER VARYING, --创建者
  create_name            CHARACTER VARYING, --创建者名称
  create_dept_code       CHARACTER VARYING, --创建者部门编号
  create_dept_name       CHARACTER VARYING, --创建者部门名称
  last_update_code       CHARACTER VARYING, --最后更新者
  last_update_name       CHARACTER VARYING, --最后更新者名称
  create_time            TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time       TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  is_deleted             BOOLEAN         DEFAULT FALSE, --是否作废或者删除
  remark                 CHARACTER VARYING, --备注
  CONSTRAINT pk_yq_act_activiti_approval PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX approval_code
  ON t_yq_act_activiti_approval (approval_code);

ALTER TABLE t_yq_act_activiti_approval
  OWNER TO park;

--注释
COMMENT ON TABLE t_yq_act_activiti_approval IS '流程审批表';

COMMENT ON COLUMN t_yq_act_activiti_approval.approval_code IS '审批编码';
COMMENT ON COLUMN t_yq_act_activiti_approval.apply_code IS '申请ID';
COMMENT ON COLUMN t_yq_act_activiti_approval.applicant_code IS '申请人编码';
COMMENT ON COLUMN t_yq_act_activiti_approval.approval_status IS '审批状态';
COMMENT ON COLUMN t_yq_act_activiti_approval.approve_comment IS '审批意见';
COMMENT ON COLUMN t_yq_act_activiti_approval.task_id IS '任务ID';
COMMENT ON COLUMN t_yq_act_activiti_approval.execution_id IS '流程实例ID';
COMMENT ON COLUMN t_yq_act_activiti_approval.process_instance_id IS '流程实例ID';
COMMENT ON COLUMN t_yq_act_activiti_approval.process_definition_id IS '流程定义ID';
COMMENT ON COLUMN t_yq_act_activiti_approval.process_definition_key IS '流程定义Key';

COMMENT ON COLUMN t_yq_act_activiti_approval.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_act_activiti_approval.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_act_activiti_approval.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_yq_act_activiti_approval.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_yq_act_activiti_approval.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_yq_act_activiti_approval.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_yq_act_activiti_approval.create_code IS '创建者';
COMMENT ON COLUMN t_yq_act_activiti_approval.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_act_activiti_approval.create_dept_code IS '创建者部门code';
COMMENT ON COLUMN t_yq_act_activiti_approval.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_yq_act_activiti_approval.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_act_activiti_approval.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_act_activiti_approval.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_act_activiti_approval.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_act_activiti_approval.is_deleted IS '是否删除';
COMMENT ON COLUMN t_yq_act_activiti_approval.remark IS '备注';
---------------------------------------- 审批表【结束】 ----------------------------------------