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
package eu.prismacapacity.aws.cloud.meta.spring.ec2;

import lombok.NonNull;
import lombok.val;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import eu.prismacapacity.aws.cloud.meta.core.ec2.InstanceMetaData;

@Configuration
public class EC2CloudMetaConfiguration {
	@Bean
	EC2MetaDataReader ec2MetaDataReader() {
		val utils = new EC2Utils();

		return new EC2MetaDataReader(utils);
	}

	@Bean
	@Lazy
	InstanceMetaData instanceMetaData(@NonNull EC2MetaDataReader metaDataReader) {
		return metaDataReader.readInstanceMetaData();
	}
}
