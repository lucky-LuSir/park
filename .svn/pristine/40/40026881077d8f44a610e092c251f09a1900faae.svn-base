---------------------------------------- 大房东合同【开始】 ----------------------------------------
--创建序列
CREATE SEQUENCE seq_yq_exp_invpark_contract
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE seq_yq_exp_invpark_contract
  OWNER TO park;

--创建表
CREATE TABLE t_yq_exp_invpark_contract (
  id                          BIGINT NOT NULL DEFAULT nextval('seq_yq_exp_invpark_contract' :: REGCLASS),
  ld_code                     CHARACTER VARYING, --大房东合同编码（自动生成）
  ld_ct_name                  CHARACTER VARYING, --大房东合同编号（按照既定规则生成）
  process_definition_id       CHARACTER VARYING, --流程定义ID
  process_instance_id         CHARACTER VARYING, --流程实例ID
  ld_ct_status                CHARACTER VARYING, --合同状态
  pk_code                     CHARACTER VARYING, --园区编号
  pk_name                     CHARACTER VARYING, --园区名称
  acreage                     NUMERIC(10, 2), --承租面积（不含公摊，即产证面积）
  landlord_name               CHARACTER VARYING, --房东名字
  landlord_sex                CHARACTER VARYING, --房东性别
  landlord_phone              CHARACTER VARYING, --房东电话
  contractor_code             CHARACTER VARYING, --签约人编码
  contractor                  CHARACTER VARYING, --签约人
  contractor_phone            CHARACTER VARYING, --签约人电话
  contractor_dept_code        CHARACTER VARYING, --签约人部门编号
  contract_start_date         TIMESTAMP WITHOUT TIME ZONE, --合同开始日期
  contract_end_date           TIMESTAMP WITHOUT TIME ZONE, --合同结束日期
  advance_day                 BIGINT, --提前多少天交租
  has_detail_add              BOOLEAN         DEFAULT FALSE, --是否详细递增
  progressive_increase_detail CHARACTER VARYING, --递增详情
  rent_increasing_cycle       BIGINT, --递增周期（每几年递增一次）
  rent_increase_proportion    NUMERIC(10, 2), --租金递增比例（每次递增百分之多少）
  rent_univalence             NUMERIC(10, 2), --租金单价
  rent_unit                   CHARACTER VARYING, --租金单位
  deposit_rule                CHARACTER VARYING, --押几
  pay_rule                    CHARACTER VARYING, --付几
  deposit                     NUMERIC(10, 2), --押金
  contract_url                CHARACTER VARYING, --附件

  --基础字段
  cpy_code                    CHARACTER VARYING, --公司编号
  cpy_name                    CHARACTER VARYING, --公司名称
  owner_code                  CHARACTER VARYING, --所属用户编码
  owner_name                  CHARACTER VARYING, --所属用户姓名
  owner_dept_code             CHARACTER VARYING, --数据所属部门
  owner_dept_name             CHARACTER VARYING, --数据所属部门名称
  create_code                 CHARACTER VARYING, --创建者
  create_name                 CHARACTER VARYING, --创建者名称
  create_dept_code            CHARACTER VARYING, --创建者部门编号
  create_dept_name            CHARACTER VARYING, --创建者部门名称
  last_update_code            CHARACTER VARYING, --最后更新者
  last_update_name            CHARACTER VARYING, --最后更新者名称
  create_time                 TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time            TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  is_deleted                  BOOLEAN         DEFAULT FALSE, --是否作废或者删除
  remark                      CHARACTER VARYING, --备注
  CONSTRAINT pk_yq_exp_invpark_contract PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX ld_code
  ON t_yq_exp_invpark_contract (ld_code);

ALTER TABLE t_yq_exp_invpark_contract
  OWNER TO park;

--注释
COMMENT ON TABLE t_yq_exp_invpark_contract IS '大房东合同表';

COMMENT ON COLUMN t_yq_exp_invpark_contract.ld_code IS '大房东合同编码（自动生成）';
COMMENT ON COLUMN t_yq_exp_invpark_contract.ld_ct_name IS '大房东合同编号（按照既定规则生成）';
COMMENT ON COLUMN t_yq_exp_invpark_contract.process_definition_id IS '流程定义ID';
COMMENT ON COLUMN t_yq_exp_invpark_contract.process_instance_id IS '流程实例ID';
COMMENT ON COLUMN t_yq_exp_invpark_contract.ld_ct_status IS '合同状态';
COMMENT ON COLUMN t_yq_exp_invpark_contract.pk_code IS '园区编号';
COMMENT ON COLUMN t_yq_exp_invpark_contract.pk_name IS '园区名称';
COMMENT ON COLUMN t_yq_exp_invpark_contract.acreage IS '承租面积（不含公摊，即产证面积）';
COMMENT ON COLUMN t_yq_exp_invpark_contract.landlord_name IS '房东名字';
COMMENT ON COLUMN t_yq_exp_invpark_contract.landlord_sex IS '房东性别';
COMMENT ON COLUMN t_yq_exp_invpark_contract.landlord_phone IS '房东电话';
COMMENT ON COLUMN t_yq_exp_invpark_contract.contractor_code IS '签约人编码';
COMMENT ON COLUMN t_yq_exp_invpark_contract.contractor IS '签约人';
COMMENT ON COLUMN t_yq_exp_invpark_contract.contractor_phone IS '签约人电话';
COMMENT ON COLUMN t_yq_exp_invpark_contract.contractor_dept_code IS '签约人部门';
COMMENT ON COLUMN t_yq_exp_invpark_contract.contract_start_date IS '合同开始日期';
COMMENT ON COLUMN t_yq_exp_invpark_contract.contract_end_date IS '合同结束日期';
COMMENT ON COLUMN t_yq_exp_invpark_contract.advance_day IS '提前多少天交租';
COMMENT ON COLUMN t_yq_exp_invpark_contract.has_detail_add IS '是否详细递增';
COMMENT ON COLUMN t_yq_exp_invpark_contract.progressive_increase_detail IS '递增详情';
COMMENT ON COLUMN t_yq_exp_invpark_contract.rent_increasing_cycle IS '递增周期（每几年递增一次）';
COMMENT ON COLUMN t_yq_exp_invpark_contract.rent_increase_proportion IS '租金递增比例（每次递增百分之多少）';
COMMENT ON COLUMN t_yq_exp_invpark_contract.rent_univalence IS '租金单价';
COMMENT ON COLUMN t_yq_exp_invpark_contract.rent_unit IS '租金单位';
COMMENT ON COLUMN t_yq_exp_invpark_contract.deposit_rule IS '押几';
COMMENT ON COLUMN t_yq_exp_invpark_contract.pay_rule IS '付几';
COMMENT ON COLUMN t_yq_exp_invpark_contract.deposit IS '押金';
COMMENT ON COLUMN t_yq_exp_invpark_contract.contract_url IS '合同附件';

COMMENT ON COLUMN t_yq_exp_invpark_contract.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_exp_invpark_contract.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_exp_invpark_contract.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_yq_exp_invpark_contract.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_yq_exp_invpark_contract.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_yq_exp_invpark_contract.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_yq_exp_invpark_contract.create_code IS '创建者';
COMMENT ON COLUMN t_yq_exp_invpark_contract.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_exp_invpark_contract.create_dept_code IS '创建者部门code';
COMMENT ON COLUMN t_yq_exp_invpark_contract.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_yq_exp_invpark_contract.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_exp_invpark_contract.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_exp_invpark_contract.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_exp_invpark_contract.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_exp_invpark_contract.is_deleted IS '是否删除';
COMMENT ON COLUMN t_yq_exp_invpark_contract.remark IS '备注';
---------------------------------------- 大房东合同【结束】 ----------------------------------------