package com.restapijwt.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

public enum TaskStatus {
    NEW, OPEN, IN_PROGRESS, COMPLETED, CANCELED
}
