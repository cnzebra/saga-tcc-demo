/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.loessland.saga.demo.inventory;

import com.loessland.saga.demo.inventory.model.Product;
import com.loessland.saga.demo.inventory.model.ProductDao;
import org.apache.servicecomb.saga.omega.spring.EnableOmega;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableOmega
@MapperScan("com.loessland.saga.demo.inventory")
public class TccInventoryApplication {
  public static void main(String[] args) {
    SpringApplication.run(TccInventoryApplication.class, args);
  }

  @Bean
  CommandLineRunner kickOff(ProductDao productDao) {
    return args -> {
      // Set up the inventory
      productDao.save(new Product("ProductA", 100));
      productDao.save(new Product("ProductB", 10));
      productDao.save(new Product("ProductC", 1));
    };
  }
}
