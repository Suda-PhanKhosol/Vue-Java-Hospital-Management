package com.cpe.register.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="Gender")
public class Gender {

    @Id
    @SequenceGenerator(name="Gender_seq",sequenceName="Gender_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Gender_seq")  
    @Column(name="Gender_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NotNull String gen_name;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Register> regis;
}
