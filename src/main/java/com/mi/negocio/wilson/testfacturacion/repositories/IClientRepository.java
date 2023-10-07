package com.mi.negocio.wilson.testfacturacion.repositories;

import com.mi.negocio.wilson.testfacturacion.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Spring data repository for the {@link com.mi.negocio.wilson.testfacturacion.entities.Client} entity
 *
 * @author wcajisaca
 */
@Repository
public interface IClientRepository extends JpaRepository<Client, Serializable> {
    /**
     * Get client by identification or surname
     *
     * @param filter Owner client identification
     * @return {@link Client}
     */
    @Query("SELECT c FROM Client c" +
            " WHERE c.status = :status" +
            " AND (c.identification = :filter OR LOWER(c.surname) LIKE LOWER(CONCAT('%', :filter, '%')))")
    Set<Client> findByStatusAndIdentificationOrSurname(@Param("status") Integer status, @Param("filter") String filter);

    /**
     * Get client by identification
     * @param id
     * @param status
     * @return
     */
    Optional<Client> findByIdentificationAndStatus(String id, Integer status);

    /**
     * Get client by id and status
     * @param id
     * @param status
     * @return
     */
    Optional<Client> findByIdAndStatus(UUID id, Integer status);
}