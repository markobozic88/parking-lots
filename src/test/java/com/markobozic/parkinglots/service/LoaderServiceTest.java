package com.markobozic.parkinglots.service;

import com.markobozic.parkinglots.repository.LoaderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import static com.markobozic.parkinglots.utils.Consts.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {LoaderService.class})
class LoaderServiceTest {

    private LoaderService loaderService;

    @MockBean
    private NamedParameterJdbcOperations jdbcTemplate;

    @MockBean
    private LoaderRepository loaderRepository;

    @MockBean
    private MultipartFile file;

    @BeforeEach
    public void setUp() {
        loaderService = new LoaderServiceImpl(jdbcTemplate, loaderRepository);
    }

    @Test
    void deleteAllRecords_OK() {
        doNothing().when(loaderRepository).deleteAll();
        String response = loaderService.deleteAllRecords();
        assertEquals(SUCCESS, response);
    }

    @Test
    void deleteAllRecords_FAIL() {
        doThrow(new RuntimeException()).when(loaderRepository).deleteAll();
        String response = loaderService.deleteAllRecords();
        assertEquals(FAIL, response);
    }

    @Test
    void addRecords_recordsAlreadyStored_OK() {
        when(loaderRepository.findOne(jdbcTemplate)).thenReturn(Collections.singletonList(UUID.randomUUID()));
        String response = loaderService.addRecords();
        assertEquals(RECORDS_STORED, response);
    }

    @Test
    void addRecords_OK() {
        LoaderServiceImpl loaderService = new LoaderServiceImpl(jdbcTemplate, loaderRepository);
        when(loaderRepository.findOne(jdbcTemplate)).thenReturn(Collections.emptyList());
        loaderService.setCsvFileDirectory("src/test/resources/parking-lot-test.csv");

        String response = loaderService.addRecords();
        assertEquals(SUCCESS + "Added 100 records in database.", response);
    }

    @Test
    void addRecords_errorInReadingCSV_returnFailMessage() {
        LoaderServiceImpl loaderService = new LoaderServiceImpl(jdbcTemplate, loaderRepository);
        when(loaderRepository.findOne(jdbcTemplate)).thenReturn(Collections.emptyList());
        loaderService.setCsvFileDirectory("src/test/resources/parking.csv");

        String response = loaderService.addRecords();
        assertEquals(FAIL, response);
    }

    @Test
    void addRecordsUploadCVS_recordsAlreadyStored_OK() {
        when(loaderRepository.findOne(jdbcTemplate)).thenReturn(Collections.singletonList(UUID.randomUUID()));
        String response = loaderService.addRecordsUploadCVS(file);
        assertEquals(RECORDS_STORED, response);
    }

    @Test
    void addRecordsUploadCVS_OK() throws IOException {
        when(loaderRepository.findOne(jdbcTemplate)).thenReturn(Collections.emptyList());
        file = new MockMultipartFile("test.csv", new FileInputStream(
                new File("src/test/resources/parking-lot-test.csv")));

        String response = loaderService.addRecordsUploadCVS(file);
        assertEquals(SUCCESS + "Added 100 records in database.", response);
    }

}
