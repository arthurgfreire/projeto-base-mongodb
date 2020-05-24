package com.agf.mongo.service.impl;

import com.agf.mongo.service.RegionService;
import com.agf.mongo.domain.Region;
import com.agf.mongo.repository.RegionRepository;
import com.agf.mongo.service.dto.RegionDTO;
import com.agf.mongo.service.mapper.RegionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Region}.
 */
@Service
public class RegionServiceImpl implements RegionService {

    private final Logger log = LoggerFactory.getLogger(RegionServiceImpl.class);

    private final RegionRepository regionRepository;

    private final RegionMapper regionMapper;

    public RegionServiceImpl(RegionRepository regionRepository, RegionMapper regionMapper) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    /**
     * Save a region.
     *
     * @param regionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RegionDTO save(RegionDTO regionDTO) {
        log.debug("Request to save Region : {}", regionDTO);
        Region region = regionMapper.toEntity(regionDTO);
        region = regionRepository.save(region);
        return regionMapper.toDto(region);
    }

    /**
     * Get all the regions.
     *
     * @return the list of entities.
     */
    @Override
    public List<RegionDTO> findAll() {
        log.debug("Request to get all Regions");
        return regionRepository.findAll().stream()
            .map(regionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one region by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<RegionDTO> findOne(String id) {
        log.debug("Request to get Region : {}", id);
        return regionRepository.findById(id)
            .map(regionMapper::toDto);
    }

    /**
     * Delete the region by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Region : {}", id);

        regionRepository.deleteById(id);
    }
}
