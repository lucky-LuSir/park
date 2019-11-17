-----------------********************* 园区列表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_hos_park
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_hos_park
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_hos_park;
CREATE TABLE t_yq_pro_hos_park
(

  id                    BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_hos_park'::regclass),
  pk_code                CHARACTER VARYING,
  pk_name                CHARACTER VARYING,
  min_rent_area              NUMERIC(10, 2),
  average_price              NUMERIC(10, 2),
  land_block             CHARACTER VARYING,
  estate_year            NUMERIC(20, 8),
  use_elec_total          NUMERIC(20, 8),
  road_width             NUMERIC(20, 8),
  property_cost          NUMERIC(20, 8),
  acreage                 NUMERIC(10, 2),
  rent_invest_area      NUMERIC(10, 2),
  invest_area            NUMERIC(10, 2),
  investment_vacant_land  NUMERIC(10, 2),
  parking_space             BIGINT,


  rentable_area          NUMERIC(10, 2),
  longest_lease          NUMERIC(10, 2),
  shortest_lease         NUMERIC(10, 2),
  building_total         NUMERIC(10, 2),
  industry_restrictions  CHARACTER VARYING,

  has_register           BOOLEAN,
  has_certify            BOOLEAN,
  has_eia                BOOLEAN,
  has_sewage_certify      BOOLEAN,
  has_canteen            BOOLEAN,
  has_pub_booth           BOOLEAN,
  has_office_build        BOOLEAN,
  has_canopy             BOOLEAN,
  has_car_park            BOOLEAN,
  has_load_dock           BOOLEAN,
  has_supermarket        BOOLEAN,
  has_chargingPile        BOOLEAN,

  territory_type         CHARACTER VARYING,
  territory_type_name    CHARACTER VARYING,
  province_code          CHARACTER VARYING,
  city_code              CHARACTER VARYING,
  region_code            CHARACTER VARYING,
  street_code            CHARACTER VARYING,
  province_name          CHARACTER VARYING,
  city_name              CHARACTER VARYING,
  region_name            CHARACTER VARYING,
  street_name            CHARACTER VARYING,
  address                CHARACTER VARYING,

  lead_trade             CHARACTER VARYING,
  traffic_matching       CHARACTER VARYING,
  favoured_policy        CHARACTER VARYING,
  business_start_day     TIMESTAMP,
  business_end_day        TIMESTAMP,
  meter_reading           CHARACTER VARYING,
  use_elec_surplus        NUMERIC(10, 2),

  park_video              CHARACTER VARYING,
  out_picture             CHARACTER VARYING,
  aerial_view             CHARACTER VARYING,
  park_status                CHARACTER VARYING,

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


  CONSTRAINT pk_yq_pro_hos_park PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;
CREATE UNIQUE INDEX pk_code ON t_yq_pro_hos_park (pk_code);
CREATE INDEX pk_cpy_code ON t_yq_pro_hos_park (cpy_code);

ALTER TABLE t_yq_pro_hos_park
  OWNER TO park;


COMMENT ON TABLE t_yq_pro_hos_park IS '园区列表';
COMMENT ON COLUMN t_yq_pro_hos_park.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_pro_hos_park.cpy_name IS '公司名称';
COMMENT ON COLUMN t_yq_pro_hos_park.pk_code IS '园区编号';
COMMENT ON COLUMN t_yq_pro_hos_park.pk_name IS '园区名';
COMMENT ON COLUMN t_yq_pro_hos_park.min_rent_area IS '园区起租面积';
COMMENT ON COLUMN t_yq_pro_hos_park.average_price IS '园区起租面积';
COMMENT ON COLUMN t_yq_pro_hos_park.land_block IS '所属地块';
COMMENT ON COLUMN t_yq_pro_hos_park.estate_year IS '地产年限';
COMMENT ON COLUMN t_yq_pro_hos_park.use_elec_total IS '总用电量';
COMMENT ON COLUMN t_yq_pro_hos_park.road_width IS '道路宽度';
COMMENT ON COLUMN t_yq_pro_hos_park.property_cost IS '物业费 ';
COMMENT ON COLUMN t_yq_pro_hos_park.acreage IS '园区总招商面积';
COMMENT ON COLUMN t_yq_pro_hos_park.invest_area IS '计租面积(含公摊)';
COMMENT ON COLUMN t_yq_pro_hos_park.rent_invest_area IS '出租计租面积(含公摊)';
COMMENT ON COLUMN t_yq_pro_hos_park.investment_vacant_land IS '可招商空地';
COMMENT ON COLUMN t_yq_pro_hos_park.parking_space IS '停车位数量';


COMMENT ON COLUMN t_yq_pro_hos_park.rentable_area IS '可租用面积';
COMMENT ON COLUMN t_yq_pro_hos_park.longest_lease IS '最长租期';
COMMENT ON COLUMN t_yq_pro_hos_park.shortest_lease IS '最短租期';
COMMENT ON COLUMN t_yq_pro_hos_park.building_total IS '楼栋总数';
COMMENT ON COLUMN t_yq_pro_hos_park.industry_restrictions IS '行业限制';

COMMENT ON COLUMN t_yq_pro_hos_park.has_register IS '是否注册';
COMMENT ON COLUMN t_yq_pro_hos_park.has_certify IS '有无产证';
COMMENT ON COLUMN t_yq_pro_hos_park.has_eia IS '是否环评';
COMMENT ON COLUMN t_yq_pro_hos_park.has_sewage_certify IS '有无排污证';
COMMENT ON COLUMN t_yq_pro_hos_park.has_canteen IS '有无食堂';
COMMENT ON COLUMN t_yq_pro_hos_park.has_pub_booth IS '有无公摊';
COMMENT ON COLUMN t_yq_pro_hos_park.has_office_build IS '有无办公楼';
COMMENT ON COLUMN t_yq_pro_hos_park.has_canopy IS '有无雨棚';
COMMENT ON COLUMN t_yq_pro_hos_park.has_car_park IS '有无停车场';
COMMENT ON COLUMN t_yq_pro_hos_park.has_load_dock IS '有无卸货平台';
COMMENT ON COLUMN t_yq_pro_hos_park.has_supermarket IS '有无超市';
COMMENT ON COLUMN t_yq_pro_hos_park.has_chargingPile IS '有无充电桩';

COMMENT ON COLUMN t_yq_pro_hos_park.territory_type IS '所在大区(部门编号)';
COMMENT ON COLUMN t_yq_pro_hos_park.territory_type_name IS '所在大区名称';
COMMENT ON COLUMN t_yq_pro_hos_park.province_code IS '所在省份编码';
COMMENT ON COLUMN t_yq_pro_hos_park.city_code IS '所属城市编码';
COMMENT ON COLUMN t_yq_pro_hos_park.region_code IS '所属区域编码';
COMMENT ON COLUMN t_yq_pro_hos_park.street_code IS '所属街道编码';
COMMENT ON COLUMN t_yq_pro_hos_park.province_name IS '所在省份名字';
COMMENT ON COLUMN t_yq_pro_hos_park.city_name IS '所属城市名字';
COMMENT ON COLUMN t_yq_pro_hos_park.region_name IS '所属区域名字';
COMMENT ON COLUMN t_yq_pro_hos_park.street_name IS '所属街道名字';
COMMENT ON COLUMN t_yq_pro_hos_park.address IS '详细地址';



COMMENT ON COLUMN t_yq_pro_hos_park.lead_trade IS '主导行业';
COMMENT ON COLUMN t_yq_pro_hos_park.traffic_matching IS '交通配套';
COMMENT ON COLUMN t_yq_pro_hos_park.favoured_policy IS '优惠政策';
COMMENT ON COLUMN t_yq_pro_hos_park.business_start_day IS '经营开始日';
COMMENT ON COLUMN t_yq_pro_hos_park.business_end_day IS '经营到期日';
COMMENT ON COLUMN t_yq_pro_hos_park.meter_reading IS '每月抄表日';
COMMENT ON COLUMN t_yq_pro_hos_park.use_elec_surplus IS '剩余电量';

COMMENT ON COLUMN t_yq_pro_hos_park.park_video IS '房源视频';
COMMENT ON COLUMN t_yq_pro_hos_park.out_picture IS '外立面';
COMMENT ON COLUMN t_yq_pro_hos_park.aerial_view IS '鸟瞰图';
COMMENT ON COLUMN t_yq_pro_hos_park.park_status IS '园区状态';


-----------------********************* 园区列表  end     **************************----------------------