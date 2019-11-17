-----------------********************* 合同:房源表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_contract_house
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_contract_house
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_contract_house;
CREATE TABLE t_yq_ati_con_contract_house
(
  id                       BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_contract_house' :: REGCLASS),

  ct_code                  CHARACTER VARYING,
  rent_hos_code            CHARACTER VARYING,
  pk_code                  CHARACTER VARYING,
  pk_name                  CHARACTER VARYING,
  bd_code                  CHARACTER VARYING,
  bd_name                  CHARACTER VARYING,
  fl_code                  CHARACTER VARYING,
  fl_count                 BIGINT,
  room_number              CHARACTER VARYING,
  rent_area                NUMERIC(10, 2),
  invest_area              NUMERIC(10, 2),
  public_office_proportion CHARACTER VARYING,
  unit_price               NUMERIC(10, 2),
  unit                     CHARACTER VARYING,
  monthly_rent             NUMERIC(10, 2),
  property_unit_price      NUMERIC(10, 2),

  -- 基础字段,
  cpy_code                 CHARACTER VARYING,
  cpy_name                 CHARACTER VARYING,
  create_code              CHARACTER VARYING,
  create_name              CHARACTER VARYING,
  last_update_code         CHARACTER VARYING,
  last_update_name         CHARACTER VARYING,
  create_time              TIMESTAMP WITHOUT TIME ZONE,
  last_update_time         TIMESTAMP WITHOUT TIME ZONE,
  remark                   CHARACTER VARYING,
  is_deleted               BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_ati_con_contract_house PRIMARY KEY (id)
)
WITH (OIDS = FALSE
);

CREATE UNIQUE INDEX rent_hos_code
  ON t_yq_ati_con_contract_house (rent_hos_code);
CREATE INDEX con_house_ct_code
  ON t_yq_ati_con_contract_house (ct_code);
CREATE INDEX con_house_cpy_code
  ON t_yq_ati_con_contract_house (cpy_code);

ALTER TABLE t_yq_ati_con_contract_house
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_con_contract_house IS '合同:房源表';
COMMENT ON COLUMN t_yq_ati_con_contract_house.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_con_contract_house.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_contract_house.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_contract_house.rent_hos_code IS '租房编号';
COMMENT ON COLUMN t_yq_ati_con_contract_house.pk_code IS '园区编码';
COMMENT ON COLUMN t_yq_ati_con_contract_house.pk_name IS '园区名称';
COMMENT ON COLUMN t_yq_ati_con_contract_house.bd_code IS '建筑编码';
COMMENT ON COLUMN t_yq_ati_con_contract_house.bd_name IS '建筑名称';
COMMENT ON COLUMN t_yq_ati_con_contract_house.fl_code IS '楼层编码';
COMMENT ON COLUMN t_yq_ati_con_contract_house.fl_count IS '楼层数';
COMMENT ON COLUMN t_yq_ati_con_contract_house.room_number IS '房间号';
COMMENT ON COLUMN t_yq_ati_con_contract_house.rent_area IS '出租承租面积';
COMMENT ON COLUMN t_yq_ati_con_contract_house.invest_area IS '出租招商面积（含公摊）';
COMMENT ON COLUMN t_yq_ati_con_contract_house.public_office_proportion IS '公摊比例';
COMMENT ON COLUMN t_yq_ati_con_contract_house.unit_price IS '租金单价';
COMMENT ON COLUMN t_yq_ati_con_contract_house.unit IS '租金单位';
COMMENT ON COLUMN t_yq_ati_con_contract_house.monthly_rent IS '月租金总额';
COMMENT ON COLUMN t_yq_ati_con_contract_house.property_unit_price IS '物业费单价';

-----------------********************* 合同:房源表  end     **************************----------------------

-----------------********************* 合同:电费应收表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_electricity_receipt
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_electricity_receipt
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_electricity_receipt;
CREATE TABLE t_yq_ati_con_electricity_receipt
(
  id                              BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_electricity_receipt' :: REGCLASS),

  ele_code                        CHARACTER VARYING,
  ct_code                         CHARACTER VARYING,
  cus_code                        CHARACTER VARYING,
  company_name                    CHARACTER VARYING,
  last_billing_time               TIMESTAMP WITHOUT TIME ZONE,
  current_billing_time            TIMESTAMP WITHOUT TIME ZONE,
  basic_electricity_price_bymonth NUMERIC(10, 2),
  electricity_price               NUMERIC(10, 2),
  receivable_price                NUMERIC(10, 2),
  real_price                      NUMERIC(10, 2),
  paid_price                      NUMERIC(10, 2),
  receiving_channel               CHARACTER VARYING,
  receiving_channel_code          CHARACTER VARYING,
  receiving_date                  TIMESTAMP WITHOUT TIME ZONE,
  pay_date                        TIMESTAMP WITHOUT TIME ZONE,
  pay_status                      CHARACTER VARYING,
  has_tax                         BOOLEAN,
  tax_point                       CHARACTER VARYING,
  tax_price                       NUMERIC(10, 2),

  -- 基础字段,
  cpy_code                        CHARACTER VARYING,
  cpy_name                        CHARACTER VARYING,
  create_code                     CHARACTER VARYING,
  create_name                     CHARACTER VARYING,
  create_dept_code                CHARACTER VARYING,
  create_dept_name                CHARACTER VARYING,
  last_update_code                CHARACTER VARYING,
  last_update_name                CHARACTER VARYING,
  create_time                     TIMESTAMP WITHOUT TIME ZONE,
  last_update_time                TIMESTAMP WITHOUT TIME ZONE,
  remark                          CHARACTER VARYING,
  is_deleted                      BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_ati_con_electricity_receipt PRIMARY KEY (id)
)
WITH (OIDS = FALSE
);

CREATE UNIQUE INDEX ele_code
  ON t_yq_ati_con_electricity_receipt (ele_code);
CREATE INDEX ele_ct_code
  ON t_yq_ati_con_electricity_receipt (ct_code);
CREATE INDEX ele_cus_code
  ON t_yq_ati_con_electricity_receipt (cus_code);
CREATE INDEX ele_cpy_code
  ON t_yq_ati_con_electricity_receipt (cpy_code);

ALTER TABLE t_yq_ati_con_electricity_receipt
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_con_electricity_receipt IS '合同:电费应收表';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.ele_code IS '电费缴收编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.cus_code IS '客户编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.company_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.last_billing_time IS '上次结算时间';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.current_billing_time IS '本次结算时间';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.basic_electricity_price_bymonth IS '每月基础电费(基础电费单价*总用电量)';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.electricity_price IS '用电费用';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.receivable_price IS '应收电费';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.real_price IS '实收电费';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.paid_price IS '已缴费（一共）';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.receiving_channel IS '收款渠道';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.receiving_channel_code IS '收款渠道单号';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.pay_date IS '支付时间';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.receiving_date IS '最后收款时间';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.pay_status IS '物业费状态';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.has_tax IS '是否含税';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.tax_point IS '发票税点';
COMMENT ON COLUMN t_yq_ati_con_electricity_receipt.tax_price IS '税金';

-----------------********************* 合同:电费应收表  end     **************************----------------------

-----------------********************* 合同:电表记录表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_electricity_record
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_electricity_record
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_electricity_record;
CREATE TABLE t_yq_ati_con_electricity_record
(
  id                         BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_electricity_record' :: REGCLASS),

  ele_record_code            CHARACTER VARYING,
  ele_meter_code             CHARACTER VARYING,
  ct_code                    CHARACTER VARYING,
  ele_code                   CHARACTER VARYING,
  ele_meter_name             CHARACTER VARYING,
  electricity_billing_type   CHARACTER VARYING,
  last_meter_record_date     TIMESTAMP WITHOUT TIME ZONE,
  current_meter_record_date  TIMESTAMP WITHOUT TIME ZONE,
  last_ele_average_degree    NUMERIC(10, 2),
  current_ele_average_degree NUMERIC(10, 2),
  last_peak_degree           NUMERIC(10, 2),
  current_peak_degree        NUMERIC(10, 2),
  last_trough_degree         NUMERIC(10, 2),
  current_trough_degree      NUMERIC(10, 2),
  last_flat_degree           NUMERIC(10, 2),
  current_flat_degree        NUMERIC(10, 2),
  peak_degree                NUMERIC(10, 2),
  trough_degree              NUMERIC(10, 2),
  flat_degree                NUMERIC(10, 2),
  real_degree                NUMERIC(10, 2),
  record_month               CHARACTER VARYING,
  has_bill                   BOOLEAN         DEFAULT FALSE,

  -- 基础字段,
  cpy_code                   CHARACTER VARYING,
  cpy_name                   CHARACTER VARYING,
  create_code                CHARACTER VARYING,
  create_name                CHARACTER VARYING,
  create_dept_code           CHARACTER VARYING,
  create_dept_name           CHARACTER VARYING,
  last_update_code           CHARACTER VARYING,
  last_update_name           CHARACTER VARYING,
  create_time                TIMESTAMP WITHOUT TIME ZONE,
  last_update_time           TIMESTAMP WITHOUT TIME ZONE,
  remark                     CHARACTER VARYING,
  is_deleted                 BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_ati_con_electricity_record PRIMARY KEY (id)
)
WITH (OIDS = FALSE
);

CREATE UNIQUE INDEX record_ele_record_code
  ON t_yq_ati_con_electricity_record (ele_record_code);
CREATE INDEX record_ele_code
  ON t_yq_ati_con_electricity_record (ele_code);
CREATE INDEX record_ct_code
  ON t_yq_ati_con_electricity_record (ct_code);
CREATE INDEX record_ele_meter_code
  ON t_yq_ati_con_electricity_record (ele_meter_code);
CREATE INDEX record_ele_cpy_code
  ON t_yq_ati_con_electricity_record (cpy_code);

ALTER TABLE t_yq_ati_con_electricity_record
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_con_electricity_record IS '合同:电表记录表';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.ele_record_code IS '抄表记录编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.ele_meter_code IS '电表编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.ele_code IS '电费缴收编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.ele_meter_name IS '电表名称';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.electricity_billing_type IS '计费方式';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.last_meter_record_date IS '上次抄表时间';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.current_meter_record_date IS '本次抄表时间';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.last_ele_average_degree IS '上次均价电表度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.current_ele_average_degree IS '本次均价电表度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.last_peak_degree IS '上次峰值抄表度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.current_peak_degree IS '本次峰值抄表度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.last_trough_degree IS '上次谷值抄表度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.current_trough_degree IS '本次谷值抄表度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.last_flat_degree IS '上次平值抄表度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.current_flat_degree IS '本次平值抄表度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.peak_degree IS '高峰实用度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.trough_degree IS '低峰实用度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.flat_degree IS '平峰实用度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.real_degree IS '实用度数';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.record_month IS '抄表月份';
COMMENT ON COLUMN t_yq_ati_con_electricity_record.has_bill IS '是否生成账单';

-----------------********************* 合同:电表记录表  end     **************************----------------------

-----------------********************* 合同:电表信息表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_electricity_meter
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_electricity_meter
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_electricity_meter;
CREATE TABLE t_yq_ati_con_electricity_meter
(
  id                        BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_electricity_meter' :: REGCLASS),

  ele_meter_code            CHARACTER VARYING,
  ct_code                   CHARACTER VARYING,
  ele_code                  CHARACTER VARYING,
  ele_meter_name            CHARACTER VARYING,
  multiple                  NUMERIC(10, 2),
  electricity_billing_type  CHARACTER VARYING,
  init_electricity          NUMERIC(10, 2),
  electricity_average_price NUMERIC(10, 2),
  electricity_peak          NUMERIC(10, 2),
  electricity_flat          NUMERIC(10, 2),
  electricity_trough        NUMERIC(10, 2),
  electricity_peak_price    NUMERIC(10, 2),
  electricity_flat_price    NUMERIC(10, 2),
  electricity_trough_price  NUMERIC(10, 2),
  ele_is_open                 CHARACTER VARYING,

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
  CONSTRAINT pk_yq_ati_con_electricity_meter PRIMARY KEY (id)
)
WITH (OIDS = FALSE
);

CREATE UNIQUE INDEX meter_ele_meter_code
  ON t_yq_ati_con_electricity_meter (ele_meter_code);
CREATE INDEX meter_ele_code
  ON t_yq_ati_con_electricity_meter (ele_code);
CREATE INDEX meter_ct_code
  ON t_yq_ati_con_electricity_meter (ct_code);
CREATE INDEX meter_cpy_code
  ON t_yq_ati_con_electricity_meter (cpy_code);

ALTER TABLE t_yq_ati_con_electricity_meter
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_con_electricity_meter IS '合同:电表信息表';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.ele_meter_code IS '电表编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.ele_code IS '电费缴收编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.ele_meter_name IS '电表名称';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.multiple IS '倍率';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.electricity_billing_type IS '计费方式';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.init_electricity IS '均价初始电表';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.electricity_average_price IS '均价单价';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.electricity_peak IS '初始峰值';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.electricity_flat IS '初始平值';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.electricity_trough IS '初始谷值';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.electricity_peak_price IS '电费波峰单价';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.electricity_flat_price IS '电费平谷单价';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.electricity_trough_price IS '电费波谷单价';
COMMENT ON COLUMN t_yq_ati_con_electricity_meter.ele_is_open IS '是否启用';

-----------------********************* 合同:电表信息表  end     **************************----------------------

-----------------********************* 合同:停车费应收表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_parking_receipt
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_parking_receipt
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_parking_receipt;
CREATE TABLE t_yq_ati_con_parking_receipt
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_parking_receipt' :: REGCLASS),

  parking_code           CHARACTER VARYING,
  ct_code                CHARACTER VARYING,
  cus_code               CHARACTER VARYING,
  company_name           CHARACTER VARYING,
  start_time             TIMESTAMP WITHOUT TIME ZONE,
  end_time               TIMESTAMP WITHOUT TIME ZONE,
  unit_price             NUMERIC(10, 2),
  parking_space          BIGINT,
  receivable_price       NUMERIC(10, 2),
  real_price             NUMERIC(10, 2),
  paid_price             NUMERIC(10, 2),
  receiving_channel      CHARACTER VARYING,
  receiving_channel_code CHARACTER VARYING,
  receiving_date         TIMESTAMP WITHOUT TIME ZONE,
  pay_status             CHARACTER VARYING,
  parking_remark         CHARACTER VARYING,
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
  create_time            TIMESTAMP WITHOUT TIME ZONE,
  last_update_time       TIMESTAMP WITHOUT TIME ZONE,
  remark                 CHARACTER VARYING,
  is_deleted             BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_ati_con_parking_receipt PRIMARY KEY (id)
)
WITH (OIDS = FALSE
);

CREATE UNIQUE INDEX parking_code
  ON t_yq_ati_con_parking_receipt (parking_code);
CREATE INDEX con_parking_ct_code
  ON t_yq_ati_con_parking_receipt (ct_code);
CREATE INDEX con_parking_cpy_code
  ON t_yq_ati_con_parking_receipt (cpy_code);

ALTER TABLE t_yq_ati_con_parking_receipt
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_con_parking_receipt IS '合同:停车费应收表';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.parking_code IS '停车费编号';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.cus_code IS '客户编号';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.company_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.start_time IS '计费开始日期';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.end_time IS '计费结束日期';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.unit_price IS '停车位单价';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.parking_space IS '停车位个数';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.receivable_price IS '应收停车费';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.real_price IS '实收停车费';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.paid_price IS '已收款(共缴)';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.receiving_channel IS '收款渠道';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.receiving_channel_code IS '收款渠道单号';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.receiving_date IS '最后收款时间';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.pay_status IS '物业费状态';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.parking_remark IS '停车备注';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.has_tax IS '是否含税';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.tax_point IS '发票税点';
COMMENT ON COLUMN t_yq_ati_con_parking_receipt.tax_price IS '税金';

-----------------********************* 合同:停车费应收表  end     **************************----------------------