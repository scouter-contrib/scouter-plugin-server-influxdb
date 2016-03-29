package scouter.plugin.server.influxdb;

import scouter.lang.pack.PerfCounterPack;
import scouter.lang.plugin.PluginConstants;
import scouter.lang.plugin.annotation.ServerPlugin;
import scouter.server.Configure;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2016. 3. 29.
 */
public class InfluxdbPlugin {
    Configure conf = Configure.getInstance();

    @ServerPlugin(PluginConstants.PLUGIN_SERVER_COUNTER)
    public void counter(PerfCounterPack pack) {

    }
}
