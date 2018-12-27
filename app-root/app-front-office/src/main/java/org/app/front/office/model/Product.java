package org.app.front.office.model;

import org.app.front.office.type.ProductType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private Long id;
	private String name;
	private ProductType type;
	private Double prix;

}
