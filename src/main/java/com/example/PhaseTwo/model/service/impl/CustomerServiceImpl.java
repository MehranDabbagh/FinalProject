package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.*;
import com.example.PhaseTwo.model.entity.dto.CustomerDto;
import com.example.PhaseTwo.model.entity.dto.PayingWithCredit;
import com.example.PhaseTwo.model.repository.BidRepository;
import com.example.PhaseTwo.model.repository.CustomerRepository;
import com.example.PhaseTwo.model.repository.ExpertRepository;
import com.example.PhaseTwo.model.repository.OrdersRepository;
import com.example.PhaseTwo.model.service.CustomerService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private OrdersRepository ordersRepository;
    private BidRepository bidRepository;
    private ExpertRepository expertRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, OrdersRepository ordersRepository, BidRepository bidRepository, ExpertRepository expertRepository) {
        this.customerRepository = customerRepository;
        this.ordersRepository = ordersRepository;
        this.bidRepository = bidRepository;
        this.expertRepository = expertRepository;
    }

    @Override
    public CustomerDto save(Customer customer) {
        customer.setSingUpDate(LocalDateTime.now());
        customer.setRole(Role.ROLE_COSTUMER);
        Customer customer1 = customerRepository.save(customer);
        System.out.println(customer1.getId());
        return convertingToDto(customer1);
    }

    @Override
    public void update(CustomerDto customer) {
        customerRepository.save(convertingToCustomer(customer));
    }

    @Override
    public void changingPassword(Long Id, String password) {
        Customer customer = customerRepository.findById(Id).orElse(null);
        if (customer != null) {
            customer.setPassword(password);
            customerRepository.save(customer);
            return;
        }
        throw new NullPointerException("wrong id!");
    }

    private Example<Customer> createExample(String firstName, String lastName, String email) {
        Customer customer = new Customer();
        customer.setFirstname(firstName);
        customer.setLastname(lastName);
        customer.setUsername(email);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase("firstName", "lastName", "email")
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Customer> usersExample = Example.of(customer, matcher);
        return usersExample;
    }

    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return convertingToDto(customer);
        }
        throw new NullPointerException("wrong id!");
    }

    @Override
    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll().stream().collect(Collectors.toList());
        return customers.stream().map(customer -> convertingToDto(customer)).collect(Collectors.toList());

    }

    @Override
    public void delete(CustomerDto customer) {
        customerRepository.deleteById(customer.getId());
    }

    @Override
    public List<CustomerDto> findByOptional(String firstname, String lastname, String email) {
        Example<Customer> experts = createExample(firstname, lastname, email);
        List<Customer> customers1 = new ArrayList<>();
        customerRepository.findAll(experts).forEach(customers1::add);
        return customers1.stream().map(customer -> convertingToDto(customer)).collect(Collectors.toList());
    }

    @Override
    public List<Orders> findOrders(Long CustomerId) {
        return ordersRepository.findByCustomerId(CustomerId);
    }

    @Override
    public void finishingOrder(Long customerId, Long orderId, Long bidId) {
        Orders orders = ordersRepository.findById(orderId).orElse(null);
        LocalDateTime now = LocalDateTime.now();
        Bid bid = bidRepository.findById(bidId).orElse(null);
        if (orders == null || bid == null) {
            throw new NullPointerException("not found!");
        }
        if (orders.getCustomer().getId() != customerId) {
            throw new NullPointerException("order and customer dose not match!");
        }
        if (orders.getRequiredDate().plusHours(bid.getHoursNeeded()).isBefore(now)) {
            Integer delay = now.getHour() - (orders.getRequiredDate().plusHours(bid.getHoursNeeded()).getHour());
            if (delay > 0) {
                Expert expert = expertRepository.findById(bid.getExpert().getId()).orElse(null);
                if (expert != null) {
                    if (delay > 20) {
                        expert.setPoint(0l);
                    } else {
                        expert.setPoint(expert.getPoint() * ((100 - (delay * 5))) / 100);
                    }
                    expertRepository.save(expert);
                }
            }
        }
        orders.setStatus(Status.Done);
        ordersRepository.save(orders);

    }

    @Override
    public void payingWithCredit(PayingWithCredit payingWithCredit) {
        Orders orders = ordersRepository.findById(payingWithCredit.getOrderId()).orElse(null);
        if (orders == null || orders.getCustomer().getId() != payingWithCredit.getCustomerId()) {
            throw new NullPointerException("bad request!");
        }
        Bid bid = bidRepository.findById(payingWithCredit.getBidId()).orElse(null);
        if (bid == null || bid.getExpert().getId() != payingWithCredit.getExpertId() || bid.getOffer() != payingWithCredit.getPrice()) {
            throw new NullPointerException("bad request!");
        }
        Customer customer = customerRepository.findById(payingWithCredit.getCustomerId()).orElse(null);
        if (customer.getCredit() < payingWithCredit.getPrice()) {
            throw new InputMismatchException("not enough money!");
        }
        customer.setCredit(customer.getCredit() - payingWithCredit.getPrice());
        Expert expert = expertRepository.findById(payingWithCredit.getExpertId()).orElse(null);
        expert.setCredit(expert.getCredit() + Math.round((payingWithCredit.getPrice() * 0.7)));
        expertRepository.save(expert);
        customerRepository.save(customer);

    }

    public CustomerDto convertingToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getFirstname(), customer.getLastname(), customer.getUsername(), customer.getVerified(), customer.getSingUpDate(), customer.getCredit());
        return customerDto;
    }

    public Customer convertingToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstname(customerDto.getFirstName());
        customer.setLastname(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setSingUpDate(customerDto.getSingUpDate());
        customer.setCredit(customerDto.getCredit());
        customer.setVerified(customerDto.getVerified());
        customer.setRole(customerDto.getRole());
        return customer;
    }

}
