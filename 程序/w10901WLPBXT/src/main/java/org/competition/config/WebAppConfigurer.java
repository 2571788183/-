package org.competition.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import org.competition.filter.AttachmentFilter;
import org.competition.filter.EncodingFilter;
import org.competition.filter.HttpSessionFilter;
import org.competition.filter.IndexFilter;
import org.competition.filter.LoginFilter;
import org.competition.filter.PageFilter;
import org.competition.interceptor.LoginInterceptor;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

	@Bean 
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/backend/css/**","/backend/js/**","/backend/fonts/**",
        		"/backend/images/**","/style/**","/upload_file/**","/upload_image/**","/backend/sysName.txt");
    }
    
    @Bean
    public FilterRegistrationBean<EncodingFilter> encodingFilter() {
    	FilterRegistrationBean<EncodingFilter> filterRegistrationBean = new FilterRegistrationBean<EncodingFilter>();
    	filterRegistrationBean.setFilter(new EncodingFilter());
    	filterRegistrationBean.addUrlPatterns("/*");
    	filterRegistrationBean.setName("encodingFilter");
    	return filterRegistrationBean;
    }
    
    @Bean
    public FilterRegistrationBean<PageFilter> pageFilter() {
    	FilterRegistrationBean<PageFilter> filterRegistrationBean = new FilterRegistrationBean<PageFilter>();
    	filterRegistrationBean.setFilter(new PageFilter());
    	filterRegistrationBean.addUrlPatterns("/*");
    	filterRegistrationBean.setName("pageFilter");
    	return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean<AttachmentFilter> attachmentFilter() {
    	FilterRegistrationBean<AttachmentFilter> filterRegistrationBean = new FilterRegistrationBean<AttachmentFilter>();
    	filterRegistrationBean.setFilter(new AttachmentFilter());
    	filterRegistrationBean.addUrlPatterns("/*");
    	filterRegistrationBean.setName("attachmentFilter");
    	return filterRegistrationBean;
    }
    @Value("${service.lg.filter.check.urlPattern}")
    private String urlPattern;
    @Value("${service.lg.filter.check.escape}")
    private String escape;
    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {
    	FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<LoginFilter>();
    	filterRegistrationBean.setFilter(new LoginFilter());
    	filterRegistrationBean.addUrlPatterns("/backend/*");
    	filterRegistrationBean.addInitParameter("urlPattern", urlPattern);
    	filterRegistrationBean.addInitParameter("escape", escape);
    	filterRegistrationBean.setName("loginFilter");
    	return filterRegistrationBean;
    }
    
    @Bean 
	public HttpSessionFilter httpSessionFilter() {
		return new HttpSessionFilter();
	}
    @Bean
    public FilterRegistrationBean<HttpSessionFilter> httpSessionFilterRegistor() {
    	FilterRegistrationBean<HttpSessionFilter> filterRegistrationBean = new FilterRegistrationBean<HttpSessionFilter>();
    	filterRegistrationBean.setFilter(httpSessionFilter());
    	filterRegistrationBean.addUrlPatterns("/*");
    	filterRegistrationBean.setName("httpSessionFilter");
    	return filterRegistrationBean;
    }
    @Bean 
	public IndexFilter getIndexFilter() {
		return new IndexFilter();
	}
    @Bean
    public FilterRegistrationBean<IndexFilter> indexFilter() {
        FilterRegistrationBean<IndexFilter> filterRegistrationBean = new FilterRegistrationBean<IndexFilter>();
        filterRegistrationBean.setFilter(getIndexFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("indexFilter");
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean<UrlRewriteFilter> urlFilter() {
    	FilterRegistrationBean<UrlRewriteFilter> filterRegistrationBean = new FilterRegistrationBean<UrlRewriteFilter>();
    	filterRegistrationBean.setFilter(new UrlRewriteFilter());
    	filterRegistrationBean.addUrlPatterns("/*");
    	filterRegistrationBean.setName("urlFilter");
    	filterRegistrationBean.addInitParameter("confReloadCheckInterval", "3");
    	EnumSet<DispatcherType> dispatcherTypes = EnumSet.allOf(DispatcherType.class);
    	dispatcherTypes.add(DispatcherType.FORWARD);
    	dispatcherTypes.add(DispatcherType.REQUEST);
    	filterRegistrationBean.setDispatcherTypes(dispatcherTypes);
    	return filterRegistrationBean;
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxRequestSize(DataSize.ofMegabytes(200));
        factory.setMaxFileSize(DataSize.ofMegabytes(200));
        return factory.createMultipartConfig();
    }
}
