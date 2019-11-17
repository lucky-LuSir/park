-----------------********************* 园区对接人实体表  begin     **************************----------------------
CREATE SEQUENCE seq_yq_pro_hos_park_butter
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_hos_park_butter
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_hos_park_butter;
CREATE TABLE t_yq_pro_hos_park_butter
(
    id                    BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_hos_park_butter'::regclass),
    pbj_code               CHARACTER VARYING,
    pk_code                CHARACTER VARYING,
    pk_address             CHARACTER VARYING,
    emp_code               CHARACTER VARYING,
    butt_joint_type         CHARACTER VARYING,
    post_name               CHARACTER VARYING,
    duty_description      CHARACTER VARYING,

-- 基础字段,
cpy_code              CHARACTER VARYING,
cpy_name              CHARACTER VARYING,
create_code           CHARACTER VARYING,
create_name           CHARACTER VARYING,
create_dept_code   CHARACTER VARYING,
last_update_code    CHARACTER VARYING,
last_update_name     CHARACTER VARYING,
create_time         TIMESTAMP WITHOUT TIME ZONE,
last_update_time   TIMESTAMP WITHOUT TIME ZONE,
remark               CHARACTER VARYING,
is_deleted           BOOLEAN DEFAULT FALSE,

  CONSTRAINT pk_yq_pro_hos_park_butt_joint PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;
CREATE UNIQUE INDEX pbj_code ON t_yq_pro_hos_park_butter (pbj_code);
CREATE INDEX pbj_pk_code ON t_yq_pro_hos_park_butter (pk_code);
CREATE INDEX pbj_cpy_code ON t_yq_pro_hos_park_butter (cpy_code);

ALTER TABLE t_yq_pro_hos_park_butter
  OWNER TO park;

COMMENT ON TABLE t_yq_pro_hos_park_butter IS '园区对接人表';
COMMENT ON COLUMN t_yq_pro_hos_park_butter.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_pro_hos_park_butter.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_pro_hos_park_butter.pbj_code IS '对接人编码';
COMMENT ON COLUMN t_yq_pro_hos_park_butter.pk_code IS '园区编号';
COMMENT ON COLUMN t_yq_pro_hos_park_butter.pk_address IS '园区地址';
COMMENT ON COLUMN t_yq_pro_hos_park_butter.emp_code IS '人员编号';
COMMENT ON COLUMN t_yq_pro_hos_park_butter.butt_joint_type IS '对接人类型';
COMMENT ON COLUMN t_yq_pro_hos_park_butter.post_name IS '岗位';
COMMENT ON COLUMN t_yq_pro_hos_park_butter.duty_description IS '责任描述';

-----------------********************* 园区对接人实体表  end     **************************----------------------