package me.seoly.goodtable.core.model.entity

import jakarta.persistence.*
import me.seoly.goodtable.core.Const
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import me.seoly.spring.jpa.BaseEntity
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate


@Entity
@Table(
    name = "store_table",
    indexes = []
)
@SQLDelete(sql = "UPDATE store_table SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@DynamicInsert
@DynamicUpdate
data class TableEntity (
    @Column(name = "store_id")
    var storeId: Long,

    @Column(length = Const.COMMON_NAME_LENGTH)
    var name: String,

    @Column
    var capacity: Int,
): BaseEntity() {
    @Column
    var width: Int = 0

    @Column
    var height: Int = 0

    @Column
    var positionX: Int = 0

    @Column
    var positionY: Int = 0

    @Column
    @ColumnDefault("''")
    var description: String = ""

    @Column
    @ColumnDefault("''")
    var memo: String = ""

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false, insertable = false, updatable = false)
    lateinit var store: StoreEntity
}