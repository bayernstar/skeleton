package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.TagsRecord;

public class TagResponse {
    @JsonProperty
    Integer id;

    @JsonProperty
    String tag;

    public TagResponse(TagsRecord dbRecord) {
        this.tag = dbRecord.getTag();
        this.id = dbRecord.getId();
    }
}
