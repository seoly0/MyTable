package me.seoly.mytable.core.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(
    name = "menu_option_relation",
    indexes = []
)
class MenuOptionRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "store_id")
    var storeId: Long = 0

    @Column(name = "menu_id")
    var menuId: Long = 0

    @Column(name = "option_id")
    var optionId: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, updatable = false, insertable = false)
    lateinit var store: StoreEntity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false, updatable = false, insertable = false)
    lateinit var menu: MenuEntity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false, updatable = false, insertable = false)
    lateinit var option: OptionEntity
}