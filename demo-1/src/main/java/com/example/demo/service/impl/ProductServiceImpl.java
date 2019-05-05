package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Color;
import com.example.demo.entity.Product;
import com.example.demo.entity.Size;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.customer.ProductRepositoryCutomer;
import com.example.demo.utils.FilterKeyword;

@Service
@Transactional
public class ProductServiceImpl implements ProductRepositoryCutomer {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ProductRepository productRepository;

//	@Override
//	public List<Product> getAll() {
////		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
////		CriteriaQuery<Product> query = criteria.createQuery(Product.class);
////		Root<Product> root = query.from(Product.class);
////		List<Predicate> predicates = new ArrayList<Predicate>();
////		query.where(predicates.toArray(new Predicate[] {}));
////		TypedQuery<Product> typedQuery = entityManager.createQuery(query.select(root));
////		return typedQuery.getResultList();
//		return productRepository.findAll(where);
//	}
	@Override
	public List<Product> findProductsByName(FilterKeyword filterKeyword) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = cb.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
//		Join<Product, Color> colors = root.join("colors");
//		Join<Product, Size> sizes = root.join("sizes");
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (filterKeyword.getKeyword() == null) {
			query.orderBy(cb.asc(root.get("id")));

		} else {
			query.orderBy(filterKeyword.getSort() ? (cb.asc(root.get(filterKeyword.getKeyword())))
					: (cb.desc(root.get(filterKeyword.getKeyword()))));
		}
		if (filterKeyword.getFileName() != null) {
			predicates.add(cb.like(root.get("name"), "%" + filterKeyword.getFileName() + "%"));
		}
		if (filterKeyword.getName() != null) {
			predicates.add(cb.equal(root.get("name"), filterKeyword.getName()));
		}
		if (filterKeyword.getPrice() != null) {
			predicates.add(cb.equal(root.get("price"), filterKeyword.getPrice()));
		}
		if (filterKeyword.getDescription() != null) {
			predicates.add(cb.equal(root.get("description"), filterKeyword.getDescription()));
		}
//		if (filterKeyword.getNameColor() != null) {
//			predicates.add(cb.equal(colors.get("nameColor"), filterKeyword.getNameColor()));
//		}

		query.where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Product> typedQuery = entityManager.createQuery(query.select(root))
				.setMaxResults(filterKeyword.getPageSize())
				.setFirstResult((filterKeyword.getPage() - 1) * filterKeyword.getPageSize());

		return typedQuery.getResultList();

	}

	public void addProduct(Product product) {
		List<Size> sizes = new ArrayList<Size>();
		for (Size size : product.getSizes()) {
			Size size2 = new Size();
			size2.setSizeName(size.getSizeName());
			size2.setTotal(size.getTotal());
			size2.setProduct(product);
			sizes.add(size2);
		}
		List<Color> colors = new ArrayList<Color>();
		for (Color color : product.getColors()) {
			Color color2 = new Color();
			color2.setNameColor(color.getNameColor());
			color2.setTotal(color.getTotal());
			color2.setProduct(product);
			colors.add(color2);
		}
		product.setColors(colors);
		product.setSizes(sizes);
		entityManager.persist(product);

	}

	public void updateProduct(Product product) {
		Product product2 = productRepository.findById(product.getId()).get();
		if (product2 != null) {
			product2.setId(product.getId());
			product2.setName(product.getName());
			product2.setDescription(product.getDescription());
			product2.setPrice(product.getPrice());
			product2.getColors().clear();
			product2.getSizes().clear();
			List<Size> sizes = new ArrayList<Size>();
			for (Size size : product.getSizes()) {
				Size size2 = new Size();
				size2.setSizeName(size.getSizeName());
				size2.setTotal(size.getTotal());
				size2.setProduct(product2);
				sizes.add(size2);
			}
			List<Color> colors = new ArrayList<Color>();
			for (Color color : product.getColors()) {
				Color color2 = new Color();
				color2.setNameColor(color.getNameColor());
				color2.setTotal(color.getTotal());
				color2.setProduct(product2);
				colors.add(color2);
			}
			product2.getColors().addAll(colors);
			product2.getSizes().addAll(sizes);
			entityManager.merge(product2);
		}
	}
}
