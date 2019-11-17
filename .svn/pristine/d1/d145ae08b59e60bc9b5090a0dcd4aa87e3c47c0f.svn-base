---------------------------- 保险费应收列表 ----------------------------

CREATE SEQUENCE seq_yq_ati_con_rent_premium
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

ALTER TABLE seq_yq_ati_con_rent_premium
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_rent_premium;

CREATE TABLE t_yq_ati_con_rent_premium
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_rent_receipt_pay' :: REGCLASS),
  premium_code           CHARACTER VARYING, --保险缴费编号
  ct_code                CHARACTER VARYING, --合同编号
  premium_start_date     TIMESTAMP, --保险费开始日期
  premium_end_date       TIMESTAMP, --保险费结束日期
  deadline               TIMESTAMP, --截至日期
  premium_amount         NUMERIC(10, 2), --投保金额
  receivable_premium     NUMERIC(10, 2), --应收保险费
  paid_price           NUMERIC(10, 2), --已收保险费
  the_payment            NUMERIC(10, 2), --本次缴费
  has_tax                BOOLEAN         DEFAULT FALSE, --是否含税
  tax_point              CHARACTER VARYING, --发票税点
  tax_price              NUMERIC(10, 2), --税金
  total_price            NUMERIC(10, 2), --合计应收金额
  receiving_channel      CHARACTER VARYING, --收款渠道
  receiving_channel_code CHARACTER VARYING, --收款渠道单号
  receiving_date         TIMESTAMP, --收款时间
  pay_date               TIMESTAMP, --支付时间
  pay_status             CHARACTER VARYING, --支付状态

  -- 基础字段,
  cpy_code               CHARACTER VARYING,
  cpy_name               CHARACTER VARYING,
  owner_code             CHARACTER VARYING,
  owner_name             CHARACTER VARYING,
  owner_dept_code        CHARACTER VARYING,
  owner_dept_name        CHARACTER VARYING,
  create_code            CHARACTER VARYING,
  create_name            CHARACTER VARYING,
  create_dept_code       CHARACTER VARYING,
  create_dept_name       CHARACTER VARYING,
  last_update_code       CHARACTER VARYING,
  last_update_name       CHARACTER VARYING,
  create_time            TIMESTAMP WITHOUT TIME ZONE,
  last_update_time       TIMESTAMP WITHOUT TIME ZONE,
  is_deleted             BOOLEAN         DEFAULT FALSE,
  remark                 CHARACTER VARYING,

  CONSTRAINT pk_yq_ati_con_rent_premium PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

ALTER TABLE t_yq_ati_con_rent_premium
  OWNER TO park;

COMMENT ON TABLE t_yq_ati_con_rent_premium IS '合同：保险费应收列表';

COMMENT ON COLUMN t_yq_ati_con_rent_premium.premium_code IS '保险缴费编号';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.premium_start_date IS '保险费开始日期';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.premium_end_date IS '保险费结束日期';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.deadline IS '截至日期';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.premium_amount IS '投保金额';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.receivable_premium IS '应收保险费';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.paid_price IS '已收保险费';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.the_payment IS '本次缴费';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.has_tax IS '是否含税';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.tax_point IS '发票税点';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.tax_price IS '税金';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.total_price IS '合计应收金额';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.receiving_channel IS '收款渠道';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.receiving_channel_code IS '收款渠道单号';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.receiving_date IS '收款时间';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.pay_date IS '支付时间';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.pay_status IS '租金状态';

COMMENT ON COLUMN t_yq_ati_con_rent_premium.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.create_code IS '创建者';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.create_dept_code IS '创建者部门code';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.is_deleted IS '是否删除';
COMMENT ON COLUMN t_yq_ati_con_rent_premium.remark IS '备注';

---------------------------- 保险费应收列表 ----------------------------
