package eatyourbeets.resources;

import eatyourbeets.resources.CardTooltips;
import eatyourbeets.resources.common.CommonImages;
import eatyourbeets.utilities.PCLColors;

public class BetaCardTooltips extends CardTooltips
{

    // TODO Here is an example of how to use tooltip backgrounds for badges
    public void InitializeIcons()
    {
        super.InitializeIcons();
        CommonImages.Badges badges = GR.Common.Images.Badges;
        Afterlife.SetIcon(badges.Afterlife.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_AFTERLIFE);
        Autoplay.SetIcon(badges.Autoplay.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_AUTOPLAY);
        Delayed.SetIcon(badges.Delayed.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_DELAYED);
        Ethereal.SetIcon(badges.Ethereal.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_ETHEREAL);
        Exhaust.SetIcon(badges.Exhaust.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_EXHAUST);
        Harmonic.SetIcon(badges.Harmonic.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_HARMONIC);
        Haste.SetIcon(badges.Haste.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_HASTE);
        HasteInfinite.SetIcon(badges.Haste.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_HASTE);
        HasteOnce.SetIcon(badges.Haste.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_HASTE);
        Innate.SetIcon(badges.Innate.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_INNATE);
        Loyal.SetIcon(badges.Loyal.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_LOYAL);
        Purge.SetIcon(badges.Purge.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_PURGE);
        Retain.SetIcon(badges.Retain.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_RETAIN);
        RetainInfinite.SetIcon(badges.Retain.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_RETAIN);
        RetainOnce.SetIcon(badges.Retain.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_RETAIN);
        Unplayable.SetIcon(badges.Unplayable.Texture(), 6).SetBadgeBackground(PCLColors.COLOR_UNPLAYABLE);
    }
}
