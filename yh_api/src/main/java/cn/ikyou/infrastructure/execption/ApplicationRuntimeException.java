package cn.ikyou.infrastructure.execption;

public class ApplicationRuntimeException extends RuntimeException{

    private static final long serialVersionUID = -8230857598243800400L;

    public ApplicationRuntimeException() {

    }

    public ApplicationRuntimeException(String msg) {
        super(msg);
    }

}