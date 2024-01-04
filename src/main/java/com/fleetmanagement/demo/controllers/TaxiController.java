package com.fleetmanagement.demo.controllers;

import com.fleetmanagement.demo.models.TaxiModel;
import com.fleetmanagement.demo.repository.TaxiRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaxiController {

    @Autowired
    TaxiRepository taxiRepository;

    @Operation(summary = "Get all taxis from database")
    @GetMapping("/taxis")
    public ResponseEntity<List<TaxiModel>> getAllTaxis(){
        return ResponseEntity.status(HttpStatus.OK).body(taxiRepository.findAll());
    }

    @GetMapping("/pageable/taxis")
    public ResponseEntity<Page<TaxiModel>> getAllTaxisPageable(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(taxiRepository.findAll(pageable));
    }
}
