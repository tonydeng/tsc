package com.github.tonydeng.tsc.cl;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tonydeng on 16/1/25.
 */
@Ignore
public class ThriftCommandLineTest {
    private static final Logger log = LoggerFactory.getLogger(ThriftCommandLineTest.class);
    private static Options options;
    private static String[] args;

    @Before
    public void init() {
        options = ThriftCommandLine.initOptions("TSC");
        args = new String[]{
                "-p", "9001",
                "-h", "127.0.0.1",
                "-a"
        };
    }

        @Test
    public void testGetCommandInfo() {
        ThriftInfo info = ThriftCommandLine.getCommandInfo(options, args);

        log.info("thrift info host:'{}'  port:'{}'", info.getHost(), info.getPort());
    }

    @Test
    public void testCheckCommand() {
        options.addOption("testlong", "asdfasdf", false, "hahahhhhhfhdhfhdfh");
        options.addOption("test", false, "test Option ");
        CommandLine cl = ThriftCommandLine.checkCommand(options, args);
    }
}
