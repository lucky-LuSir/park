-----------------********************* 房间管理表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_hos_room
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_hos_room
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_hos_room;
CREATE TABLE t_yq_pro_hos_room
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_hos_room'::regclass),
  room_code                CHARACTER VARYING,
  fl_code                 CHARACTER VARYING,
  room_number            CHARACTER VARYING,
  rent_price                  NUMERIC(10, 2),
  invest_area                 NUMERIC(10, 2),
  property                    NUMERIC(10, 2),
  increasing_cycle         NUMERIC (10,2),
  free_rent_days            NUMERIC (10,2),
  pay_rule                     NUMERIC(10, 2),
  deposit_rule               NUMERIC (10,2),
  room_status           CHARACTER VARYING,


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
  CONSTRAINT pk_yq_pro_hos_room PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE UNIQUE INDEX room_code ON t_yq_pro_hos_room (room_code);
CREATE INDEX room_fl_code ON t_yq_pro_hos_room(fl_code);
CREATE INDEX room_cpy_code ON t_yq_pro_hos_room(cpy_code);

ALTER TABLE t_yq_pro_hos_room
  OWNER TO park;

COMMENT ON TABLE t_yq_pro_hos_room IS '房间管理表';
COMMENT ON COLUMN t_yq_pro_hos_room.room_code IS '房间编码';
COMMENT ON COLUMN t_yq_pro_hos_room.fl_code IS '楼层编码';
COMMENT ON COLUMN t_yq_pro_hos_room.room_number IS '办公室编号';
COMMENT ON COLUMN t_yq_pro_hos_room.rent_price IS '房间租金';
COMMENT ON COLUMN t_yq_pro_hos_room.invest_area IS '计租面积（含公摊）';
COMMENT ON COLUMN t_yq_pro_hos_room.property IS '物业费';
COMMENT ON COLUMN t_yq_pro_hos_room.increasing_cycle IS '递增周期(单位:年)';
COMMENT ON COLUMN t_yq_pro_hos_room.free_rent_days IS '免租期天数';
COMMENT ON COLUMN t_yq_pro_hos_room.pay_rule IS '付款规则(数字:付几个月)';
COMMENT ON COLUMN t_yq_pro_hos_room.deposit_rule IS '押金规则(数字:付几个月)';
COMMENT ON COLUMN t_yq_pro_hos_room.room_status IS '出租状态';


-----------------********************* 房间管理表  end     **************************----------------------
