package cn.ikyou.interfaces.pc.vo.meterial;

import cn.ikyou.infrastructure.request.PageQuery;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PCMeterialResVO {
    @ApiModelProperty(value = "物料ID")
    private Integer id;
    @ApiModelProperty(value = "组织机构ID")
    private Integer orgId;
    @ApiModelProperty(value = "组织机构名称")
    private String orgName;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "数量")
    private Integer amount;
    @ApiModelProperty(value = "操作类型（消耗，修复）")
    private Integer operationType;
    @ApiModelProperty(value = "物品规格型号")
    private String model;
    @ApiModelProperty(value = "单位名称")
    private String unitName;
    @ApiModelProperty(value = "创建人ID")
    private Integer createId;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新ID")
    private Integer updateId;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "版本号")
    private Integer version;
    
}
