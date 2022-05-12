package com.example.PhaseTwo;

import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.service.impl.BidServiceImpl;
import com.example.PhaseTwo.model.service.impl.CustomerServiceImpl;
import com.example.PhaseTwo.model.service.impl.ExpertServiceImpl;
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
        BidServiceImpl bidService;
        ExpertServiceImpl expertService;

        public OnStartup(CustomerServiceImpl customerService, OrderServiceImpl orderService, BidServiceImpl bidService, ExpertServiceImpl expertService) {
            this.customerService = customerService;
            this.orderService = orderService;
            this.bidService = bidService;
            this.expertService = expertService;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            //Making an order
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


            //Making a bid
            //Arrange
            Expert expert = new Expert();
            expert.setFirstname("ehsan");
            Expert expert1 = expertService.save(expert);
            Bid bid = new Bid();
            bid.setExpert(expert1);
            bid.setTimeToStart(LocalDateTime.of(2027, 11, 16, 11, 10));
            bid.setBidDate(LocalDateTime.now());
            bid.setHoursNeeded(5l);
            //Act
            Bid bid1 = bidService.save(bid, expert1, orders1);
            //Assert
            Bid loadedBid = bidService.findById(bid1.getId());
            System.out.println(bid1.getId() != null);
            System.out.println(loadedBid != null);
            System.out.println(Objects.equals(loadedBid.getId(), bid1.getId()));
            System.out.println(Objects.equals(loadedBid.getHoursNeeded(), bid1.getHoursNeeded()));
            System.out.println(Objects.equals(loadedBid.getTimeToStart(), bid1.getTimeToStart()));
            System.out.println(Objects.equals(loadedBid.getExpert().getId(), bid1.getExpert().getId()));
            System.out.println(Objects.equals(loadedBid.getOrders().getId(), orders1.getId()));


        }
    }

}
