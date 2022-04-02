package cn.ikyou.interfaces.app.vo.version;

import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/20
 * 说明：
 */
@Data
public class AppVersionReqVO {

    private String method;
    private String version;
    private String name;
    private String code;
    private String ts;
    private String platform;
}
