package cn.ikyou.interfaces.pc.vo.car;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PCCarsResVO {
    @ApiModelProperty(value = "car ID")
    private Integer id;
    @ApiModelProperty("商业品牌")
    private String businessBrand;
    @ApiModelProperty("车牌号")
    private String licenseCar;
    @ApiModelProperty("机构ID")
    private Integer orgId;
    @ApiModelProperty("机构名称")
    private String orgName;
    @ApiModelProperty("创建人")
    private Integer createId;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新人")
    private Integer updateId;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("版本号")
    private Integer version;
}
