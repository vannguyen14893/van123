package com.example.demo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.Color;
import com.example.demo.entity.Product;
import com.example.demo.entity.Size;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.repository.customer.ProductRepositoryCutomer;
import com.example.demo.utils.Contans;
import com.example.demo.utils.FilterKeyword;

@Service
@Transactional
public class ProductServiceImpl implements ProductRepositoryCutomer {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SupplierRepository supplierRepository;

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
		Join<Product, Size> sizes = root.join("sizes");
		Join<Size, Color> colors = sizes.join("colors");
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!StringUtils.isNotBlank(filterKeyword.getKeyword())) {
			query.orderBy(cb.asc(root.get("id")));
		} else {
			query.orderBy(filterKeyword.getSort() ? (cb.asc(root.get(filterKeyword.getKeyword())))
					: (cb.desc(root.get(filterKeyword.getKeyword()))));
		}
		if (StringUtils.isNotBlank(filterKeyword.getFileName())) {
			predicates.add(cb.like(root.get("name"), "%" + filterKeyword.getFileName() + "%"));
		}
		if (StringUtils.isNotBlank(filterKeyword.getName())) {
			predicates.add(cb.equal(root.get("name"), filterKeyword.getName()));

		}
		if (StringUtils.isNotBlank(filterKeyword.getCreateDate())) {
			String dateFormat = filterKeyword.getCreateDate();
			try {
				Date date = new SimpleDateFormat(Contans.FORMAT_DATE).parse(dateFormat);
				predicates.add(cb.equal(root.get("createDate"), date));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if (filterKeyword.getPrice() != null) {
			predicates.add(cb.equal(root.get("price"), filterKeyword.getPrice()));
		}
		if (StringUtils.isNotBlank(filterKeyword.getDescription())) {
			predicates.add(cb.equal(root.get("description"), filterKeyword.getDescription()));
		}
		if (filterKeyword.getNameColor() != null) {
			predicates.add(cb.equal(colors.get("nameColor"), filterKeyword.getNameColor()));
		}
		if (filterKeyword.getSizeName() != null) {
			predicates.add(cb.equal(sizes.get("sizeName"), filterKeyword.getSizeName()));
		}

		query.where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Product> typedQuery = entityManager.createQuery(query.select(root))
				.setMaxResults(filterKeyword.getPageSize())

				.setFirstResult((filterKeyword.getPage() - 1) * filterKeyword.getPageSize());

		return typedQuery.getResultList();

	}

	public Long count() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Product.class)));
		return entityManager.createQuery(cq).getSingleResult();
	}

	public void addProduct(Product product) {

		List<Size> sizes = new ArrayList<Size>();
		for (Size size : product.getSizes()) {
			Size size2 = new Size();
			size2.setSizeName(size.getSizeName());
			size2.setProduct(product);
			List<Color> colors = new ArrayList<Color>();
			for (Color color : size.getColors()) {
				Color color2 = new Color();
				color2.setNameColor(color.getNameColor());
				color2.setTotal(color.getTotal());
				color2.setSize(size2);
				colors.add(color2);
				List<Integer> qty = colors.stream().map(x -> x.getTotal()).collect(Collectors.toList());
				Integer sum1 = qty.stream().mapToInt(Integer::intValue).sum();
				size2.setColors(colors);
				size2.setTotal(sum1);
			}
			sizes.add(size2);
			List<Integer> collect = sizes.stream().map(x -> x.getTotal()).collect(Collectors.toList());
			Integer sum = collect.stream().mapToInt(Integer::intValue).sum();
			product.setTotal(sum);
		}
		product.setSizes(sizes);
		product.setCreateDate(new Date());

		product.setCategory(categoryRepository.findById(product.getCategory().getId()).get());
		product.setBrand(brandRepository.findById(product.getBrand().getId()).get());
		product.setSupplier(supplierRepository.findById(product.getSupplier().getId()).get());

		entityManager.persist(product);

	}

	public boolean checkProductName(String name) {
		Product product = productRepository.findByName(name);
		if (product != null) {
			return true;
		}
		return false;
	}

	public boolean checkDate(Date startDate, Date endDate) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(Contans.FORMAT_DATE);
		String myDate = sdf.format(date);
		String stDate = sdf.format(startDate);
		String edDate = sdf.format(endDate);
		if (startDate != null || endDate != null) {
			if (stDate.compareTo(myDate) >= 0) {
				if (stDate.compareTo(edDate) >= 0) {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	public void updateProduct(Product product) {
		Product product2 = productRepository.findById(product.getId()).get();
		if (product2 != null) {
			product2.setId(product.getId());
			product2.setName(product.getName());
			product2.setDescription(product.getDescription());
			product2.setPrice(product.getPrice());
			product2.setBrand(brandRepository.findById(product.getBrand().getId()).get());
			product2.setCategory(categoryRepository.findById(product.getCategory().getId()).get());
			product2.setSupplier(supplierRepository.findById(product.getSupplier().getId()).get());
			product2.getSizes().clear();
			List<Size> sizes = new ArrayList<Size>();
			for (Size size : product.getSizes()) {
				Size size2 = new Size();
				size2.setId(size.getId());
				size2.setSizeName(size.getSizeName());
				size2.setProduct(product2);
				for (Color color : size.getColors()) {
					Color color2 = new Color();
					color2.setId(color.getId());
					color2.setNameColor(color.getNameColor());
					color2.setTotal(color.getTotal());
					color2.setSize(size);
					size2.setColors(Arrays.asList(color2));
					List<Color> colors = Arrays.asList(color2);
					List<Integer> qty = colors.stream().map(x -> x.getTotal()).collect(Collectors.toList());
					Integer sum1 = qty.stream().mapToInt(Integer::intValue).sum();
					size2.setTotal(sum1);
				}
				sizes.add(size2);
			}
			List<Integer> collect = sizes.stream().map(x -> x.getTotal()).collect(Collectors.toList());
			Integer sum = collect.stream().mapToInt(Integer::intValue).sum();
			product2.setTotal(sum);
			product2.getSizes().addAll(sizes);
			entityManager.merge(product2);
		}
	}

	public void deleteProduct(Integer id) {
		Product product = productRepository.findById(id).get();
		if (product != null) {
			productRepository.deleteById(id);

		}

	}
}
