package huster.top;

/**
 * Created by longan.rtb on 2019/7/8.
 */
public class JavaAgentTest {

    public String helloWorld() {
        System.out.println("Hello world!");
        return "ok";
    }

    public static void main(String[] args) {
        JavaAgentTest javaAgentTest = new JavaAgentTest();
        //循环一定要放在这个地方，否则放在helloWorld方法的话，这个方法永远没有结束,也就没有办法在return之后插入代码.
        for (;;) {
         try {
             javaAgentTest.helloWorld();
             Thread.sleep(5000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
        }

    }
}
