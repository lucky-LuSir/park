----用户表----
CREATE SEQUENCE seq_yq_ati_cus_channel_cpy
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_cus_channel_cpy
  OWNER TO park;

CREATE TABLE t_yq_ati_cus_channel_cpy
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_cus_channel_cpy' :: REGCLASS),
  company_code         CHARACTER VARYING,
  channel_cpy_name         CHARACTER VARYING,
  channel_cpy_remark         CHARACTER VARYING,
  channel_cpy_address         CHARACTER VARYING,

  -- 基础字段,
  cpy_code              CHARACTER VARYING,
  cpy_name              CHARACTER VARYING,
  create_code           CHARACTER VARYING,
  create_name           CHARACTER VARYING,
  last_update_code      CHARACTER VARYING,
  last_update_name      CHARACTER VARYING,
  create_time           TIMESTAMP WITHOUT TIME ZONE,
  last_update_time      TIMESTAMP WITHOUT TIME ZONE,
  remark                CHARACTER VARYING,
  is_deleted            BOOLEAN DEFAULT FALSE,
  CONSTRAINT pk_yq_ati_cus_channel_cpy PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_yq_ati_cus_channel_cpy
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_cus_channel_cpy IS '渠道单位表';
COMMENT ON COLUMN t_yq_ati_cus_channel_cpy.company_code IS '单位编码';
COMMENT ON COLUMN t_yq_ati_cus_channel_cpy.channel_cpy_name IS '单位名称';
COMMENT ON COLUMN t_yq_ati_cus_channel_cpy.channel_cpy_remark IS '单位描述';
COMMENT ON COLUMN t_yq_ati_cus_channel_cpy.channel_cpy_address IS '单位地址';
