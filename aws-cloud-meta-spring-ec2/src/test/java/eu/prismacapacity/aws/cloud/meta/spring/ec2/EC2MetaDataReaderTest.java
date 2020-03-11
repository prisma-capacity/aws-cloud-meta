package eu.prismacapacity.aws.cloud.meta.spring.ec2;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EC2MetaDataReaderTest {
    @Mock
    EC2Utils utils;

    @Test
    void readInstanceMetaData() {
        when(utils.getAmiId()).thenReturn("1");
        when(utils.getInstanceId()).thenReturn("2");
        when(utils.getInstanceType()).thenReturn("3");

        val uut = new EC2MetaDataReader(utils);
        val meta = uut.readInstanceMetaData();

        assertEquals(meta.getAmiId(), "1");
        assertEquals(meta.getInstanceId(), "2");
        assertEquals(meta.getInstanceType(), "3");
    }
}