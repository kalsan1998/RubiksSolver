package rubiks.drools;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by Kaan on 2017-07-29.
 */
public abstract class KieSessionMaker {
    private static KieServices kieServices = KieServices.Factory.get();
    private static KieContainer kieContainer = kieServices.getKieClasspathContainer();
    private static KieBase kieBase = kieContainer.getKieBase("solver");

    public static KieSession getKieSession(){

        return kieBase.newKieSession();
    }
}