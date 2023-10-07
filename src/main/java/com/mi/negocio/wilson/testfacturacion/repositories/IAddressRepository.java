package com.mi.negocio.wilson.testfacturacion.repositories;

import com.mi.negocio.wilson.testfacturacion.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Spring data repository for the {@link Address} entity
 */
@Repository
public interface IAddressRepository extends JpaRepository<Address, Serializable> {
    Set<Address> findByIdClientAndStatus(UUID idClient, Integer status);
    @Modifying
    @Query("UPDATE Address d SET d.mainAddress = 0 WHERE d.idClient = :CLIENT_ID AND d.mainAddress = 1")
    void updateAddressMatriz(@Param("CLIENT_ID") UUID clientId);
}