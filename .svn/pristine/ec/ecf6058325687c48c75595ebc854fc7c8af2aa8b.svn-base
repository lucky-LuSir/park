-----------------********************* 物业检查信息记录表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_inspect_info
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_inspect_info
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_inspect_info;
CREATE TABLE t_yq_pro_inspect_info
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_inspect_info'::regclass),
  inspect_code            CHARACTER VARYING,
  template_code             CHARACTER VARYING,
  task_code                 CHARACTER VARYING,
  inspect_time            TIMESTAMP WITHOUT TIME ZONE,
  be_inspected_emp_code             CHARACTER VARYING,
  be_inspected_emp_name    CHARACTER VARYING,
  inspect_emp_code         CHARACTER VARYING,
  inspect_emp_name         CHARACTER VARYING,
  pk_code                  CHARACTER VARYING,
  pk_name                  CHARACTER VARYING,
  total_grade                    NUMERIC(10, 2),
  total_score                    NUMERIC(10, 2),
  inspect_type             CHARACTER VARYING,


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
  CONSTRAINT pk_yq_pro_inspect_info PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE UNIQUE INDEX inspect_info_code ON t_yq_pro_inspect_info (inspect_code);
CREATE INDEX inspect_info_cpy_code ON t_yq_pro_inspect_info(cpy_code);

ALTER TABLE t_yq_pro_inspect_info
  OWNER TO park;

COMMENT ON TABLE t_yq_pro_inspect_info IS '物业检查信息记录表';
COMMENT ON COLUMN t_yq_pro_inspect_info.inspect_code IS '检查编号';
COMMENT ON COLUMN t_yq_pro_inspect_info.template_code IS '模板编号';
COMMENT ON COLUMN t_yq_pro_inspect_info.task_code IS '任务编号';
COMMENT ON COLUMN t_yq_pro_inspect_info.inspect_time IS '检查日期';
COMMENT ON COLUMN t_yq_pro_inspect_info.be_inspected_emp_code IS '责任人编号';
COMMENT ON COLUMN t_yq_pro_inspect_info.be_inspected_emp_name IS '责任人员姓名';
COMMENT ON COLUMN t_yq_pro_inspect_info.inspect_emp_code IS '检查人编号';
COMMENT ON COLUMN t_yq_pro_inspect_info.inspect_emp_name IS '检查人姓名';
COMMENT ON COLUMN t_yq_pro_inspect_info.pk_code IS '园区编号';
COMMENT ON COLUMN t_yq_pro_inspect_info.pk_name IS '园区名称';
COMMENT ON COLUMN t_yq_pro_inspect_info.total_grade IS '总评分';
COMMENT ON COLUMN t_yq_pro_inspect_info.total_score IS '总得分';
COMMENT ON COLUMN t_yq_pro_inspect_info.inspect_type IS '检查类型(1卫生检查 2安全巡检)';


-----------------********************* 物业检查信息记录表  end     **************************----------------------
