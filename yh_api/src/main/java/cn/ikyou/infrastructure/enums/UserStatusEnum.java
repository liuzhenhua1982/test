package cn.ikyou.infrastructure.enums;

/**
 * 通用状态枚举
 */
public enum UserStatusEnum {

    STATUS_1(0,"正常"),
    STATUS_2(1,"禁用");

    UserStatusEnum(Integer status, String name){
        this.status=status;
        this.name=name;
    }

    private Integer status;
    private String name;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
