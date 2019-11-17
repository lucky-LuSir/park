-----------------每日任务表---------
CREATE SEQUENCE seq_yq_task_attract_task
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_task_attract_task
  OWNER TO park;

CREATE TABLE t_yq_task_attract_task
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_task_attract_task' :: REGCLASS),
  task_code        CHARACTER VARYING,
  emp_code         CHARACTER VARYING,
  emp_name         CHARACTER VARYING,
  task_time        TIMESTAMP WITHOUT TIME ZONE,
  banner           NUMERIC(10, 2),
  leaflets         NUMERIC(10, 2),
  friends_share    NUMERIC(10, 2),
  description      CHARACTER VARYING,
  pk_name          CHARACTER VARYING,

  -- 基础字段,
  cpy_code         CHARACTER VARYING,
  cpy_name         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code CHARACTER VARYING,
  create_dept_name CHARACTER VARYING,
  create_name      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  last_update_name CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  remark           CHARACTER VARYING,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_task_attract_task PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
CREATE UNIQUE INDEX task_code
  ON t_yq_task_attract_task (task_code);
CREATE INDEX task_emp_code
  ON t_yq_task_attract_task (emp_code);
CREATE INDEX task_cpy_code
  ON t_yq_task_attract_task (cpy_code);

ALTER TABLE t_yq_task_attract_task
  OWNER TO park;
COMMENT ON TABLE t_yq_task_attract_task IS '每日任务表';
COMMENT ON COLUMN t_yq_task_attract_task.task_code IS '任务编号';
COMMENT ON COLUMN t_yq_task_attract_task.emp_code IS '人员编号';
COMMENT ON COLUMN t_yq_task_attract_task.emp_name IS '姓名';
COMMENT ON COLUMN t_yq_task_attract_task.task_time IS '任务时间';
COMMENT ON COLUMN t_yq_task_attract_task.banner IS '横幅';
COMMENT ON COLUMN t_yq_task_attract_task.leaflets IS '宣传单';
COMMENT ON COLUMN t_yq_task_attract_task.friends_share IS '微信朋友圈';
COMMENT ON COLUMN t_yq_task_attract_task.description IS '任务描述';
COMMENT ON COLUMN t_yq_task_attract_task.pk_name IS '园区项目';
