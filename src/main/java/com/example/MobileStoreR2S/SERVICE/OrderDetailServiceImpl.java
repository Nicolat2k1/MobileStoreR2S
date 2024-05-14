package com.example.MobileStoreR2S.SERVICE;

import java.util.List;
import java.util.stream.Collectors;

import com.example.MobileStoreR2S.DTO.OrderDetailDTO;
import com.example.MobileStoreR2S.ENTITY.OrderDetail;
import com.example.MobileStoreR2S.EXCEPTION.NotFoundException;
import com.example.MobileStoreR2S.MAPPER.OrderDetailMP;
import com.example.MobileStoreR2S.REPOSITORY.OrderDetailRPS;
import org.springframework.stereotype.Service;



@Service
public class OrderDetailServiceImpl implements OrderDetailSV{
    private final OrderDetailMP orderDetailMapper;
    private final OrderDetailRPS orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailMP orderDetailMapper, OrderDetailRPS orderDetailRepository) {
        this.orderDetailMapper = orderDetailMapper;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetailDTO> findAll() {
        return orderDetailRepository.findAll().stream()
                .map(orderDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDetailDTO findById(long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        return orderDetailMapper.toDTO(orderDetail);
    }
}
