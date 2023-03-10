package org.zerock;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.WebBoard;
import org.zerock.persistence.WebBoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WebBoardRepositoryTests {

	@Autowired
	 WebBoardRepository  repo;
	
	/*
	@Test
	public void insertBoardDummies() {

		IntStream.range(0, 300).forEach(i -> {

			WebBoard board = new WebBoard();

			board.setTitle("Sample Board Title " + i);
			board.setContent("Content Sample ..." + i + " of Board ");
			board.setWriter("user0" + (i % 10));

			repo.save(board);
			
		}); 
	}
	*/
	
	// 272
	/*
	@Test
	public void testList1() {

		Pageable  pageable = PageRequest.of(0,  20, Direction.DESC, "bno");
		
		Page<WebBoard> result = repo.findAll( repo.makePredicate(null, null), pageable );
		
		log.info("PAGE : " + result.getPageable() );
		log.info(  "-".repeat(30) );
		result.getContent().forEach( board -> log.info("" + board) ); 
		
	}
	*/
	
	// p 274
	@Test
	public void testList2() {

		int page = 0;  // 1 페이지
		int size = 20; // 기본  
		Pageable pageable = PageRequest.of(page, size, Direction.DESC, "bno");

		Page<WebBoard> result = repo.findAll(repo.makePredicate("t", "10"), pageable);

		log.info("PAGE: " + result.getPageable());

		log.info("----------------------");
		result.getContent().forEach(board -> log.info("" + board));

		// Hibernate: select webboard0_.bno as bno1_0_, webboard0_.content as content2_0_, webboard0_.regdate as regdate3_0_, webboard0_.title as title4_0_, webboard0_.updatedate as updateda5_0_, webboard0_.writer as writer6_0_ from tbl_webboards webboard0_ where webboard0_.bno>? and (webboard0_.title like ? escape '!') order by webboard0_.bno desc limit ?
	}

} // class










