package cn.jokang.demos.dbcp.druid;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.builder.SQLBuilderFactory;
import com.alibaba.druid.sql.builder.SQLSelectBuilder;
import com.alibaba.druid.util.JdbcConstants;
import org.junit.Test;

import java.util.List;

/**
 * @author zhoukang04
 * @date 2023/4/3
 */
public class SimpleDruidTests {
    @Test
    public void simpleTest() {
        final DbType dbType = JdbcConstants.MYSQL; // 可以是ORACLE、POSTGRESQL、SQLSERVER、ODPS等
        String sql = "select a+b as c from t join t2";
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
        System.out.println(SQLUtils.toSQLString(stmtList, dbType));

        System.out.println(SQLUtils.toSQLString(SQLUtils.toMySqlExpr("a=b")));
        SQLSelectStatement sqlStatement = SQLSelectStatement.class.cast(stmtList.get(0));
        SQLSelect aSelect = sqlStatement.getSelect();

        SQLSelectBuilder builder = SQLBuilderFactory.createSelectSQLBuilder(dbType);
        builder.select("a").from("t1");

    }
}
