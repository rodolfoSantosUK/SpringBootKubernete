package com.dailycodeBuffer.orderSrvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductResponse {


   private String productName;
   private Long productId ;
   private Long quantity ;
   private Long price ;


}
