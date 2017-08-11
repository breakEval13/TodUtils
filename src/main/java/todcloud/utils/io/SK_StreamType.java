package todcloud.utils.io;


import java.nio.charset.Charset;
/**
 * Created by zhangjianxin on 2017/8/11.
 */
public class SK_StreamType {
    public static final byte BYTE = 1;
    public static final byte SHORT = 2;
    public static final byte INT = 3;
    public static final byte LONG = 4;
    public static final byte CHAR = 5;
    public static final byte FLOAT = 6;
    public static final byte DOUBLE = 7;
    public static final byte BOOLEAN = 8;
    public static final byte MAP = 9;
    public static final byte LIST = 10;
    public static final byte STRING = 11;
    public static final byte DATE = 12;

    // byte

    public static final byte BYTE_MIN_VALUE = -128;
    public static final byte BYTE_MAX_VALUE = 127;
    // short

    public static final short SHORT_MIN_VALUE = -32768;
    public static final short SHORT_MAX_VALUE = 32767;
    // int

    public static final int INT_MIN_VALUE = 0x80000000;
    public static final int INT_MAX_VALUE = 0x7fffffff;
    // long

    public static final long LONG_MIN_VALUE = 0x8000000000000000L;
    public static final long LONG_MAX_VALUE = 0x7fffffffffffffffL;
    // float

    public static final float FLOAT_MIN_VALUE = 0x0.000002P-126f; // 1.4e-45f

    public static final float FLOAT_MAX_VALUE = 0x1.fffffeP+127f; // 3.4028235e+38f

    // double

    public static final double DOUBLE_MIN_VALUE = 0x0.0000000000001P-1022; // 4.9e-324

    public static final double DOUBLE_MAX_VALUE = 0x1.fffffffffffffP+1023; // 1.7976931348623157e+308


    public static final Charset UTF8 = Charset.forName("UTF-8");
}