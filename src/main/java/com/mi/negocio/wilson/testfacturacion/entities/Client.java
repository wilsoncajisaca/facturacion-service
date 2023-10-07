package com.mi.negocio.wilson.testfacturacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mi.negocio.wilson.testfacturacion.entities.audit.Audit;
import com.mi.negocio.wilson.testfacturacion.validations.EcuadorianDNI;
import com.mi.negocio.wilson.testfacturacion.validations.Validations;
import lombok.*;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends Audit implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_client",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "identification_type", length = 3, nullable = false)
    @With
    private String identificationType;

    @EcuadorianDNI
    @Column(name = "identification", length = 13, nullable = false, unique = true)
    @With
    private String identification;

    @Column(name = "name", length = 100, nullable = false)
    @With
    private String name;

    @Column(name = "surname", length = 100, nullable = false)
    @With
    private String surname;

    @Pattern(regexp = Validations.REGEX_MAIL_VALIDATION, message = "Email required")
    @Column(name = "email", length = 250, nullable = false)
    @With
    private String email;

    @Pattern(regexp = Validations.REGEX_CELL_VALIDATION, message = "Invalid Number phone")
    @Column(name = "number_cellphone", length = 10)
    @With
    private String numberCellphone;

    @Column(name = "status", length = 1)
    @JsonIgnore
    @With
    private Integer status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "client")
    @Where(clause = "main_address = 1 AND status = 1")
    private Set<Address> addresses = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.status = 1;
    }

    @Tolerate
    public Client(){
        super();
    }
}
