package com.ritice.wallet_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Currency {
    @Id
    private String code;
    private  String name;
    private  Double salePrice;
    private  Double purchasePrice;

}
