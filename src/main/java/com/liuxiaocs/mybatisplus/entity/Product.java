package com.liuxiaocs.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author liuxiaocs
 * @date 2021/4/4 22:07
 */
@Data
public class Product {

    private Long id;
    private String name;
    private Integer price;

    @Version
    private Integer version;
}
