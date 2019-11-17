-----------------********************* 园区统计列表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_hos_bi_parkbi
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_hos_bi_parkbi
  OWNER TO park;

CREATE TABLE t_yq_pro_hos_bi_parkbi
(

  id                          BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_hos_bi_parkbi'::regclass),
  pk_code                     CHARACTER VARYING,
  pk_name                     CHARACTER VARYING,
  address                     CHARACTER VARYING,
  intent_cus_count             int,
  cus_count                   int,
  ct_warning_count            int,
  rent_warning_count          int,
  building_total              NUMERIC(10, 2),
  use_elec_surplus            NUMERIC(10, 2),
  meter_reading               CHARACTER VARYING,
  overdue_count               NUMERIC(10, 2),
  area_rate                   NUMERIC(10, 2),
  rent_invest_area           NUMERIC(10, 4),
  rentable_area               NUMERIC(10, 2),
  area_prop                   NUMERIC(10, 4),
  month_revenue               NUMERIC(10, 2),
  expenditure                 NUMERIC(10, 2),
  profit_growth               NUMERIC(10, 2),
  meter_data                  CHARACTER VARYING,
  total_cost                  NUMERIC(15, 2),
  province_name              CHARACTER VARYING,
  city_name                  CHARACTER VARYING,
  region_name                CHARACTER VARYING,
  street_name                CHARACTER VARYING,
  territory_type             CHARACTER VARYING,
  territory_type_name        CHARACTER VARYING,

  cpy_code                    CHARACTER VARYING,
  cpy_name                    CHARACTER VARYING,
  create_code                 CHARACTER VARYING,
  create_name                 CHARACTER VARYING,
  last_update_code            CHARACTER VARYING,
  last_update_name            CHARACTER VARYING,
  create_time                 TIMESTAMP WITHOUT TIME ZONE,
  last_update_time            TIMESTAMP WITHOUT TIME ZONE,
  remark                      CHARACTER VARYING,
  is_deleted                  BOOLEAN DEFAULT FALSE,


  CONSTRAINT pk_yq_pro_hos_bi_parkbi PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;
CREATE UNIQUE INDEX bi_pk_code ON t_yq_pro_hos_bi_parkbi (pk_code);
CREATE INDEX bi_cpy_code ON t_yq_pro_hos_bi_parkbi(cpy_code);

ALTER TABLE t_yq_pro_hos_bi_parkbi
  OWNER TO park;


COMMENT ON TABLE t_yq_pro_hos_bi_parkbi IS '园区统计列表';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.pk_code IS '园区编号';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.pk_name IS '园区名';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.address IS '园区地址';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.intent_cus_count IS '意向客户数';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.cus_count IS '入住客户总数';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.ct_warning_count IS '合同到期预警数';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.rent_warning_count IS '15天内应缴租金户';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.building_total IS '楼栋总数';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.use_elec_surplus IS '剩余电量';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.meter_reading IS '抄表日期';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.overdue_count IS '费用逾期数';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.area_rate IS '公摊比';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.rent_invest_area IS '出租含公摊面积';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.rentable_area IS '当前空置面积';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.area_prop IS '空置比例';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.month_revenue IS '本月营收';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.expenditure IS '费用支出';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.profit_growth IS '利润增长';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.meter_data IS '每月抄表日期';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.total_cost IS '15日内应收房租和物业费';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.province_name IS '省';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.city_name IS '市';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.region_name IS '区';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.street_name IS '街道';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.territory_type IS '所在大区(部门编号)';
COMMENT ON COLUMN t_yq_pro_hos_bi_parkbi.territory_type_name IS '所在大区名称';


-----------------********************* 园区统计列表  end     **************************----------------------