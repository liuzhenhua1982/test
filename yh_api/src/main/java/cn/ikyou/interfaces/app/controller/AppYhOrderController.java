package cn.ikyou.interfaces.app.controller;

import cn.ikyou.application.business.AppYhOrderService;
import cn.ikyou.domain.business.entity.TWkOrders;
import cn.ikyou.domain.usercenter.entity.SysUser;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.response.PageResult;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.app.vo.yhorder.*;
import cn.ikyou.interfaces.app.wrapper.AppOrderWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;


@Slf4j
@RestController
@Api(tags = "养护工单")
@AllArgsConstructor
@RequestMapping("/api/yh-order")
public class AppYhOrderController {

    private final AppYhOrderService appYhOrderService;


    @ResponseBody
    @ApiOperation("物料获取")
    @RequestMapping(value = "/material-list", method = RequestMethod.POST)
    public Result<List<MaterialResVO>> meterialList(@RequestBody MaterialReqVO materialReqVO) {
        Result<List<MaterialResVO>> result = Result.build();
        List<MaterialResVO> list=appYhOrderService.meterialList(materialReqVO);
        result.setData(list);
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setMsg("获取成功");
        return result;
    }


    @ResponseBody
    @ApiOperation("使用车辆")
    @RequestMapping(value = "/car-list", method = RequestMethod.GET)
    public Result<List<CarResVO>> carList() {
        Result<List<CarResVO>> result = Result.build();
        try{
            List<CarResVO> carResVOS = appYhOrderService.carList();
            result.setData(carResVOS);
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("获取成功");

        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("获取失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }


    @ResponseBody
    @ApiOperation("作业人员表")
    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public Result<List<UserResVO>> userList() {
        Result<List<UserResVO>> result = Result.build();
        try{
            List<UserResVO> userResVOS = appYhOrderService.userList();
            result.setData(userResVOS);
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("获取成功");

        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("获取失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }


    @ResponseBody
    @ApiOperation("物料详情列表")
    @RequestMapping(value = "/wl-list", method = RequestMethod.GET)
    public Result<List<WlResVO>> wlDetailList(@RequestParam(value="wkId",required=true) Integer wkId) {
        Result<List<WlResVO>> result = Result.build();
        try{
            List<WlResVO> resVOList = appYhOrderService.wlDetailList(wkId);
            result.setData(resVOList);
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("获取成功");
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("获取失败");
            log.error(ExceptionUtils.getMessage(e));
        }

        return result;
    }


    @ResponseBody
    @ApiOperation("删除物料")
    @RequestMapping(value = "/wl-del", method = RequestMethod.GET)
    public Result wlDel(@RequestParam(value="id",required=true) Integer id) {
        Result result = Result.build();
        try{
            Integer res = appYhOrderService.wlDel(id);
            if(res==0){
                result.setStatus(HttpStatusCode.FAILURE_CODE);
                result.setError("删除失败");
            }else{
                result.setStatus(HttpStatusCode.SUCCESS_CODE);
                result.setMsg("删除成功");
            }
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("删除失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }

    @ResponseBody
    @ApiOperation("物料添加")
    @RequestMapping(value = "/wl-create", method = RequestMethod.POST)
    public Result<Integer> wlCreate(@RequestBody WlReqVO wlReqVO) {
        Result<Integer> result = Result.build();
        try{
            Integer res = appYhOrderService.saveWL(wlReqVO);
            if(res!=null){
                result.setStatus(HttpStatusCode.SUCCESS_CODE);
                result.setMsg("创建成功");
                result.setData(res);
            }else{
                result.setStatus(HttpStatusCode.FAILURE_CODE);
                result.setError("创建失败");
            }
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("创建失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }


    @ResponseBody
    @ApiOperation("作业详情列表")
    @RequestMapping(value = "/zy-list", method = RequestMethod.GET)
    public Result<List<ZyDetailResVO>> zyDetailList(@RequestParam(value="wkId",required=true) Integer wkId,@RequestParam(value="zyType",required=true) Integer zyType) {
        Result<List<ZyDetailResVO>> result = Result.build();
        try{
            List<ZyDetailResVO> resVOList = appYhOrderService.zyDetailList(wkId,zyType);
            result.setData(resVOList);
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("获取成功");
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("获取失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }


    @ResponseBody
    @ApiOperation("删除作业")
    @RequestMapping(value = "/zy-del", method = RequestMethod.GET)
    public Result zyDel(@RequestParam(value="id",required=true) Integer id) {
        Result result = Result.build();
        try{
            Integer res = appYhOrderService.zyDel(id);
            if(res==0){
                result.setStatus(HttpStatusCode.FAILURE_CODE);
                result.setError("删除失败");
            }else{
                result.setStatus(HttpStatusCode.SUCCESS_CODE);
                result.setMsg("删除成功");
            }
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("删除失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }


    @ResponseBody
    @ApiOperation("添加作业")
    @RequestMapping(value = "/zy-create", method = RequestMethod.POST)
    public Result<Integer> zyDetailCreate(@RequestBody ZyDetailReqVO detailReqVO) {
        Result<Integer> result = Result.build();
        try{
            Integer res = appYhOrderService.saveDetails(detailReqVO);
            if(res!=null){
                result.setStatus(HttpStatusCode.SUCCESS_CODE);
                result.setMsg("创建成功");
                result.setData(res);
            }else{
                result.setStatus(HttpStatusCode.FAILURE_CODE);
                result.setError("创建失败");
            }
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("创建失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }


    @ResponseBody
    @ApiOperation("工单详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Result<YhOrderResVO> detail(@RequestParam(value="wkId",required=true) Integer wkId) {
        Result<YhOrderResVO> result = Result.build();
        try{
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            YhOrderResVO order = appYhOrderService.orderDetails(wkId);
            if(!StringUtils.isEmpty(order.getDate())){
                order.setDate(f.format(f.parse(order.getDate())));
            }
            if(!StringUtils.isEmpty(order.getStartTime())){
                order.setStartTime(f.format(f.parse(order.getStartTime())));
            }
            if(!StringUtils.isEmpty(order.getEndTime())){
                order.setEndTime(f.format(f.parse(order.getEndTime())));
            }
            result.setData(order);
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("获取成功");
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("获取失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }

    @ResponseBody
    @ApiOperation("工单列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageResult<YhOrderResVO> list(@RequestBody YhOrderQueryReqVO yhOrderQueryReqVO) {
        PageResult<YhOrderResVO> result = PageResult.build();
        try{
            IPage<YhOrderResVO> page= appYhOrderService.selectPage(yhOrderQueryReqVO);
            result.setList(page.getRecords());
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setTotal(page.getTotal());
            result.setMsg("获取成功");
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("获取失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }


    @ResponseBody
    @ApiOperation("创建工单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result<YhOrderResVO> create(@RequestBody YhOrderReqVO orderReqVO) {
        Result<YhOrderResVO> result = Result.build();
        try{
            YhOrderResVO res = appYhOrderService.save(orderReqVO);
            result.setData(res);
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("创建成功");
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("创建失败");
            log.error(ExceptionUtils.getMessage(e));
        }

        return result;
    }

    @ResponseBody
    @ApiOperation("更新")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody YhOrderUpdateReqVO orderReqVO) {
        Result result = Result.build();
        try{
            boolean res = appYhOrderService.update(orderReqVO);
            if(res){
                result.setStatus(HttpStatusCode.SUCCESS_CODE);
                result.setMsg("更新成功");
            }else{
                result.setStatus(HttpStatusCode.FAILURE_CODE);
                result.setError("更新失败");
            }

        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("更新失败");
            log.error(ExceptionUtils.getMessage(e));
        }

        return result;
    }

}
