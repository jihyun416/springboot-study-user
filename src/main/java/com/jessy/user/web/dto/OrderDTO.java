package com.jessy.user.web.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    String orderNumber;
    String customerFirstName;
    String customerLastName;
    String billingCity;
    String billingStreet;
}
