-------------------------------- 部门管理表 --------------------------------
CREATE SEQUENCE seq_yq_pro_feedback
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_pro_feedback
  OWNER TO park;

DROP TABLE IF EXISTS t_yq_pro_feedback;
CREATE TABLE t_yq_pro_feedback
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_yq_pro_feedback' :: REGCLASS),

  fb_code            CHARACTER VARYING,
  fb_type            CHARACTER VARYING,
  fb_title            CHARACTER VARYING,
  fb_description    CHARACTER VARYING,
  fb_img             CHARACTER VARYING,
  fb_status          CHARACTER VARYING,
  ask_question_name CHARACTER VARYING,
  ask_question_phone CHARACTER VARYING,
  deal_question_code  CHARACTER VARYING,
  deal_question_name CHARACTER VARYING,
  deal_question_phone CHARACTER VARYING,
  browse_number      BIGINT,
  like_number         BIGINT,
  comment_number     BIGINT,


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
  CONSTRAINT pk_yq_pro_feedback PRIMARY KEY (id)
)
WITH (OIDS = FALSE
);

CREATE UNIQUE INDEX fb_code
  ON t_yq_pro_feedback (fb_code);

ALTER TABLE t_yq_pro_feedback
  OWNER TO park;
COMMENT ON TABLE t_yq_pro_feedback IS '问题反馈表';
COMMENT ON COLUMN t_yq_pro_feedback.fb_code IS '反馈问题编号';
COMMENT ON COLUMN t_yq_pro_feedback.fb_type IS '反馈类型';
COMMENT ON COLUMN t_yq_pro_feedback.fb_type IS '反馈问题标题';
COMMENT ON COLUMN t_yq_pro_feedback.fb_description IS '反馈描述';
COMMENT ON COLUMN t_yq_pro_feedback.fb_img IS '反馈图片';
COMMENT ON COLUMN t_yq_pro_feedback.fb_status IS '反馈问题状态';
COMMENT ON COLUMN t_yq_pro_feedback.comment_number IS '评论数';
COMMENT ON COLUMN t_yq_pro_feedback.ask_question_name IS '提出问题的人';
COMMENT ON COLUMN t_yq_pro_feedback.ask_question_phone IS '提出问题的人的手机号';
COMMENT ON COLUMN t_yq_pro_feedback.deal_question_code IS '处理问题的人的编码';
COMMENT ON COLUMN t_yq_pro_feedback.deal_question_name IS '处理问题的人';
COMMENT ON COLUMN t_yq_pro_feedback.deal_question_phone IS '处理问题的人的手机号';
COMMENT ON COLUMN t_yq_pro_feedback.browse_number IS '浏览次数';
COMMENT ON COLUMN t_yq_pro_feedback.like_number IS '点赞数';
COMMENT ON COLUMN t_yq_pro_feedback.comment_number IS '评论数';
