package com.erickmob.xyinc.controller;

import com.erickmob.xyinc.dto.PoiDTO;
import com.erickmob.xyinc.dto.PoisNearByDTO;
import com.erickmob.xyinc.entity.Poi;
import com.erickmob.xyinc.repository.PoiRepository;
import com.erickmob.xyinc.service.PoiService;
import com.erickmob.xyinc.util.TestUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PoiControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PoiService poiService;

    @Autowired
    private PoiRepository poiRepository;

    @Before
    public void setup() {
        tearDown();
        mockMvc = MockMvcBuilders.standaloneSetup(new PoiController(poiService)).build();
    }

    @After
    public void tearDown() {
        poiRepository.deleteAll();
    }

    @Test
    public void testCreatePoiDTOListAndExpectIsCreated() throws Exception {
        try{

            mockMvc.perform(
                    post("/api/poi")
                            .contentType(TestUtil.APPLICATION_JSON_UTF8)
                            .content(TestUtil.convertObjectToJsonBytes(getTestPoiList())))
                    .andExpect(status().isCreated());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAllPoiDTOAndExpectIsOkAndSize7() throws Exception {
        try{

            poiService.saveAll(getTestPoiList());

            ResultActions resultActions = mockMvc.perform(
                    get("/api/poi")
                            .contentType(TestUtil.APPLICATION_JSON_UTF8)
                            )
                    .andExpect(status().isOk());
            String contentAsString = getFromResponse(resultActions);
            List<PoiDTO> response = objectMapper.readValue(contentAsString,  new TypeReference<List<PoiDTO>>(){});

            Assert.assertEquals(response.size(), 7);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Test
    public void testNearby() throws Exception {
        try{

            poiService.saveAll(getTestPoiList());

            ResultActions resultActions = mockMvc.perform(
                    post("/api/poi/nearby")
                            .contentType(TestUtil.APPLICATION_JSON_UTF8)
                            .content(TestUtil.convertObjectToJsonBytes(getPoisNearByDTO())))
                    .andExpect(status().isOk());

            String contentAsString = getFromResponse(resultActions);
            List<Poi> response = objectMapper.readValue(contentAsString,  new TypeReference<List<Poi>>(){});

            Assert.assertNotNull(response);
            Assert.assertEquals(response.size(), 4);

            Assert.assertEquals(response.get(0).getX(), 27);
            Assert.assertEquals(response.get(0).getY(), 12);

            Assert.assertEquals(response.get(1).getX(), 15);
            Assert.assertEquals(response.get(1).getY(), 12);

            Assert.assertEquals(response.get(2).getX(), 12);
            Assert.assertEquals(response.get(2).getY(), 8);

            Assert.assertEquals(response.get(3).getX(), 23);
            Assert.assertEquals(response.get(3).getY(), 6);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getFromResponse(ResultActions resultActions) throws UnsupportedEncodingException {
        MvcResult result = resultActions.andReturn();
        return result.getResponse().getContentAsString();
    }

    private List<PoiDTO> getTestPoiList() {
        PoiDTO lanchonete = new PoiDTO(27, 12);
        PoiDTO posto = new PoiDTO(31,18);
        PoiDTO joalheria = new PoiDTO(15,12);
        PoiDTO floricultura= new PoiDTO(19,21);
        PoiDTO pub = new PoiDTO(12,8);
        PoiDTO supermercado = new PoiDTO(23,6);
        PoiDTO churrascaria = new PoiDTO(28,2);
        List<PoiDTO> list = Arrays.asList(lanchonete
        ,posto
        ,joalheria
        ,floricultura
        ,pub
        ,supermercado
        ,churrascaria);
        return list;
    }

    private PoisNearByDTO getPoisNearByDTO(){
        return new PoisNearByDTO(20,10,10);
    }

}
