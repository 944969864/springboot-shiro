package com.dudu.config;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author 嘟嘟嘟
 * @ClassName ShiroConfig
 * @Project springboot-08-shiro
 * @CreateTime 2020/12/23 16:56
 */
@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean      第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        // anno:无需认证就可以访问
        // authc:必须认证才能访问
        // user:必须拥有记住我功能才能用
        //perms:拥有对某个资源的权限才能访问
        // role:拥有某个角色权限才能访问
        Map<String, String> filtertMap=new LinkedHashMap<>();//拦截
        //filtertMap.put("/user/add","authc");
        //filtertMap.put("/user/update","authc");
        filtertMap.put("/user/*","authc");//和上面两行功能一样
        //设置登录的请求
        bean.setLoginUrl("/toLogin");
        bean.setFilterChainDefinitionMap(filtertMap);

        return bean;
    }
    //DafaultWebSecurityManger    第二步
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联 UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //创建realm对象   需要自定义类  第一步
    @Bean//UserRealm 是UserRealm类
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
