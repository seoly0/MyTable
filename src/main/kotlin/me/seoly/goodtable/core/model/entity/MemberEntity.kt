package me.seoly.goodtable.core.model.entity

import jakarta.persistence.*
import me.seoly.goodtable.core.model.type.MemberType
import me.seoly.spring.jpa.BaseEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(
    name = "member",
    indexes = []
)
@SQLDelete(sql = "UPDATE member SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
class MemberEntity(
    @Column(name = "store_id")
    val storeId: Long,

    @Column(name = "account_id")
    val accountId: Long,

    @Column
    @Enumerated(EnumType.STRING)
    val memberType: MemberType,

    ): BaseEntity() {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false, insertable = false, updatable = false)
    lateinit var store: StoreEntity

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false, insertable = false, updatable = false)
    lateinit var account: AccountEntity
}