package eu.prismacapacity.aws.cloud.meta.spring.ec2;

import eu.prismacapacity.aws.cloud.meta.core.ec2.InstanceMetaData;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EC2CloudMetaConfigurationTest {

    @Test
    void ec2MetaDataReader() {
        val uut = new EC2CloudMetaConfiguration();

        val reader = uut.ec2MetaDataReader();

        assertNotNull(reader);
    }

    @Test
    void instanceMetaData() {
        val reader = mock(EC2MetaDataReader.class);
        val data = new InstanceMetaData("foo", "bar", "baz");

        when(reader.readInstanceMetaData()).thenReturn(data);

        val uut = new EC2CloudMetaConfiguration();
        val result = uut.instanceMetaData(reader);

        assertNotNull(result);
        assertEquals(result, data);

        verify(reader).readInstanceMetaData();
    }
}