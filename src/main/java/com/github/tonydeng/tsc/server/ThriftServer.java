package com.github.tonydeng.tsc.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Created by tonydeng on 16/1/25.
 */
public class ThriftServer {
    private static final Logger log = LoggerFactory.getLogger(ThriftServer.class);

    public void start(TProcessor processor, String host, int port){
        if (log.isInfoEnabled())
            log.info("start thrift server,host is {}, port is {} ......", host, port);

        try {
            InetSocketAddress address = new InetSocketAddress(host,port);
            TServerTransport transport = new TServerSocket(address);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(transport)
//                    .inputTransportFactory(new TFramedTransport.Factory())
//                    .outputTransportFactory(new TFramedTransport.Factory())
                    .protocolFactory(new TCompactProtocol.Factory())
                    .processor(processor);

            TThreadPoolServer server = new TThreadPoolServer(args);

            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
            if (log.isErrorEnabled())
                log.error("start thrift server error:'{}'", e);
        }
    }
}
