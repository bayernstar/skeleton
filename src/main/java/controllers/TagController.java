package controllers;

import api.TagResponse;
import api.ReceiptResponse;
import dao.TagDao;
import generated.tables.records.TagsRecord;
import generated.tables.records.ReceiptsRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    @Path("/tags/{tag}")
    public void toggleTag(@PathParam("tag") String tagName, int id) {
        tags.insert(tagName, id);
        }

    @GET
    @Path("/tags/id/{id}")
    public List<TagResponse> getTags(@PathParam("id") int id) {
        List<TagsRecord> tagRecords = tags.getTags(id);
        return tagRecords.stream().map(TagResponse::new).collect(toList());
    }

    @GET
    @Path("/tags/{tag}")
    public List<ReceiptResponse> getReceipts(@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptRecords = tags.getTaggedReceipts(tagName);
        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());
    }
}
