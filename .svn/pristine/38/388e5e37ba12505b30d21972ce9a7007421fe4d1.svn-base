-----------------********************* 物业检查得分信息表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_inspect_score
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_inspect_score
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_inspect_score;
CREATE TABLE t_yq_pro_inspect_score
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_inspect_score'::regclass),
  inspect_code            CHARACTER VARYING,
  obj_rl_code                 CHARACTER VARYING,
  obj_rl_name                 CHARACTER VARYING,
  grade                  NUMERIC(10, 2),
  score                  NUMERIC(10, 2),
  type                   CHARACTER VARYING,
  parent_code            CHARACTER VARYING,
  describe               CHARACTER VARYING,
  file_code               CHARACTER VARYING,
  remark                 CHARACTER VARYING,
  CONSTRAINT pk_yq_pro_inspect_score PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE INDEX inspect_score_code ON t_yq_pro_inspect_score (inspect_code);

ALTER TABLE t_yq_pro_inspect_score
  OWNER TO park;

COMMENT ON TABLE t_yq_pro_inspect_score IS '物业检查得分信息表';
COMMENT ON COLUMN t_yq_pro_inspect_score.inspect_code IS '检查编号';
COMMENT ON COLUMN t_yq_pro_inspect_score.obj_rl_code IS '事项编号';
COMMENT ON COLUMN t_yq_pro_inspect_score.obj_rl_name IS '事项名称';
COMMENT ON COLUMN t_yq_pro_inspect_score.grade IS '事项评分';
COMMENT ON COLUMN t_yq_pro_inspect_score.score IS '事项得分';
COMMENT ON COLUMN t_yq_pro_inspect_score.type IS '类型';
COMMENT ON COLUMN t_yq_pro_inspect_score.parent_code IS '父事项编号';
COMMENT ON COLUMN t_yq_pro_inspect_score.describe IS '事项描述';
COMMENT ON COLUMN t_yq_pro_inspect_score.file_code IS '文件编号';
COMMENT ON COLUMN t_yq_pro_inspect_score.file_code IS '评分备注';


-----------------********************* 物业检查得分信息表  end     **************************----------------------
