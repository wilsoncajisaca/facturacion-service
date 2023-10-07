package com.mi.negocio.wilson.testfacturacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

import static com.mi.negocio.wilson.testfacturacion.commons.ConstantsFacturacion.ACTIVE_STATUS;

/**
 * @author wcajisaca
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Entity
@Table(name = "address")
@Builder
public class Address implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_address",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "id_client")
    @With
    @JsonIgnore
    private UUID idClient;
    @JsonIgnore
    @JoinColumn(name = "id_client",
            referencedColumnName = "id",
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_client"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Client client;

    @Column(name = "main_address", nullable = false, length = 1)
    @With
    private Integer mainAddress;

    @Size(min = 3, max = 100)
    @Column(name = "province", length = 100)
    @With
    private String province;

    @Size(min = 3, max = 100)
    @Column(name = "city", length = 100)
    @With
    private String city;

    @Size(min = 3, max = 250)
    @Column(name = "reference_address", nullable = false, length = 250)
    @With
    private String referenceAddress;

    @Column(name = "status", nullable = false, length = 1)
    @With
    @JsonIgnore
    private Integer status;

    @PrePersist
    public void prePersist() {
        this.status = ACTIVE_STATUS;
    }

    @Tolerate
    public Address() {
        super();
    }

}
