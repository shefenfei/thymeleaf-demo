package com.fisher.demo.gateway.config;

//@Configuration
//@EnableWebFluxSecurity
public class GatewaySecurityConfig {


    private static final String[] excludePaths = {"/lesson/**", "/demo/getDict", "/demo/save1"};
/*
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity.authorizeExchange()
                .pathMatchers(excludePaths).permitAll()
                .anyExchange().authenticated()
                .and().csrf(csrf -> csrf.disable())
                .cors(corsSpec -> {
                    corsSpec.configurationSource(serverWebExchange -> {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.addAllowedOrigin("*");
                        return corsConfiguration;
                    });
                });
//                .formLogin().and()
//                .exceptionHandling().authenticationEntryPoint(new CustomServerAuthenticationEntryPoint("/login"));
        return httpSecurity.build();
    }

    private String[] buildExcludePaths(String[] excludePaths) {
        ArrayList<String> paths = new ArrayList<>();
        if (excludePaths.length == 0) {
            return new String[]{};
        }
        for (String excludePath : excludePaths) {
            paths.add("/school" + excludePath);
        }
        return paths.toArray(new String[0]);
    }*/

}
