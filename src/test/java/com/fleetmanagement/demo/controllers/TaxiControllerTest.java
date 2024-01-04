package com.fleetmanagement.demo.controllers;

import com.fleetmanagement.demo.models.TaxiModel;
import com.fleetmanagement.demo.repository.TaxiRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaxiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TaxiRepository taxiRepository;

    @Test
    void getAllTaxis() throws Exception {
        TaxiModel taxi = new TaxiModel();
        taxi.setPlate("ABC-1234");
        taxi.setId(1234);

        ArrayList<TaxiModel> taxiList = new ArrayList<>();
        taxiList.add(taxi);

        Mockito.when(taxiRepository.findAll()).thenReturn(taxiList);

        this.mockMvc.perform(get("/taxis"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1234,'plate':'ABC-1234'}]"));
    }

    @Test
    void getAllTaxisPagleable() throws Exception{
        TaxiModel taxi = new TaxiModel();
        taxi.setPlate("ABC-1234");
        taxi.setId(1234);

        Page<TaxiModel> page = new PageImpl<>(List.of(taxi));

        Mockito.when(taxiRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(page);

        this.mockMvc.perform(get("/pageable/taxis"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'content':[{'id':1234,'plate':'ABC-1234'}],'pageable':'INSTANCE','last':true,'totalPages':1,'totalElements':1,'size':1,'number':0,'sort':{'sorted':false,'empty':true,'unsorted':true},'first':true,'numberOfElements':1,'empty':false}"));
    }

}
