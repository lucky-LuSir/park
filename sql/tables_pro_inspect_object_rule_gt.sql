-----------------********************* 物业检查事项规则  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_inspect_object_rule
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_inspect_object_rule
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_inspect_object_rule;
CREATE TABLE t_yq_pro_inspect_object_rule
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_inspect_object_rule'::regclass),
  obj_rl_code            CHARACTER VARYING,
  obj_rl_name            CHARACTER VARYING,
  grade                  NUMERIC(10, 2),
  type                   CHARACTER VARYING,
  parent_code            CHARACTER VARYING,
  template_code               CHARACTER VARYING,
  describe               CHARACTER VARYING,


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
  CONSTRAINT pk_yq_pro_inspect_object_rule PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE UNIQUE INDEX inspect_obj_rl_code ON t_yq_pro_inspect_object_rule (obj_rl_code);
CREATE INDEX inspect_cpy_code ON t_yq_pro_inspect_object_rule(cpy_code);

ALTER TABLE t_yq_pro_inspect_object_rule
  OWNER TO park;

COMMENT ON TABLE t_yq_pro_inspect_object_rule IS '物业检查事项规则';
COMMENT ON COLUMN t_yq_pro_inspect_object_rule.obj_rl_code IS '规则编号';
COMMENT ON COLUMN t_yq_pro_inspect_object_rule.obj_rl_name IS '规则名称';
COMMENT ON COLUMN t_yq_pro_inspect_object_rule.grade IS '规则所占分数';
COMMENT ON COLUMN t_yq_pro_inspect_object_rule.type IS '规则类型 1:节点 2:具体规则';
COMMENT ON COLUMN t_yq_pro_inspect_object_rule.parent_code IS '上级节点编号';
COMMENT ON COLUMN t_yq_pro_inspect_object_rule.template_code IS '所属模板编号';
COMMENT ON COLUMN t_yq_pro_inspect_object_rule.describe IS '规则描述';


-----------------********************* 物业检查事项规则  end     **************************----------------------
