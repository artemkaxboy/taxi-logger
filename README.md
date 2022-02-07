# Yandex Taxi Ride Price Logger

## How to run

```shell
docker run -d \
  -e SPRING_DATA_MONGODB_URI=mongodb://username:password@server:27017/database \
  -e YANDEX_API_CLID=abcdef \
  -e YANDEX_API_KEY=xxxzzz \
  -e YANDEX_API_CACHE-TTL=5m \
  -p 8080:8080 \
  ghcr.io/artemkaxboy/taxi-logger
```

## Manage ride routes

[//]: # (TODO REST API to add route)

## Access

Use prometheus to store data:

```yaml
  # https://github.com/artemkaxboy/taxi-logger
  - job_name: spring_micrometer
    honor_timestamps: true
    metrics_path: /actuator/prometheus
    scheme: http
    static_configs:
    - targets:
      - {your-ip}:8080
```
