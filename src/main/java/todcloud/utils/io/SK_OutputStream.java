package todcloud.utils.io;

/**
 * Created by zhangjianxin on 2017/8/11.
 */
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class SK_OutputStream {

    public static final void writeData(OutputStream os, byte v)
            throws IOException {
        os.write(v);
    }

    public static final void writeData(OutputStream os, byte[] v)
            throws IOException {
        os.write(v);
    }

    public static final void writeByte(OutputStream os, byte v)
            throws IOException {
        writeData(os, SK_StreamType.BYTE);
        writeData(os, v);
    }

    public static final void writeShort(OutputStream os, short v)
            throws IOException {
        if (v >= SK_StreamType.SHORT_MIN_VALUE
                && v <= SK_StreamType.SHORT_MAX_VALUE) {
            writeData(os, SK_StreamType.SHORT);
            writeData(os, (byte) ((v >> 8) & 0xff));
            writeData(os, (byte) ((v >> 0) & 0xff));
        } else {
            writeByte(os, (byte) v);
        }
    }

    public static final void writeInt(OutputStream os, int v)
            throws IOException {
        if (v >= SK_StreamType.INT_MIN_VALUE
                && v <= SK_StreamType.INT_MAX_VALUE) {
            writeData(os, SK_StreamType.INT);
            writeData(os, (byte) ((v >> 24) & 0xff));
            writeData(os, (byte) ((v >> 16) & 0xff));
            writeData(os, (byte) ((v >> 8) & 0xff));
            writeData(os, (byte) ((v >> 0) & 0xff));
        } else {
            writeShort(os, (short) v);
        }
    }

    public static final void writeLong(OutputStream os, long v)
            throws IOException {
        if (v >= SK_StreamType.LONG_MIN_VALUE
                && v <= SK_StreamType.LONG_MAX_VALUE) {
            writeData(os, SK_StreamType.LONG);
            writeData(os, (byte) ((v >> 56) & 0xff));
            writeData(os, (byte) ((v >> 48) & 0xff));
            writeData(os, (byte) ((v >> 40) & 0xff));
            writeData(os, (byte) ((v >> 32) & 0xff));
            writeData(os, (byte) ((v >> 24) & 0xff));
            writeData(os, (byte) ((v >> 16) & 0xff));
            writeData(os, (byte) ((v >> 8) & 0xff));
            writeData(os, (byte) ((v >> 0) & 0xff));
        } else {
            writeInt(os, (int) v);
        }
    }

    public static final void writeFloat(OutputStream os, float v)
            throws IOException {
        writeData(os, SK_StreamType.FLOAT);
        int _v = Float.floatToIntBits(v);
        writeData(os, (byte) ((_v >> 24) & 0xff));
        writeData(os, (byte) ((_v >> 16) & 0xff));
        writeData(os, (byte) ((_v >> 8) & 0xff));
        writeData(os, (byte) ((_v >> 0) & 0xff));
    }

    public static final void writeDouble(OutputStream os, double v)
            throws IOException {
        if (v >= SK_StreamType.FLOAT_MIN_VALUE
                && v <= SK_StreamType.FLOAT_MAX_VALUE) {
            writeData(os, SK_StreamType.DOUBLE);
            long _v = Double.doubleToLongBits(v);
            writeData(os, (byte) ((_v >> 56) & 0xff));
            writeData(os, (byte) ((_v >> 48) & 0xff));
            writeData(os, (byte) ((_v >> 40) & 0xff));
            writeData(os, (byte) ((_v >> 32) & 0xff));
            writeData(os, (byte) ((_v >> 24) & 0xff));
            writeData(os, (byte) ((_v >> 16) & 0xff));
            writeData(os, (byte) ((_v >> 8) & 0xff));
            writeData(os, (byte) ((_v >> 0) & 0xff));
        } else {
            writeFloat(os, (float) v);
        }
    }

    public static final void writeString(OutputStream os, String v)
            throws Exception {
        byte[] _byte = v.getBytes(SK_StreamType.UTF8);
        int l = _byte.length;
        writeData(os, SK_StreamType.STRING);
        writeInt(os, l);
        writeData(os, _byte);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final void writeMap(OutputStream os, Map map)
            throws Exception {
        Set<Entry> entrys = map.entrySet();
        Object key = null;
        Object value = null;
        writeData(os, SK_StreamType.MAP);
        writeInt(os, map.size());
        for (Entry enter : entrys) {
            key = enter.getKey();
            writeObject(os, key);
            value = enter.getValue();
            writeObject(os, value);
        }

    }

    @SuppressWarnings("rawtypes")
    public static final void writeList(OutputStream os, List list)
            throws Exception {
        writeData(os, SK_StreamType.LIST);
        writeInt(os, list.size());
        for (Object object : list) {
            writeObject(os, object);
        }
    }

    public static final void writeDate(OutputStream os, Date date)
            throws Exception {
        writeData(os, SK_StreamType.DATE);
        long v = date.getTime();
        writeData(os, (byte) ((v >> 56) & 0xff));
        writeData(os, (byte) ((v >> 48) & 0xff));
        writeData(os, (byte) ((v >> 40) & 0xff));
        writeData(os, (byte) ((v >> 32) & 0xff));
        writeData(os, (byte) ((v >> 24) & 0xff));
        writeData(os, (byte) ((v >> 16) & 0xff));
        writeData(os, (byte) ((v >> 8) & 0xff));
        writeData(os, (byte) ((v >> 0) & 0xff));
    }

    public static final void writeBoolean(OutputStream os, boolean bool)
            throws IOException {
        writeData(os, SK_StreamType.BOOLEAN);
        if (bool)
            writeData(os, (byte) 1);
        else
            writeData(os, (byte) 0);
    }

    @SuppressWarnings("rawtypes")
    public static final void writeObject(OutputStream os, Object object)
            throws Exception {
        if (object instanceof Byte) {
            writeByte(os, (byte) object);
        } else if (object instanceof Short) {
            writeShort(os, (short) object);
        } else if (object instanceof Integer) {
            writeInt(os, (int) object);
        } else if (object instanceof Long) {
            writeLong(os, (long) object);
        } else if (object instanceof Float) {
            writeFloat(os, (float) object);
        } else if (object instanceof Double) {
            writeDouble(os, (double) object);
        } else if (object instanceof String) {
            writeString(os, (String) object);
        } else if (object instanceof Map) {
            writeMap(os, (Map) object);
        } else if (object instanceof List) {
            writeList(os, (List) object);
        } else if (object instanceof Date) {
            writeDate(os, (Date) object);
        } else if (object instanceof Boolean) {
            writeBoolean(os, (boolean) object);
        }
    }
}