package material;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClothSorter {

    private static final ClothSorter instance = new ClothSorter();

    private ClothSorter() {
    }

    public static ClothSorter getInstance() {
        return instance;
    }
    
    public List<Cloth> sortLowPrice(List<Cloth> clothList) {
        Collections.sort(clothList, new Comparator<Cloth>() {
            @Override
            public int compare(Cloth c1, Cloth c2) {
                return Double.compare(c1.getCloth_price(), c2.getCloth_price());
            }
        });
        return clothList;
    }
    
    public List<Cloth> sortHighPrice(List<Cloth> clothList) {
        Collections.sort(clothList, new Comparator<Cloth>() {
            @Override
            public int compare(Cloth c1, Cloth c2) {
                return Double.compare(c2.getCloth_price(), c1.getCloth_price());
            }
        });
        return clothList;
    }

    public List<Cloth> sortByName(List<Cloth> clothList) {
        Collections.sort(clothList, new Comparator<Cloth>() {
            @Override
            public int compare(Cloth c1, Cloth c2) {
                return c1.getCloth_name().compareTo(c2.getCloth_name());
            }
        });
        return clothList;
    }
}
