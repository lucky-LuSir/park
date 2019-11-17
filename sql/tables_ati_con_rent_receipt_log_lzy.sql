

-----------------********************* 合同:租金应收列表日志  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_rent_receipt_log
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_rent_receipt_log
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_rent_receipt_log;
CREATE TABLE t_yq_ati_con_rent_receipt_log
(

  id                          BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_rent_receipt_log'::regclass),
  rent_code                   CHARACTER VARYING,
  ip                          CHARACTER VARYING,
  method                      CHARACTER VARYING,

  -- 基础字段,
cpy_code              CHARACTER VARYING,
cpy_name              CHARACTER VARYING,
create_code           CHARACTER VARYING,
create_name           CHARACTER VARYING,
last_update_code    CHARACTER VARYING,
last_update_name     CHARACTER VARYING,
create_time         TIMESTAMP WITHOUT TIME ZONE,
last_update_time   TIMESTAMP WITHOUT TIME ZONE,
is_deleted           BOOLEAN DEFAULT FALSE,

  CONSTRAINT pk_yq_ati_con_rent_receipt_log PRIMARY KEY (id)

)
WITH (OIDS=FALSE)
;
ALTER TABLE t_yq_ati_con_rent_receipt_log
  OWNER TO park;

COMMENT ON TABLE t_yq_ati_con_rent_receipt_log IS '租金应收列表日志';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_log.rent_code IS '当期租金编号';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_log.ip IS 'ip';
COMMENT ON COLUMN t_yq_ati_con_rent_receipt_log.method IS '方法';

-----------------********************* 租金应收列表日志  end     **************************----------------------
