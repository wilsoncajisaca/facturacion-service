package com.mi.negocio.wilson.testfacturacion.entities.audit;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class Audit implements Serializable {
    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        createdOn = new Date();
        createdBy = "";
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = new Date();
        updatedBy = "";
    }

    @Tolerate
    public Audit(){
        super();
    }
}