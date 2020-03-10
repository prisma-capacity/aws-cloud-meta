# aws-cloud-meta-spring-ecs

## Usage (Spring-Boot)

Add the Spring-Boot starter and the ECS
```
<dependency>
  <groupId>eu.prismacapacity</groupId>
  <artifactId>aws-cloud-meta-spring-boot-starter</artifactId>
  <version>VERSION</version>
</dependency>

<dependency>
  <groupId>eu.prismacapacity</groupId>
  <artifactId>aws-cloud-meta-spring-ecs</artifactId>
  <version>VERSION</version>
</dependency>
```

Add the following property
```
aws.cloud.meta.enabled=true
```

If you do so Spring-Boot tries to instantiate a `ContainerMetaData` and `TaskMetaData` bean during boot.