package com.base.apiserver.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.base.apiserver.service.MemberService;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private SecretKeyProvider keyProvider;
    
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new MemberService();
    }
    
    @Bean
    public TokenStore tokenStore() 
    {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
	public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("heapyung88");
        return converter;
	}
    
    /*
    @Bean
    public JwtAccessTokenConverter accessTokenConverter()
    {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        try {
            converter.setSigningKey(keyProvider.getKey());
        } catch (URISyntaxException | KeyStoreException | NoSuchAlgorithmException | IOException | UnrecoverableKeyException | CertificateException e) {
            e.printStackTrace();
        }

        return converter;
    }
	*/
    
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() 
    {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        return defaultTokenServices;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception 
    {
        endpoints.authenticationManager(this.authenticationManager)
        		.tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
       
    	oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    	//oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
        //			.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }
    
    @Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
    	
    	BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		configurer
				.inMemory()
				.withClient("haepyung")
				.secret("haepyung123")
				.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
				.scopes("read", "write", "trust")
				.resourceIds("oauth2_application")
				.accessTokenValiditySeconds(60)
				.refreshTokenValiditySeconds(10000);
	}
}
