package com.info.config;

import org.springframework.context.annotation.Configuration;

@Configuration
/*@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)*/
public class WebSecurityConfig {

   /* @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/configuration/ui",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/swagger-ui/index.html")
                                .permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer(
                        OAuth2ResourceServerConfigurer::jwt);

        return http.build();
    }*/

    /*@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/disposal-methods/**","/items/**")
                                .hasAuthority("SCOPE_internal")
                                .anyRequest()
                                .authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }
*/
}
