package search;

import java.util.ArrayList;
import java.util.List;

import material.Cloth;

public class SoftSearchAlgorithm {
	
	private SoftSearchServiceImpl impl  = SoftSearchServiceImpl.getInstance();
	
	public List<Cloth> searchCloth(List<Cloth> chooseClothList,  String searchStr) {
		
		if (chooseClothList == null) {
			String str = searchStr;
			List<Cloth> allCloth = impl.findAllCloth();
			List<Cloth> result = new ArrayList<Cloth>();
		
			for (int i = 0; i < allCloth.size(); i++) {
				Cloth cloth = allCloth.get(i);
				System.out.println("---------------------------------");
				System.out.println(cloth.toString());
				System.out.println("---------------------------------");
				
				if (cloth.getCloth_name().contains(str) || cloth.getCloth_brand().contains(str)) {
					result.add(cloth);
				} else if (str.contains("빨강") || str.contains("빨간") || str.contains("붉은") || str.contains("적색")) {
					if (cloth.getColor_category() == 1) {
						result.add(cloth);
					}
				} else if (str.contains("노랑") || str.contains("노란")) {
					if (cloth.getColor_category() == 2) {
						result.add(cloth);
					}
				} else if (str.contains("파랑") || str.contains("파란")) {
					if (cloth.getColor_category() == 3) {
						result.add(cloth);
					}
				} else if (str.contains("초록") || str.contains("녹색")) {
					if (cloth.getColor_category() == 4) {
						result.add(cloth);
					}
				} else if (str.contains("갈색")) {
					if (cloth.getColor_category() == 5) {
						result.add(cloth);
					}
				} else if (str.contains("무채") || str.contains("검은") || str.contains("검정") || str.contains("흰")) {
					if (cloth.getColor_category() == 6) {
						result.add(cloth);
					}
				} else if (str.contains("봄")) {
					if (cloth.getSeason_category() == 1 || cloth.getSeason_category() == 5) {
						result.add(cloth);
					}
				} else if (str.contains("여름")) {
					if (cloth.getSeason_category() == 2 || cloth.getSeason_category() == 5) {
						result.add(cloth);
					}
				} else if (str.contains("가을")) {
					if (cloth.getSeason_category() == 3 || cloth.getSeason_category() == 5) {
						result.add(cloth);
					}
				} else if (str.contains("겨울")) {
					if (cloth.getSeason_category() == 4 || cloth.getSeason_category() == 5) {
						result.add(cloth);
					}
				}
			}
			
			return result;
		} else {
			return null;
		}
	
	}
	
}
