----用户表----
CREATE SEQUENCE seq_yq_ati_cus_channel
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_cus_channel
  OWNER TO park;

CREATE TABLE t_yq_ati_cus_channel
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_cus_channel' :: REGCLASS),
  channel_code        CHARACTER VARYING,
  channel_status        CHARACTER VARYING,
  user_name      CHARACTER VARYING,
  phone         CHARACTER VARYING,
  sex       CHARACTER VARYING,
  post         CHARACTER VARYING,
  company_code         CHARACTER VARYING,

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
  CONSTRAINT pk_yq_ati_cus_channel PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_yq_ati_cus_channel
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_cus_channel IS '渠道表';
COMMENT ON COLUMN t_yq_ati_cus_channel.channel_code IS '渠道编码';
COMMENT ON COLUMN t_yq_ati_cus_channel.channel_status IS '渠道类型';
COMMENT ON COLUMN t_yq_ati_cus_channel.user_name IS '联系人姓名';
COMMENT ON COLUMN t_yq_ati_cus_channel.phone IS '联系人电话';
COMMENT ON COLUMN t_yq_ati_cus_channel.sex IS '联系人性别';
COMMENT ON COLUMN t_yq_ati_cus_channel.post IS '联系人职务';
COMMENT ON COLUMN t_yq_ati_cus_channel.company_code IS '单位编码';
