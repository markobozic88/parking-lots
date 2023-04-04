package com.markobozic.parkinglots.service;

import com.markobozic.parkinglots.repository.entity.ParkingLotEntity;
import com.markobozic.parkinglots.repository.LoaderRepository;
import com.markobozic.parkinglots.mappers.MapperUtils;
import com.markobozic.parkinglots.service.model.ParkingLotDetails;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.markobozic.parkinglots.utils.Consts.*;

@Service
public class LoaderServiceImpl implements LoaderService {

    private static final Logger LOG = LoggerFactory.getLogger(LoaderServiceImpl.class);

    private final NamedParameterJdbcOperations jdbcTemplate;
    private final LoaderRepository loaderRepository;

    @Setter // for the tests
    @Value("${csv.file.directory}")
    private String csvFileDirectory;

    public LoaderServiceImpl(final NamedParameterJdbcOperations jdbcTemplate, final LoaderRepository loaderRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.loaderRepository = loaderRepository;
    }

    @Override
    @Transactional
    public String addRecords() {
        List<UUID> recordsExists = loaderRepository.findOne(jdbcTemplate);
        if (!recordsExists.isEmpty()) {
            return RECORDS_STORED;
        }

        List<ParkingLotDetails> parkingLots = getParkingLotsFromSoredCVS();
        if (parkingLots.isEmpty()) {
            return FAIL;
        }

        List<ParkingLotEntity> lotEntities = parkingLots.stream().map(MapperUtils::fromModelToEntity).collect(Collectors.toList());
        loaderRepository.saveAll(lotEntities);

        return SUCCESS + "Added " + lotEntities.size() + " records in database.";
    }

    @Override
    public String addRecordsUploadCVS(final MultipartFile file) {
        List<UUID> recordsExists = loaderRepository.findOne(jdbcTemplate);
        if (!recordsExists.isEmpty()) {
            return RECORDS_STORED;
        }

        List<ParkingLotDetails> parkingLots = getParkingLotsFromUploadedCVS(file);
        if (parkingLots.isEmpty()) {
            return FAIL;
        }

        List<ParkingLotEntity> lotEntities = parkingLots.stream().map(MapperUtils::fromModelToEntity).collect(Collectors.toList());
        loaderRepository.saveAll(lotEntities);

        return SUCCESS + "Added " + lotEntities.size() + " records in database.";
    }

    @Override
    public String deleteAllRecords() {
        try {
            loaderRepository.deleteAll();
            return SUCCESS;
        } catch (RuntimeException re) {
            return FAIL;
        }
    }

    private List<ParkingLotDetails> getParkingLotsFromSoredCVS() {
        CSVReader csvReader;
        try {
            File initialFile = new File(csvFileDirectory);
            InputStream input = new FileInputStream(initialFile);

            Reader reader = new InputStreamReader(input);
            csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        } catch (IOException e) {
            LOG.error("Something went wrong in reading CSV file! Error: {}", e.getMessage());
            return Collections.emptyList();
        }

        return getParkingLotDetails(csvReader);
    }

    private List<ParkingLotDetails> getParkingLotsFromUploadedCVS(MultipartFile file) {
        CSVReader csvReader;
        try {
            Reader reader = new InputStreamReader(file.getInputStream());
            csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        } catch (IOException e) {
            LOG.error("Something went wrong in reading CSV file! Error: {}", e.getMessage());
            return Collections.emptyList();
        }

        return getParkingLotDetails(csvReader);
    }

    private List<ParkingLotDetails> getParkingLotDetails(CSVReader csvReader) {
        List<ParkingLotDetails> parkingLots = new ArrayList<>();
        try {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                parkingLots.add(new ParkingLotDetails(nextLine));
            }
        } catch (IOException | CsvValidationException e) {
            LOG.error("Something went wrong in reading line from CSV file. Error: {}", e.getMessage());
            return Collections.emptyList();
        }

        return parkingLots;
    }
}
