package me.seoly.mytable.core.model.entity

import jakarta.persistence.*
import me.seoly.mytable.core.model.type.AccountStateType
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import me.seoly.spring.jpa.BaseEntity
import me.seoly.mytable.core.Const
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(
    name = "account",
    indexes = []
)
@SQLDelete(sql = "UPDATE account SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@DynamicInsert
@DynamicUpdate
data class AccountEntity(
    @Column(length = Const.EMAIL_LENGTH)
    var email: String,

    @Column(length = Const.HUMAN_NAME_LENGTH)
    var name: String,

    @Column(length = Const.PASSWORD_LENGTH)
    var password: String,

    @Column(length = Const.TEL_LENGTH)
    var phone: String,
): BaseEntity() {

    @Column
    @Enumerated(EnumType.STRING)
    var state: AccountStateType = AccountStateType.ACTIVE
}