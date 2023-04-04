package com.markobozic.parkinglots.service;

import org.springframework.web.multipart.MultipartFile;

public interface LoaderService {
    String addRecords();

    String addRecordsUploadCVS(final MultipartFile file);

    String deleteAllRecords();
}
