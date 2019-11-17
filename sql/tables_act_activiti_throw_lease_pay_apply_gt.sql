---------------------------------------- 退租流程审批表【开始】 ----------------------------------------
--创建序列
CREATE SEQUENCE seq_yq_act_activiti_throw_lease_pay_apply
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE seq_yq_act_activiti_throw_lease_pay_apply
  OWNER TO park;

--创建表
CREATE TABLE t_yq_act_activiti_throw_lease_pay_apply (
  id                           BIGINT NOT NULL DEFAULT nextval('seq_yq_act_activiti_throw_lease_pay_apply' :: REGCLASS),
  apply_code                   CHARACTER VARYING, --退款申请编码
  throw_lease_apply_code       CHARACTER VARYING, --退租申请编码
  ct_code                      CHARACTER VARYING, --合同编号
  ct_name                      CHARACTER VARYING, --合同编号
  pd_id                        CHARACTER VARYING, --流程定义ID
  process_instance_id          CHARACTER VARYING, --流程实体ID
  pay_money                    CHARACTER VARYING, --付款金额
  appoint_pay_time             TIMESTAMP WITHOUT TIME ZONE, --约定付款日期
  is_contract                  CHARACTER VARYING, --是否签订合同或协议
  is_invoice                   CHARACTER VARYING, --是否有发票
  invoice_in_time              TIMESTAMP WITHOUT TIME ZONE, --到票时间
  pay_company                  CHARACTER VARYING, --付款方
  payee                        CHARACTER VARYING, -- 收款方
  approval_status               CHARACTER VARYING, -- 审批状态
  payment_apply_remark         CHARACTER VARYING, --付款申请原因
  out_picture                  CHARACTER VARYING, --附件（图片）
  reject_reason                CHARACTER VARYING, --驳回原因

  --基础字段
  cpy_code                     CHARACTER VARYING, --公司编号
  cpy_name                     CHARACTER VARYING, --公司名称
  owner_code                   CHARACTER VARYING, --所属用户编码
  owner_name                   CHARACTER VARYING, --所属用户姓名
  owner_dept_code              CHARACTER VARYING, --数据所属部门
  owner_dept_name              CHARACTER VARYING, --数据所属部门名称
  create_code                  CHARACTER VARYING, --创建者
  create_name                  CHARACTER VARYING, --创建者名称
  create_dept_code             CHARACTER VARYING, --创建者部门编号
  create_dept_name             CHARACTER VARYING, --创建者部门名称
  last_update_code             CHARACTER VARYING, --最后更新者
  last_update_name             CHARACTER VARYING, --最后更新者名称
  create_time                  TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time             TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  is_deleted                   BOOLEAN         DEFAULT FALSE, --是否作废或者删除
  remark                       CHARACTER VARYING, --备注
  CONSTRAINT pk_yq_act_activiti_throw_lease_pay_apply PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX pay_apply_code
  ON t_yq_act_activiti_throw_lease_pay_apply (apply_code);

ALTER TABLE t_yq_act_activiti_throw_lease_pay_apply
  OWNER TO park;

--注释
COMMENT ON TABLE t_yq_act_activiti_throw_lease_pay_apply IS '退租付款申请表';

COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.apply_code IS '退款审批编码';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.throw_lease_apply_code IS '退租申请编码';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.ct_code IS '合同编码';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.ct_name IS '合同编码';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.pd_id IS '流程定义ID';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.process_instance_id IS '流程实体ID';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.pay_money IS '付款金额';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.appoint_pay_time IS '约定到款日期';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.is_contract IS '是否有合同或协议 1是 0否';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.is_invoice IS '是否有发票 1是 0否';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.invoice_in_time IS '到票日期';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.pay_company IS '付款方';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.payee IS '收款方';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.approval_status IS '审批状态';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.payment_apply_remark IS '付款申请原因';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.out_picture IS '附件(图片)';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.reject_reason IS '驳回原因';

COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.create_code IS '创建者';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.create_dept_code IS '创建者部门code';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.is_deleted IS '是否删除';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_pay_apply.remark IS '备注';
---------------------------------------- 退租流程审批表【结束】 ----------------------------------------