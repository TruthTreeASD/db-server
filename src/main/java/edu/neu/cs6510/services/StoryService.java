package edu.neu.cs6510.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.neu.cs6510.enums.EMessageType;
import edu.neu.cs6510.enums.VoteType;
import edu.neu.cs6510.exception.NoSuchStoryException;
import edu.neu.cs6510.util.Page;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import edu.neu.cs6510.util.sqs.MessageWapper;
import edu.neu.cs6510.util.sqs.SQSUtil;
import io.searchbox.core.*;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import edu.neu.cs6510.model.Story;
import edu.neu.cs6510.util.GsonUtil;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.search.sort.Sort;


@Service
public class StoryService {

	@Autowired
	JestClient client;

	private static String INDEX = "stories";
	private static String TYPE = "story";

	public List<Story> createStory(Story story) {
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

	public Story updateStory(Story story) {
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

	public Page<Story> getAllPage(Integer pageSize,Integer currentPage) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery())
				.from((currentPage - 1) * pageSize)
				.size(pageSize);
		return searchPage(searchSourceBuilder, pageSize, currentPage);
	}

	public List<Story> getAllApproved() {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("isApproved", "true"));
		searchSourceBuilder.sort("timestamp", SortOrder.DESC);
		return search(searchSourceBuilder);
	}

	public Page<Story> getAllApprovedPage(Integer pageSize,Integer currentPage) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("isApproved", "true"))
				.from((currentPage - 1) * pageSize)
				.size(pageSize);;
		searchSourceBuilder.sort("timestamp", SortOrder.DESC);
		return searchPage(searchSourceBuilder, pageSize, currentPage);
	}

	public List<Story> getAllPending() {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("isApproved", "false"));
		searchSourceBuilder.sort("timestamp", SortOrder.DESC);
		return search(searchSourceBuilder);
	}

	public Page<Story> getAllPendingPage(Integer pageSize,Integer currentPage) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("isApproved", "false"))
				.from((currentPage - 1) * pageSize)
				.size(pageSize);
		searchSourceBuilder.sort("timestamp", SortOrder.DESC);
		return searchPage(searchSourceBuilder, pageSize, currentPage);
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

		System.out.println(search.toString());
		return getStoryFromJsonObject(result.getJsonObject());
	}

	private Page<Story> searchPage(SearchSourceBuilder searchSourceBuilder, Integer pageSize, Integer currentPage) {
		Search search = new Search.Builder(searchSourceBuilder.toString())
				// multiple index or types can be added.
				.addIndex(INDEX)
				.addType(TYPE)
				.build();
		SearchResult result = null;
		result = (SearchResult) execute(search);
		Page<Story> page = new Page<Story>( getStoryFromJsonObject(result.getJsonObject()), result.getTotal(),currentPage, pageSize);
		return page;
	}

	public List<Story> searchByFieldandOrder(String fieldname, String order) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		Sort sort = null;
		if (order == null || order.toLowerCase().equals("asc")) {
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

	public Page<Story> searchByFieldandOrderPage(String fieldname, String order,Integer pageSize,Integer currentPage) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.from((currentPage - 1) * pageSize)
				.size(pageSize);
		Sort sort = null;
		if (order == null || order.toLowerCase().equals("asc")) {
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
		Page<Story> page = new Page<Story>( getStoryFromJsonObject(result.getJsonObject()), result.getTotal(),currentPage, pageSize);
		return page;

	}

	public List<Story> getStoryFromJsonObject(JsonObject jsonObject) {
		List<Story> stories = new ArrayList<Story>();
		JsonArray results = jsonObject.getAsJsonObject("hits").getAsJsonArray("hits");
		for (JsonElement jsonElement : results) {
			JsonObject jsonObject1 = jsonElement.getAsJsonObject().getAsJsonObject("_source");
			Story story = GsonUtil.fromJson(jsonObject1.toString(), Story.class);
			stories.add(story);
		}
		return stories;
	}

	public ResponseMessage updateVoteToQueue(String id, VoteType voteType){
		MessageWapper messageWapper = new MessageWapper(EMessageType.valueOf(voteType.toString()), id);
		SQSUtil.sendMessage(GsonUtil.t2Json(messageWapper));
		return Result.success();
	}


	public List<Story> setApproved(String id){

		String script = "{\n" +
				"    \"script\" : \"ctx._source.isApproved = true \""  + "\n" +
				"}";
		execute(new Update.Builder(script).index(INDEX).type(TYPE).id(id).build());
		return getById(id);
	}


	public List<Story> searchByKeyword(String keyword) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders
                .multiMatchQuery(keyword, "content", "title", "author", "tags")
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

  public List<Story> searchByKeyword(String keyword, List<String> feilds) {
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    if (feilds.isEmpty()) {
      feilds.addAll(Arrays.asList("content", "title", "author", "tags"));
    }
    searchSourceBuilder.query(QueryBuilders
            .multiMatchQuery(keyword, feilds.toArray(new String[feilds.size()]))
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

	public Page<Story> searchByKeywordPage(String keyword,Integer pageSize,Integer currentPage) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders
				.multiMatchQuery(keyword, "content", "title", "author", "tags")
				.fuzziness(Fuzziness.AUTO));
		Search search = new Search.Builder(searchSourceBuilder.toString())
				// multiple index or types can be added.
				.addIndex(INDEX)
				.addType(TYPE)
				.build();

		SearchResult result = null;
		result = (SearchResult) execute(search);
		Page<Story> page = new Page<Story>( getStoryFromJsonObject(result.getJsonObject()), result.getTotal(),currentPage, pageSize);
		return page;
	}

	private JestResult execute(Action action) {
		JestResult result = null;
		try {
			result = client.execute(action);
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



	public List<Story> updateVote(String id, EMessageType eMessageType){
		Story stroy = null;
		try {
			stroy = getById(id).get(0);
		} catch (IndexOutOfBoundsException outofBoundsException) {
			throw new NoSuchStoryException("Cannot find this story!");
		}

		long value = eMessageType == eMessageType.UPVOTE ?
				stroy.getUpvote() + 1 :
				(eMessageType == eMessageType.DOWNVOTE ? (stroy.getDownvote() + 1) : (stroy.getFreq() + 1));

		String script = "{\n" +
				"    \"script\" : \"ctx._source." + eMessageType.getField() + " = " + value + "\""  + "\n" +
				"}";
		execute(new Update.Builder(script).index(INDEX).type(TYPE).id(id).build());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return getById(id);
	}

}
