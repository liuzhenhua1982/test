package cn.ikyou.infrastructure.inter;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class SpinnerWriteHandler implements SheetWriteHandler
{

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder)
    {

    }
            //冒泡排序


    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder)
    {
        //String[] ageTypes = new String[] {"0 - 14", "15 - 25", "26 - 50", "51 - ~"};
        String[] schoolTypes = new String[] {"清华大学", "北京大学", "郑州大学", "南京大学"};
        Map<Integer, String[]> mapDropDown = new HashMap<>();
        // 这里的key值 对应导出列的顺序 从0开始
        // mapDropDown.put(1, ageTypes);
        mapDropDown.put(10, schoolTypes);
        Sheet sheet = writeSheetHolder.getSheet();
        /// 开始设置下拉框
        DataValidationHelper helper = sheet.getDataValidationHelper();// 设置下拉框
        for (Map.Entry<Integer, String[]> entry : mapDropDown.entrySet())
        {
            /*** 起始行、终止行、起始列、终止列 **/
            CellRangeAddressList addressList = new CellRangeAddressList(1, 65536, entry.getKey(), entry.getKey());
            /*** 设置下拉框数据 **/
            DataValidationConstraint constraint = helper.createExplicitListConstraint(entry.getValue());
            DataValidation dataValidation = helper.createValidation(constraint, addressList);
            /*** 处理Excel兼容性问题 **/
            if (dataValidation instanceof XSSFDataValidation)
            {
                dataValidation.setSuppressDropDownArrow(true);
                dataValidation.setShowErrorBox(true);
            }
            else
            {
                dataValidation.setSuppressDropDownArrow(false);
            }
            sheet.addValidationData(dataValidation);
        }

    }
}
