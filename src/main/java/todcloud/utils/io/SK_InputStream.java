package todcloud.utils.io;

/**
 * Created by zhangjianxin on 2017/8/11.
 */
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**

 * 此对象方法中的参数Byte表示类型头信息

 * @author zhangjianxin

 *

 */
public class SK_InputStream {

    public static final byte readData(InputStream in) throws IOException {
        int ch = in.read();
        if (ch < 0)
            throw new EOFException();
        return (byte) (ch);
    }

    public static final byte readByte(InputStream in, Byte type)
            throws IOException {
        if (type == null) {
            type = readData(in);
        }
        if (type == SK_StreamType.BYTE) {
            return readData(in);
        }
        throw new EOFException();
    }

    public static final short readShort(InputStream in, Byte type)
            throws IOException {
        if (type == null) {
            type = readData(in);
        }
        if (type == SK_StreamType.SHORT) {
            int _v;
            _v = ((readData(in) & 0xff) << 8);
            _v += ((readData(in) & 0xff) << 0);
            short v = (short) (_v);
            return v;
        } else {
            return readByte(in, type);
        }
    }

    public static final int readInt(InputStream in, Byte type)
            throws IOException {
        if (type == null) {
            type = readData(in);
        }
        if (type == SK_StreamType.INT) {
            int _v;
            _v = ((readData(in) & 0xff) << 24);
            _v += ((readData(in) & 0xff) << 16);
            _v += ((readData(in) & 0xff) << 8);
            _v += ((readData(in) & 0xff) << 0);
            return _v;
        } else {
            return readShort(in, type);
        }
    }

    public static final long readLong(InputStream in, Byte type)
            throws IOException {
        if (type == null) {
            type = readData(in);
        }
        if (type == SK_StreamType.LONG) {
            long _v;
            _v = ((readData(in) & 0xff) << 56);
            _v += ((readData(in) & 0xff) << 48);
            _v += ((readData(in) & 0xff) << 40);
            _v += ((readData(in) & 0xff) << 32);
            _v += ((readData(in) & 0xff) << 24);
            _v += ((readData(in) & 0xff) << 16);
            _v += ((readData(in) & 0xff) << 8);
            _v += ((readData(in) & 0xff) << 0);
            return _v;
        } else {
            return readInt(in, type);
        }
    }

    public static final float readFloat(InputStream in, Byte type)
            throws IOException {
        if (type == null) {
            type = readData(in);
        }
        if (type == SK_StreamType.FLOAT) {
            int _v;
            _v = ((readData(in) & 0xff) << 24);
            _v += ((readData(in) & 0xff) << 16);
            _v += ((readData(in) & 0xff) << 8);
            _v += ((readData(in) & 0xff) << 0);
            return Float.intBitsToFloat(_v);
        }
        throw new EOFException();
    }

    public static final double readDouble(InputStream in, Byte type)
            throws IOException {
        if (type == null) {
            type = readData(in);
        }
        if (type == SK_StreamType.DOUBLE) {
            long high;
            high = ((readData(in) & 0xff) << 56);
            high += ((readData(in) & 0xff) << 48);
            high += ((readData(in) & 0xff) << 40);
            high += ((readData(in) & 0xff) << 32);
            long low;
            low = ((readData(in) & 0xff) << 24);
            low += ((readData(in) & 0xff) << 16);
            low += ((readData(in) & 0xff) << 8);
            low += ((readData(in) & 0xff) << 0);
            long _v = (high << 32) + (0xffffffffL & low);
            return Double.longBitsToDouble(_v);
        } else {
            return readFloat(in, type);
        }
    }

    public static final String readString(InputStream in, Byte type)
            throws Exception {
        if (type == null) {
            type = readData(in);
        }
        if (type == SK_StreamType.STRING) {
            int l = readInt(in, null);
            byte[] _byte = new byte[l];
            in.read(_byte, 0, l);
            return new String(_byte, SK_StreamType.UTF8);
        }
        throw new EOFException();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final Map readMap(InputStream in, Byte type) throws Exception {
        if (type == null) {
            type = readData(in);
        }
        if (type == SK_StreamType.MAP) {
            int l = readInt(in, null);
            Map map = new HashMap();
            for (int i = 0; i < l; i++) {
                map.put(readObject(in), readObject(in));
            }
            return map;
        }
        throw new EOFException();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final List readList(InputStream in, Byte type)
            throws Exception {
        if (type == null) {
            type = readData(in);
        }

        if (type == SK_StreamType.LIST) {
            int l = readInt(in, null);
            List list = new ArrayList();
            for (int i = 0; i < l; i++) {
                list.add(readObject(in));
            }
            return list;
        }
        throw new EOFException();
    }

    public static final Date readDate(InputStream in, Byte type)
            throws Exception {
        if (type == null) {
            type = readData(in);
        }
        if (type == SK_StreamType.DATE) {
            long _v;
            _v = ((readData(in) & 0xff) << 56);
            _v += ((readData(in) & 0xff) << 48);
            _v += ((readData(in) & 0xff) << 40);
            _v += ((readData(in) & 0xff) << 32);
            _v += ((readData(in) & 0xff) << 24);
            _v += ((readData(in) & 0xff) << 16);
            _v += ((readData(in) & 0xff) << 8);
            _v += ((readData(in) & 0xff) << 0);
            return new Date(_v);
        }
        throw new EOFException();
    }

    public static final boolean readBoolean(InputStream in, Byte type)
            throws IOException {
        if (type == null) {
            type = readData(in);
        }
        if (type == SK_StreamType.BOOLEAN) {
            byte b = readData(in);
            return b == ((byte) 1) ? true : false;
        }
        throw new EOFException();
    }

    public static final Object readObject(InputStream in) throws Exception {
        byte type = readData(in);
        switch (type) {
            case SK_StreamType.BYTE:
                return readByte(in, type);
            case SK_StreamType.SHORT:
                return readShort(in, type);
            case SK_StreamType.INT:
                return readInt(in, type);
            case SK_StreamType.LONG:
                return readLong(in, type);
            case SK_StreamType.FLOAT:
                return readFloat(in, type);
            case SK_StreamType.DOUBLE:
                return readDouble(in, type);
            case SK_StreamType.STRING:
                return readString(in, type);
            case SK_StreamType.MAP:
                return readMap(in, type);
            case SK_StreamType.LIST:
                return readList(in, type);
            case SK_StreamType.DATE:
                return readDate(in, type);
            case SK_StreamType.BOOLEAN:
                return readBoolean(in, type);
            default:
                throw new EOFException();
        }
    }
}
