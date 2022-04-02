package cn.ikyou.interfaces.base.vo.user.req;

public class UserUpdateReqVO extends UserAddReqVO{

    private Integer userId;

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
