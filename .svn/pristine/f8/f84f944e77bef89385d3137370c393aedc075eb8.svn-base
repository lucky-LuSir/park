-----------------********************* 合同操作记录表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_con_contract_operate_his
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_contract_operate_his
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_con_contract_operate_his;
CREATE TABLE t_yq_ati_con_contract_operate_his
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_contract_operate_his'::regclass),

  ct_code                CHARACTER VARYING,
  ct_status              CHARACTER VARYING,
  operate_remark        CHARACTER VARYING,

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
  CONSTRAINT pk_yq_ati_con_contract_operate_his PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE INDEX con_operatehis_ct_code ON t_yq_ati_con_contract_operate_his (ct_code);

ALTER TABLE t_yq_ati_con_contract_operate_his
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_con_contract_operate_his IS '合同操作记录表';
COMMENT ON COLUMN t_yq_ati_con_contract_operate_his.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_contract_operate_his.ct_status IS '合同状态';
COMMENT ON COLUMN t_yq_ati_con_contract_operate_his.operate_remark IS '操作说明';

-----------------********************* 合同操作记录表  end     **************************----------------------
