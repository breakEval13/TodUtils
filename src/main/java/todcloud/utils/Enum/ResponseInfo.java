package todcloud.utils.Enum;

/**
 * Created by zhangjianxin on 2017/6/29.
 */
public enum ResponseInfo {
    SERVER_ERROR("返回码异常", "6666"),
    SUCCESS("success", "200"), ERROR("Not Fount", "404"), GATEWAY("Fuck Server", "502"), CREATE("Created", "201");


    private String code;
    private String msg;
    private ResponseInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getReturnCode() {
        return code;
    }
    public String getReturnMsg() {
        return msg;
    }

    public static String getResponseMsg(String code){
        for(ResponseInfo responseInfo:ResponseInfo.values()){
            if(code.equals(responseInfo.getReturnCode())){
                return responseInfo.getReturnMsg();
            }
        }
        return SERVER_ERROR.getReturnMsg();
    }
}