package com.markobozic.parkinglots.controller;

import com.markobozic.parkinglots.service.LoaderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String addRecords() {
        return loaderService.addRecords();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add-upload")
    public String addRecordsUploadCVS(@RequestPart("file") final MultipartFile file,
                                      @RequestParam(value = "skipLines", required = false) final Integer skipLines) {
        return loaderService.addRecordsUploadCVS(file, skipLines);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete-all")
    public String deleteAllRecords() {
        return loaderService.deleteAllRecords();
    }

}
