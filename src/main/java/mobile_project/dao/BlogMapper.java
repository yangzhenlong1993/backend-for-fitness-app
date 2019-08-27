package mobile_project.dao;

import java.util.List;
import mobile_project.bean.Blog;
import mobile_project.bean.BlogExample;
import org.apache.ibatis.annotations.Param;

public interface BlogMapper {
    long countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExampleWithBLOBs(BlogExample example);

    List<Blog> selectByExample(BlogExample example);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExampleWithBLOBs(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);
}