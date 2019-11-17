-----------------********************* 物业检查任务表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_inspect_task_obj
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_inspect_task_obj
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_inspect_task_obj;
CREATE TABLE t_yq_pro_inspect_task_obj
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_inspect_task_obj'::regclass),
  task_code            CHARACTER VARYING,
  obj_rl_code            CHARACTER VARYING,

  CONSTRAINT pk_yq_pro_inspect_task_obj PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE INDEX inspect_task_obj_code ON t_yq_pro_inspect_task_obj(task_code);

ALTER TABLE t_yq_pro_inspect_task
  OWNER TO park;

COMMENT ON TABLE t_yq_pro_inspect_task_obj IS '物业检查任务表';
COMMENT ON COLUMN t_yq_pro_inspect_task_obj.task_code IS '任务编号';
COMMENT ON COLUMN t_yq_pro_inspect_task_obj.obj_rl_code IS '事项规则编号';


-----------------********************* 物业检查任务表  end     **************************----------------------
