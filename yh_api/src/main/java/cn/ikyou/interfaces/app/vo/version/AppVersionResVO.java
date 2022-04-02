package cn.ikyou.interfaces.app.vo.version;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/20
 * 说明：
 */
@Data
public class AppVersionResVO {
    @JsonProperty(value="update_flag")
    private Integer updateFlag;
    @JsonProperty(value="update_url")
    private String updateUrl;
    @JsonProperty(value="forceupdate")
    private Integer forceupdate;
    @JsonProperty(value="update_tips")
    private String updateTips;
    @JsonProperty(value="version")
    private String version;
    @JsonProperty(value="size")
    private Integer size;
    @JsonProperty(value="wgt_flag")
    private Integer wgtFlag;
    @JsonProperty(value="wgt_url")
    private String wgtUrl;
}
