-----------------********************* 园区出入管理  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_car
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_car
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_car;
CREATE TABLE t_yq_pro_car
(

  id                    BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_car'::regclass),
  cm_code                CHARACTER VARYING,
  pk_code                CHARACTER VARYING,
  pk_name              CHARACTER VARYING,
  license_num              CHARACTER VARYING,
  in_time             TIMESTAMP WITHOUT TIME ZONE,
  out_time            TIMESTAMP WITHOUT TIME ZONE,
  visitor          CHARACTER VARYING,
  visitor_phone             CHARACTER VARYING,
  cus_code          CHARACTER VARYING,
  company                 CHARACTER VARYING,
  other_company      CHARACTER VARYING,
  be_visitor_name       CHARACTER VARYING,
  be_visitor_phone      CHARACTER VARYING,
  visitor_id_card       CHARACTER VARYING,
  visitor_num         INT4,
  visit_purpose       CHARACTER VARYING,
  in_out_type           CHARACTER VARYING,

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


  CONSTRAINT pk_yq_pro_car PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;
CREATE UNIQUE INDEX cm_code ON t_yq_pro_car (cm_code);
CREATE INDEX pk_cpy_code ON t_yq_pro_car (cpy_code);

ALTER TABLE t_yq_pro_car
  OWNER TO park;


COMMENT ON TABLE t_yq_pro_car IS '园区进出管理';
COMMENT ON COLUMN t_yq_pro_car.cm_code IS '编号';
COMMENT ON COLUMN t_yq_pro_car.license_num IS '车牌号';
COMMENT ON COLUMN t_yq_pro_car.pk_code IS '园区编号';
COMMENT ON COLUMN t_yq_pro_car.pk_name IS '园区名';
COMMENT ON COLUMN t_yq_pro_car.in_time IS '进入时间';
COMMENT ON COLUMN t_yq_pro_car.out_time IS '出去时间';
COMMENT ON COLUMN t_yq_pro_car.visitor IS '访问人';
COMMENT ON COLUMN t_yq_pro_car.visitor_phone IS '访问人手机号';
COMMENT ON COLUMN t_yq_pro_car.cus_code IS '被访客户编号';
COMMENT ON COLUMN t_yq_pro_car.company IS '被访客户公司名';
COMMENT ON COLUMN t_yq_pro_car.other_company IS '其他被访客户名';
COMMENT ON COLUMN t_yq_pro_car.be_visitor_name IS '被访客户姓名';
COMMENT ON COLUMN t_yq_pro_car.be_visitor_phone IS '被访客户手机号';
COMMENT ON COLUMN t_yq_pro_car.visitor_id_card IS '来访人身份证';
COMMENT ON COLUMN t_yq_pro_car.visitor_num IS '来访人数';
COMMENT ON COLUMN t_yq_pro_car.visit_purpose IS '来访目的';
COMMENT ON COLUMN t_yq_pro_car.in_out_type IS '进出类型(1:车辆进出 2:人员进出)';


-----------------********************* 园区进出管理  end     **************************----------------------