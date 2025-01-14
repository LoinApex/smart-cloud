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
package io.github.smart.cloud.starter.monitor.admin.autoconfigure;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * 监控服务权限控制
 *
 * @author collin
 * @date 2024-01-16
 */
@Configuration
public class SecurityAutoConfiguration extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;

    public SecurityAutoConfiguration(final AdminServerProperties adminServerProperties) {
        super();
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        final SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminContextPath + "/");

        http.authorizeRequests()
                .antMatchers(new String[]{this.adminContextPath + "/assets/**"}).permitAll()
                .antMatchers(new String[]{this.adminContextPath + "/login"}).permitAll()
                .antMatchers(new String[]{this.adminContextPath + "/actuator/**"}).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage(this.adminContextPath + "/login").successHandler(successHandler)
                .and().logout().logoutUrl(this.adminContextPath + "/logout")
                .and().httpBasic()
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers(new String[]{this.adminContextPath + "/instances", this.adminContextPath + "/actuator/**"});
    }

}