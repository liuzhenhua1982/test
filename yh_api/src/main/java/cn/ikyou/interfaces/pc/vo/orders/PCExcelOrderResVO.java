package cn.ikyou.interfaces.pc.vo.orders;

import com.alibaba.excel.annotation.ExcelProperty;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class PCExcelOrderResVO {
    @ColumnWidth(6)
    @ExcelProperty(value = "序号", index = 0)
    private Integer number;
    @ColumnWidth(16)
    @ExcelProperty(value = "工作区名", index = 1)
    private String orgName;
    @ColumnWidth(16)
    @ExcelProperty(value = "作业项目", index = 2)
    private String workItem;
    @ColumnWidth(16)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "日期", index = 3)
    private String date;
    @ColumnWidth(40)
    @ExcelProperty(value = "作业地点", index = 4)
    private String address;
    @ColumnWidth(15)
    @ExcelProperty(value = "天气", index = 5)
    private String weather;
    @ColumnWidth(15)
    @ExcelProperty(value = "工长", index = 6)
    private String workLeaderQm;
    @ColumnWidth(20)
    @ExcelProperty(value = "开始时间", index = 7)
    private String startTime;
    @ColumnWidth(20)
    @ExcelProperty(value = "结束时间", index = 8)
    private String endTime;
    @ColumnWidth(10)
    @ExcelProperty(value = "状态", index = 9)
    private String status;
    @ColumnWidth(10)
    @ExcelProperty(value = "下拉框", index = 10)
    private List<String> comList;
}
