package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.DataException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface ProductRepository extends CrudRepository<Product, Integer> {
    
    List<Product> findAll();

    @Query("SELECT p FROM ProductType p")
    List<ProductType> findAllProductTypes();

    @Query("SELECT p FROM ProductType p WHERE p.name=:productName")
    ProductType findProductTypeByName(@Param("productName") String name) throws DataException;

    @Query("SELECT p FROM Product p WHERE p.price<?1")
    List<Product> findByPriceLessThan(Double cost) throws DataException;

    Optional<Product> findById(int id);
    Product findByName(String name);
    
    @SuppressWarnings("unchecked")
    Product save(Product p);
}
