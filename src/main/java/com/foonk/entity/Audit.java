package com.foonk.entity;

import jdk.dynalink.Operation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Serializable entityId;
    private String entityName;
    private String entityContent;
    private Operation operation;
    public enum Operation{
        SAVE, UPDATE, DELETE, INSERT
    }

}
