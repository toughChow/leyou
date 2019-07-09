package com.leyou.eurekaprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 当关闭ApplicationContext时，它将销毁所有单例bean，
 * 首先销毁eurekaAutoServiceRegistration，然后销毁feignContext。
 * 当销毁feignContext时，它将关闭与每个FeignClient相关联的ApplicationContext。
 * 因为eurekaAutoServiceRegistration侦听ContextClosedEvent，所以这些事件将被发送到该bean
 * @author wwl
 * @since  2019-7-1
 */
@Component
public class FeignBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (containsBeanDefinition(beanFactory, "feignContext", "eurekaAutoServiceRegistration")) {
            BeanDefinition bd = beanFactory.getBeanDefinition("feignContext");
            bd.setDependsOn("eurekaAutoServiceRegistration");
        }
    }

    private boolean containsBeanDefinition(ConfigurableListableBeanFactory beanFactory, String... beans) {
        return Arrays.stream(beans).allMatch(b -> beanFactory.containsBeanDefinition(b));
    }
}
