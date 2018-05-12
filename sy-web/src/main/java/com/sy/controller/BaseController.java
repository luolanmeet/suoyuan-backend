package com.sy.controller;

import com.object.code.ErrorCode;
import com.object.resp.BaseResp;

/**
 *
 * @author cck
 */
public class BaseController {

    public static final BaseResp OK = BaseResp.builder().code(ErrorCode.OK).build();
    /**
     * 构造成功响应
     * @return
     */
    public BaseResp success() {
        return OK;
    }

    /**
     * 构造成功响应
     * @param data    要返回的数据
     * @return
     */
    public BaseResp success(Object data) {
        return BaseResp.builder().code(ErrorCode.OK).data(data).build();
    }

    /**
     * 构造失败响应
     * @param code 错误状态码
     * @param cause 出错原因
     * @return
     */
    public BaseResp failure(int resultCode, String cause) {
        return BaseResp.builder().code(resultCode).cause(cause).build();
    }

    /**
     * 构造失败响应
     * @param code 错误状态码
     * @return
     */
    public BaseResp failure(int resultCode) {
        return BaseResp.builder().code(resultCode).build();
    }

    /**
     * 构造失败响应, 状态码为: <code>WebErrorCode.INTERNAL_ERROR</code>
     * @param cause 出错原因
     * @return
     */
    public BaseResp failure(String cause) {
        return BaseResp.builder().code(ErrorCode.INTERNAL_ERROR).cause(cause).build();
    }

    /**
     * 构造失败响应, 状态码为: <code>WebErrorCode.INTERNAL_ERROR</code>
     * @return
     */
    public BaseResp failure() {
        return BaseResp.builder().code(ErrorCode.INTERNAL_ERROR).build();
    }
}
