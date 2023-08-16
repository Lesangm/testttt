package net.smlee.studywebmemo.mappers;

import net.smlee.studywebmemo.entities.MemoEntity;
import net.smlee.studywebmemo.models.PagingModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MemoMapper {
    int insert(MemoEntity memoEntity);

    MemoEntity[] selectAll();

    int selectCount(@Param(value = "searchCriterion") String searchCriterion,
                    @Param(value = "searchQuery") String searchQuery);

    MemoEntity[] selectByPage(@Param(value = "pagingModel") PagingModel pagingModel,
                              @Param(value = "searchCriterion") String searchCriterion,
                              @Param(value = "searchQuery") String searchQuery);

    int deleteByIndex(@Param(value = "index") int index);

    int updateByIndex(@Param(value = "index") int index,
                      @Param(value = "text") String text);
}
