package eu.prismacapacity.aws.cloud.meta.spring.ecs;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.prismacapacity.aws.cloud.meta.core.ecs.TaskMetaData;
import lombok.val;
import okhttp3.*;
import okhttp3.mock.MockInterceptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ECSMetaDataReaderTest {
    @Mock
    ObjectMapper objectMapper;

    @Mock
    TaskMetaData taskMetaData;

    @Test
    void testSuccessfulRead() throws IOException {
        val interceptor = new MockInterceptor();

        interceptor
                .addRule()
                .get(ECSMetaDataReader.ECS_TASK_ENDPOINT_URL)
                .respond(RESPONSE);

        val client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        when(objectMapper.readValue(RESPONSE, TaskMetaData.class))
                .thenReturn(taskMetaData);

        val uut = new ECSMetaDataReader(client, objectMapper);

        val result = uut.readTaskMetaData();
        assertTrue(result.isPresent());

        verify(objectMapper).readValue(RESPONSE, TaskMetaData.class);
    }

    @Test
    void testUnsuccessfulRead() {
        val interceptor = new MockInterceptor();

        interceptor
                .addRule()
                .get(ECSMetaDataReader.ECS_TASK_ENDPOINT_URL)
                .respond(404);

        val client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        val uut = new ECSMetaDataReader(client, objectMapper);

        val result = uut.readTaskMetaData();
        assertFalse(result.isPresent());

        verifyNoInteractions(objectMapper);
    }

    @Test
    void testUnsuccessfulReadWhenExceptionOccurs() throws IOException {
        val client = mock(OkHttpClient.class);
        val call = mock(Call.class);

        when(client.newCall(any())).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("foo"));

        val uut = new ECSMetaDataReader(client, objectMapper);

        val result = uut.readTaskMetaData();
        assertFalse(result.isPresent());

        verifyNoInteractions(objectMapper);
    }

    static String RESPONSE = "{\n" +
            "  \"foo\": \"bar\"\n" +
            "}";
}