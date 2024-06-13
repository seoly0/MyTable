package me.seoly.mytable.core.model.entity

import jakarta.persistence.*
import me.seoly.mytable.core.Const
import me.seoly.mytable.core.model.type.MenuStateType
import me.seoly.mytable.core.model.type.OptionConstraint
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import me.seoly.spring.jpa.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(
    name = "option",
    indexes = []
)
@SQLDelete(sql = "UPDATE option SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@DynamicInsert
@DynamicUpdate
data class OptionEntity (

    @Column(name = "store_id")
    var storeId: Long,

    @Column(length = Const.COMMON_NAME_LENGTH)
    var name: String,

    @Column
    var required: Boolean,

    @Column
    @Enumerated(EnumType.STRING)
    var constraint: OptionConstraint,

    @Column
    var constraintNumber: Int?,

    @Column
    var default: Long?,
): BaseEntity() {

    @Column
    @Enumerated(EnumType.STRING)
    var state: MenuStateType = MenuStateType.ON_SALE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, updatable = false, insertable = false)
    lateinit var store: StoreEntity

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "option_id")
    var itemList: List<OptionItemEntity> = mutableListOf()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "option")
    var relations: List<MenuOptionRelation> = emptyList()
}