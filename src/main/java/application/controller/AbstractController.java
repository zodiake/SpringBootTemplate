package application.controller;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class AbstractController<T> {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id");
	}

	protected PageRequest createPageRequest(int page, int size, Sort sort) {
		PageRequest pageRequest = new PageRequest(page - 1, size, sort);
		return pageRequest;
	}

	protected PageRequest createPageRequest(int page, int size) {
		Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
		return createPageRequest(page, size, sort);
	}

	protected PageImpl<T> createPageImpl(Pageable pageable,List<T> lists,Long total) {
		PageImpl<T> pageImpl=new PageImpl<T>(lists,pageable,total.intValue());
		return pageImpl;
	}
}
