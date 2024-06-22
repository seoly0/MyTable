package me.seoly.mytable.service

import me.seoly.mytable.core.model.entity.MemberEntity
import me.seoly.mytable.core.model.entity.StoreConfigEntity
import me.seoly.mytable.core.model.entity.StoreEntity
import me.seoly.mytable.core.model.entity.StoreOpeningEntity
import me.seoly.mytable.core.model.type.MemberType
import me.seoly.mytable.exception.EntityNotExistException
import me.seoly.mytable.exception.InvalidTimeException
import me.seoly.mytable.serializer.StoreSerializer
import me.seoly.mytable.repository.MemberRepository
import me.seoly.mytable.repository.StoreConfigRepository
import me.seoly.mytable.repository.StoreOpeningRepository
import me.seoly.mytable.repository.StoreRepository
import me.seoly.utils.ModelMapper
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class StoreService(
    private val storeRepository: StoreRepository,
    private val storeConfigRepository: StoreConfigRepository,
    private val storeOpeningRepository: StoreOpeningRepository,
    private val memberRepository: MemberRepository,
    private val modelMapper: ModelMapper,
    ) {

    fun createStore(create: StoreSerializer.Request.Create): StoreSerializer.Response.Default {

        val newStore = modelMapper.map(create, StoreEntity::class.java)
        storeRepository.save(newStore)

        val storeConfig = StoreConfigEntity(storeId = newStore.id)
        storeConfigRepository.save(storeConfig)

        val newMember = MemberEntity(
            accountId = create.ownerId,
            storeId = newStore.id,
            type = MemberType.OWNER,
        )
        memberRepository.save(newMember)

        return modelMapper.map(newStore, StoreSerializer.Response.Default::class.java)
    }

    fun serveStore(storeId: Long): StoreSerializer.Response.Default {

        val entity = storeRepository.findById(storeId)

        if (entity.isEmpty) {
            throw EntityNotExistException("존재하지 않는 지점입니다.")
        }

        return modelMapper.map(entity, StoreSerializer.Response.Default::class.java)
    }

    fun serveMyStoreList(accountId: Long): List<StoreSerializer.Response.Default> {
        val entityList = storeRepository.getAllByOwnerId(accountId)

        return entityList.map {
            modelMapper.map(it, StoreSerializer.Response.Default::class.java)
        }
    }

    fun searchStore(name: String, pageable: Pageable): List<StoreSerializer.Response.Default> {

        return storeRepository.getAllByNameContains(name, pageable).map {
            modelMapper.map(it, StoreSerializer.Response.Default::class.java)
        }
    }

    fun parseTime(t: String): Int {
        val token = t.split(":")

        try {
            val hour: Int = token[0].toInt()
            val min: Int = token[1].toInt()

            if (hour < 0 || hour > 47 || min < 0 || min > 59) {
                throw InvalidTimeException()
            }

            return hour * 60 + min
        }
        catch (e: Exception) {
            throw InvalidTimeException()
        }

    }

    fun getStoreOpened(storeId: Long): Boolean {
        val entity = storeRepository.findById(storeId)

        if (entity.isEmpty) {
            throw EntityNotExistException()
        }

        return entity.get().opened
    }

    fun setStoreOpened(storeId: Long, opened: Boolean) {
        val optional = storeRepository.findById(storeId)

        if (optional.isEmpty) {
            throw EntityNotExistException()
        }

        val entity = optional.get()
        entity.opened = opened

        storeRepository.save(entity)
    }

    fun getStoreIsOpening(storeId: Long): Boolean {
        val now = LocalDateTime.now()

        val list = storeOpeningRepository.findAllByStoreId(storeId)

        val filtered = list.filter { entity -> entity.dayOfWeek == now.dayOfWeek }

        // TODO 24시 이후도 판단 필요

        if (filtered.isEmpty()) {
            return false
        }
        else {
            val nowTime = now.hour * 60 + now.minute
            return parseTime(filtered[0].openTime) < nowTime && parseTime(filtered[0].closeTime) > nowTime
        }
    }

    fun setStoreOpening(
        storeId: Long,
        create: List<StoreSerializer.Request.Opening>
    ): List<StoreSerializer.Response.Opening> {

        val entityList = storeOpeningRepository.findAllByStoreId(storeId)

        val result = create.map {

            if ( parseTime(it.openTime) > parseTime(it.closeTime) ) {
                throw InvalidTimeException("닫는 시간이 여는 시간을 앞설 수 없습니다.")
            }

            if ( parseTime(it.lastOrderTime) > parseTime(it.closeTime) ) {
                throw InvalidTimeException("닫는 시간이 마지막 주문 시간을 앞설 수 없습니다.")
            }

            val filtered = entityList.filter { entity -> entity.dayOfWeek == it.dayOfWeek }

            if (filtered.isEmpty()) {
                StoreOpeningEntity(
                    storeId = storeId,
                    dayOfWeek = it.dayOfWeek,
                    openTime = it.openTime,
                    closeTime = it.closeTime,
                    lastOrderTime = it.lastOrderTime,
                )
            }
            else {
                val entity = filtered[0]
                entity.openTime = it.openTime
                entity.closeTime = it.closeTime
                entity.lastOrderTime = it.lastOrderTime
                entity
            }
        }

        storeOpeningRepository.saveAll(result)

        return result.map {
            modelMapper.map(it, StoreSerializer.Response.Opening::class.java)
        }
    }

    fun serveStoreOpening(storeId: Long): List<StoreSerializer.Response.Opening> {
        val list = storeOpeningRepository.findAllByStoreId(storeId)

        return list.map {
            modelMapper.map(it, StoreSerializer.Response.Opening::class.java)
        }
    }
}