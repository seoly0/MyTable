package me.seoly.mytable.service

import me.seoly.mytable.core.model.entity.MemberEntity
import me.seoly.mytable.serializer.MemberSerializer
import me.seoly.mytable.repository.MemberRepository
import me.seoly.utils.ModelMapper
import org.springframework.stereotype.Service

@Service
class MemberService (
    private val memberRepository: MemberRepository,
    private val modelMapper: ModelMapper,
) {

    fun createMember(storeId: Long, create: MemberSerializer.Request.Create): MemberSerializer.Response.Default {

        val entity = MemberEntity(
            storeId = storeId,
            accountId = create.accountId,
            type = create.type,
        )

        memberRepository.save(entity)

        return modelMapper.map(entity, MemberSerializer.Response.Default::class.java)
    }

    fun serveStoreMemberList(storeId: Long): List<MemberSerializer.Response.Default> {

        return memberRepository.findAllByStoreId(storeId).map {
            modelMapper.map(it, MemberSerializer.Response.Default::class.java)
        }
    }
}