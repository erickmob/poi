package com.erickmob.xyinc.controller;

import com.erickmob.xyinc.dto.PoiDTO;
import com.erickmob.xyinc.dto.PoisNearByDTO;
import com.erickmob.xyinc.entity.Poi;
import com.erickmob.xyinc.service.PoiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/poi")
@Api(tags = "Poi")
public class PoiController {

    @Autowired
    private PoiService poiService;

    private final Logger log = LoggerFactory.getLogger(PoiController.class);

    public PoiController(PoiService service){
        this.poiService = service;
    }

    @PostMapping
    @ApiOperation(value = "Saving new poi list.")
    @ResponseStatus(HttpStatus.CREATED)
    public List<PoiDTO> save(@RequestBody List<PoiDTO> poiList) throws IOException {
        poiService.saveAll(poiList);
        return poiList;
    }

    @GetMapping
    @ApiOperation(value = "Find all Pois")
    @ResponseStatus(HttpStatus.OK)
    public List<Poi> findAll() {
        return poiService.findAll();
    }

    @PostMapping(path = "/nearby")
    @ApiOperation(value = "Find Pois near by x and y and within distance d")
    @ResponseStatus(HttpStatus.OK)
    public List<Poi> nearby(@RequestBody PoisNearByDTO poisNearByDTO) throws IOException {
        log.debug("REST request to convert: {}", poisNearByDTO);
        return poiService.findAllNearBy(poisNearByDTO);
    }


}
