package eatyourbeets.cards.base;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.*;
import org.apache.commons.lang3.StringUtils;

// TODO I changed the badge rendering logic to render the icon and badge separately
// TODO There are a few extra tags; feel free to remove the ones you don't need
public class BetaCardText extends EYBCardText
{
    protected void RenderBadges(SpriteBatch sb, boolean inHand)
    {
        final float alpha = UpdateBadgeAlpha();

        int offset_y = 0;
        if (card.hasTag(AFTERLIFE))
        {
            offset_y -= RenderBadge(sb, BADGES.Afterlife.Texture(), PCLColors.COLOR_AFTERLIFE, offset_y, alpha, null);
        }
        if (card.unplayable || PCLGameUtilities.IsUnplayableThisTurn(card))
        {
            offset_y -= RenderBadge(sb, BADGES.Unplayable.Texture(), PCLColors.COLOR_UNPLAYABLE, offset_y, alpha, null);
        }
        if (card.hasTag(GR.Enums.CardTags.DELAYED))
        {
            offset_y -= RenderBadge(sb, BADGES.Delayed.Texture(), PCLColors.COLOR_DELAYED, offset_y, alpha, null);
        }
        else if (card.isInnate || card.hasTag(GR.Enums.CardTags.PCL_INNATE))
        {
            offset_y -= RenderBadge(sb, BADGES.Innate.Texture(), PCLColors.COLOR_INNATE, offset_y, alpha, null);
        }
        if (card.hasTag(GR.Enums.CardTags.HARMONIC))
        {
            offset_y -= RenderBadge(sb, BADGES.Harmonic.Texture(), PCLColors.COLOR_HARMONIC, offset_y, alpha, null);
        }
        if (card.hasTag(GR.Enums.CardTags.LOYAL))
        {
            offset_y -= RenderBadge(sb, BADGES.Loyal.Texture(), PCLColors.COLOR_LOYAL, offset_y, alpha, null);
        }
        if (card.isEthereal)
        {
            offset_y -= RenderBadge(sb, BADGES.Ethereal.Texture(), PCLColors.COLOR_ETHEREAL, offset_y, alpha, null);
        }
        if (card.selfRetain)
        {
            offset_y -= RenderBadge(sb, BADGES.Retain.Texture(), PCLColors.COLOR_RETAIN, offset_y, alpha, null, true);
        }
        else if (card.retain)
        {
            offset_y -= RenderBadge(sb, BADGES.Retain.Texture(), PCLColors.COLOR_RETAIN, offset_y, alpha, null);
        }
        if (card.hasTag(GR.Enums.CardTags.HASTE_INFINITE))
        {
            offset_y -= RenderBadge(sb, BADGES.Haste.Texture(), PCLColors.COLOR_HASTE, offset_y, alpha, null, true);
        }
        else if (card.hasTag(GR.Enums.CardTags.HASTE))
        {
            offset_y -= RenderBadge(sb, BADGES.Haste.Texture(), PCLColors.COLOR_HASTE, offset_y, alpha, null);
        }

        if (card.purgeOnUse || card.hasTag(GR.Enums.CardTags.PURGE))
        {
            offset_y -= RenderBadge(sb, BADGES.Purge.Texture(), PCLColors.COLOR_PURGE, offset_y, alpha, null);
        }
        else if (card.exhaust || card.exhaustOnUseOnce)
        {
            offset_y -= RenderBadge(sb, BADGES.Exhaust.Texture(), PCLColors.COLOR_EXHAUST, offset_y, alpha, null);
        }
        if (card.hasTag(GR.Enums.CardTags.AUTOPLAY))
        {
            //noinspection UnusedAssignment
            offset_y -= RenderBadge(sb, BADGES.Autoplay.Texture(), PCLColors.COLOR_AUTOPLAY, offset_y, alpha, null);
        }

        // TODO everything beyond this point in the original function has not been changed
    }

    private float RenderBadge(SpriteBatch sb, Texture texture, Color color, float offset_y, float alpha, String text) {
        return RenderBadge(sb, texture, color, offset_y, alpha, text, false);
    }

    private float RenderBadge(SpriteBatch sb, Texture texture, Color color, float offset_y, float alpha, String text, boolean isInfinite)
    {
        Vector2 offset = new Vector2(AbstractCard.RAW_W * 0.45f, AbstractCard.RAW_H * 0.45f + offset_y);

        RenderHelpers.DrawOnCardAuto(sb, card, GR.Common.Images.Badges.Base_Badge.Texture(), new Vector2(AbstractCard.RAW_W * 0.45f, AbstractCard.RAW_H * 0.45f + offset_y), 64, 64, color, alpha, 1);
        RenderHelpers.DrawOnCardAuto(sb, card, texture, new Vector2(AbstractCard.RAW_W * 0.45f, AbstractCard.RAW_H * 0.45f + offset_y), 64, 64, Color.WHITE, alpha, 1);
        RenderHelpers.DrawOnCardAuto(sb, card, GR.Common.Images.Badges.Base_Border.Texture(), new Vector2(AbstractCard.RAW_W * 0.45f, AbstractCard.RAW_H * 0.45f + offset_y), 64, 64, Color.WHITE, alpha, 1);
        if (isInfinite) {
            RenderHelpers.DrawOnCardAuto(sb, card, GR.Common.Images.Badges.Base_Infinite.Texture(), new Vector2(AbstractCard.RAW_W * 0.45f, AbstractCard.RAW_H * 0.45f + offset_y), 64, 64, Color.WHITE, alpha, 1);
        }

        if (text != null)
        {
            final BitmapFont font = EYBFontHelper.CardIconFont_Large;

            offset = new Vector2(0.5f, 0.425f + offset_y);
            font.getData().setScale(0.5f * card.drawScale);
            RenderHelpers.WriteOnCard(sb, card, font, text, offset.x, offset.y, Settings.CREAM_COLOR, true);
            RenderHelpers.ResetFont(font);
        }

        return 38;
    }
}
