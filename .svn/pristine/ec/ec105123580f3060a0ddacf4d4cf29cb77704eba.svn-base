-----------------********************* 楼层管理表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_hos_floor
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_hos_floor
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_hos_floor;
CREATE TABLE t_yq_pro_hos_floor
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_hos_floor'::regclass),

  pk_code                CHARACTER VARYING,
  pk_name               CHARACTER VARYING,
  bd_code               CHARACTER VARYING,
  bd_name               CHARACTER VARYING,
  fl_code               CHARACTER VARYING,
  use_type              CHARACTER VARYING,
  decoration_type      CHARACTER VARYING,
  floor_type            CHARACTER VARYING,
  floor_status          CHARACTER VARYING,
  floor_bear_weight    NUMERIC(10, 2),
  this_floor            CHARACTER VARYING,
  invest_area            NUMERIC(10, 2),
  rentable_area        NUMERIC(10, 2),
  rented_area           NUMERIC(10, 2),
  floor_height          NUMERIC(10, 2),
  pillar_spacing        NUMERIC(10, 2),
  floor_deep             CHARACTER VARYING,
  has_elevator           BOOLEAN,
  has_driving            BOOLEAN,

  has_bathroom            BOOLEAN,
  has_office_area         BOOLEAN,

  floor_video              CHARACTER VARYING,
  out_picture              CHARACTER VARYING,
  aerial_view              CHARACTER VARYING,
  road_map                 CHARACTER VARYING,
  internal_diagram        CHARACTER VARYING,
  floor_price               NUMERIC(10, 2),

  floor_unit_price               NUMERIC(10, 2),
  price_unit               CHARACTER VARYING,
  tenant_price               NUMERIC(10, 2),
  tenant_unit               CHARACTER VARYING,

  --基础字段,
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
  CONSTRAINT pk_yq_pro_hos_floor PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE UNIQUE INDEX fl_code ON t_yq_pro_hos_floor (fl_code);
CREATE INDEX floor_pk_code ON t_yq_pro_hos_floor(pk_code);
CREATE INDEX floor_bd_code ON t_yq_pro_hos_floor(bd_code);
CREATE INDEX floor_cpy_code ON t_yq_pro_hos_floor(cpy_code);

ALTER TABLE t_yq_pro_hos_floor
  OWNER TO park;

COMMENT ON TABLE t_yq_pro_hos_floor IS '楼层管理表';
COMMENT ON COLUMN t_yq_pro_hos_floor.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_pro_hos_floor.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_pro_hos_floor.pk_code IS '园区编码';
COMMENT ON COLUMN t_yq_pro_hos_floor.pk_name IS '园区名称';
COMMENT ON COLUMN t_yq_pro_hos_floor.bd_code IS '建筑编码';
COMMENT ON COLUMN t_yq_pro_hos_floor.bd_name IS '建筑名称';
COMMENT ON COLUMN t_yq_pro_hos_floor.fl_code IS '楼层编码';
COMMENT ON COLUMN t_yq_pro_hos_floor.use_type IS '用途';
COMMENT ON COLUMN t_yq_pro_hos_floor.decoration_type IS '装修类型';
COMMENT ON COLUMN t_yq_pro_hos_floor.floor_status IS '楼层状态';
COMMENT ON COLUMN t_yq_pro_hos_floor.floor_type IS '地坪类型';
COMMENT ON COLUMN t_yq_pro_hos_floor.floor_bear_weight IS '地坪承重';
COMMENT ON COLUMN t_yq_pro_hos_floor.this_floor IS '所在层';
COMMENT ON COLUMN t_yq_pro_hos_floor.invest_area IS '计租面积（含公摊）';
COMMENT ON COLUMN t_yq_pro_hos_floor.rentable_area IS '可租用面积';
COMMENT ON COLUMN t_yq_pro_hos_floor.rented_area IS '已租用面积';
COMMENT ON COLUMN t_yq_pro_hos_floor.floor_height IS '层高';
COMMENT ON COLUMN t_yq_pro_hos_floor.pillar_spacing IS '柱距';
COMMENT ON COLUMN t_yq_pro_hos_floor.floor_deep IS '长*宽';
COMMENT ON COLUMN t_yq_pro_hos_floor.has_elevator IS '是否通电梯';
COMMENT ON COLUMN t_yq_pro_hos_floor.has_driving IS '是否有行车';
COMMENT ON COLUMN t_yq_pro_hos_floor.has_bathroom IS '是否有卫生间';
COMMENT ON COLUMN t_yq_pro_hos_floor.has_office_area IS '是否有办公区域';
COMMENT ON COLUMN t_yq_pro_hos_floor.floor_price IS '楼层租金';
COMMENT ON COLUMN t_yq_pro_hos_floor.floor_unit_price IS '销售定价价';
COMMENT ON COLUMN t_yq_pro_hos_floor.price_unit IS '定价单位';
COMMENT ON COLUMN t_yq_pro_hos_floor.tenant_price IS '承租单价';
COMMENT ON COLUMN t_yq_pro_hos_floor.tenant_unit IS '承租单价单位';

COMMENT ON COLUMN t_yq_pro_hos_floor.floor_video IS '园区视频';
COMMENT ON COLUMN t_yq_pro_hos_floor.out_picture IS '外立面';
COMMENT ON COLUMN t_yq_pro_hos_floor.aerial_view IS '鸟瞰图';
COMMENT ON COLUMN t_yq_pro_hos_floor.road_map IS '道路图';
COMMENT ON COLUMN t_yq_pro_hos_floor.internal_diagram IS '内部图';

-----------------********************* 楼层管理表  end     **************************----------------------

-----------------********************* 楼层价格历史表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_hos_price_history
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_hos_price_history
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_hos_price_history;
CREATE TABLE t_yq_pro_hos_price_history
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_hos_price_history'::regclass),

  pk_code               CHARACTER VARYING,
  bd_code               CHARACTER VARYING,
  fl_code               CHARACTER VARYING,
  price                  NUMERIC(10, 2),
  price_unit            CHARACTER VARYING,
  pricing_time          TIMESTAMP,

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
  CONSTRAINT pk_yq_pro_hos_price_history PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE UNIQUE INDEX his_price_fl_code ON t_yq_pro_hos_price_history (fl_code);
CREATE INDEX his_price_cpy_code ON t_yq_pro_hos_price_history (cpy_code);


ALTER TABLE t_yq_pro_hos_price_history
  OWNER TO park;
COMMENT ON TABLE t_yq_pro_hos_price_history IS '楼层价格历史表';
COMMENT ON COLUMN t_yq_pro_hos_price_history.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_pro_hos_price_history.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_pro_hos_price_history.pk_code IS '园区编码';
COMMENT ON COLUMN t_yq_pro_hos_price_history.bd_code IS '建筑编码';
COMMENT ON COLUMN t_yq_pro_hos_price_history.fl_code IS '楼层编码';
COMMENT ON COLUMN t_yq_pro_hos_price_history.price IS '价格';
COMMENT ON COLUMN t_yq_pro_hos_price_history.price_unit IS '价格单位';
COMMENT ON COLUMN t_yq_pro_hos_price_history.pricing_time IS '定价时间';

-----------------********************* 楼层价格历史表  end     **************************----------------------
