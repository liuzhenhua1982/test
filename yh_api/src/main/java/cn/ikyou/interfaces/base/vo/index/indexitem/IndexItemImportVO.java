package cn.ikyou.interfaces.base.vo.index.indexitem;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;


@Data
public class IndexItemImportVO {

	@Excel(name = "序号", width = 40, orderNum = "0")
    private String sn;
	@Excel(name = "指标项目名称", width = 40, orderNum = "1")
    private String name;
	@Excel(name = "单位", width = 40, orderNum = "2")
    private String unit;

}