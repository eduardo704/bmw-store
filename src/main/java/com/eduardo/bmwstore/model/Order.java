package com.eduardo.bmwstore.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.eduardo.bmwstore.converters.ProductConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false,  updatable = false)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @Convert(converter = ProductConverter.class)
    @Lob
    private List<Product> products;
}
