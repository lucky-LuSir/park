
-- ----------------------------
-- 新增初始化看板
-- ----------------------------


------------------第1--------------------
INSERT INTO t_yq_sys_board_program

(
pg_code, pg_name, program_type,url,

cpy_code,create_code, last_update_code, create_time, last_update_time, remark, is_deleted )

VALUES
	(
	'pg001', '我的数据', '1', 'noImg',

	  'cpy001', 'e001', 'e001', '2018-09-07 10:11:26', '2018-09-07 10:11:31','显示个人本月招商面积,意向客户,新签租户', 'f');

------------------end--------------------

------------------第2--------------------
INSERT INTO t_yq_sys_board_program

(
pg_code, pg_name, program_type,url,

cpy_code,create_code, last_update_code, create_time, last_update_time, remark, is_deleted )

VALUES
	(
	'pg002', '园区去化看板', '1', 'noImg',

	  'cpy001', 'e001', 'e001', '2018-09-07 10:11:26', '2018-09-07 10:11:31','显示选择园区的去化面积', 'f');

------------------end--------------------

------------------第3--------------------
INSERT INTO t_yq_sys_board_program

(
pg_code, pg_name, program_type,url,

cpy_code,create_code, last_update_code, create_time, last_update_time, remark, is_deleted )

VALUES
	(
	'pg003', '招商人员数据看板', '1', 'noImg',

	  'cpy001', 'e001', 'e001', '2018-09-07 10:11:26', '2018-09-07 10:11:31','显示招商人员的每月数据统计', 'f');

------------------end--------------------

------------------第4--------------------
INSERT INTO t_yq_sys_board_program

(
pg_code, pg_name, program_type,url,

cpy_code,create_code, last_update_code, create_time, last_update_time, remark, is_deleted )

VALUES
	(
	'pg004', '招商人员任务数据看板', '1', 'noImg',

	  'cpy001', 'e001', 'e001', '2018-09-07 10:11:26', '2018-09-07 10:11:31','显示招商人员的任务展示', 'f');

------------------end--------------------

------------------第5--------------------
INSERT INTO t_yq_sys_board_program

(
pg_code, pg_name, program_type,url,

cpy_code,create_code, last_update_code, create_time, last_update_time, remark, is_deleted )

VALUES
	(
	'pg005', '应收租金费用异常看板', '1', 'noImg',

	  'cpy001', 'e001', 'e001', '2018-09-07 10:11:26', '2018-09-07 10:11:31','显示应收租金费用异常的统计数据', 'f');

------------------end--------------------

------------------第6--------------------
INSERT INTO t_yq_sys_board_program

(
pg_code, pg_name, program_type,url,

cpy_code,create_code, last_update_code, create_time, last_update_time, remark, is_deleted )

VALUES
	(
	'pg006', '应收电费费用异常看板', '1', 'noImg',

	  'cpy001', 'e001', 'e001', '2018-09-07 10:11:26', '2018-09-07 10:11:31','显示应收电费费用异常的统计数据', 'f');

------------------end--------------------

------------------第7--------------------
INSERT INTO t_yq_sys_board_program

(
pg_code, pg_name, program_type,url,

cpy_code,create_code, last_update_code, create_time, last_update_time, remark, is_deleted )

VALUES
	(
	'pg007', '应收水费用异常看板', '1', 'noImg',

	  'cpy001', 'e001', 'e001', '2018-09-07 10:11:26', '2018-09-07 10:11:31','显示应收水费费用异常的统计数据', 'f');

------------------end--------------------

------------------第8--------------------
INSERT INTO t_yq_sys_board_program

(
pg_code, pg_name, program_type,url,

cpy_code,create_code, last_update_code, create_time, last_update_time, remark, is_deleted )

VALUES
	(
	'pg008', '应收停车费用异常看板', '1', 'noImg',

	  'cpy001', 'e001', 'e001', '2018-09-07 10:11:26', '2018-09-07 10:11:31','显示应收停车费用异常的统计数据', 'f');

------------------end--------------------



-- ----------------------------
-- 新增初始化公司
-- ----------------------------

INSERT INTO t_yq_sys_cpy_company

(
cpy_code, cpy_name, cpy_img,

create_code, create_name, last_update_code, last_update_name, create_time, last_update_time, remark, is_deleted )

VALUES
	(
	'cpy001',
	'三蚁科技',
	'noImg',

	'无create_code',
	'无创建人',
	'无update_code',
	'无修改人',
	'2018-09-04 10:28:03.774',
	'2018-09-04 10:28:03.774',
	'无备注',
	'f')


------------------end--------------------