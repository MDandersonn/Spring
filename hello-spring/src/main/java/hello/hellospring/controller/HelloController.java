package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")//get방식으로 받음
//    /hello로 맵핑한다는말
    public String hello(Model model) {//이 메서드가 자동 실행됨, 스프링이 model만들어서 넣어줌
        model.addAttribute("data","hello!!"); //모델넣어서 보냄
         return "hello";// resources의 templates폴더 밑의 파일들 중 찾아서 ,  hello.html로 보낸다는 의미.
    }
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name1, Model model){
//        http://localhost:8080/hello-mvc?name=Spring!!!!1313 이렇게써야함 파라미터가없이쓰면 페이지에러뜸
        //get방식으로  ?name="넘겨줄값" 으로 파라미터를 name으로 받아서 그걸 name1로 지정하겠다는뜻.
        model.addAttribute("name2", name1);//name1값은 지정한 값으로
        return "hello-template";
        //viewResolver가 html을 찾아줌
    }
    @GetMapping("hello-string")
    @ResponseBody //http에서 body부에 리턴값을 직접넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name1){
        return "안녕하세요"+ name1;//  "안녕하세요 mymessage" 이렇게 문자가 그대로 넘어감
        //뷰 이런게없이 위 문자가 그대로 전달됨
        //StringHttpsMessageConverter가 작동(StringConverter)
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name1){
        Hello hello = new Hello();
        hello.setName(name1);
        return hello;//제이슨형태로 반환한다. 클래스변수가 키 밸류는 파라미터로 받은것.
        //리턴값이 문자가아니라 클래스객체면 제이슨형태로 반환한다
        //MappingJackson2HttpsMessageConverter가 작동(JsonConverter)
    }
    static class Hello{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
