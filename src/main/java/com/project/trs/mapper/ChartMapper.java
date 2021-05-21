package com.project.trs.mapper;

import com.project.trs.response.ChartResponse;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface ChartMapper {

    default ChartResponse createChartResponse(Map<String, Long> chartMap) {
        return ChartResponse.builder()
                .chartMap(chartMap)
                .build();
    }
}
