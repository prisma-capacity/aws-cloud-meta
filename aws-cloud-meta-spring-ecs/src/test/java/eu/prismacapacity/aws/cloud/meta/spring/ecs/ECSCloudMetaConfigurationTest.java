package eu.prismacapacity.aws.cloud.meta.spring.ecs;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.prismacapacity.aws.cloud.meta.core.ecs.ContainerMetaData;
import eu.prismacapacity.aws.cloud.meta.core.ecs.TaskMetaData;
import lombok.val;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ECSCloudMetaConfigurationTest {
    @Mock
    ECSMetaDataReader ECSMetaDataReader;

    @Mock
    TaskMetaData taskMetaData;

    @Mock
    ContainerMetaData containerMetaData;

    @Mock
    Environment environment;

    @Test
    void testTaskEndpointReader() {
        when(environment.getRequiredProperty("ECS_CONTAINER_METADATA_URI")).thenReturn("foo");

        val uut = new ECSCloudMetaConfiguration();

        val reader = uut.taskEndpointReader(environment);

        assertNotNull(reader);
    }

    @Test
    void testSuccessfulTaskMetaData() {
        val uut = new ECSCloudMetaConfiguration();

        when(ECSMetaDataReader.readTaskMetaData()).thenReturn(Optional.of(taskMetaData));

        val metaData = uut.taskMetaData(ECSMetaDataReader);

        assertNotNull(metaData);
    }

    @Test
    void testUnsuccessfulTaskMetaData() {
        val uut = new ECSCloudMetaConfiguration();

        when(ECSMetaDataReader.readTaskMetaData()).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class,
                () -> uut.taskMetaData(ECSMetaDataReader));
    }

    @Test
    void testSuccessfulContainerMetaData() {
        val uut = new ECSCloudMetaConfiguration();

        when(ECSMetaDataReader.readContainerMetaData()).thenReturn(Optional.of(containerMetaData));

        val metaData = uut.containerMetaData(ECSMetaDataReader);

        assertNotNull(metaData);
    }
}