-----------------********************* 大房东租金表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_exp_invpark_contract_landlord_rent
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE seq_yq_exp_invpark_contract_landlord_rent
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_exp_invpark_contract_landlord_rent;
CREATE TABLE t_yq_exp_invpark_contract_landlord_rent
(
  id                         BIGINT NOT NULL DEFAULT nextval('seq_yq_exp_invpark_contract_landlord_rent' :: REGCLASS),
  rent_code                  CHARACTER VARYING,
  ld_code                    CHARACTER VARYING,
  cost_start_time            TIMESTAMP,
  cost_end_time              TIMESTAMP,
  deadline                   TIMESTAMP,
  pay_status                 CHARACTER VARYING,

  rent_price                 NUMERIC(10, 2),
  rent_tax_price             NUMERIC(10, 2),
  rent_on_invoice            NUMERIC(10, 2),
  rent_real_tax_point        CHARACTER VARYING,
  rent_invoice_tax_point     CHARACTER VARYING,

  property_price             NUMERIC(10, 2),
  property_tax_price         NUMERIC(10, 2),
  property_on_invoice        NUMERIC(10, 2),
  property_real_tax_point    CHARACTER VARYING,
  property_invoice_tax_point CHARACTER VARYING,
  other_price                NUMERIC(10, 2),
  total_price                NUMERIC(10, 2),
  paid_price                 NUMERIC(10, 2),
  discounts_price            NUMERIC(10, 2),

  --基础字段
  cpy_code                   CHARACTER VARYING, --公司编号
  cpy_name                   CHARACTER VARYING, --公司名称
  owner_code                 CHARACTER VARYING, --所属用户编码
  owner_name                 CHARACTER VARYING, --所属用户姓名
  owner_dept_code            CHARACTER VARYING, --数据所属部门
  owner_dept_name            CHARACTER VARYING, --数据所属部门名称
  create_code                CHARACTER VARYING, --创建者
  create_name                CHARACTER VARYING, --创建者名称
  create_dept_code           CHARACTER VARYING, --创建者部门编号
  create_dept_name           CHARACTER VARYING, --创建者部门名称
  last_update_code           CHARACTER VARYING, --最后更新者
  last_update_name           CHARACTER VARYING, --最后更新者名称
  create_time                TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time           TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  is_deleted                 BOOLEAN         DEFAULT FALSE, --是否作废或者删除
  remark                     CHARACTER VARYING, --备注

  CONSTRAINT pk_yq_exp_invpark_contract_landlord_rent PRIMARY KEY (id)
)
WITH (OIDS = FALSE
);

ALTER TABLE t_yq_exp_invpark_contract_landlord_rent
  OWNER TO park;

COMMENT ON TABLE t_yq_exp_invpark_contract_landlord_rent IS '大房东租金应收列表';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.rent_code IS '费用缴费编号';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.ld_code IS '大房东合同编号';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.cost_start_time IS '缴费开始日期';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.cost_end_time IS '缴费结束日期';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.deadline IS '缴费截止日期';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.pay_status IS '租金状态';

COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.rent_price IS '本期租金';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.rent_tax_price IS '租金应付税额';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.rent_on_invoice IS '租金开票金额';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.rent_real_tax_point IS '租金实付税率';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.rent_invoice_tax_point IS '租金票面税率';

COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.property_price IS '本期物业费';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.property_tax_price IS '物业应付税额';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.property_on_invoice IS '物业开票金额';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.property_real_tax_point IS '物业实付税率';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.property_invoice_tax_point IS '物业票面税率';

COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.other_price IS '其他应付';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.total_price IS '总应收金额';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.paid_price IS '总已收金额';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.discounts_price IS '优惠金额';


COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.create_code IS '创建者';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.create_dept_code IS '创建者部门code';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.is_deleted IS '是否删除';
COMMENT ON COLUMN t_yq_exp_invpark_contract_landlord_rent.remark IS '备注';
-----------------********************* 大房东租金表  end     **************************----------------------
