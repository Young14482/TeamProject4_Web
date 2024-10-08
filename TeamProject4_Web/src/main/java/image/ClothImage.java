package image;

import java.util.Objects;

public class ClothImage {
	private int cloth_num;
	private String image_name;
	private String list_image;
	private String main_image1;
	private String main_image2;
	private String main_image3;
	private String explanation_image1;
	private String explanation_image2;
	private String explanation_image3;
	private String explanation_image4;
	private String explanation_image5;
	
	public ClothImage() {}
	
	public ClothImage(int cloth_num, String image_name, String list_image, String main_image1, String main_image2,
			String main_image3) {
		super();
		this.cloth_num = cloth_num;
		this.image_name = image_name;
		this.list_image = list_image;
		this.main_image1 = main_image1;
		this.main_image2 = main_image2;
		this.main_image3 = main_image3;
	}

	public int getCloth_num() {
		return cloth_num;
	}

	public void setCloth_num(int cloth_num) {
		this.cloth_num = cloth_num;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getList_image() {
		return list_image;
	}

	public void setList_image(String list_image) {
		this.list_image = list_image;
	}

	public String getMain_image1() {
		return main_image1;
	}

	public void setMain_image1(String main_image1) {
		this.main_image1 = main_image1;
	}

	public String getMain_image2() {
		return main_image2;
	}

	public void setMain_image2(String main_image2) {
		this.main_image2 = main_image2;
	}

	public String getMain_image3() {
		return main_image3;
	}

	public void setMain_image3(String main_image3) {
		this.main_image3 = main_image3;
	}

	@Override
	public String toString() {
		return "ClothImage [cloth_num=" + cloth_num + ", image_name=" + image_name + ", list_image=" + list_image
				+ ", main_image1=" + main_image1 + ", main_image2=" + main_image2 + ", main_image3=" + main_image3
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cloth_num, image_name, list_image, main_image1, main_image2, main_image3);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClothImage other = (ClothImage) obj;
		return cloth_num == other.cloth_num && Objects.equals(image_name, other.image_name)
				&& Objects.equals(list_image, other.list_image) && Objects.equals(main_image1, other.main_image1)
				&& Objects.equals(main_image2, other.main_image2) && Objects.equals(main_image3, other.main_image3);
	}
	
	
}
