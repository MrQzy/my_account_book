package com.qzy.mab.support;

/**
 * 自定义响应状态
 *
 * @author qzy
 */
public enum CustomHttpStatus {

    /**
     * 正常返回
     */
    OK(200, "OK"),

    /**
     * 请求错误
     */
    BAD_REQUEST(400, "Bad Request"),

    /**
     * 未登录
     */
    NOT_LOGIN(401, "Not Login"),

    /**
     * 内部服务异常
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    /**
     * 业务异常
     */
    BUSINESS_EXCEPTION(601, "Business Exceptions"),

    /**
     * 密码过期
     */
    PASSWORD_NO_WORK_EXCEPTION(602, "密码已经过期!"),

    /**
     * 密码错误次数过多
     */
    PASSWORD_ERROR_MORE_TIMRES_EXCEPTION(603, "密码错误次数过多!"),

    /**
     * 卖家登录后，没有店铺列表
     */
    NO_SHOP_LIST_EXCEPTION(620, "No Shop List"),

    /**
     * 卖家注册成功后，调用登录接口异常
     */
    REGIST_SUCCESS_LOGIN_FAILED(630, "注册成功,前往登录!");

    private final int value;

    private final String reasonPhrase;

    private CustomHttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * 返回状态码
     *
     * @return
     */
    public int value() {
        return this.value;
    }

    /**
     * 返回状态信息
     *
     * @return
     */
    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
