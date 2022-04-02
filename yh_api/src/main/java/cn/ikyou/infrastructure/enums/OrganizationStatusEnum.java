package cn.ikyou.infrastructure.enums;

/**
 * 通用状态枚举
 */
public enum OrganizationStatusEnum {

    STATUS_1("STATUS_1","正常"),
    STATUS_2("STATUS_2","禁用");

    OrganizationStatusEnum(String status, String name){
        this.status=status;
        this.name=name;
    }

    private String status;
    private String name;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
