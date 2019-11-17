-----------------********************* 园区车辆管理  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_car_manager
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_car_manager
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_car_manager;
CREATE TABLE t_yq_pro_car_manager
(

  id                    BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_car_manager'::regclass),
  car_code                CHARACTER VARYING,
  license_num              CHARACTER VARYING,
  pk_code                CHARACTER VARYING,
  pk_name              CHARACTER VARYING,
  car_owner_name                 CHARACTER VARYING,
  car_owner_phone             CHARACTER VARYING,
  car_owner_id_card       CHARACTER VARYING,
  cus_code          CHARACTER VARYING,
  company                 CHARACTER VARYING,
  driving_licence       CHARACTER VARYING,
  vehicle_license       CHARACTER VARYING,


--基础字段
  cpy_code                     CHARACTER VARYING, --公司编号
  cpy_name                     CHARACTER VARYING, --公司名称
  owner_code                   CHARACTER VARYING, --所属用户编码
  owner_name                   CHARACTER VARYING, --所属用户姓名
  owner_dept_code              CHARACTER VARYING, --数据所属部门
  owner_dept_name              CHARACTER VARYING, --数据所属部门名称
  create_code                  CHARACTER VARYING, --创建者
  create_name                  CHARACTER VARYING, --创建者名称
  create_dept_code             CHARACTER VARYING, --创建者部门编号
  create_dept_name             CHARACTER VARYING, --创建者部门名称
  last_update_code             CHARACTER VARYING, --最后更新者
  last_update_name             CHARACTER VARYING, --最后更新者名称
  create_time                  TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time             TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  is_deleted                   BOOLEAN         DEFAULT FALSE, --是否作废或者删除
  remark                       CHARACTER VARYING, --备注


  CONSTRAINT pk_yq_pro_car_manager PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;
CREATE UNIQUE INDEX car_code ON t_yq_pro_car_manager (car_code);
CREATE INDEX cpy_code ON t_yq_pro_car_manager (cpy_code);

ALTER TABLE t_yq_pro_car_manager
  OWNER TO park;


COMMENT ON TABLE t_yq_pro_car_manager IS '园区车辆管理';
COMMENT ON COLUMN t_yq_pro_car_manager.car_code IS '车辆编号';
COMMENT ON COLUMN t_yq_pro_car_manager.license_num IS '车牌号';
COMMENT ON COLUMN t_yq_pro_car_manager.pk_code IS '园区编号';
COMMENT ON COLUMN t_yq_pro_car_manager.pk_name IS '园区名';
COMMENT ON COLUMN t_yq_pro_car_manager.car_owner_name IS '车辆拥有者姓名';
COMMENT ON COLUMN t_yq_pro_car_manager.car_owner_phone IS '车辆拥有者电话';
COMMENT ON COLUMN t_yq_pro_car_manager.car_owner_id_card IS '车辆拥有者身份证';
COMMENT ON COLUMN t_yq_pro_car_manager.cus_code IS '所属客户编号';
COMMENT ON COLUMN t_yq_pro_car_manager.company IS '所属客户名称';
COMMENT ON COLUMN t_yq_pro_car_manager.driving_licence IS '驾驶证';
COMMENT ON COLUMN t_yq_pro_car_manager.vehicle_license IS '汽车行驶证';


-----------------********************* 园区车辆管理  end     **************************----------------------