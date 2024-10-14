package management;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

import material.AppContextListener;
import user.User;

// 작성자 : 이나겸, 이진석

@WebServlet("/productManage")
public class ProductManageServlet extends HttpServlet {
	AppContextListener app;
	ManageService service = new ManageServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");

		// 로그인한 userId가 admin이어야 관리 페이지 진입 가능
		if (userId.equals("admin")) {
			req.setCharacterEncoding("utf-8");

			// 사이즈 보관
			Map<String, String> size = new HashMap();
			size.put("1", "S");
			size.put("2", "M");
			size.put("3", "L");
			size.put("4", "XL");
			size.put("5", "XXL");

			// 옷의 권장성별
			List<String> gender = new ArrayList<String>();
			gender.add("남자");
			gender.add("여자");
			gender.add("공용");

			// 각 카테고리 데이터 뽑아오기
			List<Category_color> color;
			List<Category_s> sList;
			List<Category_season> season;
			List<Category_usage> usage;

			List<Cloth> allClothList = null; // 모든 옷 리스트

			try (SqlSession sqlSession = app.getSqlSession()) {
				ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

				allClothList = manageMapper.getAllCloth();
				color = service.selectColor();
				sList = service.selectS();
				season = service.selectSeason();
				usage = service.selectUsage();

				// 전체 상품 수
				int allClothCount = manageMapper.getAllClothCount();
				req.setAttribute("allClothCount", allClothCount);
			}

			req.setAttribute("size", size);
			req.setAttribute("gender", gender);

			req.setAttribute("color", color);
			req.setAttribute("sList", sList);
			req.setAttribute("season", season);
			req.setAttribute("usage", usage);
			req.setAttribute("allClothList", allClothList);

			req.getRequestDispatcher("/WEB-INF/views/productManage.jsp").forward(req, resp);

		} else { // 로그인한 userId가 admin이 아닐 경우 메인 페이지만 진입 가능
			req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
		}
	}

	// 상품 추가
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		req.setCharacterEncoding("utf-8");

		JsonMapper mapper = new JsonMapper();
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);

		}

		String json = sb.toString();
		JsonNode rootNode = mapper.readTree(json);
		Cloth cloth = mapper.treeToValue(rootNode.get("Cloth"), Cloth.class);
		Categorys categorys = mapper.treeToValue(rootNode.get("Categorys"), Categorys.class);

		// 1. cloth 먼저 insert
		int clothPk = 0;
		int categoryPk = 0;
		int lastNum = 0;
		int num = service.insertCloth(cloth);

		if (num > 0) {
			num = 0;

			// 2. cloth의 pk값 빼오기
			clothPk = service.SelectLastPk();

			// 3. inventory insert
			cloth.setCloth_num(clothPk);
			num = service.insertInventory(cloth);

			if (num > 0 && clothPk > 0) {
				// 4. Categorys insert
				num = 0;

				if (categorys.getUsage_category2() != 0 && categorys.getUsage_category3() != 0) {
					num = service.insertCategorys1(clothPk, categorys);

				} else if (categorys.getUsage_category2() != 0 && categorys.getUsage_category3() == 0) {
					num = service.insertCategorys3(clothPk, categorys);

				} else if (categorys.getUsage_category2() == 0 && categorys.getUsage_category3() != 0) {
					num = service.insertCategorys2(clothPk, categorys);

				} else {
					num = service.insertCategorys4(clothPk, categorys);
				}

				// 5. 카테고리 만들어질때 pk값 빼오기
				categoryPk = service.SelectLastPk();
				if (num > 0 && categoryPk > 0) {
					lastNum = service.insertUpdateCloth(clothPk, categoryPk);
				}
			}
		}

		if (lastNum > 0) {
			resp.setStatus(200);

		} else {
			resp.setStatus(420);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		req.setCharacterEncoding("utf-8");

		JsonMapper mapper = new JsonMapper();
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);

		}
		String json = sb.toString();
		Cloth cloth = mapper.readValue(json, Cloth.class);
		System.out.println(cloth.toString());
		int num = service.updateInfoCloth(cloth);
		System.out.println("1번됨");

		if (num > 0) {
			num = service.updateInfoInventory(cloth);
			System.out.println("2번 됨");

			if (num > 0) {
				resp.setStatus(200);

			} else {
				resp.setStatus(408);
				System.out.println("인벤토리 업데이트 실패");
				// 인벤토리 업데이트 실패
			}

		} else {
			resp.setStatus(404);
			System.out.println("옷정보 업데이트 부터 실패");
			// 옷정보 업데이트 부터 실패
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		req.setCharacterEncoding("utf-8");

		JsonMapper mapper = new JsonMapper();
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		String json = sb.toString();
		Cloth cloth = mapper.readValue(json, Cloth.class);
		int num = service.logicalClothDelete(cloth.getCloth_num());

		if (num > 0) {
			resp.setStatus(200);

		} else {
			resp.setStatus(408);
			// 인벤토리 업데이트 실패
		}
	}
}