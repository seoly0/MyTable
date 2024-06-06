package me.seoly.goodtable.service

import me.seoly.goodtable.core.model.entity.MemberEntity
import me.seoly.goodtable.payload.MemberPayload
import me.seoly.goodtable.repository.MemberRepository
import me.seoly.utils.ModelMapper
import org.springframework.stereotype.Service

@Service
class MemberService (
    private val memberRepository: MemberRepository,
    private val modelMapper: ModelMapper,
) {

    fun createMember(storeId: Long, create: MemberPayload.Request.Create): MemberPayload.Response.Default {

        val entity = MemberEntity(
            storeId = storeId,
            accountId = create.accountId,
            memberType = create.memberType,
        )

        memberRepository.save(entity)

        return modelMapper.map(entity, MemberPayload.Response.Default::class.java)
    }

    fun serveStoreMemberList(storeId: Long): List<MemberPayload.Response.Default> {

        return memberRepository.findAllByStoreId(storeId).map {
            modelMapper.map(it, MemberPayload.Response.Default::class.java)
        }
    }
}