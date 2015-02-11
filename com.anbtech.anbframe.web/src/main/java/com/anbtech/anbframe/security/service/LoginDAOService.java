package com.anbtech.anbframe.security.service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anbtech.anbframe.security.service.domain.User;

@Service
public class LoginDAOService extends JdbcDaoSupport {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public User getUserInfo(String username) throws UsernameNotFoundException {
		String sql = "SELECT EMP_ID username" + ", PASSWORD  password"
				+ " FROM ANB_EMPLOYEE " + " WHERE EMP_ID = ? ";
		return (User) getJdbcTemplate().queryForObject(sql,
				new Object[] { username },
				new BeanPropertyRowMapper(User.class));
	}

}
