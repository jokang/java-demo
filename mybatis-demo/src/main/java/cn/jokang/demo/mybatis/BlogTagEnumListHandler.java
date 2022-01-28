package cn.jokang.demo.mybatis;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MyBatis的枚举字段列表配置
 * <p>
 * https://www.jianshu.com/p/8e0a2d06892c
 *
 * @author zhoukang
 * @date 2019-10-23
 */
public class BlogTagEnumListHandler extends BaseTypeHandler<List<BlogTagEnum>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, List<BlogTagEnum> enumLst,
                                    JdbcType jdbcType) throws SQLException {
        List<Integer> values = enumLst.stream().map(BlogTagEnum::getValue).collect(Collectors.toList());
        preparedStatement.setString(columnIndex, Joiner.on(",").join(values));
    }

    @Override
    public List<BlogTagEnum> getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String val = resultSet.getString(columnName);
        // resultSet.getInt 在数据库里字段为NULL的情况下回返回0!!!
        // 而且wasNull方法需要在getInt之后调用才有效...
        if (resultSet.wasNull()) {
            return null;
        }

        return Splitter.on(",").splitToList(val).stream()
            .map(x -> BlogTagEnum.fromValue(Integer.parseInt(x)))
            .collect(Collectors.toList());
    }

    @Override
    public List<BlogTagEnum> getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String val = resultSet.getString(columnIndex);
        // resultSet.getInt 在数据库里字段为NULL的情况下回返回0!!!
        // 而且wasNull方法需要在getInt之后调用才有效...
        if (resultSet.wasNull()) {
            return null;
        }
        return Splitter.on(",").splitToList(val).stream()
            .map(x -> BlogTagEnum.fromValue(Integer.parseInt(x)))
            .collect(Collectors.toList());
    }

    @Override
    public List<BlogTagEnum> getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String val = callableStatement.getString(columnIndex);
        // resultSet.getInt 在数据库里字段为NULL的情况下回返回0!!!
        // 而且wasNull方法需要在getInt之后调用才有效...
        if (callableStatement.wasNull()) {
            return null;
        }
        return Splitter.on(",").splitToList(val).stream()
            .map(x -> BlogTagEnum.fromValue(Integer.parseInt(x)))
            .collect(Collectors.toList());

    }
}
