package com.kfwy.park.controller.hrm;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.controller.hrm.vo.EmpBiServiceRequest;
import com.kfwy.park.controller.hrm.vo.EmpBiServiceResponse;
import com.kfwy.park.hrm.org.bi.business.IEmpBiManager;
import com.kfwy.park.hrm.org.bi.entity.EmpBiEntity;
import com.kfwy.park.hrm.org.employee.business.IEmployeeManager;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;
import com.kfwy.park.pro.house.building.business.IParkButtJointManager;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.entity.ParkButtJointEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 招商人员统计看板接口
 * @Auth xpp
 * @Date 2018/8/3
 * @Version 1.0
 */
@Controller
@RequestMapping("/empBi")
public class EmpBiService extends SpringRestService {

    @Autowired
    private IEmpBiManager empBiManager;

    @Autowired
    private IParkManager parkManager;

    @Autowired
    private IParkButtJointManager parkButtJointManager;

    @Autowired
    private IEmployeeManager employeeManager;

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 描述:查询一个招商人员,一条对应的看板数据
     * @Auth xpp
     * @Date 2018/8/6 19:19
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/queryOne"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryOne(@RequestBody EmpBiServiceRequest request) throws IllegalAccessException, ParseException {

        EmpBiServiceResponse response = new EmpBiServiceResponse();

        Date startDate = null;
        Date endDate = null;
        Date date = new Date();

        EmpBiEntity empBiEntity = request.getEntity();

        if (!empBiEntity.getDateStartAndEndList().isEmpty()) {
            startDate = empBiEntity.getDateStartAndEndList().get(0);// 获取开始日期
            endDate = empBiEntity.getDateStartAndEndList().get(1);// 获取结束日期
        } else {
            startDate = DateFormatUtil.monthBegin(date);
            endDate = DateFormatUtil.monthEnd(date);
        }


        /***多次调用部分*start**/
        EmpBiEntity result = empBiManager.queryOne(empBiEntity.getEmpCode(), startDate, endDate);

        //获取一个招商人员,负责园区
        Map<String, Object> mapParkButt = new HashMap<>();
        mapParkButt.put("empCode", request.getEntity().getEmpCode());
        mapParkButt.put("isDeleted", false);
        List<ParkButtJointEntity> listParkButt = parkButtJointManager.findByMap(mapParkButt);
        List<ParkEntity> pkList = new ArrayList<ParkEntity>();
        for (int i = 0; i < listParkButt.size(); i++) {
            ParkButtJointEntity one = listParkButt.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("pkCode", one.getPkCode());
            map.put("isDeleted", false);
            ParkEntity parkOne = new ParkEntity();
            parkOne = parkManager.findOne(map);
            pkList.add(parkOne);
        }

        String pkNameStr = "(初始值)";
        for (int i = 0; i < pkList.size(); i++) {
            if (i == 0) {
                pkNameStr = pkNameStr + pkList.get(i).getPkName();
            } else {
                pkNameStr = pkNameStr + "  ,  " + pkList.get(i).getPkName();
            }

        }
        result.setPkName(pkNameStr);
        /***多次调用部分*end**/

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 描述:查询一个部门内招商人员统计列表
     * @Auth xpp
     * @Date 2018/8/9 17:42
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody EmpBiServiceRequest request) throws IllegalAccessException, ParseException {

        EmpBiServiceResponse response = new EmpBiServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("deptCode", request.getDeptCode());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        // 根据deptCode查询部门下的所有人员
        List<EmployeeEntity> employeeList = employeeManager.findByMap(param);
        EmpBiEntity empBiEntity = request.getEntity();

        Date startDate = null;
        Date endDate = null;
        Date date = new Date();

        List<Date> dateStartAndEndList = empBiEntity.getDateStartAndEndList();

        if (dateStartAndEndList != null) {
            startDate = empBiEntity.getDateStartAndEndList().get(0);// 获取开始日期
            endDate = empBiEntity.getDateStartAndEndList().get(1);// 获取结束日期
        } else {
            startDate = DateFormatUtil.monthBegin(date);
            endDate = DateFormatUtil.monthEnd(date);
        }

        List<EmpBiEntity> empBiList = new ArrayList<EmpBiEntity>();
        for (int i = 0; i < employeeList.size(); i++) {
            String empCode = employeeList.get(i).getEmpCode();

            // 根据empCode查询每一个招商人员数据
            EmpBiEntity empBiOne = getOneEmpBi(empCode, startDate, endDate);
            empBiList.add(empBiOne);
        }

        // 对empBiList根据去化面积排序
        SortByRentArea mysort = new SortByRentArea();
        Collections.sort(empBiList, mysort);
        // 加rankNum排名值
        for (int i = 0; i < empBiList.size(); i++) {
            empBiList.get(i).setRankNum(i + 1);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("empBiList", empBiList);
        map.put("firstDate", startDate);
        map.put("endDate", endDate);

        response.setResult(map);
        return this.success(response);
    }

    /**
     * @return com.kfwy.park.hrm.org.bi.entity.EmpBiEntity
     * @Description 描述:queryList内部方法--查询一个招商人员的数据
     * @Auth xpp
     * @Date 2018/8/9 14:50
     * @Version 1.0
     * @Param [empCode]
     **/
    public EmpBiEntity getOneEmpBi(String empCode, Date startDate, Date endDate) throws ParseException {
        EmpBiEntity empBi = new EmpBiEntity();
        empBi.setSignAreaMonth(new BigDecimal("0.00"));
        empBi = empBiManager.queryOne(empCode, startDate, endDate);

        //获取一个招商人员,负责园区
        Map<String, Object> mapParkButt = new HashMap<>();
        mapParkButt.put("empCode", empCode);
        mapParkButt.put("isDeleted", false);

        List<ParkButtJointEntity> listParkButt = parkButtJointManager.findByMap(mapParkButt);

        List<ParkEntity> pkList = new ArrayList<ParkEntity>();
        for (int i = 0; i < listParkButt.size(); i++) {
            ParkButtJointEntity one = listParkButt.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("pkCode", one.getPkCode());
            map.put("isDeleted", false);
            ParkEntity parkOne = new ParkEntity();
            parkOne = parkManager.findOne(map);
            if(parkOne != null) {
                pkList.add(parkOne);
            }
        }

        String pkNameStr = "";
        for (int i = 0; i < pkList.size(); i++) {
            if (i == 0) {
                pkNameStr = pkNameStr + pkList.get(i).getPkName();
            } else {
                pkNameStr = pkNameStr + "  ,  " + pkList.get(i).getPkName();
            }
        }

        empBi.setPkName(pkNameStr);
        return empBi;
    }

}


/**
 * @Description 描述:内部类class,实现arrayLis排序的Comparator接口
 * @Auth xpp
 * @Date 2018/8/9 16:35
 * @Version 1.0
 * @Param
 * @return
 **/
class SortByRentArea implements Comparator {
    public int compare(Object o1, Object o2) {
        EmpBiEntity s1 = (EmpBiEntity) o1;
        EmpBiEntity s2 = (EmpBiEntity) o2;

        BigDecimal bd1 = s1.getSignAreaMonth();
        BigDecimal bd2 = s2.getSignAreaMonth();
        int a = bd1.compareTo(bd2);
        /**a = -1,表示bd1小于bd2；
         a = 0,表示bd1等于bd2；
         a = 1,表示bd1大于bd2；
         **/
        if (a == 1) {
            return -1;
        }
        return 1;
    }
}
