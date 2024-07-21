package studio.zealous.smartportfolio.util.converter;

import org.springframework.stereotype.Component;
import studio.zealous.smartportfolio.dto.holding.HoldingDto;
import studio.zealous.smartportfolio.entity.holding.Holding;

import java.util.ArrayList;
import java.util.List;

@Component
public class HoldingConverter {
    public HoldingDto toDto(Holding entity) {
        HoldingDto dto = new HoldingDto();
        dto.setT1Quantity(entity.getT1Quantity());
        dto.setAuthorisedQuantity(entity.getAuthorisedQuantity());
        dto.setProduct(entity.getProduct());
        dto.setQuantity(entity.getQuantity());
        dto.setLtp(entity.getLtp());
        dto.setHaircut(entity.getHaircut());
        dto.setProfitAndLoss(entity.getProfitAndLoss());
        dto.setCollateralType(entity.getCollateralType());
        dto.setAveragePrice(entity.getAveragePrice());
        dto.setTradingSymbol(entity.getTradingSymbol());
        dto.setPnlPercentage(entity.getPnlPercentage());
        dto.setExchange(entity.getExchange());
        dto.setClose(entity.getClose());
        dto.setIsin(entity.getIsin());
        dto.setRealisedQuantity(entity.getRealisedQuantity());
        dto.setSymbolToken(entity.getSymbolToken());
        dto.setCollateralQuantity(entity.getCollateralQuantity());
        return dto;
    }

    public List<HoldingDto> toListOfDto(List<Holding> entities) {
        List<HoldingDto> dtos = new ArrayList<>();
        for (Holding entity : entities) {
            HoldingDto dto = new HoldingDto();
            dto.setT1Quantity(entity.getT1Quantity());
            dto.setAuthorisedQuantity(entity.getAuthorisedQuantity());
            dto.setProduct(entity.getProduct());
            dto.setQuantity(entity.getQuantity());
            dto.setLtp(entity.getLtp());
            dto.setHaircut(entity.getHaircut());
            dto.setProfitAndLoss(entity.getProfitAndLoss());
            dto.setCollateralType(entity.getCollateralType());
            dto.setAveragePrice(entity.getAveragePrice());
            dto.setTradingSymbol(entity.getTradingSymbol());
            dto.setPnlPercentage(entity.getPnlPercentage());
            dto.setExchange(entity.getExchange());
            dto.setClose(entity.getClose());
            dto.setIsin(entity.getIsin());
            dto.setRealisedQuantity(entity.getRealisedQuantity());
            dto.setSymbolToken(entity.getSymbolToken());
            dto.setCollateralQuantity(entity.getCollateralQuantity());
            dtos.add(dto);
        }
        return dtos;
    }

    public Holding toEntity(HoldingDto dto) {
        Holding entity = new Holding();
        entity.setT1Quantity(dto.getT1Quantity());
        entity.setAuthorisedQuantity(dto.getAuthorisedQuantity());
        entity.setProduct(dto.getProduct());
        entity.setQuantity(dto.getQuantity());
        entity.setLtp(dto.getLtp());
        entity.setHaircut(dto.getHaircut());
        entity.setProfitAndLoss(dto.getProfitAndLoss());
        entity.setCollateralType(dto.getCollateralType());
        entity.setAveragePrice(dto.getAveragePrice());
        entity.setTradingSymbol(dto.getTradingSymbol());
        entity.setPnlPercentage(dto.getPnlPercentage());
        entity.setExchange(dto.getExchange());
        entity.setClose(dto.getClose());
        entity.setIsin(dto.getIsin());
        entity.setRealisedQuantity(dto.getRealisedQuantity());
        entity.setSymbolToken(dto.getSymbolToken());
        entity.setCollateralQuantity(dto.getCollateralQuantity());
        // Set other entity fields like user and createdAt if needed
        return entity;
    }

    public List<Holding> toListOfEntity(List<HoldingDto> dtos){
        List<Holding> entities = new ArrayList<>();
        for(HoldingDto dto : dtos){
            Holding entity = new Holding();
            entity.setT1Quantity(dto.getT1Quantity());
            entity.setAuthorisedQuantity(dto.getAuthorisedQuantity());
            entity.setProduct(dto.getProduct());
            entity.setQuantity(dto.getQuantity());
            entity.setLtp(dto.getLtp());
            entity.setHaircut(dto.getHaircut());
            entity.setProfitAndLoss(dto.getProfitAndLoss());
            entity.setCollateralType(dto.getCollateralType());
            entity.setAveragePrice(dto.getAveragePrice());
            entity.setTradingSymbol(dto.getTradingSymbol());
            entity.setPnlPercentage(dto.getPnlPercentage());
            entity.setExchange(dto.getExchange());
            entity.setClose(dto.getClose());
            entity.setIsin(dto.getIsin());
            entity.setRealisedQuantity(dto.getRealisedQuantity());
            entity.setSymbolToken(dto.getSymbolToken());
            entity.setCollateralQuantity(dto.getCollateralQuantity());
            // Set other entity fields like user and createdAt if needed
            entities.add(entity);
        }
        return entities;
    }
}
