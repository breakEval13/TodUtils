package todcloud.utils.proxy;

/**
 * Created by zhangjianxin on 2017/8/9.
 */
public class HumenProxy implements Humen{

    private Humen humen;

    public HumenProxy(){
        humen = new HumenImpl();
    }

    @Override
    public void eat(String food){
        before();
        humen.eat(food);
        after();
    }

    private void before(){
        System.out.println("cook");
    }

    private void after(){
        System.out.println("swap");
    }
    public static void main(String[] args){
        Humen humenProxy = new HumenProxy();
        humenProxy.eat("rice");
    }
}