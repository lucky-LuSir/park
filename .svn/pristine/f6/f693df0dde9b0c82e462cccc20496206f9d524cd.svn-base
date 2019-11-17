-----------------********************* 客户上下架表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_cus_updown
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_cus_updown
  OWNER TO park;

CREATE TABLE t_yq_ati_cus_updown
(

  id                          BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_cus_updown'::regclass),
  cus_code                   CHARACTER VARYING,
  cus_down_type             CHARACTER VARYING,
  cus_updown_type           CHARACTER VARYING,
  cus_down_reason           CHARACTER VARYING,
  province_code             CHARACTER VARYING,
  province_name             CHARACTER VARYING,
  city_code                 CHARACTER VARYING,
  city_name                 CHARACTER VARYING,
  region_code               CHARACTER VARYING,
  region_name               CHARACTER VARYING,
  deal_people               CHARACTER VARYING,
  emp_code                   CHARACTER VARYING,
  emp_name                   CHARACTER VARYING,
  cus_emp_code               CHARACTER VARYING,
  cus_emp_name               CHARACTER VARYING,
  contract_start_time       TIMESTAMP WITHOUT TIME ZONE,
  contract_end_time         TIMESTAMP WITHOUT TIME ZONE,

  cpy_code                    CHARACTER VARYING,
  cpy_name                    CHARACTER VARYING,
  create_code                 CHARACTER VARYING,
  create_name                 CHARACTER VARYING,
  last_update_code            CHARACTER VARYING,
  last_update_name            CHARACTER VARYING,
  create_time                 TIMESTAMP WITHOUT TIME ZONE,
  last_update_time            TIMESTAMP WITHOUT TIME ZONE,
  is_deleted                  BOOLEAN DEFAULT FALSE,

  CONSTRAINT pk_yq_ati_cus_updown PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;
CREATE INDEX updown_cus_code ON t_yq_ati_cus_updown (cus_code);
CREATE INDEX updown_cpy_code ON t_yq_ati_cus_updown (cpy_code);

ALTER TABLE t_yq_ati_cus_updown
  OWNER TO park;

COMMENT ON TABLE t_yq_ati_cus_updown IS '客户上下架表';
COMMENT ON COLUMN t_yq_ati_cus_updown.cus_code IS '客户编码';
COMMENT ON COLUMN t_yq_ati_cus_updown.cus_down_type IS '下架类型';
COMMENT ON COLUMN t_yq_ati_cus_updown.cus_updown_type IS '类型（上架,下架）';
COMMENT ON COLUMN t_yq_ati_cus_updown.cus_down_reason IS '下架原因';
COMMENT ON COLUMN t_yq_ati_cus_updown.province_code IS '省编码';
COMMENT ON COLUMN t_yq_ati_cus_updown.province_name IS '省';
COMMENT ON COLUMN t_yq_ati_cus_updown.city_code IS '市编码';
COMMENT ON COLUMN t_yq_ati_cus_updown.city_name IS '市';
COMMENT ON COLUMN t_yq_ati_cus_updown.region_code IS '区编码';
COMMENT ON COLUMN t_yq_ati_cus_updown.region_name IS '区';
COMMENT ON COLUMN t_yq_ati_cus_updown.deal_people IS '被谁成交';
COMMENT ON COLUMN t_yq_ati_cus_updown.emp_code IS '上下架员工编号';
COMMENT ON COLUMN t_yq_ati_cus_updown.emp_name IS '上下架员工姓名';
COMMENT ON COLUMN t_yq_ati_cus_updown.cus_emp_code IS '客户所属人编号';
COMMENT ON COLUMN t_yq_ati_cus_updown.cus_emp_name IS '客户所属人姓名';
COMMENT ON COLUMN t_yq_ati_cus_updown.contract_start_time IS '合同签订日期';
COMMENT ON COLUMN t_yq_ati_cus_updown.contract_end_time IS '合同结束日期';

-----------------********************* 客户上下架表  end     **************************----------------------