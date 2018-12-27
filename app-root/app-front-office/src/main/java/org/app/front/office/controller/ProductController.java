package org.app.front.office.controller;

import org.app.front.office.model.Product;
import org.app.front.office.type.ProductType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

	@GetMapping("/one-produit")
	Product getProduct() {
		return new Product(Long.valueOf("1"), "artel musculation", ProductType.SPORT, Double.valueOf("20"));
	}

}
