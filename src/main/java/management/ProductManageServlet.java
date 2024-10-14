package management;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

import material.AppContextListener;

// 작성자 : 이나겸

@WebServlet("/productManage")
public class ProductManageServlet extends HttpServlet {
   AppContextListener app;
   ManageService service = new ManageServiceImpl();

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("utf-8");

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

      req.setAttribute("color", color);
      req.setAttribute("sList", sList);
      req.setAttribute("season", season);
      req.setAttribute("usage", usage);

      req.setAttribute("allClothList", allClothList);

      req.getRequestDispatcher("/WEB-INF/views/productManage.jsp").forward(req, resp);
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
      JsonNode rootNode = mapper.readTree(json);

      Cloth cloth = mapper.treeToValue(rootNode.get("Cloth"), Cloth.class);
      Categorys categorys = mapper.treeToValue(rootNode.get("Categorys"), Categorys.class);
      
      // 1. cloth 먼저 insert
      int clothPk = 0;
      int categoryPk = 0;
      int lastNum = 0;
      int num = service.insertCloth(cloth);
      if (num > 0) {
         num=0;
         // 2. cloth의 pk값 빼오기
         clothPk = service.SelectLastPk();
         // 3. inventory insert
         cloth.setCloth_num(clothPk);
         num = service.insertInventory(cloth);
         if (num > 0 && clothPk > 0) {
            // 4. Categorys insert
            num = 0;
            if(categorys.getUsage_category2() != 0 && categorys.getUsage_category3() != 0 ) {
               num = service.insertCategorys1(clothPk, categorys);
            } else if (categorys.getUsage_category2() != 0 && categorys.getUsage_category3() == 0 ) {
               num = service.insertCategorys3(clothPk, categorys);
            } else if (categorys.getUsage_category2() == 0 && categorys.getUsage_category3() != 0 ) {
               num = service.insertCategorys2(clothPk, categorys);
            } else {
               num = service.insertCategorys4(clothPk, categorys);
            }
            // 5. 카테고리 만들어질때 pk값 빼오기
            categoryPk = service.SelectLastPk();
            if (num > 0 && categoryPk > 0) {
               lastNum = service.updateCloth(clothPk, categoryPk);
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
   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      app = new AppContextListener();

      // 삭제할 상품의 cloth_num 가져오기
      String clothNumParam = req.getParameter("cloth_num");

      if (clothNumParam != null && !clothNumParam.isEmpty()) {
         int clothNum = Integer.parseInt(clothNumParam);

         // SqlSession을 이용하여 데이터베이스에서 해당 상품 삭제
         try (SqlSession sqlSession = app.getSqlSession()) {
            ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
            manageMapper.deleteCloth(clothNum); // cloth_num으로 해당 상품 삭제
            sqlSession.commit(); // 변경사항을 DB에 반영
         }

         // 삭제 성공 시 상태 코드 204 (No Content) 반환
         resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

      } else {
         // cloth_num이 없거나 잘못된 경우 400 Bad Request 반환
         resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid cloth_num");
      }
   }

}