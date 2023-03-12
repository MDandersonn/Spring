package hello.core.singleton;
public class StatefulService {
/*
    private int price; //상태를 유지하는 필드
//진짜 공유필드는 조심해야 한다! 스프링 빈은 항상 무상태(stateless)로 설계하자

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제!
    }

    //필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
    public int getPrice() {
        return price;
    }

 */
    //아래처럼 상태유지필드를 없애고  지역변수로 받아서 사용할 것.
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

}