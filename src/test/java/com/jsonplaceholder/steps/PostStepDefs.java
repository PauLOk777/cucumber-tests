package com.jsonplaceholder.steps;

import com.jsonplaceholder.config.Config;
import com.jsonplaceholder.endpoints.PostsEndPoint;
import com.jsonplaceholder.models.Post;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.jsonplaceholder.config.Config.BASE_URI;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostStepDefs {

    private Response response;
    private long id;
    private Post post;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @Given("post id is {long}")
    public void givenPostId(long id) {
        this.id = id;
    }

    @Given("post with next data:")
    public void givenPost(DataTable dataTable) {
        post = convertDataTableToListOfPosts(dataTable).get(0);
    }

    @When("I try to get post by id")
    public void getPostById() {
        response = PostsEndPoint.getPostById(id);
    }

    @When("I try to create new post")
    public void createNewPost() {
        response = PostsEndPoint.createPost(post);
    }

    @When("I try to modify post")
    public void modifyPost() {
        response = PostsEndPoint.modifyPost(post);
    }

    @When("I try to delete post")
    public void deletePost() {
        response = PostsEndPoint.deletePost(id);
    }

    @Then("I receive status code {int}")
    public void compareStatusCodes(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("I receive post with id {int}")
    public void checkThatIdsIsEqual(int id) {
        response.then().body(Config.ID, equalTo(id));
    }

    private List<Post> convertDataTableToListOfPosts(DataTable table) {
        return table.asMaps(Object.class, Object.class).stream()
                .map(this::mapToPost)
                .collect(Collectors.toList());
    }

    private Post mapToPost(Map<Object, Object> map) {
        if (map.containsKey(Config.ID)) {
            return new Post(Long.parseLong((String) map.get(Config.USER_ID)),
                    Long.parseLong((String) map.get(Config.ID)),
                    (String) map.get(Config.TITLE), (String) map.get(Config.BODY));
        } else {
            return new Post(Long.parseLong((String) map.get(Config.USER_ID)), (String) map.get(Config.TITLE),
                    (String) map.get(Config.BODY));
        }
    }
    
}
