-------企业帐套---------------------------------------------------
CREATE SEQUENCE seq_yq_sys_cpy_company
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_sys_cpy_company
  OWNER TO park;

CREATE TABLE t_yq_sys_cpy_company
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_sys_cpy_company' :: REGCLASS),
  cpy_code         CHARACTER VARYING UNIQUE,
  cpy_name         CHARACTER VARYING,
  user_code        CHARACTER VARYING,
  user_name        CHARACTER VARYING,
  sex              CHARACTER VARYING,
  phone            CHARACTER VARYING,
  address          CHARACTER VARYING,
  cpy_img          CHARACTER VARYING,

  -- 基础字段,
  create_code      CHARACTER VARYING,
  create_name      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  last_update_name CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  remark           CHARACTER VARYING,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_sys_cpy_company PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

ALTER TABLE t_yq_sys_cpy_company
  OWNER TO park;

COMMENT ON TABLE t_yq_sys_cpy_company IS '企业帐套表';
COMMENT ON COLUMN t_yq_sys_cpy_company.cpy_code IS '企业帐套编码';
COMMENT ON COLUMN t_yq_sys_cpy_company.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_sys_cpy_company.user_code IS '企业管理员code';
COMMENT ON COLUMN t_yq_sys_cpy_company.user_name IS '企业管理员姓名';
COMMENT ON COLUMN t_yq_sys_cpy_company.sex IS '公司名称';
COMMENT ON COLUMN t_yq_sys_cpy_company.phone IS '公司名称';
COMMENT ON COLUMN t_yq_sys_cpy_company.address IS '公司名称';
COMMENT ON COLUMN t_yq_sys_cpy_company.remark IS '备注';
COMMENT ON COLUMN t_yq_sys_cpy_company.cpy_img IS '公司图标';
