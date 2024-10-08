package search;

import java.util.ArrayList;
import java.util.List;

import material.Cloth;

public class SoftSearchAlgorithm {

	private SoftSearchServiceImpl impl = SoftSearchServiceImpl.getInstance();

	public List<Cloth> searchCloth(String searchStr, List<Cloth> searchClothList) {

		if (searchClothList == null) {
			List<Cloth> allCloth = impl.findAllCloth();
			List<Cloth> result = new ArrayList<Cloth>();

			for (int i = 0; i < allCloth.size(); i++) {
				Cloth cloth = allCloth.get(i);

				algorithm(searchStr, result, cloth);
			}

			return result;
		} else {
			List<Cloth> result = new ArrayList<Cloth>();

			for (int i = 0; i < searchClothList.size(); i++) {
				Cloth cloth = searchClothList.get(i);

				algorithm(searchStr, result, cloth);
			}

			return result;
		}

	}

	private void algorithm(String str, List<Cloth> result, Cloth cloth) {

		int[] clothesTopNums = { 1, 2, 3, 4, 5, 6, 11, 12, 13, 14, 15, 16 };
		int[] clothesBottomNums = { 7, 8, 9, 10, 17 };
		int[] clothesTopAndBottomNums = { 18 };

		int s_category = cloth.getS_category();
		int color_category = cloth.getColor_category();
		int season_category = cloth.getSeason_category();
		
		if (cloth.getCloth_name().contains(str) || cloth.getCloth_brand().contains(str)) {
			result.add(cloth);
		} else if (str.contains("빨강") || str.contains("빨간") || str.contains("붉은") || str.contains("적색")) {
			if (color_category == 1) {
				result.add(cloth);
			}
		} else if (str.contains("노랑") || str.contains("노란") || str.contains("누런")) {
			if (color_category == 2) {
				result.add(cloth);
			}
		} else if (str.contains("파랑") || str.contains("파란") || str.contains("퍼런") || str.contains("푸른")) {
			if (color_category == 3) {
				result.add(cloth);
			}
		} else if (str.contains("초록") || str.contains("녹색")) {
			if (color_category == 4) {
				result.add(cloth);
			}
		} else if (str.contains("갈색") || str.contains("고동")) {
			if (color_category == 5) {
				result.add(cloth);
			}
		} else if (str.contains("무채") || str.contains("검은") || str.contains("검정") || str.contains("흰")) {
			if (color_category == 6) {
				result.add(cloth);
			}
		} else if (str.contains("봄")) {
			if (season_category == 1 || season_category == 5) {
				result.add(cloth);
			}
		} else if (str.contains("여름")) {
			if (season_category == 2 || season_category == 5) {
				result.add(cloth);
			}
		} else if (str.contains("가을")) {
			if (season_category == 3 || season_category == 5) {
				result.add(cloth);
			}
		} else if (str.contains("겨울")) {
			if (season_category == 4 || season_category == 5) {
				result.add(cloth);
			}
		} else if (str.contains("상의") || str.contains("웃옷")) {
			for (int number : clothesTopNums) {
				if (number == s_category) {
					result.add(cloth);
					break;
				}
			}
		} else if (str.contains("하의") || str.contains("바지")) {
			for (int number : clothesBottomNums) {
				if (number == s_category) {
					result.add(cloth);
					break;
				}
			}
		} else if (str.contains("세트")) {
			for (int number : clothesTopAndBottomNums) {
				if (number == s_category) {
					result.add(cloth);
					break;
				}
			}
		}
	}

}
