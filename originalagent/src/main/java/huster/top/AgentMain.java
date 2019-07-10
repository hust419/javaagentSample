package huster.top;

import java.lang.instrument.Instrumentation;

/**
 * Created by longan.rtb on 2019/7/8.
 */
public class AgentMain  {

    public static void premain(String args, Instrumentation inst) {
       System.out.println("permain has been called, arguments is : " + args);
       inst.addTransformer(new AOPImplement());
    }

    //attach方式.
    public static void agentmain(String args, Instrumentation inst){
        System.out.println("444agentmain has been called, arguments is : " + args);
        System.out.println("is enable :" + String.valueOf(inst.isRetransformClassesSupported()));
        inst.addTransformer(new AOPImplement2(),true);
        try {
            inst.retransformClasses(Class.forName("huster.top.JavaAgentTest"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
