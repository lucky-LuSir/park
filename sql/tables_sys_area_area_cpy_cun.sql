
---------根据公司相关的区域-----------
CREATE SEQUENCE seq_yq_sys_area_company
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_sys_area_company
  OWNER TO park;

CREATE TABLE t_yq_sys_area_company
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_yq_sys_area_company' :: REGCLASS),
  area_code        CHARACTER VARYING,
  parent_code      CHARACTER VARYING DEFAULT '0',
  name             CHARACTER VARYING,
  short_name       CHARACTER VARYING,
  latitude         NUMERIC(16, 13),
  longitude        NUMERIC(16, 13),
  level            SMALLINT,
  sort             INT,
  status           CHARACTER VARYING DEFAULT '0',
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_yq_sys_area_company PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_yq_sys_area_company
  OWNER TO park;
COMMENT ON TABLE t_yq_sys_area_company IS '区域信息表关联到公司';
COMMENT ON COLUMN t_yq_sys_area_company.area_code IS '区域编码';
COMMENT ON COLUMN t_yq_sys_area_company.parent_code IS '父区域编码';
COMMENT ON COLUMN t_yq_sys_area_company.name IS '区域名称';
COMMENT ON COLUMN t_yq_sys_area_company.short_name IS '区域名称简称';
COMMENT ON COLUMN t_yq_sys_area_company.longitude IS '经度坐标';
COMMENT ON COLUMN t_yq_sys_area_company.latitude IS '纬度坐标';
COMMENT ON COLUMN t_yq_sys_area_company.level IS '1/2/3/4(省市区镇)';
COMMENT ON COLUMN t_yq_sys_area_company.sort IS '排序';
COMMENT ON COLUMN t_yq_sys_area_company.status IS '状态(0禁用1启用)';
COMMENT ON COLUMN t_yq_sys_area_company.cpy_code IS '企业帐套编码';
