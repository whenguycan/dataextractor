package com.kaltsit.dataextractor;

import com.kaltsit.dataextractor.extractor.ColumnValueConverter;
import com.kaltsit.dataextractor.extractor.SqlBuilder;

import javax.sql.DataSource;
import java.io.File;

/**
 * @author wangcy
 * @date 2021/11/10 15:27
 */
public class ExtractorBuilder {

    private Extractor extractor = new Extractor();

    public ExtractorBuilder setDir(File targetDir) {
        extractor.targetDir = targetDir;
        return this;
    }

    public ExtractorBuilder setDataSource(DataSource dataSource) {
        extractor.dataSource = dataSource;
        return this;
    }

    public ExtractorBuilder setSqlBuilder(SqlBuilder sqlBuilder) {
        extractor.sqlBuilder = sqlBuilder;
        return this;
    }

    public ExtractorBuilder addConverter(ColumnValueConverter converter) {
        extractor.converters.add(converter);
        return this;
    }

    public Extractor build() {
        return extractor;
    }

}
