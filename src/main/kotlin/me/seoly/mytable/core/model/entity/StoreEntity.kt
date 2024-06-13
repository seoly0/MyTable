package me.seoly.mytable.core.model.entity

import jakarta.persistence.*
import me.seoly.mytable.core.Const
import me.seoly.spring.jpa.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(
    name = "store",
    indexes = []
)
@SQLDelete(sql = "UPDATE store SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@DynamicInsert
@DynamicUpdate
data class StoreEntity (
    @Column(length = Const.COMMON_NAME_LENGTH)
    var name: String,

    @Column
    var address: String,

    @Column(name = "owner_id")
    var ownerId: Long,

    @Column(length = Const.BIZ_NUMBER_LENGTH)
    var bizNumber: String,

    @Column(length = Const.TEL_LENGTH)
    var tel: String,
): BaseEntity() {
    @Column
    var introduction: String = ""

    @Column
    var blueprint: String = ""

    @Column
    var rating: String = ""

//    @Column
//    var policy: String = ""

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false, insertable = false, updatable = false)
    var owner: AccountEntity? = null
}
