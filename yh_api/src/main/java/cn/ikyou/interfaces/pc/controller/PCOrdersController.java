package cn.ikyou.interfaces.pc.controller;

import cn.ikyou.application.business.PCYhOrderService;
import cn.ikyou.domain.business.entity.TWkOrders;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.enums.OrderStatusEnum;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.inter.SpinnerWriteHandler;
import cn.ikyou.infrastructure.response.PageResult;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.pc.vo.orders.*;
import cn.ikyou.interfaces.pc.wrapper.PCOrdersWrapper;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "工单查询")
@AllArgsConstructor
@RequestMapping("/api/pc/orders")
public class PCOrdersController {

    private final PCYhOrderService pcYhOrderService;
    private final PCOrdersWrapper pcOrdersWrapper;
    private final SpinnerWriteHandler spinnerWriteHandler;
    @ResponseBody
    @ApiOperation("查询记录")
    @RequestMapping(value = "/searchPage", method = RequestMethod.POST)
    public PageResult<PCOrdersResVO> searchPage(@RequestBody  PCOrdersReqVO reqVO) {
        PageResult<PCOrdersResVO> result = PageResult.build();
        try{
            IPage<PCOrdersResVO> tWkOrdersIPage = pcYhOrderService.selectPage(reqVO);
            result.setList(tWkOrdersIPage.getRecords());
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setTotal(tWkOrdersIPage.getTotal());
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
    @ApiOperation("工单详细")
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public Result<PCOrderDetailsResVO> details(@RequestParam(value="wkId",required=true) Integer wkId) {
        Result<PCOrderDetailsResVO> result = Result.build();
        try{
            PCOrderDetailsResVO res = pcYhOrderService.details(wkId);
            result.setData(res);
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
    @ApiOperation("导出excel")
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void download(@RequestBody  PCOrdersReqVO reqVO) {
        HttpServletResponse response =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        try{
            List<TWkOrders> list = pcYhOrderService.selectList(reqVO);
            List<PCExcelOrderResVO> resVOList = new ArrayList<>();
            Integer count=1;
            for (TWkOrders orders : list) {
                PCExcelOrderResVO resVO = new PCExcelOrderResVO();
                BeanUtils.copyProperties(orders,resVO);
                StringBuffer str = new StringBuffer();
                str.append("【").append(orders.getWorkGs()).append("-").append(orders.getWorkFx()).append("】")
                        .append(" ").append(orders.getWorkLoationStart()).append("->").append(orders.getWorkLocatonEnd());
                resVO.setAddress(str.toString());

                if(OrderStatusEnum.COMPLETE.getStatus().equals(resVO.getStatus())){
                    resVO.setStatus("归档");
                }else if(OrderStatusEnum.EXECUTE.getStatus().equals(resVO.getStatus())){
                    resVO.setStatus("进行中");
                }else if (OrderStatusEnum.SUCCESS.getStatus().equals(resVO.getStatus())){
                    resVO.setStatus("正常");
                }

                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    if(!StringUtils.isEmpty(resVO.getDate())){
                        String[] yyyymmnn = resVO.getDate().split("-");
                        String year = yyyymmnn[0];
                        String month = yyyymmnn[1];
                        String day = yyyymmnn[2].substring(0,2);
                        resVO.setDate(String.format("%s年%s月%s日",year,month,day));
                    }

                resVO.setNumber(count++);
                resVOList.add(resVO);
            }
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("工单记录", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            //xls 一个 Sheet 最多支持 65535 行，如果数据要在一个 Sheet 中可以通过指定 ExcelFormat.Xlsx 来导出
            EasyExcel.write(response.getOutputStream(), PCExcelOrderResVO.class).registerWriteHandler(spinnerWriteHandler).sheet("工单记录").doWrite(resVOList);
        }catch (ServiceCheckException e){
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            log.error(ExceptionUtils.getMessage(e));
        }
    }
    @ResponseBody
    @ApiOperation("工作区名称")
    @RequestMapping(value = "/org-list", method = RequestMethod.GET)
    public Result<List<PCOrgResVO>> OrgList() {
        Result<List<PCOrgResVO>> result = Result.build();
        try{
            List<Organization> organizations = pcYhOrderService.orgList();
            result.setData(pcOrdersWrapper.orgListToVO(organizations));
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
    @ApiOperation("删除")
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public Result del(@RequestParam(value="id",required=true) Integer id) {
        Result result = Result.build();
        try{
            boolean r = pcYhOrderService.del(id);
            if(!r){
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
    @ApiOperation("归档")
    @RequestMapping(value = "/archive", method = RequestMethod.GET)
    public Result archive(@RequestParam(value="id",required=true) Integer id) {
        Result result = Result.build();
        try{
            boolean r = pcYhOrderService.archive(id);
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("归档成功");
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("归档失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }
    @ResponseBody
    @ApiOperation("生成报表")
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public Result report(@RequestParam(value="Id",required=true) Integer id) {
        Result result = Result.build();
        try{
            String path = pcYhOrderService.report(id);
            result.setData(path);
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("生成成功");
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("生成失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }
}
