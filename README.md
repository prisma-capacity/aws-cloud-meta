<div align="right"><a target="myNextJob" href="https://www.prisma-capacity.eu/careers#job-offers">
    <img class="inline" src="prisma.png">
</a></div>

# aws-cloud-meta

![Java CI](https://github.com/prisma-capacity/aws-cloud-meta/workflows/Java%20CI/badge.svg?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/333bfd567a6a447895212994b414f077)](https://app.codacy.com/gh/prisma-capacity/aws-cloud-meta?utm_source=github.com&utm_medium=referral&utm_content=prisma-capacity/aws-cloud-meta&utm_campaign=Badge_Grade_Settings)
[![codecov](https://codecov.io/gh/prisma-capacity/aws-cloud-meta/branch/master/graph/badge.svg)](https://codecov.io/gh/prisma-capacity/aws-cloud-meta)
[![MavenCentral](https://img.shields.io/maven-central/v/eu.prismacapacity/aws-cloud-meta)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22eu.prismacapacity%22)
<a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img class="inline" src="https://img.shields.io/badge/license-ASL2-green.svg?style=flat">
</a>
[![Dependabot Status](https://api.dependabot.com/badges/status?host=github&repo=prisma-capacity/aws-cloud-meta)](https://dependabot.com)


### Motivation

Sometimes it's necessary that you know about the container/task or instance you're running on. We use this meta data to enrich 
our logs and metrics to increase visibility and transparency of issues.

### aws-cloud-meta library

#### Features
* provides beans for ECS (incl Fargate) and EC2
* (optional) Spring-Boot Starter
* (optional) Spring (not boot) configuration module 

### Usage for Spring-Boot

Please have a look at the corresponding sub package
* [ECS](aws-cloud-meta-spring-ecs/)
* [EC2](aws-cloud-meta-spring-ec2/) 