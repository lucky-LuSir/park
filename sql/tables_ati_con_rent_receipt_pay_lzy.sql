-----------------********************* 合同:租金应收缴费表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_rent_receipt_pay
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_rent_receipt_pay
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_rent_receipt_pay;
CREATE TABLE t_yq_ati_con_rent_receipt_pay
(

  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_rent_receipt_pay' :: REGCLASS),
  pay_code               CHARACTER VARYING,
  ct_code                CHARACTER VARYING,
  code                   CHARACTER VARYING,
  cost_type              CHARACTER VARYING,
  cost_start_time        TIMESTAMP,
  cost_end_time          TIMESTAMP,
  receivable_rent        NUMERIC(10, 2),
  real_rent              NUMERIC(10, 2),
  paid_price             NUMERIC(10, 2),
  pay_date               TIMESTAMP,
  receiving_channel      CHARACTER VARYING,
  receiving_channel_code CHARACTER VARYING,
  pay_status             CHARACTER VARYING,
  has_tax                BOOLEAN,
  tax_point              CHARACTER VARYING,
  tax_price              NUMERIC(10, 2),
  property_price         NUMERIC(10, 2),
  pro_has_tax            BOOLEAN,
  pro_tax_point          CHARACTER VARYING,

  -- 基础字段,
  cpy_code               CHARACTER VARYING,
  cpy_name               CHARACTER VARYING,
  create_code            CHARACTER VARYING,
  create_name            CHARACTER VARYING,
  last_update_code       CHARACTER VARYING,
  last_update_name       CHARACTER VARYING,
  create_time            TIMESTAMP WITHOUT TIME ZONE,
  last_update_time       TIMESTAMP WITHOUT TIME ZONE,
  remark                 CHARACTER VARYING,
  is_deleted             BOOLEAN         DEFAULT FALSE,

  CONSTRAINT pk_yq_ati_con_rent_receipt_pay PRIMARY KEY (id)

)
WITH (OIDS = FALSE
);
ALTER TABLE t_yq_ati_con_rent_receipt_pay
  OWNER TO park;

COMMENT ON TABLE t_yq_ati_con_rent_receipt_pay IS '租金应收列表';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.code IS '费用缴费编号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.cost_type IS '费用缴费类型(1:租金,2:电费,3:水费,4:停车费)';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.cost_start_time IS '缴费开始日期';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.cost_end_time IS '缴费结束日期';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.receivable_rent IS '应收租金';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.real_rent IS '实收租金';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.pay_date IS '支付时间';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.receiving_channel IS '收款渠道';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.receiving_channel_code IS '收款渠道单号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.pay_status IS '租金状态';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.has_tax IS '租金是否含税';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.tax_point IS '发票税点';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.property_price IS '物业费';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.pro_has_tax IS '物业费是否含税';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_pay.pro_tax_point IS '物业费税点';

-----------------********************* 租金应收缴费表  end     **************************----------------------
