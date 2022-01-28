import cn.jokang.demo.thrift.idl.shared.SharedStruct;
import cn.jokang.demo.thrift.idl.tutorial.Calculator;
import cn.jokang.demo.thrift.idl.tutorial.InvalidOperation;
import cn.jokang.demo.thrift.idl.tutorial.Work;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.junit.Test;

/**
 * @author zhoukang
 * @date 2020/7/17
 */
public class ServerTest {
    @Test
    public void testServer() {
        try {
            Calculator.Processor processor = new Calculator.Processor(new Calculator.Iface() {

                @Override
                public SharedStruct getStruct(int key) throws TException {
                    return null;
                }

                @Override
                public void ping() throws TException {

                }

                @Override
                public int add(int num1, int num2) throws TException {
                    return 0;
                }

                @Override
                public int calculate(int logid, Work w) throws InvalidOperation, TException {
                    return 0;
                }

                @Override
                public void zip() throws TException {

                }

                @Override
                public Work echo(Work w) throws InvalidOperation, TException {
                    Work ret = new Work();
                    w.setNum1(1);
                    return ret;
                }
            });

            Runnable simple = new Runnable() {
                public void run() {
                    simple(processor);
                }
            };

            Thread t = new Thread(simple);
            t.start();
            t.join();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
    public static void simple(Calculator.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
