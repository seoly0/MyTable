package me.seoly.goodtable.service

import me.seoly.goodtable.core.model.entity.MemberEntity
import me.seoly.goodtable.core.model.entity.StoreConfigEntity
import me.seoly.goodtable.core.model.entity.StoreEntity
import me.seoly.goodtable.core.model.entity.StoreOpeningEntity
import me.seoly.goodtable.core.model.type.MemberType
import me.seoly.goodtable.exception.EntityNotExistException
import me.seoly.goodtable.exception.InvalidTimeException
import me.seoly.goodtable.payload.StorePayload
import me.seoly.goodtable.repository.MemberRepository
import me.seoly.goodtable.repository.StoreConfigRepository
import me.seoly.goodtable.repository.StoreOpeningRepository
import me.seoly.goodtable.repository.StoreRepository
import me.seoly.utils.ModelMapper
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

    fun createStore(create: StorePayload.Request.Create): StorePayload.Response.Default {

        val newStore = modelMapper.map(create, StoreEntity::class.java)
        storeRepository.save(newStore)

        val storeConfig = StoreConfigEntity(storeId = newStore.id)
        storeConfigRepository.save(storeConfig)

        val newMember = MemberEntity(
            accountId = create.ownerId,
            storeId = newStore.id,
            memberType = MemberType.OWNER,
        )
        memberRepository.save(newMember)

        return modelMapper.map(newStore, StorePayload.Response.Default::class.java)
    }

    fun serveStore(storeId: Long): StorePayload.Response.Default {

        val entity = storeRepository.findById(storeId)

        if (entity.isEmpty) {
            throw EntityNotExistException("존재하지 않는 지점입니다.")
        }

        return modelMapper.map(entity, StorePayload.Response.Default::class.java)
    }

    fun serveMyStoreList(accountId: Long): List<StorePayload.Response.Default> {
        val entityList = storeRepository.getAllByOwnerId(accountId)

        return entityList.map {
            modelMapper.map(it, StorePayload.Response.Default::class.java)
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

    fun getStoreIsOpened(storeId: Long): Boolean {
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
        create: List<StorePayload.Request.Opening>
    ): List<StorePayload.Response.Opening> {

        val list = storeOpeningRepository.findAllByStoreId(storeId)

        val result = create.map {

            if ( parseTime(it.openTime) > parseTime(it.closeTime) ) {
                throw InvalidTimeException("닫는 시간이 여는 시간을 앞설 수 없습니다.")
            }

            if ( parseTime(it.lastOrderTime) > parseTime(it.closeTime) ) {
                throw InvalidTimeException("닫는 시간이 마지막 주문 시간을 앞설 수 없습니다.")
            }

            val exist = list.filter { entity -> entity.dayOfWeek == it.dayOfWeek }

            if (exist.isEmpty()) {
                StoreOpeningEntity(
                    storeId = storeId,
                    dayOfWeek = it.dayOfWeek,
                    openTime = it.openTime,
                    closeTime = it.closeTime,
                    lastOrderTime = it.lastOrderTime,
                )
            }
            else {
                val entity = exist[0]
                entity.openTime = it.openTime
                entity.closeTime = it.closeTime
                entity.lastOrderTime = it.lastOrderTime
                entity
            }
        }

        storeOpeningRepository.saveAll(result)

        return result.map {
            modelMapper.map(it, StorePayload.Response.Opening::class.java)
        }
    }

    fun serveStoreOpening(storeId: Long): List<StorePayload.Response.Opening> {
        val list = storeOpeningRepository.findAllByStoreId(storeId)

        return list.map {
            modelMapper.map(it, StorePayload.Response.Opening::class.java)
        }
    }
}