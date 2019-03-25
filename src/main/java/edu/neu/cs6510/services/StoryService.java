package edu.neu.cs6510.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.neu.cs6510.model.Story;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StoryService {

    private static String INDEX = "stories";
    private static String TYPE = "story";

    public Story createStory(Story story, JestClient client){
        Story storyToAdd = new Story();
        storyToAdd.setId(story.getId());
        storyToAdd.setBody(story.getBody());
        storyToAdd.setTitle(story.getTitle());
        storyToAdd.setTags(story.getTags());
        storyToAdd.setAuthor(story.getAuthor());
        storyToAdd.setVotes(story.getVotes());
        Index index = new Index.Builder(storyToAdd).index(INDEX).type(TYPE).build();
        try {
            client.execute(index);
            return story;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Story updateStory(Story story, JestClient client){
        Index index = new Index.Builder(story).index(INDEX).type(TYPE).build();
        try {
            client.execute(index);
            return story;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Story> getAll(JestClient client) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(INDEX)
                .addType(TYPE)
                .build();
        SearchResult result = null;
        try {
            result = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getStoryFromJsonObject(result.getJsonObject());
    }

    public List<Story> getById(String id, JestClient client) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.idsQuery(TYPE).addIds(id));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(INDEX)
                .addType(TYPE)
                .build();
        SearchResult result = null;
        try {
            result = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getStoryFromJsonObject(result.getJsonObject());
    }

    public List<Story> getStoryFromJsonObject(JsonObject jsonObject) {
        List<Story> stories = new ArrayList<Story>();
        JsonArray results = jsonObject.getAsJsonObject("hits").getAsJsonArray("hits");
        for(JsonElement jsonElement : results) {
            JsonObject jsonObject1 = jsonElement.getAsJsonObject().getAsJsonObject("_source");
            Story story = new Story();
            story.setId(jsonObject1.get("id").toString());
            story.setAuthor(jsonObject1.get("author").toString());
            story.setBody(jsonObject1.get("body").toString());
            story.setTags(Arrays.asList(jsonObject1.get("tags").toString().split(",")));
            story.setTitle(jsonObject1.get("title").toString());
            story.setVotes(Integer.parseInt(jsonObject1.get("votes").toString()));
            stories.add(story);
        }
        return stories;
    }

}
