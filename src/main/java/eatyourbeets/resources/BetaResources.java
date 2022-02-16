package eatyourbeets.resources;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardMetadata;
import eatyourbeets.interfaces.delegates.ActionT2;
import eatyourbeets.resources.animator.AnimatorStrings;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;
import eatyourbeets.resources.animatorbeta.loadouts.Loadout_Bleach;
import eatyourbeets.resources.animatorbeta.loadouts.Loadout_Rewrite;
import eatyourbeets.utilities.JUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class BetaResources extends AbstractResources
{
    public static class Enums {
        public static class CardTags {
            @SpireEnum
            public static com.megacrit.cardcrawl.cards.AbstractCard.CardTags AUTOPLAY;
            @SpireEnum
            public static com.megacrit.cardcrawl.cards.AbstractCard.CardTags HARMONIC;
        }
    }

    private static final ArrayList<String> classNames = new ArrayList();
    public static BetaResources Beta;
    public static final String BASE_PREFIX = "animatorbeta";
    protected static final ArrayList<String> betaCardClassNames = GetClassNamesFromJarFile("eatyourbeets.cards.");
    protected static final ArrayList<String> betaRelicClassNames = GetClassNamesFromJarFile("eatyourbeets.relics.");
    protected static final ArrayList<String> betaPowerClassNames = GetClassNamesFromJarFile("eatyourbeets.powers.");

    public static boolean IsLoaded() {
        return Beta != null && Beta.isLoaded;
    }

    public final BetaStrings Strings = new BetaStrings();

    public BetaResources() {
        super(BASE_PREFIX, GR.Enums.Cards.THE_ANIMATOR, GR.Enums.Characters.THE_ANIMATOR);
    }

    public static boolean IsTranslationSupported(Settings.GameLanguage language)
    {
        //This should be set only for beta branches.  Do not merge this into master.
        return false;//language == Settings.GameLanguage.RUS; // language == Settings.GameLanguage.ZHS || language == Settings.GameLanguage.ZHT;
    }


    public static void Initialize()
    {
        Beta = new BetaResources();
        Beta.InitializeInternal();
        Beta.InitializeColor();
        BaseMod.subscribe(Beta);
    }

    @Override
    protected void InitializeKeywords() {
        if (!this.isLoaded) {
            JUtils.LogInfo(this, "InitializeKeywords();");
            LoadKeywords(GR.Enums.Cards.THE_ANIMATOR);
        }
    }

    @Override
    protected void InitializeRelics() {
        if (!this.isLoaded) {
            JUtils.LogInfo(this, "InitializeRelics();");
            LoadCustomRelics();
        }
    }

    @Override
    protected void InitializeStrings() {
        if (!this.isLoaded) {
            JUtils.LogInfo(this, "InitializeStrings();");
            String json = this.GetFallbackFile("CardStrings.json").readString(StandardCharsets.UTF_8.name());
            LoadGroupedCardStrings(this.ProcessJson(json, true));
            if (this.testFolder.isDirectory() || IsTranslationSupported(Settings.language)) {
                FileHandle file = this.GetFile(Settings.language, "CardStrings.json");
                if (file.exists()) {
                    String json2 = file.readString(StandardCharsets.UTF_8.name());
                    LoadGroupedCardStrings(this.ProcessJson(json2, false));
                }
            }

            String jsonString = new String(Gdx.files.internal("Animator-CardMetadata.json").readBytes());
            // Combine the two card data databases so that we have one unified cardData database
            // Honor the original cards if they already exist for some reason
            Map<String, EYBCardMetadata> dataToAdd = (new Gson()).fromJson(jsonString, (new TypeToken<Map<String, EYBCardMetadata>>() {
            }).getType());
            dataToAdd.forEach((key, value) -> GR.Animator.CardData.merge(key, value, (v1, v2) -> v1));
            this.LoadCustomStrings(RelicStrings.class);
            this.LoadCustomStrings(PowerStrings.class);
        }
    }

    @Override
    protected void InitializeCards()
    {
        if (!this.isLoaded) {
            JUtils.LogInfo(this, "InitializeCards();");
            this.LoadCustomCards(this.prefix);
            // We need to post initialize the beta cards since those won't be caught with AnimatorResources
            EYBCardData.PostInitialize();
        }

    }

    // Translation support is handled separately for beta cards
    @Override
    public FileHandle GetFile(Settings.GameLanguage language, String fileName) {
        if (this.IsBetaTranslation() && (new File(this.testFolder.path() + "/" + fileName)).isFile()) {
            return Gdx.files.internal(this.testFolder.path() + "/" + fileName);
        } else {
            if (!IsTranslationSupported(language)) {
                language = Settings.GameLanguage.ENG;
            }

            return Gdx.files.internal("localization/" + this.prefix.toLowerCase() + "/" + language.name().toLowerCase() + "/" + fileName);
        }
    }

    @Override
    protected void LoadKeywords(AbstractCard.CardColor requiredColor) {
        super.LoadKeywords(this.GetFallbackFile("KeywordStrings.json"), requiredColor);
        if (this.IsBetaTranslation() || IsTranslationSupported(Settings.language)) {
            super.LoadKeywords(this.GetFile(Settings.language, "KeywordStrings.json"), requiredColor);
        }

    }

    @Override
    protected void LoadCustomStrings(Class<?> type) {
        super.LoadCustomStrings(type, this.GetFallbackFile(type.getSimpleName() + ".json"));
        if (this.IsBetaTranslation() || IsTranslationSupported(Settings.language)) {
            super.LoadCustomStrings(type, this.GetFile(Settings.language, type.getSimpleName() + ".json"));
        }

    }

    @Override
    protected void LoadCustomRelics()
    {
        String prefix = "eatyourbeets.relics." + BASE_PREFIX;
        for (String s : betaRelicClassNames)
        {
            if (s.startsWith(prefix))
            {
                try
                {
                    logger.info("Adding: " + s);

                    LoadCustomRelic(Class.forName(s), GR.Enums.Cards.THE_ANIMATOR);
                }
                catch (ClassNotFoundException e)
                {
                    logger.warn("Class not found : " + s);
                }
            }
        }
    }

    @Override
    protected void LoadCustomCards(String character) {
        String prefix = "eatyourbeets.cards." + character;

        for(String s : betaCardClassNames) {
            if (s.startsWith(prefix)) {
                try {
                    logger.info("Adding: " + s);
                    this.LoadCustomCard(Class.forName(s));
                } catch (ClassNotFoundException var6) {
                    logger.warn("Class not found : " + s);
                }
            }
        }

    }

    @Override
    protected void LoadCustomPowers(String character) {
        String prefix = "eatyourbeets.cards." + character;

        for(String s : betaCardClassNames) {
            if (s.startsWith(prefix)) {
                try {
                    logger.info("Adding: " + s);
                    Class<?> type = Class.forName(s);
                    if (CanInstantiate(type)) {
                        BaseMod.addPower((Class<AbstractPower>) type, CreateID(character, type.getSimpleName()));
                    }
                } catch (ClassNotFoundException var6) {
                    logger.warn("Class not found : " + s);
                }
            }
        }

    }

    public String ProcessJson(String originalString, boolean useFallback) {
        return GR.Animator.ProcessJson(originalString, useFallback);
    }

    protected static ArrayList<String> GetClassNamesFromJarFile(String prefix) {
        if (classNames.size() == 0) {
            try {
                String path = BetaResources.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                JarInputStream jarFile = new JarInputStream(new FileInputStream(path));

                while(true) {
                    JarEntry entry = jarFile.getNextJarEntry();
                    if (entry == null) {
                        break;
                    }

                    String name = entry.getName();
                    if (name.endsWith(".class") && name.indexOf(36) == -1) {
                        classNames.add(name.replaceAll("/", "\\."));
                    }
                }
            } catch (URISyntaxException | IOException var5) {
                throw new RuntimeException(var5);
            }
        }

        ArrayList<String> result = new ArrayList();
        Iterator var7 = classNames.iterator();

        while(var7.hasNext()) {
            String entry = (String)var7.next();
            if (entry.startsWith(prefix)) {
                result.add(entry.substring(0, entry.lastIndexOf(46)));
            }
        }

        return result;
    }
}