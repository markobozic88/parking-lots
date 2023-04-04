package com.markobozic.parkinglots.repository;

import com.markobozic.parkinglots.repository.custom.UUIDRow;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {LoaderRepository.class})
class LoaderRepositoryTest {

    private final LoaderRepository loaderRepository = spy(LoaderRepository.class);

    @Test
    void findOne_OK() {
        UUID recordId = UUID.randomUUID();
        NamedParameterJdbcOperations jdbcTemplate = mock(NamedParameterJdbcOperations.class);

        when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(UUIDRow.class)))
                .thenReturn(Collections.singletonList(recordId));

        List<UUID> response = loaderRepository.findOne(jdbcTemplate);
        assertEquals(recordId, response.get(0));
    }

    @Test
    void findOne_IllegalStateException() {
        NamedParameterJdbcOperations jdbcTemplate = mock(NamedParameterJdbcOperations.class);

        when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(UUIDRow.class)))
                .thenThrow(new IllegalStateException());

        assertThrows(IllegalStateException.class, () -> loaderRepository.findOne(jdbcTemplate));
    }

}
