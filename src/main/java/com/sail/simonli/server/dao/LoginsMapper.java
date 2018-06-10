package com.sail.simonli.server.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sail.simonli.server.entity.Logins;

public interface LoginsMapper {
	
    int deleteByPrimaryKey(Integer loginId);

    int insert(Logins record);

   @Insert(" INSERT INTO s_logins(username,series,token,validtime) VALUES (#{username,jdbcType=VARCHAR},#{series,jdbcType=VARCHAR},#{token,jdbcType=VARCHAR},#{validtime})")
    int insertSelective(Logins record);

    Logins selectByPrimaryKey(Integer loginId);

    @Update("UPDATE s_logins SET  series =#{series,jdbcType=VARCHAR},  token =#{token,jdbcType=VARCHAR}, validtime =#{validtime} WHERE login_id =#{loginId,jdbcType=VARCHAR}")
    int updateByPrimaryKeySelective(Logins record);

    int updateByPrimaryKey(Logins record);
    
    @Select("select t.*,t.login_id as loginId from s_logins t where username = #{username,jdbcType=VARCHAR} and series = #{series,jdbcType=VARCHAR}")
    Logins  selectByUsernameAndSeries(@Param("username") String username, @Param("series") String series);
    
    
    @Select("select t.*,t.login_id as loginId from s_logins t where username = #{username,jdbcType=VARCHAR} ")
    Logins  selectByUsername(@Param("username") String username);
    
}