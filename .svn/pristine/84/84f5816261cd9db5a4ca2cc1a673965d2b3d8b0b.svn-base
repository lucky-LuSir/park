-----------------********************* 合同:租金递增列表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_rent_increment
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_rent_increment
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_rent_increment;
CREATE TABLE t_yq_ati_con_rent_increment
(
  id                          BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_rent_increment'::regclass),
  inc_code                   CHARACTER VARYING,
  ct_code                    CHARACTER VARYING,
  inc_start_time            TIMESTAMP,
  inc_end_time               TIMESTAMP,
  rent_total                 NUMERIC(10, 2),
  avg_unit_price            NUMERIC(10, 2),
  rent_invest_area          NUMERIC(10, 2),
  rent_increase_proportion     NUMERIC(10, 2),

  -- 基础字段,
  cpy_code              CHARACTER VARYING,
  cpy_name              CHARACTER VARYING,
  create_code           CHARACTER VARYING,
  create_name           CHARACTER VARYING,
  create_dept_code     CHARACTER VARYING,
  last_update_code     CHARACTER VARYING,
  last_update_name     CHARACTER VARYING,
  create_time          TIMESTAMP WITHOUT TIME ZONE,
  last_update_time    TIMESTAMP WITHOUT TIME ZONE,
  is_deleted           BOOLEAN DEFAULT FALSE,

  CONSTRAINT pk_yq_ati_con_rent_increment PRIMARY KEY (id)

)
WITH (OIDS=FALSE)
;
CREATE UNIQUE INDEX inc_code ON t_yq_ati_con_rent_increment (inc_code);
CREATE INDEX inc_ct_code ON t_yq_ati_con_rent_increment (ct_code);

ALTER TABLE t_yq_ati_con_rent_increment
  OWNER TO park;

COMMENT ON TABLE t_yq_ati_con_rent_increment IS '租金递增列表';
COMMENT ON COLUMN t_yq_ati_con_rent_increment.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_con_rent_increment.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_rent_increment.inc_code IS '租金递增编号';
COMMENT ON COLUMN t_yq_ati_con_rent_increment.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_rent_increment.inc_start_time IS '递增开始时间';
COMMENT ON COLUMN t_yq_ati_con_rent_increment.inc_end_time IS '递增结束时间';
COMMENT ON COLUMN t_yq_ati_con_rent_increment.rent_total IS '每月租金(本合同所有房屋总和)';
COMMENT ON COLUMN t_yq_ati_con_rent_increment.avg_unit_price IS '单价(元/平米/月)';
COMMENT ON COLUMN t_yq_ati_con_rent_increment.rent_invest_area IS '出租含公摊比面积';
COMMENT ON COLUMN t_yq_ati_con_rent_increment.rent_increase_proportion IS '递增比';



-----------------********************* 租金递增列表  end     **************************----------------------