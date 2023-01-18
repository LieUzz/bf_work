package com.example.shoppingapp.dao;

import com.example.shoppingapp.dao.AbstractHibernateDAO;
import com.example.shoppingapp.entity.Order;
import com.example.shoppingapp.entity.util.OrderResult;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("orderDao")
public class OrderDao extends AbstractHibernateDAO<Order> {

    public OrderDao() {
        setClazz(Order.class);
    }

    public Order getById(Integer id) {
        return findById(id);
    }
    public List<Order> getAllOrders() {
        return loadAllData();
    }

    public List<OrderResult> getAllOrdersForAsync() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<OrderResult> orderResults = new ArrayList<>();
        loadAllData().forEach(o -> orderResults.add(OrderResult.builder()
                .order_id(o.getId())
                .user_id(o.getUser().getId())
                .order_status(o.getOrder_status())
                .order_time(o.getOrder_time())
                .user_name(o.getUser().getUsername())
                .build()));
        return orderResults;
    }

    public void createNewOrder(Order order) {
        createData(order);
    }

    public List<Order> getOrdersByUserId(Integer uid) {
        return loadAllData()
                .stream()
                .filter(a->a.getUser().getId().equals(uid))
                .collect(Collectors.toList());
    }

    public void updateOrder(Order order) {
        updateData(order);
    }
}
