package registrar.api;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import registrar.domain.Post;

import java.util.concurrent.TimeUnit;

import static registrar.Constants.GROUP_ID;
import static registrar.Constants.USER_ID;

public class RegistrationTask implements Runnable {

    private UserActor userActor;
    private VkApiUser vkApiUser = new VkApiUser();

    public RegistrationTask(UserActor userActor) {
        this.userActor = userActor;
    }

    @Override
    public void run() {
        try {
            Post topPost = vkApiUser.getTopPost(userActor, GROUP_ID);
            while (true) {
                Post newPost = vkApiUser.getTopPost(userActor, GROUP_ID);
                if (newPost.getDate().equals(topPost.getDate())) {
                    TimeUnit.SECONDS.sleep(2L);
                } else {
                    vkApiUser.createComment(userActor, newPost, "Vital Radio, 4-8");
                    vkApiUser.sendMessage(userActor, USER_ID, "Success");
                    break;
                }
            }
        } catch (ClientException | ApiException | InterruptedException e) {
            try {
                vkApiUser.sendMessage(userActor, USER_ID, e.getMessage());
            } catch (ClientException | ApiException ex) {
                ex.printStackTrace();
            }
        }
    }

}
