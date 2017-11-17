import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.depromeet.hay.domain.Article;

@Mapper
public interface ArticleMapper {
	void update(@Param("id") int id, @Param("article") Article article);
}
