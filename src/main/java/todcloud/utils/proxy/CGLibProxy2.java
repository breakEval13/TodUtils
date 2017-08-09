package todcloud.utils.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangjianxin on 2017/8/9.
 */
public class CGLibProxy2 implements MethodInterceptor {
    private static CGLibProxy instance = new CGLibProxy();

    private CGLibProxy2(){}

    public static CGLibProxy getInstance(){
        return instance;
    }

    private void before(){
        System.out.println("cook");
    }

    private void after(){
        System.out.println("swap");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            before();
            Object result = methodProxy.invokeSuper(o,objects);
            after();
            return result;
    }
    public static void main(String[] atgs){
        Humen humenProxy = CGLibProxy2.getInstance().getProxy(HumenImpl.class);
        humenProxy.eat("rice");
    }
}
