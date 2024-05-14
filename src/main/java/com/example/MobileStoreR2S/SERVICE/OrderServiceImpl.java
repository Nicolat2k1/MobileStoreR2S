package com.example.MobileStoreR2S.SERVICE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.MobileStoreR2S.DTO.OrderDTO;
import com.example.MobileStoreR2S.DTO.OrderDetailDTO;
import com.example.MobileStoreR2S.ENTITY.Order;
import com.example.MobileStoreR2S.ENTITY.OrderDetail;
import com.example.MobileStoreR2S.ENTITY.Product;
import com.example.MobileStoreR2S.ENTITY.User;
import com.example.MobileStoreR2S.EXCEPTION.NotFoundException;
import com.example.MobileStoreR2S.MAPPER.OrderMP;
import com.example.MobileStoreR2S.MAPPER.ProductMP;
import com.example.MobileStoreR2S.REPOSITORY.OrderDetailRPS;
import com.example.MobileStoreR2S.REPOSITORY.OrderRPS;
import com.example.MobileStoreR2S.REPOSITORY.UserRPS;
import org.springframework.stereotype.Service;



@Service
public class OrderServiceImpl implements OrderSV {
    private final OrderMP orderMapper;
    private final OrderRPS orderRepository;
    private final UserRPS userRepository;
    private final OrderDetailRPS orderDetailRepository;
    private final ProductSV productService;
    private final ProductMP productMapper;

    public OrderServiceImpl(OrderMP orderMapper, OrderRPS orderRepository, UserRPS userRepository,
                            OrderDetailRPS orderDetailRepository, ProductServiceImpl productServicer, ProductMP productMapper) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productService = productServicer;
        this.productMapper = productMapper;
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO findById(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        return orderMapper.toDTO(order);
    }

    public OrderDTO create(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new NotFoundException(orderDTO.getUserId()));

        Order order = orderMapper.toEntity(orderDTO);
        order.setUser(user);

        int totalQuantity = 0;
        double grandTotal = 0.0;
        List<OrderDetail> listOrderDetails = new ArrayList<>();

        for (OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetailDTOs()) {
            Product product = productMapper.toEntity(productService.findById(orderDetailDTO.getProductId()));

            // Tạo một đối tượng OrderDetail mới và thiết lập thông tin
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(orderDetailDTO.getQuantity());
            orderDetail.setUnitPrice(product.getUnitPrice());

            // Tính toán giá trị price cho OrderDetail
            double price = orderDetail.getQuantity() * orderDetail.getUnitPrice();
            orderDetail.setPrice(price);

            // Cập nhật tổng số lượng và tổng giá trị của đơn hàng
            totalQuantity += orderDetail.getQuantity();
            grandTotal += price;

            listOrderDetails.add(orderDetail);
        }

        listOrderDetails = orderDetailRepository.saveAll(listOrderDetails);

        for (OrderDetail orderDetail : listOrderDetails) {
            orderDetail.setOrder(order);
        }

        // Cập nhật thông tin tổng số lượng và tổng giá trị của đơn hàng
        order.setTotalQuantity(totalQuantity);
        order.setGrandTotal(grandTotal);
        order.setOrderDetails(listOrderDetails);

        order = orderRepository.save(order);

        return orderMapper.toDTO(order);
    }

    public void delete(long id) {
        if (!orderRepository.existsById(id)) {
            throw new NotFoundException(id);
        }

        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderDetailId(id);

        if (orderDetails != null && !orderDetails.isEmpty()) {
            orderDetailRepository.deleteAll(orderDetails);
        }

        orderRepository.deleteById(id);
    }


}
