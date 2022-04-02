package cn.ikyou.interfaces.pc.vo.dict;

import cn.ikyou.domain.dictionary.entity.Dictionary;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PCDictResVO {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "上级ID")
    private Integer pId;
    @ApiModelProperty(value = "组织机构ID")
    private Integer orgId;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "机构名称")
    private String orgName;
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
