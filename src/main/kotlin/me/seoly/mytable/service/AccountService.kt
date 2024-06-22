package me.seoly.mytable.service

import me.seoly.mytable.core.model.entity.AccountEntity
import me.seoly.mytable.core.model.type.AccountStateType
import me.seoly.mytable.exception.EntityDuplicatedException
import me.seoly.mytable.exception.EntityNotExistException
import me.seoly.mytable.serializer.AccountSerializer
import me.seoly.mytable.repository.AccountRepository
import me.seoly.spring.utils.ModelMapper
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val modelMapper: ModelMapper,
    private val passwordEncoder: PasswordEncoder,
) {

    fun createAccount(create: AccountSerializer.Request.Create): AccountSerializer.Response.Default {

        val exist = accountRepository.findByEmail(create.email)

        if (exist != null) {
            throw EntityDuplicatedException("이미 가입된 이메일입니다.")
        }

        create.password = passwordEncoder.encode(create.password)

        val entity = modelMapper.map(create, AccountEntity::class.java)
        entity.state = AccountStateType.ACTIVE

        accountRepository.save(entity)

        return modelMapper.map(entity, AccountSerializer.Response.Default::class.java)
    }

    fun serveAccount(accountId: Long?): AccountSerializer.Response.Default? {

        var entity: AccountEntity? = null

        entity = try {
            if (accountId != null) {
                accountRepository.getReferenceById(accountId)
            } else {
                accountRepository.getReferenceById(1)
            }
        } catch (exception: JpaObjectRetrievalFailureException) {
            throw EntityNotExistException("존재하지 않는 계정입니다.")
        }

        return modelMapper.map(entity, AccountSerializer.Response.Default::class.java)
    }
}