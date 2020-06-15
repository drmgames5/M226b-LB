package bztf.shopapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bztf.shopapi.model.Product;

/**
 * ProductRepository
 * - Das ist eine Schnittstelle zur Datenbank
 */
@Repository // Spring Boot macht daraus eine StandardRepository
public interface ProductRepository extends CrudRepository<Product, Long>{

    
}