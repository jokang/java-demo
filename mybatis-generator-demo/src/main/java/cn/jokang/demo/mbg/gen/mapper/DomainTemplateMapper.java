package cn.jokang.demo.mbg.gen.mapper;

import cn.jokang.demo.mbg.gen.domain.DomainTemplate;
import cn.jokang.demo.mbg.gen.domain.DomainTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DomainTemplateMapper {
    long countByExample(DomainTemplateExample example);

    int deleteByExample(DomainTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DomainTemplate record);

    int insertSelective(DomainTemplate record);

    List<DomainTemplate> selectByExample(DomainTemplateExample example);

    DomainTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DomainTemplate record, @Param("example") DomainTemplateExample example);

    int updateByExample(@Param("record") DomainTemplate record, @Param("example") DomainTemplateExample example);

    int updateByPrimaryKeySelective(DomainTemplate record);

    int updateByPrimaryKey(DomainTemplate record);
}