import cn.jokang.demo.thrift.idl.tutorial.Calculator;
import cn.jokang.demo.thrift.idl.tutorial.Work;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.Test;

/**
 * @author zhoukang
 * @date 2020/7/17
 */
public class ClientTest {
    @Test
    public void testClient() {
        try {
            TTransport transport;

            transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            Calculator.Client client = new Calculator.Client(protocol);

            perform(client);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(Calculator.Client client) throws TException
    {
        Work w = client.echo(new Work());
        System.out.println(w);
    }
}
