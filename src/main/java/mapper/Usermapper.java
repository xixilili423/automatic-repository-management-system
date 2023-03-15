package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface Usermapper extends BaseMapper<user> {

}
