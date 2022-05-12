package com.example.PhaseTwo;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.service.impl.CustomerServiceImpl;
import com.example.PhaseTwo.model.service.impl.OrderServiceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@SpringBootApplication
public class PhaseTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhaseTwoApplication.class, args);
    }

    @Component
    static
    class OnStartup implements ApplicationRunner {
        CustomerServiceImpl customerService;
        OrderServiceImpl orderService;

        public OnStartup(CustomerServiceImpl customerService, OrderServiceImpl orderService) {
            this.customerService = customerService;
            this.orderService = orderService;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
//Arrange
            Customer customer = new Customer();
            customer.setFirstname("mehran");
            Customer customer1 = customerService.save(customer);
            Orders orders = new Orders();
            orders.setCustomer(customer);
            orders.setAddress("inja");
            orders.setRequiredDate(LocalDateTime.now());
            orders.setPrice(15L);
            //Act
            Orders orders1 = orderService.buildingOrder(orders, customer1);
            //Assert
            Orders loadedOrder = orderService.findById(orders1.getId());
            System.out.println(orders1.getId() != null);
            System.out.println(loadedOrder != null);
            System.out.println(Objects.equals(loadedOrder.getId(), orders1.getId()));
            System.out.println(Objects.equals(loadedOrder.getPrice(), orders1.getPrice()));
            System.out.println(Objects.equals(loadedOrder.getAddress(), orders1.getAddress()));
            System.out.println(Objects.equals(loadedOrder.getCustomer().getId(), orders1.getCustomer().getId()));


        }
    }

}
