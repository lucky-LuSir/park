-- t_yq_pro_hos_park 园区表   园区公摊比    ------
ALTER TABLE t_yq_pro_hos_park ADD COLUMN area_rate  NUMERIC(10, 2);
COMMENT ON COLUMN t_yq_pro_hos_park.area_rate IS '园区公摊比';

------------------    end    ------------------------