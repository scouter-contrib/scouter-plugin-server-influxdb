package scouter.plugin.server.influxdb;

import scouter.server.Configure;
import scouter.server.Logger;
import scouter.util.StringUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2016. 3. 1.
 */
public class UdpAgent {
    private static UdpAgent agent = new UdpAgent();

    Configure conf = Configure.getInstance();
    InetAddress serverHost;
    int serverPort;
    String localUdpAddr;
    int localUdpPort = 0;
    private DatagramSocket datagram;

    private UdpAgent() {
        openDatagramSocket();
    }

    public static UdpAgent getInstance() {
        return agent;
    }

    public void setTarget(String ip, int port) {
        try {
            serverHost = InetAddress.getByName(ip);
            serverPort = port;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLocalAddr(String localIp, int localPort) {
        try {
            localUdpAddr = StringUtil.isEmpty(localIp) ? null : localIp;
            localUdpPort = localPort;
            openDatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void close(DatagramSocket d) {
        if (d != null) {
            try {
                d.close();
            } catch (Exception e) {
            }
        }
    }

    private void openDatagramSocket() {
        try {
            if (datagram != null) {
                close(datagram);
            }
            if (localUdpAddr != null) {
                datagram = new DatagramSocket(localUdpPort, InetAddress.getByName(localUdpAddr));
                Logger.println("InfluxDB Agent UDP local.addr=" + localUdpAddr + " local.port=" + localUdpPort);
            } else {
                datagram = new DatagramSocket(localUdpPort);
                Logger.println("InfluxDB Agent UDP local.port=" + localUdpPort);
            }

        } catch (Exception e) {
            if (conf._trace) {
                Logger.printStackTrace(e);
            } else {
                Logger.println(e.getMessage());
            }
        }
    }

    public boolean write(String lineProtocol) {
        try {
            byte buff[] = lineProtocol.getBytes();
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            packet.setAddress(serverHost);
            packet.setPort(serverPort);
            datagram.send(packet);
            return true;

        } catch (IOException e) {
            System.err.println("[influxdb UDP write]" + e.getMessage());
            return false;
        }
    }

    public void close() {
        if (datagram != null) {
            datagram.close();
        }
        datagram = null;
    }

}
