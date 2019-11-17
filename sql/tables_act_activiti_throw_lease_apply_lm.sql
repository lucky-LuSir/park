---------------------------------------- 退租流程审批表【开始】 ----------------------------------------
--创建序列
CREATE SEQUENCE seq_yq_act_activiti_throw_lease_apply
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE seq_yq_act_activiti_throw_lease_apply
  OWNER TO park;

--创建表
CREATE TABLE t_yq_act_activiti_throw_lease_apply (
  id                           BIGINT NOT NULL DEFAULT nextval('seq_yq_act_activiti_throw_lease_apply' :: REGCLASS),
  apply_code                   CHARACTER VARYING, --审批编码
  pd_id                        CHARACTER VARYING, --流程定义ID
  process_instance_id          CHARACTER VARYING, --流程实例ID
  ct_code                      CHARACTER VARYING, --合同编码
  ct_name                      CHARACTER VARYING, --合同编号
  approval_status              CHARACTER VARYING, --审批状态
  throw_lease_type             CHARACTER VARYING, --退租类型
  duty_type                    CHARACTER VARYING, --退租责任类型
  money_type                   CHARACTER VARYING, --金额类型
  money                        NUMERIC(10, 2), --金额

  --甲方
  party_a                      CHARACTER VARYING, --甲方
  party_a_address              CHARACTER VARYING, --甲方地址
  party_a_legal_representative CHARACTER VARYING, --甲方法定代表
  party_a_phone                CHARACTER VARYING, --甲方电话

  --乙方
  party_b                      CHARACTER VARYING, --乙方
  party_b_address              CHARACTER VARYING, --乙方地址
  party_b_legal_representative CHARACTER VARYING, --乙方法定代表
  party_b_phone                CHARACTER VARYING, --乙方电话

  --退租信息
  throw_lease_date             TIMESTAMP WITHOUT TIME ZONE, --退租日期
  leased_area                  CHARACTER VARYING, --租赁区域
  throw_lease_area             NUMERIC(10, 2), --退租面积
  monthly_rent                 NUMERIC(10, 2), --月租金
  refund_bank                  CHARACTER VARYING, --退款银行
  refund_account_name          CHARACTER VARYING, --退款账号姓名
  refund_account_number        CHARACTER VARYING, --退款银行账号
  throw_lease_remark           CHARACTER VARYING, --退租备注说明

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
  CONSTRAINT pk_yq_act_activiti_throw_lease_apply PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX apply_code
  ON t_yq_act_activiti_throw_lease_apply (apply_code);

ALTER TABLE t_yq_act_activiti_throw_lease_apply
  OWNER TO park;

--注释
COMMENT ON TABLE t_yq_act_activiti_throw_lease_apply IS '退租流程审批表';

COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.apply_code IS '审批编码';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.pd_id IS '流程定义ID';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.process_instance_id IS '流程实例ID';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.ct_code IS '合同编码';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.ct_name IS '合同编号';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.approval_status IS '审批状态';

COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.throw_lease_type IS '退租类型';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.duty_type IS '退租责任类型';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.money_type IS '金额类型';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.money IS '金额';

COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.party_a IS '甲方';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.party_a_address IS '甲方地址';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.party_a_legal_representative IS '甲方法定代表';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.party_a_phone IS '甲方电话';

COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.party_b IS '乙方';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.party_b_address IS '乙方地址';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.party_b_legal_representative IS '乙方法定代表';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.party_b_phone IS '乙方电话';

COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.throw_lease_date IS '退租日期';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.leased_area IS '租赁区域';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.throw_lease_area IS '退租面积';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.monthly_rent IS '月租金';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.refund_bank IS '退款银行';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.refund_account_name IS '退款账号姓名';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.refund_account_number IS '退款银行账号';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.throw_lease_remark IS '退租备注说明';

COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.create_code IS '创建者';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.create_dept_code IS '创建者部门code';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.is_deleted IS '是否删除';
COMMENT ON COLUMN t_yq_act_activiti_throw_lease_apply.remark IS '备注';
---------------------------------------- 退租流程审批表【结束】 ----------------------------------------