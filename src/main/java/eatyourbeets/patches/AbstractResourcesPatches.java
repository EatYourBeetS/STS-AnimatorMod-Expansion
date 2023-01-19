package eatyourbeets.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import eatyourbeets.resources.AbstractResources;
import eatyourbeets.resources.GR;
import eatyourbeets.resources.animator.AnimatorResources;
import eatyourbeets.resources.animatorClassic.AnimatorClassicResources;
import eatyourbeets.utilities.BetaJUtils;

import java.util.ArrayList;

public class AbstractResourcesPatches
{
    public static final String CARD_FILE_BETA = "CardStringsBeta.json";
    private static final ReflectionHacks.RMethod LOAD_CUSTOM_CARDS = ReflectionHacks.privateMethod(GR.class, "LoadCustomCards", String.class, ArrayList.class);
    private static final ReflectionHacks.RMethod LOAD_CARD_STRINGS = ReflectionHacks.privateMethod(AbstractResources.class, "LoadCardStrings", String.class);

    @SpirePatch(clz = AbstractResources.class, method = "LoadCustomCards")
    public static class AbstractResources_LoadCustomCards
    {
        @SpirePostfixPatch
        public static void Postfix(AbstractResources __instance)
        {
            LOAD_CUSTOM_CARDS.invoke(__instance, __instance.Prefix, BetaJUtils.GetClassNamesFromJarFile(GR.CARD_PREFIX));
        }
    }

    @SpirePatch(clz = AnimatorResources.class, method = "InitializeStrings")
    public static class AnimatorResources_InitializeStrings
    {
        @SpirePostfixPatch
        public static void Postfix(AnimatorResources __instance)
        {
            LOAD_CARD_STRINGS.invoke(__instance, CARD_FILE_BETA);
        }
    }

    @SpirePatch(clz = AnimatorClassicResources.class, method = "InitializeStrings")
    public static class AnimatorClassicResources_InitializeStrings
    {
        @SpirePostfixPatch
        public static void Postfix(AnimatorClassicResources __instance)
        {
            LOAD_CARD_STRINGS.invoke(__instance, CARD_FILE_BETA);
        }
    }
}
