package com.markobozic.parkinglots.controller;

import com.markobozic.parkinglots.service.LoaderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/loader")
public class LoaderController {

    private final LoaderService loaderService;

    public LoaderController(final LoaderService loaderService) {
        this.loaderService = loaderService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/add")
    @ApiOperation(value = "Trigger this API to add records in DB from stored CSV file.")
    public String addRecords() {
        return loaderService.addRecords();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add-upload")
    @ApiOperation(value = "API for add records in DB from uploaded CSV.")
    public String addRecordsUploadCVS(
            @ApiParam(name = "file", value = "CSV file for upload.")
            @RequestPart("file") final MultipartFile file) {
        return loaderService.addRecordsUploadCVS(file);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete-all")
    @ApiOperation(value = "API for delete all records from DB.")
    public String deleteAllRecords() {
        return loaderService.deleteAllRecords();
    }

}
