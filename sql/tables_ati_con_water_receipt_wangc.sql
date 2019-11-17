-----------------********************* 合同:水费应收列表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_water_receipt
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_water_receipt
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_water_receipt;
CREATE TABLE t_yq_ati_con_water_receipt
(

  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_water_receipt' :: REGCLASS),
  water_code             CHARACTER VARYING,
  ct_code                CHARACTER VARYING,
  cus_code               CHARACTER VARYING,
  company_name           CHARACTER VARYING,
  last_billing_time      TIMESTAMP,
  current_billing_time   TIMESTAMP,
  receivable_price       NUMERIC(10, 2),
  real_price             NUMERIC(10, 2),
  paid_price             NUMERIC(10, 2),
  receiving_channel      CHARACTER VARYING,
  receiving_channel_code CHARACTER VARYING,
  receiving_date         TIMESTAMP,
  pay_status             CHARACTER VARYING,
  pay_date               TIMESTAMP,
  has_tax                BOOLEAN,
  tax_point              CHARACTER VARYING,
  tax_price              NUMERIC(10, 2),

  -- 基础字段,
  cpy_code               CHARACTER VARYING,
  cpy_name               CHARACTER VARYING,
  create_code            CHARACTER VARYING,
  create_name            CHARACTER VARYING,
  last_update_code       CHARACTER VARYING,
  last_update_name       CHARACTER VARYING,
  create_dept_code       CHARACTER VARYING, --创建部门code
  create_dept_name       CHARACTER VARYING, --创建部门名称
  create_time            TIMESTAMP WITHOUT TIME ZONE,
  last_update_time       TIMESTAMP WITHOUT TIME ZONE,
  remark                 CHARACTER VARYING,
  is_deleted             BOOLEAN         DEFAULT FALSE,

  CONSTRAINT pk_yq_ati_con_water_receipt PRIMARY KEY (id)

)
WITH (OIDS = FALSE
);
CREATE UNIQUE INDEX water_code
  ON t_yq_ati_con_water_receipt (water_code);
CREATE INDEX wr_ct_code
  ON t_yq_ati_con_water_receipt (ct_code);
CREATE INDEX wr_cus_code
  ON t_yq_ati_con_water_receipt (cus_code);
CREATE INDEX wr_receiving_channel_code
  ON t_yq_ati_con_water_receipt (receiving_channel_code);
CREATE INDEX wr_cpy_code
  ON t_yq_ati_con_water_receipt (cpy_code);

ALTER TABLE t_yq_ati_con_water_receipt
  OWNER TO park;


COMMENT ON TABLE t_yq_ati_con_water_receipt IS '水费应收列表';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.water_code IS '水费应收列表编号';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.cus_code IS '客户编号';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.company_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.last_billing_time IS '上一次抄表时间';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.current_billing_time IS '本次抄表时间';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.receivable_price IS '应收水费 实用量*水费单价';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.real_price IS '实收水费';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.paid_price IS '已收款(共缴)';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.receiving_channel IS '收款渠道 ';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.receiving_channel_code IS '收款渠道单号';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.receiving_date IS '最后收款时间';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.pay_status IS '状态';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.pay_date IS '支付时间';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.remark IS '备注';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.has_tax IS '是否含税';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.tax_point IS '发票税点';
COMMENT ON COLUMN t_yq_ati_con_water_receipt.tax_price IS '税金';

-----------------********************* 水费应收列表  end     **************************----------------------

-----------------********************* 合同:水表信息表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_water_meter
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_water_meter
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_water_meter;
CREATE TABLE t_yq_ati_con_water_meter
(

  id                 BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_water_meter' :: REGCLASS),
  water_meter_code   CHARACTER VARYING,
  ct_code            CHARACTER VARYING,
  water_meter_name   CHARACTER VARYING,
  water_billing_type CHARACTER VARYING,
  water_unit_price   NUMERIC(10, 2),
  init_water         NUMERIC(10, 2),
  water_amount       NUMERIC(10, 2),

  -- 基础字段,
  cpy_code           CHARACTER VARYING,
  cpy_name           CHARACTER VARYING,
  create_code        CHARACTER VARYING,
  create_name        CHARACTER VARYING,
  create_dept_code   CHARACTER VARYING,
  create_dept_name   CHARACTER VARYING,
  last_update_code   CHARACTER VARYING,
  last_update_name   CHARACTER VARYING,
  create_time        TIMESTAMP WITHOUT TIME ZONE,
  last_update_time   TIMESTAMP WITHOUT TIME ZONE,
  remark             CHARACTER VARYING,
  is_deleted         BOOLEAN         DEFAULT FALSE,

  CONSTRAINT pk_yq_ati_con_water_meter PRIMARY KEY (id)

)
WITH (OIDS = FALSE
);
CREATE UNIQUE INDEX water_meter_code
  ON t_yq_ati_con_water_meter (water_meter_code);
CREATE INDEX water_meter_ct_code
  ON t_yq_ati_con_water_meter (ct_code);

ALTER TABLE t_yq_ati_con_water_meter
  OWNER TO park;


COMMENT ON TABLE t_yq_ati_con_water_meter IS '水表信息表';
COMMENT ON COLUMN t_yq_ati_con_water_meter.water_meter_code IS '水表编号';
COMMENT ON COLUMN t_yq_ati_con_water_meter.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_water_meter.water_meter_name IS '水表名称';
COMMENT ON COLUMN t_yq_ati_con_water_meter.water_billing_type IS '计费方式';
COMMENT ON COLUMN t_yq_ati_con_water_meter.water_unit_price IS '水费单价（抄表计费）';
COMMENT ON COLUMN t_yq_ati_con_water_meter.init_water IS '初始电表（抄表计费）';
COMMENT ON COLUMN t_yq_ati_con_water_meter.water_amount IS '水费金额（均摊计费）';

-----------------********************* 合同:水表信息表  end     **************************----------------------


-----------------********************* 合同:水表抄表记录表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_water_record
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_water_record
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_water_record;
CREATE TABLE t_yq_ati_con_water_record
(

  id                        BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_water_record' :: REGCLASS),
  water_record_code         CHARACTER VARYING,
  water_code                CHARACTER VARYING,
  water_meter_code          CHARACTER VARYING,
  water_meter_name          CHARACTER VARYING,
  ct_code                   CHARACTER VARYING,
  last_meter_record_date    TIMESTAMP WITHOUT TIME ZONE,
  current_meter_record_date TIMESTAMP WITHOUT TIME ZONE,

  water_billing_type        CHARACTER VARYING,
  water_amount              NUMERIC(10, 2),
  last_billing_water        NUMERIC(10, 2),
  current_billing_water     NUMERIC(10, 2),
  water_unit_price          NUMERIC(10, 2),
  real_water                NUMERIC(10, 2),
  record_month              CHARACTER VARYING,
  has_bill                  BOOLEAN         DEFAULT FALSE,


  -- 基础字段,
  cpy_code                  CHARACTER VARYING,
  cpy_name                  CHARACTER VARYING,
  create_code               CHARACTER VARYING,
  create_name               CHARACTER VARYING,
  create_dept_code          CHARACTER VARYING,
  create_dept_name          CHARACTER VARYING,
  last_update_code          CHARACTER VARYING,
  last_update_name          CHARACTER VARYING,
  create_time               TIMESTAMP WITHOUT TIME ZONE,
  last_update_time          TIMESTAMP WITHOUT TIME ZONE,
  remark                    CHARACTER VARYING,
  is_deleted                BOOLEAN         DEFAULT FALSE,

  CONSTRAINT pk_yq_ati_con_water_record PRIMARY KEY (id)

)
WITH (OIDS = FALSE
);
CREATE UNIQUE INDEX water_record_code
  ON t_yq_ati_con_water_record (water_record_code);
CREATE INDEX wr_record_water_code
  ON t_yq_ati_con_water_record (water_code);
CREATE INDEX wr_record_meter_code
  ON t_yq_ati_con_water_record (water_meter_code);
CREATE INDEX wr_record_ct_code
  ON t_yq_ati_con_water_record (ct_code);

ALTER TABLE t_yq_ati_con_water_record
  OWNER TO park;

COMMENT ON TABLE t_yq_ati_con_water_record IS '水表抄表记录表';
COMMENT ON COLUMN t_yq_ati_con_water_record.water_record_code IS '水表抄表编号';
COMMENT ON COLUMN t_yq_ati_con_water_record.water_code IS '抄表费用编号';
COMMENT ON COLUMN t_yq_ati_con_water_record.water_meter_code IS '水表编号';
COMMENT ON COLUMN t_yq_ati_con_water_record.water_meter_name IS '水名称';
COMMENT ON COLUMN t_yq_ati_con_water_record.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_water_record.last_meter_record_date IS '上次抄表时间';
COMMENT ON COLUMN t_yq_ati_con_water_record.current_meter_record_date IS '本次抄表时间';
COMMENT ON COLUMN t_yq_ati_con_water_record.water_billing_type IS '计费方式';
COMMENT ON COLUMN t_yq_ati_con_water_record.water_amount IS '水费金额（均摊计费）';
COMMENT ON COLUMN t_yq_ati_con_water_record.last_billing_water IS '上一次抄表吨位（抄表计费）';
COMMENT ON COLUMN t_yq_ati_con_water_record.current_billing_water IS '本次抄表吨位（抄表计费）';
COMMENT ON COLUMN t_yq_ati_con_water_record.water_unit_price IS '水费单价（抄表计费）';
COMMENT ON COLUMN t_yq_ati_con_water_record.real_water IS '实用量 单位为吨（抄表计费）';
COMMENT ON COLUMN t_yq_ati_con_water_record.record_month IS '抄表月份';
COMMENT ON COLUMN t_yq_ati_con_water_record.has_bill IS '是否生成账单';

-----------------********************* 合同:水表抄表记录表  end     **************************----------------------