# scouter-plugin-server-influxdb
###Scouter server plugin for an influxdb one of the time seriesedb.
![Englsh](https://img.shields.io/badge/language-English-red.svg) [![Korean](https://img.shields.io/badge/language-Korean-blue.svg)](README_kr.md)

- This plugin is for scouter server and gather performance counters by scouter and send them to the influxDB.

### configuration (default file location : conf/scouter.conf)
#### basic properties
* **ext_plugin_influxdb_enabled** : (default : true)
* **ext_plugin_influxdb_measurement** : influxdb measurement name (default : counter)

#### properties for using udp protocol(default)
* **ext_plugin_influxdb_udp** : use UDP protocol (default : true)
* **ext_plugin_influxdb_udp_local_ip** : UDP local address (default : null)
* **ext_plugin_influxdb_udp_local_port** : UDP local address (default : 0)
* **ext_plugin_influxdb_udp_target_ip** : UDP target(influxdb) address (default : 127.0.0.1)
* **ext_plugin_influxdb_udp_target_port** : UDP target(influxdb) port (default : 8089)

#### properties for using http protocol (set false **ext_plugin_influxdb_udp** for using http)
* **ext_plugin_influxdb_http_target_ip** : http target(influxdb) address (default : 127.0.0.1)
* **ext_plugin_influxdb_http_target_port** : http target(influxdb) port (default : 8086)
* **ext_plugin_influxdb_http_retention_policy** : measurement retention policy (default : default)
* **ext_plugin_influxdb_id** : (default : root)
* **ext_plugin_influxdb_password** : (default : root)
* **ext_plugin_influxdb_dbName** : influxdb dbName (default : scouterCounter)
    
### dependencies
Refer to [pom.xml](./pom.xml)

### Build
 - mvn clean install
    
### Deploy
 - `scouter-plugin-server-influxdb-x.x.x.jar` on `target` directory and whole libraries on `target/lib` are copied to `lib` directory of the scouter sever root directory.
 - restart the scouter server.
