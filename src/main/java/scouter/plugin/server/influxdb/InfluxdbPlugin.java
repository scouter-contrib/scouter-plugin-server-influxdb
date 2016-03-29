package scouter.plugin.server.influxdb;

import org.influxdb.dto.Point;
import scouter.lang.TimeTypeEnum;
import scouter.lang.pack.PerfCounterPack;
import scouter.lang.plugin.PluginConstants;
import scouter.lang.plugin.annotation.ServerPlugin;
import scouter.lang.value.Value;
import scouter.server.Configure;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2016. 3. 29.
 */
public class InfluxdbPlugin {
    Configure conf = Configure.getInstance();

    final String confMeasurement = "ext_plugin_influxdb_measurement";
    final String confTagObj      = "ext_plugin_influxdb_tag_obj";
    final String confTagTimeType = "ext_plugin_influxdb_tag_timetype";

    final String confMeasurementDefault = "counter";
    final String confTagObjDefault      = "obj";
    final String confTagTimeTypeDefault = "timetype";

    @ServerPlugin(PluginConstants.PLUGIN_SERVER_COUNTER)
    public void counter(PerfCounterPack pack) {
        String measurementName = conf.getValue(confMeasurement, confMeasurementDefault);
        String objTagName = conf.getValue(confTagObj, confTagObjDefault);
        String timeTypeTagName = conf.getValue(confTagTimeType, confTagTimeTypeDefault);

        try {
            Point.Builder builder = Point.measurement(measurementName)
                    .time(pack.time, TimeUnit.MILLISECONDS)
                    .tag(objTagName, pack.objName)
                    .tag(timeTypeTagName, TimeTypeEnum.getString(pack.timetype));

            Map<String, Value> dataMap = pack.data.toMap();
            for (Map.Entry<String, Value> field : dataMap.entrySet()) {
                Value valueOrigin = field.getValue();
                if (valueOrigin == null) {
                    continue;
                }
                Object value = valueOrigin.toJavaObject();
                String key = field.getKey();
                builder.field(key, value);
            }

            Point point = builder.build();
            String line = point.lineProtocol();

            System.out.println(line);

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
