

-----------------********************* 合同:租金应收列表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_rent_receipt
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_rent_receipt
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_rent_receipt;
CREATE TABLE t_yq_ati_con_rent_receipt
(

  id                          BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_rent_receipt'::regclass),
  rent_code                   CHARACTER VARYING,
  ct_code                     CHARACTER VARYING,
  pk_code                     CHARACTER VARYING,
  rent_start_time             TIMESTAMP,
  rent_end_time               TIMESTAMP,
  total_price                 NUMERIC(10, 2),
  auto_rent                   NUMERIC(10, 2),
  receivable_rent             NUMERIC(10, 2),
  real_rent                   NUMERIC(10, 2),
  paid_price                  NUMERIC(10, 2),
  receiving_channel           CHARACTER VARYING,
  receiving_channel_code      CHARACTER VARYING,
  pay_date                     TIMESTAMP,
  receiving_date              TIMESTAMP,
  pay_status                  CHARACTER VARYING,
  has_tax                     BOOLEAN,
  tax_point                   CHARACTER VARYING,
  tax_price                   NUMERIC(10, 2),
  property_price             NUMERIC(10, 2),
  pro_has_tax                 BOOLEAN,
  pro_tax_point               CHARACTER VARYING,
  overdue_days                int,
  late_payment               NUMERIC(10, 2),
  discounts_price               NUMERIC(10, 2),

  -- 基础字段,
cpy_code              CHARACTER VARYING,
cpy_name              CHARACTER VARYING,
create_code           CHARACTER VARYING,
create_name           CHARACTER VARYING,
last_update_code    CHARACTER VARYING,
last_update_name     CHARACTER VARYING,
create_time         TIMESTAMP WITHOUT TIME ZONE,
last_update_time   TIMESTAMP WITHOUT TIME ZONE,
remark               CHARACTER VARYING,
is_deleted           BOOLEAN DEFAULT FALSE,

  CONSTRAINT pk_yq_ati_con_rent_receipt PRIMARY KEY (id)

)
WITH (OIDS=FALSE)
;
CREATE UNIQUE INDEX rent_code ON t_yq_ati_con_rent_receipt (rent_code);
CREATE INDEX rent_ct_code ON t_yq_ati_con_rent_receipt (ct_code);
CREATE INDEX rent_pk_code ON t_yq_ati_con_rent_receipt (pk_code);
CREATE INDEX rent_receiving_channel_code ON t_yq_ati_con_rent_receipt (receiving_channel_code);
CREATE INDEX rent_cpy_code ON t_yq_ati_con_rent_receipt (cpy_code);

ALTER TABLE t_yq_ati_con_rent_receipt
  OWNER TO park;

COMMENT ON TABLE t_yq_ati_con_rent_receipt IS '租金应收列表';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.rent_code IS '当期租金编号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.pk_code IS '园区编号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.rent_start_time IS '租金开始日期';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.rent_end_time IS '租金结束日期';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.total_price IS '合计应收金额';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.auto_rent IS '自动计算租金';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.receivable_rent IS '应收租金';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.real_rent IS '实收租金';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.paid_price IS '已付款';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.receiving_channel IS '收款渠道';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.receiving_channel_code IS '收款渠道单号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.pay_date IS '支付时间';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.receiving_date IS '收款时间';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.pay_status IS '租金状态';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.remark IS '备注';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.has_tax IS '租金是否含税';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.tax_point IS '租金发票税点';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.tax_price IS '税金';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.property_price IS '物业费';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.pro_has_tax IS '物业费是否含税';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.pro_tax_point IS '物业费发票税点';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.overdue_days IS '逾期天数';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.late_payment IS '滞纳金';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt.discounts_price IS '优惠金额';

-----------------********************* 租金应收列表  end     **************************----------------------

-----------------********************* 合同:租金应收明细列表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_rent_receipt_detail
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_rent_receipt_detail
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_rent_receipt_detail;
CREATE TABLE t_yq_ati_con_rent_receipt_detail
(
  id                          BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_rent_receipt_detail'::regclass),
  rrd_code                   CHARACTER VARYING,
  rent_code                   CHARACTER VARYING,
  rent_hos_code                CHARACTER VARYING,
  rent_area                   NUMERIC(10, 2),
  invest_area                   NUMERIC(10, 2),
  public_office_proportion    CHARACTER VARYING,
  Incbefore_price             NUMERIC(10, 2),
  Incafter_price              NUMERIC(10, 2),
  unit                        CHARACTER VARYING,
  month_price                 NUMERIC(10, 2),
  receivable_rent             NUMERIC(10, 2),

  -- 基础字段,
cpy_code              CHARACTER VARYING,
cpy_name              CHARACTER VARYING,
create_code           CHARACTER VARYING,
create_name           CHARACTER VARYING,
last_update_code    CHARACTER VARYING,
last_update_name     CHARACTER VARYING,
create_time         TIMESTAMP WITHOUT TIME ZONE,
last_update_time   TIMESTAMP WITHOUT TIME ZONE,
remark               CHARACTER VARYING,
is_deleted           BOOLEAN DEFAULT FALSE,

  CONSTRAINT pk_yq_ati_con_rent_receipt_detail PRIMARY KEY (id)

)
WITH (OIDS=FALSE)
;
CREATE UNIQUE INDEX rrd_code ON t_yq_ati_con_rent_receipt_detail (rrd_code);
CREATE INDEX rrd_rent_code ON t_yq_ati_con_rent_receipt_detail (rent_code);
CREATE INDEX rrd_rent_hos_code ON t_yq_ati_con_rent_receipt_detail (rent_hos_code);
CREATE INDEX rrd_cpy_code ON t_yq_ati_con_rent_receipt_detail (cpy_code);

ALTER TABLE t_yq_ati_con_rent_receipt_detail
  OWNER TO park;

COMMENT ON TABLE t_yq_ati_con_rent_receipt_detail IS '物业费应收列表';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.rrd_code IS '租金明细列表编号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.rent_code IS '当期租金编号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.rent_hos_code IS '租房编号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.rent_area IS '出租承租面积';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.invest_area IS '出租面积（含公摊）';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.public_office_proportion IS '公摊比例';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.Incbefore_price IS '递增前租金单价';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.Incafter_price IS '递增后租金单价';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.unit IS '租金单位';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.month_price IS '月租金总额';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_detail.receivable_rent IS '应收租金';



-----------------********************* 租金应收明细列表  end     **************************----------------------