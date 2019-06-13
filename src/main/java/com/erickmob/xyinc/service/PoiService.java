package com.erickmob.xyinc.service;

import com.erickmob.xyinc.dto.PoiDTO;
import com.erickmob.xyinc.dto.PoisNearByDTO;
import com.erickmob.xyinc.entity.Poi;
import com.erickmob.xyinc.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoiService {

    @Autowired
    private PoiRepository poiRepository;

    public void saveAll(List<PoiDTO> poiList) {
        poiList.stream().forEach(e -> this.poiRepository.save(new Poi(e.getX(), e.getY())));
    }


    public List<Poi> findAll() {
        return this.poiRepository.findAll();
    }

    public List<Poi> findAllNearBy(PoisNearByDTO poisNearByDTO) {
        Poi from = this.createFromPoi(poisNearByDTO);
        return this.findAll()
                .stream()
                .filter(e -> from.getPoiAsPoint().distance(e.getPoiAsPoint()) < poisNearByDTO.getDistance())
                .collect(Collectors.toList());
    }

    private Poi createFromPoi(PoisNearByDTO poisNearByDTO) {
        return new Poi(poisNearByDTO.getX(), poisNearByDTO.getY());
    }
}
