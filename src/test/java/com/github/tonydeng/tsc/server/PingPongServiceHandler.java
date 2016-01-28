package com.github.tonydeng.tsc.server;

import com.github.tonydeng.thrift.Ping;
import com.github.tonydeng.thrift.PingPongService;
import com.github.tonydeng.thrift.Pong;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tonydeng on 15/9/28.
 */
@Ignore
public class PingPongServiceHandler implements PingPongService.Iface {

    private static  final Logger log = LoggerFactory.getLogger(PingPongServiceHandler.class);

    @Override
    public Pong knock(Ping ping) throws TException {
        log.info("ping pong knock.......");

        String message  = ping.getMessage();
        String answer = StringUtils.reverse(message);

        Pong pong = new Pong();

        pong.setAnswer(answer);

        log.info("Got message {} and sent answer {}",message,answer);

        return pong;
    }
}
