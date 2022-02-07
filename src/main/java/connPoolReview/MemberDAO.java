package connPoolReview;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    private Connection conn;

    public List<MemberVO> listMembers(){
        List<MemberVO> list = new ArrayList<>();
        try {
            connDB();
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

    private void connDB(){
//        try {
//            Class.forName(driver);
//            conn = DriverManager.getConnection(url, user, pwd);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
