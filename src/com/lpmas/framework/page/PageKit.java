package com.lpmas.framework.page;

import java.util.ArrayList;
import java.util.List;

public class PageKit {
	// private static Logger log = LoggerFactory.getLogger(PageKit.class);

	public static final int OTHER_FLAG = 0;
	public static final int CURRENT_FLAG = 1;

	public static List<int[]> getPageNumberList(PageBean pageBean, int displayTotalNumber) {
		List<int[]> list = new ArrayList<int[]>();

		int[] pageNumberRange = getPageNumberRange(pageBean.getTotalPageNumber(), pageBean.getCurrentPageNumber(),
				displayTotalNumber);
		int startPageNumber = pageNumberRange[0];// 起始显示
		int endPageNumber = pageNumberRange[1];// 终止显示

		for (int i = startPageNumber; i <= endPageNumber; i++) {
			if (i == pageBean.getCurrentPageNumber()) {
				list.add(new int[] { i, CURRENT_FLAG });
			} else {
				list.add(new int[] { i, OTHER_FLAG });
			}
		}
		return list;
	}

	public static int[] getPageNumberRange(int totalNumber, int currentPageNumber, int displayTotalNumber) {
		int gapNumber = (displayTotalNumber + 1) / 2;// 前后间距
		int startPageNumber = 1;// 起始显示
		int endPageNumber = totalNumber;// 终止显示

		// 首页在间距之内
		int startGap = currentPageNumber - gapNumber;
		if (startGap <= 0) {
			startPageNumber = 1;
			if (displayTotalNumber > totalNumber) {
				endPageNumber = totalNumber;
			} else {
				endPageNumber = displayTotalNumber;
			}
		}

		// 尾页在间距之内
		int endGap = currentPageNumber + gapNumber;
		if (endGap >= endPageNumber) {
			if (displayTotalNumber >= endPageNumber) {
				startPageNumber = 1;
			} else {
				startPageNumber = endPageNumber - displayTotalNumber + 1;
			}
		}

		if (startGap > 0 && endGap <= endPageNumber) {
			startPageNumber = currentPageNumber - (displayTotalNumber - gapNumber);
			endPageNumber = currentPageNumber + (displayTotalNumber - gapNumber);
		}

		return new int[] { startPageNumber, endPageNumber };
	}
}
