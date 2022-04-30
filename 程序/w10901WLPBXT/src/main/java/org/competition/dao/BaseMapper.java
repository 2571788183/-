package org.competition.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.competition.model.PersistenceModel;

public interface BaseMapper<T> {
    @Insert({"<script>"+
    		"insert into t_${table}"
    		+"<foreach collection=\"values.keys\" item=\"field\"   separator = \",\" open='(' close=')'>"
	            +"<if test=\"field!='id'\">"
	            	+"`${field}`"
	            +"</if>"
            +"</foreach>"
    		+"values"
            +"<foreach collection=\"values.keys\" item=\"key\" open='(' close=')' separator = \",\">"
            	+"<if test=\"key!='id'\">"
            		+"#{values.${key}.value,jdbcType=${values[key].jdbcType}}"
	            +"</if>"
            +"</foreach>"
    		+"</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "t.id")
    void save(@Param("table") String table,@Param("values") Map<String,PersistenceModel> values,T t);
    
    @Update({"<script>"+
    		"update t_${table}"
    		+"<set>"
    		+"<foreach collection=\"values.keys\" item=\"key\" open='' close='' separator = \",\">"
    		+"<if test=\"key!='id'\">"
    		+"${key}=#{values.${key}.value}"
    		+"</if>"
    		+"</foreach>"
    		+"</set>"
    		+" where id = #{values.id.value}"
    		+"</script>"})
    void update(@Param("table") String table,@Param("values") Map<String,PersistenceModel> values);
    
    @Delete({"delete from t_${table} where id = #{t.id}"})
    void delete(@Param("table") String table,@Param("t") T t);
    
	@Select("select * from t_${table} where id = #{id}")
	T getById(@Param("table") String table,@Param("id") int id);
	
	@Select("select * from t_${table} where users = #{id}")
	T getByUserId(@Param("table") String table,@Param("id") int id);
	
	@Select("${sql}")
	int selectCount(@Param("sql") String sql);
	
	@Select("${sql}")
	List<T> select(@Param("sql") String sql);
}
