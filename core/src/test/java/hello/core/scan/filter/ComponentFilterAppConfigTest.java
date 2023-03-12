package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.ComponentScan.Filter;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                                        () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    /*
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION,//애노테이션과관련된 필터를 만들겠다는것
                                    classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION,
                                    classes = MyExcludeComponent.class)
    )//@MyExcludeComponent가 달린 클래스는 컴포넌트스캔에서 제외된다. 그래서 빈으로 동록이 안된다.

     */

    /*
    //BeanA도 빼고 싶을떄,
    @ComponentScan(
            includeFilters = {
                    @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            },
            excludeFilters = {
                    @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
                    @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
            }
    )
    */
    static class ComponentFilterAppConfig {
    }
}
