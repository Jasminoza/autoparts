package ru.yolkin.autoparts.model.entity.autodoc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record InventoryItem(
    String id,
    BigDecimal price,
    Integer quantity,
    Integer minimalDeliveryDays,
    Integer deliveryDays,
    LocalDate updateDate,
    Supplier supplier,
    Integer orderCompletePercent,
    String directionToManufacturerId,
    Integer minimalQuantity,
    String idPartner,
    String priceType,
    List<Sparepart> analogs,
    String fotoType,
    List<String> fotoIds,
    Boolean isPhotoAvaiable,
    Integer commentCount,
    Boolean isCompatibility,
    Mark mark,
    Boolean isPrivateExists,
    List<Supplier> sparePartShops
){
}
