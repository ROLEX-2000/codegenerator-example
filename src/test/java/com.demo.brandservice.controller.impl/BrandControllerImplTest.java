package com.demo.brandservice.controller.impl;

import com.demo.brandservice.controller.impl.BrandControllerImpl;
import com.demo.brandservice.controller.impl.CustomUtils;
import com.demo.brandservice.dto.BrandDTO;
import com.demo.brandservice.entity.Brand;
import com.demo.brandservice.mapper.BrandMapper;
import com.demo.brandservice.mapper.ReferenceMapper;
import com.demo.brandservice.service.BrandService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BrandControllerImplTest {
    //TODO: create the data Test generator class BrandBuilder
    private static final String ENDPOINT_URL = "/brands";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private BrandControllerImpl brandController;
    @MockBean
    private BrandService brandService;
    @MockBean
    private BrandMapper brandMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.brandController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(brandMapper.asDTOList(ArgumentMatchers.any())).thenReturn(BrandBuilder.getListDTO());

        Mockito.when(brandService.findAll()).thenReturn(BrandBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(brandMapper.asDTO(ArgumentMatchers.any())).thenReturn(BrandBuilder.getDTO());

        Mockito.when(brandService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(BrandBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(brandService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(brandService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(brandMapper.asEntity(ArgumentMatchers.any())).thenReturn(BrandBuilder.getEntity());
        Mockito.when(brandService.save(ArgumentMatchers.any(Brand.class))).thenReturn(BrandBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(BrandBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(brandService, Mockito.times(1)).save(ArgumentMatchers.any(Brand.class));
        Mockito.verifyNoMoreInteractions(brandService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(brandMapper.asEntity(ArgumentMatchers.any())).thenReturn(BrandBuilder.getEntity());
        Mockito.when(brandService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(BrandBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(BrandBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(brandService, Mockito.times(1)).update(ArgumentMatchers.any(Brand.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(brandService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(brandService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(brandService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(brandService);
    }