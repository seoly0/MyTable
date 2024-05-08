package me.seoly.goodtable.core.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import me.seoly.goodtable.core.Const
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import me.seoly.spring.jpa.BaseEntity


//, me.seoly.goodtable.core.model.schema.Table

@Entity
@Table(
    name = "table",
    indexes = []
)
@SQLDelete(sql = "UPDATE table SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
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
    lateinit var description: String

    @Column
    lateinit var memo: String

//    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false, insertable = false, updatable = false)
    lateinit var store: StoreEntity
}