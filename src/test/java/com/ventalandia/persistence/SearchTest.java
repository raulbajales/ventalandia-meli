package com.ventalandia.persistence;

import static org.junit.Assert.*;


import java.util.Date;

import org.junit.Test;

import com.google.appengine.api.search.Query;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.SearchService;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchException;
import com.google.appengine.api.search.StatusCode;
import com.google.appengine.api.search.Cursor;
import com.google.appengine.api.search.Query;
import com.google.appengine.api.search.QueryOptions;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.SearchException;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SortExpression;
import com.google.appengine.api.search.SortOptions;
import com.ventalandia.domain.DomainTest;

public class SearchTest extends DomainTest {

    @Test
    public void test() {
        Index index = this.getIndex();
        System.out.println(index.getName());

        index.add(createDoc("all iphones goes to hell", "juan"));
        index.add(createDoc("all androids goes to heaven", "juan"));
        index.add(createDoc("all androids goes to heaven", "pedro"));
        
        try {
//            Results<ScoredDocument> results = findDocuments("android", 10, Cursor.newBuilder().build());
            Results<ScoredDocument> results = index.search("buyer:juan");
            for (ScoredDocument document : results) {
                System.out.println(document);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    private Document createDoc(String content, String buyer) {
        Document doc = Document.newBuilder()
                .addField(Field.newBuilder().setName("content").setText(content))
                .addField(Field.newBuilder().setName("buyer").setAtom(buyer))
                .addField(Field.newBuilder().setName("date").setDate(Field.date(new Date())))
                .build();
        return doc;
    }

    public Index getIndex() {
        SearchService searchService = SearchServiceFactory.getSearchService();
        IndexSpec indexSpec = IndexSpec.newBuilder().setName("test_index").build();
        return searchService.getIndex(indexSpec);
    }
    
    public Results<ScoredDocument> findDocuments(String queryString, int limit, Cursor cursor) {
        try {
            SortOptions sortOptions = SortOptions.newBuilder()
                .addSortExpression(SortExpression.newBuilder()
                    .setExpression("buyer")
                    .setDirection(SortExpression.SortDirection.DESCENDING)
                    .setDefaultValue(""))
                .setLimit(1000)
                .build();
            
            QueryOptions options = QueryOptions.newBuilder()
                .setLimit(limit)
                .setFieldsToSnippet("content")
                .setFieldsToReturn("buyer", "content", "date")
                .setSortOptions(sortOptions)
                .setCursor(cursor)
                .build();
            Query query = Query.newBuilder().setOptions(options).build(queryString);
            return getIndex().search(query);
        } catch (SearchException e) {
            e.printStackTrace();
            return null;
        }
    }

}
