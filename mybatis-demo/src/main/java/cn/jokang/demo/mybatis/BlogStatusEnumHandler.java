package cn.jokang.demo.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MyBatis的枚举字段配置
 * <p>
 * https://www.jianshu.com/p/8e0a2d06892c
 *
 * @author zhoukang
 * @date 2019-10-23
 */
public class BlogStatusEnumHandler extends BaseTypeHandler<BlogStatusEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, BlogStatusEnum enumObj,
                                    JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(columnIndex, enumObj.getValue());
    }

    @Override
    public BlogStatusEnum getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        int val = resultSet.getInt(columnName);
        // resultSet.getInt 在数据库里字段为NULL的情况下回返回0!!!
        // 而且wasNull方法需要在getInt之后调用才有效...
        if (resultSet.wasNull()) {
            return null;
        }
        return BlogStatusEnum.fromValue(val);
    }

    @Override
    public BlogStatusEnum getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        int val = resultSet.getInt(columnIndex);
        // resultSet.getInt 在数据库里字段为NULL的情况下回返回0!!!
        // 而且wasNull方法需要在getInt之后调用才有效...
        if (resultSet.wasNull()) {
            return null;
        }
        return BlogStatusEnum.fromValue(val);
    }

    @Override
    public BlogStatusEnum getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        int val = callableStatement.getInt(columnIndex);
        // resultSet.getInt 在数据库里字段为NULL的情况下回返回0!!!
        // 而且wasNull方法需要在getInt之后调用才有效...
        if (callableStatement.wasNull()) {
            return null;
        }
        return BlogStatusEnum.fromValue(val);

    }
}
