package org.perfumepedia.DataBase.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
@Getter
@Setter
@ToString
public class FilterProductsInBrandDto {
    private List<String> types;
    private List<String> sex;
}
