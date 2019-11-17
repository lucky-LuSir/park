-------------------------------- 用户表 --------------------------------
CREATE SEQUENCE seq_yq_sys_user_user
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_sys_user_user
  OWNER TO park;

CREATE TABLE t_yq_sys_user_user
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_sys_user_user' :: REGCLASS),
  user_code        CHARACTER VARYING,
  user_name        CHARACTER VARYING,
  cpy_mark         CHARACTER VARYING,
  work_number      CHARACTER VARYING,
  password         CHARACTER VARYING,
  user_phone       CHARACTER VARYING,
  emp_code         CHARACTER VARYING,
  emp_name         CHARACTER VARYING,
  dept_code        CHARACTER VARYING,
  dept_name        CHARACTER VARYING,
  post_code        CHARACTER VARYING,
  email            CHARACTER VARYING,
  sex              CHARACTER VARYING,
  owner_code       CHARACTER VARYING,
  owner_name       CHARACTER VARYING,
  owner_dept_code  CHARACTER VARYING,
  owner_dept_name  CHARACTER VARYING,

  -- 基础字段,
  cpy_code         CHARACTER VARYING,
  cpy_name         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_name      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  last_update_name CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  remark           CHARACTER VARYING,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_sys_user_user PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_yq_sys_user_user
  OWNER TO park;
COMMENT ON TABLE t_yq_sys_user_user IS '用户表';
COMMENT ON COLUMN t_yq_sys_user_user.user_code IS '用户编码';
COMMENT ON COLUMN t_yq_sys_user_user.user_name IS '用户姓名';
COMMENT ON COLUMN t_yq_sys_user_user.cpy_mark IS '公司代码';
COMMENT ON COLUMN t_yq_sys_user_user.emp_code IS '人员编码';
COMMENT ON COLUMN t_yq_sys_user_user.emp_name IS '人员姓名';
COMMENT ON COLUMN t_yq_sys_user_user.dept_code IS '部门编码';
COMMENT ON COLUMN t_yq_sys_user_user.dept_name IS '部门名称';
COMMENT ON COLUMN t_yq_sys_user_user.user_phone IS '用户电话';
COMMENT ON COLUMN t_yq_sys_user_user.work_number IS '工号';
COMMENT ON COLUMN t_yq_sys_user_user.email IS '邮箱';
COMMENT ON COLUMN t_yq_sys_user_user.sex IS '性别';
COMMENT ON COLUMN t_yq_sys_user_user.password IS '加密后的密码';
COMMENT ON COLUMN t_yq_sys_user_user.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_sys_user_user.cpy_name IS '公司名称';

-------------------------------- 用户角色关联表 --------------------------------
CREATE SEQUENCE seq_yq_sys_user_user_role
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_sys_user_user_role
  OWNER TO park;

CREATE TABLE t_yq_sys_user_user_role
(
  id        BIGINT NOT NULL DEFAULT nextval('seq_yq_sys_user_user_role' :: REGCLASS),
  user_code CHARACTER VARYING,
  role_code CHARACTER VARYING,
  cpy_code  CHARACTER VARYING,
  CONSTRAINT pk_yq_sys_user_user_role PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_yq_sys_user_user_role
  OWNER TO park;
COMMENT ON TABLE t_yq_sys_user_user_role IS '用户对应角色表';
COMMENT ON COLUMN t_yq_sys_user_user_role.user_code IS '用户编码';
COMMENT ON COLUMN t_yq_sys_user_user_role.role_code IS '用户姓名';
COMMENT ON COLUMN t_yq_sys_user_user_role.cpy_code IS '企业帐套';
