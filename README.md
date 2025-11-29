Project for load test of rqueue queues with priority
<br/>
Prepared for github issue reproducing
<br/>
https://github.com/sonus21/rqueue/issues/276
<br/>
<br/>
Run " .\gradlew clean build" to build project.
<br/>
Check that file "build/libs/rqueue-priority-load-test-1.0.0.jar" was created.
<br/>
Run docker-compose.yml file for application start with Redis database.
<br/>
<br/>
Application success logs are turned off by default.
<br/>
Add this to java start cmd in docker-compose file if you want to see application success logs:
<br/>
-Dlogging.level.rqueuepriorityloadtest=DEBUG
<br/>
<br/>
Check local application work via http request:
<br/>
\- send message to queue with high priority:
<br/>
curl -X POST 'http://localhost:8080/load-test' -H 'Content-Type: application/json' -d '{"priority":"HIGH"}'
<br/>
\- send message to queue with medium priority:
<br/>
curl -X POST 'http://localhost:8080/load-test' -H 'Content-Type: application/json' -d '{"priority":"MEDIUM"}'
<br/>
\- send message to queue with low priority:
<br/>
curl -X POST 'http://localhost:8080/load-test' -H 'Content-Type: application/json' -d '{"priority":"LOW"}'
<br/>
<br/>
Setup Apache JMeter to start load test file "load_test.jmx"
(just open jmx file and start it via Apache Jmeter desktop UI)
<br/>
<br/>
Install Redis client to check Redis database data (for example, "Another Redis Desktop Manager" client).
<br/>
<br/>
Install Prometheus to collect metric data.
Install Grafana to visualize metrics.
More about it there https://www.baeldung.com/spring-boot-prometheus
After grafana datasource will be set 
you can import dashboard import file "grafana-dashboard-file-for-import.json"
(maybe with minor updates for datasource id)
that contains necessary metric visualization for load test research.