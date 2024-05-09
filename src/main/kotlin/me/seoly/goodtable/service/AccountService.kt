package me.seoly.goodtable.service

import me.seoly.goodtable.core.model.entity.AccountEntity
import me.seoly.goodtable.core.model.type.AccountStateType
import me.seoly.goodtable.payload.AccountPayload
import me.seoly.goodtable.repository.AccountRepository
import me.seoly.utils.ModelMapper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val modelMapper: ModelMapper,
    private val passwordEncoder: PasswordEncoder,
) {

    fun createAccount(create: AccountPayload.Request.Create): AccountPayload.Response.Default {

        create.password = passwordEncoder.encode(create.password)

        val entity = modelMapper.map(create, AccountEntity::class.java)
        entity.state = AccountStateType.ACTIVE

        accountRepository.save(entity)

        return modelMapper.map(entity, AccountPayload.Response.Default::class.java)
    }

    fun serveAccount(accountId: Long?): AccountPayload.Response.Default? {

        var entity: AccountEntity? = null

        if (accountId != null) {
            entity = accountRepository.getReferenceById(accountId)
        }
        else {
            entity = accountRepository.getReferenceById(1)
        }

        return modelMapper.map(entity, AccountPayload.Response.Default::class.java)
    }
}