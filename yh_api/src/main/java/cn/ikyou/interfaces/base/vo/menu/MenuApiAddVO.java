package cn.ikyou.interfaces.base.vo.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 开发人：石聪辉
 * 开发时间：2021/9/9
 * 说明：
 */
@Data
public class MenuApiAddVO {

    @ApiModelProperty(value = "菜单ID")
    private Integer menuId;
    @ApiModelProperty(value = "api id集合")
    private List<Integer> apiId;
}
