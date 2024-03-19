package kr.co.sist.dao;

import kr.co.sist.util.DbConnection;
import kr.co.sist.vo.DocumentVO;
import kr.co.sist.vo.EmpInfoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Desc : 관리자 메뉴 view에 표시될 Data<br>
 * 작성자 : 고한별<br>
 * 작성일 : 2024.03.15
 */
public class AdminMenuDAO {
    private static AdminMenuDAO adminMenuDAO;

    private AdminMenuDAO() {
    }

    /**
     * Desc : 관리자 메뉴 view에 사용되는 DAO 객체화
     * @return DAO객체
     */
    public static AdminMenuDAO getInstance(){
        if (adminMenuDAO == null){
            adminMenuDAO = new AdminMenuDAO();
        }
        return adminMenuDAO;
    }

    /**
     * Desc : 업무 알람에 필요한 Data 호출
     * @return : 관련 데이터 list
     * @throws SQLException
     */
    public int alertWork(boolean isCodeFive) throws SQLException {
        int count = 0;
        Connection connection = DbConnection.getCon();
        PreparedStatement preparedStatement = null;

        try {
            String countDocs = "select * from bussiness_log where CODE2 = 1";
            if (isCodeFive) {
                countDocs += " and CODE = 5";
            } else {
                countDocs += " and CODE <> 5";
            }
            preparedStatement = connection.prepareStatement(countDocs);
            count = preparedStatement.executeUpdate();
        } finally {
            DbConnection.dbClose(null, preparedStatement, connection);
        }

        return count;
    }
}
