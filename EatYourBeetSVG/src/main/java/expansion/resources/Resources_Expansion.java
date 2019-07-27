package expansion.resources;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import eatyourbeets.resources.AbstractResources;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Resources_Expansion extends AbstractResources
{
    private static String languagePath = null;

    public static void Initialize()
    {
        Resources_Expansion resources = new Resources_Expansion();
        resources.InitializeInternal();
        resources.InitializeColor();
        BaseMod.subscribe(resources);
    }

    private static void LoadLanguagePath()
    {
        if (languagePath != null)
        {
            return;
        }

//        if (Settings.language == Settings.GameLanguage.ZHT)
//        {
//            languagePath = "localization/animator/expansion/zht/";
//        }
//        else if (Settings.language == Settings.GameLanguage.ZHS)
//        {
//            languagePath = "localization/animator/expansion/zhs/";
//        }
//        else if (Settings.language == Settings.GameLanguage.FRA)
//        {
//            languagePath = "localization/animator/expansion/fra/";
//        }
//        else
//        {
            languagePath = "localization/animator/expansion/eng/";
//        }
    }

    @Override
    protected void InitializeCards()
    {
        String jsonString = Gdx.files.internal("localization/animator/expansion/eng/CardStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Map<String, CardStrings> map = (Map)(new Gson()).fromJson(jsonString, (new TypeToken<HashMap<String, CardStrings>>() {
        }).getType());
        Iterator var3 = map.keySet().iterator();

        while(var3.hasNext()) {
            String s = (String)var3.next();

            try {
                logger.info("Adding: " + s);
                LoadCard(Class.forName("expansion.cards.animator." + s.replace("animator:expansion:", "")));
            } catch (ClassNotFoundException var6) {
                logger.warn("Class not found : " + s);
            }
        }
    }

    @Override
    protected void InitializeStrings()
    {
        LoadLanguagePath();

        BaseMod.loadCustomStringsFile(CardStrings.class, languagePath + "CardStrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, languagePath + "PotionStrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, languagePath + "PowerStrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, languagePath + "RelicStrings.json");
    }
}