package com.ejbank.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ejbank_advisor")
public class AdvisorEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }
}
