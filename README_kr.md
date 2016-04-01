# scouter-plugin-server-influxdb
###Scouter server plugin for an influxdb one of the time seriesedb.
[![Englsh](https://img.shields.io/badge/language-English-red.svg)](README.md) ![Korean](https://img.shields.io/badge/language-Korean-blue.svg)

- Scouter Server Plugin으로 성능 counter 정보를 influxdb로 전송해 주는 plugin 이다.

### configuration (스카우터 서버 설치 경로 하위의 conf/scouter.conf)
#### 기본 설정
* **ext_plugin_influxdb_enabled** : 본 plugin 사용 여부 (default : true)
* **ext_plugin_influxdb_measurement** : influxdb measurement 명 (default : counter)

#### udp 방식 연동 여부 설정
* **ext_plugin_influxdb_udp** : UDP protocol로 연동 (default : true)
* **ext_plugin_influxdb_udp_local_ip** : UDP local address (default : null)
* **ext_plugin_influxdb_udp_local_port** : UDP local address (default : 0)
* **ext_plugin_influxdb_udp_target_ip** : UDP target(influxdb) address (default : 127.0.0.1)
* **ext_plugin_influxdb_udp_target_port** : UDP target(influxdb) port (default : 8089)

#### http 방식 연동 여부 설정 (set false **ext_plugin_influxdb_udp** for using http)
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
 - target에 생성되는 scouter-plugin-server-influxdb-x.x.x.jar 와 target/lib에 생성되는 전체 library를 scouter sever의 lib 디렉토리에 저장하고 scouter server를 재시작한다
