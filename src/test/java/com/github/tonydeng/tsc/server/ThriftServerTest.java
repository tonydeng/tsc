package com.github.tonydeng.tsc.server;

import com.github.tonydeng.thrift.PingPongService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tonydeng on 16/1/25.
 */
public class ThriftServerTest {
    private static final Logger log = LoggerFactory.getLogger(ThriftServerTest.class);

    @Test
    public void testStart(){
        log.info("test");
        ThriftServer thriftServer = new ThriftServer();
        thriftServer.start(new PingPongService.Processor<>(new PingPongServiceHandler()),"127.0.0.1",9002);
    }
}
