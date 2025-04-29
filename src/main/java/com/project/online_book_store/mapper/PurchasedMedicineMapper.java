package com.project.online_book_store.mapper;

import com.project.online_book_store.dto.PurchasedMedicineDTO;
import com.project.online_book_store.entity.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchasedMedicineMapper {

    @Mapping(source = "medicine.name", target = "medicineName")
    @Mapping(source = "medicine.stockQuantity", target = "quantity")
    @Mapping(source = "medicine.price", target = "price")
    @Mapping(source = "medicine.manufacturer", target = "manufacturer")
    @Mapping(source = "medicine.manufactureDate", target = "manufactureDate")
    @Mapping(source = "medicine.expiryDate", target = "expiryDate")
    PurchasedMedicineDTO toMedicineDTO(Medicine medicine);
}
