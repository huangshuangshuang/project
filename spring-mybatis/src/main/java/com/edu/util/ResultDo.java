package com.edu.util;

import java.io.Serializable;

public class ResultDo<T> implements Serializable{
    private static final long serialVersionUID=684874614326656456L;
    private T data;
    private boolean isSuccess=false;
    private String errorMsg;
    private String errorCode;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
