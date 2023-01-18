import basemod.BaseMod;
import basemod.interfaces.PreStartGameSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class ModInitializer implements PreStartGameSubscriber
{
    private static final ModInitializer instance = new ModInitializer();

    public static void initialize()
    {
        BaseMod.subscribe(instance);
    }

    @Override
    public void receivePreStartGame()
    {
        //TODO
    }
}