package application.utils;

import org.springframework.data.domain.PageImpl;

import application.domain.Page;

public class PageUtils {
	public static Page caculatePage(PageImpl page) {
		int current = page.getNumber();
		int pages = page.getTotalPages();
		if (pages <= 7) {
			// return begin and total pages;
			return page(1,pages);
		} else {
			if (current <= 4) {
				int end = pages >= 7 ? 7 : pages;
				return page(1,end);
			} else if (current >= pages - 3 && current <= pages) {
				int begin = pages - 7;
				int end = pages;
				//return begin end
				return page(begin,end);
			} else {
				int begin = current - 3;
                int end = current + 3 >= pages ? pages : current + 3;
                return page(begin,end);
			}
		}
	}

	private static Page page(int i, int j) {
		// TODO Auto-generated method stub
		return new Page(i,j);
	}
}
