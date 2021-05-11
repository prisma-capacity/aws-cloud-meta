/*
 * Copyright © 2021 PRISMA European Capacity Platform GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.prismacapacity.aws.cloud.meta.core.ecs;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskMetaData {
	@JsonProperty("Cluster")
	String cluster;

	@JsonProperty("TaskARN")
	String taskArn;

	@JsonProperty("Family")
	String family;

	@JsonProperty("Revision")
	String revision;

	@JsonProperty("DesiredStatus")
	String desiredStatus;

	@JsonProperty("KnownStatus")
	String knownStatus;

	@JsonProperty("Containers")
	List<ContainerMetaData> containers;

	@JsonProperty("Limits")
	Limit limits;
}