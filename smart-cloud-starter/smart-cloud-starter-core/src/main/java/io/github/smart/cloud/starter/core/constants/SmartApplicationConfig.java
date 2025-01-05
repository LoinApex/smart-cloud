/*
 * Copyright © 2019 collin (1634753825@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.smart.cloud.starter.core.constants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.Assert;

/**
 * 应用配置
 *
 * @author collin
 * @date 2021-11-11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SmartApplicationConfig {

    /**
     * 应用启动类
     */
    @Getter
    @Setter
    private static Class<?> mainApplicationClass;

    /**
     * 应用基础包名
     */
    @Setter
    private static String[] basePackages;

    public static String[] getBasePackages() {
        Assert.isTrue(ArrayUtils.isNotEmpty(SmartApplicationConfig.basePackages), "basePackages未配置！！！");

        return basePackages;
    }

}