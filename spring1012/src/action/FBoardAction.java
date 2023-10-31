package action;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import dao.FboardDao;
import dto.FboardDTO;
// list
public class FBoardAction implements Action{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String url = "fboard/fboardList";
		boolean method= false;
//		DAO 연결 사용
		List<FboardDTO> list = FboardDao.getDao().listFboard();
		request.setAttribute("list", list);
		request.setAttribute("msg", "ListPage!");
		return new ActionForward(url, method);
	}

}
