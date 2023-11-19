package com.example.cinemachainmanagement.DTO;

import com.mysql.cj.log.Log;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductCategoryDTO {
    private Long id;
    private String categoryName;
}
