package todcloud.utils.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**

 * @UserName : zhangjianxin

 * @DataTime : 2016年4月24日 下午11:49:30

 * @Description ：集合工具类

 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SK_Collections {
    public static final List newList() {
        return Collections.synchronizedList(new ArrayList());
        // return new CopyOnWriteArrayList();

    }

    public static final <K, V> Map<K, V> newMap() {
        // return Collections.synchronizedMap(new HashMap());

        return new ConcurrentHashMap<K, V>();
    }

    public static final <K, V> Map<K, V> newSortedMap() {
        return Collections.synchronizedMap(new TreeMap<K, V>());
    }

    public static final Set newSet() {
        // return Collections.synchronizedSet(new HashSet());

        return new CopyOnWriteArraySet();
    }

    public static final Set newSortedSet() {
        return Collections.synchronizedSet(new TreeSet());
    }
}