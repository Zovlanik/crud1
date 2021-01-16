package com.zovlanik.crud.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="account_status")
public enum AccountStatus{
    ACTIVE, BANNED, DELETED
}
