package eatyourbeets.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.*;
import com.megacrit.cardcrawl.cards.curses.*;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import eatyourbeets.cards.animator.colorless.rare.KotoriKanbe;
import eatyourbeets.cards.animator.colorless.uncommon.IchigoKurosaki;
import eatyourbeets.cards.animator.curse.common.*;
import eatyourbeets.cards.animator.curse.special.Curse_AscendersBane;
import eatyourbeets.cards.animator.special.*;
import eatyourbeets.cards.animator.status.*;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.delegates.ActionT2;
import eatyourbeets.resources.BetaResources;
import eatyourbeets.resources.GR;
import eatyourbeets.resources.animator.AnimatorDungeonData;
import eatyourbeets.resources.animator.AnimatorPlayerData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;
import eatyourbeets.resources.animator.misc.AnimatorRuntimeLoadout;
import eatyourbeets.resources.animatorbeta.loadouts.Loadout_Bleach;
import eatyourbeets.resources.animatorbeta.loadouts.Loadout_Rewrite;
import eatyourbeets.ui.animator.seriesSelection.AnimatorSeriesSelectScreen;
import eatyourbeets.utilities.GameUtilities;

import java.util.HashSet;

public class CardLibraryPatches
{
    // Replace duplicate colorless cards from the official mod only if beta series are enabled
    public static EYBCardData GetReplacement(String cardID) {
        if (IchigoKurosaki.DATA.ID.equals(cardID)) {
            return eatyourbeets.cards.animatorbeta.series.Bleach.IchigoKurosaki.DATA;
        }
        if (IchigoKurosaki_Bankai.DATA.ID.equals(cardID)) {
            return eatyourbeets.cards.animatorbeta.special.IchigoKurosaki_Bankai.DATA;
        }
        if (KotoriKanbe.DATA.ID.equals(cardID)) {
            return eatyourbeets.cards.animatorbeta.series.Rewrite.KotoriKanbe.DATA;
        }
        return null;
    }

    public static void TryReplace(AbstractCard[] card) {
        if (GameUtilities.IsPlayerClass(GR.Animator.PlayerClass)) {
            EYBCardData data = GetReplacement(card[0].cardID);
            if (data != null) {
                card[0] = data.MakeCopy(card[0].upgraded);
            }
        }
    }

    @SpirePatch(
            clz = CardLibrary.class,
            method = "getCard",
            paramtypez = {String.class}
    )
    public static class CardLibraryPatches_GetCard {
        public CardLibraryPatches_GetCard() {
        }

        @SpirePrefixPatch
        public static SpireReturn<AbstractCard> Prefix(String key) {
            if (BetaResources.IsLoaded() && GR.IsLoaded() && GR.Animator.Dungeon != null) {
                EYBCardData data = GetReplacement(key);
                if (data != null && GR.Animator.Dungeon.HasBetaSeries) {
                    return SpireReturn.Return(data.MakeCopy(false));
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = MakeTempCardInDiscardAction.class,
            method = "<ctor>",
            paramtypez = {AbstractCard.class, int.class}
    )
    public static class MakeTempCardInDiscardActionPatches_Ctor {
        public MakeTempCardInDiscardActionPatches_Ctor() {
        }

        @SpirePrefixPatch
        public static void Prefix(MakeTempCardInDiscardAction __instance, @ByRef AbstractCard[] card, int amount) {
            TryReplace(card);
        }
    }

    @SpirePatch(
            clz = MakeTempCardInDrawPileAction.class,
            method = "<ctor>",
            paramtypez = {AbstractCard.class, int.class, boolean.class, boolean.class, boolean.class, float.class, float.class}
    )
    public static class MakeTempCardInDrawPileActionPatches_Ctor {
        public MakeTempCardInDrawPileActionPatches_Ctor() {
        }

        @SpirePrefixPatch
        public static void Prefix(MakeTempCardInDrawPileAction __instance, @ByRef AbstractCard[] card, int amount, boolean randomSpot, boolean autoPosition, boolean toBottom, float cardX, float cardY) {
            TryReplace(card);
        }
    }

    @SpirePatch(
            clz = MakeTempCardInHandAction.class,
            method = "<ctor>",
            paramtypez = {AbstractCard.class, int.class}
    )
    public static class MakeTempCardInHandActionPatches_Ctor2 {
        public MakeTempCardInHandActionPatches_Ctor2() {
        }

        @SpirePrefixPatch
        public static void Prefix(MakeTempCardInHandAction __instance, @ByRef AbstractCard[] card, int amount) {
            TryReplace(card);
        }
    }

    @SpirePatch(
            clz = MakeTempCardInHandAction.class,
            method = "<ctor>",
            paramtypez = {AbstractCard.class, boolean.class}
    )
    public static class MakeTempCardInHandActionPatches_Ctor1 {
        public MakeTempCardInHandActionPatches_Ctor1() {
        }

        @SpirePrefixPatch
        public static void Prefix(MakeTempCardInHandAction __instance, @ByRef AbstractCard[] card, boolean isOtherCardInCenter) {
            TryReplace(card);
        }
    }
}
