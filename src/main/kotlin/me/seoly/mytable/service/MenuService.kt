package me.seoly.mytable.service


import me.seoly.mytable.core.model.entity.MenuCategoryEntity
import me.seoly.mytable.core.model.entity.MenuEntity
import me.seoly.mytable.core.model.entity.OptionEntity
import me.seoly.mytable.core.model.entity.OptionItemEntity
import me.seoly.mytable.core.model.type.MenuStateType
import me.seoly.mytable.exception.EntityNotExistException
import me.seoly.mytable.serializer.CategorySerializer
import me.seoly.mytable.serializer.MenuSerializer
import me.seoly.mytable.serializer.OptionSerializer
import me.seoly.mytable.repository.*
import me.seoly.utils.ModelMapper
import org.springframework.stereotype.Service

@Service
class MenuService (
    val categoryRepository: MenuCategoryRepository,
    val menuRepository: MenuRepository,
    val optionRepository: OptionRepository,
    val menuOptionRelationRepository: MenuOptionRelationRepository,
    val itemRepository: OptionItemRepository,
    val modelMapper: ModelMapper,
) {

    fun createMenuCategory(
        storeId: Long,
        create: CategorySerializer.Request.Create,
    ): CategorySerializer.Response.Default {

        val entity = modelMapper.map(create, MenuCategoryEntity::class.java)
        entity.storeId = storeId

        categoryRepository.save(entity)

        return modelMapper.map(entity, CategorySerializer.Response.Default::class.java)
    }

    fun serveStoreMenuCategoryList (
        storeId: Long,
    ): List<CategorySerializer.Response.Default> {
        return categoryRepository.findAllByStoreId(storeId).map {
            modelMapper.map(it, CategorySerializer.Response.Default::class.java)
        }
    }

    fun createMenu(storeId: Long, create: MenuSerializer.Request.Create): MenuSerializer.Response.Default {

        val entity = modelMapper.map(create, MenuEntity::class.java)
        entity.storeId = storeId
        entity.state = MenuStateType.ON_SALE

        menuRepository.save(entity)

        return modelMapper.map(entity, MenuSerializer.Response.Default::class.java)
    }

    fun serveMenuList(storeId: Long): List<MenuSerializer.Response.Default> {

        return menuRepository.findAllByStoreId(storeId).map {
            modelMapper.map(it, MenuSerializer.Response.Default::class.java)
        }
    }

    fun serveMenuDetail(storeId: Long, menuId: Long): MenuSerializer.Response.Default {

        val entity = menuRepository.findByIdAndStoreId(menuId, storeId) ?: throw EntityNotExistException()

        return modelMapper.map(entity, MenuSerializer.Response.Default::class.java)
    }

    fun serveMenuListByCategory(storeId: Long, categoryId: Long): List<MenuSerializer.Response.Default> {

        return menuRepository.findAllByStoreIdAndCategoryId(storeId, categoryId).map {
            modelMapper.map(it, MenuSerializer.Response.Default::class.java)
        }
    }

    fun createOption(storeId: Long, create: OptionSerializer.Request.Create): OptionSerializer.Response.WithItem {

        val option = OptionEntity(
            storeId = storeId,
            name = create.name,
            default = create.default,
            required = create.required,
            constraint = create.constraint,
            constraintNumber = create.constraintNumber,
        )

        optionRepository.save(option)

        option.itemList = create.itemList.map {
            OptionItemEntity(
                optionId = option.id,
                name = it.name,
                price = it.price,
            )
        }

        itemRepository.saveAll(option.itemList)

        return modelMapper.map(option, OptionSerializer.Response.WithItem::class.java)
    }

    fun serveOption(storeId: Long, optionId: Long): OptionSerializer.Response.WithItem {
        val option = optionRepository.getReferenceById(optionId)

        return modelMapper.map(option, OptionSerializer.Response.WithItem::class.java)
    }

    fun createOptionItem(storeId: Long, menuId: Long, optionId: Long) {

    }

    fun serveMenuOption(storeId: Long, menuId: Long): List<OptionSerializer.Response.WithItem> {

        val optionList = menuOptionRelationRepository.findAllByStoreIdAndMenuId(storeId, menuId)

        return optionList.map { modelMapper.map(it.option, OptionSerializer.Response.WithItem::class.java) }
    }
}