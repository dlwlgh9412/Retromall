package com.retro.retromall.product.domain.repository

import com.retro.retromall.product.domain.Product
import com.retro.retromall.product.domain.repository.projection.ProductResponseProjection
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>, ProductRepositoryCustom {
    @EntityGraph(attributePaths = ["author", "category", "hashTags", "images"])
    fun findProjectedById(productId: Long): ProductResponseProjection?
}
