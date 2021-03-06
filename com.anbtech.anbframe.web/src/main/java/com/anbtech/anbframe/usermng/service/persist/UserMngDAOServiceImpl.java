package com.anbtech.anbframe.usermng.service.persist;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.anbtech.anbframe.anbweb.vo.UserMngVO;
import com.anbtech.anbframe.usermng.service.UserMngDAOService;

@Service
public class UserMngDAOServiceImpl implements UserMngDAOService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.anbtech.anbframe.usermng.service.persist.UserMngDAOService#getListUser(com.anbtech.anbframe.anbweb.usermng.vo.UserMngVO)
	 */
	public ArrayList getListUser(UserMngVO param){
		String sql = "SELECT EMP_ID empId"
						 + ", ANB_USER_USER_ID  anbUserUserId"
						 + ", EMP.ANB_RANK_RANK_CODE  anbRankRankCode"
						 + ", EMP.ANB_DIV_DIV_CODE  anbDivDivCode"
						 + ", RANK.RANK_NAME  anbRankRankName"
						 + ", DIV.DIV_NAME  anbDivDivName"
					     + ", EMP_NAME  empName"
				         + ", EMP_EMAIL empEmail"
				         + ", EMP_NAME_ENG empNameEng"
				         + ", EMP_PHONE  empPhone"
				         + ", EMP_HANDPHONE empHandphone"
				         + ", EMP_ADDRESS empAddress"
				         + ", IN_DATE inDate"
				         + ", MAR_DATE marDate"
				         + ", POST_CODE postCode"
				         + ", MAR_YN marYn"
				         + ", CAR_YN carYn"
				         + ", EMP_TYPE empType"
				         + "  FROM  ANB_EMPLOYEE EMP , ANB_DIV DIV , ANB_RANK RANK"
				         + "  WHERE EMP.ANB_RANK_RANK_CODE = RANK.RANK_CODE"
				         + "  AND EMP.ANB_DIV_DIV_CODE = DIV.DIV_CODE";
		return (ArrayList)jdbcTemplate.query(sql,new BeanPropertyRowMapper(UserMngVO.class));
	}
	
	/* (non-Javadoc)
	 * @see com.anbtech.anbframe.usermng.service.persist.UserMngDAOService#updateUser(com.anbtech.anbframe.anbweb.usermng.vo.UserMngVO)
	 */
	synchronized public int updateUser(UserMngVO param){
		int cnt = 0;
		StringBuilder sb = new StringBuilder(); 
		sb.append(" UPDATE ANB_EMPLOYEE SET ");
		sb.append(" EMP_NAME = ?");
		sb.append(" ,EMP_EMAIL = ?");
		sb.append(" ,EMP_PHONE = ?");
		sb.append(" ,EMP_ADDRESS = ?");
		sb.append(" ,ANB_RANK_RANK_CODE = ?");
		sb.append(" ,ANB_DIV_DIV_CODE = ?");
		sb.append(" WHERE ANB_USER_USER_ID = ?");
		cnt = jdbcTemplate.update(sb.toString(),param.getEmpName()
				,param.getEmpEmail()
				,param.getEmpPhone()
				,param.getEmpAddress()
				,param.getAnbRankRankCode()
				,param.getAnbDivDivCode()
				,param.getAnbUserUserId());
		return cnt;
	}
	
	/* (non-Javadoc)
	 * @see com.anbtech.anbframe.usermng.service.persist.UserMngDAOService#deleteUser(java.lang.String)
	 */
	synchronized public void deleteUser(String user_id) throws Exception{
		StringBuffer sb = new StringBuffer();
		
		sb.append(" DELETE FROM ANB_EMPLOYEE where anb_user_user_id = ?");
		Object[] args = {user_id};
		String sql = sb.toString();
		jdbcTemplate.update(sql, args);
		deleteAnbUser(user_id);
	}
	
	/* (non-Javadoc)
	 * @see com.anbtech.anbframe.usermng.service.persist.UserMngDAOService#insertUser(com.anbtech.anbframe.anbweb.usermng.vo.UserMngVO)
	 */
	synchronized public void insertUser(UserMngVO param) throws Exception{
		    insertAnbUser(param);
			String sql = "INSERT INTO ANB_EMPLOYEE (EMP_ID, ANB_USER_USER_ID, ANB_RANK_RANK_CODE, ANB_DIV_DIV_CODE, ANB_PRIVILEGE_PRI_CODE, EMP_NAME, EMP_EMAIL, EMP_NAME_ENG, EMP_PHONE, EMP_HANDPHONE, EMP_ADDRESS, IN_DATE, MAR_DATE, POST_CODE, MAR_YN, CAR_YN, EMP_TYPE) "
					+ "VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,  ? , ? , ? , ? , ?) ";
			jdbcTemplate.update(sql, new Object[] { 
				getEmpId(),
				param.getAnbUserUserId(),
				param.getAnbRankRankCode(),       
				param.getAnbDivDivCode(),         
				param.getAnbPrivilegePriCode(),   
				param.getEmpName(),               
				param.getEmpEmail(),              
				param.getEmpNameEng(),            
				param.getEmpPhone(),              
				param.getEmpHandphone(),          
				param.getEmpAddress(),            
				param.getInDate(),                
				param.getMarDate(),               
				param.getPostCode(),              
				param.getMarYn(),                 
				param.getCarYn(),                 
				param.getEmpType()});               
				
	}
	
	
	/* (non-Javadoc)
	 * @see com.anbtech.anbframe.usermng.service.persist.UserMngDAOService#getEmpId()
	 */
	public String getEmpId() throws Exception{
		String sql = "select 'A'||LPAD(TO_NUMBER(SUBSTR(max(emp_id),2,5)) + 1,5,'0')"
				+"from ANB_EMPLOYEE";
		String empId = "";
		empId = (String) jdbcTemplate.queryForObject(sql, String.class);
		return empId;
	}
	
	/* (non-Javadoc)
	 * @see com.anbtech.anbframe.usermng.service.persist.UserMngDAOService#checkDuplicationId(com.anbtech.anbframe.anbweb.usermng.vo.UserMngVO)
	 */
	@SuppressWarnings("deprecation")
	public int checkDuplicationId(UserMngVO param) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT COUNT(*) ");
		sb.append(" from ANB_EMPLOYEE ");
        sb.append(" where ANB_USER_USER_ID  = ? ");
        return (int) jdbcTemplate.queryForInt(sb.toString(),new Object[] {param.getAnbUserUserId()});
	}
	
	private void insertAnbUser(UserMngVO param) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ANB_USER (USER_ID, USER_NAME, USER_PW, USER_GENDER, USER_BIRTH, SUB_DATE, MOD_DATE, PRS_STATUS )");
		sb.append("              VALUES (? , ? , '' , '' , '' , '' , '','')");
		jdbcTemplate.update(sb.toString(), new Object[]{
			param.getAnbUserUserId() 
			,param.getEmpName()
		});
	}
	
	private int deleteAnbUser(String userId)throws Exception{
		int cnt = 0;
		StringBuilder sb = new StringBuilder(); 
		sb.append(" DELETE FROM ANB_USER WHERE USER_ID = ? ");
		cnt = jdbcTemplate.update(sb.toString(),new Object[] {userId});
		return cnt;
	}
	
}
