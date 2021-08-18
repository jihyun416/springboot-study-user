package com.jessy.user.domain;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    String orderNumber;
    Customer customer;
    Address billing;
}
