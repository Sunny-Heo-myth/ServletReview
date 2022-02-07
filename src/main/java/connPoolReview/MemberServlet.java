package connPoolReview;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MemberServlet", value = "/MemberServlet")
public class MemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doHandle(request, response);
        } catch (ParseException e) {
                e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doHandle(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        MemberDAO dao = new MemberDAO();
        PrintWriter out = response.getWriter();
        String command = request.getParameter("command");

        if (command != null && command.equals("addMember")) {
            String _name = request.getParameter("name");
            String _owner = request.getParameter("owner");
            String _job = request.getParameter("job");
            String _sex = request.getParameter("sex");

            Date birthDate = new SimpleDateFormat("dd/MM/yyy").parse(request.getParameter("birth"));
            java.sql.Date bDate = new java.sql.Date(birthDate.getTime());

            Date deathDate = new SimpleDateFormat("dd/MM/yyy").parse(request.getParameter("death"));
            java.sql.Date dDate = new java.sql.Date(deathDate.getTime());

            MemberVO vo = new MemberVO();
            vo.setName(_name);
            vo.setOwner(_owner);
            vo.setJob(_job);
            vo.setSex(_sex);
            vo.setBirth(bDate);
            vo.setDeath(dDate);

            dao.addMember(vo);
        }
        else if (command != null && command.equals("delMember")) {
            String name = request.getParameter("name");
            dao.delMember(name);
        }

        List<MemberVO> list = dao.listMembers();

        out.print("<html><body>");
        out.print("<table border=1><tr align='center'>");
        out.print("<td>name</td><td>ownerName</td><td>job</td>" +
                "<td>sex</td><td>birthDate</td><td>deathDate</td>");

        for (MemberVO memberVO : list) {
            String name = memberVO.getName();
            String owner = memberVO.getOwner();
            String job = memberVO.getJob();
            String sex = memberVO.getSex();
            java.sql.Date birth = memberVO.getBirth();
            java.sql.Date death = memberVO.getDeath();

            out.print("<tr><td>" + name + "</td><td>" + owner +
                    "<tr><td>" + job + "</td><td>" + sex +
                    "<tr><td>" + birth + "</td><td>" + death +
                    "<tr><td>" + "<a href='/ServletReview/MemberServlet?command=delMember&name=" + name +
                    "'> delete member </a></td></tr>");
        }
        out.print("</table></body></html>");
        out.print("<a href='/ServletReview/DataSource/memberForm.html'> register new member </a>");
    }
}
