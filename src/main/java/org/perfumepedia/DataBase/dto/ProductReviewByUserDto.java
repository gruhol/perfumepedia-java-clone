package org.perfumepedia.DataBase.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class ProductReviewByUserDto {
    @NotNull
    private Long idProduct;
    private Short score;
    @Length(min = 2, message = "{errors.field.must.have.min.value.char}")
    private String textReview;
}
