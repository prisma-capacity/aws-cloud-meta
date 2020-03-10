/*
 * Copyright © 2020 PRISMA European Capacity Platform GmbH
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

import java.io.IOException;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.prismacapacity.aws.cloud.meta.core.ecs.ContainerMetaData;
import eu.prismacapacity.aws.cloud.meta.core.ecs.TaskMetaData;

@Slf4j
@AllArgsConstructor
public class ECSMetaDataReader {
	private final String containerMetaDataUri;

	private final OkHttpClient client;

	private final ObjectMapper objectMapper;

	public Optional<TaskMetaData> readTaskMetaData() {
		return read(containerMetaDataUri + "/task", TaskMetaData.class);
	}

	public Optional<ContainerMetaData> readContainerMetaData() {
		return read(containerMetaDataUri, ContainerMetaData.class);
	}

	private <T> Optional<T> read(@NonNull String url, Class<T> target) {
		val request = new Request.Builder().url(url).build();

		try (val response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				log.error("Couldn't fetch task meta data: {}", response.message());
				return Optional.empty();
			}

			val result = objectMapper.readValue(response.body().string(), target);

			return Optional.of(result);
		} catch (IOException ex) {
			log.error("Couldn't fetch task meta data", ex);

			return Optional.empty();
		}
	}
}
