package registrar.api;

import com.vk.api.sdk.client.actors.UserActor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static registrar.Constants.APPLICATION_ID;
import static registrar.Constants.LOGIN;
import static registrar.Constants.PASSWORD;
import static registrar.Constants.SCOPE;
import static registrar.Constants.USER_ID;

public class VitalRadioRegistrar {
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void register(String registrationDate) {
        String token = getToken();

        UserActor userActor = new UserActor(USER_ID, token);
        RegistrationTask task = new RegistrationTask(userActor);


        Date date = convertDate(registrationDate);
        scheduler.schedule(task, date.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    private String getToken() {
        try {
            return VkTokenGetter.getAccessToken(APPLICATION_ID, SCOPE, LOGIN, PASSWORD);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Date convertDate(String registrationDate) {
        try {
            return format.parse(registrationDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Can not convert date from " + registrationDate);
        }
    }

}
