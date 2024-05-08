package me.seoly.goodtable.core.model.entity

import jakarta.persistence.*
import me.seoly.goodtable.core.Const
import me.seoly.goodtable.core.model.type.MenuStateType
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import me.seoly.spring.jpa.BaseEntity

@Entity
@Table(
    name = "option_item",
    indexes = []
)
@SQLDelete(sql = "UPDATE option_item SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
data class OptionItemEntity (
    @Column(name = "option_id")
    var optionId: Long,

    @Column(length = Const.COMMON_NAME_LENGTH)
    var name: String,

    @Column
    var price: Int,
): BaseEntity() {

    @Column
    @Enumerated(EnumType.STRING)
    var state: MenuStateType = MenuStateType.ON_SALE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false, updatable = false, insertable = false)
    lateinit var option: OptionEntity
}