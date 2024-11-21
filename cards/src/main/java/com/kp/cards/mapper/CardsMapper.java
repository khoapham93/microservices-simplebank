package com.kp.cards.mapper;

import com.kp.cards.dto.CardsDto;
import com.kp.cards.entity.Cards;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CardsMapper {

    CardsDto toDto(Cards cards);

    Cards toEntity(CardsDto cardsDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "cardId", ignore = true)
    Cards updateEntity(CardsDto cardsDto, @MappingTarget Cards cards);

}
