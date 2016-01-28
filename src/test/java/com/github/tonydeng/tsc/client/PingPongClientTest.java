package com.github.tonydeng.tsc.client;

import com.github.tonydeng.tcp4j.ThriftClient;
import com.github.tonydeng.tcp4j.ThriftClientFactory;
import com.github.tonydeng.tcp4j.impl.ThriftClientImpl;
import com.github.tonydeng.tcp4j.pool.ThriftServerInfo;
import com.github.tonydeng.thrift.Ping;
import com.github.tonydeng.thrift.PingPongService;
import com.github.tonydeng.thrift.Pong;
import com.google.common.collect.Lists;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

/**
 * Created by tonydeng on 16/1/25.
 */
@Ignore
public class PingPongClientTest {
    private static final Logger log = LoggerFactory.getLogger(PingPongClientTest.class);

    private static Properties config;

    private static List<ThriftServerInfo> serverInfos;

    private static Ping ping;

    @Before
    public void init(){
        config = new Properties();
        config.setProperty("thrift.server.1","127.0.0.1:9002");

        serverInfos = Lists.newArrayList(
                ThriftServerInfo.of("127.0.0.1",9002)
        );
    }

    @Test
    public void testKnockByFactory() throws TException {
        ping = new Ping("Hi TSC Factory");

        ThriftClient client = new ThriftClientFactory(config).getDefaultThriftClient();

        Pong pong  = client.iface(PingPongService.Client.class).knock(ping);
        log.info("ping:'{}' pong:'{}'",ping,pong);
    }

    @Test
    public  void testKnockByClient() throws TException {
        ping = new Ping("Hi TSC Client");

        ThriftClient client = new ThriftClientImpl(serverInfos);

        Pong pong = client.iface(PingPongService.Client.class).knock(ping);
        log.info("ping:'{}' pong:'{}'",ping,pong);
    }
}
