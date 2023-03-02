package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect//aop사용할때 씀
@Component//컴포넌트스캔쓸때,  이렇게하든지 아니면 스프링콘피그에 @Bean으로 등록해서쓴다.

public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))")
    //공통사항이 적용될 파일을 선정( 모두 다 로 설정했음)
    /*가짜 멤버서비스를만듦(프록시) 컨테이너에 스프링빈을 등록할때
    * 가짜 스프링빈을 앞에 사워놈 가짜스프링빈이 끝나면 joinPoint.proceed()하면
    * 진짜를 호출해줌*/
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());//어떤 메서드를 콜하는지 이름을 얻음
        try {
            return joinPoint.proceed();//다음메서드로 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
                    "ms");
        }
    }
}