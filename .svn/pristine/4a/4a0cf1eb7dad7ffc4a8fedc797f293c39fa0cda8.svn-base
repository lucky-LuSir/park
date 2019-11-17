-----------------********************* 物业检查模板表  begin     **************************----------------------

CREATE SEQUENCE seq_yq_pro_inspect_template
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_inspect_template
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_inspect_template;
CREATE TABLE t_yq_pro_inspect_template
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_inspect_template'::regclass),
  template_code            CHARACTER VARYING,
  template_name            CHARACTER VARYING,
  describe                 CHARACTER VARYING,
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
  CONSTRAINT pk_yq_pro_inspect_template PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE UNIQUE INDEX inspect_template_code ON t_yq_pro_inspect_template (template_code);
CREATE INDEX template_cpy_code ON t_yq_pro_inspect_template(cpy_code);

ALTER TABLE t_yq_pro_inspect_template
  OWNER TO park;

COMMENT ON TABLE t_yq_pro_inspect_template IS '物业检查模板表';
COMMENT ON COLUMN t_yq_pro_inspect_template.template_code IS '模板编号';
COMMENT ON COLUMN t_yq_pro_inspect_template.template_name IS '模板名称';
COMMENT ON COLUMN t_yq_pro_inspect_template.describe IS '描述';
COMMENT ON COLUMN t_yq_pro_inspect_template.inspect_type IS '检查类型(1卫生检查 2安全巡检)';


-----------------********************* 物业检查模板表  end     **************************----------------------
