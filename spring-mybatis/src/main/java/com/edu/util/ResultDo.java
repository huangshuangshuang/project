package com.edu.util;

import java.io.Serializable;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/3/8
 * Description:
 * <author>           <time>             <version>        <desc>
 * 黄双双           2016/3/8              00000001        创建文件
 */
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
