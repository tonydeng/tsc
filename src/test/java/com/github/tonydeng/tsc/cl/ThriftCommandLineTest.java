package com.github.tonydeng.tsc.cl;

import org.apache.commons.cli.Options;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tonydeng on 16/1/25.
 */
public class ThriftCommandLineTest {
    private static final Logger log = LoggerFactory.getLogger(ThriftCommandLineTest.class);
    private static Options options;
    private static String[] args;

    @Before
    public void init() {
        options = ThriftCommandLine.initOptions("tsc");
        args = new String[]{
                "-p", "9001",
                "-h", "127.0.0.1"
        };
    }

    @Test
    public void testGetCommandInfo() {
        ThriftInfo info = ThriftCommandLine.getCommandInfo(options, args);

        log.info("thrift info host:'{}'  port:'{}'",info.getHost(),info.getPort());
    }
}
