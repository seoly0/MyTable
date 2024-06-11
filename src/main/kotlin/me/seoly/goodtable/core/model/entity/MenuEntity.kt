package me.seoly.goodtable.core.model.entity

import jakarta.persistence.*
import me.seoly.goodtable.core.Const
import me.seoly.goodtable.core.model.type.MenuStateType
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import me.seoly.spring.jpa.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(
    name = "menu",
    indexes = []
)
@SQLDelete(sql = "UPDATE menu SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@DynamicInsert
@DynamicUpdate
data class MenuEntity (
    @Column(name = "store_id")
    var storeId: Long,

    @Column(name = "category_id")
    var categoryId: Long?,

    @Column(length = Const.COMMON_NAME_LENGTH)
    var name: String,

    @Column
    var price: Int,
): BaseEntity() {

    @Column
    @Enumerated(EnumType.STRING)
    var state: MenuStateType = MenuStateType.ON_SALE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, updatable = false, insertable = false)
    lateinit var store: StoreEntity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, updatable = false, insertable = false)
    lateinit var category: MenuCategoryEntity

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    lateinit var relations: List<MenuOptionRelation>
}