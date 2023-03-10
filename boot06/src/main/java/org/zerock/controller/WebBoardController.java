package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.WebBoard;
import org.zerock.persistence.WebBoardRepository;
import org.zerock.vo.PageMaker;
import org.zerock.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {
	
	@Autowired
	private WebBoardRepository repo;
	
	// 페이징 처리 첫 번째 p 276
	/*
	@GetMapping("/list")
	public void list( @PageableDefault(page = 0, size = 20, direction = Direction.DESC, sort = "bno")  Pageable pageable) {
		log.info("WebBoardController.list() called...");
	}
	*/

	// 페이징 처리 두 번째 p 277 - PageVO 생성
	@GetMapping("/list")
	public void list( PageVO vo, Model model ) {
		log.info("WebBoardController.list() called...");
		Pageable pageable = vo.makePageable(0, "bno");
		// log.info("" + pageable);
		// ㄱ. QuerydslPredicateExecutor<WebBoard> 인터페이스를 구현한 클래스에서 findAll() 메서드를 통해 페이징 처리 가능		
		//     WebBoardRepository extends CrudRepository<WebBoard, Long> 
		// ㄴ. findAll() 메서드의 매개변수( Predicate, Pageable ) 로 페이징 처리 가능..
		// ㄷ. QWebBoard 자동 생성을 위해 pom.xml 에 querydsl 2개 추가  + plugin 설정 하면 자동으로 QWebBoard.java 파일 생성됨. 
		// ㄹ. PageVO 클래스의  makePageable() 메서드로 Pageable 객체 얻어옴..
		Page<WebBoard> result = this.repo.findAll( this.repo.makePredicate(vo.getType(), vo.getKeyword()), pageable)	;	
		
		//model.addAttribute("result", result);
		// p 288
		model.addAttribute("result", new PageMaker<WebBoard>(result));
	}
	
	// p 308
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo")WebBoard vo ){
		log.info("WebBoardController.register get");
		vo.setTitle("샘플 게시물 제목입니다....");
		vo.setContent("내용을 처리해 봅니다 " );
		vo.setWriter("user00");
	}
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo")WebBoard vo, RedirectAttributes rttr){
		
		log.info("WebBoardController.register post");
		log.info("" + vo);

		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/boards/list";
	}
	
	// p 316
	@GetMapping("/view")
	public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model){
		
		log.info("BNO: "+ bno);
		
		// bno 게시글 정보를 얻어와서 null 이 아니면  vo 에 저장~
		repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
		
	}
	
	// p 321
	@GetMapping("/modify")
	public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model){
		
		log.info("MODIFY BNO: "+ bno);
		
		repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
	}
	
	@PostMapping("/modify")
	public String modifyPost(WebBoard board, PageVO vo, RedirectAttributes rttr ){
		
		log.info("Modify WebBoard: " + board);
		
		
		repo.findById(board.getBno()).ifPresent( origin ->{
		 
			origin.setTitle(board.getTitle());
			origin.setContent(board.getContent());
			
			repo.save(origin);
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("bno", origin.getBno());
		});
		
		//페이징과 검색했던 결과로 이동하는 경우 
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/boards/view";
	}
	
	// p 325
	@PostMapping("/delete")
	public String delete(Long bno, PageVO vo, RedirectAttributes rttr ){
		
		log.info("DELETE BNO: " + bno);
		
		repo.deleteById(bno);
		
		rttr.addFlashAttribute("msg", "success");

		//페이징과 검색했던 결과로 이동하는 경우 
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/boards/list";
	}
	
} // class













