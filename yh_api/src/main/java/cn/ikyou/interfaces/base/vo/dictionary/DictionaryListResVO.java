package cn.ikyou.interfaces.base.vo.dictionary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "字典列表", description = "字典列表")
public class DictionaryListResVO {

    @ApiModelProperty(value="id")
    private Integer id;
    @ApiModelProperty(value="上级ID")
    private Integer pId;
    @ApiModelProperty(value="字典名称")
    private String name;
    @ApiModelProperty(value="字典编码")
    private String code;

}