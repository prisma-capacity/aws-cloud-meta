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
package eu.prismacapacity.aws.cloud.meta.spring.ecs;

import lombok.NonNull;
import lombok.val;
import okhttp3.OkHttpClient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.prismacapacity.aws.cloud.meta.core.ecs.ContainerMetaData;
import eu.prismacapacity.aws.cloud.meta.core.ecs.TaskMetaData;

@Configuration
public class ECSCloudMetaConfiguration {
	@Bean
	@ConditionalOnMissingBean
	ECSMetaDataReader taskEndpointReader(@NonNull ObjectMapper objectMapper, @NonNull Environment env) {
		val client = new OkHttpClient();
		val containerMetaDataUri = env.getRequiredProperty("ECS_CONTAINER_METADATA_URI");

		return new ECSMetaDataReader(containerMetaDataUri, client, objectMapper);
	}

	@Bean
	@ConditionalOnMissingBean
	TaskMetaData taskMetaData(@NonNull ECSMetaDataReader metaDataReader) {
		return metaDataReader.readTaskMetaData()
				.orElseThrow(() -> new IllegalStateException("Couldn't load ECS task meta data"));
	}

	@Bean
	@ConditionalOnMissingBean
	ContainerMetaData containerMetaData(@NonNull ECSMetaDataReader metaDataReader) {
		return metaDataReader.readContainerMetaData()
				.orElseThrow(() -> new IllegalStateException("Couldn't load ECS container meta data"));
	}
}
