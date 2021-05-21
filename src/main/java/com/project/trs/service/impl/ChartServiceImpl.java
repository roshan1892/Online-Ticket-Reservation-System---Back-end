package com.project.trs.service.impl;

import com.project.trs.mapper.ChartMapper;
import com.project.trs.repository.PaymentRepository;
import com.project.trs.repository.TicketRepository;
import com.project.trs.response.ChartResponse;
import com.project.trs.service.ChartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChartServiceImpl implements ChartService {
    private final TicketRepository ticketRepository;
    private final PaymentRepository paymentRepository;
    private final ChartMapper chartMapper;

    @Override
    public ChartResponse getChartData(String user) {
        Map<String, Long> chartMap = new HashMap<>();
        chartMap.put("ticket", ticketRepository.countByCreatedBy(user));
        chartMap.put("payment", paymentRepository.countByCreatedBy(user));
        return chartMapper.createChartResponse(chartMap);
    }
}
