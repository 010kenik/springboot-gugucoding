package org.zerock.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.QWebBoard;
import org.zerock.domain.WebBoard;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long> 
																						, QuerydslPredicateExecutor<WebBoard> {
	
	// p 271 
	// 페이징 처리를 위해서는 QuerydslPredicateExecutor 인터페이스의 findAll() 메서드 사용한다.
	// 매개변수 Predicate와 Pageable 2개가 필요하다.
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QWebBoard board = QWebBoard.webBoard; 
		
		builder.and(board.bno.gt(0));
		
		if(type == null){
			return builder;
		}
		
		switch(type){
		case "t":
			builder.and(board.title.like("%" + keyword +"%"));
			break;
		case "c":
			builder.and(board.content.like("%" + keyword +"%"));
			break;
		case "w":
			builder.and(board.writer.like("%" + keyword +"%"));
			break;
		}
		
		return builder;
	}

}
