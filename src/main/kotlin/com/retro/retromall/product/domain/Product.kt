package com.retro.retromall.product.domain

import com.retro.retromall.category.domain.Category
import com.retro.retromall.hashtag.domain.HashTag
import com.retro.retromall.member.domain.Member
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_product")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "member_id")
    val author: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_name", referencedColumnName = "category_name")
    var category: Category?,

    @Column(name = "content", nullable = true)
    var content: String?,

    @Column(name = "amount", nullable = false)
    var amount: Int,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "ProductHashTag",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "hashtag_id")]
    )
    var hashtags: MutableSet<HashTag> = mutableSetOf(),

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var images: MutableSet<ProductImage> = mutableSetOf(),

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "modified_at")
    var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    constructor(
        author: Member,
        category: Category,
        content: String?,
        amount: Int,
    ) : this(null, author, category, content, amount)

    fun addHashTags(hashtags: List<HashTag>) {
        this.hashtags.addAll(hashtags)
        hashtags.forEach { it.products.add(this) }
    }

    fun addImages(images: List<ProductImage>) {
        this.images.addAll(images)
        images.forEach { it.product = this }
    }

    fun modifyProduct(content: String, amount: Int, category: Category) {
        this.content = content
        this.amount = amount
        this.category = category
        this.modifiedAt = LocalDateTime.now()
    }
}