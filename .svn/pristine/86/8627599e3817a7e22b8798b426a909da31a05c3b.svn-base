-----------------********************* 中介联系人表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_ati_agent_middleman
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_agent_middleman
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_ati_agent_middleman;
CREATE TABLE t_yq_ati_agent_middleman
(
  id                    BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_agent_middleman'::regclass),
  mid_code              CHARACTER VARYING,
  middleman_type        CHARACTER VARYING,
  middleman_name        CHARACTER VARYING,
  phone                 CHARACTER VARYING,
  telephone             CHARACTER VARYING,

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

  CONSTRAINT pk_yq_ati_agent_middleman PRIMARY KEY (id)

)
WITH (OIDS=FALSE)
;
CREATE UNIQUE INDEX mid_code ON t_yq_ati_agent_middleman (mid_code);
ALTER TABLE t_yq_ati_agent_middleman
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_agent_middleman IS '中介联系人表';
COMMENT ON COLUMN t_yq_ati_agent_middleman.mid_code IS '中介人编号';
COMMENT ON COLUMN t_yq_ati_agent_middleman.middleman_type IS '中介人类型';
COMMENT ON COLUMN t_yq_ati_agent_middleman.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_ati_agent_middleman.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_agent_middleman.middleman_name IS '中介人姓名';
COMMENT ON COLUMN t_yq_ati_agent_middleman.phone IS '手机号';
COMMENT ON COLUMN t_yq_ati_agent_middleman.telephone IS '固定电话';



-----------------********************* 中介联系人表  end     **************************----------------------
