-------------------------------- 看板表 --------------------------------
CREATE SEQUENCE seq_yq_sys_board_program
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_sys_board_program
  OWNER TO park;

CREATE TABLE t_yq_sys_board_program
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_sys_board_program' :: REGCLASS),
  pg_code          CHARACTER VARYING,
  pg_name          CHARACTER VARYING,
  program_type     CHARACTER VARYING,
  url              CHARACTER VARYING,
  default_param    CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_sys_board_program PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_yq_sys_board_program
  OWNER TO park;
COMMENT ON TABLE t_yq_sys_board_program IS '看板表';
COMMENT ON COLUMN t_yq_sys_board_program.pg_code IS '小程序编码';
COMMENT ON COLUMN t_yq_sys_board_program.pg_name IS '小程序名称';
COMMENT ON COLUMN t_yq_sys_board_program.program_type IS '小程序类型';
COMMENT ON COLUMN t_yq_sys_board_program.url IS '小程序缩略图片';
COMMENT ON COLUMN t_yq_sys_board_program.default_param IS '默认参数';

-------------------------------- 用户对应看板表 --------------------------------
CREATE SEQUENCE seq_yq_sys_board_board
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_sys_board_board
  OWNER TO park;

CREATE TABLE t_yq_sys_board_board
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_sys_board_board' :: REGCLASS),
  board_code       CHARACTER VARYING,
  pg_code          CHARACTER VARYING,
  param            CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_sys_board_board PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
--创建索引
CREATE INDEX board_create_code
  ON t_yq_sys_board_board (create_code);
CREATE INDEX board_cpy_code
  ON t_yq_sys_board_board (cpy_code);

ALTER TABLE t_yq_sys_board_board
  OWNER TO park;
COMMENT ON TABLE t_yq_sys_board_board IS '用户对应看板表';
COMMENT ON COLUMN t_yq_sys_board_board.board_code IS '看板编码';
COMMENT ON COLUMN t_yq_sys_board_board.pg_code IS '小程序编码';
COMMENT ON COLUMN t_yq_sys_board_board.param IS '参数json';
