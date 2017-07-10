package com.crdloo.loanloop.exception;

import com.crdloo.loanloop.data.Response;
import com.crdloo.loanloop.errorcode.IMessageCode;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1337477185117468301L;

    private int status;
    private String message;
    private String serverMsg;
    
    public BusinessException() {
        super("");
        this.status = 200;
        this.message = "";
        this.serverMsg = "";
    }

    public BusinessException(int status, String message, String serverMsg) {
        super(message);
        this.status = status;
        this.message = message;
        this.serverMsg = serverMsg;
    }

    public BusinessException(IMessageCode messageCode) {
        this(messageCode.code(), messageCode.message(),
                "Business error : " + messageCode.message());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServerMsg() {
        return serverMsg;
    }

    public void setServerMsg(String serverMsg) {
        this.serverMsg = serverMsg;
    }

    public Response<Object> toResponse() {
        Response<Object> response = new Response<Object>();

        response.setCode(status);
        response.setInfo(message);

        return response;
    }
}
