package com.iftm.course.service;

import com.iftm.course.dto.CategoryDTO;
import com.iftm.course.dto.ProductCategoryDTO;
import com.iftm.course.dto.ProductDTO;
import com.iftm.course.entities.Category;
import com.iftm.course.entities.Product;
import com.iftm.course.repository.CategoryRepository;
import com.iftm.course.repository.ProductRepository;
import com.iftm.course.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements Serializable {

    private static final long serialVersionUID = -613315401025372110L;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> findAll(){
        List<Product> list = productRepository.findAll();
        return list.stream().map( e -> new ProductDTO(e) ).collect( Collectors.toList());
    }

    public ProductDTO findById(Long id){
        Optional<Product> entity = productRepository.findById( id );
        return  new ProductDTO(entity.get());
    }

    @Transactional
    public ProductDTO insert(ProductCategoryDTO dto){
        Product entity = dto.toEntity();
        setProductCategories(entity,dto.getCategories());
        entity = productRepository.save( entity );
        return new ProductDTO(entity) ;
    }

    @Transactional
    public ProductDTO update(Long id, ProductCategoryDTO dto){
        try {
            Product entity = productRepository.getOne( id );
            updateData( entity , dto );
            entity = productRepository.save( entity );
            return new ProductDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException( id );
        }

    }

    private void updateData(Product entity, ProductCategoryDTO dto) {
        entity.setName( dto.getName() );
        entity.setDescription( dto.getDescription() );
        entity.setPrice( dto.getPrice() );
        entity.setImgUrl( dto.getImgUrl() );
        if (dto.getCategories() != null && dto.getCategories().size() > 0){
            setProductCategories( entity, dto.getCategories() );
        }
    }

    private void setProductCategories(Product entity, List<CategoryDTO> categoryDTOS) {
        entity.getCategories().clear();
        for (CategoryDTO categoryDTO: categoryDTOS){
            Category category =  categoryRepository.getOne( categoryDTO.getId() );
            entity.getCategories().add( category );
        }
    }

}