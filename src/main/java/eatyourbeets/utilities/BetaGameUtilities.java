package eatyourbeets.utilities;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.cards.base.EYBCard;

import static eatyourbeets.resources.GR.Enums.CardTags.*;
import static eatyourbeets.resources.GR.Enums.CardTags.HASTE;

public class BetaGameUtilities extends GameUtilities
{
    // TODO Convenience Function for adding tags to cards
    // TODO I would also like to add support for an Autoplay tag, which I see has been defined in GR.Enums. However, I'm not sure if it is actually used (i.e. Essence_Eruza doesn't use this tag)
    public static void ModifyCardTag(AbstractCard card, AbstractCard.CardTags tag, boolean value)
    {
        if (tag == null || card == null) {
            return;
        }
        EYBCard eCard = JUtils.SafeCast(card, EYBCard.class);
        if (eCard != null) {
            if (PURGE.equals(tag)) {
                eCard.SetPurge(value);
            }
            else if (DELAYED.equals(tag)) {
                eCard.SetDelayed(value);
            }
            else if (HASTE.equals(tag)) {
                eCard.SetHaste(value);
            }
            else if (LOYAL.equals(tag)) {
                eCard.SetLoyal(value);
            }
            else if (value) {
                eCard.tags.add(tag);
            }
            else {
                eCard.tags.remove(tag);
            }
        }
        else if (value) {
            card.tags.add(tag);
        }
        else {
            card.tags.remove(tag);
        }
    }

}
