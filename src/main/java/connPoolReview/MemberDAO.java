package connPoolReview;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    /*
    <<Without DataSource>>
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/servlet_review";
    private static final String user = "hsymyth"
    private static final String pwd = "gjDHD1992"
     */

    private Connection conn;
    private PreparedStatement preparedStatement;
    private DataSource dataSource;

    public MemberDAO() {
        try {
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/PoolReview");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<MemberVO> listMembers(){
        List<MemberVO> list = new ArrayList<>();
        try {
            //connDB();
            conn = dataSource.getConnection();
            String query = "select * from t_members";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String owner = resultSet.getString("owner");
                String job = resultSet.getString("job");
                String sex = resultSet.getString("sex");
                Date birthDate = resultSet.getDate("birth");
                Date deathDate = resultSet.getDate("death");
                MemberVO vo = new MemberVO();
                vo.setName(name);
                vo.setName(owner);
                vo.setName(job);
                vo.setName(sex);
                vo.setBirth(birthDate);
                vo.setDeath(deathDate);
                list.add(vo);
            }
            resultSet.close();
            preparedStatement.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addMember(MemberVO memberVO) {
        try {
            conn = dataSource.getConnection();
            String name = memberVO.getName();
            String owner = memberVO.getOwner();
            String job = memberVO.getJob();
            String sex = memberVO.getSex();
            java.sql.Date birth = memberVO.getBirth();
            java.sql.Date death = memberVO.getDeath();

            String query = "insert into t_members (name, owner, job, sex, birth, death) values(?, ?, ?, ?, ?, ?)";

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delMember(String name) {
        try {
            conn = dataSource.getConnection();
            String query = "delete from t_members where name=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
        <<Without DataSource>>
    private void connDB(){


        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pwd);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

     */
}
