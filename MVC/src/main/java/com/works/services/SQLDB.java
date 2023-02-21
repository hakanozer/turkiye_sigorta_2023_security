package com.works.services;

import com.works.configs.DB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
public class SQLDB {

    final DB db;

    public boolean login( String email, String password ) {
        try {
            // select * from admin where email = 'a@a.com' and password = '' or 1 = 1 --'
            //String sql = "select * from admin where email = '"+email+"' and password = '"+password+"' ";
            String sql = " select * from admin where email = ? and password = ? ";
            PreparedStatement st = db.dataSource().getConnection().prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            return rs.next();
        }catch (Exception ex) {
            return false;
        }
    }

}
