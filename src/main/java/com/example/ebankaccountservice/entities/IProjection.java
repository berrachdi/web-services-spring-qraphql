package com.example.ebankaccountservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = Account.class, name = "p1")
public interface IProjection {
    String getId();
    String getCurrency();

}
