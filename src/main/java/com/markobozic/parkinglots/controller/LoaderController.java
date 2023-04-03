package com.markobozic.parkinglots.controller;

import com.markobozic.parkinglots.service.LoaderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

}
