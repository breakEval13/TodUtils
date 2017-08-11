package todcloud.utils.io;

/**
 * Created by zhangjianxin on 2017/8/11.
 */
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SK_Buffer {

    public static final byte readData(ByteBuffer buf) {
        return buf.get();
    }

    public static final byte readByte(ByteBuffer buf, Byte type)
            throws SK_BufferException {
        if (type == null) {
            type = readData(buf);
        }
        if (type == SK_StreamType.BYTE) {
            return readData(buf);
        }
        throw new SK_BufferException();
    }

    public static final short readShort(ByteBuffer buf, Byte type)
            throws SK_BufferException {
        if (type == null) {
            type = readData(buf);
        }
        if (type == SK_StreamType.SHORT) {
            int _v;
            _v = ((readData(buf) & 0xff) << 8);
            _v += ((readData(buf) & 0xff) << 0);
            short v = (short) (_v);
            return v;
        } else {
            return readByte(buf, type);
        }
    }

    public static final int readInt(ByteBuffer buf, Byte type)
            throws SK_BufferException {
        if (type == null) {
            type = readData(buf);
        }
        if (type == SK_StreamType.INT) {
            int _v;
            _v = ((readData(buf) & 0xff) << 24);
            _v += ((readData(buf) & 0xff) << 16);
            _v += ((readData(buf) & 0xff) << 8);
            _v += ((readData(buf) & 0xff) << 0);
            return _v;
        } else {
            return readShort(buf, type);
        }
    }

    public static final long readLong(ByteBuffer buf, Byte type)
            throws SK_BufferException {
        if (type == null) {
            type = readData(buf);
        }
        if (type == SK_StreamType.LONG) {
            long _v;
            _v = ((readData(buf) & 0xff) << 56);
            _v += ((readData(buf) & 0xff) << 48);
            _v += ((readData(buf) & 0xff) << 40);
            _v += ((readData(buf) & 0xff) << 32);
            _v += ((readData(buf) & 0xff) << 24);
            _v += ((readData(buf) & 0xff) << 16);
            _v += ((readData(buf) & 0xff) << 8);
            _v += ((readData(buf) & 0xff) << 0);
            return _v;
        } else {
            return readInt(buf, type);
        }
    }

    public static final float readFloat(ByteBuffer buf, Byte type)
            throws SK_BufferException {
        if (type == null) {
            type = readData(buf);
        }
        if (type == SK_StreamType.FLOAT) {
            int _v;
            _v = ((readData(buf) & 0xff) << 24);
            _v += ((readData(buf) & 0xff) << 16);
            _v += ((readData(buf) & 0xff) << 8);
            _v += ((readData(buf) & 0xff) << 0);
            return Float.intBitsToFloat(_v);
        }
        throw new SK_BufferException();
    }

    public static final double readDouble(ByteBuffer buf, Byte type)
            throws SK_BufferException {
        if (type == null) {
            type = readData(buf);
        }
        if (type == SK_StreamType.DOUBLE) {
            long high;
            high = ((readData(buf) & 0xff) << 56);
            high += ((readData(buf) & 0xff) << 48);
            high += ((readData(buf) & 0xff) << 40);
            high += ((readData(buf) & 0xff) << 32);
            long low;
            low = ((readData(buf) & 0xff) << 24);
            low += ((readData(buf) & 0xff) << 16);
            low += ((readData(buf) & 0xff) << 8);
            low += ((readData(buf) & 0xff) << 0);
            long _v = (high << 32) + (0xffffffffL & low);
            return Double.longBitsToDouble(_v);
        } else {
            return readFloat(buf, type);
        }
    }

    public static final String readString(ByteBuffer buf, Byte type)
            throws SK_BufferException {
        if (type == null) {
            type = readData(buf);
        }
        if (type == SK_StreamType.STRING) {
            int l = readInt(buf, null);
            byte[] _byte = new byte[l];
            buf.get(_byte, 0, l);
            return new String(_byte, SK_StreamType.UTF8);
        }
        throw new SK_BufferException();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final Map readMap(ByteBuffer buf, Byte type)
            throws SK_BufferException {
        if (type == null) {
            type = readData(buf);
        }
        if (type == SK_StreamType.MAP) {
            int l = readInt(buf, null);
            Map map = new HashMap();
            for (int i = 0; i < l; i++) {
                map.put(readObject(buf), readObject(buf));
            }
            return map;
        }
        throw new SK_BufferException();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final List readList(ByteBuffer buf, Byte type)
            throws SK_BufferException {
        if (type == null) {
            type = readData(buf);
        }

        if (type == SK_StreamType.LIST) {
            int l = readInt(buf, null);
            List list = new ArrayList();
            for (int i = 0; i < l; i++) {
                list.add(readObject(buf));
            }
            return list;
        }
        throw new SK_BufferException();
    }

    public static final Date readDate(ByteBuffer buf, Byte type)
            throws SK_BufferException {
        if (type == null) {
            type = readData(buf);
        }
        if (type == SK_StreamType.DATE) {
            long _v;
            _v = ((readData(buf) & 0xff) << 56);
            _v += ((readData(buf) & 0xff) << 48);
            _v += ((readData(buf) & 0xff) << 40);
            _v += ((readData(buf) & 0xff) << 32);
            _v += ((readData(buf) & 0xff) << 24);
            _v += ((readData(buf) & 0xff) << 16);
            _v += ((readData(buf) & 0xff) << 8);
            _v += ((readData(buf) & 0xff) << 0);
            return new Date(_v);
        }
        throw new SK_BufferException();
    }

    public static final boolean readBoolean(ByteBuffer buf, Byte type)
            throws SK_BufferException {
        if (type == null) {
            type = readData(buf);
        }
        if (type == SK_StreamType.BOOLEAN) {
            byte b = readData(buf);
            return b == ((byte) 1) ? true : false;
        }
        throw new SK_BufferException();
    }

    public static final Object readObject(ByteBuffer buf)
            throws SK_BufferException {
        byte type = readData(buf);
        switch (type) {
            case SK_StreamType.BYTE:
                return readByte(buf, type);
            case SK_StreamType.SHORT:
                return readShort(buf, type);
            case SK_StreamType.INT:
                return readInt(buf, type);
            case SK_StreamType.LONG:
                return readLong(buf, type);
            case SK_StreamType.FLOAT:
                return readFloat(buf, type);
            case SK_StreamType.DOUBLE:
                return readDouble(buf, type);
            case SK_StreamType.STRING:
                return readString(buf, type);
            case SK_StreamType.MAP:
                return readMap(buf, type);
            case SK_StreamType.LIST:
                return readList(buf, type);
            case SK_StreamType.DATE:
                return readDate(buf, type);
            case SK_StreamType.BOOLEAN:
                return readBoolean(buf, type);
            default:
                throw new SK_BufferException();
        }
    }

    // ------------------------------------------------


    public static final void writeData(ByteBuffer buf, byte v)
            throws SK_BufferException {
        buf.put(v);
    }

    public static final void writeData(ByteBuffer buf, byte[] v)
            throws SK_BufferException {
        buf.put(v);
    }

    public static final void writeByte(ByteBuffer buf, byte v)
            throws SK_BufferException {
        writeData(buf, SK_StreamType.BYTE);
        writeData(buf, v);
    }

    public static final void writeShort(ByteBuffer buf, short v)
            throws SK_BufferException {
        if (v >= SK_StreamType.SHORT_MIN_VALUE
                && v <= SK_StreamType.SHORT_MAX_VALUE) {
            writeData(buf, SK_StreamType.SHORT);
            writeData(buf, (byte) ((v >> 8) & 0xff));
            writeData(buf, (byte) ((v >> 0) & 0xff));
        } else {
            writeByte(buf, (byte) v);
        }
    }

    public static final void writeInt(ByteBuffer buf, int v)
            throws SK_BufferException {
        if (v >= SK_StreamType.INT_MIN_VALUE
                && v <= SK_StreamType.INT_MAX_VALUE) {
            writeData(buf, SK_StreamType.INT);
            writeData(buf, (byte) ((v >> 24) & 0xff));
            writeData(buf, (byte) ((v >> 16) & 0xff));
            writeData(buf, (byte) ((v >> 8) & 0xff));
            writeData(buf, (byte) ((v >> 0) & 0xff));
        } else {
            writeShort(buf, (short) v);
        }
    }

    public static final void writeLong(ByteBuffer buf, long v)
            throws SK_BufferException {
        if (v >= SK_StreamType.LONG_MIN_VALUE
                && v <= SK_StreamType.LONG_MAX_VALUE) {
            writeData(buf, SK_StreamType.LONG);
            writeData(buf, (byte) ((v >> 56) & 0xff));
            writeData(buf, (byte) ((v >> 48) & 0xff));
            writeData(buf, (byte) ((v >> 40) & 0xff));
            writeData(buf, (byte) ((v >> 32) & 0xff));
            writeData(buf, (byte) ((v >> 24) & 0xff));
            writeData(buf, (byte) ((v >> 16) & 0xff));
            writeData(buf, (byte) ((v >> 8) & 0xff));
            writeData(buf, (byte) ((v >> 0) & 0xff));
        } else {
            writeInt(buf, (int) v);
        }
    }

    public static final void writeFloat(ByteBuffer buf, float v)
            throws SK_BufferException {
        writeData(buf, SK_StreamType.FLOAT);
        int _v = Float.floatToIntBits(v);
        writeData(buf, (byte) ((_v >> 24) & 0xff));
        writeData(buf, (byte) ((_v >> 16) & 0xff));
        writeData(buf, (byte) ((_v >> 8) & 0xff));
        writeData(buf, (byte) ((_v >> 0) & 0xff));
    }

    public static final void writeDouble(ByteBuffer buf, double v)
            throws SK_BufferException {
        if (v >= SK_StreamType.FLOAT_MIN_VALUE
                && v <= SK_StreamType.FLOAT_MAX_VALUE) {
            writeData(buf, SK_StreamType.DOUBLE);
            long _v = Double.doubleToLongBits(v);
            writeData(buf, (byte) ((_v >> 56) & 0xff));
            writeData(buf, (byte) ((_v >> 48) & 0xff));
            writeData(buf, (byte) ((_v >> 40) & 0xff));
            writeData(buf, (byte) ((_v >> 32) & 0xff));
            writeData(buf, (byte) ((_v >> 24) & 0xff));
            writeData(buf, (byte) ((_v >> 16) & 0xff));
            writeData(buf, (byte) ((_v >> 8) & 0xff));
            writeData(buf, (byte) ((_v >> 0) & 0xff));
        } else {
            writeFloat(buf, (float) v);
        }
    }

    public static final void writeString(ByteBuffer buf, String v)
            throws SK_BufferException {
        byte[] _byte = v.getBytes(SK_StreamType.UTF8);
        int l = _byte.length;
        writeData(buf, SK_StreamType.STRING);
        writeInt(buf, l);
        writeData(buf, _byte);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final void writeMap(ByteBuffer buf, Map map)
            throws SK_BufferException {
        Set<Entry> entrys = map.entrySet();
        Object key = null;
        Object value = null;
        writeData(buf, SK_StreamType.MAP);
        writeInt(buf, map.size());
        for (Entry enter : entrys) {
            key = enter.getKey();
            writeObject(buf, key);
            value = enter.getValue();
            writeObject(buf, value);
        }

    }

    @SuppressWarnings("rawtypes")
    public static final void writeList(ByteBuffer buf, List list)
            throws SK_BufferException {
        writeData(buf, SK_StreamType.LIST);
        writeInt(buf, list.size());
        for (Object object : list) {
            writeObject(buf, object);
        }
    }

    public static final void writeDate(ByteBuffer buf, Date date)
            throws SK_BufferException {
        writeData(buf, SK_StreamType.DATE);
        long v = date.getTime();
        writeData(buf, (byte) ((v >> 56) & 0xff));
        writeData(buf, (byte) ((v >> 48) & 0xff));
        writeData(buf, (byte) ((v >> 40) & 0xff));
        writeData(buf, (byte) ((v >> 32) & 0xff));
        writeData(buf, (byte) ((v >> 24) & 0xff));
        writeData(buf, (byte) ((v >> 16) & 0xff));
        writeData(buf, (byte) ((v >> 8) & 0xff));
        writeData(buf, (byte) ((v >> 0) & 0xff));
    }

    public static final void writeBoolean(ByteBuffer buf, boolean bool)
            throws SK_BufferException {
        writeData(buf, SK_StreamType.BOOLEAN);
        if (bool)
            writeData(buf, (byte) 1);
        else
            writeData(buf, (byte) 0);
    }

    @SuppressWarnings("rawtypes")
    public static final void writeObject(ByteBuffer buf, Object object)
            throws SK_BufferException {
        if (object instanceof Byte) {
            writeByte(buf, (byte) object);
        } else if (object instanceof Short) {
            writeShort(buf, (short) object);
        } else if (object instanceof Integer) {
            writeInt(buf, (int) object);
        } else if (object instanceof Long) {
            writeLong(buf, (long) object);
        } else if (object instanceof Float) {
            writeFloat(buf, (float) object);
        } else if (object instanceof Double) {
            writeDouble(buf, (double) object);
        } else if (object instanceof String) {
            writeString(buf, (String) object);
        } else if (object instanceof Map) {
            writeMap(buf, (Map) object);
        } else if (object instanceof List) {
            writeList(buf, (List) object);
        } else if (object instanceof Date) {
            writeDate(buf, (Date) object);
        } else if (object instanceof Boolean) {
            writeBoolean(buf, (boolean) object);
        }
    }
}