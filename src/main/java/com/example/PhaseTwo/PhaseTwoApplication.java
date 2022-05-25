package com.example.PhaseTwo;

import com.example.PhaseTwo.model.entity.*;
import com.example.PhaseTwo.model.service.impl.*;
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
        UserServiceImpl userService;
        AdminServiceImpl adminService;

        public OnStartup(CustomerServiceImpl customerService, OrderServiceImpl orderService, BidServiceImpl bidService, ExpertServiceImpl expertService, UserServiceImpl userService, AdminServiceImpl adminService) {
            this.customerService = customerService;
            this.orderService = orderService;
            this.bidService = bidService;
            this.expertService = expertService;
            this.userService = userService;
            this.adminService = adminService;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {

            //  userService.changingPassword(83l, "mehrandab1999");
            //   Expert expert=new Expert();
            //    expert.setId(116l);
            //    SubService service=new SubService();
            //   service.setId(113l);
            //   expertService.linkingExpertToSubService(expert,service);
        //    SubService subService = new SubService();
          //  subService.setId(113L);
         //   System.out.println(expertService.findingExpertsBySubService(subService));

        }
    }

}
