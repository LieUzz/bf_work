package com.example.shoppingapp.dao;

import com.example.shoppingapp.dao.AbstractHibernateDAO;
import com.example.shoppingapp.entity.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("orderDetailDao")
public class OrderDetailDao extends AbstractHibernateDAO<OrderDetail> {

    public OrderDetailDao() {
        setClazz(OrderDetail.class);
    }

    public OrderDetail getById(Integer id) {
        return findById(id);
    }
    public List<OrderDetail> getAllOrderDetails() {
        return loadAllData();
    }

    public void createNewOrderDetail(OrderDetail orderDetail) {
        createData(orderDetail);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(Integer oid) {
        return loadAllData().stream()
                .filter(a->a.getOrder().getId().equals(oid))
                .collect(Collectors.toList());
    }

}
