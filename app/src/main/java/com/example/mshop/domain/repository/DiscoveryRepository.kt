package com.example.mshop.domain.repository

import com.sudo248.base_android.core.DataState
import com.example.mshop.domain.entity.discovery.Category
import com.example.mshop.domain.entity.discovery.CommentList
import com.example.mshop.domain.entity.discovery.Offset
import com.example.mshop.domain.entity.discovery.Product
import com.example.mshop.domain.entity.discovery.ProductInfo
import com.example.mshop.domain.entity.discovery.ProductList
import com.example.mshop.domain.entity.discovery.Review
import com.example.mshop.domain.entity.discovery.ReviewList
import com.example.mshop.domain.entity.discovery.Supplier
import com.example.mshop.domain.entity.discovery.UpsertReview

interface DiscoveryRepository {
    suspend fun getCategories(): DataState<List<Category>, Exception>

    suspend fun getCategoryById(categoryId: String): DataState<Category, Exception>

    suspend fun getProductDetail(productId: String): DataState<Product, Exception>

    suspend fun getProductInfo(productId: String): DataState<ProductInfo, Exception>

    suspend fun searchProductByName(productName: String): DataState<ProductList, Exception>

    suspend fun getProductListByCategoryId(
        categoryId: String,
        offset: Offset
    ): DataState<ProductList, Exception>

    suspend fun getProductList(
        categoryId: String = "",
        name: String = "",
        offset: Offset
    ): DataState<ProductList, Exception>

    suspend fun getSupplierDetail(supplierId: String): DataState<Supplier, Exception>

    suspend fun getRecommendProductList(offset: Offset): DataState<ProductList, Exception>

    suspend fun getCommentsOfProduct(
        productId: String,
        offset: Offset
    ): DataState<CommentList, Exception>

    suspend fun getReviews(
        isReviewed: Boolean,
        offset: Offset
    ): DataState<ReviewList, Exception>

    suspend fun upsertReview(upsertReview: UpsertReview): DataState<Review, Exception>

    suspend fun deleteComment(commentId: String): DataState<Unit, Exception>
}