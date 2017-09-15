package com.limitless.haulified.Haulifier.webaccess;

/*
 * A word of caution :
 * You must use this response body carefully, as this is used in BaseWA line no : 116 -128
 * When you get a vector object from a parser, which happens to be empty, i mean 'empty' being
 * Service successfully called but service returned no data, then you must not send null from 
 * the BaseParser extended class's getData() method, if done so, can lead to error being registered
 * in the above lines for variable  "response.isError = true", which can lead to wrong service
 * handeling in your dataretrieved() method in BaseActivity/Activity/FragmentActivity/BaseFragment
 * extended classes.
 */
public class Response {
    public ServiceMethods method;
    public boolean isError;
    public Object data;
    public String messageCode;
    public String message;
    public String errorMessage;
    public int statusCode;

    public Response(ServiceMethods method, boolean isError, Object data, String messageCode, String message, int statusCode, String errorMessage) {
        this.method = method;
        this.isError = isError;
        this.data = data;
        this.messageCode = messageCode;
        this.message = message;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
}
