package me.seoly.goodtable.service

import me.seoly.goodtable.core.model.entity.MemberEntity
import me.seoly.goodtable.core.model.entity.StoreEntity
import me.seoly.goodtable.core.model.type.MemberType
import me.seoly.goodtable.payload.StorePayload
import me.seoly.goodtable.repository.MemberRepository
import me.seoly.goodtable.repository.StoreRepository
import me.seoly.utils.ModelMapper
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val storeRepository: StoreRepository,
    private val memberRepository: MemberRepository,
    private val modelMapper: ModelMapper,
    ) {

    fun createStore(create: StorePayload.Request.Create): StorePayload.Response.Default {

        val newStore = modelMapper.map(create, StoreEntity::class.java)
        storeRepository.save(newStore)

        val newMember = MemberEntity(
            accountId = create.ownerId,
            storeId = newStore.id,
            memberType = MemberType.OWNER
        )
        memberRepository.save(newMember)

        return modelMapper.map(newStore, StorePayload.Response.Default::class.java)
    }

    fun serveStore(storeId: Long): StoreEntity? {
        return storeRepository.getReferenceById(storeId)
    }

    fun serveMyStore(accountId: Long): List<StoreEntity> {
        return storeRepository.getAllByOwnerId(accountId)
    }
}