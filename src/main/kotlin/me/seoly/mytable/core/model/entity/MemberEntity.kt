package me.seoly.mytable.core.model.entity

import jakarta.persistence.*
import me.seoly.mytable.core.model.type.MemberType
import me.seoly.spring.jpa.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(
    name = "store_member",
    indexes = []
)
@SQLDelete(sql = "UPDATE store_member SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@DynamicInsert
@DynamicUpdate
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