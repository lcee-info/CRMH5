package com.sail.simonli.server.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sail.simonli.server.entity.NameIndex;

public interface NameIndexMapper {
	
   
	@Update("update s_name_index set nameindex =#{nameindex,jdbcType=INTEGER}")
	int updateByPrimaryKeySelective(NameIndex nameIndex);
	 
    @Select("select nameindex from s_name_index t for update")
    NameIndex  selectNameIndex();
    
}