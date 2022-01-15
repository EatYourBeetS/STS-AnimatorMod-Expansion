import basemod.BaseMod;
import basemod.interfaces.PreStartGameSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import eatyourbeets.resources.BetaResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class ModInitializer implements PreStartGameSubscriber
{
    private static final ModInitializer instance = new ModInitializer();

    public static void initialize()
    {
        BaseMod.subscribe(instance);
        BetaResources.Initialize();
    }

    @Override
    public void receivePreStartGame()
    {
        //TODO
    }
}