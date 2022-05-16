package com.restapijwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.restapijwt.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.sql.Time;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
//@Where(clause = "active=true")
//@JsonIgnoreProperties(value = {"id,name"})
public class Company extends AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //company qo'shilganda userniyam avtomatik save qilish PERSIST
    //company delete userniyam avtomatik delete qilish REMOVE
    //company edit userniyam avtomatik edit qilish MERGE
    //company crud userniyam avtomatik crud qilish ALL
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//    @OneToOne(cascade = CascadeType.ALL)
    @OneToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User director;


}
