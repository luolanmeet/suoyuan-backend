/**
 * Copyright (c) 2017, 2023, IPLUS STUDIO and/or its affiliates. All rights reserved.
 * IPLUS STUDIO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages={"com.sy.mapper"})
@SpringBootApplication(scanBasePackageClasses=Application.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}