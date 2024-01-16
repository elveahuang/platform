package cn.elvea.platform.commons.core.data.core.dialect;

import org.springframework.data.relational.core.dialect.Dialect;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface DbDialect extends Dialect {

    TemporaryTableClause temporaryTable();

    DateTimeClause dateTime();

}
