package cn.ikyou.interfaces.base.vo.dictionary;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 开发人：石聪辉
 * 开发时间：2021/12/6
 * 说明：
 */
@Data
public class DictionaryMapResVO {
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "类型下的字典")
    private List<DictionaryListResVO> dict;
}
