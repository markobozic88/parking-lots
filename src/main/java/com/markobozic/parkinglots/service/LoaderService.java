package com.markobozic.parkinglots.service;

import org.springframework.web.multipart.MultipartFile;

public interface LoaderService {
    String addRecords();

    String addRecordsUploadCVS(MultipartFile file, int skipLines);

    String deleteAllRecords();
}
