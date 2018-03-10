package registrar.api;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.wall.WallComment;
import registrar.domain.Post;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static registrar.Constants.GROUP_ID;
import static registrar.Constants.USER_ID;

public class RegistrationTask implements Runnable {
    private static final String COMMENT_STATISTICS_TEMPLATE = "%d) %s (+%d)\r\n";
    private static final String POST_LINK_TEMPLATE = "https://vk.com/%s?w=wall%d_%d\r\n";

    private VkApiUser vkApiUser = new VkApiUser();
    private UserActor userActor;
    private String message;

    public RegistrationTask(UserActor userActor, String message) {
        this.userActor = userActor;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Post topPost = vkApiUser.getTopPost(userActor, GROUP_ID);
            Post newPost = null;
            while (true) {
                newPost = vkApiUser.getTopPost(userActor, GROUP_ID);
                if (newPost.getDate().equals(topPost.getDate())) {
                    TimeUnit.SECONDS.sleep(2L);
                } else {
                    vkApiUser.createComment(userActor, newPost, message);
                    break;
                }
            }
            TimeUnit.SECONDS.sleep(30);
            sendStatistics(newPost);
        } catch (ClientException | ApiException | InterruptedException e) {
            try {
                vkApiUser.sendMessage(userActor, USER_ID, e.getMessage());
            } catch (ClientException | ApiException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sendStatistics(Post post) throws ClientException, ApiException {
        vkApiUser.sendMessage(userActor, USER_ID, formatStatistics(post));
    }

    private String formatStatistics(Post post) throws ClientException, ApiException {
        List<WallComment> comments = vkApiUser.getComments(userActor, post, 10);

        StringBuilder builder = new StringBuilder();
        builder.append(createPostLink(post));
        for (int i = 0; i < comments.size(); i++) {
            WallComment comment = comments.get(i);
            int diff = comment.getDate() - post.getDate();
            builder.append(String.format(COMMENT_STATISTICS_TEMPLATE, i + 1, comment.getText(), diff));
        }
        return builder.toString();
    }

    private String createPostLink(Post post) throws ClientException, ApiException {
        String source = post.getSourceId().toString();
        GroupFull groupFull = vkApiUser.getGroup(userActor, source.startsWith("-") ? source.substring(1) : source);
        return String.format(POST_LINK_TEMPLATE, groupFull.getScreenName(), post.getSourceId(), post.getPostId());
    }
}
