package dao;

import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;
import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }
    public void insert(String tagName, int id) {
        if(dsl.selectFrom(TAGS).where(TAGS.TAG.eq(tagName)).and(TAGS.ID.eq(id)).fetch().isEmpty())
            dsl.insertInto(TAGS, TAGS.ID, TAGS.TAG).values(id,tagName).execute();
        else
            dsl.delete(TAGS).where(TAGS.TAG.eq(tagName)).and(TAGS.ID.eq(id)).execute();
    }

    public List<ReceiptsRecord> getTaggedReceipts(String tagName) {
        return dsl.selectFrom(RECEIPTS).
                where(RECEIPTS.ID.in(dsl.select(TAGS.ID).from(TAGS).where(TAGS.TAG.eq(tagName))))
                .fetch();
    }
}
