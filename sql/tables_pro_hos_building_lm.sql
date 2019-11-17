-----------------------楼栋表 begin-----------------------
--创建序列
CREATE SEQUENCE seq_yq_pro_hos_building
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_hos_building
  OWNER TO park;

--创建表
CREATE TABLE t_yq_pro_hos_building (
  id                      BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_hos_building' :: REGCLASS),
  pk_code                 CHARACTER VARYING, --园区编码
  pk_name                 CHARACTER VARYING, --园区名称
  bd_code                 CHARACTER VARYING, --建筑编码

  --建筑基本信息
  bd_name                 CHARACTER VARYING, --建筑名称
  this_building           CHARACTER VARYING, --当前楼栋
  building_status         CHARACTER VARYING, --建筑状态
  floors_total            CHARACTER VARYING, --楼层总数
  invest_area             NUMERIC(10, 2), --计租总面积
  rentable_area           NUMERIC(10, 2), --可租用面积
  building_structure      CHARACTER VARYING, --建筑结构
  elevator_quantity       CHARACTER VARYING, --电梯数量
  freight_elevator_weight NUMERIC(10, 2), --货梯承重
  open_door               CHARACTER VARYING, --货梯开门(单向,双向)
  firefighting_level      CHARACTER VARYING, --消防等级

  building_video          CHARACTER VARYING, --楼栋视频
  out_picture             CHARACTER VARYING, --外立面

  cpy_code                CHARACTER VARYING, --公司编号
  cpy_name                CHARACTER VARYING, --公司名称
  create_code             CHARACTER VARYING, --创建者
  create_name             CHARACTER VARYING, --创建者名称
  create_dept_code        CHARACTER VARYING,
  last_update_code        CHARACTER VARYING, --最后更新者
  last_update_name        CHARACTER VARYING, --最后更新者名称
  create_time             TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time        TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  remark                  CHARACTER VARYING, --备注
  is_deleted              BOOLEAN         DEFAULT FALSE, --是否删除
  CONSTRAINT pk_yq_pro_hos_building PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX bd_code
  ON t_yq_pro_hos_building (bd_code);
--创建索引
CREATE INDEX building_pk_code
  ON t_yq_pro_hos_building (pk_code);
CREATE INDEX building_cpy_code
  ON t_yq_pro_hos_building (cpy_code);

ALTER TABLE t_yq_pro_hos_building
  OWNER TO park;

--注释
COMMENT ON TABLE t_yq_pro_hos_building IS '建筑管理表';
COMMENT ON COLUMN t_yq_pro_hos_building.pk_code IS '园区编码';
COMMENT ON COLUMN t_yq_pro_hos_building.pk_name IS '园区名称';
COMMENT ON COLUMN t_yq_pro_hos_building.bd_code IS '建筑编码';
COMMENT ON COLUMN t_yq_pro_hos_building.bd_name IS '建筑名称';
COMMENT ON COLUMN t_yq_pro_hos_building.this_building IS '当前楼栋';
COMMENT ON COLUMN t_yq_pro_hos_building.building_status IS '建筑状态';
COMMENT ON COLUMN t_yq_pro_hos_building.floors_total IS '楼层总数';
COMMENT ON COLUMN t_yq_pro_hos_building.invest_area IS '计租面积（含公摊）';
COMMENT ON COLUMN t_yq_pro_hos_building.rentable_area IS '可租用面积';
COMMENT ON COLUMN t_yq_pro_hos_building.building_structure IS '建筑结构';
COMMENT ON COLUMN t_yq_pro_hos_building.elevator_quantity IS '电梯数量';
COMMENT ON COLUMN t_yq_pro_hos_building.freight_elevator_weight IS '货梯承重';
COMMENT ON COLUMN t_yq_pro_hos_building.open_door IS '货梯开门(单向,双向)';
COMMENT ON COLUMN t_yq_pro_hos_building.firefighting_level IS '消防等级';
COMMENT ON COLUMN t_yq_pro_hos_building.building_video IS '楼栋视频';
COMMENT ON COLUMN t_yq_pro_hos_building.out_picture IS '外立面';

COMMENT ON COLUMN t_yq_pro_hos_building.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_pro_hos_building.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_pro_hos_building.create_code IS '创建者';
COMMENT ON COLUMN t_yq_pro_hos_building.create_name IS '创建者名称';
COMMENT ON COLUMN t_yq_pro_hos_building.create_dept_code IS '部门code';
COMMENT ON COLUMN t_yq_pro_hos_building.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_yq_pro_hos_building.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_yq_pro_hos_building.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_pro_hos_building.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_yq_pro_hos_building.remark IS '备注';
COMMENT ON COLUMN t_yq_pro_hos_building.is_deleted IS '是否删除';
-----------------------楼栋表 end-----------------------