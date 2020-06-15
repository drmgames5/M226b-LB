package bztf.shopapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bztf.shopapi.model.Order;

/**
 * OrderRepository
 * - Das ist eine Schnittstelle zur Datenbank
 */
@Repository // Spring Boot macht daraus eine StandardRepository
public interface OrderRepository extends CrudRepository<Order, Long>{

    
}