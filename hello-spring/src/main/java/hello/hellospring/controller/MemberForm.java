package hello.hellospring.controller;

public class MemberForm {
    private String name;
//form이름과 매칭이되면서 값이 들어올것
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
