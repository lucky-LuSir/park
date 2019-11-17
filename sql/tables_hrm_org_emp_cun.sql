-------------------------------- 人员表 --------------------------------
CREATE SEQUENCE seq_yq_hrm_org_employee
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_hrm_org_employee
  OWNER TO park;

CREATE TABLE t_yq_hrm_org_employee
(
  id                   BIGINT NOT NULL DEFAULT nextval('seq_yq_hrm_org_employee' :: REGCLASS),
  emp_code             CHARACTER VARYING, --人员编号
  work_number          CHARACTER VARYING, --工号
  emp_name             CHARACTER VARYING, --姓名
  emp_img              CHARACTER VARYING, --头像
  sex                  CHARACTER VARYING, --性别
  emp_phone            CHARACTER VARYING, --电话
  card                 CHARACTER VARYING, --身份证
  birth_date           TIMESTAMP WITHOUT TIME ZONE, --出生日期
  household_location   CHARACTER VARYING, --户籍所在地
  household_type       CHARACTER VARYING, --户籍类型
  graduate             CHARACTER VARYING, --毕业院校
  education            CHARACTER VARYING, --学历
  has_marry            BOOLEAN         DEFAULT FALSE, --婚否

  urg_name             CHARACTER VARYING, --紧急联系人(2018-7-4添加字段)
  urg_phone            CHARACTER VARYING, --紧急联系人电话(2018-7-4添加字段)
  urg_relation         CHARACTER VARYING, --与紧急联系人关系(2018-7-4添加字段)

  dept_code            CHARACTER VARYING, --所属部门编码
  dept_name            CHARACTER VARYING, --所属部门名称
  post_code            CHARACTER VARYING, --岗位编码
  post_name            CHARACTER VARYING, --岗位名称

  contract_start_date  TIMESTAMP WITHOUT TIME ZONE, --劳动合同起始日
  contract_end_date    TIMESTAMP WITHOUT TIME ZONE, --劳动合同到期日
  social_security_city CHARACTER VARYING, --社保地
  open_bank            CHARACTER VARYING, --开户行
  bank_card            CHARACTER VARYING, --银行卡号
  has_user             BOOLEAN         DEFAULT FALSE, --是否生成User
  emp_is_work         BOOLEAN         DEFAULT FALSE, --员工是否在职

  -- 基础字段,
  cpy_code             CHARACTER VARYING,
  cpy_name             CHARACTER VARYING,
  create_code          CHARACTER VARYING,
  create_name          CHARACTER VARYING,
  last_update_code     CHARACTER VARYING,
  last_update_name     CHARACTER VARYING,
  create_time          TIMESTAMP WITHOUT TIME ZONE,
  last_update_time     TIMESTAMP WITHOUT TIME ZONE,
  owner_code           CHARACTER VARYING,
  owner_name           CHARACTER VARYING,
  owner_dept_code      CHARACTER VARYING,
  owner_dept_name      CHARACTER VARYING,
  remark               CHARACTER VARYING,
  is_deleted           BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_hrm_org_employee PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

--创建唯一主键
CREATE UNIQUE INDEX emp_code
  ON t_yq_hrm_org_employee (emp_code);
--创建索引(岗位)
CREATE INDEX employee_post_code
  ON t_yq_hrm_org_employee (post_code);
CREATE INDEX employee_cpy_code
  ON t_yq_hrm_org_employee (cpy_code);


ALTER TABLE t_yq_hrm_org_employee
  OWNER TO park;
COMMENT ON TABLE t_yq_hrm_org_employee IS '人员表';
COMMENT ON COLUMN t_yq_hrm_org_employee.emp_code IS '人员编号';
COMMENT ON COLUMN t_yq_hrm_org_employee.work_number IS '工号(可登陆账号)';
COMMENT ON COLUMN t_yq_hrm_org_employee.emp_name IS '姓名';
COMMENT ON COLUMN t_yq_hrm_org_employee.sex IS '性别(男,女)';
COMMENT ON COLUMN t_yq_hrm_org_employee.emp_phone IS '手机号';
COMMENT ON COLUMN t_yq_hrm_org_employee.card IS '身份证';
COMMENT ON COLUMN t_yq_hrm_org_employee.birth_date IS '出生日期';
COMMENT ON COLUMN t_yq_hrm_org_employee.household_location IS '户籍所在地';
COMMENT ON COLUMN t_yq_hrm_org_employee.household_type IS '户籍类型';
COMMENT ON COLUMN t_yq_hrm_org_employee.graduate IS '毕业院校';
COMMENT ON COLUMN t_yq_hrm_org_employee.education IS '学历';
COMMENT ON COLUMN t_yq_hrm_org_employee.has_marry IS '婚否';
COMMENT ON COLUMN t_yq_hrm_org_employee.emp_img IS '头像';

COMMENT ON COLUMN t_yq_hrm_org_employee.urg_name IS '紧急联系人';
COMMENT ON COLUMN t_yq_hrm_org_employee.urg_phone IS '紧急联系人电话';
COMMENT ON COLUMN t_yq_hrm_org_employee.urg_relation IS '与紧急联系人关系';

COMMENT ON COLUMN t_yq_hrm_org_employee.dept_code IS '所属部门编码';
COMMENT ON COLUMN t_yq_hrm_org_employee.dept_name IS '所属部门名称';
COMMENT ON COLUMN t_yq_hrm_org_employee.post_code IS '岗位编码';
COMMENT ON COLUMN t_yq_hrm_org_employee.post_name IS '岗位名称';

COMMENT ON COLUMN t_yq_hrm_org_employee.contract_start_date IS '劳动合同起始日';
COMMENT ON COLUMN t_yq_hrm_org_employee.contract_end_date IS '劳动合同到期日';
COMMENT ON COLUMN t_yq_hrm_org_employee.social_security_city IS '社保地';
COMMENT ON COLUMN t_yq_hrm_org_employee.open_bank IS '开户银行';
COMMENT ON COLUMN t_yq_hrm_org_employee.bank_card IS '银行卡号';
COMMENT ON COLUMN t_yq_hrm_org_employee.has_user IS '是否生成User';
COMMENT ON COLUMN t_yq_hrm_org_employee.emp_is_work IS '员工是否在职';

COMMENT ON COLUMN t_yq_hrm_org_employee.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_hrm_org_employee.cpy_name IS '公司编号';
COMMENT ON COLUMN t_yq_hrm_org_employee.create_code IS '创建人编码';
COMMENT ON COLUMN t_yq_hrm_org_employee.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_yq_hrm_org_employee.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_hrm_org_employee.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_yq_hrm_org_employee.is_deleted IS '是否删除';


-------------------------------- 岗位表 --------------------------------
CREATE SEQUENCE seq_yq_hrm_org_post
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_hrm_org_post
  OWNER TO park;

CREATE TABLE t_yq_hrm_org_post
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_hrm_org_post' :: REGCLASS),
  post_code        CHARACTER VARYING,
  post_name        CHARACTER VARYING,

  -- 基础字段,
  cpy_code         CHARACTER VARYING,
  cpy_name         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_name      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  last_update_name CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  remark            CHARACTER VARYING,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_yq_hrm_org_post PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_yq_hrm_org_post
  OWNER TO park;
COMMENT ON TABLE t_yq_hrm_org_post IS '岗位表';
COMMENT ON COLUMN t_yq_hrm_org_post.post_code IS '岗位编码';
COMMENT ON COLUMN t_yq_hrm_org_post.post_name IS '岗位名称';

COMMENT ON COLUMN t_yq_hrm_org_post.remark IS '岗位备注';
COMMENT ON COLUMN t_yq_hrm_org_post.cpy_code IS '公司编号';
COMMENT ON COLUMN t_yq_hrm_org_post.create_code IS '创建人编码';
COMMENT ON COLUMN t_yq_hrm_org_post.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_yq_hrm_org_post.create_time IS '创建时间';
COMMENT ON COLUMN t_yq_hrm_org_post.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_yq_hrm_org_post.is_deleted IS '是否删除';


-------------------------------- 岗位权限关联表 --------------------------------
CREATE SEQUENCE seq_yq_hrm_org_post_menu
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_hrm_org_post_menu
  OWNER TO park;

CREATE TABLE t_yq_hrm_org_post_menu
(
  id        BIGINT NOT NULL DEFAULT nextval('seq_yq_hrm_org_post_menu' :: REGCLASS),
  post_code CHARACTER VARYING,
  menu_code CHARACTER VARYING,

  CONSTRAINT pk_yq_hrm_org_post_menu PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_yq_hrm_org_post_menu
  OWNER TO park;
COMMENT ON TABLE t_yq_hrm_org_post_menu IS '岗位关联菜单表';
COMMENT ON COLUMN t_yq_hrm_org_post_menu.post_code IS '岗位编码';
COMMENT ON COLUMN t_yq_hrm_org_post_menu.menu_code IS '菜单编码';

