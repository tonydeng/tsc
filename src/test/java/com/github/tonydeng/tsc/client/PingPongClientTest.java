package com.github.tonydeng.tsc.client;

import com.github.tonydeng.tcp4j.ThriftClient;
import com.github.tonydeng.tcp4j.ThriftClientFactory;
import com.github.tonydeng.thrift.Ping;
import com.github.tonydeng.thrift.PingPongService;
import com.github.tonydeng.thrift.Pong;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by tonydeng on 16/1/25.
 */
public class PingPongClientTest {
    private static final Logger log = LoggerFactory.getLogger(PingPongClientTest.class);

    private static Properties config;

    @Before
    public void init(){
        config = new Properties();
        config.setProperty("thrift.server.1","127.0.0.1:9002");
    }

    @Test
    public void testKnock() throws TException {
        Ping ping = new Ping("Hi TSC");

        ThriftClient client = new ThriftClientFactory(config).getDefaultThriftClient();

        Pong pong  = client.iface(PingPongService.Client.class).knock(ping);
        log.info("pong:'{}'",pong);
    }
}
