package edu.neu.cs6510.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.neu.cs6510.model.Story;
import edu.neu.cs6510.util.GsonUtil;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.core.search.sort.Sort;
import io.searchbox.indices.CreateIndex;


import org.elasticsearch.common.unit.Fuzziness;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoryService {


    @Autowired
    JestClient client;

    private static String INDEX = "stories";
    private static String TYPE = "story";

    public List<Story> createStory(Story story){
        Long timeStamp = System.currentTimeMillis();
        String id = story.getAuthor() + timeStamp.toString();
        story.setId(id);
        story.setTimestamp(System.currentTimeMillis());
        story.setApproved(false);
        Index index = new Index.Builder(story).index(INDEX).type(TYPE).build();
        JestResult result = execute(index);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getById(id);
    }

    public Story updateStory(Story story){
        Index index = new Index.Builder(story).index(INDEX).type(TYPE).build();
        execute(index);
        return story;
    }

    public List<Story> getAll() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.sort("timestamp", SortOrder.DESC);
        return search(searchSourceBuilder);
    }

    public List<Story> getById(String id) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.idsQuery(TYPE).addIds(id));
        return search(searchSourceBuilder);
    }

    private List<Story> search(SearchSourceBuilder searchSourceBuilder) {
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(INDEX)
                .addType(TYPE)
                .build();
        SearchResult result = null;
        result = (SearchResult) execute(search);
        return getStoryFromJsonObject(result.getJsonObject());
    }

    public List<Story> searchByFieldandOrder(String fieldname, String order) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        Sort sort = null;
        if(order == null || order.toLowerCase().equals("asc")){
            sort = new Sort(fieldname.toLowerCase(), Sort.Sorting.ASC);
        }
        else{
            sort = new Sort(fieldname.toLowerCase(), Sort.Sorting.DESC);
        }
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(INDEX)
                .addType(TYPE)
                .addSort(sort)
                .build();
        SearchResult result = null;
        result = (SearchResult) execute(search);
        return getStoryFromJsonObject(result.getJsonObject());

    }


    public List<Story> getStoryFromJsonObject(JsonObject jsonObject) {
        List<Story> stories = new ArrayList<Story>();
        JsonArray results = jsonObject.getAsJsonObject("hits").getAsJsonArray("hits");
        for(JsonElement jsonElement : results) {
            JsonObject jsonObject1 = jsonElement.getAsJsonObject().getAsJsonObject("_source");
            Story story = GsonUtil.fromJson(jsonObject1.toString(), Story.class);
            stories.add(story);
        }
        return stories;
    }

    public List<Story> updateVote(JestClient client, String id, String typeVote, int value){
        String script = "{\n" +
                "    \"script\" : \"ctx._source." + typeVote + " = " + value + "\""  + "\n" +
                "}";
        execute(new Update.Builder(script).index(INDEX).type(TYPE).id(id).build());
        return getById(id);
    }

    public List<Story> searchByKeyword(JestClient client, String keyword){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders
                .multiMatchQuery(keyword, "id", "content", "title", "author", "tags")
                .fuzziness(Fuzziness.AUTO));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(INDEX)
                .addType(TYPE)
                .build();

        SearchResult result = null;
        result = (SearchResult) execute(search);
        return getStoryFromJsonObject(result.getJsonObject());
    }
    
    private JestResult execute(Action action) {
        JestResult result = null;
        try {
            result =  client.execute(action);
        } catch (IOException e) {
            try {
                result = client.execute(action);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return result;
    }

}
