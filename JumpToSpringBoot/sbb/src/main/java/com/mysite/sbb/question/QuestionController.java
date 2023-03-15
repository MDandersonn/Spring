package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RequestMapping("/question")
@RequiredArgsConstructor
/*
 * @RequiredArgsConstructor는
 * 롬복이 제공하는 애너테이션으로 final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할을 한다
 * 의존성주입은 @Autowired는  생성자가 하나면 생략해도되기에 자동으로 의존성주입해줌.
 */
@Controller
public class QuestionController {

	private final QuestionService questionService;

	@GetMapping("/list")
	// @ResponseBody//이걸빼면 template의 페이지로 이동하라는말
	public String list(Model model) {
//Model 객체는 따로 생성할 필요없이 컨트롤러 메서드의 매개변수로 지정하기만 하면 스프링부트가 자동으로 Model 객체를 생성한다.

		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);
		return "question_list";
	}
	
	@GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
		 Question question = this.questionService.getQuestion(id);
	        model.addAttribute("question", question);
        return "question_detail";
    }
	

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
    	/*
   question_form.html 템플릿은 "질문 등록하기" 버튼을 통해 GET 방식으로 요청되더라도 th:object에 의해 QuestionForm 객체가 필요하기 때문이다.
   QuestionForm과 같이 매개변수로 바인딩한 객체는 Model 객체로 전달하지 않아도 템플릿에서 사용이 가능하다.
    	 */
        return "question_form";
    }
    @PostMapping("/create")
//    public String questionCreate(@RequestParam String subject, @RequestParam String content) {
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
    	/*questionCreate 메서드의 매개변수를 subject, content 대신 QuestionForm 객체로 변경했다.
    	 *  subject, content 항목을 지닌 폼이 전송되면 QuestionForm의 subject, content 속성이 자동으로 바인딩 된다. 
    	 * 이것은 스프링 프레임워크의 바인딩 기능이다
    	 * 
    	 * QuestionForm 매개변수 앞에 @Valid 애너테이션을 적용했다. 
    	 * @Valid 애너테이션을 적용하면 QuestionForm의 @NotEmpty, @Size 등으로 설정한 검증 기능이 동작한다.
    	 *  그리고 이어지는 BindingResult 매개변수는 @Valid 애너테이션으로 인해 검증이 수행된 결과를 의미하는 객체이다.
    	 */
    	if (bindingResult.hasErrors()) {
            return "question_form";
        }
    	
//    	this.questionService.create(subject, content);
    	this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
}