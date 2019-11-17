-------------------------------- 部门管理表 --------------------------------
CREATE SEQUENCE seq_yq_pro_feedback_reply
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_feedback_reply
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_feedback_reply;
CREATE TABLE t_yq_pro_feedback_reply
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_feedback_reply' :: REGCLASS),

  reply_code            CHARACTER VARYING,
  fb_code               CHARACTER VARYING,
  reply_description    CHARACTER VARYING,
  reply_img             CHARACTER VARYING,

    --基础字段
  cpy_code               CHARACTER VARYING, --公司编号
  cpy_name               CHARACTER VARYING, --公司名称
  owner_code             CHARACTER VARYING, --所属用户编码
  owner_name             CHARACTER VARYING, --所属用户姓名
  owner_dept_code        CHARACTER VARYING, --数据所属部门
  owner_dept_name        CHARACTER VARYING, --数据所属部门名称
  create_code            CHARACTER VARYING, --创建者
  create_name            CHARACTER VARYING, --创建者名称
  create_dept_code       CHARACTER VARYING, --创建者部门编号
  create_dept_name       CHARACTER VARYING, --创建者部门名称
  last_update_code       CHARACTER VARYING, --最后更新者
  last_update_name       CHARACTER VARYING, --最后更新者名称
  create_time            TIMESTAMP WITHOUT TIME ZONE, --创建时间
  last_update_time       TIMESTAMP WITHOUT TIME ZONE, --最后更新时间
  is_deleted             BOOLEAN         DEFAULT FALSE, --是否作废或者删除
  remark                 CHARACTER VARYING, --备注
  CONSTRAINT pk_yq_pro_feedback_reply PRIMARY KEY (id)
)
WITH (OIDS = FALSE
);

CREATE UNIQUE INDEX reply_code
  ON t_yq_pro_feedback_reply (reply_code);

ALTER TABLE t_yq_pro_feedback_reply
  OWNER TO park;
COMMENT ON TABLE t_yq_pro_feedback_reply IS '问题反馈回复表';
COMMENT ON COLUMN t_yq_pro_feedback_reply.reply_code IS '反馈问题编码';
COMMENT ON COLUMN t_yq_pro_feedback_reply.fb_code IS '反馈编码';
COMMENT ON COLUMN t_yq_pro_feedback_reply.reply_description IS '反馈回复描述';
COMMENT ON COLUMN t_yq_pro_feedback_reply.reply_img IS '反馈回复图片';
