package cn.ikyou.infrastructure.enums;

public enum OrderStatusEnum {
    SUCCESS("SUCCESS","正常"),
    EXECUTE("EXECUTE","进行中"),
    COMPLETE("COMPLETE","归档");

    OrderStatusEnum(String status, String name) {
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
