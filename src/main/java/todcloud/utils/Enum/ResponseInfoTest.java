package todcloud.utils.Enum;

/**
 * Created by zhangjianxin on 2017/6/29.
 */
public class ResponseInfoTest {
    public static void main(String[] args) {
        System.out.println(ResponseInfo.SUCCESS.getReturnCode());
        String code = ResponseInfo.SUCCESS.getReturnCode();
        System.out.println(ResponseInfo.getResponseMsg(code));
    }
}
