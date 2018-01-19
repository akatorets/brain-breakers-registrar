package registrar.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.queries.newsfeed.NewsfeedGetFilter;
import registrar.domain.Post;

/**
 * Performs operations with vk API.
 */
public class VkApiUser {
    private VkApiClient vkApiClient = new VkApiClient(HttpTransportClient.getInstance());
    private JsonParser jsonParser = new JsonParser();

    /**
     * Return last post from specified source.
     * @param userActor the user on whose behalf the request is made
     * @param sourceId source
     * @return the top post
     * @throws ClientException on vk API client error
     */
    public Post getTopPost(UserActor userActor, String sourceId) throws ClientException {
        String newsResponse = vkApiClient.newsfeed()
                .get(userActor)
                .sourceIds(sourceId)
                .filters(NewsfeedGetFilter.POST)
                .count(1)
                .executeAsString();

        return convertFromJson(newsResponse);
    }

    /**
     * Send a comment under the post.
     * @param userActor the user on whose behalf the comment is posted
     * @param post the post under which you need to leave a comment
     * @param message comment message
     * @throws ClientException on vk API client error
     * @throws ApiException on vk API error
     */
    public void createComment(UserActor userActor, Post post, String message) throws ClientException, ApiException {
        vkApiClient.wall()
                .createComment(userActor, post.getPostId())
                .ownerId(post.getSourceId())
                .message(message)
                .execute();
    }

    /**
     * Send a message.
     * @param userActor the user on whose behalf the message is sent
     * @param peerId the destination user id
     * @param message message
     * @throws ClientException on vk API client error
     * @throws ApiException on vk API error
     */
    public void sendMessage(UserActor userActor, Integer peerId, String message) throws ClientException, ApiException {
        vkApiClient.messages()
                .send(userActor)
                .peerId(peerId)
                .message(message)
                .execute();
    }

    private Post convertFromJson(String responseJson) {
        JsonObject response = jsonParser.parse(responseJson).getAsJsonObject().getAsJsonObject("response");
        JsonArray items = response.getAsJsonArray("items");
        return new Gson().fromJson(items.iterator().next(), Post.class);
    }

}
