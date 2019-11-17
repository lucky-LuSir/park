--渠道跟进表
CREATE SEQUENCE seq_yq_ati_cus_channel_followup
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_cus_channel_followup
  OWNER TO park;

CREATE TABLE t_yq_ati_cus_channel_followup
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_cus_channel_followup' :: REGCLASS),
  followup_code    CHARACTER VARYING,
  channel_code     CHARACTER VARYING,
  company_code  CHARACTER VARYING,
  followup_type    CHARACTER VARYING,
  followup_result  CHARACTER VARYING,


  -- 基础字段,
  cpy_code         CHARACTER VARYING,
  cpy_name         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_name      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  create_dept_name      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  last_update_name CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN DEFAULT FALSE,
  remark           CHARACTER VARYING,
  CONSTRAINT pk_yq_ati_cus_channel_followup PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

CREATE UNIQUE INDEX channel_followup_code ON t_yq_ati_cus_channel_followup (followup_code);
CREATE INDEX channel_channel_code ON t_yq_ati_cus_channel_followup (channel_code);
CREATE INDEX channel_company_code ON t_yq_ati_cus_channel_followup (company_code);

ALTER TABLE t_yq_ati_cus_channel_followup OWNER TO park;

COMMENT ON TABLE t_yq_ati_cus_channel_followup IS '渠道跟进表';

COMMENT ON COLUMN t_yq_ati_cus_channel_followup.followup_code IS '跟进编码';
COMMENT ON COLUMN t_yq_ati_cus_channel_followup.channel_code IS '渠道编码';
COMMENT ON COLUMN t_yq_ati_cus_channel_followup.company_code IS '渠道公司编码';
COMMENT ON COLUMN t_yq_ati_cus_channel_followup.followup_type IS '跟进类型（1电话拜访，2客户来访）';
COMMENT ON COLUMN t_yq_ati_cus_channel_followup.followup_result IS '跟进结果';
