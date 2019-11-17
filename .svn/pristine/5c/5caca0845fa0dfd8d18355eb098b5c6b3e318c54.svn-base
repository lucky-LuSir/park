
-----------------*********************     合同表  start   **************************----------------------
CREATE SEQUENCE seq_yq_ati_con_contract
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_yq_ati_con_contract
  OWNER TO park;

CREATE TABLE t_yq_ati_con_contract
(
  id                        BIGINT NOT NULL DEFAULT nextval('seq_yq_ati_con_contract' :: REGCLASS),
  ct_code                    CHARACTER VARYING,
  renewed_contract_code     CHARACTER VARYING,
  ct_status                  CHARACTER VARYING,
  contractor_code           CHARACTER VARYING,
  contractor                CHARACTER VARYING,
  contractor_dept_code      CHARACTER VARYING,
  contractor_dept           CHARACTER VARYING,
  contractor_phone                  CHARACTER VARYING,
  contract_start_time       TIMESTAMP WITHOUT TIME ZONE,
  contract_end_time         TIMESTAMP WITHOUT TIME ZONE,
  free_rent_days             BIGINT,
  free_rent_start_time      TIMESTAMP WITHOUT TIME ZONE,
  free_rent_end_time        TIMESTAMP WITHOUT TIME ZONE,
  start_rent_date           TIMESTAMP WITHOUT TIME ZONE,
  cus_code                  CHARACTER VARYING,
  company_name              CHARACTER VARYING,
  relate_name               CHARACTER VARYING,
  relate_phone              CHARACTER VARYING,
  landline_phone              CHARACTER VARYING,
  relate_position           CHARACTER VARYING,
  house_address             CHARACTER VARYING,
  delivery_date             TIMESTAMP WITHOUT TIME ZONE,
  free_rent_around          CHARACTER VARYING,
  total_rental_area         NUMERIC (10,2),
  public_office_proportion  CHARACTER VARYING,
  inc_detail_type           CHARACTER VARYING,
  rent_increase_proportion  NUMERIC (10,2),
  increasing_cycle          BIGINT,
  inc_detail_case           CHARACTER VARYING,
  has_tax                   BOOLEAN  DEFAULT FALSE,
  tax_point                 CHARACTER VARYING,
  pay_rule                  BIGINT,
  deposit                   NUMERIC (10,2),
  deposit_rule              BIGINT,
  receivable_deposit        NUMERIC (10,2),
  deposit_status            CHARACTER VARYING,
  property_unit_price       NUMERIC (10,2),
  property_unit             CHARACTER VARYING,
  has_invoice_by_property   BOOLEAN  DEFAULT FALSE,
  month_count_by_property   BIGINT,
  billing_date_by_property  TIMESTAMP WITHOUT TIME ZONE,
  total_electricity         NUMERIC (10,2),
  total_electricity_unit    CHARACTER VARYING,
  basic_electricity_unit_price  NUMERIC (10,2),
  basic_electricity_unit    CHARACTER VARYING,
  basic_electricity_price_by_month  NUMERIC (10,2),
  add_loss                  NUMERIC (10,2),
  use_type              CHARACTER VARYING,
  water_price               NUMERIC (10,2),
  water_unit                CHARACTER VARYING,
  free_parking_count        BIGINT,
  parking_price             NUMERIC (10,2),
  parking_space              BIGINT,
  im_contract_code          CHARACTER VARYING,
  im_cus_code               CHARACTER VARYING,
  im_cus_name               CHARACTER VARYING,
  im_company                CHARACTER VARYING,
  im_relate_phone           CHARACTER VARYING,
  im_receipt_account        CHARACTER VARYING,
  im_receipt_number         CHARACTER VARYING,
  im_bank                   CHARACTER VARYING,
  im_payable_amount         NUMERIC (10,2),
  im_status                 CHARACTER VARYING,


  trade                             CHARACTER VARYING,
  trade_name                        CHARACTER VARYING,
  company_product                   CHARACTER VARYING,
  pk_code                           CHARACTER VARYING,

  ct_name                           CHARACTER VARYING,
  rent_total                        NUMERIC (10,2),
  contract_url                      CHARACTER VARYING,
  advance_date_count                BIGINT,
  party_name                        CHARACTER VARYING,
  contract_date                     TIMESTAMP WITHOUT TIME ZONE,
  el_has_tax                         BOOLEAN  DEFAULT FALSE,
  ele_deposit                       NUMERIC (10,2),
  water_deposit                     NUMERIC (10,2),

  fpay_house_rent                   NUMERIC (10,2),
  fpay_property_price              NUMERIC (10,2),
  fpay_insurance_price             NUMERIC (10,2),
  fpay_project_deposit             NUMERIC (10,2),
  fpay_bond                         NUMERIC (10,2),

  water_billing_type               CHARACTER VARYING,
  init_water                        NUMERIC (10,2),
  water_amount                        NUMERIC (10,2),
  wa_has_tax                         BOOLEAN  DEFAULT FALSE,
  property_total                    NUMERIC (10,2),
  pk_name                        CHARACTER VARYING,
  parking_count                       BIGINT,
  parking_add_time                    TIMESTAMP,
  customer_type                        CHARACTER VARYING,
  relate_sex                        CHARACTER VARYING,
  pa_has_tax                        BOOLEAN  DEFAULT FALSE,

  --合同添加税收信息
  output_value                           NUMERIC (10,2),--产值
  has_revenue_back_local                BOOLEAN  DEFAULT FALSE,--税收是否可落户当地
  tax_take                            NUMERIC (10,2),--税收金额
  equipment_list                   CHARACTER VARYING,--设备清单
  equipment_value                   NUMERIC (10,2),--设备价值

  delete_cause                       CHARACTER VARYING,--合同刪除原因
  delete_type                         CHARACTER VARYING,--合同刪除类型
  contract_edit_remark                 CHARACTER VARYING,--合同修改记录


  cpy_code                  CHARACTER VARYING,
  cpy_name                  CHARACTER VARYING,
  create_code               CHARACTER VARYING,
  create_name               CHARACTER VARYING,
  last_update_code          CHARACTER VARYING,
  last_update_name          CHARACTER VARYING,
  create_time               TIMESTAMP WITHOUT TIME ZONE ,
  last_update_time          TIMESTAMP WITHOUT TIME ZONE ,
  is_deleted                BOOLEAN   DEFAULT FALSE,
  remark                    CHARACTER VARYING,
  CONSTRAINT pk_yq_ati_con_contract PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

CREATE UNIQUE INDEX ct_code ON t_yq_ati_con_contract (ct_code);
CREATE INDEX renewed_contract_code ON t_yq_ati_con_contract(renewed_contract_code);
CREATE INDEX con_cus_code ON t_yq_ati_con_contract(cus_code);
CREATE INDEX con_cpy_code ON t_yq_ati_con_contract(cpy_code);

ALTER TABLE t_yq_ati_con_contract
  OWNER TO park;
COMMENT ON TABLE t_yq_ati_con_contract IS '合同表';
COMMENT ON COLUMN t_yq_ati_con_contract.ct_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_contract.renewed_contract_code IS '续签的合同编号';
COMMENT ON COLUMN t_yq_ati_con_contract.ct_status IS '合同状态';
COMMENT ON COLUMN t_yq_ati_con_contract.contract_start_time IS '合同开始时间';
COMMENT ON COLUMN t_yq_ati_con_contract.contract_end_time IS '合同结束时间';
COMMENT ON COLUMN t_yq_ati_con_contract.free_rent_days IS '免租期天数';
COMMENT ON COLUMN t_yq_ati_con_contract.free_rent_start_time IS '免租期开始时间';
COMMENT ON COLUMN t_yq_ati_con_contract.free_rent_end_time IS '免租期结束时间';
COMMENT ON COLUMN t_yq_ati_con_contract.start_rent_date IS '开始计租日期';
COMMENT ON COLUMN t_yq_ati_con_contract.cus_code IS '客户编码';
COMMENT ON COLUMN t_yq_ati_con_contract.company_name IS '公司名称';
COMMENT ON COLUMN t_yq_ati_con_contract.relate_name IS '联系人姓名';
COMMENT ON COLUMN t_yq_ati_con_contract.relate_phone IS '联系人电话';
COMMENT ON COLUMN t_yq_ati_con_contract.landline_phone IS '座机';
COMMENT ON COLUMN t_yq_ati_con_contract.relate_position IS '联系人职务';
COMMENT ON COLUMN t_yq_ati_con_contract.house_address IS '房源地址';
COMMENT ON COLUMN t_yq_ati_con_contract.delivery_date IS '交付日期';
COMMENT ON COLUMN t_yq_ati_con_contract.free_rent_around IS '免租期前后';
COMMENT ON COLUMN t_yq_ati_con_contract.total_rental_area IS '总出租面积（含公摊）';
COMMENT ON COLUMN t_yq_ati_con_contract.public_office_proportion IS '公摊比例';
COMMENT ON COLUMN t_yq_ati_con_contract.inc_detail_type IS '是否详细递增';
COMMENT ON COLUMN t_yq_ati_con_contract.rent_increase_proportion IS '租金递增比例';
COMMENT ON COLUMN t_yq_ati_con_contract.increasing_cycle IS '递增周期(单位:年)';
COMMENT ON COLUMN t_yq_ati_con_contract.inc_detail_case IS '详情递增容器';
COMMENT ON COLUMN t_yq_ati_con_contract.has_tax IS '租金是否含税';
COMMENT ON COLUMN t_yq_ati_con_contract.tax_point IS '发票税点';
COMMENT ON COLUMN t_yq_ati_con_contract.pay_rule IS '付款规则(数字:付几个月';
COMMENT ON COLUMN t_yq_ati_con_contract.deposit IS '押金（保证金）';
COMMENT ON COLUMN t_yq_ati_con_contract.deposit_rule IS '押金规则(数字:付几个月)';
COMMENT ON COLUMN t_yq_ati_con_contract.receivable_deposit IS '应收押金金额';
COMMENT ON COLUMN t_yq_ati_con_contract.deposit_status IS '押金状态';
COMMENT ON COLUMN t_yq_ati_con_contract.property_unit_price IS '物业费用单价';
COMMENT ON COLUMN t_yq_ati_con_contract.property_unit IS '物业费单位';
COMMENT ON COLUMN t_yq_ati_con_contract.has_invoice_by_property IS '物业费是否含票';
COMMENT ON COLUMN t_yq_ati_con_contract.month_count_by_property IS '物业费几个月缴一次';
COMMENT ON COLUMN t_yq_ati_con_contract.billing_date_by_property IS '物业费开始计费日期';
COMMENT ON COLUMN t_yq_ati_con_contract.total_electricity IS '总用电量';
COMMENT ON COLUMN t_yq_ati_con_contract.total_electricity_unit IS '总用电量单位';
COMMENT ON COLUMN t_yq_ati_con_contract.basic_electricity_unit_price IS '基础电费单价';
COMMENT ON COLUMN t_yq_ati_con_contract.basic_electricity_unit IS '基础电费单价单位';
COMMENT ON COLUMN t_yq_ati_con_contract.basic_electricity_price_by_month IS '每月基础电费';
COMMENT ON COLUMN t_yq_ati_con_contract.add_loss IS '加收损耗';
COMMENT ON COLUMN t_yq_ati_con_contract.use_type IS '用途';
COMMENT ON COLUMN t_yq_ati_con_contract.water_price IS '水费单价';
COMMENT ON COLUMN t_yq_ati_con_contract.water_unit IS '水费单价单位';
COMMENT ON COLUMN t_yq_ati_con_contract.free_parking_count IS '免费停车位个数';
COMMENT ON COLUMN t_yq_ati_con_contract.parking_price IS '停车位单价';
COMMENT ON COLUMN t_yq_ati_con_contract.parking_space IS '停车位个数(不含免)';
COMMENT ON COLUMN t_yq_ati_con_contract.im_contract_code IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_contract.im_cus_code IS '客户编号';
COMMENT ON COLUMN t_yq_ati_con_contract.im_cus_name IS '客户名称';
COMMENT ON COLUMN t_yq_ati_con_contract.im_company IS '中介公司';
COMMENT ON COLUMN t_yq_ati_con_contract.im_relate_phone IS '联系人电话';
COMMENT ON COLUMN t_yq_ati_con_contract.im_receipt_account IS '收款账户';
COMMENT ON COLUMN t_yq_ati_con_contract.im_receipt_number IS '收款账号';
COMMENT ON COLUMN t_yq_ati_con_contract.im_bank IS '开户银行';
COMMENT ON COLUMN t_yq_ati_con_contract.im_payable_amount IS '应付金额';
COMMENT ON COLUMN t_yq_ati_con_contract.im_status IS '状态';
COMMENT ON COLUMN t_yq_ati_con_contract.contractor_code IS '签约人编码';
COMMENT ON COLUMN t_yq_ati_con_contract.contractor IS '签约人';
COMMENT ON COLUMN t_yq_ati_con_contract.contractor_dept_code IS '签约部门编码';
COMMENT ON COLUMN t_yq_ati_con_contract.contractor_dept IS '签约部门';
COMMENT ON COLUMN t_yq_ati_con_contract.trade IS '所属行业';
COMMENT ON COLUMN t_yq_ati_con_contract.trade_name IS '所属行业名称';
COMMENT ON COLUMN t_yq_ati_con_contract.company_product IS '公司产品';
COMMENT ON COLUMN t_yq_ati_con_contract.pk_code IS '入驻园区';
COMMENT ON COLUMN t_yq_ati_con_contract.contractor_phone IS '签约联系电话';
COMMENT ON COLUMN t_yq_ati_con_contract.ct_name IS '合同编号';
COMMENT ON COLUMN t_yq_ati_con_contract.rent_total IS '租金合计';
COMMENT ON COLUMN t_yq_ati_con_contract.contract_url IS '合同url';
COMMENT ON COLUMN t_yq_ati_con_contract.advance_date_count IS '提前交租';
COMMENT ON COLUMN t_yq_ati_con_contract.party_name IS '甲方公司';
COMMENT ON COLUMN t_yq_ati_con_contract.contract_date IS '签约日期';
COMMENT ON COLUMN t_yq_ati_con_contract.el_has_tax IS '电费是否含税';
COMMENT ON COLUMN t_yq_ati_con_contract.ele_deposit IS '电费押金';
COMMENT ON COLUMN t_yq_ati_con_contract.water_deposit IS '水费押金';
COMMENT ON COLUMN t_yq_ati_con_contract.fpay_house_rent IS '首期支付房屋租金';
COMMENT ON COLUMN t_yq_ati_con_contract.fpay_property_price IS '首期支付物业费';
COMMENT ON COLUMN t_yq_ati_con_contract.fpay_insurance_price IS '首期支付保险费';
COMMENT ON COLUMN t_yq_ati_con_contract.fpay_project_deposit IS '首期支付工程押金';
COMMENT ON COLUMN t_yq_ati_con_contract.fpay_bond IS '首期支付保证金';

COMMENT ON COLUMN t_yq_ati_con_contract.water_billing_type IS '水费计费方式';
COMMENT ON COLUMN t_yq_ati_con_contract.init_water IS '水表初始值';
COMMENT ON COLUMN t_yq_ati_con_contract.water_amount IS '水费金额';
COMMENT ON COLUMN t_yq_ati_con_contract.wa_has_tax IS '水表是否含税';
COMMENT ON COLUMN t_yq_ati_con_contract.property_total IS '物业费合计';
COMMENT ON COLUMN t_yq_ati_con_contract.pk_code IS '园区名称';
COMMENT ON COLUMN t_yq_ati_con_contract.parking_count IS '当前停车位个数（不含免）';
COMMENT ON COLUMN t_yq_ati_con_contract.parking_add_time IS '新增停车位时间';
COMMENT ON COLUMN t_yq_ati_con_contract.customer_type IS '客户类型(1是公司,2是个人)';
COMMENT ON COLUMN t_yq_ati_con_contract.relate_sex IS '联系人性别(1是男,2是女)';
COMMENT ON COLUMN t_yq_ati_con_contract.pa_has_tax IS '停车费是否含税';


COMMENT ON COLUMN t_yq_ati_con_contract.output_value IS '产值';
COMMENT ON COLUMN t_yq_ati_con_contract.has_revenue_back_local IS '税收是否可落户当地';
COMMENT ON COLUMN t_yq_ati_con_contract.tax_take IS '税收金额';
COMMENT ON COLUMN t_yq_ati_con_contract.equipment_list IS '设备清单';
COMMENT ON COLUMN t_yq_ati_con_contract.equipment_value IS '设备价值';

COMMENT ON COLUMN t_yq_ati_con_contract.delete_cause IS '合同刪除原因';
COMMENT ON COLUMN t_yq_ati_con_contract.delete_type IS '合同刪除类型';
COMMENT ON COLUMN t_yq_ati_con_contract.contract_edit_remark IS '合同修改记录';
-----------------*********************     合同表  end     **************************----------------------