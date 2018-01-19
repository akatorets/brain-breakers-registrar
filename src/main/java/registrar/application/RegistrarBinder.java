package registrar.application;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import registrar.api.VitalRadioRegistrar;

import javax.inject.Singleton;

public class RegistrarBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(VitalRadioRegistrar.class)
                .to(VitalRadioRegistrar.class)
                .in(Singleton.class);
    }
}
