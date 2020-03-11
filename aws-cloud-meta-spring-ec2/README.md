# aws-cloud-meta-spring-ec2

## Usage (Spring-Boot)

Add the Spring-Boot starter and the EC2 module
```
<dependency>
  <groupId>eu.prismacapacity</groupId>
  <artifactId>aws-cloud-meta-spring-boot-starter</artifactId>
  <version>VERSION</version>
</dependency>

<dependency>
  <groupId>eu.prismacapacity</groupId>
  <artifactId>aws-cloud-meta-spring-ec2</artifactId>
  <version>VERSION</version>
</dependency>
```

Add the following property
```
aws.cloud.meta.enabled=true
```

If you do so Spring-Boot tries to instantiate a `InstanceMetaData`.