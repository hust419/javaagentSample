package huster.top;

import com.sun.tools.attach.VirtualMachine;

/**
 * Created by longan.rtb on 2019/7/9.
 */
public class AttachAgent {

    public static void main(String[] args)  throws Exception {
        String pid = args[0];
        String jar = args[1];
        if (jar == null  || "".equals(jar)) {
            jar = "/Users/longan.rtb/JavaProject/javaagentdemo/originalagent/target/original-agent-jar-with-dependencies.jar";
        }
        VirtualMachine vmObj = null;
        try {
            vmObj = VirtualMachine.attach(pid);
            vmObj.loadAgent(jar,
                    "this is arguments");
        } finally {
            if (vmObj != null) {
                vmObj.detach();
            }

        }
    }
}
