package com.github.tonydeng.tsc;

import org.apache.commons.cli.*;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by tonydeng on 16/1/25.
 */
public class ThriftCommandLine {
    /**
     * 初始化命令行参数
     *
     * @param project
     * @return
     */
    public static Options initOptions(String project) {
        Options options = new Options();

        options.addOption("help", false, "Print help for BlueBird");
        options.addOption("h", "host", true, "BLueBird Server Run TCP IPAddress");
        options.addOption("p", "port", true, "BlueBird Server Run TCP PORT");
        options.addOption("a", "auto", false, "BlueBird Server Auto Run TCP IPAddress And PORT");
        return options;
    }

    /**
     * 验证命令参数
     *
     * @param options
     * @param args
     * @return
     */
    public static CommandLine checkCommand(Options options, String[] args) {
        BasicParser parser = new BasicParser();
        try {
            CommandLine cl = parser.parse(options, args);
            if (cl.getOptions().length > 0) {
                if (cl.hasOption("help")) {
                    printHelp(options);
                }

            }else {
                printHelp(options);
            }
            return cl;
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;

    }


    private static void printHelp(Options options) {
        HelpFormatter f = new HelpFormatter();
        f.printHelp("OptionTip", options);
        System.exit(0);
    }

    /**
     * 获得本地
     * @return
     */
    public static String getLocalIp() {
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;

            while (e.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) e.nextElement();
                Enumeration address = ni.getInetAddresses();

                while (address.hasMoreElements()) {
                    ip = (InetAddress) address.nextElement();
                    if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return null;
    }
}
